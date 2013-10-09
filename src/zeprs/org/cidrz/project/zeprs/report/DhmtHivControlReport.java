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

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 * @author ericl
 * 
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class DhmtHivControlReport extends ZEPRSReport {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(DhmtHivControlReport.class);

    String reportMonth;
    String reportYear;
    Date beginDate;
    Date endDate;

    protected int rprTotalPos;
    protected int rprTotalPosYtd;

    private int womenTreatedStds;
    private int womenGenitalUlcers;
    private int womenVaginalDischarges;
    private int womenNewAnc;
    private int womenAttendedPnc;
    private int totalDeliveries;
    private String womenGenitalUlcersCalc;
    private String womenVaginalDischargesCalc;
    private String womenAttendedPncCalc;


    DhmtHivControlReport() {

        reportMonth = null;
        reportYear = null;
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


    public int getWomenTreatedStds() {
        return womenTreatedStds;
    }

    public void setWomenTreatedStds(int womenTreatedStds) {
        this.womenTreatedStds = womenTreatedStds;
    }

    public int getWomenGenitalUlcers() {
        return womenGenitalUlcers;
    }

    public void setWomenGenitalUlcers(int womenGenitalUlcers) {
        this.womenGenitalUlcers = womenGenitalUlcers;
    }

    public int getWomenVaginalDischarges() {
        return womenVaginalDischarges;
    }

    public void setWomenVaginalDischarges(int womenVaginalDischarges) {
        this.womenVaginalDischarges = womenVaginalDischarges;
    }

    public int getWomenAttendedPnc() {
        return womenAttendedPnc;
    }

    public void setWomenAttendedPnc(int womenAttendedPnc) {
        this.womenAttendedPnc = womenAttendedPnc;
    }

    public String getWomenGenitalUlcersCalc() {
        return womenGenitalUlcersCalc;
    }

    public void setWomenGenitalUlcersCalc(String womenGenitalUlcersCalc) {
        this.womenGenitalUlcersCalc = womenGenitalUlcersCalc;
    }

    public String getWomenVaginalDischargesCalc() {
        return womenVaginalDischargesCalc;
    }

    public void setWomenVaginalDischargesCalc(String womenVaginalDischargesCalc) {
        this.womenVaginalDischargesCalc = womenVaginalDischargesCalc;
    }

    public String getWomenAttendedPncCalc() {
        return womenAttendedPncCalc;
    }

    public void setWomenAttendedPncCalc(String womenAttendedPncCalc) {
        this.womenAttendedPncCalc = womenAttendedPncCalc;
    }

    public int getWomenNewAnc() {
        return womenNewAnc;
    }

    public void setWomenNewAnc(int womenNewAnc) {
        this.womenNewAnc = womenNewAnc;
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
            this.setWomenNewAnc(ZEPRSSharedItems.getTotalFirstAnteAttendances(beginDate, endDate, siteID, conn));

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
            }
            this.setTotalDeliveries(deliveries);
            rs.close();

            /**
             * RPR
             */
            int rprPenicillin = 0;

            formID = 90;
            rs = ZEPRSUtils.getEncounters(formID, "rpr ", beginDate, endDate, siteID, conn, false);

            while (rs.next()) {
                int rprResult = rs.getInt("rpr_result");
                int rprDrug = rs.getInt("rpr_drug");
                if ((rprResult == Constants.RPR_RESULT_NONREACTIVE) || (rprResult == Constants.RPR_RESULT_REACTIVE)) {
                    if (rprResult == Constants.RPR_RESULT_REACTIVE) {
                        //rprPositive++;
                        if (rprDrug == Constants.RPR_TREATMENT_PENICILLIN) {
                            rprPenicillin++;
                        }
                    }
                }
            }
            this.setWomenTreatedStds(rprPenicillin);

            ///////////////////////////////////////////////////////////////////////
            // PNC = postnatal visits
            ///////////////////////////////////////////////////////////////////////
            this.setWomenAttendedPnc(ZEPRSSharedItems.getTotalPostAttendances(beginDate, endDate, siteID, conn));

            /**
             * Women Genital Ulcers
             */
            formID = 65;
            rs = ZEPRSUtils.getEncounters(formID, "problabor ", beginDate, endDate, siteID, conn, false);
            int womenGenitalUlcers = 0;
            while (rs.next()) {

                int condition = rs.getInt("condition_of_vulva_321");
                if (condition == 1163) {   // ulcers
                    womenGenitalUlcers++;
                }
            }
            this.setWomenGenitalUlcers(womenGenitalUlcers);
            rs.close();

            /**
             * Women Vaginal Discharges
             */
            formID = 78;
            rs = ZEPRSUtils.getEncounters(formID, "probpostnatal ", beginDate, endDate, siteID, conn, false);
            int womenVaginalDischarges = 0;
            while (rs.next()) {
                int discharge = rs.getInt("vag_discharge_1252");
                if (discharge == 1) {
                    womenVaginalDischarges++;
                }
            }
            rs.close();

            formID = 65;
            womenVaginalDischarges = this.getWomenVaginalDischarges();
            rs = ZEPRSUtils.getEncounters(formID, "problabor ", beginDate, endDate, siteID, conn, false);
            while (rs.next()) {
                int discharge = rs.getInt("vag_discharge_1252");
                if (discharge == 1) {
                    womenVaginalDischarges++;
                }
            }
            rs.close();
            this.setWomenVaginalDischarges(womenVaginalDischarges);

            /**
             * calcs
             */
            DecimalFormat df= new DecimalFormat("##.##");
            if (this.getWomenGenitalUlcers() > 0 && this.getWomenTreatedStds() > 0) {
                double womenGenitalUlcersCalc = (double)this.getWomenGenitalUlcers() / (double)this.getWomenTreatedStds();
                this.setWomenGenitalUlcersCalc(df.format(womenGenitalUlcersCalc));
            }
            if (this.getWomenVaginalDischarges() > 0 && this.getWomenTreatedStds() > 0) {
                double womenVaginalDischargesCalc = (double)this.getWomenVaginalDischarges() / (double)this.getWomenTreatedStds();
                this.setWomenVaginalDischargesCalc(df.format(womenVaginalDischargesCalc));
            }
            if (this.getWomenAttendedPnc() > 0 && this.getTotalDeliveries() > 0) {
                double womenAttendedPncCalc = (double)this.getWomenAttendedPnc() / (double)this.getTotalDeliveries();
                this.setWomenAttendedPncCalc(df.format(womenAttendedPncCalc));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected static void processRpr(Connection conn, int patientId, DhmtHivControlReport report, String field) {

        int rprPositive = 0;
        int rprPenicillin = 0;
        try {
            // First, get the form id for the RPR form
            int formID = 90;

            ResultSet rs = ZEPRSUtils.getPatientEncounters(patientId, "rpr ", conn);

            while (rs.next()) {
                int rprResult = rs.getInt("rpr_result");
                int rprDrug = rs.getInt("rpr_drug");
                if ((rprResult == Constants.RPR_RESULT_NONREACTIVE) || (rprResult == Constants.RPR_RESULT_REACTIVE)) {
                    if (rprResult == Constants.RPR_RESULT_REACTIVE) {
                        //rprPositive++;
                        if (rprDrug == Constants.RPR_TREATMENT_PENICILLIN) {
                            rprPenicillin++;
                        }
                    }
                }
            }
            report.setWomenTreatedStds(rprPenicillin);
            /*if (field.equals("rprTotalPos")) {
                report.setRprTotalPos(rprPositive);
            } else {
                report.setRprTotalPosYtd(rprPositive);
            }*/

            rs.close();
        } catch (Exception e) {
            log.error(e);
        }
    }
}
