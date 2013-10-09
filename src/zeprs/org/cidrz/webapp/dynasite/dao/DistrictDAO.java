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
import org.cidrz.webapp.dynasite.valueobject.District;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 4, 2005
 *         Time: 9:22:16 AM
 */
public class DistrictDAO {

    /**
     * Returns one record
     * unused
     * @param id
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static District getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        District item = null;
        String sql;
        ArrayList values;
        sql = "select id, district_id AS districtId, district_name AS districtName from district where id=? ";
        values = new ArrayList();
        values.add(id);
        item = (District) DatabaseUtils.getBean(conn, District.class, sql, values);
        return item;
    }

    /**
     * @return List of objects
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAll() throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, district_id AS districtId, district_name AS districtName from district";
        list = DatabaseUtils.getList(District.class, sql, values);
        return list;
    }

    /**
     * List of objects
     *
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAll(String order) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, district_id AS districtId, district_name AS districtName from district order by ?";
        values.add(order);
        list = DatabaseUtils.getList(District.class, sql, values);
        return list;
    }

    /**
     * List of objects
     *
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAll(Connection conn, String order) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, district_id AS districtId, district_name AS districtName from district order by ?";
        values.add(order);
        list = DatabaseUtils.getList(conn, District.class, sql, values);
        return list;
    }

}
