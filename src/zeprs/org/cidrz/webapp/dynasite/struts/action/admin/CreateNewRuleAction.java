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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.dao.PageItemDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.PersistentObjectAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: chrisk
 * Date: 11/16/2004
 * Time: 5:12:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreateNewRuleAction extends PersistentObjectAction {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(CreateNewRuleAction.class);

    protected final ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getParameter("pageItem") != null) {
            addRequestItems(request);
        } else {
            return mapping.findForward("admin/formList");
        }

        return mapping.findForward(SUCCESS_FORWARD);
    }

    protected void addRequestItems(HttpServletRequest request) {
        String pageItemId = null;
        if (request.getParameter("pageItem") != null) {
            Connection adminConn = null;
            try {
                adminConn = DatabaseUtils.getAdminConnection();
                pageItemId = request.getParameter("pageItem").toString();
                PageItem pageItem = PageItemDAO.getPageItemGraph(adminConn, new Long(pageItemId));
                // (PageItem) DynaSiteObjects.getPageItems().get(new Long(pageItemId));
                FormField formField = pageItem.getForm_field();
                Long id = formField.getId();
                request.setAttribute("providerId", id);
                request.setAttribute("providerClass", "org.cidrz.webapp.dynasite.valueobject.FormField");
                request.setAttribute("formId", pageItem.getFormId());
                request.setAttribute("pageItem", pageItem.getId());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                /*if (conn != null && !conn.isClosed()) {
                    conn.close();
                }*/
                try {
                    if (adminConn != null && !adminConn.isClosed()) {
                        try {
                            adminConn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        // http://localhost:8080/zeprs/admin/rule/new.do;jsessionid=00797FB30BBB59FDB0E07B7A7182A217?providerId=225
        // &providerClass=org.cidrz.webapp.dynasite.valueobject.FormField&formId=80&pageItem=3241

    }

}
