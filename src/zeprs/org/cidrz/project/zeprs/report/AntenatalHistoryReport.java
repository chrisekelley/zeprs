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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.report.valueobject.AntenatalHistory;
import org.cidrz.project.zeprs.report.valueobject.AntenatalReport;
import org.cidrz.project.zeprs.report.valueobject.CurrentPregnancyStatus;
import org.cidrz.project.zeprs.report.valueobject.DrugsDispensed;
import org.cidrz.project.zeprs.report.valueobject.HivStamp;
import org.cidrz.project.zeprs.report.valueobject.PregnancyReport;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.ProbLabor;
import org.cidrz.project.zeprs.valueobject.report.PatientRegistrationClean;
import org.cidrz.project.zeprs.valueobject.report.gen.CurrentMedicineReport;
import org.cidrz.project.zeprs.valueobject.report.gen.DeliverySumReport;
import org.cidrz.project.zeprs.valueobject.report.gen.DrugInterventionReport;
import org.cidrz.project.zeprs.valueobject.report.gen.InitialVisitReport;
import org.cidrz.project.zeprs.valueobject.report.gen.LabTestReport;
import org.cidrz.project.zeprs.valueobject.report.gen.MaternalDischargeReport;
import org.cidrz.project.zeprs.valueobject.report.gen.MedSurgHistReport;
import org.cidrz.project.zeprs.valueobject.report.gen.PrevPregnanciesReport;
import org.cidrz.project.zeprs.valueobject.report.gen.RprReport;
import org.cidrz.project.zeprs.valueobject.report.gen.SafeMotherhoodCareReport;
import org.cidrz.webapp.dynasite.dao.DemographicsDAO;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.LabTestDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.PregnancyDAO;
import org.cidrz.webapp.dynasite.dao.reports.ReportDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.FieldEnumeration;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.Patient;

/**
 * Generates the ANC Card.
 * @author CEKelley
 */
public class AntenatalHistoryReport {

    private static Log log = LogFactory.getFactory().getInstance(AntenatalHistoryReport.class);

