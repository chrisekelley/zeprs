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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.dao.ApplicationUpdateDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.PersistentObjectAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * List updates scheduled for remote servers.
 * Created by IntelliJ IDEA.
 * User: Chris Kelleu
 * Date: Mar 20, 2006
 * Time: 3:45:34 PM
 */
public class ApplicationUpdateAction extends PersistentObjectAction {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(ApplicationUpdateAction.class);

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = DatabaseUtils.getAdminConnection();
            if (request.getParameter("delete") != null) {
            Long id= Long.valueOf(request.getParameter("id"));
                try {
                    ApplicationUpdateDAO.delete(conn, id);
                } catch (SQLException e) {
                    request.setAttribute("exception", e);
                    mapping.findForward("error");
                }
            }

            List appUpdates = ApplicationUpdateDAO.getAll(conn);
            request.setAttribute("appUpdates", appUpdates);


        } catch (ServletException e) {
            ApplicationUpdateAction.log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return mapping.findForward(SUCCESS_FORWARD);
    }

}
