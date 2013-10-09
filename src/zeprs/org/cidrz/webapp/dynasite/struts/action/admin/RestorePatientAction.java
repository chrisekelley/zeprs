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

import org.apache.struts.action.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.FileUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.admin.Rss;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.valueobject.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * Restores a deleted patient record.
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 8, 2006
 *         Time: 1:08:00 PM
 */
public class RestorePatientAction extends BaseAction {
	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getFactory().getInstance(RestorePatientAction.class);
	protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Connection conn = null;
		try {
			conn = DatabaseUtils.getZEPRSConnection("zepadmin");
			StringBuffer comments = new StringBuffer();
			DynaActionForm dynaForm = (DynaActionForm) form;
			String zeprsId = null;
			String patientFileName = null;
			Date pubDate = null;
			if (dynaForm != null) {
				if (dynaForm.get("zeprsId") != null) {
					zeprsId = (String) dynaForm.get("zeprsId");
				}
				if (dynaForm.get("patientFileName") != null) {
					patientFileName = (String) dynaForm.get("patientFileName");
				}
				/*if (dynaForm.get("pubDate") != null && !dynaForm.get("pubDate").equals("")) {
	            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            	pubDate = sdf.parse((String) dynaForm.get("pubDate"));
	            }*/
			}
			String filePath = org.cidrz.webapp.dynasite.Constants.PATIENTS_DELETED_PATH;
			String restorePath = org.cidrz.webapp.dynasite.Constants.PATIENTS_RESTORED_PATH;

			if (patientFileName != null && !patientFileName.equals("")) {
				Patient patient = (Patient) XmlUtils.getOne(filePath + patientFileName);
				request.setAttribute("patientFileName", patientFileName);
				Long lastModified = patient.getPatientStatusreport().getLastModified().getTime();
				pubDate = new Date(lastModified);
				// if the patient in the database already?
				Patient patientInDatabase = null;;
				try {
					patientInDatabase = PatientDAO.getOneFromZeprsId(conn, zeprsId);
					request.setAttribute("exception", "This patient - " + zeprsId + " - is already in the database; Delete the patient first if you are attempting to import the patient record from a file");
					return mapping.findForward("error");
				} catch (ObjectNotFoundException e) {
					// it's OK
				}
				// see if this patient is already in the filesystem
				try {
					Rss.importPatientRecord(null, patientFileName, pubDate, filePath, null, null, conn, comments, Long.valueOf(1), "ZEP", Boolean.FALSE);
					File src = new File(filePath, patientFileName);
					File tgt = new File(restorePath, patientFileName);
					try {
						FileUtils.renameTo(src, tgt);
					} catch (ObjectNotFoundException e) {
						// it's kinda ok.
					}
				} catch (Exception e) {
		            e.printStackTrace();
					request.setAttribute("exception", e);
					return mapping.findForward("error");
				}
				return mapping.findForward("home");
			} else {
				if (zeprsId != null && !zeprsId.equals("")) {
					String[] zeprsArray = zeprsId.split("-");
					String testId = null;
					try {
						String districtId = zeprsArray[0];
						String siteId = zeprsArray[1];
						String zepatientId = zeprsArray[2];
						testId = siteId + "-" + zepatientId;
					} catch (ArrayIndexOutOfBoundsException e) {
						request.setAttribute("message", "The Id you submitted does not have the format 5040-NNN-NNNNN-N");
						return mapping.findForward("success");
					}
					// if the patient in the database already?
					Patient patientInDatabase = null;;
					try {
						patientInDatabase = PatientDAO.getOneFromZeprsId(conn, zeprsId);
						request.setAttribute("exception", "This patient - " + zeprsId + " - is already in the database; Delete the patient first if you are attempting to import the patient record from a file");
						return mapping.findForward("error");
					} catch (ObjectNotFoundException e) {
						// it's OK
					}
					// find the patient xml file on the filesystem
					// get listing of files in temp dir
					File name = new File(filePath);
					String[] directory = name.list();
					List<String> files = new ArrayList<String>();
					TreeMap<String, String> fileMap = new TreeMap<String, String>();
					for (int i = 0; i < directory.length; i++) {
						String fileName = directory[i];
						File file = new File(filePath, fileName);
						String formattedDate = DateUtils.getFormattedDate(file.lastModified());
						fileMap.put(fileName, formattedDate);
						files.add(fileName);
					}
					Collections.sort(files);
					//loop through these files and find the patient
					String fileName = null;
					int i = 0;
					TreeMap<String, String> moreAccurateMap = new TreeMap<String, String>();
					Long dateLong = Long.valueOf(0);
					for (Iterator iterator = files.iterator(); iterator.hasNext();) {
						String filename = (String) iterator.next();
						if (filename.contains(testId)) {
							i++;
							File file = new File(filePath, filename);
							Long lastModified = file.lastModified();
							String formattedDate = DateUtils.getFormattedDate(lastModified);
							moreAccurateMap.put(filename, formattedDate);
							if (lastModified > dateLong) {
								dateLong = lastModified;
								patientFileName = filename;
							}
						}
					}
					if (!patientFileName.equals("")) {
						Patient patient = (Patient) XmlUtils.getOne(filePath + patientFileName, null);
						request.setAttribute("patientFileName", patientFileName);
						//Long lastModified = patient.getPatientStatusreport().getLastModified().getTime();
						//pubDate = new Date(lastModified);
						//request.setAttribute("pubDate", pubDate.toString());
						request.setAttribute("patient", patient);
						request.setAttribute("zeprsId", patient);
						if (i >1) {
							request.setAttribute("message", "The system found more than one patient that matched the input. " +
									"The system has selected the most recent file. <br/>" +
									"Press the 'Submit' button if it is the correct record or delete the duplicate record and try again. ");
							//request.setAttribute("fileMap", moreAccurateMap);
						}
					} else {
						request.setAttribute("fileMap", fileMap);
					}
				}
			}

		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return mapping.findForward("success");
	}

}
