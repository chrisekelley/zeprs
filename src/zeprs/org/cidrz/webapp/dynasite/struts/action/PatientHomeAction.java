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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;

/**
 * Loads the patient onto the session
 */

public final class PatientHomeAction extends BasePatientAction {
    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());


    /**
     * Negotiate whether the user has a current pregnancy or needs to view history.
     * <p/>
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
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
    	Long flowId = null;
    	SessionPatient sessionPatient = null;
    	HttpSession session = request.getSession();

        try {
			sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
		} catch (SessionUtil.AttributeNotFoundException e1) {
			//log.debug("No session - SessionUtil.AttributeNotFoundException" + e1);
			return mapping.findForward("home");
		}
		patientId = sessionPatient.getId();
		pregnancyId = sessionPatient.getCurrentPregnancyId();
        String forward;
        String params = null;

        // test of the pregnancy conclusion form needs to be completed.
        Integer egaCurrent = sessionPatient.getCurrentEgaCalc();
        // 315 + 42 (6 weeks) = 357;
        if (egaCurrent != null && egaCurrent > 357) {
            Form nextForm = (Form) DynaSiteObjects.getForms().get(new Long("71"));
            String formName = nextForm.getName();
            request.setAttribute("name", formName);
            request.setAttribute("id", nextForm.getId());
            return mapping.findForward("nextForm");
        }

        if ((sessionPatient.getCurrentFlowId()) != null) {
            Long currentFlowId = sessionPatient.getCurrentFlowId();
            Long currentFormId = sessionPatient.getCurrentFormId();
            switch (currentFlowId.intValue()) {
                // Antepartum
                case 1:
                    forward = "patientAnte";
                    break;
                    // History
                case 2:
                    request.setAttribute("flowId", "2");
                    params = "flowId=2";
                    forward = "patientTask";
                    break;
                // intrapartum - partograph
                case 3:
                    try {
                        if (sessionPatient.getPartographStatus().getField1551() == null) {
                            forward = "partograph";
                            // if partograph is closed, send to delivery summary
                        } else {
                            Form nextForm = (Form) DynaSiteObjects.getForms().get(new Long("66"));
                            String formName = nextForm.getName();
                            request.setAttribute("name", formName);
                            request.setAttribute("id", nextForm.getId());
                            return mapping.findForward("nextForm");
                        }
                    } catch (NullPointerException e) {
                        // it's ok
                        request.setAttribute("flowId", "7");
                        params = "flowId=7";
                        forward = "patientTask";
                    }
                    break;
                    // Delivery summary/postnatal
                case 4:
                    request.setAttribute("flowId", "4");
                    params = "flowId=4";
                    forward = "patientTask";
                    break;
                    // nicu
                case 5:
                    request.setAttribute("flowId", "5");
                    params = "flowId=5";
                    forward = "patientTask";
                    break;
                    // uth discharge
                case 6:
                    // ante hosp summary
                    if (currentFormId.intValue() == 63) {
                        forward = "patientAnte";
                    } else {
                        // postnatal hosp summary
                        // request.setAttribute("flowId", "4");
                        forward = "discharge";
                    }
                    break;
                    // Labour
                case 7:
                    try {
                        if (sessionPatient.getPartographStatus().getField1551() == null) {
                            forward = "partograph";
                            // if partograph is closed, send to delivery summary
                        } else {
                            Form nextForm = (Form) DynaSiteObjects.getForms().get(new Long("66"));
                            String formName = nextForm.getName();
                            request.setAttribute("name", formName);
                            request.setAttribute("id", nextForm.getId());
                            return mapping.findForward("nextForm");
                        }
                    } catch (NullPointerException e) {
                        // it's ok
                        request.setAttribute("flowId", "7");
                        params = "flowId=7";
                        forward = "patientTask";
                    }
                    break;
                case 9:   // new patient registration
                    forward = "pregnancyDating";    // send to pregnancyDating
                    break;
                case 102: // lab
                    if (sessionPatient.getDeliveryCompleted().equals(Boolean.TRUE)) {
                        request.setAttribute("flowId", "4");
                        params = "flowId=4";
                        forward = "patientTask";
                    } else {
                        forward = "patientAnte";
                    }
                    break;
                case 103: // safe motherhood
                    if (sessionPatient.getDeliveryCompleted().equals(Boolean.TRUE)) {
                        request.setAttribute("flowId", "4");
                        params = "flowId=4";
                        forward = "patientTask";
                    } else {
                        forward = "patientAnte";
                    }
                    break;
                default:
                    // request.setAttribute("flowId", "2");
                    forward = "problem";
                    break;
            }
        } else {
            // forward = "pregnancyDating";
            if (sessionPatient.getParentId() != null) {
                request.setAttribute("flowId", "4");
                params = "flowId=4";
                forward = "patientTask";
            } else {
                if (sessionPatient.getCurrentPregnancyId().intValue() == -1) {
                    forward = "pregnancyDating";
                } else {
                    forward = "previousPregnancies";
                }
            }
        }

        // if patient's current pregnancy is over, send to the previous pregnancies page, to choose the pregnancy to view.
        // if ((sessionPatient.getCurrentPregnancyId() != null) && (sessionPatient.getDatePregnancyEnd() != null)) {
        /*if ((sessionPatient.getCurrentPregnancyId() == null)) {
            // sessionPatient.setCurrentPregnancyId(null);
            // sessionPatient.setDatePregnancyBegin(null);
            // sessionPatient.setDatePregnancyEnd(null);
            if (sessionPatient.getParentId() != null) {
                request.setAttribute("flowId", "4");
                forward = "patientTask";
            } else {
                forward = "previousPregnancies";
            }
        }*/

        // if user is data clerk, send to home page.
        if (request.isUserInRole("CREATE_NEW_PATIENTS_AND_SEARCH")) {
            request.setAttribute("patientId", patientId);
            ActionForward forwardForm = null;
            String forwardString = "/demographics.do?patientId=" + patientId;
            forwardForm = new ActionForward(forwardString);
            return forwardForm;
        } else {
            // request.setAttribute("patientId", patientId);
            // mapping.setParameter("patientId=" + patientId);
            // return mapping.findForward(forward);
            request.setAttribute("patientId", patientId);
            ActionForward forwardForm = null;
            String forwardString = null;
            if (params != null) {
                forwardString = "/" + forward + ".do?patientId=" + patientId + "&" + params;
            } else {
                forwardString = "/" + forward + ".do?patientId=" + patientId;
            }
            forwardForm = new ActionForward(forwardString);
            forwardForm.setRedirect(true);
            return forwardForm;
        }
    }
}
