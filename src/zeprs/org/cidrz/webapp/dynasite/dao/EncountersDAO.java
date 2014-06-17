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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.BaseEncounter;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.RoutineAnte;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.logic.EncounterProcessor;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.utils.StringManipulation;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.SessionPatient;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.rti.zcore.utils.database.DatabaseCompatability;

/**
 * Provide objects about forms
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 8, 2005
 *         Time: 7:09:58 PM
 */
public class EncountersDAO {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(EncountersDAO.class);

    /**
     * Has the patient done this encounter?
     *
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param formId
     * @return true if record exists
     */
    public static Boolean checkEncounter(Connection conn, Long patientId, Long pregnancyId, Long formId) {
        Boolean status;
        EncounterData encounter = null;
        String sql;
        ArrayList values;
        sql = "select date_visit AS dateVisit from encounter where patient_id=? and pregnancy_id=? and form_id=?";
        values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        values.add(formId);
        status = Boolean.TRUE;
        try {
            encounter = (EncounterData) DatabaseUtils.getBean(conn, EncounterData.class, sql, values);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } catch (ObjectNotFoundException e) {
            status = Boolean.FALSE;
        }
        return status;
    }

    /**
     * Does this id already exist? This is used in the xml import of a patient record.
     * @param conn
     * @param districtId
     * @return null if this id is already in the system; otherwise, return patient id
     */
    public static Object checkDistrictId(Connection conn, String districtId) {
        // Long id = null;
        Object item = null;
        String sql;
        ArrayList values;
        sql = "select id from patient where district_patient_id=?";
        values = new ArrayList();
        values.add(districtId);
        try {
            // DatabaseUtils.getBean(conn, Patient.class, sql, values);
            item = DatabaseUtils.getScalar(conn, sql, values);
        } catch (SQLException e) {
            log.error(e);
        }
        return item;
    }

    /**
     * Has the patient done this encounter in any pregnancies?
     *
     * @param conn
     * @param patientId
     * @param formId
     * @return true if record exists
     */
    public static Boolean checkAllEncounters(Connection conn, Long patientId, Long formId) {
        Boolean status;
        EncounterData encounter = null;
        String sql;
        ArrayList values;
        sql = "select date_visit AS dateVisit from encounter where patient_id=? and form_id=?";
        values = new ArrayList();
        values.add(patientId);
        values.add(formId);
        status = Boolean.TRUE;
        try {
            encounter = (EncounterData) DatabaseUtils.getBean(conn, EncounterData.class, sql, values);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } catch (ObjectNotFoundException e) {
            status = Boolean.FALSE;
        }
        return status;
    }

    /**
     * Is this a valid encounterId?
     *
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param encounterId
     * @return formId - used to render display of encounter data
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Long checkEncounterId(Connection conn, Long patientId, Long pregnancyId, Long encounterId) throws SQLException, ServletException, ObjectNotFoundException {
        Long formId = null;
        EncounterData encounter = null;
        String sql;
        ArrayList values;
        sql = "select form_id AS formId from encounter where patient_id=? and pregnancy_id=? and id=?";
        values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        values.add(encounterId);
        encounter = (EncounterData) DatabaseUtils.getBean(conn, EncounterData.class, sql, values);
        formId = encounter.getFormId();
        return formId;

    }

       /**
     * Is this a valid encounterId? Works for any pregnancy
     *
     * @param conn
        * @param patientId
        * @param encounterId
        * @return formId - used to render display of encounter data
        * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Long checkEncounterIdNoPreg(Connection conn, Long patientId, Long encounterId) throws SQLException, ServletException, ObjectNotFoundException {
        Long formId = null;
        EncounterData encounter = null;
        String sql;
        ArrayList values;
        sql = "select form_id AS formId from encounter where patient_id=? and id=?";
        values = new ArrayList();
        values.add(patientId);
        values.add(encounterId);
        encounter = (EncounterData) DatabaseUtils.getBean(conn, EncounterData.class, sql, values);
        formId = encounter.getFormId();
        return formId;
    }

    /**
     * Checks if an imported encounter exists
     * @param conn
     * @param patientId
     * @param encounterId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Long checkImportedEncounterIdNoPreg(Connection conn, Long patientId, Long encounterId) throws SQLException, ServletException, ObjectNotFoundException {
        Long id = null;
        EncounterData encounter = null;
        String sql;
        ArrayList values;
        sql = "select id from encounter where patient_id=? and import_encounter_id=?";
        values = new ArrayList();
        values.add(patientId);
        values.add(encounterId);
        encounter = (EncounterData) DatabaseUtils.getBean(conn, EncounterData.class, sql, values);
        id = encounter.getId();
        return id;
    }

    /**
     * Checks encounter.uuid (which is a unique indexed field) to see if an encounter is already in the system.
     * @param conn
     * @param uuid
     * @return id of encounter
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Long checkEncounterUuid(Connection conn, String uuid) throws SQLException, ServletException, ObjectNotFoundException {
    	Long id = null;
    	EncounterData encounter = null;
    	String sql;
    	ArrayList values;
    	sql = "select id from encounter where uuid=?";
    	values = new ArrayList();
    	values.add(uuid);
    	encounter = (EncounterData) DatabaseUtils.getBean(conn, EncounterData.class, sql, values);
    	id = encounter.getId();
    	return id;
    }

    /**
     * Quick way to test if a value in a patient's record meets a certain criteria
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param tableName
     * @param column
     * @param comparison
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Boolean checkEncounterfield(Connection conn, Long patientId, Long pregnancyId, String tableName, String column, String comparison) throws SQLException, ServletException {
        Boolean status;
        Object encounter = null;
        String sql;
        ArrayList values;
        sql = "select encounter.id from encounter, " + tableName + " where " + tableName + ".id = encounter.id and encounter.patient_id=? and encounter.pregnancy_id=? and " + column + "=" + comparison;
        values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        encounter = DatabaseUtils.getObject(conn, sql, values);
        if (encounter != null) {
            status = Boolean.TRUE;
        } else {
            status = Boolean.FALSE;
        }
        return status;
    }

      /**
     * Get previous encounter for a patient for this pregnancy.
     * See FormDAO.create - patient_status update section for flow logic
     * @param patientId
     * @param pregnancyId
     * @param encounterId
     * @return  EncounterData previous encounter
     * @throws SQLException
     */
    public static EncounterData getPreviousEncounter(Connection conn, Long patientId, Long pregnancyId, Long encounterId) throws SQLException, ServletException, ObjectNotFoundException {
        EncounterData item;
        String sql = "SELECT id, form_id AS formId, flow_id AS flowId from encounter " +
                "where patient_id=? AND pregnancy_id=? AND id < ? " +
                "AND flow_id != 2 AND flow_id != 6 AND flow_id != 8 AND flow_id != 102 AND flow_id != 103 AND flow_id != 100\n" +
                "ORDER BY id DESC LIMIT 1";
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        values.add(encounterId);
        //item = DatabaseUtils.getList(conn, EncounterData.class, sql, values);
        item = (EncounterData) DatabaseUtils.getZEPRSBean(conn, EncounterData.class, sql, values);
        return item;
    }

