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
import org.cidrz.webapp.dynasite.dao.DemographicsDAO;
import org.cidrz.webapp.dynasite.dao.PregnancyDAO;
import org.cidrz.webapp.dynasite.dao.SessionPatientDAO;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.security.Principal;
import java.sql.Connection;
import java.util.List;


public final class DemoAction extends BasePatientAction {
    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
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
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        Connection conn = null;
        ActionForward forward = null;
        Long patientId = null;
        Long pregnancyId = null;
        SessionPatient sessionPatient = null;
        HttpSession session = request.getSession();
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);

            patientId = SessionPatientDAO.getPatientId(conn, request);
            sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
            pregnancyId = sessionPatient.getCurrentPregnancyId();

            request.setAttribute("patientId", patientId);
            // Find encounter id for Patient Registration form
            String encounterID = DemographicsDAO.getDemographicsId(conn, patientId).toString();
            request.setAttribute("encounterId", encounterID);
            if ((patientId != null)) {
                Boolean status = Boolean.valueOf(true);
                List activeProblems = PatientRecordUtils.assembleProblemTaskList(conn, patientId, pregnancyId, status, sessionPatient);
                request.setAttribute("activeProblems", activeProblems);
                // now get inactive problems
                status = Boolean.valueOf(false);
                List inactiveProblems = PatientRecordUtils.assembleProblemTaskList(conn, patientId, pregnancyId, status, sessionPatient);
                request.setAttribute("inactiveProblems", inactiveProblems);
            }

            List prevousPregnancies = PregnancyDAO.getPatientPregnancies(conn, patientId);
            if (prevousPregnancies.size() > 0) {
                request.setAttribute("prevousPregnancies", prevousPregnancies);

            }
            forward = new ActionForward("/form1/correct.do");
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return forward;
    }
}