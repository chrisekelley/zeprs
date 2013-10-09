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
import org.cidrz.webapp.dynasite.dao.PatientSearchDAO;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Publisher;
import org.cidrz.webapp.dynasite.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.sql.Result;

import java.net.UnknownHostException;
import java.security.Principal;
import java.sql.Connection;
import java.util.List;
import java.io.FileNotFoundException;


/**
 * Sets up the home page after user logs in.
 */

public final class HomeAction extends BaseAction {

    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    /**
     * Build the ZEPRS home page, incorporating the search interface/results
     * if it's a report-only user, send to reports
     * otherwise, send to permissions page.
     *
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

        HttpSession session = request.getSession();
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            if (request.isUserInRole("VIEW_INDIVIDUAL_PATIENT_RECORDS") || request.isUserInRole("CREATE_NEW_PATIENTS_AND_SEARCH"))
            {
                String searchStringRequest = request.getParameter("search_string");
                String firstSurname = request.getParameter("first_surname");  // used in a-z search
                String labour = request.getParameter("labour");  // used in a-z search
                String searchType = "keyword";
                String searchString = "";
                if (searchStringRequest == null) {
                	searchString = "";
                } else {
                	searchString = searchStringRequest.trim().toLowerCase();
                }
                if (firstSurname != null && !firstSurname.equals("")) {
                    searchType = "firstSurname";
                    searchString = firstSurname;
                    request.setAttribute("firstSurname", firstSurname);
                }
                request.setAttribute("searchString", searchString);
                String patientSiteId = SessionUtil.getInstance(session).getClientSettings().getSiteId().toString();
                request.setAttribute("patientSiteId", patientSiteId);

                String site = request.getParameter("site");
                request.setAttribute("site", site);
                if (site != null) {
                    if (site.equals("")) {
                        site = patientSiteId;
                    }
                }

                Integer offset = 0;
                Integer rowCount = 12;
                Integer nextRows = 12;
                if (request.getParameter("offset") != null) {
                    offset = Integer.valueOf(request.getParameter("offset"));
                }
                if (request.getParameter("rowCount") != null) {
                    rowCount = Integer.valueOf(request.getParameter("rowCount"));
                }
                if (request.getParameter("next") != null) {
                    nextRows = Integer.valueOf(request.getParameter("next"));
                }
                if (site == null) {
                    site = patientSiteId;
                }
                Result results = null;
                if (request.getParameter("labour") != null && !request.getParameter("labour").equals("")) {
                    request.setAttribute("labour", 1);
                    results = PatientSearchDAO.getResults(conn, site, searchString, offset, rowCount, searchType, 1, username);
                } else {
                    results = PatientSearchDAO.getResults(conn, site, searchString, offset, rowCount, searchType, 0, username);
                }

                if (results != null && results.getRowCount() == 0) {
                	if (searchString.startsWith("5040-")) {
                    	if (! site.equals("all")) {
                    		// check if this id is in other sites on this pc
                            results = PatientSearchDAO.getResults(conn, "all", searchString, offset, rowCount, searchType, 0, username);
                            if (results.getRowCount() == 0) {
                                //request.setAttribute("enablePatientImport", "enablePatientImport");
                            }
                    	} else {
                            //request.setAttribute("enablePatientImport", "enablePatientImport");
                    	}
                    }
                }

                request.setAttribute("results", results);
                int qryRowCount = results.getRowCount();
                // boolean rowsLimited = results.isLimitedByMaxRows();
                if (qryRowCount < rowCount.intValue()) {
                    nextRows = 0;
                } else {
                    // request.setAttribute("next", rowCount);
                    nextRows = rowCount;
                }
                request.setAttribute("next", nextRows);
                SessionUtil.getInstance(session).setSessionPatient(null);

                List sites = null;
                sites = DynaSiteObjects.getClinics();//
                request.setAttribute("sites", sites);

                if (SessionUtil.getInstance(request.getSession()).isClientConfigured()) {
                    String sitename = SessionUtil.getInstance(session).getClientSettings().getSite().getName();
                    request.setAttribute("sitename", sitename);
                } else {
                    request.setAttribute("sitename", "Configure PC: ");
                }

                // String hostname = request.getServerName();
                String hostname = "192.168.20.6";
                request.setAttribute("hostname", hostname);
                String fullname = null;
                try {
                    fullname = SessionUtil.getInstance(session).getFullname();
                } catch (SessionUtil.AttributeNotFoundException e) {
                    // ok
                }
                request.setAttribute("fullname", fullname);

                /**
                 * Think hard about re-enabling the following bit - it hits the site on every Home page access.
                 * Put into DynaSiteObjects during init serverlet startup.
                 */

                /*String ipAddress = null;
                java.net.InetAddress i;
				try {
					i = java.net.InetAddress.getLocalHost();
					ipAddress = i.getHostAddress();
				} catch (UnknownHostException e1) {
					log.debug("Cannot resolve hostname. Setting ipAddress to 127.0.0.1");
					ipAddress = "127.0.0.1";
				}

                request.setAttribute("ipAddress", ipAddress);

                String file = Constants.ARCHIVE_PATH + "publisher.xml";
                Publisher publisher = null;
                try {
                    publisher = (Publisher) XmlUtils.getOne(file);
                } catch (FileNotFoundException e) {
                    // it's ok - file not created yet.
                }
                request.setAttribute("publisher", publisher);*/

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
                return mapping.findForward("success");
            } else if (request.isUserInRole("VIEW_SELECTED_REPORTS_AND_VIEW_STATISTICAL_SUMMARIES")) {
            	if (conn != null && !conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
                return mapping.findForward("reports");
            } else if (request.isUserInRole("CREATE_MEDICAL_STAFF_IDS_AND_PASSWORDS_FOR_MEDICAL_STAFF")) {
            	if (conn != null && !conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
                return mapping.findForward("admin/users");
            }
        } catch (ServletException e) {
            log.error(e);
            request.setAttribute("exception", "There is an error generating the Search Results for the Home page. Please stand by - the system may be undergoing maintenance.");
            return mapping.findForward("error");
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }

        }

        return mapping.findForward("noPermissions");
    }


}
