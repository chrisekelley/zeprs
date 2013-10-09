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

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.report.PatientStatusReport;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.AuditInfoBeanProcessor;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.ZEPRSRowProcessor;
import org.cidrz.webapp.dynasite.valueobject.Patient;


/**
 * Provide objects about forms
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 23, 2005
 *         Time: 7:09:58 PM
 */
public class PatientDAO {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(PatientDAO.class);

    /**
     * Returns one record
     *
     * @param conn
     * @param id
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static Patient getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        Patient item = null;
        String sql;
        ArrayList values;
        sql = "select p.id, p.first_name AS firstName, p.surname, p.nrc_number AS nrcNumber, " +
                "p.district_patient_id AS districtPatientid, p.birthdate AS birthdate, p.hiv_positive AS hivPositive, " +
                "height, parent_id AS parentId, site_id AS siteId, p.uuid AS uuid, p.parent_uuid AS parentUuid, p.last_modified AS lastModified  " +
                "from patient p " +
                "where id=? ";
        values = new ArrayList();
        values.add(id);
        item = (Patient) DatabaseUtils.getZEPRSBean(conn, Patient.class, sql, values);
        return item;
    }

    /**
     * Gets a patient record from uuid;
     * @param conn
     * @param uuid
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Patient getOne(Connection conn, String uuid) throws SQLException, ServletException, ObjectNotFoundException {
    	Patient item = null;
    	String sql;
    	ArrayList<String> values;
    	sql = "select p.id, p.first_name AS firstName, p.surname, p.nrc_number AS nrcNumber, " +
    	"p.district_patient_id AS districtPatientid, p.birthdate AS birthdate, p.hiv_positive AS hivPositive, " +
    	"height, parent_id AS parentId, site_id AS siteId, p.uuid AS uuid, p.parent_uuid AS parentUuid, p.last_modified AS lastModified " +
    	"from patient p " +
    	"where uuid=? ";
    	values = new ArrayList<String>();
    	values.add(uuid);
    	item = (Patient) DatabaseUtils.getZEPRSBean(conn, Patient.class, sql, values);
    	return item;
    }

    /**
     * Fetches a patient using zeprsId
     * uses patient_status.last_modified AS lastModified to get the most accurate lastModified
     * uses patient_status.site_id AS siteId to get the most accurate siteId
     * @param conn
     * @param zeprsId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Patient getOneFromZeprsId(Connection conn, String zeprsId) throws SQLException, ServletException, ObjectNotFoundException {
    	Patient item = null;
    	String sql;
    	ArrayList<String> values;
    	sql = "select p.id, p.first_name AS firstName, p.surname, p.nrc_number AS nrcNumber, " +
    	"p.district_patient_id AS districtPatientid, p.birthdate AS birthdate, p.hiv_positive AS hivPositive, " +
    	"height, parent_id AS parentId, ps.site_id AS siteId, p.uuid AS uuid, p.parent_uuid AS parentUuid, ps.last_modified AS lastModified " +
    	"from patient p, patient_status ps " +
    	"where p.id = ps.id " +
    	"AND district_patient_id=? ";
    	values = new ArrayList<String>();
    	values.add(zeprsId);
    	item = (Patient) DatabaseUtils.getZEPRSBean(conn, Patient.class, sql, values);
    	return item;
    }

    /**
     * Gets every single bit of the patient.
     * Used for XML patient reports.
     * @param conn
     * @param id
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Patient getWholePatient(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        Patient item = null;
        String sql;
        ArrayList values;
        sql = "select p.id, p.first_name AS firstName, p.surname, p.nrc_number AS nrcNumber, " +
                "p.district_patient_id AS districtPatientid, address_id AS address, obstetric_record_number AS obstetricRecordnumber, " +
                "sex AS sex, parent_id AS parentId, parent_district_id AS parentDistrictPatientid, p.birthdate AS birthdate, age_at_first_visit AS ageAtFirstVisit, " +
                "time_of_birth AS timeOfBirth, pregnancy_id AS pregnancyId, dead AS dead, hiv_positive AS hivPositive, " +
                "date_death AS dateDeath, death_encounter_id AS encounterIdDeath, height AS heigh, " +
                "import_site AS importSite, import_date AS importDate, import_patient_id AS importPatientId, " +
                "created, last_modified AS lastModified, created_by AS createdBy, last_modified_by AS lastModifiedBy, site_id AS siteId, " +
                "p.uuid AS uuid, p.parent_uuid AS parentUuid " +
                "from patient p " +
                "where id=? ";
        values = new ArrayList();
        values.add(id);
        item = (Patient) DatabaseUtils.getZEPRSBean(conn, Patient.class, sql, values);
        return item;
    }

    /**
     * Save the patient record
     * Used for XML patient reports.
     * @param conn
     * @param patient
     * @return id of new record
     * @throws Exception
     */
    public static Long save(Connection conn, Patient patient) throws Exception {
    	Long id;
    	String sql;
    	ArrayList values;
    	sql = "INSERT INTO patient " +
    	"(first_name, surname, nrc_number, district_patient_id, address_id, obstetric_record_number, sex, parent_id, parent_district_id, birthdate, " +
    	"age_at_first_visit, time_of_birth, pregnancy_id, dead, hiv_positive, date_death, death_encounter_id, height, import_patient_id, created, " +
    	"last_modified, created_by, last_modified_by, site_id, uuid, parent_uuid) " +
    	"VALUES (?,?,?,?,?,?,?,?,?,?," +
    	" ?,?,?,?,?,?,?,?,?,?," +
    	"?,?,?,?,?,?)";
    	values = new ArrayList();
    	values.add(patient.getFirstName());
    	values.add(patient.getSurname());
    	values.add(patient.getNrcNumber());
    	values.add(patient.getDistrictPatientid());
    	values.add(patient.getAddress());
    	values.add(patient.getObstetricRecordnumber());
    	values.add(patient.getSex());
    	values.add(patient.getParentId());
    	values.add(patient.getParentDistrictPatientid());
    	values.add(patient.getBirthdate());
    	values.add(patient.getAgeAtFirstVisit());
    	values.add(patient.getTimeOfBirth());
    	values.add(patient.getPregnancyId());
    	values.add(patient.getDead());
    	values.add(patient.getHivPositive());
    	values.add(patient.getDateDeath());
    	values.add(patient.getEncounterIdDeath());
    	values.add(patient.getHeight());
    	values.add(patient.getImportPatientId());
    	values.add(patient.getCreated());
    	values.add(patient.getLastModified());
    	values.add(patient.getCreatedBy());
    	values.add(patient.getLastModifiedBy());
    	values.add(patient.getSiteId());
    	values.add(patient.getUuid());
    	values.add(patient.getParentUuid());
    	id = (Long) DatabaseUtils.create(conn, sql, values.toArray());
    	return id;
    }

