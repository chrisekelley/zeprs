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

import java.sql.*;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SafeMotherhoodTallyReport extends ZEPRSReport {

    private int firstAnteAtt;
    private int firstPostAtt;
    private int anteReatt;
    private int protectedPregsTT1;
    private int protectedPregsTT2;
    private int protectedPregsTT3;
    private int protectedPregsTT4;
    private int protectedPregsTT5;
    private int prevProtected;
    private int totalProtectedPregs;

    private String reportMonth;
    private String reportYear;

    SafeMotherhoodTallyReport() {

        firstAnteAtt = -1;
        firstPostAtt = -1;
        anteReatt = -1;
        protectedPregsTT1 = -1;
        protectedPregsTT2 = -1;
        protectedPregsTT3 = -1;
        protectedPregsTT4 = -1;
        protectedPregsTT5 = -1;
        prevProtected = -1;
        totalProtectedPregs = -1;

        reportMonth = null;
        reportYear = null;
    }


    /**
     * @return Returns the anteReatt.
     */
    public int getAnteReatt() {
        return anteReatt;
    }

    /**
     * @param anteReatt The anteReatt to set.
     */
    public void setAnteReatt(int anteReatt) {
        this.anteReatt = anteReatt;
    }

    /**
     * @return Returns the firstAnteAtt.
     */
    public int getFirstAnteAtt() {
        return firstAnteAtt;
    }

    /**
     * @param firstAnteAtt The firstAnteAtt to set.
     */
    public void setFirstAnteAtt(int firstAnteAtt) {
        this.firstAnteAtt = firstAnteAtt;
    }

    /**
     * @return Returns the firstPostAtt.
     */
    public int getFirstPostAtt() {
        return firstPostAtt;
    }

    /**
     * @param firstPostAtt The firstPostAtt to set.
     */
    public void setFirstPostAtt(int firstPostAtt) {
        this.firstPostAtt = firstPostAtt;
    }

    /**
     * @return Returns the protectedPregsTT1.
     */
    public int getProtectedPregsTT1() {
        return protectedPregsTT1;
    }

    /**
     * @param protectedPregsTT1 The protectedPregsTT1 to set.
     */
    public void setProtectedPregsTT1(int protectedPregsTT1) {
        this.protectedPregsTT1 = protectedPregsTT1;
    }

    /**
     * @return Returns the protectedPregsTT2.
     */
    public int getProtectedPregsTT2() {
        return protectedPregsTT2;
    }

    /**
     * @param protectedPregsTT2 The protectedPregsTT2 to set.
     */
    public void setProtectedPregsTT2(int protectedPregsTT2) {
        this.protectedPregsTT2 = protectedPregsTT2;
    }

    /**
     * @return Returns the protectedPregsTT3.
     */
    public int getProtectedPregsTT3() {
        return protectedPregsTT3;
    }

    /**
     * @param protectedPregsTT3 The protectedPregsTT3 to set.
     */
    public void setProtectedPregsTT3(int protectedPregsTT3) {
        this.protectedPregsTT3 = protectedPregsTT3;
    }

    /**
     * @return Returns the protectedPregsTT4.
     */
    public int getProtectedPregsTT4() {
        return protectedPregsTT4;
    }

    /**
     * @param protectedPregsTT4 The protectedPregsTT4 to set.
     */
    public void setProtectedPregsTT4(int protectedPregsTT4) {
        this.protectedPregsTT4 = protectedPregsTT4;
    }

    /**
     * @return Returns the protectedPregsTT5.
     */
    public int getProtectedPregsTT5() {
        return protectedPregsTT5;
    }

    /**
     * @param protectedPregsTT5 The protectedPregsTT5 to set.
     */
    public void setProtectedPregsTT5(int protectedPregsTT5) {
        this.protectedPregsTT5 = protectedPregsTT5;
    }

    /**
     * @return Returns the prevProtected.
     */
    public int getPrevProtected() {
        return prevProtected;
    }

    /**
     * @param prevProtected The prevProtected to set.
     */
    public void setPrevProtected(int prevProtected) {
        this.prevProtected = prevProtected;
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
     * @return Returns the totalProtectedPregs.
     */
    public int getTotalProtectedPregs() {
        return totalProtectedPregs;
    }

    /**
     * @param totalProtectedPregs The totalProtectedPregs to set.
     */
    public void setTotalProtectedPregs(int totalProtectedPregs) {
        this.totalProtectedPregs = totalProtectedPregs;
    }

    public void loadReport(Date beginDate, Date endDate) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsStruc = null;
        String sql = null;
        int formID = 0;

        // set the month and year for the report
        this.setReportMonth(ZEPRSUtils.getReportMonth(beginDate, endDate));
        this.setReportYear(ZEPRSUtils.getReportYear(beginDate, endDate));

        int siteID = super.getSiteId();

        try {

            Connection conn = ZEPRSUtils.getZEPRSConnection();

            ///////////////////////////////////////////////////////////////////////
            // First Antenatal Attendances
            ///////////////////////////////////////////////////////////////////////
            this.setFirstAnteAtt(ZEPRSSharedItems.getTotalFirstAnteAttendances(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Antenatal ReAttendances
            ///////////////////////////////////////////////////////////////////////
            this.setAnteReatt(ZEPRSSharedItems.getTotalAnteReattendances(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Pregnancies Protected by Tetanus Toxiod Immunisation
            ///////////////////////////////////////////////////////////////////////

            // First, get the form id for the Tetanus Toxoid (TT) Immunisations form
            //formID = ZEPRSUtils.getFormID("Tetanus Toxoid (TT) Immunisations");
            formID = 4;
            // Next, determine how many times the Tetanus Toxoid (TT) Immunisations
            // form was submitted
            rs = ZEPRSUtils.getEncounters(formID, "safemotherhood", beginDate, endDate, siteID, conn, true);

            int protectedPregsTT1 = 0;
            int protectedPregsTT2 = 0;
            int protectedPregsTT3 = 0;
            int protectedPregsTT4 = 0;
            int protectedPregsTT5 = 0;
            int prevProtected = 0;
            int totalProtected = 0;

            while (rs.next()) {

                if (rs.getDate("tt1_110") != null) { // number of TT Doses as child
                    protectedPregsTT1++;
                }
                if (rs.getDate("tt2_111") != null) { // number of TT Doses as child
                    protectedPregsTT2++;
                }
                if (rs.getDate("tt3_112") != null) { // number of TT Doses as child
                    protectedPregsTT3++;
                }
                if (rs.getDate("tt4_113") != null) { // number of TT Doses as child
                    protectedPregsTT4++;
                }
                if (rs.getDate("tt5_114") != null) { // number of TT Doses as child
                    protectedPregsTT5++;
                }

                /*
                   TODO: verify this-- will this form be submitted if pregnant mother
                   has already been fully immunized prior to this pregancy?  If not,
                   then we need to requery for all encounters with this mother,
                   not just those during the dates given to determine this value...
                */

                /*if (rs.getInt("number_of_tt_109") == 5) { // previous doses
                    totalProtected++;
                }*/
            }

            // total protected pregs defined as all women who have received TT2 - TT5
            // plus all women who received all 5 doses prior to current pregnancy
            totalProtected += (protectedPregsTT2 + protectedPregsTT3 + protectedPregsTT4 + protectedPregsTT5);

            this.setProtectedPregsTT1(protectedPregsTT1);
            this.setProtectedPregsTT2(protectedPregsTT2);
            this.setProtectedPregsTT3(protectedPregsTT3);
            this.setProtectedPregsTT4(protectedPregsTT4);
            this.setProtectedPregsTT5(protectedPregsTT5);
            this.setPrevProtected(prevProtected);
            this.setTotalProtectedPregs(totalProtected);
            rs.close();
            ///////////////////////////////////////////////////////////////////////
            // First Postnatal Attendances
            ///////////////////////////////////////////////////////////////////////
            this.setFirstPostAtt(ZEPRSSharedItems.getTotalFirstPostAttendances(beginDate, endDate, siteID, conn));
            conn.close();
        } catch (Exception e) {
//            log.error("Error running the report: " + e.getMessage());
//			throw Exception(e);
        }
    }
}
