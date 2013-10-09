package org.cidrz.webapp.dynasite.struts.action.admin;

import java.security.Principal;
import java.sql.Connection;
import java.util.Map;

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
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.PatientStatusDAO;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.struts.action.generic.BasePatientAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;
import org.cidrz.webapp.dynasite.valueobject.Site;

public final class ResetFlowAction extends BasePatientAction {
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

		Long patientId = null;
		Long pregnancyId = null;
		SessionPatient sessionPatient = null;

		sessionPatient = SessionUtil.getInstance(session).getSessionPatient();

		if (sessionPatient != null) {
			patientId = sessionPatient.getId();
			pregnancyId = sessionPatient.getCurrentPregnancyId();
		try {
			conn = DatabaseUtils.getSpecialRootConnection("zeprsAdmin");
			// Find the previous encounter
			EncounterData encounterData = EncountersDAO.getPreviousAntenatalEncounter(conn, patientId, pregnancyId);
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
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("exception", e);
			return mapping.findForward("error");
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		} else {
			request.setAttribute("message", "You must be viewing a patient record in order to perform this task.");
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}
}
