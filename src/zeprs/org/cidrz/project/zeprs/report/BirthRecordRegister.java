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
import org.cidrz.project.zeprs.report.valueobject.BirthRecordPatient;
import org.cidrz.project.zeprs.valueobject.gen.MaternalDischarge;
import org.cidrz.project.zeprs.valueobject.gen.NewbornEval;
import org.cidrz.project.zeprs.valueobject.report.PatientRegistrationClean;
import org.cidrz.webapp.dynasite.dao.DemographicsDAO;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;
import org.cidrz.webapp.dynasite.valueobject.Patient;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class BirthRecordRegister extends ZEPRSRegister {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(BirthRecordRegister.class);

    ArrayList patients = new ArrayList();
    String reportMonth;
    String reportYear;

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
    public void addPatient(BirthRecordPatient patient) {
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
                BirthRecordPatient patient = null;
                // Get the mother's demo info
                    PatientRegistrationClean motherPatientRegistration = null;
                    try {
                        motherPatientRegistration = (PatientRegistrationClean) DemographicsDAO.getOne(conn, (long) patientID);
                    } catch (ServletException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ObjectNotFoundException e) {
                        log.error(e);
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
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
                // patient.setCurrentPregnancyId(pregnancyId);
                // get children
                List children = PatientDAO.getChildren(conn, (long) patientID, pregnancyId);
                for (int i = 0; i < children.size(); i++) {
                    Patient child = (Patient) children.get(i);
                    Long childId = child.getId();
                    try {
                        patient = loadPatient(childId.intValue(), pregnancyId, beginDate, endDate, siteId, conn);
                        if (patient != null) {
                            patient.setMotherPatientRegistration(motherPatientRegistration);
                            this.addPatient(patient);
                        }
                    } catch (Exception e) {
                        log.error("Error fetching child in BirthRecordRegister - childId: " + childId + " Mother's patientID: " + patientID + " pregnancyId: " + pregnancyId);
                        log.error(e);
                        e.printStackTrace();
                    }
                }
            }
            rs.close();
        } catch (ServletException e) {
            log.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            log.error(e);
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BirthRecordPatient loadPatient(int patientID, Long pregnancyId, Date beginDate, Date endDate, int siteID, Connection conn) {

        Long patientId = (long) patientID;
        BirthRecordPatient patient = new BirthRecordPatient();
        patient.setPatientId(patientId);

        // Get the newborn's demo info
        PatientRegistrationClean pr = null;
        try {
            pr = (PatientRegistrationClean) DemographicsDAO.getOne(conn, (long) patientID);
            patient.setInfantPatientRegistration(pr);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            log.error("Error running BirthRecordPatient report: No Patient Registration for patientId: " + patientID);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        NewbornEval newbornEval = null;
        try {
            newbornEval = (NewbornEval) EncountersDAO.getOne(conn, patientId, pregnancyId, (long) 23);
            Boolean homeBorn = newbornEval.getField1921();
            if (homeBorn) {
                patient.setPlaceOfBirth("Home");
            } else {
                String placeOfBirth = newbornEval.getSiteName();
                patient.setPlaceOfBirth(placeOfBirth);
            }
            if (pr != null) {
                try {
                    pr.setBirthDate(newbornEval.getField1267().toString());
                    if (newbornEval.getField1514() != null) {
                        pr.setTimeOfBirth(newbornEval.getField1514().toString());
                    }
                } catch (NullPointerException e) {
                    log.error(e);
                }
            }

            Integer sexInt = newbornEval.getField490();
            if (sexInt != null) {
                if (sexInt == 1) {
                    patient.setSex("Female");
                } else {
                    patient.setSex("Male");
                }
            }

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
        return patient;
    }
}
