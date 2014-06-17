/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.rti.zcore.dynasite.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.rti.zcore.FormDomain;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Nov 26, 2008
 *         Time: 6:05:00 PM
 */
public class FormDomainDAO {

    /**
     * Returns one record
     *
     * @param conn
     * @param id
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static FormDomain getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        FormDomain item = null;
        String sql;
        ArrayList values;
        sql = "select id, name, global_identifier_name AS globalIdentifierName from ADMIN.form_domain where id=? ";
        values = new ArrayList();
        values.add(id);
        item = (FormDomain) DatabaseUtils.getBean(conn, FormDomain.class, sql, values);
        return item;
    }

    /**
     * Fetches a formType by UUID
     * @param conn
     * @param uuid
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static FormDomain getOneByUUID(Connection conn, String uuid) throws SQLException, ServletException, ObjectNotFoundException {
    	FormDomain item = null;
    	String sql;
    	ArrayList values;
    	sql = "select id, name, global_identifier_name AS globalIdentifierName from ADMIN.form_domain where GLOBAL_IDENTIFIER_NAME=? ";
    	values = new ArrayList();
    	values.add(uuid);
    	item = (FormDomain) DatabaseUtils.getBean(conn, FormDomain.class, sql, values);
    	return item;
    }

    /**
     * @param conn
     * @return List of objects
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAll(Connection conn) throws SQLException, ServletException {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, name, global_identifier_name from ADMIN.form_domain ORDER BY name";
        list = DatabaseUtils.getList(conn, FormDomain.class, sql, values);
        return list;
    }


    /**
     * Saves name and uuid of a new formType.
     * @param conn
     * @param formType
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Long save(Connection conn, FormDomain formType) throws SQLException, ServletException {

        String sql = "INSERT INTO admin.form_domain " +
                "(name, global_identifier_name) " +
                "VALUES (?,?)";
        ArrayList values = new ArrayList();
        values.add(formType.getName());
        values.add(formType.getGlobalIdentifierName());
        Long id = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return id;
    }

}
