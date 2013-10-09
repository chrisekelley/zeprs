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

import java.io.File;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;
import org.cidrz.project.zeprs.valueobject.report.PatientStatusReport;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.PatientStatusDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.utils.admin.Rss;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Publisher;
import org.cidrz.webapp.dynasite.valueobject.Site;


/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: March 16, 2006
 * Time: 5:30:49 PM
 */
public final class ExportPatientAction extends DownloadAction {

	private static final DateFormat DATE_PARSER = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getFactory().getInstance(ExportPatientAction.class);

	protected StreamInfo getStreamInfo(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {

		HttpSession session = request.getSession();
		Connection conn = null;
		String siteAbbrev = null;
		Principal user = request.getUserPrincipal();
		String zeprsId= null;
		String uuid= null;
		File file = null;
		if (request.getAttribute("zeprsId") != null) {
			zeprsId = (String) request.getAttribute("zeprsId");
		}
		if (request.getParameter("zeprsId") != null) {
			zeprsId = (String) request.getParameter("zeprsId");
		}
		if (request.getAttribute("uuid") != null) {
			uuid = (String) request.getAttribute("uuid");
		}
		if (request.getParameter("uuid") != null) {
			uuid = (String) request.getParameter("uuid");
		}

		Publisher publisher = null;
		String publisherFile = Constants.ARCHIVE_PATH + "publisher.xml";
		String ipAddress = null;
		try {
			publisher = (Publisher) XmlUtils.getOne(publisherFile);
			ipAddress = publisher.getUrl();
		} catch (FileNotFoundException e) {
			log.debug("publisher.xml not available - cannot render site rss files.");
		}

		String port = Constants.APP_PORT;

		try {
			if (conn == null) {
				conn = DatabaseUtils.getZEPRSConnection("zeprsadmin");
			}
			try {
				Patient patient = null;
				if (zeprsId == null || zeprsId.equals("null")) {
					patient = PatientDAO.getOne(conn, uuid);
					zeprsId = patient.getDistrictPatientid();
				} else {
					patient = PatientDAO.getOneFromZeprsId(conn, zeprsId);
				}
				PatientStatusReport psr = PatientStatusDAO.getOne(conn, patient.getId());
				String surname = patient.getSurname();
				Long patientId = patient.getId();
				Long siteId = psr.getSiteId();
				Site site = (Site) DynaSiteObjects.getClinicMap().get(siteId);
				siteAbbrev = site.getAbbreviation();
				//String filePath = DynaSiteObjects.getMasterArchiveIndex().get(zeprsId);
				String filePath = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + Constants.pathSep + "local" + Constants.pathSep;
				String fileName = Rss.getPatientFilename(zeprsId, surname, patientId);
				//String url = "http://" + ipAddress + ":" + port + "/archive/" + siteAbbrev + "/local/" + fileName;
				file = new File(filePath + fileName);
				//file = new File(filePath);
				if (! file.exists()) {
					log.debug("Manually rendering patient record for " + zeprsId);
					PatientRecordUtils.archivePatient(conn, patientId, siteAbbrev, filePath, fileName);
					/*
					// try another location
					siteId = patient.getSiteId();
					site = (Site) DynaSiteObjects.getClinicMap().get(siteId);
					siteAbbrev = site.getAbbreviation();
					filePath = org.cidrz.webapp.dynasite.Constants.ARCHIVE_PATH + siteAbbrev + Constants.pathSep + "local" + Constants.pathSep;
					fileName = Rss.getPatientFilename(zeprsId, surname, patientId);*/
					file = new File(filePath + fileName);
				}
			} catch (ObjectNotFoundException e) {
				String message = "Unable to locate patient for zeprsId:" + zeprsId + " and uuid: " + uuid;
				log.debug(message);
				request.setAttribute("message", message);
				String contentType = "text/txt";
				String filePath = org.cidrz.webapp.dynasite.Constants.ERROR404_PATH;
				//String url = "http://" + ipAddress + ":" + port + "/archive/" + siteAbbrev + "/local/" + fileName;
				file = new File(filePath);
				StreamInfo fsi = null;;
				try {
					fsi = new FileStreamInfo(contentType, file);
				} catch (Exception e1) {
					log.debug(e1);
				}
				request.setAttribute("exception", e);
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return fsi;
			}

		} catch (ServletException e) {
			e.printStackTrace();
		} finally {
			// Just in case, let's clean things up
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        String outputType = Constants.PATIENT_RECORD_OUTPUT;
		String contentType = "text/" + outputType;
		StreamInfo fsi = null;;
			try {
				fsi = new FileStreamInfo(contentType, file);
			} catch (Exception e) {
				log.debug(e);
			}

		return fsi;

	}
}