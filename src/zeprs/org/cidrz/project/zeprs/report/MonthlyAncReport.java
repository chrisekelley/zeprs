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
import org.cidrz.project.zeprs.report.valueobject.AntenatalSummary;
import org.cidrz.webapp.dynasite.dao.AncReportDAO;
import org.cidrz.webapp.dynasite.dao.SiteDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Site;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
 * @author ckelley@rti.org
 */
public class MonthlyAncReport extends ZEPRSReport {

	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getFactory().getInstance(DailyAntenatalSummaryReport.class);

	private String reportMonth;
	private String reportYear;
	private Map dailySummaries = new TreeMap();
	private Map monthlySummaries = new TreeMap();
	private Map yearlySummaries = new TreeMap();
	private Map siteSummaries = new TreeMap();
	private List siteSummaryList = new ArrayList();
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
	public Map getMonthlySummaries() {
		return monthlySummaries;
	}

	public void setMonthlySummaries(Map monthlySummaries) {
		this.monthlySummaries = monthlySummaries;
	}

	public Map getYearlySummaries() {
		return yearlySummaries;
	}

	public void setYearlySummaries(Map yearlySummaries) {
		this.yearlySummaries = yearlySummaries;
	}

	public Map getSiteSummaries() {
		return siteSummaries;
	}

