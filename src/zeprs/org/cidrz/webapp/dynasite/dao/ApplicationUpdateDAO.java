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
import org.cidrz.webapp.dynasite.struts.action.admin.PropogateUpdateAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.ApplicationUpdate;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Apr 29, 2006
 *         Time: 5:23:23 PM
 */
public class ApplicationUpdateDAO {

    public static Object getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        Object result;
        ArrayList values;
        values = new ArrayList();
        String sql = "SELECT * FROM admin.appupdate WHERE id=?;";
        values.add(id);
        result = DatabaseUtils.getBean(conn, ApplicationUpdate.class, sql, values);
        return result;
    }

    /**
     * Fetch list of update operations from admin db
     * Used for propgating updates to remote instances of ZEPRS
     * @see PropogateUpdateAction and UpdateDatabase
     *
     * @param conn
     * @param db
     * @return list of archive operations
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAll(Connection conn, String db) throws SQLException, ServletException {
        List results;
        ArrayList values = new ArrayList();
        String sql = "SELECT id,updateid, dateposted, dateinstalled, type, job, message FROM " + db + ".appupdate " +
                "ORDER BY dateposted ASC";
        results = DatabaseUtils.getList(conn, ApplicationUpdate.class, sql, values);
        return results;
    }

    /**
     * Fetch list of update operations from admin db
     * @param conn
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAll(Connection conn) throws SQLException, ServletException {
        List results;
        ArrayList values = new ArrayList();
        String sql = "SELECT aa.id,aa.updateid, aa.dateposted, za.dateinstalled, aa.type, aa.job, aa.message\n" +
                "FROM admin.appupdate aa\n" +
                "LEFT JOIN zeprs.appupdate za ON za.updateid = aa.id\n" +
                "ORDER BY dateposted DESC";
        results = DatabaseUtils.getList(conn, ApplicationUpdate.class, sql, values);
        return results;
    }

    /**
     * Fetch list of update operations on local instance
     *
     * @param conn
     * @return list of archive operations
     * @throws SQLException
     * @throws ServletException
     */
    public static List getNew(Connection conn) throws SQLException, ServletException {
        List results;
        ArrayList values = new ArrayList();
        String sql = "SELECT * FROM zeprs.appupdate WHERE dateinstalled IS NULL ORDER BY dateposted DESC";
        results = DatabaseUtils.getList(conn, ApplicationUpdate.class, sql, values);
        return results;
    }

    /**
     * Saves new appupdate instance in admin database
     *
     * @param conn
     * @param db
     * @param updateid
     * @param dateInstalled
     * @param type
     * @param job
     * @return id of new record
     * @throws SQLException
     */
    public static Object save(Connection conn, String db, Long updateid, Timestamp dateposted, Timestamp dateInstalled, String type, String job) throws SQLException {
        Object result = 0;
        ArrayList values;
        values = new ArrayList();
        String sql = "INSERT into " + db + ".appupdate " +
                "SET updateid=?, dateposted=?, dateinstalled=?, type=?, job=?";
        values.add(updateid);
        values.add(dateposted);
        values.add(dateInstalled);
        values.add(type);
        values.add(job);
        result = DatabaseUtils.create(conn, sql, values.toArray());
        return result;
    }

    /**
     * Delete appupdate record
     *
     * @param conn
     * @throws SQLException
     */
    public static void delete(Connection conn, Long id) throws SQLException {
        Object result = 0;
        ArrayList values;
        values = new ArrayList();
        String sql = "DELETE FROM admin.appupdate WHERE id=?;";
        values.add(id);
        result = DatabaseUtils.create(conn, sql, values.toArray());
    }

    /**
     * Set dateInstalled after running sql in ApplicationUpdate
     * @param conn
     * @param dateInstalled
     * @param id
     * @throws SQLException
     */
    public static void setInstalled(Connection conn, Date dateInstalled, Long id) throws SQLException {
        ArrayList values;
        values = new ArrayList();
        String sql = "UPDATE admin.appupdate SET dateinstalled=? WHERE id=?;";
        values.add(dateInstalled);
        values.add(id);
        DatabaseUtils.create(conn, sql, values.toArray());
    }
}
