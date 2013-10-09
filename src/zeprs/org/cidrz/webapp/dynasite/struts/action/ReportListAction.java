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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class ReportListAction extends BaseAction {
    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        /*List reports = new ArrayList();
        File name = new File(Constants.REPORTS_XML_PATH);
        String[] directory = name.list();
        List files = new ArrayList();
        for (int i = 0; i < directory.length; i++) {
            String fileName = directory[i];
            File reportFile = new File(Constants.REPORTS_XML_PATH, fileName);
            files.add(reportFile);
        }
        Collections.sort(files);
        for (int j = 0; j < files.size(); j++) {
            File reportFile = (File) files.get(j);
            ReportItem report = new ReportItem();
            report.setFileName(reportFile.getName());
            report.setLastModified(new Date(reportFile.lastModified()));
            report.setLength(reportFile.length());
            reports.add(report);
        }*/
       /* for (int i = 0; i < directory.length; i++) {
            String fileName = directory[i];
            File reportFile = new File(Constants.REPORTS_XML_PATH, fileName);
            ReportItem report = new ReportItem();
            report.setFileName(reportFile.getName());
            report.setLastModified(new Date(reportFile.lastModified()));
            report.setLength(reportFile.length());
            reports.add(report);
        }*/

       // request.setAttribute("reports", reports);

        String patientSiteId = SessionUtil.getInstance(session).getClientSettings().getSiteId().toString();
        request.setAttribute("patientSiteId", patientSiteId);

        String site = request.getParameter("site");
        request.setAttribute("site", site);
        if (site != null) {
            if (site.equals("")) {
                site = patientSiteId;
            }
        }

        List sites = null;
        sites = DynaSiteObjects.getClinics();
        request.setAttribute("sites", sites);

        request.setAttribute("view", "yes");
        return mapping.findForward("success");
    }

}
