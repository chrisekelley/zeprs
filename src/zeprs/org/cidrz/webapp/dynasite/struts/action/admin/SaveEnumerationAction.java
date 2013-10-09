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
import org.cidrz.webapp.dynasite.dao.PageItemDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.SaveObjectAction;
import org.cidrz.webapp.dynasite.utils.struts.ParameterActionForward;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;

public class SaveEnumerationAction extends SaveObjectAction {
    protected ActionForward handleSuccessForward(ActionMapping mapping, HttpServletRequest request) {
        Connection conn = DatabaseUtils.getAdminConnection();

        try {
            request.setAttribute(SUBJECT_KEY, PageItemDAO.getPageItemGraph(conn, Long.decode(request.getParameter("pageItem"))));

        } catch (Exception e) {
            log.error("failed to re-fetch the parent Field of this FieldEnumeration. PageItem id = " + request.getParameter("pageItem"));
        }
        ParameterActionForward fwd = new ParameterActionForward(mapping.findForward(SUCCESS_FORWARD));
        request.setAttribute("pageItem", request.getParameter("pageItem"));
        request.setAttribute("formId", request.getParameter("formId"));
        //fwd.addParameter("id", enum.getField().getId());
        return fwd;
    }
}
