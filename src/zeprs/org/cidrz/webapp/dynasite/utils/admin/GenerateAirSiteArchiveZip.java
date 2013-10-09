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

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.utils.FileUtils;
import org.cidrz.webapp.dynasite.utils.Zip;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Feb. 11, 2009
 *         Time: 5:29:22 PM
 */
public class GenerateAirSiteArchiveZip implements StatefulJob  {

    static Log log = LogFactory.getLog(GenerateAirSiteArchiveZip.class);

    /**
     * Archives a single site for testing.
     */
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

    	// Every job has it's own job detail
        JobDetail jobDetail = context.getJobDetail();
        Boolean export2sites = (Boolean) jobDetail.getJobDataMap().get("export2sites");

        // The name is defined in the job definition
        String jobName = jobDetail.getName();

        // Log the time the job started
        // GenerateRssFile.logger.info(jobName + " fired at " + new Date());

        Date generateStatusDate = null;
        Map statusMap = DynaSiteObjects.getStatusMap();
        if (statusMap.get("RSS-Gen-date")!= null) {
            generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
        }

        Date now = new Date(System.currentTimeMillis());
            if (generateStatusDate != null) {
                String message = "RSS Generate is being used by another process started at " + generateStatusDate +
                ". Please try again later";
                log.debug(message);
            } else {
                statusMap.put("RSS-Gen-date", now);
                statusMap.put("RSS-message", "Generate Site Xml");
            }
        try {
        	//Zip.zip(path, Constants.MASTER_ARCHIVE_ZIP, Constants.MASTER_ARCHIVE_CHECKSUM);
        	//Zip.CreateZipFile(new File(Constants.MASTER_ARCHIVE_ZIP), new File[]{new File(path)}, false, Constants.ARCHIVE_PATH);
        	File zipFile = new File(Constants.ARCHIVE_PATH + Constants.MASTER_ARCHIVE_AIR_ZIP_FILENAME);
        	if (zipFile.exists()) {
        		zipFile.delete();
        	}
        	String path = "AIR" + File.separator + "**";
        	log.debug("Creating archive of " + path);
            Zip.zip(Constants.ARCHIVE_PATH, Constants.MASTER_ARCHIVE_AIR_ZIP, path);
            // Make this zip file available for download in the /archive location.
            FileUtils.copyQuick(Constants.MASTER_ARCHIVE_AIR_ZIP, Constants.ARCHIVE_PATH + Constants.MASTER_ARCHIVE_AIR_ZIP_FILENAME);
        	log.debug("Completed archive creation of " + path);
        } catch (Exception e) {
            //e.printStackTrace();
            log.error(e);
        } finally {
            generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
            if (generateStatusDate != null && generateStatusDate == now) {
                statusMap.remove("RSS-Gen-date");
                statusMap.remove("RSS-message");
            }
        }
    }
}
