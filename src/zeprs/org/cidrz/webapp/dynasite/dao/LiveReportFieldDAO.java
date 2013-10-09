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
import org.cidrz.webapp.dynasite.valueobject.LiveReportField;

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
public class LiveReportFieldDAO {

    /**
     * Returns one record
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static LiveReportField getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        LiveReportField item = null;
        String sql;
        ArrayList values;
        sql = "select id, live_report_id AS liveReportId, form_id AS formId, page_item_id AS pageItemId, " +
                "form_field_id AS FormFieldId, report_property AS reportProperty, shared from live_report_field where id=? ";
        values = new ArrayList();
        values.add(id);
        item = (LiveReportField) DatabaseUtils.getBean(conn, LiveReportField.class, sql, values);
        return item;
    }

    /**
     * unused
     * @return List of objects
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAll(Connection conn) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, live_report_id AS liveReportId, form_id AS formId, page_item_id AS pageItemId, " +
                "form_field_id AS FormFieldId, report_property AS reportProperty, shared from live_report_field;";
        list = DatabaseUtils.getList(conn, LiveReportField.class, sql, values);
        return list;
    }

    /**
     * @return List of objects
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAllForReport(Connection conn, Long id) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, live_report_id AS liveReportId, form_id AS formId, page_item_id AS pageItemId, " +
                "form_field_id AS FormFieldId, report_property AS reportProperty, shared from live_report_field " +
                "where live_report_id=?";
        values.add(id);
        list = DatabaseUtils.getList(conn, LiveReportField.class, sql, values);
        return list;
    }

    /**
     * @return List of objects
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAllForField(Connection conn, Long id) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, live_report_id AS liveReportId, form_id AS formId, page_item_id AS pageItemId, " +
                "form_field_id AS FormFieldId, report_property AS reportProperty, shared from live_report_field " +
                "where form_field_id=?";
        values.add(id);
        list = DatabaseUtils.getList(conn, LiveReportField.class, sql, values);
        return list;
    }

}
