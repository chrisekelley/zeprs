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
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.report.valueobject.AntenatalHistory;
import org.cidrz.project.zeprs.report.valueobject.CurrentPregnancyStatus;
import org.cidrz.project.zeprs.report.valueobject.DrugsDispensed;
import org.cidrz.project.zeprs.report.valueobject.HivStamp;
import org.cidrz.project.zeprs.report.valueobject.PostnatalHistory;
import org.cidrz.project.zeprs.report.valueobject.PregnancyReport;
import org.cidrz.project.zeprs.report.valueobject.Referral;
import org.cidrz.project.zeprs.valueobject.gen.DeliverySum;
import org.cidrz.project.zeprs.valueobject.report.PatientRegistrationClean;
import org.cidrz.project.zeprs.valueobject.report.gen.AntesumReport;
import org.cidrz.project.zeprs.valueobject.report.gen.DeliverySumReport;
import org.cidrz.project.zeprs.valueobject.report.gen.DrugInterventionReport;
import org.cidrz.project.zeprs.valueobject.report.gen.MaternalDischargeReport;
import org.cidrz.project.zeprs.valueobject.report.gen.PostnatalHospReport;
import org.cidrz.project.zeprs.valueobject.report.gen.PuerperiumReport;
import org.cidrz.project.zeprs.valueobject.report.gen.RoutineAnteReport;
import org.cidrz.project.zeprs.valueobject.report.gen.RprReport;
import org.cidrz.project.zeprs.valueobject.report.gen.SafeMotherhoodCareReport;
import org.cidrz.webapp.dynasite.dao.DemographicsDAO;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.PregnancyDAO;
import org.cidrz.webapp.dynasite.dao.reports.ReportDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.rules.impl.OutcomeImpl;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Site;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Feb 9, 2006
 *         Time: 5:02:58 PM
 */
public class PostnatalHistoryReport {

    private static Log log = LogFactory.getFactory().getInstance(PostnatalHistoryReport.class);

