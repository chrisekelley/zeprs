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
import org.cidrz.project.zeprs.valueobject.report.PatientRegistrationClean;
import org.cidrz.webapp.dynasite.dao.DemographicsDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

/**
 * @author ckelley
 */
public class EligibleForArtReport extends ZEPRSRegister {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(EligibleForArtReport.class);

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
                //rs = ZEPRSUtils.getPatientsWithCounselVisits(conn, Long.valueOf(siteId), beginDate, endDate);
                rs = ZEPRSUtils.getLowCD4Patients(conn, Long.valueOf(siteId), beginDate, endDate);
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
                        //Date encounterDate = rs.getDate("dateVisit");
                        //Date hivTestDate = rs.getDate("testDate");

                        Date cd4Date = rs.getDate("dateLabRequest");
                        Integer cd4Result = rs.getInt("cd4count");

                        Integer cd4Done = null;
                        //Date cd4Date = null;
                        //Integer cd4Result = null;
                        Date hgbDate = null;
                        Float hgbResult = null;
                        Date regimenVisitDate = null;
                        String whoScreen = null;
                        String referralToArtFirstVisit = null;
                        String referralToArt = null;
                        String pmtctRegimen = null;
                        Integer enrolled = null;
                        String clinic = null;
                        String egaWeeks = null;
                        Date dateNextVisit = null;
                        Integer encounterId;

                        if (patientId.longValue() != currentPatientId.longValue()) {

                            patientId = currentPatientId;
                            patient = new ReflexPatient();
                            patient.setId(currentPatientId);

                            /*if (hivTestDate != null) {
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
                            }*/
                            try {
                                ResultSet arvVisit = ZEPRSUtils.getArvVisits(conn, patientId);
                                int i = 0;
                                while (arvVisit.next()) {
                                	i++;
                                    cd4Done = arvVisit.getInt("cd4tested");
                                    regimenVisitDate = arvVisit.getDate("regimen_visit_date");
                                    whoScreen = arvVisit.getString("who_stage");
                                    String referralToArtClinic = arvVisit.getString("referred_art_clinic");
                                    if (i==1) {
                                        referralToArtFirstVisit = referralToArtClinic;
                                    }
                                    if (referralToArtClinic != null && !referralToArtClinic.equals("No")) {
                                    	referralToArt = "Yes";
                                    }
                                    pmtctRegimen = arvVisit.getString("regimen");
                                    enrolled = arvVisit.getInt("enrolled_in_art");
                                    clinic = arvVisit.getString("site_name");
                                }
                                arvVisit.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            /*String currentRegimen = pmtctRegimen;
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

                            */

                            try {
								PatientRegistrationClean demographics = (PatientRegistrationClean) DemographicsDAO.getOne(conn, patientId);
								patient.setPhone(demographics.getPatientPhone());
								String address = demographics.getAddress1();
								if (demographics.getAddress2() != null) {
									if (address == null) {
										address = demographics.getAddress2();
									} else {
										address.concat("<br/>").concat(demographics.getAddress2());
									}
								}
								patient.setAddress(address);

								String contact = demographics.getForenamesHusband();
								if (contact == null) {
									contact = demographics.getForenameEemergContact();
								}
								patient.setContact(contact);

								String contactPhone = demographics.getTelNoHusband();
								if (contactPhone == null) {
									contactPhone = demographics.getTelNoEmergContact();
								}
								patient.setContactPhone(contactPhone);
							} catch (ObjectNotFoundException e) {
								log.debug("Demographics not found for patient id = " + patientId);
							}

                            /*try {
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
                            }*/

                            if (districtPatientId != null) {
                                patient.setDistrictPatientId(districtPatientId);
                            }
                            if (patientName != null) {
                                patient.setPatientName(patientName);
                            }
                            //patient.setEncounterDate(encounterDate);

                            /*if (hivTestDate != null) {
                                patient.setHivPositiveTestDate(hivTestDate);
                            }*/
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
                            if (referralToArtFirstVisit != null) {
                            	patient.setReferralToArtFirstVisit(referralToArtFirstVisit);
                            }
                            if (pmtctRegimen != null) {
                                patient.setPmtctRegimen(pmtctRegimen);
                            }
                            if (enrolled != null) {
                            	if (enrolled == 1) {
                            		patient.setEnrolled("Yes");
                            	} else {
                            		patient.setEnrolled("No");
                            	}
                            }
                            if (clinic != null) {
                            	patient.setArtClinic(clinic);
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
                        }

                      //  }	// end if (patientId == 54599) {
                    } catch (SQLException e) {
                        EligibleForArtReport.log.error(e);
                    }
                }
            } catch (SQLException e) {
                EligibleForArtReport.log.error(e);
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                rs.close();
                conn.close();
            } catch (SQLException e) {
                EligibleForArtReport.log.error(e);
            }


        }
    }
}
