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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

/**
 * This is triggered by a Quartz automated process to generate RSS file that lists patient records.
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Nov 28, 2005
 *         Time: 12:23:41 PM
 */
public class GenerateRssFile implements StatefulJob  {

    static Log log = LogFactory.getLog(GenerateRssFile.class);

    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        Connection conn = null;
        Date generateStatusDate = null;
        Map statusMap = DynaSiteObjects.getStatusMap();
        if (statusMap.get("RSS-Gen-date")!= null) {
            generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
        }

        Date now = new Date(System.currentTimeMillis());
        try {
            conn = DatabaseUtils.getZEPRSConnection("zeprsadmin");
            // Every job has it's own job detail
            JobDetail jobDetail = context.getJobDetail();

            // The name is defined in the job definition
            String jobName = jobDetail.getName();

            // Log the time the job started
            // GenerateRssFile.log.info(jobName + " fired at " + new Date());
            Date startTime = new Date();
            Long siteId = null;
            String file = Constants.ARCHIVE_PATH + "publisher.xml";
            Publisher publisher = null;
            try {
                publisher = (Publisher) XmlUtils.getOne(file);
            } catch (FileNotFoundException e) {
            	log.debug(e);
            	e.printStackTrace();
            } catch (IOException e) {
            	log.debug(e);
            	e.printStackTrace();
            }

            if (publisher != null) {
                if (generateStatusDate == null) {
                    statusMap.put("RSS-Gen-date", now);
                    Site site = (Site) DynaSiteObjects.getClinicMap().get(publisher.getSiteId());
                    siteId = site.getId();
                    String siteAbbrev = site.getAbbreviation();
                    try {
                        Boolean checkXml = null;
                        //int archivedPatients = Rss.createRssFile(patientListResultSet, siteId, conn, siteAbbrev, checkXml);
                        String output = Rss.createSiteRssFiles(conn, siteAbbrev, siteId, checkXml);
                        Date endTime = new Date();
                        long difference = (endTime.getTime() - startTime.getTime());
                        //String message = "Archived Modified Patient Records: <br/>" + output + "<br/> in " + difference / 1000 + " seconds.";
                        String message = "Archived Modified Patient Records in " + difference / 1000 + " seconds.";
                        log.info(message);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ObjectNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (PersistenceException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (FeedException e) {
                        e.printStackTrace();
                    }
                    generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
                    if (generateStatusDate != null && generateStatusDate == now) {
                        statusMap.remove("RSS-Gen-date");
                    }
                } else {
                    log.info("Busy w/ another process. Try again later.");
                }
            } else {
                log.info("Publisher not yet configured; unable to generate RSS file.");
            }
            // Log the time the job ended
            // GenerateRssFile.log.info(jobName + " completed at " + new Date());
        } catch (ServletException e) {
        	log.debug(e);
        	e.printStackTrace();
        } finally {
            generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
            if (generateStatusDate != null && generateStatusDate == now) {
                statusMap.remove("RSS-Gen-date");
            }
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
            	log.debug(e);
            }
        }
    }
}
