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
import org.apache.commons.beanutils.BeanUtils;
import org.cidrz.project.zeprs.report.valueobject.CurrentPregnancyStatus;
import org.cidrz.project.zeprs.report.valueobject.NeonatalMortalityPatient;
import org.cidrz.project.zeprs.report.valueobject.SafeMotherhoodEncounter;
import org.cidrz.project.zeprs.valueobject.*;
import org.cidrz.project.zeprs.valueobject.gen.PregnancyDating;
import org.cidrz.project.zeprs.valueobject.gen.DeliverySum;
import org.cidrz.project.zeprs.valueobject.gen.Rpr;
import org.cidrz.project.zeprs.valueobject.report.PatientRegistrationClean;
import org.cidrz.project.zeprs.valueobject.report.gen.ProbLaborReport;
import org.cidrz.project.zeprs.valueobject.report.gen.LatentFirstStageLabourReport;
import org.cidrz.project.zeprs.valueobject.report.gen.NewbornEvalReport;
import org.cidrz.webapp.dynasite.dao.DemographicsDAO;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class NeonatalMortality extends ZEPRSRegister {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(NeonatalMortality.class);

    ArrayList patients = new ArrayList();

    public ArrayList getPatients() {
        return patients;
    }

    public void setPatients(ArrayList patients) {
        this.patients = patients;
    }

    /**
     * @param patient The patients to set.
     */
    public void addPatient(NeonatalMortalityPatient patient) {
        patients.add(patient);
    }

    public void getPatientRegister(Date beginDate, Date endDate, int siteId) {

        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = ZEPRSUtils.getZEPRSConnection();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        // First, get the unique list of infants who died during the time period in question
        // by looping through patient table to check is_dead
        try {
            rs = ZEPRSSharedItems.getNeonatalDeaths(beginDate, endDate, siteId, conn);
            // For each patient, load the report class and add this patient to the list
            while (rs.next()) {
                int patientID = rs.getInt("patient_id");
                int parentId = rs.getInt("parent_id");
                Long labourAdmissionEncounterId = rs.getLong("labour_admission_encounter_id");
                int currentPregnancyId = rs.getInt("pregnancy_id");
                NeonatalMortalityPatient patient = null;
                patient = loadPatient(patientID, parentId, labourAdmissionEncounterId, currentPregnancyId, beginDate, endDate, siteId, conn);
                if (patient != null) {
                    this.addPatient(patient);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public NeonatalMortalityPatient loadPatient(int patientID, int parentId, Long labourAdmissionEncounterId, int currentPregnancyId, Date beginDate, Date endDate, int siteID, Connection conn) {

        Long patientId = (long) patientID;
        NeonatalMortalityPatient patient = new NeonatalMortalityPatient();
        patient.setPatientId(patientId);
        patient.setParentId(Long.valueOf(parentId));
        patient.setCurrentPregnancyId(Long.valueOf(currentPregnancyId));
        patient.setLabourAdmissionEncounterId(labourAdmissionEncounterId);

        // load the infant data
        PatientRegistrationClean infantData = null;
        try {
            infantData = (PatientRegistrationClean) DemographicsDAO.getOne(conn, patientId);
            patient.setInfantData(infantData);
            /*String birthDate = infantData.getBirthDate();
            if (birthDate != null) {
                int age = ZEPRSUtils.getCurrentAge(Date.valueOf(birthDate));
                patient.getInfantData().setCurrentAge(age);
            }*/
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Long motherId = patient.getParentId();

        // load the mother data
        PatientRegistrationClean motherData = null;
        try {
            motherData = (PatientRegistrationClean) DemographicsDAO.getOne(conn, motherId);
            patient.setMotherData(motherData);
            String birthDate = motherData.getBirthDate();
            if (birthDate != null) {
                int age = ZEPRSUtils.getCurrentAge(Date.valueOf(birthDate));
                patient.getMotherData().setCurrentAge(age);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * LMP, EDD - CurrentPregnancyStatus is a convenient container for this data.
         */
        CurrentPregnancyStatus currentPregnancyStatus = null;
        try {
            currentPregnancyStatus = PatientRecordUtils.getEga(conn, motherId.intValue());
            patient.setCurrentPregnancyStatus(currentPregnancyStatus);
        } catch (SQLException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (ObjectNotFoundException e) {
            log.error(e);
        }

        /**
         * Parity
         */

        try {
            Integer parity = PatientRecordUtils.getParity(conn, motherId);
            patient.setMotherParity(parity);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        /**
         *  numberMotherAncVisits
         */


        int numberMotherAncVisits = ZEPRSUtils.getEncountersCount(80, "routineante", motherId.intValue(), conn);
        patient.setNumberMotherAncVisits(numberMotherAncVisits);

        /**
         * motherAncClinics
         */
        ResultSet rs = null;
        StringBuffer motherAncClinics = new StringBuffer();
        HashMap motherAncClinicsMap = new HashMap();

        try {
            rs = ZEPRSUtils.getEncounterClinics(80, "routineante", beginDate, endDate, motherId.intValue(), conn);
            while (rs.next()) {
                String motherAncClinic = rs.getString("site_name");
                String motherAncClinicId = rs.getString("site_id");
                motherAncClinicsMap.put(motherAncClinicId, motherAncClinic);
                /*if (rs.isFirst()) {
                    motherAncClinics.append(motherAncClinic);
                    //motherAncClinics = motherAncClinic;
                } else {
                    motherAncClinics.append(", " + motherAncClinic);
                   // motherAncClinics = motherAncClinics + ", " + motherAncClinic;
                }*/
            }
            Set clinics =  motherAncClinicsMap.entrySet();
            boolean count = false;
            for (Object clinic : clinics) {
                Map.Entry entry = (Map.Entry) clinic;
                String clinicName = (String) entry.getValue();
                if (!count) {
                    count = true;
                    motherAncClinics.append(clinicName);
                } else {
                    motherAncClinics.append(", ");
                    motherAncClinics.append(clinicName);
                }
            }
            patient.setMotherAncClinics(motherAncClinics.toString());
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // admissionLabour - find the encounter that signals the beginning of labour - dilatation >=4.
        // This can be either observations or problem/labour visit. We get this value from pregnancy table

        //Long labourAdmissionEncounterId = patient.getLabourAdmissionEncounterId();


        try {
        	EncounterData encounter = null;
        	encounter = (EncounterData) EncountersDAO.getOneById(conn, labourAdmissionEncounterId);
        	if (encounter != null) {
        		patient.setAdmissionLabour(encounter.getDateVisit());
        		// tease out the correct form
        		if (encounter.getFormId().intValue() == 65) {
        			ProbLaborReport probLabor = (ProbLaborReport) EncountersDAO.getOneReportById(conn, labourAdmissionEncounterId, encounter.getFormId(), ProbLaborReport.class);
        			Form encounterForm = (Form) DynaSiteObjects.getForms().get((long) 65);
        			Map encMap = PatientRecordUtils.getEncounterMap(encounterForm, probLabor, "starSchemaNameR");
        			probLabor.setEncounterMap(encMap);
        			try {
        				BeanUtils.copyProperties(probLabor, probLabor.getEncounterMap());
        			} catch (IllegalAccessException e) {
        				log.error(e);
        			} catch (InvocationTargetException e) {
        				log.error(e);
        			}

        			if (probLabor.getBp_systolic_224R() != null) {
        				patient.setBpMother(probLabor.getBp_systolic_224R() + "/" + probLabor.getBp_diastolic_225R());
        			}

        			if (probLabor.getCervix_dilatation325R() != null) {
        				patient.setDilatation(Integer.valueOf(probLabor.getCervix_dilatation325R()));
        			}
        			patient.setFhr(probLabor.getFoetal_heart_rate_abd_palp_318R());
        			patient.setNurseAdmitting(probLabor.getCreatedByName());
        		} else if (encounter.getFormId().intValue() == 17) {
        			LatentFirstStageLabourReport observations = (LatentFirstStageLabourReport) EncountersDAO.getOneReportById(conn, labourAdmissionEncounterId, encounter.getFormId(), LatentFirstStageLabourReport.class);
        			Form encounterForm = (Form) DynaSiteObjects.getForms().get((long) 17);
        			Map encMap = PatientRecordUtils.getEncounterMap(encounterForm, observations, "starSchemaNameR");
        			observations.setEncounterMap(encMap);
        			try {
        				BeanUtils.copyProperties(observations, observations.getEncounterMap());
        			} catch (IllegalAccessException e) {
        				log.error(e);
        			} catch (InvocationTargetException e) {
        				log.error(e);
        			}

        			if (observations.getBp_systolic_224R() != null) {
        				patient.setBpMother(observations.getBp_systolic_224R() + "/" + observations.getBp_diastolic_225R());
        			}

        			if (observations.getCervix_dilatation325R() != null) {
        				patient.setDilatation(Integer.valueOf(observations.getCervix_dilatation325R()));
        			}
        			patient.setFhr(observations.getFoetal_heart_rate_230R());
        			patient.setNurseAdmitting(observations.getCreatedByName());
        		}
        	} else {
        		 //log.debug("Missing labourAdmissionEncounterId in NeonatalMortality report - motherId: " + parentId + " newbornId: " + patientId);
        	}
        } catch (ServletException e) {
        	e.printStackTrace();
        } catch (SQLException e) {
        	e.printStackTrace();
        } catch (ObjectNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }



        // get EGA of first pregnancyDating
        try {
            List pregDatingList = EncountersDAO.getAll(conn, motherId, (long) currentPregnancyId, (long) 82,PregnancyDating.class );
            PregnancyDating pregnancyDating = (PregnancyDating) pregDatingList.get(0);
            Integer firstEga = pregnancyDating.getField129();
            int days = firstEga.intValue() % 7;
            int weeks = firstEga.intValue() / 7;
            patient.setEgaFirstVisit(weeks + ", " + days + "/7");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Birth weight, date, time
        try {
            NewbornEvalReport newbornEval = (NewbornEvalReport) EncountersDAO.getOneReports(conn, patientId, patient.getCurrentPregnancyId(), (long) 23);
            String birthweight = newbornEval.getWeight_at_birth_491R();
            String birthDate = newbornEval.getDate_of_birthR();
            String birthTime = newbornEval.getTime_of_birth_1514R();
            String aliveSb = newbornEval.getAlive_sb_493R();
            String neonatalDeath = newbornEval.getNeonatal_dea_1180R();
            String egaInfant = newbornEval.getEga_weeksR();
            StringBuffer outcome = new StringBuffer();
            if (aliveSb != null) {
                outcome.append(aliveSb);
            }
             if (neonatalDeath != null) {
                outcome.append("neonatalDeath: ").append(neonatalDeath);
            }
            if (birthweight != null) {
            patient.setBirthweight(Float.valueOf(birthweight));
            }

            patient.setBirthDate(Date.valueOf(birthDate));
            patient.setBirthtime(Time.valueOf(birthTime));
            patient.setOutcome(outcome.toString());

            int days = Integer.valueOf(egaInfant) % 7;
            int weeks = Integer.valueOf(egaInfant) / 7;
            patient.setEgaInfant(weeks + ", " + days + "/7");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

/*
        // nurseDelivering - use value from deliverysum
        try {
            DeliverySum deliverySum = (DeliverySum) EncountersDAO.getOne(conn, motherId, patient.getCurrentPregnancyId(), (long) 66);
            patient.setNurseDelivering(deliverySum.getField1961());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        // rpr

        List rprVisits = null;
        StringBuffer rprText = new StringBuffer();
        try {
            rprVisits = EncountersDAO.getAll(conn, motherId, (long) currentPregnancyId, (long) 90, Rpr.class);
            for (int i = 0; i < rprVisits.size(); i++) {
                Rpr rprVisit = (Rpr) rprVisits.get(i);
                Date dateVisit = rprVisit.getDateVisit();
                Integer rprResult = rprVisit.getField1563();
                Integer rprDrugInt = rprVisit.getField1565();
                String staffName = rprVisit.getLastModifiedByName();
                if (i > 0) {
                	rprText.append("<br/>");
                }
                if (rprResult != null) {
                	//rprText.append("<a href=\"rpr.do;jsessionid=${pageContext.request.session.id}?patientId="+patientId+"&pregnancyId="+currentPregnancyId+"\">");
                    switch (i) {
                        case 0:
                            if (rprResult == 2784) {
                            	rprText.append("RPR1: neg");
                            } else if (rprResult == 2785) {
                            	rprText.append("RPR1: pos");
                            } else if (rprResult == 2786) {
                            	rprText.append("RPR1: N/A");
                            }
                            break;
                        case 1:
                            if (rprResult == 2784) {
                            	rprText.append("RPR2: neg");
                            } else if (rprResult == 2785) {
                            	rprText.append("RPR2: pos");
                            } else if (rprResult == 2786) {
                            	rprText.append("RPR2: N/A");
                            }
                            break;
                    }
                }
                if (rprDrugInt != null) {
                	rprText.append(", treated");
                }
                //rprText.append("</a>");
            }
            if (rprVisits.size() > 0) {
                patient.setRprResults(rprText.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }


}
