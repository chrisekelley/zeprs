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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.logic.SystemStateManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Apr 23, 2004
 * Time: 3:45:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class SystemStateAction extends BaseAction {
    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dynaForm = (DynaActionForm) form;
        if (dynaForm.get("state") != null && !dynaForm.get("state").equals("")) {
            try {
                SystemStateManager.setCurrentState(Integer.parseInt((String) dynaForm.get("state")), (String) dynaForm.get("message"));
                request.setAttribute("message", "State changed successfully.");
                return mapping.findForward("formAdmin");
            } catch (Exception ex) {
                request.setAttribute("message", ex.getMessage());
            }
        } else {
            dynaForm.set("state", Integer.toString(SystemStateManager.getCurrentState()));
            return mapping.findForward(SUCCESS_FORWARD);
        }
        return mapping.findForward(SUCCESS_FORWARD);
    }

}
