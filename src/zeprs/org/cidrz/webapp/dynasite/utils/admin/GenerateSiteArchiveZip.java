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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.FileUtils;
import org.cidrz.webapp.dynasite.utils.MD5Utils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * Generates an archive of all changed file for the previous day.
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Feb. 11, 2009
 *         Time: 5:29:22 PM
 */
public class GenerateSiteArchiveZip implements StatefulJob  {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(GenerateSiteArchiveZip.class);
    static final int BUFFER = 2048;

	    public void execute(JobExecutionContext context)
	            throws JobExecutionException {

	    	// Every job has it's own job detail
	        JobDetail jobDetail = context.getJobDetail();

	        // The name is defined in the job definition
	        String jobName = jobDetail.getName();

	        // Log the time the job started
	        // GenerateRssFile.logger.info(jobName + " fired at " + new Date());

	        Date generateStatusDate = null;
	        Map statusMap = DynaSiteObjects.getStatusMap();
	        if (statusMap.get("RSS-Gen-date")!= null) {
	            generateStatusDate = (Date) statusMap.get("RSS-Gen-date");
	        }
	        Connection conn = null;
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
	        	BufferedInputStream origin = null;
	        	// Create zip in /archive first.
	    		FileOutputStream dest = new FileOutputStream(Constants.MASTER_ARCHIVE_ZIP_PATH);
	    		//CheckedOutputStream checksum = new CheckedOutputStream(dest, new Adler32());
	    		//ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(checksum));
	    		ZipOutputStream out = new ZipOutputStream(dest);

	        	conn = DatabaseUtils.getZEPRSConnection("zeprsadmin");
	        	/*File zipFile = new File(Constants.ARCHIVE_PATH + Constants.MASTER_ARCHIVE_ZIP_FILENAME);
	        	if (zipFile.exists()) {
	        		zipFile.delete();
	        	}*/
	        	ResultSet updatedPatients = null;
	        	updatedPatients = PatientDAO.getAllYesterday(conn);
	        	List updatedPatientList = new ArrayList();
	        	while (updatedPatients.next()) {
    				Long patientId = updatedPatients.getLong("id");
	    			String zeprsId = updatedPatients.getString("district_patient_id");
    				String surname = updatedPatients.getString("surname");
    				Long clinicId = updatedPatients.getLong("site_id");   // clinic the patient belongs to
    				Site site = null;
    				String siteAbbrev = null;
    				// get the siteAbbrev from the parent's record only
    				try {
    					site = (Site) DynaSiteObjects.getClinicMap().get(clinicId);
    					siteAbbrev = site.getAbbreviation();
    				} catch (Exception e) {
    					log.error("Incorrect site data for patient: " + zeprsId + " clinicId: " + clinicId);
    				}
    				//String filePath = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + Constants.pathSep + "local" + Constants.pathSep;
    				String recordFileName = Rss.getPatientFilename(zeprsId, surname, patientId);
    				String pathToFile = siteAbbrev + Constants.pathSep + "local" + Constants.pathSep + recordFileName;
    				if (!updatedPatientList.contains(pathToFile)) {
    					updatedPatientList.add(updatedPatientList);
    					// zip file
        	    		byte data[] = new byte[BUFFER];
        				File f = new File(Constants.ARCHIVE_PATH + pathToFile);
            			if ( !f.isDirectory() ) {
            				log.debug("Adding: "+pathToFile);
                			FileInputStream fi = new FileInputStream(Constants.ARCHIVE_PATH + pathToFile);
                			origin = new BufferedInputStream(fi, BUFFER);
                			ZipEntry entry = new ZipEntry(pathToFile);
                			entry.setTime(f.lastModified());
                			out.putNextEntry(entry);
                			int count;
                			while((count = origin.read(data, 0, BUFFER)) != -1) {
                				out.write(data, 0, count);
                			}
                			origin.close();
                			fi.close();
            			}
    				}
	        	}
	        	out.close();
	    		dest.close();
	    		byte[] buffer = FileUtils.readFile(new File(Constants.MASTER_ARCHIVE_ZIP_PATH));
	            String md5sum = DigestUtils.md5Hex(buffer);
	    		//String md5String = MD5Utils.writeMD5(new File());
	    		//log.debug("checksum: " + checksum.getChecksum().getValue());
	    		log.debug("checksum: " + md5sum);
	    		Writer writer = new FileWriter(Constants.MASTER_ARCHIVE_CHECKSUM_PATH);
	    		//writer.write(String.valueOf(checksum.getChecksum().getValue()));
	    		writer.write(md5sum);
				writer.close();

	        	//Zip.CreateZipFile(new File(Constants.MASTER_ARCHIVE_ZIP), new File[]{new File(Constants.ARCHIVE_PATH)}, false, Constants.ARCHIVE_PATH);
	        	// Make this zip file available for download in the /archive location.
	        	//FileUtils.copyQuick(Constants.ARCHIVE_PATH + Constants.MASTER_ARCHIVE_ZIP_FILENAME, Constants.MASTER_ARCHIVE_ZIP_PATH);
	        	log.debug("Completed archive creation of " + Constants.MASTER_ARCHIVE_ZIP_PATH);
	        } catch (Exception e) {
	        	//e.printStackTrace();
	        	log.error(e);
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
