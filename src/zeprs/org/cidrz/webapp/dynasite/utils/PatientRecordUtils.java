/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.utils;

import java.beans.PropertyDescriptor;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.cidrz.project.zeprs.report.valueobject.CurrentPregnancyStatus;
import org.cidrz.project.zeprs.report.valueobject.HivStamp;
import org.cidrz.project.zeprs.valueobject.BaseEncounter;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.DeliverySum;
import org.cidrz.project.zeprs.valueobject.gen.LabTest;
import org.cidrz.project.zeprs.valueobject.gen.NewbornEval;
import org.cidrz.project.zeprs.valueobject.gen.PartographStatus;
import org.cidrz.project.zeprs.valueobject.gen.PatientRegistration;
import org.cidrz.project.zeprs.valueobject.gen.PregnancyDating;
import org.cidrz.project.zeprs.valueobject.gen.PrevPregnancies;
import org.cidrz.project.zeprs.valueobject.gen.ProbLabor;
import org.cidrz.project.zeprs.valueobject.gen.SafeMotherhoodCare;
import org.cidrz.project.zeprs.valueobject.gen.SmCounselingVisit;
import org.cidrz.project.zeprs.valueobject.partograph.Cervix;
import org.cidrz.project.zeprs.valueobject.report.PatientStatusReport;
import org.cidrz.project.zeprs.valueobject.report.gen.ArvRegimenReport;
import org.cidrz.project.zeprs.valueobject.report.gen.DeliverySumReport;
import org.cidrz.project.zeprs.valueobject.report.gen.NewbornEvalReport;
import org.cidrz.project.zeprs.valueobject.report.gen.SafeMotherhoodCareReport;
import org.cidrz.project.zeprs.valueobject.report.gen.SmCounselingVisitReport;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.CommentDAO;
import org.cidrz.webapp.dynasite.dao.EncounterArchiveDAO;
import org.cidrz.webapp.dynasite.dao.EncounterValueArchiveDAO;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.FormDAO;
import org.cidrz.webapp.dynasite.dao.LabTestDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeArchiveDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.PatientStatusDAO;
import org.cidrz.webapp.dynasite.dao.PregnancyDAO;
import org.cidrz.webapp.dynasite.dao.PregnancyDatingDAO;
import org.cidrz.webapp.dynasite.dao.ProblemArchiveDAO;
import org.cidrz.webapp.dynasite.dao.ProblemDAO;
import org.cidrz.webapp.dynasite.dao.RprTestDAO;
import org.cidrz.webapp.dynasite.dao.SessionPatientDAO;
import org.cidrz.webapp.dynasite.dao.partograph.CervixDAO;
import org.cidrz.webapp.dynasite.dao.partograph.PartographDAO;
import org.cidrz.webapp.dynasite.dao.reports.ReportDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.remote.Newborn;
import org.cidrz.webapp.dynasite.remote.PatientId;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.rules.OutcomeArchive;
import org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome;
import org.cidrz.webapp.dynasite.rules.impl.InfoOutcome;
import org.cidrz.webapp.dynasite.rules.impl.OutcomeImpl;
import org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.valueobject.Drugs;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.FieldEnumeration;
import org.cidrz.webapp.dynasite.valueobject.Flow;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.Partograph;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Pregnancy;
import org.cidrz.webapp.dynasite.valueobject.Problem;
import org.cidrz.webapp.dynasite.valueobject.ProblemArchive;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.valueobject.Task;
import org.cidrz.webapp.dynasite.valueobject.TaskList;

/**
 * Methods that help assemble objects for display
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Jun 8, 2005
 * Time: 6:24:15 PM
 */

public class PatientRecordUtils {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(PatientRecordUtils.class);

    public static List assembleProblemTaskList(Connection conn, Long patientId, Long pregnancyId, Boolean status, SessionPatient sessionPatient) throws SQLException, ObjectNotFoundException, PersistenceException, ServletException, IllegalAccessException, InvocationTargetException {
        if (pregnancyId == -1) {    // new pregnancy
            List activeProblems = new ArrayList();
            // long-term problems
            List longTermProblems = sessionPatient.getLongTermProblems();
            if (longTermProblems != null && longTermProblems.size() > 0 && status == true) {
                Task ltp = new Task();
                ltp.setLabel("Long-term Problems");
                ltp.setMessageType("longterm");
                activeProblems.add(ltp);
                activeProblems.addAll(longTermProblems);
            }
            return activeProblems;
        }
        List activeProblems = new ArrayList();

        Long parentId = null;
        try {
            parentId = sessionPatient.getParentId();
        } catch (Exception e) {
            log.error(e);
        }
        Date datePregnancyEnd = null;
        try {
            datePregnancyEnd = sessionPatient.getDatePregnancyEnd();
        } catch (Exception e) {
            log.error(e);
        }

        // get the tasks and put them into activeProblems
        // but first check if this patient has a parent. if true, then you don't need to list missing forms.
        // we still need to query other forms to get hiv status for the child.
        // forms(tasks) are only required for the mother.
        // also, you are viewing previous pregnancy, also do not need these tasks.

        // begin zeprs-specific code
        if (status.equals(Boolean.TRUE)) {
            if ((datePregnancyEnd == null)) {
            	getPendingLabs(conn, patientId, pregnancyId, sessionPatient, activeProblems, parentId);
            }
        }
    	// end zeprs-specific code

        List activeProbList = assembleProblemList(conn, patientId, pregnancyId, status);
        Task task = new Task();
        task.setLabel("System-generated Outcomes");
        task.setMessageType("system");
        if (activeProbList.size() > 0) {
            activeProblems.add(task);
        }

        activeProblems.addAll(activeProbList);

        if (parentId != null) {
            Task childTask = new Task();
            childTask.setLabel("Mother Problems");
            childTask.setMessageType("mother");
            activeProblems.add(childTask);
            List probs = assembleProblemList(conn, parentId, pregnancyId, status);
            activeProblems.addAll(probs);
        }

        if (sessionPatient.getChildren().size() != 0) {
            Task childTask = new Task();

            List children = sessionPatient.getChildren();
            List childActiveProblems = new ArrayList();
            boolean messageSet = false;
            for (int i = 0; i < children.size(); i++) {
                Patient child = (Patient) children.get(i);
                List probs = assembleProblemList(conn, child.getId(), pregnancyId, status);
                if (probs.size() > 0 & messageSet == false) {
                    childTask.setLabel("Infant Problems");
                    childTask.setMessageType("infant");
                    activeProblems.add(childTask);
                    messageSet = true;
                }
                childActiveProblems.addAll(probs);
            }
            activeProblems.addAll(childActiveProblems);
        }

        // Create missing form tasks
        // if viewing prev pregnancy, do not display
        if (sessionPatient.getDatePregnancyEnd() == null) {
            if (parentId == null && status.equals(Boolean.TRUE)) {
                TaskList tasklist = new TaskList();
                List tasks = null;
                tasks = tasklist.getProblemListTasks(conn, sessionPatient);
                Iterator alltasks = tasks.iterator();

                boolean messageSet = false;
                while (alltasks.hasNext()) {
                    Task thisTask = (Task) alltasks.next();
                    if (thisTask.isActive()) {
                        if (thisTask.getLabel() != null) {
                            if (messageSet == false) {
                                Task missingForms = new Task();
                                missingForms.setLabel("Missing Forms");
                                missingForms.setMessageType("missing");
                                activeProblems.add(missingForms);
                                messageSet = true;
                            }
                            activeProblems.add(thisTask);
                        }
                    }
                }
            }
        }

        // long-term problems
        List longTermProblems = sessionPatient.getLongTermProblems();
        if (longTermProblems != null && longTermProblems.size() > 0 && status == true) {
            Task ltp = new Task();
            ltp.setLabel("Long-term Problems");
            ltp.setMessageType("longterm");
            activeProblems.add(ltp);
            activeProblems.addAll(longTermProblems);
        }
        return activeProblems;
    }

    /**
     * Add missing labs to the activeProblems list. Only  if ((datePregnancyEnd == null)) {
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param sessionPatient
     * @param activeProblems
     * @param parentId
     * @throws NumberFormatException
     * @throws ServletException
     * @throws SQLException
     */
    private static void getPendingLabs(Connection conn, Long patientId, Long pregnancyId, SessionPatient sessionPatient, List activeProblems, Long parentId)
    throws NumberFormatException, ServletException, SQLException {
    	// run the counsel visit query because you'll use some of this info (hivstatus) for child.
    	List counselVisits = null;
    	Long formId;
    	Long motherId = null;

    	if (parentId != null) {
    		motherId = parentId;
    	} else {
    		motherId = patientId;
    	}
    	// now get a list of all pending lab studies.
    	List labTests = null;

    	try {
    		labTests = LabTestDAO.getAll(conn, patientId, pregnancyId);
    	} catch (ServletException e) {
    		log.error(e);
    	} catch (SQLException e) {
    		log.error(e);
    	}

    	for (int i = 0; i < labTests.size(); i++) {
    		Task labTest = (Task) labTests.get(i);
    		FieldEnumeration fieldEnum = null;
    		if (labTest.getLabel() != null) {
    			try {
    				fieldEnum = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get(Long.valueOf(labTest.getLabel()));
    				labTest.setLabel(fieldEnum.getEnumeration());
    			} catch (NumberFormatException e) {
    				labTest.setLabel("Lab Test");
    				// log.error("Error geting enum: " + "labTest.getLabel(): " + labTest.getLabel() + " patientId: " + patientId + " encounterId: " + labTest.getEncounterId());
    				//log.error(e.getStackTrace().toString());
    			}
    		} else {
    			// log.error("labTest.getLabel() is null: " + labTest.getLabel() + " patientId: " + patientId + " encounterId: " + labTest.getEncounterId());
    		}
    	}

    	if (labTests.size() > 0) {
    		Task missingLabs = new Task();
    		missingLabs.setLabel("Missing Lab Results");
    		missingLabs.setMessageType("missing");
    		activeProblems.add(missingLabs);
    		activeProblems.addAll(labTests);
    	}

    	// now get a list of all pending rpr Tests.
    	List rprTests = null;

    	try {
    		rprTests = RprTestDAO.getAll(conn, patientId, pregnancyId);
    	} catch (ServletException e) {
    		log.error(e);
    	} catch (SQLException e) {
    		log.error(e);
    	}

    	if (rprTests.size() > 0) {
    		Task missingRprs = new Task();
    		missingRprs.setLabel("Missing RPR Screen");
    		missingRprs.setMessageType("missing");
    		activeProblems.add(missingRprs);
    		activeProblems.addAll(rprTests);
    	}

    	// mother only rules
    	if (parentId == null) {
    		boolean deliveryCompleted = sessionPatient.getDeliveryCompleted().booleanValue();
    		// EGA tests
    		int egaWeeks = 0;
    		if (sessionPatient.getCurrentEgaCalc() != null) {
    			egaWeeks = sessionPatient.getCurrentEgaCalc() / 7;
    		}

    		/*if (smCare == null) {
    		             Task missingTask = new Task();
    		             missingTask.setLabel("Please submit Safe motherhood Care form.");
    		             missingTask.setMessageType("missing");
    		             activeProblems.add(missingTask);
    		         }*/

    		if (egaWeeks >= 36 && !deliveryCompleted) {
    			// test VCT visits
    			formId = new Long("91");  // sm Care
    			try {
    				counselVisits = EncountersDAO.getAll(conn, motherId, pregnancyId, formId, SmCounselingVisit.class);
    			} catch (IOException e) {
    				log.error(e);
    			}
    			int counselVisitYesCount = 0;
    			if (counselVisits.size() > 0) {
    				for (int i = 0; i < counselVisits.size(); i++) {
    					SmCounselingVisit smCounselingVisit = (SmCounselingVisit) counselVisits.get(i);
    					if (smCounselingVisit.getField1930() != null && smCounselingVisit.getField1930() == 1) {
    						counselVisitYesCount += 1;
    					}
    				}
    			}
    			if (counselVisitYesCount < 2) {
    				Task missingVct = new Task();
    				missingVct.setLabel("Patient needs 2 VCT sessions before 36 weeks.");
    				missingVct.setMessageType("missing");
    				activeProblems.add(missingVct);
    			}
    			// test TT visits and hiv status
    			formId = new Long("92");  //safeMotherhoodCare
    			SafeMotherhoodCare smCare = null;
    			try {
    				smCare = (SafeMotherhoodCare) EncountersDAO.getOne(conn, motherId, pregnancyId, formId);
    			} catch (IOException e) {
    				log.error(e);
    			} catch (ClassNotFoundException e) {
    				log.error(e);
    			} catch (ObjectNotFoundException e) {
    				// it's ok
    			}

    			if (smCare != null) {
    				Byte tt1 = smCare.getField1887();
    				Byte tt2 = smCare.getField1888();
    				Byte childhoodImm = smCare.getField107();

    				if (childhoodImm != null && childhoodImm == 1) {
    					if (tt1 != null && tt1 != 1) {
    						Task missingTask = new Task();
    						missingTask.setLabel("Please dispense TT1.");
    						missingTask.setMessageType("missing");
    						activeProblems.add(missingTask);
    					}
    				} else {
    					if (tt1 != null && tt2 != null) {
    						if (tt2 != 1 || tt1 != 1) {
    							Task missingTask = new Task();
    							missingTask.setLabel("Mother needs at least 2 TT immunisations before 36 weeks.");
    							missingTask.setMessageType("missing");
    							activeProblems.add(missingTask);
    						}
    					} else {
    						Task missingTask = new Task();
    						missingTask.setLabel("Mother needs at least 2 TT immunisations before 36 weeks.");
    						missingTask.setMessageType("missing");
    						activeProblems.add(missingTask);
    					}
    				}
    			} else {
    				Task missingTask = new Task();
    				missingTask.setLabel("Please complete Safe motherhood Care form - need to track TT.");
    				missingTask.setMessageType("missing");
    				activeProblems.add(missingTask);
    			}
    			// HB lab tests
    			formId = new Long("87");
    			List hbLabTests = null;
    			try {
    				hbLabTests = EncountersDAO.getAll(conn, patientId, pregnancyId, formId, LabTest.class);
    			} catch (IOException e) {
    				log.error(e);
    			}
    			int hbCount = 0;
    			for (int i = 0; i < hbLabTests.size(); i++) {
    				LabTest labTest = (LabTest) hbLabTests.get(i);
    				Integer labType = labTest.getField1845();
    				if (labType != null) {
    					if (labType == 2925) {
    						hbCount++;
    					} else if (labType == 2926) {
    						hbCount++;
    					}
    				}
    			}
    			if (hbCount < 2) {
    				Task missingTask = new Task();
    				missingTask.setLabel("Mother needs at least 2 Hb tests before 36 weeks.");
    				missingTask.setMessageType("missing");
    				activeProblems.add(missingTask);
    			}
    		}

    		if (egaWeeks >= 34 && !deliveryCompleted) {
    			Task missingTask = new Task();
    			missingTask.setLabel("EGA >= 34. Please check the presentation to see if this is not breech.");
    			missingTask.setMessageType("missing");
    			activeProblems.add(missingTask);
    		}
    	}
    }

