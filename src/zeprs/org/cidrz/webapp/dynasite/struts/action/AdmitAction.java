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
import org.apache.struts.validator.DynaValidatorForm;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.dao.PatientStatusDAO;
import org.cidrz.webapp.dynasite.dao.PatientRegistrationDAO;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.struts.StrutsUtils;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;
import org.cidrz.webapp.dynasite.valueobject.Site;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Connection;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 14, 2005
 *         Time: 11:14:28 AM
 */
public class AdmitAction extends BasePatientAction {

    /**
     * Commons Logging instance.
     */

    private static Log log = LogFactory.getFactory().getInstance(AdmitAction.class);


    /**
     * Create.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Extract attributes we will need
        HttpSession session = request.getSession();
        Principal userPrincipal = request.getUserPrincipal();
        String username = userPrincipal.getName();

        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            SessionPatient sessionPatient = null;

            DynaValidatorForm dynaForm = (DynaValidatorForm) form;
            Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
            Long siteId = site.getId();
            sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
            Long patientId = sessionPatient.getId();
            Long referralId = (Long) dynaForm.get("referralId");
            String condition = (String) dynaForm.get("condition");
            Integer ward = (Integer) dynaForm.get("ward");
            String firm = (String) dynaForm.get("firm");

            EncounterData vo = null;
            // log.debug("Saving record - patientId: " + patientId + ", siteId: " + siteId + ", username: " + username);
            try {
                // start the transactrion
                String sql = "START TRANSACTION;";
                DatabaseUtils.create(conn, sql);
                OutcomeDAO.admit(conn, referralId, condition, username, siteId, ward, firm);
                PatientStatusDAO.updatePatientStatusFirm(conn, username, siteId, patientId, firm);
                PatientRegistrationDAO.updateFirm(conn, patientId, firm);
                sql = "COMMIT";
            DatabaseUtils.create(conn, sql);
            } catch (Exception e) {
                String sql = "ROLLBACK";
                DatabaseUtils.create(conn, sql);
                log.error(e);
                if (sessionPatient == null) {
                    log.error("Error saving form = null sessionPatient");
                }
            }

            // Reset form
            form.reset(mapping, request);
            StrutsUtils.removeFormBean(mapping, request);

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