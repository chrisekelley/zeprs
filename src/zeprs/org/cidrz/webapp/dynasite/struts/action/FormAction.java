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

import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.DynaValidatorForm;
import org.cidrz.project.zeprs.valueobject.BaseEncounter;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.LabTest;
import org.cidrz.project.zeprs.valueobject.gen.NewbornEval;
import org.cidrz.project.zeprs.valueobject.gen.Rpr;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.FormDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.PatientStatusDAO;
import org.cidrz.webapp.dynasite.dao.SessionPatientDAO;
import org.cidrz.webapp.dynasite.dao.UserDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.logic.EncounterProcessor;
import org.cidrz.webapp.dynasite.security.AuthManager;
import org.cidrz.webapp.dynasite.security.UserUnauthorizedException;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.utils.PopulatePatientRecord;
import org.cidrz.webapp.dynasite.utils.StringManipulation;
import org.cidrz.webapp.dynasite.utils.struts.StrutsUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Pregnancy;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;
import org.cidrz.webapp.dynasite.valueobject.Site;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 14, 2005
 *         Time: 11:14:28 AM
 */
public class FormAction extends BasePatientAction {

    /**
     * Commons Logging instance.
     */

    private static Log log = LogFactory.getFactory().getInstance(FormAction.class);

    /**
     * Create record from form.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Extract attributes we will need
        HttpSession session = request.getSession();
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        DynaValidatorForm dynaForm = null;

        int formId = 0;

        SessionPatient sessionPatient = null;
        Long patientId = null;
        Long pregnancyId = null;
        Long flowId = null;

        dynaForm = (DynaValidatorForm) form;
        Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
        Long siteId = site.getId();
        Boolean newPregnancy = null;

        // formId = mapping.getParameter();
        try {
            formId = Integer.parseInt(mapping.getParameter().trim());
            // log.debug("Form id: " + formId);
        } catch (NumberFormatException e) {
            log.error(e);
        }

        if (formId == 1) {
        	// check if there is a duplicate id
        	Object item = dynaForm.get("field1513");
        	if (item != null) {
        		String zeprsId = (String) item;
        		Connection conn = null;
        		conn = DatabaseUtils.getZEPRSConnection(username);
        		Boolean status = PatientDAO.checkPatientId(conn, zeprsId);
        		if (status == Boolean.FALSE) {
        			request.setAttribute("exception", "You have assigned a duplicate ZEPRS ID - the ID you entered is already taken. Please return to the Registration page and enter a unique ZEPRS ID. Be sure to click 'Check ID' if you are manually entering an ID.");
        			log.error("Duplicate id attempted to be assigned: " + zeprsId + " by " + username);
                    return mapping.findForward("error");
        		}
        		conn.close();
        	}
        }

        //resolve the patientId - it has been either push via the request or gathered from the sessionPatient
        if (formId != 1 && formId != 125) {
            sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
            patientId = sessionPatient.getId();
        }
        
        if (formId == 125) {
//        	private String field2157;	//username
//        	private String field2158;	//password
//        	private String field2159;	//email
//        	private String field2160;	//forenames
//        	private String field2161;	//lastname
//        	private String field2162;	//mobile
//        	private String field2163;	//phone
        	
			String password = (String) dynaForm.get("field2158");	//password
			ActionMessages errors = new ActionMessages();
			if (password.length() > 12) {
				errors.add("errors", new ActionMessage("errors.password.length.long"));
				saveErrors(request, errors);
			} else if (password.length() < 8) {
				errors.add("errors", new ActionMessage("errors.password.length.short"));
				saveErrors(request, errors);
			}

        	// Check for duplicate username
        	if ( dynaForm.get("field2157") != null) {	//username
        		String searchUsername = (String) dynaForm.get("field2157");
        		Object userObject;
        		Connection conn = null;
				try {
                    conn = DatabaseUtils.getZEPRSConnection(username);
					userObject = UserDAO.getUser(conn, searchUsername);
					errors.add("errors", new ActionMessage("errors.duplicate.username", searchUsername));
				} catch (ObjectNotFoundException e) {
					// It's ok - there should not be a user.
				 } finally {
                     if (conn != null && !conn.isClosed()) {
                         conn.close();
                     }
                 }
        	}
//			String hashPass = null;
//			if (Constants.PASSWORD_ALGORITHM != null && Constants.PASSWORD_ENCODING != null) {
//        		String algorithm = Constants.PASSWORD_ALGORITHM;
//        		String encodingMethod = Constants.PASSWORD_ENCODING;
//        		try {
//        			hashPass = EncryptionUtils.hash(algorithm, encodingMethod, password);
//        		} catch (Exception e) {
//        			// TODO Auto-generated catch block
//        			e.printStackTrace();
//        		}
//				dynaForm.set("password", hashPass);
//        	} else {
//        		log.debug("You must set up password.algorithm and password.encoding when encryptionMethod equals app");
//        	}
		}

        // Some of the forms are submitted in edit-mode. We need to deal w/ these up-front

        // Previous Pregnancies form
        // If user clicks "None" forward to the next form.
        // Selecting none in prev preg form does not require password
        if (formId == 2) {
            if (request.getParameter("forward") != null) {
                if (request.getParameter("forward").equals("none")) {
                    Connection conn = null;
                    try {
                        conn = DatabaseUtils.getZEPRSConnection(username);
                        pregnancyId = sessionPatient.getCurrentPregnancyId();
                        PatientStatusDAO.updateNoPreviousPregnancies(conn, patientId, username, siteId);
                        // need to load new value for noPreviousPregnancies
                        SessionPatientDAO.updateSessionPatient(conn, patientId, pregnancyId, session);
                    } catch (Exception e) {
                        log.error(e);
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    } finally {
                        if (conn != null && !conn.isClosed()) {
                            conn.close();
                        }
                    }
                    resetToken(request);
                    Form nextForm = (Form) DynaSiteObjects.getForms().get(new Long("70"));
                    String formName = nextForm.getName();
                    request.setAttribute("name", formName);
                    request.setAttribute("id", nextForm.getId());
                    ActionForward forwardForm = null;
                    forwardForm = new ActionForward("/form70/new.do");
                    forwardForm.setRedirect(true);
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                    return forwardForm;
                }
            }
        }

        /**
         * Prevent Delivery Summary form from being posted from a child's record.
         */
        if (formId == 66) {
            if (sessionPatient != null && sessionPatient.getParentId() != null) {
                request.setAttribute("exception", "You may not post a Delivery Summary form while viewing a child. Please return to the mother's record.");
                return mapping.findForward("error");
            }
        }

        // Get a form and flow from the formDef; add them to the encounter
        Form formDef = (Form) DynaSiteObjects.getForms().get(new Long(formId));

        if (formDef.isRequireReauth()) {

            try {
                AuthManager.confirmIdentity(request, user.getName(), request.getParameter("password"));
            } catch (UserUnauthorizedException e) {
                ActionMessages errors = new ActionMessages();
                errors.add("errors", new ActionMessage("errors.userunauthorized"));
                saveErrors(request, errors);
                try {
                    String formName = (String) DynaSiteObjects.getFormNames().get("form" + formId);
                    if (formName == null) {
                        return mapping.getInputForward();
                    } else {
                        if (formName.equals("demographics")) {
                            return mapping.getInputForward();
                        } else {
                            return mapping.findForward(formName + "Error");
                        }
                    }
                } catch (Exception e1) {
                    return mapping.getInputForward();
                }
            }
        }

