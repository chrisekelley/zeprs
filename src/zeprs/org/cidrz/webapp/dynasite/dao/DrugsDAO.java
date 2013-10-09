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

import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Drugs;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 4, 2005
 *         Time: 8:26:25 AM
 */
public class DrugsDAO {

    public static List getAll(Connection conn) throws SQLException, ServletException {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, name, teratogenic, inactive from drugs WHERE (inactive IS NULL OR inactive !=1)  ORDER BY name;";
        list = DatabaseUtils.getList(conn, Drugs.class, sql, values);
        return list;
    }

    /**
     * Fetch a single drug.
     * unused
     * @param id
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Drugs getOne(Long id) throws SQLException, ServletException, ObjectNotFoundException {
        Drugs item = null;
        String sql;
        ArrayList values;
        sql = "select id, name, teratogenic from drugs where id=? ";
        values = new ArrayList();
        values.add(id);
        item = (Drugs) DatabaseUtils.getBean(Drugs.class, sql, values);
        return item;
    }
}
