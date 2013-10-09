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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.project.zeprs.valueobject.gen.DeliverySum;
import org.cidrz.project.zeprs.valueobject.gen.PartographStatus;
import org.cidrz.project.zeprs.valueobject.partograph.*;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.partograph.*;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Apr 8, 2005
 * Time: 5:42:32 PM
 */
public final class PartographAction extends BasePatientAction {

    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    protected ActionForward doExecute(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {

        //assemble the items we're going to pass in the request
        HttpSession session = request.getSession();
        // set the session to 90 minutes - 5400 seconds.
        session.setMaxInactiveInterval(5400);
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            Long patientId = null;
            try {
                patientId = SessionUtil.getInstance(session).getSessionPatient().getId();
            } catch (SessionUtil.AttributeNotFoundException e) {
                return mapping.findForward("home");
            }
            Long pregnancyId = SessionUtil.getInstance(session).getSessionPatient().getCurrentPregnancyId();

            // Has partograh already been started?
            PartographStatus partographStatus = null;
            try {
                partographStatus = (PartographStatus) EncountersDAO.getMostRecentRecord(conn, patientId, pregnancyId, new Long("79"), PartographStatus.class);
               // Date startedDate = new Date(partographStatus.getCreated().getTime());
                request.setAttribute("started", partographStatus.getCreated());
                request.setAttribute("startedLong", partographStatus.getCreated().getTime());
                request.setAttribute("subject", partographStatus);
                request.setAttribute("formId", "79");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ObjectNotFoundException e) {
                // it's ok - not started yet.
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            // FetalHeartRate
            FetalHeartRate fetalHr = null;
            try {
                FetalHeartRateDAO dao = new FetalHeartRateDAO();
                fetalHr = (FetalHeartRate) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("fetalHr", fetalHr);

            // Liquor
            Liquor liquor = null;
            try {
                LiquorDAO dao = new LiquorDAO();
                liquor = (Liquor) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("liquor", liquor);

            // Moulding
            Moulding moulding = null;
            try {
                MouldingDAO dao = new MouldingDAO();
                moulding = (Moulding) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("moulding", moulding);

            // Cervix
            Cervix cervix = null;
            try {
                CervixDAO dao = new CervixDAO();
                cervix = (Cervix) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("cervix", cervix);

            // Descent
            Descent descent = null;
            try {
                DescentDAO dao = new DescentDAO();
                descent = (Descent) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("descent", descent);

            // Contractions
            Contractions contractions = null;
            try {
                ContractionsDAO dao = new ContractionsDAO();
                contractions = (Contractions) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("contractions", contractions);

            // Oxytocin
            Oxytocin oxytocin = null;
            try {
                OxytocinDAO dao = new OxytocinDAO();
                oxytocin = (Oxytocin) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("oxytocin", oxytocin);

            // DrugsDispensed
            DrugsDispensed drugsDispensed = null;
            try {
                DrugsDispensedDAO dao = new DrugsDispensedDAO();
                drugsDispensed = (DrugsDispensed) dao.getOne(conn, patientId, pregnancyId);

            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("drugsDispensed", drugsDispensed);

            // BloodPressure
            BloodPressure bloodPressure = null;
            try {
                BloodPressureDAO dao = new BloodPressureDAO();
                bloodPressure = (BloodPressure) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("bloodPressure", bloodPressure);

            // Pulse
            Pulse pulse = null;
            try {
                PulseDAO dao = new PulseDAO();
                pulse = (Pulse) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("pulse", pulse);

            // Temperature
            Temperature temperature = null;
            try {
                TemperatureDAO dao = new TemperatureDAO();
                temperature = (Temperature) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("temperature", temperature);

            // Respiration
            Respiration respiration = null;
            try {
                RespirationDAO dao = new RespirationDAO();
                respiration = (Respiration) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("respiration", respiration);

            // UrineAmount
            UrineAmount urineAmount = null;
            try {
                UrineAmountDAO dao = new UrineAmountDAO();
                urineAmount = (UrineAmount) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("urineAmount", urineAmount);

            // UrinalysisProtein
            UrinalysisProtein urinalysisProtein = null;
            try {
                UrinalysisProteinDAO dao = new UrinalysisProteinDAO();
                urinalysisProtein = (UrinalysisProtein) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("urinalysisProtein", urinalysisProtein);

            // UrinalysisAcetone
            UrinalysisAcetone urinalysisAcetone = null;
            try {
                UrinalysisAcetoneDAO dao = new UrinalysisAcetoneDAO();
                urinalysisAcetone = (UrinalysisAcetone) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("urinalysisAcetone", urinalysisAcetone);

            // UrinalysisGlucose
            UrinalysisGlucose urinalysisGlucose = null;
            try {
                UrinalysisGlucoseDAO dao = new UrinalysisGlucoseDAO();
                urinalysisGlucose = (UrinalysisGlucose) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("urinalysisGlucose", urinalysisGlucose);

            // VaginalExamParto
            VaginalExamParto vaginalExam = new VaginalExamParto();
            try {
                VaginalExamPartoDAO dao = new VaginalExamPartoDAO();
                vaginalExam = (VaginalExamParto) dao.getOne(conn, patientId, pregnancyId);
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
            request.setAttribute("vaginalExam", vaginalExam);

            DeliverySum delivSum = null;
            try {
                delivSum = (DeliverySum) EncountersDAO.getOne(conn, patientId, pregnancyId, "SQL_RETRIEVE66", DeliverySum.class);
            } catch (IOException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (SQLException e) {
                log.error(e);
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ObjectNotFoundException e) {
                // it's ok
            }

            if (delivSum != null) {
                request.setAttribute("delivSum", delivSum);
            }
            request.setAttribute("user", username);
            String siteId = "";
            try {
                siteId = SessionUtil.getInstance(session).getClientSettings().getSiteId().toString();
            } catch (SessionUtil.AttributeNotFoundException e) {
                // it's ok - we're in admin mode.
            }

            request.setAttribute("siteId", siteId);

        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return mapping.findForward("success");
    }
}