	public void setSiteSummaries(Map siteSummaries) {
		this.siteSummaries = siteSummaries;
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
		MonthlyAncReport.log = log;
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

	/**
	 * kudos: http://forum.java.sun.com/thread.jspa?forumID=31&messageID=2205776&threadID=475504
	 *
	 * @param beginDate
	 * @param endDate
	 */
	 public void loadReport(Date beginDate, Date endDate) {

		int siteID = super.getSiteId();
		Date beginYearlyReportDate = beginDate;
		// set the month and year for the report
		this.setReportMonth(ZEPRSUtils.getReportMonth(beginDate, endDate));
		this.setReportYear(ZEPRSUtils.getReportYear(beginDate, endDate));

		Boolean yearlyReport = false;
		Boolean saveReport = false;
		Boolean allSites = false;

		Object yearProp = this.getReportProperties().get("year");
		if (yearProp != null) {
			yearlyReport = true;
		}
		Object saveProp = this.getReportProperties().get("save");
		if (saveProp != null) {
			saveReport = true;
		}
		Object allsitesProp = this.getReportProperties().get("allSites");
		if (allsitesProp != null) {
			allSites = true;
		}

		Calendar gc = new GregorianCalendar();
		gc.setTime(endDate);
		int maxDate = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
		int month = gc.get(Calendar.MONTH);
		int year = gc.get(Calendar.YEAR);
		gc.set(year, month, 0, 0, 0, 0);
		gc.add(Calendar.DAY_OF_MONTH, 1);
		Calendar monthEndCal = new GregorianCalendar();
		monthEndCal.set(year, month, maxDate, 0, 0, 0);

		//  re-assign values for begin/endDate
		Calendar yearlyReportGc = null;
		if (yearlyReport == Boolean.TRUE) {
			yearlyReportGc = new GregorianCalendar();
			yearlyReportGc.setTime(beginYearlyReportDate);
			//int maxDate = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
			month = yearlyReportGc.get(Calendar.MONTH);
			year = yearlyReportGc.get(Calendar.YEAR);
			gc.set(year, month, 0, 0, 0, 0);
			gc.add(Calendar.DAY_OF_MONTH, 1);
			//Calendar monthEndCal = new GregorianCalendar();
			//monthEndCal.set(year, month, maxDate, 0, 0, 0);
			beginDate = new Date(gc.getTime().getTime());
		} else {
			beginDate = new Date(gc.getTime().getTime());
		}
		endDate = new Date(monthEndCal.getTime().getTime());

		this.setBeginDate(beginDate);
		this.setEndDate(endDate);

		// Build the dailySummaries Map.
		while (!gc.after(monthEndCal) && dailySummaries.size() < 32) {
			Date thisDate = new Date(gc.getTime().getTime());
			AntenatalSummary dailyAntenatalSummary = new AntenatalSummary();
			dailyAntenatalSummary.setDateVisit(thisDate);
			dailyAntenatalSummary.setSiteId(siteID);
			dailySummaries.put(thisDate, dailyAntenatalSummary);
			gc.add(Calendar.DAY_OF_MONTH, 1);
		}
		// Build the year monthly summaries map if this is a yearly summary
		if (yearlyReport == Boolean.TRUE) {
			while (!yearlyReportGc.after(monthEndCal)) {
				Date thisDate = new Date(yearlyReportGc.getTime().getTime());
				HashMap monthMap = new HashMap();
				monthlySummaries.put(thisDate, monthMap);
				yearlyReportGc.add(Calendar.MONTH, 1);
			}
		}

		// Use this clean monthlySummaries later when assemlbing the siteSummaries.
		//Map cleanMonthlySummaries = new HashMap();
		//cleanMonthlySummaries.putAll(monthlySummaries);

		if (saveReport == Boolean.TRUE) {


			//conn = DatabaseUtils.getAdminConnection();
			// loop through the monthlySummaries
			Iterator  iter = monthlySummaries.keySet().iterator();
			while(iter.hasNext())
			{

				//  setup datasource
				Connection conn = null;
				try {
					DataSource dataSource = null;
					dataSource = DatabaseUtils.getZEPRSDataSource();
					conn = dataSource.getConnection();

					beginDate = (Date) iter.next();
					Calendar monthGc = new GregorianCalendar();
					monthGc.setTime(beginDate);
					maxDate = monthGc.getActualMaximum(Calendar.DAY_OF_MONTH);
					month = monthGc.get(Calendar.MONTH);
					year = monthGc.get(Calendar.YEAR);
					monthGc.set(year, month, 0, 0, 0, 0);
					monthGc.add(Calendar.DAY_OF_MONTH, 1);
					monthEndCal = new GregorianCalendar();
					monthEndCal.set(year, month, maxDate, 0, 0, 0);
					endDate = new Date(monthEndCal.getTime().getTime());
					//Reinit the dailySummaries Map.
					dailySummaries.clear();
					monthGc.setTime(beginDate);
					while (!monthGc.after(monthEndCal) && dailySummaries.size() < 32) {
						Date thisDate = new Date(monthGc.getTime().getTime());
						AntenatalSummary dailyAntenatalSummary = new AntenatalSummary();
						dailyAntenatalSummary.setDateVisit(thisDate);
						dailyAntenatalSummary.setSiteId(siteID);
						dailySummaries.put(thisDate, dailyAntenatalSummary);
						monthGc.add(Calendar.DAY_OF_MONTH, 1);
					}
					getMonthlyReport(beginDate, endDate, siteID, conn);
					System.out.println("Saved records for month beginning " + beginDate + " for siteId " + siteId);
					System.out.println("Trying to close conn.");
					conn.close();
					System.out.println("Closed conn.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						if (!conn.isClosed()) {
							System.out.println("Trying to close conn.");
							conn.close();
							System.out.println("Closed conn.");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			System.out.println("Finished saving records for " + this.getBeginDate() + "-" + this.getEndDate() + " for siteId " + siteId);
		}

		if (saveReport == Boolean.FALSE) {
			// fetch values from the database
			Connection adminConn = DatabaseUtils.getAdminConnection();
			int siteSummariesSize = 0;
			try {
				if (allSites == Boolean.TRUE) {
					// Loop through the sites and store them in siteSummaries Map.
					List sites = null;
					sites = SiteDAO.getAllActive(adminConn);
					for (Iterator iterS = sites.iterator(); iterS.hasNext();) {
						Site site = (Site) iterS.next();
						List dailyReports = AncReportDAO.getAll(adminConn, Long.valueOf(site.getId()));
						TreeMap thisCleanMonthlySummary = new TreeMap();
						yearlyReportGc = new GregorianCalendar();
						yearlyReportGc.setTime(beginYearlyReportDate);
						//int maxDate = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
						month = yearlyReportGc.get(Calendar.MONTH);
						year = yearlyReportGc.get(Calendar.YEAR);
						gc.set(year, month, 0, 0, 0, 0);
						gc.add(Calendar.DAY_OF_MONTH, 1);
						while (!yearlyReportGc.after(monthEndCal)) {
							Date thisDate = new Date(yearlyReportGc.getTime().getTime());
							TreeMap monthMap = new TreeMap();
							thisCleanMonthlySummary.put(thisDate, monthMap);
							yearlyReportGc.add(Calendar.MONTH, 1);
						}
						//thisCleanMonthlySummary.putAll(cleanMonthlySummaries);
						for (Iterator iter = dailyReports.iterator(); iter.hasNext();) {
							AntenatalSummary summary = (AntenatalSummary) iter.next();
							Date dateVisit = summary.getDateVisit();
							// get the month of this summary
							java.util.Calendar c = java.util.Calendar.getInstance();
							c.setTime(dateVisit);
							int summaryMonth = c.get(Calendar.MONTH) + 1;
							int summaryYear = c.get(Calendar.YEAR);
							// yyyy-mm-dd
							String monthSummaryString = summaryYear + "-" + summaryMonth + "-01";
							Date monthSummaryDate = Date.valueOf(monthSummaryString);
							TreeMap monthSummary = (TreeMap) thisCleanMonthlySummary.get(monthSummaryDate);
							monthSummary.put(dateVisit, summary);
							//thisCleanMonthlySummary.put(dateVisit, summary);
						}
						TreeMap siteSummary = new TreeMap();
						//thisCleanMonthlySummary.putAll(monthlySummaries);
						siteSummary.putAll(thisCleanMonthlySummary);
						Site fullSite = (Site) DynaSiteObjects.getClinicMap().get(site.getId());
						siteSummaries.put(fullSite.getName(), siteSummary);
						siteSummariesSize = siteSummaries.size();
					}
					siteSummariesSize = siteSummaries.size();
				} else {
					List dailyReports = AncReportDAO.getAll(adminConn, Long.valueOf(siteID));
					for (Iterator iter = dailyReports.iterator(); iter.hasNext();) {
						AntenatalSummary summary = (AntenatalSummary) iter.next();
						Date dateVisit = summary.getDateVisit();
						// get the month of this summary
						java.util.Calendar c = java.util.Calendar.getInstance();
						c.setTime(dateVisit);
						int summaryMonth = c.get(Calendar.MONTH) + 1;
						int summaryYear = c.get(Calendar.YEAR);
						// yyyy-mm-dd
						String monthSummaryString = summaryYear + "-" + summaryMonth + "-01";
						Date monthSummaryDate = Date.valueOf(monthSummaryString);
						HashMap monthSummary = (HashMap) monthlySummaries.get(monthSummaryDate);
						monthSummary.put(dateVisit, summary);
					}
				}


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					adminConn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}


	 }

	 /**
	  * Creates ANC summary for every day in the month.
	  * Persists to the anc_report database.
	  * @param beginDate
	  * @param endDate
	  * @param siteID
	  * @param conn
	  */
	 private void getMonthlyReport(Date beginDate, Date endDate, int siteID, Connection conn) {
		 try {
			 // setup datasource
			 //Connection conn;
			 //conn = ZEPRSUtils.getZEPRSConnection();
			 ResultSet rs;

			 // First loop through all of the encounters for this period and identify the date of the initial visit for each patient.
			 // test
			 HashMap allVisits = new HashMap();
			 HashMap newAncMap = new HashMap();  // Stores date of first ANC visit, initial visit, or newborn eval for a patient during this period. For "New ANC client" field
			 // Date newAncDate = null;
			 HashMap initialVisitFormMap = new HashMap();    // First visit (routine ante, initial visit or newborn eval) this patient has made during the course of preg.
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

					 // increment the form totals
					 int routineAnteForms = this.getRoutineAnteForms();
					 int initialVisitForms = this.getInitialVisitForms();
					 int newbornEvalForms = this.getNewbornEvalForms();
					 int arvRegimenForms = this.getArvRegimenForms();
					 int safeMotherhoodCareForms = this.getSafeMotherhoodCareForms();
					 int smCounselingVisitForms = this.getSmCounselingVisitForms();
					 int labTestForms = this.getLabTestForms();
					 int rprForms = this.getRprForms();

					 switch (formId.intValue()) {
					 case 80:
						 routineAnteForms ++;
						 this.setRoutineAnteForms(routineAnteForms);
						 break;
					 case 77:
						 initialVisitForms ++;
						 this.setInitialVisitForms(initialVisitForms);
						 break;
					 case 23:
						 newbornEvalForms ++;
						 this.setNewbornEvalForms(newbornEvalForms);
						 break;
					 case 89:
						 arvRegimenForms ++;
						 this.setArvRegimenForms(arvRegimenForms);
						 break;
					 case 92:
						 safeMotherhoodCareForms ++;
						 this.setSafeMotherhoodCareForms(safeMotherhoodCareForms);
						 break;
					 case 91:
						 smCounselingVisitForms ++;
						 this.setSmCounselingVisitForms(smCounselingVisitForms);
						 break;
					 case 87:
						 labTestForms ++;
						 this.setLabTestForms(labTestForms);
						 break;
					 case 90:
						 rprForms ++;
						 this.setRprForms(rprForms);
						 break;
					 }

					 allVisits.put(patientId, thisDateVisit);
					 AntenatalSummary dailyAntenatalSummary = (AntenatalSummary) dailySummaries.get(thisDateVisit);
					 // newAncDate = (Date) newAncMap.get(patientId);
					 Date initialVisitFormMapDate = (Date) initialVisitFormMap.get(patientId);

					 // only for mothers - not children
					 if (parentId == null || parentId == 0) {
						 // Get the visitDates for initial visit and first routine ante to calculate value for initialVisitFormMap
						 // This is done for the first record in this resultSet for each patient.
						 if (initialVisitFormMap.get(patientId) == null) {
							 // first check for date for initial Visit form.
							 ResultSet initialVisitRs = null;
							 initialVisitRs = ZEPRSUtils.getPatientEncounters(patientId.intValue(), pregnancyId.intValue(), 77, "initialvisit", conn);
							 if (!initialVisitRs.next()) {
								 // ResultSet is empty - no Initial Visit form submitted for this patient
								 int noInitialVisit = dailyAntenatalSummary.getNoInitialVisit();
								 dailyAntenatalSummary.setNoInitialVisit(noInitialVisit + 1);
								 List noInitialVisitList = dailyAntenatalSummary.getNoInitialVisitList();
								 if (noInitialVisitList == null) {
									 noInitialVisitList = new ArrayList();
								 }
								 noInitialVisitList.add(patientId);
								 dailyAntenatalSummary.setNoInitialVisitList(noInitialVisitList);
							 } else {
								 while (initialVisitRs.next()) {
									 Date iVDateVisit = initialVisitRs.getDate("date_visit");
									 initialVisitFormMap.put(patientId, iVDateVisit);
								 }
							 }
							 initialVisitRs.close();

							 // Now check the date the first routine ante form was submitted.
							 Date routineAnteDateVisit = ZEPRSUtils.getFirstVisit(patientId.intValue(), pregnancyId.intValue(), 80, conn);
							 if (routineAnteDateVisit != null) {
								 // check if there's already a value for initialVisitFormMap
								 Date iVDateVisit = (Date) initialVisitFormMap.get(patientId);
								 if (iVDateVisit != null && routineAnteDateVisit.getTime() <= iVDateVisit.getTime()) {
									 if (routineAnteDateVisit.getTime() <= iVDateVisit.getTime()) {
										 initialVisitFormMap.put(patientId, routineAnteDateVisit);
									 }
								 } else {
									 initialVisitFormMap.put(patientId, routineAnteDateVisit);
								 }
							 } else {
								 int noRoutineAnteVisits = dailyAntenatalSummary.getNoRoutineAnteVisit();
								 dailyAntenatalSummary.setNoRoutineAnteVisit(noRoutineAnteVisits + 1);
								 List noRoutineAnteVisitList = dailyAntenatalSummary.getNoRoutineAnteVisitList();
								 if (noRoutineAnteVisitList == null) {
									 noRoutineAnteVisitList = new ArrayList();
								 }
								 noRoutineAnteVisitList.add(patientId);
								 dailyAntenatalSummary.setNoRoutineAnteVisitList(noRoutineAnteVisitList);
							 }
						 }

						 // update initialVisitFormMapDate
						 initialVisitFormMapDate = (Date) initialVisitFormMap.get(patientId);
						 if (initialVisitFormMapDate == null) {
							 initialVisitFormMap.put(patientId, thisDateVisit);
							 initialVisitFormMapDate = (Date) initialVisitFormMap.get(patientId);
							 //log.error("Initial Visit missing for " + patientId);
						 }

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

						 // Has this patient had a Routine ante visit yet? Use the 1st one to calculate date for newANC visit.
						 //HashMap routineAnteMapPeriod = new HashMap();
						 //HashMap routineAnteMapAll = new HashMap();
						 // boolean newAncSet = false;
						 switch (formId.intValue()) {
						 case 77:
							 // initial visit form - enter into newAncMap if necessary
							 if (thisDateVisit.getTime() == initialVisitFormMapDate.getTime() && newAncMap.get(patientId) == null)
							 {
								 // set this visit date as the new ANC date for this period.
								 newAncMap.put(patientId, thisDateVisit);
								 // increment newANCClients
								 int newANC = dailyAntenatalSummary.getNewANCClient();
								 dailyAntenatalSummary.setNewANCClient(newANC + 1);
							 }
						 case 80:
							 // fetch the whole encounter record
							 ResultSet routineAnteRs = ZEPRSUtils.getEncounterById(encounterId, "routineante", conn);
							 while (routineAnteRs.next()) {
								 // Date rADateVisit = rs.getDate("date_visit");
								 //long rADateVisitL = rADateVisit.getTime();
								 // Integer numRoutineAnteVisitsPeriod = (Integer) routineAnteMapPeriod.get(patientId);
								 // Integer numRoutineAnteVisitsAll = (Integer) routineAnteMapAll.get(patientId);
								 /*if (newAncMap.get(patientId) == null) {
                                        // set this visit date as the new ANC date.
                                        newAncMap.put(patientId, thisDateVisit);
                                        newAncDate = thisDateVisit;
                                    }*/
								 //   if (rADateVisitL >= beginDate.getTime() && rADateVisitL <= endDate.getTime()) {
								 Long rAPatientId = routineAnteRs.getLong("patient_id");
								 Long rAPregnancyId = routineAnteRs.getLong("pregnancy_id");
								 /*Integer malariaSp = routineAnteRs.getInt("malaria_sp_dosage");
                                    Integer vermox = routineAnteRs.getInt("deworming");
                                    int sp1 = dailyAntenatalSummary.getSp1();
                                    int sp2 = dailyAntenatalSummary.getSp2();
                                    int sp3 = dailyAntenatalSummary.getSp3();
                                    int vermoxGiven = dailyAntenatalSummary.getVermoxGiven();
                                    if (malariaSp != null) {
                                        int malariaSpInt = malariaSp.intValue();
                                        switch (malariaSpInt) {
                                            case 2984:
                                                dailyAntenatalSummary.setSp1(sp1 + 1);
                                                break;
                                            case 2985:
                                                dailyAntenatalSummary.setSp2(sp2 + 1);
                                                break;
                                            case 2986:
                                                dailyAntenatalSummary.setSp3(sp3 + 1);
                                                break;
                                        }
                                    }

                                    if (vermox != null && vermox.intValue() == 1) {
                                        dailyAntenatalSummary.setVermoxGiven(vermoxGiven + 1);
                                    }*/
								 //    if (numRoutineAnteVisitsAll == null && numRoutineAnteVisitsPeriod == null)
								 if (thisDateVisit.getTime() == initialVisitFormMapDate.getTime()) {
									 if (newAncMap.get(patientId) == null) {
										 // set this visit date as the new ANC date for this period.
										 newAncMap.put(patientId, thisDateVisit);
										 // increment newANCClients
										 int newANC = dailyAntenatalSummary.getNewANCClient();
										 dailyAntenatalSummary.setNewANCClient(newANC + 1);
									 }
									 /*
                                        // Set EGA values from first visit
                                        // routineAnteMapPeriod.put(patientId, 1);
                                        Integer ega = routineAnteRs.getInt("ega_129");
                                        // Using pregnant.js as guide:
                                        // end of first trimester at 12 weeks = 84 days;
                                        // end of second trimester = 189 days
                                        int firstVisit1stTrim = dailyAntenatalSummary.getFirstVisit1stTrim();
                                        int firstVisit2ndTrim = dailyAntenatalSummary.getFirstVisit2ndTrim();
                                        int firstVisit3rdTrim = dailyAntenatalSummary.getFirstVisit3rdTrim();
                                        if (ega != null) {
                                            if (ega.intValue() <= 84) {
                                                dailyAntenatalSummary.setFirstVisit1stTrim(firstVisit1stTrim + 1);
                                            }
                                            if (ega.intValue() > 84 && ega.intValue() <= 189) {
                                                dailyAntenatalSummary.setFirstVisit2ndTrim(firstVisit2ndTrim + 1);
                                            }
                                            if (ega.intValue() > 189) {
                                                dailyAntenatalSummary.setFirstVisit3rdTrim(firstVisit3rdTrim + 1);
                                            }
                                        }*/
								 } else {
									 // increment numRoutineAnteVisits
									 int revisits = dailyAntenatalSummary.getRevisits();
									 dailyAntenatalSummary.setRevisits(revisits +1);
									 // numRoutineAnteVisitsPeriod = numRoutineAnteVisitsPeriod + 1;
									 // routineAnteMapPeriod.put(patientId, numRoutineAnteVisitsPeriod);
								 }
							 }
							 //   }
							 routineAnteRs.close();
							 break;
							 /*case 89:

                                Advice from Perry Killam 5/22/2006: "On this antenatal summary, I would only count Maternal NVP as ones
                                prescribed in the ARV form – either NVP or NVP + AZT but not AZT only or HAART or Other.  In the near
                                future, they’ll report NVP only and AZT + NVP separately, but the PMTCT group hasn’t actually finalized
                                this recommendation yet."

                                // Regimen - mother
                                // rs = ZEPRSUtils.getEncountersMother(89, "arvregimen", beginDate, endDate, siteID, conn);
                                ResultSet arvVisitRS = ZEPRSUtils.getEncounterById(encounterId, "arvregimen", conn);
                                while (arvVisitRS.next()) {
                                    Date dateVisit = arvVisitRS.getDate("date_visit");
                                    Integer phaseOfPregnancy = arvVisitRS.getInt("phaseOfPregnancy");   // 2934 = antenatal
                                    if ((deliveryDateVisit == null) || (deliveryDateVisit != null) && (dateVisit.getTime() <= deliveryDateVisit.getTime()))
                                    {
                                        if (phaseOfPregnancy == 2934) {
                                            Integer regimen = arvVisitRS.getInt("regimen");
                                            Integer referred = arvVisitRS.getInt("referred_art_clinic");
                                            Byte receivedRegimen = arvVisitRS.getByte("receivedregimen");

                                            int maternalNVP = dailyAntenatalSummary.getMaternalNVP();
                                            int maternalAzt = dailyAntenatalSummary.getMaternalAzt();
                                            int referredArt = dailyAntenatalSummary.getReferredArt();
                                            if (regimen != null) {
                                                {
                                                    if (receivedRegimen == 1) {
                                                        if (regimen.intValue() == 2938 || regimen.intValue() == 2970)
                                                        {  // either NVP or NVP + AZT
                                                            dailyAntenatalSummary.setMaternalNVP(maternalNVP + 1);
                                                        }
                                                        if (regimen.intValue() == 2937) {  // either AZT
                                                            dailyAntenatalSummary.setMaternalAzt(maternalAzt + 1);
                                                        }
                                                    }
                                                }
                                            }
                                            if (referred != null) {
                                                int referredArtInt = referred.intValue();
                                                if ((referredArtInt == 3060) || (referredArtInt == 3061) || (referredArtInt == 3062) || (referredArtInt == 3070))
                                                {
                                                    dailyAntenatalSummary.setReferredArt(referredArt + 1);
                                                }
                                            }
                                        }

                                    }
                                }
                                arvVisitRS.close();
                                break;
                            case 92:
                                ResultSet smcVisitRS = ZEPRSUtils.getEncounterById(encounterId, "safemotherhoodcare", conn);
                                while (smcVisitRS.next()) {
                                    Integer tt1Field = smcVisitRS.getInt("tt1_done");
                                    Integer tt2Field = smcVisitRS.getInt("tt2_done");
                                    Integer tt3Field = smcVisitRS.getInt("tt3_done");
                                    Integer tt4Field = smcVisitRS.getInt("tt4_done");
                                    Integer tt5Field = smcVisitRS.getInt("tt5_done");
                                    int tt1 = dailyAntenatalSummary.getTt1();
                                    int tt2 = dailyAntenatalSummary.getTt2();
                                    int tt3 = dailyAntenatalSummary.getTt3();
                                    int tt4 = dailyAntenatalSummary.getTt4();
                                    int tt5 = dailyAntenatalSummary.getTt5();
                                    int ttProtected = dailyAntenatalSummary.getTtProtected();
                                    int ttComplete = dailyAntenatalSummary.getTtComplete();
                                    if (tt1Field != null) {
                                        if (tt1Field.intValue() == 1) {
                                            dailyAntenatalSummary.setTt1(tt1 + 1);
                                        }
                                    }
                                    if (tt2Field != null) {
                                        if (tt2Field.intValue() == 1) {
                                            dailyAntenatalSummary.setTt2(tt2 + 1);
                                        }
                                    }
                                    if (tt3Field != null) {
                                        if (tt3Field.intValue() == 1) {
                                            dailyAntenatalSummary.setTt3(tt3 + 1);
                                        }
                                    }
                                    if (tt4Field != null) {
                                        if (tt4Field.intValue() == 1) {
                                            dailyAntenatalSummary.setTt4(tt4 + 1);
                                        }
                                    }
                                    if (tt5Field != null) {
                                        if (tt5Field.intValue() == 1) {
                                            dailyAntenatalSummary.setTt5(tt5 + 1);
                                        }
                                    }
                                    if ((tt5Field != null && tt5Field.intValue() == 1)) {
                                        dailyAntenatalSummary.setTtComplete(ttComplete + 1);
                                    }
                                    if ((tt2Field != null && tt2Field.intValue() == 1)) {
                                        dailyAntenatalSummary.setTtProtected(ttProtected + 1);
                                    }
                                }
                                smcVisitRS.close();
                                break;*/
						 case 91:
							 // counsel visits
							 /*if (newAncMap.get(patientId) == null) {
                                    // set this visit date as the new ANC date.
                                    newAncMap.put(patientId, thisDateVisit);
                                    newAncDate = thisDateVisit;
                                    log.debug("ANC visit missing? for patient: " + patientId);
                                }*/
							 ResultSet counselVisitRs = ZEPRSUtils.getEncounterById(encounterId, "smcounselingvisit", conn);
							 int counselledNew = dailyAntenatalSummary.getCounselledNew();
							 int counselledRevisits = dailyAntenatalSummary.getCounselledRevisits();
							 int hivTestedRevisit = dailyAntenatalSummary.getHivTestedRevisit();
							 int hivTestedNewAnc = dailyAntenatalSummary.getHivTestedNewAnc();
							 int preTestCounseled = dailyAntenatalSummary.getPreTestCounseled();

							 while (counselVisitRs.next()) {
								 byte counselled = counselVisitRs.getByte("counselled");
								 int tested = counselVisitRs.getInt("hiv_tested");
								 if (counselled == 1) {
									 dailyAntenatalSummary.setPreTestCounseled(preTestCounseled + 1);
									 if (thisDateVisit.getTime() == initialVisitFormMapDate.getTime())
									 {
										 dailyAntenatalSummary.setCounselledNew(counselledNew + 1);
									 } else {
										 dailyAntenatalSummary.setCounselledRevisits(counselledRevisits + 1);

									 }
								 }
								 switch (tested) {
								 case 2966:  // Yes
								 int hivTested = dailyAntenatalSummary.getHivTested();
								 dailyAntenatalSummary.setHivTested(hivTested + 1);
								 if (thisDateVisit.getTime() == initialVisitFormMapDate.getTime())
								 {
									 dailyAntenatalSummary.setHivTestedNewAnc(hivTestedNewAnc + 1);
								 } else {
									 dailyAntenatalSummary.setHivTestedRevisit(hivTestedRevisit + 1);
								 }
								 break;
								 case 2967:  // No
									 break;
								 case 2968:  // Patient refused to be tested.
									 int refusedTesting = dailyAntenatalSummary.getRefusedTesting();
									 dailyAntenatalSummary.setRefusedTesting(refusedTesting + 1);
									 break;
								 }
							 }
							 counselVisitRs.close();
							 break;
						 }
					 } else {
						 // Get newborn eval for child to calculate newANC
						 // Also fetches Infant NVP value
						 /*if (initialVisitFormMap.get(patientId) == null) {
                            ResultSet newbornevaltRS = ZEPRSUtils.getPatientEncounters(patientId.intValue(), pregnancyId.intValue(), 23, "newborneval", conn);
                            while (newbornevaltRS.next()) {
                                Date dateVisit = newbornevaltRS.getDate("date_visit");
                                Byte nvpGiven = newbornevaltRS.getByte("initial_nevirapine_dose");
                                // fill vlaues for both initialVisitFormMap amd newAncMap
                                initialVisitFormMap.put(patientId, dateVisit);
                                // newAncMap.put(patientId, thisDateVisit);
                                // newAncDate = thisDateVisit;
                                int infantNVP = dailyAntenatalSummary.getInfantNVP();
                                if (nvpGiven != null) {
                                    {
                                        if (nvpGiven == 1) {
                                            dailyAntenatalSummary.setInfantNVP(infantNVP + 1);
                                        }
                                    }
                                }
                            }
                            newbornevaltRS.close();
                        }*/
					 }
					 /*
                    Now process forms used both by mother and child
					  */
					 /*switch (formId.intValue()) {

                        case 87:
                            // Labs
                            // rs = ZEPRSUtils.getEncounters(87, "labtest", beginDate, endDate, siteID, conn);
                            ResultSet labVisitRS = ZEPRSUtils.getEncounterById(encounterId, "labtest", conn);
                            while (labVisitRS.next()) {
                                Integer type = labVisitRS.getInt("labType");
                                Float resultsF = labVisitRS.getFloat("resultsNumeric");
                                int hgbDone = dailyAntenatalSummary.getHgbDone();
                                int hgbLT10 = dailyAntenatalSummary.getHgbLT10();
                                if (type != null) {
                                    int typeInt = type.intValue();
                                    {
                                        if (typeInt == 2925 || typeInt == 2926) {  // hB1 or hB2
                                            dailyAntenatalSummary.setHgbDone(hgbDone + 1);
                                        }
                                    }
                                }
                                if (resultsF != null && resultsF >0) {
                                    float resultsfl = resultsF.floatValue();
                                    if (resultsfl < 10) {
                                        dailyAntenatalSummary.setHgbLT10(hgbLT10 + 1);
                                    }
                                }
                            }
                            labVisitRS.close();
                            break;
                    }*/
				 }
				 rs.close();
			 } catch (SQLException e) {
				 e.printStackTrace();
			 }




			 /* catch (ServletException e) {
                e.printStackTrace();
            }*/

			 // Safe Motherhood Care - TT card
			 //rs = ZEPRSUtils.getEncounters(92, "safemotherhoodcare", beginDate, endDate, siteID, conn);

			 // Date of test may be different from date of counsel visit, which uses the standard date_visit field.
			 // we must search on date of test field
			 try {
				 // Retrieve all Encounter records for this form
				 String sql = "SELECT * FROM encounter,smcounselingvisit " +
				 "WHERE encounter.id = smcounselingvisit.id " +
				 "AND form_id = ? AND testDate >= ? AND testDate <= ? ";
				 if (siteID != 0) {
					 sql = sql + "AND site_id = ?";
				 }
				 sql = sql + " ORDER BY testDate";
				 //log.debug("sql: " + sql);
				 //log.debug("beginDate: " + beginDate);
				 //log.debug("endDate: " + endDate);
				 //log.debug("form_id: " + 91);
				 rs = ZEPRSUtils.getEncounters(sql, 91, beginDate, endDate, siteID, conn);
				 while (rs.next()) {
					 Date testDate = rs.getDate("testDate");
					 int result = rs.getInt("hiv_test_result");
					 int tested = rs.getInt("hiv_tested");
					 Byte receivedResultsByte = rs.getByte("patient_received_results");
					 AntenatalSummary dailyAntenatalSummary = (AntenatalSummary) dailySummaries.get(testDate);
					 switch (result) {
					 case 2939:  // NR - non-reactive
						 int hivNegative = dailyAntenatalSummary.getHivNegative();
						 dailyAntenatalSummary.setHivNegative(hivNegative + 1);
						 break;
					 case 2940:  // R - reactive
						 int hivPositive = dailyAntenatalSummary.getHivPositive();
						 dailyAntenatalSummary.setHivPositive(hivPositive + 1);
						 // log.debug("hivPositive: " + dailyAntenatalSummary.getHivPositive());
						 break;
					 case 2941:  // I - indeterminate
						 int hivIndeterminate = dailyAntenatalSummary.getHivIndeterminate();
						 dailyAntenatalSummary.setHivIndeterminate(hivIndeterminate + 1);
						 break;
					 }
					 if (receivedResultsByte != null) {
						 if (receivedResultsByte == 1) {
							 int receivedResults = dailyAntenatalSummary.getReceivedResults();
							 dailyAntenatalSummary.setReceivedResults(receivedResults + 1);
						 } else if (receivedResultsByte == 0) {
							 if (tested == 2966) {
								 int uncollectedResults = dailyAntenatalSummary.getUncollectedResults();
								 dailyAntenatalSummary.setUncollectedResults(uncollectedResults + 1);
							 }
						 }
					 }
				 }
				 rs.close();
			 } catch (Exception ex) {
				 ex.printStackTrace();
			 }

			 // Regimen - child
			 /*rs = ZEPRSUtils.getEncountersChildren(89, "arvregimen", beginDate, endDate, siteID, conn);
            while (rs.next()) {
                Date thisDateVisit = rs.getDate("date_visit");
                Integer regimen = rs.getInt("regimen");
                Byte receivedRegimen = rs.getByte("receivedregimen");
                AntenatalSummary dailyAntenatalSummary = (AntenatalSummary) dailySummaries.get(thisDateVisit);
                int infantNVP = dailyAntenatalSummary.getInfantNVP();
                if (regimen != null) {
                    {
                        if (receivedRegimen == 1) {
                            dailyAntenatalSummary.setInfantNVP(infantNVP + 1);
                        }
                    }
                }
            }
            rs.close();*/

			 // RPR
			 String sql = "SELECT * FROM encounter,rpr " +
			 "WHERE encounter.id = rpr.id " +
			 "AND form_id = ? AND rpr_date >= ? AND rpr_date <= ? ";
			 if (siteID != 0) {
				 sql = sql + "AND site_id = ?";
			 }
			 sql = sql + " ORDER BY rpr_date";
			 rs = ZEPRSUtils.getEncounters(sql, 90, beginDate, endDate, siteID, conn);
			 while (rs.next()) {
				 Date thisDateVisit = rs.getDate("rpr_date");
				 Integer result = rs.getInt("rpr_result");
				 Integer treatmentDrug = rs.getInt("rpr_drug");
				 AntenatalSummary dailyAntenatalSummary = (AntenatalSummary) dailySummaries.get(thisDateVisit);
				 int rprTested = dailyAntenatalSummary.getRprTested();
				 int rprPositive = dailyAntenatalSummary.getRprPositive();
				 int rprNegative = dailyAntenatalSummary.getRprNegative();
				 int syphilisTreated = dailyAntenatalSummary.getSyphilisTreated();
				 if (result != null) {
					 if (result != 2786) {
						 dailyAntenatalSummary.setRprTested(rprTested + 1);
					 }
					 if (result == 2785) {
						 dailyAntenatalSummary.setRprPositive(rprPositive + 1);
					 } else if (result == 2784) {
						 dailyAntenatalSummary.setRprNegative(rprNegative + 1);
					 }
				 }
				 if (treatmentDrug != null && treatmentDrug > 0) {
					 dailyAntenatalSummary.setSyphilisTreated(syphilisTreated + 1);
				 }
			 }
			 rs.close();

		 } catch (Exception e) {
			 e.printStackTrace();
		 }

		 // Loop through the dailySummaries and persist them to anc_report table in zeprs database
		 Connection adminConn = null;
		 DataSource dataSource = null;
		 try {
			 dataSource = DatabaseUtils.getAdminDatasource();
			 adminConn = dataSource.getConnection();

			 Iterator  iterD = dailySummaries.keySet().iterator();
			 while (iterD.hasNext()) {
				 Date key = (Date) iterD.next();
				 AntenatalSummary antenatalSummary = (AntenatalSummary) dailySummaries.get(key);
				 AncReportDAO.save(adminConn, antenatalSummary);
			 }
		 } catch (SQLException e1) {
			 // TODO Auto-generated catch block
			 e1.printStackTrace();
		 } catch (ServletException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 } finally {
			 try {
				 System.out.println("Trying to close adminConn.");
				 adminConn.close();
				 System.out.println("Closed adminConn.");
			 } catch (SQLException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
		 }
	 }
}
