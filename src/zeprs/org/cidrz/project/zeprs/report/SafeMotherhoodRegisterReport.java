/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * Created on Mar 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SafeMotherhoodRegisterReport extends ZEPRSRegister {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(SafeMotherhoodRegisterReport.class);

    ArrayList patients = new ArrayList();
    String reportMonth;
    String reportYear;
    String type;

    /*SafeMotherhoodRegisterReport() {
        reportMonth = null;
        reportYear = null;
        // patients = new ArrayList();
    }*/

    /**
     * @return Returns the reportMonth.
     */
    public String getReportMonth() {
        return reportMonth;
    }

    /**
     * @param reportMonth The reportMonth to set.
     */
    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
    }

    /**
     * @return Returns the reportYear.
     */
    public String getReportYear() {
        return reportYear;
    }

    /**
     * @param reportYear The reportYear to set.
     */
    public void setReportYear(String reportYear) {
        this.reportYear = reportYear;
    }

    /**
     * @return Returns the siteId from the super class.
     */
    public int getSiteId() {
        return super.getSiteId();
    }

    /**
     * @return Returns the siteName from the super class.
     */
    public String getSiteName() {
        return super.getSiteName();
    }

    /**
     * @return Returns the patients.
     */
    public ArrayList getPatients() {
        return patients;
    }

    /**
     * @param patient The patients to set.
     */
    public void addPatient(SafeMotherhoodRegisterPatient patient) {
        if (patients == null) {
            patients = new ArrayList();
        }
        patients.add(patient);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void getPatientRegister(Date beginDate, Date endDate, int siteId) {

        SafeMotherhoodRegisterPatient smrPatient;

        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;

        // TODO: Need to query to get unique pregnancies-- that is, if a
        // woman was pregnant more than 1 time during the time frame in
        // question, then each pregnancy must be treated separately;
        // currently, the following assumes only 1 pregnancy per woman...

        // First, get the unique list of patients who visited during the
        // time period in question
        Connection conn = null;
        try {
            conn = ZEPRSUtils.getZEPRSConnection();
        } catch (ServletException e) {
            log.error(e);
        }

        try {
            if (this.getType().equals("antenatal")) {
                 rs = ZEPRSUtils.getMCHMothers(beginDate, endDate, siteId, conn);
            } else if (this.getType().equals("antenatalPrint")) {
                 rs = ZEPRSUtils.getMCHMothersSingleVisit(beginDate, endDate, siteId, conn);
            } else {
                rs = ZEPRSUtils.getPostnatalMothers(beginDate, endDate, siteId, conn);  // postnatal
            }
        } catch (ServletException e) {
            log.error(e);
        }

        // For each patient, load the report class and add this patient
        // to the list

        try {
            while (rs.next()) {
                try {
                    int patientID = rs.getInt("patient_id");
                    String currentType = this.getType();
                    smrPatient = new SafeMotherhoodRegisterPatient();
                    if (this.getType().equals("antenatalPrint")) {
                        int cnt = rs.getInt("cnt");
                        currentType = "antenatal";
                        if (cnt == 1) {
                            try {
                                smrPatient.loadPatient(patientID, beginDate, endDate, siteId, currentType, conn);
                                // For SM register, include only the first AN visit attendees.  
                                if (smrPatient.getFirstAttDate().getTime() >= beginDate.getTime()) {
                                    this.addPatient(smrPatient);
                                }
                            } catch (Exception e) {
                                log.error(e + " patientID: " + patientID + "; siteId: " + siteId + "; conn closed?: " + conn.isClosed());
                                e.printStackTrace();
                            }

                        }
                    } else {
                        try {
                            smrPatient.loadPatient(patientID, beginDate, endDate, siteId, currentType, conn);
                        } catch (Exception e) {
                            log.error(e + " patientID: " + patientID + "; siteId: " + siteId + "; conn closed?: " + conn.isClosed());
                            e.printStackTrace();
                        }
                        this.addPatient(smrPatient);
                    }

                } catch (SQLException e) {
                    log.error(e);
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }
        try {
            rs.close();
            conn.close();
        } catch (SQLException e) {
            log.error(e);
        }
    }
}