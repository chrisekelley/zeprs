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
import org.cidrz.webapp.dynasite.valueobject.Comment;
import org.cidrz.webapp.dynasite.valueobject.ArchiveLog;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Persists the dates when patient records were archived to XML and RSS feed llast generated.
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 16, 2006
 *         Time: 6:19:05 PM
 */
public class ArchiveDAO {

	/**
	 * Fetch the most recent archive record in order to see when the patient records were most recently archived.
	 * @param conn
	 * @return
	 * @throws SQLException
	 * @throws ServletException
	 * @throws ObjectNotFoundException
	 */
    public static Object getOne(Connection conn) throws SQLException, ServletException, ObjectNotFoundException {
        Object result;
        ArrayList values;
        values = new ArrayList();
        String sql = "SELECT id, archived " +
                "FROM archivelog ORDER BY id DESC LIMIT 1;";
        result = DatabaseUtils.getBean(conn, ArchiveLog.class, sql, values);
        return result;
    }

    /**
     * Fetch list of archive operations
     * @param conn
     * @return  list of archive operations
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAll(Connection conn) throws SQLException, ServletException {
        List results;
        ArrayList values = new ArrayList();
        String sql = "Select * FROM archivelog ORDER BY archived DESC";
        results = DatabaseUtils.getList(conn, ArchiveLog.class, sql, values);
        return results;
    }

    /**
     * Saves new archive log instance
     * @param conn
     * @param time
     * @param checksun
     * @return id of new record
     * @throws SQLException
     */
    public static Object save(Connection conn, Timestamp time, Long checksun) throws SQLException {
        Object result = 0;
        ArrayList values;
        values = new ArrayList();
        String sql = "INSERT into archivelog " +
                "SET archived=?, checksum=?";
        values.add(time);
        values.add(checksun);
        result = DatabaseUtils.create(conn, sql, values.toArray());
        return result;
    }

    /**
     * Reset the archive log to enable export of all patient records to xml files
     * @param conn
     * @throws SQLException
     */
    public static void deleteAll(Connection conn) throws SQLException {
        Object result = 0;
        ArrayList values;
        values = new ArrayList();
        String sql = "delete from archivelog;";
        result = DatabaseUtils.create(conn, sql, values.toArray());
    }
}
