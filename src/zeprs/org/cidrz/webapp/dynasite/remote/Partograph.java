/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.remote;

import org.apache.commons.beanutils.BeanUtils;
import org.cidrz.project.zeprs.valueobject.BaseRecord;
import org.cidrz.project.zeprs.valueobject.gen.PartographStatus;
import org.cidrz.webapp.dynasite.dao.EncountersDAO;
import org.cidrz.webapp.dynasite.dao.FormDAO;
import org.cidrz.webapp.dynasite.dao.PersistenceDAO;
import org.cidrz.webapp.dynasite.dao.partograph.PartographDAO;
import org.cidrz.webapp.dynasite.dao.partograph.CervixDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.exception.PopulationException;
import org.cidrz.webapp.dynasite.utils.BeanPopulator;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Drugs;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.Site;

import javax.servlet.ServletException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TimeZone;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Apr 11, 2005
 * Time: 4:29:25 PM
 */
public class Partograph {

	/**
	 * Saves instance of PartographStatus to indicate that the partograph has started.
	 * Creates an instance of whatever Partograph-related class is being operated on.
	 * @param conn
	 * @param value
	 * @param valueName
	 * @param timeName
	 * @param patientId
	 * @param pregnancyId
	 * @param dateVisitValue
	 * @param username
	 * @param site
	 * @param classNameString
	 * @return
	 * @throws Exception
	 * @throws ObjectNotFoundException
	 * @throws PersistenceException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
    private static Object setupBean(Connection conn, String value, String valueName, String timeName, String patientId, String pregnancyId, String dateVisitValue, String username, Site site, String classNameString) throws Exception, ObjectNotFoundException, PersistenceException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        BaseRecord bean = null;

        PartographStatus partographStatus = null;
        Long formId = new Long("79");
        try {
            partographStatus = (PartographStatus) EncountersDAO.getOne(conn, Long.decode(patientId), Long.decode(pregnancyId), "SQL_RETRIEVE79", PartographStatus.class);
        } catch (ObjectNotFoundException e) {
            // it's ok - let's setup a new partograph record for this patient/pregnancy
            partographStatus = new PartographStatus();
            Long flowId = new Long("3");
            partographStatus.setField1549(Boolean.TRUE);
            partographStatus.setDateVisit(java.sql.Date.valueOf(dateVisitValue));
            partographStatus.setFlowId(flowId);
            partographStatus.setFormId(formId);
            partographStatus.setPatientId(Long.decode(patientId));
            partographStatus.setPregnancyId(Long.decode(pregnancyId));
            Form form = (Form) DynaSiteObjects.getForms().get(new Long("79"));
            FormDAO.create(conn, partographStatus, username, site.getId(), form, flowId, null);
        }

        Class daoClassName = Class.forName("org.cidrz.webapp.dynasite.dao.partograph." + classNameString + "DAO");
        PersistenceDAO dao = (PersistenceDAO) daoClassName.newInstance();
        Class className = Class.forName("org.cidrz.project.zeprs.valueobject.partograph." + classNameString);

        try {
            bean = dao.getOne(conn, Long.decode(patientId), Long.decode(pregnancyId));
        } catch (ObjectNotFoundException e) {
            bean = (BaseRecord) className.newInstance();
        }

        HashMap fields = new HashMap();
        fields.put("patientId", patientId);
        fields.put("pregnancyId", pregnancyId);
        fields.put(valueName, value);
        fields.put("dateVisit", dateVisitValue);
        BeanPopulator.populate(fields, bean);
        return bean;
    }

    /**
     * Used both in partograph.js and Populate patient records to insert parto values.
     *
     * @param value
     * @param column
     * @param classNameString
     * @param propertyName
     * @param patientId
     * @param pregnancyId
     * @param username
     * @param siteId
     * @param dateVisitValue
     * @return
     * @throws Exception
     * @throws ObjectNotFoundException
     * @throws PopulationException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static String insertValue(String value, String column, String classNameString, String propertyName, String patientId, String pregnancyId, String username, String siteId, String dateVisitValue) throws Exception, ObjectNotFoundException, PopulationException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String result = "";
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            Class className = Class.forName("org.cidrz.project.zeprs.valueobject.partograph." + classNameString);
            String valueName = "";
            valueName = new StringBuffer().append(propertyName).append(column).toString();
            String timeName = "";
            timeName = "timeObservation" + column;
            Site site = (Site) DynaSiteObjects.getClinicMap().get(new Long(siteId));
            // if value is empty, return to page. Do not insert empty value.
            if (value.equals("")) {
                result = value + "=" + column;
                return result;
            }

            BaseRecord bean = (BaseRecord) setupBean(conn, value, valueName, timeName, patientId, pregnancyId, dateVisitValue, username, site, classNameString);

            if (className.toString().equals("class org.cidrz.project.zeprs.valueobject.partograph.DrugsDispensed")) {
                StringBuffer drugList = new StringBuffer();
                for (StringTokenizer stringTokenizer = new StringTokenizer(value, ","); stringTokenizer.hasMoreTokens();)
                {
                    String drugId = stringTokenizer.nextToken();
                    Drugs drug = (Drugs) DynaSiteObjects.getDrugMap().get(new Long(drugId));
                    drugList.append(drug.getName() + "<br>");
                }
                result = drugList.toString() + "=" + column;
            }
            Calendar cal = Calendar.getInstance();
            String DATE_FORMAT = "HH:mm:ss";
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
            sdf.setTimeZone(TimeZone.getDefault());
            String timeNow = sdf.format(cal.getTime());

            // Don't want to update the time value when you're editing another value.
            String timeValue = "";
            try {
                timeValue = BeanUtils.getProperty(bean, timeName);
            } catch (InvocationTargetException e) {
                // it's ok
            } catch (NoSuchMethodException e) {
                // it's ok
            }

            if (timeValue == null) {
                timeValue = timeNow;
            }

            PartographDAO.create(conn, bean, value, valueName, classNameString, username, siteId, timeValue, timeName);
            if (result.equals("")) {
                result = value + "=" + column + "=" + timeValue;
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
        }
        return result;
    }

    /**
     * Used for partograph tables that use enums and are multiple rows, such as contractions
     *
     * @param value
     * @param column
     * @param classNameString
     * @param propertyName
     * @param patientId
     * @param pregnancyId
     * @param username
     * @param siteId
     * @param dateVisitValue
     * @return
     * @throws Exception
     * @throws ObjectNotFoundException
     * @throws org.cidrz.webapp.dynasite.exception.PopulationException
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public String insertEnumValue(String value, String row, String column, String classNameString, String propertyName, String patientId, String pregnancyId, String username, String siteId, String dateVisitValue) throws Exception, ObjectNotFoundException, PopulationException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String result = "";
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            Class className = Class.forName("org.cidrz.project.zeprs.valueobject.partograph." + classNameString);
            String valueName = "";
            valueName = new StringBuffer().append(propertyName).append(column).toString();
            String timeName = "";
            timeName = "timeObservation" + column;
            Site site = (Site) DynaSiteObjects.getClinicMap().get(new Long(siteId));
            // if value is empty, return to page. Do not insert empty value.
            if (value.equals("")) {
                result = value + "=" + column;
                return result;
            }

            BaseRecord bean = (BaseRecord) setupBean(conn, value, valueName, timeName, patientId, pregnancyId, dateVisitValue, username, site, classNameString);
            if (className.toString().equals("class org.cidrz.project.zeprs.valueobject.partograph.DrugsDispensed")) {
                StringBuffer drugList = new StringBuffer();
                for (StringTokenizer stringTokenizer = new StringTokenizer(value, ","); stringTokenizer.hasMoreTokens();)
                {
                    String drugId = stringTokenizer.nextToken();
                    Drugs drug = (Drugs) DynaSiteObjects.getDrugMap().get(new Long(drugId));
                    drugList.append(drug.getName()).append("<br>");
                }
                result = drugList.toString() + "=" + column;
            }
            Calendar cal = Calendar.getInstance();
            String DATE_FORMAT = "HH:mm:ss";
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
            sdf.setTimeZone(TimeZone.getDefault());
            String timeNow = sdf.format(cal.getTime());

            // Don't want to update the time value when you're editing another value.
            String timeValue = "";
            try {
                timeValue = BeanUtils.getProperty(bean, timeName);
            } catch (InvocationTargetException e) {
                // it's ok
            } catch (NoSuchMethodException e) {
                // it's ok
            }

            if (timeValue == null) {
                timeValue = timeNow;
            }

            PartographDAO.create(conn, bean, value, valueName, classNameString, username, siteId, timeValue, timeName);
            if (result.equals("")) {
                result = value + "=" + column;
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
        }
        return result;
    }

    /**
     * Returns time in addition to value
     *
     * @param value
     * @param column
     * @param classNameString
     * @param propertyName
     * @param patientId
     * @param pregnancyId
     * @param username
     * @param siteId
     * @param dateVisitValue
     * @return
     * @throws PersistenceException
     * @throws ObjectNotFoundException
     * @throws PopulationException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public String insertValueTime(String value, String column, String classNameString, String propertyName, String patientId, String pregnancyId, String username, String siteId, String dateVisitValue) throws Exception, ObjectNotFoundException, PopulationException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String result = "";
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            Class className = Class.forName("org.cidrz.project.zeprs.valueobject.partograph." + classNameString);
            String valueName = "";
            valueName = new StringBuffer().append(propertyName).append(column).toString();
            String timeName = "";
            timeName = "timeObservation" + column;
            Site site = (Site) DynaSiteObjects.getClinicMap().get(new Long(siteId));
            // if value is empty, return to page. Do not insert empty value.
            if (value.equals("")) {
                result = value + "=" + column;
                return result;
            }

            BaseRecord bean = (BaseRecord) setupBean(conn, value, valueName, timeName, patientId, pregnancyId, dateVisitValue, username, site, classNameString);

            Calendar cal = Calendar.getInstance();
            String DATE_FORMAT = "HH:mm:ss";
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
            sdf.setTimeZone(TimeZone.getDefault());
            String timeNow = sdf.format(cal.getTime());

            // Don't want to update the time value when you're editing another value.
            String timeValue = "";
            try {
                timeValue = BeanUtils.getProperty(bean, timeName);
            } catch (InvocationTargetException e) {
                // it's ok
            } catch (NoSuchMethodException e) {
                // it's ok
            }

            if (timeValue == null) {
                timeValue = timeNow;
            }


            if (className.toString().equals("class org.cidrz.project.zeprs.valueobject.partograph.DrugsDispensed")) {
                StringBuffer drugList = new StringBuffer();
                for (StringTokenizer stringTokenizer = new StringTokenizer(value, ","); stringTokenizer.hasMoreTokens();)
                {
                    String drugId = stringTokenizer.nextToken();
                    Drugs drug = (Drugs) DynaSiteObjects.getDrugMap().get(new Long(drugId));
                    drugList.append(drug.getName() + "<br>");
                }
                result = drugList.toString() + "=" + column + "=" + timeValue;
            }


            PartographDAO.create(conn, bean, value, valueName, classNameString, username, siteId, timeValue, timeName);
            if (result.equals("")) {
                result = value + "=" + column + "=" + timeValue;
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
        }
        return result;
    }

    public String deleteValue(String value, String column, String classNameString, String propertyName, String patientId, String pregnancyId, String username, String siteId, String dateVisitValue) throws Exception, ObjectNotFoundException, PopulationException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String result = "";
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            Class className = Class.forName("org.cidrz.project.zeprs.valueobject.partograph." + classNameString);
            String valueName = "";
            valueName = new StringBuffer().append(propertyName).append(column).toString();
            String timeName = "";
            timeName = "timeObservation" + column;
            Site site = (Site) DynaSiteObjects.getClinicMap().get(new Long(siteId));
           /* // if value is empty, return to page. Do not insert empty value.
            if (value.equals("")) {
                result = value + "=" + column;
                return result;
            }*/
            BaseRecord bean = (BaseRecord) setupBean(conn, value, valueName, timeName, patientId, pregnancyId, dateVisitValue, username, site, classNameString);

