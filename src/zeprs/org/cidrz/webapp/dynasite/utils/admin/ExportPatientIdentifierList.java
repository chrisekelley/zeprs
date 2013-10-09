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
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.DistrictId;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.PatientIdentifierDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.codehaus.jackson.map.ObjectMapper;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Feb. 11, 2009
 *         Time: 5:29:22 PM
 */
public class ExportPatientIdentifierList implements StatefulJob  {

    static Log log = LogFactory.getLog(ExportPatientIdentifierList.class);

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
        try {
        	conn = DatabaseUtils.getZEPRSConnection("zepadmin");
        	List<Site> clinics = DynaSiteObjects.getClinics();
        	for (Site site : clinics) {
        		String siteId = site.getSiteAlphaId();
        		String siteAbbrev = site.getAbbreviation();
        		List<DistrictId> patientIdList = PatientIdentifierDAO.getAllSite(conn, siteId);
        		String filepath = Constants.ARCHIVE_PATH + siteAbbrev + Constants.pathSep + Constants.PATIENT_ID_LIST_FILENAME;
        		//XmlUtils.saveJson(patientIdList, filepath);
        		/*Type listType = new TypeToken<List<DistrictId>>() {}.getType();
        		Gson gson = new Gson();
        		String jspnString = gson.toJson(patientIdList, listType);
        		FileOutputStream writer = new FileOutputStream(filepath);
        		writer.write(jspnString.getBytes());*/

        		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        		mapper.writeValue(new File(filepath), patientIdList);
        	}
        } catch (ServletException e) {
        	log.error(e);
        } catch (SQLException e) {
        	log.error(e);
        } catch (IOException e) {
        	log.error(e);
        } finally {
        	try {
        		if (conn != null && !conn.isClosed()) {
        			conn.close();
        		}
        	} catch (SQLException e) {
        		log.error(e);
        	}
        }
    }
}
