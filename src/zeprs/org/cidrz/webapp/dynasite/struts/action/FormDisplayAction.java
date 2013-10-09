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
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;
import org.cidrz.project.zeprs.report.ZEPRSUtils;
import org.cidrz.project.zeprs.valueobject.BaseEncounter;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.AnteUltrasoundEval;
import org.cidrz.project.zeprs.valueobject.gen.DeliverySum;
import org.cidrz.project.zeprs.valueobject.gen.InitialVisit;
import org.cidrz.project.zeprs.valueobject.gen.LabTest;
import org.cidrz.project.zeprs.valueobject.gen.NewbornEval;
import org.cidrz.project.zeprs.valueobject.gen.Newbornrecord;
import org.cidrz.project.zeprs.valueobject.gen.PartographStatus;
import org.cidrz.project.zeprs.valueobject.gen.PatientRegistration;
import org.cidrz.project.zeprs.valueobject.gen.UltrasoundFetusEval;
import org.cidrz.project.zeprs.valueobject.partograph.Liquor;
import org.cidrz.project.zeprs.valueobject.report.gen.ChemistryReport;
import org.cidrz.project.zeprs.valueobject.report.gen.HemotologyReport;
import org.cidrz.project.zeprs.valueobject.report.gen.LiverfunctionReport;
import org.cidrz.project.zeprs.valueobject.report.gen.UrinalysisReport;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.dao.PatientDAO;
import org.cidrz.webapp.dynasite.dao.PregnancyDatingDAO;
import org.cidrz.webapp.dynasite.dao.SessionPatientDAO;
import org.cidrz.webapp.dynasite.dao.UltrasoundDAO;
import org.cidrz.webapp.dynasite.dao.partograph.LiquorDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.remote.Newborn;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.DateUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.utils.StringManipulation;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;
import org.cidrz.webapp.dynasite.valueobject.Site;

/**
 * Action which locates the requested Form and passes it to the JSP for rendering.
 */

public final class FormDisplayAction extends BasePatientAction {

    /**
     * Commons Logging instance.
     */

    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        /*if (SystemStateManager.getCurrentState() != SystemStateManager.STATUS_NORMAL) {
            return mapping.findForward(LOCKED_FORWARD);
        }*/

