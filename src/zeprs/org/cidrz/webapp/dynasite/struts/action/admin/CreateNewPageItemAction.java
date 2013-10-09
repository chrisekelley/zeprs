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

import org.cidrz.webapp.dynasite.struts.action.generic.CreateNewObjectAction;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.dao.FormDAO;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Aug 12, 2004
 * Time: 4:00:01 PM
 */
public class CreateNewPageItemAction extends CreateNewObjectAction {

    protected void addRequestItems(HttpServletRequest request) {
        request.setAttribute("form_id", request.getParameter("formId"));
        Form encounterForm = null;
        //encounterForm = (Form) PersistenceManagerFactory.getInstance(Form.class).getOneForm(Long.valueOf(request.getParameter("formId")));
        Long formId = Long.valueOf(request.getParameter("formId"));
        encounterForm = (Form) DynaSiteObjects.getForms().get(formId);
        if (encounterForm == null) {
            Connection conn = DatabaseUtils.getAdminConnection();
            try {
                encounterForm = FormDisplayDAO.getFormGraph(conn, formId);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("encounterForm", encounterForm);

    }
}