    /**
     * Fetch list of postnatal and intrapartum encounters.
     * Could be useful when trying to switch user back to antepartum when deleting postnatal records.
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param encounterId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static List getPreviousPostnatalEncounters(Connection conn, Long patientId, Long pregnancyId) throws SQLException, ServletException, ObjectNotFoundException {
        List list;
        String sql = "SELECT id, form_id AS formId, flow_id AS flowId from encounter " +
                "where patient_id=? AND pregnancy_id=? " +
                "AND (flow_id = 3) OR  (flow_id = 4)" +
                "ORDER BY id DESC ";
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        list = DatabaseUtils.getList(conn, EncounterData.class, sql, values);
        //item = (EncounterData) DatabaseUtils.getZEPRSBean(conn, EncounterData.class, sql, values);
        return list;
    }

    /**
     * Fetch list of antenatal-related encounters. Checks Antepartum, History, or New Patient flow forms.
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static EncounterData getPreviousAntenatalEncounter(Connection conn, Long patientId, Long pregnancyId) throws SQLException, ServletException, ObjectNotFoundException {
    	EncounterData item;
    	String sql = "SELECT id, form_id AS formId, flow_id AS flowId from encounter " +
    	"where patient_id=? AND pregnancy_id=? " +
    	"AND (flow_id = 1) OR  (flow_id = 2) OR  (flow_id = 9)" +
    	"ORDER BY id DESC LIMIT 1";
    	ArrayList values = new ArrayList();
    	values.add(patientId);
    	values.add(pregnancyId);
    	item = (EncounterData) DatabaseUtils.getZEPRSBean(conn, EncounterData.class, sql, values);
    	return item;
    }

    /**
     * List of all encounters for routine antenatal only. SQL comes from the generatedSQL.properties
     * Remember: Routine antenatal only.
     *
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param genSqlName
     * @return List of routine ante records, ordered by date_visit
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAllRoutineAnte(Connection conn, Long patientId, Long pregnancyId, String genSqlName) throws IOException, ServletException, SQLException {
        List items;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sql = (String) queries.get(genSqlName) + " ORDER BY date_visit" ;
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        items = DatabaseUtils.getList(conn, RoutineAnte.class, sql, values);
        return items;
    }
    

	/**
	 * Applies a simple constraint to the query.
	 *
	 * @param conn
	 * @param formId
	 * @param genSqlName
	 * @param clazz
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
	public static List getAllConstraint(Connection conn, Long formId, String genSqlName, Class clazz,
			String constraintClause, Long constraintLong) throws IOException, ServletException, SQLException {
		List items;
		Map queries = QueryLoader.instance().load("/" + Constants.SQL_GENERATED_PROPERTIES);
		String sql = (String) queries.get(genSqlName);
		String sqlExtra = sql.concat(" WHERE " + constraintClause + " = " + constraintLong);
		ArrayList values = new ArrayList();
		// values.add(formId);
		items = DatabaseUtils.getList(conn, clazz, sqlExtra, values);
		return items;
	}

	/**
	 * Applies a simple constraint and ORDER BY clause to the query.
	 * @param conn
	 * @param formId
	 * @param genSqlName
	 * @param clazz
	 * @param constraintClause
	 * @param constraintLong
	 * @param orderByClause
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
	public static List getAllConstraintOrderBy(Connection conn, Long formId, String genSqlName, Class clazz,
			String constraintClause, Long constraintLong, String orderByClause) throws IOException, ServletException, SQLException {
		List items;
		Map queries = QueryLoader.instance().load("/" + Constants.SQL_GENERATED_PROPERTIES);
		String sql = (String) queries.get(genSqlName);
		String sqlExtra = sql.concat(" WHERE " + constraintClause + " = " + constraintLong + " ORDER BY " + orderByClause);
		ArrayList values = new ArrayList();
		// values.add(formId);
		items = DatabaseUtils.getList(conn, clazz, sqlExtra, values);
		return items;
	}
	
	/**
	 * Used for admin classes - records that are edited in the Admin interface
	 * Not associated with patients. Replacement for MySQL LIMIT - maxRows
	 * Orders by id asc.
	 *
	 * @param conn
	 * @param formId
	 * @param genSqlName
	 * @param clazz
	 * @param maxRows
	 * @param offset TODO
	 * @return list of records
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
    public static List getAll(Connection conn, Long formId, String genSqlName, Class clazz, Integer maxRows, Integer offset) throws IOException, ServletException, SQLException {
    	List items;
    	Map queries = QueryLoader.instance().load("/" + Constants.SQL_GENERATED_PROPERTIES);
    	//String sql = (String) queries.get(genSqlName) + " ORDER BY id asc OFFSET " + offset + " ROWS FETCH NEXT " + maxRows + " ROWS ONLY";
		String insertPagerEnd = DatabaseCompatability.insertPagerEnd(Constants.DATABASE_TYPE, "id asc", offset, maxRows);
    	String sql = (String) queries.get(genSqlName) + insertPagerEnd;
    	ArrayList values = new ArrayList();
    	if (Constants.DATABASE_TYPE.equals("mssql")) {
    		sql = "SELECT TOP " + maxRows + " " + sql;
    	}
    	//items = DatabaseUtils.getList(conn, clazz, sql, values, maxRows);
    	items = DatabaseUtils.getList(conn, clazz, sql, values);
    	return items;
    }
	
	  /**
     * Used for admin classes - records that are edited in the Admin interface
	 * Not associated with patients. Replacement for MySQL LIMIT - maxRows
	 * Supports custom order by statement.
     * @param conn
     * @param formId
     * @param genSqlName
     * @param clazz
     * @param maxRows
     * @param offset
     * @param order "id asc" if null
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAll(Connection conn, Long formId, String genSqlName, Class clazz, Integer maxRows, Integer offset, String order) throws IOException, ServletException, SQLException {
    	List items;
    	Map queries = QueryLoader.instance().load("/" + Constants.SQL_GENERATED_PROPERTIES);
    	if (order == null) {
    		order = "id asc";
    	}
    	String insertPagerEnd = DatabaseCompatability.insertPagerEnd(Constants.DATABASE_TYPE, order, offset, maxRows);
    	String sql = (String) queries.get(genSqlName) + insertPagerEnd;
    	ArrayList values = new ArrayList();
    	if (Constants.DATABASE_TYPE.equals("mssql")) {
    		sql = "SELECT TOP " + maxRows + " " + sql;
    	}
    	items = DatabaseUtils.getList(conn, clazz, sql, values);
    	return items;
    }

    /**
     * List of all encounters for this class. SQL comes from the generatedSQL.properties
     * Works for all classes managed by dynasite.
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param genSqlName
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAll(Connection conn, Long patientId, Long pregnancyId, String genSqlName, Class clazz) throws IOException, ServletException, SQLException {
        List items;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sql = (String) queries.get(genSqlName);
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        items = DatabaseUtils.getList(conn, clazz, sql, values);
        return items;
    }

    /**
     * List of all encounters for this class. SQL comes from the generatedSQL.properties
     * Works for all classes managed by dynasite.
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param genSqlName
     * @param clazz
     * @param orderClause
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAllOrdered(Connection conn, Long patientId, Long pregnancyId, String genSqlName, Class clazz, String orderClause) throws IOException, ServletException, SQLException {
    	List items;
    	Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
    	String sql = (String) queries.get(genSqlName) + " " + orderClause;
    	ArrayList values = new ArrayList();
    	values.add(patientId);
    	values.add(pregnancyId);
    	items = DatabaseUtils.getList(conn, clazz, sql, values);
    	return items;
    }

    /**
     * Simpler way to get all records
     * unused
     * @param patientId
     * @param pregnancyId
     * @param formId
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAll(Long patientId, Long pregnancyId, Long formId, Class clazz) throws IOException, ServletException, SQLException {
        List items;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sqlName =  "SQL_RETRIEVE" + formId;
        String sql = (String) queries.get(sqlName);
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        items = DatabaseUtils.getList(clazz, sql, values);
        return items;
    }

    /**
     * Simpler way to get all records
     * Used in some Reports
     * Provides dynasite-friendly fieldnames - such as field84, rather than human-friendly fieldnames.
     * @param patientId
     * @param pregnancyId
     * @param formId
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAll(Connection conn, Long patientId, Long pregnancyId, Long formId, Class clazz) throws IOException, ServletException, SQLException {
        List items;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sqlName =  "SQL_RETRIEVE" + formId;
        String sql = (String) queries.get(sqlName);
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        items = DatabaseUtils.getList(conn, clazz, sql, values);
        return items;
    }

    /**
     * Get count of records for a form
     * unused
     * @param patientId
     * @param pregnancyId
     * @param formId
     * @return
     * @throws ServletException
     * @throws SQLException
     */
    public static Object getCount(Connection conn, Long patientId, Long pregnancyId, Long formId) throws ServletException, SQLException {
        Object item;
        String sql = "SELECT COUNT(id) from encounter where form_id=? AND patient_id=? AND pregnancy_id=?";
        ArrayList values = new ArrayList();
        values.add(formId);
        values.add(patientId);
        values.add(pregnancyId);
        item = DatabaseUtils.getScalar(conn, sql, values);
        return item;
    }

