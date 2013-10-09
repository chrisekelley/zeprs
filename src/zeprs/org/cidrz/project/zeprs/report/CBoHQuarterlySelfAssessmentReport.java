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
 * Created on Mar 29, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.utils.DateUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CBoHQuarterlySelfAssessmentReport extends ZEPRSReport {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(CBoHQuarterlySelfAssessmentReport.class);

    String reportMonth;
    String reportYear;
    Date beginDate;
    Date endDate;

    int newPatients;
    int totalDeliveries;
    protected int rprTotalPos;
    protected int rprTotalPosYtd;
    private int tt1done;
    private int tt2done;
    private int tt3done;
    private int tt4done;
    private int tt5done;

    CBoHQuarterlySelfAssessmentReport() {

        reportMonth = null;
        reportYear = null;

        newPatients = -1;
    }

    /**
     * @return Returns the newPatients.
     */
    public int getNewPatients() {
        return newPatients;
    }

    /**
     * @param newPatients The newPatients to set.
     */
    public void setNewPatients(int newPatients) {
        this.newPatients = newPatients;
    }

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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTotalDeliveries() {
        return totalDeliveries;
    }

    public void setTotalDeliveries(int totalDeliveries) {
        this.totalDeliveries = totalDeliveries;
    }

    public int getRprTotalPos() {
        return rprTotalPos;
    }

    public void setRprTotalPos(int rprTotalPos) {
        this.rprTotalPos = rprTotalPos;
    }

    public int getRprTotalPosYtd() {
        return rprTotalPosYtd;
    }

    public void setRprTotalPosYtd(int rprTotalPosYtd) {
        this.rprTotalPosYtd = rprTotalPosYtd;
    }

    public int getTt1done() {
        return tt1done;
    }

    public void setTt1done(int tt1done) {
        this.tt1done = tt1done;
    }

    public int getTt2done() {
        return tt2done;
    }

    public void setTt2done(int tt2done) {
        this.tt2done = tt2done;
    }

    public int getTt3done() {
        return tt3done;
    }

    public void setTt3done(int tt3done) {
        this.tt3done = tt3done;
    }

    public int getTt4done() {
        return tt4done;
    }

    public void setTt4done(int tt4done) {
        this.tt4done = tt4done;
    }

    public int getTt5done() {
        return tt5done;
    }

    public void setTt5done(int tt5done) {
        this.tt5done = tt5done;
    }

    public void loadReport(Date beginDate, Date endDate) {
        int siteID = super.getSiteId();

        // set the month and year for the report
        this.setReportMonth(ZEPRSUtils.getReportMonth(beginDate, endDate));
        this.setReportYear(ZEPRSUtils.getReportYear(beginDate, endDate));
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        Connection conn = null;
        try {
             conn = ZEPRSUtils.getZEPRSConnection();

            ///////////////////////////////////////////////////////////////////////
            // ANC New
            ///////////////////////////////////////////////////////////////////////
            this.setNewPatients(ZEPRSSharedItems.getTotalFirstAnteAttendances(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Total Deliveries
            ///////////////////////////////////////////////////////////////////////

            /**
             * Loop through the deliveries at the clinic, then get rpr and tetanus stats. We don't use begin/end date
             * for rpr and tetanus stats, because they may have been given before the report range.
             * These are tests that may be done many months before the pregnancy.
             */
            int deliveries = 0;
            int formID = 66;
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "deliverysum ", beginDate, endDate, siteID, conn, true);
            while (rs.next()) {
                deliveries++;
                int patientId = rs.getInt("patient_id");
                /**
                 * RPR
                 */
                processRpr(conn, patientId, this, "rprTotalPos");
                /**
                 * Pregnancies Protected against Tetanus - tt1 - tt5
                 */
                processTetanus(conn, patientId, this);
            }
            //this.setTotalDeliveries(ZEPRSSharedItems.getTotalDeliveries(beginDate, endDate, siteID, conn));
            this.setTotalDeliveries(deliveries);
            rs.close();
            // last year's rpr stats
            deliveries = 0;
            formID = 66;
            String beginDateYtdStr = DateUtils.createFutureDate(beginDate, -365);
            Date beginDateYtd = Date.valueOf(beginDateYtdStr);
            String endDateYtdStr = DateUtils.createFutureDate(endDate, -365);
            Date endDateYtd = Date.valueOf(endDateYtdStr);
            rs = ZEPRSUtils.getEncounters(formID, "deliverysum ", beginDateYtd, endDateYtd, siteID, conn, true);
            while (rs.next()) {
                deliveries++;
                int patientId = rs.getInt("patient_id");
                processRpr(conn, patientId, this, "rprTotalPosYtd");
            }
            rs.close();
           // conn.close();
        } catch (Exception e) {
            log.error("Error running the report: " + e.getMessage());
        } finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected static void processRpr(Connection conn, int patientId, CBoHQuarterlySelfAssessmentReport report, String field) {

        int rprPositive = 0;

        try {
            // First, get the form id for the RPR form
            int formID = 90;

            ResultSet rs = ZEPRSUtils.getPatientEncounters(patientId, "rpr ", conn);

            while (rs.next()) {
                int rprResult = rs.getInt("rpr_result");
                if ((rprResult == Constants.RPR_RESULT_NONREACTIVE) || (rprResult == Constants.RPR_RESULT_REACTIVE)) {
                    if (rprResult == Constants.RPR_RESULT_REACTIVE) {
                        rprPositive++;
                    }
                }
            }
            if (field.equals("rprTotalPos")) {
                report.setRprTotalPos(rprPositive);
            } else {
                report.setRprTotalPosYtd(rprPositive);
            }

            rs.close();

            // Get the rpr results from previous year
            formID = 90;


            rs.close();

        } catch (Exception e) {
            log.error(e);
        }
    }

    protected static void processTetanus(Connection conn, int patientId, CBoHQuarterlySelfAssessmentReport report) {

        try {

            /**
             * tetanus toxoid results
             */
            int formID = 92;
            // ResultSet rs = ZEPRSUtils.getEncounters(formID, "safeMotherhoodCare", beginDate, endDate, siteID, conn, true);
            ResultSet rs = ZEPRSUtils.getPatientEncounters(patientId, "safemotherhoodcare ", conn);
            while (rs.next()) {
                int tt1 = rs.getInt("tt1_done");
                int tt2 = rs.getInt("tt2_done");
                int tt3 = rs.getInt("tt3_done");
                int tt4 = rs.getInt("tt4_done");
                int tt5 = rs.getInt("tt5_done");
                if (tt1 == 1) {
                    tt1++;
                }
                if (tt2 == 1) {
                    tt2++;
                }
                if (tt3 == 1) {
                    tt3++;
                }
                if (tt4 == 1) {
                    tt4++;
                }
                if (tt5 == 1) {
                    tt5++;
                }
                report.setTt1done(tt1);
                report.setTt2done(tt2);
                report.setTt3done(tt3);
                report.setTt4done(tt4);
                report.setTt5done(tt5);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

}
