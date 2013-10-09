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
import org.cidrz.webapp.dynasite.dao.EncounterValueArchiveDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.valueobject.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Provides listing of changes to values in an encounter.
 */
public class EncounterArchiveListAction extends BaseAction {
	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getFactory().getInstance(EncounterArchiveListAction.class);

	protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Principal userPrincipal = request.getUserPrincipal();
		String username = userPrincipal.getName();
		Date beginDate = null;
		Date endDate = null;
		String task = null;
		String zeprsId = null;

	    // month
		java.util.Calendar c = java.util.Calendar.getInstance();
	    c.add(java.util.Calendar.MONTH, -1);
	    java.util.Date date1monthpast = c.getTime();
	    String DATE_FORMAT = "yyyy-MM-dd";
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
	    sdf.setTimeZone(TimeZone.getDefault());
	    String date1monthpastStr = sdf.format(date1monthpast);
	    java.sql.Date date1monthpastSql =  java.sql.Date.valueOf(date1monthpastStr);
	    request.setAttribute("date1monthpast", date1monthpast);
	    request.setAttribute("date1monthpastSql", date1monthpastSql);
	    java.sql.Date dateNow = DateUtils.getNow();
	    request.setAttribute("dateNow", dateNow);
	    // week
	    java.util.Calendar c2 = java.util.Calendar.getInstance();
	    c2.add(java.util.Calendar.WEEK_OF_YEAR, -1);
	    java.util.Date date1weekpast = c2.getTime();
	    java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat(DATE_FORMAT);
	    sdf2.setTimeZone(TimeZone.getDefault());
	    String date1weekpastStr = sdf2.format(date1weekpast);
	    java.sql.Date date1weekpastSql =  java.sql.Date.valueOf(date1weekpastStr);
	    request.setAttribute("date1weekpastSql", date1weekpastSql);
	    // quarter
	    java.util.Calendar c3 = java.util.Calendar.getInstance();
	    c3.add(java.util.Calendar.MONTH, -3);
	    java.util.Date quarterPast = c3.getTime();
	    java.text.SimpleDateFormat sdf3 = new java.text.SimpleDateFormat(DATE_FORMAT);
	    sdf2.setTimeZone(TimeZone.getDefault());
	    String quarterPastStr = sdf3.format(quarterPast);
	    java.sql.Date quarterPastSql =  java.sql.Date.valueOf(quarterPastStr);
	    request.setAttribute("quarterPastSql", quarterPastSql);

	    //String systemState = String.valueOf(SystemStateManager.getCurrentState());
	    //request.setAttribute("systemState",systemState);

		if (request.getParameter("bdate") != null) {
			beginDate = Date.valueOf(String.valueOf(request.getParameter("bdate")));
			request.setAttribute("date1weekpastSql", beginDate);
		} else {
			beginDate = date1weekpastSql;
		}
		if (request.getParameter("edate") != null) {
			endDate = Date.valueOf(String.valueOf(request.getParameter("edate")));
			request.setAttribute("endDate", endDate);
		} else {
			endDate = dateNow;
		}
		if (request.getParameter("task") != null) {
			task = String.valueOf(request.getParameter("task"));
		}
		if (request.getParameter("zeprsId") != null) {
			zeprsId = String.valueOf(request.getParameter("zeprsId"));
		}

			Connection conn = null;
			try {
				conn = DatabaseUtils.getZEPRSConnection(username);
				List resolvedItems = new ArrayList();
				List items = null;
				if (zeprsId != null) {
					items = EncounterValueArchiveDAO.getAll(conn, zeprsId);
				} else {
					items = EncounterValueArchiveDAO.getAll(conn, beginDate, endDate);
				}

				for (int i = 0; i < items.size(); i++) {
					EncounterValueArchive encounterValueArchive = (EncounterValueArchive) items.get(i);
					Long pageItemId = encounterValueArchive.getPageItemId();
					PageItem pageItem = (PageItem) DynaSiteObjects.getPageItems().get(pageItemId);
					Long value = null;
					Long previousValue = null;
					if (pageItem != null) {
						FormField formField = null;
						try {
							formField = pageItem.getForm_field();
						} catch (Exception e) {
							log.error(e);
						}

						try {
							value = Long.decode(encounterValueArchive.getValue());
							previousValue = Long.decode(encounterValueArchive.getPreviousValue());
							if (formField.getType().equals("Enum")) {
								try {
									FieldEnumeration fieldEnum = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get(value);
									encounterValueArchive.setValue(fieldEnum.getEnumeration());
									FieldEnumeration fieldEnum2 = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get(previousValue);
									encounterValueArchive.setPreviousValue(fieldEnum2.getEnumeration());
								} catch (Exception e) {
									// it's ok
								}
							}
						} catch (NumberFormatException e) {
							// it's not an enum, or is something else...
						}
					} else {
						// value = encounterValueArchive.getValue();
						// previousValue = encounterValueArchive.getPreviousValue();
					}
					resolvedItems.add(encounterValueArchive);
				}

				request.setAttribute("items", resolvedItems);
			} catch (ServletException e) {
				log.error(e);
			} finally {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			}

		return mapping.findForward("success");
	}
}
