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

import java.sql.Date;
import java.sql.Connection;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class DeliveryTallySheetReport extends ZEPRSReport {

    private int normalDeliveries;
    private int complicatedDeliveries;
    private int totalDeliveries;
    private int liveBirths;
    private int stillBirths;
    private int lowBirthWeights;
    private String reportMonth;
    private String reportYear;
    private int ancOld;
    private int ancNew;
    private int totalVisits;
    private int maternalDeaths;
    private int neonatalDeaths;
    private int totalDeaths;
    private Date beginDate;
    private Date endDate;


    DeliveryTallySheetReport() {

        normalDeliveries = -1;
        complicatedDeliveries = -1;
        liveBirths = -1;
        stillBirths = -1;
        lowBirthWeights = -1;
        reportMonth = null;
        reportYear = null;
        ancOld = -1;
        ancNew = -1;
        totalVisits = -1;
    }

    /**
     * @return Returns the complicatedDeliveries.
     */
    public int getComplicatedDeliveries() {
        return complicatedDeliveries;
    }

    /**
     * @param complicatedDeliveries The complicatedDeliveries to set.
     */
    public void setComplicatedDeliveries(int complicatedDeliveries) {
        this.complicatedDeliveries = complicatedDeliveries;
    }

    public int getTotalDeliveries() {
        return totalDeliveries;
    }

    public void setTotalDeliveries(int totalDeliveries) {
        this.totalDeliveries = totalDeliveries;
    }

    /**
     * @return Returns the liveBirths.
     */
    public int getLiveBirths() {
        return liveBirths;
    }

    /**
     * @param liveBirths The liveBirths to set.
     */
    public void setLiveBirths(int liveBirths) {
        this.liveBirths = liveBirths;
    }

    /**
     * @return Returns the lowBirthWeights.
     */
    public int getLowBirthWeights() {
        return lowBirthWeights;
    }

    /**
     * @param lowBirthWeights The lowBirthWeights to set.
     */
    public void setLowBirthWeights(int lowBirthWeights) {
        this.lowBirthWeights = lowBirthWeights;
    }

    /**
     * @return Returns the normalDeliveries.
     */
    public int getNormalDeliveries() {
        return normalDeliveries;
    }

    /**
     * @param normalDeliveries The normalDeliveries to set.
     */
    public void setNormalDeliveries(int normalDeliveries) {
        this.normalDeliveries = normalDeliveries;
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
     * @return Returns the stillBirths.
     */
    public int getStillBirths() {
        return stillBirths;
    }

    /**
     * @param stillBirths The stillBirths to set.
     */
    public void setStillBirths(int stillBirths) {
        this.stillBirths = stillBirths;
    }

        /**
     * @return Returns the ancNew.
     */
    public int getAncNew() {
        return ancNew;
    }

    /**
     * @param ancNew The ancNew to set.
     */
    public void setAncNew(int ancNew) {
        this.ancNew = ancNew;
    }

    /**
     * @return Returns the ancOLD.
     */
    public int getAncOld() {
        return ancOld;
    }

    /**
     * @param ancOld The ancOLD to set.
     */
    public void setAncOld(int ancOld) {
        this.ancOld = ancOld;
    }

    public int getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(int totalVisits) {
        this.totalVisits = totalVisits;
    }

    public int getMaternalDeaths() {
        return maternalDeaths;
    }

    public void setMaternalDeaths(int maternalDeaths) {
        this.maternalDeaths = maternalDeaths;
    }

    public int getNeonatalDeaths() {
        return neonatalDeaths;
    }

    public void setNeonatalDeaths(int neonatalDeaths) {
        this.neonatalDeaths = neonatalDeaths;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
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

    public void loadReport(Date beginDate, Date endDate) {

        int siteID = super.getSiteId();

        // set the month and year for the report
        this.setReportMonth(ZEPRSUtils.getReportMonth(beginDate, endDate));
        this.setReportYear(ZEPRSUtils.getReportYear(beginDate, endDate));
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);

        try {
            // setup datasource
            Connection conn = null;
            conn = ZEPRSUtils.getZEPRSConnection();

            ///////////////////////////////////////////////////////////////////////
            // Normal Deliveries
            ///////////////////////////////////////////////////////////////////////
            this.setNormalDeliveries(ZEPRSSharedItems.getTotalNormalDeliveries(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Complicated Deliveries
            ///////////////////////////////////////////////////////////////////////
            this.setComplicatedDeliveries(ZEPRSSharedItems.getTotalComplicatedDeliveries(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Total Deliveries
            ///////////////////////////////////////////////////////////////////////
            this.setTotalDeliveries(ZEPRSSharedItems.getTotalDeliveries(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Live Births
            ///////////////////////////////////////////////////////////////////////
            this.setLiveBirths(ZEPRSSharedItems.getTotalLiveBirths(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Still Births
            ///////////////////////////////////////////////////////////////////////
            int freshSB = ZEPRSSharedItems.getTotalFreshStillBirths(beginDate, endDate, siteID, conn);
            int maceratedSB = ZEPRSSharedItems.getTotalMaceratedStillBirths(beginDate, endDate, siteID, conn);
            this.setStillBirths(freshSB + maceratedSB);

            ///////////////////////////////////////////////////////////////////////
            // Low Birth Weigth
            ///////////////////////////////////////////////////////////////////////
            this.setLowBirthWeights(ZEPRSSharedItems.getTotalLowBirthWeights(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // ANC Old
            ///////////////////////////////////////////////////////////////////////
            this.setAncOld(ZEPRSSharedItems.getTotalAnteReattendances(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // ANC New
            ///////////////////////////////////////////////////////////////////////
            this.setAncNew(ZEPRSSharedItems.getTotalFirstAnteAttendances(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Total visits
            ///////////////////////////////////////////////////////////////////////
            int count = ZEPRSUtils.getEncountersCount(beginDate, endDate, siteID, conn);
            this.setTotalVisits(count);

            ///////////////////////////////////////////////////////////////////////
            // Maternal Deaths
            ///////////////////////////////////////////////////////////////////////
            this.setMaternalDeaths(ZEPRSSharedItems.getTotalMaternalDeaths(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Neonatal Deaths
            ///////////////////////////////////////////////////////////////////////
            this.setNeonatalDeaths(ZEPRSSharedItems.getTotalNeonatalDeaths(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Total Deaths
            ///////////////////////////////////////////////////////////////////////
            this.setTotalDeaths(ZEPRSSharedItems.getTotalDeaths(beginDate, endDate, siteID, conn));

            conn.close();
        } catch (Exception e) {
            // TBD
        }
    }
}
