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
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.dao.ClientSettingsDAO;
import org.cidrz.webapp.dynasite.dao.StaffDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * Implementation of <strong>Action</strong> that validates a registration form.
 *
 * @author ckelley
 */
public final class SetupFormAction extends Action {

    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Principal userPrincipal = request.getUserPrincipal();
        String username = null;
        try {
            username = userPrincipal.getName();
        } catch (Exception e) {
            log.error(e);
            request.setAttribute("exception - error in getUserPrincipal().getName", e);
            return actionMapping.findForward("error");
        }
        String site_id = request.getParameter("site_id");
        // district id is preset in ZEPRS; it is configurable in PTS.
        String district_id = "1";
        if (site_id == null || site_id.equals("")) {
            return actionMapping.findForward("input");
        }
        Cookie cookie = new Cookie("site_id",site_id);
        cookie.setMaxAge(60*60*24*365*2); // 2 years
        response.addCookie(cookie);
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            ClientSettings settings = null;
            try {
                settings = SessionUtil.getInstance(request.getSession()).getClientSettings();
            } catch (SessionUtil.AttributeNotFoundException e) {
                try {
                    settings = (ClientSettings) ClientSettingsDAO.getOne(conn, settings.getIpAddress());
                } catch (ObjectNotFoundException e1) {
                    settings = new ClientSettings();
                } catch (NullPointerException e1) {
                    settings = new ClientSettings();
                }
            }
            Site site = (Site) DynaSiteObjects.getClinicMap().get(Long.valueOf(site_id));
            settings.setSite(site);
            settings.setSiteId(site.getId());
            /*District district = (District) DynaSiteObjects.getDistrictsMap().get(Long.valueOf(district_id));
            settings.setDistrict(district);*/
            settings.setDistrictId(Long.valueOf(district_id));
            settings.setIpAddress(request.getRemoteAddr());
            /*try {
                ClientSettingsDAO.delete(conn, settings, request.getUserPrincipal(), site);
            } catch (SQLException e) {
                log.error(e);
            } catch (ServletException e) {
                log.error(e);
            }
            ClientSettingsDAO.save(conn, settings, request.getUserPrincipal(), site);*/
            SessionUtil.getInstance(request.getSession()).setClientSettings(settings);
            Staff staff = null;
            try {
                staff = (Staff) StaffDAO.getOne(conn, username);
                SessionUtil.getInstance(session).setFirstname(staff.getFirstName());
                SessionUtil.getInstance(session).setLastname(staff.getLastName());
                SessionUtil.getInstance(session).setFullname(staff.getLastName() + ", " + staff.getFirstName());
            } catch (ObjectNotFoundException e) {
                log.error(e);
                if (username.equals("")) {
                    request.setAttribute("exception", "Please login with another username - this one is invalid.");
                } else {
                    request.setAttribute("exception", e);
                }
                return actionMapping.findForward("error");
            }

        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return actionMapping.findForward("home");
    }
}