        Connection conn = null;
        try {
        	if (formId == 125) {
            	conn = DatabaseUtils.getSpecialRootConnection(username);
        	} else {
            	conn = DatabaseUtils.getZEPRSConnection(username);
        	}
        	Long encounterId = null;
        	try {
        		encounterId = (Long) dynaForm.get("id");
        	} catch (IllegalArgumentException e) {
        		if (request.getParameter("id") != null) {
        			if (!request.getParameter("id").equals("")) {
        				encounterId = Long.valueOf(request.getParameter("id"));
        			} else if (formId == 79) {
        				// when user closes partograph, there is a chance the form did not have the id
        				EncounterData encounter = null;
        				try {
        					encounter = (EncounterData) EncountersDAO.getId(conn, patientId, pregnancyId, new Long(formId));
        					encounterId = encounter.getId();
        					// log.debug("Partograph id= " + encounterId);
        				} catch (ObjectNotFoundException e1) {
        					log.error("Partograph record not found.  patientId: " + patientId + ", pregnancyId: " + pregnancyId + ", formId: " + formId);
        				}
        			}
        		}
        	}

        	if (sessionPatient != null) {
        		if (sessionPatient.getCurrentPregnancyId() == null) {
        			newPregnancy = Boolean.TRUE;
        		}
        	}

        	if (formId == 87) {  // Lab request - form 87
        		String selectionList = request.getParameter("selectionList1845");
        		if (!selectionList.equals("")) {
        			String[] valueList = selectionList.split(",");
        			for (int x = 0; x < valueList.length; x++) {
        				String value = valueList[x];
        				int labTypeId = Integer.valueOf(value);
        				if (labTypeId == 3043) {
        					saveRprTestRequest(dynaForm, patientId, sessionPatient, conn, username, siteId, labTypeId);
        				} else {
        					saveLabTestRequest(dynaForm, patientId, sessionPatient, conn, username, siteId, labTypeId);
        				}

        			}
        		}
        		if (!conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
        		return createForward(request, mapping, patientId, pregnancyId, dynaForm, session, formId);
        	}

        	if (formId == 89) {  // arv regimen form
        		String fieldCheck = dynaForm.getMap().get("field1996").toString();
        		if (fieldCheck.equals("1")) { // if Tested for CD4? is true
        			// submit an instnce of CD4 Lab request - form 87
        			saveLabTestRequest(dynaForm, patientId, sessionPatient, conn, username, siteId, 3042);
        		}
        	}

        	if (formId == 80) {  // Routine Antenatal form
        		String egaValue = dynaForm.getMap().get("field129").toString();

        		String dateVisitValue = dynaForm.getMap().get("field1").toString();
        		Date now = DateUtils.getNow();
        		Date dateVisit = DateUtils.getvisitDate(dynaForm);
        		long diff = DateUtils.calculateDays(dateVisit, now);
        		if (diff > 0) { // If dateVisit ! = now
        			// calc ega for this visit.
        			Long ega = Long.valueOf(egaValue);
        			long newEga = ega - diff;
        			if (newEga < ega) {
        				dynaForm.set("field129", String.valueOf(newEga));
        			}
        		}
        	}
        	EncounterData vo = null;
        	try {
        		vo = PopulatePatientRecord.saveForm(conn, formDef, String.valueOf(formId), patientId, dynaForm, encounterId, siteId, username, sessionPatient);
        	} catch (Exception e) {
        		log.error("Error saving record - formId: " + formId + ", patientId: " + patientId + ", encounterId: "
        				+ encounterId + ", siteId: " + siteId + ", username: " + username + " Error: " + e);
        		if (sessionPatient == null) {
        			log.error("Error saving record - null sessionPatient");
        		}
        		e.printStackTrace();
        		if (!conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
        		request.setAttribute("exception", e);
                return mapping.findForward("error");
        	}

        	// need to update the globals for some of the forms
        	// Now set sessionPatient for form 1, patient registration form
        	if ((formId == 1) || (newPregnancy == Boolean.TRUE)) {
        		try {
        			SessionPatientDAO.updateSessionPatient(conn, vo.getPatientId(), vo.getPregnancyId(), session);
        			// re-initialise the globals
        			sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
        			sessionPatient.setCurrentFlowId(new Long("9"));
        			flowId = sessionPatient.getCurrentFlowId();
        			pregnancyId = sessionPatient.getCurrentPregnancyId();
        			patientId = sessionPatient.getId();
        		} catch (ServletException e) {
        			log.error(e);
        		} catch (SQLException e) {
        			log.error(e);
        		} catch (SessionUtil.AttributeNotFoundException e) {
        			log.error(e);
        		} catch (ObjectNotFoundException e) {
        			log.error(e);
        		} catch (NumberFormatException e) {
        			log.error(e);
        		} catch (NullPointerException e) {
        			log.error("newPregnancy: " + newPregnancy + "Error: " + e);
        			if (vo == null) {
        				log.error("vo (EncounterData) is null after form 1 registration submitted.");
        			}
        			if (session == null) {
        				log.error("session is null after form 1 registration submitted.");
        			}
        		}
        	} else if (formId == 82) {
        		// pregnancy dating
        		SessionPatientDAO.updateSessionPatient(conn, vo.getPatientId(), vo.getPregnancyId(), session);
        		// re-initialise the globals
        		sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
        		flowId = sessionPatient.getCurrentFlowId();
        	} else if (pregnancyId == null) {
    			//Forms that don't require patient(including admin forms) don't need the session refreshed since they aren't patient oriented
        		if (formDef.isRequirePatient() == true) {
            		SessionPatientDAO.updateSessionPatient(conn, vo.getPatientId(), vo.getPregnancyId(), session);
            		// re-initialise the globals
            		sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
            		flowId = sessionPatient.getCurrentFlowId();
            		pregnancyId = sessionPatient.getCurrentPregnancyId();
        		}
        	}

        	// Reset form
        	form.reset(mapping, request);
        	StrutsUtils.removeFormBean(mapping, request);
        } catch (ServletException e) {
        	log.error(e);
        } finally {
        	if (conn != null && !conn.isClosed()) {
        		conn.close();
        	}
        }

        /**
         * Forwards section - send user to the next form
         */

        return createForward(request, mapping, patientId, pregnancyId, dynaForm, session, formId);
    }

    /**
     * Saves Lab request and creates problem
     * @param dynaForm
     * @param patientId
     * @param sessionPatient
     * @param conn
     * @param username
     * @param siteId
     * @param labTypeId
     * @throws Exception
     */
    public static void saveLabTestRequest(DynaValidatorForm dynaForm, Long patientId, SessionPatient sessionPatient, Connection conn, String username, Long siteId, int labTypeId) throws Exception {
        Form labTestFormdef = (Form) DynaSiteObjects.getForms().get(Long.valueOf(87));
        LabTest labtest = new LabTest();
        labtest.setField1844(DateUtils.getNow());
        labtest.setField1845(labTypeId);
        labtest.setFormId(labTestFormdef.getId());
        Date visitDateD = null;
        visitDateD = DateUtils.getvisitDate(dynaForm);
        labtest.setDateVisit(visitDateD);
        labtest.setPatientId(patientId);
        labtest.setFlowId(labTestFormdef.getFlowId());
        labtest.setPregnancyId(sessionPatient.getCurrentPregnancyId());
        Long currentFlowId = sessionPatient.getCurrentFlowId();
        FormDAO.create(conn, labtest, username, siteId, labTestFormdef, currentFlowId, null);
        EncounterProcessor.processRules(conn, labTestFormdef, labtest, username);
    }

    /**
     * Saves RPR request and creates problem
     * @param dynaForm
     * @param patientId
     * @param sessionPatient
     * @param conn
     * @param username
     * @param siteId
     * @param labTypeId
     * @throws Exception
     */
    public static void saveRprTestRequest(DynaValidatorForm dynaForm, Long patientId, SessionPatient sessionPatient, Connection conn, String username, Long siteId, int labTypeId) throws Exception {
        Form rprTestFormdef = (Form) DynaSiteObjects.getForms().get(Long.valueOf(90));
        Rpr labtest = new Rpr();
        labtest.setField2006(DateUtils.getNow());
        labtest.setFormId(rprTestFormdef.getId());
        Date visitDateD = null;
        visitDateD = DateUtils.getvisitDate(dynaForm);
        labtest.setDateVisit(visitDateD);
        labtest.setPatientId(patientId);
        labtest.setFlowId(rprTestFormdef.getFlowId());
        labtest.setPregnancyId(sessionPatient.getCurrentPregnancyId());
        Long currentFlowId = sessionPatient.getCurrentFlowId();
        FormDAO.create(conn, labtest, username, siteId, rprTestFormdef, currentFlowId, null);
        EncounterProcessor.processRules(conn, rprTestFormdef, labtest, username);
    }

    /**
     * Check formId to see what is the next form to be served.
     *
     * @param request
     * @param mapping
     * @param patientId
     * @param pregnancyId
     * @param dynaForm
     * @param session
     * @param formId
     * @return the next page/form
     */
    private ActionForward createForward(HttpServletRequest request, ActionMapping mapping, Long patientId, Long pregnancyId, DynaValidatorForm dynaForm, HttpSession session, int formId) {
        SessionPatient sessionPatient = null;
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            try {
                sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
            } catch (SessionUtil.AttributeNotFoundException e) {
//                log.error("Unable to get SessionPatient");
            }

            Form nextForm = new Form();

            // new patient registration takes you to pregnancy dating form, unless user is data clerk
            if (formId == 1) {
                // if user is data clerk, send to home page.
                if (request.isUserInRole("CREATE_NEW_PATIENTS_AND_SEARCH")) {
                    return mapping.findForward("home");
                } else {
                    resetToken(request);
                    return mapping.findForward("pregnancyDating");
                }
            }

            if (formId == 23) {
                // newborn evaluation
               // if (request.getParameter("forward").equals("completed")) {

                    Long motherId = sessionPatient.getParentId();
                    //NewbornEval encounter = (NewbornEval) EncountersDAO.getOne(conn, encounterId, "SQL_RETRIEVEID" + formId, NewbornEval.class);
                    NewbornEval encounter = null;
					try {
						encounter = (NewbornEval) EncountersDAO.getOne(conn, patientId, pregnancyId, "SQL_RETRIEVE23", NewbornEval.class);
					} catch (ObjectNotFoundException e) {
						log.error(e);
					} catch (IOException e) {
						log.error(e);
					} catch (SQLException e) {
						log.error(e);
					}
                    encounter.setSessionPatient(sessionPatient);
                    Form formDef = (Form) DynaSiteObjects.getForms().get(new Long("23"));
                    // the value for Alive/SB:
                    Integer value = encounter.getField493();
                    //Integer arvTreatment = encounter.getField1884();
                    if (value != null) {
                        if ((value.intValue() != 0) & (value.intValue() != 289)) {
                            // send to still birth record
                            resetToken(request);
                            // Get this Form
                            nextForm = (Form) DynaSiteObjects.getForms().get(new Long("40"));
                            String formName = nextForm.getName();
                            request.setAttribute("name", formName);
                            request.setAttribute("id", nextForm.getId());
                            ActionForward forwardForm = null;
                            forwardForm = new ActionForward("/form40/new.do?patientId=" + patientId + "&next=newborn");
                            forwardForm.setRedirect(true);
                            return forwardForm;
                        } else {
                            return getNewbornFlowForm(conn, sessionPatient, request, motherId);
                        }
                    } else {
                        // Get the mother and see number of children
                        Patient mother = sessionPatient.getMother();
                        List children = PatientDAO.getChildren(conn, mother.getId(), pregnancyId);
                        if (children.size() > 0) {
                            ActionForward forwardForm = PatientRecordUtils.getNextNewbornEval(conn, sessionPatient, request);
                            // forwardForm.setRedirect(true);
                            return forwardForm;
                        }
                        request.setAttribute("patientId", motherId);
                        return mapping.findForward("patientPuer");
                    }
                //}
            }

            // pregnancy dating form takes you to previous pregnancies form
            if (formId == 82) {
                // part of reload prevention scheme:
                resetToken(request);
                // if  prev. pregnancies form has not been completed in any pregnancy, send user to it
                Boolean status = null;
                status = EncountersDAO.checkAllEncounters(conn, patientId, new Long("2"));
                Boolean noPregs = sessionPatient.getNoPreviousPregnancies();
                if ((status.equals(Boolean.TRUE)) || (noPregs.equals(Boolean.TRUE))) {
                    // otherwise send to patientAnte
                    return mapping.findForward("patientAnte");
                } else {
                    // send user to prev. pregnancies form
                    return mapping.findForward("previousPregnancies");
                }
            }

            // Medical/Surgical History takes you to patientAnte. If select current medicine � go to Current Medicine form.
            if (formId == 70) {
                // part of reload prevention scheme:
                resetToken(request);

                if (!dynaForm.getMap().get("field95").equals("")) {
                    // Currently taking medicine
                    String fieldCheck = dynaForm.getMap().get("field95").toString();
                    // part of reload prevention scheme:
                    resetToken(request);
                    if (fieldCheck.equals("1")) {
                        return mapping.findForward("currentMedicine");
                    } else {
                        return mapping.findForward("patientAnte");
                    }
                } else {
                    return mapping.findForward("patientAnte");
                }
            }

            // Current Medicine form takes you back to patient ante form.
            // First check for ega
            if (formId == 55) {
                // part of reload prevention scheme
                resetToken(request);
                Integer currentEga = sessionPatient.getCurrentEgaDaysDB();
                try {
                    if ((currentEga) == 0) {
                        return mapping.findForward("pregnancyDating");
                    } else {
                        return mapping.findForward("patientAnte");
                    }
                } catch (Exception e) {
                    // log.error("either sessionPatient or currentEga is not initialised."); // if currentEga not avail, then preg. dating form probably not submitted.
                    return mapping.findForward("pregnancyDating");
                }
            }

            // routine antenatal form processing
            if (formId == 80) {
                // part of reload prevention scheme:
                resetToken(request);
                // see if problem field is checked - send to problem form is true
                if (!dynaForm.getMap().get("field1616").equals("")) {
                    request.removeAttribute(SUBJECT_KEY);
                    nextForm = (Form) DynaSiteObjects.getForms().get(new Long("65"));
                    String formName = nextForm.getName();
                    request.setAttribute("name", formName);
                    // request.setAttribute("id", nextForm.getId());
                    ActionForward forwardForm = null;
                    forwardForm = new ActionForward("/form65/new.do");
                    forwardForm.setRedirect(true);
                    return forwardForm;
                } else {
                    // check if initial visit physical exam has been submitted.
                    Boolean status = null;
                    try {
                        status = EncountersDAO.checkEncounter(conn, patientId, pregnancyId, new Long("77"));
                    } catch (NumberFormatException e) {
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    }

                    if (status == Boolean.TRUE) {
                        // if  initial visit physical exam has been submitted, check if Safe Motherohod exam has been submitted.
                        return mapping.findForward("safeMotherhood");
                    } else {
                        nextForm = (Form) DynaSiteObjects.getForms().get(new Long("77"));
                        String formName = nextForm.getName();
                        request.setAttribute("name", formName);
                        request.setAttribute("id", nextForm.getId());
                        return mapping.findForward("nextForm");
                    }
                }
            }

            // inital visit form takes you back to safeMotherhood form
            if (formId == 77) {
                // part of reload prevention scheme:
                resetToken(request);
                return mapping.findForward("safeMotherhood");
            }

            // safe motherhood form used to take you back to patientAnte
            // safe motherhood-related forms - labs, drugs, arv's etc -  take you back to safeMotherhood
          //  if ((formId == 92) || (formId == 87) || (formId == 90) || (formId == 88) || (formId == 91) || (formId == 89))
            if ((formId >= 87 && formId <= 92) ||  (formId >= 101 && formId <= 104))
            {
                if (request.getParameter("next") != null) {
                    String next = request.getParameter("next");
                    Long parentId = sessionPatient.getParentId();
                    List children = sessionPatient.getChildren();
                    if (parentId != null) {
                        children = PatientDAO.getChildren(conn, parentId, pregnancyId);
                    }
                    if (next.equals("newborn")) {
                        if (children.size() > 0) {
                            ActionForward forwardForm = PatientRecordUtils.getNextNewbornEval(conn, sessionPatient, request);
                            // forwardForm.setRedirect(true);
                            return forwardForm;
                        }
                    } else if (next.equals("infantDischarge")) {
                        if (children.size() > 0) {
                            ActionForward forwardForm = getInfantDischarge(conn, sessionPatient, request);
                            // forwardForm.setRedirect(true);
                            if (forwardForm != null) {
                                return forwardForm;
                            }
                        }
                    } else if (next.equals("deliveryTask")) {
                        request.setAttribute("flowId", "4");
                        return mapping.findForward("patientTask");
                    } else if (next.equals("nicu")) {
                        request.setAttribute("flowId", "5");
                        return mapping.findForward("patientTask");
                    } else if (next.equals("problemPostnatal")) {
                        ActionForward forwardForm = null;
                        if (parentId != null) {
                            nextForm = (Form) DynaSiteObjects.getForms().get(new Long("32"));    // Infant Problem/Postnatal Visit
                            String formName = nextForm.getName();
                            request.setAttribute("name", formName);
                            request.setAttribute("id", nextForm.getId());
                            forwardForm = new ActionForward("/form32/new.do");
                        } else {
                            nextForm = (Form) DynaSiteObjects.getForms().get(new Long("78"));   // Maternal Problem/Postnatal Visit
                            String formName = nextForm.getName();
                            request.setAttribute("name", formName);
                            request.setAttribute("id", nextForm.getId());
                            forwardForm = new ActionForward("/form78/new.do");
                        }
                        forwardForm.setRedirect(true);
                        return forwardForm;
                    }
                }
                // part of reload prevention scheme:
                resetToken(request);
                if (sessionPatient.getParentId() != null) {
                    StrutsUtils.removeFormBean(mapping, request);
                    request.setAttribute("flowId", "4");
                    ActionForward forward = null;
                    forward = mapping.findForward("patientTask");
                    return forward;
                } else {
                    if (formId == 87) {
                        return mapping.findForward("labs");
                    } else if (formId == 88) {
                        return mapping.findForward("drugs");
                    } else if (formId == 89) {
                        return mapping.findForward("arv");
                    } else if (formId == 91) {
                        String fieldCheck = dynaForm.getMap().get("field1866").toString();
                        if (fieldCheck != null && fieldCheck.equals("2940")) {
                            return mapping.findForward("arv");
                        } else {
                            return mapping.findForward("safeMotherhood");
                        }
                    } else if (formId >= 101 && formId <= 104) {
                        return mapping.findForward("labs");
                    } else {
                        return mapping.findForward("safeMotherhood");
                    }
                }
            }

            // Previous pregnancies form takes you back to itself or surgical history
            if (formId == 2) {
                // part of reload prevention scheme:
                resetToken(request);
                if ((request.getParameter("forward") != null) && (!request.getParameter("forward").equals(""))) {
                    if (request.getParameter("forward").equals("add")) {
                        dynaForm.getMap().clear();
                        // request.getParameterMap().clear();
                        return mapping.findForward("previousPregnancies");
                    }
                } else {
                    // check if Medical/Surgical History form has already been submitted
                    Boolean medSurgHist = EncountersDAO.checkAllEncounters(conn, patientId, new Long("70"));
                    resetToken(request);
                    if (medSurgHist) {
                        request.setAttribute("flowId", "5");
                        return mapping.findForward("patientTask");
                    } else {
                        nextForm = (Form) DynaSiteObjects.getForms().get(new Long("70"));
                        String formName = nextForm.getName();
                        request.setAttribute("name", formName);
                        request.setAttribute("id", nextForm.getId());
                        ActionForward forwardForm = null;
                        forwardForm = new ActionForward("/form70/new.do");
                        forwardForm.setRedirect(true);
                        return forwardForm;
                    }
                }
            }

            // Antenatal Ultrasound Evaluation takes you back to patient ante form.
            // First check for ega
            if (formId == 44) {
                // part of reload prevention scheme
                resetToken(request);
                return mapping.findForward("patientAnte");
            }

            // Postnatal Ultrasound Evaluation takes you back to postnatal delivery summary task list
            // First check for ega
            if (formId == 75) {
                // part of reload prevention scheme
                resetToken(request);
                request.setAttribute("flowId", "4");
                return mapping.findForward("patientTask");
            }

            // Problem/Labour Visit form
            if (formId == 65) {
                // it it's a miscarriage, send to preg. conclusion
                String miscarriage = String.valueOf(dynaForm.getMap().get("field1758"));
                if (!miscarriage.equals("")) {
                    nextForm = (Form) DynaSiteObjects.getForms().get(new Long("71"));
                    String formName = nextForm.getName();
                    request.setAttribute("name", formName);
                    request.setAttribute("id", nextForm.getId());
                    return mapping.findForward("nextForm");
                }
                // if Maternal death, send to Maternal discharge suammry
                String maternalDeath = String.valueOf(dynaForm.getMap().get("field2139"));
                if (!maternalDeath.equals("")) {
                    nextForm = (Form) DynaSiteObjects.getForms().get(new Long("68"));
                    String formName = nextForm.getName();
                    request.setAttribute("name", formName);
                    request.setAttribute("id", nextForm.getId());
                    return mapping.findForward("nextForm");
                }
                // if user enters Admission for labour on Problem/labour form's Disposition field
                String disposition = String.valueOf(dynaForm.getMap().get("field1266"));
                // part of reload prevention scheme:
                resetToken(request);
                // Cervix Dilatation input field
                String fieldValue = (String) dynaForm.getMap().get("field325");
                Long cervixDilatation = null;
                try {
                    cervixDilatation = new Long(fieldValue);
                } catch (NumberFormatException e) {
                    cervixDilatation = new Long("0");
                }
                if (disposition.equals("2700")) {
                    // referral
                    //request.setAttribute("flowId", "7");
                    //return mapping.findForward("patientTask");
                    return mapping.findForward("safeMotherhood");
                } else if (disposition.equals("2910")) {
                    // Admit to uth
                    //request.setAttribute("flowId", "7");
                    //return mapping.findForward("patientTask");
                    return mapping.findForward("safeMotherhood");
                } else if (cervixDilatation.intValue() >= 4) {
                    session.setAttribute("forward", "partograph");
                    return mapping.findForward("partograph");
                } else if (disposition.equals("2697")) {   // disposition field - admit for active labour field
                    if (cervixDilatation.intValue() >= 4) {
                        session.setAttribute("forward", "partograph");
                        return mapping.findForward("partograph");
                    } else if (cervixDilatation.intValue() < 4) {
                        return mapping.findForward("labourObservations");
                    } else {
                        //request.setAttribute("flowId", "7");
                        //return mapping.findForward("patientTask");
                        return mapping.findForward("safeMotherhood");
                    }
                } else if (disposition.equals("2838")) {
                    // labourObservations
                    if (cervixDilatation.intValue() >= 4) {
                        session.setAttribute("forward", "partograph");
                        return mapping.findForward("partograph");
                    } else if (cervixDilatation.intValue() < 4) {
                        return mapping.findForward("labourObservations");
                    } else {
                        return mapping.findForward("labourObservations");
                    }
                    // discharge home was chosen
                } else if (disposition.equals("2699")) {

                    // check if initial visit physical exam has been submitted.
                    Boolean status = null;
                    try {
                        status = EncountersDAO.checkEncounter(conn, patientId, pregnancyId, new Long("77"));
                    } catch (NumberFormatException e) {
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    }
                    if (status == Boolean.TRUE) {
                        // if  initial visit physical exam has been submitted, send user to routine antenatal chart
                        return mapping.findForward("patientAnte");
                    } else {
                        // send to initial visit physical exam
                        nextForm = (Form) DynaSiteObjects.getForms().get(new Long("77"));
                        String formName = nextForm.getName();
                        request.setAttribute("name", formName);
                        request.setAttribute("id", nextForm.getId());
                        return mapping.findForward("nextForm");
                    }
                } else {
                    // check if initial visit physical exam has been submitted.
                    Boolean status = null;
                    try {
                        status = EncountersDAO.checkEncounter(conn, patientId, pregnancyId, new Long("77"));
                    } catch (NumberFormatException e) {
                        request.setAttribute("exception", e);
                        return mapping.findForward("error");
                    }
                    if (status == Boolean.TRUE) {
                        // if  initial visit physical exam has been submitted, send user to Problem or Labour task list
                        // request.setAttribute("flowId", "7");
                        // return mapping.findForward("patientTask");
                        return mapping.findForward("safeMotherhood");
                    } else {
                        // sned to initial visit physical exam
                        nextForm = (Form) DynaSiteObjects.getForms().get(new Long("77"));
                        String formName = nextForm.getName();
                        request.setAttribute("name", formName);
                        request.setAttribute("id", nextForm.getId());
                        return mapping.findForward("nextForm");
                    }
                }
            }

            // Observations for Latent Phase of 1st Stage of Labour form takes you back to labour tasklist
            if (formId == 17) {
                // part of reload prevention scheme:
                resetToken(request);

                // Cervix Dilatation input field
                String fieldValue = (String) dynaForm.getMap().get("field325");
                Long cervixDilatation = null;
                try {
                    cervixDilatation = new Long(fieldValue);
                } catch (NumberFormatException e) {
                    cervixDilatation = new Long("0");
                }

                // if user enters Admission for labour
                String disposition = String.valueOf(dynaForm.getMap().get("field1761"));
                if (disposition.equals("2845")) {
                    // Refer to UTH
                    request.setAttribute("flowId", "7");
                    return mapping.findForward("patientTask");
                } else if (cervixDilatation.intValue() >= 4) {   // cervixDilatation value short-ciruits things...
                    session.setAttribute("forward", "partograph");
                    return mapping.findForward("partograph");
                } else if (disposition.equals("2843")) {
                    // discharge home
                    return mapping.findForward("patientAnte");
                } else if (disposition.equals("2844")) {
                    // Admit for active labour (Begin Partograph)
                    if (cervixDilatation.intValue() >= 4) {   // cervixDilatation value short-ciruits things...
                        session.setAttribute("forward", "partograph");
                        return mapping.findForward("partograph");
                    } else {
                        return mapping.findForward("labourObservations");
                    }
                } else if (disposition.equals("2919")) {  // continue observations
                    return mapping.findForward("labourObservations");
                } else {
                    return mapping.findForward("labourObservations");
                }
            }

            // Partograph Delivery form takes user to Delivery Summary Form form 66
            if (formId == 79) {
                // part of reload prevention scheme:
                nextForm = (Form) DynaSiteObjects.getForms().get(new Long("66"));
                String formName = nextForm.getName();
                request.setAttribute("name", formName);
                request.setAttribute("id", nextForm.getId());
                ActionForward forwardForm = null;
                forwardForm = new ActionForward("/form66/new.do");
                forwardForm.setRedirect(true);
                return forwardForm;
            }

            // puerperium form takes you back to delivery/summary task list or itself
            if (formId == 81) {
                // part of reload prevention scheme:
                resetToken(request);

                // see if problem field is checked
                if (!dynaForm.getMap().get("field1616").toString().equals("")) {    // send user to probPostnatal form
                    nextForm = (Form) DynaSiteObjects.getForms().get(new Long("78"));
                    String formName = nextForm.getName();
                    request.setAttribute("name", formName);
                    request.setAttribute("id", nextForm.getId());
                    ActionForward forwardForm = null;
                    String forwardString = "/form78/new.do";
                    forwardForm = new ActionForward(forwardString);
                    forwardForm.setRedirect(true);
                    return forwardForm;
                }
                // get value for disposition
                String disposition = String.valueOf(dynaForm.getMap().get("field1655"));
                // see if discharge to home is selected  - send user to maternal discharge
                if (disposition.equals("2907")) {
                    Boolean status = null;
                    status = EncountersDAO.checkEncounter(conn, patientId, pregnancyId, new Long("68"));
                    if (status == false) {
                        nextForm = (Form) DynaSiteObjects.getForms().get(new Long("68"));
                        String formName = nextForm.getName();
                        request.setAttribute("name", formName);
                        request.setAttribute("id", nextForm.getId());
                        ActionForward forwardForm = null;
                        String forwardString = "/form68/new.do";
                        forwardForm = new ActionForward(forwardString);
                        forwardForm.setRedirect(true);
                        if (!conn.isClosed()) {
                            conn.close();
                            conn = null;
                        }
                        return forwardForm;
                    } else {
                        // send to task list
                        request.setAttribute("flowId", "4");
                        if (!conn.isClosed()) {
                            conn.close();
                            conn = null;
                        }
                        return mapping.findForward("patientTask");
                    }

                } else if (disposition.equals("2908")) {
                    // see if refer to uth is selected  - send user to maternal discharge
                    Boolean status = null;
                    status = EncountersDAO.checkEncounter(conn, patientId, pregnancyId, new Long("68"));
                    if (status == false) {
                        nextForm = (Form) DynaSiteObjects.getForms().get(new Long("68"));
                        String formName = nextForm.getName();
                        request.setAttribute("name", formName);
                        request.setAttribute("id", nextForm.getId());
                        ActionForward forwardForm = null;
                        String forwardString = "/form68/new.do";
                        forwardForm = new ActionForward(forwardString);
                        forwardForm.setRedirect(true);
                        if (!conn.isClosed()) {
                            conn.close();
                            conn = null;
                        }
                        return forwardForm;
                    } else {
                        // send to task list
                        request.setAttribute("flowId", "4");
                        if (!conn.isClosed()) {
                            conn.close();
                            conn = null;
                        }
                        return mapping.findForward("patientTask");
                    }
                } else if (disposition.equals("2909")) {
                    // see if continue observations is selected  - send user to patientPuer
                    // return to puerperium form
                    StrutsUtils.removeFormBean(mapping, request);
                    return mapping.findForward("patientPuer");
                } else {
                    // send to tasl list
                    request.setAttribute("flowId", "4");
                    return mapping.findForward("patientTask");
                }
            }

            // Postnatal Maternal Visit form takes you back to problem
            if (formId == 28) {
                // part of reload prevention scheme:
                resetToken(request);
                // see if problem field is checked - send to problem/postnatal
                if (!dynaForm.getMap().get("field1616").toString().equals("")) {
                    nextForm = (Form) DynaSiteObjects.getForms().get(new Long("78"));
                    String formName = nextForm.getName();
                    request.setAttribute("name", formName);
                    request.setAttribute("id", nextForm.getId());
                    ActionForward forwardForm = null;
                    forwardForm = new ActionForward("/form78/new.do");
                    forwardForm.setRedirect(true);
                    return forwardForm;
                } else {
                	// return to delivery summary task list
                    StrutsUtils.removeFormBean(mapping, request);
                    request.setAttribute("flowId", "4");
                    ActionForward forward = null;
                    forward = mapping.findForward("patientTask");
                    return forward;
                }
            }

            // Infant Postnatal Visit form takes you back to patientTask
            if (formId == 86) {
                // part of reload prevention scheme:
                resetToken(request);
                // see if problem field is checked - send to infant problem/postnatal
                if (!dynaForm.getMap().get("field1616").toString().equals("")) {
                    nextForm = (Form) DynaSiteObjects.getForms().get(new Long("32"));
                    String formName = nextForm.getName();
                    request.setAttribute("name", formName);
                    request.setAttribute("id", nextForm.getId());
                    ActionForward forwardForm = null;
                    forwardForm = new ActionForward("/form32/new.do");
                    forwardForm.setRedirect(true);
                    return forwardForm;
                } else {
                    // return to delivery summary task list
                    StrutsUtils.removeFormBean(mapping, request);
                    request.setAttribute("flowId", "4");
                    ActionForward forward = null;
                    forward = mapping.findForward("patientTask");
                    return forward;
                }
            }

            // Infant Problem/Postnatal Visit form takes you back to patientTask
            if (formId == 32) {
                // part of reload prevention scheme:
                resetToken(request);
                // return to delivery summary task list
                StrutsUtils.removeFormBean(mapping, request);
                request.setAttribute("flowId", "4");
                ActionForward forward = null;
                forward = mapping.findForward("patientTask");
                return forward;
            }

            // Maternal Discharge Summary form takes you to infant discharge, if possible;
            // otherwise to delivery summary/postnatal task list
            if (formId == 68) {
                // part of reload prevention scheme:
                resetToken(request);
                // see contents of outcome field
                if (!dynaForm.getMap().get("field1654").toString().equals("")) {
                    String fieldCheck = dynaForm.getMap().get("field1654").toString();
                    if (fieldCheck.equals("2817")) {    // refer to UTH
                        return mapping.findForward("problem");
                    }
                }
                if (sessionPatient.getChildren().size() > 0) {
                    ActionForward forwardForm = getInfantDischarge(conn, sessionPatient, request);
                    // forwardForm.setRedirect(true);
                    if (forwardForm != null) {
                        return forwardForm;
                    }
                }
                request.setAttribute("flowId", "4");
                return mapping.findForward("patientTask");
            }

            // Infant discharge summary take you to mother's delivery summary task list, after processing all of the infants
            if (formId == 84) {
                // Get the mother and see number of children
                Patient mother = sessionPatient.getMother();
                List children = PatientDAO.getChildren(conn, mother.getId(), sessionPatient.getCurrentPregnancyId());
                if (children.size() > 1) {
                    ActionForward forwardForm = getInfantDischarge(conn, sessionPatient, request);
                    // forwardForm.setRedirect(true);
                    if (forwardForm != null) {
                        return forwardForm;
                    }
                }
                ActionForward forwardForm = null;
                forwardForm = new ActionForward("/patientTask.do?patientId=" + mother.getId() + "&flowId=4");
                forwardForm.setRedirect(true);
                return forwardForm;
            }

            // referral form takes you back to home
            if (formId == 83) {
                // part of reload prevention scheme:
                resetToken(request);
                return mapping.findForward("home");
            }

            // Antenatal Hospitalization Summary form takes you to tasklist
            if (formId == 63) {
                // part of reload prevention scheme:
                resetToken(request);
                // it it's a miscarriage, send to preg. conclusion
                String miscarriage = String.valueOf(dynaForm.getMap().get("field932"));
                if (!miscarriage.equals("")) {
                    nextForm = (Form) DynaSiteObjects.getForms().get(new Long("71"));
                    String formName = nextForm.getName();
                    request.setAttribute("name", formName);
                    request.setAttribute("id", nextForm.getId());
                    return mapping.findForward("nextForm");
                }
                // Missed Abortion field checked?
                if (!dynaForm.getMap().get("field1769").equals("")) {
                    nextForm = (Form) DynaSiteObjects.getForms().get(new Long("71"));
                    String formName = nextForm.getName();
                    request.setAttribute("name", formName);
                    request.setAttribute("id", nextForm.getId());
                    return mapping.findForward("nextForm");
                }
                // MVA field checked?
                if (!dynaForm.getMap().get("field1285").equals("")) {
                    nextForm = (Form) DynaSiteObjects.getForms().get(new Long("71"));
                    String formName = nextForm.getName();
                    request.setAttribute("name", formName);
                    request.setAttribute("id", nextForm.getId());
                    return mapping.findForward("nextForm");
                }

                // Dilatation and Curettage field checked?
                if (!dynaForm.getMap().get("field1286").equals("")) {
                    nextForm = (Form) DynaSiteObjects.getForms().get(new Long("71"));
                    String formName = nextForm.getName();
                    request.setAttribute("name", formName);
                    request.setAttribute("id", nextForm.getId());
                    return mapping.findForward("nextForm");
                }
                return mapping.findForward("discharge");
            }

            // Postnatal Hospitalization Summary form takes you to tasklist
            if (formId == 74) {
                // part of reload prevention scheme:
                resetToken(request);
                // request.setAttribute("flowId", "6");
                return mapping.findForward("discharge");
            }

            // UTH Neonatal Case Record form takes you to Infant Physical Examination
            if (formId == 33) {
                // part of reload prevention scheme:
                resetToken(request);
                nextForm = (Form) DynaSiteObjects.getForms().get(new Long("36"));
                String formName = nextForm.getName();
                request.setAttribute("name", formName);
                request.setAttribute("id", nextForm.getId());
                ActionForward forwardForm = null;
                forwardForm = new ActionForward("/form36/new.do");
                forwardForm.setRedirect(true);
                return forwardForm;

            }

            // Infant Physical Examination form takes you to NICU Hospitalization Summary Form
            if (formId == 36) {
                // part of reload prevention scheme:
                resetToken(request);
                nextForm = (Form) DynaSiteObjects.getForms().get(new Long("76"));
                String formName = nextForm.getName();
                request.setAttribute("name", formName);
                request.setAttribute("id", nextForm.getId());
                ActionForward forwardForm = null;
                forwardForm = new ActionForward("/form76/new.do");
                forwardForm.setRedirect(true);
                return forwardForm;
            }

            // Summary of Admission of Infant at NICU form takes you to nicu tasklist
            if (formId == 37) {
                // part of reload prevention scheme:
                resetToken(request);
                request.setAttribute("flowId", "5");
                return mapping.findForward("patientTask");
            }

            // NICU Hospitalization Summary Form form takes you to nicu tasklist
            if (formId == 76) {
            	// part of reload prevention scheme:
            	resetToken(request);
            	request.setAttribute("flowId", "5");
            	return mapping.findForward("patientTask");
            }

            if (formId == 78) {
                // Maternal postnatal problem visit takes you back to Postnatal flow page
                request.setAttribute("flowId", "4");
                // part of reload prevention scheme:
                resetToken(request);
                return mapping.findForward("patientTask");
                // routine antenatal form takes you back to chart
            }

            // still birth delivery record goes to Maternal Discharge Summary
            // need to switch to the mother's session
            if (formId == 40) {
                // send to patientpuer
                resetToken(request);
                // Get this Form
                nextForm = (Form) DynaSiteObjects.getForms().get(new Long("68"));
                String formName = nextForm.getName();
                request.setAttribute("name", formName);
                request.setAttribute("id", nextForm.getId());
                ActionForward forwardForm = null;
                Long motherId = sessionPatient.getParentId();
                String forwardString = null;
                String next = request.getParameter("next");
                Long parentId = sessionPatient.getParentId();
                List children = sessionPatient.getChildren();
                if (parentId != null) {
                    children = PatientDAO.getChildren(conn, parentId, pregnancyId);
                }
                if (next != null && next.equals("newborn")) {
                    if (children.size() > 0) {
                        forwardForm = PatientRecordUtils.getNextNewbornEval(conn, sessionPatient, request);
                        // forwardForm.setRedirect(true);
                        return forwardForm;
                    }
                }
                if (motherId != null) {
                    StrutsUtils.removeFormBean(mapping, request);
                    forwardString = "/patientPuer.do?patientId=" + motherId;
                } else {
                    forwardString = "/form68/new.do";  // maternal discharge
                }
                forwardForm = new ActionForward(forwardString);
                forwardForm.setRedirect(true);
                return forwardForm;
            }

            // pregnancy conclusion form takes you to previous pregnancies form (with patientHome in between)
            if (formId == 71) {
                // part of reload prevention scheme:
                resetToken(request);
                // re-initialise the globals
                try {
                    sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
                } catch (SessionUtil.AttributeNotFoundException e) {
                    log.error(e);
                }
                // send user to home.
                return mapping.findForward("home");
            }

            if (formId == 66) {
                // Delivery summary takes you back to Newborn summary
                if (sessionPatient.getChildren().size() > 0) {
                    // find the infant forms
                    List children = sessionPatient.getChildren();
                    EncounterData encounter = null;
                    // get the first child - this list is sorted by patient.id in the sql query.
                    Patient child = (Patient) children.get(0);
                    Long childId = child.getId();
                    ActionForward forwardForm = PatientRecordUtils.getNextNewbornEval(conn, sessionPatient, request);
                    // forwardForm.setRedirect(true);
                    return forwardForm;
                }
                request.setAttribute("flowId", "4");
                // part of reload prevention scheme:
                resetToken(request);
                return mapping.findForward("patientTask");
                // routine antenatal form takes you back to chart
            }
            
            if (formId == 125) {
            	ActionForward forwardForm = null;
            	String forwardString = null;
//				forwardString = "/admin/records/list.do?formId=" + formId;
				forwardString = "/admin/users.do";
				forwardForm = new ActionForward(forwardString);
                forwardForm.setRedirect(true);
                return forwardForm;
            }

            if (nextForm.getId() == null) {
                // part of reload prevention scheme:
                resetToken(request);
                return mapping.findForward("problem");
            }
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
        	log.error(e);
		} finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return (new ActionForward(mapping.getInput()));
    }

