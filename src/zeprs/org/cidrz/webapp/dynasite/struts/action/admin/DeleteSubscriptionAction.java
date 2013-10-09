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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.dao.SubscriptionDAO;
import org.cidrz.webapp.dynasite.dao.UpdateLogDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Subscription;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: April 3, 2006
 * Time: 9:51 AM
 */
public final class DeleteSubscriptionAction extends BaseAction {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(DeleteSubscriptionAction.class);

    protected ActionForward doExecute(ActionMapping mapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        /*Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
        Principal user = request.getUserPrincipal();
        String username = user.getName();*/
        Connection conn = null;

        Long id = null;
        if (request.getParameter("id") != null) {
            id = Long.valueOf(request.getParameter("id"));
        }


        try {
            conn = DatabaseUtils.getSpecialRootConnection("zeprsAdmin");
            if (id != null) {
                try {
                	Subscription subscription = (Subscription) SubscriptionDAO.getOne(conn, id);
                	String siteAbbrev = subscription.getSite();
                    SubscriptionDAO.delete(conn, id);
                    UpdateLogDAO.delete(conn, siteAbbrev);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            List subscriptions = SubscriptionDAO.getAll(conn);
            request.setAttribute("subscriptions", subscriptions);
            List logs = UpdateLogDAO.getAll(conn);
            request.setAttribute("logs", logs);
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