    /**
     * Has patient made visits on multiple days (is this first visit?)
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @return
     * @throws ServletException
     * @throws SQLException
     */
    public static List getVisits(Connection conn, Long patientId, Long pregnancyId) throws ServletException, SQLException {
        List list;
        String sql = "SELECT date_visit AS dateVisit from encounter where patient_id=? AND pregnancy_id=? GROUP BY date_visit;";
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        list = DatabaseUtils.getList(conn, EncounterData.class, sql, values);
        return list;
    }

    /**
     * Get all of the encounters for a class - used for reporting all encounters in a pregnancy for a patient
     * Provides nice field names.
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param formId
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAllReport(Connection conn, Long patientId, Long pregnancyId, Long formId, Class clazz) throws IOException, ServletException, SQLException {
        List items;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sqlName =  "SQL_RETRIEVE_REPORT_PATIENT" + formId;
        String sql = (String) queries.get(sqlName);
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        items = DatabaseUtils.getList(conn, clazz, sql, values);
        return items;
    }

    /**
     * Get all of the encounters for a class - used for reports
     * @param conn
     * @param formId
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAll(Connection conn, Long formId, Class clazz) throws IOException, ServletException, SQLException {
        List items;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sqlName =  "SQL_RETRIEVEALL" + formId;
        String sql = (String) queries.get(sqlName);
        ArrayList values = new ArrayList();
        items = DatabaseUtils.getList(conn, clazz, sql, values);
        return items;
    }

    /**
     * Get all records for patient accross all pregnancies
     * Use only for previous pregnancies form.
     *
     * @param conn
     * @param patientId
     * @param formId
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAllPregs(Connection conn, Long patientId, Long formId, Class clazz) throws IOException, ServletException, SQLException {
        List items;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sqlName = "SQL_RETRIEVEPREGS" + formId;
        String sql = (String) queries.get(sqlName);
        sql = sql + " ORDER BY year_of_delivery_51, month_of_delivery";
        ArrayList values = new ArrayList();
        values.add(patientId);
        items = DatabaseUtils.getList(conn, clazz, sql, values);
        return items;
    }


    /**
     * This is used for xml reports - fetch a list of all encounters for this patient across all pregnancies
     * Connection added for reports
     * @param conn
     * @param patientId
     * @param formId
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAllPregsReport(Connection conn, Long patientId, Long formId, Class clazz) throws IOException, ServletException, SQLException {
        List items;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sqlName =  "SQL_RETRIEVE_REPORT_PREGS" + formId;
        String sql = (String) queries.get(sqlName);
        ArrayList values = new ArrayList();
        values.add(patientId);
        items = DatabaseUtils.getList(conn, clazz, sql, values);
        return items;
    }

    /**
     * Fetch a record for a patient/pregnancy for a particular class.
     * This only works when only one record can be submitted for this particular class
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param genSqlName
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static Object getOne(Connection conn, Long patientId, Long pregnancyId, String genSqlName, Class clazz) throws IOException, ServletException, SQLException, ObjectNotFoundException {
        Object item;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sql = (String) queries.get(genSqlName);
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        item = DatabaseUtils.getZEPRSBean(conn, clazz, sql, values);
        return item;
    }


    /**
     * Probably the easiest way to get a record. Must be in org.cidrz.project.zeprs.valueobject though.
     * This one's for reports.
     * @param patientId
     * @param pregnancyId
     * @param formId
     * @return record
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static Object getOne(Connection conn, Long patientId, Long pregnancyId, Long formId) throws IOException, ServletException, SQLException, ObjectNotFoundException, ClassNotFoundException {
        Object item;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sqlName = "SQL_RETRIEVE" + formId;
        String sql = (String) queries.get(sqlName);
        Form form = (Form) DynaSiteObjects.getForms().get(formId);
        String clazzName = StringManipulation.fixClassname(form.getName());
        Class clazz = null;
        clazz = Class.forName("org.cidrz.project.zeprs.valueobject.gen." + clazzName);
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        item = DatabaseUtils.getZEPRSBean(conn, clazz, sql, values);
        return item;
    }

    /**
     * Probably the easiest way to get a record. Must be in org.cidrz.project.zeprs.valueobject though.
     * This one's for reports - it provides "nice" field names.
     * @param patientId
     * @param pregnancyId
     * @param formId
     * @return record
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static Object getOneReports(Connection conn, Long patientId, Long pregnancyId, Long formId) throws IOException, ServletException, SQLException, ObjectNotFoundException, ClassNotFoundException {
        EncounterData item;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sqlName = "SQL_RETRIEVE_REPORT_PATIENT" + formId;
        String sql = (String) queries.get(sqlName);
        Form form = (Form) DynaSiteObjects.getForms().get(formId);
        String clazzName = StringManipulation.fixClassname(form.getName() + "Report");
        Class clazz = null;
        clazz = Class.forName("org.cidrz.project.zeprs.valueobject.report.gen." + clazzName);
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        item = (EncounterData) DatabaseUtils.getZEPRSBean(conn, clazz, sql, values);
        Form encounterForm = (Form) DynaSiteObjects.getForms().get(formId);
        Map encMap = PatientRecordUtils.getEncounterMap(encounterForm, item, "starSchemaNameR");
        item.setEncounterMap(encMap);
        try {
            BeanUtils.copyProperties(item, item.getEncounterMap());
        } catch (IllegalAccessException e) {
            log.error(e);
        } catch (InvocationTargetException e) {
            log.error(e);
        }
        return item;
    }

    /**
     * Probably the easiest way to get a record. Must be in org.cidrz.project.zeprs.valueobject though.
     * This one's for reports - it provides "nice" field names. Does not take pregnancyId - only for forms that are posted once.
     * @param patientId
     * @param formId
     * @return record
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static Object getOneReport(Connection conn, Long patientId, Long formId, Class clazz) throws IOException, ServletException, SQLException, ObjectNotFoundException, ClassNotFoundException {
        Object item;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sqlName = "SQL_RETRIEVE_REPORT_PREGS" + formId;
        String sql = (String) queries.get(sqlName);
        ArrayList values = new ArrayList();
        values.add(patientId);
        item = DatabaseUtils.getZEPRSBean(conn, clazz, sql, values);
        return item;
    }

    /**
     * This one's for reports - it provides "nice" field names.
     * You can stuff it into any ole class you choose...
     * Does not resolve enums though - use getResolvedOne instead for resolution.
     * @param conn
     * @param patientId
     * @param formId
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     * @throws ClassNotFoundException
     */
    public static Object getOneReport(Connection conn, Long patientId, Long pregnancyId, Long formId, Class clazz) throws IOException, ServletException, SQLException, ObjectNotFoundException, ClassNotFoundException {
        Object item;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sqlName = "SQL_RETRIEVE_REPORT_PATIENT" + formId;
        String sql = (String) queries.get(sqlName);
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        item = DatabaseUtils.getZEPRSBean(conn, clazz, sql, values);
        return item;
    }

