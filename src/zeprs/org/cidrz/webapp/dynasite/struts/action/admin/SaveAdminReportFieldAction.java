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

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.struts.action.generic.SaveObjectAction;
import org.cidrz.webapp.dynasite.utils.struts.ParameterActionForward;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;

import javax.servlet.http.HttpServletRequest;

public class SaveAdminReportFieldAction extends SaveObjectAction {


    protected ActionForward handleSuccessForward(ActionMapping mapping, HttpServletRequest request) throws PersistenceException, ObjectNotFoundException {
        ParameterActionForward fwd = new ParameterActionForward(mapping.findForward(SUCCESS_FORWARD));
        request.setAttribute("form_id", request.getParameter("formId"));
        return fwd;
    }
}


