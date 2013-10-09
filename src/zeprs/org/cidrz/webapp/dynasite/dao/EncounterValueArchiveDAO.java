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
import org.cidrz.webapp.dynasite.valueobject.EncounterValueArchive;
import org.cidrz.project.zeprs.valueobject.EncounterData;

import javax.servlet.ServletException;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 22, 2005
 *         Time: 11:48:02 AM
 */
public class EncounterValueArchiveDAO {

	/**
	 * Fetches a single EncounterValueArchive record
	 * @param conn
	 * @param uuid
	 * @return
	 * @throws ServletException
	 * @throws SQLException
	 * @throws ObjectNotFoundException
	 */
    public static Object getOne(Connection conn, String uuid) throws ServletException, SQLException, ObjectNotFoundException {
        Object item;
        String sql = "select v.id, v.uuid AS uuid, v.encounter_uuid AS encounterUuid " +
                "FROM encounter_value_archive v " +
                "WHERE v.uuid=? ";
        ArrayList values = new ArrayList();
        values.add(uuid);
        item = DatabaseUtils.getBean(conn, EncounterValueArchive.class, sql, values);
        return item;
    }

    /**
     * @return List of all changes to data
     * @throws ServletException
     * @throws SQLException
     * @param conn
     */
    // todo: Limit search to date range
    public static List getAll(Connection conn) throws ServletException, SQLException {
        List items;
        String sql = "select v.id, v.encounter_id AS encounterId, v.page_item_id AS pageItemId, v.value, v.previous_value AS previousValue," +
                "v.patient_id AS patientId, v.pregnancy_id AS pregnancyId, v.field_id AS fieldId, v.last_modified AS lastModified, " +
                "v.created, v.last_modified_by AS lastModifiedBy, v.created_by AS createdBy, v.site_id AS siteId, " +
                "f.label AS formLabel, e.date_visit AS dateVisit, CONCAT(p.surname,', ',p.first_name) AS patientName, " +
                "CONCAT(u.lastname,', ',u.firstname) AS ModUserName, v.uuid AS uuid, v.encounter_uuid AS encounterUuid  " +
                "FROM encounter_value_archive v, encounter e, admin.form f, patient p, userdata.address u " +
                "WHERE v.encounter_id = e.id " +
                "AND e.form_id = f.id " +
                "AND v.patient_id = p.id " +
                "AND v.last_modified_by = u.nickname " +
                "AND f.is_enabled=1 " +
                "ORDER BY v.last_modified DESC;";
        ArrayList values = new ArrayList();
        items = DatabaseUtils.getList(conn, EncounterValueArchive.class, sql, values);
        return items;
    }

    public static List getAll(Connection conn, Date beginDate, Date endDate) throws ServletException, SQLException {
    	List items;
    	String sql = "select v.id, v.encounter_id AS encounterId, v.page_item_id AS pageItemId, v.value, v.previous_value AS previousValue," +
    	"v.patient_id AS patientId, v.pregnancy_id AS pregnancyId, v.field_id AS fieldId, v.last_modified AS lastModified, " +
    	"v.created, v.last_modified_by AS lastModifiedBy, v.created_by AS createdBy, v.site_id AS siteId, " +
    	"f.label AS formLabel, e.date_visit AS dateVisit, CONCAT(p.surname,', ',p.first_name) AS patientName, " +
    	"CONCAT(u.lastname,', ',u.firstname) AS ModUserName, v.uuid AS uuid, v.encounter_uuid AS encounterUuid  " +
    	"FROM encounter_value_archive v " +
		"LEFT JOIN userdata.address u ON v.last_modified_by=u.nickname " +
		"LEFT JOIN encounter e ON v.encounter_id = e.id " +
		"LEFT JOIN admin.form f ON e.form_id = f.id " +
		"LEFT JOIN patient p ON v.patient_id = p.id " +
		"WHERE (f.id != 23 AND v.previous_value !=0) " +
    	"AND DATE_FORMAT(v.last_modified,'%Y-%m-%d') >=? " +
    	"AND DATE_FORMAT(v.last_modified,'%Y-%m-%d') <=? " +
    	"ORDER BY v.last_modified DESC;";
    	ArrayList<Date> values = new ArrayList<Date>();
    	values.add(beginDate);
    	values.add(endDate);
    	items = DatabaseUtils.getList(conn, EncounterValueArchive.class, sql, values);
    	return items;
    }

