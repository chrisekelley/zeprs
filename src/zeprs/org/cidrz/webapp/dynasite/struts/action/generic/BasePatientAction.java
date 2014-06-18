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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.dao.ClientSettingsDAO;
import org.cidrz.webapp.dynasite.dao.SessionPatientDAO;
import org.cidrz.webapp.dynasite.dao.StaffDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Provides basic variables and methods for Actions relating to patients
 */
public class BasePatientAction extends Action {
    public static final String SUBJECT_KEY = "subject";
    public static final String ID_KEY = "id";
    public static final String SUCCESS_FORWARD = "success";
    public static final String LOCKED_FORWARD = "locked";
    public static final String SETUP_FORWARD = "setup";
    public static final String NOTLOCKED_FORWARD = "notlocked";
    //public static Long patientId;
    //public static Long pregnancyId;
    //public static Long flowId;
    //public static SessionPatient sessionPatient;

    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(BasePatientAction.class);

    public final ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Long patientId = null;
        Long pregnancyId = null;
        Long flowId = null;
        SessionPatient sessionPatient = null;
        HttpSession session = request.getSession();
        Connection conn = null;
        if (!SessionUtil.getInstance(request.getSession()).isClientConfigured()) {
            try {
                Principal userPrincipal = request.getUserPrincipal();
                String username = null;
                try {
                    username = userPrincipal.getName();
                } catch (Exception e) {
                    log.error(e);
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
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
                    	if (conn != null && !conn.isClosed()) {
                            conn.close();
                            conn = null;
                        }
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
                	if (conn != null && !conn.isClosed()) {
                        conn.close();
                        conn = null;
                    }
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
                    SessionUtil.getInstance(session).setFullname(staff.getLastName() + ", " + staff.getFirstName());
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (ObjectNotFoundException e) {
                    // username is not in userdata.address
                    request.setAttribute("message", "Please login with another username - this one is invalid.");
                    return mapping.findForward("logout");
                } finally {
                	if (conn != null && !conn.isClosed()) {
                        conn.close();
                        conn = null;
                    }
                }
            } catch (ServletException e) {
                log.error(e);
            } finally {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            }
        }

        // prevent re-submit of form when reloading partograph.
        saveToken(request);
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            try {
            	// if pregnancyId is in the request, we may be switching to view another previous pregnancy.
                if (request.getParameter("pregnancyId") != null) {
                    SessionUtil.getInstance(session).setSessionPatient(null);
                }
                patientId = SessionPatientDAO.getPatientId(conn, request);
                sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
                flowId = sessionPatient.getCurrentFlowId();
                pregnancyId = sessionPatient.getCurrentPregnancyId();
                patientId = sessionPatient.getId();

                // see if we need to clear out sessionPatient
                // some forms affect data in session patient - may need to reload it to get fresh data
                if (mapping.getParameter() != null && !mapping.getParameter().equals("")) {
                    String formId = mapping.getParameter();
                    if (formId.equals("1") & (request.getParameter("patientId") == null)) {
                        // clear out sessionPatient
                        SessionUtil.getInstance(session).setSessionPatient(null);
                        sessionPatient = null;
                        flowId = null;
                        pregnancyId = null;
                        patientId = null;
                    }
                }
                if (mapping.getPath().equals("/patientPuer")) {
                    // load the correct patient
                    if (request.getAttribute("patientId") != null) {
                        patientId = Long.decode(String.valueOf(request.getAttribute("patientId")));
                        SessionPatientDAO.updateSessionPatient(conn, patientId, pregnancyId, session);
                        // re-initialize the globals
                        sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
                        flowId = sessionPatient.getCurrentFlowId();
                        pregnancyId = sessionPatient.getCurrentPregnancyId();
                    }
                }
            } catch (SessionUtil.AttributeNotFoundException e) {
                // need to initialize the sessionPatient
                // must have patientID from the request
                // pass 0 if pregnancyId not selected - this will display current pregnancy
                if (request.getParameter("patientId") != null) {
                    patientId = Long.decode(request.getParameter("patientId"));
                }
                if (request.getParameter("pregnancyId") != null) {
                    pregnancyId = Long.decode(request.getParameter("pregnancyId"));
                } else {
                    pregnancyId = Long.decode("0");
                }
                if (mapping.getParameter() != null && !mapping.getParameter().equals("")) {
                    String formId = mapping.getParameter();
                    if (formId.equals("1") & (request.getParameter("patientId") == null)) {
                        // clear out sessionPatient
                        SessionUtil.getInstance(session).setSessionPatient(null);
                        sessionPatient = null;
                        flowId = null;
                        pregnancyId = null;
                        patientId = null;
                    }
                }
                if (patientId != null) {
                    try {
                        SessionPatientDAO.updateSessionPatient(conn, patientId, pregnancyId, session);
                    } catch (SessionUtil.AttributeNotFoundException e1) {
                        String errorMessage = "<p>An error has occurred. The system was unable to retrieve the requested patient. " +
                                "Please press the \"Back\" button and try another link.</p>" +
                                "<p>This error has been logged by the system.</p>";
                        String logMessage = "Unable to initialize/set sessionpatient : patientId: " + patientId + ", pregnancyId: " + pregnancyId + "\n" +
                                "Error: " + e;
                        log.error(logMessage);
                        if (sessionPatient == null) {
                           log.error("sessionPatient is empty");
                        } else {
                            flowId = sessionPatient.getCurrentFlowId();
                            log.error("current flow: " + flowId);
                        }
                        request.setAttribute("exception", errorMessage);
                        return mapping.findForward("error");
                    } catch (ObjectNotFoundException e1) {
                        String errorMessage = "<p>An error has occurred. The system was unable to retrieve the requested patient. " +
                                "Please press the \"Back\" button and try another link.</p>" +
                                "<p>This error has been logged by the system.</p>";
                        String logMessage = errorMessage +
                                "\n * Code is from BasePatientAction." +
                                "\n * Debug: patientId: " + patientId + ", pregnancyId: " + pregnancyId + "Error: " + e;
                        log.error(logMessage);
                        request.setAttribute("exception", errorMessage);
                        return mapping.findForward("error");
                    } finally {
                    	if (conn != null && !conn.isClosed()) {
                            conn.close();
                            conn = null;
                        }
                    }
                    // re-initialise some fields
                    sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
                    flowId = sessionPatient.getCurrentFlowId();
                    pregnancyId = sessionPatient.getCurrentPregnancyId();
                    patientId = sessionPatient.getId();
                }
            } catch (ObjectNotFoundException e) {
                request.setAttribute("exception", "There was an error accessing the patient record - it may no longer be in the database. Please return to the Home page and search again.");
                return mapping.findForward("error");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } finally {
            	if (conn != null && !conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
            }
            request.setAttribute("patientId", patientId);
            request.setAttribute("sessionPatient", sessionPatient);

            if (request.getAttribute("encounterId") != null) {
                request.setAttribute("encounterId", request.getAttribute("encounterId"));
            }

            if (mapping.getParameter() != null && !mapping.getParameter().equals("")) {
                String formId = mapping.getParameter();
                if (!formId.equals("1") && !formId.equals("125") && sessionPatient == null) {
                    return mapping.findForward("home");
                }
            }
            // The formNames map is used to resolve the correct URL for a form,
            // such as prev. pregnnancies, which uses a custom url
            HashMap formNames = DynaSiteObjects.getFormNames();
            request.setAttribute("formNames", formNames);
        } catch (ServletException e) {
            log.error("Error while trying to get sessionPatient in BasePatientAction. Error: " + e);
        } finally {
        	if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
        }
        return doExecute(mapping, form, request, response);
    }

    protected ActionForward doExecute
            (ActionMapping
                    mapping, ActionForm
                    form, HttpServletRequest
                    request, HttpServletResponse
                    response) throws Exception {
        return mapping.findForward(SUCCESS_FORWARD);
    }
}