    /**
     * Resolves enums provided in getOneReport - used for reports.
     * @param conn
     * @param patientId
     * @param formId
     * @param clazz
     * @return
     * @throws ObjectNotFoundException
     */
    public static Object getResolvedOne(Connection conn, Long patientId, Long formId, Class clazz) throws ObjectNotFoundException {
        EncounterData record = null;
        try {
            record = (EncounterData) EncountersDAO.getOneReport(conn, patientId, formId, clazz);
        } catch (IOException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } catch (ClassNotFoundException e) {
            log.error(e);
        }
        Form encounterForm = (Form) DynaSiteObjects.getForms().get(formId);
        Map encMap = PatientRecordUtils.getEncounterMap(encounterForm, record, "starSchemaNameR");
        record.setEncounterMap(encMap);
        try {
            BeanUtils.copyProperties(record, record.getEncounterMap());
        } catch (IllegalAccessException e) {
            log.error(e);
        } catch (InvocationTargetException e) {
            log.error(e);
        }
        return record;
    }

    /**
     * Resolves enums provided in getOneReport - used for reports.
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param formId
     * @param clazz
     * @return
     * @throws ObjectNotFoundException
     */
    public static Object getResolvedOne(Connection conn, Long patientId, Long pregnancyId, Long formId, Class clazz) throws ObjectNotFoundException {
        EncounterData record = null;
        try {
            record = (EncounterData) EncountersDAO.getOneReport(conn, patientId, pregnancyId, formId, clazz);
        } catch (IOException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } catch (ClassNotFoundException e) {
            log.error(e);
        }
        Form encounterForm = (Form) DynaSiteObjects.getForms().get(formId);
        Map encMap = PatientRecordUtils.getEncounterMap(encounterForm, record, "starSchemaNameR");
        record.setEncounterMap(encMap);
        try {
            BeanUtils.copyProperties(record, record.getEncounterMap());
        } catch (IllegalAccessException e) {
            log.error(e);
        } catch (InvocationTargetException e) {
            log.error(e);
        }
        return record;
    }

