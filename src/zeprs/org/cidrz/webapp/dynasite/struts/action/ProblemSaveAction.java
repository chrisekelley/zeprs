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
import org.cidrz.webapp.dynasite.dao.SessionPatientDAO;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.struts.StrutsUtils;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;

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
public class ProblemSaveAction extends BasePatientAction {

    /**
     * Commons Logging instance.
     */

    private static Log log = LogFactory.getFactory().getInstance(ProblemSaveAction.class);


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

        Principal user = request.getUserPrincipal();
        String username = user.getName();
        Connection conn = null;
        Long patientId = null;
        SessionPatient sessionPatient = null;
        HttpSession session = request.getSession();
        sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
        //try {
            //conn = DatabaseUtils.getZEPRSConnection(username);
            int formId = 0;
            try {
                formId = Integer.parseInt(mapping.getParameter().trim());
                log.debug("Form id: " + formId);
            } catch (NumberFormatException e) {
                log.error(e);
            }

            //resolve the patientId - it has been either push via the request or gathered from the sessionPatient
            if (formId != 1) {
                //patientId = SessionPatientDAO.getPatientId(conn, request);
            	patientId = sessionPatient.getId();
            }
            // Reset form
            form.reset(mapping, request);
            StrutsUtils.removeFormBean(mapping, request);
      /*  } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }*/
        /**
         * Forwards section - send user to the next form
         */

        return mapping.findForward("success");
    }
}