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
 * Created on Mar 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

import org.cidrz.project.zeprs.valueobject.gen.PregnancyDating;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.List;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class LUDHMBReport extends ZEPRSReport {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(LUDHMBReport.class);

    private int aaNewANC;
    private int aaOldANC;
    private int aaTotalANC;
    private int aaPostnatalAtt;

    private int newANCFirstTrimester;
    private int newANCSecondTrimester;
    private int newANCThirdTrimester;

    private int birthsLive;
    private int birthsPremature;

    private int stillBirthsFresh;
    private int stillBirthsMacerated;

    private int deathsNeonatal;
    private int deathsMaternal;
    private int deathsAbortions;

    private int rpr1Total;
    private int rpr1TotalPos;
    private int rpr1TotalPosTreated;
    private int rpr1TotalPartnerTreated;

    private int rpr2Total;
    private int rpr2TotalPos;
    private int rpr2TotalPosTreated;
    private int rpr2TotalPartnerTreated;

    private int newbornsTreated;

    private int hbBelow10g;
    private int hbTotal;

    private String reportMonth;
    private String reportYear;

    private int tt1done;
    private int tt2done;
    private int tt3done;
    private int tt4done;
    private int tt5done;

    private int fansidar1;
    private int fansidar2;
    private int fansidar3;

    private Date beginDate;
    private Date endDate;

    LUDHMBReport() {

        this.aaNewANC = -1;
        this.aaOldANC = -1;
        this.aaTotalANC = -1;
        this.aaPostnatalAtt = -1;

        this.newANCFirstTrimester = -1;
        this.newANCSecondTrimester = -1;
        this.newANCThirdTrimester = -1;

        this.birthsLive = -1;
        this.birthsPremature = -1;

        this.stillBirthsFresh = -1;
        this.stillBirthsMacerated = -1;

        this.deathsNeonatal = -1;
        this.deathsMaternal = -1;
        this.deathsAbortions = -1;

        this.rpr1Total = -1;
        this.rpr1TotalPos = -1;
        this.rpr1TotalPosTreated = -1;
        this.rpr1TotalPartnerTreated = -1;

        this.rpr2Total = -1;
        this.rpr2TotalPos = -1;
        this.rpr2TotalPosTreated = -1;
        this.rpr2TotalPartnerTreated = -1;

        this.newbornsTreated = -1;

        this.hbBelow10g = -1;
        this.hbTotal = -1;

//		this.reportBeginDate = null;
//		this.reportEndDate = null;

        this.reportMonth = null;
        this.reportYear = null;
    }

    /**
     * @return Returns the aaNewANC.
     */
    public int getAaNewANC() {
        return aaNewANC;
    }

    /**
     * @param aaNewANC The aaNewANC to set.
     */
    public void setAaNewANC(int aaNewANC) {
        this.aaNewANC = aaNewANC;
    }

    /**
     * @return Returns the aaOldANC.
     */
    public int getAaOldANC() {
        return aaOldANC;
    }

    /**
     * @param aaOldANC The aaOldANC to set.
     */
    public void setAaOldANC(int aaOldANC) {
        this.aaOldANC = aaOldANC;
    }

    /**
     * @return Returns the aaPostnatalAtt.
     */
    public int getAaPostnatalAtt() {
        return aaPostnatalAtt;
    }

    /**
     * @param aaPostnatalAtt The aaPostnatalAtt to set.
     */
    public void setAaPostnatalAtt(int aaPostnatalAtt) {
        this.aaPostnatalAtt = aaPostnatalAtt;
    }

    /**
     * @return Returns the aaTotalANC.
     */
    public int getAaTotalANC() {
        return aaTotalANC;
    }

    /**
     * @param aaTotalANC The aaTotalANC to set.
     */
    public void setAaTotalANC(int aaTotalANC) {
        this.aaTotalANC = aaTotalANC;
    }

    /**
     * @return Returns the birthsLive.
     */
    public int getBirthsLive() {
        return birthsLive;
    }

    /**
     * @param birthsLive The birthsLive to set.
     */
    public void setBirthsLive(int birthsLive) {
        this.birthsLive = birthsLive;
    }

    /**
     * @return Returns the birthsPremature.
     */
    public int getBirthsPremature() {
        return birthsPremature;
    }

    /**
     * @param birthsPremature The birthsPremature to set.
     */
    public void setBirthsPremature(int birthsPremature) {
        this.birthsPremature = birthsPremature;
    }

    /**
     * @return Returns the deathsAbortions.
     */
    public int getDeathsAbortions() {
        return deathsAbortions;
    }

    /**
     * @param deathsAbortions The deathsAbortions to set.
     */
    public void setDeathsAbortions(int deathsAbortions) {
        this.deathsAbortions = deathsAbortions;
    }

    /**
     * @return Returns the deathsMaternal.
     */
    public int getDeathsMaternal() {
        return deathsMaternal;
    }

    /**
     * @param deathsMaternal The deathsMaternal to set.
     */
    public void setDeathsMaternal(int deathsMaternal) {
        this.deathsMaternal = deathsMaternal;
    }

    /**
     * @return Returns the deathsNeonatal.
     */
    public int getDeathsNeonatal() {
        return deathsNeonatal;
    }

    /**
     * @param deathsNeonatal The deathsNeonatal to set.
     */
    public void setDeathsNeonatal(int deathsNeonatal) {
        this.deathsNeonatal = deathsNeonatal;
    }

    /**
     * @return Returns the hbBelow10g.
     */
    public int getHbBelow10g() {
        return hbBelow10g;
    }

    /**
     * @param hbBelow10g The hbBelow10g to set.
     */
    public void setHbBelow10g(int hbBelow10g) {
        this.hbBelow10g = hbBelow10g;
    }

    /**
     * @return Returns the hbTotal.
     */
    public int getHbTotal() {
        return hbTotal;
    }

    /**
     * @param hbTotal The hbTotal to set.
     */
    public void setHbTotal(int hbTotal) {
        this.hbTotal = hbTotal;
    }

    /**
     * @return Returns the newANCFirstTrimester.
     */
    public int getNewANCFirstTrimester() {
        return newANCFirstTrimester;
    }

    /**
     * @param newANCFirstTrimester The newANCFirstTrimester to set.
     */
    public void setNewANCFirstTrimester(int newANCFirstTrimester) {
        this.newANCFirstTrimester = newANCFirstTrimester;
    }

    /**
     * @return Returns the newANCSecondTrimester.
     */
    public int getNewANCSecondTrimester() {
        return newANCSecondTrimester;
    }

    /**
     * @param newANCSecondTrimester The newANCSecondTrimester to set.
     */
    public void setNewANCSecondTrimester(int newANCSecondTrimester) {
        this.newANCSecondTrimester = newANCSecondTrimester;
    }

    /**
     * @return Returns the newANCThirdTrimester.
     */
    public int getNewANCThirdTrimester() {
        return newANCThirdTrimester;
    }

    /**
     * @param newANCThirdTrimester The newANCThirdTrimester to set.
     */
    public void setNewANCThirdTrimester(int newANCThirdTrimester) {
        this.newANCThirdTrimester = newANCThirdTrimester;
    }

    /**
     * @return Returns the newbornsTreated.
     */
    public int getNewbornsTreated() {
        return newbornsTreated;
    }

    /**
     * @param newbornsTreated The newbornsTreated to set.
     */
    public void setNewbornsTreated(int newbornsTreated) {
        this.newbornsTreated = newbornsTreated;
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
     * @return Returns the reportEndDate.
     */
    public String getReportYear() {
        return reportYear;
    }

    /**
     * @param reportYear The reportEndDate to set.
     */
    public void setReportYear(String reportYear) {
        this.reportYear = reportYear;
    }

    /**
     * @return Returns the rpr1Total.
     */
    public int getRpr1Total() {
        return rpr1Total;
    }

    /**
     * @param rpr1Total The rpr1Total to set.
     */
    public void setRpr1Total(int rpr1Total) {
        this.rpr1Total = rpr1Total;
    }

    /**
     * @return Returns the rpr1TotalPartnerTreated.
     */
    public int getRpr1TotalPartnerTreated() {
        return rpr1TotalPartnerTreated;
    }

    /**
     * @param rpr1TotalPartnerTreated The rpr1TotalPartnerTreated to set.
     */
    public void setRpr1TotalPartnerTreated(int rpr1TotalPartnerTreated) {
        this.rpr1TotalPartnerTreated = rpr1TotalPartnerTreated;
    }

    /**
     * @return Returns the rpr1TotalPos.
     */
    public int getRpr1TotalPos() {
        return rpr1TotalPos;
    }

    /**
     * @param rpr1TotalPos The rpr1TotalPos to set.
     */
    public void setRpr1TotalPos(int rpr1TotalPos) {
        this.rpr1TotalPos = rpr1TotalPos;
    }

    /**
     * @return Returns the rpr1TotalPosTreated.
     */
    public int getRpr1TotalPosTreated() {
        return rpr1TotalPosTreated;
    }

    /**
     * @param rpr1TotalPosTreated The rpr1TotalPosTreated to set.
     */
    public void setRpr1TotalPosTreated(int rpr1TotalPosTreated) {
        this.rpr1TotalPosTreated = rpr1TotalPosTreated;
    }

    /**
     * @return Returns the rpr2Total.
     */
    public int getRpr2Total() {
        return rpr2Total;
    }

    /**
     * @param rpr2Total The rpr2Total to set.
     */
    public void setRpr2Total(int rpr2Total) {
        this.rpr2Total = rpr2Total;
    }

    /**
     * @return Returns the rpr2TotalPartnerTreated.
     */
    public int getRpr2TotalPartnerTreated() {
        return rpr2TotalPartnerTreated;
    }

    /**
     * @param rpr2TotalPartnerTreated The rpr2TotalPartnerTreated to set.
     */
    public void setRpr2TotalPartnerTreated(int rpr2TotalPartnerTreated) {
        this.rpr2TotalPartnerTreated = rpr2TotalPartnerTreated;
    }

    /**
     * @return Returns the rpr2TotalPos.
     */
    public int getRpr2TotalPos() {
        return rpr2TotalPos;
    }

    /**
     * @param rpr2TotalPos The rpr2TotalPos to set.
     */
    public void setRpr2TotalPos(int rpr2TotalPos) {
        this.rpr2TotalPos = rpr2TotalPos;
    }

    /**
     * @return Returns the rpr2TotalPosTreated.
     */
    public int getRpr2TotalPosTreated() {
        return rpr2TotalPosTreated;
    }

    /**
     * @param rpr2TotalPosTreated The rpr2TotalPosTreated to set.
     */
    public void setRpr2TotalPosTreated(int rpr2TotalPosTreated) {
        this.rpr2TotalPosTreated = rpr2TotalPosTreated;
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
     * @return Returns the stillBirthsFresh.
     */
    public int getStillBirthsFresh() {
        return stillBirthsFresh;
    }

    /**
     * @param stillBirthsFresh The stillBirthsFresh to set.
     */
    public void setStillBirthsFresh(int stillBirthsFresh) {
        this.stillBirthsFresh = stillBirthsFresh;
    }

    /**
     * @return Returns the stillBirthsMacerated.
     */
    public int getStillBirthsMacerated() {
        return stillBirthsMacerated;
    }

    /**
     * @param stillBirthsMacerated The stillBirthsMacerated to set.
     */
    public void setStillBirthsMacerated(int stillBirthsMacerated) {
        this.stillBirthsMacerated = stillBirthsMacerated;
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

    public int getFansidar1() {
        return fansidar1;
    }

    public void setFansidar1(int fansidar1) {
        this.fansidar1 = fansidar1;
    }

    public int getFansidar2() {
        return fansidar2;
    }

    public void setFansidar2(int fansidar2) {
        this.fansidar2 = fansidar2;
    }

    public int getFansidar3() {
        return fansidar3;
    }

    public void setFansidar3(int fansidar3) {
        this.fansidar3 = fansidar3;
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

        ResultSet rs = null;
        int formID = 0;
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

            // Connection conn = ZEPRSUtils.getZEPRSConnection();

            ///////////////////////////////////////////////////////////////////////
            // New Antenatal Attendances
            ///////////////////////////////////////////////////////////////////////
            int count1 = ZEPRSSharedItems.getTotalFirstAnteAttendances(beginDate, endDate, siteID, conn);
            this.setAaNewANC(count1);

            ///////////////////////////////////////////////////////////////////////
            // Old Antenatal Attendances (Reattendances)
            ///////////////////////////////////////////////////////////////////////
            int count2 = ZEPRSSharedItems.getTotalAnteReattendances(beginDate, endDate, siteID, conn);
            this.setAaOldANC(count2);

            ///////////////////////////////////////////////////////////////////////
            // Total Antenatal Attendances
            ///////////////////////////////////////////////////////////////////////
            this.setAaTotalANC(count1 + count2);

            ///////////////////////////////////////////////////////////////////////
            // Postnatal Attendances
            ///////////////////////////////////////////////////////////////////////
            this.setAaPostnatalAtt(ZEPRSSharedItems.getTotalPostAttendances(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // First, Second, and Third Trimester Attendances
            ///////////////////////////////////////////////////////////////////////

            int newAnteFirstTri = 0;
            int newAnteSecondTri = 0;
            int newAnteThirdTri = 0;

            // Get a list of new anc visits and calculate ega for date_visit.
            formID = 77;
            try {
                rs = ZEPRSUtils.getEncounters(formID, "initialvisit ", beginDate, endDate, siteID, conn, true);
                while (rs.next()) {
                    Long patientId = rs.getLong("patient_id");
                    Date datevisit = rs.getDate("date_visit");
                    Long pregnancyId = rs.getLong("pregnancy_id");
                    int egaDays = 0;
                    // grab the value for lmp from the final pregnancyDating record - hopefully the most accurate value
                    try {
                        List pregDatingList = EncountersDAO.getAll(conn, patientId, pregnancyId, (long) 82, PregnancyDating.class);
                        if (pregDatingList != null) {
                        int lastRecord = pregDatingList.size() - 1;
                        PregnancyDating pregnancyDating = (PregnancyDating) pregDatingList.get(lastRecord);
                        Date lmpDate = pregnancyDating.getField127();
                        // get the difference between date visit and lmp date
                        egaDays = Long.valueOf(DateUtils.calculateDays(lmpDate, datevisit)).intValue();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // first trimester - 14 weeks = 98 days
                    if (egaDays <= 98) {
                        newAnteFirstTri++;
                    } else if (egaDays > 98 & egaDays <= 189) {
                        newAnteSecondTri++;
                    } else if (egaDays > 189) {
                        newAnteThirdTri++;
                    }

                    //int weeks= Long.valueOf(egaDays).intValue();
                }
                rs.close();
                this.setNewANCFirstTrimester(newAnteFirstTri);
                this.setNewANCSecondTrimester(newAnteSecondTri);
                this.setNewANCThirdTrimester(newAnteThirdTri);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ///////////////////////////////////////////////////////////////////////
            // Live Births, Still Births, Premature Births, Neonatal Deaths
            ///////////////////////////////////////////////////////////////////////
            this.setBirthsLive(ZEPRSSharedItems.getTotalLiveBirths(beginDate, endDate, siteID, conn));
            this.setStillBirthsFresh(ZEPRSSharedItems.getTotalFreshStillBirths(beginDate, endDate, siteID, conn));
            this.setStillBirthsMacerated(ZEPRSSharedItems.getTotalMaceratedStillBirths(beginDate, endDate, siteID, conn));
            this.setBirthsPremature(ZEPRSSharedItems.getTotalPrematureBirths(beginDate, endDate, siteID, conn));
            this.setDeathsNeonatal(ZEPRSSharedItems.getTotalNeonatalDeaths(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Maternal Deaths
            ///////////////////////////////////////////////////////////////////////
            this.setDeathsMaternal(ZEPRSSharedItems.getTotalMaternalDeaths(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Mothers tested for RPR, Tested Positive, Treated (Test 1)
            ///////////////////////////////////////////////////////////////////////

            this.processRpr(conn, beginDate, endDate, siteID);

            ///////////////////////////////////////////////////////////////////////
            //Total Hb tests performed, % below 10g
            ///////////////////////////////////////////////////////////////////////

            processPuerperium(conn, beginDate, endDate, siteID, this);
            processPostnatalMaternalVisit(conn, beginDate, endDate, siteID, this);
            processLabTests(conn, beginDate, endDate, siteID, this);
            ///////////////////////////////////////////////////////////////////////
            // TT1 - TT5
            ///////////////////////////////////////////////////////////////////////

            processTetanus(conn, beginDate, endDate, siteID, this);

            ///////////////////////////////////////////////////////////////////////
            // Malaria SP1-3 - Fansadar
            ///////////////////////////////////////////////////////////////////////
            processDrugs(conn, beginDate, endDate, siteID, this);

        } catch (Exception e) {
           log.error(e);
        }
    }

    protected void processRpr(Connection conn, Date beginDate, Date endDate, int siteID) {

        int rpr1Total = 0;
        int rpr1Positive = 0;
        int rpr1Treated = 0;
        int rpr2Total = 0;
        int rpr2Positive = 0;
        int rpr2Treated = 0;
        int rprNewborn = 0;

        try {
            // First, get the form id for the RPR form
            int formID = 90;

            // Next, determine how many times the RPR form was submitted for the appropriate time period
            // order by patient

            ResultSet rs = null;
            try {
                String table = "rpr";
                String sql = "SELECT * FROM encounter," + table + " WHERE encounter.id = " + table + ".id " +
                        "AND form_id = ? AND date_visit >= ? AND date_visit <= ? ";
                if (siteID != 0) {
                    sql = sql + "AND site_id = ? ";
                }
                sql = sql + "ORDER BY patient_id";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, Integer.toString(formID));
                ps.setDate(2, beginDate);
                ps.setDate(3, endDate);
                if (siteID != 0) {
                    ps.setInt(4, siteID);
                }
                rs = ps.executeQuery();
            } catch (Exception ex) {
                log.error(ex);
            }

            int count = 0;
            Long patientId = new Long(0);
            while (rs.next()) {
                if (patientId.intValue() != rs.getLong("patient_id")) {
                    count = 1;  // rpr1
                    patientId = rs.getLong("patient_id");
                } else {
                    count = 2;  // rpr2
                }

                int rprResult = rs.getInt("rpr_result");
                int rprTreatment = rs.getInt("rpr_drug");
                // hb1 and hb2
                if ((rprResult == Constants.RPR_RESULT_NONREACTIVE) || (rprResult == Constants.RPR_RESULT_REACTIVE)) {
                    if (count == 1) {
                        rpr1Total++;
                    } else {
                        rpr2Total++;
                    }
                    if (rprResult == Constants.RPR_RESULT_REACTIVE) {
                        if (count == 1) {
                            rpr1Positive++;
                        } else {
                            rpr2Positive++;
                        }
                        if ((rprTreatment == Constants.RPR_TREATMENT_ERYTHROMYCIN) || (rprTreatment == Constants.RPR_TREATMENT_PENICILLIN))
                        {
                            if (count == 1) {
                                rpr1Treated++;
                            } else {
                                rpr2Treated++;
                            }
                        }
                        // Newborns treated born to RPR reactive mothers
                        try {
                            String sql = "SELECT id from patient " +
                                    "WHERE parent_id = ?;";
                            PreparedStatement ps = conn.prepareStatement(sql);
                            ps.setLong(1,Long.valueOf(patientId));
                            ResultSet newbornRs = ps.executeQuery();
                            while(newbornRs.next()) {
                                rprNewborn++;
                            }
                            newbornRs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            this.setRpr1Total(rpr1Total);
            this.setRpr2Total(rpr2Total);
            this.setRpr1TotalPos(rpr1Positive);
            this.setRpr2TotalPos(rpr2Positive);
            this.setRpr1TotalPosTreated(rpr1Treated);
            this.setRpr2TotalPosTreated(rpr2Treated);
            this.setNewbornsTreated(rprNewborn);

            // rs = ZEPRSUtils.getEncounters(formID, "safemotherhood", beginDate, endDate, siteID, "hb2_date", conn);

            rs.close();
        } catch (Exception e) {
            log.error(e);
        }
    }

    protected static void processPuerperium(Connection conn, Date beginDate, Date endDate, int siteID, LUDHMBReport report) {

        int hb = report.getHbTotal();
        int hb10 = report.getHbBelow10g();

        try {
            int formID = 81;
            // determine how many times the form was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "puerperium", beginDate, endDate, siteID, conn, false);
            // loop through all records to count the number fresh still births
            while (rs.next()) {
                if (rs.getInt("hb_235") > 0) {
                    hb++;
                }
                if (rs.getInt("hb_235") < 10) {
                    hb10++;
                }

            }
        } catch (Exception e) {
            log.error(e);
        }

        report.setHbTotal(hb);
        report.setHbBelow10g(hb10);
    }

    protected static void processLabTests(Connection conn, Date beginDate, Date endDate, int siteID, LUDHMBReport report) {

        int hb = report.getHbTotal();
        int hb10 = report.getHbBelow10g();

        try {
            // First, get the form id for the Laboratory Results form
            int formID = 87;

            // Next, determine how many times the Laboratory Results form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "labtest ", beginDate, endDate, siteID, conn, true);

            while (rs.next()) {
                int hbNumeric = rs.getInt("resultsNumeric");
                int labType = rs.getInt("labType");
                // hb1 and hb2
                if ((labType == 2925 || labType == 2926) && hbNumeric != 0) {
                    hb++;
                    if (hbNumeric < 10) {
                        hb10++;
                    }
                }
            }

            // rs = ZEPRSUtils.getEncounters(formID, "safemotherhood", beginDate, endDate, siteID, "hb2_date", conn);

            rs.close();
        } catch (Exception e) {
            // TBD
        }
        report.setHbTotal(hb);
        report.setHbBelow10g(hb10);
    }

    protected static void processPostnatalMaternalVisit(Connection conn, Date beginDate, Date endDate, int siteID, LUDHMBReport report) {

        int hb = report.getHbTotal();
        int hb10 = report.getHbBelow10g();

        try {
            int formID = 28;
            // determine how many times the form was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "postnatalmaternalvisit", beginDate, endDate, siteID, conn, false);
            // loop through all records to count the number fresh still births
            while (rs.next()) {
                int hbCol = rs.getInt("hb_235");
                if (hbCol > 0) {
                    hb++;
                }
                if (hbCol < 10) {
                    hb10++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        report.setHbTotal(hb);
        report.setHbBelow10g(hb10);
    }

    protected static void processTetanus(Connection conn, Date beginDate, Date endDate, int siteID, LUDHMBReport report) {

        try {

            /**
             * tetanus toxoid results
             */
            int formID = 92;
            //ResultSet rs = ZEPRSUtils.getEncounters(patientId, "safeMotherhoodCare ", conn);
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "safemotherhoodcare", beginDate, endDate, siteID, conn, false);

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

    protected static void processDrugs(Connection conn, Date beginDate, Date endDate, int siteID, LUDHMBReport report) {

        int fansidar1 = 0;
        int fansidar2 = 0;
        int fansidar3 = 0;

        try {
            // First, get the form id for the Laboratory Results form
            int formID = 88;

            // Next, determine how many times the Laboratory Results form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "drugintervention ", beginDate, endDate, siteID, conn, true);

            while (rs.next()) {
                int drug1 = rs.getInt("drugType");
                int drug2 = rs.getInt("drug_type2");
                int drug3 = rs.getInt("drug_type3");
                int drug4 = rs.getInt("drug_type4");
                int drug5 = rs.getInt("drug_type5");
                int drug6 = rs.getInt("drug_type6");

                if (drug1 == 2932) {
                    fansidar1++;
                } else if (drug1 == 2979) {
                    fansidar2++;
                } else if (drug1 == 2980) {
                    fansidar3++;
                }
                if (drug2 == 2932) {
                    fansidar1++;
                } else if (drug2 == 2979) {
                    fansidar2++;
                } else if (drug2 == 2980) {
                    fansidar3++;
                }
                if (drug3 == 2932) {
                    fansidar1++;
                } else if (drug3 == 2979) {
                    fansidar2++;
                } else if (drug3 == 2980) {
                    fansidar3++;
                }
                if (drug4 == 2932) {
                    fansidar1++;
                } else if (drug4 == 2979) {
                    fansidar2++;
                } else if (drug4 == 2980) {
                    fansidar3++;
                }
                if (drug5 == 2932) {
                    fansidar1++;
                } else if (drug5 == 2979) {
                    fansidar2++;
                } else if (drug5 == 2980) {
                    fansidar3++;
                }
                if (drug6 == 2932) {
                    fansidar1++;
                } else if (drug6 == 2979) {
                    fansidar2++;
                } else if (drug6 == 2980) {
                    fansidar3++;
                }
            }

            rs.close();
        } catch (Exception e) {
            // TBD
        }
        report.setFansidar1(fansidar1);
        report.setFansidar2(fansidar2);
        report.setFansidar3(fansidar3);
    }
}
