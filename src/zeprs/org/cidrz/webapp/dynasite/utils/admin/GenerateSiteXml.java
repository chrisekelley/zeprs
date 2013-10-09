/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.utils.admin;

import com.sun.syndication.io.FeedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.ArchiveDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Publisher;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import javax.servlet.ServletException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jan 2, 2009
 *         Time: 5:29:22 PM
 */
public class GenerateSiteXml implements StatefulJob  {

    static Log log = LogFactory.getLog(GenerateSiteXml.class);

    public void execute(JobExecutionContext context)
            throws JobExecutionException {

    	// Every job has it's own job detail
        JobDetail jobDetail = context.getJobDetail();
        Boolean export2sites = (Boolean) jobDetail.getJobDataMap().get("export2sites");

        // The name is defined in the job definition
        String jobName = jobDetail.getName();

        // Log the time the job started
        // GenerateRssFile.logger.info(jobName + " fired at " + new Date());
        Connection conn = null;
        String siteAbbrev = null;
        Long sitePublishId = null;
        // What site are we publishing?
        String file = Constants.ARCHIVE_PATH + "publisher.xml";
        Publisher publisher = null;
        ResultSet rs;
        try {
            publisher = (Publisher) XmlUtils.getOne(file);
            sitePublishId = publisher.getSiteId();
            // log.debug("sitePublishId: " + sitePublishId );
            Site site = (Site) DynaSiteObjects.getClinicMap().get(sitePublishId);
            siteAbbrev = site.getAbbreviation();
        } catch (FileNotFoundException e) {
            // it's ok - file not created yet.
        } catch (IOException e) {
            log.error(e);
		}

        Date generateStatusDate = null;
        Map statusMap = DynaSiteObjects.getStatusMap();
        if (statusMap.get("RSS-Gen-date")!= null) {
            generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
        }

        Date now = new Date(System.currentTimeMillis());
        if (publisher != null) {
            if (generateStatusDate != null) {
                String message = "RSS Generate is being used by another process started at " + generateStatusDate +
                ". Please try again later";
                log.debug(message);
            } else {
                statusMap.put("RSS-Gen-date", now);
                statusMap.put("RSS-message", "Generate Site Xml");
            }
        try {
        	// using the super special root connection for this one mate!
            conn = DatabaseUtils.getSpecialRootConnection("zeprsadmin");
            ArchiveDAO.deleteAll(conn); // resets the archivelog to enable exporting all records.
            try {
                //int archivedPatients = Rss.createRssFile(rs, sitePublishId, conn, siteAbbrev, false);
                String message = Rss.createSiteRssFiles(conn, siteAbbrev, sitePublishId, false);
                //String message = "Number of Archived Patients: " + archivedPatients;
                log.debug(message);
            } catch (Exception e) {
                log.error(e);
                e.printStackTrace();
            }
            // Log the time the job ended
            // GenerateRssFile.logger.info(jobName + " completed at " + new Date());
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
            if (generateStatusDate != null && generateStatusDate == now) {
                statusMap.remove("RSS-Gen-date");
                statusMap.remove("RSS-message");
            }
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        }
    }
}
