/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.report;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.report.valueobject.CurrentPregnancyStatus;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;

/**
 * @author ckelley
 */
public class ReflexRegisterReport extends ZEPRSRegister {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(ReflexRegisterReport.class);

    ArrayList<ReflexPatient> patients = new ArrayList<ReflexPatient>();
    String reportMonth;
    String reportYear;
    String type;

    /*SafeMotherhoodRegisterReport() {
        reportMonth = null;
        reportYear = null;
        // patients = new ArrayList();
    }*/

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
     * @return Returns the patients.
     */
    public ArrayList getPatients() {
        return patients;
    }

    /**
     * @param patient The patients to set.
     */
    public void addPatient(ReflexPatient patient) {
        if (patients == null) {
            patients = new ArrayList();
        }
        patients.add(patient);
    }

    public void addPatientRecords(TreeMap<Date, ReflexPatient> patientRecords) {
        if (patients == null) {
            patients = new ArrayList<ReflexPatient>();
        }
        patients.addAll(patientRecords.values());
        /*Collection visits = visitMap.values();
    	for (Iterator iterator = visits
    			.iterator(); iterator.hasNext();) {
    		ReflexPatient record = (ReflexPatient) iterator.next();
    		log.debug(record.getEncounterId() + " : " + record.getRegimenVisitDate() + " : " + record.getEncounterDate() + " : " + record.getLabType());
    	}*/
        /*Collection<ReflexPatient> visits = patientRecords.values();
        for (Iterator<ReflexPatient> iterator = visits.iterator(); iterator.hasNext();) {
			ReflexPatient visit = (ReflexPatient) iterator.next();
			patients.add(visit);
		}
        log.debug(patients.size());*/
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * query for most recent counsel visit (encounter table) by this patient in the date range.
     * query for hiv positive test date.
     * query for most recent CD4 and Hgb counts
     * query for date of HIV positive result
     *
     * @param beginDate
     * @param endDate
     * @param siteId
     */
    public void getPatientRegister(Date beginDate, Date endDate, int siteId) {

        ReflexPatient patient = null;
        ReflexPatient patientRecord = null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;

        Connection conn = null;

        try {
            conn = DatabaseUtils.getAdminConnection();

            if (endDate == null) {
                endDate = beginDate;
            }
            try {
                rs = ZEPRSUtils.getPatientsWithCounselVisits(conn, Long.valueOf(siteId), beginDate, endDate);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // For each patient, load the report class and add this patient
            // to the list
            Long patientId = (long) 0;
            try {
                while (rs.next()) {
                    try {
                        Long currentPatientId = rs.getLong("patient_id");
                        String districtPatientId = rs.getString("district_patient_id");
                        String patientName = rs.getString("patient_name");
                        Date encounterDate = rs.getDate("dateVisit");
                        Date hivTestDate = rs.getDate("testDate");
                        Integer cd4Done = null;
                        Date cd4Date = null;
                        Integer cd4Result = null;
                        Date hgbDate = null;
                        Float hgbResult = null;
                        Date regimenVisitDate = null;
                        String whoScreen = null;
                        String referralToArt = null;
                        String pmtctRegimen = null;
                        String egaWeeks = null;
                        Date dateNextVisit = null;
                        Integer encounterId;

                        //if (currentPatientId == 54599) {

                        if (patientId.longValue() != currentPatientId.longValue()) {

                            patientId = currentPatientId;
                            patient = new ReflexPatient();
                            patient.setId(currentPatientId);

                            if (hivTestDate != null) {
                                patient.setHivPositiveTestDate(hivTestDate);
                            } else {
                                try {
                                    ResultSet hivPositiveTest = ZEPRSUtils.getHivPositiveResult(conn, patientId);
                                    while (hivPositiveTest.next()) {
                                        hivTestDate = hivPositiveTest.getDate("testDate");
                                    }
                                    hivPositiveTest.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            try {
                                ResultSet arvVisit = ZEPRSUtils.getArvVisit(conn, patientId);
                                while (arvVisit.next()) {
                                    cd4Done = arvVisit.getInt("cd4tested");
                                    regimenVisitDate = arvVisit.getDate("regimen_visit_date");
                                    whoScreen = arvVisit.getString("who_stage");
                                    referralToArt = arvVisit.getString("referred_art_clinic");
                                    pmtctRegimen = arvVisit.getString("regimen");
                                }
                                arvVisit.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            String currentRegimen = pmtctRegimen;
                            StringBuffer allRegimens = new StringBuffer();

                            try {
                            	ResultSet previousRegimens = ZEPRSUtils.getPreviousArvRegimen(conn, patientId);
                            	if (previousRegimens != null) {
                            		while (previousRegimens.next()) {
                                		String previousRegimen = previousRegimens.getString("regimen");
                                		if (previousRegimen != null) {
                                			if (!previousRegimen.equals(currentRegimen)) {
                                    			allRegimens.append("<br/>").append(previousRegimen);
                                    		}
                                		}
                                	}
                            	}
                            	previousRegimens.close();
                            } catch (Exception e) {
                            	e.printStackTrace();
                            }

                            if (allRegimens.length() > 0) {
                            	if (pmtctRegimen != null) {
                            		pmtctRegimen = pmtctRegimen.concat(allRegimens.toString());
                            	} else {
                            		pmtctRegimen = allRegimens.toString();
                            	}
                            }

                            try {
                                ResultSet cd4lab = ZEPRSUtils.getRecentCD4Lab(conn, patientId);
                                while (cd4lab.next()) {
                                    cd4Date = cd4lab.getDate("dateLabRequest");
                                    cd4Result = cd4lab.getInt("cd4count");
                                    // set cd4done to 1, in case it was not requested in the most recent arvVisit.
                                    cd4Done = 1;
                                }
                                cd4lab.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                ResultSet hgbLab = ZEPRSUtils.getRecentHgbLab(conn, patientId);
                                while (hgbLab.next()) {
                                    hgbDate = hgbLab.getDate("dateLabRequest");
                                    hgbResult = hgbLab.getFloat("resultsNumeric");
                                }
                                hgbLab.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                            	CurrentPregnancyStatus currentAnte = PatientRecordUtils.getEga(conn, patientId.intValue());
                            	if (currentAnte != null) {
                            		egaWeeks = currentAnte.getEgaWeeks();
                            	}
                                /*ResultSet egaReport = ZEPRSUtils.getOneHivReport(conn, patientId);
                                while (egaReport.next()) {
                                    egaWeeks = egaReport.getString("ega_weeks");
                                }
                                egaReport.close();*/
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                ResultSet ancReport = ZEPRSUtils.getNextAncVisitDate(conn, patientId, encounterDate);
                                while (ancReport.next()) {
                                    dateNextVisit = ancReport.getDate("date_next_appt");
                                }
                                ancReport.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (districtPatientId != null) {
                                patient.setDistrictPatientId(districtPatientId);
                            }
                            if (patientName != null) {
                                patient.setPatientName(patientName);
                            }
                            patient.setEncounterDate(encounterDate);

                            if (hivTestDate != null) {
                                patient.setHivPositiveTestDate(hivTestDate);
                            }
                            /*if (cd4Done != null && cd4Done == Boolean.TRUE) {
                                patient.setCd4Done(Boolean.TRUE);
                            }*/
                            if (cd4Done != null && cd4Done == 1) {
                            	patient.setCd4Done(Boolean.TRUE);
                            } else {
                            	patient.setCd4Done(Boolean.FALSE);
                            }
                            if (cd4Date != null) {
                                patient.setCd4Date(cd4Date);
                            }
                            if (cd4Result != null && cd4Result != 0) {
                                patient.setCd4Result(cd4Result);
                            }
                            if (hgbDate != null) {
                                patient.setHgbDate(hgbDate);
                            }
                            if (hgbResult != null && hgbResult != 0.0) {
                                patient.setHgbResult(hgbResult);
                            }
                            if (regimenVisitDate != null) {
                                patient.setRegimenVisitDate(regimenVisitDate);
                            }
                            if (whoScreen != null) {
                                patient.setWhoScreen(whoScreen);
                            }
                            if (referralToArt != null) {
                                patient.setReferralToArt(referralToArt);
                            }
                            if (pmtctRegimen != null) {
                                patient.setPmtctRegimen(pmtctRegimen);
                            }
                            if (egaWeeks != null) {
                                patient.setEgaWeeks(egaWeeks);
                            }
                            if (dateNextVisit != null) {
                                patient.setDateNextVisit(dateNextVisit);
                            }

                            if (patient != null) {
                                this.addPatient(patient);
                            }

                            // get previous visit records from the cached hiv_reports table
                            if (this.getType().equals("view")) {
                                TreeMap<Date, ReflexPatient> visitMap = new TreeMap<Date, ReflexPatient>();
                                try {
                                    ResultSet hivReport = ZEPRSUtils.getHivReportRecords(conn, patientId);
                                    Date currentVisitMapDate = null;	// current date in visitMap - used for ordering the map upon insertion.
                                    while (hivReport.next()) {
                                        Date currentRecordDate = hivReport.getDate("encounter_date");
                                        /*if (prevRecordDate == null) {
                                            prevRecordDate = encounterDate;
                                        }*/
                                        Date cd4DateReport = hivReport.getDate("cd4_date");
                                        Date hgbDateReport = hivReport.getDate("hgb_date");
                                        encounterId = hivReport.getInt("encounter_id");
                                        // don't include hgb or cd4 records that are already in the initial record.
                                       if ((currentRecordDate != null) && ! currentRecordDate.equals(encounterDate)) {
                                    	    patientRecord = (ReflexPatient) visitMap.get(currentRecordDate);
                                    	    if (patientRecord == null) {
                                    	    	patientRecord = new ReflexPatient();
                                                patientRecord.setId(currentPatientId);
                                                patientRecord.setEncounterDate(currentRecordDate);
                                    	    }

                                            Integer cd4DoneReport = hivReport.getInt("cd4_done");
                                            if (cd4DoneReport != null) {
                                            	if (cd4DoneReport == 1) {
                                                	patientRecord.setCd4Done(Boolean.TRUE);
                                                } else {
                                                	patientRecord.setCd4Done(Boolean.FALSE);
                                                }
                                            }

                                            if (cd4DateReport != null) {
                                                patientRecord.setCd4Date(cd4DateReport);
                                            }
                                            Integer cd4ResultReport = hivReport.getInt("cd4_result");
                                            if (cd4ResultReport != null && cd4ResultReport > 0) {
                                                patientRecord.setCd4Result(cd4ResultReport);
                                            }

                                            if (hgbDateReport != null) {
                                                patientRecord.setHgbDate(hgbDateReport);
                                                patientRecord.setEncounterId(encounterId);
                                            }
                                            Float hgbResultReport = hivReport.getFloat("hgb_result");
                                            if (hgbResultReport != null && hgbResultReport > 0.0) {
                                                patientRecord.setHgbResult(hgbResultReport);
                                            }
                                            String whoScreenReport = hivReport.getString("who_stage");
                                            if (whoScreenReport != null) {
                                                patientRecord.setWhoScreen(whoScreenReport);
                                            }
                                            String referralToArtReport = hivReport.getString("referred_art_clinic");
                                            if (referralToArtReport != null) {
                                                patientRecord.setReferralToArt(referralToArtReport);
                                            }
                                            String pmtctRegimenReport = hivReport.getString("regimen");
                                            if (pmtctRegimenReport != null) {
                                                patientRecord.setPmtctRegimen(pmtctRegimenReport);
                                            }
                                            //Date regimenVisitDateReport = hivReport.getDate("regimen_visit_date");
                                            patientRecord.setRegimenVisitDate(currentRecordDate);
                                            /*if (regimenVisitDateReport != null) {
                                                patientRecord.setRegimenVisitDate(regimenVisitDateReport);
                                            } else {
                                            	patientRecord.setRegimenVisitDate(currentRecordDate);
                                            }*/
                                            String labType = hivReport.getString("lab_type");
                                            if (labType != null) {
                                                patientRecord.setLabType(labType);
                                            }
                                            String egaWeeksReport = hivReport.getString("ega_weeks");
                                            if (egaWeeksReport != null) {
                                            	patientRecord.setEgaWeeks(egaWeeksReport);
                                            }
                                            //this.addPatient(patientRecord);
                                            visitMap.put(currentRecordDate, patientRecord);
                                        }
                                    }
                                    // add the items in this map to the list
                                    //if (patientId == 54599) {
                                    /*	Collection visits = visitMap.values();
                                    	for (Iterator iterator = visits
                                    			.iterator(); iterator.hasNext();) {
                                    		ReflexPatient record = (ReflexPatient) iterator.next();
                                    		log.debug(record.getEncounterId() + " : " + record.getRegimenVisitDate() + " : " + record.getEncounterDate() + " : " + record.getLabType());
                                    	}*/
                                   // }
                                    this.addPatientRecords(visitMap);
                                    hivReport.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                        }

                      //  }	// end if (patientId == 54599) {
                    } catch (SQLException e) {
                        ReflexRegisterReport.log.error(e);
                    }
                }
            } catch (SQLException e) {
                ReflexRegisterReport.log.error(e);
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                rs.close();
                conn.close();
            } catch (SQLException e) {
                ReflexRegisterReport.log.error(e);
            }


        }
    }
}
