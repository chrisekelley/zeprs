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
import org.cidrz.project.zeprs.valueobject.gen.Antesum;
import org.cidrz.project.zeprs.valueobject.BaseEncounter;
import org.cidrz.webapp.dynasite.dao.ReferralDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;
import org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Display correct safe motherhood form - in edit or new form mode
 * Add patient, confirmation, and pregnancy to the session.
 */

public final class UTHSummaryAction extends BasePatientAction {
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


        Long patientId = null;
        Long pregnancyId = null;
        SessionPatient sessionPatient = null;
        HttpSession session = request.getSession();
        sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
        patientId = sessionPatient.getId();
        pregnancyId = sessionPatient.getCurrentPregnancyId();
        
        Long formId = null;
        if (request.getParameter("formId") != null) {
            formId = Long.decode(request.getParameter("formId"));
        }

        try {
            ReferralOutcome referral = (ReferralOutcome) ReferralDAO.getMostRecentReferral(patientId, true);
            request.setAttribute("referral", referral);
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            request.setAttribute("noReferralMessage", "No referral for this visit");
        }

        Form nextForm = new Form();
        BaseEncounter antesum = null;
        ActionForward forward = null;
        try {
            antesum = (BaseEncounter) PersistenceManagerFactory.getInstance(Antesum.class).getPatientData(patientId, pregnancyId);
        } catch (PersistenceException e) {
            request.setAttribute("exception", e);
            return mapping.findForward("error");
        } catch (ObjectNotFoundException e) {
            // send user to new  form
            nextForm = (Form) DynaSiteObjects.getForms().get(formId);
            String formName = nextForm.getName();
            request.setAttribute("name", formName);
            request.setAttribute("id", nextForm.getId());
            forward = new ActionForward("/summaryform" + formId + "/new.do");
            return forward;
        }

        request.setAttribute("encounterId", antesum.getId().toString());
        forward = new ActionForward("/summaryform" + formId + "/correct.do");
        return forward;
    }

}
