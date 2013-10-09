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
import org.cidrz.webapp.dynasite.dao.FormTypeDAO;
import org.cidrz.webapp.dynasite.dao.FormFieldDAO;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.PersistentObjectAction;
import org.cidrz.webapp.dynasite.utils.BeanPopulator;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Identifiable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 17, 2004
 * Time: 9:49:43 AM
 * To change this template use File | Settings | File Templates.
 */

public class EditFormAction extends PersistentObjectAction {
    protected final ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Class clazz = getMappedClass(mapping);
        //does the user want to edit an existing object?
        Identifiable subject = null;
        Connection conn = DatabaseUtils.getAdminConnection();
        if (request.getAttribute(SUBJECT_KEY) != null) {
            subject = (Identifiable) request.getAttribute(SUBJECT_KEY);
        } else if (request.getParameter(ID_KEY) != null && !request.getParameter(ID_KEY).equals("")) {
            Long formId = Long.valueOf(request.getParameter(ID_KEY));
            subject = FormDisplayDAO.getFormGraph(conn, formId);
        }
        // last chance!
        if (subject == null) {
        	Long formId = Long.valueOf(request.getParameter("formId"));
        	subject = FormDisplayDAO.getFormGraph(conn, formId);
        }
        BeanPopulator.populate(subject, (DynaActionForm) form);
        request.setAttribute(SUBJECT_KEY, subject);
        addRequestItems(request);
        conn.close();
        return mapping.findForward(SUCCESS_FORWARD);
    }

    protected void addRequestItems(HttpServletRequest request) {
        List flows = null;
        flows = DynaSiteObjects.getFlows();
        request.setAttribute("flows", flows);
        List formTypes = null;
        Connection conn = DatabaseUtils.getAdminConnection();
        try {
            formTypes = FormTypeDAO.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        request.setAttribute("formTypes", formTypes);
        List shared_fields = null;
        try {
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