        HttpSession session = request.getSession();
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        Connection conn = null;
        Form encounterForm;
        BaseEncounter encounter = null;
        Map encMap = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);

            String formId;
            String encounterIdString = "";

            if (mapping.getParameter() != null && !mapping.getParameter().equals("")) {
                formId = mapping.getParameter();
                if (request.getAttribute("encounterId") != null) {
                    encounterIdString = request.getAttribute("encounterId").toString();
                }
            } else {
                formId = request.getAttribute("id").toString();
            }

            // Sometimes encounterId is sent in url
            if (request.getParameter("encounterId") != null) {
                encounterIdString = request.getParameter("encounterId").toString();
            }

            SessionPatient sessionPatient = null;
            Long patientId = null;
            Long pregnancyId = null;

            if (request.getParameter("next") != null) {
                String next = request.getParameter("next");
                request.setAttribute("next", next);
            }

            String siteId = "";
            try {
                siteId = SessionUtil.getInstance(session).getClientSettings().getSiteId().toString();
            } catch (SessionUtil.AttributeNotFoundException e) {
                // it's ok - we're in admin mode.
            }

            if (! formId.equals("1")) {
                try {
                    sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
                } catch (SessionUtil.AttributeNotFoundException e) {
                    log.error("Unable to get SessionPatient");
                }


                try {
                    patientId = sessionPatient.getId();
                    pregnancyId = sessionPatient.getCurrentPregnancyId();
                } catch (Exception e) {
                    log.error("Unable to get SessionPatient field");
                }
            } else {
                if (request.getParameter("patientId") != null) {
                    patientId = Long.valueOf(request.getParameter("patientId"));
                    try {
                        sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
                    } catch (SessionUtil.AttributeNotFoundException e) {
                        log.error("Unable to get SessionPatient");
                    }
                    pregnancyId = sessionPatient.getCurrentPregnancyId();
                }
            }

            encounterForm = ((Form) DynaSiteObjects.getForms().get(new Long(formId)));

            HashMap visiblePageItems = new HashMap();
            if (request.getParameter("id") != null) {
                encounterIdString = request.getParameter("id");
            }

            /**
             * Prevent Delivery Summary form from being posted from a child's record.
             */
            if (formId.equals("66")) {
                if (sessionPatient != null && sessionPatient.getParentId() != null) {
                    request.setAttribute("exception", "You may not post a Delivery Summary form while viewing a child. Please return to the mother's record.");
                    return mapping.findForward("error");
                }
            }

            boolean drugList = false;
            // add encounter id to referral form
            if (encounterForm.getId().intValue() == 83) {
                String lastEncounterId = (String) request.getAttribute("lastEncounterId");
                DynaValidatorForm dynaForm = (DynaValidatorForm) form;
                dynaForm.set("field1619", lastEncounterId);
            }

            String newform = "";
            if (request.getAttribute("newform") != null) {
                newform = (String) request.getAttribute("newform");
            }

            // Editing a form?
            // if (!newform.equals("1") || encounterIdString != null || !encounterIdString.equals("")) {
            if (!encounterIdString.equals("")) {
                Long encounterId = new Long(encounterIdString);
                String className = "org.cidrz.project.zeprs.valueobject.gen." + StringManipulation.fixClassname(encounterForm.getName());
                Class clazz = null;
                try {
                    clazz = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    encounter = (BaseEncounter) EncountersDAO.getOneById(conn, encounterId, new Long(formId), clazz);
                } catch (ObjectNotFoundException e) {
                    String errorMessage = "<p>An error has occurred. The system was unable to retrieve the requested record. " +
                            "Please press the \"Back\" button and try another link.</p>" +
                            "<p>This error has been logged by the system.</p>";
                    String logMessage = errorMessage +
                            "\n * Code is from FormDisplayAction." +
                            "\n * Debug: encounterId: " + encounterId + ", class: " + clazz + "Error: " + e;
                    log.error(logMessage);
                    request.setAttribute("exception", errorMessage);
                    return mapping.findForward("error");
                }
                DynaValidatorForm dynaForm = (DynaValidatorForm) form;
                // used to store values used in multiselect tag
                HashMap multiValues = new HashMap();

                // Attach a map of encounter values that has enumerations already resolved.
                encMap = PatientRecordUtils.getEncounterMap(encounterForm, encounter, "fieldId");
                encounter.setEncounterMap(encMap);

                // Section Map is used to reveal hidden fields that have values
                Map formSection = (Map) DynaSiteObjects.getFormSections().get(encounterForm.getId());
                Map formDependencies = (Map) DynaSiteObjects.getFormDependencies().get(encounterForm.getId());
                Map collapsingSections = (Map) DynaSiteObjects.getCollapsingSections().get(encounterForm.getId());
                Map collapsingDependencies = (Map) DynaSiteObjects.getCollapsingDependencies().get(encounterForm.getId());
                // Loop through the pageItems and use the encounterMap to identify the pageItems that have values
                // If it has a value, use the sectionMap to make that section visible.
                Long section = null;
                Long collapsingTableId = null;
                // Set newPageItems = new TreeSet(new DisplayOrderComparator());
                for (Iterator iterator = encounterForm.getPageItems().iterator(); iterator.hasNext();) {
                    PageItem pageItem = (PageItem) iterator.next();
                    // createPageItem(pageItem);
                    String value = null;
                    Long collapsingSectionId = null;
                    if (pageItem.getForm_field().isEnabled() == true) {
                        // Find which section the field is in
                        try {
                            section = (Long) formDependencies.get(pageItem.getForm_field().getId());
                        } catch (Exception e) {
                            // it's ok
                        }
                        // Is it in a collapsingSection?
                        try {
                            collapsingSectionId = (Long) collapsingDependencies.get(pageItem.getForm_field().getId());
                            if (collapsingSectionId != null) {
                                ArrayList collapsingSection = (ArrayList) collapsingSections.get(collapsingSectionId);
                                //the table that is dependent upon the collapsing table if the second item in the list.
                                collapsingTableId = (Long) collapsingSection.get(1);
                            }
                            // collapsingTableId = (Long) formDependencies.get(collapsingSection);
                        } catch (Exception e) {
                            // it's ok
                        }
                        value = (String) encMap.get("field" + pageItem.getForm_field().getId());
                        // value = BeanUtils.getProperty(encounter, "field" + pageItem.getForm_field().getId());
                        // Do not need to set property  if it's null
                        if (value != null) {
                            if (!pageItem.getForm_field().getType().equals("Display")) {
                                dynaForm.set("field" + pageItem.getForm_field().getId(), value);
                            }
                            // Use the sectionMap to make that section visible if necessary.
                            if ((!pageItem.isVisible()) & (section != null)) {
                                // pageItem.setVisible(true);
                                visiblePageItems.put("pageItem" + pageItem.getId(), "visible");
                            }
                            // Use the sectionMap to make that collapsingSection visible if necessary.
                            if (collapsingTableId != null) {
                                visiblePageItems.put("pageItem" + collapsingTableId, "visible");
                            }

                            // also set its sister fields in the section to true
                            // loop through the formSection, matching the masterId
                            List deps = (List) formSection.get(section);
                            if (deps != null) {
                                for (int i = 0; i < deps.size(); i++) {
                                    Long depId = (Long) deps.get(i);
                                    PageItem depPageItem = (PageItem) DynaSiteObjects.getPageItems().get(depId);
                                    // depPageItem.setVisible(true);
                                    visiblePageItems.put("pageItem" + pageItem.getId(), "visible");
                                }
                            }

                        }

                        if (pageItem.getInputType().equals("druglist")) {
                            drugList = true;
                        }

                        if (pageItem.getInputType().equals("multiselect_enum")) {
                            List masterList = new ArrayList();
                            multiValues.put(pageItem.getForm_field().getId(), masterList);
                        }

                        // populate the multiHelper array
                        // each field in which the multiselect widget stores data has the multiselect widget field id in the
                        // visibleDependencies1 property

                        if (pageItem.getInputType().equals("multiselect_item")) {
                            List itemList = null;
                            String visDeps1 = pageItem.getVisibleDependencies1();
                            if (visDeps1 != null) {
                                try {
                                    itemList = (List) multiValues.get(new Long(visDeps1));
                                } catch (NullPointerException e) {
                                    e.printStackTrace();  // multiselect_enum not exist, or out of order.
                                }
                            } else {
                                String error = "multiselect widget setup error - select the widget id for this field's visible deps1.";
                                log.error(error);
                                request.setAttribute("exception", error);
                                return mapping.findForward("error");
                            }

                            value = BeanUtils.getProperty(encounter, "field" + pageItem.getForm_field().getId());
                            if (value != null) {
                                //multifields.append(value+ ",");
                                itemList.add(value);
                                //multiValues.put(pageItem.getVisibleDependencies1(), itemList);
                            }
                        }
                    }
                }

                request.setAttribute(SUBJECT_KEY, encounter);
                request.setAttribute("multiValues", multiValues);
                Date dateVisit = encounter.getDateVisit();
                request.setAttribute("dateVisit", dateVisit);
                // used for remote widgets
                request.setAttribute("className", className);
                // loading of body onload DWRUtil.useLoadingMessage()
                request.setAttribute("dwr", 1);
            }

            if (visiblePageItems.size() > 0) {
                request.setAttribute("visiblePageItems", visiblePageItems);
            }

            request.setAttribute("encounterForm", encounterForm);

            List drugs = DynaSiteObjects.getDrugs();
            request.setAttribute("drugs", drugs);

            List sites = DynaSiteObjects.getClinics();
            request.setAttribute("sites", sites);

            String patientSiteId = SessionUtil.getInstance(session).getClientSettings().getSiteId().toString();
            Site site = (Site) DynaSiteObjects.getClinicMap().get(new Long(patientSiteId));
            Integer siteTypeId = site.getSiteTypeId();
            String siteAlphaId = site.getSiteAlphaId().substring(0, 2);
            String clinicId = site.getSiteAlphaId().substring(2, 3);
            ArrayList uthSubsites = new ArrayList();
            uthSubsites.add("A");
            uthSubsites.add("B");
            uthSubsites.add("C");
            uthSubsites.add("D");
            uthSubsites.add("4");
            uthSubsites.add("5");
            request.setAttribute("patientSiteId", patientSiteId);
            request.setAttribute("siteAlphaId", siteAlphaId);
            request.setAttribute("clinicId", clinicId);
            request.setAttribute("siteTypeId", siteTypeId);
            request.setAttribute("uthSubsites", uthSubsites);

            if ((encounterIdString.equals(""))) {

                // See if this form has 1 MaxSubmissions
                int maxSubmissions = encounterForm.getMaxSubmissions();
                if (maxSubmissions == 1) {
                	EncounterData encounterOneOnly = null;
                    try {
                    	encounterOneOnly = (EncounterData) EncountersDAO.getId(conn, patientId, pregnancyId, new Long(formId));
                        Long encounterId = encounterOneOnly.getId();
                        ActionForward forwardForm = null;
                        forwardForm = new ActionForward("/viewEncounter.do?patientId=" + patientId + "&id=" + encounterId);
                        forwardForm.setRedirect(true);
                        return forwardForm;
                        // send to the record view of this form.
                    } catch (ObjectNotFoundException e1) {
                        // it's ok - form not submitted yet.
                    }
                }

                // patient registration needs sex to be pre-filled to female
                if (encounterForm.getId().intValue() == 1) {
                    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
                    dynaForm.set("field490", "1");
                }
                // still birth record needs some value pre-filled
                if (encounterForm.getId().intValue() == 40) {
                	DynaValidatorForm dynaForm = (DynaValidatorForm) form;
                	if (sessionPatient.getCurrentEgaCalc() != null) {
                		String ega = sessionPatient.getCurrentEgaCalc().toString();
                		dynaForm.set("field129", ega);
                	}
                	try {
                		// first get the mother
                		Long motherId = sessionPatient.getParentId();
                		if (motherId != null) {
                			// this is the child
                			SessionPatient mother = SessionPatientDAO.getSessionPatient(conn, motherId, pregnancyId);
                			if (mother.getCurrentEgaDaysEncounterId() != null) {
                				mother = PatientRecordUtils.updateCurrentEga(conn, mother.getCurrentEgaDaysEncounterId(), mother);
                			}
                			// ega
                			dynaForm.set("field129", mother.getCurrentEgaCalc().toString());
                			NewbornEval newbornEval = null;
                			try {
                				newbornEval = (NewbornEval) EncountersDAO.getOne(conn, patientId, pregnancyId, "SQL_RETRIEVE23", NewbornEval.class);
                				// time of birth
                				dynaForm.set("field1514", newbornEval.getField1514().toString());
                				// date of birth
                				dynaForm.set("field844", newbornEval.getField1267().toString());
                			} catch (IOException e) {
                				log.error(e);
                			} catch (ServletException e) {
                				log.error(e);
                			} catch (SQLException e) {
                				log.error("newbornEval not populated: " + " patientId: " + patientId + " pregnancyId: " + pregnancyId + " error: " + e);
                			} catch (ObjectNotFoundException e) {
                				log.error("newbornEval not populated: " + " patientId: " + patientId + " pregnancyId: " + pregnancyId + " error: " + e);
                			}
                		} else {
                			// set ega if this form is being filled out under the mother
                			dynaForm.set("field129", sessionPatient.getCurrentEgaCalc().toString());
                		}
                	} catch (ServletException e) {
                		log.error(e);
                	} catch (SQLException e) {
                		log.error(e);
                	} catch (ObjectNotFoundException e) {
                		// it's ok
                	}
                }

                if (encounterForm.getId().intValue() == 65) {   //Problem labour visit
                    InitialVisit initialVisit = null;
                    try {
                        initialVisit = (InitialVisit) EncountersDAO.getOne(conn, patientId, pregnancyId, "SQL_RETRIEVE77", InitialVisit.class);
                        if (initialVisit.getField159() != null) {
                            Integer height = initialVisit.getField159();
                            DynaValidatorForm dynaForm = (DynaValidatorForm) form;
                            dynaForm.set("field159", height.toString());   // height
                        }
                    } catch (IOException e) {
                        log.error(e);
                    } catch (ServletException e) {
                        log.error(e);
                    } catch (SQLException e) {
                        log.error(e);
                    } catch (ObjectNotFoundException e) {
                        // it's ok - Delivery Sum form has not been submitted yet.
                    }
                }

                if (encounterForm.getId().intValue() == 23) {   //Newborn Eval
                	Newbornrecord newbornRecord = null;
                	try {
                		newbornRecord = (Newbornrecord) EncountersDAO.getOne(conn, patientId, pregnancyId, "SQL_RETRIEVE109", Newbornrecord.class);
                		DynaValidatorForm dynaForm = (DynaValidatorForm) form;
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
                	} catch (IOException e) {
                		log.error(e);
                	} catch (ServletException e) {
                		log.error(e);
                	} catch (SQLException e) {
                		log.error(e);
                	} catch (ObjectNotFoundException e) {
                		// it's ok - Delivery Sum form has not been submitted yet.
                	}
                }

                if (encounterForm.getId().intValue() == 68) {   //Maternal Discharge Summary
                    DeliverySum deliverySum = null;
                    try {
                        deliverySum = (DeliverySum) EncountersDAO.getOne(conn, patientId, pregnancyId, "SQL_RETRIEVE66", DeliverySum.class);
                        if (deliverySum.getField447() != null) {
                            Integer modeOfDelivery = deliverySum.getField447();
                            if (modeOfDelivery.intValue() == 792) {
                                DynaValidatorForm dynaForm = (DynaValidatorForm) form;
                                dynaForm.set("field1650", "true");   // CS
                            }
                        }
                    } catch (IOException e) {
                        log.error(e);
                    } catch (ServletException e) {
                        log.error(e);
                    } catch (SQLException e) {
                        log.error(e);
                    } catch (ObjectNotFoundException e) {
                        // it's ok - Delivery Sum form has not been submitted yet.
                    }
                }

                if (encounterForm.getId().intValue() == 84) {   //Infant Discharge Summary
                	DynaValidatorForm dynaForm = (DynaValidatorForm) form;
                	NewbornEval newbornEval = null;
                    try {
                        newbornEval = (NewbornEval) EncountersDAO.getOne(conn, patientId, pregnancyId, "SQL_RETRIEVE23", NewbornEval.class);
                        // Did baby receive ARV treatment?
                        if (newbornEval.getField1884() != null) {
                        	dynaForm.set("field1884", newbornEval.getField1884().toString());
                        }
                        // Given Nevirapine Initial Dose?
                        /*if (newbornEval.getField1939() != null) {
                        	dynaForm.set("field1939", newbornEval.getField1939().toString());
                        }*/
                        // Regimen - baby dose:
                        if (newbornEval.getField1899() != null) {
                        	dynaForm.set("field1899", newbornEval.getField1899().toString());
                        }
                        // Amount of dosage:
                        if (newbornEval.getField1893() != null) {
                        	dynaForm.set("field1893", newbornEval.getField1893().toString());
                        }

                    } catch (IOException e) {
                        log.error(e);
                    } catch (ServletException e) {
                        log.error(e);
                    } catch (SQLException e) {
                        log.error("newbornEval not populated: " + " patientId: " + patientId + " pregnancyId: " + pregnancyId + " error: " + e);
                    } catch (ObjectNotFoundException e) {
                        //log.error("NewbornEval not populated before rendering Infant discharge summary: " + " patientId: " + patientId + " pregnancyId: " + pregnancyId + " error: " + e + " Sent to error page.");
                        request.setAttribute("exception", "The Newborn evaluation form is not submitted for this patient. Please submit the Newborn evaluation form first before submitting the Infant Discharge Summary.");
                        return mapping.findForward("error");
                    }
                }

                if (encounterForm.getId().intValue() == 33) { // UTH Neonatal Case Record
                    Long parentId = sessionPatient.getParentId();
                    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
                    // prefill date of birth
                    String birthDate = sessionPatient.getBirthdate().toString();
                    dynaForm.set("field728", birthDate);
                    // prefill time of birth
                    String birthTime = sessionPatient.getTimeOfBirth().toString();
                    dynaForm.set("field1514", birthTime);
                    // Patient mother = PatientDAO.getOne(sessionPatient.getParentId());
                    SessionPatient mother = SessionPatientDAO.getSessionPatient(conn, parentId, pregnancyId);
                    java.sql.Date motherBirthdate = mother.getBirthdate();
                    if (motherBirthdate != null) {
                        int mothersAge = ZEPRSUtils.getCurrentAge(motherBirthdate);
                        dynaForm.set("field730", String.valueOf(mothersAge));
                    }
                    int infantAge = ZEPRSUtils.getNewbornAge(sessionPatient.getBirthdate());
                    dynaForm.set("field732", String.valueOf(infantAge));
                    int sex = sessionPatient.getSex();
                    dynaForm.set("field490", String.valueOf(sex));
                    // Calculate parity - Fill out the number of children born by this mother - including stillbirths
                    int parity = PatientRecordUtils.getParity(conn, parentId);
                    dynaForm.set("field734", String.valueOf(parity));
                    // LMP
                    Date lmp = mother.getCurrentLmpDate();
                    if (lmp != null) {
                        dynaForm.set("field735", lmp.toString());
                    }
                    // EGA - calc EGA from last EGA estimate + and date of delivery
                    Long egaEncounterId = mother.getCurrentEgaDaysEncounterId();
                    if (egaEncounterId != null) {
                        java.util.Date egaDateVisit = PregnancyDatingDAO.getEgaDate(conn, egaEncounterId);
                        long egaToday = mother.getCurrentEgaDaysDB().longValue();
                        long diffDays = DateUtils.calculateDays(egaDateVisit, sessionPatient.getBirthdate());
                        long ega = egaToday + diffDays;
                        dynaForm.set("field129", String.valueOf(ega));
                    }
                    // abo, rhesus
                    Integer bloodType;
                    Integer rhesus;
                    List bloodTests = EncountersDAO.getAll(conn, parentId, pregnancyId, new Long("87"), LabTest.class);
                    if (bloodTests.size() > 0) {
                        for (int i = 0; i < bloodTests.size(); i++) {
                            LabTest labTest = (LabTest) bloodTests.get(i);
                            if (labTest.getField1845() == 2923) {   // type of lab: blood test
                                bloodType = labTest.getField1847(); // results field
                                dynaForm.set("field738", String.valueOf(bloodType));
                            } else if (labTest.getField1845() == 2924) {   // type of lab: rhesus
                                rhesus = labTest.getField1847(); // results field
                                dynaForm.set("field739", String.valueOf(rhesus));
                            }
                        }
                    }
                    // Mothers Occupation
                    PatientRegistration demographics = (PatientRegistration) EncountersDAO.getOne(conn, parentId, pregnancyId, new Long("1"));
                    Integer occupation = demographics.getField12();
                    if (occupation != null) {
                        dynaForm.set("field12", String.valueOf(occupation));
                    }
                    DeliverySum deliverySum = null;
                    // get delivery summary for following fields
                    try {
                        deliverySum = (DeliverySum) EncountersDAO.getOne(conn, parentId, pregnancyId, new Long("66"));
                    } catch (IOException e) {
                        log.error(e);
                    } catch (ServletException e) {
                        log.error(e);
                    } catch (SQLException e) {
                        log.error(e);
                    } catch (ObjectNotFoundException e) {
                        // it's ok
                    }
                    if (deliverySum != null) {
                        Integer mode = deliverySum.getField447();
                        if (mode != null) {
                            dynaForm.set("field447", String.valueOf(mode));
                        }
                        //  Indication for C/S Forceps/Vacuum:
                        Integer indication = deliverySum.getField60();
                        if (indication != null) {
                            dynaForm.set("field60", String.valueOf(indication));
                        }
                        //  Place of Delivery: preset to Clinic if deliverySum form avail.
                        if (deliverySum.getSiteId().intValue() == 38) {
                            dynaForm.set("field50", String.valueOf(553));   // preset to Hos
                        } else {
                            dynaForm.set("field50", String.valueOf(19));   // preset to Hos
                        }
                        if (deliverySum.getField328() != null) {
                            Date rupturedDate = deliverySum.getField328();
                            if (rupturedDate != null) {
                                dynaForm.set("field328", rupturedDate.toString());
                            }
                        }
                        if (deliverySum.getField329() != null) {
                            Time rupturedTime = deliverySum.getField329();
                            if (rupturedTime != null) {
                                dynaForm.set("field329", rupturedTime.toString());
                            }
                        }
                        long firstStageHours = PatientRecordUtils.calcFirstStage(deliverySum);
                        if (firstStageHours > 0) {
                            dynaForm.set("field748", String.valueOf(firstStageHours));
                        }

                        long secondStageHours = PatientRecordUtils.calcSecondStage(deliverySum, sessionPatient.getBirthdate(), sessionPatient.getTimeOfBirth());
                        if (secondStageHours > 0) {
                            dynaForm.set("field749", String.valueOf(secondStageHours));
                        }
                        if (deliverySum.getField440() != null) {
                            Integer placentaType = deliverySum.getField440();
                            if (placentaType != null) {
                                dynaForm.set("field440", placentaType.toString());
                            }
                        }
                        if (deliverySum.getField441() != null) {
                            Integer weight = deliverySum.getField441();
                            if (weight != null) {
                                dynaForm.set("field441", weight.toString());
                            }
                        }
                        List children = PatientDAO.getChildren(conn, parentId, pregnancyId);
                        if (children.size() > 0) {
                            int childrenNum = children.size();
                            if (childrenNum == 1) {
                                dynaForm.set("field63", "28");
                            } else if (childrenNum == 2) {
                                dynaForm.set("field63", "562");
                            } else if (childrenNum == 3) {
                                dynaForm.set("field63", "1088");
                            } else if (childrenNum >= 4) {
                                dynaForm.set("field63", "1415");
                            }
                        }
                    }
                    /*MedSurgHist medSurgHist = null;
                    try {
                        medSurgHist = (MedSurgHist) EncountersDAO.getOne(parentId, pregnancyId, new Long("70"));
                        Byte diabetes = medSurgHist.getField72();
                        if (diabetes !=null) {
                             dynaForm.set("field1542", "927");
                        }
                    } catch (ObjectNotFoundException e) {
                        // it's ok
                    }*/
                }

                // provide partographStatus to aid calculating the Delivery Summary form's "Duration of Labour" value
                // also loop through liquor to find rupture of membranes
                if (encounterForm.getId().intValue() == 66) {  // Delivery Summary
                    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
                    // set the  Username of Nurse Delivering field
                    dynaForm.set("field1961", request.getUserPrincipal().getName());
                    // Loop through list of probLabour visits and get the timestamp of the last one - this indicated beginning of labour
                    Timestamp firstStageBegan = null;
                    firstStageBegan = PatientRecordUtils.getFirstStageBegan(conn, patientId, pregnancyId);
                    if (firstStageBegan != null) {
                        String firstStage = DateUtils.getTime(firstStageBegan);
                        dynaForm.set("field431", firstStage);  // first_stage_began_431
                    }

                    String firstStageEnd = null;
                    firstStageEnd = PatientRecordUtils.getFirstStageEnd(conn, patientId, pregnancyId);
                    if (firstStageEnd != null) {
                        dynaForm.set("field432", firstStageEnd);  // complete_dilitation_432
                    }


                    try {
                        PartographStatus partographStatus = (PartographStatus) EncountersDAO.getOne(conn, patientId, pregnancyId, "SQL_RETRIEVE79", PartographStatus.class);
                        // getLabourDuration(partographStatus, request);
                        request.setAttribute("partographStatus", partographStatus);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ServletException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ObjectNotFoundException e) {
                        // it's ok - parto has not been started.
                    }
                    String timeRuptured = null;
                    int timeKey = 0;
                    Liquor liquor = null;
                    LiquorDAO dao = new LiquorDAO();
                    try {
                        liquor = (Liquor) dao.getOne(conn, patientId, pregnancyId);
                        Map liquorMap = BeanUtils.describe(liquor);
                        Set liquorSet = liquorMap.entrySet();
                        for (Iterator iterator = liquorSet.iterator(); iterator.hasNext();) {
                            Map.Entry entry = (Map.Entry) iterator.next();
                            String key = (String) entry.getKey();
                            Object value = entry.getValue();
                            if (key.startsWith("liquor")) {
                                String liquorValue = (String) value;
                                if ((liquorValue != null) && (!liquorValue.equals("I"))) {
                                    int currentTimeKey = (new Integer(key.substring(6)));
                                    if ((timeKey == 0) || (currentTimeKey < timeKey)) {
                                        timeRuptured = String.valueOf(liquorMap.get("timeObservation" + currentTimeKey));
                                    }
                                }
                            }
                            /*String[] result = key.split("_");
                         String columnType =  result[0];
                         String item = result[1];*/
                        }
                        if (timeRuptured != null) {
                            dynaForm.set("field329", timeRuptured);
                        }
                    } catch (SQLException e) {
                        log.error(e);
                    } catch (ServletException e) {
                        log.error(e);
                    } catch (IOException e) {
                        log.error(e);
                    } catch (ObjectNotFoundException e) {
                        // skip
                    }
                }
            }


            if (encounterForm.getId().intValue() == 44) { // Ultrasound eval.
                DynaValidatorForm dynaForm = (DynaValidatorForm) form;
                // Setup the sequence number for the ultrasound form
                List ultraVisits = EncountersDAO.getAll(conn, patientId, pregnancyId, new Long("44"), AnteUltrasoundEval.class);
                int ultraVisitsNum = 1;
                ultraVisitsNum = ultraVisitsNum + ultraVisits.size();
                // preset field1916 - exam seq/  number
                dynaForm.set("field1916", String.valueOf(ultraVisitsNum));
                request.setAttribute("examSequence", String.valueOf(ultraVisitsNum));

                //pre=populate the seq num for fetuses
                Long fetusEvalFormId = new Long("93");
                Form fetusEvalForm = (Form) DynaSiteObjects.getForms().get(fetusEvalFormId);
                List fetusMapList = new ArrayList();
                int seqFetuses = 0;
                Long encounterId = null;
                if (encounter != null) {
                	encounterId = encounter.getId();
                    AnteUltrasoundEval ultraVisit = (AnteUltrasoundEval) encounter;
                    Integer examSequence = ultraVisit.getField1916();
                    // EncountersDAO.getOneById(encounterForm.getId())
                    List fetuses = UltrasoundDAO.getExams(conn, examSequence, patientId, pregnancyId, fetusEvalFormId, UltrasoundFetusEval.class);
                    // EncountersDAO.getAll(patientId, pregnancyId, fetusEvalFormId, UltrasoundFetusEval.class);
                    if (fetuses != null) {
                        seqFetuses = fetuses.size();
                        for (int i = 0; i < fetuses.size(); i++) {
                            UltrasoundFetusEval fetusEval = (UltrasoundFetusEval) fetuses.get(i);
                            Map fMap = PatientRecordUtils.getEncounterMap(fetusEvalForm, fetusEval, "fieldId");
                            fetusMapList.add(fMap);
                        }
                        if (fetusMapList != null) {
                            request.setAttribute("fetusMapList", fetusMapList);
                        }
                    }
                }
                // String seqFetuses = String.valueOf(seqFetuses + 1);
                request.setAttribute("encounterId", encounterId);
                request.setAttribute("seqFetuses", String.valueOf(seqFetuses + 1));
                //setup some vars
                request.setAttribute("user", request.getUserPrincipal().getName());
                request.setAttribute("patientId", patientId);
                request.setAttribute("siteId", siteId);
                request.setAttribute("pregnancyId", pregnancyId);
            }

            if (encounterForm.getId().intValue() == 66) {  // delivery summary
                //pre=populate the seq num for infants
                //force
                String seqChildren = null;
                try {
                    seqChildren = String.valueOf(sessionPatient.getChildren().size());
                } catch (NullPointerException e) {
                    log.error("Null value for sessionPatient.getChildren() - should at least be an empty list. PatientId: " + patientId + " pregnancyId: " + pregnancyId);
                }
                request.setAttribute("seqChildren", seqChildren);
                //setup some vars
                request.setAttribute("user", request.getUserPrincipal().getName());
                request.setAttribute("patientId", patientId);
                request.setAttribute("siteId", siteId);
                request.setAttribute("pregnancyId", pregnancyId);
                String rawNewbornList = Newborn.GetNewbornList(conn,patientId, pregnancyId);
                String newbornList =  null;
                try {
                    String[] newbornArray =  rawNewbornList.split("\\|");
                    newbornList = newbornArray[1];
                } catch (Exception e) {
                    //
                }
                request.setAttribute("newbornList", newbornList);
            }

            int encounterFormId = encounterForm.getId().intValue();
            if ((encounterFormId >= 87 || encounterFormId <= 91) ||  (encounterFormId >= 101 || encounterFormId <= 104))
             //       (encounterFormId == 87) || (encounterFormId == 90) || (encounterFormId == 88) || (encounterFormId == 91) || (encounterFormId == 89))
            {
                // populate the records for this class
                List chartItems = null;
                String classname = StringManipulation.fixClassname(encounterForm.getName());
                Class className = Class.forName("org.cidrz.project.zeprs.valueobject.gen." + classname);

                try {
                	if (encounterFormId == 87) {
                        chartItems = EncountersDAO.getAllOrdered(conn, patientId, pregnancyId, "SQL_RETRIEVE" + formId, className, "ORDER BY created DESC");
                	} else {
                        chartItems = EncountersDAO.getAll(conn, patientId, pregnancyId, "SQL_RETRIEVE" + formId, className);
                	}
                } catch (IOException e) {
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                } catch (ServletException e) {
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                } catch (SQLException e) {
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                }
                // Attach a map of encounter values that has enumerations already resolved.
                Form encForm = (Form) DynaSiteObjects.getForms().get(encounterForm.getId());
                for (int i = 0; i < chartItems.size(); i++) {
                    encounter = (EncounterData) chartItems.get(i);
                    // Form encForm = (Form) DynaSiteObjects.getForms().get(encounter.getFormId());
                    encMap = PatientRecordUtils.getEncounterMap(encForm, encounter, "fieldId");
                    if (encounter.getFormId().intValue() == 87) {
                        LabTest labtest = (LabTest) encounter;
                        Long labtestId = labtest.getId();
                        Long extendedLabId = labtest.getField2044();
                        Integer labType = labtest.getField1845();
                        Long extendedLabFormId = null;
                        if (extendedLabId != null) {
                        	EncounterData extendedLab = null;
                        	switch (labType.intValue()) {
                        	case 3071:
                        		extendedLabFormId = new Long("101");
                        		try {
                        			extendedLab = (HemotologyReport) EncountersDAO.getOneReportByIdResolved(conn, extendedLabId, extendedLabFormId, HemotologyReport.class);
                        		} catch (ObjectNotFoundException e) {
                        			log.debug("Record missing for extendedLabId: " + extendedLabId + "with extendedLabFormId: " + extendedLabFormId + " patientId: " + patientId + " labtestId: " + labtestId + " Solution: delete record from labtest using extendedLabId and labtestId");
                        		}
                        		break;
                        	case 3072:
                        		extendedLabFormId = new Long("102");
                        		try {
                        			extendedLab = (ChemistryReport) EncountersDAO.getOneReportByIdResolved(conn, extendedLabId, extendedLabFormId, ChemistryReport.class);
                        		} catch (ObjectNotFoundException e) {
                        			log.debug("Record missing for extendedLabId: " + extendedLabId + "with extendedLabFormId: " + extendedLabFormId + " patientId: " + patientId + " labtestId: " + labtestId + " Solution: delete record from labtest using extendedLabId and labtestId");
                        		}
                        		break;
                        	case 3073:
                        		extendedLabFormId = new Long("103");
                        		try {
                        			extendedLab = (LiverfunctionReport) EncountersDAO.getOneReportByIdResolved(conn, extendedLabId, extendedLabFormId, LiverfunctionReport.class);
                        		} catch (ObjectNotFoundException e) {
                        			log.debug("Record missing for extendedLabId: " + extendedLabId + "with extendedLabFormId: " + extendedLabFormId + " patientId: " + patientId + " labtestId: " + labtestId + " Solution: delete record from labtest using extendedLabId and labtestId");
                        		}
                        		break;
                        	case 3074:
                        		extendedLabFormId = new Long("104");
                        		try {
                        			extendedLab = (UrinalysisReport) EncountersDAO.getOneReportByIdResolved(conn, extendedLabId, extendedLabFormId, UrinalysisReport.class);
                        		} catch (ObjectNotFoundException e) {
                        			log.debug("Record missing for extendedLabId: " + extendedLabId + "with extendedLabFormId: " + extendedLabFormId + " patientId: " + patientId + " labtestId: " + labtestId + " Solution: delete record from labtest using extendedLabId and labtestId");
                        		}
                        		break;
                        	}
                        	if (extendedLab != null) {
                        		String results = getEncounterValues(extendedLab, "br");
                        		encMap.put("field1847", results);
                        	}
                        }
                    }
                    if ((encounterFormId >= 101 && encounterFormId <= 104)) {
                        if (encMap.get("field2037") != null) {
                            Long labTestId = Long.valueOf((String) encMap.get("field2037"));
                            LabTest labTest = (LabTest) EncountersDAO.getOneById(conn, labTestId, Long.valueOf("87"), LabTest.class);
                            Date dateRequested = labTest.getField1844();
                            Date dateResult = labTest.getField1846();
                            encMap.put("dateRequested", dateRequested);
                            encMap.put("dateResult", dateResult);
                        }
                    }
                   // log.debug("map: " + encMap.toString());
                    encounter.setEncounterMap(encMap);
                }
                if (chartItems.size() > 0) {
                    request.setAttribute("chartItems", chartItems);
                    request.setAttribute("formId", encounterForm.getId());
                    // loading of body onload DWRUtil.useLoadingMessage()
                    request.setAttribute("dwr", 1);
                }
                /*if (sessionPatient != null) {
                	String zeprsId = sessionPatient.getDistrictPatientid();
                    zeprsId = "5040-100-01569-1";
                    Connection limsConn = DatabaseUtils.getLimsConnection();
                    List limsResults = LabTestDAO.getLims(limsConn, zeprsId);
                    if (limsResults.size() > 0) {
                        request.setAttribute("limsResults", limsResults);
                    }
                }*/
            }

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
                } catch (NullPointerException e) {
                    // not in this form
                } catch (IllegalArgumentException e) {
                    // not in this form
                }
                request.setAttribute("referral", referral);
            }

            List yearList = DateUtils.getYearList();
            request.setAttribute("yearList", yearList);

            // Keep this block at the end - it sets sessionPatient to null in certain circumstances.
            // Set the tasklist for particular circumstances. First check if the form requires a patient or if "id" is in the reqiest.
            if ((encounterForm.isRequirePatient() || ((request.getParameter("id") != null)))) {
                // we don't need the tasklist if we're just editing a form or it's in unassigned flow
                Long unassigned = new Long("100");
                if (request.getParameter("id") == null) {
                    if (!encounterForm.getFlow().getId().equals(unassigned)) {
                         // moved code for form 66 below.
                    }
                }
                Boolean status = Boolean.valueOf(true);
                // 2/15/2006 - I've been seeing null pregnancyId's - trying to track down this issue.
                if (pregnancyId == null) {
                    log.debug("|--- pregnancyId is null - patientId: " + patientId + " siteId: " + siteId);
                    log.debug("|--- encounterForm.getId(): " + encounterForm.getId());
                    log.debug("Redirecting to home page.");
                    return mapping.findForward("home");
                }
                List activeProblems = PatientRecordUtils.assembleProblemTaskList(conn, patientId, pregnancyId, status, sessionPatient);
                request.setAttribute("activeProblems", activeProblems);
                // now get inactive problems
                status = Boolean.valueOf(false);
                List inactiveProblems = PatientRecordUtils.assembleProblemTaskList(conn, patientId, pregnancyId, status, sessionPatient);
                request.setAttribute("inactiveProblems", inactiveProblems);
                // Display task list if editing form 1.
            } else if ((encounterForm.getId().intValue() == 1) & (patientId != null)) {
                Boolean status = Boolean.valueOf(true);
                List activeProblems = PatientRecordUtils.assembleProblemTaskList(conn, patientId, pregnancyId, status, sessionPatient);
                request.setAttribute("activeProblems", activeProblems);
                // now get inactive problems
                status = Boolean.valueOf(false);
                List inactiveProblems = PatientRecordUtils.assembleProblemTaskList(conn, patientId, pregnancyId, status, sessionPatient);
                request.setAttribute("inactiveProblems", inactiveProblems);
                // otherwise reset sessionPatient
            } else {
                SessionUtil.getInstance(session).setSessionPatient(null);
            }

        } catch (ServletException e) {
            log.error(e);
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }

        encounterForm = null;

        return mapping.findForward("success");

    }

    /**
     * Provides a list of values for extended Lab tests (forms 101-104)
     * @param extendedLab
     * @param delimiter
     * @return String
     */
    public static String getEncounterValues(EncounterData extendedLab, String delimiter) {
        StringBuffer sb = new StringBuffer();
        Set extLabSet = extendedLab.getEncounterMap().entrySet();
        for (Iterator iterator = extLabSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (!key.equals("labtest_idR")) {
                int index = key.length();
                sb.append(key.substring(0, index - 1));
                sb.append(":");
                sb.append(value);
                if (delimiter.equals("br")) {
                    sb.append("<br/>");
                } else {
                    sb.append("; ");
                }

            }
        }
        return sb.toString();
    }
}
