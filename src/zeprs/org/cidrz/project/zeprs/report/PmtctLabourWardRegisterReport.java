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
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Site;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class PmtctLabourWardRegisterReport extends ZEPRSRegister {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(PmtctLabourWardRegisterReport.class);

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
    public void addPatient(PmtctLabourWardPatient patient) {
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

        PmtctLabourWardPatient pmtctPatient = null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;

        // First, get the unique list of patients who visited during the
        // time period in question
        Connection conn = null;
        try {
            conn = ZEPRSUtils.getZEPRSConnection();
        } catch (ServletException e) {
            log.error(e);
        }

        /**
         * Delivery summary - arv received question
         */
        ResultSet deliverySumRs = null;
        try {
            int formID = 66;
            deliverySumRs = ZEPRSUtils.getEncounters(formID, "deliverysum", beginDate, endDate, siteId, conn, false);
            // loop through all records to count the number assisted breech delivery
            while (deliverySumRs.next()) {
                pmtctPatient = new PmtctLabourWardPatient();
                int patientID = deliverySumRs.getInt("patient_id");
                int pregnancyId = deliverySumRs.getInt("pregnancy_id");
                pmtctPatient.setCurrentPregnancyId(Long.valueOf(pregnancyId));
                pmtctPatient.setDateDelivery(deliverySumRs.getDate("date_visit"));
                if (deliverySumRs.getInt("mother_received_arv") == 1) {
                    pmtctPatient.setArvReceived(true);
                    siteId = deliverySumRs.getInt("site_id");
                    Site site = (Site) DynaSiteObjects.getClinicMap().get(new Long(siteId));
                    pmtctPatient.setPlaceArvReceived(site.getName());
                    pmtctPatient.setDateArvReceived(pmtctPatient.getDateDelivery());
                }
                int modeOdDelivery = deliverySumRs.getInt("mode_of_delivery_447");

                if (modeOdDelivery > 0) {
                    pmtctPatient.setBirthType(PatientRecordUtils.resolveEnum(modeOdDelivery, 0));
                }
                int regimen = deliverySumRs.getInt("regimen");
                if (regimen > 0) {
                    pmtctPatient.setRegimenMother(PatientRecordUtils.resolveEnum(regimen, 0));
                }
                pmtctPatient.setDeliveryNurse(deliverySumRs.getString("nurse_delivering"));
                pmtctPatient.loadPatient(patientID, beginDate, endDate, siteId, conn);
                if (pmtctPatient != null && pmtctPatient.getHivPositive() == 1) {
                	this.addPatient(pmtctPatient);
                }

            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            deliverySumRs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /* try {
                    rs = ZEPRSUtils.getUniqueVisits(beginDate, endDate, siteId, conn);
                } catch (ServletException e) {
                    log.error(e);
                }
        */
        // For each patient, load the report class and add this patient
        // to the list

        /* try {
            while (rs.next()) {
                try {
                    int patientID = rs.getInt("patient_id");
                    pmtctPatient = new PmtctLabourWardPatient();
                    try {
                        pmtctPatient.loadPatient(patientID, beginDate, endDate, siteId, conn);
                    } catch (Exception e) {
                        log.error(e + " patientID: " + patientID + "; siteId: " + siteId + "; conn closed?: " + conn.isClosed());
                        e.printStackTrace();
                    }
                    this.addPatient(pmtctPatient);
                } catch (SQLException e) {
                    log.error(e);
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }*/

    }
}