    /**
     * Queries db and assembles data for ANC card.
     * @param conn
     * @param patientId
     * @return AntenatalHistory
     */
    public static AntenatalHistory generateAntenatalHistory(Connection conn, Long patientId) {

        AntenatalHistory ante = new AntenatalHistory();
        Long pregnancyId;
        Long currentPregnancyID = PatientRecordUtils.getPregnancyId(conn, patientId);
        pregnancyId = currentPregnancyID;
        PatientRegistrationClean pr = null;
        try {
            pr = (PatientRegistrationClean) DemographicsDAO.getOne(conn, patientId);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (pr.getBirthDate() != null) {
            Date birthDate = Date.valueOf(pr.getBirthDate());
            int age = ZEPRSUtils.getCurrentAge(birthDate);
            pr.setCurrentAge(age);
        }

        if (pr.getUthReferralTypeId() != null) {
            int uthReferralTypeId = pr.getUthReferralTypeId();
            FieldEnumeration fenum = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get(Long.valueOf(uthReferralTypeId));
            pr.setUthReferralType(fenum.getEnumeration());
        }

        try {
            Patient patient = PatientDAO.getOne(conn, patientId);
            Integer height = patient.getHeight();
            pr.setHeight(height);
            String zeprsId = patient.getDistrictPatientid();
            if (pr.getPatientIdNumber() == null) {
                pr.setPatientIdNumber(zeprsId);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

        ante.setPatientRegistration(pr);

        Date deliveryDateVisit = null;
        DeliverySumReport deliverySum = null;
		try {
			deliverySum = (DeliverySumReport) EncountersDAO.getResolvedOne(conn, patientId, pregnancyId, (long) 66, DeliverySumReport.class);
			deliveryDateVisit = deliverySum.getDateVisit();
		} catch (ObjectNotFoundException e) {
			// Have not submitted DeliverySum yet.
		}
        CurrentPregnancyStatus currentPregnancyStatus = null;
        try {
            currentPregnancyStatus = PatientRecordUtils.getEga(conn, patientId.intValue());
        } catch (SQLException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (ObjectNotFoundException e) {
            log.error(e);
        }

        if (currentPregnancyStatus == null) {
            currentPregnancyStatus = new CurrentPregnancyStatus();
        }
        try {
            Integer parity = PatientRecordUtils.getParity(conn, patientId);
            currentPregnancyStatus.setParity(parity);
            Integer gravida = PatientRecordUtils.getGravida(conn, patientId);
            currentPregnancyStatus.setGravida(gravida);
        } catch (SQLException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        }


        InitialVisitReport initialVisit;
		try {
			initialVisit = (InitialVisitReport) EncountersDAO.getResolvedOne(conn, patientId, pregnancyId, (long) 77, InitialVisitReport.class);
	        ante.setInitialVisit(initialVisit);
		} catch (ObjectNotFoundException e1) {
			//log.debug(e1);
		}

        List<LabTestReport> labVisits;
        List<LabTestReport> labTests = new ArrayList<LabTestReport>();
        labVisits = ReportDAO.getAll(conn, patientId, new Long("87"), LabTestReport.class);
        for (int i = 0; i < labVisits.size(); i++) {
        	LabTestReport labTestReport = (LabTestReport) labVisits.get(i);
        	Date labDateVisit = labTestReport.getDateVisit();
        	boolean addToLabTests = false;
        	if (deliveryDateVisit != null) {
        		if (labDateVisit != null) {
        			if (labDateVisit.getTime() < deliveryDateVisit.getTime()) {
        				addToLabTests = true;
        			}
        		} else {
        			addToLabTests = true;
        		}
        	} else {
        		addToLabTests = true;
        	}
        	if (addToLabTests) {
        		String test = labTestReport.getLabTypeR();
        		String result = labTestReport.getResultsR();
        		String dateResults = labTestReport.getDateLabResultsR();
        		if (dateResults != null) {
    				labTestReport.setDateLabResultsR(dateResults.replace("00:00:00.0", ""));
        		}
        		if (test != null && test.equals("Blood Type")) {
        			currentPregnancyStatus.setAboGroup(result);
        		} else if (test != null && test.equals("Rhesus Status")) {
        			currentPregnancyStatus.setRhesus(result);
        		} else if (test != null && test.equals("CD4 Count")) {
        			labTestReport.setResultsR(labTestReport.getCd4countR());
        		}

        		if (labTestReport.getExtended_test_idR() != null) {
        			Long extendedLabId = Long.valueOf(labTestReport.getExtended_test_idR());
            		String labType = labTestReport.getLabTypeR();
            		String extendedResults = LabTestDAO.getExtendedLabResults(conn, extendedLabId, labType);
            		if (extendedResults != null) {
        				labTestReport.setResultsR(extendedResults);
            		}
        		}
            	labTests.add(labTestReport);
        	}
        }

        ante.setLabTests(labTests);

        /*if (labVisits.size() < 2) {
            int addLabVisits = 2 - labVisits.size();
            if (addLabVisits > 0) {
                for (int k = 0; k < addLabVisits; k++) {
                    LabTestReport addLabVisit = new LabTestReport();
                    labVisits.add(addLabVisit);
                }
            }
        }*/

        List rprVisits = ReportDAO.getAll(conn, patientId, new Long("90"), RprReport.class);
        if (rprVisits.size() < 2) {
            int addRprVisits = 2 - rprVisits.size();
            if (addRprVisits > 0) {
                for (int k = 0; k < addRprVisits; k++) {
                    RprReport addRprVisit = new RprReport();
                    rprVisits.add(addRprVisit);
                }
            }
        }
        ante.setRprScreens(rprVisits);

        // Initialise the drugs list which will populate ante
        List drugs = new ArrayList();
        List drugInterventions = ReportDAO.getAll(conn, patientId, new Long("88"), DrugInterventionReport.class);
        for (int i = 0; i < drugInterventions.size(); i++) {
        	DrugInterventionReport drugInterventionReport = (DrugInterventionReport) drugInterventions.get(i);
        	Date labDateVisit = drugInterventionReport.getDateVisit();
        	boolean addToDrugs = false;
        	if (deliveryDateVisit != null) {
        		if (labDateVisit != null) {
        			if (labDateVisit.getTime() < deliveryDateVisit.getTime()) {
        				addToDrugs = true;
        			}
        		} else {
        			addToDrugs = true;
        		}
        	} else {
        		addToDrugs = true;
        	}
        	if (addToDrugs) {
        		DrugsDispensed drugsDispensed = null;
        		drugsDispensed = ZEPRSUtils.assembleDrugsDispensed(drugInterventionReport);
    			if (drugsDispensed != null) {
    				drugs.add(drugsDispensed);
    			}
        	}
        }

        ante.setCurrentPregnancyStatus(currentPregnancyStatus);

        SafeMotherhoodCareReport smc = null;
        HivStamp hivStamp = null;
        try {
            smc = (SafeMotherhoodCareReport) EncountersDAO.getResolvedOne(conn, patientId, (long) 92, SafeMotherhoodCareReport.class);
            ante.setSmc(smc);
            hivStamp = PatientRecordUtils.getHivStamp(conn, patientId, pregnancyId, smc);
            ante.setHivStamp(hivStamp);
        } catch (ObjectNotFoundException e) {
        	// Even though SM Care has not been submitted, other forms may provide data to the hivStamp.
        	smc = new SafeMotherhoodCareReport();
        	hivStamp = new HivStamp();
        	// cekelley 01122010
        	ante.setSmc(smc);
            hivStamp = PatientRecordUtils.getHivStamp(conn, patientId, pregnancyId, smc);
        	ante.setHivStamp(hivStamp);
        }

        MedSurgHistReport medHist;
        try {
            medHist = (MedSurgHistReport) EncountersDAO.getOneReport(conn, patientId, (long) 70, MedSurgHistReport.class);
            StringBuffer medSurgHist = populateStringFromRecord(hivStamp, medHist, 70);
            try {
                BeanUtils.copyProperties(medHist, medHist.getEncounterMap());
            } catch (IllegalAccessException e) {
                log.error(e);
            } catch (InvocationTargetException e) {
                log.error(e);
            }
            if (medHist != null) {
                // ante.setMedicalSugHistory(medHist);
                ante.setMedicalSugHistoryStr(medSurgHist.toString());
            }
        } catch (IOException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } catch (ObjectNotFoundException e) {
             // it's ok
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
        	StringBuffer currentMedicineSBuf = new StringBuffer();
        	List<CurrentMedicineReport> records = EncountersDAO.getAllPregsReport(conn, patientId, (long) 55, CurrentMedicineReport.class);
        	for (CurrentMedicineReport currentMedicineReport : records) {
            	StringBuffer currentMedicineRecorSBuf = populateStringFromRecord(hivStamp, currentMedicineReport, 55);
            	currentMedicineSBuf.append(currentMedicineRecorSBuf.toString()).append("; ");
			}
        	if (currentMedicineSBuf.length() > 0) {
        		ante.setCurrentMedicineStr(currentMedicineSBuf.toString());
        	}
        } catch (IOException e) {
        	log.error(e);
        } catch (ServletException e) {
        	log.error(e);
        } catch (SQLException e) {
        	log.error(e);
        }

        HashMap probLaborMap = null;
        List probLabourVisits =  null;
        try {
            probLabourVisits = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("65"), ProbLabor.class);
            if (probLabourVisits.size() > 0) {
                probLaborMap = new HashMap();
                Form encounterForm = (Form) DynaSiteObjects.getForms().get((long) 65);
                PageItem diagnosisBegin = (PageItem) DynaSiteObjects.getPageItems().get(new Long("2711"));
                PageItem diagnosisEnd = (PageItem) DynaSiteObjects.getPageItems().get(new Long("2712"));
                int startField = diagnosisBegin.getDisplayOrder(); // 128;    // False labour -1
                int endField = diagnosisEnd.getDisplayOrder();    // 149;    // CNS Normal +1
                Set pageItems = encounterForm.getPageItems();
                for (int i = 0; i < probLabourVisits.size(); i++) {
                    ProbLabor probLaborVisit = (ProbLabor) probLabourVisits.get(i);
                    Date dateVisit = probLaborVisit.getDateVisit();
                    List visits = null;
                     if (probLaborMap.size() >0) {
                         visits = (List) probLaborMap.get(dateVisit);
                         if (visits == null) {
                             visits = new ArrayList();
                         }
                    } else {
                         visits = new ArrayList();
                     }
                    StringBuffer sbuf = new StringBuffer();
                    StringBuffer sbufDis = new StringBuffer();
                    Map encMap = PatientRecordUtils.getEncounterMap(encounterForm, probLaborVisit, "field");
                    // fetch display orders for table headers:

                    for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
                        PageItem pageItem = (PageItem) iterator.next();
                        FormField formField = pageItem.getForm_field();
                        Long formFieldId = formField.getId();
                        String key = "field" + formFieldId;
                        Integer displayOrder = pageItem.getDisplayOrder();
                        if ((displayOrder > startField) & (displayOrder < endField)) {
                            String value = (String) encMap.get(key);
                            // sbuf.append(", ").append(formField.getLabel()).append(": ").append(value);
                            if (value != null && value.equals("Yes")) {
                                if (sbuf.length() == 0) {
                                    sbuf.append("Diagnoses: ").append(formField.getLabel());
                                } else {
                                    sbuf.append(", ").append(formField.getLabel());
                                }
                            }
                        }
                    }
                    if (probLaborVisit.getField1359() != null) {
                        sbuf.append(" Other diagnosis: " + probLaborVisit.getField1359());
                    }
                    visits.add(sbuf.toString());
                    if (encMap.get("field1266") != null) {
                        sbufDis.append("Disposition: ").append((String) encMap.get("field1266"));
                    }
                    if (encMap.get("field1507") != null) {
                        sbufDis.append("Disposition: ").append((String) encMap.get("field1507"));
                    }
                    visits.add(sbufDis.toString());
                    probLaborMap.put(dateVisit, visits);
                    //probLaborVisit.setEncounterMap(encMap);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List prevPregs = ReportDAO.getAllResolved(conn, patientId, (long) 2, PrevPregnanciesReport.class);
        ante.setPrevPregs(prevPregs);

        List pregnancies = null;
        try {
            pregnancies = PregnancyDAO.getPatientPregnancyReports(conn, patientId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        ante.setPregnancies(pregnancies);

        for (int i = 0; i < pregnancies.size(); i++) {
            PregnancyReport pregnancyReport = (PregnancyReport) pregnancies.get(i);
            pregnancyId = pregnancyReport.getId();
            List anteVisits = ReportDAO.getAllResolved(conn, patientId, pregnancyId, (long) 80, AntenatalReport.class);
            if (anteVisits.size() < 5) {
                int addRoutineVisits = 5 - anteVisits.size();
                if (addRoutineVisits > 0) {
                    for (int k = 0; k < addRoutineVisits; k++) {
                        AntenatalReport routineAnteReport = new AntenatalReport();
                        anteVisits.add(routineAnteReport);
                    }
                }
            }
            pregnancyReport.setAnteVisits(anteVisits);
            if (pregnancyId == currentPregnancyID.intValue()) {
            	ante.setAnteVisits(anteVisits);
            }

            // loop through the ante visits and get the drugs dispensed
            for (int j = 0; j < anteVisits.size(); j++) {
                AntenatalReport routineAnteReport = (AntenatalReport) anteVisits.get(j);
                // add probLabor visit data
                Date dateVisit = routineAnteReport.getDateVisit();
                if (probLaborMap != null) {
                    List problemRemarks = (List) probLaborMap.get(dateVisit);
                    if (problemRemarks != null) {
                        routineAnteReport.setIs_problemR(problemRemarks.toString());
                    }
                    probLaborMap.remove(dateVisit);
                }

                DrugsDispensed drugsDispensed = new DrugsDispensed();
                drugsDispensed.setId(routineAnteReport.getId());
                boolean isDispensed = false;
                if (routineAnteReport.getFolateR() != null && routineAnteReport.getFolateR().equals("Yes")) {
                    isDispensed = true;
                    drugsDispensed.setFolic(true);
                }
                if (routineAnteReport.getIronR() != null && routineAnteReport.getIronR().equals("Yes")) {
                    isDispensed = true;
                    drugsDispensed.setIron(true);
                }
                if (routineAnteReport.getMalaria_sp_dosageR() != null) {
                    isDispensed = true;
                    String malariaSP = routineAnteReport.getMalaria_sp_dosageR();
                    drugsDispensed.setMalariaSp(malariaSP);
                }
                if (routineAnteReport.getDewormingR() != null && routineAnteReport.getDewormingR().equals("Yes")) {
                    isDispensed = true;
                    drugsDispensed.setDeworming(true);
                }
                if (isDispensed) {
                    drugsDispensed.setDateDispensed(routineAnteReport.getDateVisit());
                    drugs.add(drugsDispensed);
                }
            }
            for (int j = 0; j < anteVisits.size(); j++) {
            	AntenatalReport routineAnteReport = (AntenatalReport) anteVisits.get(j);
            	// add smc visit data
            	Date dateVisit = routineAnteReport.getDateVisit();
            	if ((dateVisit != null) && (smc != null && smc.getDateVisit() != null)) {
                	if (dateVisit.getTime() == smc.getDateVisit().getTime()) {
                		routineAnteReport.setPatient_sleep_ITNR(smc.getPatient_sleep_ITNR());
                	}
            	}
            }

            /*if (drugs.size() < 4) {
                int addDrugs = 4 - drugs.size();
                if (addDrugs > 0) {
                    for (int k = 0; k < addDrugs; k++) {
                        DrugsDispensed addDrug = new DrugsDispensed();
                        drugs.add(addDrug);
                    }
                }
            }*/

            ante.setDrugs(drugs);

            // if there are any left-over prob-labour visits
            if (probLaborMap != null && probLaborMap.size() > 0) {
                Set probLaborSet = probLaborMap.entrySet();
                StringBuffer sbuf = new StringBuffer();
                sbuf.append(" ");
                for (Iterator iterator = probLaborSet.iterator(); iterator.hasNext();) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    Date visitDate = (Date) entry.getKey();
                    List probList = (List) entry.getValue();
                    StringBuffer probs = new StringBuffer();
                    for (int j = 0; j < probList.size(); j++) {
                        String prob = String.valueOf(probList.get(j));
                        probs.append(prob).append(" ");
                    }
                    sbuf.append(visitDate).append(":").append(probs).append(";");
                }
                ante.setProbLaborVisits(sbuf.toString());
            }

            pregnancyReport.setDeliverySum(deliverySum);

            try {
                MaternalDischargeReport maternalDischarge = (MaternalDischargeReport) EncountersDAO.getResolvedOne(conn, patientId, pregnancyId, (long) 68, MaternalDischargeReport.class);
                pregnancyReport.setMaternalDischarge(maternalDischarge);
            } catch (ObjectNotFoundException e) {
                // it's ok
            }

            //List puerperium = ReportDAO.getAllResolved(conn, patientId, pregnancyId, (long) 81, PuerperiumReport.class);
            //pregnancyReport.setPuerperium(puerperium);

            ZEPRSSharedItems.getInfantSummary(conn, patientId, pregnancyId, pregnancyReport);
        }

        ante.setReportDate(DateUtils.getNowPretty());

        return ante;
    }

	/**
	 * @param hivStamp
	 * @param record
	 * @param formId TODO
	 * @return
	 */
	private static StringBuffer populateStringFromRecord(HivStamp hivStamp, EncounterData record, long formId) {
		// Attach a map of encounter values that has enumerations already resolved.
		StringBuffer sBuf = new StringBuffer();
		sBuf.append("Date Visit: " + record.getDateVisit());
		Form encounterForm = (Form) DynaSiteObjects.getForms().get((long) formId);
		Map encMap = PatientRecordUtils.getEncounterMap(encounterForm, record, "starSchemaNameR");
		record.setEncounterMap(encMap);
		LinkedHashMap <String,String> labelMap = new LinkedHashMap<String,String>();
		Set pageItems = encounterForm.getPageItems();
		for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
		    PageItem pageItem = (PageItem) iterator.next();
		    FormField formField = pageItem.getForm_field();
		    String key = formField.getStarSchemaName() + "R";
		    String value = (String) encMap.get(key);
		    if (value != null) {
		        String label = formField.getLabel();
		    	//log.debug(pageItem.getDisplayOrder() + ":" +label );
		        labelMap.put(label, value);
		    }
		}
		Set labelSet = labelMap.entrySet();
		for (Iterator iterator = labelSet.iterator(); iterator.hasNext();) {
		    Map.Entry entry = (Map.Entry) iterator.next();
		    // remove Diagnosed with HIV/AIDS - add to hiv stamp instead
		    if (entry.getKey().equals("Diagnosed with HIV/AIDS") && hivStamp != null) {
		    	if (entry.getValue().equals("Yes")) {
		            hivStamp.setHivHistory(true);
		    	}
		    } else {
		        if (!entry.getValue().equals("No")) {
		            if (sBuf.length() == 0) {
		                if (entry.getValue().equals("Yes")) {
		                    sBuf.append(entry.getKey());
		                } else {
		                    sBuf.append(entry.getKey() + ": " + entry.getValue());
		                }
		            } else {
		                if (entry.getValue().equals("Yes")) {
		                    sBuf.append("; " + entry.getKey());
		                } else {
		                    sBuf.append("; " + entry.getKey() + ": " + entry.getValue());
		                }
		            }
		        }
		    }
		}
		return sBuf;
	}

}