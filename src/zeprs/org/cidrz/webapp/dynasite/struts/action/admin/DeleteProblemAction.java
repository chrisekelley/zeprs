/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action.admin;

import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.utils.struts.ParameterActionForward;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: April 3, 2006
 * Time: 9:51 AM
 */
public final class DeleteProblemAction extends BaseAction {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(DeleteProblemAction.class);

    protected ActionForward doExecute(ActionMapping mapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        Connection conn = null;

        Long id = null;
        if (request.getParameter("id") != null) {
            id = Long.valueOf(request.getParameter("id"));
        }

        try {
            conn = DatabaseUtils.getSpecialRootConnection("zeprsAdmin");
            if (id != null) {
                try {
                    Long patientId = PatientRecordUtils.deleteProblem(conn, id, username, site);
                    ParameterActionForward forward = new ParameterActionForward(mapping.findForward("problem"));
                    forward.addParameter("patientId", patientId);
                    return forward;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ObjectNotFoundException e) {
                    e.printStackTrace();
                    return mapping.findForward("error");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("exception", e);
            return mapping.findForward("error");
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return mapping.findForward("success");
    }

}
