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

import org.cidrz.project.zeprs.report.valueobject.PregnancyReport;
import org.cidrz.project.zeprs.valueobject.gen.PrevPregnancies;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Pregnancy;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 4, 2005
 *         Time: 9:22:16 AM
 */
public class PregnancyDAO {

    /**
     * Returns one pregnancy record
     *
     * @param id
     * @return one pregnancy record
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
	public static Pregnancy getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
		Pregnancy item = null;
		String sql;
		ArrayList values;
		sql = "SELECT id, patient_id AS patientId, date_pregnancy_begin AS datePregnancyBegin, " +
		"pregnancy_begin_encounter_id AS pregnancyBeginEncounter, date_pregnancy_end AS datePregnancyEnd, " +
		"last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, " +
		"created_by AS createdBy, site_id AS siteId, created_site_id AS createdSiteId," +
		"labour_admission_encounter_id AS labourAdmissionEncounterId, date_labour_admission AS dateActiveLabour, " +
		"import_pregnancy_id AS importPregnancyId, uuid, " +
		"pregnancy_begin_encounter_uuid AS pregnancyBeginEncounterUuid, pregnancy_end_encounter_id AS pregnancyEndEncounter, " +
		"pregnancy_end_encounter_uuid AS pregnancyEndEncounterUuid, " +
		"labour_admission_encounter_uuid AS labourAdmissionEncounterUuid " +
		"FROM pregnancy WHERE id=?";
		values = new ArrayList();
		values.add(id);
		item = (Pregnancy) DatabaseUtils.getZEPRSBean(conn, Pregnancy.class, sql, values);
		return item;
	}

    /**
     * Returns one pregnancy record by UUID
     * @param conn
     * @param uuid
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Pregnancy getOne(Connection conn, String uuid) throws SQLException, ServletException, ObjectNotFoundException {
    	Pregnancy item = null;
    	String sql;
    	ArrayList values;
    	sql = "SELECT id, patient_id AS patientId, date_pregnancy_begin AS datePregnancyBegin, " +
    	"pregnancy_begin_encounter_id AS pregnancyBeginEncounter, date_pregnancy_end AS datePregnancyEnd, " +
    	"last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, " +
    	"created_by AS createdBy, site_id AS siteId, created_site_id AS createdSiteId," +
    	"labour_admission_encounter_id AS labourAdmissionEncounterId, date_labour_admission AS dateActiveLabour, " +
    	"import_pregnancy_id AS importPregnancyId, uuid, " +
		"pregnancy_begin_encounter_uuid AS pregnancyBeginEncounterUuid, pregnancy_end_encounter_id AS pregnancyEndEncounter, " +
		"pregnancy_end_encounter_uuid AS pregnancyEndEncounterUuid, " +
    	"labour_admission_encounter_uuid AS labourAdmissionEncounterUuid " +
    	"FROM pregnancy WHERE uuid=?";
    	values = new ArrayList();
    	values.add(uuid);
    	item = (Pregnancy) DatabaseUtils.getZEPRSBean(conn, Pregnancy.class, sql, values);
    	return item;
    }


    /**
     * Fetches id from import_pregnancy_id - used in import process.
     * @param conn
     * @param importPregnancyId
     * @param patientId
     * @return
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */

    public static Long getOneByImportedId(Connection conn, Long importPregnancyId, Long patientId) throws ServletException, SQLException, ObjectNotFoundException {
    	Long id = null;
    	String sql = "SELECT id FROM pregnancy WHERE import_pregnancy_id=? and patient_id=? ";
    	ArrayList values = new ArrayList();
    	values.add(importPregnancyId);
    	values.add(patientId);
    	Pregnancy pregnancy = (Pregnancy) DatabaseUtils.getZEPRSBean(conn, Pregnancy.class, sql, values);
    	if (pregnancy != null) {
    		id = pregnancy.getId();
    	}
    	return id;
    }

