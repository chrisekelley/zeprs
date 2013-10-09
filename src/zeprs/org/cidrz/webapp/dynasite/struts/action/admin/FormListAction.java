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

import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Dave Peckham
 * Date: Apr 23, 2004
 * Time: 3:45:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormListAction extends BaseAction {
    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*String systemstate = request.getParameter("systemstate");
        if (systemstate != null && new Integer(systemstate) ==1) {
            SystemStateManager.setCurrentState(2, "");
        }*/
        // init this map
        Map fieldFormMap = DynaSiteObjects.getFieldToForms(); 
        return mapping.findForward(SUCCESS_FORWARD);
    }

}
