/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.remote;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.*;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.project.zeprs.valueobject.gen.Rpr;
import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.io.IOException;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 13, 2005
 *         Time: 11:32:53 AM
 */
public class Encounter {

    /**
     * Commons Logging instance.
     */
    public static Log log = LogFactory.getFactory().getInstance(Encounter.class);

    /**
     * @param inputType
     * @param value
     * @param pageItemId
     * @param formId
     * @param encounterId
     * @param widgetType  - "Form" or "Chart"
     * @return updated value
     */
    public static String update(String inputType, String value, Long pageItemId, Long formId, Long encounterId, String widgetType, Long displayField) {
        String result = "";
        WebContext exec = WebContextFactory.get();
        String username = null;
        try {
            username = exec.getHttpServletRequest().getUserPrincipal().getName();
        } catch (NullPointerException e) {
            // unit testing - it's ok...
            username = "demo";
        }
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            SessionUtil zeprsSession = null;
            try {
                HttpSession session = exec.getSession();
                zeprsSession = (SessionUtil) session.getAttribute("zeprs_session");
            } catch (Exception e) {
                // unit testing - it's ok...
            }
            Long patientId = null;
            Long pregnancyId = null;
            Long siteId = null;
            String documentId = null;

            if (displayField == null) {
                displayField = encounterId;
            }

            PageItem pageItem = (PageItem) DynaSiteObjects.getPageItems().get(pageItemId);
            Long formFieldId = pageItem.getForm_field().getId();

            if (widgetType.equals("Form")) {
                documentId = String.valueOf(formFieldId);
            } else if (widgetType.equals("Chart")) {
                documentId = String.valueOf(encounterId) + "." + String.valueOf(formFieldId);
            }
            if (pageItemId == 3861) {
                documentId = String.valueOf(encounterId) + "." + String.valueOf(displayField);
            }

            SessionPatient sessionPatient = null;  // sessionPatient used for rule processing in EncountersDAO.update
            if (zeprsSession != null) {
            	try {
                    sessionPatient = zeprsSession.getSessionPatient();
                    patientId = zeprsSession.getSessionPatient().getId();
                    pregnancyId = zeprsSession.getSessionPatient().getCurrentPregnancyId();
                    ClientSettings clientSettings = zeprsSession.getClientSettings();
                    siteId = clientSettings.getSiteId();
                } catch (SessionUtil.AttributeNotFoundException e) {
                	log.error("inputType: " + inputType + " value: " + value + " pageItemId: " + pageItemId + " formId: " + formId + " encounterId: " + encounterId);
                    return documentId + "=" + "Error: your session may have expired. Please refresh this page or login again.";
                } catch (NullPointerException e) {
                    // it's ok - testing
                    patientId = new Long("44");
                    pregnancyId = new Long("38");
                    siteId = new Long("1");
                }
            } else {
            	log.error("inputType: " + inputType + " value: " + value + " pageItemId: " + pageItemId + " formId: " + formId + " encounterId: " + encounterId);
                return documentId + "=" + "Error: your session may have expired. Please refresh this page or login again.";
            }

            Timestamp lastModified = null;
            if (value != null) {
                try {
                    EncountersDAO.update(conn, value, pageItemId, formId, encounterId, siteId, username, patientId, pregnancyId, sessionPatient, lastModified, null);
                } catch (SQLException e) {
                    log.error(e);
                } catch (ServletException e) {
                    log.error(e);
                } catch (PersistenceException e) {
                	result = documentId + "=" + "Error:" + e.getMessage();
				}

                if (result.equals("")) {
                    if (inputType.equals("select") || inputType.equals("select-dwr") || inputType.equals("multiselect_item"))
                    {
                        FieldEnumeration fieldEnum = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get(Long.valueOf(value));

                        switch (formFieldId.intValue()) {
                            case 1563:
                                // see if RPR result date has value
                                String rprDate = "";
                                try {
                                    Rpr item = (Rpr) EncountersDAO.getOneById(conn, encounterId, formId, Rpr.class);
                                    if (item.getField1562() == null) {
                                        // check if reactive or non-reactive
                                        if (value.equals("2784") || value.equals("2785")) {
                                            // update w/ today's date
                                            rprDate = new Date(System.currentTimeMillis()).toString();
                                            Long rprDatePi = new Long("3878");
											EncountersDAO.update(conn, rprDate, rprDatePi, formId, encounterId, siteId, username, patientId, pregnancyId, sessionPatient, lastModified, null);
                                        }
                                    } else {
                                        rprDate = "";
                                    }
                                } catch (IOException e) {
                                	log.debug(e);
                                } catch (SQLException e) {
                                	log.debug(e);
                                } catch (ObjectNotFoundException e) {
                                	log.debug(e);
                                } catch (PersistenceException e) {
									log.debug(e);
								}
                                String rprDateDocumentId = String.valueOf(encounterId) + ".1562";
                                result = documentId + "=" + fieldEnum.getEnumeration() + "=" + rprDateDocumentId + "=" + rprDate;
                                break;
                            case 1565:
                                // see if RPR treatment date has value
                                rprDate = new Date(System.currentTimeMillis()).toString();
                                try {
                                    Rpr item = (Rpr) EncountersDAO.getOneById(conn, encounterId, formId, Rpr.class);
                                    if (item.getField1564() == null) {
                                        // update w/ today's date
                                        Long rprDatePi = new Long("3880");
                                        EncountersDAO.update(conn, rprDate, rprDatePi, formId, encounterId, siteId, username, patientId, pregnancyId, sessionPatient, lastModified, null);
                                    } else {
                                        rprDate = "";
                                    }
                                } catch (IOException e) {
                                	log.debug(e);
                                } catch (SQLException e) {
                                	log.debug(e);
                                } catch (ObjectNotFoundException e) {
                                	log.debug(e);
                                } catch (PersistenceException e) {
									log.debug(e);
								}
                                rprDateDocumentId = String.valueOf(encounterId) + ".1564";
                                result = documentId + "=" + fieldEnum.getEnumeration() + "=" + rprDateDocumentId + "=" + rprDate;
                                break;
                            default:
                                result = documentId + "=" + fieldEnum.getEnumeration();
                                break;
                        }

                    } else if (inputType.equals("lab_results")) {
                        FieldEnumeration fieldEnum = (FieldEnumeration) DynaSiteObjects.getFieldEnumerations().get(Long.valueOf(value));
                        result = documentId + "=" + fieldEnum.getEnumeration();
                    } else if (inputType.equals("currentMedicine")) {
                        // Drugs drug = DrugsDAO.getOne(Long.valueOf(value));
                        Drugs drug = null;
                        try {
                            drug = (Drugs) DynaSiteObjects.getDrugMap().get(Long.valueOf(value));
                            result = documentId + "=" + drug.getName();
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        if (drug == null) {
                            result = documentId + "=" + value;
                        }
                    } else if (inputType.equals("Yes/No")) {
                        if (value.equals("1")) {
                            value = "Yes";
                        } else if (value.equals("0")) {
                            value = "No";
                        }
                        result = documentId + "=" + value;
                    } else if (inputType.equals("checkbox")) {
                        if (value.equals("true")) {
                            value = "Yes";
                        } else if (value.equals("1")) {
                            value = "Yes";
                        } else if (value.equals("on")) {
                            value = "Yes";
                        } else if (value.equals("false")) {
                            value = "No";
                        } else if (value.equals("0")) {
                            value = "No";
                        } else if (value.equals("off")) {
                            value = "No";
                        }
                        result = documentId + "=" + value;
                    } else if (inputType.equals("checkbox_dwr")) {
                        if (value.equals("true")) {
                            value = "Yes";
                        } else if (value.equals("on")) {
                            value = "Yes";
                        } else if (value.equals("false")) {
                            value = "No";
                        } else if (value.equals("off")) {
                            value = "No";
                        }
                        result = documentId + "=" + value;
                    } else if (inputType.equals("sex")) {
                        if (value.equals("1")) {
                            value = "Female";
                        } else if (value.equals("2")) {
                            value = "Male";
                        }
                        result = documentId + "=" + value;
                    } else if (inputType.equals("ega")) {
                        int valueInt = new Integer(value);
                        int days = valueInt % 7;
                        int weeks = valueInt / 7;
                        value = weeks + ", " + days + "/7";
                        result = documentId + "=" + value;
                    } else if (pageItem.getInputType().equals("sites") || pageItem.getInputType().equals("sites_not_selected")) {
                        Long thisSite = new Long(value);
                        Site site = (Site) DynaSiteObjects.getClinicMap().get(thisSite);
                        value = site.getName();
                        result = documentId + "=" + value;
                    } else if (inputType.equals("text") & displayField.intValue() != formFieldId.intValue()) {
                        // used in Lab form chart to share two fields in one cell.
                        /*if (displayField != 0) {
                            documentId = String.valueOf(encounterId) + "." + String.valueOf(displayField);
                        }*/
                        result = documentId + "=" + value;
                    } else {
                        result = documentId + "=" + value;
                    }
                }
            } else {
                result = documentId + "=" + "Error: No value entered.";
            }
        } catch (ServletException e) {
            log.error(e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Updates value for encounter record metadata.
     * @param inputType
     * @param value
     * @param encounterId
     * @param widgetType
     * @return
     */
    public static String updateMetadata(String inputType, String value, Long encounterId) {
    	String result = "";
    	WebContext exec = WebContextFactory.get();
    	String username = null;
    	try {
    		username = exec.getHttpServletRequest().getUserPrincipal().getName();
    	} catch (NullPointerException e) {
    		// unit testing - it's ok...
    		username = "demo";
    	}
    	Connection conn = null;
    	try {
    		conn = DatabaseUtils.getZEPRSConnection(username);
    		SessionUtil zeprs_session = null;
    		try {
    			HttpSession session = exec.getSession();
    			zeprs_session = (SessionUtil) session.getAttribute("zeprs_session");
    		} catch (Exception e) {
    			// unit testing - it's ok...
    		}
    		Long patientId = null;
    		Long pregnancyId = null;
    		Long siteId = null;
    		try {
    			ClientSettings clientSettings = zeprs_session.getClientSettings();
    			siteId = clientSettings.getSiteId();
    		} catch (SessionUtil.AttributeNotFoundException e) {
    			log.error(e);
    		} catch (NullPointerException e) {
    			// it's ok - unit testing
    			siteId = new Long("1");
    		}
    		SessionPatient sessionPatient = null;  // sessionPatient used for rule processing in EncountersDAO.update
    		try {
    			sessionPatient = zeprs_session.getSessionPatient();
    		} catch (SessionUtil.AttributeNotFoundException e) {
    			log.error(e);
    		}
    		Timestamp lastModified = new Timestamp(System.currentTimeMillis());
    		if (value != null) {
    			try {
    				//EncountersDAO.update(conn, value, pageItemId, formId, encounterId, siteId, username, patientId, pregnancyId, sessionPatient, lastModified);
    				if (inputType.startsWith("SiteId")) {
    					EncountersDAO.touchEncounterTable(conn, username, Long.valueOf(value), encounterId, lastModified);
    				} else if (inputType.startsWith("dateVisit")) {
    					EncountersDAO.touchEncounterTable(conn, username, Date.valueOf(value), encounterId);
    				}
    			} catch (SQLException e) {
    				log.error(e);
    			}

    			if (result.equals("")) {
    				if (inputType.startsWith("SiteId")) {
    					Site site = (Site) DynaSiteObjects.getClinicMap().get(Long.valueOf(value));
    					result = inputType + "=" + site.getName();
    				} else if (inputType.startsWith("dateVisit")) {
    					result = inputType + "=" + value;
    				}
    			}
    		} else {
    			result = inputType + "=" + "Error: No value entered.";
    		}
    	} catch (ServletException e) {
    		log.error(e);
    	} finally {
    		try {
    			if (conn != null && !conn.isClosed()) {
    				conn.close();
    				conn = null;
    			}
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	return result;
    }
}