    /**
     * unused
     * @param encounterId
     * @param genSqlName
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static Object getOne(Long encounterId, String genSqlName, Class clazz) throws IOException, ServletException, SQLException, ObjectNotFoundException {
        Object item;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sql = (String) queries.get(genSqlName);
        ArrayList values = new ArrayList();
        values.add(encounterId);
        item = DatabaseUtils.getBean(clazz, sql, values);
        return item;
    }

    /**
     *
     * @param conn
     * @param encounterId
     * @param genSqlName
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static Object getOne(Connection conn, Long encounterId, String genSqlName, Class clazz) throws IOException, ServletException, SQLException, ObjectNotFoundException {
        Object item;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sql = (String) queries.get(genSqlName);
        ArrayList values = new ArrayList();
        values.add(encounterId);
        item = DatabaseUtils.getBean(conn, clazz, sql, values);
        return item;
    }


    /**
     * Gets record by id  - used SQL_RETRIEVEID
     * @param conn
     * @param encounterId
     * @param formId
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static Object getOneById(Connection conn, Long encounterId, Long formId, Class clazz) throws IOException, ServletException, SQLException, ObjectNotFoundException {
        Object item;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sqlName =  "SQL_RETRIEVEID" + formId;
        String sql = (String) queries.get(sqlName);
        ArrayList values = new ArrayList();
        values.add(encounterId);
         try {
             item = DatabaseUtils.getZEPRSBean(conn, clazz, sql, values);
         } catch (ObjectNotFoundException e) {
             log.error("SQL: " + sql + " encounterId: " + encounterId);
             throw new ObjectNotFoundException(e);
         }
         return item;
    }

    /**
     * Used mostly for reports - can access fields by user-friendly starschemaname, rather than fieldX
     * @param conn
     * @param encounterId
     * @param formId
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static Object getOneReportById(Connection conn, Long encounterId, Long formId, Class clazz) throws IOException, ServletException, SQLException, ObjectNotFoundException {
        Object item;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sqlName =  "SQL_RETRIEVE_REPORT_ID" + formId;
        String sql = (String) queries.get(sqlName);
        ArrayList values = new ArrayList();
        values.add(encounterId);
         try {
             item = DatabaseUtils.getZEPRSBean(conn, clazz, sql, values);
         } catch (ObjectNotFoundException e) {
             log.error("SQL: " + sql + " encounterId: " + encounterId);
             throw new ObjectNotFoundException(e);
         }
         return item;
    }

    /**
     * Uses Report fieldnames, resolves enums, waxes floor.
     * @param conn
     * @param encounterId
     * @param formId
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static Object getOneReportByIdResolved(Connection conn, Long encounterId, Long formId, Class clazz) throws IOException, ServletException, SQLException, ObjectNotFoundException {
        BaseEncounter item;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sqlName =  "SQL_RETRIEVE_REPORT_ID" + formId;
        String sql = (String) queries.get(sqlName);
        ArrayList values = new ArrayList();
        values.add(encounterId);
         try {
             item = (BaseEncounter) DatabaseUtils.getZEPRSBean(conn, clazz, sql, values);
         } catch (ObjectNotFoundException e) {
             log.error("SQL: " + sql + " encounterId: " + encounterId);
             throw new ObjectNotFoundException(e);
         }

        Form encounterForm = (Form) DynaSiteObjects.getForms().get(formId);
        Map encMap = PatientRecordUtils.getEncounterMap(encounterForm, item, "starSchemaNameR");
        item.setEncounterMap(encMap);
        try {
            BeanUtils.copyProperties(item, item.getEncounterMap());
        } catch (IllegalAccessException e) {
            log.error(e);
        } catch (InvocationTargetException e) {
            log.error(e);
        }

         return item;
    }

    /**
     * Fetches an EncounterData object by encounter id.
     * @param conn
     * @param encounterId
     * @return
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static Object getOneById(Connection conn, Long encounterId) throws ServletException, SQLException, ObjectNotFoundException {
        Object item = null;
        String sql = "SELECT id, form_id AS formId, date_visit AS dateVisit, patient_id AS patientId, pregnancy_id as pregnancyId\n" +
                "FROM encounter WHERE id=? ";
        ArrayList values = new ArrayList();
        values.add(encounterId);
        try {
            item = DatabaseUtils.getZEPRSBean(conn, EncounterData.class, sql, values);
        } catch (ObjectNotFoundException e) {
            log.error("SQL: " + sql + " encounterId: " + encounterId);
            //throw new ObjectNotFoundException(e);
        }
        return item;
    }

    /**
     * Fetches an EncounterData object by uuid
     * @param conn
     * @param uuid
     * @return
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static Object getOneByUuid(Connection conn, String uuid) throws ServletException, SQLException, ObjectNotFoundException {
    	Object item = null;
    	String sql = "SELECT id, form_id AS formId, date_visit AS dateVisit, patient_id AS patientId, " +
    	"pregnancy_id as pregnancyId,\n" +
    	"last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, " +
    	"created_by AS createdBy, site_id AS siteId, uuid " +
    	"FROM encounter WHERE uuid=? ";
    	ArrayList values = new ArrayList();
    	values.add(uuid);
    	//try {
    		item = DatabaseUtils.getZEPRSBean(conn, EncounterData.class, sql, values);
    	/*} catch (ObjectNotFoundException e) {
    		log.error("SQL: " + sql + " uuid: " + uuid);
    		//throw new ObjectNotFoundException(e);
    	}*/
    	return item;
    }

    /**
     * Fetches encounter by imported id.
     * Useful in import sync process.
     * @param conn
     * @param importEncounterId
     * @param patientId
     * @param siteId
     * @return
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static Object getOneByImportedId(Connection conn, Long importEncounterId, Long patientId, Long siteId) throws ServletException, SQLException, ObjectNotFoundException {
    	Object item = null;
    	String sql = "SELECT id, form_id AS formId, date_visit AS dateVisit, patient_id AS patientId, " +
    	"pregnancy_id as pregnancyId,\n" +
    	"last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, " +
    	"created_by AS createdBy, site_id AS siteId " +
    	"FROM encounter WHERE import_encounter_id=? and patient_id=? AND site_id=? ";
    	ArrayList values = new ArrayList();
    	values.add(importEncounterId);
    	values.add(patientId);
    	values.add(siteId);
    	try {
    		item = DatabaseUtils.getZEPRSBean(conn, EncounterData.class, sql, values);
    	} catch (ObjectNotFoundException e) {
    		log.error("SQL: " + sql + " encounterId: " + importEncounterId);
    		//throw new ObjectNotFoundException(e);
    	}
    	return item;
    }

    /**
     * Fetch value from a field
     * @param conn
     * @param encounterId
     * @param formId
     * @param colName
     * @return
     * @throws ServletException
     * @throws SQLException
     */
    public static Object getValue(Connection conn, Long encounterId, Long formId, String colName) throws ServletException, SQLException {
        Object item;
        Form encounterForm = ((Form) DynaSiteObjects.getForms().get(formId));
        String table = encounterForm.getName().toLowerCase();
        String sql = "SELECT " + colName + " FROM " + table + " WHERE id=" + encounterId;
        item = DatabaseUtils.getScalar(conn, sql);
        return item;
    }

    /**
     * fetches most recent record using order by last_modified_by desc
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param formId
     * @param clazz
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static Object getMostRecentRecord(Connection conn, Long patientId, Long pregnancyId, Long formId, Class clazz) throws IOException, ServletException, SQLException, ObjectNotFoundException {
        Object item;
        Map queries = QueryLoader.instance().load("/" + Constants.SQL_DEMO_PROPERTIES);
        String sqlName =  "SQL_RETRIEVE" + formId;
        String sql = (String) queries.get(sqlName) + " ORDER BY last_modified_by DESC";
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        item = DatabaseUtils.getZEPRSBean(conn, clazz, sql, values);
        return item;
    }

    /**
     * Use this only for one-to-one relationships i.e. if only one record can be inserted per patient
     *
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param formId
     * @return
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     */
    public static Object getId(Connection conn, Long patientId, Long pregnancyId, Long formId) throws ServletException, SQLException, ObjectNotFoundException {
        Object item;
        String sql;
        ArrayList values;
        sql = "select id from encounter where patient_id=? and pregnancy_id=? and form_id=?";
        values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        values.add(formId);
        item = DatabaseUtils.getBean(conn, EncounterData.class, sql, values);
        return item;
    }

    /**
     * Updates the subclass table, then the encounter table
     *
     * @param conn
     * @param value
     * @param pageItemId
     * @param formId
     * @param encounterId
     * @param siteId
     * @param user
     * @param patientId
     * @param pregnancyId
     * @param sessionPatient
     * @param lastModified - normally null, unless this update has been imported.
     * @param uuid - uuid of imported encounterValueArchive, if available.
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws PersistenceException
     */
    public static Long update(Connection conn, String value, Long pageItemId, Long formId, Long encounterId, Long siteId, String user, Long patientId, Long pregnancyId, SessionPatient sessionPatient, Timestamp lastModified, String uuid) throws SQLException, ServletException, PersistenceException {
    	Form form = (Form) DynaSiteObjects.getForms().get(formId);
    	String table = form.getName().toLowerCase();
    	PageItem pageItem = (PageItem) DynaSiteObjects.getPageItems().get(pageItemId);
    	FormField formField = pageItem.getForm_field();
    	Long formFieldId = pageItem.getForm_field().getId();
    	if (lastModified == null) {
    		lastModified = new Timestamp(System.currentTimeMillis());
    	}
    	String dataType = formField.getType();
    	if (dataType.equals("Float")) {
    		try {
    			Float floatValue = Float.valueOf(value);
    		} catch (NumberFormatException e) {
    			throw new PersistenceException("This input field requires a float value (e.g.: 3.2). You entered : " + value, e, false);
    		}
    	} else if (dataType.equals("Integer")) {
    		try {
    			Integer intValue = Integer.valueOf(value);
    		} catch (NumberFormatException e) {
    			throw new PersistenceException("This input field requires an integer value (e.g.: 55). You entered : " + value, e, false);
    		}
    	}
    	// first get the previous encounter
    	EncounterData encounter = null;
    	String className = "org.cidrz.project.zeprs.valueobject.gen." + StringManipulation.fixClassname(form.getName());
    	Class clazz = null;
    	try {
    		clazz = Class.forName(className);
    	} catch (ClassNotFoundException e) {
    		log.error(e);
    	}
    	try {
    		encounter = (EncounterData) clazz.newInstance();
    	} catch (InstantiationException e) {
    		log.error(e);
    	} catch (IllegalAccessException e) {
    		log.error(e);
    	}

    	try {
    		encounter = (EncounterData) EncountersDAO.getOne(conn, encounterId, "SQL_RETRIEVEID" + formId, clazz);
    	} catch (IOException e) {
    		log.error(e);
    	} catch (ServletException e) {
    		log.error(e);
    	} catch (SQLException e) {
    		log.error(e);
    	} catch (ObjectNotFoundException e) {
    		log.error(e);
    	}

    	encounter.setLastModified(lastModified);

    	Object obj = null;
    	try {
    		obj = BeanUtils.getProperty(encounter, "field" + formFieldId);
    	} catch (NoSuchMethodException e) {
    		log.error(e);
    	} catch (IllegalAccessException e) {
    		log.error(e);
    	} catch (InvocationTargetException e) {
    		log.error(e);
    	}

    	// start the transaction
    	String sql = "START TRANSACTION;";
    	// ArrayList array = new ArrayList();
    	try {
    		DatabaseUtils.create(conn, sql);

    		String column = formField.getStarSchemaName();
    		sql = "UPDATE " + table +
    		" SET " + column + "=?" +
    		" WHERE id=?" +
    		"; ";
    		String previousValue = String.valueOf(obj);
    		ArrayList values = new ArrayList();
    		values.add(value);
    		values.add(encounterId);
    		// we want it to throw a new Persistence exception.
    		DatabaseUtils.create(conn, sql, values.toArray());
    		// Update the encounter table
    		if (formId.intValue() == 70) {
    			// Need to set pregnancyId in case this is med/surg history being updated.
    			encounter.setPregnancyId(pregnancyId);
    			updatePregEncounterTable(conn, user, siteId, encounterId, pregnancyId, lastModified);
    		} else {
    			touchEncounterTable(conn, user, siteId, encounterId, lastModified);
    		}

    		// if it's a field in the patient table, update it.
    		updateDemographics(conn, patientId, formFieldId, value);

    		// update misc values
    		java.sql.Date dateVisit = encounter.getDateVisit();
    		try {
    			//int intValue = new Integer(value);
    			FormDAO.updatePatientValues(conn, patientId, formFieldId.intValue(), value, dateVisit, encounterId);
    		} catch (NumberFormatException e) {
    			// skip
    		}

    		// Update encounter data - for SM Care dateVisit fields
    		FormDAO.updateEncounterData(conn, patientId, formFieldId.intValue(), value, dateVisit, encounterId);

    		if (value != null) {
    			// update enc w/ the new value
    			try {
    				BeanUtils.setProperty(encounter, "field" + formFieldId, value);
    				// now see if there are any rules triggered, and persist the rule.
    				encounter.setSessionPatient(sessionPatient);     // script rule processing needs session patient in the encounter
    				try {
    				EncounterProcessor.checkRule(conn, form, pageItem, value, user, encounter);
    				} catch (Exception e) {
    					log.error("Rule eval error - encounterId: " + encounter.getId() + " PatientId: " + encounter.getPatientId() + " fieldId: " + pageItem.getForm_field().getId() + " error: " + e);
    				}
    			} catch (IllegalAccessException e) {
    				log.error(e);
    			} catch (InvocationTargetException e) {
    				log.error(e);
    			} catch (ConversionException e) {
    				// ok for now...log.error(e);
    			}
    		}

    		// Update the encounter _value_archive table
    		EncounterValueArchiveDAO.save(conn, user, encounter, siteId, encounterId, pageItemId, value, previousValue, patientId, pregnancyId, formField.getId(), lastModified, uuid);

    		// update patient_status table
    		EncounterData vo = encounter;
    		if (pageItem.getForm_field().getId().longValue() == 1971) {
    			PatientStatusDAO.updatePatientStatusFirm(conn, user, siteId, patientId, value);
    		} else {
    			PatientStatusDAO.touchPatientStatus(conn, vo, user, siteId, patientId);
    		}

    		/**
    		 * Commit the transaction
    		 */
    		sql = "COMMIT";
    		DatabaseUtils.create(conn, sql);
    	} catch (Exception e) {
    		sql = "ROLLBACK";
    		try {
    			DatabaseUtils.create(conn, sql);
    		} catch (Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		log.error("Rolling back transaction: " + e);
    		e.printStackTrace();
    		throw new PersistenceException("Problem updating this record.", e, true);
    		//throw new SQLException("Error saving this record.", e.getMessage());
    	}
    	return encounterId;
    }

    /**
     * Update values in the patient table changed from the Demographics/Patient registration form.
     * @param conn
     * @param patientId
     * @param formFieldId
     * @param preparedValue
     * @throws ServletException
     * @throws SQLException
     */
    private static void updateDemographics(Connection conn, Long patientId, Long formFieldId, String preparedValue) throws ServletException, SQLException {
        String sql;
        ArrayList patientValues = new ArrayList();
        patientValues.add(preparedValue);
        patientValues.add(patientId);
        switch(formFieldId.intValue()) {
            case 6:
                sql = "UPDATE patient SET surname=? WHERE id=?;";
                DatabaseUtils.create(conn, sql, patientValues.toArray());
                break;
            case 7:
                sql = "UPDATE patient SET first_name=? WHERE id=?;";
                DatabaseUtils.create(conn, sql, patientValues.toArray());
                break;
            case 9:
                sql = "UPDATE patient SET nrc_number=? WHERE id=?;";
                DatabaseUtils.create(conn, sql, patientValues.toArray());
                break;
            case 17:
                sql = "UPDATE patient SET birthdate=? WHERE id=?;";
                DatabaseUtils.create(conn, sql, patientValues.toArray());
                break;
            case 1134:
                sql = "UPDATE patient SET obstetric_record_number=? WHERE id=?;";
                DatabaseUtils.create(conn, sql, patientValues.toArray());
                break;
            case 1135:
		        sql = "UPDATE patient SET age_at_first_visit=? WHERE id=?;";
                DatabaseUtils.create(conn, sql, patientValues.toArray());
                break;
            case 1513:
                sql = "UPDATE patient SET district_patient_id=? WHERE id=?;";
                DatabaseUtils.create(conn, sql, patientValues.toArray());
                break;
            case 1511:
            	// resolve the correct site id
            	Site site = (Site) DynaSiteObjects.getClinicKeyAlphaMap().get(preparedValue);
				patientValues.clear();
				patientValues.add(site.getId());
		        patientValues.add(patientId);
                sql = "UPDATE patient SET site_id=? WHERE id=?;";
                DatabaseUtils.create(conn, sql, patientValues.toArray());
                break;
        }
    }

    /**
     * Updates the encounter table metadata such as last_modified, last_modified_by, and site_id.
     * Sets the last_modified value to current time.
     * @param conn
     * @param user
     * @param siteId
     * @param encounterId
     * @throws ServletException
     * @throws SQLException
     */
    public static void touchEncounterTable(Connection conn, String user, Long siteId, Long encounterId) throws ServletException, SQLException {
        ArrayList values;
        String sql;
        values = new ArrayList();
        sql = "UPDATE encounter" +
                " SET last_modified='" + new Timestamp(System.currentTimeMillis()) +
                "', last_modified_by='" + user +
                "', site_id=" + siteId +
                " WHERE id=?" +
                "; ";
        values.add(encounterId);
        DatabaseUtils.create(conn, sql, values.toArray());
        // log.debug("SQL update: " + sql);
    }

    /**
     * Updates the encounter table metadata such as last_modified, last_modified_by, and date_visit.
     * Sets the last_modified value to current time.
     * @param conn
     * @param user
     * @param dateVisit
     * @param encounterId
     * @throws ServletException
     * @throws SQLException
     */
    public static void touchEncounterTable(Connection conn, String user, Date dateVisit , Long encounterId) throws ServletException, SQLException {
    	ArrayList values;
    	String sql;
    	values = new ArrayList();
    	sql = "UPDATE encounter" +
    	" SET last_modified=?" +
    	", last_modified_by=?" +
    	", date_visit=?" +
    	" WHERE id=?" +
    	"; ";
    	values.add(new Timestamp(System.currentTimeMillis()));
    	values.add(user);
    	values.add(dateVisit);
    	values.add(encounterId);
    	DatabaseUtils.create(conn, sql, values.toArray());
    	// log.debug("SQL update: " + sql);
    }

    /**
     * Updates last_modified, last_modified_by, and site_id in encounter table.
     * @param conn
     * @param user
     * @param siteId
     * @param encounterId
     * @param lastModified
     * @throws SQLException
     * @throws ServletException
     * @throws SQLException
     */
    public static void touchEncounterTable(Connection conn, String user, Long siteId, Long encounterId, Timestamp lastModified) throws SQLException {
        ArrayList values;
        String sql;
        values = new ArrayList();
        sql = "UPDATE encounter" +
                " SET last_modified='" + lastModified +
                "', last_modified_by='" + user +
                "', site_id=" + siteId +
                " WHERE id=?" +
                "; ";
        values.add(encounterId);
        DatabaseUtils.create(conn, sql, values.toArray());
        // log.debug("SQL update: " + sql);
    }

    /**
     * When Med/sugical history is updated, the pregnancy ID need to be updated to make new problems be persisted in the correctp pregnancy.
     * @param conn
     * @param user
     * @param siteId
     * @param encounterId
     * @param lastModified
     * @throws ServletException
     * @throws SQLException
     */
    public static void updatePregEncounterTable(Connection conn, String user, Long siteId, Long encounterId, Long pregnancyId, Timestamp lastModified) throws ServletException, SQLException {
        ArrayList values;
        String sql;
        values = new ArrayList();
        sql = "UPDATE encounter" +
                " SET last_modified='" + lastModified +
                "', last_modified_by='" + user +
                "', site_id=" + siteId +
                ", pregnancy_id=" + pregnancyId +
                " WHERE id=" + encounterId +
                "; ";
        DatabaseUtils.create(conn, sql, values.toArray());
    }

    /**
     * Updates multiple value for the subclass table, then updates the encounter table timestamp/last encounter fields.
     * Archives previous entries.
     *
     * @param conn
     * @param formId
     * @param encounterId
     * @param siteId
     * @param user
     * @param patientId
     * @param pregnancyId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Long updateMultipleValues(Connection conn, Map valueMap, Long formId, Long encounterId, Long siteId, String user, Long patientId, Long pregnancyId) throws SQLException, ServletException {
        Form form = (Form) DynaSiteObjects.getForms().get(formId);
        String table = form.getName().toLowerCase();
        // get the previous encounter
        EncounterData encounter = null;
        String className = "org.cidrz.project.zeprs.valueobject.gen." + StringManipulation.fixClassname(form.getName());
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            encounter = (EncounterData) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            encounter = (EncounterData) EncountersDAO.getOne(conn, patientId, pregnancyId, "SQL_RETRIEVE" + formId, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

        Set valueSet = valueMap.entrySet();
        HashMap formFieldMap = (HashMap) DynaSiteObjects.getFieldToPageItem().get(formId);
        for (Iterator iterator = valueSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String formFieldName = (String) entry.getKey();
            String formFieldNameTrunc = formFieldName.substring(5);
            Long formFieldId = new Long(formFieldNameTrunc);
            String value = (String) entry.getValue();
            // do not process field 1 - dateVisit field
            if (formFieldId.intValue() != 1) {
                if (!value.equals("")) {
                    Long pageItemId = (Long) formFieldMap.get(formFieldId);
                    PageItem pageItem = (PageItem) DynaSiteObjects.getPageItems().get(pageItemId);
                    FormField formField = pageItem.getForm_field();
                    Object obj = null;
                    try {
                        obj = BeanUtils.getProperty(encounter, "field" + formFieldId);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    String previousValue = String.valueOf(obj);
                    String preparedValue = null;
                    if (formField.getType().equals("Boolean")) {
                        preparedValue = value;
                    } else if (formField.getType().equals("Long")) {
                        preparedValue = value;
                    } else if (formField.getType().equals("enum")) {
                        preparedValue = value;
                    } else if (formField.getType().equals("Enum")) {
                        preparedValue = value;
                    } else if (formField.getType().equals("Float")) {
                        preparedValue = value;
                    } else if (formField.getType().equals("Date")) {
                        preparedValue = "'" + value + "'";
                    } else if (formField.getType().equals("Text")) {
                        preparedValue = "'" + value + "'";
                    } else if (formField.getType().equals("Time")) {
                        preparedValue = "'" + value + "'";
                    } else {
                        preparedValue = value;
                    }

                    String column = formField.getStarSchemaName();
                    String sql = null;
                    ArrayList values = new ArrayList();
                    sql = "UPDATE " + table +
                            " SET " + column + "=" + preparedValue +
                            " WHERE id=?" +
                            "; ";
                    // values.add(value);
                    values.add(encounterId);
                    log.debug("Update sql: " + sql + ", encounterId: " + encounterId);
                    DatabaseUtils.create(conn, sql, values.toArray());
                    // Update the encounter _value_archive table
                    values = new ArrayList();
                    sql = "INSERT INTO encounter_value_archive" +
                            " SET encounter_id=?," +
                            " page_item_id=?," +
                            " value=?," +
                            " previous_value=?," +
                            " patient_id=?," +
                            " pregnancy_id=?," +
                            " field_id=?," +
                            " last_modified='" + new Timestamp(System.currentTimeMillis()) +
                            "', last_modified_by='" + user +
                            "', created='" + encounter.getCreated() +
                            "', created_by='" + encounter.getCreatedBy() +
                            "', site_id=" + siteId +
                            "; ";
                    values.add(encounterId);
                    values.add(pageItemId);
                    values.add(value);
                    values.add(previousValue);
                    values.add(patientId);
                    values.add(pregnancyId);
                    values.add(formField.getId());
                    DatabaseUtils.create(conn, sql, values.toArray());
                }
            }
        }

        String sql = null;
        ArrayList values = new ArrayList();
        // Update the encounter table
        values = new ArrayList();
        sql = "UPDATE encounter" +
                " SET last_modified='" + new Timestamp(System.currentTimeMillis()) +
                "', last_modified_by='" + user +
                "', site_id=" + siteId +
                " WHERE id=?" +
                "; ";
        values.add(encounterId);
        DatabaseUtils.create(conn, sql, values.toArray());
        return encounterId;
    }

    /**
     * Get metadate for all encounters for all patients - phew!
     * @param conn
     * @return
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAll(Connection conn) throws ServletException, SQLException {
        List items;
        String sql = "select encounter.id AS id, encounter.patient_id AS patientId, encounter.form_id AS formId, " +
                "encounter.flow_id AS flowId, encounter.date_visit AS dateVisit, encounter.pregnancy_id AS pregnancyId, " +
                "encounter.last_modified AS lastModified, encounter.created AS created, " +
                "encounter.last_modified_by AS lastModifiedBy, encounter.created_by AS createdBy, " +
                "encounter.site_id AS siteId, patient.surname as surname, patient.first_name AS firstName, " +
                "patient.district_patient_id AS zeprsId, patient.parent_id AS parentId, " +
                "CONCAT_WS(',',userdata.address.lastname,userdata.address.firstname) AS staffName, " +
                "LEFT(form.label, 8) AS formName \n" +
                "FROM encounter, patient, userdata.address, form\n" +
                "WHERE patient.id = encounter.patient_id " +
                "AND userdata.address.nickname = encounter.last_modified_by " +
                "AND encounter.form_id = form.id \n" +
                "ORDER BY patient_id, created";
        ArrayList values = new ArrayList();
        items = DatabaseUtils.getList(conn, EncounterData.class, sql, values);
        return items;
    }

    /**
     * Get metadate for all encounters for a patient
     * @param conn
     * @return
     * @throws ServletException
     * @throws SQLException
     */
    public static List getAll(Connection conn, Long patientId) throws ServletException, SQLException {
        List items;
        String sql = "select id AS id, patient_id AS patientId, form_id AS formId, flow_id AS flowId, " +
                "date_visit AS dateVisit, pregnancy_id AS pregnancyId, " +
                "last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, " +
                "created_by AS createdBy, site_id AS siteId, referral_id AS referralId, " +
                "created_site_id AS createdSiteId, import_encounter_id AS importEncounterId, uuid " +
                "FROM encounter " +
                "WHERE patient_id=? " +
                "ORDER BY created";
        ArrayList values = new ArrayList();
        values.add(patientId);
        items = DatabaseUtils.getList(conn, EncounterData.class, sql, values);
        return items;
    }

    /**
     * Deletes all encounters from a single table for a patient.
     * @param conn
     * @param patientId
     * @param tableName
     * @return
     * @throws Exception
     */
    public static String delete(Connection conn, Long patientId, String tableName) throws Exception {
        String result = null;
        Statement stmt = null;
        conn.setAutoCommit(false);
        stmt = conn.createStatement();
        stmt.execute("START TRANSACTION;");
        stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
        String sql = "DELETE encounter," + tableName.toLowerCase() + " FROM encounter," + tableName.toLowerCase() + " " +
                "WHERE encounter.id = "+ tableName.toLowerCase() + ".id " +
                "AND patient_id=? ";
        ArrayList values = new ArrayList();
        values.add(patientId);
            int results = DatabaseUtils.update(conn, sql, values.toArray());
            if (results >0) {
               result = "Encounters deleted.";
            }
        conn.commit();
        return result;
    }

    /**
     * Deletes a single encounter from a single table for a patient.
     * Be careful here - there's a chance this enocunter could be in patient status
     * @param conn
     * @param tableName
     * @return
     * @throws Exception
     */
    public static String delete(Connection conn, String tableName, Long encounterId) throws Exception {
        String result = null;
        Statement stmt = null;
        conn.setAutoCommit(false);
        stmt = conn.createStatement();
        try {
            stmt.execute("START TRANSACTION;");
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
            String sql = "DELETE encounter," + tableName.toLowerCase() + " " +
                    "FROM encounter," + tableName.toLowerCase() + " " +
                    "WHERE encounter.id = " + tableName.toLowerCase() + ".id " +
                    "AND encounter.id = ?; ";
            ArrayList values = new ArrayList();
            values.add(encounterId);
            int results = DatabaseUtils.update(conn, sql, values.toArray());
            if (results >0) {
               result = "Encounter deleted.";
            }
            conn.commit();
        } catch (Exception e) {
            stmt.execute("ROLLBACK;");
            log.error(e);
            throw new SQLException("Error deleting this record.", e.getMessage());
        }
        conn.setAutoCommit(true);
        return result;
    }

    /**
     * Used when importing from xml patient file
     * @param conn
     * @param importEncounterId
     * @return
     * @throws SQLException
     * @throws ObjectNotFoundException
     * @throws ServletException
     */
    public static EncounterData getNewEncounterData(Connection conn, Long importEncounterId) throws SQLException, ObjectNotFoundException, ServletException {
        EncounterData item;
        String sql = "SELECT id, pregnancy_id AS pregnancyId, form_id AS formId " +
                "FROM encounter " +
                "WHERE import_encounter_id=?";
        ArrayList values = new ArrayList();
        values.add(importEncounterId);
        item = (EncounterData) DatabaseUtils.getZEPRSBean(conn, EncounterData.class, sql, values);
        return item;
    }

    /**
     * Used when importing from xml patient file. Fetches encounter by uuid
     * @param conn
     * @param uuid
     * @return
     * @throws SQLException
     * @throws ObjectNotFoundException
     * @throws ServletException
     */
    public static EncounterData getNewEncounterData(Connection conn, String uuid) throws SQLException, ObjectNotFoundException, ServletException {
    	EncounterData item;
    	String sql = "SELECT id, pregnancy_id AS pregnancyId, form_id AS formId " +
    	"FROM encounter " +
    	"WHERE uuid=?";
    	ArrayList values = new ArrayList();
    	values.add(uuid);
    	item = (EncounterData) DatabaseUtils.getZEPRSBean(conn, EncounterData.class, sql, values);
    	return item;
    }

    /**
     * This is used when attempting to serialize a patient record who does not have a pregnancy id in patient status or in patient tables.
     * @param conn
     * @param patientId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static EncounterData getEncounterWithPregnancyId(Connection conn, Long patientId) throws SQLException, ServletException, ObjectNotFoundException {
        EncounterData item;
        String sql = "SELECT id, pregnancy_id AS pregnancyId " +
        		"from encounter " +
                "where patient_id=? AND pregnancy_id IS NOT NULL " +
                "ORDER BY id DESC LIMIT 1";
        ArrayList values = new ArrayList();
        values.add(patientId);
        item = (EncounterData) DatabaseUtils.getZEPRSBean(conn, EncounterData.class, sql, values);
        return item;
    }

    /**
     * Fetches all encounters w/ null pregnancy id's.
     * @param conn
     * @param patientId
     * @return
     * @throws ServletException
     * @throws SQLException
     */
    public static List<EncounterData> getAllNullPregnancyId(Connection conn, Long patientId) throws ServletException, SQLException {
        List<EncounterData> items;
        String sql = "select id AS id, patient_id AS patientId, form_id AS formId, flow_id AS flowId, " +
                "date_visit AS dateVisit, pregnancy_id AS pregnancyId, " +
                "last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, " +
                "created_by AS createdBy, site_id AS siteId, referral_id AS referralId, " +
                "created_site_id AS createdSiteId, import_encounter_id AS importEncounterId, uuid " +
                "FROM encounter " +
                "WHERE patient_id=? " +
                "AND pregnancy_id IS NULL " +
                "ORDER BY id DESC";
        ArrayList values = new ArrayList();
        values.add(patientId);
        items = DatabaseUtils.getList(conn, EncounterData.class, sql, values);
        return items;
    }

    /**
     * Updates pregnancy_id.
     * @param conn
     * @param encounterId
     * @param pregnancyId
     * @throws ServletException
     * @throws SQLException
     */
    public static void updatePregEncounterTable(Connection conn, Long encounterId, Long pregnancyId) throws ServletException, SQLException {
        ArrayList values;
        String sql;
        values = new ArrayList();
        sql = "UPDATE encounter" +
                " SET pregnancy_id=" + pregnancyId +
                " WHERE id=" + encounterId +
                "; ";
        DatabaseUtils.create(conn, sql, values.toArray());
    }
}
