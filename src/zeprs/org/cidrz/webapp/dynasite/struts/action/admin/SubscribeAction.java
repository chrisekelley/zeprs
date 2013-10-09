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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.cidrz.webapp.dynasite.dao.SubscriptionDAO;
import org.cidrz.webapp.dynasite.dao.UpdateLogDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.PersistentObjectAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.FileUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Apr 23, 2004
 * Time: 3:45:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class SubscribeAction extends PersistentObjectAction {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(SubscribeAction.class);

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Class clazz = getMappedClass(mapping);
        DynaActionForm dynaForm = (DynaActionForm) form;
        Identifiable subject = null;
        Long id;
        Principal userPrincipal = request.getUserPrincipal();
        String username = userPrincipal.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            if (dynaForm != null) {
                FileUtils.createArchive();  // refresh the filesystem in case there were changes.
                String url = (String) dynaForm.get("url");
                SubscriptionDAO.save(conn, url);
            }
            List subscriptions = SubscriptionDAO.getAll(conn);
            request.setAttribute("subscriptions", subscriptions);
            List logs = UpdateLogDAO.getAll(conn);
            request.setAttribute("logs", logs);
        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        Map statusMap = DynaSiteObjects.getStatusMap();
        if (statusMap.get("RSS-Gen-date")!= null) {
            Date generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
            request.setAttribute("generateStatusDate", generateStatusDate.toString());
        }
        if (statusMap.get("RSS-message")!= null) {
            String statusMessage = (String) statusMap.get("RSS-message");
            request.setAttribute("statusMessage", statusMessage);
        }
		String DATE_FORMAT = "yyyy-MM-dd";
		java.util.Calendar c4 = java.util.Calendar.getInstance();
		c4.add(java.util.Calendar.DATE, -1);
		java.util.Date date1daypast = c4.getTime();
		java.text.SimpleDateFormat sdf4 = new java.text.SimpleDateFormat(DATE_FORMAT);
		sdf4.setTimeZone(TimeZone.getDefault());
		String date1daypastStr = sdf4.format(date1daypast);
		java.sql.Date date1daypastSql =  java.sql.Date.valueOf(date1daypastStr);
		//Timestamp date1daypast = new Timestamp(c4.getTime().getTime());
		//java.sql.Date dateNow = DateUtils.getNow();

		java.util.Calendar cal2 = java.util.Calendar.getInstance();
		cal2.add(java.util.Calendar.DATE, 1);
		java.util.Date date1DayFuture = cal2.getTime();
		java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat(DATE_FORMAT);
		sdf2.setTimeZone(TimeZone.getDefault());
		String date1DayFutureStr = sdf2.format(date1DayFuture);
		java.sql.Date date1DayFutureSql =  java.sql.Date.valueOf(date1DayFutureStr);

		request.setAttribute("date1daypast", date1daypastSql);
		request.setAttribute("date1DayFuture", date1DayFutureSql);
        return mapping.findForward(SUCCESS_FORWARD);
    }

}
