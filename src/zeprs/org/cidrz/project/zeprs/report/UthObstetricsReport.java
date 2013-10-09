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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.ReferralDAO;
import org.cidrz.webapp.dynasite.valueobject.Patient;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class UthObstetricsReport extends ZEPRSReport {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(UthObstetricsReport.class);

    private int aaPostnatalAtt;

    private int birthsLive;

    private String reportMonth;
    private String reportYear;

    private int labourWardAdmissions;
    private int totalDeliveries;
    private int spontaneousVertexDeliveries;
    private String spontaneousVertexDeliveriesTotalDeliveriesCalc;
    private int breechDeliveries;
    private String breechDeliveriesTotalDeliveriesCalc;
    private int forcepsDeliveries;
    private String forcepsDeliveriesTotalDeliveriesCalc;
    private int vacuumExtractionDeliveries;
    private String vacuumExtractionDeliveriesTotalDeliveriesCalc;
    private int caesarianSectionDeliveries;
    private String caesarianSectionDeliveriesTotalDeliveriesCalc;
    private int facePresentationDeliveries;
    private String facePresentationDeliveriesTotalDeliveriesCalc;
    private int maternalDeaths;
    private String maternalDeathsTotalDeliveriesCalc;
    private int bBAs;
    private String bBAsTotalDeliveriesCalc;
    private int referredCases;
    private String referredCasesTotalDeliveriesCalc;
    private int rupturedUterus;
    private String rupturedUterusTotalDeliveriesCalc;
    private int postnatalAttendances;
    private int ttVaccinations;
    private int freshStillbirthsFemale;
    private int freshStillbirthsMale;
    private int freshStillbirthsTotal;
    private int maceratedStillbirthsFemale;
    private int maceratedStillbirthsMale;
    private int maceratedStillbirthsTotal;

    private int liveBirthsFemale;
    private int liveBirthsMale;
    private int liveBirthsTotal;
    private int neonatalDeathsFemale;
    private int neonatalDeathsMale;
    private int neonatalDeathsTotal;
    private int prematureLiveBirthsFemale;
    private int prematureLiveBirthsMale;
    private int prematureLiveBirthsTotal;
    private int stillBirthsFemale;
    private int stillBirthsMale;
    private int stillBirthsTotal;
    private int totalInfantsBornFemale;
    private int totalInfantsBornMale;
    private int totalInfantsBornTotal;
    private int tripletsFemale;
    private int tripletsMale;
    private int tripletsTotal;
    private int twinsFemale;
    private int twinsMale;
    private int twinsTotal;
    private int underweightBabiesFemale;
    private int underweightBabiesMale;
    private int underweightBabiesTotal;
    private int bCG;
    private int oPV0;
    private Date beginDate;
    private Date endDate;

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

    public int getLabourWardAdmissions() {
        return labourWardAdmissions;
    }

    public void setLabourWardAdmissions(int labourWardAdmissions) {
        this.labourWardAdmissions = labourWardAdmissions;
    }

    public int getTotalDeliveries() {
        return totalDeliveries;
    }

    public void setTotalDeliveries(int totalDeliveries) {
        this.totalDeliveries = totalDeliveries;
    }

    public int getSpontaneousVertexDeliveries() {
        return spontaneousVertexDeliveries;
    }

    public void setSpontaneousVertexDeliveries(int spontaneousVertexDeliveries) {
        this.spontaneousVertexDeliveries = spontaneousVertexDeliveries;
    }

    public int getBreechDeliveries() {
        return breechDeliveries;
    }

    public void setBreechDeliveries(int breechDeliveries) {
        this.breechDeliveries = breechDeliveries;
    }


    public int getForcepsDeliveries() {
        return forcepsDeliveries;
    }

    public void setForcepsDeliveries(int forcepsDeliveries) {
        this.forcepsDeliveries = forcepsDeliveries;
    }


    public int getVacuumExtractionDeliveries() {
        return vacuumExtractionDeliveries;
    }

    public void setVacuumExtractionDeliveries(int vacuumExtractionDeliveries) {
        this.vacuumExtractionDeliveries = vacuumExtractionDeliveries;
    }


    public int getCaesarianSectionDeliveries() {
        return caesarianSectionDeliveries;
    }

    public void setCaesarianSectionDeliveries(int caesarianSectionDeliveries) {
        this.caesarianSectionDeliveries = caesarianSectionDeliveries;
    }

    public int getFacePresentationDeliveries() {
        return facePresentationDeliveries;
    }

    public void setFacePresentationDeliveries(int facePresentationDeliveries) {
        this.facePresentationDeliveries = facePresentationDeliveries;
    }

    public int getMaternalDeaths() {
        return maternalDeaths;
    }

    public void setMaternalDeaths(int maternalDeaths) {
        this.maternalDeaths = maternalDeaths;
    }


    public int getbBAs() {
        return bBAs;
    }

    public void setbBAs(int bBAs) {
        this.bBAs = bBAs;
    }

    public int getReferredCases() {
        return referredCases;
    }

    public void setReferredCases(int referredCases) {
        this.referredCases = referredCases;
    }


    public int getRupturedUterus() {
        return rupturedUterus;
    }

    public void setRupturedUterus(int rupturedUterus) {
        this.rupturedUterus = rupturedUterus;
    }

    public String getSpontaneousVertexDeliveriesTotalDeliveriesCalc() {
        return spontaneousVertexDeliveriesTotalDeliveriesCalc;
    }

    public void setSpontaneousVertexDeliveriesTotalDeliveriesCalc(String spontaneousVertexDeliveriesTotalDeliveriesCalc) {
        this.spontaneousVertexDeliveriesTotalDeliveriesCalc = spontaneousVertexDeliveriesTotalDeliveriesCalc;
    }

    public String getBreechDeliveriesTotalDeliveriesCalc() {
        return breechDeliveriesTotalDeliveriesCalc;
    }

    public void setBreechDeliveriesTotalDeliveriesCalc(String breechDeliveriesTotalDeliveriesCalc) {
        this.breechDeliveriesTotalDeliveriesCalc = breechDeliveriesTotalDeliveriesCalc;
    }

    public String getForcepsDeliveriesTotalDeliveriesCalc() {
        return forcepsDeliveriesTotalDeliveriesCalc;
    }

    public void setForcepsDeliveriesTotalDeliveriesCalc(String forcepsDeliveriesTotalDeliveriesCalc) {
        this.forcepsDeliveriesTotalDeliveriesCalc = forcepsDeliveriesTotalDeliveriesCalc;
    }

    public String getVacuumExtractionDeliveriesTotalDeliveriesCalc() {
        return vacuumExtractionDeliveriesTotalDeliveriesCalc;
    }

    public void setVacuumExtractionDeliveriesTotalDeliveriesCalc(String vacuumExtractionDeliveriesTotalDeliveriesCalc) {
        this.vacuumExtractionDeliveriesTotalDeliveriesCalc = vacuumExtractionDeliveriesTotalDeliveriesCalc;
    }

    public String getCaesarianSectionDeliveriesTotalDeliveriesCalc() {
        return caesarianSectionDeliveriesTotalDeliveriesCalc;
    }

    public void setCaesarianSectionDeliveriesTotalDeliveriesCalc(String caesarianSectionDeliveriesTotalDeliveriesCalc) {
        this.caesarianSectionDeliveriesTotalDeliveriesCalc = caesarianSectionDeliveriesTotalDeliveriesCalc;
    }

    public String getFacePresentationDeliveriesTotalDeliveriesCalc() {
        return facePresentationDeliveriesTotalDeliveriesCalc;
    }

    public void setFacePresentationDeliveriesTotalDeliveriesCalc(String facePresentationDeliveriesTotalDeliveriesCalc) {
        this.facePresentationDeliveriesTotalDeliveriesCalc = facePresentationDeliveriesTotalDeliveriesCalc;
    }

    public String getMaternalDeathsTotalDeliveriesCalc() {
        return maternalDeathsTotalDeliveriesCalc;
    }

    public void setMaternalDeathsTotalDeliveriesCalc(String maternalDeathsTotalDeliveriesCalc) {
        this.maternalDeathsTotalDeliveriesCalc = maternalDeathsTotalDeliveriesCalc;
    }

    public String getbBAsTotalDeliveriesCalc() {
        return bBAsTotalDeliveriesCalc;
    }

    public void setbBAsTotalDeliveriesCalc(String bBAsTotalDeliveriesCalc) {
        this.bBAsTotalDeliveriesCalc = bBAsTotalDeliveriesCalc;
    }

    public String getReferredCasesTotalDeliveriesCalc() {
        return referredCasesTotalDeliveriesCalc;
    }

    public void setReferredCasesTotalDeliveriesCalc(String referredCasesTotalDeliveriesCalc) {
        this.referredCasesTotalDeliveriesCalc = referredCasesTotalDeliveriesCalc;
    }

    public String getRupturedUterusTotalDeliveriesCalc() {
        return rupturedUterusTotalDeliveriesCalc;
    }

    public void setRupturedUterusTotalDeliveriesCalc(String rupturedUterusTotalDeliveriesCalc) {
        this.rupturedUterusTotalDeliveriesCalc = rupturedUterusTotalDeliveriesCalc;
    }

    public int getPostnatalAttendances() {
        return postnatalAttendances;
    }

    public void setPostnatalAttendances(int postnatalAttendances) {
        this.postnatalAttendances = postnatalAttendances;
    }

    public int getTtVaccinations() {
        return ttVaccinations;
    }

    public void setTtVaccinations(int ttVaccinations) {
        this.ttVaccinations = ttVaccinations;
    }

    public int getFreshStillbirthsFemale() {
        return freshStillbirthsFemale;
    }

    public void setFreshStillbirthsFemale(int freshStillbirthsFemale) {
        this.freshStillbirthsFemale = freshStillbirthsFemale;
    }

    public int getFreshStillbirthsMale() {
        return freshStillbirthsMale;
    }

    public void setFreshStillbirthsMale(int freshStillbirthsMale) {
        this.freshStillbirthsMale = freshStillbirthsMale;
    }

    public int getFreshStillbirthsTotal() {
        return freshStillbirthsTotal;
    }

    public void setFreshStillbirthsTotal(int freshStillbirthsTotal) {
        this.freshStillbirthsTotal = freshStillbirthsTotal;
    }

    public int getLiveBirthsFemale() {
        return liveBirthsFemale;
    }

    public void setLiveBirthsFemale(int liveBirthsFemale) {
        this.liveBirthsFemale = liveBirthsFemale;
    }

    public int getLiveBirthsMale() {
        return liveBirthsMale;
    }

    public void setLiveBirthsMale(int liveBirthsMale) {
        this.liveBirthsMale = liveBirthsMale;
    }

    public int getLiveBirthsTotal() {
        return liveBirthsTotal;
    }

    public void setLiveBirthsTotal(int liveBirthsTotal) {
        this.liveBirthsTotal = liveBirthsTotal;
    }

    public int getNeonatalDeathsFemale() {
        return neonatalDeathsFemale;
    }

    public void setNeonatalDeathsFemale(int neonatalDeathsFemale) {
        this.neonatalDeathsFemale = neonatalDeathsFemale;
    }

    public int getNeonatalDeathsMale() {
        return neonatalDeathsMale;
    }

    public void setNeonatalDeathsMale(int neonatalDeathsMale) {
        this.neonatalDeathsMale = neonatalDeathsMale;
    }

    public int getNeonatalDeathsTotal() {
        return neonatalDeathsTotal;
    }

    public void setNeonatalDeathsTotal(int neonatalDeathsTotal) {
        this.neonatalDeathsTotal = neonatalDeathsTotal;
    }

    public int getPrematureLiveBirthsFemale() {
        return prematureLiveBirthsFemale;
    }

    public void setPrematureLiveBirthsFemale(int prematureLiveBirthsFemale) {
        this.prematureLiveBirthsFemale = prematureLiveBirthsFemale;
    }

    public int getPrematureLiveBirthsMale() {
        return prematureLiveBirthsMale;
    }

    public void setPrematureLiveBirthsMale(int prematureLiveBirthsMale) {
        this.prematureLiveBirthsMale = prematureLiveBirthsMale;
    }

    public int getPrematureLiveBirthsTotal() {
        return prematureLiveBirthsTotal;
    }

    public void setPrematureLiveBirthsTotal(int prematureLiveBirthsTotal) {
        this.prematureLiveBirthsTotal = prematureLiveBirthsTotal;
    }

    public int getStillBirthsFemale() {
        return stillBirthsFemale;
    }

    public void setStillBirthsFemale(int stillBirthsFemale) {
        this.stillBirthsFemale = stillBirthsFemale;
    }

    public int getStillBirthsMale() {
        return stillBirthsMale;
    }

    public void setStillBirthsMale(int stillBirthsMale) {
        this.stillBirthsMale = stillBirthsMale;
    }

    public int getStillBirthsTotal() {
        return stillBirthsTotal;
    }

    public void setStillBirthsTotal(int stillBirthsTotal) {
        this.stillBirthsTotal = stillBirthsTotal;
    }

    public int getTotalInfantsBornFemale() {
        return totalInfantsBornFemale;
    }

    public void setTotalInfantsBornFemale(int totalInfantsBornFemale) {
        this.totalInfantsBornFemale = totalInfantsBornFemale;
    }

    public int getTotalInfantsBornMale() {
        return totalInfantsBornMale;
    }

    public void setTotalInfantsBornMale(int totalInfantsBornMale) {
        this.totalInfantsBornMale = totalInfantsBornMale;
    }

    public int getTotalInfantsBornTotal() {
        return totalInfantsBornTotal;
    }

    public void setTotalInfantsBornTotal(int totalInfantsBornTotal) {
        this.totalInfantsBornTotal = totalInfantsBornTotal;
    }

    public int getTripletsFemale() {
        return tripletsFemale;
    }

    public void setTripletsFemale(int tripletsFemale) {
        this.tripletsFemale = tripletsFemale;
    }

    public int getTripletsMale() {
        return tripletsMale;
    }

    public void setTripletsMale(int tripletsMale) {
        this.tripletsMale = tripletsMale;
    }

    public int getTripletsTotal() {
        return tripletsTotal;
    }

    public void setTripletsTotal(int tripletsTotal) {
        this.tripletsTotal = tripletsTotal;
    }

    public int getTwinsFemale() {
        return twinsFemale;
    }

    public void setTwinsFemale(int twinsFemale) {
        this.twinsFemale = twinsFemale;
    }

    public int getTwinsMale() {
        return twinsMale;
    }

    public void setTwinsMale(int twinsMale) {
        this.twinsMale = twinsMale;
    }

    public int getTwinsTotal() {
        return twinsTotal;
    }

    public void setTwinsTotal(int twinsTotal) {
        this.twinsTotal = twinsTotal;
    }

    public int getUnderweightBabiesFemale() {
        return underweightBabiesFemale;
    }

    public void setUnderweightBabiesFemale(int underweightBabiesFemale) {
        this.underweightBabiesFemale = underweightBabiesFemale;
    }

    public int getUnderweightBabiesMale() {
        return underweightBabiesMale;
    }

    public void setUnderweightBabiesMale(int underweightBabiesMale) {
        this.underweightBabiesMale = underweightBabiesMale;
    }

    public int getUnderweightBabiesTotal() {
        return underweightBabiesTotal;
    }

    public void setUnderweightBabiesTotal(int underweightBabiesTotal) {
        this.underweightBabiesTotal = underweightBabiesTotal;
    }

    public int getbCG() {
        return bCG;
    }

    public void setbCG(int bCG) {
        this.bCG = bCG;
    }

    public int getoPV0() {
        return oPV0;
    }

    public void setoPV0(int oPV0) {
        this.oPV0 = oPV0;
    }

    public int getMaceratedStillbirthsFemale() {
        return maceratedStillbirthsFemale;
    }

    public void setMaceratedStillbirthsFemale(int maceratedStillbirthsFemale) {
        this.maceratedStillbirthsFemale = maceratedStillbirthsFemale;
    }

    public int getMaceratedStillbirthsMale() {
        return maceratedStillbirthsMale;
    }

    public void setMaceratedStillbirthsMale(int maceratedStillbirthsMale) {
        this.maceratedStillbirthsMale = maceratedStillbirthsMale;
    }

    public int getMaceratedStillbirthsTotal() {
        return maceratedStillbirthsTotal;
    }

    public void setMaceratedStillbirthsTotal(int maceratedStillbirthsTotal) {
        this.maceratedStillbirthsTotal = maceratedStillbirthsTotal;
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
        DecimalFormat df = new DecimalFormat("##.##%");
        try {
            // setup datasource
            Connection conn = null;
            conn = ZEPRSUtils.getZEPRSConnection();

            ///////////////////////////////////////////////////////////////////////
            // Total Deliveries
            ///////////////////////////////////////////////////////////////////////
            try {
                int deliveries = 0;
                int breech = 0;
                int spont = 0;
                int forceps = 0;
                int vacuum = 0;
                int caesarian = 0;
                int tripletsFemale = 0;
                int tripletsMale = 0;
                int tripletsTotal = 0;
                int twinsFemale = 0;
                int twinsMale = 0;
                int twinsTotal = 0;

                //formID = 66;
                rs = ZEPRSUtils.getEncountersUth("deliverysum ", beginDate, endDate, conn, true);
                while (rs.next()) {
                    Long patientId = rs.getLong("patient_id");
                    Long pregnancyId = rs.getLong("pregnancy_id");
                    deliveries++;
                    int modeOfDelivery = rs.getInt("mode_of_delivery_447");
                    // loop through all records to count the number assisted breech delivery
                    if (modeOfDelivery == Constants.ASSISTED_BREECH_DELIVERY) {
                        breech++;
                    } else if (modeOfDelivery == Constants.SPONTANEOUS_VAGINAL) {
                        spont++;
                    } else if (modeOfDelivery == Constants.FORCEPS_DELIVERY) {
                        forceps++;
                    } else if (modeOfDelivery == Constants.VACUUMN_DELIVERY) {
                        vacuum++;
                    } else if (modeOfDelivery == Constants.CESAREAN_DELIVERY) {
                        caesarian++;
                    }

                    // see number of newborns
                    List children = PatientDAO.getChildren(conn, patientId, pregnancyId);
                    int sex = 0;
                    for (int i = 0; i < children.size(); i++) {
                        Patient child = (Patient) children.get(i);
                        sex = child.getSex();
                    }
                    if (children.size() == 2) {
                        twinsTotal++;
                        if (sex == 1) {
                            twinsFemale++;
                        } else {
                            twinsMale++;
                        }
                    }
                    if (children.size() == 3) {
                        tripletsTotal++;
                        if (sex == 1) {
                            tripletsFemale++;
                        } else {
                            tripletsMale++;
                        }
                    }
                }
                this.setTotalDeliveries(deliveries);
                this.setBreechDeliveries(breech);
                this.setSpontaneousVertexDeliveries(spont);
                this.setForcepsDeliveries(forceps);
                this.setVacuumExtractionDeliveries(vacuum);
                this.setCaesarianSectionDeliveries(caesarian);
                this.setTwinsFemale(twinsFemale);
                this.setTwinsMale(twinsMale);
                this.setTwinsTotal(twinsTotal);
                this.setTripletsFemale(tripletsFemale);
                this.setTripletsMale(tripletsMale);
                this.setTripletsTotal(tripletsTotal);

                if (this.getTotalDeliveries() > 0) {
                    if (this.getBreechDeliveries() > 0) {
                        double breechDeliveriesTotalDeliveriesCalc = (double) this.getBreechDeliveries() / (double) this.getTotalDeliveries();
                        this.setBreechDeliveriesTotalDeliveriesCalc(df.format(breechDeliveriesTotalDeliveriesCalc));
                    }
                    if (this.getSpontaneousVertexDeliveries() > 0) {
                        double spontaneousVertexDeliveriesTotalDeliveriesCalc = (double) this.getSpontaneousVertexDeliveries() / (double) this.getTotalDeliveries();
                        this.setSpontaneousVertexDeliveriesTotalDeliveriesCalc(df.format(spontaneousVertexDeliveriesTotalDeliveriesCalc));
                    }
                    if (this.getForcepsDeliveries() > 0) {
                        double forcepsDeliveriesTotalDeliveriesCalc = (double) this.getForcepsDeliveries() / (double) this.getTotalDeliveries();
                        this.setForcepsDeliveriesTotalDeliveriesCalc(df.format(forcepsDeliveriesTotalDeliveriesCalc));
                    }
                    if (this.getVacuumExtractionDeliveries() > 0) {
                        double vacuumExtractionDeliveriesTotalDeliveriesCalc = (double) this.getVacuumExtractionDeliveries() / (double) this.getTotalDeliveries();
                        this.setVacuumExtractionDeliveriesTotalDeliveriesCalc(df.format(vacuumExtractionDeliveriesTotalDeliveriesCalc));
                    }
                    if (this.getCaesarianSectionDeliveries() > 0) {
                        double caesarianSectionDeliveriesTotalDeliveriesCalc = (double) this.getCaesarianSectionDeliveries() / (double) this.getTotalDeliveries();
                        this.setCaesarianSectionDeliveriesTotalDeliveriesCalc(df.format(caesarianSectionDeliveriesTotalDeliveriesCalc));
                    }
                }
                rs.close();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ///////////////////////////////////////////////////////////////////////
            // Postnatal Attendances
            ///////////////////////////////////////////////////////////////////////
            try {
                this.setAaPostnatalAtt(ZEPRSSharedItems.getTotalPostnatalVisitUth(beginDate, endDate, conn));
            } catch (Exception e) {
                e.printStackTrace();
            }
            int rupturedUterusCount = processPostnatalHosp(beginDate, endDate, conn);
            this.setRupturedUterus(rupturedUterusCount);
            if (this.getRupturedUterus() > 0) {
                double rupturedUterusCalc = (double) this.getRupturedUterus() / (double) this.getTotalDeliveries();
                this.setRupturedUterusTotalDeliveriesCalc(df.format(rupturedUterusCalc));
            }

            ///////////////////////////////////////////////////////////////////////
            // Maternal Deaths
            ///////////////////////////////////////////////////////////////////////
            this.setMaternalDeaths(ZEPRSSharedItems.getTotalMaternalDeathsUTH(beginDate, endDate, conn));
            if (this.getTotalDeliveries() > 0) {
                if (this.getMaternalDeaths() > 0) {
                    double maternalDeathsTotalDeliveriesCalc = (double) this.getMaternalDeaths() / (double) this.getTotalDeliveries();
                    this.setMaternalDeathsTotalDeliveriesCalc(df.format(maternalDeathsTotalDeliveriesCalc));
                }
            }

            int referrals = ReferralDAO.getReferralCount(beginDate, endDate, conn);
            this.setReferredCases(referrals);
            if (referrals > 0) {
                double referredCasesTotalDeliveriesCalc = referrals/(double) this.getTotalDeliveries();
                this.setReferredCasesTotalDeliveriesCalc(df.format(referredCasesTotalDeliveriesCalc));
            }

            processNewbornEval(conn, beginDate, endDate, this);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int processPostnatalHosp(Date beginDate, Date endDate, Connection conn) throws ServletException, SQLException {
        int count = 0;
        ResultSet rs = ZEPRSUtils.getEncountersUth("postnatalhosp", beginDate, endDate, conn, false);
        while (rs.next()) {
            int rupturedUterus = rs.getInt("ruptured_uterus");
            if (rupturedUterus == 1) {
                count++;
            }
        }
        return count;
    }

    protected static void processNewbornEval(Connection conn, Date beginDate, Date endDate, UthObstetricsReport report) {

        int fsbM = 0;
        int fsbF = 0;
        int msbM = 0;
        int msbF = 0;
        int aliveM = 0;
        int aliveF = 0;
        int prematureM = 0;
        int prematureF = 0;
        int neonatalM = 0;
        int neonatalF = 0;
        int bba = 0;
        int infantM = 0;
        int infantF = 0;
        DecimalFormat df = new DecimalFormat("##.##%");

        try {
            ResultSet rs = ZEPRSUtils.getEncountersUth("newborneval", beginDate, endDate, conn, false);
            while (rs.next()) {
                int sex = rs.getInt("sex_490");

                if (sex == 1) {
                    infantF++;
                } else {
                    infantM++;
                }

                if (rs.getInt("alive_sb_493") == Constants.ALIVE) {
                    if (sex == 1) {
                        aliveF++;
                    } else {
                        aliveM++;
                    }
                }

                if (rs.getInt("alive_sb_493") == Constants.FRESH_STILL_BIRTH) {
                    if (sex == 1) {
                        fsbF++;
                    } else {
                        fsbM++;
                    }
                }
                if (rs.getInt("alive_sb_493") == Constants.MACERATED_STILL_BIRTH) {
                    if (sex == 1) {
                        msbF++;
                    } else {
                        msbM++;
                    }
                }
                if (rs.getInt("if_premature_num_weeks_gest_488") != 0) {
                    if (sex == 1) {
                        prematureF++;
                    } else {
                        prematureM++;
                    }
                }
                if (rs.getBoolean("neonatal_dea_1180")) {
                    if (sex == 1) {
                        neonatalF++;
                    } else {
                        neonatalM++;
                    }
                }
                 if (rs.getBoolean("born_at_home")) {
                    bba++;
                }
            }
            rs.close();
        } catch (Exception e) {
            log.error(e);
        }

        report.setbBAs(bba);
         if (bba > 0) {
                double bBAsTotalDeliveriesCalc = bba/(double) report.getTotalDeliveries();
                report.setbBAsTotalDeliveriesCalc(df.format(bBAsTotalDeliveriesCalc));
            }
        report.setFreshStillbirthsFemale(fsbF);
        report.setFreshStillbirthsMale(fsbM);
        report.setFreshStillbirthsTotal(fsbF + fsbM);
        report.setMaceratedStillbirthsFemale(msbF);
        report.setMaceratedStillbirthsMale(msbM);
        report.setMaceratedStillbirthsTotal(msbF + msbM);
        report.setLiveBirthsFemale(aliveF);
        report.setLiveBirthsMale(aliveM);
        report.setLiveBirthsTotal(aliveF + aliveM);
        report.setNeonatalDeathsFemale(neonatalF);
        report.setNeonatalDeathsMale(neonatalM);
        report.setNeonatalDeathsTotal(neonatalF + neonatalM);
        report.setPrematureLiveBirthsFemale(prematureF);
        report.setPrematureLiveBirthsMale(prematureM);
        report.setPrematureLiveBirthsTotal(prematureF + prematureM);
        report.setStillBirthsFemale(fsbF + msbF);
        report.setStillBirthsMale(fsbM + msbM);
        report.setStillBirthsTotal(fsbF + msbF + fsbM + msbM);
        report.setTotalInfantsBornFemale(infantF);
        report.setTotalInfantsBornMale(infantM);
        report.setTotalInfantsBornTotal(infantF + infantM);
    }
}
