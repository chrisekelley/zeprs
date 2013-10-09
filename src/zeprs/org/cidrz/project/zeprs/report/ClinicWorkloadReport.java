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
 * Created on Mar 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.utils.DynasiteSourceGenerator;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Connection;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ClinicWorkloadReport extends ZEPRSReport {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(ClinicWorkloadReport.class);

    private int admissions;
    private int deliveries;
    private int discharges;

    private int birthsBBA;
    private int birthsBreech;
    private int birthsFacePres;
    private int birthsTwins;
    private int birthsPremature;

    private int deathsNeonatal;

    private int stillBirthsFresh;
    private int stillBirthsMacerated;

    private int offLabour;

    private int ancOld;
    private int ancNew;

    private int hbTotal;
    private int hbBelow10g;

    private int rprTotal;
    private int rprTotalPos;
    private int rprTotalPosTreated;

    private int postFirstWeekVisits;
    private int postSecondWeekVisits;
    private int postThirdWeekVisits;
    private int postFourthWeekVisits;
    private int postFifthWeekVisits;
    private int postSixthWeekVisits;

    private String reportMonth;
    private String reportYear;

    private int totalReferrals;
    private int totalReferralsMother; // B-block
    private int totalReferralsInfant; // D-block

    private int falseLabour;
    private int trueLabor;
    private int ruptureofMembranes;
    private int intactMembranes;
    private int preeclampHypert;
    private int prematureLabour;
    private int malariaDiag;
    private int anaemia;
    private int highbpDiag;
    private int vaginalBleedingBiag;
    private int intrauterineDeath;
    private int utiDiag;
    private int pneumoniaDiag;
    private int tbDiag;
    private int vaginalThrushDiag;
    private int oralThrushDiag;
    private int eclampsia;
    private int abruptiaPlacenta;
    private int miscarriage;
    private int diagother;
    private int brokenepisiotomy;
    private int puerperalsepsis;
    private int breastengorgement;
    private int secondaryPph;
    private int mastitis;
    private int breastAbscess;
    private int bowelObstruction;
    private int indigestion;
    private int opthalmiaNeonatorum;
    private int dehydration;
    private int umbilicalInfection;
    private int diarrhoea;

    private Date beginDate;
    private Date endDate;


    ClinicWorkloadReport() {

        admissions = -1;
        deliveries = -1;
        discharges = -1;

        birthsBBA = -1;
        birthsBreech = -1;
        birthsFacePres = -1;
        birthsPremature = -1;
        birthsTwins = -1;

        deathsNeonatal = -1;

        stillBirthsFresh = -1;
        stillBirthsMacerated = -1;

        offLabour = -1;

        ancOld = -1;
        ancNew = -1;

        hbTotal = 0;
        hbBelow10g = 0;

        rprTotal = 0;
        rprTotalPos = 0;
        rprTotalPosTreated = 0;

        postFirstWeekVisits = 0;
        postSecondWeekVisits = 0;
        postThirdWeekVisits = 0;
        postFourthWeekVisits = 0;
        postFifthWeekVisits = 0;
        postSixthWeekVisits = 0;

        reportMonth = null;
        reportYear = null;
    }

    /**
     * @return Returns the admissions.
     */
    public int getAdmissions() {
        return admissions;
    }

    /**
     * @param admissions The admissions to set.
     */
    public void setAdmissions(int admissions) {
        this.admissions = admissions;
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

    /**
     * @return Returns the birthsBBA.
     */
    public int getBirthsBBA() {
        return birthsBBA;
    }

    /**
     * @param birthsBBA The birthsBBA to set.
     */
    public void setBirthsBBA(int birthsBBA) {
        this.birthsBBA = birthsBBA;
    }

    /**
     * @return Returns the birthsBreech.
     */
    public int getBirthsBreech() {
        return birthsBreech;
    }

    /**
     * @param birthsBreech The birthsBreech to set.
     */
    public void setBirthsBreech(int birthsBreech) {
        this.birthsBreech = birthsBreech;
    }

    /**
     * @return Returns the birthsFacePres.
     */
    public int getBirthsFacePres() {
        return birthsFacePres;
    }

    /**
     * @param birthsFacePres The birthsFacePres to set.
     */
    public void setBirthsFacePres(int birthsFacePres) {
        this.birthsFacePres = birthsFacePres;
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
     * @return Returns the birthsTwins.
     */
    public int getBirthsTwins() {
        return birthsTwins;
    }

    /**
     * @param birthsTwins The birthsTwins to set.
     */
    public void setBirthsTwins(int birthsTwins) {
        this.birthsTwins = birthsTwins;
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
     * @return Returns the deliveries.
     */
    public int getDeliveries() {
        return deliveries;
    }

    /**
     * @param deliveries The deliveries to set.
     */
    public void setDeliveries(int deliveries) {
        this.deliveries = deliveries;
    }

    /**
     * @return Returns the discharges.
     */
    public int getDischarges() {
        return discharges;
    }

    /**
     * @param discharges The discharges to set.
     */
    public void setDischarges(int discharges) {
        this.discharges = discharges;
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
     * @return Returns the offLabour.
     */
    public int getOffLabour() {
        return offLabour;
    }

    /**
     * @param offLabour The offLabour to set.
     */
    public void setOffLabour(int offLabour) {
        this.offLabour = offLabour;
    }

    /**
     * @return Returns the postFirstWeekVisits.
     */
    public int getPostFirstWeekVisits() {
        return postFirstWeekVisits;
    }

    /**
     * @param postFirstWeekVisits The postFirstWeekVisits to set.
     */
    public void setPostFirstWeekVisits(int postFirstWeekVisits) {
        this.postFirstWeekVisits = postFirstWeekVisits;
    }

    public int getPostSecondWeekVisits() {
        return postSecondWeekVisits;
    }

    public void setPostSecondWeekVisits(int postSecondWeekVisits) {
        this.postSecondWeekVisits = postSecondWeekVisits;
    }

    public int getPostThirdWeekVisits() {
        return postThirdWeekVisits;
    }

    public void setPostThirdWeekVisits(int postThirdWeekVisits) {
        this.postThirdWeekVisits = postThirdWeekVisits;
    }

    public int getPostFourthWeekVisits() {
        return postFourthWeekVisits;
    }

    public void setPostFourthWeekVisits(int postFourthWeekVisits) {
        this.postFourthWeekVisits = postFourthWeekVisits;
    }

    public int getPostFifthWeekVisits() {
        return postFifthWeekVisits;
    }

    public void setPostFifthWeekVisits(int postFifthWeekVisits) {
        this.postFifthWeekVisits = postFifthWeekVisits;
    }

    /**
     * @return Returns the postSixthWeekVisits.
     */
    public int getPostSixthWeekVisits() {
        return postSixthWeekVisits;
    }

    /**
     * @param postSixthWeekVisits The postSixthWeekVisits to set.
     */
    public void setPostSixthWeekVisits(int postSixthWeekVisits) {
        this.postSixthWeekVisits = postSixthWeekVisits;
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
     * @return Returns the rprTotal.
     */
    public int getRprTotal() {
        return rprTotal;
    }

    /**
     * @param rprTotal The rprTotal to set.
     */
    public void setRprTotal(int rprTotal) {
        this.rprTotal = rprTotal;
    }

    /**
     * @return Returns the rprTotalPos.
     */
    public int getRprTotalPos() {
        return rprTotalPos;
    }

    /**
     * @param rprTotalPos The rprTotalPos to set.
     */
    public void setRprTotalPos(int rprTotalPos) {
        this.rprTotalPos = rprTotalPos;
    }

    /**
     * @return Returns the rprTotalPosTreated.
     */
    public int getRprTotalPosTreated() {
        return rprTotalPosTreated;
    }

    /**
     * @param rprTotalPosTreated The rprTotalPosTreated to set.
     */
    public void setRprTotalPosTreated(int rprTotalPosTreated) {
        this.rprTotalPosTreated = rprTotalPosTreated;
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

    public int getTotalReferrals() {
        return totalReferrals;
    }

    public void setTotalReferrals(int totalReferrals) {
        this.totalReferrals = totalReferrals;
    }

    public int getTotalReferralsMother() {
        return totalReferralsMother;
    }

    public void setTotalReferralsMother(int totalReferralsMother) {
        this.totalReferralsMother = totalReferralsMother;
    }

    public int getTotalReferralsInfant() {
        return totalReferralsInfant;
    }

    public void setTotalReferralsInfant(int totalReferralsInfant) {
        this.totalReferralsInfant = totalReferralsInfant;
    }

    public int getFalseLabour() {
        return falseLabour;
    }

    public void setFalseLabour(int falseLabour) {
        this.falseLabour = falseLabour;
    }

    public int getTrueLabor() {
        return trueLabor;
    }

    public void setTrueLabor(int trueLabor) {
        this.trueLabor = trueLabor;
    }

    public int getRuptureofMembranes() {
        return ruptureofMembranes;
    }

    public void setRuptureofMembranes(int ruptureofMembranes) {
        this.ruptureofMembranes = ruptureofMembranes;
    }

    public int getIntactMembranes() {
        return intactMembranes;
    }

    public void setIntactMembranes(int intactMembranes) {
        this.intactMembranes = intactMembranes;
    }

    public int getPreeclampHypert() {
        return preeclampHypert;
    }

    public void setPreeclampHypert(int preeclampHypert) {
        this.preeclampHypert = preeclampHypert;
    }

    public int getPrematureLabour() {
        return prematureLabour;
    }

    public void setPrematureLabour(int prematureLabour) {
        this.prematureLabour = prematureLabour;
    }

    public int getMalariaDiag() {
        return malariaDiag;
    }

    public void setMalariaDiag(int malariaDiag) {
        this.malariaDiag = malariaDiag;
    }

    public int getAnaemia() {
        return anaemia;
    }

    public void setAnaemia(int anaemia) {
        this.anaemia = anaemia;
    }

    public int getHighbpDiag() {
        return highbpDiag;
    }

    public void setHighbpDiag(int highbpDiag) {
        this.highbpDiag = highbpDiag;
    }

    public int getVaginalBleedingBiag() {
        return vaginalBleedingBiag;
    }

    public void setVaginalBleedingBiag(int vaginalBleedingBiag) {
        this.vaginalBleedingBiag = vaginalBleedingBiag;
    }

    public int getIntrauterineDeath() {
        return intrauterineDeath;
    }

    public void setIntrauterineDeath(int intrauterineDeath) {
        this.intrauterineDeath = intrauterineDeath;
    }

    public int getUtiDiag() {
        return utiDiag;
    }

    public void setUtiDiag(int utiDiag) {
        this.utiDiag = utiDiag;
    }

    public int getPneumoniaDiag() {
        return pneumoniaDiag;
    }

    public void setPneumoniaDiag(int pneumoniaDiag) {
        this.pneumoniaDiag = pneumoniaDiag;
    }

    public int getTbDiag() {
        return tbDiag;
    }

    public void setTbDiag(int tbDiag) {
        this.tbDiag = tbDiag;
    }

    public int getVaginalThrushDiag() {
        return vaginalThrushDiag;
    }

    public void setVaginalThrushDiag(int vaginalThrushDiag) {
        this.vaginalThrushDiag = vaginalThrushDiag;
    }

    public int getOralThrushDiag() {
        return oralThrushDiag;
    }

    public void setOralThrushDiag(int oralThrushDiag) {
        this.oralThrushDiag = oralThrushDiag;
    }

    public int getEclampsia() {
        return eclampsia;
    }

    public void setEclampsia(int eclampsia) {
        this.eclampsia = eclampsia;
    }

    public int getAbruptiaPlacenta() {
        return abruptiaPlacenta;
    }

    public void setAbruptiaPlacenta(int abruptiaPlacenta) {
        this.abruptiaPlacenta = abruptiaPlacenta;
    }

    public int getMiscarriage() {
        return miscarriage;
    }

    public void setMiscarriage(int miscarriage) {
        this.miscarriage = miscarriage;
    }

    public int getDiagother() {
        return diagother;
    }

    public void setDiagother(int diagother) {
        this.diagother = diagother;
    }

    public int getBrokenepisiotomy() {
        return brokenepisiotomy;
    }

    public void setBrokenepisiotomy(int brokenepisiotomy) {
        this.brokenepisiotomy = brokenepisiotomy;
    }

    public int getPuerperalsepsis() {
        return puerperalsepsis;
    }

    public void setPuerperalsepsis(int puerperalsepsis) {
        this.puerperalsepsis = puerperalsepsis;
    }

    public int getBreastengorgement() {
        return breastengorgement;
    }

    public void setBreastengorgement(int breastengorgement) {
        this.breastengorgement = breastengorgement;
    }

    public int getSecondaryPph() {
        return secondaryPph;
    }

    public void setSecondaryPph(int secondaryPph) {
        this.secondaryPph = secondaryPph;
    }

    public int getMastitis() {
        return mastitis;
    }

    public void setMastitis(int mastitis) {
        this.mastitis = mastitis;
    }

    public int getBreastAbscess() {
        return breastAbscess;
    }

    public void setBreastAbscess(int breastAbscess) {
        this.breastAbscess = breastAbscess;
    }

    public int getBowelObstruction() {
        return bowelObstruction;
    }

    public void setBowelObstruction(int bowelObstruction) {
        this.bowelObstruction = bowelObstruction;
    }

    public int getIndigestion() {
        return indigestion;
    }

    public void setIndigestion(int indigestion) {
        this.indigestion = indigestion;
    }

    public int getOpthalmiaNeonatorum() {
        return opthalmiaNeonatorum;
    }

    public void setOpthalmiaNeonatorum(int opthalmiaNeonatorum) {
        this.opthalmiaNeonatorum = opthalmiaNeonatorum;
    }

    public int getDehydration() {
        return dehydration;
    }

    public void setDehydration(int dehydration) {
        this.dehydration = dehydration;
    }

    public int getUmbilicalInfection() {
        return umbilicalInfection;
    }

    public void setUmbilicalInfection(int umbilicalInfection) {
        this.umbilicalInfection = umbilicalInfection;
    }

    public int getDiarrhoea() {
        return diarrhoea;
    }

    public void setDiarrhoea(int diarrhoea) {
        this.diarrhoea = diarrhoea;
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

            // loop through records for common forms to populate fields
            processNewbornEval(conn, beginDate, endDate, siteID, this);
            processDeliveySummary(conn, beginDate, endDate, siteID, this);
            processPuerperium(conn, beginDate, endDate, siteID, this);
            processPostnatalMaternalVisit(conn, beginDate, endDate, siteID, this);
            processLabTests(conn, beginDate, endDate, siteID, this);
            processInfantVisits(conn, beginDate, endDate, siteID, this);
            processRpr(conn, beginDate, endDate, siteID, this);
            processReferrals(conn, beginDate, endDate, siteID, this);
            ///////////////////////////////////////////////////////////////////////
            // Admissions
            ///////////////////////////////////////////////////////////////////////
          //  this.setAdmissions(ZEPRSSharedItems.getTotalAdmissions(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Deliveries
            ///////////////////////////////////////////////////////////////////////
            this.setDeliveries(ZEPRSSharedItems.getTotalDeliveries(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Discharges
            ///////////////////////////////////////////////////////////////////////
            this.setDischarges(ZEPRSSharedItems.getTotalDischarges(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Total number of referrals -  Trans out to U.T.H.
            ///////////////////////////////////////////////////////////////////////
            this.setTotalReferrals(ZEPRSSharedItems.getTotalReferrals(beginDate, endDate, siteID, conn));
            ///////////////////////////////////////////////////////////////////////
            // Mothers referred to UTH - B Block
            ///////////////////////////////////////////////////////////////////////

            ///////////////////////////////////////////////////////////////////////
            // Infants  referred to UTH - D Block
            ///////////////////////////////////////////////////////////////////////

            ///////////////////////////////////////////////////////////////////////
            // BBA Births
            ///////////////////////////////////////////////////////////////////////
            // this.setBirthsBBA(ZEPRSSharedItems.getTotalBBABirths(beginDate, endDate, siteID, conn));
            ///////////////////////////////////////////////////////////////////////
            // Breech Births
            ///////////////////////////////////////////////////////////////////////
            //this.setBirthsBreech(ZEPRSSharedItems.getTotalBreechBirths(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Face Presentation Births
            ///////////////////////////////////////////////////////////////////////

            ///////////////////////////////////////////////////////////////////////
            // Twins
            ///////////////////////////////////////////////////////////////////////
            /**
             * Loop through patients and get the number of fetuses delivered
             * if >1, increment
             */
            int twins = 0;
            rs = ZEPRSUtils.getUniqueVisits(beginDate, endDate, siteID, conn);

            // For each patient, load the report class and add this patient
            // to the list
            // log.debug("Number of patients: " + rs.getFetchSize());
            while (rs.next()) {
                int patientID = rs.getInt("patient_id");
                ResultSet children = ZEPRSUtils.getChildrenCount(patientID, conn);
                // Move to the end of the result set
                children.next();
                // Get the row number of the last row which is also the row count
                int rowCount = children.getInt(1);
                if (rowCount > 1) {
                    twins++;
                }
            }
            this.setBirthsTwins(twins);


            ///////////////////////////////////////////////////////////////////////
            // Premature Births
            ///////////////////////////////////////////////////////////////////////
            //this.setBirthsPremature(ZEPRSSharedItems.getTotalPrematureBirths(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Neonatal Deaths -  death of a live-born infant within the first 28 days of life (dictionary.com)
            ///////////////////////////////////////////////////////////////////////
            /**
             * Loop through postnatal infant visits
             */
            // this.setDeathsNeonatal(ZEPRSSharedItems.getTotalNeonatalDeaths(beginDate, endDate, siteID, conn));
            // using processInfantVisits()

            ///////////////////////////////////////////////////////////////////////
            // Macerated Still Births
            ///////////////////////////////////////////////////////////////////////
           // this.setStillBirthsMacerated(ZEPRSSharedItems.getTotalMaceratedStillBirths(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Fresh Still Births
            ///////////////////////////////////////////////////////////////////////
           // this.setStillBirthsFresh(ZEPRSSharedItems.getTotalFreshStillBirths(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Off Labour
            ///////////////////////////////////////////////////////////////////////

            /**
             * 9/19/2005: Need to be able to enter BBA (Born Before Arrival) under Create newborn record in Postnatal/delivery summary
             * Must add new field to form...
             */

            ///////////////////////////////////////////////////////////////////////
            // ANC Old
            ///////////////////////////////////////////////////////////////////////
            this.setAncOld(ZEPRSSharedItems.getTotalAnteReattendances(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // ANC New
            ///////////////////////////////////////////////////////////////////////
            this.setAncNew(ZEPRSSharedItems.getTotalFirstAnteAttendances(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // HB
            ///////////////////////////////////////////////////////////////////////
           // this.setHbTotal(ZEPRSSharedItems.getTotalHBTests(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // HB Below 10g
            ///////////////////////////////////////////////////////////////////////
           // this.setHbBelow10g(ZEPRSSharedItems.getTotalHBBelow10g(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // RPR
            ///////////////////////////////////////////////////////////////////////
            //this.setRprTotal(ZEPRSSharedItems.getTotalRPRTests(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Positive RPR
            ///////////////////////////////////////////////////////////////////////
            //this.setRprTotalPos(ZEPRSSharedItems.getTotalRPRPositive(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Treated RPR
            ///////////////////////////////////////////////////////////////////////
            // this.setRprTotalPosTreated(ZEPRSSharedItems.getTotalRPRPositiveTreated(beginDate, endDate, siteID, conn));

            ///////////////////////////////////////////////////////////////////////
            // Postnatal first week visits
            ///////////////////////////////////////////////////////////////////////

            ///////////////////////////////////////////////////////////////////////
            // Postnatal sixth week visits
            ///////////////////////////////////////////////////////////////////////


         conn.close();

        } catch (Exception e) {
            // e.printStackTrace();
            log.error("Error running the report: " + e.getMessage());
        }
    }

    public static void processDeliveySummary(Connection conn, Date beginDate, Date endDate, int siteID, ClinicWorkloadReport report) {

       int breech = 0;
       //int spont = 0;

       try {
           // First, get the form id for the delivery summary form
           int formID = 66;
           // Next, process the records for the items you need to count
           ResultSet rs = ZEPRSUtils.getEncounters(formID, "deliverysum", beginDate, endDate, siteID, conn, false);

           // loop through all records to count the number assisted breech delivery
           while (rs.next()) {
               if (rs.getInt("mode_of_delivery_447") == Constants.ASSISTED_BREECH_DELIVERY) {
                   breech++;
               }
           }
           /*while (rs.next()) {
               if (rs.getInt("mode_of_delivery_447") == Constants.SPONTANEOUS_VAGINAL) {
                   spont++;
               }
           }*/
       } catch (Exception e) {
           log.error(e);
       }
       report.setBirthsBreech(breech);

    }

    protected static void processNewbornEval(Connection conn, Date beginDate, Date endDate, int siteID, ClinicWorkloadReport report) {

        int fsb = 0;
        int msb = 0;
        int premature = 0;
        int neonatal = 0;
        int bba = 0;

        try {
            // First, get the form id for the Newborn Evaluation form
            // int formID = ZEPRSUtils.getFormID("Newborn Evaluation");
            int formID = 23;

            // Next, determine how many times the Newborn Evaluation form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "newborneval", beginDate, endDate, siteID, conn, true);

            // loop through all records to count the number fresh still births
            while (rs.next()) {
                if (rs.getInt("alive_sb_493") == Constants.FRESH_STILL_BIRTH) {
                    fsb++;
                }
                if (rs.getInt("alive_sb_493") == Constants.MACERATED_STILL_BIRTH) {
                    msb++;
                }
                 if (rs.getInt("if_premature_num_weeks_gest_488") != 0) {
                    premature++;
                }
                 if (rs.getBoolean("neonatal_dea_1180")) {
                    neonatal++;
                }
                if (rs.getBoolean("born_at_home")) {
                    bba++;
                }
            }
        } catch (Exception e) {
            log.error(e);
        }

        report.setStillBirthsFresh(fsb);
        report.setStillBirthsMacerated(msb);
        report.setBirthsPremature(premature);
        report.setDeathsNeonatal(neonatal);
        report.setBirthsBBA(bba);
    }

    protected static void processPuerperium(Connection conn, Date beginDate, Date endDate, int siteID, ClinicWorkloadReport report) {

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

    protected static void processPostnatalMaternalVisit(Connection conn, Date beginDate, Date endDate, int siteID, ClinicWorkloadReport report) {

        int hb = report.getHbTotal();
        int hb10 = report.getHbBelow10g();
        int visit1week = 0;
        int visit2week = 0;
        int visit3week = 0;
        int visit4week = 0;
        int visit5week = 0;
        int visit6week = 0;

        try {
            int formID = 28;
            // determine how many times the form was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "postnatalmaternalvisit", beginDate, endDate, siteID, conn, false);
            // loop through all records to count the number fresh still births
            while (rs.next()) {
                int hbCol = rs.getInt("hb_235");
                int visit = rs.getInt("postnatal_visit_601");
                if (hbCol > 0) {
                    hb++;
                }
                if (hbCol < 10) {
                    hb10++;
                }
                switch (visit) {
                    case 339:
                        visit1week++;
                        break;
                    case 2681:
                        visit2week++;
                        break;
                    case 2682:
                        visit3week++;
                        break;
                    case 2683:
                        visit4week++;
                        break;
                    case 2684:
                        visit5week++;
                        break;
                    case 870:
                        visit6week++;
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        report.setHbTotal(hb);
        report.setHbBelow10g(hb10);
        report.setPostFirstWeekVisits(visit1week);
        report.setPostSecondWeekVisits(visit2week);
        report.setPostThirdWeekVisits(visit3week);
        report.setPostFourthWeekVisits(visit4week);
        report.setPostFifthWeekVisits(visit5week);
        report.setPostFirstWeekVisits(visit6week);
    }

    protected static void processLabTests(Connection conn, Date beginDate, Date endDate, int siteID, ClinicWorkloadReport report) {

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

    public static void processInfantVisits(Connection conn, Date beginDate, Date endDate, int siteID, ClinicWorkloadReport report) {

        int death = 0;
        //int spont = 0;

        try {
            // First, get the form id for the postnatal infant visit form
            int formID = 86;
            // Next, process the records for the items you need to count
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "postnatalinfant", beginDate, endDate, siteID, conn, false);

            // loop through all records to count the number assisted breech delivery
            while (rs.next()) {
                if (rs.getInt("infant_status") == Constants.POSTNATAL_INFANT_DEATH) {
                    death++;
                }
            }

        } catch (Exception e) {
            log.error(e);
        }
        try {
            // First, get the form id for the postnatal infant visit form
            int formID = 32;
            // Next, process the records for the items you need to count
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "probpostnatalinfant", beginDate, endDate, siteID, conn, false);

            // loop through all records to count the number assisted breech delivery
            while (rs.next()) {
                if (rs.getInt("infant_status") == Constants.POSTNATAL_INFANT_DEATH) {
                    death++;
                }
            }

        } catch (Exception e) {
            log.error(e);
        }
        report.setDeathsNeonatal(death);
    }

    protected static void processRpr(Connection conn, Date beginDate, Date endDate, int siteID, ClinicWorkloadReport report) {

        int rprTotal = 0;
        int rprPositive = 0;
        int rprTreated = 0;

        try {
            // First, get the form id for the RPR form
            int formID = 90;

            // Next, determine how many times the RPR form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "rpr ", beginDate, endDate, siteID, conn, true);

            while (rs.next()) {
                int rprResult = rs.getInt("rpr_result");
                int rprTreatment = rs.getInt("rpr_drug");
                // hb1 and hb2
                if ((rprResult == Constants.RPR_RESULT_NONREACTIVE) || (rprResult == Constants.RPR_RESULT_REACTIVE)) {
                    rprTotal++;
                    if (rprResult == Constants.RPR_RESULT_REACTIVE) {
                        rprPositive++;
                        if ((rprTreatment == Constants.RPR_TREATMENT_ERYTHROMYCIN) || (rprTreatment == Constants.RPR_TREATMENT_PENICILLIN))
                        {
                            rprTreated++;
                        }
                    }
                }
            }

            // rs = ZEPRSUtils.getEncounters(formID, "safemotherhood", beginDate, endDate, siteID, "hb2_date", conn);

            rs.close();
        } catch (Exception e) {
            // TBD
        }
        report.setRprTotal(rprTotal);
        report.setRprTotalPos(rprPositive);
        report.setRprTotalPosTreated(rprTreated);
    }

    protected void processReferrals(Connection conn, Date beginDate, Date endDate, int siteID, ClinicWorkloadReport report) {

        falseLabour = 0;
        trueLabor = 0;
        ruptureofMembranes = 0;
        intactMembranes = 0;
        preeclampHypert = 0;
        prematureLabour = 0;
        malariaDiag = 0;
        anaemia = 0;
        highbpDiag = 0;
        vaginalBleedingBiag = 0;
        intrauterineDeath = 0;
        utiDiag = 0;
        pneumoniaDiag = 0;
        tbDiag = 0;
        vaginalThrushDiag = 0;
        oralThrushDiag = 0;
        eclampsia = 0;
        abruptiaPlacenta = 0;
        miscarriage = 0;
        diagother = 0;
        brokenepisiotomy = 0;
        puerperalsepsis = 0;
        breastengorgement = 0;
        secondaryPph = 0;
        mastitis = 0;
        breastAbscess = 0;
        bowelObstruction = 0;
        indigestion = 0;
        opthalmiaNeonatorum = 0;
        dehydration = 0;
        umbilicalInfection = 0;
        diarrhoea = 0;


        try {
            // First, get the form id for the RPR form
            int formID = 94;

            // Next, determine how many times the RPR form
            // was submitted for the appropriate time period
            ResultSet rs = ZEPRSUtils.getEncounters(formID, "referral_reasons ", beginDate, endDate, siteID, conn, true);

            while (rs.next()) {
                if (rs.getInt("false_labour") == 1) {
                    falseLabour++;
                }
                if (rs.getInt("true_labor") == 1) {
                    trueLabor++;
                }
                if (rs.getInt("rupture_of_membranes") == 1) {
                    ruptureofMembranes++;
                }
                if (rs.getInt("intact_membranes") == 1) {
                    intactMembranes++;
                }
                if (rs.getInt("preeclamp_hypert_1265") == 1) {
                    preeclampHypert++;
                }
                if (rs.getInt("premature_labour") == 1) {
                    prematureLabour++;
                }
                if (rs.getInt("malaria_diag") == 1) {
                    malariaDiag++;
                }
                if (rs.getInt("anaemia") == 1) {
                    anaemia++;
                }
                if (rs.getInt("high_bp_diag") == 1) {
                    highbpDiag++;
                }
                if (rs.getInt("vaginal_bleeding_diag") == 1) {
                    vaginalBleedingBiag++;
                }
                if (rs.getInt("intrauterine_death") == 1) {
                    intrauterineDeath++;
                }
                if (rs.getInt("uti_diag") == 1) {
                    utiDiag++;
                }
                if (rs.getInt("pneumonia_diag") == 1) {
                    pneumoniaDiag++;
                }
                if (rs.getInt("tb_diag") == 1) {
                    tbDiag++;
                }
                if (rs.getInt("vaginal_thrush_diag") == 1) {
                    vaginalThrushDiag++;
                }
                if (rs.getInt("oral_thrush_diag") == 1) {
                    oralThrushDiag++;
                }
                if (rs.getInt("eclampsia") == 1) {
                    eclampsia++;
                }
                if (rs.getInt("abruptia_placenta") == 1) {
                    abruptiaPlacenta++;
                }
                if (rs.getInt("miscarriage") == 1) {
                    miscarriage++;
                }
                if (rs.getString("diag_other") != null) {
                    diagother++;
                }
                if (rs.getInt("broken_episiotomy") == 1) {
                    brokenepisiotomy++;
                }
                if (rs.getInt("puerperal_sepsis") == 1) {
                    puerperalsepsis++;
                }
                if (rs.getInt("breast_engorgement") == 1) {
                    breastengorgement++;
                }
                if (rs.getInt("secondary_pph") == 1) {
                    secondaryPph++;
                }
                if (rs.getInt("mastitis") == 1) {
                    mastitis++;
                }
                if (rs.getInt("breast_abscess") == 1) {
                    breastAbscess++;
                }
                if (rs.getInt("bowel_obstruction") == 1) {
                    bowelObstruction++;
                }
                if (rs.getInt("indigestion") == 1) {
                    indigestion++;
                }
                if (rs.getInt("opthalmia_neonatorum") == 1) {
                    opthalmiaNeonatorum++;
                }
                if (rs.getInt("dehydration") == 1) {
                    dehydration++;
                }
                if (rs.getInt("umbilical_infection") == 1) {
                    umbilicalInfection++;
                }
                if (rs.getInt("diarrhoea") == 1) {
                    diarrhoea++;
                }
            }
            // rs = ZEPRSUtils.getEncounters(formID, "safemotherhood", beginDate, endDate, siteID, "hb2_date", conn);

            rs.close();
        } catch (Exception e) {
            // TBD
        }
    }

}
