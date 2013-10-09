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
import org.cidrz.webapp.dynasite.valueobject.Subscription;

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
public class SubscriptionDAO {

    /**
     * Fetch a single subscription record.
     * @param conn
     * @param id
     * @return a single subscription record.
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Object getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        Object result;
        ArrayList values;
        values = new ArrayList();
        String sql = "SELECT id, url, datesubscribed, enabled, site " +
                "FROM subscription " +
                "WHERE id = ?";
        values.add(id);
        result = DatabaseUtils.getBean(conn, Subscription.class, sql, values);
        return result;
    }

    /**
     * Query by URL
     * @param conn
     * @param siteAbbrev
     * @return one subscription
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Object getOne(Connection conn, String  url) throws SQLException, ServletException, ObjectNotFoundException {
    	Object result;
    	ArrayList values;
    	values = new ArrayList();
    	String sql = "SELECT id, url, datesubscribed, enabled, site " +
    	"FROM subscription " +
    	"WHERE url = ?";
    	values.add(url);
    	result = DatabaseUtils.getBean(conn, Subscription.class, sql, values);
    	return result;
    }

    /**
     * Fetch all subscription instances.
     * @param conn
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAll(Connection conn) throws SQLException, ServletException {
        List result;
        ArrayList values;
        values = new ArrayList();
        String sql = "SELECT * " +
                "FROM subscription;";
        result = DatabaseUtils.getList(conn, Subscription.class, sql, values);
        return result;
    }

    /**
     * Saves new subscription instance.
     * @param conn
     * @param url
     * @return
     * @throws java.sql.SQLException
     */
    public static Object save(Connection conn, String url) throws SQLException {
        Object result = 0;
        ArrayList values;
        values = new ArrayList();
        String sql = "INSERT into subscription " +
                "SET url=?";
        values.add(url);
        result = DatabaseUtils.create(conn, sql, values.toArray());
        return result;
    }

    /**
     * Updates subscription instance w/ site info that comes from rss file when it's processed for the first time..
     * @param conn
     * @param id
     * @param site
     * @param datesubscribed
     * @return
     * @throws java.sql.SQLException
     */
    public static Object updateSite(Connection conn, Long id, String site, Timestamp datesubscribed) throws SQLException {
        Object result = 0;
        ArrayList values;
        values = new ArrayList();
        String sql = "UPDATE subscription " +
                "SET site=?, datesubscribed=? " +
                "WHERE id=?";
        values.add(site);
        values.add(datesubscribed);
        values.add(id);
        result = DatabaseUtils.create(conn, sql, values.toArray());
        return result;
    }

    /**
     * Deletes new subscription instance.
     * @param conn
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public static Object delete(Connection conn, Long id) throws SQLException {
        Object result = 0;
        ArrayList values;
        values = new ArrayList();
        String sql = "DELETE FROM subscription " +
                "WHERE id=?";
        values.add(id);
        result = DatabaseUtils.create(conn, sql, values.toArray());
        return result;
    }
}
