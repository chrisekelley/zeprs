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
import org.apache.struts.validator.DynaValidatorForm;
import org.cidrz.project.zeprs.valueobject.gen.Puerperium;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.utils.StringManipulation;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Prepare patient record.
 * Add patient, confirmation, and pregnancy to the session.
 * Create the chart, tasklist, and problems list.
 */

public final class PatientPuerAction extends BasePatientAction {
    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    /**
     * @param mapping  The ActionMapping used to select this instance
     * @param form     The optional ActionForm bean for this request (if any)
     * @param request  The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return Action to forward to
     * @throws Exception if an input/output error or servlet exception occurs
     */
    protected ActionForward doExecute(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {

        // Extract attributes we will need
        HttpSession session = request.getSession();
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            SessionPatient sessionPatient = null;
            try {
                sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
            } catch (SessionUtil.AttributeNotFoundException e) {
                log.error("Unable to get SessionPatient");
            }

            Long patientId = null;
            Long pregnancyId = null;
            try {
                assert sessionPatient != null;
                patientId = sessionPatient.getId();
                pregnancyId = sessionPatient.getCurrentPregnancyId();
            } catch (Exception e) {
                log.error("Unable to get SessionPatient field" + e);
            }

            // populate the Antenatal table
            List chartItems = null;

            try {
                chartItems = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("81"), Puerperium.class);
            } catch (IOException e) {
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (ServletException e) {
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (SQLException e) {
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            } catch (NumberFormatException e) {
                request.setAttribute("exception", e);
                return mapping.findForward("error");
            }

            request.setAttribute("chartItems", chartItems);
            // loading of body onload DWRUtil.useLoadingMessage()
            request.setAttribute("dwr", 1);

            Form encounterForm = (Form) DynaSiteObjects.getForms().get(Long.valueOf("81"));

            // Attach a map of encounter values that has enumerations already resolved.
            for (int i = 0; i < chartItems.size(); i++) {
                Puerperium encounter = (Puerperium) chartItems.get(i);
                Map encMap = new LinkedHashMap();
                encMap = PatientRecordUtils.getEncounterMap(encounterForm, encounter, "fieldId");
                encounter.setEncounterMap(encMap);
            }

            Patient parent = null;

            // if this is Maternaldischarge link, preset problem field
            if (mapping.getPath().equals("/maternalDischargeAction")) {
                DynaValidatorForm dynaForm = (DynaValidatorForm) form;
                dynaForm.set("field1655", "checked");
            }

            request.setAttribute("encounterForm", encounterForm);

            // ega
            Integer egaToday = null;
            egaToday = sessionPatient.getCurrentEgaCalc();

            request.setAttribute("egaToday", egaToday);

            // tells tags to enabled dwr input for data
            request.setAttribute("dwr_input", "1");

            // The following items are used to render the javascript for the widgets
            String classname = StringManipulation.fixClassname(encounterForm.getName());
            request.setAttribute("classname", classname);

            // name of the remote class used for DWR
            request.setAttribute("remoteClass", "GenericChart");

            // String user = request.getUserPrincipal().getName();
            request.setAttribute("user", username);

            String siteId = "";
            try {
                siteId = SessionUtil.getInstance(session).getClientSettings().getSiteId().toString();
            } catch (SessionUtil.AttributeNotFoundException e) {
                // it's ok - we're in admin mode.
            }

            request.setAttribute("siteId", siteId);

            Boolean status = Boolean.valueOf(true);
            List activeProblems = PatientRecordUtils.assembleProblemTaskList(conn, patientId, pregnancyId, status, sessionPatient);
            request.setAttribute("activeProblems", activeProblems);

            // used for uth discharge
            if (request.getParameter("referralId") != null) {
                Long referralId = Long.valueOf(request.getParameter("referralId"));
                request.setAttribute("referralId", referralId);
                Outcome referral = OutcomeDAO.getOne(conn, referralId);
                DynaValidatorForm dynaForm = (DynaValidatorForm) form;
                try {
                    String field1911 = (String) dynaForm.get("field1911");
                    if (field1911.equals("")) {
                        dynaForm.set("field1911", referralId.toString());
                    }
                } catch (IllegalArgumentException e) {
                    // not in this form
                }

                request.setAttribute("referral", referral);
            }
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