    /**
     * Update a patient record
     * Used for XML patient reports.
     * @param conn
     * @param id
     * @return
     * @throws Exception
     */
    public static int update(Connection conn, Long id) throws Exception {
        int result;
        String sql;
        ArrayList values;
        sql = "UPDATE patient SET first_name=?, surname=?, nrc_number=?, district_patient_id=?, address_id=?, " +
                "obstetric_record_number=?, sex=?, parent_id=?, parent_district_id=?, birthdate=?, age_at_first_visit=?, time_of_birth=?, " +
                "pregnancy_id=?, dead=?, hiv_positive=?, date_death=?, death_encounter_id=? height=? " +
                "WHERE id=? ";
        values = new ArrayList();
        values.add(id);
        result = DatabaseUtils.update(conn, sql, values.toArray());
        return result;
    }

    /**
     * Update a patient record w/ import metadata
     * @param conn
     * @param id
     * @param importSite
     * @param importDate
     * @return
     * @throws Exception
     */
    public static int updateImportData(Connection conn, Long id, String importSite, Timestamp importDate) throws Exception {
        int result;
        String sql;
        ArrayList values;
        sql = "UPDATE patient " +
                "SET import_site=?, import_date=? " +
                "WHERE id=? ";
        values = new ArrayList();
        values.add(importSite);
        values.add(importDate);
        values.add(id);
        result = DatabaseUtils.update(conn, sql, values.toArray());
        return result;
    }

    /**
     * Updates parent_id for children.
     * Used for importing mother record when children are already in the system.
     * @param conn
     * @param id
     * @param uuid
     * @return
     * @throws Exception
     */
    public static int updateParentId(Connection conn, Long id, String uuid) throws Exception {
    	int result;
    	String sql;
    	ArrayList values;
    	sql = "UPDATE patient " +
    	"SET parent_id=?" +
    	"WHERE uuid=? ";
    	values = new ArrayList();
    	values.add(id);
    	values.add(uuid);
    	result = DatabaseUtils.update(conn, sql, values.toArray());
    	return result;
    }

