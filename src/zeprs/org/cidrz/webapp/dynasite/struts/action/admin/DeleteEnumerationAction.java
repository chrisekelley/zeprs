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
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.struts.action.generic.DeleteObjectAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.struts.ParameterActionForward;
import org.cidrz.webapp.dynasite.valueobject.FieldEnumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteEnumerationAction extends DeleteObjectAction {
    protected ActionForward handleSuccessForward(ActionMapping mapping, HttpServletRequest request) {
        ParameterActionForward fwd = null;
        Connection conn = null;
        try {
            conn = DatabaseUtils.getAdminConnection();
            FieldEnumeration fieldEnum = (FieldEnumeration) request.getAttribute(SUBJECT_KEY);
            try {
                request.setAttribute(SUBJECT_KEY, PageItemDAO.getOne(conn, Long.decode(request.getParameter("pageItem"))));
            } catch (SQLException e) {
                log.error("failed to re-fetch the parent Field of this FieldEnumeration. FieldEnumeration id = " + fieldEnum.getId());
            } catch (ObjectNotFoundException e) {
                log.error("failed to re-fetch the parent Field of this FieldEnumeration. FieldEnumeration id = " + fieldEnum.getId());
            } catch (NumberFormatException e) {
                log.error("failed to re-fetch the parent Field of this FieldEnumeration. FieldEnumeration id = " + fieldEnum.getId());
            }
            fwd = new ParameterActionForward(mapping.findForward(SUCCESS_FORWARD));
            request.setAttribute("pageItem", request.getParameter("pageItem"));
            request.setAttribute("formId", request.getParameter("formId"));
        } catch (ServletException e) {
            log.error(e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return fwd;
    }
}
