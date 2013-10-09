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

import org.cidrz.project.zeprs.valueobject.DrugRegimen;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Formerly used for DrugRegimen - not currently in use.
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 4, 2005
 *         Time: 9:22:16 AM
 */
public class DrugRegimenDAO {

    /**
     * Returns one record
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static DrugRegimen getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        DrugRegimen item = null;
        String sql;
        ArrayList values;
        sql = "select id, name, regimen_type AS regimenType from drug_regimen_list where id=? ";
        values = new ArrayList();
        values.add(id);
        item = (DrugRegimen) DatabaseUtils.getBean(conn, DrugRegimen.class, sql, values);
        return item;
    }

    /**
     * @return List of objects
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAll(Connection conn) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, name, regimen_type AS regimenType from drug_regimen_list";
        list = DatabaseUtils.getList(conn, DrugRegimen.class, sql, values);
        return list;
    }

    /**
     * List of objects
     *
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAll(String order) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, name, regimen_type AS regimenType from drug_regimen_list order by ?";
        values.add(order);
        list = DatabaseUtils.getList(DrugRegimen.class, sql, values);
        return list;
    }
}