    /**
     * @param conn
     * @return List of objects
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAll(Connection conn) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select p.id, p.first_name AS firstName, p.surname, p.nrc_number AS nrcNumber, " +
                "p.district_patient_id AS districtPatientid " +
                "from patient p";
        list = DatabaseUtils.getList(conn, Patient.class, sql, values);
        return list;
    }

    /**
     * List of patients, sorted
     * unused
     *
     * @param order
     * @return List of patients, sorted
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAll(Connection conn, String order) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select p.id, p.first_name AS firstName, p.surname, p.nrc_number AS nrcNumber, " +
                "p.district_patient_id AS districtPatientid " +
                "from patient p " +
                "order by ?";
        values.add(order);
        list = DatabaseUtils.getList(conn, Patient.class, sql, values);
        return list;
    }

    /**
     * Is this a unique Patient ID? Check if it's in the district_patient_id field of the patient table.
     *
     * @param conn
     * @param patientId
     * @return true if unique
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static Boolean checkPatientId(Connection conn, String patientId) throws SQLException, ServletException {
        Boolean status;
        Object result = null;
        String sql;
        ArrayList values;
        sql = "select district_patient_id AS districtPatientd from patient where district_patient_id=?";
        values = new ArrayList();
        values.add(patientId);
        result = DatabaseUtils.getScalar(conn, sql, values);
        if (result == null) {
            // unique id
            status = Boolean.TRUE;
        } else {
            status = Boolean.FALSE;
        }
        return status;
    }

    /**
     * Checks the uuid to see if this is a unique patient.
     * @param conn
     * @param patientId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Long getIdFromUuid(Connection conn, String uuid) throws SQLException, ServletException {
    	Long result = null;
    	String sql;
    	ArrayList<String> values;
    	sql = "select id from patient where uuid=?";
    	values = new ArrayList<String>();
    	values.add(uuid);
    	result = (Long) DatabaseUtils.getScalar(conn, sql, values);
    	return result;
    }

    /**
     * Fetches most recent patient. unuused.
     * @param conn
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Patient getNewest(Connection conn) throws SQLException, ServletException, ObjectNotFoundException {
        Patient item = null;
        String sql;
        ArrayList values;
        sql = "select p.id, p.first_name AS firstName, p.surname, p.nrc_number AS nrcNumber, " +
                "p.district_patient_id AS districtPatientid " +
                "from patient p " +
                "order by created DESC ";
        values = new ArrayList();
        item = (Patient) DatabaseUtils.getBean(conn, Patient.class, sql, values);
        return item;
    }

    /**
     * Check when patient record was last modified.
     * This is used to calculate if sessionPatient needs refreshing in BasePatientAction
     *
     * @param conn
     * @param patientId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws org.cidrz.webapp.dynasite.exception.ObjectNotFoundException
     *
     */
    public static PatientStatusReport getLastModified(Connection conn, Long patientId) throws SQLException, ServletException, ObjectNotFoundException {
        PatientStatusReport item = null;
        String sql;
        ArrayList values;
        sql = "select p.last_modified AS lastModified " +
                "from patient_status p " +
                "where p.id=?;";
        values = new ArrayList();
        values.add(patientId);
        item = (PatientStatusReport) DatabaseUtils.getBean(conn, PatientStatusReport.class, sql, values);
        if (item.getLastModified() == null) {
            throw new ObjectNotFoundException();
        }
        return item;
    }

