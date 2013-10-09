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
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.SessionPatientDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;


/**
 * Implementation of <strong>Action</strong> that displays an encounter record.
 *
 * @author ckelley
 */
public final class PatientEncounterAction extends BasePatientAction {

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
        String encounterID = request.getParameter("id");
        Principal userPrincipal = request.getUserPrincipal();
        String username = userPrincipal.getName();
        Long formId = null;
        Connection conn = null;
        
        Long patientId = null;
        Long pregnancyId = null;
        SessionPatient sessionPatient = null;
        HttpSession session = request.getSession();
        
        
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            // patientId = SessionPatientDAO.getPatientId(conn, request);
            sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
            patientId = sessionPatient.getId();
            pregnancyId = sessionPatient.getCurrentPregnancyId();
            if (encounterID != null && encounterID != "") {
                try {
                    formId = EncountersDAO.checkEncounterId(conn, patientId, pregnancyId, Long.decode(encounterID));
                } catch (SQLException e) {
                    log.error(e);
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                } catch (ServletException e) {
                    log.error(e);
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                } catch (ObjectNotFoundException e) {
                    try {
                        formId = EncountersDAO.checkEncounterIdNoPreg(conn, patientId, Long.decode(encounterID));
                    } catch (ObjectNotFoundException e1) {
                        String error = "This encounter either does not exist, is not for the current patient, " +
                                "or the session has expired. " +
                                "Please hit the \"Back\" button and refresh the page or return to the Home page and select another patient. ";
                        request.setAttribute("exception", error);
                        return mapping.findForward("error");
                    }
                } catch (NumberFormatException e) {
                    log.error(e);
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                }
            }
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        // Send to the correct forward.
        if (formId == null) {
            return mapping.findForward("problem");
        } else {
            HashMap formNames = DynaSiteObjects.getFormNames();
            request.setAttribute("encounterId", encounterID);

            String referralId = null;
            if (request.getParameter("referralId") != null) {
                referralId = request.getParameter("referralId");
            }

            ActionForward forward = null;
            try {
                String forwardString = (String) formNames.get("form" + formId);
                if (forwardString == null) {
                    forward = new ActionForward("/form" + formId + "/correct.do");
                } else {
                    if (referralId != null) {
                        forward = new ActionForward("/" + forwardString + ".do" + "?patientId=" + patientId + "&referralId=" + referralId);
                        forward.setRedirect(true);
                    } else {
                        forward = new ActionForward("/" + forwardString + ".do" + "?patientId=" + patientId);
                        forward.setRedirect(true);
                    }

                }
            } catch (Exception e) {
                forward = new ActionForward("/form" + formId + "/correct.do");
            }
            return forward;
        }
    }
}