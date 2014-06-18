package org.rti.zcore.utils.struts;

import org.apache.struts.action.ActionForward;
import org.cidrz.webapp.dynasite.valueobject.Form;


public class StrutsHelperImpl implements StrutsHelper {
	  /**
    *
    * @param actionType
    * @param patientId
    * @param encounterForm
    * @return
    * @throws NumberFormatException
    */
	public ActionForward getActionForward(String actionType, Long patientId, Form encounterForm) throws NumberFormatException {
		ActionForward forwardForm = null;
		String forwardString = null;
		Long formTypeId = encounterForm.getFormTypeId();
		Long formId = encounterForm.getId();
//		String formName = DynaSiteObjects.getFormIdClassNameMap().get(formId);
		if (actionType.equals("foo")) {
			switch (formTypeId.intValue()) {
			case 5: 	// admin
				forwardString = "/admin/records/list.do?formId=" + formId;
				forwardForm = new ActionForward(forwardString);
//				forwardForm.setRedirect(true);
				break;
			case 7: // charts
				forwardString = "/chart.do?id=" + formId;
				forwardForm = new ActionForward(forwardString);
				forwardForm.setRedirect(true);
				break;
			default:
				//return mapping.findForward("patientHome");
//				if (formName.equals("Appointment")) {
//					forwardString = "/Appointment/new.do?patientId=" + patientId;
//					forwardForm = new ActionForward(forwardString);
//					forwardForm.setRedirect(true);
//				} else {
					forwardString = "/patientHome.do?patientId=" + patientId;
					forwardForm = new ActionForward(forwardString);
					forwardForm.setRedirect(true);
//				}
				break;
			}
		}
		return forwardForm;
	}
}