    public static List assembleProblemList(Connection conn, Long patientId, Long pregnancyId, Boolean status) throws SQLException, ObjectNotFoundException, PersistenceException, ServletException, IllegalAccessException, InvocationTargetException {
        List activeProblems = new ArrayList();
        List problemlist = new ArrayList();
        List outcomelist = new ArrayList();
        // get the active problems

        problemlist = ProblemDAO.getList(conn, patientId, pregnancyId, status);
        // get the active Outcomes
        if (pregnancyId.intValue() != 0) {
            outcomelist = OutcomeDAO.getAll(conn, patientId, pregnancyId, status);
        }

        classOutcomes(outcomelist, activeProblems);
        // add them to the activeProblems list
        activeProblems.addAll(problemlist);

        Collections.sort(activeProblems, new org.cidrz.webapp.dynasite.utils.LastModifiedComparator());

        return activeProblems;
    }

    public static void classOutcomes(List outcomelist, List activeProblems) throws IllegalAccessException, InvocationTargetException {
        for (int i = 0; i < outcomelist.size(); i++) {
            Outcome outcome = (Outcome) outcomelist.get(i);
            if (outcome.getOutcomeType().equals("encounter")) {
                EncounterOutcome eo = new EncounterOutcome();
                try {
                    BeanUtils.copyProperties(eo, outcome);
                } catch (ConversionException e) {
                    // it's ok - null sql value
                }
                activeProblems.add(eo);
            } else if (outcome.getOutcomeType().equals("info")) {
                InfoOutcome io = new InfoOutcome();
                try {
                    BeanUtils.copyProperties(io, outcome);
                } catch (ConversionException e) {
                    // it's ok - null sql value
                }
                activeProblems.add(io);
            } else if (outcome.getOutcomeType().equals("referral")) {
                ReferralOutcome ro = new ReferralOutcome();
                try {
                    BeanUtils.copyProperties(ro, outcome);
                } catch (ConversionException e) {
                    // it's ok - null sql value
                }
                activeProblems.add(ro);
            }
        }
    }

    /**
     * Assemble Referral List using OutcomeDAO.getReferrals
     * unused
     *
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param status
     * @return
     * @throws SQLException
     * @throws ObjectNotFoundException
     * @throws PersistenceException
     * @throws ServletException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static List assembleReferralList(Connection conn, Long patientId, Long pregnancyId, Boolean status) throws SQLException, ObjectNotFoundException, PersistenceException, ServletException, IllegalAccessException, InvocationTargetException {
        List activeProblems = new ArrayList();
        List problemlist = new ArrayList();
        List outcomelist = new ArrayList();
        // get the active Outcomes
        outcomelist = OutcomeDAO.getReferrals(conn, patientId, pregnancyId);

        // add them to the activeProblems list
        activeProblems.addAll(problemlist);
        // activeProblems.addAll(outcomelist);
        Collections.sort(activeProblems, new org.cidrz.webapp.dynasite.utils.LastModifiedComparator());
        return activeProblems;
    }

    /**
     * Calculate parity.
     * Here's the rule: Stillbirths, neonatal death or IUFD count towards Parity but Miscarriages do not.
     * If the woman has one live bith, two miscarriages, one stillbirth and is now pregnant again,
     * she would be a Gravida 5 Para 2.
     *
     * @param conn
     * @param parentId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static int getParity(Connection conn, Long parentId) throws SQLException, ServletException {
        List prevPregnancies = PregnancyDAO.getFetusTalley(conn, parentId);
        // see if there is an open preg - get count of children from this pregnancy
        List recentNewborns = null;
        int recentNewbornsSize = 0;
        try {
            SessionPatient sessionPatient = SessionPatientDAO.getSessionPatient(conn, parentId, (long) 0);
            if (sessionPatient.getDatePregnancyEnd() == null && sessionPatient.getCurrentPregnancyId() != null) {
                recentNewborns = PatientDAO.getChildren(conn, parentId, sessionPatient.getCurrentPregnancyId());
            }
        } catch (ObjectNotFoundException e) {
            log.error(e);
        }
        int parity = 0;
        if (prevPregnancies.size() > 0) {
            for (int i = 0; i < prevPregnancies.size(); i++) {
                PrevPregnancies prevPregnancy = (PrevPregnancies) prevPregnancies.get(i);

                if (prevPregnancy.getField53() != null) {  // Outcome of Pregnancy
                    Integer outcome = prevPregnancy.getField53();
                    if (outcome != 1830) { // 1830 - miscarriage - don't count them
                        if (prevPregnancy.getField63() != null) {  // Num. Fetuses
                            Long fenumId = Long.valueOf(prevPregnancy.getField63());
                            FieldEnumeration fenum = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get(fenumId);
                            Integer numFetuses = Integer.valueOf(fenum.getNumericValue());
                            parity = parity + numFetuses;
                        }
                    }
                    // }
                    // parity = parity + numFetuses;
                }
            }
        }

        if (recentNewborns != null) {
            recentNewbornsSize = recentNewborns.size();
            parity = (parity + recentNewbornsSize);
        }
        return parity;
    }

    /**
     * This adds one to get gravida - this will include the current pregnancy
     *
     * @param conn
     * @param parentId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static int getGravida(Connection conn, Long parentId) throws SQLException, ServletException {
        int gravida = 0;

        // see if there is an open preg - get count of children from this pregnancy
        List zeprsPregnancies = null;
        try {
            SessionPatient sessionPatient = SessionPatientDAO.getSessionPatient(conn, parentId, (long) 0);
            if (sessionPatient.getDatePregnancyEnd() == null && sessionPatient.getCurrentPregnancyId() != null) {
                zeprsPregnancies = PatientDAO.getChildren(conn, parentId, sessionPatient.getCurrentPregnancyId());
                if (zeprsPregnancies.size() == 0) {
                    // add 1 to count the current pregnancy to indicate that the mother is still expecting.
                    gravida = 1;
                } else {  // mother has delivered already - get the accurate number of fetuses delivered.
                    gravida = zeprsPregnancies.size();
                }
            }

        } catch (ObjectNotFoundException e) {
            log.error(e);
        }
        // Now calculate the previous pregnancies:
        List prevPregnancies = PregnancyDAO.getFetusTalley(conn, parentId);
        if (prevPregnancies.size() > 0) {
        	for (int i = 0; i < prevPregnancies.size(); i++) {
        		PrevPregnancies prevPregnancy = (PrevPregnancies) prevPregnancies.get(i);
        		if (prevPregnancy.getField63() != null) {	// number of fetuses
        			Long fenumId = Long.valueOf(prevPregnancy.getField63());
        			if (fenumId != 3206) {	// 3206 = N/A
        				FieldEnumeration fenum = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get(fenumId);
        				Integer numFetuses = Integer.valueOf(fenum.getNumericValue());
        				gravida = gravida + numFetuses;
        			}
        		}
        		if (prevPregnancy.getField53() != null) {  // Outcome of Pregnancy
        			Integer outcome = prevPregnancy.getField53();
        			if (outcome == 1830) { // 1830 - miscarriage - may not have made an entry for num fetuses.
        				gravida = gravida + 1;   // add one to total.
        			}
        		}
        	}
        }
        return gravida;
    }

    /**
     * currently unused
     *
     * @param partographStatus
     * @param request
     */
    public static void getLabourDuration(PartographStatus partographStatus, HttpServletRequest request) {
        Timestamp created = partographStatus.getCreated();
        Date dateCompleted = partographStatus.getField1551();
        Time timeOfBirth = partographStatus.getField1552();
        if (dateCompleted != null) {
            long started = created.getTime();
            long dateEnded = dateCompleted.getTime();
            long timeEnded = timeOfBirth.getTime();
            long ended = dateEnded + timeEnded;
            long duration = ended - started;
            long seconds = duration / 1000;
            long hours = seconds / 3600;
            long minutes = seconds % 3600;
            String durationString = hours + ":" + minutes;
            request.setAttribute("labourDuration", durationString);
        }
    }

    /**
     * Calculate duration of labour using values from delivery summary form.
     *
     * @param deliverySum
     * @param birthdate
     * @param birthTime
     * @return durationTotal
     */
    public static long getDurationOfLabour(DeliverySum deliverySum, Date birthdate, Time birthTime) {
        long durationStage1 = 0;
        long durationStage2 = 0;
        long durationTotal = 0;
        try {
            durationStage1 = calcFirstStage(deliverySum);
            durationStage2 = calcSecondStage(deliverySum, birthdate, birthTime);
        } catch (Exception e) {
            log.error(e);
        }
        durationTotal = durationStage1 + durationStage2;
        return durationTotal;
    }

