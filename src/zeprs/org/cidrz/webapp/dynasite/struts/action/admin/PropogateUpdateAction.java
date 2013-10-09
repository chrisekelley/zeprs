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

import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.ApplicationUpdateDAO;
import org.cidrz.webapp.dynasite.dao.SubscriptionDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.PersistentObjectAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Subscription;

/**
 * Send updates to remote servers. This is current not in-use.
 * Created by IntelliJ IDEA.
 * User: Chris Kelleu
 * Date: Mar 20, 2006
 * Time: 3:45:34 PM
 */
public class PropogateUpdateAction extends PersistentObjectAction {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(PropogateUpdateAction.class);

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //todo complete this

        Principal userPrincipal = request.getUserPrincipal();
        String username = userPrincipal.getName();
        Connection conn = null;
        String message = null;
        StringBuffer sbuf = new StringBuffer();
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            List subscriptions = SubscriptionDAO.getAll(conn);
            for (int i = 0; i < subscriptions.size(); i++) {
                Subscription subscription = (Subscription) subscriptions.get(i);
                String sub = subscription.getUrl();
                String [] subArray = sub.split("/");
                String ipAddress = subArray[2];
                // get list of updates
                List localUpdates = ApplicationUpdateDAO.getAll(conn, "admin");
                // load the updated admin database
                String adminSql = Constants.DYNASITE_RESOURCES_PATH + "/admin_install.sql";
                //todo - open mysql connection on remote site
                Connection remoteConn = null;
                try {
                    remoteConn = DatabaseUtils.getRemoteConnection("mysql", ipAddress, "admin", "zepadmin", "zepadmin11");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (remoteConn != null && !remoteConn.isClosed()) {
                        remoteConn.close();
                    }
                }
                if (remoteConn != null) {
                    sbuf.append(subArray[2]).append(" Updated<br/>");
                }


            }
            message = sbuf.toString();
            request.setAttribute("message", message);
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return mapping.findForward(SUCCESS_FORWARD);
    }

}
