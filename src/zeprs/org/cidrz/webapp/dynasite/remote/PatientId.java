/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.remote;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.PatientIdentifierDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Dec 19, 2004
 * Time: 5:28:43 PM
 */
public class PatientId {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(PatientId.class);


    /**
     * Checks if patient id is used, saves id if it's a duplicate.
     *
     * @param patientId
     * @param siteId
     * @return string
     */

    public static String checkPatientId(String patientId, String siteId, String districtId, String assignedId) {
        Boolean status;
        WebContext exec = WebContextFactory.get();
        String userName = null;
        try {
            userName = exec.getHttpServletRequest().getUserPrincipal().getName();
        } catch (NullPointerException e) {
            // unit testing - it's ok...
            // log.error("Error getting username from exec.getHttpServletRequest().getUserPrincipal().getName()" + e);
            userName = "zeprsadmin";
        }
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(userName);
            if (patientId.equals("")) {
                return "Error: Empty field submitted. Please enter a Patient ID.";
            }
            // Check if it's in the district_patient_id field of the patient table.
            status = PatientDAO.checkPatientId(conn, patientId);
            if (status.equals(Boolean.TRUE)) {
            	PatientIdentifierDAO.save(conn, districtId, Long.valueOf(siteId), assignedId);
                return "Success! Patient ID is Okay. Proceed completing the rest of the form.";

            } else {
                // must have been an imported record and the patientid field was not reserved. Let's reserve it now.
            	PatientIdentifierDAO.save(conn, siteId, Long.valueOf(districtId), assignedId);
                log.error("Duplicate patient id attempted: " + patientId);
            }
        } catch (SQLException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
            log.error(e.getStackTrace());
            return "ServletException " + e.getMessage();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "Error: ID " + patientId + " is already in the system - please search for this patient, enter another Patient ID, or use the 'Get New ID' button to generate a new ID.";
    }

    /**
     * Checks if a patient id is in the district_patient_id field of the patient table. If it is, must have been an imported record
     * and the patientid field was not reserved. It reserves the id in the district_id table.
     * @param conn
     * @param patientId
     * @param siteId
     * @param districtId
     * @param assignedId
     * @return
     */
    public static String getPatientId(Connection conn, String patientId, String siteId, String districtId, String assignedId) {
        Boolean status;
        try {
            if (patientId.equals("")) {
                return "Error: Empty field submitted. Please enter a Patient ID.";
            }
            // Check if it's in the district_patient_id field of the patient table.
            status = PatientDAO.checkPatientId(conn, patientId);
            if (status.equals(Boolean.TRUE)) {
                return "Success! Patient ID is Okay. Proceed completing the rest of the form.";
            } else {
                // must have been an imported record and the patientid field was not reserved. Let's reserve it now.
            	PatientIdentifierDAO.save(conn, siteId, Long.valueOf(districtId), assignedId);
                // log.error("Duplicate patient id attempted: " + patientId);
            }
        } catch (SQLException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
            log.error(e.getStackTrace());
            return "ServletException " + e.getMessage();
        }
        return "Error: ID " + patientId + " is already in the system - please search for this patient or enter another Patient ID.";
    }

    /**
     * Returns a valid district_patient_id. Checks if a patient id is in the district_patient_id field of the patient table.
     * @param districtId
     * @param siteId
     * @return
     */
    public static String setPatientId(String districtId, String siteId) {
        String patientId = null;
        WebContext exec = WebContextFactory.get();
        String userName = null;
        try {
            userName = exec.getHttpServletRequest().getUserPrincipal().getName();
        } catch (NullPointerException e) {
            // unit testing - it's ok...
            // log.error("Error getting username from exec.getHttpServletRequest().getUserPrincipal().getName()" + e);
            userName = "zeprsadmin";
        }
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(userName);
            if (districtId.equals("")) {
                return "Error: Empty field submitted. Please enter a district ID.";
            } else if (siteId.equals("")) {
                return "Error: Empty field submitted. Please enter a site ID.";
            }
            patientId = CreateVerifiedPatientId(conn, districtId, siteId);
            conn.close();
        } catch (ServletException e) {
            log.error(e);
        } catch (Exception e) {
            patientId = e.getMessage();
            log.error(e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return patientId;
    }

    /**
     * Attempts to verify that the id chosen is available. If there are duplicates, it tries to call setPatientId again 19 times.
     * @param conn
     * @param districtId
     * @param siteId
     * @return
     * @throws Exception
     */
    public static String CreateVerifiedPatientId(Connection conn, String districtId, String siteId) throws Exception {
        String patientId = null;
        int retry = 0;
        try {
            patientId = PatientIdentifierDAO.getPatientId(conn, new Integer(districtId), siteId);
        } catch (SQLException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        }
        // create the id string
        String testPid = PatientRecordUtils.createZeprsId(districtId, siteId, patientId);
        // now test if it's available - if there are already duplicates
        String testId = getPatientId(conn, testPid, siteId, districtId, patientId);
        if (testId.startsWith("Error")) {
            testId = "";
            retry = retry + 1;
            // try again
            if (retry < 20) {
                patientId = setPatientId(districtId, siteId);
            } else {
                String error =  "Error: The application has made 20 attempts to assign an Id. ";
                throw new Exception(error);
                //return patientId;
            }
        } else {
            retry = 0;
            //return patientId;
        }
        return patientId;
    }
}
