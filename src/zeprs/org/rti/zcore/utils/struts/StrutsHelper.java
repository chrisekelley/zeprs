package org.rti.zcore.utils.struts;

import org.apache.struts.action.ActionForward;
import org.cidrz.webapp.dynasite.valueobject.Form;

public interface StrutsHelper {
	public ActionForward getActionForward(String actionType, Long patientId, Form encounterForm) throws NumberFormatException;

}
