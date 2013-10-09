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

import org.cidrz.webapp.dynasite.utils.DataStructureUtils;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.LiveReport;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 4, 2005
 *         Time: 9:22:16 AM
 */
public class LiveReportDAO {

    /**
     * Returns one record
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static LiveReport getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        LiveReport item = null;
        String sql;
        ArrayList values;
        sql = "select id, report_name AS reportName from live_report where id=? ";
        values = new ArrayList();
        values.add(id);
        item = (LiveReport) DatabaseUtils.getBean(conn, LiveReport.class, sql, values);
        List items = LiveReportFieldDAO.getAllForReport(conn, id);
        Set fields = DataStructureUtils.listToSetNoComparator(items);
        item.setFields(fields);
        return item;
    }

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
        String sql = sql = "select id, report_name AS reportName from live_report";
        list = DatabaseUtils.getList(conn, LiveReport.class, sql, values);
        return list;
    }

}
