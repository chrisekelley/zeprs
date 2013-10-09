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
import org.cidrz.webapp.dynasite.dao.ReferralDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.sql.Result;
import java.security.Principal;
import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: May 26, 2005
 * Time: 1:18:43 PM
 */
public final class ReferralListAction extends BaseAction {
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

        Principal user = request.getUserPrincipal();
        String username = user.getName();

        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);

            // Extract attributes we will need
            String sortOrder = "o.last_modified";

            int offset = 0;
            int rowCount = 20;
            int nextRows = 20;
            if (request.getParameter("offset") != null) {
                offset = Integer.valueOf(request.getParameter("offset"));
            }
            if (request.getParameter("rowCount") != null) {
                rowCount = Integer.valueOf(request.getParameter("rowCount"));
            }
            if (request.getParameter("next") != null) {
                nextRows = Integer.valueOf(request.getParameter("next"));
            }

            Result results = null;
            try {
                results = ReferralDAO.getList(conn, sortOrder, offset, rowCount);
            } catch (ServletException e) {
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            request.setAttribute("results", results);

            int qryRowCount = results.getRowCount();
            // boolean rowsLimited = results.isLimitedByMaxRows();
            if (qryRowCount < rowCount) {
                nextRows = 0;
            } else {
                // request.setAttribute("next", rowCount);
                nextRows = rowCount;
            }
            request.setAttribute("next", nextRows);
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (!conn.isClosed()) {
                conn.close();
                conn = null;
            }
        }
        return mapping.findForward("success");
    }

}
