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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.ArchiveDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.utils.admin.Rss;
import org.cidrz.webapp.dynasite.valueobject.Publisher;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: March 16, 2006
 * Time: 5:30:49 PM
 */
public final class ExportClinicAction extends Action {

    private static final DateFormat DATE_PARSER = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(ExportClinicAction.class);

    public final ActionForward execute(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {


        HttpSession session = request.getSession();
        Boolean checkXml = null;
        if (request.getParameter("checkXml") != null) {
             checkXml = true;
        }
        Connection conn = null;
        String siteAbbrev = null;
        Long sitePublishId = null;

        if (request.getParameter("siteId") != null) {
        	sitePublishId = Long.valueOf(request.getParameter("siteId"));
        	Site site = (Site) DynaSiteObjects.getClinicMap().get(sitePublishId);
        	siteAbbrev = site.getAbbreviation();
        } else {
            Publisher publisher = null;
            try {
            	// What site are we publishing?
                String file = Constants.ARCHIVE_PATH + "publisher.xml";
                publisher = (Publisher) XmlUtils.getOne(file);
                sitePublishId = publisher.getSiteId();
                // log.debug("sitePublishId: " + sitePublishId );
                Site site = (Site) DynaSiteObjects.getClinicMap().get(sitePublishId);
                siteAbbrev = site.getAbbreviation();
            } catch (FileNotFoundException e) {
                // it's ok - file not created yet.
            }
        }

        Date generateStatusDate = null;
        Map statusMap = DynaSiteObjects.getStatusMap();
        if (statusMap.get("RSS-Gen-date")!= null) {
            generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
        }
        Date now = new Date(System.currentTimeMillis());

        if (siteAbbrev != null) {
            if (generateStatusDate != null) {
                request.setAttribute("message", "RSS Generate is being used by another process started at " + generateStatusDate +
                        ". Please try again later");
                return mapping.findForward("error");
            } else {
                statusMap.put("RSS-Gen-date", now);
            }
            try {
                // using the super special root connection for this one mate!
                conn = DatabaseUtils.getSpecialRootConnection("zepadmin");
                if (request.getParameter("all") != null) {
                    ArchiveDAO.deleteAll(conn); // resets the archivelog to enable exporting all records.
                }
                try {
                    statusMap.put("RSS-message", "Generating Site Xml");
                    String message = Rss.createSiteRssFiles(conn, siteAbbrev, sitePublishId, checkXml);
                    //String message = "Number of Archived Patients: " + archivedPatients;
                    request.setAttribute("message", message);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                }

            } finally {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            }
            generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
            if (generateStatusDate != null && generateStatusDate == now) {
                statusMap.remove("RSS-Gen-date");
                statusMap.remove("RSS-message");
            }
        }
        return mapping.findForward("success");
    }

}