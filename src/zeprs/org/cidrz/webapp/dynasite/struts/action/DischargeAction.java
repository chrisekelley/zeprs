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
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Connection;
import java.util.List;

/**
 * Creates list of referrals for UTH discharge
 *
 * @author Chris Kelley
 */
public final class DischargeAction extends BasePatientAction {

    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    /**
     * Create a list of problems, from problems and systme-generated outcomes.
     * If problemId is sent in request, the return the comments for that Id; otherwise, sned most recent comments.
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
        Principal userPrincipal = request.getUserPrincipal();
        String username = userPrincipal.getName();

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
            Boolean status = null;
            status = Boolean.valueOf(true);

            List referrals = null;
            try {
                referrals = OutcomeDAO.getReferrals(conn, patientId, pregnancyId);
            } catch (Exception e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            }

            request.setAttribute("referrals", referrals);
            request.setAttribute("patientId", patientId);
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