    /**
     * List edits for patient id
     * @param conn
     * @param patientId
     * @return List edits for patient
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAll(Connection conn, Long patientId) throws ServletException, SQLException {
        List items;
        String sql = "select v.id, v.encounter_id AS encounterId, v.page_item_id AS pageItemId, v.value, v.previous_value AS previousValue," +
                "v.patient_id AS patientId, v.pregnancy_id AS pregnancyId, v.field_id AS fieldId, v.last_modified AS lastModified, " +
                "v.created, v.last_modified_by AS lastModifiedBy, v.created_by AS createdBy, v.site_id AS siteId, " +
                "f.label AS formLabel, e.date_visit AS dateVisit, CONCAT(p.surname,', ',p.first_name) AS patientName, " +
                "CONCAT(u.lastname,', ',u.firstname) AS ModUserName, e.form_id AS formId, v.uuid AS uuid, v.encounter_uuid AS encounterUuid  " +
                "FROM encounter_value_archive v, encounter e, admin.form f, patient p, userdata.address u " +
                "WHERE v.encounter_id = e.id " +
                "AND e.form_id = f.id " +
                "AND v.patient_id = p.id " +
                "AND v.last_modified_by = u.nickname " +
                "AND f.is_enabled=1 " +
                "AND v.patient_id=? " +
                //"AND (f.id != 23 AND v.previous_value !=0) " +
                "ORDER BY v.last_modified;";
        ArrayList values = new ArrayList();
        values.add(patientId);
        items = DatabaseUtils.getList(conn, EncounterValueArchive.class, sql, values);
        return items;
    }

    /**
     * List edits for ZEPRS id
     * @param conn
     * @param patientId
     * @return List edits for patient
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAll(Connection conn, String zeprsId) throws ServletException, SQLException {
        List items;
        String sql = "select v.id, v.encounter_id AS encounterId, v.page_item_id AS pageItemId, v.value, v.previous_value AS previousValue," +
                "v.patient_id AS patientId, v.pregnancy_id AS pregnancyId, v.field_id AS fieldId, v.last_modified AS lastModified, " +
                "v.created, v.last_modified_by AS lastModifiedBy, v.created_by AS createdBy, v.site_id AS siteId, " +
                "f.label AS formLabel, e.date_visit AS dateVisit, CONCAT(p.surname,', ',p.first_name) AS patientName, " +
                "CONCAT(u.lastname,', ',u.firstname) AS ModUserName, e.form_id AS formId, v.uuid AS uuid, v.encounter_uuid AS encounterUuid  " +
                "FROM encounter_value_archive v, encounter e, admin.form f, patient p, userdata.address u " +
                "WHERE v.encounter_id = e.id " +
                "AND e.form_id = f.id " +
                "AND v.patient_id = p.id " +
                "AND v.last_modified_by = u.nickname " +
                "AND f.is_enabled=1 " +
                "AND p.district_patient_id=? " +
                "AND (f.id != 23 AND v.previous_value !=0) " +
                //"AND f.id != 23 " +
                "ORDER BY v.last_modified;";
        ArrayList values = new ArrayList();
        values.add(zeprsId);
        items = DatabaseUtils.getList(conn, EncounterValueArchive.class, sql, values);
        return items;
    }

    /**
     * Deletes all edits to encounter_value_archive for this patient.
     * @param conn
     * @param patientId
     * @return
     * @throws Exception
     */
    public static String delete(Connection conn, Long patientId) throws Exception {
        String result = null;
        Statement stmt = null;
        conn.setAutoCommit(false);
        stmt = conn.createStatement();
        stmt.execute("START TRANSACTION;");
        stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
        String sql = "DELETE encounter_value_archive FROM encounter_value_archive " +
                "WHERE patient_id=? ";
        ArrayList values = new ArrayList();
        values.add(patientId);
        int results = DatabaseUtils.update(conn, sql, values.toArray());
        if (results > 0) {
            result = "Encounter edits deleted.";
        }
        conn.commit();
        return result;
    }

    /**
     * Save a value in encounterValueArchive. This is done when editing a value in the web application.
     * @param conn
     * @param user
     * @param encounter
     * @param siteId
     * @param encounterId
     * @param pageItemId
     * @param value
     * @param previousValue
     * @param patientId
     * @param pregnancyId
     * @param formFieldId
     * @param lastModified
     * @param uuid
     * @throws ServletException
     * @throws SQLException
     */
    public static void save(Connection conn, String user, EncounterData encounter, Long siteId, Long encounterId, Long pageItemId, String value, String previousValue, Long patientId, Long pregnancyId, Long formFieldId, Timestamp lastModified, String uuid) throws ServletException, SQLException {
        ArrayList values;
        String sql;
        if (uuid == null) {
        	// create the uuid for the record
        	UUID uuidRand = UUID.randomUUID();
        	uuid = uuidRand.toString();
        }
        values = new ArrayList();
        sql = "INSERT INTO encounter_value_archive " +
        "(encounter_id," +
        " page_item_id," +
        " value," +
        " previous_value," +
        " patient_id," +
        " pregnancy_id," +
        " field_id," +
        " last_modified," +
        " last_modified_by," +
        " created," +
        " created_by, " +
        " site_id, " +
        "uuid, encounter_uuid) " +
        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        values.add(encounterId);
        values.add(pageItemId);
        values.add(value);
        values.add(previousValue);
        values.add(patientId);
        values.add(pregnancyId);
        values.add(formFieldId);
        values.add(lastModified);
        values.add(user);
        values.add(encounter.getCreated());
        values.add(encounter.getCreatedBy());
        values.add(siteId);
        values.add(uuid);
        values.add(encounter.getUuid());
        DatabaseUtils.create(conn, sql, values.toArray());
    }
}