            if (className.toString().equals("class org.cidrz.project.zeprs.valueobject.partograph.DrugsDispensed")) {
                StringBuffer drugList = new StringBuffer();
                for (StringTokenizer stringTokenizer = new StringTokenizer(value, ","); stringTokenizer.hasMoreTokens();)
                {
                    String drugId = stringTokenizer.nextToken();
                    Drugs drug = (Drugs) DynaSiteObjects.getDrugMap().get(new Long(drugId));
                    drugList.append(drug.getName() + "<br>");
                }
                result = drugList.toString() + "=" + column;
            }
            Calendar cal = Calendar.getInstance();
            String DATE_FORMAT = "HH:mm:ss";
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
            sdf.setTimeZone(TimeZone.getDefault());
            String timeNow = sdf.format(cal.getTime());

            // Don't want to update the time value when you're editing another value.
            String timeValue = "";
            try {
                timeValue = BeanUtils.getProperty(bean, timeName);
            } catch (InvocationTargetException e) {
                // it's ok
            } catch (NoSuchMethodException e) {
                // it's ok
            }

            if (timeValue == null) {
                timeValue = timeNow;
            }

            PartographDAO.delete(conn, bean, valueName, classNameString, username, siteId, timeName);
            if (result.equals("")) {
                result = value + "=" + column;
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
        }
        return result;
    }

    /**
     * Updates action line values for Cervix
     * @param row
     * @param column
     * @param patientId
     * @param pregnancyId
     * @param username
     * @return
     * @throws Exception
     */
    public String updateActionLineValues(String row, String column, String patientId, String pregnancyId, String username) throws Exception {
        String result = "";
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            int val = CervixDAO.updateActionLines(conn, row, column, patientId, pregnancyId);
            if (val == 1) {
                result = row + "=" + column;
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
        }
        return result;
    }
}
