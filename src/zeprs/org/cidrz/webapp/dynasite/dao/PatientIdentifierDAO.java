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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.DistrictId;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Feb 28, 2009
 *         Time: 4:00 PM
 */
public class PatientIdentifierDAO {

    /**
     * Commons Logging instance.
     */
    static Log log = LogFactory.getFactory().getInstance(PatientIdentifierDAO.class);


    /**
     * Returns one record
     * unused
     * @param id
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static DistrictId getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
    	DistrictId item = null;
        String sql;
        ArrayList values;
        sql = "select id, district_id AS districtId, site_id AS siteId, patient_id AS patientId, checksum from district_id where id=? ";
        values = new ArrayList();
        values.add(id);
        item = (DistrictId) DatabaseUtils.getBean(conn, DistrictId.class, sql, values);
        return item;
    }

    /**
     * @return List of objects
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAll() throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, district_id AS districtId, site_id AS siteId, patient_id AS patientId, checksum from district_id";
        list = DatabaseUtils.getList(DistrictId.class, sql, values);
        return list;
    }

    /**
     * List of objects
     *
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAll(Connection conn, String order) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, district_id AS districtId, site_id AS siteId, patient_id AS patientId, checksum from district_id order by ?";
        values.add(order);
        list = DatabaseUtils.getList(conn, DistrictId.class, sql, values);
        return list;
    }

    /**
     * Useful for providing standalone sites a list of patient id's that have already been assigned
     * @param conn
     * @param siteId - site_alpha_id in site table
     * @return List of patient id records ordered by patient_id
     * @throws SQLException
     * @throws ServletException
     */
    public static List<DistrictId> getAllSite(Connection conn, String siteId) throws SQLException, ServletException {
    	List<DistrictId> list = new ArrayList<DistrictId>();
    	ArrayList values = new ArrayList();
    	values = new ArrayList();
    	String sql = "select id, district_id AS districtId, site_id AS siteId, patient_id AS patientId, checksum from district_id " +
    			"WHERE site_id = ? ORDER BY patient_id";
    	values.add(siteId);
    	list = DatabaseUtils.getList(conn, DistrictId.class, sql, values);
    	return list;
    }

    /**
     * Saves patient id, keeping it from being re-assigned.
     * @param conn
     * @param siteId
     * @param districtId
     * @param patientId
     * @throws SQLException
     */
    public static void save(Connection conn, String siteId, Long districtId, String patientId) throws SQLException {
        ArrayList values = new ArrayList();
        String sql = "INSERT INTO district_id (site_id, district_id, patient_id) VALUES (?,?,?)";
        values.add(siteId);
        values.add(districtId);
        values.add(patientId);
        DatabaseUtils.create(conn, sql, values.toArray());
    }

