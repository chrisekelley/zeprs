/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */
package org.rti.zcore.dar.struts.action;

import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.project.zeprs.valueobject.BaseEncounter;
import org.cidrz.webapp.dynasite.Constants;
import org.rti.zcore.DropdownItem;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.Task;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
//import org.rti.zcore.dar.report.valueobject.StockReport;
//import org.rti.zcore.impl.BaseSessionSubject;
//import org.rti.zcore.impl.TimsSessionSubject;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.utils.StringManipulation;
import org.cidrz.webapp.dynasite.utils.WidgetUtils;

/**
 * Note: You can't use this action on patients because it does not extend BasePatientAction.
 * If you send constraintClause and constraintLong in the request, you can get detail listings,
 * which is useful for stock_items.
 * @author ckelley
 *
 */
public class ListAction extends BaseAction {

	/**
	 * Commons Logging instance.
	 */
	private Log log = LogFactory.getFactory().getInstance(ListAction.class);

	protected ActionForward doExecute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {

//		Extract attributes we will need
		HttpSession session = request.getSession();
		Principal user = request.getUserPrincipal();
		String username = user.getName();
		Form encounterForm;
		BaseEncounter encounter = null;
		Map encMap = null;
		Long formId = null;
		Long patientId = null;
		Long eventId = null;
		String constraintClause = null;
		Long constraintLong = null;
		String detailName = null;
//		BaseSessionSubject sessionPatient = null;
		Integer maxRows = 0;
		Integer offset = 0;
		Integer prevRows = 0;
		Integer nextRows = 0;
		Connection conn = null;
		String className = null;
		try {
			conn = DatabaseUtils.getZEPRSConnection(username);
			if (request.getParameter("formId") != null) {
				formId = Long.decode(request.getParameter("formId"));
			} else if (request.getAttribute("formId") != null) {
				formId = Long.decode(request.getAttribute("formId").toString());
			}
//			if (request.getParameter("className") != null) {
//				className = request.getParameter("className");
//				formId = (Long) DynaSiteObjects.getFormNameMap().get(className);
//			} else if (request.getAttribute("className") != null) {
//				className = (String) request.getAttribute("className");
//				formId = (Long) DynaSiteObjects.getFormNameMap().get(className);
//			}
			if (request.getParameter("constraintClause") != null) {
				constraintClause = request.getParameter("constraintClause");
			} else if (request.getAttribute("constraintClause") != null) {
				constraintClause = request.getAttribute("constraintClause").toString();
			}
			if (request.getParameter("constraintLong") != null) {
				constraintLong = Long.decode(request.getParameter("constraintLong"));
			} else if (request.getAttribute("constraintLong") != null) {
				constraintLong = Long.decode(request.getAttribute("constraintLong").toString());
			}
			if (request.getParameter("maxRows") != null) {
				maxRows = Integer.decode(request.getParameter("maxRows"));
			} else if (request.getAttribute("maxRows") != null) {
				maxRows = Integer.decode(request.getAttribute("maxRows").toString());
			} else {
				maxRows = 20;
			}
			if (request.getParameter("offset") != null) {
				offset = Integer.decode(request.getParameter("offset"));
			} else if (request.getAttribute("offset") != null) {
				offset = Integer.decode(request.getAttribute("offset").toString());
			}
			if (request.getParameter("prevRows") != null) {
				prevRows = Integer.decode(request.getParameter("prevRows"));
				offset = prevRows;
			} else if (request.getAttribute("prevRows") != null) {
				prevRows = Integer.decode(request.getAttribute("prevRows").toString());
				offset = prevRows;
			}
			if (request.getParameter("nextRows") != null) {
				nextRows = Integer.decode(request.getParameter("nextRows"));
			} else if (request.getAttribute("nextRows") != null) {
				nextRows = Integer.decode(request.getAttribute("nextRows").toString());
			}
//			if (mapping.getParameter() != null && !mapping.getParameter().equals("")) {
//            	String formName = mapping.getParameter();
//            	formId = (Long) DynaSiteObjects.getFormNameMap().get(formName);
//            }
			// Admin pages usually do not have a sessionPatient. This is a hack to use code that uses sessionPatient.
//			sessionPatient = new TimsSessionSubject();
//            SessionUtil.getInstance(session).setSessionPatient(sessionPatient);

			encounterForm = ((Form) DynaSiteObjects.getForms().get(new Long(formId)));
			Long formTypeId = encounterForm.getFormTypeId();
			// populate the records for this class
			List items = null;
			if (className != null && className.equals("MenuItem")) {
//				items = DynaSiteObjects.getMenuItemList(); //must be sorted
			} else {
				String classname = StringManipulation.fixClassname(encounterForm.getName());
				Class clazz = null;
				try {
					clazz = Class.forName(Constants.DYNASITE_FORMS_PACKAGE + "." + classname);
				} catch (ClassNotFoundException e1) {
					if (classname.equals("UserInfo")) {
						clazz = Class.forName("org.rti.zcore." + classname);
					}
				}

				try {
					String orderBy = "id DESC";
					switch (formTypeId.intValue()) {
					case 5:	// admin
						if (constraintLong != null) {
							/*if (formId == 161) { // stock
								// Get the item - form 131
								Class clazz = Class.forName(DynaSiteObjects.getDynasiteFormsPackage() + ".Item");
								Item stockItem = (Item) EncountersDAO.getOne(conn, constraintLong, "SQL_RETRIEVE_ONE_ADMIN131", clazz);
								detailName = stockItem.getField2153();
								request.setAttribute("detailName", detailName);
							}*/
							//String orderBy = "id DESC";
							items = EncountersDAO.getAllConstraintOrderBy(conn, formId, "SQL_RETRIEVE_ALL_ADMIN" + formId, clazz, constraintClause, constraintLong, orderBy);
						} else {
							if (formId == 161) { // stock
								items = EncountersDAO.getAll(conn, formId, "SQL_RETRIEVE_ALL_ADMIN" + formId, clazz, maxRows, offset, "id DESC");
							} else if (formId == 128) { // regimen groups
								items = EncountersDAO.getAll(conn, formId, "SQL_RETRIEVE_ALL_ADMIN_PAGER" + formId, clazz, maxRows, offset, "name ");
							} else if (formId == 129) { // regimen
								items = EncountersDAO.getAll(conn, formId, "SQL_RETRIEVE_ALL_ADMIN_PAGER" + formId, clazz, maxRows, offset, "code ");
							} else if (formId == 130) { // item groups
								items = EncountersDAO.getAll(conn, formId, "SQL_RETRIEVE_ALL_ADMIN_PAGER" + formId, clazz, maxRows, offset, "name ");
							} else {
								if (className != null && className.equals("MenuItem")) {
//									items = DynaSiteObjects.getMenuItemList(); //must be sorted
								} else {
									if (formId == 181) {
										orderBy = "regimen_id DESC";
										items = EncountersDAO.getAll(conn, formId, "SQL_RETRIEVE_ALL_ADMIN_PAGER" + formId, clazz, maxRows, offset, orderBy);
									} else {
										items = EncountersDAO.getAll(conn, formId, "SQL_RETRIEVE_ALL_ADMIN_PAGER" + formId, clazz, maxRows, offset, orderBy);
									}
								}
							}
						}
						break;
					case 8: // list - for patients
						items = EncountersDAO.getAll(conn, formId, "SQL_RETRIEVEALL" + formId, clazz, maxRows, offset);
						break;
					default:
						items = EncountersDAO.getAll(conn, formId, "SQL_RETRIEVE_ALL" + formId, clazz, maxRows, offset);
						break;
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
			}

			request.setAttribute("maxRows", maxRows);
			nextRows = offset + maxRows;
			if (items.size() < maxRows) {
				if (offset == 0) {
					request.setAttribute("noNavigationWidget", "1");
				}
			} else {
				request.setAttribute("offset", nextRows);
			}

			if (offset-maxRows >=0) {
				prevRows = offset-maxRows;
				request.setAttribute("prevRows", prevRows);
			}
			request.setAttribute("nextRows", nextRows);

			// Attach a map of encounter values that has enumerations already resolved.
			Form encForm = (Form) DynaSiteObjects.getForms().get(encounterForm.getId());
			for (int i = 0; i < items.size(); i++) {
				encounter = (EncounterData) items.get(i);
				// Form encForm = (Form) DynaSiteObjects.getForms().get(encounter.getFormId());
				encMap = PatientRecordUtils.getEncounterMap(encForm, encounter, "fieldId");

				encounter.setEncounterMap(encMap);
			}
			if (items.size() > 0) {
				request.setAttribute("chartItems", items);
				request.setAttribute("formId", encounterForm.getId());
				// loading of body onload DWRUtil.useLoadingMessage()
				request.setAttribute("dwr", 1);
			}

			 // Process the dynamic dropdown lists.
            HashMap listMap = new HashMap();
            Form inlineForm = null;
//            HashMap<Long,StockReport> balanceMap = null;
//            if (DynaSiteObjects.getStatusMap().get("balanceMap") != null) {
//				balanceMap = (HashMap<Long, StockReport>) DynaSiteObjects.getStatusMap().get("balanceMap");
//            }
//            for (Iterator iterator = encounterForm.getPageItems().iterator(); iterator.hasNext();) {
//                PageItem pageItem = (PageItem) iterator.next();
//                FormField formField = pageItem.getForm_field();
//                String identifier = formField.getIdentifier();

//                if (pageItem.getInputType().equals("dropdown") || pageItem.getInputType().equals("dropdown-add-one") || pageItem.getInputType().equals("dropdown_site")) {
//                	String dropdownConstraint = null;
//                	String pageItemDropdownConstraint = pageItem.getDropdownConstraint();
//                	if (pageItemDropdownConstraint != null && pageItemDropdownConstraint.endsWith("'siteAbbrev'")) {
//                		String siteAbbrev = SessionUtil.getInstance(session).getClientSettings().getSite().getAbbreviation();
//                		dropdownConstraint = pageItemDropdownConstraint.replace("'siteAbbrev'", "'" + siteAbbrev + "'");
//                	} else {
//                		dropdownConstraint = pageItemDropdownConstraint;
//                	}
//                	List<DropdownItem> list = WidgetUtils.getList(conn, pageItem.getDropdownTable(), pageItem.getDropdownColumn(), dropdownConstraint, pageItem.getDropdownOrderByClause(), DropdownItem.class, pageItem.getFkIdentifier());
//                	String formName = encForm.getClassname();
//                	if (formName.equals("StockControl")) {
//                		for (DropdownItem dropdownItem : list) {
//                			if (balanceMap != null) {
//                				String itemIdStr = dropdownItem.getDropdownId();
//                				Long itemId = Long.valueOf(itemIdStr);
//                				StockReport stockReport = balanceMap.get(itemId);
//    							Integer balance = 0;
//    							if (stockReport != null) {
//    								balance = stockReport.getBalanceBF();
//        							String label = dropdownItem.getDropdownValue();
//        							if (balance <= 0) {
//        								String value = dropdownItem.getDropdownValue();
//        								dropdownItem.setDropdownValue(value + " ** Out of Stock ** Bal: " + balance);
//        							} else {
//            							dropdownItem.setDropdownValue(label + " Bal: " + balance);
//        							}
//    							}
//                			}
//						}
//                	}
//                	listMap.put(pageItem.getId(), list);
//                	if (pageItem.getInputType().equals("dropdown-add-one")) {
//                		String classNameString = StringManipulation.fixClassname(pageItem.getDropdownTable());
//                        Long inlineFormId = (Long) DynaSiteObjects.getFormNameMap().get(classNameString);
//                        inlineForm = ((Form) DynaSiteObjects.getForms().get(new Long(inlineFormId)));
//                        // Create a list of fieldnames for inline forms.
//                        ArrayList<String> inlineFields = new ArrayList<String>();
//                        for (Iterator iterator2 = inlineForm.getPageItems().iterator(); iterator2.hasNext();) {
//                        	PageItem pageItem2 = (PageItem) iterator2.next();
//                            if (pageItem2.getForm_field().isEnabled() == true && !pageItem2.getForm_field().getType().equals("Display")) {
//                            	inlineFields.add(pageItem2.getForm_field().getIdentifier());
//                            }
//                        }
//                        request.setAttribute("inlineForm_"+identifier, inlineForm);
//                        request.setAttribute("inlineFields_"+identifier, inlineFields);
//                        // loading of body onload DWRUtil.useLoadingMessage()
//                        request.setAttribute("dwr", 1);
//                	}
//                }
//            }
            request.setAttribute("listMap", listMap);

            request.setAttribute("encounterForm", encounterForm);

            List sites = DynaSiteObjects.getClinics();
            request.setAttribute("sites", sites);

//            if (Constants.STOCK_PROBLEMS_ENABLED != null && Constants.STOCK_PROBLEMS_ENABLED.equals("true")) {
//            	//List<Task> stockAlertList = PatientRecordUtils.getStockAlerts();
//            	List<Task> stockAlertList = null;
//                if (DynaSiteObjects.getStatusMap().get("stockAlertList") != null) {
//                	stockAlertList = (List<Task>) DynaSiteObjects.getStatusMap().get("stockAlertList");
//            	}
//            	request.setAttribute("activeProblems", stockAlertList);
//            }

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return mapping.findForward("success");
	}

}
