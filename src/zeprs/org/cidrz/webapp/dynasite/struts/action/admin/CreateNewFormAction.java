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

import org.cidrz.webapp.dynasite.dao.FlowDAO;
import org.cidrz.webapp.dynasite.dao.FormTypeDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.CreateNewObjectAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: chrisk
 * Date: 11/16/2004
 * Time: 5:12:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreateNewFormAction extends CreateNewObjectAction {

    protected void addRequestItems(HttpServletRequest request) {
        Connection conn = DatabaseUtils.getAdminConnection();
        List flows = null;
        try {
            flows = FlowDAO.getAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        request.setAttribute("flows", flows);
        List formTypes = null;
        try {
            // formTypes = PersistenceManagerFactory.getInstance(FormType.class).getAll();
            formTypes = FormTypeDAO.getAll(conn);
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
        request.setAttribute("formTypes", formTypes);
    }

}