    /**
     * Skip infant has a stillbirth delivery record
     *
     * @param conn
     * @param sessionPatient
     * @param request
     * @return forward
     */
    private ActionForward getInfantDischarge(Connection conn, SessionPatient sessionPatient, HttpServletRequest request) {

        Form nextForm;
        // List the children
        List children = null;
        if (sessionPatient.getParentId() != null) {
            // Get the mother and see number of children
            Patient mother = sessionPatient.getMother();
            Long pregnancyId = sessionPatient.getCurrentPregnancyId();
            children = PatientDAO.getChildren(conn, mother.getId(), pregnancyId);
        } else {
            children = sessionPatient.getChildren();
        }
        // EncounterData encounter = null;
        Long childId = null;
        ActionForward forwardForm = null;
        // if (sessionPatient.getNewbornsDischarged() != null) {
        // iterate through list of children and find one who has not done newborn eval
        for (int c = 0; c < children.size(); c++) {
            Patient newborn = (Patient) children.get(c);
            // List newbornsDischarged = sessionPatient.getNewbornsDischarged();
            // boolean isdischarged = newbornsDischarged.contains(newborn.getId());
            Long patientId = newborn.getId();
            Long pregnancyId = sessionPatient.getCurrentPregnancyId();
            Boolean isStillbourn;
            Boolean isSummaried;
            isStillbourn = EncountersDAO.checkEncounter(conn, patientId, pregnancyId, new Long("40"));
            isSummaried = EncountersDAO.checkEncounter(conn, patientId, pregnancyId, new Long("84"));
            if (!isStillbourn && !isSummaried) {
                childId = newborn.getId();
                break;
            }
        }
        /* } else {
            // get the first child - this list is sorted by patient.id in the sql query.
            Patient child = (Patient) children.get(0);
            childId = child.getId();
        }*/
        if (childId == null) {
            return forwardForm;
        } else {
            nextForm = (Form) DynaSiteObjects.getForms().get(new Long("84"));
            String formName = nextForm.getName();
            request.setAttribute("name", formName);
            request.setAttribute("id", nextForm.getId());
            // need to set the sessionPatient to the child
            request.setAttribute("patientId", childId);
            String forwardString = "/form84/new.do?patientId=" + childId;
            forwardForm = new ActionForward(forwardString);
            forwardForm.setRedirect(true);
            return forwardForm;
        }
    }

