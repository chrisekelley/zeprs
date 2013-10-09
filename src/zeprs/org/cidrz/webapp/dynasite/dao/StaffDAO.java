/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.dao;

import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Staff;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 22, 2005
 *         Time: 12:20:17 PM
 */
public class StaffDAO {

    public static Object getOne(Connection conn, String username) throws SQLException, ServletException, ObjectNotFoundException {
        Object result = null;
        ArrayList values = new ArrayList();
        String sql = "select u.nickname AS id, u.firstname AS firstName, u.lastname AS lastName " +
                "from userdata.address u " +
                "where u.nickname=?";
        values.add(username);
        result = DatabaseUtils.getBean(conn, Staff.class, sql, values);
        return result;

    }
}