    /**
     * Queries db and assembles data for ANC card.
     *
     * @param conn
     * @param patientId
     * @return PostnatalHistory
     */
    public static PostnatalHistory generatePostnatalHistory(Connection conn, Long patientId) {

        PostnatalHistory post = new PostnatalHistory();
        String reportPrinted = DateUtils.getNowPretty();
        post.setReportPrinted(reportPrinted);
        Long pregnancyId;
        pregnancyId = PatientRecordUtils.getPregnancyId(conn, patientId);
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

        post.setPatientRegistration(pr);

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
        post.setCurrentPregnancyStatus(currentPregnancyStatus);


        /**
         * PregnancyReport is a convenient container for  visits during this pregnancy
         */
        PregnancyReport pregnancyReport = null;
        try {
            pregnancyReport = PregnancyDAO.getOnePatientPregnancyReport(conn, pregnancyId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            // pregnancy is probably over
        }
        Set sites = new TreeSet();
        if (pregnancyReport != null) {
            pregnancyId = pregnancyReport.getId();

            List anteVisits = ReportDAO.getAllResolved(conn, patientId, pregnancyId, (long) 80, RoutineAnteReport.class);
            if (anteVisits.size() > 0) {
                pregnancyReport.setAnteVisits(anteVisits);
                RoutineAnteReport anteVisit = (RoutineAnteReport) anteVisits.get(0);
                String dateFirstEga = anteVisit.getEga_129R();
                pregnancyReport.setDateFirstEga(dateFirstEga);

                for (int i = 0; i < anteVisits.size(); i++) {
                    RoutineAnteReport routineAnteReport = (RoutineAnteReport) anteVisits.get(i);
                    sites.add(routineAnteReport.getSiteName());
                }

            }

            String siteList = "";
            for (Iterator iterator = sites.iterator(); iterator.hasNext();) {
                String siteName = (String) iterator.next();
                if (siteList.equals("")) {
                    siteList = siteName;
                } else {
                    siteList = siteList + ", " + siteName;
                }
            }
            pregnancyReport.setSiteList(siteList);
            ZEPRSSharedItems.getInfantSummary(conn, patientId, pregnancyId, pregnancyReport);

            try {
                DeliverySumReport deliverySum = (DeliverySumReport) EncountersDAO.getResolvedOne(conn, patientId, pregnancyId, (long) 66, DeliverySumReport.class);
                pregnancyReport.setDeliverySum(deliverySum);
                Date dateVisitDelivery = deliverySum.getDateVisit();

                try {
                    String deliverySummaryEga = PatientRecordUtils.getEgaDelivery(conn, patientId.intValue(), dateVisitDelivery);
                    pregnancyReport.setDeliverySummaryEga(deliverySummaryEga);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    e.printStackTrace();
                }

                long durationTotal = 0;
                // getDurationOfLabour takes a DeliverySum, not DeliverySumReport...
                DeliverySum deliverySumOne = null;
                deliverySumOne = (DeliverySum) EncountersDAO.getOne(conn, patientId, pregnancyId, new Long("66"));
                if (pregnancyReport.getDateLastInfantDelivered() != null && pregnancyReport.getTimeLastInfantDelivered() != null)
                {
                    durationTotal = PatientRecordUtils.getDurationOfLabour(deliverySumOne, pregnancyReport.getDateLastInfantDelivered(), pregnancyReport.getTimeLastInfantDelivered());
                }
                pregnancyReport.setDurationOfLabour(durationTotal);

            } catch (ObjectNotFoundException e) {
                // it's ok
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                MaternalDischargeReport maternalDischarge = (MaternalDischargeReport) EncountersDAO.getResolvedOne(conn, patientId, pregnancyId, (long) 68, MaternalDischargeReport.class);
                pregnancyReport.setMaternalDischarge(maternalDischarge);
            } catch (ObjectNotFoundException e) {
                // it's ok
            }

            // get the last puerperium visit
            List puerperiumVisits = ReportDAO.getAllResolved(conn, patientId, pregnancyId, (long) 81, PuerperiumReport.class);
            if (puerperiumVisits.size() > 0) {
                int perNum = puerperiumVisits.size() - 1;
                pregnancyReport.setPuerperium((PuerperiumReport) puerperiumVisits.get(perNum));
            }

            /**
             * Referrals
             */
            List referrals = null;
            List admissions = new ArrayList();
            try {
                referrals = OutcomeDAO.getReferrals(conn, patientId, pregnancyId);
                for (int i = 0; i < referrals.size(); i++) {
                    Referral referral = new Referral();
                    OutcomeImpl outcome = (OutcomeImpl) referrals.get(i);
                    // Date dateVisit = new Date(outcome.getCreated().getTime());
                    // String staffName = outcome.getLastModifiedBy();
                    // Site referringSite = (Site) DynaSiteObjects.getClinicMap().get(outcome.getReferringSiteId());
                    Long siteId = outcome.getSiteId();
                    Site admittingSite = (Site) DynaSiteObjects.getClinicMap().get(siteId);
                    Integer wardId = outcome.getWardId();
                    String ward = "";
                    if (wardId != null) {
                       ward = PatientRecordUtils.resolveEnum(wardId, 0);
                    }
                    Long dischargeId = outcome.getDischargeId();

                    referral.setDate(outcome.getUthAdmissionDate());
                    referral.setPlace(admittingSite.getName() + " - " + ward);

                    if (dischargeId != null) {
                        Long formId = EncountersDAO.checkEncounterId(conn, patientId, pregnancyId, dischargeId);
                        String className = (String) DynaSiteObjects.getFormIdToClassNames().get("report" + formId);
                        Class clazz = null;
                        try {
                            clazz = Class.forName(className);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        if (formId.intValue() == 63) {
                            try {
                                AntesumReport discharge = (AntesumReport) EncountersDAO.getResolvedOne(conn, patientId, formId, clazz);
                                String diagnosis = getDiagnosis(discharge);
                                referral.setDiagnosis(diagnosis);
                                String drugsTaken = getDrugsTaken(discharge);
                                referral.setDrugsGiven(drugsTaken);
                                String actionTaken = getActionTaken(discharge);
                                referral.setActionTaken(actionTaken);
                            } catch (ObjectNotFoundException e) {
                                //
                            }

                        } else if (formId.intValue() == 74) {
                            try {
                                PostnatalHospReport discharge = (PostnatalHospReport) EncountersDAO.getResolvedOne(conn, patientId, formId, clazz);
                                String diagnosis = getDiagnosis(discharge);
                                referral.setDiagnosis(diagnosis);
                                String drugsTaken = getDrugsTaken(discharge);
                                referral.setDrugsGiven(drugsTaken);
                                String actionTaken = getActionTaken(discharge);
                                referral.setActionTaken(actionTaken);
                            } catch (ObjectNotFoundException e) {
                                //
                            }
                        }
                    }

                    long ega = 0;
                    if (outcome.getUthAdmissionDate() != null && currentPregnancyStatus.getDateEga() != null && currentPregnancyStatus.getEga() != null) {
                        ega = PatientRecordUtils.calcEgaforDate(outcome.getUthAdmissionDate(), currentPregnancyStatus.getDateEga(), currentPregnancyStatus.getEgaDb());
                    }
                    if (ega >0) {
                        long egaWeeks = ega / 7;
                        long egaDays = ega % 7;
                        referral.setGestation(egaWeeks + " " + egaDays + "/7");
                    }
                    admissions.add(referral);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (referrals.size() < 2) {
                int numToAdd = 2 - referrals.size();
                if (numToAdd > 0) {
                    for (int k = 0; k < numToAdd; k++) {
                        Referral referral = new Referral();
                        admissions.add(referral);
                    }
                }
            }
            pregnancyReport.setReferrals(admissions);

            List rprVisits = ReportDAO.getAll(conn, patientId, new Long("90"), RprReport.class);
            AntenatalHistory anteHistory = new AntenatalHistory();
            anteHistory.setRprScreens(rprVisits);
            pregnancyReport.setAnteHistory(anteHistory);
        }
        post.setPregnancyReport(pregnancyReport);

        // hivStamp
        SafeMotherhoodCareReport smc = null;
        try {
            smc = (SafeMotherhoodCareReport) EncountersDAO.getResolvedOne(conn, patientId, (long) 92, SafeMotherhoodCareReport.class);
            // post.setSmc(smc);
            HivStamp hivStamp = PatientRecordUtils.getHivStamp(conn, patientId, pregnancyId, smc);
            post.setHivStamp(hivStamp);
        } catch (ObjectNotFoundException e) {
            // not filled out yet...
        }

        post.setReportDate(DateUtils.getNowPretty());
        return post;
    }

    public static String getDiagnosis(AntesumReport discharge) {
        StringBuffer sbuf = new StringBuffer();
        if (discharge.getAnaemiaR() != null && discharge.getAnaemiaR().equals("Yes")) {
            sbuf.append("Anaemia, ");
        }
        if (discharge.getCardiac_diseaseR() != null && discharge.getCardiac_diseaseR().equals("Yes")) {
            sbuf.append("Cardiac disease, ");
        }
        if (discharge.getDiabetes_gestationalR() != null && discharge.getDiabetes_gestationalR().equals("Yes")) {
            sbuf.append("Diabetes: gestational, ");
        }
        if (discharge.getDiabetes_pregestationalR() != null && discharge.getDiabetes_pregestationalR().equals("Yes")) {
            sbuf.append("Diabetes: pregestational, ");
        }
        if (discharge.getDysentaryR() != null && discharge.getDysentaryR().equals("Yes")) {
            sbuf.append("Dysentary, ");
        }
        if (discharge.getEpilepsyR() != null && discharge.getEpilepsyR().equals("Yes")) {
            sbuf.append("Epilepsy, ");
        }
        if (discharge.getGastroenteritisR() != null && discharge.getGastroenteritisR().equals("Yes")) {
            sbuf.append("Gastroenteritis, ");
        }
        if (discharge.getHemoglobinopathy_sickleR() != null && discharge.getHemoglobinopathy_sickleR().equals("Yes")) {
            sbuf.append("Hemoglobinopathy: sickle cell, ");
        }
        if (discharge.getHemoglobinopathy_thallasemiaR() != null && discharge.getHemoglobinopathy_thallasemiaR().equals("Yes")) {
            sbuf.append("Hemoglobinopathy: thallasemia, ");
        }
        if (discharge.getHepatitisR() != null && discharge.getHepatitisR().equals("Yes")) {
            sbuf.append("Hepatitis, ");
        }
        if (discharge.getMalaria_suspectedR() != null && discharge.getMalaria_suspectedR().equals("Yes")) {
            sbuf.append("Malaria suspected, ");
        }
        if (discharge.getMissed_abortionR() != null && discharge.getMissed_abortionR().equals("Yes")) {
            sbuf.append("Missed abortion, ");
        }
        if (discharge.getPolyarthritisR() != null && discharge.getPolyarthritisR().equals("Yes")) {
            sbuf.append("Polyarthritis, ");
        }
        if (discharge.getPsychosisR() != null && discharge.getPsychosisR().equals("Yes")) {
            sbuf.append("Psychosis, ");
        }
        if (discharge.getTuberculosisR() != null && discharge.getTuberculosisR().equals("Yes")) {
            sbuf.append("Tuberculosis, ");
        }
        if (discharge.getPreterm_labourR() != null && discharge.getPreterm_labourR().equals("Yes")) {
            sbuf.append("Preterm labour, ");
        }
        if (discharge.getPromR() != null && discharge.getPromR().equals("Yes")) {
            sbuf.append("PROM, ");
        }
        if (discharge.getBreech_presentationR() != null && discharge.getBreech_presentationR().equals("Yes")) {
            sbuf.append("Breech presentation, ");
        }
        if (discharge.getAphR() != null && discharge.getAphR() == "Yes") {
            sbuf.append("APH, ");
        }
        if (discharge.getUtiR() != null && discharge.getUtiR().equals("Yes")) {
            sbuf.append("UTI, ");
        }
        if (discharge.getAntepartum_hemhorrhageR() != null && discharge.getAntepartum_hemhorrhageR().equals("Yes")) {
            sbuf.append("Antepartum Hemhorrhage, ");
        }
        if (discharge.getDrainingR() != null && discharge.getDrainingR().equals("Yes")) {
            sbuf.append("Draining, ");
        }
        if (discharge.getMalpresentationsR() != null && discharge.getMalpresentationsR().equals("Yes")) {
            sbuf.append("Malpresentations, ");
        }
        if (discharge.getPrevious_c_sR() != null && discharge.getPrevious_c_sR().equals("Yes")) {
            sbuf.append("Previous C/S, ");
        }
        if (discharge.getWrong_datesR() != null && discharge.getWrong_datesR().equals("Yes")) {
            sbuf.append("Wrong Dates, ");
        }
        if (discharge.getHypertensive_disordersR() != null) {
            sbuf.append("Hypertensive Disorders: " + discharge.getHypertensive_disordersR() + ", ");
        }
        if (discharge.getMiscarriageR() != null) {
            sbuf.append("Miscarriage: " + discharge.getMiscarriageR() + ", ");
        }
        if (discharge.getDiag_otherR() != null && discharge.getDiag_otherR() != null) {
            sbuf.append(discharge.getDiag_otherR() + ", ");
        }
        int length = sbuf.toString().length()-2;
        String diagnosis = sbuf.toString().substring(0,length);
        return diagnosis;
    }

    public static String getDiagnosis(PostnatalHospReport discharge) {
        StringBuffer sbuf = new StringBuffer();
        if (discharge.getAnaemiaR() != null && discharge.getAnaemiaR().equals("Yes")) {
            sbuf.append("Anaemia, ");
        }
        if (discharge.getCardiac_diseaseR() != null && discharge.getCardiac_diseaseR().equals("Yes")) {
            sbuf.append("Cardiac disease, ");
        }
        if (discharge.getDiabetes_gestationalR() != null && discharge.getDiabetes_gestationalR().equals("Yes")) {
            sbuf.append("Diabetes: gestational, ");
        }
        if (discharge.getDiabetes_pregestationalR() != null && discharge.getDiabetes_pregestationalR().equals("Yes")) {
            sbuf.append("Diabetes: pregestational, ");
        }
        if (discharge.getDysentaryR() != null && discharge.getDysentaryR().equals("Yes")) {
            sbuf.append("Dysentary, ");
        }
        if (discharge.getEpilepsyR() != null && discharge.getEpilepsyR().equals("Yes")) {
            sbuf.append("Epilepsy, ");
        }
        if (discharge.getGastroenteritisR() != null && discharge.getGastroenteritisR().equals("Yes")) {
            sbuf.append("Gastroenteritis, ");
        }
        if (discharge.getHemoglobinopathy_sickleR() != null && discharge.getHemoglobinopathy_sickleR().equals("Yes")) {
            sbuf.append("Hemoglobinopathy: sickle cell, ");
        }
        if (discharge.getHemoglobinopathy_thallasemiaR() != null && discharge.getHemoglobinopathy_thallasemiaR().equals("Yes")) {
            sbuf.append("Hemoglobinopathy: thallasemia, ");
        }
        if (discharge.getHepatitisR() != null && discharge.getHepatitisR().equals("Yes")) {
            sbuf.append("Hepatitis, ");
        }
        if (discharge.getMalaria_suspectedR() != null && discharge.getMalaria_suspectedR().equals("Yes")) {
            sbuf.append("Malaria suspected, ");
        }
        if (discharge.getPolyarthritisR() != null && discharge.getPolyarthritisR().equals("Yes")) {
            sbuf.append("Polyarthritis, ");
        }
        if (discharge.getPsychosisR() != null && discharge.getPsychosisR().equals("Yes")) {
            sbuf.append("Psychosis, ");
        }
        if (discharge.getTuberculosisR() != null && discharge.getTuberculosisR().equals("Yes")) {
            sbuf.append("Tuberculosis, ");
        }
        if (discharge.getDrainingR() != null && discharge.getDrainingR().equals("Yes")) {
            sbuf.append("Draining, ");
        }
        if (discharge.getProlonged_first_stageR() != null && discharge.getProlonged_first_stageR().equals("Yes")) {
            sbuf.append("Prolonged first stage, ");
        }
        if (discharge.getCpdR() != null && discharge.getCpdR().equals("Yes")) {
            sbuf.append("CPD, ");
        }
        if (discharge.getPphR() != null && discharge.getPphR().equals("Yes")) {
            sbuf.append("PPH, ");
        }
        if (discharge.getRuptured_placentaR() != null && discharge.getRuptured_placentaR().equals("Yes")) {
            sbuf.append("Ruptured placenta, ");
        }
        if (discharge.getSepsisR() != null && discharge.getSepsisR().equals("Yes")) {
            sbuf.append("Sepsis, ");
        }
        if (discharge.getProlonged_laborR() != null && discharge.getProlonged_laborR().equals("Yes")) {
            sbuf.append("Prolonged labour, ");
        }
        if (discharge.getRuptured_uterusR() != null && discharge.getRuptured_uterusR().equals("Yes")) {
            sbuf.append("Ruptured uterus, ");
        }
        if (discharge.getCervical_tearsR() != null && discharge.getCervical_tearsR().equals("Yes")) {
            sbuf.append("Cervical tears, ");
        }
        if (discharge.getMultiple_pregnancyR() != null && discharge.getMultiple_pregnancyR().equals("Yes")) {
            sbuf.append("Multiple_pregnancy, ");
        }
        if (discharge.getMalpresentationsR() != null && discharge.getMalpresentationsR().equals("Yes")) {
            sbuf.append("Malpresentations, ");
        }
        if (discharge.getIntrapartum_foetal_distressR() != null && discharge.getIntrapartum_foetal_distressR().equals("Yes")) {
            sbuf.append("Intrapartum_foetal_distress, ");
        }
        if (discharge.getPrevious_c_sR() != null && discharge.getPrevious_c_sR().equals("Yes")) {
            sbuf.append("Previous C/S, ");
        }
        if (discharge.getBroken_episotumR() != null && discharge.getBroken_episotumR().equals("Yes")) {
            sbuf.append("Broken episotum, ");
        }
        if (discharge.getDizzinessR() != null && discharge.getDizzinessR().equals("Yes")) {
            sbuf.append("Dizziness, ");
        }
        if (discharge.getFeverR() != null && discharge.getFeverR().equals("Yes")) {
            sbuf.append("Fever, ");
        }
        if (discharge.getPsychosisR() != null && discharge.getPsychosisR().equals("Yes")) {
            sbuf.append("Psychosis, ");
        }
        if (discharge.getHypertensive_disorderR() != null && discharge.getHypertensive_disorderR().equals("Yes")) {
            sbuf.append("Hypertensive disorder, ");
        }
        if (discharge.getDiag_otherR() != null && discharge.getDiag_otherR() != null) {
            sbuf.append(discharge.getDiag_otherR() + ", ");
        }
        int length = sbuf.toString().length()-2;
        String diagnosis = sbuf.toString().substring(0,length);
        return diagnosis;
    }

    public static String getDrugsTaken(AntesumReport discharge) {
        StringBuffer sbuf = new StringBuffer();
        if (discharge.getDrug_1_1136R() != null) {
            sbuf.append(discharge.getDrug_1_1136R() + ", ");
        }
        if (discharge.getDrug_2_1137R() != null) {
            sbuf.append(discharge.getDrug_2_1137R() + ", ");
        }
        if (discharge.getDrug_3_1138R() != null) {
            sbuf.append(discharge.getDrug_3_1138R() + ", ");
        }
        if (discharge.getDrug_4_1139R() != null) {
            sbuf.append(discharge.getDrug_4_1139R() + ", ");
        }
        if (discharge.getDrug_5_1140R() != null) {
            sbuf.append(discharge.getDrug_5_1140R() + ", ");
        }
        if (discharge.getDrug_6_1141R() != null) {
            sbuf.append(discharge.getDrug_6_1141R() + ", ");
        }
        if (discharge.getDrug_7_1142R() != null) {
            sbuf.append(discharge.getDrug_7_1142R() + ", ");
        }
        if (discharge.getDrug_8_1143R() != null) {
            sbuf.append(discharge.getDrug_8_1143R() + ", ");
        }
        if (discharge.getDrug_9_1144R() != null) {
            sbuf.append(discharge.getDrug_9_1144R() + ", ");
        }
        if (discharge.getDrug_10_1145R() != null) {
            sbuf.append(discharge.getDrug_10_1145R() + ", ");
        }
        if (discharge.getMed_treatments_other_descR() != null) {
            sbuf.append(discharge.getMed_treatments_other_descR() + ", ");
        }

        int length = sbuf.toString().length()-2;
        String drugsDispensed = sbuf.toString().substring(0,length);
        return drugsDispensed;
    }

    public static String getDrugsTaken(PostnatalHospReport discharge) {
        StringBuffer sbuf = new StringBuffer();
        if (discharge.getDrug_1_1136R() != null) {
            sbuf.append(discharge.getDrug_1_1136R() + ", ");
        }
        if (discharge.getDrug_2_1137R() != null) {
            sbuf.append(discharge.getDrug_2_1137R() + ", ");
        }
        if (discharge.getDrug_3_1138R() != null) {
            sbuf.append(discharge.getDrug_3_1138R() + ", ");
        }
        if (discharge.getDrug_4_1139R() != null) {
            sbuf.append(discharge.getDrug_4_1139R() + ", ");
        }
        if (discharge.getDrug_5_1140R() != null) {
            sbuf.append(discharge.getDrug_5_1140R() + ", ");
        }
        if (discharge.getDrug_6_1141R() != null) {
            sbuf.append(discharge.getDrug_6_1141R() + ", ");
        }
        if (discharge.getDrug_7_1142R() != null) {
            sbuf.append(discharge.getDrug_7_1142R() + ", ");
        }
        if (discharge.getDrug_8_1143R() != null) {
            sbuf.append(discharge.getDrug_8_1143R() + ", ");
        }
        if (discharge.getDrug_9_1144R() != null) {
            sbuf.append(discharge.getDrug_9_1144R() + ", ");
        }
        if (discharge.getDrug_10_1145R() != null) {
            sbuf.append(discharge.getDrug_10_1145R() + ", ");
        }
        if (discharge.getMed_treatments_other_descR() != null) {
            sbuf.append(discharge.getMed_treatments_other_descR() + ", ");
        }

        int length = sbuf.toString().length()-2;
        String drugsDispensed = sbuf.toString().substring(0,length);
        return drugsDispensed;
    }

    public static String getActionTaken(AntesumReport discharge) {
        StringBuffer sbuf = new StringBuffer();
        if (discharge.getMvaR() != null && discharge.getMvaR().equals("Yes")) {
            sbuf.append("MVA, ");
        }
        if (discharge.getDilatation_and_curettageR() != null && discharge.getDilatation_and_curettageR().equals("Yes")) {
            sbuf.append("Dilatation and curettage, ");
        }
        if (discharge.getLaparotomyR() != null && discharge.getLaparotomyR().equals("Yes")) {
            sbuf.append("Laparotomy, ");
        }
        if (discharge.getHysterectomyR() != null && discharge.getHysterectomyR().equals("Yes")) {
            sbuf.append("Hysterectomy, ");
        }
        if (discharge.getSalpingostomyR() != null && discharge.getSalpingostomyR().equals("Yes")) {
            sbuf.append("Salpingostomy, ");
        }
        if (discharge.getSurg_treat_other_descR() != null) {
            sbuf.append(discharge.getSurg_treat_other_descR() + ", ");
        }

        if (discharge.getMedications_dischargeR() != null) {
            sbuf.append(discharge.getMedications_dischargeR() + ", ");
        }
        int length = sbuf.toString().length()-2;
        String diagnosis = sbuf.toString().substring(0,length);
        return diagnosis;
    }

    public static String getActionTaken(PostnatalHospReport discharge) {
        StringBuffer sbuf = new StringBuffer();
        if (discharge.getMvaR() != null && discharge.getMvaR().equals("Yes")) {
            sbuf.append("MVA, ");
        }
        if (discharge.getDilatation_and_curettageR() != null && discharge.getDilatation_and_curettageR().equals("Yes")) {
            sbuf.append("Dilatation and curettage, ");
        }
        if (discharge.getLaparotomyR() != null && discharge.getLaparotomyR().equals("Yes")) {
            sbuf.append("Laparotomy, ");
        }
        if (discharge.getHysterectomyR() != null && discharge.getHysterectomyR().equals("Yes")) {
            sbuf.append("Hysterectomy, ");
        }
        if (discharge.getSalpingostomyR() != null && discharge.getSalpingostomyR().equals("Yes")) {
            sbuf.append("Salpingostomy, ");
        }
        if (discharge.getEpisiotomy_sutureR() != null && discharge.getEpisiotomy_sutureR().equals("Yes")) {
            sbuf.append("Episiotomy suture, ");
        }
        if (discharge.getCsR() != null && discharge.getCsR().equals("Yes")) {
            sbuf.append("C/S, ");
        }
        if (discharge.getBtlR() != null && discharge.getBtlR().equals("Yes")) {
            sbuf.append("BTL, ");
        }
        if (discharge.getInstrumental_deliveryR() != null && discharge.getInstrumental_deliveryR().equals("Yes")) {
            sbuf.append("Instrumental delivery, ");
        }
        if (discharge.getBlood_transfusionR() != null && discharge.getBlood_transfusionR().equals("Yes")) {
            sbuf.append("Blood transfusion, ");
        }
        if (discharge.getSurg_treat_other_descR() != null) {
            sbuf.append(discharge.getSurg_treat_other_descR() + ", ");
        }
        if (discharge.getMedications_dischargeR() != null) {
            sbuf.append(discharge.getMedications_dischargeR() + ", ");
        }
        int length = sbuf.toString().length()-2;
        String diagnosis = sbuf.toString().substring(0,length);
        return diagnosis;
    }

	/**
	 * @param conn
	 * @param patientId
	 * @param deliveryDateVisit
	 * @param drugs
	 * @throws NumberFormatException
	 */
	public static List<DrugsDispensed> assemblePostnatalDrugs(Connection conn, Long patientId, Date deliveryDateVisit)
			throws NumberFormatException {
		List<DrugsDispensed> drugs = new ArrayList<DrugsDispensed>();
		List drugInterventions = ReportDAO.getAll(conn, patientId, new Long("88"), DrugInterventionReport.class);
		for (int i = 0; i < drugInterventions.size(); i++) {
			DrugInterventionReport drugInterventionReport = (DrugInterventionReport) drugInterventions.get(i);
			Date labDateVisit = drugInterventionReport.getDateVisit();
			boolean addToDrugs = false;
			if (deliveryDateVisit != null) {
				if (labDateVisit != null) {
					if (labDateVisit.getTime() >= deliveryDateVisit.getTime()) {
						addToDrugs = true;
					}
				}
			}
			if (addToDrugs) {
				DrugsDispensed drugsDispensed = null;
				drugsDispensed = ZEPRSUtils.assembleDrugsDispensed(drugInterventionReport);
				if (drugsDispensed != null) {
					drugs.add(drugsDispensed);
				}
			}
		}
		return drugs;
	}
}