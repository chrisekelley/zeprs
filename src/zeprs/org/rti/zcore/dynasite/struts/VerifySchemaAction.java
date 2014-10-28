/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.rti.zcore.dynasite.struts;

import java.security.Principal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.Constants;
import org.rti.zcore.dynasite.utils.DynasiteUtils;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.struts.ParameterActionForward;

/**
 * Logs and implements updates to the database.
 * kudos: http://www.tonyspencer.com/mt/archives/2005/01/execute_mysql_s.htm
 * User: Chris Kelley
 * Date: Jun 20, 2006
 * Time: 3:45:34 PM
 */
public class VerifySchemaAction extends BaseAction {
	/**
	 * Commons Logging instance.
	 */
	public static Log log = LogFactory.getFactory().getInstance(VerifySchemaAction.class);

	protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Principal userPrincipal = request.getUserPrincipal();
		String username = userPrincipal.getName();
		Result result = null;
		int resultUpdate = 0;
		String message = null;
		String execute = request.getParameter("execute");
//		Connection conn = null;
		Connection connAdmin = null;
		Connection connApp = null;
		String path = Constants.DYNASITE_XML_PATH;
		ArrayList<String> missingCols = null;
        Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
        String localeString = locale.toString();
		try {
//			conn = DatabaseUtils.getZEPRSConnection(username);
			connAdmin = DatabaseUtils.getAdminConnection();
			connApp = DatabaseUtils.getSpecialRootConnection(username);
			missingCols = DynasiteUtils.verifySchema(connAdmin, connApp, username, execute, localeString);
			if (execute != null) {
				message = "Schema database Updated.";
				ParameterActionForward fwd = new ParameterActionForward(mapping.findForward("admin/message"));
				fwd.addParameter("messageList", missingCols);
				fwd.addParameter("message", message);
				return fwd;
			}
		} catch (Exception e) {
			request.setAttribute("exception", e);
			return mapping.findForward("error");
		} finally {
			if (connApp != null && !connApp.isClosed()) {
				connApp.close();
			}
			if (connAdmin != null && !connAdmin.isClosed()) {
				connAdmin.close();
			}
		}
		request.setAttribute("messageList", missingCols);
		request.setAttribute("message", message);
		request.setAttribute("nextLink", "admin/verifySchema.do");
		request.setAttribute("nextLinkParam", "execute");
		request.setAttribute("nextLinkParamValue", "1");
		request.setAttribute("nextLinkText", "Execute SQL statements");
		return mapping.findForward(SUCCESS_FORWARD);
	}

}
