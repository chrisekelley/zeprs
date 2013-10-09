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
import org.cidrz.webapp.dynasite.dao.PregnancyDAO;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.Pregnancy;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;
import org.cidrz.webapp.dynasite.valueobject.TaskList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Connection;
import java.util.List;

/**
 * Collect patient Tasks
 *
 * @author Chris Kelley
 */
public final class PatientTaskAction extends BasePatientAction {
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
                log.error("Unable to get SessionPatient");
            }

            Long patientId = null;
            Long pregnancyId = null;
            Long currentFlowId = null;
            try {
                assert sessionPatient != null;
                patientId = sessionPatient.getId();
                pregnancyId = sessionPatient.getCurrentPregnancyId();
                currentFlowId = sessionPatient.getCurrentFlowId();
            } catch (Exception e) {
                log.error("Unable to get SessionPatient field" + e);
            }

            TaskList tasklist = new TaskList();
            Long viewFlowId = null;
            // if flowId is in the request, set sessionPatient's flowId
            if (request.getParameter("flowId") != null) {
                viewFlowId = Long.decode(request.getParameter("flowId"));
            } else if (request.getAttribute("flowId") != null) {
                viewFlowId = Long.decode(request.getAttribute("flowId").toString());
            } else {
                try {
                    viewFlowId = currentFlowId;
                    assert viewFlowId != null;
                    if (viewFlowId.intValue() == 9) {   // if it's new patient registration
                        viewFlowId = new Long("2");     // send to history
                    }
                } catch (Exception e) {
                    // a test user w/ no flo - send to history.
                    viewFlowId = new Long("2");
                }
            }

            tasklist.getTaskList(conn, tasklist, patientId, viewFlowId, pregnancyId, sessionPatient);

            // deal w/ prev zeprs pregnancies - may not have started new pregnancy yet.
            int prevPregs = 0;
            List prevZeprsPregs = PregnancyDAO.getPatientPregnancies(conn, patientId);
            for (int i = 0; i < prevZeprsPregs.size(); i++) {
                Pregnancy pregnancies = (Pregnancy) prevZeprsPregs.get(i);
                if (pregnancies.getDatePregnancyEnd() != null) {
                    prevPregs ++;
                }
            }
            request.setAttribute("prevZeprsPregs", prevPregs);
            request.setAttribute("tasklist", tasklist);

            Boolean status = Boolean.valueOf(true);
            List activeProblems = PatientRecordUtils.assembleProblemTaskList(conn, patientId, pregnancyId, status, sessionPatient);
            request.setAttribute("activeProblems", activeProblems);
            // now get inactive problems
            status = Boolean.valueOf(false);
            List inactiveProblems = PatientRecordUtils.assembleProblemTaskList(conn, patientId, pregnancyId, status, sessionPatient);
            request.setAttribute("inactiveProblems", inactiveProblems);
            conn.close();

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