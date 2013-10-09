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
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.dao.FormFieldDAO;
import org.cidrz.webapp.dynasite.dao.PageItemDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.struts.action.generic.PersistentObjectAction;
import org.cidrz.webapp.dynasite.utils.BeanPopulator;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;
import org.cidrz.webapp.dynasite.valueobject.PageItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Aug 23, 2004
 * Time: 3:03:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditPageItemAction extends PersistentObjectAction {

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Class clazz = getMappedClass(mapping);
        //does the user want to edit an existing object?
        Identifiable subject = null;
        Connection adminConn = null;
        try {
            adminConn = DatabaseUtils.getAdminConnection();
            if (request.getAttribute(SUBJECT_KEY) != null) {
                subject = (Identifiable) request.getAttribute(SUBJECT_KEY);
            } else if (request.getParameter(ID_KEY) != null && !request.getParameter(ID_KEY).equals("")) {
                subject = PageItemDAO.getPageItemGraph(adminConn, Long.valueOf(request.getParameter(ID_KEY)));
                //  PersistenceManagerFactory.getInstance(clazz).getOne(Long.valueOf(request.getParameter(ID_KEY)));
            }
            BeanPopulator.populate(subject, (DynaActionForm) form);
            request.setAttribute(SUBJECT_KEY, subject);
            addRequestItems(request, subject);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*if (conn != null && !conn.isClosed()) {
                conn.close();
            }*/
            if (adminConn != null && !adminConn.isClosed()) {
                adminConn.close();
            }
        }

        return mapping.findForward(SUCCESS_FORWARD);
    }

    protected void addRequestItems(HttpServletRequest request, Identifiable subject) {
        Connection conn = DatabaseUtils.getAdminConnection();
        request.setAttribute("form_id", request.getParameter("formId"));
        Form encounterForm = null;
        PageItem pageItem = (PageItem) subject;
        FormField form_field = pageItem.getForm_field();

        try {

            encounterForm = FormDisplayDAO.getFormGraph(conn, new Long(request.getParameter("formId")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

        request.setAttribute("encounterForm", encounterForm);

        /*List reports = null;
        try {
            reports = LiveReportFieldDAO.getAllForField(conn, form_field.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        request.setAttribute("reports", reports);

        List allReports = null;
        try {
            allReports = LiveReportDAO.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        request.setAttribute("allReports", allReports);
*/
        List shared_fields = null;
        try {
            // shared_fields = PersistenceManagerFactory.getInstance(FormField.class).getAllSharedfields();
            shared_fields = FormFieldDAO.getAllSharedFields(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("shared_fields", shared_fields);
    }

}
