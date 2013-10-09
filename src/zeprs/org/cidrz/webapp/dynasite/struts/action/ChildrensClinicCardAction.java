/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.project.zeprs.report.valueobject.ChildrensClinicCard;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.Newbornrecord;
import org.cidrz.project.zeprs.valueobject.report.gen.*;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.reports.ReportDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ChildrensClinicCardAction extends BaseAction {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(ChildrensClinicCardAction.class);

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Long patientId = null;
        Long parentId = null;
        try {
            patientId = Long.valueOf(request.getParameter("patientId"));
            parentId = Long.valueOf(request.getParameter("parentId"));

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Principal user = request.getUserPrincipal();
        String username = user.getName();

        ChildrensClinicCard childrensClinicCard = new ChildrensClinicCard();

        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            // get the demographics for newborn and mother
            PatientRegistrationReport patReg = (PatientRegistrationReport) EncountersDAO.getResolvedOne(conn, patientId, Long.valueOf(1), PatientRegistrationReport.class);
            childrensClinicCard.setPatientRegistration(patReg);
            PatientRegistrationReport motherRegistration = (PatientRegistrationReport) EncountersDAO.getResolvedOne(conn, parentId, Long.valueOf(1), PatientRegistrationReport.class);
            childrensClinicCard.setNotherRegistration(motherRegistration);
            // get the siblings
            List prevPregnancies = ReportDAO.getAllResolved(conn, parentId, Long.valueOf(2), PrevPregnanciesReport.class);
            childrensClinicCard.setSiblings(prevPregnancies);

            NewbornEvalReport newbornEval;
			try {
				newbornEval = (NewbornEvalReport) EncountersDAO.getResolvedOne(conn, patientId, Long.valueOf(23), NewbornEvalReport.class);
				childrensClinicCard.setNewbornEval(newbornEval);
			} catch (ObjectNotFoundException e1) {
				// no newborn eval submitted yet...
				NewbornrecordReport newbornReport = (NewbornrecordReport) EncountersDAO.getResolvedOne(conn, patientId, Long.valueOf(109), NewbornrecordReport.class);
				newbornEval = new NewbornEvalReport();
				newbornEval.setDate_of_birthR(newbornReport.getDate_of_birthR());
				newbornEval.setTime_of_birth_1514R(newbornReport.getTime_of_birth_1514R());
				newbornEval.setSequence_num_489R(newbornReport.getSequence_num_489R());
				newbornEval.setSex_490R(newbornReport.getSex_490R());
				newbornEval.setWeight_at_birth_491R(newbornReport.getWeight_at_birth_491R());
				childrensClinicCard.setNewbornEval(newbornEval);
			}
            

            List postnatalInfantvisits = ReportDAO.getAllResolved(conn, patientId, Long.valueOf(86), PostnatalInfantReport.class);
            childrensClinicCard.setPostnatalInfantVisits(postnatalInfantvisits);

            List probPostnatalInfantvisits = ReportDAO.getAllResolved(conn, patientId, Long.valueOf(32), ProbPostnatalInfantReport.class);
            childrensClinicCard.setProbPostnatalInfantVisits(probPostnatalInfantvisits);

            try {
                InfantDischargeSummaryReport infantDischarge = (InfantDischargeSummaryReport) EncountersDAO.getResolvedOne(conn, patientId, Long.valueOf(84), InfantDischargeSummaryReport.class);
                childrensClinicCard.setInfantDischargeSummary(infantDischarge);
            } catch (ObjectNotFoundException e) {
                // it's ok
            }

            try {
                Map encMap;
                EncounterData report = null;
                SafeMotherhoodCareReport smc = (SafeMotherhoodCareReport) EncountersDAO.getOneReport(conn, parentId, (long) 92, SafeMotherhoodCareReport.class);
                Form encounterForm = (Form) DynaSiteObjects.getForms().get((long) 92);
                encMap = PatientRecordUtils.getEncounterMap(encounterForm, smc, "starSchemaNameR");
                smc.setEncounterMap(encMap);
                try {
                    BeanUtils.copyProperties(smc, smc.getEncounterMap());
                } catch (IllegalAccessException e) {
                    log.error(e);
                } catch (InvocationTargetException e) {
                    log.error(e);
                }
                childrensClinicCard.setSmc(smc);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ObjectNotFoundException e) {
                // it's ok
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            XStream xstream = new XStream();
            xstream.alias("PatientRegistrationReport", PatientRegistrationReport.class);
            xstream.alias("PrevPregnanciesReport", PrevPregnanciesReport.class);
            xstream.alias("PostnatalInfantReport", PostnatalInfantReport.class);
            xstream.alias("log", org.apache.commons.logging.impl.Log4JLogger.class);
            xstream.alias("baby", org.cidrz.project.zeprs.valueobject.report.gen.NewbornEvalReport.class);
            xstream.alias("SMP", org.cidrz.project.zeprs.report.SafeMotherhoodRegisterPatient.class);
            xstream.alias("ChildrensClinicCard", org.cidrz.project.zeprs.report.valueobject.ChildrensClinicCard.class);
            xstream.alias("smc", SafeMotherhoodCareReport.class);
            response.setContentType("text/xml");
            // response.setContentType("application/vnd.ms-excel");
            PrintWriter writer = response.getWriter();
            writer.write("<?xml version=\"1.0\"?>\n");
            writer.write("<?xml-stylesheet type=\"text/xsl\" href=\"xsl/ChildrensClinicCard.xsl\" ?>\n");
            xstream.toXML(childrensClinicCard, writer);
            //writer.write(xml);
            writer.flush();
            writer.close();

        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }

        // use writer to render text
        return (null);
    }
}
