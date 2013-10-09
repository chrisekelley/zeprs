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
import org.cidrz.project.zeprs.valueobject.gen.RoutineAnte;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.utils.StringManipulation;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Prepare patient record.
 * Add patient, confirmation, and pregnancy to the session.
 * Create the chart, tasklist, and problems list.
 */

public final class PatientAnteAction extends BasePatientAction {
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
            try {
                sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
            } catch (SessionUtil.AttributeNotFoundException e) {
            	request.setAttribute("exception", "Sorry - we're having a problem with the system. Please return to the home page and try again.");
            	log.error("SessionPatient is null - forwarded to error page.");
                return mapping.findForward("error");
            }

            Long patientId = null;
            Long pregnancyId = null;
            Long mostRecentEncounterId = null;
            try {
                assert sessionPatient != null;
                patientId = sessionPatient.getId();
                pregnancyId = sessionPatient.getCurrentPregnancyId();
                mostRecentEncounterId = sessionPatient.getCurrentFlowEncounterId();
            } catch (Exception e) {
                log.error("Unable to get SessionPatient field" + e);
            }
            // we must have ega - redirect to pregDating otherwise
            Integer currentEgaDays = null;
            try {
                assert sessionPatient != null;
                currentEgaDays = null;
                currentEgaDays = sessionPatient.getCurrentEgaDaysDB();
            } catch (Exception e) {
                log.error("Unable to get SessionPatient field" + e);
            }
            if (currentEgaDays == null & sessionPatient.getDatePregnancyEnd() == null) {
                return mapping.findForward("pregnancyDating");
            }

            // populate the Antenatal table
            List chartItems = null;

            try {
                chartItems = EncountersDAO.getAllRoutineAnte(conn, patientId, pregnancyId, "SQL_RETRIEVE80");
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
            for (int i = 0; i < chartItems.size(); i++) {
                RoutineAnte encounter = (RoutineAnte) chartItems.get(i);
                Map encMap = new LinkedHashMap();
                Form encForm = (Form) DynaSiteObjects.getForms().get(new Long("80"));
                encMap = PatientRecordUtils.getEncounterMap(encForm, encounter, "fieldId");
                encounter.setEncounterMap(encMap);
            }

            chartItems = null;

            // get form for display below the chart based on which flow patient is in
            Form encounterForm = null;
            request.setAttribute("mostRecentEncounterId", mostRecentEncounterId);
            Long parentId = null;
            parentId = sessionPatient.getParentId();

            // do not display encounter form for children
            if (parentId == null) {
                encounterForm = (Form) DynaSiteObjects.getForms().get(new Long("80"));
                request.setAttribute("encounterForm", encounterForm);
            }

            if (sessionPatient.getDatePregnancyEnd() == null)
            {    // Don't calc. egaToday if viewing records from prev. pregnancy
                // ega
                Integer egaToday = null;

                if (sessionPatient.getCurrentEgaDaysDB().intValue() != 0) {
                    egaToday = sessionPatient.getCurrentEgaCalc();
                }

                request.setAttribute("egaToday", egaToday);
            }
            // get the list of  problems

            Boolean status = Boolean.valueOf(true);

            // get the tasks and put them into activeProblems
            // but first check if this patient has a parent. if true, then you don't need to list missing forms.
            // forms(tasks) are only required for the mother.
            // also, you are viewing previous pregnancy, also do not need these tasks.
            List activeProblems = PatientRecordUtils.assembleProblemTaskList(conn, patientId, pregnancyId, status, sessionPatient);
            request.setAttribute("activeProblems", activeProblems);
            // now get inactive problems
            status = Boolean.valueOf(false);
            List inactiveProblems = PatientRecordUtils.assembleProblemTaskList(conn, patientId, pregnancyId, status, sessionPatient);
            request.setAttribute("inactiveProblems", inactiveProblems);
            // The following items are used to render the javascript for the widgets
            String classname = StringManipulation.fixClassname(encounterForm.getName());
            request.setAttribute("classname", classname);

            // name of the remote class used for DWR
            request.setAttribute("remoteClass", "Antenatal");
            request.setAttribute("user", username);

            String siteId = "";
            try {
                siteId = SessionUtil.getInstance(session).getClientSettings().getSiteId().toString();
            } catch (SessionUtil.AttributeNotFoundException e) {
                // it's ok - we're in admin mode.
            }

            request.setAttribute("siteId", siteId);

            if (parentId != null) {
                request.setAttribute("parentId", parentId);
            }
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return mapping.findForward("success");
    }

}
