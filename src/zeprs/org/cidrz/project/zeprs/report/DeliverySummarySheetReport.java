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

import org.cidrz.project.zeprs.report.valueobject.DailyDeliverySummary;
import org.cidrz.webapp.dynasite.dao.SessionPatientDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.valueobject.Patient;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class DeliverySummarySheetReport extends ZEPRSReport {

    private String reportMonth;
    private String reportYear;
    private Map dailySummaries = new TreeMap();
    private Date beginDate;
    private Date endDate;


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

    public Map getDailySummaries() {
        return dailySummaries;
    }

    public void setDailySummaries(Map dailySummaries) {
        this.dailySummaries = dailySummaries;
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

        // hi
        try {
            // setup datasource
            Connection conn = null;
            conn = ZEPRSUtils.getZEPRSConnection();

            ///////////////////////////////////////////////////////////////////////
            // Delivered in the Unit
            ///////////////////////////////////////////////////////////////////////

            /**
             * Had to refactor this to query newborneval rather than deliverysum.
             * Data entry folks were not changing the date_visit field to date of birth when back-entering
             * records; therefore, newborneval is a better way to making this resultset.
             */

            Map motherDeliveries = new TreeMap();

            ResultSet rs = ZEPRSSharedItems.getNewborns(beginDate, endDate, siteID, conn );
            Date dateVisit = null;
            while (rs.next()) {
                int patientId = rs.getInt("patient_id");
                int parentId = rs.getInt("parent_id");
                int pregnancyId = rs.getInt("pregnancy_id");
                Date thisDateVisit = rs.getDate("date_of_birth");
                Boolean isDead = rs.getBoolean("dead");
                int stillbirth = rs.getInt("alive_sb_493");

                boolean countDelivery = true;
                int infantAlive = 0;
                int infantDead = 0;
                int fsb = 0;
                int msb = 0;
                byte nvp = 0;
                int regimen = 0;
                int routineUterotonicGiven = 0;
                int hivTestedInLabour = 0;
                int hiv = 0;
                int alive = 0;
                int dead =0;
                int infantToHospital = 0;
                int motherToHospital = 0;
                int womenFormulaFeeding = 0;
                int womenMixedFeeding = 0;
                int womenBreastFeeding = 0;

                if (isDead != null && isDead.booleanValue() == true) {
                   infantDead = 1;	// if fsb or msb, we will set to 0 later.
                } else {
                   infantAlive = 1;
                }

                if (stillbirth == Constants.FRESH_STILL_BIRTH) {
                    fsb = 1;
                    infantDead = 0;
                }
                if (stillbirth == Constants.MACERATED_STILL_BIRTH) {
                    msb = 1;
                    infantDead = 0;
                }

                if (motherDeliveries.get(parentId) !=null) {
                	int thisPregnancyId = (Integer)motherDeliveries.get(parentId);
                	if (thisPregnancyId == pregnancyId) {
                    	countDelivery = false;
                	}
                } else {
                	motherDeliveries.put(parentId, pregnancyId);
                }

                if (countDelivery == true) {
                	// now fetch the delivery record for the mother(parent_id)
                	ResultSet delivery = ZEPRSSharedItems.getDelivery(conn, parentId, pregnancyId);
                	while (delivery.next()) {
                		nvp = delivery.getByte("given_nvp_tablets_1223");
                		regimen = delivery.getInt("regimen");
                		routineUterotonicGiven = delivery.getInt("uterotonic_med_given");
                		hivTestedInLabour = delivery.getInt("hiv_tested_in_labour");
                	}

                	// get patient table for this mother
                	ResultSet patient = ZEPRSSharedItems.getPatient(parentId, conn);
                	while (patient.next()) {
                		hiv = patient.getInt("hiv_positive");
                		dead = patient.getInt("dead");
                	}
                	// get mother's referrals
                	List referrals = OutcomeDAO.getReferrals(conn, Long.valueOf(parentId), Long.valueOf(pregnancyId));
                	if (referrals.size() >0) {
                		motherToHospital = 1;
                	}
                	// get maternal discharge summary - for feeding status
                	ResultSet discharge = null;
                	try {
                		discharge = ZEPRSUtils.getPatientEncounters(parentId, pregnancyId, 68, "maternaldischarge", beginDate, endDate, conn);
                		while (discharge.next()) {
                			int feedingType = discharge.getInt("feeding_type");
                			if (feedingType == 2885) // Formula
                			{
                				womenFormulaFeeding = 1;
                			} else if (feedingType == 2886) // Breastfeeding
                			{
                				womenBreastFeeding = 1;
                			} else if (feedingType == 2959) // Combined
                			{
                				womenMixedFeeding = 1;
                			}
                		}
                	} catch (ServletException e) {
                		e.printStackTrace();
                	}
                }

                // Get the infant's referrals
                try {
                    List referrals = OutcomeDAO.getReferrals(conn, Long.valueOf(patientId), Long.valueOf(pregnancyId));
                    if (referrals.size() >0) {
                        infantToHospital = 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (dateVisit != null && dateVisit.equals(thisDateVisit)) {
                    // get this from the deliverySummaries map
                    DailyDeliverySummary dailyDeliverySummary = (DailyDeliverySummary) dailySummaries.get(thisDateVisit);
                    int deliveries = dailyDeliverySummary.getDeliveries();
                    dailyDeliverySummary.setDeliveries(deliveries + 1);
                    int hivPosMothers = dailyDeliverySummary.getHivPosMothers();
                    dailyDeliverySummary.setHivPosMothers(hivPosMothers + hiv);
                    int maternalNvpIngested = dailyDeliverySummary.getMaternalNvpIngested();
                    if (nvp == 1) {
                       dailyDeliverySummary.setMaternalNvpIngested(maternalNvpIngested + 1);
                    }
                    int mothersTestedInLabour = dailyDeliverySummary.getMothersTestedInLabour();
                    if (hivTestedInLabour == 1) {
                    	dailyDeliverySummary.setMothersTestedInLabour(mothersTestedInLabour + 1);
                    }
                    if (regimen >0) {
                    	// NVP or NVP+AZT
                    	if (regimen == 2938 || regimen == 2970) {
                    		dailyDeliverySummary.setMaternalNvpIngested(maternalNvpIngested + 1);
                    	}
                    }
                    dailyDeliverySummary.setWomenBreastFeeding(dailyDeliverySummary.getWomenBreastFeeding() + womenBreastFeeding);
                    dailyDeliverySummary.setWomenFormulaFeeding(dailyDeliverySummary.getWomenFormulaFeeding() + womenFormulaFeeding);
                    dailyDeliverySummary.setWomenMixedFeeding(dailyDeliverySummary.getWomenMixedFeeding() + womenMixedFeeding);

                    if (dead ==1) {
                        dailyDeliverySummary.setMotherDied(dailyDeliverySummary.getMotherDied() + dead);
                    } else {
                       dailyDeliverySummary.setMotherAlive(dailyDeliverySummary.getMotherAlive() + 1);
                    }
                    dailyDeliverySummary.setInfantAlive(dailyDeliverySummary.getInfantAlive() + infantAlive);
                    dailyDeliverySummary.setInfantDied(dailyDeliverySummary.getInfantDied() + infantDead);
                    dailyDeliverySummary.setMotherToHospital(dailyDeliverySummary.getMotherToHospital() + motherToHospital);
                    dailyDeliverySummary.setInfantToHospital(dailyDeliverySummary.getInfantToHospital() + infantToHospital);
                    dailyDeliverySummary.setFsb(dailyDeliverySummary.getFsb() + fsb);
                    dailyDeliverySummary.setMsb(dailyDeliverySummary.getMsb() + msb);
                    int rUterotonicGiven = dailyDeliverySummary.getRoutineUterotonicGiven();
                    if (routineUterotonicGiven > 0) {
                    	switch (routineUterotonicGiven) {
						case 3087:
							dailyDeliverySummary.setRoutineUterotonicGiven(rUterotonicGiven +1);
							break;
						case 3088:
							dailyDeliverySummary.setRoutineUterotonicGiven(rUterotonicGiven +1);
							break;
						case 3089:
							dailyDeliverySummary.setRoutineUterotonicGiven(rUterotonicGiven +1);
							break;
						case 3093:
							dailyDeliverySummary.setRoutineUterotonicGiven(rUterotonicGiven +1);
							break;
						default:
							break;
						}
                    }
                } else {
                    dateVisit = thisDateVisit;
                    DailyDeliverySummary dailyDeliverySummary = new DailyDeliverySummary();
                    //dailySum.setDeliveries(1);
                    int deliveries = dailyDeliverySummary.getDeliveries();
                    dailyDeliverySummary.setDeliveries(deliveries + 1);
                    int hivPosMothers = dailyDeliverySummary.getHivPosMothers();
                    dailyDeliverySummary.setHivPosMothers(hivPosMothers + hiv);
                    int maternalNvpIngested = dailyDeliverySummary.getMaternalNvpIngested();
                    if (nvp == 1) {
                       dailyDeliverySummary.setMaternalNvpIngested(maternalNvpIngested + 1);
                    }
                    int mothersTestedInLabour = dailyDeliverySummary.getMothersTestedInLabour();
                    if (hivTestedInLabour == 1) {
                    	dailyDeliverySummary.setMothersTestedInLabour(mothersTestedInLabour + 1);
                    }
                    if (regimen >0) {
                    	// NVP or NVP+AZT
                    	if (regimen == 2938 || regimen == 2970) {
                    		dailyDeliverySummary.setMaternalNvpIngested(maternalNvpIngested + 1);
                    	}
                    }
                    dailyDeliverySummary.setWomenBreastFeeding(dailyDeliverySummary.getWomenBreastFeeding() + womenBreastFeeding);
                    dailyDeliverySummary.setWomenFormulaFeeding(dailyDeliverySummary.getWomenFormulaFeeding() + womenFormulaFeeding);
                    dailyDeliverySummary.setWomenMixedFeeding(dailyDeliverySummary.getWomenMixedFeeding() + womenMixedFeeding);

                    if (dead ==1) {
                        dailyDeliverySummary.setMotherDied(dailyDeliverySummary.getMotherDied() + dead);
                    } else {
                       dailyDeliverySummary.setMotherAlive(dailyDeliverySummary.getMotherAlive() + 1);
                    }
                    dailyDeliverySummary.setInfantAlive(dailyDeliverySummary.getInfantAlive() + infantAlive);
                    dailyDeliverySummary.setInfantDied(dailyDeliverySummary.getInfantDied() + infantDead);
                    dailyDeliverySummary.setMotherToHospital(dailyDeliverySummary.getMotherToHospital() + motherToHospital);
                    dailyDeliverySummary.setInfantToHospital(dailyDeliverySummary.getInfantToHospital() + infantToHospital);
                    dailyDeliverySummary.setFsb(dailyDeliverySummary.getFsb() + fsb);
                    dailyDeliverySummary.setMsb(dailyDeliverySummary.getMsb() + msb);
                    int rUterotonicGiven = dailyDeliverySummary.getRoutineUterotonicGiven();
                    if (routineUterotonicGiven > 0) {
                    	switch (routineUterotonicGiven) {
						case 3087:
							dailyDeliverySummary.setRoutineUterotonicGiven(rUterotonicGiven +1);
							break;
						case 3088:
							dailyDeliverySummary.setRoutineUterotonicGiven(rUterotonicGiven +1);
							break;
						case 3089:
							dailyDeliverySummary.setRoutineUterotonicGiven(rUterotonicGiven +1);
							break;
						case 3093:
							dailyDeliverySummary.setRoutineUterotonicGiven(rUterotonicGiven +1);
							break;
						default:
							break;
						}
                    }
                    dailySummaries.put(dateVisit, dailyDeliverySummary);
                }
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