    protected static ActionForward getNewbornFlowForm(Connection conn, SessionPatient sessionPatient, HttpServletRequest request, Long motherId) {
	    // Get the mother and see number of children
	    Patient mother = sessionPatient.getMother();
	    Long pregnancyId = sessionPatient.getCurrentPregnancyId();
	    List children = PatientDAO.getChildren(conn, mother.getId(), pregnancyId);
	    if (children.size() > 0) {
	        ActionForward forwardForm = PatientRecordUtils.getNextNewbornEval(conn, sessionPatient, request);
	        // forwardForm.setRedirect(true);
	        if (forwardForm != null) {
	            return forwardForm;
	        }
	    }
	    request.setAttribute("patientId", motherId);
	    // Get this Form
	    ActionForward forwardForm = PatientRecordUtils.getPuerperiumForward(request, motherId);
	    return forwardForm;
	}

	/**
     * Sets up the vo (value object) for processing.
     * Sets dateVisit from value in field1 (or other date fields depending on the form) which is in the dynaform
     * @param formDef
     * @param formId
     * @param patientId
     * @param dynaForm
     * @param sessionPatient
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static BaseEncounter setupFormObject(Form formDef, String formId, Long patientId, DynaValidatorForm dynaForm, SessionPatient sessionPatient) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String classname = "org.cidrz.project.zeprs.valueobject.gen." + StringManipulation.fixClassname(formDef.getName());
        Class formClass = Class.forName(classname);
        BaseEncounter formObj = (BaseEncounter) formClass.newInstance();

        if (!formId.equals("1")) {
            formObj.setPatientId(patientId);
        }
        formObj.setFormId(formDef.getId());
        Date visitDateD = null;
        visitDateD = DateUtils.getvisitDate(dynaForm);
        formObj.setDateVisit(visitDateD);
        formObj.setPatientId(patientId);
        // Pregnancy section
		if (formObj.getFormId().intValue() != 1 && formDef.getFormTypeId() != 5 ) {
            // use the mother's current pregnancy value
            if (sessionPatient.getParentId() != null) {
                // Saving a record of an infant - need to get mother's current pregnancy id
                if (formObj.getId() == null) {
                    formObj.setPregnancyId(sessionPatient.getCurrentPregnancyId());
                }
            } else {
                updatePregnancy(formDef, formObj, dynaForm, sessionPatient);
            }
        }
        try {
            Object field1911 = dynaForm.get("field1911");
            if (field1911 != null) {
                Long referralId = new Long((String) dynaForm.get("field1911"));
                formObj.setReferralId(referralId);
            }
        } catch (IllegalArgumentException e) {
            // not in this form
        }

        // current flow is set in the session by BasePatientAction
        // but we need the flow of the form
        formObj.setFlowId(formDef.getFlow().getId());
        return formObj;
    }

    /**
     * If this is a pregnancy form, test if this is the current pregnancy. if not, create a new pregnancy.
     * Need to check this for every form submission, because it is not certain which will the the first form used
     * when starting a new pregnancy. This avoids hard-coding form id's into the system.
     * Well, we are hard-coding the formid to terminate the pregnancy
     * Also need to reload the current pregnancy in the session when updating it.
     * We don't process infants with this.
     *
     * @param formDef
     * @param encounter
     * @param dynaForm
     * @param sessionPatient
     */
    private static void updatePregnancy(Form formDef, BaseEncounter encounter, DynaValidatorForm dynaForm, SessionPatient sessionPatient) {

        Pregnancy pregnancy = null;
        if (sessionPatient.getCurrentPregnancyId() != null) {
            // Patient has already had a pregnancy
            if (sessionPatient.getDatePregnancyEnd() != null) {
                // the current pregnancy has ended - create a new pregnancy
                Date visitDateD = null;
                visitDateD = DateUtils.getvisitDate(dynaForm);
                pregnancy = createPregnancy(encounter, visitDateD);
                encounter.setPregnancyId(pregnancy.getId());
                sessionPatient.setCurrentPregnancyId(pregnancy.getId());
            } else {
                // Don't add a new pregnancy if the current one has not ended.
                // is this the Pregnancy Conclusion form?
                if (formDef.getId().equals(Long.valueOf("71"))) {
                    // user must switch the checkbox to indicate pregnancy has ended
                    if (dynaForm.getMap().get("field1367") != null) {
                        // we're not using now, because the pregnancy could have terminated earlier than the date of visit.
                        String pregnancyEnd = dynaForm.getMap().get("field1369").toString();
                        sessionPatient.setDatePregnancyEnd(Date.valueOf(pregnancyEnd));
                    }
                    encounter.setPregnancyId(sessionPatient.getCurrentPregnancyId());
                } else {
                    // Add the current pregnancy to the encounter
                    encounter.setPregnancyId(sessionPatient.getCurrentPregnancyId());
                }
            }
        } else {
            // This is the first pregnancy in the system for this patient.
            Date visitDateD = null;
            visitDateD = DateUtils.getvisitDate(dynaForm);
            pregnancy = createPregnancy(encounter, visitDateD);
            encounter.setPregnancyId(pregnancy.getId());
            sessionPatient.setCurrentPregnancyId(pregnancy.getId());
            sessionPatient.setDatePregnancyBegin(pregnancy.getDatePregnancyBegin());
        }
    }

    /**
     * Create new pregnancy object
     * @param encounter
     * @param visitDateD
     * @return
     */
    private static Pregnancy createPregnancy(BaseEncounter encounter, Date visitDateD) {
        Pregnancy pregnancy = new Pregnancy();
        EncounterData beginEncounter = (EncounterData) encounter;
        pregnancy.setPregnancyBeginEncounterId(beginEncounter.getId());
        pregnancy.setDatePregnancyBegin(visitDateD);
        pregnancy.setPatientId(encounter.getPatientId());
        pregnancy.setAuditInfo(encounter.getAuditInfo());
        // encounter.getPatientId().getPregnancies().add(pregnancy);
        // log.error("TBD: Must persist this new pregnancy");
        return pregnancy;
    }
}