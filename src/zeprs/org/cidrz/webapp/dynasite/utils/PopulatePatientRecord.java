/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.validator.DynaValidatorForm;
import org.cidrz.project.zeprs.valueobject.BaseEncounter;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.Newbornrecord;
import org.cidrz.project.zeprs.valueobject.gen.PatientRegistration;
import org.cidrz.project.zeprs.valueobject.gen.NewbornEval;
import org.cidrz.project.zeprs.valueobject.gen.UserInfo;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.FormDAO;
import org.cidrz.webapp.dynasite.dao.SessionPatientDAO;
import org.cidrz.webapp.dynasite.dao.UserDAO;
import org.cidrz.webapp.dynasite.struts.action.FormAction;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.valueobject.*;
import org.cidrz.webapp.dynasite.remote.Partograph;
import org.cidrz.webapp.dynasite.remote.PatientId;
import org.cidrz.webapp.dynasite.logic.EncounterProcessor;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.session.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.io.IOException;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Aug 1, 2005
 *         Time: 9:29:05 AM
 */
public class PopulatePatientRecord {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(PopulatePatientRecord.class);

    //public static Long encounterId = null;
    // public static Long siteId = new Long("1");
    // public static String username = "demo";

    /**
     * Creates a test patient
     * @param conn
     * @param cfg
     * @param session
     * @param patientType - basic: Patient up to prob/labour visit
     * @param username
     * @return
     * @throws Exception
     * @throws SQLException
     */
    public static EncounterData populate(Connection conn, ModuleConfig cfg, HttpSession session, String patientType, String username) throws Exception, SQLException {
    	Long patientId = null;
    	Long pregnancyId = null;
    	Long flowId = null;
    	Long encounterId = null;
    	SessionPatient sessionPatient = null;
    	/*sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
    	patientId = sessionPatient.getId();
    	pregnancyId = sessionPatient.getCurrentPregnancyId();*/

        DynaActionForm dynaForm = null;
        String firstName = null;
        Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
        Long siteId = site.getId();
        boolean longEGA = false;
        boolean noPrevs = false;
        if (patientType.equals("partograph")) {
            firstName = "Parto";
        } else if (patientType.equals("labour")) {
            firstName = "Labour";
        } else if (patientType.equals("infants")) {
            firstName = "Twins";
        } else if (patientType.equals("PosIinfant")) {
            firstName = "PosSingle";
        } else if (patientType.equals("NegInfant")) {
            firstName = "NegSingle";
        } else if (patientType.equals("NegInfantClose")) {
            firstName = "NegSiCl";
        } else if (patientType.equals("NegInfantEnd")) {
            firstName = "NegSiEnd";
        }  else if (patientType.equals("NegInfantEndNoPrevPregs")) {
            firstName = "NegSiEnd";
            noPrevs = true;
        } else if (patientType.equals("NegInfantSBEnd")) {
            firstName = "NegSiSBEnd";
        } else if (patientType.equals("NegInfantSB")) {
        	firstName = "NegSiSB";
        } else {
            firstName = "Basic";
            longEGA = false;  // change to true of you want a long ega to test system-generated problems.
        }
        // create new patient
        dynaForm = createPatient(cfg, firstName, siteId);
         // Create initial dateVisit field to store the earliest dateVisit value.
        Date dateVisit = null;
        dateVisit = DateUtils.getvisitDate(dynaForm);
        Long formId = new Long("1");
        Form formDef = (Form) DynaSiteObjects.getForms().get(formId);
        EncounterData patReg = null;
        try {
            patReg = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
        } catch (Exception e) {
            log.error(e);
        }
        patientId = patReg.getPatientId();
        sessionPatient = SessionPatientDAO.updateSessionPatient(conn, patientId, patReg.getPregnancyId(), session, sessionPatient);
        // create pregDating
        longEGA = false;
        dynaForm = createPregnancyDating(cfg, longEGA, 0);
        formId = new Long("82");
        formDef = (Form) DynaSiteObjects.getForms().get(formId);
        try {
            EncounterData pregDat = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
        } catch (Exception e) {
            log.error(e);
        }
        sessionPatient = SessionPatientDAO.updateSessionPatient(conn, patientId, patReg.getPregnancyId(), session, sessionPatient);
        // create PrevPregnancies
        if (!noPrevs) {
            dynaForm = createPrevPregnancies(cfg);
            formId = new Long("2");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            try {
                EncounterData prevPreg = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
        }

        // create medical/surgical history
        dynaForm = createMedSurgHistory(cfg);
        formId = new Long("70");
        formDef = (Form) DynaSiteObjects.getForms().get(formId);
        try {
            EncounterData medSurgHist = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
        } catch (Exception e) {
            log.error(e);
        }
        // create Safe Motherhood
        dynaForm = createSafeMotherhood(cfg);
        dynaForm.set("field1", dateVisit.toString());
        formId = new Long("92");
        formDef = (Form) DynaSiteObjects.getForms().get(formId);
        try {
            EncounterData safeMother = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
        } catch (Exception e) {
            log.error(e);
        }
        // create RPR
        dynaForm = createRpr(cfg);
        dynaForm.set("field1", dateVisit.toString());
        formId = new Long("90");
        formDef = (Form) DynaSiteObjects.getForms().get(formId);
        try {
            EncounterData rpr = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
        } catch (Exception e) {
            log.error(e);
        }
        // Create initial dateVisit field to store the earliest dateVisit value.
        // create Routine Antenatal
        dynaForm = createRoutineAntenatal(cfg, sessionPatient, 30);
        // dateVisit = DateUtils.getvisitDate(dynaForm);
        //dynaForm.set("field1", dateVisit.toString());
        formId = new Long("80");
        formDef = (Form) DynaSiteObjects.getForms().get(formId);
        try {
            saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
        } catch (Exception e) {
            log.error(e);
        }
        // create Routine Antenatal
        /*dynaForm = createRoutineAntenatal(cfg, sessionPatient, 90);
        Date dateVisitRA2 = DateUtils.getvisitDate(dynaForm);
        if (dateVisitRA2.getTime() < dateVisit.getTime()) {
            dateVisit = dateVisitRA2;
        }
        formId = new Long("80");
        formDef = (Form) DynaSiteObjects.getForms().get(formId);
        try {
            saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
        } catch (Exception e) {
            log.error(e);
        }*/
        // create Routine Antenatal
        /*dynaForm = createRoutineAntenatal(cfg, sessionPatient, 120);
        Date dateVisitRA3 = DateUtils.getvisitDate(dynaForm);
        if (dateVisitRA3.getTime() < dateVisit.getTime()) {
            dateVisit = dateVisitRA3;
        }
        formId = new Long("80");
        formDef = (Form) DynaSiteObjects.getForms().get(formId);
        try {
            saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
        } catch (Exception e) {
            log.error(e);
        }*/
        // create initial visit patient
        dynaForm = createInitialVisit(cfg);
        dynaForm.set("field1", dateVisit.toString());
        formId = new Long("77");
        formDef = (Form) DynaSiteObjects.getForms().get(formId);
        try {
            saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
        } catch (Exception e) {
            log.error(e);
        }
        // sessionPatient = SessionPatientDAO.updateSessionPatient(patientId, patReg.getPregnancyId(), session, sessionPatient);
        // create simple problem/labour visit
        dynaForm = createProblem(cfg);
        dynaForm.set("field1", dateVisit.toString());
        formId = new Long("65");
        formDef = (Form) DynaSiteObjects.getForms().get(formId);
        try {
            EncounterData problem = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
        } catch (Exception e) {
            log.error(e);
        }

        if (patientType.equals("infants")) {
            // create problem/labour visit w/ active labour
            dynaForm = createDilatationProblem(cfg);
            formId = new Long("65");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData problem = null;
            try {
                 problem = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            createPartograph(conn, patientId, problem.getPregnancyId(), username, siteId.toString());
            createNewborns(conn, patientId, problem.getPregnancyId(), username, siteId.toString(), session);
            dynaForm = createDeliverySummary(cfg);
            formId = new Long("66");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData delivSum = null;
            try {
                 delivSum = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPuerperium(cfg);
            formId = new Long("81");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData puerperium = null;
            try {
                 puerperium = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }dynaForm = createPuerperium(cfg);
            formId = new Long("81");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            try {
                 puerperium = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createMaternaldischarge(cfg);
            formId = new Long("68");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData maternalDischarge = null;
            try {
                 maternalDischarge = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPostnatalMaternalVisit(cfg);
            formId = new Long("28");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData postnatalVisit = null;
            try {
                 postnatalVisit = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
        } else if (patientType.equals("PosIinfant")) {
            // create pos. mother w/ one infant
            dynaForm = createDilatationProblem(cfg);
            formId = new Long("65");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData problem = null;
            try {
                 problem = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            // create a counsel visit w/ hiv pos result
            dynaForm = createCounselVisitPos(cfg);
            formId = new Long("91");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData counsel = null;
            try {
                 counsel = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            createPartograph(conn, patientId, problem.getPregnancyId(), username, siteId.toString());
            Long infantId = createNewborn(conn, patientId, problem.getPregnancyId(), username, siteId.toString(), session);
            createNewbornEvalRecord(conn, cfg, username, infantId, siteId, sessionPatient, patReg);
            dynaForm = createDeliverySummary(cfg);
            formId = new Long("66");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData delivSum = null;
            try {
                 delivSum = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPuerperium(cfg);
            formId = new Long("81");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData puerperium = null;
            try {
                 puerperium = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createMaternaldischarge(cfg);
            formId = new Long("68");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData maternalDischarge = null;
            try {
                 maternalDischarge = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPostnatalMaternalVisit(cfg);
            formId = new Long("28");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData postnatalVisit = null;
            try {
                 postnatalVisit = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
        } else if (patientType.equals("NegInfant")) {
            // create mother w/ one infant - no hiv test
            dynaForm = createDilatationProblem(cfg);
            formId = new Long("65");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData problem = null;
            try {
                 problem = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            createPartograph(conn, patientId, problem.getPregnancyId(), username, siteId.toString());
            Long infantId = createNewborn(conn, patientId, problem.getPregnancyId(), username, siteId.toString(), session);
            createNewbornEvalRecord(conn, cfg, username, infantId, siteId, sessionPatient, patReg);
            dynaForm = createDeliverySummary(cfg);
            formId = new Long("66");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData delivSum = null;
            try {
                 delivSum = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPuerperium(cfg);
            formId = new Long("81");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData puerperium = null;
            try {
                 puerperium = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createMaternaldischarge(cfg);
            formId = new Long("68");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData maternalDischarge = null;
            try {
                 maternalDischarge = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPostnatalMaternalVisit(cfg);
            formId = new Long("28");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData postnatalVisit = null;
            try {
                 postnatalVisit = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
        } else if (patientType.equals("NegInfantClose")) {
            // create mother w/ one infant - no hiv test - preg dating >42 days
            // create pregDating
            dynaForm = createPregnancyDating(cfg, longEGA, 1);
            formId = new Long("82");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            try {
                EncounterData pregDat = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            sessionPatient = SessionPatientDAO.updateSessionPatient(conn, patientId, patReg.getPregnancyId(), session, sessionPatient);
            dynaForm = createDilatationProblem(cfg);
            formId = new Long("65");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData problem = null;
            try {
                 problem = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            createPartograph(conn, patientId, problem.getPregnancyId(), username, siteId.toString());
            Long infantId = createNewborn(conn, patientId, problem.getPregnancyId(), username, siteId.toString(), session);
            createNewbornEvalRecord(conn, cfg, username, infantId, siteId, sessionPatient, patReg);
            dynaForm = createDeliverySummary(cfg);
            formId = new Long("66");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData delivSum = null;
            try {
                 delivSum = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPuerperium(cfg);
            formId = new Long("81");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData puerperium = null;
            try {
                 puerperium = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createMaternaldischarge(cfg);
            formId = new Long("68");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData maternalDischarge = null;
            try {
                 maternalDischarge = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPostnatalMaternalVisit(cfg);
            formId = new Long("28");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData postnatalVisit = null;
            try {
                 postnatalVisit = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
        } else if (patientType.equals("NegInfantEnd") || patientType.equals("NegInfantEndNoPrevPregs")) {
            // create mother w/ one infant - no hiv test - preg dating >42 days - closed
            // create pregDating
            dynaForm = createPregnancyDating(cfg, longEGA, 1);
            formId = new Long("82");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            try {
                EncounterData pregDat = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            sessionPatient = SessionPatientDAO.updateSessionPatient(conn, patientId, patReg.getPregnancyId(), session, sessionPatient);
            dynaForm = createDilatationProblem(cfg);
            formId = new Long("65");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData problem = null;
            try {
                 problem = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            createPartograph(conn, patientId, problem.getPregnancyId(), username, siteId.toString());
            Long infantId = createNewborn(conn, patientId, problem.getPregnancyId(), username, siteId.toString(), session);
            createNewbornEvalRecord(conn, cfg, username, infantId, siteId, sessionPatient, patReg);
            dynaForm = createDeliverySummary(cfg);
            formId = new Long("66");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData delivSum = null;
            try {
                 delivSum = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPuerperium(cfg);
            formId = new Long("81");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData puerperium = null;
            try {
                 puerperium = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createMaternaldischarge(cfg);
            formId = new Long("68");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData maternalDischarge = null;
            try {
                 maternalDischarge = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPostnatalMaternalVisit(cfg);
            formId = new Long("28");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData postnatalVisit = null;
            try {
                 postnatalVisit = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPregnancyConclusion(cfg);
            formId = new Long("71");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData pregConclusion = null;
            try {
                 pregConclusion = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }

        } else if (patientType.equals("NegInfantSBEnd")) {
            // create mother w/ one infant - still birth - no hiv test - preg dating > 42 days - closed
            // create pregDating
            dynaForm = createPregnancyDating(cfg, longEGA, 1);
            formId = new Long("82");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            try {
                EncounterData pregDat = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            sessionPatient = SessionPatientDAO.updateSessionPatient(conn, patientId, patReg.getPregnancyId(), session, sessionPatient);
            dynaForm = createDilatationProblem(cfg);
            formId = new Long("65");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData problem = null;
            try {
                 problem = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            createPartograph(conn, patientId, problem.getPregnancyId(), username, siteId.toString());
            Long infantId = createStillborn(conn, patientId, problem.getPregnancyId(), username, siteId, session, sessionPatient, cfg);
            // Creating newborneval record in the previous createStillborn
            createNewbornEvalRecordStrillborn(conn, cfg, username, infantId, siteId, sessionPatient, patReg);
            dynaForm = createDeliverySummary(cfg);
            formId = new Long("66");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData delivSum = null;
            try {
                 delivSum = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPuerperium(cfg);
            formId = new Long("81");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData puerperium = null;
            try {
                 puerperium = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createMaternaldischarge(cfg);
            formId = new Long("68");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData maternalDischarge = null;
            try {
                 maternalDischarge = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPostnatalMaternalVisit(cfg);
            formId = new Long("28");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData postnatalVisit = null;
            try {
                 postnatalVisit = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPregnancyConclusion(cfg);
            formId = new Long("71");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData pregConclusion = null;
            try {
                 pregConclusion = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
        } else if (patientType.equals("NegInfantSB")) {
            // create mother w/ one infant - still birth - no hiv test - preg dating > 42 days - closed
            // create pregDating
            dynaForm = createPregnancyDating(cfg, longEGA, 1);
            formId = new Long("82");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            try {
                EncounterData pregDat = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            sessionPatient = SessionPatientDAO.updateSessionPatient(conn, patientId, patReg.getPregnancyId(), session, sessionPatient);
            dynaForm = createDilatationProblem(cfg);
            formId = new Long("65");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData problem = null;
            try {
                 problem = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            createPartograph(conn, patientId, problem.getPregnancyId(), username, siteId.toString());
            Long infantId = createStillborn(conn, patientId, problem.getPregnancyId(), username, siteId, session, sessionPatient, cfg);
            // Creating newborneval record in the previous createStillborn
            createNewbornEvalRecordStrillborn(conn, cfg, username, infantId, siteId, sessionPatient, patReg);
            dynaForm = createDeliverySummary(cfg);
            formId = new Long("66");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData delivSum = null;
            try {
                 delivSum = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPuerperium(cfg);
            formId = new Long("81");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData puerperium = null;
            try {
                 puerperium = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createMaternaldischarge(cfg);
            formId = new Long("68");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData maternalDischarge = null;
            try {
                 maternalDischarge = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            dynaForm = createPostnatalMaternalVisit(cfg);
            formId = new Long("28");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData postnatalVisit = null;
            try {
                 postnatalVisit = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
        } else if (patientType.equals("partograph")) {
            // create problem/labour visit w/ active labour, one partograph entry
            dynaForm = createDilatationProblem(cfg);
            formId = new Long("65");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData problem = null;
            try {
                 problem = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
            createPartograph(conn, patientId, problem.getPregnancyId(), username, siteId.toString());
        } else if (patientType.equals("labour")) {
            // create problem/labour visit w/ active labour, one partograph entry
            dynaForm = createDilatationProblem(cfg);
            formId = new Long("65");
            formDef = (Form) DynaSiteObjects.getForms().get(formId);
            EncounterData problem = null;
            try {
                 problem = saveForm(conn, formDef, formId.toString(), patientId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
            } catch (Exception e) {
                log.error(e);
            }
        }
        // sessionPatient = SessionPatientDAO.updateSessionPatient(patientId, patReg.getPregnancyId(), session, sessionPatient);
        /*try {
            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return patReg;

    }

	private static void createNewbornEvalRecord(Connection conn,
			ModuleConfig cfg, String username, Long patientId, Long siteId,
			SessionPatient sessionPatient, EncounterData patReg)
			throws IOException, ServletException, SQLException,
			ObjectNotFoundException {
		DynaActionForm dynaForm;
		Long formId;
		Form formDef;
		Long encounterId = null;
		Newbornrecord newbornRecord = (Newbornrecord) EncountersDAO.getOne(conn, patientId, patReg.getPregnancyId(), "SQL_RETRIEVE109", Newbornrecord.class);
		Long infantId = newbornRecord.getPatientId();
		//DynaValidatorForm dynaForm = (DynaValidatorForm) form;
		formId = new Long("23");
		//DynaActionForm dynaForm = null;
		dynaForm = createDynaForm(cfg, formId.toString());
		if (newbornRecord.getField2083() != null) {
			Date dob = newbornRecord.getField2083();
			dynaForm.set("field1267", dob.toString());   // dob
		}
		if (newbornRecord.getField2084() != null) {
			Time tob = newbornRecord.getField2084();
			dynaForm.set("field1514", tob.toString());   // tob
		}
		if (newbornRecord.getField2085() != null) {
			Integer seq = newbornRecord.getField2085();
			dynaForm.set("field489", seq.toString());   // seq
		}
		if (newbornRecord.getField2086() != null) {
			Integer sex = newbornRecord.getField2086();
			dynaForm.set("field490", sex.toString());   // sex
		}
		if (newbornRecord.getField2087() != null) {
			Float weight = newbornRecord.getField2087();
			dynaForm.set("field491", weight.toString());   // weight
		}
		if (newbornRecord.getField2088() != null) {
			Integer ega = newbornRecord.getField2088();
			dynaForm.set("field2055", ega.toString());   // ega weeks
		}
		dynaForm.set("field1", DateUtils.getNow().toString());
		//dynaForm = createNewbornForm(cfg, dynaForm);
		//formId = new Long("23");
		formDef = (Form) DynaSiteObjects.getForms().get(formId);
		EncounterData newbornEval = null;
		try {
			newbornEval = saveForm(conn, formDef, formId.toString(), infantId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
		} catch (Exception e) {
		    log.error(e);
		}
	}

	private static void createNewbornEvalRecordStrillborn(Connection conn,
			ModuleConfig cfg, String username, Long patientId, Long siteId,
			SessionPatient sessionPatient, EncounterData patReg)
	throws IOException, ServletException, SQLException,
	ObjectNotFoundException {
		DynaActionForm dynaForm;
		Long formId;
		Form formDef;
		Long encounterId = null;
		Long pregnancyId = patReg.getPregnancyId();
		Newbornrecord newbornRecord = (Newbornrecord) EncountersDAO.getOne(conn, patientId, pregnancyId, "SQL_RETRIEVE109", Newbornrecord.class);
		Long infantId = newbornRecord.getPatientId();
		//DynaValidatorForm dynaForm = (DynaValidatorForm) form;
		formId = new Long("23");
		//DynaActionForm dynaForm = null;
		dynaForm = createDynaForm(cfg, formId.toString());
		if (newbornRecord.getField2083() != null) {
			Date dob = newbornRecord.getField2083();
			dynaForm.set("field1267", dob.toString());   // dob
		}
		if (newbornRecord.getField2084() != null) {
			Time tob = newbornRecord.getField2084();
			dynaForm.set("field1514", tob.toString());   // tob
		}
		if (newbornRecord.getField2085() != null) {
			Integer seq = newbornRecord.getField2085();
			dynaForm.set("field489", seq.toString());   // seq
		}
		if (newbornRecord.getField2086() != null) {
			Integer sex = newbornRecord.getField2086();
			dynaForm.set("field490", sex.toString());   // sex
		}
		if (newbornRecord.getField2087() != null) {
			Float weight = newbornRecord.getField2087();
			dynaForm.set("field491", weight.toString());   // weight
		}
		if (newbornRecord.getField2088() != null) {
			Integer ega = newbornRecord.getField2088();
			dynaForm.set("field2055", ega.toString());   // ega weeks
		}
		dynaForm.set("field493", "821");
		dynaForm.set("field1", DateUtils.getNow().toString());
		//dynaForm = createNewbornForm(cfg, dynaForm);
		//formId = new Long("23");
		formDef = (Form) DynaSiteObjects.getForms().get(formId);
		EncounterData newbornEval = null;
		try {
			newbornEval = saveForm(conn, formDef, formId.toString(), infantId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
		} catch (Exception e) {
			log.error(e);
		}

		dynaForm = null;
        dynaForm = createStillBirthRecord(cfg);
        formId = new Long("40");
        formDef = (Form) DynaSiteObjects.getForms().get(formId);
        EncounterData sbr = null;
        try {
            sbr = saveForm(conn, formDef, formId.toString(), infantId, (DynaValidatorForm) dynaForm, encounterId, siteId, username, sessionPatient);
        } catch (Exception e) {
            log.error(e);
        }
	}

    /**
     * Create data structure to save form data to its table, save data, run form through EncounterProcessor to see if it
     * triggers any outcome exceptions
     * @param conn
     * @param formDef
     * @param formId
     * @param patientId
     * @param dynaForm
     * @param encounterId
     * @param siteId
     * @param username
     * @param sessionPatient
     * @return
     * @throws Exception
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws SQLException
     * @throws ServletException
     */
    public static EncounterData saveForm(Connection conn, Form formDef, String formId, Long patientId, DynaValidatorForm dynaForm, Long encounterId, Long siteId, String username, SessionPatient sessionPatient) throws Exception, InstantiationException, ClassNotFoundException, InvocationTargetException, SQLException, ServletException {
        // Setup the valueobject
        BaseEncounter formObj = FormAction.setupFormObject(formDef, formId, patientId, dynaForm, sessionPatient);
        // Delegate creation to a BO
        Map src = dynaForm.getMap();
        Iterator i = src.keySet().iterator();
        String key = "";
        while (i.hasNext()) {
            key = (String) i.next();
            Object value = src.get(key);
            if (value != null) {
                if (!value.toString().equals("")) {
                    try {
                        BeanUtils.copyProperty(formObj, key, src.get(key));
                    } catch (ConversionException e) {
                        log.error("Error saving Form: " + formDef.getLabel() + " Form id: " + formId + " Key: " + key + " Value: " + value + " Error: " + e);
                    }
                }
            }
        }

        EncounterData enc = (EncounterData) formObj;

        // set the AuditInfo
        // Let each persistence method set the timestamps.
        String createdBy = username;
        enc.setCreatedBy(createdBy);
        String lastModifiedBy = username;
        enc.setLastModifiedBy(lastModifiedBy);
        Long createdSiteId = siteId;
        enc.setCreatedSiteId(createdSiteId);
        Long currentSiteId = siteId;
        enc.setSiteId(currentSiteId);

        Long currentFlowId = null;
        try {
            currentFlowId = sessionPatient.getCurrentFlowId();
        } catch (NullPointerException e) {
            // assume new patient registration
            currentFlowId = new Long("9");
            // log.debug("Assigning currentFlowId as 1 to form id: " + formDef.getId());
        }
        EncounterData vo = null;
        // process user form
//        if (formId == "125") {
//        	UserInfo user = (UserInfo) vo;
////        	private String field2157;	//username
////        	private String field2158;	//password
////        	private String field2159;	//email
////        	private String field2160;	//forenames
////        	private String field2161;	//lastname
////        	private String field2162;	//mobile
////        	private String field2163;	//phone
//        	
//        	UserDAO.insertUser(conn, user.getField2157(), user.getField2160(), user.getField2161(), user.getField2159(), user.getField2162(), user.getField2163());
//        }
        vo = FormDAO.create(conn, enc, username, siteId, formDef, currentFlowId, null);
        //this persists any outcomes to db. We're not using the value it returns in the view at this point, but if
        // we needed it, simply pass it in the response.
        if (!formId.equals("125") ) {
            vo.setSessionPatient(sessionPatient);     // script rule processing needs session patient in the encounter
            EncounterProcessor.processRules(conn, formDef, vo, username);
        }

        //conn.close();
        // conn = null;
        return vo;
    }

    /**
     * used to import patient records from xml
     * This does *not* do outcome processing (rules) - Those will be imported manually
     * @param conn
     * @param formObj
     * @param formDef
     * @param siteId
     * @param username
     * @return
     * @throws Exception
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws SQLException
     * @throws ServletException
     */
    public static EncounterData importForm(Connection conn, EncounterData formObj, Form formDef, Long siteId, String username) throws Exception, InstantiationException, ClassNotFoundException, InvocationTargetException, SQLException, ServletException {

        EncounterData vo = null;
        Long currentFlowId = null;
        try {
            currentFlowId = formObj.getFlowId();
        } catch (NullPointerException e) {
            // assume new patient registration
            currentFlowId = new Long("9");
            // log.debug("Assigning currentFlowId as 1 to form id: " + formDef.getId());
        }
        vo = FormDAO.create(conn, formObj, username, siteId, formDef, currentFlowId, true);
        return vo;
    }


    public static DynaActionForm createPatient(ModuleConfig modCfg, String firstName, Long siteId) {
        FormBeanConfig formBeanConfig = modCfg.findFormBeanConfig("form1");
        DynaActionForm dynaForm = null;
        DynaActionFormClass dynaClass = DynaActionFormClass.createDynaActionFormClass(formBeanConfig);
        try {
            dynaForm = (DynaActionForm) dynaClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        // create random number
        Random generator = new Random();

        int first = generator.nextInt(100000);
        int sur = generator.nextInt(10000);
        int addNum = generator.nextInt(10000);
        int age = generator.nextInt(40) + 13;
        String birthDate = DateUtils.generateBirthdate(age).toString();
        PatientRegistration registration = new PatientRegistration();
        registration.setField6("Patient" + sur);
        if (firstName != null) {
           registration.setField7(firstName + first);
        } else {
           registration.setField7("Test" + first);
        }
        registration.setField1135(Integer.valueOf(String.valueOf(age)));
        registration.setField19(addNum + " Park Place");
        registration.setField17(java.sql.Date.valueOf(birthDate));
        registration.setField490(Integer.valueOf(1));
        // create the zeprsId
        Site site = (Site) DynaSiteObjects.getClinicMap().get(siteId);
        String zepSiteId = site.getSiteAlphaId();
        String zePatientId = PatientId.setPatientId("5040", zepSiteId);
        String zeprsId = PatientRecordUtils.createZeprsId("5040", zepSiteId, zePatientId);
        registration.setField1513(zeprsId);
        registration.setField13("5040");
        registration.setField1511(site.getSiteAlphaId().substring(0, 3));

        Map regMap = new HashMap();

        try {
            regMap = BeanUtils.describe(registration);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Set dynaSet = dynaForm.getMap().entrySet();
        for (Iterator iterator = dynaSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            dynaForm.set(key, regMap.get(key));
        }

        dynaForm.set("field1", DateUtils.getNow().toString());
        return dynaForm;
    }

    /**
     *
     * @param modCfg
     * @param longEGA
     * @param concludeOffset - used to make test pregnancies that need to be concluded (patient delivered, current ega > 41 days
     * @return DynaActionForm
     */
    public static DynaActionForm createPregnancyDating(ModuleConfig modCfg, boolean longEGA, int concludeOffset) {
         // create random number
        Random generator = new Random();
        //int offsetInt = 210;
        //int limit = 70;
        // int offsetInt = 150;
        int offsetInt = 10;
        int limit = 40;
        if (longEGA) {
            // offsetInt = 150;
            // limit = 130;
            offsetInt = 210;
            limit = 70;
        }
        if (concludeOffset > 0) {    // make > 335 days, with a year range
            offsetInt = concludeOffset + 335;
            limit = concludeOffset + 365;
        }
        int offset = generator.nextInt(limit) + offsetInt;
        Long formId = new Long("82");
        DynaActionForm dynaForm = null;
        Date lmpDate = DateUtils.generateDate(offset);
        String currentEgaDateCalc = DateUtils.createFutureDate(lmpDate, 280);
        dynaForm = createDynaForm(modCfg, formId.toString());
        // lmp_reliability_126
        dynaForm.set("field126", "67");
        //lmpdate
        dynaForm.set("field127", lmpDate.toString());
        //edd
        dynaForm.set("field128", currentEgaDateCalc);
        // ega
        dynaForm.set("field129", String.valueOf(offset));
        // dating method
        dynaForm.set("field1615", "2805");
        String currentPregDat = DateUtils.createFutureDate(lmpDate, 30);
        dynaForm.set("field1", currentPregDat);
        return dynaForm;
    }

    public static DynaActionForm createRpr(ModuleConfig modCfg) {
        // create random number
        Random generator = new Random();
        int offset = generator.nextInt(3);
        Long formId = new Long("90");
        DynaActionForm dynaForm = null;
        dynaForm = createDynaForm(modCfg, formId.toString());
        // Date of RPR Screen
        dynaForm.set("field1562", DateUtils.getNow().toString());
        // RPR Result
        if (offset == 2) {
            dynaForm.set("field1563", "2785");  // Reactive
        } else {
            dynaForm.set("field1563", "2784");  // Non-Reactive (Negative)
        }
        // RPR Treatment Date:
        dynaForm.set("field1564", DateUtils.getNow().toString());
        // dateRprRequest
        dynaForm.set("field2006", DateUtils.getNow().toString());
        // RPR Drug
        dynaForm.set("field1565", "2787");
        // RPR Dosage
        // generator = new Random();
        // offset = generator.nextInt(21) + 1;
        dynaForm.set("field1570", "2.4");
        dynaForm.set("field1", DateUtils.getNow().toString());
        return dynaForm;
    }

    public static DynaActionForm createNewbornForm(ModuleConfig modCfg, DynaActionForm dynaForm) {
    	// create random number
    	Random generator = new Random();
    	int offset = generator.nextInt(3);
    	/*String newbornDateField = DateUtils.getNow().toString();
        String birthtime = DateUtils.getTime();
        String sequence = "1";
        String sex = "1";
        String weight = "2.3";*/



    	dynaForm.set("field1", DateUtils.getNow().toString());
    	return dynaForm;
    }

    public static DynaActionForm createPrevPregnancies(ModuleConfig modCfg) {
        Long formId = new Long("2");
        DynaActionForm dynaForm = null;
        Form form = (Form) DynaSiteObjects.getForms().get(formId);
        dynaForm = fillData(modCfg, formId, form);
        return dynaForm;
    }

    public static DynaActionForm createPuerperium(ModuleConfig modCfg) {
        Long formId = new Long("81");
        DynaActionForm dynaForm = null;
        Form form = (Form) DynaSiteObjects.getForms().get(formId);
        dynaForm = fillData(modCfg, formId, form);
        return dynaForm;
    }

    public static DynaActionForm createSafeMotherhood(ModuleConfig modCfg) {
        Long formId = new Long("92");
        DynaActionForm dynaForm = null;
        dynaForm = createDynaForm(modCfg, formId.toString());
        dynaForm.set("field1675", "1");
        Random generator = new Random();
        boolean ran = generator.nextBoolean();
        // Boolean ranB = Boolean.valueOf(ran);
        if (ran) {
            dynaForm.set("field1887", "1");
            dynaForm.set("field110", DateUtils.getNow().toString());
            ran = generator.nextBoolean();
            if (ran) {
                dynaForm.set("field1888", "1");
                dynaForm.set("field111", DateUtils.getNow().toString());
                ran = generator.nextBoolean();
                if (ran) {
                    dynaForm.set("field1889", "1");
                    dynaForm.set("field112", DateUtils.getNow().toString());
                    ran = generator.nextBoolean();
                    if (ran) {
                        dynaForm.set("field1890", "1");
                        dynaForm.set("field113", DateUtils.getNow().toString());
                        ran = generator.nextBoolean();
                        if (ran) {
                            dynaForm.set("field1891", "1");
                            dynaForm.set("field114", DateUtils.getNow().toString());
                            ran = generator.nextBoolean();
                        }
                    }
                }
            }
        }
        return dynaForm;
    }

    /**
     * Create data for Routine Antenatal visit
     * @param modCfg
     * @param sessionPatient
     * @param offset
     * @return
     */
    public static DynaActionForm createRoutineAntenatal(ModuleConfig modCfg, SessionPatient sessionPatient, int offset) {
        Long formId = new Long("80");
        DynaActionForm dynaForm = null;
        //Form form = (Form) DynaSiteObjects.getForms().get(formId);
        dynaForm = createDynaForm(modCfg, formId.toString());
        // get current ega form sessionpatient
        // set a realistic date of visit based on ega
        //Date lmpDate = sessionPatient.getCurrentLmpDate();
        //String currentAnteExam = DateUtils.createFutureDate(lmpDate, offset);
        String dateVisit = DateUtils.getNow().toString();
        dynaForm.set("field1", dateVisit);
        // Integer currentEga = sessionPatient.getCurrentEgaDaysDB();
        Integer egaToday = sessionPatient.getCurrentEgaCalc();
        dynaForm.set("field129", egaToday.toString());
        //fundal_height_232
        dynaForm.set("field232", "20");
        //presentation_314
        dynaForm.set("field314", "169");
        // engaged_234
        //dynaForm.set("field234", "122");
        // foetal_heart_rate_230
        dynaForm.set("field230", "651");
        // foetal_heart_rhythm_229
        dynaForm.set("field229", "118");
        // bp_systolic_224
        dynaForm.set("field224", "2403");
        // bp_diastolic_225
        dynaForm.set("field225", "1844");
        // oedema_231
        dynaForm.set("field231", "1124");
        // weight_228
        dynaForm.set("field228", "55");
        // urinalysis_ace_244
        dynaForm.set("field244", "1131");
        // urinalysis_alb_242
        dynaForm.set("field242", "1671");
        // urinalysis_glu_243
        dynaForm.set("field243", "1444");

        return dynaForm;
    }

    public static DynaActionForm createInitialVisit(ModuleConfig modCfg) {
        Long formId = new Long("77");
        DynaActionForm dynaForm = null;
        dynaForm = createDynaForm(modCfg, formId.toString());
        // height_159
        dynaForm.set("field159", "135");
        // temperature_266
        dynaForm.set("field266", "38");
        // thyroid_165
        dynaForm.set("field165", "619");
        return dynaForm;
    }

public static DynaActionForm createMedSurgHistory(ModuleConfig modCfg) {
        Long formId = new Long("70");
        DynaActionForm dynaForm = null;
        dynaForm = createDynaForm(modCfg, formId.toString());
        // C/S
        dynaForm.set("field1927", "1");
        // Hypertension
        dynaForm.set("field74", "1");
        return dynaForm;
    }

    public static DynaActionForm createProblem(ModuleConfig modCfg) {
        Long formId = new Long("65");
        DynaActionForm dynaForm = null;
        String now = DateUtils.getNow().toString();
        dynaForm = createDynaForm(modCfg, formId.toString());
        // phase
        dynaForm.set("field1487", "2755");
        // temperature_266
        dynaForm.set("field1250", "1");
        // contractions_1250
        dynaForm.set("field1251", now);
        // cervix_dilatation325
        dynaForm.set("field325", "4");
        // true_labor
        dynaForm.set("field325", "1");
        // disposition_labor
        dynaForm.set("field1266", "2697");
        // type_of_labour
        dynaForm.set("field1755", "2837");
        return dynaForm;
    }

    /**
     * To move patient to partograph - dilatation - 6 cm.
     * @param modCfg
     * @return DynaActionForm
     */
    public static DynaActionForm createDilatationProblem(ModuleConfig modCfg) {
        Long formId = new Long("65");
        DynaActionForm dynaForm = null;
        String now = DateUtils.getNow().toString();
        dynaForm = createDynaForm(modCfg, formId.toString());
        // phase
        dynaForm.set("field1487", "2755");
        // contractions_1250
        dynaForm.set("field1250", "1");
        // Contractions, Date of onset:
        dynaForm.set("field1251", now);
        // cervix_dilatation325
        dynaForm.set("field325", "6");
        // True labor
        dynaForm.set("field1262", "1");
        // disposition_labor - Admit for Active Labor
        dynaForm.set("field1266", "2697");
        // type_of_labour - Spontaneous
        dynaForm.set("field1755", "2837");
        return dynaForm;
    }

    /**
     * smCounselingVisit - pos
     * @param modCfg
     * @return DynaActionForm
     */
    public static DynaActionForm createCounselVisitPos(ModuleConfig modCfg) {
        Long formId = new Long("91");
        DynaActionForm dynaForm = null;
        String now = DateUtils.getNow().toString();
        dynaForm = createDynaForm(modCfg, formId.toString());
        //  Date of Couseling
        dynaForm.set("field1882", now);
        // Counselled
        dynaForm.set("field1930", "1");
        // HIV Tested
        dynaForm.set("field1931", "2966");
        // Date of Test
        dynaForm.set("field1865", now);
        // HIV Result - R - reactive
        dynaForm.set("field1866", "2940");
        // Patient received results
        dynaForm.set("field1867", "1");
        return dynaForm;
    }

    public static DynaActionForm createStillBirthRecord(ModuleConfig modCfg) {
        Long formId = new Long("40");
        DynaActionForm dynaForm = null;
        String now = DateUtils.getNow().toString();
        dynaForm = createDynaForm(modCfg, formId.toString());
        //  Date of Birth (infant)
        dynaForm.set("field844", now);
        // weight
        dynaForm.set("field846", "2.53");
        // Type of Still Birth
        dynaForm.set("field858", "455");
        return dynaForm;
    }

    /**
     * Create simple entry in the Partograph. Cannot use Conn since Partograph.insertValue is also used by javascript dwr function.
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param user
     * @param siteId
     */
    public static void createPartograph(Connection conn, Long patientId, Long pregnancyId, String user, String siteId) {

        String value = "160";
        String column = "1";
        String classNameString = "FetalHeartRate";
        String propertyName =  "fetalHeartRate";
        String dateVisitValue = DateUtils.getNow().toString();
        try {
            Partograph.insertValue(value, column, classNameString, propertyName, patientId.toString(), pregnancyId.toString(), user, siteId, dateVisitValue);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public static void createNewborns(Connection conn, Long patientId, Long pregnancyId, String user, String siteId, HttpSession session) {

        String newbornDateField = DateUtils.getNow().toString();
        String birthtime = DateUtils.getTime();
        String sequence = "1";
        String sex = "1";
        String weight = "2.3";
        try {
            PatientRecordUtils.createNewborn(conn, patientId.toString(), sequence, newbornDateField, sex, pregnancyId.toString(), birthtime, user, siteId, session, weight);
        } catch (Exception e) {
            log.error(e);
        }
        // now create the second infant
        sequence = "2";
        try {
            PatientRecordUtils.createNewborn(conn, patientId.toString(), sequence, newbornDateField, sex, pregnancyId.toString(), birthtime, user, siteId, session, weight);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public static Long createNewborn(Connection conn, Long patientId, Long pregnancyId, String user, String siteId, HttpSession session) {
    	Long infantId = null;
        String newbornDateField = DateUtils.getNow().toString();
        String birthtime = DateUtils.getTime();
        String sequence = "1";
        String sex = "1";
        String weight = "2.3";
        try {
            // PatientRecordUtils.createNewborn(newbornDateField, birthtime, sequence, patientId.toString(), sex, user, siteId, pregnancyId.toString(), session);
            infantId = PatientRecordUtils.createTestNewborn(conn, patientId.toString(), sequence, newbornDateField, sex, pregnancyId.toString(), birthtime, user, siteId, session, weight);
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }
        return infantId;
    }

    public static Long createStillborn(Connection conn, Long patientId, Long pregnancyId, String username, Long siteId, HttpSession session, SessionPatient sessionPatient, ModuleConfig cfg) {
    	Long infantId = null;
        String newbornDateField = DateUtils.getNow().toString();
        String birthtime = DateUtils.getTime();
        String sequence = "1";
        String sex = "1";
        String weight = "2.3";
        try {
             infantId = PatientRecordUtils.createTestNewborn(conn, patientId.toString(), sequence, newbornDateField, sex, pregnancyId.toString(), birthtime, username, siteId.toString(), session, weight);
        } catch (Exception e) {
            log.error(e);
        }
        return infantId;
    }

    public static DynaActionForm createDeliverySummary(ModuleConfig modCfg) {
        Long formId = new Long("66");
        DynaActionForm dynaForm = null;
        String now = DateUtils.getNow().toString();
        String time = DateUtils.getTime();
        dynaForm = createDynaForm(modCfg, formId.toString());
        // Latent phase duration - Normal
        dynaForm.set("field421", "244");
        // Rupture of Membranes Date
        dynaForm.set("field328", now);
        // Rupture of Membranes Time
        dynaForm.set("field329", time);
        // Mode of Delivery - Spontaneous Vaginal
        dynaForm.set("field447", "260");
        return dynaForm;
    }

    public static DynaActionForm createPregnancyConclusion(ModuleConfig modCfg) {
        String now = DateUtils.getNow().toString();
        Long formId = new Long("71");
        DynaActionForm dynaForm = null;
        dynaForm = createDynaForm(modCfg, formId.toString());
        // Pregnancy has ended
        dynaForm.set("field1367", "1");
        // Date Pregnancy Ended
        dynaForm.set("field1369", now);
        return dynaForm;
    }

    public static DynaActionForm createMaternaldischarge(ModuleConfig modCfg) {
        String now = DateUtils.getNow().toString();
        Long formId = new Long("68");
        DynaActionForm dynaForm = null;
        dynaForm = createDynaForm(modCfg, formId.toString());
        // Feeding Type
        dynaForm.set("field1791", "2886");
        // Bonding Well:
        dynaForm.set("field577", "1");
        // General Condition
        dynaForm.set("field260", "131");
        return dynaForm;
    }

    public static DynaActionForm createPostnatalMaternalVisit(ModuleConfig modCfg) {
        String now = DateUtils.getNow().toString();
        Long formId = new Long("28");
        DynaActionForm dynaForm = null;
        Form form = (Form) DynaSiteObjects.getForms().get(formId);
        dynaForm = fillData(modCfg, formId, form);
        return dynaForm;
    }

    private static DynaActionForm createDynaForm(ModuleConfig modCfg, String formId) {
        FormBeanConfig formCfg = modCfg.findFormBeanConfig("form" + formId);
        DynaActionFormClass dynaClass = DynaActionFormClass.createDynaActionFormClass(formCfg);
        DynaActionForm dynaForm = null;
        try {
            dynaForm = (DynaActionForm) dynaClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        dynaForm.set("field1", DateUtils.getNow().toString());
        return dynaForm;
    }

    private static DynaActionForm fillData(ModuleConfig modCfg, Long formId, Form form) {
        FormBeanConfig formCfg = modCfg.findFormBeanConfig("form" + formId.intValue());
        DynaActionFormClass dynaClass = DynaActionFormClass.createDynaActionFormClass(formCfg);
        DynaActionForm dynaForm = null;
        try {
            dynaForm = (DynaActionForm) dynaClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        // create random number
        Random generator = new Random();

        int first = generator.nextInt(100000);
        int sur = generator.nextInt(10000);
        int addNum = generator.nextInt(10000);
        int age = generator.nextInt(60) + 13;
        int six = generator.nextInt(6) + 1;
        int floatRan = generator.nextInt(100);
        int textRan = generator.nextInt(100000);

        /*PrevPregnancies prevPregnancies = new PrevPregnancies();
        Map prevMap = new HashMap();
        try {
            prevMap = BeanUtils.describe(prevPregnancies);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }*/

        Set pageItems = form.getPageItems();
        String value = null;
        for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
            PageItem pageItem = (PageItem) iterator.next();
            FormField formField = pageItem.getForm_field();
            if (formField.isEnabled()) {
                if (!formField.getType().equals("Display")) {
                    value = "";
                    int min = 0;
                    int max = 0;
                    if (formField.getMinValue() != null) {
                        if (formField.getMinValue().intValue() > 0) {
                            min = formField.getMinValue().intValue();
                        }
                    }
                    if (formField.getMaxValue() != null) {
                        if (formField.getMaxValue().intValue() > 0) {
                            max = formField.getMaxValue().intValue();
                        }
                    }
                    if (formField.getType().equals("Boolean")) {
                        value = "0";
                    } else if (formField.getType().equals("Long")) {
                        if (pageItem.getInputType().equals("prevPregnanciesLink")) {
                            // skip
                        } else {
                            value = String.valueOf(six);
                        }
                    } else if (formField.getType().equals("enum")) {
                        Set fieldEnums = formField.getEnumerations();
                        Object[] enumArray = fieldEnums.toArray();
                        FieldEnumeration fieldEnum = (FieldEnumeration) enumArray[0];
                        value = fieldEnum.getId().toString();
                    } else if (formField.getType().equals("Enum")) {
                        Set fieldEnums = formField.getEnumerations();
                        if (fieldEnums.size() > 0) {
                            Object[] enumArray = fieldEnums.toArray();
                            FieldEnumeration fieldEnum = null;
                            if (!pageItem.getInputType().equals("checkbox_dwr")) {
                                fieldEnum = (FieldEnumeration) enumArray[0];
                                value = fieldEnum.getId().toString();
                            } else {
                                value = "0";
                            }
                        } else {
                            if (pageItem.getInputType().equals("checkbox_dwr")) {
                                value = "0";
                            }
                        }

                    } else if (formField.getType().equals("Float")) {
                        if (formField.getMinValue() != null) {
                            if (min > 0) {
                                int intVal = generator.nextInt(max - min) + min;
                                value = String.valueOf(intVal);
                            } else if (max > 0) {
                                int intVal = generator.nextInt(max - min) + min;
                                value = String.valueOf(intVal);
                            } else {
                                value = String.valueOf(floatRan);
                            }
                        } else {
                            value = String.valueOf(floatRan);
                        }
                    } else if (formField.getType().equals("Date")) {
                        value = DateUtils.getNow().toString();
                    } else if (formField.getType().equals("Text")) {
                        value = "test value" + textRan;
                    } else if (formField.getType().equals("Time")) {
                        value = DateUtils.getTime();
                    } else if (formField.getType().equals("sex")) {
                        value = "1";
                    } else if (formField.getType().equals("Yes/No")) {
                        value = "0";
                    } else if (formField.getType().equals("Year")) {
                        if (formField.getMinValue() != null) {
                            if (min > 0) {
                                max = new Integer(DateUtils.getYear()).intValue();
                                int intVal = generator.nextInt(max - min) + min;
                                value = String.valueOf(intVal);
                            } else {
                                value = String.valueOf(floatRan);
                            }
                        }

                    } else if (formField.getType().startsWith("Integer")) {
                        if (formField.getMinValue() != null) {
                            if (min > 0) {
                                int intVal = generator.nextInt(max - min) + min;
                                value = String.valueOf(intVal);
                            } else if (max > 0) {
                                int intVal = generator.nextInt(max - min) + min;
                                value = String.valueOf(intVal);
                            } else {
                                value = String.valueOf(floatRan);
                            }
                        } else {
                            value = String.valueOf(floatRan);
                        }
                    } else {
                        value = "test value" + textRan;
                    }
                    dynaForm.set("field" + formField.getId(), value);
                }
            }
        }

        dynaForm.set("field1", DateUtils.getNow().toString());
        return dynaForm;
    }

    /**
     * Used when testing export of patient records to xml
     * Usage: String zeprsId = PopulatePatientRecord.createTestPatientId(zepSiteId, registration);
     * @param zepSiteId
     * @param registration
     * @return String zeprs id
     */
    public static String createTestPatientId(String zepSiteId, PatientRegistration registration) {
        // create a new patient id - this is for testing only
        String zePatientId = PatientId.setPatientId("5040", zepSiteId);
        String zepDistrictId = "5040";
        String zeprsId = PatientRecordUtils.createZeprsId(zepDistrictId, zepSiteId, zePatientId);
        registration.setField1513(zeprsId);
        registration.setField6(registration.getField6() + "Test");
        return zeprsId;
    }
}
