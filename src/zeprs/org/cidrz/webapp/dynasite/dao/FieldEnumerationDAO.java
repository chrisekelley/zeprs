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
import org.cidrz.webapp.dynasite.valueobject.FieldEnumeration;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;

import javax.servlet.ServletException;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 4, 2005
 *         Time: 9:22:16 AM
 */
public class FieldEnumerationDAO {

    /**
     * Returns one record. Be sure to use admin db on this one.
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static FieldEnumeration getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        FieldEnumeration item = null;
        String sql;
        ArrayList values;
        sql = "select id, field_id AS fieldId, enumeration, numeric_value AS numericValue, is_enabled AS enabled, display_order AS displayOrder from field_enumeration where id=? ";
        values = new ArrayList();
        values.add(id);
        item = (FieldEnumeration) DatabaseUtils.getBean(conn, FieldEnumeration.class, sql, values);
        return item;
    }
    
    /**
     * Fetches an enumeration by importId
     * @param conn
     * @param importId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static FieldEnumeration getOneByImportId(Connection conn, Long importId) throws SQLException, ServletException, ObjectNotFoundException {
    	FieldEnumeration item = null;
    	String sql;
    	ArrayList values;
    	sql = "select id, field_id AS fieldId, enumeration, numeric_value AS numericValue, is_enabled AS enabled, display_order AS displayOrder, import_id AS importId "
//    			"openmrs_concept, uuid, parent_uuid as parentUuid, locale  "
    			+ "from ADMIN.field_enumeration where import_id=? ";
    	values = new ArrayList();
    	values.add(importId);
    	item = (FieldEnumeration) DatabaseUtils.getBean(conn, FieldEnumeration.class, sql, values);
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
        String sql = "select id, field_id AS fieldId, enumeration, numeric_value AS numericValue, is_enabled AS enabled, " +
                "display_order AS displayOrder, " +
                "CONCAT(enumeration, ' (', numeric_value, ')') AS concatEnumNumeric " +
                "from field_enumeration ";
        list = DatabaseUtils.getList(conn, FieldEnumeration.class, sql, values);
        return list;
    }

    public static List getAll(Connection conn, Long fieldId) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, field_id AS fieldId, enumeration, numeric_value AS numericValue, is_enabled AS enabled, " +
                "display_order AS displayOrder, " +
                "CONCAT(enumeration, ' (', numeric_value, ')', IF(is_enabled=1,' enabled',' *disabled*')) AS concatEnumNumeric " +
                "from field_enumeration " +
                "WHERE field_id=? " +
                "ORDER BY display_order asc";
        values.add(fieldId);
        list = DatabaseUtils.getList(conn, FieldEnumeration.class, sql, values);
        return list;
    }

    /**
     * List of enabled FieldEnumerations - check if enabled
     * @param conn
     * @param fieldId
     * @return List of enabled FieldEnumerations
     * @throws SQLException
     * @throws ServletException
     */
    public static int getMaxDisplayOrder(Connection conn, Long fieldId, int enabled) throws SQLException, ServletException {
    	int maxValue = 0;
    	ArrayList values = new ArrayList();
    	values = new ArrayList();
    	String sql = "select MAX(display_order) from admin.field_enumeration where field_id = ? and is_enabled = 1";
    	values.add(fieldId);
    	Object value =  DatabaseUtils.getScalar(conn, sql, values);
    	if (value != null) {
    		maxValue =  (Integer) value;
    	}
    	return maxValue;
    }


