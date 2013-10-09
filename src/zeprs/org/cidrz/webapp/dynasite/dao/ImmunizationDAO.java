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

import org.cidrz.project.zeprs.valueobject.Immunization;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 4, 2005
 *         Time: 9:22:16 AM
 */
public class ImmunizationDAO {

    /**
     * @return List of objects
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     * @param conn
     */
    public static List getAll(Connection conn) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, name from immunization";
        list = DatabaseUtils.getList(conn, Immunization.class, sql, values);
        return list;
    }

    /**
     * Returns one record
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static Immunization getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        Immunization item = null;
        String sql;
        ArrayList values;
        sql = "select id, name from immunization where id=? ";
        values = new ArrayList();
        values.add(id);
        item = (Immunization) DatabaseUtils.getBean(conn, Immunization.class, sql, values);
        return item;
    }
}
