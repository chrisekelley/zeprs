/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action.generic;

import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.ClientSettingsDAO;
import org.cidrz.webapp.dynasite.dao.StaffDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.ClientSettings;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.valueobject.Staff;

/**
 * NOTE: This is not "abstract" because some mappings use this directly.
 */
public class BaseAction extends Action {
    public static final String SUBJECT_KEY = "subject";
    public static final String ID_KEY = "id";
    public static final String SUCCESS_FORWARD = "success";
    public static final String LOCKED_FORWARD = "locked";
    public static final String SETUP_FORWARD = "setup";
    public static final String NOTLOCKED_FORWARD = "notlocked";


    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    /**
     * Use this on non-patient pages - for reports and admin tasks
     * Use BasePatientAction for patient-related pages.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public final ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Connection conn = null;
        String fullName = "";
		String siteName = "";
		request.setAttribute("appName", Constants.APP_NAME);
        request.setAttribute("appTemplateDir", Constants.APP_TEMPLATEDIR);
        request.setAttribute("appTitle", Constants.APP_TITLE);
        if (!SessionUtil.getInstance(request.getSession()).isClientConfigured()) {
            try {
                Principal userPrincipal = request.getUserPrincipal();
                if (userPrincipal == null) {
                	// occasionally getting NPE on some requests for userPrincipal
                	return mapping.findForward("homelink");
                }
                String username = null;
                try {
                    username = userPrincipal.getName();
                } catch (Exception e) {
                    log.error(e);
                    request.setAttribute("exception", e);
                    return mapping.findForward("home");
                }
                conn = DatabaseUtils.getZEPRSConnection(username);
                ClientSettings settings = new ClientSettings();
                String ipAddress = request.getRemoteAddr();
                settings.setIpAddress(ipAddress);
                Cookie[] cookies = request.getCookies();
                Long siteId = null;
                String district_id = "1";
                settings.setDistrictId(Long.valueOf(district_id));

                if (cookies == null) {
                    // log.debug("No cookies");
                    try {
                        settings = (ClientSettings) ClientSettingsDAO.getOne(conn, ipAddress);
                        siteId = settings.getSiteId();
                        Cookie cookie = new Cookie("site_id",siteId.toString());
                        cookie.setMaxAge(60*60*24*365*2); // 2 years
                        response.addCookie(cookie);
                    } catch (SQLException e) {
                        log.error(e);
                    } catch (ServletException e) {
                        log.error(e);
                    } catch (ObjectNotFoundException e) {
                        return mapping.findForward("setup");
                    }
                } else {
                    Cookie cookie;
                    for(int i=0; i<cookies.length; i++) {
                        cookie = cookies[i];
                        //log.debug("Cookie name: " + cookie.getName() + " value: " + cookie.getValue());
                        if (cookie.getName().equals("site_id")) {
                            siteId = new Long(cookie.getValue());

                        }
                    }
                }

                if (siteId == null) {
                    return mapping.findForward("setup");
                } else {
                    settings.setSiteId(siteId);
                }
                Site site = (Site) DynaSiteObjects.getClinicMap().get(Long.valueOf(settings.getSiteId()));
                settings.setSite(site);
                SessionUtil.getInstance(request.getSession()).setClientSettings(settings);
                Staff staff = null;
                try {
                	staff = (Staff) StaffDAO.getOne(conn, username);
                	SessionUtil.getInstance(session).setFirstname(staff.getFirstName());
                	SessionUtil.getInstance(session).setLastname(staff.getLastName());
                	fullName = staff.getLastName() + ", " + staff.getFirstName();
                	SessionUtil.getInstance(session).setFullname(fullName);
                	siteName = SessionUtil.getInstance(session).getClientSettings().getSite().getName();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (ObjectNotFoundException e) {
                    // username is not in userdata.address
                    request.setAttribute("message", "Please login with another username - this one is invalid.");
                    return mapping.findForward("logout");
                }
            } catch (ServletException e) {
                log.error(e);
            } finally {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            }
        } else {
			fullName = SessionUtil.getInstance(session).getFullname();
			siteName = SessionUtil.getInstance(session).getClientSettings().getSite().getName();
        }
        if (request.getAttribute("message") != null)  {
            request.setAttribute("message", request.getAttribute("message"));
        }
        // Clear out session patient
        SessionUtil.getInstance(session).setSessionPatient(null);
        request.setAttribute("fullName", fullName);
		request.setAttribute("siteName", siteName);
        return doExecute(mapping, form, request, response);
    }

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(SUCCESS_FORWARD);
    }
}