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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.dao.PatientStatusDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Site;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: May 16, 2005
 * Time: 4:55:49 PM
 */
public final class DeletePatientAction extends BaseAction {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(DeletePatientAction.class);

    /**
     * Deletes either a single patient or a whole passel of 'em.
     * Include patientId in the request to delete a single patient.
     * Include siteId and date info in the request to delete all patients in a site
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected ActionForward doExecute(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
        String siteAbbrev = site.getAbbreviation();
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        Connection conn = null;

        Long patientId = null;
        Date beginDate = null;
        Date endDate = null;
        Long siteId = null;
        ResultSet rs;
        if (request.getParameter("patientId") != null) {
            patientId = Long.valueOf(request.getParameter("patientId"));
        }
        if (request.getParameter("bdate") != null && !request.getParameter("bdate").equals("")) {
            beginDate = Date.valueOf(request.getParameter("bdate"));
        }
        if (request.getParameter("edate") != null && !request.getParameter("edate").equals("")) {
            endDate = Date.valueOf(request.getParameter("edate"));
        }
        if (request.getParameter("siteId") != null) {
            siteId = Long.valueOf(request.getParameter("siteId"));
        }

        String filePath = org.cidrz.webapp.dynasite.Constants.PATIENTS_DELETED_PATH;

        try {
            // using the super special root connection for this one mate!
            conn = DatabaseUtils.getSpecialRootConnection(username);
            if (patientId != null) {
                try {
                    boolean isLogged = true;
                    StringBuffer sbuf = PatientRecordUtils.archiveDeletePatient(conn, patientId, siteAbbrev, isLogged, filePath);
                    request.setAttribute("message", sbuf.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("exception", "SQL error");
                    return mapping.findForward("error");
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (ObjectNotFoundException e) {
                    // hmmm
                }
            } else if (siteId != null) {
                try {
                    int numPatients = 0;
                    rs = PatientStatusDAO.getAllDateSite(conn, beginDate, endDate, siteId);
                    String message = "";
                    StringBuffer sbufLog = new StringBuffer();
                    while (rs.next()) {
                        patientId = rs.getLong("id");
                        boolean isLogged = true;
                        site = (Site) DynaSiteObjects.getClinicMap().get(siteId);
                        StringBuffer sbuf = PatientRecordUtils.archiveDeletePatient(conn, patientId, site.getAbbreviation(), isLogged, filePath);
                        sbufLog.append(sbuf.toString());
                        sbufLog.append("<br/>");
                    }
                    message = sbufLog.toString();
                    if (message.equals("")) {
                    	message = "No patient records deleted.";
                    }
                    request.setAttribute("message", message);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                } finally {
                    //

                }
            } else {
                request.setAttribute("exception", "Bad URL.");
                return mapping.findForward("error");
            }

        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return mapping.findForward("success");
    }

}
