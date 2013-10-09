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

import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.EditObjectAction;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Aug 6, 2004
 * Time: 3:03:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditFieldAction extends EditObjectAction {

    protected void addRequestItems(HttpServletRequest request) {
        request.setAttribute("form_id", request.getParameter("formId"));
        Form encounterForm = null;
        try {
            Connection conn = DatabaseUtils.getAdminConnection();
            encounterForm = FormDisplayDAO.getFormGraph(conn, Long.valueOf(request.getParameter("formId")));
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("encounterForm", encounterForm);
    }

}
