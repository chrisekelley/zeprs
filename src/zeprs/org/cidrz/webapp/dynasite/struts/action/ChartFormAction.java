/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.dao.PregnancyDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.utils.StringManipulation;
import org.cidrz.webapp.dynasite.valueobject.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * Prepare patient record.
 * Add patient, confirmation, and pregnancy to the session.
 * Create the chart, tasklist, and problems list.
 */

public class ChartFormAction extends BasePatientAction {
    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());


    /**
     * @param mapping  The ActionMapping used to select this instance
     * @param form     The optional ActionForm bean for this request (if any)
     * @param request  The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return Action to forward to
     * @throws Exception if an input/output error or servlet exception occurs
     */
    protected ActionForward doExecute(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {

        // Extract attributes we will need
        HttpSession session = request.getSession();
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            SessionPatient sessionPatient = null;
            Long patientId = null;
            Long pregnancyId = null;
            sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
            try {
                patientId = sessionPatient.getId();
                pregnancyId = sessionPatient.getCurrentPregnancyId();
            } catch (Exception e) {
                log.error("Unable to get SessionPatient field");
            }

            // get form for display below the chart based on which flow patient is in
            Form encounterForm = null;

            String formId;
            if (mapping.getParameter() != null && !mapping.getParameter().equals("")) {
                formId = mapping.getParameter();
            } else {
                formId = request.getAttribute("id").toString();
            }
            encounterForm = (Form) DynaSiteObjects.getForms().get(Long.valueOf(formId));
            Set pageItems = encounterForm.getPageItems();
            for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
                PageItem pageItem = (PageItem) iterator.next();
                request.setAttribute("field" + pageItem.getForm_field().getId(), pageItem);
            }

            request.setAttribute("encounterForm", encounterForm);

            List drugs = DynaSiteObjects.getDrugs();
            request.setAttribute("drugs", drugs);
            // tells tags to enabled dwr input for data
            request.setAttribute("dwr_input", "1");

            String classname = StringManipulation.fixClassname(encounterForm.getName());

            // The following items are used to render the javascript for the widgets
            request.setAttribute("classname", classname);

            // name of the remote class used for DWR
            request.setAttribute("remoteClass", "GenericChart");

            // String user = request.getUserPrincipal().getName();
            request.setAttribute("user", username);

            String siteId = "";
            try {
                siteId = SessionUtil.getInstance(session).getClientSettings().getSiteId().toString();
            } catch (SessionUtil.AttributeNotFoundException e) {
                // it's ok - we're in admin mode.
            }

            request.setAttribute("siteId", siteId);

            if (formId.equals("2")) {
                List prevousPregnancies = PregnancyDAO.getPatientPregnancies(conn, patientId);
                Long currentPregId = null;
                for (int i = 0; i < prevousPregnancies.size(); i++) {
                    Pregnancy pregnancy = (Pregnancy) prevousPregnancies.get(i);
                    if (pregnancy.getDatePregnancyEnd() == null) {
                        currentPregId = pregnancy.getId();
                    }
                }
                if (prevousPregnancies.size() > 0) {
                    request.setAttribute("prevousPregnancies", prevousPregnancies);
                }
                if (currentPregId != null) {
                    request.setAttribute("currentPregId", currentPregId.toString());
                }
                // populate the chart w/ all records from all pregnancies
                List chartItems = null;
                Class className = Class.forName("org.cidrz.project.zeprs.valueobject.gen." + classname);
                chartItems = EncountersDAO.getAllPregs(conn, patientId, new Long(formId), className);
                // Attach a map of encounter values that has enumerations already resolved.
                EncounterData encounter;
                for (int i = 0; i < chartItems.size(); i++) {
                    encounter = (EncounterData) chartItems.get(i);
                    Map encMap = new LinkedHashMap();
                    Form encForm = (Form) DynaSiteObjects.getForms().get(encounter.getFormId());
                    encMap = PatientRecordUtils.getEncounterMap(encForm, encounter, "fieldId");
                    encounter.setEncounterMap(encMap);
                }
                request.setAttribute("chartItems", chartItems);
                // loading of body onload DWRUtil.useLoadingMessage()
                request.setAttribute("dwr", 1);
            } else {
                // populate the records for this class
                List chartItems = null;

                try {
                    chartItems = getChartItems(classname, conn, patientId, pregnancyId, formId);
                } catch (IOException e) {
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                } catch (ServletException e) {
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                } catch (SQLException e) {
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                }

                request.setAttribute("chartItems", chartItems);
                // loading of body onload DWRUtil.useLoadingMessage()
                request.setAttribute("dwr", 1);

                // Attach a map of encounter values that has enumerations already resolved.
                EncounterData encounter;
                for (int i = 0; i < chartItems.size(); i++) {
                    encounter = (EncounterData) chartItems.get(i);
                    Map encMap = new LinkedHashMap();
                    Form encForm = (Form) DynaSiteObjects.getForms().get(encounter.getFormId());
                    encMap = PatientRecordUtils.getEncounterMap(encForm, encounter, "fieldId");
                    encounter.setEncounterMap(encMap);
                }

                if (formId.equals("55")) {
                    List arvChartItems = null;
                    try {
                        String arvClassname = StringManipulation.fixClassname("arvRegimen");
                        arvChartItems = getChartItems(arvClassname, conn, patientId, pregnancyId, "89");
                        request.setAttribute("chart", arvChartItems);
                    } catch (IOException e) {
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    } catch (ServletException e) {
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    } catch (SQLException e) {
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    }
                    Form chartForm = (Form) DynaSiteObjects.getForms().get(Long.valueOf("89"));
                    request.setAttribute("chartForm", chartForm);
                }
            }

            if (pregnancyId.intValue() != -1) {   // not a new pregnancy
                // used for uth admission
                if (request.getParameter("referralId") != null) {
                    Long referralId = Long.valueOf(request.getParameter("referralId"));
                    request.setAttribute("referralId", referralId);
                    Outcome referral = OutcomeDAO.getOne(conn, referralId);
                    request.setAttribute("referral", referral);
                }

                // ega
                Integer egaToday = null;
                if (sessionPatient.getCurrentEgaDaysDB() != null) {
                    egaToday = sessionPatient.getCurrentEgaCalc();
                }
                request.setAttribute("egaToday", egaToday);
            }

            Boolean status = Boolean.valueOf(true);
            List activeProblems = null;
            try {
                activeProblems = PatientRecordUtils.assembleProblemTaskList(conn, patientId, pregnancyId, status, sessionPatient);
            } catch (SQLException e) {
                log.error(e);
            } catch (ObjectNotFoundException e) {
                // probably a new pregnancy
            } catch (PersistenceException e) {
                log.error(e);
            } catch (ServletException e) {
                log.error(e);
            } catch (IllegalAccessException e) {
                log.error(e);
            } catch (InvocationTargetException e) {
                log.error(e);
            }

            request.setAttribute("activeProblems", activeProblems);

            // now get inactive problems
            status = Boolean.valueOf(false);
            List inactiveProblems = PatientRecordUtils.assembleProblemTaskList(conn, patientId, pregnancyId, status, sessionPatient);

            request.setAttribute("inactiveProblems", inactiveProblems);

            List yearList = DateUtils.getYearList();
            request.setAttribute("yearList", yearList);
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }

        return mapping.findForward("success");
    }

    public static List getChartItems(String classname, Connection conn, Long patientId, Long pregnancyId, String formId) throws ClassNotFoundException, IOException, ServletException, SQLException {
        List records;
        Class className = Class.forName("org.cidrz.project.zeprs.valueobject.gen." + classname);
        records = EncountersDAO.getAll(conn, patientId, pregnancyId, "SQL_RETRIEVE" + formId, className);
        // Attach a map of encounter values that has enumerations already resolved.
        EncounterData encounter;
        Form encForm = (Form) DynaSiteObjects.getForms().get(new Long(formId));
        for (int i = 0; i < records.size(); i++) {
            encounter = (EncounterData) records.get(i);
            Map encMap = new LinkedHashMap();
            encMap = PatientRecordUtils.getEncounterMap(encForm, encounter, "fieldId");
            encounter.setEncounterMap(encMap);
        }
        return records;
    }
}
