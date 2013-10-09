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
import org.cidrz.webapp.dynasite.valueobject.ArchiveLog;
import org.cidrz.webapp.dynasite.valueobject.UpdateLog;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 16, 2006
 *         Time: 6:19:05 PM
 */
public class UpdateLogDAO {

    public static Object getOne(Connection conn) throws SQLException, ServletException, ObjectNotFoundException {
        Object result;
        ArrayList values;
        values = new ArrayList();
        String sql = "SELECT * FROM updatelog ORDER BY id DESC LIMIT 1;";
        result = DatabaseUtils.getBean(conn, UpdateLog.class, sql, values);
        return result;
    }

        public static Object getLastUpdate(Connection conn, String site) throws SQLException, ServletException, ObjectNotFoundException {
        Object result;
        ArrayList values;
        values = new ArrayList();
        String sql = "SELECT * FROM updatelog WHERE site = ? ORDER BY builddate DESC LIMIT 1;";
        values.add(site);
        result = DatabaseUtils.getBean(conn, UpdateLog.class, sql, values);
        return result;
    }

    /**
     * Fetch list of update operations
     * @param conn
     * @return  list of archive operations
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAll(Connection conn) throws SQLException, ServletException {
        List results;
        ArrayList values = new ArrayList();
        String sql = "Select * FROM updatelog ORDER BY id DESC";
        results = DatabaseUtils.getList(conn, UpdateLog.class, sql, values);
        return results;
    }

    /**
     * Saves new update log instance
     * @param conn
     * @param updated
     * @param lastBuildDate - when the imported RSS feed was created.
     * @param site
     * @return
     * @throws java.sql.SQLException
     */
    public static Object save(Connection conn, Timestamp updated, Timestamp lastBuildDate, String site, String comments) throws SQLException {
        Object result = 0;
        ArrayList values;
        values = new ArrayList();
        String sql = "INSERT into updatelog " +
                "SET updated=?, builddate=?, site=?, comments=?";
        values.add(updated);
        values.add(lastBuildDate);
        values.add(site);
        values.add(comments);
        result = DatabaseUtils.create(conn, sql, values.toArray());
        return result;
    }

    /**
     * Deletes a site's update records.
     * @param conn
     * @param site
     * @return
     * @throws SQLException
     */
    public static Object delete(Connection conn, String site) throws SQLException {
    	Object result = 0;
    	ArrayList values;
    	values = new ArrayList();
    	String sql = "delete from updatelog " +
    	"WHERE site=?";
    	values.add(site);
    	result = DatabaseUtils.create(conn, sql, values.toArray());
    	return result;
    }
}
