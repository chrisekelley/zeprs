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
import org.apache.struts.validator.DynaValidatorForm;
import org.cidrz.webapp.dynasite.dao.UserDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Staff;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Apr 23, 2004
 * Time: 3:45:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserAdminAction extends BaseAction {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(UserAdminAction.class);

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Principal userPrincipal = request.getUserPrincipal();
        String username = userPrincipal.getName();

        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            Integer offset = 0;
            Integer rowCount = 15;
            if (request.getParameter("offset") != null) {
                offset = Integer.valueOf(request.getParameter("offset"));
            }
            if (request.getParameter("rowCount") != null) {
                rowCount = Integer.valueOf(request.getParameter("rowCount"));
            }
            DynaValidatorForm dynaForm = (DynaValidatorForm) form;
            List users = new ArrayList();
            if (!dynaForm.get("searchUsername").equals("")) {
                request.setAttribute("search", "1");
                String searchUsername = (String) dynaForm.get("searchUsername");
                try {
                    users = UserDAO.getSearchResults(conn, searchUsername);
                } catch (SQLException e) {
                    log.error(e);
                } catch (ServletException e) {
                    log.error(e);
                }
                if (users.size() == 0) {
                    request.setAttribute("error", "Username not found. Please try again.");
                }
            } else {
                users = UserDAO.getAll(conn, "lastname", offset, rowCount);
            }
            request.setAttribute("users", users);
            List groups = UserDAO.getAllGroups(conn);
            request.setAttribute("groups", groups);

            if (request.getParameter("group") != null) {
                try {
                    Long group = Long.valueOf(request.getParameter("group"));
                    String thisUsername = request.getParameter("username");
                    UserDAO.insertGroup(conn, thisUsername, group);
                } catch (NumberFormatException e) {
                    log.error(e);
                } catch (SQLException e) {
                    log.error(e);
                } catch (ServletException e) {
                    log.error(e);
                }

            }
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        // part of reload prevention scheme:
        resetToken(request);

        return mapping.findForward(SUCCESS_FORWARD);
    }

}
