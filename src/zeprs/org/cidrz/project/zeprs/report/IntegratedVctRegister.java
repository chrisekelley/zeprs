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
 * Created on Mar 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.report.valueobject.IntegratedVctPatient;
import org.cidrz.project.zeprs.valueobject.gen.PatientRegistration;
import org.cidrz.project.zeprs.valueobject.gen.SafeMotherhoodCare;
import org.cidrz.project.zeprs.valueobject.gen.SmCounselingVisit;
import org.cidrz.webapp.dynasite.dao.DemographicsDAO;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class IntegratedVctRegister extends ZEPRSRegister {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(IntegratedVctRegister.class);

    ArrayList patients = new ArrayList();
    String reportMonth;
    String reportYear;

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
    public void addPatient(IntegratedVctPatient patient) {
        patients.add(patient);
    }

    public void getPatientRegister(Date beginDate, Date endDate, int siteId) {



        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;

        try {

            // First, get the unique list of patients who visited during the
            // time period in question
            Connection conn = null;
            conn = ZEPRSUtils.getZEPRSConnection();
            rs = ZEPRSUtils.getMCHMothers(beginDate, endDate, siteId, conn);
            String currentType = this.getType();

            // For each patient, load the report class and add this patient
            // to the list

            while (rs.next()) {
                int patientID = rs.getInt("patient_id");
                IntegratedVctPatient vctPatient;
                vctPatient = loadPatient(patientID, beginDate, endDate, siteId, currentType, conn);
                if (this.getType() != null && this.getType().equals("ignoreRepeatNoTest")) {
                     if (vctPatient.isRepeatAncClient()) {
                         if (vctPatient.isTookTestRevisit()) {
                              this.addPatient(vctPatient);
                         }
                     } else {
                          this.addPatient(vctPatient);
                     }
                } else {
                    this.addPatient(vctPatient);
                }

            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IntegratedVctPatient loadPatient(int patientID, Date beginDate, Date endDate, int siteID, String type, Connection conn) {

        Long patientId = (long) patientID;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsStruc = null;
        String sql = null;
        int formID = 0;
        IntegratedVctPatient vctPatient = new IntegratedVctPatient();
        vctPatient.setPatientId(patientId);

        /**
         * First get the current pregnancy id
         */
        Long pregnancyId = null;
        SessionPatient currPreg = null;
        try {
            currPreg = PatientRecordUtils.getSessionPatientPregnancy(conn, (long) patientID);
            pregnancyId = currPreg.getCurrentPregnancyId();
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } catch (ObjectNotFoundException e) {
            log.error(e);
        }
        vctPatient.setCurrentPregnancyId(pregnancyId);

        /**
         * Is this a new patient?
         */
        try {
            List visits = EncountersDAO.getVisits(conn, patientId, pregnancyId);
            if (visits.size() > 1) {
                vctPatient.setRepeatAncClient(true);
            } else {
                vctPatient.setNewAncClient(true);
            }
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        }


        /**
         * We are *not* checking id patient already took test before first visit?
         */
        SafeMotherhoodCare smc = null;
        try {
            smc = (SafeMotherhoodCare) EncountersDAO.getOne(conn, patientId, pregnancyId, (long) 92);
            /*Byte tested = smc.getField1675();
            Integer result = smc.getField1866();
            if (tested != null && tested.byteValue() == 1) {
                if (result != null) {
                    switch (result) {
                        case 2939:
                            vctPatient.setTestResult("NR");
                            break;
                        case 2940:
                            vctPatient.setTestResult("R");
                            break;
                        case 2941:
                            vctPatient.setTestResult("I");
                            break;
                    }
                }
            }*/
        } catch (IOException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } catch (ObjectNotFoundException e) {
            // create new sm care instance.
            smc = new SafeMotherhoodCare();
        } catch (ClassNotFoundException e) {
            log.error(e);
        } catch (NullPointerException e) {
            log.error(e);
        }

        /**
         * Did patient take test first visit?
         */

       // if (smc != null) {
            try {
                Date smcVisitDate = smc.getDateVisit();
                List counselVisits = EncountersDAO.getAll(conn, patientId, pregnancyId, (long) 91, SmCounselingVisit.class);
                for (int i = 0; i < counselVisits.size(); i++) {
                    SmCounselingVisit counselingVisit = (SmCounselingVisit) counselVisits.get(i);
                    Date counselVisitDate = counselingVisit.getDateVisit();
                    Integer tested = counselingVisit.getField1931();
                    Integer result = counselingVisit.getField1866();
                    if (result != null) {
                        switch (result) {
                            case 2939:
                                vctPatient.setTestResult("NR");
                                break;
                            case 2940:
                                vctPatient.setTestResult("R");
                                break;
                            case 2941:
                                vctPatient.setTestResult("I");
                                break;
                        }
                    }

                    if (tested != null && smcVisitDate != null) {
                        if (tested == 2968) {
                           vctPatient.setTestResult("TR");
                        }
                        if (counselVisitDate.toString().equals(smcVisitDate.toString())) {
                            if ((tested) == 2966) {
                                vctPatient.setTookTestFirstVisit(true);
                            }
                        } else {
                            if ((tested) == 2966) {
                                vctPatient.setTookTestRevisit(true);
                            }
                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
      //  }

        // Find encounter id for Patient Registration form
        try {
            Long encounterId = null;
            try {
                encounterId = DemographicsDAO.getDemographicsId(conn, patientId);
            } catch (SQLException e) {
                log.error(e);
            } catch (ServletException e) {
                log.error(e);
            } catch (ObjectNotFoundException e) {
                log.debug("Unable to fetch Demographics for patient id: " + patientId + " while running IntegratedVCTRegister.");
                vctPatient.setSmRegisterNumber("unknown");
            }
            if (encounterId != null) {
                PatientRegistration pr = (PatientRegistration) EncountersDAO.getOneById(conn, encounterId, (long) 1, PatientRegistration.class);
                String smNumber = null;
                smNumber = pr.getField1513();
                if (smNumber != null) {
                    vctPatient.setSmRegisterNumber(smNumber);
                } else {
                    vctPatient.setSmRegisterNumber("unknown");
                }
                Date birthDate = pr.getField17();
                if (birthDate != null) {
                    int age = ZEPRSUtils.getCurrentAge(birthDate);
                    if (age <= 4) {
                        vctPatient.setAgeGroupUnder5(true);
                    } else if (age >= 1 && age <= 14) {
                        vctPatient.setAgeGroup15_19(true);
                    } else if (age >= 15 && age <= 19) {
                        vctPatient.setAgeGroup15_19(true);
                    } else if (age >= 20 && age <= 24) {
                        vctPatient.setAgeGroup20_24(true);
                    } else if (age >= 25 && age <= 34) {
                        vctPatient.setAgeGroup25_34(true);
                    } else if (age >= 35 && age <= 39) {
                        vctPatient.setAgeGroup35_39(true);
                    } else if (age >= 40 && age <= 44) {
                        vctPatient.setAgeGroup40_44(true);
                    } else if (age >= 45 && age <= 49) {
                        vctPatient.setAgeGroup45_49(true);
                    } else {
                        vctPatient.setAgeGroup50plus(true);
                    }
                }
                Integer maritalStatus = pr.getField10();
                if (maritalStatus != null) {
                    switch (maritalStatus) {
                        case 4:
                            vctPatient.setMaritalStatus_Single_F(true);
                            break;
                        case 8:
                            vctPatient.setMaritalStatus_Married_F(true);
                            break;
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return vctPatient;
    }
}
