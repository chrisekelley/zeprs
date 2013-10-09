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
import org.cidrz.project.zeprs.valueobject.gen.ArvRegimen;
import org.cidrz.project.zeprs.valueobject.gen.NewbornEval;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.SessionPatientDAO;
import org.cidrz.webapp.dynasite.logic.EncounterProcessor;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 14, 2005
 *         Time: 11:14:28 AM
 */
public class ForwardAction extends BasePatientAction {

    /**
     * Commons Logging instance.
     */

    private static Log log = LogFactory.getFactory().getInstance(ForwardAction.class);


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

        Principal userPrincipal = request.getUserPrincipal();
        String username = userPrincipal.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            HttpSession session = request.getSession();
            DynaValidatorForm dynaForm = (DynaValidatorForm) form;
            ActionForward forward = null;
            Long patientId = null;
            Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
            Long siteId = site.getId();
            SessionPatient sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
            int formId = 0;
            try {
                formId = Integer.parseInt(request.getParameter("formId").trim());
            } catch (NumberFormatException e) {
                log.error(e);
            }

            //resolve the patientId - it has been either pushed via the request or gathered from the sessionPatient
            if (formId != 1) {
                patientId = SessionPatientDAO.getPatientId(conn, request);
            }

            
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return mapping.findForward("home");
    }

}
