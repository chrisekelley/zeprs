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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.dao.SiteDAO;
import org.cidrz.webapp.dynasite.dao.SubscriptionDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.valueobject.Subscription;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.sun.syndication.feed.rss.Channel;

import javax.servlet.ServletException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Ap 19, 2006
 *         Time: 12:23:41 PM
 */
public class ImportFeed implements StatefulJob  {

    static Log log = LogFactory.getLog(ImportFeed.class);

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        // Every job has it's own job detail
        JobDetail jobDetail = context.getJobDetail();

        // The name is defined in the job definition
        String jobName = jobDetail.getName();

        // Log the time the job started
        // GenerateRssFile.log.info(jobName + " fired at " + new Date());
        Connection conn = null;
        execute(conn);
    }

    public void execute(Connection conn) {

        Date generateStatusDate = null;
        Map statusMap = DynaSiteObjects.getStatusMap();
        if (statusMap.get("RSS-Gen-date")!= null) {
            generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
        }
        Date now = new Date(System.currentTimeMillis());
        try {
            if (conn == null) {
                conn = DatabaseUtils.getZEPRSConnection("zeprsadmin");
            }
            // Log the time the job started
            // GenerateRssFile.log.info(jobName + " fired at " + new Date());
            Long siteId = null;
            Boolean view = false;
            StringBuffer comments = new StringBuffer();
            Long start = null;
            List feeds = null;
            Long reload = null;

            if (generateStatusDate == null) {
                statusMap.put("RSS-Gen-date", now);
                try {
                    feeds = SubscriptionDAO.getAll(conn);
                    for (int i = 0; i < feeds.size(); i++) {
                        Subscription subscription = (Subscription) feeds.get(i);
                        String urlString = subscription.getUrl();
                        String message = null;
                        String patientId = null;
                        log.info("Processing subscription at " + urlString);
                        statusMap.put("RSS-message", "Importing Feed from " + urlString);
                        try {
                            Date startTime = new Date();
                            Date endTime = new Date();
                            long difference = (endTime.getTime() - startTime.getTime());

                            String siteAbbrev = subscription.getSite();
        	    			if (siteAbbrev == null) {
        						// import it manually
        						Channel channel = Rss.downloadRssFile(urlString);
        						siteAbbrev = channel.getTitle();
        						SubscriptionDAO.updateSite(conn, subscription.getId(), siteAbbrev, null);
        	    			}
        	    			Site site;
        					try {
        						site = (Site) SiteDAO.getOne(conn, siteAbbrev);
        		    			siteId = site.getId();
                                message = Rss.importFeed(conn, urlString, view, comments, start, siteId, reload, patientId);
        					} catch (ObjectNotFoundException e) {
        						log.debug("Site not found for " + siteAbbrev);
        					}
                            String durationMessage = "Processed subscription of " + urlString + " in " + difference / 1000 + " seconds.";
                            log.info(durationMessage);
                        } catch (ConnectException e) {
                            log.debug("Unable to connect to " + urlString + " Error: " + e);
                        } catch (NoRouteToHostException e) {
                            log.debug("Unable to connect to " + urlString + " Error: " + e);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
                if (generateStatusDate != null && generateStatusDate == now) {
                    statusMap.remove("RSS-Gen-date");
                    statusMap.remove("RSS-message");
                }
            } else {
                log.info("Another process is currently importing an RSS feed. Try again later.");
            }
            // Log the time the job ended
            // GenerateRssFile.log.info(jobName + " completed at " + new Date());
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            // Just in case, let's clean things up
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

        //

    }
}
