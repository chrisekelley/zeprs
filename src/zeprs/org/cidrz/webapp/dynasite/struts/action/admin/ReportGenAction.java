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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.project.zeprs.report.DbOutput;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.sql.Connection;

public class ReportGenAction extends BaseAction {
    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Principal userPrincipal = request.getUserPrincipal();
        String username = userPrincipal.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            if (request.getParameter("patients") != null) {
                DbOutput.generatePatientList(conn);
                return mapping.findForward("reports");
            }
            if (request.getParameter("outcomes") != null) {
                DbOutput.generateOutcomesList(conn);
                return mapping.findForward("reports");
            }
            if (request.getParameter("encounter") != null) {
                DbOutput.generateEncounterDataList(conn);
                return mapping.findForward("reports");
            }

            if (request.getParameter("all") != null) {
                DbOutput.generatePatientList(conn);
                DbOutput.generateOutcomesList(conn);
                XmlUtils.generateReportXML(conn);
                return mapping.findForward("reports");
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return mapping.findForward("success");
    }
}