    /**
     * Loop through list of probLabour visits and get the timestamp of the last one - this indicated beginning of labour
     *
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @return Timestamp that indicates beginning of labour
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     */
    public static Timestamp getFirstStageBegan(Connection conn, Long patientId, Long pregnancyId) throws IOException, ServletException, SQLException {
        Timestamp firstStageBegan = null;
        List probLabourVisits = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("65"), ProbLabor.class);
        int size = probLabourVisits.size();
        if (size != 0) {
            ProbLabor probLabor = (ProbLabor) probLabourVisits.get(size - 1);
            firstStageBegan = probLabor.getCreated();
        }
        return firstStageBegan;
    }

    /**
     * Loop through partograph/cervix records and get time when cervix >=10cm
     *
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @return String when cervix >=10cm - you may need to convert this to Time
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     *
     * @throws NoSuchMethodException
     */
    public static String getFirstStageEnd(Connection conn, Long patientId, Long pregnancyId) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String firstStageEnd = null;
        // Loop through partograph/cervix records and get time when cervix >=10cm
        Cervix cervix = null;
        CervixDAO cervixDao = new CervixDAO();
        try {
            cervix = (Cervix) cervixDao.getOne(conn, patientId, pregnancyId);
            Map cervixMap = BeanUtils.describe(cervix);
            for (int i = 1; i <= 10; i++) {
                String cervixValue = (String) cervixMap.get("cervix" + i);
                if (cervixValue != null) {
                    Integer cervixInt = new Integer(cervixValue);
                    if (cervixInt == 10) {
                        firstStageEnd = (String) cervixMap.get("timeObservation" + i);
                    }
                }
            }
        } catch (SQLException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        } catch (ObjectNotFoundException e) {
            // it's ok
        }
        return firstStageEnd;
    }

    public static long calcFirstStage(DeliverySum deliverySum) {
        long firstStageHours = 0;
        boolean calcFirstStage = true;
        Date firstStageBeganDate = null;
        Time firstStageBeganTime = null;
        Date firstStageEndDate = null;
        Time firstStageEndTime = null;
        if (deliverySum.getField1821() != null) {
            firstStageBeganDate = deliverySum.getField1821();
        } else {
            calcFirstStage = false;
        }
        if (deliverySum.getField431() != null) {
            firstStageBeganTime = deliverySum.getField431();
        } else {
            calcFirstStage = false;
        }
        if (deliverySum.getField1824() != null) {
            firstStageEndDate = deliverySum.getField1824();
        } else {
            calcFirstStage = false;
        }
        if (deliverySum.getField432() != null) {
            firstStageEndTime = deliverySum.getField432();
        } else {
            calcFirstStage = false;
        }

        if (calcFirstStage) {
            long firstStageBeginLong = firstStageBeganDate.getTime() + firstStageBeganTime.getTime();
            long firstStageEndLong = firstStageEndDate.getTime() + firstStageEndTime.getTime();
            // there are 86400 seconds in a day
            long diffSeconds = (firstStageEndLong - firstStageBeginLong) / 1000;
            firstStageHours = diffSeconds / 3600;
        }
        return firstStageHours;
    }

    /**
     * Second Stage starts when the complete cervical dilatation – and ends on delivery of the baby
     *
     * @param deliverySum
     * @return long value
     */
    public static long calcSecondStage(DeliverySum deliverySum, Date birthdate, Time birthTime) {
        long secondStageHours = 0;
        boolean calcSecondStage = true;

        Date firstStageEndDate = null;
        Time firstStageEndTime = null;

        if (deliverySum.getField1824() != null) {
            firstStageEndDate = deliverySum.getField1824();
        } else {
            calcSecondStage = false;
        }
        if (deliverySum.getField432() != null) {
            firstStageEndTime = deliverySum.getField432();
        } else {
            calcSecondStage = false;
        }

        if (calcSecondStage) {

            long firstStageEndLong = firstStageEndDate.getTime() + firstStageEndTime.getTime();
            long secondStageEndLong = birthdate.getTime() + birthTime.getTime();
            // there are 86400 seconds in a day
            long diffSeconds = (secondStageEndLong - firstStageEndLong) / 1000;
            secondStageHours = diffSeconds / 3600;
        }
        return secondStageHours;
    }

    /**
     * Used in DWR remote form, as well as to create test newborns.
     * Creates patient and patient_status objects for the newborn, then a patientregistration(form 1) object.
     * Creates a ZEPRS id for the newborn. Persists these objects.
     * Does not create a newborn evaluation; instead, creates a newbornrecord, which is a placeholder.
     *
     * @param conn
     * @param patientId
     * @param sequence
     * @param newbornDateField
     * @param sex
     * @param pregnancyId
     * @param birthtime
     * @param user
     * @param siteId
     * @param session
     * @param weight
     * @return String response for display via AJAX
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     * @throws PersistenceException
     */
    public static String createNewborn(Connection conn, String patientId, String sequence, String newbornDateField, String sex, String pregnancyId, String birthtime, String user, String siteId, HttpSession session, String weight) throws ServletException, SQLException, ObjectNotFoundException, PersistenceException {
        Long motherId = new Long(patientId);
        String response = null;
        if (sequence.equals("")) {
            response = "Error: Problem calculating number of infants. Please contact system support.";
            log.error(response);
        } else {
            BaseEncounter encounter = new NewbornEval();
            Form formDef = (Form) DynaSiteObjects.getForms().get(new Long("23"));
            encounter.setFormId(formDef.getId());
            encounter.setDateVisit(Date.valueOf(newbornDateField));
            Integer SexInt = new Integer(sex);
            Float weightF = null;
            if (!weight.equals("")) {
                weightF = new Float(weight);
            }
            SessionPatient sessionPatient = SessionPatientDAO.getSessionPatient(conn, Long.decode(patientId), Long.decode(pregnancyId));
            Patient infant = PatientRecordUtils.createNewbornObject(sequence, sessionPatient.getId(), SexInt, newbornDateField, sessionPatient, birthtime);
            if (infant == null) {
                response = "Error: Problem creating infant record for " + sessionPatient.getDistrictPatientid() + ". Please contact system support.";
                log.error(response);
            } else {
                // create an instance of PatientRegistration for infant
                PatientRegistration patReg = PatientRecordUtils.createPatientRegistrationObject(infant);
                patReg.setFormId(new Long("1"));
                patReg.setField490(Integer.valueOf(sex));
                // create the zeprsId
                Site site = (Site) DynaSiteObjects.getClinicMap().get(Long.valueOf(siteId));
                String districtId = "5040";
                String zepSiteId = site.getSiteAlphaId().substring(0,2) + "3";
                String zePatientId  = null;
                zePatientId = PatientId.setPatientId("5040", zepSiteId);
                /*int checksum = new Integer(zePatientId).intValue() % 10;
                if (checksum < 0) {
                    checksum += 10;
                }*/
                //String zeprsId = "5040" + "-" + zepSiteId + "-" + zePatientId + "-" + checksum;

                /*try {
                    zePatientId = PatientId.CreateVerifiedPatientId(conn, districtId, zepSiteId);
                } catch (Exception e) {
                    log.error(e);
                }*/
                String zeprsId = PatientRecordUtils.createZeprsId("5040", zepSiteId, zePatientId);
                patReg.setField1513(zeprsId);
                patReg.setField13("5040");
                patReg.setField1511(site.getSiteAlphaId().substring(0, 3));
                Form patRegFormDel = (Form) DynaSiteObjects.getForms().get(new Long("1"));
                long egaWeeks = 0;
                if (sessionPatient.getCurrentEgaDaysDB() != null) {
                	long egaToday = sessionPatient.getCurrentEgaDaysDB().longValue();
                    java.util.Date dateVisit = PregnancyDatingDAO.getEgaDate(conn, sessionPatient.getCurrentEgaDaysEncounterId());
                    egaToday = calcCurrentEga(dateVisit, egaToday);
                    egaWeeks = egaToday / 7;
                }
                try {
                    FormDAO.createNewborn(conn, patReg, user, new Long(siteId), patRegFormDel, motherId, infant, weightF, egaWeeks);
                    // Updates the sessionPatient, including children
                    SessionPatientDAO.updateSessionPatient(conn, motherId, new Long(pregnancyId), session);
                    // re-initialise the globals
                    sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
                } catch (Exception e) {
                    response = "Error: Problem creating infant record for " + sessionPatient.getDistrictPatientid() + ". Please contact system support.";
                    log.error(response);
                    log.error(e);
                    e.printStackTrace();
                }
                response = Newborn.createResponse(motherId, pregnancyId);
            }
        }
        return response;
    }

    /**
     * This is used for creating test newborns.
     * @param conn
     * @param patientId
     * @param sequence
     * @param newbornDateField
     * @param sex
     * @param pregnancyId
     * @param birthtime
     * @param user
     * @param siteId
     * @param session
     * @param weight
     * @return
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     * @throws PersistenceException
     */
    public static Long createTestNewborn(Connection conn, String patientId, String sequence, String newbornDateField, String sex, String pregnancyId, String birthtime, String user, String siteId, HttpSession session, String weight) throws ServletException, SQLException, ObjectNotFoundException, PersistenceException {
    	Long motherId = new Long(patientId);
    	Long infantId = null;
    	String response = null;
    	if (sequence.equals("")) {
    		response = "Error: Problem calculating number of infants. Please contact system support.";
    		log.error(response);
    	} else {
    		BaseEncounter encounter = new NewbornEval();
    		Form formDef = (Form) DynaSiteObjects.getForms().get(new Long("23"));
    		encounter.setFormId(formDef.getId());
    		encounter.setDateVisit(Date.valueOf(newbornDateField));
    		Integer SexInt = new Integer(sex);
    		Float weightF = null;
    		if (!weight.equals("")) {
    			weightF = new Float(weight);
    		}
    		SessionPatient sessionPatient = SessionPatientDAO.getSessionPatient(conn, Long.decode(patientId), Long.decode(pregnancyId));
            String uuid = sessionPatient.getUuid();
    		Patient infant = PatientRecordUtils.createNewbornObject(sequence, sessionPatient.getId(), SexInt, newbornDateField, sessionPatient, birthtime);
    		if (infant == null) {
    			response = "Error: Problem creating infant record for " + sessionPatient.getDistrictPatientid() + ". Please contact system support.";
    			log.error(response);
    		} else {
    			// create an instance of PatientRegistration for infant
    			PatientRegistration patReg = PatientRecordUtils.createPatientRegistrationObject(infant);
    			patReg.setFormId(new Long("1"));
    			patReg.setField490(Integer.valueOf(sex));
    			// create the zeprsId
    			Site site = (Site) DynaSiteObjects.getClinicMap().get(Long.valueOf(siteId));
    			String districtId = "5040";
    			String zepSiteId = site.getSiteAlphaId().substring(0,2) + "3";
    			String zePatientId  = null;
    			zePatientId = PatientId.setPatientId("5040", zepSiteId);
    			/*int checksum = new Integer(zePatientId).intValue() % 10;
                if (checksum < 0) {
                    checksum += 10;
                }*/
    			//String zeprsId = "5040" + "-" + zepSiteId + "-" + zePatientId + "-" + checksum;

    			/*try {
                    zePatientId = PatientId.CreateVerifiedPatientId(conn, districtId, zepSiteId);
                } catch (Exception e) {
                    log.error(e);
                }*/
    			String zeprsId = PatientRecordUtils.createZeprsId("5040", zepSiteId, zePatientId);
    			patReg.setField1513(zeprsId);
    			patReg.setField13("5040");
    			patReg.setField1511(site.getSiteAlphaId().substring(0, 3));
    			Form patRegFormDel = (Form) DynaSiteObjects.getForms().get(new Long("1"));
    			long egaWeeks = 0;
    			if (sessionPatient.getCurrentEgaDaysDB() != null) {
    				long egaToday = sessionPatient.getCurrentEgaDaysDB().longValue();
    				java.util.Date dateVisit = PregnancyDatingDAO.getEgaDate(conn, sessionPatient.getCurrentEgaDaysEncounterId());
    				egaToday = calcCurrentEga(dateVisit, egaToday);
    				egaWeeks = egaToday / 7;
    			}
    			try {
    				EncounterData enc = FormDAO.createNewborn(conn, patReg, user, new Long(siteId), patRegFormDel, motherId, infant, weightF, egaWeeks);
    				infantId = enc.getPatientId();
    				// Updates the sessionPatient, including children
    				SessionPatientDAO.updateSessionPatient(conn, motherId, new Long(pregnancyId), session);
    				// re-initialise the globals
    				sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
    			} catch (Exception e) {
    				response = "Error: Problem creating infant record for " + sessionPatient.getDistrictPatientid() + ". Please contact system support.";
    				log.error(response);
    				log.error(e);
    				e.printStackTrace();
    			}
    			// response = Newborn.createResponse(motherId, pregnancyId);
    		}
    	}
    	return infantId;
    }

    /**
     * Used in flow from Delivery summary form.
     * Loops through Newborn Record forms to fetch the next newborn eval.
     * @param conn
     * @param sessionPatient
     * @param request
     * @return
     */
    public static ActionForward getNextNewbornEval(Connection conn, SessionPatient sessionPatient, HttpServletRequest request) {
        ActionForward forwardForm = null;
        // check if current patient is mother or infant
        List children = null;
        Long motherId = null;
        if (sessionPatient.getParentId() != null) {
            // Get the mother and see number of children
            Patient mother = sessionPatient.getMother();
            motherId = mother.getId();
            Long pregnancyId = sessionPatient.getCurrentPregnancyId();
            children = PatientDAO.getChildren(conn, mother.getId(), pregnancyId);
        } else {
            children = sessionPatient.getChildren();
            motherId = sessionPatient.getId();
        }
        Long childId = null;

        // find the infant forms
        // iterate through list of children and find one who has not modified newborn eval
        List newbornEvalsCompleted = new ArrayList();

        for (int c = 0; c < children.size(); c++) {
            Patient newborn = (Patient) children.get(c);
            Long patientId = newborn.getId();
            Long pregnancyId = sessionPatient.getCurrentPregnancyId();
            NewbornEval newbornEval = null;
            try {
            	newbornEval = (NewbornEval) EncountersDAO.getOne(conn, patientId, pregnancyId, "SQL_RETRIEVE23", NewbornEval.class);
            } catch (IOException e) {
                log.error(e);
            } catch (ServletException e) {
                log.error(e);
            } catch (SQLException e) {
                log.error(e);
            } catch (ObjectNotFoundException e) {
            	// NewbornEval not submitted - assign childId
            	childId = newborn.getId();
            }
        }

        if (childId == null) {
            forwardForm = getPuerperiumForward(request, motherId);
            return forwardForm;
        } else {
            //EncounterData encounter = null;
            Form nextForm;
            // get the record for this newborn eval form

                //encounter = (EncounterData) EncountersDAO.getId(conn, childId, sessionPatient.getCurrentPregnancyId(), new Long("23"));
                //Long encounterId = encounter.getId();
                //request.setAttribute("encounterId", encounterId);
            try {
				nextForm = (Form) DynaSiteObjects.getForms().get(new Long("23"));
				    String formName = nextForm.getName();
				    request.setAttribute("name", formName);
				    request.setAttribute("id", nextForm.getId());
				    // need to set the sessionPatient to the child
				    request.setAttribute("patientId", childId);
				    String forwardString = "/form23/new.do?patientId=" + childId;
				    forwardForm = new ActionForward(forwardString);
				    forwardForm.setRedirect(true);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            return forwardForm;
        }
    }

    public static ActionForward getPuerperiumForward(HttpServletRequest request, Long motherId) {
        Form nextForm = (Form) DynaSiteObjects.getForms().get(new Long("81"));
        String formName = nextForm.getName();
        request.setAttribute("name", formName);
        request.setAttribute("id", nextForm.getId());
        ActionForward forwardForm = null;
        forwardForm = new ActionForward("/patientPuer.do?patientId=" + motherId);
        forwardForm.setRedirect(true);
        return forwardForm;
    }

    /**
     * Extract all values in an encounter, resolving enumerations.
     * Will return key as field label or field id.
     * keytype  "starSchemaName"  is used for reports.
     * keyType "label" is used for viewing records/charts
     *
     * @param encForm
     * @param encounterRecord
     * @param keyType
     * @return Map
     */
    public static Map getEncounterMap(Form encForm, BaseEncounter encounterRecord, String keyType) {

        Log log = LogFactory.getFactory().getInstance(PatientRecordUtils.class);
        Map encMap = new LinkedHashMap();
        boolean labelKey = false;
        if (keyType.equals("starSchemaNameR2")) {
            labelKey = true;
            keyType = "starSchemaNameR";
        }
        for (Iterator iterator = encForm.getPageItems().iterator(); iterator.hasNext();) {
            PageItem pageItem = (PageItem) iterator.next();
            String value = null;
            if (pageItem.getForm_field().isEnabled()) {
                // display items first
                if ((pageItem.getForm_field().getType().equals("Display")) & (!keyType.equals("starSchemaName")) & (!keyType.equals("starSchemaNameR")))
                {
                    if ((pageItem.getInputType().equals("display-header")) || (pageItem.getInputType().equals("display-subheader-row")) ||
                            (pageItem.getInputType().equals("collapsing-display-header-begin"))) {
                        encMap.put("<strong>" + pageItem.getForm_field().getLabel() + "</strong>", null);
                    }
                } else
                if ((pageItem.getForm_field().getType().equals("Display")) & (keyType.equals("starSchemaName"))) {
                    // skip - we don't want it...
                } else
                if ((pageItem.getForm_field().getType().equals("Display")) & (keyType.equals("starSchemaNameR"))) {
                    // skip - we don't want it...
                } else if (pageItem.getInputType().equals("multiselect_enum")) {
                    // skip - we don't want it...
                } else {
                    // regular form fields
                    String key;
                    if (keyType.equals("label")) {
                        key = "field" + pageItem.getForm_field().getId();
                    } else if (keyType.equals("starSchemaName")) {
                        key = StringManipulation.firstCharToLowerCase(pageItem.getForm_field().getStarSchemaName());
                    } else if (keyType.equals("starSchemaNameR")) {
                        key = StringManipulation.firstCharToLowerCase(pageItem.getForm_field().getStarSchemaName()) + "R";
                    } else {
                        key = "field" + pageItem.getForm_field().getId();
                    }

                    try {
                        // BeanUtils.copyProperties silently ignores missing setters
                        // for key, so we have to go through great lengths here to
                        // figure out if the bean property really exists.
                        PropertyDescriptor pd = null;
                        pd = PropertyUtils.getPropertyDescriptor(encounterRecord, key);

                        if (pd == null || pd.getReadMethod() == null) {
                            throw new Exception("Property '" + key + "' in this record does not exist, please check the documentation. Properties: " +
                                    "Form_field().getType(): " + pageItem.getForm_field().getType() + "" +
                                    "; pageItem.inputType: " + pageItem.getInputType());
                        }

                        // finally we can set the bean property
                        value = BeanUtils.getProperty(encounterRecord, key);
                        if (value != null) {
                            if (keyType.equals("rules")) {
                                String fieldType = pageItem.getForm_field().getType();
                                String pageItemType = pageItem.getInputType();
                                value = PatientRecordUtils.resolveValue(value, fieldType, pageItemType);
                                Float valueFl = new Float(value);
                                encMap.put("field" + pageItem.getForm_field().getId(), valueFl);
                            } else {
                                resolveValue(pageItem, value, log, keyType, encMap);
                            }
                        } else {
                            if (keyType.equals("rules"))
                            {   // we need a zero value or we'll get an NPE upon evaluation.
                                encMap.put("field" + pageItem.getForm_field().getId(), 0);
                            }
                        }
                    } catch (IllegalAccessException e) {
                        if (pageItem.getForm_field().getType().equals("Display")) {
                            encMap.put("<strong>" + pageItem.getForm_field().getLabel() + "</strong>", null);
                        }
                    } catch (IllegalArgumentException e) {
                        // ok
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        log.error(e);
                    } catch (ConversionException e) {
                        log.error(e);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        log.error(e + " Property '" + key + "' in this record does not exist. Properties: " +
                                "Form_field().getType(): " + pageItem.getForm_field().getType() + "" +
                                "; pageItem.inputType: " + pageItem.getInputType() + " form id: " + pageItem.getFormId());
                    } catch (Exception e) {
                        log.error(e + " Property '" + key + "' in this record does not exist. Properties: " +
                                "Form_field().getType(): " + pageItem.getForm_field().getType() + "" +
                                "; pageItem.inputType: " + pageItem.getInputType() + " form id: " + pageItem.getFormId());
                    }
                }
            }
        }
        return encMap;
    }

    /**
     * Resolves values for enumerations and other field and display types
     * @param pageItem
     * @param value
     * @param log
     * @param keyType
     * @param encMap
     */
    public static void resolveValue(PageItem pageItem, String value, Log log, String keyType, Map encMap) {
        if (pageItem.getForm_field().getType().equals("Enum")) {
            if (pageItem.getInputType().equals("checkbox")) {
                if (value.equals("1")) {
                    value = "Yes";
                } else if (value.equals(String.valueOf(Boolean.TRUE))) {
                    value = "Yes";
                } else {
                    value = "";
                }
            } else if (pageItem.getInputType().equals("checkbox_dwr")) {
                if (value.equals("1")) {
                    value = "Yes";
                } else if (value.equals(String.valueOf(Boolean.TRUE))) {
                    value = "Yes";
                } else {
                    value = "";
                }
            } else if (pageItem.getInputType().equals("currentMedicine")) {
                List drugs = DynaSiteObjects.getDrugs();
                for (int i = 0; i < drugs.size(); i++) {
                    Drugs drug = (Drugs) drugs.get(i);
                    if (value.equals(drug.getId().toString())) {
                        if (drug.getTeratogenic() != null) {
                            value = drug.getName() + " <p class=\"teratogenicAlert\">*" + drug.getTeratogenic() + "*</p>";
                        } else {
                            value = drug.getName();
                        }
                    }
                }
            } else if (pageItem.getInputType().equals("sites")) {
                Long siteId = new Long(value);
                Site site = (Site) DynaSiteObjects.getClinicMap().get(siteId);
                value = site.getName();
            } else {
                if (Long.valueOf(value).intValue() > 0) {
                    FieldEnumeration fieldEnum = null;
                    try {
                        fieldEnum = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get(Long.valueOf(value));
                    } catch (NumberFormatException e) {
                        log.error("Error getting FieldEnumeration with input value: \"" + value + "\" " +
                                "pageItem.getInputType(): " + pageItem.getInputType() + " field id: " +
                                "" + pageItem.getForm_field().getId() + " " +
                                " Error message: " + e);
                        value = "";
                    } catch (NullPointerException e) {
                        log.error("Error getting FieldEnumeration with input value: \"" + value + "\" " +
                                "pageItem.getInputType(): " + pageItem.getInputType() + " field id: " +
                                "" + pageItem.getForm_field().getId() + " " +
                                " Error message: " + e);
                        value = "";
                    }
                    try {
                        value = fieldEnum.getEnumeration();
                    } catch (NullPointerException e) {
                        log.error("Error getting FieldEnumeration with input value: \"" + value + "\" " +
                                " pageItem.getInputType(): " + pageItem.getInputType() + " field id: " +
                                "" + pageItem.getForm_field().getId() + " " +
                                " Error message: " + e);
                        value = "";
                    }
                } else {
                    value = "";
                }
            }
        } else if (pageItem.getForm_field().getType().equals("Boolean")) {
            if (value.equals("1")) {
                value = "Yes";
            } else if (value.equals(String.valueOf(Boolean.TRUE))) {
                value = "Yes";
            } else {
                value = "";
            }

        } else if (pageItem.getForm_field().getType().equals("sex")) {
            if (value.equals("1")) {
                value = "Female";
            } else if (value.equals("2")) {
                value = "Male";
            }
        } else if (pageItem.getForm_field().getType().equals("Yes/No")) {
            if (value.equals("1")) {
                value = "Yes";
            } else if (value.equals(String.valueOf(Boolean.TRUE))) {
                value = "Yes";
            } else {
                value = "No";
            }
        } else if (pageItem.getInputType().equals("ega_pregnancyDating") || pageItem.getInputType().equals("ega")) {
            Integer valueInt = Integer.valueOf(value);
            int days = valueInt.intValue() % 7;
            int weeks = valueInt.intValue() / 7;
            value = weeks + ", " + days + "/7";
        } else if (pageItem.getInputType().equals("fundal_height")) {
        	Integer valueInt = Integer.valueOf(value);
        	if (valueInt == 6) {
        		value = "<12";
        	} else {
        		value = valueInt.toString();
        	}
        } else if (pageItem.getInputType().equals("sites")) {
            Long siteId = new Long(value);
            Site site = (Site) DynaSiteObjects.getClinicMap().get(siteId);
            value = site.getName();
        } else if (pageItem.getInputType().equals("sites_not_selected")) {
            Long siteId = new Long(value);
            Site site = (Site) DynaSiteObjects.getClinicMap().get(siteId);
            value = site.getName();
        }

        if (keyType.equals("label")) {
            encMap.put(pageItem.getForm_field().getLabel(), value);
        } else if (keyType.equals("starSchemaName")) {
            encMap.put(StringManipulation.firstCharToLowerCase(pageItem.getForm_field().getStarSchemaName()) + "R", value);
        } else if (keyType.equals("starSchemaNameR")) {
            encMap.put(StringManipulation.firstCharToLowerCase(pageItem.getForm_field().getStarSchemaName()) + "R", value);
        } else {
            encMap.put("field" + pageItem.getForm_field().getId(), value);
        }
    }

    /**
     * Currently unused
     *
     * @param conn
     * @param className
     * @param encounterID
     * @return
     * @throws ClassNotFoundException
     * @throws ObjectNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    public static BaseEncounter getRecord(Connection conn, String className, String encounterID) throws ClassNotFoundException, ObjectNotFoundException, SQLException, IOException, ServletException {
        BaseEncounter encounterRecord = null;
        Map encMap;
        if (className != null) {
            String classname = "org.cidrz.project.zeprs.valueobject.gen." + StringManipulation.firstCharToUpperCase(className);
            Class formClass = Class.forName(classname);
            encounterRecord = (BaseEncounter) EncountersDAO.getOne(conn, Long.valueOf(encounterID), "SQL_RETRIEVEID1", formClass);
        }
        Form encForm = (Form) DynaSiteObjects.getForms().get(encounterRecord.getFormId());
        encMap = getEncounterMap(encForm, encounterRecord, "label");
        encounterRecord.setEncounterMap(encMap);
        encounterRecord.setFormName(encForm.getLabel());
        return encounterRecord;
    }


    /**
     * This is rules to provide numeric values for rule resolution
     *
     * @param inputValue
     * @param fieldType
     * @param pageItemType
     * @return numeric value
     */
    public static String resolveValue(String inputValue, String fieldType, String pageItemType) {
        String value = "";
        if (!inputValue.equals("")) {
            if (fieldType.equals("Enum")) {
                if (pageItemType.equals("checkbox")) {
                    if (inputValue.equals("1")) {
                        value = "1";
                    } else if (inputValue.equals("true")) {
                        value = "1";
                    } else {
                        value = "0";
                    }
                } else if (pageItemType.equals("lab_results")) {
                    FieldEnumeration fe = null;
                    fe = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get(new Long(inputValue));
                    // todo: identify if field1845 = 3042, then just use inputValue.
                    if (fe == null) {
                        value = inputValue;
                    } else {
                        if (fe.getNumericValue() != null) {
                            value = fe.getNumericValue();
                        } else {
                            value = fe.getDisplayOrder().toString();
                        }
                    }

                } else if (pageItemType.equals("currentMedicine")) {
                    value = inputValue;
                } else {
                    if (!inputValue.equals("0")) {
                        value = resolveNumericValue(inputValue);
                    } else {
                        value = inputValue;
                    }
                }

            } else if (fieldType.equals("Yes/No")) {
                if (inputValue.equals("1")) {
                    value = "1";
                } else {
                    value = "0";
                }
            } else if (fieldType.equals("Boolean")) {
                if (inputValue.equals(true)) {
                    value = "1";
                } else if (inputValue.equals("1")) {
                    value = "1";
                } else {
                    value = "0";
                }
            } else {
                value = inputValue;
            }
        }
        return value;
    }

    /**
     * Provides the numeric value of enum - used in outcome resoluvtion
     *
     * @param inputValue
     * @return numeric value of enum
     */
    public static String resolveNumericValue(String inputValue) {
        String value;
        // Map enumMap = new HashMap();
        // Iterator enums = null;
        FieldEnumeration fe = null;
        Long longValue = new Long(inputValue);
        fe = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get(longValue);
        // if (pageItemType.equals("multiselect_item")) {
        //FormField parentField = (FormField) PersistenceManagerFactory.getInstance(FormField.class).getOne(new Long(this.getPageItem().getVisibleDependencies1()));
        //enums = parentField.getEnumerations().iterator();
        // fe = (FieldEnumeration) PersistenceManagerFactory.getInstance(FieldEnumeration.class).getOne(new Long(this.getValue()));
        if (fe.getNumericValue() != null) {
            value = fe.getNumericValue();
        } else {
            if (fe.getDisplayOrder() != null) {
                value = fe.getDisplayOrder().toString();
            } else {
                value = inputValue;
            }
        }
        return value;
    }

    /**
     * Use for reports to format the string value.
     *
     * @param inputValue
     * @param offSet
     * @return String value of selected enumeration
     */
    public static String resolveEnum(Integer inputValue, int offSet) {
        String value = null;
        FieldEnumeration fe;
        Long longValue = inputValue.longValue();
        fe = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get(longValue);
        try {
            if (fe.getEnumeration() != null) {
                if (offSet > 0) {
                    try {
                        value = fe.getEnumeration().substring(0, offSet);
                    } catch (StringIndexOutOfBoundsException e) {
                        log.error(e);
                        value = fe.getEnumeration();
                    }
                } else {
                    value = fe.getEnumeration();
                }
            } else {
                value = inputValue.toString();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            value = inputValue.toString();
        }
        return value;
    }

    /**
     * Fetch currentPregnancyId into SessionPatient
     * @param conn
     * @param patientId
     * @return
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static SessionPatient getSessionPatientPregnancy(Connection conn, Long patientId) throws ServletException, SQLException, ObjectNotFoundException {
        String sql;
        SessionPatient currPreg;
        sql = "select s.current_pregnancy_id AS currentPregnancyId " +
                " from patient_status s where id=?;";
        ArrayList pregVals = new ArrayList();
        pregVals.add(patientId);
        currPreg = (SessionPatient) DatabaseUtils.getBean(conn, SessionPatient.class, sql, pregVals);
        return currPreg;
    }

    /**
     * Fetch patient whose disable_lab_import IS NULL for LIMS import
     * @param conn
     * @param zeprsId
     * @param fetchPatientNames - provide patient name for preview listing.
     * @return SessionPatient w/ id and currentPregnancyId
     * @throws ObjectNotFoundException
     * @throws ServletException
     * @throws SQLException
     */
    public static SessionPatient getLimsImportPatient(Connection conn, String zeprsId, Boolean fetchPatientNames) throws ObjectNotFoundException, ServletException, SQLException {
    	String sql;
        SessionPatient currPreg;
        if (fetchPatientNames) {
        	sql = "SELECT s.current_pregnancy_id AS currentPregnancyId, p.id AS id, " +
        			"first_name AS firstName, surname AS surname, district_patient_id AS districtPatientid, " +
        			"disable_lab_import AS disableLabImport, hiv_positive AS hivPositive\n" +
            "FROM patient p, patient_status s\n" +
            "WHERE p.id = s.id\n" +
          //  "AND disable_lab_import IS NULL\n" +
            "AND p.district_patient_id=?";
        } else {
        	sql = "SELECT s.current_pregnancy_id AS currentPregnancyId, p.id AS id, \n" +
			"disable_lab_import AS disableLabImport, hiv_positive AS hivPositive\n" +
            "FROM patient p, patient_status s\n" +
            "WHERE p.id = s.id\n" +
         //   "AND disable_lab_import IS NULL" +
            "AND p.district_patient_id=?";
        }
        ArrayList pregVals = new ArrayList();
        pregVals.add(zeprsId);
        currPreg = (SessionPatient) DatabaseUtils.getBean(conn, SessionPatient.class, sql, pregVals);
        return currPreg;
    }

    /**
     * Set the current EGA in session
     *
     * @param conn
     * @param encounterId
     * @param sessionPatient
     * @throws javax.servlet.ServletException
     * @throws java.sql.SQLException
     * @throws org.cidrz.webapp.dynasite.session.SessionUtil.AttributeNotFoundException
     *
     */
    public static SessionPatient updateCurrentEga(Connection conn, Long encounterId, SessionPatient sessionPatient) throws ServletException, SQLException, SessionUtil.AttributeNotFoundException, ObjectNotFoundException {

        if (sessionPatient.getCurrentEgaDaysDB() != null) {
            long egaToday = sessionPatient.getCurrentEgaDaysDB().longValue();
            // Flow currentFlow = FlowDAO.getOne(sessionPatient.getCurrentFlowId());
            Flow currentFlow = (Flow) DynaSiteObjects.getFlowMap().get(sessionPatient.getCurrentFlowId());

            // if patient is not yet in Delivery Summary flow, calculate current ega
            // otherwise, just return 0.

            if ((currentFlow.getFlowOrder().intValue() < 5) || currentFlow.getFlowOrder().intValue() == 101) {
                java.util.Date dateVisit = PregnancyDatingDAO.getEgaDate(conn, encounterId);
                egaToday = calcCurrentEga(dateVisit, egaToday);
                sessionPatient.setCurrentEgaCalc(new Integer((int) egaToday));
            } else {
                sessionPatient.setCurrentEgaCalc(new Integer("0"));
            }
        }
        return sessionPatient;
    }

    /**
     * Provde the dateVisit and the ega recorded that date (from pregnancy dating table, for instance),  and this will provide the current ega as of today.
     *
     * @param dateVisit
     * @param recordedEga
     * @return current ega in days
     */
    public static long calcCurrentEga(java.util.Date dateVisit, long recordedEga) {
        long egaToday = 0;
        GregorianCalendar egaCalendar = new GregorianCalendar();
        egaCalendar.setTime(dateVisit);
        GregorianCalendar today = new GregorianCalendar();
        java.util.Date d1 = egaCalendar.getTime();
        java.util.Date d2 = today.getTime();
        long l1 = d1.getTime();
        long l2 = d2.getTime();
        // there are 86400 seconds in a day
        long diffSeconds = (l2 - l1) / 1000;
        long diffDays = diffSeconds / 86400;
        // Integer egaDateDiff = new Integer(String.valueOf(diffDays));
        egaToday = recordedEga + diffDays;
        return egaToday;
    }

    /**
     * Calc ega for a record's dateVisit, based on the most current ega, which may have come from another visit.
     *
     * @param dateVisit    from record
     * @param egaDateVisit - dateVisit from ega record - most recent preg. dating record
     * @param recordedEga  - ega from most recent preg. dating record
     * @return ega for a record
     */
    public static long calcEgaforDate(java.util.Date dateVisit, java.util.Date egaDateVisit, long recordedEga) {
        long egaToday = 0;
        GregorianCalendar recordCalendar = new GregorianCalendar();
        recordCalendar.setTime(dateVisit);
        GregorianCalendar egaRecordCal = new GregorianCalendar();
        egaRecordCal.setTime(egaDateVisit);
        java.util.Date d1 = recordCalendar.getTime();
        java.util.Date d2 = egaRecordCal.getTime();
        long l1 = d1.getTime();
        long l2 = d2.getTime();
        // there are 86400 seconds in a day
        long diffSeconds = 0;
        long diffDays = 0;
        if (l1 > l2) {  // if dateVisit is after the egaDateVisit
            diffSeconds = (l1 - l2) / 1000;
            diffDays = diffSeconds / 86400;
            egaToday = recordedEga + diffDays;   // egaToday should be more than the recordedEga
        } else {     // if dateVisit is before the egaDateVisit, subtract egaDateVisit from dateVisit
            diffSeconds = (l2 - l1) / 1000;
            diffDays = diffSeconds / 86400;
            egaToday = recordedEga - diffDays;   // egaToday should be less than the recordedEga
        }
        return egaToday;
    }

    public static Object getPreparedValue(PageItem pageItem, String voValue) {
        FormField formField = pageItem.getForm_field();
        Object preparedValue = voValue;
        if (formField.getType().equals("Enum") || formField.getType().equals("Boolean")) {
            if (pageItem.getInputType().equals("checkbox") || pageItem.getInputType().equals("checkbox_dwr")) {
                try {
                    if (voValue.equals("false")) {
                        preparedValue = null;
                    } else {
                        preparedValue = voValue;
                    }
                } catch (Exception e) {
                    preparedValue = voValue;
                    log.error("*** Error adding Boolean value: " + voValue + " to hash. Error: " + e);
                }
            } else {
                try {
                    preparedValue = Integer.valueOf(voValue);
                    // log.debug("Adding Integer value to hash: " + Integer.valueOf(voValue));
                } catch (Exception e) {
                    preparedValue = voValue;
                    log.error("*** Error adding Integer value: " + voValue + " to hash. Field: " + formField.getLabel() + " Error: " + e);
                }
            }
        } else {
            preparedValue = voValue;
        }
        return preparedValue;
    }

    /**
     * Gets current pregnancy id. If it's a closed pregnancy, gets the most recent pregnancy id.
     *
     * @param conn
     * @param patientId
     * @return current pregnancy id
     */
    public static Long getPregnancyId(Connection conn, Long patientId) {
        SessionPatient currPreg = null;
        Long pregnancyId = null;
        try {
            currPreg = getSessionPatientPregnancy(conn, patientId);
            pregnancyId = currPreg.getCurrentPregnancyId();
            if (pregnancyId == 0) {
                throw new ObjectNotFoundException();
            }
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } catch (ObjectNotFoundException e) {
            List pregnancies = null;
            try {
                pregnancies = PregnancyDAO.getPatientPregnancies(conn, patientId);
                Pregnancy pregnancy = (Pregnancy) pregnancies.get(pregnancies.size() - 1);
                pregnancyId = pregnancy.getId();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ServletException e1) {
                e1.printStackTrace();
            }
        }
        return pregnancyId;
    }

    /**
     * Gets current ega data from PregnancyDating and pops it into CurrentPregnancyStatus
     *
     * @param conn
     * @param patientID
     * @return CurrentPregnancyStatus w/ ega
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static CurrentPregnancyStatus getEga(Connection conn, int patientID) throws SQLException, ServletException, ObjectNotFoundException {

        CurrentPregnancyStatus currentAnte = new CurrentPregnancyStatus();
        String sql;
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Long> values;
        // Get this data from patient_status table
        Long encounterId = null;
        Long currentFlowId = null;
        Long currentEgaDaysDb = null;
        sql = "SELECT current_lmp_date, current_ega_days, current_ega_days_encounter_id, current_flow  " +
                "FROM zeprs.patient_status WHERE id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, patientID);

        rs = ps.executeQuery();

        while (rs.next()) {
            encounterId = rs.getLong("current_ega_days_encounter_id");
            currentFlowId = rs.getLong("current_flow");
            currentEgaDaysDb = rs.getLong("current_ega_days");
        }
        currentAnte.setEgaDb(currentEgaDaysDb);
        long egaToday = 0;

        if (encounterId != null & encounterId != 0) {
            sql = "select encounter.id, date_visit AS datevisit, lmp_127_calculated AS field127, edd_128 AS field128, " +
                    "dating_method AS field1615 " +
                    "FROM zeprs.pregnancydating, zeprs.encounter WHERE encounter.id = pregnancydating.id and encounter.id=? ";
            values = new ArrayList<Long>();
            values.add(encounterId);
            PregnancyDating pregDate = (PregnancyDating) DatabaseUtils.getBean(conn, PregnancyDating.class, sql, values);
            if (pregDate != null) {
                Flow currentFlow = (Flow) DynaSiteObjects.getFlowMap().get(currentFlowId);
                if ((currentFlow.getFlowOrder().intValue() < 5) || currentFlow.getFlowOrder().intValue() == 101) {
                    java.util.Date dateVisit = pregDate.getDateVisit();
                    try {
                        egaToday = PatientRecordUtils.calcCurrentEga(dateVisit, currentEgaDaysDb);
                        currentAnte.setEga(egaToday);
                        long egaWeeks = egaToday / 7;
                        long egaDays = egaToday % 7;
                        currentAnte.setEgaWeeks(egaWeeks + " " + egaDays + "/7");
                    } catch (Exception e) {
                        log.error(e);
                    }
                }
                Date edd = pregDate.getField128();
                Date lmp = pregDate.getField127();
                Integer datingMethod = pregDate.getField1615();
                Date dateVisit = pregDate.getDateVisit();
                Integer quickening = pregDate.getField188();
                currentAnte.setEdd(edd);
                currentAnte.setLmp(lmp);
                currentAnte.setDatingMethod(datingMethod);
                currentAnte.setDateEga(dateVisit);
                currentAnte.setQuickening(quickening);
            }
        }
        return currentAnte;
    }

    /**
     * Gets ega data for delivery summary from PregnancyDating
     *
     * @param conn
     * @param patientID
     * @param dateVisitDelivery from deliverySummary
     * @return CurrentPregnancyStatus w/ ega
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static String getEgaDelivery(Connection conn, int patientID, Date dateVisitDelivery) throws SQLException, ServletException, ObjectNotFoundException {

        //CurrentPregnancyStatus currentAnte = new CurrentPregnancyStatus();
        String calcEga = null;
        String sql;
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Long> values;
        // Get this data from patient_status table
        Long encounterId = null;
        Long currentFlowId = null;
        Long currentEgaDaysDb = null;
        sql = "SELECT current_lmp_date, current_ega_days, current_ega_days_encounter_id, current_flow  " +
                "FROM patient_status WHERE id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, patientID);

        rs = ps.executeQuery();

        while (rs.next()) {
            encounterId = rs.getLong("current_ega_days_encounter_id");
            //  currentFlowId = rs.getLong("current_flow");
            currentEgaDaysDb = rs.getLong("current_ega_days");
        }

        long egaToday = 0;

        if (encounterId != null & encounterId != 0) {
            sql = "select encounter.id, date_visit AS datevisit, lmp_127_calculated AS field127, edd_128 AS field128, " +
                    "dating_method AS field1615 " +
                    "FROM pregnancydating, encounter WHERE encounter.id = pregnancydating.id and encounter.id=? ";
            values = new ArrayList<Long>();
            values.add(encounterId);
            PregnancyDating pregDate = (PregnancyDating) DatabaseUtils.getBean(conn, PregnancyDating.class, sql, values);
            if (pregDate != null) {
                //  Flow currentFlow = (Flow) DynaSiteObjects.getFlowMap().get(currentFlowId);
                //  if ((currentFlow.getFlowOrder().intValue() < 5) || currentFlow.getFlowOrder().intValue() == 101) {
                java.util.Date dateVisit = pregDate.getDateVisit();
                try {
                    egaToday = PatientRecordUtils.calcEgaforDate(dateVisitDelivery, dateVisit, currentEgaDaysDb);
                    // currentAnte.setEga(egaToday);
                    long egaWeeks = egaToday / 7;
                    long egaDays = egaToday % 7;
                    calcEga = egaWeeks + " " + egaDays + "/7";
                    // currentAnte.setEgaWeeks(egaWeeks + " " + egaDays + "/7");
                } catch (Exception e) {
                    log.error(e);
                }
                //  }
            }
        }
        return calcEga;
    }

    /**
     * Outputs a file of the patient record.
     * @param conn
     * @param patientId
     * @param siteAbbrev
     * @param filePath
     * @param fileName
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws PersistenceException
     * @throws InvocationTargetException
     */
    public static void archivePatient(Connection conn, Long patientId, String siteAbbrev, String filePath, String fileName) throws IOException, ClassNotFoundException, IllegalAccessException, PersistenceException, InvocationTargetException {
        try {
            boolean niceFieldNames = false;
            Patient patient = XmlUtils.generatePatient(conn, patientId, niceFieldNames);
            if (Constants.PATIENT_RECORD_OUTPUT.equals("xml")) {
                XmlUtils.save(patient, filePath + fileName);
            } else {
            	XmlUtils.saveJson(patient, filePath + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            // skip archive
        }
    }

    /**
     * Generates an xml file of the patient record.
     * @param conn
     * @param patientId
     * @param siteAbbrev
     * @param filePath
     * @param fileName
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws PersistenceException
     * @throws InvocationTargetException
     */
   /* public static void archivePatient(Connection conn, Long patientId, String siteAbbrev, String filePath, String fileName) throws IOException, ClassNotFoundException, IllegalAccessException, PersistenceException, InvocationTargetException {
        try {
            FileWriter file = null;
            try {
                file = new FileWriter(filePath + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Writer writer = new BufferedWriter(file);
            writer.write("<?xml version=\"1.0\"?>\n");
            boolean niceFieldNames = false;
            XmlUtils.generatePatientXML(conn, writer, patientId, niceFieldNames);
            writer.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            // skip archive
        }
    }*/

    /**
     * Deletes Patient graph
     * @param conn
     * @param patientId
     * @return
     * @throws Exception
     * @throws ServletException
     * @throws IllegalAccessException
     * @throws PersistenceException
     * @throws ObjectNotFoundException
     * @throws InvocationTargetException
     */
    public static String deletePatient(Connection conn, Long patientId) throws Exception, ServletException, IllegalAccessException, PersistenceException, ObjectNotFoundException, InvocationTargetException {

        StringBuffer sbuf = new StringBuffer();
        // delete comments
        String message = CommentDAO.delete(conn, patientId);
        sbuf.append(message);
        message = ProblemDAO.delete(conn, patientId);
        sbuf.append("<br/>" + message);
        message = OutcomeDAO.deleteAll(conn, patientId);
        sbuf.append("<br/>" + message);
        HashMap forms = DynaSiteObjects.getForms();
        Set formSet = forms.keySet();
        String results = null;
        for (Iterator iterator = formSet.iterator(); iterator.hasNext();) {
            Long formId = (Long) iterator.next();
            Form form = (Form) forms.get(formId);
            String tableName = form.getName();
            results = EncountersDAO.delete(conn, patientId, tableName);
            if (results != null) {
                sbuf.append("<br/>" + results);
            }
        }
        results = null;
        results = PatientDAO.delete(conn, patientId);
        if (results != null) {
            sbuf.append("<br/>" + results);
        }

        results = null;
        results = EncounterValueArchiveDAO.delete(conn, patientId);
        if (results != null) {
            sbuf.append("<br/>" + results);
        }

        results = null;
        HashMap partoMap = DynaSiteObjects.getPartoTables();
        Set partoSet = partoMap.entrySet();
        for (Iterator iterator = partoSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String tableName = (String) entry.getValue();
            results = PartographDAO.delete(conn, patientId, tableName);
        }
        sbuf.append("<br/>" + "Deleted partograph records.");

        results = null;
        List children = PatientDAO.getChildren(conn, patientId);
        if (children.size() == 0) {
            results = PregnancyDAO.delete(conn, patientId);
        } else {
        	results = "Mother has children - do not delete pregnancy yet.";
        }
        if (results != null) {
            sbuf.append("<br/>" + results);
        }


        return sbuf.toString();
    }

    /**
     * Archive patient records to xml, delete patient records from database, log
     *
     * @param conn
     * @param patientId
     * @param siteAbbrev
     * @param logged
     * @param filePath
     * @return
     * @throws Exception
     */
    public static StringBuffer archiveDeletePatient(Connection conn, Long patientId, String siteAbbrev, boolean logged, String filePath) throws Exception {
        int numPatients = 0;
        Patient patient = null;
        StringBuffer sbuf = new StringBuffer();
        String patientXmlName = null;
        String patientName = null;
        try {
            patient = PatientDAO.getOne(conn, patientId);
            String districtId = patient.getDistrictPatientid();
            if (patient.getSurname().length() > 10) {
                try {
                    patientName = patient.getSurname().substring(0, 10).toLowerCase().trim() + "." + patientId;
                } catch (Exception e) {
                    log.error(e);
                }
            } else {
                patientName = patient.getSurname().toLowerCase().trim() + "." + patientId;
            }
            if (districtId != null) {
                try {
                    patientName = patientName + "." + districtId.substring(5);
                } catch (StringIndexOutOfBoundsException e) {
                    log.error("district id: " + districtId);
                    e.printStackTrace();
                    patientName = patientName + "." + districtId;
                }
            }

            patientXmlName = patientName + ".xml";
            String fileName = patientXmlName.replace("/", "-");

            archivePatient(conn, patientId, siteAbbrev, filePath, fileName);
            sbuf.append("Patient data backed up.\n");
            // see if this patient has children
            String message = "";
            List children = PatientDAO.getChildren(conn, patientId);
            for (int i = 0; i < children.size(); i++) {
                Patient child = (Patient) children.get(i);
                message = message + deletePatient(conn, child.getId());
                numPatients ++;
            }
            // now delete this patient
            message = message + deletePatient(conn, patientId);
            numPatients ++;
            sbuf.append("<br/>" + message);
            String dateNow = DateUtils.getNowPretty();
            String output = siteAbbrev + "|" + dateNow + "|" + patientName;
            if (logged) {
                FileWriter file = null;
                try {
                    // append to file.
                    file = new FileWriter(org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH_LOGS + "/deleted.txt", true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Writer writer = new BufferedWriter(file);
                writer.write(output + "\n");
                writer.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            log.error("Error finding patient w/ id: " + patientId);
            // sbuf.append("Error finding patient w/ id: " + patientId);
        }
        // If you're debugging, comment out the next lines to get more output.
        StringBuffer sbuf2 = new StringBuffer();
        sbuf2.append(numPatients);
        if (numPatients > 1) {
        	String message = " patients deleted from " + patientXmlName + " record. This includes any infants in this patient record";
            sbuf2.append(message);
        } else {
        	String message = " patients deleted from " + patientXmlName + " record.";
            sbuf2.append(message);
        }
        sbuf = sbuf2;
        return sbuf;
    }

    /**
     * Looks at all records that could contribute data to the HIV stamp on the ante/postnatal card.
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param smc
     * @return
     */
    public static HivStamp getHivStamp(Connection conn, Long patientId, Long pregnancyId, SafeMotherhoodCareReport smc) {
        HivStamp hivStamp = new HivStamp();
        if (smc.getHiv_test_resultR() != null) {
            if (smc.getHiv_test_resultR().equals("R - reactive")) {
                if (smc.getPrior_hiv_testing_dateR() != null) {
                    Date priorHivDate = Date.valueOf(smc.getPrior_hiv_testing_dateR());
                    hivStamp.setStatusR(priorHivDate);
                    hivStamp.setStatusNr(null);
                    hivStamp.setStatusI(null);
                }
            } else if (smc.getHiv_test_resultR().equals("NR - non-reactive")) {
                if (smc.getPrior_hiv_testing_dateR() != null) {
                    Date priorHivDate = Date.valueOf(smc.getPrior_hiv_testing_dateR());
                    hivStamp.setStatusNr(priorHivDate);
                    hivStamp.setStatusR(null);
                    hivStamp.setStatusI(null);
                }
            } else if (smc.getHiv_test_resultR().equals("I - indeterminate")) {
                if (smc.getPrior_hiv_testing_dateR() != null) {
                    Date priorHivDate = Date.valueOf(smc.getPrior_hiv_testing_dateR());
                    hivStamp.setStatusI(priorHivDate);
                    hivStamp.setStatusR(null);
                    hivStamp.setStatusNr(null);
                }
            }
        }

        try {
            List counselVisits = ReportDAO.getAll(conn, patientId, new Long("91"), SmCounselingVisitReport.class);
            for (int i = 0; i < counselVisits.size(); i++) {
                SmCounselingVisitReport counselingVisit = (SmCounselingVisitReport) counselVisits.get(i);
                if (counselingVisit.getHiv_test_resultR() != null && counselingVisit.getTestDateR() != null) {
                    Date testDate = Date.valueOf(counselingVisit.getTestDateR());
                    if (counselingVisit.getHiv_test_resultR().equals("R - reactive")) {
                        if (counselingVisit.getTestDateR() != null) {
                            hivStamp.setStatusR(testDate);
                            hivStamp.setStatusI(null);
                            hivStamp.setStatusNr(null);
                        }
                    } else if (counselingVisit.getHiv_test_resultR().equals("NR - non-reactive")) {
                        if (counselingVisit.getTestDateR() != null) {
                            hivStamp.setStatusNr(testDate);
                            hivStamp.setStatusI(null);
                            hivStamp.setStatusR(null);
                        }
                    } else if (counselingVisit.getHiv_test_resultR().equals("I - indeterminate")) {
                        if (counselingVisit.getTestDateR() != null) {
                            hivStamp.setStatusI(testDate);
                            hivStamp.setStatusR(null);
                            hivStamp.setStatusNr(null);
                        }
                    }

                }

                if (counselingVisit.getHiv_testedR() != null && counselingVisit.getCounseling_dateR() != null) {
                    Date counselingDate = Date.valueOf(counselingVisit.getCounseling_dateR());
                    if (counselingVisit.getHiv_testedR().equals("Patient refused to be tested.")) {
                        hivStamp.setTr(counselingDate);
                        hivStamp.setTa(null);
                    } else if (counselingVisit.getHiv_testedR().equals("Yes")) {
                        hivStamp.setTa(counselingDate);
                        hivStamp.setTr(null);
                    }
                }

                if (counselingVisit.getCounseling_dateR() != null) {
                    Date counselingDate = Date.valueOf(counselingVisit.getCounseling_dateR());
                    if (counselingVisit.getCounselledR() != null) {
                        if (counselingVisit.getCounselledR().equals("No")) {
                            if (counselingDate != null) {
                                switch (i) {
                                    case 0:
                                        hivStamp.setPcr1(counselingDate);
                                        break;
                                    case 1:
                                        hivStamp.setPcr2(counselingDate);
                                        break;
                                    case 2:
                                        hivStamp.setPcr3(counselingDate);
                                        break;
                                }
                            }
                        } else {
                            hivStamp.setPca(counselingDate);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
			DeliverySumReport deliverySum = (DeliverySumReport) EncountersDAO.getResolvedOne(conn, patientId, pregnancyId, (long) 66, DeliverySumReport.class);
			if (deliverySum.getHiv_tested_in_labourR() != null && deliverySum.getHiv_tested_in_labourR().equals("Yes")) {
				hivStamp.setTa(deliverySum.getDateVisit());
			}
			if (deliverySum.getHiv_test_resultR() != null) {
				Date testDate = deliverySum.getDateVisit();
                if (deliverySum.getHiv_test_resultR().equals("R - reactive")) {
                    if (deliverySum.getHiv_test_resultR() != null) {
                        hivStamp.setStatusR(testDate);
                        hivStamp.setStatusI(null);
                        hivStamp.setStatusNr(null);
                    }
                } else if (deliverySum.getHiv_test_resultR().equals("NR - non-reactive")) {
                    if (deliverySum.getHiv_test_resultR() != null) {
                        hivStamp.setStatusNr(testDate);
                        hivStamp.setStatusI(null);
                        hivStamp.setStatusR(null);
                    }
                } else if (deliverySum.getHiv_test_resultR().equals("I - indeterminate")) {
                    if (deliverySum.getHiv_test_resultR() != null) {
                        hivStamp.setStatusI(testDate);
                        hivStamp.setStatusR(null);
                        hivStamp.setStatusNr(null);
                    }
                }
			}
			if (deliverySum.getMother_received_arvR() != null && deliverySum.getMother_received_arvR().equals("Yes")) {
				hivStamp.setMga(deliverySum.getDateVisit());
			}
			if (deliverySum.getMother_received_arvR() != null && deliverySum.getMother_received_arvR().equals("Patient refused")) {
				hivStamp.setMra(deliverySum.getDateVisit());
			}
		} catch (ObjectNotFoundException e1) {
			// it's ok - patient probably antenatal
		}

        try {
            List arvVisits = ReportDAO.getAll(conn, patientId, new Long("89"), ArvRegimenReport.class);
            for (int i = 0; i < arvVisits.size(); i++) {
                ArvRegimenReport regimenReport = (ArvRegimenReport) arvVisits.get(i);
                if (regimenReport.getReceivedRegimenR() != null && regimenReport.getRegimen_visit_dateR() != null) {
                    Date regimenDate = Date.valueOf(regimenReport.getRegimen_visit_dateR());
                    if (regimenReport.getReceivedRegimenR().equals("Yes")) {
                        hivStamp.setMga(regimenDate); // mother given arvs
                        hivStamp.setMra(null); // mother refused arvs
                    } else if (regimenReport.getReceivedRegimenR().equals("No")) {
                        if (hivStamp.getStatusR() != null) {  // sometimes arvRegimen form submitted even if patient is hiv neg.
                            //hivStamp.setMra(regimenDate); // mother refused arvs
                            hivStamp.setMga(null); // mother given arvs
                        }
                    } else if (regimenReport.getReceivedRegimenR().equals("Patient refused")) {
                        if (hivStamp.getStatusR() != null) {  // sometimes arvRegimen form submitted even if patient is hiv neg.
                            hivStamp.setMra(regimenDate); // mother refused arvs
                            hivStamp.setMga(null); // mother given arvs
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // loop through newborns for this pregnancy to get iga and inga data
        List childrenThisPreg = PatientDAO.getChildren(conn, patientId, pregnancyId);
        if (childrenThisPreg.size() > 0) {
            for (int i = 0; i < childrenThisPreg.size(); i++) {
                Patient child = (Patient) childrenThisPreg.get(i);
                try {
                    List arvVisits = ReportDAO.getAll(conn, child.getId(), new Long("89"), ArvRegimenReport.class);
                    for (int j = 0; j < arvVisits.size(); j++) {
                        ArvRegimenReport regimenReport = (ArvRegimenReport) arvVisits.get(j);
                        if (regimenReport.getReceivedRegimenR() != null && regimenReport.getRegimen_visit_dateR() != null)
                        {
                            Date regimenDate = Date.valueOf(regimenReport.getRegimen_visit_dateR());
                            if (regimenReport.getReceivedRegimenR().equals("Yes")) {
                                hivStamp.setIga(regimenDate); // mother given arvs
                                hivStamp.setInga(null); // mother refused arvs
                            } else if (regimenReport.getReceivedRegimenR().equals("No")) {
                                hivStamp.setInga(regimenDate); // mother refused arvs
                                hivStamp.setIga(null); // mother given arvs
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // IFB (Infant fed by Breast)  and  IFR (Infant fed by Replacement (formula)) - Feeding Type field from Newborn Eval.
                try {
                    NewbornEvalReport newbornEval = (NewbornEvalReport) EncountersDAO.getResolvedOne(conn, child.getId(), pregnancyId, (long) 23, NewbornEvalReport.class);
                    if (newbornEval.getFeeding_typeR() != null) {
                        if (newbornEval.getFeeding_typeR().equals("Breastfeeding")) {
                            hivStamp.setIfb(Boolean.TRUE);
                        } else if (newbornEval.getFeeding_typeR().equals("Formula")) {
                            hivStamp.setIfr(Boolean.TRUE);
                        } else if (newbornEval.getFeeding_typeR().equals("Combined")) {
                            hivStamp.setIfr(Boolean.TRUE);
                        }
                    }

                    // IFA (Infant given AntiRetrovirals) from Newborn Eval.
                    if (newbornEval.getRbd_home_regimenR() != null) {
                    	String newbornRegimen = newbornEval.getRbd_home_regimenR();
                    	Date regimenDate = newbornEval.getDateVisit();
                    	if (newbornRegimen.equals("NVP+AZT")) {
                    		hivStamp.setIga(regimenDate);
                    	}
                    }
                } catch (ObjectNotFoundException e) {
                    log.error("Error while preparing Pregnancy and Discharge Summary card: Newborn eval not completed for patient id: " + child.getId());
                }
            }
        }
        return hivStamp;
    }

    /**
     * assembles zeprs id string w/ checksum.
     * @param districtId
     * @param siteId
     * @param patientId
     * @return zeprs id
     */
    public static String createZeprsId(String districtId, String siteId, String patientId) {

        // int checksum = new Integer(patientId).intValue() % 10;
        int sum = 0;
        for (int i = 0; i < patientId.length(); i++) {
            String str = String.valueOf(patientId.charAt(i));
            int value = Integer.valueOf(str);
            sum += value;
        }
        int checksum = sum % 10;
        String zeprsId = districtId + "-" + siteId + "-" + patientId + "-" + checksum;

        return zeprsId;
    }

    /**
     * archive encounter and saves xml graph of encounter and its outcomes/comments. Deletes encounter, outcome, and comments
     * @param conn
     * @param formId
     * @param encounterId
     * @param username
     * @param site
     * @param vo
     * @throws Exception
     */
    public static void deleteEncounter(Connection conn, Long formId, Long encounterId, String username, Site site, EncounterData vo) throws Exception {
        Form encounterForm = (Form) DynaSiteObjects.getForms().get(formId);
        String className = "org.cidrz.project.zeprs.valueobject.gen." + StringManipulation.fixClassname(encounterForm.getName());
        Class clazz = null;
        Long patientId = vo.getPatientId();
        Long pregnancyId = vo.getPregnancyId();
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Timestamp dateDeleted = new Timestamp(System.currentTimeMillis());
        EncounterData encounter = null;
        encounter = (EncounterData) EncountersDAO.getOneById(conn, encounterId, formId, clazz);
        List outcomes = OutcomeDAO.getAllforEncounter(conn, encounterId);
        for (int i = 0; i < outcomes.size(); i++) {
            OutcomeImpl outcome = (OutcomeImpl) outcomes.get(i);
            List comments = CommentDAO.getAllforOutcome(conn, outcome.getId());
            outcome.setComments(comments);
        }
        encounter.setOutcomes(outcomes);
        String tableName = encounterForm.getName().toLowerCase();
        Long id = EncounterArchiveDAO.saveArchive(conn, username, encounter, dateDeleted);
        String siteAbbrev = site.getAbbreviation();
        String fileName = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + "/deletions/" + "enc" + encounter.getId() + ".xml";
        if (formId.intValue() == 79) {
        	Partograph partograph = PartographDAO.getPartograph(conn, patientId, pregnancyId);
        	XmlUtils.save(partograph, fileName);
        } else {
        	XmlUtils.save(encounter, fileName);
        }

        // Loop through the comments and delete them.
        // Some encounters may have multiple outcomes and/or comments.
        for (int i = 0; i < outcomes.size(); i++) {
            OutcomeImpl outcome = (OutcomeImpl) outcomes.get(i);
            CommentDAO.deleteOutcomeComments(conn, outcome.getId());
        }
        if (id != null) {
        	if (formId.intValue() == 79) {
        		PartographDAO.deletePartograph(conn, patientId, pregnancyId);
        	}
        	EncountersDAO.delete(conn, tableName, encounterId);
            try {
                vo.setLastModified(dateDeleted);    // sync up the timestamps
                PatientStatusDAO.touchPatientStatus(conn, vo, username, site.getId(), encounter.getPatientId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Archive the  outcome, fetches comments and saves graph of outcome to xml; Deletes outcome and comments.
     * @param conn
     * @param outcomeId
     * @param username
     * @param site
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     * @throws IOException
     */
    public static Outcome deleteOutcome(Connection conn, Long outcomeId, String username, Site site) throws SQLException, ServletException, ObjectNotFoundException, IOException {
        Outcome outcome = OutcomeDAO.getOne(conn, outcomeId);
        OutcomeArchiveDAO.save(conn, outcome, username, site.getId());
        List comments = CommentDAO.getAllforOutcome(conn, outcomeId);
        outcome.setComments(comments);
        String siteAbbrev = site.getAbbreviation();
        String fileName = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + "/deletions/" + "outcome" + outcome.getId() + ".xml";
        XmlUtils.save(outcome, fileName);
        CommentDAO.deleteOutcomeComments(conn, outcomeId);
        OutcomeDAO.deleteOne(conn, outcomeId);
        try {
			PatientStatusDAO.touchPatientStatus(conn, null, username, site.getId(), outcome.getPatientId());
		} catch (Exception e) {
			log.debug(e);
		}
        return outcome;
    }

    /**
     * Archive the  outcome, fetches comments and saves graph of outcome to xml; Deletes outcome and comments.
     * If you're syncing a patient record, an outcome may have already been deleted, so you do the best you can w/ the OutcomeArchive.
     * Persist to the db as much of the outcome info as possible (it won't include comments)
     * and save the OutcomeArchive to xml.
     * @param conn
     * @param uuid
     * @param username
     * @param site
     * @param archive
     * @param patient
     * @param duplicateId - local patient id
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     * @throws IOException
     */
    public static Outcome deleteOutcome(Connection conn, String uuid, String username, Site site, OutcomeArchive archive, Patient patient, Long duplicateId) throws SQLException, ServletException, ObjectNotFoundException, IOException {
    	String siteAbbrev = site.getAbbreviation();
    	Outcome outcome = null;
		try {
			outcome = OutcomeDAO.getOne(conn, uuid);
	    	List comments = CommentDAO.getAllforOutcome(conn, uuid);
	    	if (comments.size() > 0) {
	    		outcome.setComments(comments);
	        	CommentDAO.deleteOutcomeComments(conn, uuid);
	    	}
	    	String fileName = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + "/deletions/" + "outcome" + uuid + ".xml";
			XmlUtils.save(archive, fileName);
	    	OutcomeDAO.deleteOne(conn, uuid);
	    	OutcomeArchiveDAO.save(conn, outcome, username, site.getId());
		} catch (ObjectNotFoundException e) {
			// record already deleted or is missing.
			// log.debug("Outcome record missing - uuid: " + uuid);
			outcome = new OutcomeImpl();
			try {
				try {
					BeanUtils.copyProperties(outcome, archive);
				} catch (ConversionException e1) {
					// log.debug("unable to copy value: " + e);
				}
				outcome.setOutcomeUuid(archive.getUuid());
				outcome.setPatientId(duplicateId);
				outcome.setPatientUuid(patient.getUuid());
		    	String fileName = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + "/deletions/" + "outcome" + uuid + ".xml";
				XmlUtils.save(archive, fileName);
		    	OutcomeArchiveDAO.save(conn, outcome, username, site.getId());
			} catch (IllegalAccessException e1) {
				log.debug(e1);
			} catch (InvocationTargetException e1) {
				log.debug(e1);
			}
		}
    	return outcome;
    }

    /**
     * Archive the problem, fetches comments and saves graph of problem to xml; Deletes problem and comments.
     * @param conn
     * @param id
     * @param username
     * @param site
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     * @throws IOException
     */
    public static Long deleteProblem(Connection conn, Long id, String username, Site site) throws SQLException, ServletException, ObjectNotFoundException, IOException {
        Problem problem = ProblemDAO.getOne(conn, id);
        ProblemArchiveDAO.save(conn, problem, username, site.getId());
        List comments = CommentDAO.getAllforProblem(conn, id);
        problem.setComments(comments);
        String siteAbbrev = site.getAbbreviation();
        String fileName = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + "/deletions/" + "problem" + problem.getId() + ".xml";
        XmlUtils.save(problem, fileName);
        CommentDAO.deleteProblemComments(conn, id);
        ProblemDAO.deleteOne(conn, id);
        try {
			PatientStatusDAO.touchPatientStatus(conn, null, username, site.getId(), problem.getPatientId());
		} catch (Exception e) {
			log.error(e);
		}
        Long patientId = problem.getPatientId();
        return patientId;
    }

    /**
     * Used when syncing patient records.
     * Archive the problem, fetches comments and saves graph of problem to xml; Deletes problem and comments.
     * @param conn
     * @param uuid
     * @param username
     * @param site
     * @param archive
     * @param duplicateId
     * @param patient
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     * @throws IOException
     */
    public static Long deleteProblem(Connection conn, String uuid, String username, Site site, ProblemArchive archive, Patient patient, Long duplicateId) throws SQLException, ServletException, ObjectNotFoundException, IOException {
    	String siteAbbrev = site.getAbbreviation();
    	Problem problem = null;
		try {
			problem = ProblemDAO.getOne(conn, uuid);
			List comments = CommentDAO.getAllforProblem(conn, uuid);
			if (comments.size() > 0) {
		    	problem.setComments(comments);
		    	CommentDAO.deleteProblemComments(conn, uuid);
			}
	    	String fileName = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + "/deletions/" + "problem" + uuid + ".xml";
	    	XmlUtils.save(problem, fileName);
	    	ProblemDAO.deleteOne(conn, uuid);
		} catch (ObjectNotFoundException e) {
			problem = new Problem();
			try {
				BeanUtils.copyProperties(problem, archive);
			} catch (ConversionException e1) {
				// log.debug("unable to copy value: " + e);
			} catch (IllegalAccessException e1) {
				log.debug(e1);
			} catch (InvocationTargetException e1) {
				log.debug(e1);
			}
			problem.setUuid(uuid);
			problem.setPatientId(duplicateId);
			problem.setPatientUuid(patient.getUuid());
	    	String fileName = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + "/deletions/" + "problem" + uuid + ".xml";
	    	XmlUtils.save(problem, fileName);
		}
    	ProblemArchiveDAO.save(conn, problem, username, site.getId());
    	Long patientId = problem.getPatientId();
    	return patientId;
    }

	/**
	 * Creates instance of PatientRegistration w/ newborn data
	 * Used for dwr remote widget when creating newborns in delivery summary
	 *
	 * @param infant
	 * @return instance of PatientRegistration w/ newborn data
	 */
	public static PatientRegistration createPatientRegistrationObject(Patient infant) {
	    PatientRegistration patReg = new PatientRegistration();
	    patReg.setDateVisit(infant.getBirthdate());
	    Long flowId = new Long("9");
	    Long formId = new Long("1");
	    patReg.setFlowId(flowId);
	    patReg.setFormId(formId);
	    patReg.setPatientId(infant.getId());
	    patReg.setPregnancyId(infant.getPatientStatusreport().getCurrentPregnancyId());
	    //surname
	    patReg.setField6(infant.getSurname());
	    //firstname
	    patReg.setField7(infant.getFirstName());
	    // age
	    patReg.setField1135(new Integer("0"));
	    // birthdate
	    patReg.setField17(infant.getBirthdate());
	    return patReg;
	}

	/**
	 * Populates an instance of Patient w/ newborn data.
	 * Used for dwr remote widget when creating newborns in delivery summary
	 *
	 * @param seqNum
	 * @param patientId
	 * @param sex
	 * @param dateValue
	 * @param sessionPatient
	 * @param birthtime
	 * @return instance of Patient w/ newborn data
	 */
	public static Patient createNewbornObject(String seqNum, Long patientId, Integer sex, String dateValue, SessionPatient sessionPatient, String birthtime) {

	    Patient infant = new Patient();
	    if (seqNum.equals("0")) {
	        seqNum = "1";
	    }
        String uuid = sessionPatient.getUuid();
        infant.setParentUuid(uuid);
	    infant.setFirstName("Baby" + seqNum);
	    infant.setSurname(sessionPatient.getSurname());
	    infant.setSex(sex);
	    infant.setBirthdate(Date.valueOf(dateValue));
	    if (! birthtime.equals("")) {
	        try {
				infant.setTimeOfBirth(Time.valueOf(birthtime));
			} catch (IllegalArgumentException e) {
			}
	    }
	    infant.setParent(patientId);
	    infant.setSequenceNumber(new Integer(seqNum));
	    infant.setPregnancyId(sessionPatient.getCurrentPregnancyId());
	    infant.setParentDistrictPatientid(sessionPatient.getDistrictPatientid());
	    // create a new PatientStatusReport
	    PatientStatusReport newpsreport = new PatientStatusReport();
	    newpsreport.setPatient(infant);
	    newpsreport.setCurrentPregnancyId(sessionPatient.getCurrentPregnancyId());
	    //newpsreport.setCurrentFlow(p.getPatientStatusreport().getCurrentFlow());
	    //newpsreport.setCurrentFlowEncounter((EncounterData) formObj);
	    infant.setPatientStatusreport(newpsreport);
	    sessionPatient.getChildren().add(infant);
	    return infant;
	}
}
