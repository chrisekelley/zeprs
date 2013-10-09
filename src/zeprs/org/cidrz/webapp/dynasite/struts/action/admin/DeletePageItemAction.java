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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.dao.PageItemDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.PersistentObjectAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.struts.ParameterActionForward;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.PageItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;

public class DeletePageItemAction extends PersistentObjectAction {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(DeletePageItemAction.class);

    protected final ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // HttpSession session = request.getSession();
        Principal user = request.getUserPrincipal();
        String userName = user.getName();
        Long id;
        Long formId;
        ParameterActionForward fwd = null;
        if (request.getParameter("id") != null) {
            id = Long.valueOf(request.getParameter("id"));
            Connection adminConn = null;
            Connection conn = null;
            try {
                adminConn = DatabaseUtils.getSpecialRootConnection(userName);
                PageItem pageItem = PageItemDAO.getOne(adminConn, id);
                formId = pageItem.getFormId();
                PageItemDAO.delete(adminConn, id);
                conn = DatabaseUtils.getAdminConnection();
                Form encounterForm = FormDisplayDAO.getFormGraph(conn, formId);
                DynaSiteObjects.getForms().remove(formId);
                DynaSiteObjects.getForms().put(formId, encounterForm);
                request.setAttribute(SUBJECT_KEY, encounterForm);
                //fwd = new ParameterActionForward(mapping.findForward(SUCCESS_FORWARD));
                //request.setAttribute("form_id", request.getParameter("formId"));

                ActionRedirect redirect = new ActionRedirect(mapping.findForward(SUCCESS_FORWARD));
                redirect.addParameter("id",request.getParameter("form_id"));
                redirect.addParameter("formId",formId);

                return redirect;

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (adminConn != null && !adminConn.isClosed()) {
                    adminConn.close();
                }
                if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            }
        }

        return fwd;
    }
}
