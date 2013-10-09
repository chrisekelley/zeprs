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

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.report.valueobject.FixedCostIncentive;

/**
 * @author ckelley@rti.org
 */
public class FixedCostIncentiveReport extends ZEPRSReport {

	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getFactory().getInstance(FixedCostIncentiveReport.class);

	private String reportMonth;
	private String reportYear;
	private Map<Date, FixedCostIncentive> dailySummaries = new TreeMap<Date, FixedCostIncentive>();
	private Date beginDate;
	private Date endDate;
	private int reportMonthInt;
	// Total for each form submitted.
	private int routineAnteForms;
	private int initialVisitForms;
	private int newbornEvalForms;
	private int arvRegimenForms;
	private int safeMotherhoodCareForms;
	private int smCounselingVisitForms;
	private int labTestForms;
	private int rprForms;
	private HashMap patientRefillsMap;


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

	public Map<Date, FixedCostIncentive> getDailySummaries() {
		return dailySummaries;
	}

	public void setDailySummaries(Map<Date, FixedCostIncentive> dailySummaries) {
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

	public int getReportMonthInt() {
		return reportMonthInt;
	}

	public void setReportMonthInt(int reportMonthInt) {
		this.reportMonthInt = reportMonthInt;
	}

	public static Log getLog() {
		return log;
	}

	public static void setLog(Log log) {
		FixedCostIncentiveReport.log = log;
	}

	public int getRoutineAnteForms() {
		return routineAnteForms;
	}

	public void setRoutineAnteForms(int routineAnteForms) {
		this.routineAnteForms = routineAnteForms;
	}

	public int getInitialVisitForms() {
		return initialVisitForms;
	}

	public void setInitialVisitForms(int initialVisitForms) {
		this.initialVisitForms = initialVisitForms;
	}

	public int getNewbornEvalForms() {
		return newbornEvalForms;
	}

	public void setNewbornEvalForms(int newbornEvalForms) {
		this.newbornEvalForms = newbornEvalForms;
	}

	public int getArvRegimenForms() {
		return arvRegimenForms;
	}

	public void setArvRegimenForms(int arvRegimenForms) {
		this.arvRegimenForms = arvRegimenForms;
	}

	public int getSafeMotherhoodCareForms() {
		return safeMotherhoodCareForms;
	}

	public void setSafeMotherhoodCareForms(int safeMotherhoodCareForms) {
		this.safeMotherhoodCareForms = safeMotherhoodCareForms;
	}

	public int getSmCounselingVisitForms() {
		return smCounselingVisitForms;
	}

	public void setSmCounselingVisitForms(int smCounselingVisitForms) {
		this.smCounselingVisitForms = smCounselingVisitForms;
	}

	public int getLabTestForms() {
		return labTestForms;
	}

	public void setLabTestForms(int labTestForms) {
		this.labTestForms = labTestForms;
	}

	public int getRprForms() {
		return rprForms;
	}

	public void setRprForms(int rprForms) {
		this.rprForms = rprForms;
	}

	public HashMap getPatientRefillsMap() {
		return patientRefillsMap;
	}

	public void setPatientRefillsMap(HashMap patientRefillsMap) {
		this.patientRefillsMap = patientRefillsMap;
	}

	/**
	 * kudos: http://forum.java.sun.com/thread.jspa?forumID=31&messageID=2205776&threadID=475504
	 *
	 * @param beginDate
	 * @param endDate
	 */
	 public void loadReport(Date beginDate, Date endDate) {

		int siteID = super.getSiteId();

		// set the month and year for the report
		this.setReportMonth(ZEPRSUtils.getReportMonth(beginDate, endDate));
		this.setReportYear(ZEPRSUtils.getReportYear(beginDate, endDate));

		Calendar gc = new GregorianCalendar();
		gc.setTime(endDate);
		int maxDate = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
		int month = gc.get(Calendar.MONTH);
		int year = gc.get(Calendar.YEAR);
		gc.set(year, month, 0, 0, 0, 0);
		gc.add(Calendar.DAY_OF_MONTH, 1);
		Calendar monthEndCal = new GregorianCalendar();
		monthEndCal.set(year, month, maxDate, 0, 0, 0);

		// re-assign values for begin/endDate
		beginDate = new Date(gc.getTime().getTime());
		endDate = new Date(monthEndCal.getTime().getTime());

		this.setBeginDate(beginDate);
		this.setEndDate(endDate);

		while (!gc.after(monthEndCal) && dailySummaries.size() < 32) {
			Date thisDate = new Date(gc.getTime().getTime());
			FixedCostIncentive fixedCostIncentive = new FixedCostIncentive();
			dailySummaries.put(thisDate, fixedCostIncentive);
			gc.add(Calendar.DAY_OF_MONTH, 1);
		}

		try {
			// setup datasource
			Connection conn;
			conn = ZEPRSUtils.getZEPRSConnection();
			ResultSet rs;
			HashMap patientRefillsMap = new HashMap();	// Counts how many refills for patient.
			// First loop through all of the encounters for this period.
			try {
				rs = ZEPRSUtils.getEncounters(beginDate, endDate, siteID, conn);
				while (rs.next()) {
					Date thisDateVisit = rs.getDate("date_visit");
					if (thisDateVisit == null) {
						break;
					}
					Long patientId = rs.getLong("patient_id");
					Long pregnancyId = rs.getLong("pregnancy_id");
					Long encounterId = rs.getLong("id");
					Long formId = rs.getLong("form_id");
					Long parentId = rs.getLong("parent_id");

					//allVisits.put(patientId, thisDateVisit);
					FixedCostIncentive fixedCostIncentive = (FixedCostIncentive) dailySummaries.get(thisDateVisit);
					// only for mothers - not children
					if (parentId == null || parentId == 0) {
						// See if delivery summary form has been submitted for this mother - use the deliveryDateVisit value as the
						// cutoff date for forms - we don't want to count any lab forms submitted after delivery

						ResultSet deliverySummary = null;
						Date deliveryDateVisit = null;
						deliverySummary = ZEPRSUtils.getPatientEncounters(patientId.intValue(), pregnancyId.intValue(), 66, "deliverysum", conn);
						while (deliverySummary.next()) {
							deliveryDateVisit = deliverySummary.getDate("date_visit");
						}
						deliverySummary.close();
						// Now search through mother-only forms
						switch (formId.intValue()) {
						case 66:
							ResultSet deliverySum = ZEPRSUtils.getPatientEncounters(patientId.intValue(), pregnancyId.intValue(), 66, "deliverysum", conn);
							//int regimen = 0;
							//Byte receivedRegimen = 0;
							Byte hivTestedInLW = 0;
							int uterotonic = 0;
							while (deliverySum.next()) {
								//regimen = deliverySum.getInt("regimen");
								//receivedRegimen = deliverySum.getByte("mother_received_arv");
								hivTestedInLW = deliverySum.getByte("hiv_tested_in_labour");
								uterotonic = deliverySum.getInt("uterotonic_med_given");
							}
							deliverySum.close();

							int deliveries = fixedCostIncentive.getDeliveries();
							fixedCostIncentive.setDeliveries(deliveries + 1);

							int hivTestedInLabourWard = fixedCostIncentive.getHivTestedInLabourWard();
							int routineUterotonicGiven = fixedCostIncentive.getRoutineUterotonicGiven();

							if (hivTestedInLW > 0) {
								fixedCostIncentive.setHivTestedInLabourWard(hivTestedInLabourWard + 1);
							}

							if (uterotonic > 0) {
								if (uterotonic == 3087 || uterotonic == 3088 || uterotonic == 3088 || uterotonic == 3093) {
									fixedCostIncentive.setRoutineUterotonicGiven(routineUterotonicGiven + 1);
								}
							}
							break;
						case 89:
							/*
                                Advice from Perry Killam 5/22/2006: "On this antenatal summary, I would only count Maternal NVP as ones
                                prescribed in the ARV form – either NVP or NVP + AZT but not AZT only or HAART or Other.  In the near
                                future, they’ll report NVP only and AZT + NVP separately, but the PMTCT group hasn’t actually finalized
                                this recommendation yet."
							 */
							// Regimen - mother
							// rs = ZEPRSUtils.getEncountersMother(89, "arvregimen", beginDate, endDate, siteID, conn);
							ResultSet arvVisitRS = ZEPRSUtils.getEncounterById(encounterId, "arvregimen", conn);
							while (arvVisitRS.next()) {
								Date dateVisit = arvVisitRS.getDate("date_visit");
								Date regimenDateVisit = arvVisitRS.getDate("regimen_visit_date");
								//Integer phaseOfPregnancy = arvVisitRS.getInt("phaseOfPregnancy");   // 2934 = antenatal
								if ((deliveryDateVisit == null) || (deliveryDateVisit != null) && (dateVisit.getTime() <= deliveryDateVisit.getTime()))
								{
									/*if (phaseOfPregnancy == 2934) {*/
									Integer regimen = arvVisitRS.getInt("regimen");
									Integer referred = arvVisitRS.getInt("referred_art_clinic");
									Integer receivedRegimen = arvVisitRS.getInt("receivedregimen");

									int maternalNVP = fixedCostIncentive.getMaternalNVP();
									//int maternalAzt = fixedCostIncentive.getMaternalAzt();
									int maternalAztNewStart = fixedCostIncentive.getMaternalAztNewStart();
									int maternalAztRefills = fixedCostIncentive.getMaternalAztRefills();
									int referredArt = fixedCostIncentive.getReferredArt();
									if (regimen > 0) {
										{
											if (receivedRegimen == 3197) {
												//if (regimen == 2938 || regimen == 2970)
												if (regimen == 3222 || regimen == 3223)
												{  // either NVP or NVP + AZT
													fixedCostIncentive.setMaternalNVP(maternalNVP + 1);
												}
												//if (regimen == 2937) {  // AZT
												// See notes in DailyAntenatalSummary report for rules about counting visits
												if (regimen == 3221 || regimen == 3223) {  // AZT or NVP + AZT
													// Now check the date the first arv form was submitted.
													// if thisDateVisit =  arvDateVisit, increment setMaternalAztNewStart.
													ResultSet aztRS = ZEPRSUtils.getFirstAztRegimen(conn, patientId);
													int aztCount = 0;
													Date arvFirstVisit = null;
													while (aztRS.next()) {
														arvFirstVisit = aztRS.getDate("regimen_visit_date");
														aztCount++;
													}
													aztRS.close();
													// if thisDateVisit =  arvDateVisit, increment setMaternalAztNewStart.
													//if (aztCount > 1) {
													if (arvFirstVisit.getTime() == regimenDateVisit.getTime()) {
														fixedCostIncentive.setMaternalAztNewStart(maternalAztNewStart + 1);
														String message = patientId + "|" + dateVisit + "|" + regimenDateVisit + "|" + arvFirstVisit;
														//log.debug(message);
													} else {
														// check the refills count for this patient
														// don't increment maternalAztRefills if patient already has any refills.
														if (patientRefillsMap.get(patientId) != null) {
															Integer refills = (Integer) patientRefillsMap.get(patientId);
															int refillsInt = refills.intValue();
															refillsInt++;
															patientRefillsMap.put(patientId, refillsInt);
														} else {
															fixedCostIncentive.setMaternalAztRefills(maternalAztRefills + 1);
															patientRefillsMap.put(patientId, Integer.valueOf(1));
															String message = patientId + "|" + dateVisit + "|" + regimenDateVisit + "|" + arvFirstVisit;
															//log.debug(message);
														}
													}
													/*} else {
															fixedCostIncentive.setMaternalAztRefills(maternalAztNewStart + 1);
														}*/
												}
											}
										}
									}
									if (referred != null) {
										int referredArtInt = referred.intValue();
										if ((referredArtInt == 3060) || (referredArtInt == 3061) || (referredArtInt == 3062) || (referredArtInt == 3070))
										{
											fixedCostIncentive.setReferredArt(referredArt + 1);
										}
									}
								}
							}
							arvVisitRS.close();
							break;
						case 91:
							// counsel visits
							ResultSet counselVisitRs = ZEPRSUtils.getEncounterById(encounterId, "smcounselingvisit", conn);

							while (counselVisitRs.next()) {
								//byte counselled = counselVisitRs.getByte("counselled");
								int tested = counselVisitRs.getInt("hiv_tested");

								switch (tested) {
								case 2966:  // Yes
									int hivTested = fixedCostIncentive.getHivTested();
									fixedCostIncentive.setHivTested(hivTested + 1);
									break;
								case 2967:  // No
									break;
								case 2968:  // Patient refused to be tested.
									//int refusedTesting = fixedCostIncentive.getRefusedTesting();
									//fixedCostIncentive.setRefusedTesting(refusedTesting + 1);
									break;
								}
							}
							counselVisitRs.close();
							break;
						}
					}
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
}
