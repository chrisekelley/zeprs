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

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.cidrz.project.zeprs.valueobject.report.PatientStatusReport;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.PatientStatusDAO;
import org.cidrz.webapp.dynasite.dao.SessionPatientDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.utils.admin.Rss;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;

import com.thoughtworks.xstream.alias.CannotResolveClassException;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.io.StreamException;

/**
 * Imports patient record.
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 8, 2006
 *         Time: 1:08:00 PM
 */
public class ImportPatientAction extends BaseAction {
	/**
	 * Commons Logging instance.
	 */
	public static Log log = LogFactory.getFactory().getInstance(ImportPatientAction.class);
	protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
		Connection conn = null;
		String patientRecordFileName = null;
		Boolean isOnline = false;
		try {
			conn = DatabaseUtils.getZEPRSConnection("zepadmin");
			DynaActionForm dynaForm = (DynaActionForm) form;
			String zeprsId = null;
			if (dynaForm != null) {
				if (dynaForm.get("zeprsId") != null) {
					zeprsId = (String) dynaForm.get("zeprsId");
				}
			}
			if (zeprsId == null && request.getParameter("zeprsId") != null) {
				zeprsId = request.getParameter("zeprsId");
			}

			Patient patient = null;
			Timestamp masterPing = null;
			// check if the site is offline by seeing if the time of last contact was in the past hour.
	        Map statusMap = DynaSiteObjects.getStatusMap();
			if (statusMap.get("MasterPing")!= null) {
				masterPing = (Timestamp) statusMap.get("MasterPing");
		        isOnline = DateUtils.checkDifference(masterPing, 1);
	        }
            if (isOnline) {
    			// Download and de-serialize patient from master server
            	try {
    				patient = XmlUtils.downloadPatientRecord(zeprsId);
    			} catch (IOException e) {
    				log.error(e);
    			} catch (ConversionException e) {
    				log.error(e);
    			} catch (CannotResolveClassException e) {
    				log.error(e);
    				request.setAttribute("exception", "Unable to retrieve patient record. Error: " + e);
    				return mapping.findForward("error");
    			}
            }
			if (patient == null) {
				// Perhaps the server is off-line. Fetch patient from the cache.
				// search for this patient record from the HashMap in the sky
				String filePath = DynaSiteObjects.getMasterArchiveIndex().get(zeprsId);
				if (filePath != null) {
					String importedRecordFile = Constants.MASTER_ARCHIVE_PATH + filePath;
		            try {
						patient = (Patient) XmlUtils.getOne(importedRecordFile, null);
					} catch (IOException e) {
						String message = "Unable to access the patient record for " + zeprsId + ". Please enter another patient id.";
	    				log.debug(message);
	    				request.setAttribute("exception", message);
	    				return mapping.findForward("error");
					}
				} else {
					String message = "Unable to access the patient record for " + zeprsId + ". Please enter another patient id.";
    				log.debug(message);
    				request.setAttribute("exception", message);
    				return mapping.findForward("error");
				}
			}

	        StringBuffer comments = new StringBuffer();
			String siteAbbrev = SessionUtil.getInstance(session).getClientSettings().getSite().getAbbreviation();
    		java.util.Date pubDate = new Date(patient.getPatientStatusreport().getLastModified().getTime());
    		patientRecordFileName = Rss.getPatientFilename(zeprsId, patient.getSurname(), patient.getId());
            try {
                // import the patient record
                Rss.importPatient(null, patientRecordFileName, pubDate, conn, comments, Long.valueOf(1), siteAbbrev, false, patient);
            } catch (ObjectNotFoundException e) {
                log.error("Error processing file: " + Constants.PATIENTS_IMPORTED_PATH + patientRecordFileName + " Message: " + e);
                e.printStackTrace();
            } catch (SQLException e) {
            	throw new SQLException(e);
            } catch (StreamException e) {
            	log.error("Error fetching file: " + Constants.PATIENTS_IMPORTED_PATH + patientRecordFileName);
            } catch (Exception e) {    // field no longer used.
                log.error(e);
            }
			// load patient into the session. first get the new patient object
            Patient importedPatient = PatientDAO.getOne(conn, patient.getUuid());
            PatientStatusReport importedPsr = PatientStatusDAO.getOne(conn, importedPatient.getId());
            Long pregnancyId = importedPsr.getCurrentPregnancyId();
            if (pregnancyId == null) {
            	// This may be a patient who already has a completed pregnancy. Set pregnancyId = 0;
            	pregnancyId = new Long("0");
            }
            SessionPatientDAO.updateSessionPatient(conn, importedPatient.getId(), pregnancyId, session);
            SessionPatient sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
            request.setAttribute("patientId", importedPatient.getId());
            request.setAttribute("sessionPatient", sessionPatient);
			return mapping.findForward("patientHome");

		} catch (ServletException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return mapping.findForward("home");
	}

}
