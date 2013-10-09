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
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.SaveObjectAction;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.utils.struts.ParameterActionForward;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;


public class SaveFormFieldAction extends SaveObjectAction {
    protected ActionForward handleSuccessForward(ActionMapping mapping, HttpServletRequest request) {
        FormField field = (FormField) request.getAttribute(SUBJECT_KEY);
        try {
            Connection conn = DatabaseUtils.getAdminConnection();
            request.setAttribute(SUBJECT_KEY, FormDisplayDAO.getFormGraph(conn, Long.decode(request.getParameter("formId"))));
            conn.close();
        } catch (Exception e) {
            log.error("failed to re-fetch the parent Form -  id = " + request.getParameter("formId"));
        }
        ParameterActionForward fwd = new ParameterActionForward(mapping.findForward(SUCCESS_FORWARD));
        request.setAttribute("form_id", request.getParameter("formId"));
        request.setAttribute("id", request.getParameter("formId"));
        return fwd;
    }
}