	/**
	 * Reserves patient id in district_id table.
	 * @deprecated - duplicates functionality of save mthod.
	 * @param conn
	 * @param districtId
	 * @param siteId
	 * @param patientId
	 * @throws SQLException
	 */
	public static void reserveId(Connection conn, Integer districtId, String siteId, Integer patientId) throws SQLException {
		// Start a transaction
		conn.setAutoCommit(false);
		DistrictId result = null;
		ArrayList values;
		/*String sql = "INSERT INTO district_id " +
	            "SET site_id='" + siteId + "', district_id=" + districtId + ", patient_id=" + patientId + "";*/
		String sql = "INSERT INTO district_id (site_id, district_id, patient_id) VALUES (?,?,?)";
		values = new ArrayList();
		values.add(siteId);
		values.add(districtId);
		values.add(patientId);
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
			conn.commit();
		} catch (SQLException e) {
			log.error(e);
		} finally {
			try {
				DbUtils.close(rs);
			} finally {
				DbUtils.close(stmt);
			}
		}
	}

    /**
     * Starting assigning id's from a certain number. Some clinics already assigned id's before ZEPRS was introduced.
     * Loop though a list of assigned patient id's for a clinic, starting at a certain number (newPatientId).
     * Increment newPatientId each step. If the next patient id is not equal to newPatientId, result is newPatientId.
     * @param conn
     * @param districtId
     * @param siteId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static String getPatientId(Connection conn, Integer districtId, String siteId) throws SQLException, ServletException {
		String patientId = null;
		// Start a transaction
		conn.setAutoCommit(false);
		DistrictId result = null;
		String sql;
		ArrayList values;
		int newPatientId = 0;
		String startSql = "START TRANSACTION;";
		String selectPatientIdSql = null;
		int i = 0;
		// Remove the subSiteId from the siteId string
		String siteTruncated = siteId.substring(0, 2);
		String subSiteId = siteId.substring(2, 3);
		// start infant numbering from 1000 (infant subsubsiteid = 3)
		if (subSiteId.equals("3")) { // infant id's
			selectPatientIdSql = "SELECT DISTINCT(patient_id) "
					+ "FROM district_id " + "WHERE site_id='" + siteId
					+ "' AND district_id=" + districtId + "\n"
					+ "AND patient_id > 1000 " + "ORDER BY patient_id";
			i = 1000;
		} else {
			if (siteTruncated.equals("12")) { // Chawama
				selectPatientIdSql = "SELECT DISTINCT(patient_id) "
						+ "FROM district_id " + "WHERE site_id='" + siteId
						+ "' AND district_id=" + districtId + "\n"
						+ "AND patient_id > 1000 " + "ORDER BY patient_id";
				i = 1000;
			} else if (siteTruncated.equals("14")) { // Chilenji
				selectPatientIdSql = "SELECT DISTINCT(patient_id) "
						+ "FROM district_id " + "WHERE site_id='" + siteId
						+ "' AND district_id=" + districtId + "\n"
						+ "AND patient_id > 200 " + "ORDER BY patient_id";
				i = 200;
			} else if (siteTruncated.equals("17")) { // George
				selectPatientIdSql = "SELECT DISTINCT(patient_id) "
						+ "FROM district_id " + "WHERE site_id='" + siteId
						+ "' AND district_id=" + districtId + "\n"
						+ "AND patient_id > 799 " + "ORDER BY patient_id";
				i = 799;
			} else if (siteTruncated.equals("19")) { // Kalingalinga
				selectPatientIdSql = "SELECT DISTINCT(patient_id) "
						+ "FROM district_id " + "WHERE site_id='" + siteId
						+ "' AND district_id=" + districtId + "\n"
						+ "AND patient_id > 800 " + "ORDER BY patient_id";
				i = 800;
			} else if (siteTruncated.equals("20")) { // Kamwala
				selectPatientIdSql = "SELECT DISTINCT(patient_id) "
						+ "FROM district_id " + "WHERE site_id='" + siteId
						+ "' AND district_id=" + districtId + "\n"
						+ "AND patient_id > 1000 " + "ORDER BY patient_id";
				i = 1000;
			} else if (siteTruncated.equals("21")) { // Kanyama
				selectPatientIdSql = "SELECT DISTINCT(patient_id) "
						+ "FROM district_id " + "WHERE site_id='" + siteId
						+ "' AND district_id=" + districtId + "\n"
						+ "AND patient_id > 2600 " + "ORDER BY patient_id";
				i = 2600;
			} else if (siteTruncated.equals("22")) { // Kaunda Square
				selectPatientIdSql = "SELECT DISTINCT(patient_id) "
						+ "FROM district_id " + "WHERE site_id='" + siteId
						+ "' AND district_id=" + districtId + "\n"
						+ "AND patient_id > 1000 " + "ORDER BY patient_id";
				i = 1000;
			} else if (siteTruncated.equals("23")) { // Lilayi
				selectPatientIdSql = "SELECT DISTINCT(patient_id) "
					+ "FROM district_id " + "WHERE site_id='" + siteId
					+ "' AND district_id=" + districtId + "\n"
					+ "AND patient_id > 1000 " + "ORDER BY patient_id";
				i = 1000;
			} else if (siteTruncated.equals("24")) { // Makeni
				selectPatientIdSql = "SELECT DISTINCT(patient_id) "
						+ "FROM district_id " + "WHERE site_id='" + siteId
						+ "' AND district_id=" + districtId + "\n"
						+ "AND patient_id > 1000 " + "ORDER BY patient_id";
				i = 1000;
			} else if (siteTruncated.equals("25")) { // Mandevu
				selectPatientIdSql = "SELECT DISTINCT(patient_id) "
						+ "FROM district_id " + "WHERE site_id='" + siteId
						+ "' AND district_id=" + districtId + "\n"
						+ "AND patient_id > 1000 " + "ORDER BY patient_id";
				i = 1000;
			} else if (siteTruncated.equals("32")) { // State Lodge
				selectPatientIdSql = "SELECT DISTINCT(patient_id) "
					+ "FROM district_id " + "WHERE site_id='" + siteId
					+ "' AND district_id=" + districtId + "\n"
					+ "AND patient_id > 1000 " + "ORDER BY patient_id";
				i = 1000;
			} else {
				selectPatientIdSql = "SELECT DISTINCT(patient_id) "
						+ "FROM district_id " + "WHERE site_id='" + siteId
						+ "' AND district_id=" + districtId + " "
						+ "ORDER BY patient_id";
			}
		}

		values = new ArrayList();
		Statement stmt = null;
		ResultSet rs = null;
		String patientIdfill = null;
		try {
			stmt = conn.createStatement();
			// Don't do this in Derby - not necessary
    		if (Constants.DATABASE_TYPE.equals("mysql")) {
    			stmt.execute(startSql);
    		}
			rs = stmt.executeQuery(selectPatientIdSql);
			int patient_id = 0;
			while (rs.next()) {
				i++;
				patient_id = rs.getInt("patient_id");
				if (patient_id != i) {
					newPatientId = i;
					break;
				}
			}
			if (newPatientId == 0) {
				newPatientId = i + 1;
			}
			patientId = String.valueOf(newPatientId);
	        save(conn, siteId, districtId.longValue(), patientId);
			conn.commit();
			int len = patientId.length();
			int fillWidth = 5 - len;
			StringBuffer buffer = new StringBuffer(fillWidth + len);
			int j = 0;
			while (j < fillWidth) {
				buffer.append('0');
				j++;
			}
			buffer.append(patientId);
			patientIdfill = buffer.toString();
		} catch (SQLException e) {
			log.error(e);
		} finally {
			try {
				DbUtils.close(rs);
			} finally {
				DbUtils.close(stmt);
			}
		}
		return patientIdfill;
	}

    /**
     * Assigns zeprs patient id
     * Good example of MySQL syntax for selecting the max(id) and incrementing it.
     * @deprecated unused
     * @param conn
     * @param districtId
     * @param siteId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
	public static String getMaxPatientId(Connection conn, Integer districtId, String siteId) throws SQLException, ServletException {
	    String patientId = null;
	    // Start a transaction
	    conn.setAutoCommit(false);
	    DistrictId result = null;
	    String sql;
	    ArrayList values;
	    String sql1 = "START TRANSACTION;";
	    String sql2 = "SELECT IFNULL(@A:=MAX(patient_id)+1,@A:=1) " +
	            "FROM district_id " +
	            "WHERE site_id='" + siteId + "' AND district_id=" + districtId + "; ";
	    //String sqlIf = "IF @A=0 THEN INSERT INTO district_id " +
	    //        "SET site_id='" + siteId + "', district_id=" + districtId + ", patient_id=1;";
	    String sql3 = "INSERT INTO district_id " +
	            "SET site_id='" + siteId + "', district_id=" + districtId + ", patient_id=@A;";
	    String sql4 = "SELECT MAX(patient_id) AS patientId " +
	            "FROM district_id " +
	            "WHERE site_id='" + siteId + "' AND district_id=" + districtId + ";";
	    /*log.info(sql1);
	    log.info(sql2);
	    log.info(sql3);
	    log.info(sql4);*/
	    values = new ArrayList();
	    Statement stmt = null;
	    ResultSet rs = null;
	    String patientIdfill = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.execute(sql1);
	        stmt.execute("SET @A:=0;");
	        stmt.execute(sql2);
	        stmt.execute(sql3);
	        rs = stmt.executeQuery(sql4);
	        conn.commit();
	        while (rs.next()) {
	            patientId = rs.getString("patientId");
	        }
	        int len = patientId.length();
	        int fillWidth = 5 - len;
	        StringBuffer buffer = new StringBuffer(fillWidth + len);
	        int i = 0;
	        while (i < fillWidth) {
	            buffer.append('0');
	            i++;
	        }
	        buffer.append(patientId);
	        patientIdfill = buffer.toString();
	        //result3 = result2;
	    } catch (SQLException e) {
	        // this.rethrow(e, sql, params);
	        log.error("sql1: " + sql1 + " sql2: " + sql2 + " sql3: " + sql3 + " error: " + e);
	    } finally {
	        try {
	            //close(rs);
	            DbUtils.close(rs);
	        } finally {
	            //close(stmt);
	            DbUtils.close(stmt);
	        }
	    }
	    return patientIdfill;
	}

}