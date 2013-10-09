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
import org.cidrz.project.zeprs.report.valueobject.MaternalMortalityPatient;
import org.cidrz.project.zeprs.valueobject.gen.MaternalDischarge;
import org.cidrz.project.zeprs.valueobject.report.PatientRegistrationClean;
import org.cidrz.webapp.dynasite.dao.DemographicsDAO;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class MaternalMortalityRegister extends ZEPRSRegister {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(MaternalMortalityRegister.class);

    ArrayList patients = new ArrayList();
    String reportMonth;
    String reportYear;

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
    public void addPatient(MaternalMortalityPatient patient) {
        patients.add(patient);
    }

    public void getPatientRegister(Date beginDate, Date endDate, int siteId) {

        ResultSet rs = null;
            // First, get the unique list of patients who visited during the time period in question
        Connection conn = null;
        try {
            conn = ZEPRSUtils.getZEPRSConnection();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        try {
            rs = ZEPRSUtils.getUniqueVisits(beginDate, endDate, siteId, conn);
            // For each patient, load the report class and add this patient to the list
            while (rs.next()) {
                int patientID = rs.getInt("patient_id");
                MaternalMortalityPatient patient = null;
                patient = loadPatient(patientID, beginDate, endDate, siteId, conn);
                if (patient != null) {
                    this.addPatient(patient);
                }
            }
            rs.close();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public MaternalMortalityPatient loadPatient(int patientID, Date beginDate, Date endDate, int siteID, Connection conn) {

        Long patientId = (long) patientID;
        MaternalMortalityPatient patient = new MaternalMortalityPatient();
        patient.setPatientId(patientId);

        /**
         * First get the current pregnancy id
         */
        Long pregnancyId = null;
        SessionPatient currPreg = null;
        try {
            currPreg = PatientRecordUtils.getSessionPatientPregnancy(conn, (long) patientID);
            pregnancyId = currPreg.getCurrentPregnancyId();
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } catch (ObjectNotFoundException e) {
            log.error(e);
        }
        patient.setCurrentPregnancyId(pregnancyId);

        /**
         * Maternal Discharge Summary
         */

        MaternalDischarge discharge = null;
        boolean isDead = false;
        try {
            discharge = (MaternalDischarge) EncountersDAO.getOne(conn, patientId, pregnancyId, (long) 68);
        } catch (IOException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } catch (ObjectNotFoundException e) {
            // it's ok
        } catch (ClassNotFoundException e) {
            log.error(e);
        }

        if (discharge != null) {
            Date dischargeVisitDate = discharge.getDateVisit();
            patient.setDateDeath(dischargeVisitDate);
            Integer dischargeField = discharge.getField1654();
            if (dischargeField != null) {
                if (dischargeField == 2819) {
                    isDead = true;
                }
            }

            String comments = discharge.getField597();
            if (comments != null) {
                patient.setComments(comments);
            }
        }

        if (isDead) {
            PatientRegistrationClean pr = null;
            try {
                pr = (PatientRegistrationClean) DemographicsDAO.getOne(conn, patientId);
                patient.setPatientRegistration(pr);
                String birthDate = pr.getBirthDate();
                if (birthDate != null) {
                    int age = ZEPRSUtils.getCurrentAge(Date.valueOf(birthDate));
                    patient.getPatientRegistration().setCurrentAge(age);
                }
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            patient.setPatientRegistration(pr);
            return patient;
        } else {
            return null;
        }
    }
}
