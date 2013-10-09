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
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
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
 * Display correct safe motherhood form - in edit or new form mode
 * Add patient, confirmation, and pregnancy to the session.
 */

public final class PatientSafeMotherAction extends BasePatientAction {
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
        ActionForward forward = null;
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
            try {
                assert sessionPatient != null;
                patientId = sessionPatient.getId();
                pregnancyId = sessionPatient.getCurrentPregnancyId();
            } catch (Exception e) {
                log.error("Unable to get SessionPatient field" + e);
            }

            Form nextForm = new Form();
            EncounterData safeMotherhoodEnc = null;
            try {
                safeMotherhoodEnc = (EncounterData) EncountersDAO.getId(conn, patientId, pregnancyId, new Long("92"));
                // send user to safe motherhood form edit mode
            } catch (SQLException e) {
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // send user to new safe motherhood form
                nextForm = (Form) DynaSiteObjects.getForms().get(new Long("92"));
                String formName = nextForm.getName();
                request.setAttribute("name", formName);
                request.setAttribute("id", nextForm.getId());
                return mapping.findForward("nextForm");
            }

            HashMap links = new HashMap();
            String url1 = "patientTask.do;jsessionid=" + request.getSession().getId() + "?patientId=" + patientId + "&flowId=102";

            String link1 = "<a href=\"/zeprs/" + url1 + "\">Additional Lab Tests</a>";
            links.put("labs", link1);
            request.setAttribute("links", links);

            request.setAttribute("encounterId", safeMotherhoodEnc.getId().toString());
            forward = new ActionForward("/form92/correct.do");
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
