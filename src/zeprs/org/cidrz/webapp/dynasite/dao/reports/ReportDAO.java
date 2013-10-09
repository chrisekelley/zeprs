/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.dao.reports;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;

import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Sep 15, 2005
 *         Time: 5:59:45 PM
 */
public class ReportDAO {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(DeliveryRegisterDAO.class);

    /**
     * @param conn
     * @param beginDate
     * @param endDate
     * @param siteID
     * @return List of records w/ enumerations resolved to english, rather than providing only integer.
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     * @throws java.sql.SQLException
     */
    public static List getAll(Connection conn, Date beginDate, Date endDate, int siteID, int formId, Class clazz) throws IOException, ServletException, SQLException {

        List items;
        Map queries = QueryLoader.instance().load("/" + org.cidrz.webapp.dynasite.Constants.SQL_DEMO_PROPERTIES);
        String sql;
        if (siteID == 0) {
            sql = (String) queries.get("SQL_RETRIEVE_REPORT_SITES" + formId);
        } else {
            sql = (String) queries.get("SQL_RETRIEVE_REPORT" + formId);
        }
        ArrayList values = new ArrayList();
        values.add(beginDate);
        values.add(endDate);
        if (siteID != 0) {
            values.add(siteID);
        }

        items = DatabaseUtils.getList(conn, clazz, sql, values);
        // Attach a map of encounter values that has enumerations already resolved.
        Form encounterForm = (Form) DynaSiteObjects.getForms().get((long) formId);
        Map encMap;
        EncounterData report = null;
        // loop through list and assign string values to enum id's
        try {
            report = (EncounterData) clazz.newInstance();
        } catch (InstantiationException e) {
            log.error(e);
        } catch (IllegalAccessException e) {
            log.error(e);
        }
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                report = (EncounterData) items.get(i);
                encMap = PatientRecordUtils.getEncounterMap(encounterForm, report, "starSchemaName");
                report.setEncounterMap(encMap);

                try {
                    BeanUtils.copyProperties(report, report.getEncounterMap());
                } catch (IllegalAccessException e) {
                    log.error(e);
                } catch (InvocationTargetException e) {
                    log.error(e);
                }
            }
        }
        return items;
    }

    /**
     * Get list from all pregnancies
     * @param conn
     * @param patientId
     * @param formId
     * @param clazz
     * @return
     */
    public static List getAll(Connection conn, Long patientId, Long formId, Class clazz) {

        List items = null;
        Map queries = null;
        try {
            queries = QueryLoader.instance().load("/" + org.cidrz.webapp.dynasite.Constants.SQL_DEMO_PROPERTIES);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String sql = (String) queries.get("SQL_RETRIEVE_REPORT_PREGS" + formId);
        ArrayList values = new ArrayList();
        values.add(patientId);
        try {
            items = DatabaseUtils.getList(conn, clazz, sql, values);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Attach a map of encounter values that has enumerations already resolved.
        Form encounterForm = (Form) DynaSiteObjects.getForms().get(Long.valueOf(formId));
        Map encMap;
        EncounterData report = null;
        // loop through list and assign string values to enum id's
        try {
            report = (EncounterData) clazz.newInstance();
        } catch (InstantiationException e) {
            log.error(e);
        } catch (IllegalAccessException e) {
            log.error(e);
        }
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                report = (EncounterData) items.get(i);
                encMap = PatientRecordUtils.getEncounterMap(encounterForm, report, "starSchemaNameR");
                report.setEncounterMap(encMap);

                try {
                    BeanUtils.copyProperties(report, report.getEncounterMap());
                } catch (IllegalAccessException e) {
                    log.error(e);
                } catch (InvocationTargetException e) {
                    log.error(e);
                }
            }
        }
        return items;
    }

    /**
     * This resolves enum values - used in reports
     * @param conn
     * @param patientId
     * @param formId
     * @param clazz
     * @return list of records w/ enums resolved
     */
    public static List getAllResolved(Connection conn, Long patientId, Long formId, Class clazz) {

        List items = null;
        Map queries = null;
        try {
            queries = QueryLoader.instance().load("/" + org.cidrz.webapp.dynasite.Constants.SQL_DEMO_PROPERTIES);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String sql = (String) queries.get("SQL_RETRIEVE_REPORT_PREGS" + formId);
        ArrayList values = new ArrayList();
        values.add(patientId);
        try {
            items = DatabaseUtils.getList(conn, clazz, sql, values);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Attach a map of encounter values that has enumerations already resolved.
        Form encounterForm = (Form) DynaSiteObjects.getForms().get(Long.valueOf(formId));
        Map encMap;
        EncounterData report = null;
        // loop through list and assign string values to enum id's
        try {
            report = (EncounterData) clazz.newInstance();
        } catch (InstantiationException e) {
            log.error(e);
        } catch (IllegalAccessException e) {
            log.error(e);
        }
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                report = (EncounterData) items.get(i);
                encMap = PatientRecordUtils.getEncounterMap(encounterForm, report, "starSchemaNameR");
                report.setEncounterMap(encMap);

                try {
                    BeanUtils.copyProperties(report, report.getEncounterMap());
                } catch (IllegalAccessException e) {
                    log.error(e);
                } catch (InvocationTargetException e) {
                    log.error(e);
                }
            }
        }
        return items;
    }

    /**
     * report fields, enums resolved, per pregnancy
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param formId
     * @param clazz
     * @return list
     */
    public static List getAllResolved(Connection conn, Long patientId, Long pregnancyId, Long formId, Class clazz) {

        List items = null;
        Map queries = null;
        try {
            queries = QueryLoader.instance().load("/" + org.cidrz.webapp.dynasite.Constants.SQL_DEMO_PROPERTIES);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String sql = (String) queries.get("SQL_RETRIEVE_REPORT_PATIENT" + formId);
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        try {
            items = DatabaseUtils.getList(conn, clazz, sql, values);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Attach a map of encounter values that has enumerations already resolved.
        Form encounterForm = (Form) DynaSiteObjects.getForms().get(Long.valueOf(formId));
        Map encMap;
        EncounterData report = null;
        // loop through list and assign string values to enum id's
        try {
            report = (EncounterData) clazz.newInstance();
        } catch (InstantiationException e) {
            log.error(e);
        } catch (IllegalAccessException e) {
            log.error(e);
        }
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                report = (EncounterData) items.get(i);
                encMap = PatientRecordUtils.getEncounterMap(encounterForm, report, "starSchemaNameR");
                report.setEncounterMap(encMap);

                try {
                    BeanUtils.copyProperties(report, report.getEncounterMap());
                } catch (IllegalAccessException e) {
                    log.error(e);
                } catch (InvocationTargetException e) {
                    log.error(e);
                }
            }
        }
        return items;
    }
}
