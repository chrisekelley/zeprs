/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action.admin;

import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.report.PatientStatusReport;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.OutcomeDAO;
import org.cidrz.webapp.dynasite.dao.PatientStatusDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.rules.impl.OutcomeImpl;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Site;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: May 16, 2005
 * Time: 4:55:49 PM
 * To change this template use File | Settings | File Templates.
 */
public final class DeleteEncounterAction extends BaseAction {

	/**
	 * Commons Logging instance.
	 */
	private static Log log = LogFactory.getFactory().getInstance(DeleteEncounterAction.class);

	protected ActionForward doExecute(ActionMapping mapping,
			ActionForm actionForm,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {

		HttpSession session = request.getSession();
		Site site = SessionUtil.getInstance(session).getClientSettings().getSite();
		Principal user = request.getUserPrincipal();
		String username = user.getName();
		Connection conn = null;

		Long encounterId = null;
		Long formId = null;
		Integer deps = null;
		if (request.getParameter("encounterId") != null) {
			encounterId = Long.valueOf(request.getParameter("encounterId"));
		}
		if (request.getParameter("formId") != null) {
			formId = Long.valueOf(request.getParameter("formId"));
		}
		if (request.getParameter("deps") != null) {
			deps = Integer.valueOf(request.getParameter("deps"));
		}

		try {
			conn = DatabaseUtils.getSpecialRootConnection("zeprsAdmin");
			if (encounterId != null && formId != null) {
				EncounterData encounter = null;
				try {
					encounter = (EncounterData) EncountersDAO.getOneById(conn, encounterId);
					if (encounter != null) {
						Long patientId = encounter.getPatientId();
						Long pregnancyId = encounter.getPregnancyId();
						PatientStatusReport psr = PatientStatusDAO.getOne(conn, patientId);
						Long currentFlowEncounterId = psr.getCurrentFlowEncounterId();
						if (formId.longValue() == 1) {
							String message = "You may not delete the patient registration record. " +
							"Delete the whole patient record instead by clicking the \"Delete Patient\" link\n" +
							"on the Demographics page.";
							request.setAttribute("exception", message);
							return mapping.findForward("error");
						}
						List outcomes = OutcomeDAO.getAllforEncounter(conn, encounterId);

						if (outcomes.size() > 0) {
							if (deps != null && deps.intValue() == 1) {
								for (int i = 0; i < outcomes.size(); i++) {
									OutcomeImpl outcome = (OutcomeImpl) outcomes.get(i);
									OutcomeDAO.deleteOne(conn, outcome.getId());
								}
							} else {
								String url = "/zeprs/admin/deleteEncounter.do;jsessionid=" + session.getId() +
								"?encounterId=" + encounterId + "&formId=" + formId + "&deps=1";
								String message = "<p>This record has system-generated problems.  " +
								"Are you sure you want to delete it?.</p>" +
								"<p><a href=\"" + url + "\">Delete</a></p>";
								request.setAttribute("exception", message);
								return mapping.findForward("error");
							}
						}

						// Test to see if you are deleting the most recent encounter.
						if (encounterId.longValue() == currentFlowEncounterId.longValue()) {
							// Find the previous encounter
							EncounterData encounterData = EncountersDAO.getPreviousEncounter(conn, patientId, pregnancyId, encounterId);
							Long prevEncId = encounterData.getId();
							if (prevEncId != null) {
								// re-assign values in patient_status
								Long currentFlowId = encounterData.getFlowId();
								Map queries = QueryLoader.instance().load("/" + Constants.SQL_PATIENT_PROPERTIES);
								String sqlUpdateStatus = (String) queries.get("SQL_MODIFY_STATUS");
								EncounterData vo = new EncounterData();    // dummy EncounterData is OK.
								vo.setUuid(encounterData.getUuid());
								PatientStatusDAO.updatePatientStatus(conn, vo, currentFlowId, prevEncId, username, site.getId(), patientId, sqlUpdateStatus);
							} else {
								String message = "Unable to delete this record - please contact the system administrator. ";
								request.setAttribute("exception", message);
								return mapping.findForward("error");
							}
						}
						EncounterData vo = new EncounterData();    // dummy EncounterData is OK.
						vo.setPatientId(patientId);
						vo.setPregnancyId(pregnancyId);
						PatientRecordUtils.deleteEncounter(conn, formId, encounterId, username, site, vo);
						if (formId == 79) {
							return mapping.findForward("patientHome");
						}
						if (formId == 87) {
							return mapping.findForward("labs");
						}
						String forward = (String) DynaSiteObjects.getFormNames().get("form" + formId);
						if (forward != null) {
							return mapping.findForward(forward);
						} else {
							return mapping.findForward("home");
						}
					}
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ObjectNotFoundException e) {
					// already deleted or missing - simply send back to home.
					return mapping.findForward("home");
				}
			} else {
				// already deleted or missing - simply send back to home.
				return mapping.findForward("home");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("exception", e);
			return mapping.findForward("error");
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return mapping.findForward("success");
	}

}