    /**
     * List of objects
     * unused
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAll(Connection conn, String order) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, field_id AS fieldId, enumeration, numeric_value AS numericValue, is_enabled AS enabled, display_order AS displayOrder from field_enumeration order by ?";
        values.add(order);
        list = DatabaseUtils.getList(conn, FieldEnumeration.class, sql, values);
        return list;
    }

    /**
     * List of enums for lab results form
     * uses enums from ABO Group, Rhesus, Cervical Smear, Sickle Cell Screen, and Malaria Test Results
     * unused
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getLabEnums(Connection conn) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, field_id AS fieldId, enumeration, numeric_value AS numericValue, is_enabled AS enabled, " +
                "display_order AS displayOrder, " +
                "CONCAT(enumeration, ' (', numeric_value, ')') AS concatEnumNumeric " +
                "FROM field_enumeration " +
                "WHERE (field_id = 738 " +    // switched from 193
                "OR field_id = 196 " +
                "OR field_id = 207 " +
                "OR field_id = 1460 " +
                "OR field_id = 1462) " +
                "AND id != 1664 " +
                "AND id != 1115 " +
                "AND id != 111  " +
                "ORDER BY field_id";
        list = DatabaseUtils.getList(conn, FieldEnumeration.class, sql, values);
        return list;
    }

    /**
     * List of enums for lab results form
     * uses enums from Malaria Drug and Deworming Drug
     * unused
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getDrugEnums(Connection conn) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, field_id AS fieldId, enumeration, numeric_value AS numericValue, is_enabled AS enabled, " +
                "display_order AS displayOrder " +
                "from field_enumeration " +
                "WHERE (field_id = 1582 " +
                "OR field_id = 1584) " +
                "ORDER BY field_id";
        list = DatabaseUtils.getList(conn, FieldEnumeration.class, sql, values);
        return list;
    }
    
    /**
     * Created field_enumeration in admin
     * @param conn
     * @param fieldEnumeration TODO
     * @return
     * @throws SQLException
     */
    public static Object create(Connection conn, FieldEnumeration fieldEnumeration) throws SQLException {
    	if (fieldEnumeration.getUuid() == null) {
    		UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            fieldEnumeration.setUuid(uuidStr);
    	}

    	String sql = "insert into ADMIN.field_enumeration (field_id, enumeration, numeric_value, is_enabled, display_order "
    			+ ", import_id "
//        	+ " openmrs_concept, uuid, parent_uuid, locale"
        	+ " ) " +
//            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    		  "values (?, ?, ?, ?, ?, ?)";
        ArrayList values = new ArrayList();
        values.add(fieldEnumeration.getFieldId());
        values.add(fieldEnumeration.getEnumeration());
        values.add(fieldEnumeration.getNumericValue());
        values.add(fieldEnumeration.isEnabled());
        values.add(fieldEnumeration.getDisplayOrder());
        values.add(fieldEnumeration.getImportId());
//        values.add(fieldEnumeration.getOpenmrs_concept());
//        values.add(fieldEnumeration.getUuid());
//        values.add(fieldEnumeration.getParentUuid());
//        values.add(fieldEnumeration.getLocale());

        Long id = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return id;
    }

    /**
     * Created field_enumeration in admin
     * @param conn
     * @param fieldId
     * @param value
     * @param numericValue
     * @return
     * @throws SQLException
     */
    public static Object create(Connection conn, Long fieldId, String value, Integer numericValue) throws SQLException {
        String sql = "insert into admin.field_enumeration (field_id, enumeration, numeric_value, is_enabled, display_order) " +
                "values (?, ?, ?, ?, ?)";
        ArrayList values = new ArrayList();
        values.add(fieldId);
        values.add(value);
        values.add(numericValue);
        values.add(1);
        values.add(numericValue);
        Long id = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return id;
    }

    /**
     * Created field_enumerations from list in admin
     * @param conn
     * @param fieldId
     * @param list of enums, comma-separated
     * @param displayOrder
     * @param numericValueOrder
     * @throws SQLException
     */
    public static void createEnums(Connection conn, Long fieldId, String list, int displayOrder, int numericValueOrder) throws SQLException {
        int i = 0;
        for (StringTokenizer stringTokenizer = new StringTokenizer(list, ","); stringTokenizer.hasMoreTokens();) {
            String value = stringTokenizer.nextToken();
            value.trim();
            String sql = "insert into admin.field_enumeration (field_id, enumeration, numeric_value, is_enabled, display_order) " +
                    "values (?, ?, ?, ?, ?)";
            ArrayList values = new ArrayList();
            values.add(fieldId);
            values.add(value);
            values.add(numericValueOrder + i);
            values.add(1);
            values.add(displayOrder + i);
            i++;
            DatabaseUtils.create(conn, sql, values.toArray());
        }
    }

    public static void updateEnum(Connection conn, Long id, String enumValue, int numericValue, int displayOrder, int enabled) throws SQLException {
        String sql = "UPDATE admin.field_enumeration set enumeration=?, numeric_value=?, display_order=?, is_enabled=? " +
                "WHERE id=? ";
        ArrayList values = new ArrayList();
        values.add(enumValue);
        values.add(numericValue);
        values.add(displayOrder);
        values.add(enabled);
        values.add(id);
        DatabaseUtils.create(conn, sql, values.toArray());
    }
}