    public static String delete(Connection conn, Long patientId) throws Exception {
        String result = null;
        Statement stmt;
        conn.setAutoCommit(false);
        stmt = conn.createStatement();
        stmt.execute("START TRANSACTION;");
        stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
        String sql = "DELETE patient, patient_status FROM patient, patient_status " +
                "WHERE patient.id = patient_status.id " +
                "AND patient.id=" + patientId;
        stmt.execute(sql);
        stmt.execute("Commit");
        result = "Patient record deleted.";
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes records from patient and patient_status tables.
     * Used in databases such as Derby when one may not delete from two tables at the same time.
     * @param conn
     * @param patientId
     * @return
     * @throws Exception
     */
    public static String deleteSep(Connection conn, Long patientId) throws Exception {
    	String result = null;
    	Statement stmt;
    	conn.setAutoCommit(false);
    	stmt = conn.createStatement();
    	String sql = "DELETE FROM patient_status " +
    	"WHERE patient_status.id=" + patientId;
    	stmt.execute(sql);
    	sql = "DELETE FROM patient " +
    	"WHERE patient.id=" + patientId;
    	stmt.execute(sql);
    	result = "Patient record deleted.";
    	try {
    		conn.commit();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return result;
    }

    /**
     * Note that sql limits query to created < endDate - not <=
     *
     * @param conn
     * @return List of patient id's
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static ResultSet getAllDateSite(Connection conn, Date beginDate, Date endDate, Long siteId) throws SQLException, ServletException {

        ResultSet rs = null;
        String sql = null;
        PreparedStatement ps;
        if (beginDate != null) {
            sql = "SELECT id FROM patient WHERE created >= ? AND created < ? AND site_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, beginDate);
            ps.setDate(2, endDate);
            ps.setLong(3, siteId);
        } else {
            sql = "SELECT id FROM patient WHERE created < ? AND site_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, endDate);
            ps.setLong(2, siteId);
        }
        rs = ps.executeQuery();
        return rs;
    }

    /**
     * Fetch a list of all patients according to the dates provided
     * @param conn
     * @param beginDate
     * @param endDate
     * @return list of all patients according to the dates provided
     * @throws SQLException
     * @throws ServletException
     */
    public static ResultSet getAllDate(Connection conn, Timestamp beginDate, Timestamp endDate) throws SQLException, ServletException {

        ResultSet rs = null;
        String sql = null;
        PreparedStatement ps = null;
        if (beginDate != null) {
            sql = "SELECT p.id, ps.last_modified, p.district_patient_id, p.site_id, p.surname " +
                    "FROM patient p, patient_status ps " +
                    "WHERE p.id = ps.id  " +
                    "AND ps.last_modified >= ? AND ps.last_modified <= ?";
            ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setTimestamp(1, beginDate);
            ps.setTimestamp(2, endDate);
        } else {
            sql = "SELECT p.id, ps.last_modified, p.district_patient_id, p.site_id, p.surname " +
                    "FROM patient p, patient_status ps " +
                    "WHERE p.id = ps.id  " +
                    "AND ps.last_modified <= ?";
            ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setTimestamp(1, endDate);
        }
        rs = ps.executeQuery();
        return rs;
    }


    /**
     * Fetches all changed records from yesterday.
     * @param conn
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static ResultSet getAllYesterday(Connection conn) throws SQLException, ServletException {

    	ResultSet rs = null;
    	String sql = null;
    	PreparedStatement ps = null;
    	sql = "SELECT p.id, ps.last_modified, p.district_patient_id, p.site_id, p.surname " +
    	"FROM patient p, patient_status ps " +
        "WHERE p.id = ps.id  " +
    	"AND DATE(ps.last_modified) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)";
    	ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    	rs = ps.executeQuery();
    	return rs;
    }

    /**
     * Gets all records - include mothers and infants
     * Filter by patient_status.site_id = ? OR patient.site_id=?
     * @param conn
     * @return List of patient id's
     * @throws java.sql.SQLException
     */

    public static ResultSet getAllSiteRS(Connection conn, Long siteId) throws SQLException {

        ResultSet rs = null;
        String sql = null;
        PreparedStatement ps = null;
            sql = "SELECT DISTINCT p.id AS id, surname, parent_id, district_patient_id, p.site_id, ps.last_modified, p.uuid, p.parent_uuid  " +
                    "FROM patient p, patient_status ps " +
                    "WHERE p.id = ps.id " +
                    "AND (ps.site_id = ? OR p.site_id=?)";
            // ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps = conn.prepareStatement(sql);
            ps.setLong(1, siteId);
            ps.setLong(2, siteId);
        rs = ps.executeQuery();
        return rs;
    }

    /**
     * Filter by beginDate, endDate, patient_status.site_id = ? OR patient.site_id=?
     * @param conn
     * @return ResultSet from patient and patient_status tables
     * @throws java.sql.SQLException
     */

    public static ResultSet getAllSiteRS(Connection conn, Long siteId, Timestamp beginDate, Timestamp endDate) throws SQLException {

    	ResultSet rs = null;
    	String sql = null;
    	PreparedStatement ps = null;
    	sql = "SELECT DISTINCT p.id AS id, surname, parent_id, district_patient_id, p.site_id, ps.last_modified, p.uuid, p.parent_uuid  " +
    	"FROM patient p, patient_status ps " +
    	"WHERE p.id = ps.id " +
    	"AND (ps.site_id = ? OR p.site_id=?) " +
    	"AND ps.last_modified >= ? AND ps.last_modified <= ?";
    	ps = conn.prepareStatement(sql);
    	ps.setLong(1, siteId);
    	ps.setLong(2, siteId);
        ps.setTimestamp(3, beginDate);
        ps.setTimestamp(4, endDate);
    	rs = ps.executeQuery();
    	return rs;
    }

    /**
     * Gets all records that have recently changed at this site.
     * Filters by patient_status.site_id
     * Even if the patient record was created at this site, we're only interested in patient records that were recently updated at this site.
     * @param conn
     * @return List of patient id's
     * @throws java.sql.SQLException
     */

    public static ResultSet getAllSiteRecentRS(Connection conn, Long siteId) throws SQLException {

        ResultSet rs = null;
        String sql = null;
        PreparedStatement ps = null;
            sql = "SELECT DISTINCT p.id AS id, surname, parent_id, district_patient_id, p.site_id, ps.last_modified, p.uuid, p.parent_uuid  " +
                    "FROM patient p, patient_status ps " +
                    "WHERE p.id = ps.id " +
                    //"AND (ps.site_id = ? OR p.site_id=?)";
    				"AND ps.site_id = ?";
            // ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps = conn.prepareStatement(sql);
            ps.setLong(1, siteId);
        rs = ps.executeQuery();
        return rs;
    }

    /**
     * Fetch list of all patients
     * Used in generation of rss files
     * @param conn
     * @return ResultSet
     * @throws SQLException
     */
    public static ResultSet getAllRS(Connection conn) throws SQLException {

        ResultSet rs = null;
        String sql = null;
        PreparedStatement ps = null;
            sql = "SELECT p.id AS id, surname, parent_id, district_patient_id, p.site_id, ps.last_modified, p.uuid, p.parent_uuid  " +
                    "FROM patient p, patient_status ps " +
                    "WHERE p.id = ps.id";
            //ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        return rs;
    }

    /**
     * This is for creating smaller RSS files for testing the master - results for 2 sites: Airport and State Lodge.
     * @param conn
     * @return
     * @throws SQLException
     */
    public static ResultSet getAllRSTest(Connection conn) throws SQLException {
    	ResultSet rs = null;
    	String sql = null;
    	PreparedStatement ps = null;
    	sql = "SELECT p.id AS id, surname, parent_id, p.district_patient_id, p.site_id, ps.last_modified, p.uuid, p.parent_uuid  " +
    	"FROM patient p, patient_status ps " +
    	"WHERE p.id = ps.id " +
        "AND ((ps.site_id = 1 OR p.site_id=1) " +
    	"OR (ps.site_id = 34 OR p.site_id=34))";
        ps = conn.prepareStatement(sql);
    	rs = ps.executeQuery();
    	return rs;
    }


    /**
     * Save patient id in patientId table.
     * @param zeprsId
     * @param conn
     * @throws java.sql.SQLException
     */
    public static void importPatientId(String zeprsId, Connection conn) throws SQLException {
        String[] zepArray = zeprsId.split("-");
        String distId = zepArray[0];
        String thisSiteId = zepArray[1];
        String assignedId = zepArray[2];
        PatientIdentifierDAO.save(conn, thisSiteId, Long.valueOf(distId), assignedId);
    }

	/**
	 * Fetch a list of children for mother for this pregnancy
	 * @param conn
	 * @param patientId
	 * @param pregnancyId
	 * @return  list of children for mother for this pregnancy
	 */
	public static List getChildren(Connection conn, String parentUuid, Long pregnancyId) {

	    List list = null;
	    ArrayList values;
	    values = new ArrayList();
	    String sql = "select p.id, p.first_name AS firstName, p.surname, " +
		        "u.firstName AS 'auditInfo.lastModifiedBy.firstName', " +
		        "u.lastName AS 'auditInfo.lastModifiedBy.lastName', " +
		        "p.last_modified AS 'auditInfo.lastModified', " +
		        "p.sex, p.time_of_birth AS timeOfBirth, p.birthdate AS birthdate, " +
		        "p.pregnancy_id AS pregnancyId, p.dead AS dead, p.district_patient_id AS districtPatientid, p.uuid AS uuid, p.parent_uuid AS parentUuid " +
	            "from patient p " +
	            "LEFT JOIN userdata.address u ON p.last_modified_by=u.nickname " +
	            "where p.parent_uuid=? " +
	            "and p.pregnancy_id=? " +
	            "ORDER BY p.id";
	    values.add(parentUuid);
	    values.add(pregnancyId.intValue());
	    BeanProcessor beanprocessor = new AuditInfoBeanProcessor();
	    RowProcessor convert = new ZEPRSRowProcessor(beanprocessor);
	    try {
	        list = DatabaseUtils.getList(conn, Patient.class, sql, values, convert);
	    } catch (ServletException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	/**
	 * Fetch a list of children for mother for this pregnancy
	 * @deprecated - Use the version that takes a uuid instead.
	 * @param conn
	 * @param patientId
	 * @param pregnancyId
	 * @return  list of children for mother for this pregnancy
	 */
	public static List getChildren(Connection conn, Long patientId, Long pregnancyId) {

	    List list = null;
	    ArrayList values;
	    values = new ArrayList();
	    String sql = "select p.id, p.first_name AS firstName, p.surname, " +
	            "u.firstName AS 'auditInfo.lastModifiedBy.firstName', " +
	            "u.lastName AS 'auditInfo.lastModifiedBy.lastName', " +
	            "p.last_modified AS 'auditInfo.lastModified', " +
	            "p.sex, p.time_of_birth AS timeOfBirth, p.birthdate AS birthdate, " +
	            "p.pregnancy_id AS pregnancyId, p.dead AS dead, p.district_patient_id AS districtPatientid, p.uuid AS uuid, p.parent_uuid AS parentUuid\n " +
	            "from patient p " +
	            "LEFT JOIN userdata.address u ON p.last_modified_by=u.nickname " +
	            "where p.parent_id=? " +
	            "and p.pregnancy_id=? " +
	            "ORDER BY p.id";
	    values.add(patientId.intValue());
	    values.add(pregnancyId.intValue());
	    BeanProcessor beanprocessor = new AuditInfoBeanProcessor();
	    RowProcessor convert = new ZEPRSRowProcessor(beanprocessor);
	    try {
	        list = DatabaseUtils.getList(conn, Patient.class, sql, values, convert);
	    } catch (ServletException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	/**
	 * Get list of children for parent id, across all pregnancies
	 * @param conn
	 * @param patientId
	 * @return list of children
	 */
	public static List getChildren(Connection conn, Long patientId) {
	    List list = null;
	    ArrayList values;
	    values = new ArrayList();
	    String sql = "select p.id, p.first_name AS firstName, p.surname, " +
	            "u.firstName AS 'auditInfo.lastModifiedBy.firstName', " +
	            "u.lastName AS 'auditInfo.lastModifiedBy.lastName', " +
	            "p.last_modified AS 'auditInfo.lastModified', " +
	            "p.sex, p.time_of_birth AS timeOfBirth, p.birthdate AS birthdate, " +
	            "p.pregnancy_id AS pregnancyId, p.dead AS dead, p.district_patient_id AS districtPatientid, p.uuid AS uuid, p.parent_uuid AS parentUuid\n" +
	            "from patient p " +
	            "LEFT JOIN " + Constants.USERINFO_TABLE + " u ON  p.last_modified_by = u." + Constants.USERINFO_USERNAME +" " +
	            //"where p.last_modified_by=u." + Constants.USERINFO_USERNAME +" " +
	            "WHERE p.parent_id=? " +
	            "ORDER BY p.id";
	    values.add(new Integer(patientId.intValue()));
	    BeanProcessor beanprocessor = new AuditInfoBeanProcessor();
	    RowProcessor convert = new ZEPRSRowProcessor(beanprocessor);
	    try {
	        list = DatabaseUtils.getList(conn, Patient.class, sql, values, convert);
	    } catch (ServletException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	/**
	 * Fetches children born in this pregnancyId
	 * @param conn
	 * @param pregnancyId
	 * @return
	 */
	public static List<Patient> getChildrenPregnancy(Connection conn, Long pregnancyId) {
		List list = null;
		ArrayList values;
		values = new ArrayList();
		String sql = "select p.id, p.first_name AS firstName, p.surname, " +
		"district_patient_id AS districtPatientid, p.uuid AS uuid, p.parent_uuid AS parentUuid\n" +
		"from patient p " +
		"where p.pregnancy_id=? " +
		"ORDER BY p.id";
		values.add(pregnancyId);
		BeanProcessor beanprocessor = new AuditInfoBeanProcessor();
		RowProcessor convert = new ZEPRSRowProcessor(beanprocessor);
		try {
			list = DatabaseUtils.getList(conn, Patient.class, sql, values, convert);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}