    /**
     * @return List of pregnancies for this patient.
     * Does not work for infants - they use the pregnancy id of their mother.
     * Orders by pregnancy id - most recent pregnancy should have the highest id.
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List<Pregnancy> getPatientPregnancies(Connection conn, Long patient_id) throws SQLException, ServletException {

        List<Pregnancy> list = new ArrayList<Pregnancy>();
        ArrayList values = new ArrayList();
        String sql;
        sql = "SELECT id, patient_id AS patientId, date_pregnancy_begin AS datePregnancyBegin, " +
        "pregnancy_begin_encounter_id AS pregnancyBeginEncounter, date_pregnancy_end AS datePregnancyEnd, " +
        "last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, " +
        "created_by AS createdBy, site_id AS siteId, created_site_id AS createdSiteId," +
        "labour_admission_encounter_id AS labourAdmissionEncounterId, date_labour_admission AS dateActiveLabour, " +
        "import_pregnancy_id AS importPregnancyId, uuid, " +
		"pregnancy_begin_encounter_uuid AS pregnancyBeginEncounterUuid, pregnancy_end_encounter_id AS pregnancyEndEncounter, " +
		"pregnancy_end_encounter_uuid AS pregnancyEndEncounterUuid, " +
        "labour_admission_encounter_uuid AS labourAdmissionEncounterUuid " +
        "FROM pregnancy " +
        "WHERE patient_id=? " +
        "ORDER BY id";
        values.add(patient_id);
        list = DatabaseUtils.getList(conn, Pregnancy.class, sql, values);
        return list;
    }

    /**
     * Returns one pregnancy record
     *
     * @param pregnancyId
     * @return one pregnancy record
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static PregnancyReport getOnePatientPregnancyReport(Connection conn, Long pregnancyId) throws SQLException, ServletException, ObjectNotFoundException {
        PregnancyReport item = null;
        String sql;
        ArrayList values;
        sql = "select id, patient_id AS patientId, date_pregnancy_begin AS datePregnancyBegin, " +
                "pregnancy_begin_encounter_id AS pregnancyBeginEncounter, date_pregnancy_end AS datePregnancyEnd, " +
                "pregnancy_end_encounter_id AS pregnancyEndEncounter, pregnancy_end_encounter_id, " +
                "uuid from pregnancy where id=?";
        values = new ArrayList();
        values.add(pregnancyId);
        item = (PregnancyReport) DatabaseUtils.getZEPRSBean(conn, PregnancyReport.class, sql, values);
        return item;
    }

    /**
     * @return List of pregnancies for this patient
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getPatientPregnancyReports(Connection conn, Long patient_id) throws SQLException, ServletException {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        String sql;
        values = new ArrayList();
        sql = "SELECT id, patient_id AS patientId, date_pregnancy_begin AS datePregnancyBegin, " +
                "pregnancy_begin_encounter_id AS pregnancyBeginEncounter, date_pregnancy_end AS datePregnancyEnd, " +
                "pregnancy_end_encounter_id AS pregnancyEndEncounter, pregnancy_end_encounter_id, " +
                "uuid from pregnancy " +
                "WHERE patient_id=? " +
                "ORDER BY id DESC";
        values.add(patient_id);
        list = DatabaseUtils.getList(conn, PregnancyReport.class, sql, values);
        return list;
    }

    /**
     * Makes a simple list of previous pregnancies; Use in computing parity; Stuffs values in List of PrevPregnancies objects
     * @param conn
     * @param patient_id
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getFetusTalley(Connection conn, Long patient_id) throws SQLException, ServletException {

        List list;
        ArrayList values;
        String sql;
        values = new ArrayList();
        sql = "select number_of_fetuses_63 AS field63, outcome_of_pregnancy_53 AS field53  " +
                "FROM prevpregnancies, encounter " +
                "WHERE encounter.id = prevpregnancies.id AND encounter.patient_id=? ";
        values.add(patient_id);
        list = DatabaseUtils.getList(conn, PrevPregnancies.class, sql, values);
        return list;
    }

    /**
     * This excludes miscarriages and stillbirths - not correct to use this for parity.
     * @param conn
     * @param patient_id
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getLiveBirths(Connection conn, Long patient_id) throws SQLException, ServletException {

        List list;
        ArrayList values;
        String sql;
        values = new ArrayList();
        sql = "select number_of_fetuses_63 AS field63  " +
                "FROM prevpregnancies, encounter " +
                "WHERE encounter.id = prevpregnancies.id " +
                "AND (outcome_of_pregnancy_53 IS NULL OR " +
                "outcome_of_pregnancy_53 = '21' OR " +
                "outcome_of_pregnancy_53 = '1977' OR " +
                "outcome_of_pregnancy_53 = '2087' OR " +
                "outcome_of_pregnancy_53 = '2183' OR " +
                "outcome_of_pregnancy_53 = '2256' OR " +
                "outcome_of_pregnancy_53 = '2310' OR " +
                "outcome_of_pregnancy_53 = '2361' " +
                ") " +
                "AND encounter.patient_id=? ";
        values.add(patient_id);
        list = DatabaseUtils.getList(conn, PrevPregnancies.class, sql, values);
        return list;
    }


    /**
     * Delete patient records in pregnancy table.
     * @param conn
     * @param patientId
     * @return
     * @throws Exception
     */
    public static String delete(Connection conn, Long patientId) throws Exception {
        String result = null;
        Statement stmt;
        conn.setAutoCommit(false);
        stmt = conn.createStatement();
        stmt.execute("START TRANSACTION;");
        stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
        String sql = "DELETE pregnancy FROM pregnancy " +
                "WHERE pregnancy.patient_id=" + patientId;
        stmt.execute(sql);
        stmt.execute("Commit");
        result = "Pregnancy records deleted.";
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Creates a record in the pregnancy table. Creates uuid.
     * @param conn
     * @param queries
     * @param vo
     * @param patientId
     * @param dateVisit
     * @param userName
     * @param siteId
     * @return
     * @throws ServletException
     * @throws SQLException
     */
    public static Long createPregnancy(Connection conn, Map queries, EncounterData vo, Long patientId, Date dateVisit, String userName, Long siteId) throws ServletException, SQLException {
        Long pregnancyId;
        String sqlCreatePregnancy = (String) queries.get("SQL_CREATE_PREGNANCY");
        Pregnancy pregnancy = vo.getPregnancy();
        String pregnancyUuid = null;
        if (pregnancy != null) {
            pregnancyUuid = pregnancy.getUuid();
        } else {
        	UUID uuid = UUID.randomUUID();
            pregnancyUuid = uuid.toString();
        }
        ArrayList pregnancyValues = new ArrayList();
        // add patient_id to pregnancyValues
        pregnancyValues.add(patientId);
        // add date_visit to pregnancyValues
        pregnancyValues.add(dateVisit);
        FormDAO.AddAuditInfo(vo, pregnancyValues, userName, siteId);
        // adds uuid
        pregnancyValues.add(pregnancyUuid);
        pregnancyId = (Long) DatabaseUtils.create(conn, sqlCreatePregnancy, pregnancyValues.toArray());
        return pregnancyId;
    }

    /**
     * Imports a pregnancy into the patient record - used when the mother's record is not available.
     * @param conn
     * @param queries
     * @param pregnancy
     * @param patientId
     * @return
     * @throws ServletException
     * @throws SQLException
     */
    public static Long importPregnancy(Connection conn, Map queries, Pregnancy pregnancy, Long patientId) throws ServletException, SQLException {
    	Long pregnancyId;
    	String sqlCreatePregnancy = (String) queries.get("SQL_CREATE_PREGNANCY_IMPORT");
    	ArrayList pregnancyValues = new ArrayList();
    	// add patient_id to pregnancyValues
    	pregnancyValues.add(patientId);
    	// add date_visit to pregnancyValues
    	pregnancyValues.add(pregnancy.getDatePregnancyBegin());
    	pregnancyValues.add(pregnancy.getPregnancyBeginEncounterId());
    	pregnancyValues.add(pregnancy.getDatePregnancyEnd());
    	pregnancyValues.add(pregnancy.getPregnancyEndEncounterId());
    	pregnancyValues.add(pregnancy.getDateLabourAdmission());
    	pregnancyValues.add(pregnancy.getLabourAdmissionEncounterId());
	    // last modified
    	pregnancyValues.add(pregnancy.getLastModified());
	    // created
    	pregnancyValues.add(pregnancy.getCreated());
	    // last_modified_by
    	pregnancyValues.add(pregnancy.getLastModifiedBy());
	    // created_by
    	pregnancyValues.add(pregnancy.getCreatedBy());
	    // site_id
    	pregnancyValues.add(pregnancy.getSiteId());
    	// import_pregnancy_id
    	pregnancyValues.add(pregnancy.getId());
    	// adds uuid
    	pregnancyValues.add(pregnancy.getUuid());
    	// created_site_id
    	pregnancyValues.add(pregnancy.getSiteId());
    	pregnancyValues.add(pregnancy.getPregnancyBeginEncounterUuid());
    	pregnancyValues.add(pregnancy.getPregnancyEndEncounterUuid());
    	pregnancyValues.add(pregnancy.getLabourAdmissionEncounterUuid());
    	pregnancyId = (Long) DatabaseUtils.create(conn, sqlCreatePregnancy, pregnancyValues.toArray());
    	return pregnancyId;
    }


    /**
     * Updates all of the values in the pregnancy.
     * @param conn
     * @param queries
     * @param pregnancy
     * @param patientId
     * @return
     * @throws ServletException
     * @throws SQLException
     */
    public static Long updatePregnancy(Connection conn, Map queries, Pregnancy pregnancy, Long patientId, String uuid) throws ServletException, SQLException {
    	Long pregnancyId;
    	String sqlCreatePregnancy = (String) queries.get("SQL_UPDATE_PREGNANCY_IMPORT");
    	ArrayList pregnancyValues = new ArrayList();
    	// add patient_id to pregnancyValues
    	pregnancyValues.add(patientId);
    	// add date_visit to pregnancyValues
    	pregnancyValues.add(pregnancy.getDatePregnancyBegin());
    	pregnancyValues.add(pregnancy.getPregnancyBeginEncounterId());
    	pregnancyValues.add(pregnancy.getDatePregnancyEnd());
    	pregnancyValues.add(pregnancy.getPregnancyEndEncounterId());
    	pregnancyValues.add(pregnancy.getDateLabourAdmission());
    	pregnancyValues.add(pregnancy.getLabourAdmissionEncounterId());
    	// last modified
    	pregnancyValues.add(pregnancy.getLastModified());
    	// created
    	pregnancyValues.add(pregnancy.getCreated());
    	// last_modified_by
    	pregnancyValues.add(pregnancy.getLastModifiedBy());
    	// created_by
    	pregnancyValues.add(pregnancy.getCreatedBy());
    	// site_id
    	pregnancyValues.add(pregnancy.getSiteId());
    	// import_pregnancy_id
    	pregnancyValues.add(pregnancy.getImportPregnancyId());
    	// adds uuid
    	pregnancyValues.add(pregnancy.getUuid());
    	// created_site_id
    	pregnancyValues.add(pregnancy.getSiteId());
    	pregnancyValues.add(pregnancy.getPregnancyBeginEncounterUuid());
    	pregnancyValues.add(pregnancy.getPregnancyEndEncounterUuid());
    	pregnancyValues.add(pregnancy.getLabourAdmissionEncounterUuid());
    	pregnancyValues.add(pregnancy.getId());
    	pregnancyId = (Long) DatabaseUtils.create(conn, sqlCreatePregnancy, pregnancyValues.toArray());
    	return pregnancyId;
    }

    /**
     * Adds importPregnancyId to pregnancy when importing a patient record.
     * @param conn
     * @param patientId
     * @param importedPregnancyId
     * @return
     * @throws SQLException
     */
    public static Long updatePregnancy(Connection conn, Long patientId, Long importedPregnancyId) throws SQLException {
        Long pregnancyId;
        ArrayList pregnancyValues = new ArrayList();
        String sql = "UPDATE pregnancy SET import_pregnancy_id=? WHERE patient_id=?;";
        pregnancyValues.add(importedPregnancyId);
        pregnancyValues.add(patientId);
        pregnancyId = (Long) DatabaseUtils.create(conn, sql, pregnancyValues.toArray());
        return pregnancyId;
    }

    /**
     * Adds updates patient_id. When an infant record is imported before its mother, the common pregnancy is imported without the patient id.
     * This method updates the patient_id.
     * @param conn
     * @param patientId
     * @param importedPregnancyId
     * @return
     * @throws SQLException
     */
    public static Long updatePatientId(Connection conn, String uuid, Long patientId) throws SQLException {
    	Long pregnancyId;
    	ArrayList pregnancyValues = new ArrayList();
    	String sql = "UPDATE pregnancy SET patient_id=? WHERE uuid=?";
    	pregnancyValues.add(patientId);
    	pregnancyValues.add(uuid);
    	pregnancyId = (Long) DatabaseUtils.create(conn, sql, pregnancyValues.toArray());
    	return pregnancyId;
    }

    /**
     * Updates values such as pregnancy_begin_encounter_id, date_labour_admission and pregnancy_begin_encounter_uuid in pregnancy table for a new pregnancy.
     * We're keeping the UUID's.
     * @param conn
     * @param queries
     * @param encounterId
     * @param pregnancyId
     * @param uuid
     * @throws Exception
     */
    public static void updatePregnancyBeginData(Connection conn, Map queries, Long encounterId, Long pregnancyId, String uuid) throws Exception {
        String sqlUpdatePregnancy = (String) queries.get("SQL_MODIFY_PREGNANCY");
        //SQL_MODIFY_PREGNANCY=
        //UPDATE pregnancy SET pregnancy_begin_encounter_id=?, pregnancy_begin_encounter_uuid=? last_modified=? WHERE id=?
        ArrayList updatePregnancyValues = new ArrayList();
        // add encounterId to pregnancy - pregnancy_begin_encounter_id=?,
        updatePregnancyValues.add(encounterId);
        // set uuid for pregnancy_begin_encounter_uuid
        updatePregnancyValues.add(uuid);
        // add last_modified to pregnancy - last_modified=?
        updatePregnancyValues.add(new Timestamp(System.currentTimeMillis()));
        // set id field for pregnancy - WHERE id=?
        updatePregnancyValues.add(pregnancyId);

        DatabaseUtils.update(conn, sqlUpdatePregnancy, updatePregnancyValues.toArray());
    }

    /**
     * Sets patient_id and encounter id's in pregnancy to null when deleting a mother and keeping the children.
     * @param conn
     * @param queries
     * @param encounterId
     * @param pregnancyId
     * @param uuid
     * @throws Exception
     */
    public static void setPregnancyRecordIdsToNull(Connection conn, String uuid) throws Exception {
    	String sql = "UPDATE pregnancy " +
    			"SET patient_id=null, pregnancy_begin_encounter_id=null, pregnancy_end_encounter_id=null, labour_admission_encounter_id= null " +
    			"WHERE uuid=?";
    	ArrayList values = new ArrayList();
    	values.add(uuid);
    	DatabaseUtils.update(conn, sql, values.toArray());
    }
}
