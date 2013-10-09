/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.valueobject;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.gen.ProbLabor;
import org.cidrz.webapp.dynasite.dao.*;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.HibernateUtil;
import org.cidrz.webapp.dynasite.utils.sort.FlowOrderComparator;
import org.cidrz.webapp.dynasite.exception.PersistenceException;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Oct 14, 2004
 * Time: 11:42:35 AM
 */

/**
 * @hibernate.class table="task_list"
 * mutable="true"
 */

public class TaskList implements Identifiable {

    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    private Long id;
    private Patient patientId;
    private List taskList;
    private Long currentFlow;
    private Long mostRecentForm;
    private Flow nextFlow;
    private Form nextForm;
    private String message;
    private Long currentFlowId;

    /**
     * @return
     * @hibernate.id unsaved-value="0"
     * generator-class="identity"
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return
     * @hibernate.many-to-one column="patient_id"
     * cascade="none"
     */

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }


    public List getTaskList() {
        return taskList;
    }

    public void setTaskList(List taskList) {
        this.taskList = taskList;
    }


    public Long getCurrentFlow() {
        return currentFlow;
    }

    public void setCurrentFlow(Long currentFlow) {
        this.currentFlow = currentFlow;
    }


    public Long getMostRecentForm() {
        return mostRecentForm;
    }

    public void setMostRecentForm(Long mostRecentForm) {
        this.mostRecentForm = mostRecentForm;
    }


    public Flow getNextFlow() {
        return nextFlow;
    }

    public void setNextFlow(Flow nextFlow) {
        this.nextFlow = nextFlow;
    }

    public Form getNextForm() {
        return nextForm;
    }

    public void setNextForm(Form nextForm) {
        this.nextForm = nextForm;
    }

    /**
     * Some of the flows may need to provide instructional message to user, such as Problem/Labour flow.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCurrentFlowId() {
        return currentFlowId;
    }

    public void setCurrentFlowId(Long currentFlowId) {
        this.currentFlowId = currentFlowId;
    }

    /**
     * Populates patient TaskList. If this is the Problem/Labour flow, checks if 1st form has been done.
     * Display restricted list of tasks if pregnancy has ended.
     *
     * @param conn
     * @param tasklist
     * @param patientId
     * @param flowId
     * @param pregnancyId
     * @param sessionPatient
     * @return
     * @throws PersistenceException
     */
    public TaskList getTaskList(Connection conn, TaskList tasklist, Long patientId, Long flowId, Long pregnancyId, SessionPatient sessionPatient) throws PersistenceException, SQLException, ServletException, ObjectNotFoundException {
        List tasks = new ArrayList();

        boolean isProblemLabourFormdone = false;
        boolean isPartographStarted = false;
        boolean isDeliveryCompleted = false;
        boolean readyForPartograph = false;
        // boolean isCervixDilatated = false;
        boolean isObservationsStarted = false;
        boolean isPhaseIntrapartum = false;
        Long problemLabourFormId = Long.valueOf("65");
        // Long partographFormId = Long.valueOf("79");
        String message = "";
        // PartographStatus partographStatus = null;
        Boolean motherStatus = sessionPatient.getMotherStatus();

        // first test if user has completed initial labour form.
        if ((flowId.equals(Long.valueOf("1"))) || (flowId.equals(Long.valueOf("2"))) || (flowId.equals(Long.valueOf("7")))) {
            if (pregnancyId != null) {
                isProblemLabourFormdone = EncountersDAO.checkEncounter(conn, patientId, pregnancyId, problemLabourFormId).booleanValue();

                // isProblemLabourFormdone = testForEncounter(problemLabourFormId, patientId, pregnancyId, isProblemLabourFormdone, tasklist);
                if (isProblemLabourFormdone == false) {
                    message = "This patient has not been admitted for labor management. If you desire to admit " +
                            "this patient, please complete the Problem Visit/Labor Form.";
                    tasklist.setMessage(message);
                }
                isPhaseIntrapartum = getPhaseIntrapartum(conn, patientId, pregnancyId, problemLabourFormId, isPhaseIntrapartum);
                isObservationsStarted = EncountersDAO.checkEncounter(conn, patientId, pregnancyId, new Long("17"));
                // now test if delivery summary form has been submitted
                isDeliveryCompleted = (EncountersDAO.checkEncounter(conn, patientId, pregnancyId, new Long("66")));
                if (isDeliveryCompleted != true) {
                    isPartographStarted = (EncountersDAO.checkEncounter(conn, patientId, pregnancyId, new Long("79")));
                    if (isPartographStarted) {
                        message = "The partograph has been started.";
                        if (sessionPatient.getPartographStatus() == null) {
                            SessionPatientDAO.updatePartographStatus(conn, patientId, sessionPatient);
                        }
                    }
                } else {
                    message = "The delivery is completed.";
                }
                tasklist.setMessage(message);
            }
            readyForPartograph = isReadyForPartograph(conn, patientId, pregnancyId, readyForPartograph);

        }
        // create a task for each form.
        // do not select Labour forms if user has not submitted form 65 (isProblemLabourFormdone == true)
        // check what FormType is the form - some forms are infant or mother-only.
        // Don't want Routine Antenatal or Puerperium forms in list.

        int formType = 0;
        //List all_forms = TaskListDAO.getFormsForTask(flowId, isProblemLabourFormdone, motherStatus);
        List all_forms = new ArrayList();
        List allTasks = (List) getFlowTasks(flowId);
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = (Task) allTasks.get(i);
            if (flowId.equals(Long.valueOf("7")) && isProblemLabourFormdone == false) {
                if (task.getFlowId().equals(Long.valueOf("7")) && task.getFormId() == 65) {
                    all_forms.add(task);
                }
            } else {
               if (!sessionPatient.getMotherStatus().equals(Boolean.TRUE)) {
                   // select infant-only  or mother/infant forms - newborn eval is type 4
                   if (task.getTaskType() == 2 || task.getTaskType() == 4) {
                   all_forms.add(task);
                   }
               } else {
                   if (task.getTaskType() == 1 || task.getTaskType() == 4) {
                   all_forms.add(task);
                   }
               }
            }
        }
        List all_encounters = null;
        if (pregnancyId != null) {
            // create a task for each encounter
            all_encounters = TaskListDAO.getEncounterTasks(conn, patientId, pregnancyId, flowId);
            if (flowId.intValue() == 2) {
                List histEncounters =  TaskListDAO.getEncounterTasksHistory(conn, patientId, pregnancyId);
                if (histEncounters !=null) {
                all_encounters.addAll(histEncounters);
                }
            }
        }

        /// disabled Newborn display in mother task - it is not really adding all that much...
        /*if (flowId == 4) {
        	// Add links to children
            if (motherStatus.equals(Boolean.TRUE)) {
                // List children = TaskListDAO.createChildrenAsTasks(conn, patientId, pregnancyId);
            	List children = SessionPatientDAO.getChildren(conn, patientId, pregnancyId);
            	// Loop through the children and see if newborn eval or newborn record forms have been submitted
            	for (Iterator iterator = children.iterator(); iterator.hasNext();) {
    				Patient newborn = (Patient) iterator.next();
    				// see if newborn eval submitted for this infant
    				try {
    					Task newbornEvalTask = TaskListDAO.getNewbornTask(conn, newborn.getId());
    					tasks.add(newbornEvalTask);
    				} catch (ObjectNotFoundException e) {
    					//AuditInfo auditInfo = new AuditInfo(newborn.getLastModified(),newborn.getCreated(),)
    					String taskName = newborn.getFirstName() + " " + newborn.getSurname() + " Newborn Evaluation";
    					Task createSubmitFormTask = new Task(new Long("23"),new Long("3"), taskName, newborn.getAuditInfo(), null, 1, "Task");
    					createSubmitFormTask.setActive(true);
    					createSubmitFormTask.setPatientId(newborn.getId());
    					createSubmitFormTask.setPregnancyId(newborn.getPregnancyId());
    					createSubmitFormTask.setSex(newborn.getSex());
    					tasks.add(createSubmitFormTask);
    				}
    			}
                // tasks.addAll(children);
            }
        }*/

        // lastModifiedMap is used to display last modified date for most recent form and is displayed for forms that have multiple submissions,
        // but only the most recent is displayed, as in preg. dating
        HashMap lastModifiedMap = new HashMap();
        // now we determine if this form has been completed
        Iterator iforms = all_forms.iterator();
        // while looping through each form task, loop through each encounter task to see if that form has been completed

        while (iforms.hasNext()) {
            Task this_form = (Task) iforms.next();
            int cnt = 0;
            int encounter_count = 0;
            this_form.setPatientId(patientId);
            Iterator encounter_tasks = all_encounters.iterator();
            while (encounter_tasks.hasNext()) {
                encounter_count += 1;
                Task this_encounter = (Task) encounter_tasks.next();

                if (encounter_count == 1) {
                    currentFlow = this_encounter.getFlowId();
                    mostRecentForm = this_encounter.getFormId();
                }

                if (this_encounter.getFormId().equals(this_form.getFormId())) {
                    cnt += 1;
                    // Deactivate tasks that have exceeded their max number of submissions
                    if (cnt >= this_form.getMaxSubmissions().intValue()) {
                        this_form.setActive(false);
                        this_form.setPatientId(patientId);
                        tasks.add(this_form);
                    }

                    Timestamp lastModifiedEnc = this_encounter.getAuditInfo().getLastModified();
                    try {
                        Timestamp lastModified = (Timestamp) lastModifiedMap.get(this_encounter.getFormId());
                        if (lastModifiedEnc.getTime() > lastModified.getTime()) {
                            this_encounter.getAuditInfo().setSiteName(this_encounter.getSiteName());
                            // add it to the map
                            lastModifiedMap.put(this_encounter.getFormId(), this_encounter.getAuditInfo());
                        }
                    } catch (Exception e) {
                        // if not found, add it
                        this_encounter.getAuditInfo().setSiteName(this_encounter.getSiteName());
                        lastModifiedMap.put(this_encounter.getFormId(), this_encounter.getAuditInfo());
                    }
                    this_encounter.setActive(false);
                    tasks.add(this_encounter);
                }
            }
            this_form.setSubmissions(new Integer(cnt));
            if (cnt < this_form.getMaxSubmissions().intValue()) {
                // is this the Newborn Evaluation form?
                if (this_form.getFormId().intValue() == 23) {
                	if (motherStatus.equals(Boolean.FALSE)) {
                		this_form.setActive(true);
                        tasks.add(this_form);
                	}
                    // is it ok to display observations? display only if it has been started or in intrapartum phase
                } else if (this_form.getFormId().intValue() == 17) {
                    if (isPhaseIntrapartum || isObservationsStarted) {
                        this_form.setActive(true);
                    this_form.setPatientId(patientId);
                    tasks.add(this_form);
                    }
                    // is it ok to display partograph?
                } else if (this_form.getFormId().intValue() == 79) {
                    if (readyForPartograph) {
                        this_form.setActive(true);
                        this_form.setPatientId(patientId);
                        tasks.add(this_form);
                    }
                    // if partograph has been started, do not allow any more submissions of problem/labour visit
                } else if (this_form.getFormId().intValue() == 65) {
                    if (isDeliveryCompleted == false) {
                            this_form.setActive(true);
                            this_form.setPatientId(patientId);
                            tasks.add(this_form);
                    }
                } else {
                    this_form.setActive(true);
                    this_form.setPatientId(patientId);
                    tasks.add(this_form);
                }
            }
        }
        // Add Auditinfo for most recent task for forms that have multiple encounters behind them
        for (int i = 0; i < tasks.size(); i++) {
            Task task = (Task) tasks.get(i);
            int formId = task.getFormId().intValue();
            if ((formId == 2) || (formId == 17) || (formId == 55)  || (formId == 79) || (formId == 80) || (formId == 81) || (formId == 82)) {
                // Task.getAuditInfo initialises w/ timestamp - we need to set this to null; otherwise, if there is no
                // entry for this task in the lastModifiedMap, it will display the date the application started.
                task.setAuditInfo(null);
                AuditInfo lastModifiedTask = (AuditInfo) lastModifiedMap.get(task.getFormId());
                if (lastModifiedTask != null) {
                    task.setAuditInfo(lastModifiedTask);
                    String siteName = null;
                    try {
                        siteName = lastModifiedTask.getSiteName();
                        task.setSiteName(siteName);
                    } catch (Exception e) {
                        // null
                    }
                }
            }
        }
        tasklist.setTaskList(tasks);
        tasklist.setCurrentFlow(currentFlow);
        tasklist.setCurrentFlowId(flowId);
        tasklist.setMostRecentForm(mostRecentForm);
        tasklist.setNextFlow(nextFlow);
        tasklist.setNextForm(nextForm);
        return tasklist;
    }

    private boolean getPhaseIntrapartum(Connection conn, Long patientId, Long pregnancyId, Long problemLabourFormId, boolean phaseIntrapartum) throws ServletException, SQLException {
        try {
            List probLabourVisits = EncountersDAO.getAll(conn, patientId, pregnancyId, problemLabourFormId, ProbLabor.class);
            for (int i = 0; i < probLabourVisits.size(); i++) {
                ProbLabor probLabor = (ProbLabor) probLabourVisits.get(i);
                if (probLabor.getField1487() !=null) {   // phase
                Integer phase = probLabor.getField1487();
                    if (phase.intValue() == 2755) {    // intrapartum
                        phaseIntrapartum = true;
                    }
                }
            }
        } catch (IOException e) {
            log.error(e);
        }
        return phaseIntrapartum;
    }

    private boolean isReadyForPartograph(Connection conn, Long patientId, Long pregnancyId, boolean readyForPartograph) throws ServletException, SQLException {
        List probLabourVisits = null;
        try {
             probLabourVisits = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("65"), ProbLabor.class);
        } catch (IOException e) {
            log.error(e);
        }

        if (probLabourVisits != null) {
            for (int i = 0; i < probLabourVisits.size(); i++) {
                ProbLabor probLabor = (ProbLabor) probLabourVisits.get(i);
                Integer field325 = probLabor.getField325();
                if (field325 !=null && field325 > 3) {
                    readyForPartograph = true;
                    break;
                }
            }
        }
        return readyForPartograph;
    }

    private boolean testForEncounter(Long formId, Long patientId, Long pregnancyId, boolean problemLabourFormdone, TaskList tasklist) throws PersistenceException {
        try {
            List object = null;
            try {
                Session session = HibernateUtil.currentSession();
                Transaction tx = session.beginTransaction();
                Query query = session.createQuery("select er.id from EncounterData er where er.form.id = :problemLabourFormId " +
                        " and er.pregnancy.id = :pregnancyId " +
                        "and er.patient.id= :patientId ");
                query.setLong("problemLabourFormId", formId.longValue());
                query.setLong("patientId", patientId.longValue());
                query.setLong("pregnancyId", pregnancyId.longValue());
                object = query.list();
                tx.commit();
                HibernateUtil.closeSession();
            } catch (NonUniqueResultException e) {
                // it's ok....
            } catch (Exception e) {
                throw new PersistenceException(e);
            }
            if (object.size() == 0) {
                throw new ObjectNotFoundException();
            }
        } catch (ObjectNotFoundException e) {
            problemLabourFormdone = false;
        }
        return problemLabourFormdone;
    }

    // Identify what forms that may have been skipped but still need to be done.
    // These are called missing forms.
    public List getProblemListTasks(Connection conn, SessionPatient sessionPatient) throws PersistenceException, ObjectNotFoundException, SQLException, ServletException {
        if (sessionPatient.getCurrentPregnancyId().intValue() == -1) {   // new pregnancy
            return new ArrayList();
        }
        List tasks = new ArrayList();
        boolean readyForPartograph = false;
        List all_forms = new ArrayList();
        Long currentFlowId = null;
        //Long currentFlowOrder = null;
        //Flow currentFlow = (Flow) DynaSiteObjects.getFlowMap().get(sessionPatient.getCurrentFlowId());
        Long mostRecentFlowId = sessionPatient.getCurrentFlowId();
        //Long mostRecentFlowOrder = currentFlow.getFlowOrder();
        Boolean noPreviousPregnancies = sessionPatient.getNoPreviousPregnancies();
        Long patientId = sessionPatient.getId();
        Long pregnancyId = sessionPatient.getCurrentPregnancyId();
        Boolean isPartographStarted = (EncountersDAO.checkEncounter(conn, patientId, pregnancyId, new Long("79")));
        readyForPartograph = isReadyForPartograph(conn, patientId, pregnancyId, readyForPartograph);
        //Long historyFlow = new Long("1");
        currentFlowId = mostRecentFlowId;
        //currentFlowOrder = mostRecentFlowOrder;
        Boolean currMedFormNeeded = Boolean.FALSE;
        try {
            currMedFormNeeded =  EncountersDAO.checkEncounterfield(conn, patientId, pregnancyId, "medsurghist", "currently_taking_meds_95", "1");
        } catch (SQLException e) {
            log.error(e);
        } catch (ServletException e) {
             log.error(e);
        }

       // if (mostRecentFlowId.intValue() == 9) {
            // just filled out the patient registration form. need to sent to antenatal/history flow.
            //currentFlowOrder = historyFlow;
      //  }

        // check if problem/labour visit has been done
        boolean isProblemLabourFormdone = true;
        Long problemLabourFormId = Long.valueOf("65");
        isProblemLabourFormdone = EncountersDAO.checkEncounter(conn, sessionPatient.getId(), sessionPatient.getCurrentPregnancyId(), problemLabourFormId).booleanValue();

        // now get list of all forms, excluding routine antenatal and puerperal forms - we don't want to display those.
        //all_forms = TaskListDAO.getFormsForTask(currentFlowId, isProblemLabourFormdone, sessionPatient.getMotherStatus());
        List allTasks = (List) getFlowTasks(currentFlowId);
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = (Task) allTasks.get(i);
            if (currentFlowId.equals(Long.valueOf("7")) && isProblemLabourFormdone == false) {
                if (task.getFlowId().equals(Long.valueOf("7")) && task.getFormId() == 65) {
                    all_forms.add(task);
                }
            } else {
                if (!sessionPatient.getMotherStatus().equals(Boolean.TRUE)) {
                    // select infant-only forms
                    if (task.getTaskType() == 2) {
                        all_forms.add(task);
                    }
                } else {
                    if (task.getTaskType() == 1) {
                        all_forms.add(task);
                    }
                }
            }
        }
        // get all of the encounters
        List all_encounters = null;
        all_encounters = TaskListDAO.getEncounterTasks(conn, sessionPatient.getId(), sessionPatient.getCurrentPregnancyId(), currentFlowId);
            if (currentFlowId.intValue() == 2) {
                List histEncounters =  TaskListDAO.getEncounterTasksHistory(conn, patientId, pregnancyId);
                if (histEncounters !=null) {
                all_encounters.addAll(histEncounters);
                }
            }
        Iterator iforms = all_forms.iterator();
        // loop through the forms for this flow
        while (iforms.hasNext()) {
            Task this_form = (Task) iforms.next();
            int cnt = 0;
            Iterator encounter_tasks = all_encounters.iterator();
            // loop through the encounters already completed for this flow
            while (encounter_tasks.hasNext()) {
                Task this_encounter = (Task) encounter_tasks.next();
                if (this_encounter.getFormId().equals(this_form.getFormId())) {
                    cnt = 1;
                }
            }
            if (cnt == 0) {
                // exclude unneceesary forms
                if ((this_form.getFormId().intValue() == 2) & ((noPreviousPregnancies))) {
                    // skip
                } else if ((this_form.getFormId().intValue() == 17) & (isPartographStarted)) {
                    // skip observations if labour form has not been submitted.
                } else if ((this_form.getFormId().intValue() == 23)) {
                    // skip newborn evaluation, form 23
                } else if ((this_form.getFormId().intValue() == 55) && (!currMedFormNeeded)) {
                    // skip  current medicine, form 55
                } else if ((this_form.getFormId().intValue() == 78)) {
                    // skip Postnatal Maternal Problem Visit, form 78
                } else if (this_form.getFormId().intValue() == 17) {
                    if (isProblemLabourFormdone) {
                        this_form.setActive(true);
                        tasks.add(this_form);
                    }
                    // is it ok to display partograph?
                } else if (this_form.getFormId().intValue() == 79) {
                    if (readyForPartograph) {
                        this_form.setActive(true);
                    tasks.add(this_form);
                    }
                    // if partograph has been started, do not allow any more submissions of problem/labour visit
                } else {
                    // show these as missing forms
                    this_form.setActive(true);
                    tasks.add(this_form);
                }
            }
        }
        return tasks;
    }

    /**
     * Sets up a fresh list of Tasks
     * Since we are modifying each Task object (setting the patientId and other fields), we will clone this object.
     * @param flowId
     * @return ArrayList tasks
     */
    public static ArrayList<Task> getFlowTasks(Long flowId) {
    	ArrayList<Task> tasks = new ArrayList<Task>();
    	List<Task> dynaTasks = (List<Task>) DynaSiteObjects.getTasksForFlow().get(flowId);
    	for (Iterator<Task> iterator = dynaTasks.iterator(); iterator.hasNext();) {
			Task origTask = (Task) iterator.next();
			Task task = new Task();
            task.setLabel(origTask.getLabel());
            task.setFormId(origTask.getFormId());
            task.setMaxSubmissions(origTask.getMaxSubmissions());
            task.setTaskType(origTask.getTaskType());
            task.setFlowOrder(origTask.getFlowOrder());
            task.setFlowId(flowId);
            tasks.add(task);
		}
    	FlowOrderComparator foc = new FlowOrderComparator();
        Collections.sort(tasks, foc);
        return tasks;
    }
}