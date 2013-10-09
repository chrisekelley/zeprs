/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * Created on Mar 30, 2005
 *
 */
package org.cidrz.project.zeprs.report;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.report.valueobject.DrugsDispensed;
import org.cidrz.project.zeprs.valueobject.report.gen.DrugInterventionReport;

/**
 * @author ericl
 */

public class ZEPRSUtils {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(ZEPRSUtils.class);

    /**
     * getAgeAtVisit()
     *
     * @param dateVisit
     * @param birthDate
     * @return The age of the patient on the visit date
     */
    public static int getAgeAtVisit(Date dateVisit, Date birthDate) {

        long difference = dateVisit.getTime() - birthDate.getTime();

        // return (int) (difference / (60 * 60 * 24 * 365 * 1000));
        return (int) (difference / 1471228928);
    }

    protected static ResultSet getChildren(int mothersPatientID, Connection conn) throws ServletException {

        // Connection conn = null;
        ResultSet rs = null;

        try {
            // conn = getZEPRSConnection();

            // Retrieve all Encounter records for this form
            String sql = "SELECT id FROM patient WHERE parent_id = ?";
            // log.debug("Infant SQL: SELECT id FROM patient WHERE parent_id =" + mothersPatientID);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mothersPatientID);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            throw new ServletException("Cannot retrieve database connection", ex);
        }

        return rs;
    }
    protected static ResultSet getChildrenCount(int mothersPatientID, Connection conn) throws ServletException {

        // Connection conn = null;
        ResultSet rs = null;

        try {
            // conn = getZEPRSConnection();

            // Retrieve all Encounter records for this form
            String sql = "SELECT COUNT(id) FROM patient WHERE parent_id = ?";
            // log.debug("Infant SQL: SELECT id FROM patient WHERE parent_id =" + mothersPatientID);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mothersPatientID);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            throw new ServletException("Cannot retrieve database connection", ex);
        }

        return rs;
    }

    /**
     * @return A Connection to the database
     * @throws ServletException
     */
    protected static Connection getZEPRSConnection() throws ServletException {

        Connection conn = null;
        try {
            Context initCtx = new InitialContext();
            DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/zeprsDB");
            try {
                conn = ds.getConnection();
            } catch (SQLException e1) {
                log.error(e1);
            }
        } catch (NamingException e) {
        }

        return conn;

    }

    public static int getCurrentAge(Date birthDate) {

        Calendar cal = new GregorianCalendar();
        long now = cal.getTimeInMillis();

        long ageInMillis = now - birthDate.getTime();

        // for some strange reason, dividing by the number of millis
        // in a year directly does not produce the proper result, so
        // instead I divide by the number of millis in a day, then
        // divide again by the number of days in a year
        Long age = new Long((ageInMillis / (1000 * 60 * 60 * 24)) / 365);

        return age.intValue();
    }

    public static int getNewbornAge(Date birthDate) {

        Calendar cal = new GregorianCalendar();
        long now = cal.getTimeInMillis();

        long ageInMillis = now - birthDate.getTime();

        // for some strange reason, dividing by the number of millis
        // in a year directly does not produce the proper result, so
        // instead I divide by the number of millis in a day, then
        // divide again by the number of days in a year
        Long age = new Long((ageInMillis / (1000 * 60 * 60 * 24)));

        return age.intValue();
    }

    /**
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @return Returns the name of the month, or months representing
     *         the time frame of this report
     */
    protected static String getReportMonth(Date beginDate, Date endDate) {

        Calendar calBeginDate = new GregorianCalendar();
        Calendar calEndDate = new GregorianCalendar();

        String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        String reportMonths = null;

        // set month and year
        calBeginDate.setTime(beginDate);
        calEndDate.setTime(endDate);

        int beginMonth = calBeginDate.get(Calendar.MONTH);
        int endMonth = calEndDate.get(Calendar.MONTH);

        if (beginMonth == endMonth) {
            reportMonths = months[beginMonth];
        } else {
            reportMonths = months[beginMonth];
            for (int i = beginMonth + 1; i <= endMonth; i++) {
                if (i == endMonth) {
                    reportMonths = reportMonths + " - " + months[i];
                }
            }
        }

        return reportMonths;
    }

    /**
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @return Returns the year (in string format) representing the time frame of this report
     */
    protected static String getReportYear(Date beginDate, Date endDate) {

        Calendar calBeginDate = new GregorianCalendar();
        Calendar calEndDate = new GregorianCalendar();

        calBeginDate.setTime(beginDate);
        calEndDate.setTime(endDate);

        return (String.valueOf(calEndDate.get(Calendar.YEAR)));
    }

    /**
     * @param patientID The id associated with this patient
     * @return Retuns a RecordSet containing all Encounters for this patient
     * @throws ServletException
     */
    protected static ResultSet getPatientEncounters(int patientID, int siteID) throws ServletException {

        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = getZEPRSConnection();

            // Retrieve all Encounter records for this form
            String sql = "SELECT * FROM encounter WHERE patient_id = ? AND site_id = ? ORDER BY date_visit";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, patientID);
            ps.setInt(2, siteID);

            rs = ps.executeQuery();
        } catch (Exception ex) {
            throw new ServletException("Cannot retrieve database connection", ex);
        }

        return rs;
    }

    /**
     * @param patientID The id associated with this patient
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @return Returns all Encounters associated with this patient during the time frame
     * @throws ServletException
     */
    protected static ResultSet getPatientEncounters(int patientID, Date beginDate, Date endDate, int siteID) throws ServletException {

        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = getZEPRSConnection();

            // Retrieve all Encounter records for this form
            String sql = "SELECT * FROM encounter WHERE patient_id = ? " +
                    "AND date_visit >= ? AND date_visit <= ? AND site_id = ? ORDER BY date_visit";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, patientID);
            ps.setDate(2, beginDate);
            ps.setDate(3, endDate);
            ps.setInt(4, siteID);

            rs = ps.executeQuery();
        } catch (Exception ex) {
            throw new ServletException("Cannot retrieve database connection", ex);
        }

        return rs;
    }

    /**
     * This can accept 0 for siteId, which happens when user selects "All sites" in reports.
     * @param formID    The id of the form
     * @param table     The table that stores the form's submissions
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param groupBy   Use groupby to fetch only one record per patient?
     * @param conn
     * @param groupBy
     * @return Returns a ResultSet containing all patient Encounters pertaining
     *         to this form during the time frame
     * @throws ServletException
     */
    protected static ResultSet getEncounters(int formID, String table, Date beginDate, Date endDate, int siteID, Connection conn, boolean groupBy) throws ServletException {

        // Connection conn = null;
        ResultSet rs = null;

        try {

            // Retrieve all Encounter records for this form
            String sql = "SELECT * FROM encounter," + table + " WHERE encounter.id = " + table + ".id " +
                    "AND form_id = ? AND date_visit >= ? AND date_visit <= ? ";
            if (siteID != 0) {
                sql = sql + "AND site_id = ?";
            }
            if (groupBy) {
                sql = sql + " GROUP BY patient_id";
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Integer.toString(formID));
            ps.setDate(2, beginDate);
            ps.setDate(3, endDate);
            if (siteID != 0) {
                ps.setInt(4, siteID);
            }
            rs = ps.executeQuery();
        } catch (Exception ex) {
            throw new ServletException("Cannot retrieve database connection", ex);
        }
        return rs;
    }

    /**
     * This search all UTH sites
     * @param table     The table that stores the form's submissions
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param groupBy   Use groupby to fetch only one record per patient?
     * @param conn
     * @param groupBy
     * @return Returns a ResultSet containing all patient Encounters pertaining
     *         to this form during the time frame
     * @throws ServletException
     */
    protected static ResultSet getEncountersUth(String table, Date beginDate, Date endDate,  Connection conn, boolean groupBy) throws ServletException {

        ResultSet rs = null;

        try {

            // Retrieve all Encounter records for this form
            String sql = "SELECT * FROM " + table + " " +
                    "JOIN encounter ON encounter.id = " +  table + ".id " +
                    "JOIN site ON site.id = encounter.site_id " +
                    "AND date_visit >= ? AND date_visit <= ? " +
                    "AND site_type_id = 2";
            if (groupBy) {
                sql = sql + " GROUP BY patient_id";
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, beginDate);
            ps.setDate(2, endDate);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        return rs;
    }

    /**
     * This can accept 0 for siteId, which happens when user selects "All sites" in reports.
     * @param formID    The id of the form
     * @param table     The table that stores the form's submissions
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param conn
     * @return Returns a ResultSet containing all patient Encounters pertaining
     *         to this form during the time frame. Orders by date_visit, which is useful for monthly reports
     * @throws ServletException
     */
    protected static ResultSet getEncounters(int formID, String table, Date beginDate, Date endDate, int siteID, Connection conn) throws ServletException {

        ResultSet rs = null;

        try {

            // Retrieve all Encounter records for this form
            String sql = "SELECT * FROM encounter," + table + " WHERE encounter.id = " + table + ".id " +
                    "AND form_id = ? AND date_visit >= ? AND date_visit <= ? ";
            if (siteID != 0) {
                sql = sql + "AND site_id = ?";
            }
                sql = sql + " ORDER BY date_visit";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Integer.toString(formID));
            ps.setDate(2, beginDate);
            ps.setDate(3, endDate);
            if (siteID != 0) {
                ps.setInt(4, siteID);
            }
            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.error(ex);
        }
        return rs;
    }

    /**
     * Gets encounters for mother only.
     * This can accept 0 for siteId, which happens when user selects "All sites" in reports.
     * @param formID    The id of the form
     * @param table     The table that stores the form's submissions
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param conn
     * @return Returns a ResultSet containing all patient Encounters pertaining
     *         to this form during the time frame. Orders by date_visit, which is useful for monthly reports
     * @throws ServletException
     */
    protected static ResultSet getEncountersMother(int formID, String table, Date beginDate, Date endDate, int siteID, Connection conn) throws ServletException {

        ResultSet rs = null;

        try {

            // Retrieve all Encounter records for this form
            String sql = "SELECT * FROM patient, encounter," + table + " " +
                    "WHERE encounter.id = " + table + ".id " +
                    "AND encounter.patient_id = patient.id " +
                    "AND patient.parent_id IS NULL " +
                    "AND form_id = ? AND date_visit >= ? AND date_visit <= ? ";
            if (siteID != 0) {
                sql = sql + "AND encounter.site_id = ?";
            }
                sql = sql + " ORDER BY date_visit";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Integer.toString(formID));
            ps.setDate(2, beginDate);
            ps.setDate(3, endDate);
            if (siteID != 0) {
                ps.setInt(4, siteID);
            }
            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.error(ex);
        }
        return rs;
    }

    /**
     * Gets encounters for children only.
     * This can accept 0 for siteId, which happens when user selects "All sites" in reports.
     * @param formID    The id of the form
     * @param table     The table that stores the form's submissions
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param conn
     * @return Returns a ResultSet containing all patient Encounters pertaining
     *         to this form during the time frame. Orders by date_visit, which is useful for monthly reports
     * @throws ServletException
     */
    protected static ResultSet getEncountersChildren(int formID, String table, Date beginDate, Date endDate, int siteID, Connection conn) throws ServletException {

        ResultSet rs = null;

        try {

            // Retrieve all Encounter records for this form
            String sql = "SELECT * FROM patient, encounter," + table + " " +
                    "WHERE encounter.id = " + table + ".id " +
                    "AND encounter.patient_id = patient.id " +
                    "AND patient.parent_id IS NOT NULL " +
                    "AND form_id = ? AND date_visit >= ? AND date_visit <= ? ";
            if (siteID != 0) {
                sql = sql + "AND encounter.site_id = ?";
            }
                sql = sql + " ORDER BY date_visit";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Integer.toString(formID));
            ps.setDate(2, beginDate);
            ps.setDate(3, endDate);
            if (siteID != 0) {
                ps.setInt(4, siteID);
            }
            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.error(ex);
        }
        return rs;
    }

    /**
     * This can accept 0 for siteId, which happens when user selects "All sites" in reports.
     * @param formID    The id of the form
     * @param sql     sql for the query
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param conn
     * @return Returns a ResultSet containing all patient Encounters pertaining
     *         to this form during the time frame. Orders by date_visit, which is useful for monthly reports
     */
    protected static ResultSet getEncounters(String sql, int formID, Date beginDate, Date endDate, int siteID, Connection conn) {

        ResultSet rs = null;

        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Integer.toString(formID));
            ps.setDate(2, beginDate);
            ps.setDate(3, endDate);
            if (siteID != 0) {
                ps.setInt(4, siteID);
            }
            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.error(ex);
        }
        return rs;
    }

    /**
     * @param formID    The id of the form
     * @param table     The table that stores the form's submissions
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param groupBy   Specify groupby
     * @param conn
     * @return Returns a ResultSet containing all patient Encounters pertaining
     *         to this form during the time frame
     */
    protected static ResultSet getEncountersGroupBy(int formID, String table, Date beginDate, Date endDate, int siteID, String groupBy, Connection conn) {

        ResultSet rs = null;
            // Retrieve all Encounter records for this form
        try {
            String sql = "SELECT * FROM encounter," + table + " WHERE encounter.id = " + table + ".id\n" +
                    "AND form_id = ? AND date_visit >= ? AND date_visit <= ? AND site_id = ?\n" +
                    "GROUP BY " + groupBy + " ORDER BY " + groupBy;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Integer.toString(formID));
            ps.setDate(2, beginDate);
            ps.setDate(3, endDate);
            ps.setInt(4, siteID);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * This is a special version of this query appropriate for forms like safe motherhood that have many date entry fields.
     *
     * @param formID    The id of the form
     * @param table     The table that stores the form's submissions
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param dateField The date field to use for the query
     * @param conn
     * @return Returns a ResultSet containing all patient Encounters pertaining
     *         to this form during the time frame.
     * @throws ServletException
     */
    protected static ResultSet getEncounters(int formID, String table, Date beginDate, Date endDate, int siteID, String dateField, Connection conn) throws ServletException {

        // Connection conn = null;
        ResultSet rs = null;

        try {
            // conn = getZEPRSConnection();

            // Retrieve all Encounter records for this form
            String sql = "SELECT * FROM encounter," + table + " WHERE encounter.id = " + table + ".id " +
                    "AND form_id = ? " +
                    "AND " + table + "." + dateField + ">= ?" +
                    "AND " + table + "." + dateField + "<= ?" +
                    "AND site_id = ? " +
                    "GROUP BY patient_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Integer.toString(formID));
            ps.setDate(2, beginDate);
            ps.setDate(3, endDate);
            ps.setInt(4, siteID);

            rs = ps.executeQuery();
        } catch (Exception ex) {
            // throw new ServletException("Cannot retrieve database connection", ex);
            log.error(ex);
        }

        return rs;
    }

    /**
     * This is a special version of this query appropriate for forms like safe motherhood that have many date entry fields.
     * This one lets you check two date fields.
     *
     * @param formID     The id of the form
     * @param table      The table that stores the form's submissions
     * @param beginDate  The first date of the report's time frame
     * @param endDate    The last date of the report's time frame
     * @param dateField1 The date field to use for the query
     * @param conn
     * @return Returns a ResultSet containing all patient Encounters pertaining
     *         to this form during the time frame.
     * @throws ServletException
     */
    protected static ResultSet getEncounters(int formID, String table, Date beginDate, Date endDate, int siteID, String dateField1, String dateField2, Connection conn) throws ServletException {

        // Connection conn = null;
        ResultSet rs = null;

        try {
            // conn = getZEPRSConnection();

            // Retrieve all Encounter records for this form
            String sql = "SELECT * FROM encounter," + table + " " +
                    "WHERE encounter.id = " + table + ".id " +
                    "AND form_id = ? " +
                    "AND site_id = ? " +
                    "AND ((" + table + "." + dateField1 + ">= '" + beginDate +
                    "' AND " + table + "." + dateField1 + "<= ' " + endDate +
                    "') OR (" + table + "." + dateField2 + ">= '" + beginDate +
                    "' AND " + table + "." + dateField2 + "<= '" + endDate + "')) " +
                    "GROUP BY patient_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Integer.toString(formID));
            ps.setInt(2, siteID);
            /*ps.setDate(2, beginDate);
           ps.setDate(3, endDate);
           ps.setDate(4, beginDate);
           ps.setDate(5, endDate);*/


            rs = ps.executeQuery();
        } catch (Exception ex) {
            // throw new ServletException("Cannot retrieve database connection", ex);
            log.error(ex);
        }

        return rs;
    }

    /**
     * @param patientID The id associated with this patient
     * @param formID    The id associated a particular form
     * @param table     The table that stores this form's submissions
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param conn
     * @return Returns a Resultset containing all Encounters associated with
     *         this patient and form during the time frame. Order by date_visit - useful for monthly reports
     * @throws ServletException
     */
    public static ResultSet getPatientEncounters(int patientID, int formID, String table, Date beginDate, Date endDate, int siteID, Connection conn) throws ServletException {

        // Connection conn = null;
        ResultSet rs = null;

        try {
            // conn = getZEPRSConnection();

            // Retrieve all Encounter records for this form
            String sql = "SELECT * FROM encounter," + table + " WHERE encounter.id = " + table + ".id " +
                    "AND form_id = ? AND patient_id = ? AND date_visit >= ? AND date_visit <= ? AND site_id = ? " +
                    "ORDER BY date_visit";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, formID);
            ps.setInt(2, patientID);
            ps.setDate(3, beginDate);
            ps.setDate(4, endDate);
            ps.setInt(5, siteID);

            rs = ps.executeQuery();
        } catch (Exception ex) {
            throw new ServletException("Cannot retrieve database connection", ex);
        }

        return rs;
    }

    /**
     * Takes pregnancyId
     * @param patientID The id associated with this patient
     * @param pregnancyId   The pregnancyId associated with this patient
     * @param formID    The id associated a particular form
     * @param table     The table that stores this form's submissions
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param conn
     * @return Returns a Resultset containing all Encounters associated with
     *         this patient, pregnancy and form during the time frame
     * @throws ServletException
     */
    public static ResultSet getPatientEncounters(int patientID, int pregnancyId, int formID, String table, Date beginDate, Date endDate, Connection conn) throws ServletException {

        // Connection conn = null;
        ResultSet rs = null;

        try {
            // Retrieve all Encounter records for this form
            String sql = "SELECT * FROM encounter," + table +
                    " WHERE encounter.id = " + table + ".id \n" +
                    "AND form_id = ? AND patient_id = ? \n" +
                    "AND date_visit >= ? AND date_visit <= ? \n" +
                    "AND pregnancy_id = ? \n" +
                    "ORDER BY date_visit";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, formID);
            ps.setInt(2, patientID);
            ps.setDate(3, beginDate);
            ps.setDate(4, endDate);
            ps.setInt(5, pregnancyId);

            rs = ps.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    /**
     * Returns records for this form/patient
     * @param patientId
     * @param pregnancyId
     * @param formID
     * @param table
     * @param conn
     * @return numVisits
     */
    public static ResultSet getPatientEncounters(int patientId, int pregnancyId, int formID, String table, Connection conn) {

        // Connection conn = null;
        ResultSet rs = null;

        try {
            // Retrieve count of Encounter records for this form
            String sql = "SELECT * \n" +
                    "FROM encounter," + table +
                    " WHERE encounter.id = " + table + ".id \n" +
                    "AND form_id = ? AND patient_id = ? \n" +
                    "AND pregnancy_id = ? \n" +
                    "ORDER BY date_visit";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, formID);
            ps.setInt(2, patientId);
            ps.setInt(3, pregnancyId);

            rs = ps.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }


    public static Date getFirstVisit(int patientId, int pregnancyId, int formID, Connection conn) {

        Date dateVisit = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT date_visit FROM encounter\n" +
                    "WHERE form_id = ? \n" +
                    "AND patient_id = ? \n" +
                    "AND pregnancy_id = ? \n" +
                    "ORDER BY date_visit\n" +
                    "LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, formID);
            ps.setInt(2, patientId);
            ps.setInt(3, pregnancyId);

            rs = ps.executeQuery();
            while (rs.next()) {
                dateVisit = rs.getDate("date_visit");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dateVisit;
    }

    /**
     * Returns count of visits for this form and site - can be used to calculate re-visits
     * @param patientId
     * @param pregnancyId
     * @param formID
     * @param siteId
     * @param table
     * @param conn
     * @return count of visits for this form and site
     */
    public static ResultSet getPatientEncounterCount(int patientId, int pregnancyId, int siteId, int formID, String table, Connection conn) {

        // Connection conn = null;
        ResultSet rs = null;

        try {
            // Retrieve count of Encounter records for this form
            String sql = "SELECT COUNT(encounter.id) AS numVisits FROM encounter," + table +
                    " WHERE encounter.id = " + table + ".id \n" +
                    "AND form_id = ? AND patient_id = ? \n" +
                    "AND pregnancy_id = ?  AND site_id = ? \n" +
                    "ORDER BY date_visit";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, formID);
            ps.setInt(2, patientId);
            ps.setInt(3, pregnancyId);
            ps.setInt(4, siteId);

            rs = ps.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    /**
     * No pregnancyId or siteId
     * @param patientID The id associated with this patient
     * @param formID    The id associated a particular form
     * @param table     The table that stores this form's submissions
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param conn
     * @return Returns a Resultset containing all Encounters associated with
     *         this patient, pregnancy and form during the time frame
     * @throws ServletException
     */
    public static ResultSet getPatientEncounters(int patientID, int formID, String table, Date beginDate, Date endDate, Connection conn) throws ServletException {

        // Connection conn = null;
        ResultSet rs = null;

        try {
            // Retrieve all Encounter records for this form
            String sql = "SELECT * FROM encounter," + table +
                    " WHERE encounter.id = " + table + ".id \n" +
                    "AND form_id = ? AND patient_id = ? \n" +
                    "AND date_visit >= ? AND date_visit <= ? \n" +
                    "ORDER BY date_visit";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, formID);
            ps.setInt(2, patientID);
            ps.setDate(3, beginDate);
            ps.setDate(4, endDate);

            rs = ps.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    /**
     * No beginDate/endDate/siteId/pregnancyId
     * Used for forms that happen only once - like newborn eval
     * @param patientID The id associated with this patient
     * @param table     The table that stores this form's submissions
     * @param conn
     * @return Returns a Resultset containing all Encounters associated with
     *         this patient, pregnancy and form during the time frame
     */
    public static ResultSet getPatientEncounters(int patientID, String table, Connection conn) {

        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM encounter," + table +
                    " WHERE encounter.id = " + table + ".id \n" +
                    "AND patient_id = ? \n" +
                    "ORDER BY date_visit";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, patientID);

            rs = ps.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    /**
     * Provide the encounterId
     * @param encounterId
     * @param table
     * @param conn
     * @return a single encounter
     */
    public static ResultSet getEncounterById(Long encounterId, String table, Connection conn) {

        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM encounter," + table +
                    " WHERE encounter.id = " + table + ".id \n" +
                    "AND encounter.id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, encounterId);

            rs = ps.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    /**
     * @param patientId The id associated with this patient
     * @param pregnancyId The id associated with this patient
     * @param conn
     * @return Returns a Resultset containing all Encounters associated with
     *         this patient and form during the pregnancy. Should not restrict to site or date
     * @throws ServletException
     */
    public static ResultSet getPregnancyEncounters(Connection conn, int patientId, Long pregnancyId ) throws ServletException {

        ResultSet rs = null;

        try {
            // Retrieve all Encounter records for this form
            // String sql = "SELECT * FROM encounter WHERE patient_id = ? AND pregnancy_id=? ORDER BY date_visit";
            String sql = "SELECT * FROM encounter WHERE patient_id = ? ORDER BY date_visit";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, patientId);
            ps.setLong(2, pregnancyId);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            throw new ServletException("Cannot retrieve db connection", ex);
        }

        return rs;
    }

    /**
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param siteID   The site id
     * @param conn
     * @return Returns all Encounters for the time frame and site
     */
    protected static ResultSet getEncounters(Date beginDate, Date endDate, int siteID, Connection conn) {

        ResultSet rs = null;
        int count = 0;
        try {
            // Retrieve all Encounter records for this time period
            String sql = "SELECT e.id, e.patient_id, e.form_id, e.last_modified, e.created, e.last_modified_by, " +
                    "e.created_by, e.site_id, e.flow_id, e.date_visit, e.pregnancy_id, e.referral_id, p.parent_id, p.district_patient_id\n" +
                    "FROM encounter e, patient p\n" +
                    "WHERE e.patient_id = p.id\n" +
                    "AND date_visit >= ? AND date_visit <= ? AND e.site_id = ?\n" +
                    "ORDER BY date_visit\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, beginDate);
            ps.setDate(2, endDate);
            ps.setInt(3, siteID);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param siteID   The site id
     * @param conn
     * @return Returns a count of all Encounters pertaining to this form for the time frame and site
     */
    protected static int getEncountersCount(Date beginDate, Date endDate, int siteID, Connection conn) {

        // Connection conn = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // conn = getZEPRSConnection();

            // Retrieve all Encounter records for this time period
            String sql = "SELECT COUNT(DISTINCT(date_visit), patient_id) AS visits\n" +
                    "FROM encounter\n" +
                    "WHERE date_visit >= ? AND date_visit <= ? AND site_id = ?\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, beginDate);
            ps.setDate(2, endDate);
            ps.setInt(3, siteID);

            rs = ps.executeQuery();

            while (rs.next()) {
                //count = count + rs.getDate(1);
                count = rs.getInt("visits");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * @param formID    The id associated with the form
     * @param table     The table that stores this form's submissions
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param siteID   The site id
     * @param conn
     * @return Returns a count of all Encounters pertaining to this form for the time frame and site
     * @throws ServletException
     */
    protected static int getEncountersCount(int formID, String table, Date beginDate, Date endDate, int siteID, Connection conn) throws ServletException {

        // Connection conn = null;
        ResultSet rs = null;
        int count = 0;

        try {
            if (siteID == 0) {
                String sql = "SELECT count(*) FROM encounter\n" +
                        "WHERE form_id = ? AND date_visit >= ? AND date_visit <= ?\n" +
                        "GROUP BY patient_id";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, Integer.toString(formID));
                ps.setDate(2, beginDate);
                ps.setDate(3, endDate);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT count(*) FROM encounter\n" +
                        "WHERE form_id = ? AND date_visit >= ? AND date_visit <= ? AND site_id = ?\n" +
                        "GROUP BY patient_id";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, Integer.toString(formID));
                ps.setDate(2, beginDate);
                ps.setDate(3, endDate);
                ps.setInt(4, siteID);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                count = count + rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * @param formID    The id associated with the form
     * @param table     The table that stores this form's submissions
     * @param patientId   The patient id
     * @param conn  The connection
     * @return Returns a count of all Encounters pertaining to this form for the time frame, patient, and site
     */
    protected static int getEncountersCount(int formID, String table, int patientId, Connection conn) {

        ResultSet rs = null;
        int count = 0;

        try {

            // Retrieve all Encounter records for this form
            String sql = "SELECT count(*) AS records FROM encounter," + table +
                    " WHERE encounter.id = " + table + ".id " +
                    "AND form_id = ? " +
                    "AND patient_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, formID);
            ps.setInt(2, patientId);

            try {
                rs = ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            while (rs.next()) {
                 count = rs.getInt("records");
                // count = count + rs.getInt(1);
            }
            rs.close();
        } catch (Exception ex) {
             log.error(ex);
        }

        return count;
    }

    /**
     * @param formID    The id associated with the form
     * @param table     The table that stores this form's submissions
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param patientId   The patient id
     * @param conn  The connection
     * @return Returns the clinics pertaining to this form for the time frame and patient.
     */
    protected static ResultSet getEncounterClinics(int formID, String table, Date beginDate, Date endDate, int patientId, Connection conn) {

        ResultSet rs = null;
        int count = 0;

        try {

            // Retrieve all Encounter records for this form
            String sql = "SELECT site_id, site_name " +
                    "FROM encounter, site, " + table +
                    " WHERE encounter.id = " + table + ".id " +
                    "AND site.id = encounter.site_id " +
                    "AND form_id = ? " +
                //    "AND date_visit >= ? AND date_visit <= ? " +
                    "AND patient_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, formID);
          //  ps.setDate(2, beginDate);
         //   ps.setDate(3, endDate);
            ps.setInt(2, patientId);

            rs = ps.executeQuery();

           /* while (rs.next()) {
                count = count + rs.getInt(1);
            }
            rs.close();*/
        } catch (Exception ex) {
            log.error(ex);
            // throw new ServletException("Cannot retrieve database connection", ex);
        }

        return rs;
    }

    /**
     * @param ZEPRSForm The name of the form
     * @return Returns the id associated with this form
     * @throws ServletException
     */
    protected static int getFormID(String ZEPRSForm) throws ServletException {

        Connection conn = null;
        ResultSet rs = null;
        int id = 0;

        try {

            conn = getZEPRSConnection();

            // Determine the form id for the requested form
            String sql = "SELECT id FROM form WHERE label = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ZEPRSForm);
            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception ex) {
            throw new ServletException("Cannot retrieve database connection", ex);
        }

        return id;
    }

    /**
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param conn
     * @return Returns a ResultSet containing patient_ids representing all
     *         patients who had at least one Encounter during the time frame
     * @throws ServletException
     */
    protected static ResultSet getUniqueVisits(Date beginDate, Date endDate, int siteID, Connection conn) throws ServletException {

        //Connection conn = null;
        ResultSet rs = null;

        try {
            // conn = getZEPRSConnection();

            // Retrieve all Encounter records for this form for mothers

            if (siteID == 0) {
                String sql = "SELECT DISTINCT patient_id FROM encounter, patient " +
                        "WHERE encounter.patient_id = patient.id AND date_visit >= ? " +
                        "AND date_visit <= ? AND parent_id is null ";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT DISTINCT patient_id FROM encounter, patient " +
                        "WHERE encounter.patient_id = patient.id AND date_visit >= ? " +
                        "AND date_visit <= ? AND parent_id is null AND encounter.site_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                ps.setInt(3, siteID);
                rs = ps.executeQuery();
            }
        } catch (Exception ex) {
            log.error(ex);
        }

        return rs;
    }

    /**
     * Get MCH mothers - not in labour, having submitted a routine ante visit
     * @param beginDate
     * @param endDate
     * @param siteID
     * @param conn
     * @return
     * @throws ServletException
     */
    protected static ResultSet getMCHMothers(Date beginDate, Date endDate, int siteID, Connection conn) throws ServletException {

        //Connection conn = null;
        ResultSet rs = null;

        try {
            // conn = getZEPRSConnection();

            // Retrieve all Encounter records for this form for mothers
            // No longer checking if mother is in flow_id = 7 - Labour - because problem/laobur visit is in that flow

            if (siteID == 0) {
                String sql = "SELECT DISTINCT patient_id FROM encounter, patient " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND patient_id NOT IN (SELECT patient_id from encounter where (flow_id = 3) OR (flow_id =4))\n" +
                        "AND patient_id IN (SELECT patient_id from encounter where flow_id = 1)\n" +
                        "AND date_visit >= ? " +
                        "AND date_visit <= ? AND parent_id is null " +
                        "ORDER BY date_visit DESC";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT DISTINCT patient_id FROM encounter, patient " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND patient_id NOT IN (SELECT patient_id from encounter where (flow_id = 3) OR (flow_id =4))\n" +
                        "AND patient_id IN (SELECT patient_id from encounter where flow_id = 1)\n" +
                        "AND date_visit >= ? " +
                        "AND date_visit <= ? AND parent_id is null AND encounter.site_id = ? " +
                        "ORDER BY date_visit DESC";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                ps.setInt(3, siteID);
                rs = ps.executeQuery();
            }
        } catch (Exception ex) {
            log.error(ex);
        }

        return rs;
    }

    /**
     * Only return mothers who have had thier first visit.
     * @param beginDate
     * @param endDate
     * @param siteID
     * @param conn
     * @return
     * @throws ServletException
     */
    protected static ResultSet getMCHMothersSingleVisit(Date beginDate, Date endDate, int siteID, Connection conn) throws ServletException {

        //Connection conn = null;
        ResultSet rs = null;

        try {
            // conn = getZEPRSConnection();

            // Retrieve all Encounter records for this form for mothers
            // No longer checking if mother is in flow_id = 7 - Labour - because problem/laobur visit is in that flow

            if (siteID == 0) {
                String sql = "SELECT DISTINCT patient_id, COUNT(encounter.id) AS cnt " +
                        "FROM encounter, patient " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND patient_id NOT IN (SELECT patient_id from encounter where (flow_id = 3) OR (flow_id =4))\n" +
                        "AND patient_id IN (SELECT patient_id from encounter where flow_id = 1)\n" +
                        "AND date_visit >= ? " +
                        "AND date_visit <= ? AND parent_id is null " +
                        "AND form_id=80 " +
                        "GROUP BY encounter.patient_id";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT DISTINCT patient_id, COUNT(encounter.id) AS cnt " +
                        "FROM encounter, patient " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND patient_id NOT IN (SELECT patient_id from encounter where (flow_id = 3) OR (flow_id =4))\n" +
                        "AND patient_id IN (SELECT patient_id from encounter where flow_id = 1)\n" +
                        "AND date_visit >= ? " +
                        "AND date_visit <= ? AND parent_id is null AND encounter.site_id = ? " +
                        "AND form_id=80 " +
                        "GROUP BY encounter.patient_id";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                ps.setInt(3, siteID);
                rs = ps.executeQuery();
            }
        } catch (Exception ex) {
            log.error(ex);
        }

        return rs;
    }

    protected static ResultSet getPostnatalMothers(Date beginDate, Date endDate, int siteID, Connection conn) throws ServletException {

        ResultSet rs = null;

        try {
            if (siteID == 0) {
                String sql = "SELECT DISTINCT patient_id FROM encounter, patient " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND patient_id IN (SELECT patient_id from encounter where (flow_id = 3) OR (flow_id =4))\n" +
                        "AND patient_id IN (SELECT patient_id from encounter where flow_id = 1)\n" +
                        "AND date_visit >= ? " +
                        "AND date_visit <= ? AND parent_id is null ";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT DISTINCT patient_id FROM encounter, patient " +
                        "WHERE encounter.patient_id = patient.id " +
                        "AND patient_id IN (SELECT patient_id from encounter where (flow_id = 3) OR (flow_id =4))\n" +
                        "AND patient_id IN (SELECT patient_id from encounter where flow_id = 1)\n" +
                        "AND date_visit >= ? " +
                        "AND date_visit <= ? AND parent_id is null AND encounter.site_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                ps.setInt(3, siteID);
                rs = ps.executeQuery();
            }
        } catch (Exception ex) {
            log.error(ex);
        }

        return rs;
    }

    /**
     * @param beginDate The first date of the report's time frame
     * @param endDate   The last date of the report's time frame
     * @param conn
     * @return Returns a ResultSet containing patient_ids representing all
     *         patients who delivered during the time frame
     * @throws ServletException
     */
    protected static ResultSet getDeliveredMothers(Date beginDate, Date endDate, int siteID, Connection conn) throws ServletException {

        //Connection conn = null;
        ResultSet rs = null;

        try {
            // conn = getZEPRSConnection();

            // Retrieve all Encounter records for this form for mothers

            if (siteID == 0) {
                String sql = "SELECT DISTINCT patient_id FROM encounter, patient " +
                        "WHERE encounter.patient_id = patient.id AND date_visit >= ? " +
                        "AND date_visit <= ? AND parent_id is null " +
                        "AND encounter.form_id=66";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT DISTINCT patient_id FROM encounter, patient " +
                        "WHERE encounter.patient_id = patient.id AND date_visit >= ? " +
                        "AND date_visit <= ? AND parent_id is null AND encounter.site_id = ? " +
                        "AND encounter.form_id=66";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
                ps.setInt(3, siteID);
                rs = ps.executeQuery();
            }
        } catch (Exception ex) {
            log.error(ex);
        }

        return rs;
    }

    /**
     * Removed check for form_id=7, because it includes problem/labour visits, which may not be labour.
     * @param patientId Patient id
     * @param conn
     * @return Returns ResultSet if patient fits this criteria as labour patient
     */
    protected static ResultSet checkLabourStatus(Connection conn, Long patientId) {

        ResultSet rs = null;
        try {
            String sql = "SELECT flow_id, id\n" +
                    "FROM encounter\n" +
                    "WHERE ((flow_id = 3) OR (flow_id =4)))\n" +
                    "AND site_id=1\n" +
                    "AND patient_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, patientId);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.error(ex);
        }

        return rs;
    }

    protected static ResultSet getDailyReflexReport(Connection conn, Long siteId, Date beginDate, Date endDate) {

        ResultSet rs = null;
        String condition = "";
        if (siteId != null && siteId > 0) {
            condition = "and encounter.site_id =?\n";
        }
        try {
            String sql = "SELECT DISTINCT(patient.id) AS patient_id, patient.district_patient_id,\n" +
                    "CONCAT_WS(' ',patient.first_name,patient.surname) AS patient_name,\n" +
                    "fe1.enumeration AS who_stage, fe2.enumeration AS referred_art_clinic,\n" +
                    "fe3.enumeration AS regimen, regimen_visit_date, cd4.cd4count, hgb.resultsNumeric AS hgb_result,\n" +
                    "encounter.date_visit AS datevisit\n" +
                    "FROM zeprs.patient, zeprs.patient_status, zeprs.encounter\n" +
                    "LEFT JOIN zeprs.arvregimen ON encounter.id = arvregimen.id\n" +
                    "LEFT JOIN admin.field_enumeration fe1 on fe1.id = arvregimen.who_stage\n" +
                    "LEFT JOIN admin.field_enumeration fe2 on fe2.id = arvregimen.referred_art_clinic\n" +
                    "LEFT JOIN admin.field_enumeration fe3 on fe3.id = arvregimen.regimen\n" +
                    "LEFT JOIN zeprs.labtest cd4 ON encounter.id = cd4.id AND cd4.labType = 3042\n" +
                    "LEFT JOIN zeprs.labtest hgb ON encounter.id = hgb.id AND (hgb.labType = 2925 OR hgb.labType = 2926)\n" +
                    "WHERE patient.id = patient_status.id\n" +
                    "AND patient.id = encounter.patient_id\n" +
                    "AND patient.hiv_positive > 0\n" + condition +
                    "AND encounter.date_visit >= ?\n" +
                    "AND encounter.date_visit <= ?\n" +
                    "ORDER BY encounter.date_visit DESC, patient.surname, patient.first_name;";
            PreparedStatement ps = conn.prepareStatement(sql);
            if (siteId > 0) {
                ps.setLong(1, siteId);
                ps.setDate(2, beginDate);
                ps.setDate(3, endDate);
            } else {
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
            }

            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.error(ex);
        }

            return rs;
        }

    /**
     * Get most recent hiv report record for patient id.
     * @param conn
     * @param patientId
     * @return
     */
    protected static ResultSet getOneHivReport(Connection conn, Long patientId) {

        ResultSet rs = null;
        String condition = "";

        try {
            String sql = "SELECT ega_weeks, date_next_visit\n" +
                    "FROM zeports.hiv_report\n" +
                    "WHERE zeports.hiv_report.id = ?\n" +
                    "ORDER BY encounter_date DESC\n" +
            		"LIMIT 1 ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, patientId.intValue());
            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.error(ex);
        }

            return rs;
        }


    /**
     * Fetch all records for a patient from the hiv_report table, which is generated by a stored procedure nightly in order
     * to reduce load on the db server when this report is requested.
     * @param conn
     * @param patientId
     * @return ResultSet containing records from hiv_report with enumerations resolved.
     */
    protected static ResultSet getHivReportRecords(Connection conn, Long patientId) {

        ResultSet rs = null;
        String condition = "";

        try {
            String sql = "SELECT district_patient_id, patient_name, encounter_date, cd4_done, cd4_date, cd4_result,\n" +
                    "hgb_date, hgb_result, regimen_visit_date, who_screen, referral_to_art, pmtct_regimen, ega_weeks,\n" +
                    "date_next_visit, hiv_report.site_id, encounter_id, lab_type,\n" +
                    "fe1.enumeration AS who_stage,\n" +
                    "fe2.enumeration AS referred_art_clinic,\n" +
                    "fe3.enumeration AS regimen,\n" +
                    "regimen_visit_date\n" +
                    "FROM zeports.hiv_report\n" +
                    "LEFT JOIN admin.field_enumeration fe1 on fe1.id = hiv_report.who_screen\n" +
                    "LEFT JOIN admin.field_enumeration fe2 on fe2.id = hiv_report.referral_to_art\n" +
                    "LEFT JOIN admin.field_enumeration fe3 on fe3.id = hiv_report.pmtct_regimen\n" +
                    "WHERE zeports.hiv_report.id = ?\n" +
                    "ORDER BY encounter_date DESC;\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, patientId.intValue());
            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.error(ex);
        }

        return rs;
    }

    /**
     * Fetches date_next_appt value from routineante table
     * @param conn
     * @param patientId
     * @param dateVisit
     * @return ResultSet
     */
    protected static ResultSet getNextAncVisitDate(Connection conn, Long patientId, Date dateVisit) {

        ResultSet rs = null;
        String condition = "";

        try {
            String sql = "SELECT date_next_appt\n" +
                    "FROM zeprs.routineante, zeprs.encounter\n" +
                    "WHERE encounter.id = routineante.id\n" +
                    "AND encounter.patient_id = ?\n" +
                    "AND encounter.date_visit = ?\n" +
                    "LIMIT 1;\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, patientId.intValue());
            ps.setDate(2, dateVisit);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.error(ex);
        }
        return rs;
    }

    /**
     * Query patients who had Counsel visits during this time period.
     * @param conn
     * @param siteId
     * @param beginDate
     * @param endDate
     * @return counselvisit
     */
    protected static ResultSet getPatientsWithCounselVisits(Connection conn, Long siteId, Date beginDate, Date endDate) {

        ResultSet rs = null;
        String condition = "";
        if (siteId != null && siteId > 0) {
            condition = "and encounter.site_id =?\n";
        }

        try {
            String sql = "SELECT DISTINCT(patient.id) AS patient_id, patient.district_patient_id,\n" +
                    "CONCAT_WS(' ',patient.first_name,patient.surname) AS patient_name,\n" +
                    "encounter.date_visit AS datevisit, testDate, fe1.enumeration AS hiv_result\n" +
                    "FROM zeprs.patient, zeprs.patient_status, zeprs.encounter, zeprs.smcounselingvisit\n" +
                    "LEFT JOIN admin.field_enumeration fe1 on fe1.id = smcounselingvisit.hiv_test_result\n" +
                    "WHERE patient.id = patient_status.id\n" +
                    "AND patient.id = encounter.patient_id\n" +
                    "AND encounter.id = smcounselingvisit.id\n" +
                    //"AND patient.hiv_positive > 0 \n" + condition +
                    "AND hiv_test_result = 2940\n" +
                    condition +
                    "AND encounter.date_visit >= ?\n" +
                    "AND encounter.date_visit <= ?\n" +
                    "ORDER BY encounter.date_visit DESC, patient.surname, patient.first_name;\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            if (siteId > 0) {
                ps.setLong(1, siteId);
                ps.setDate(2, beginDate);
                ps.setDate(3, endDate);
            } else {
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
            }
            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.error(ex);
        }
        return rs;
    }

    /**
     * Fetch most recent arv regimen visit
     * @param conn
     * @param patientId
     * @return arvregimen visit
     */
    protected static ResultSet getArvVisit(Connection conn, Long patientId) {

        ResultSet rs = null;

        try {
            String sql = "SELECT regimen_visit_date, cd4tested, " +
                    "fe1.enumeration AS who_stage, fe2.enumeration AS referred_art_clinic,\n" +
                    "fe3.enumeration AS regimen\n, enrolled_in_art, site.site_name\n" +
                    "FROM zeprs.encounter, zeprs.arvregimen\n" +
                    "LEFT JOIN admin.field_enumeration fe1 on fe1.id = arvregimen.who_stage\n" +
                    "LEFT JOIN admin.field_enumeration fe2 on fe2.id = arvregimen.referred_art_clinic\n" +
                    "LEFT JOIN admin.field_enumeration fe3 on fe3.id = arvregimen.regimen\n" +
                    "LEFT JOIN admin.site site on site.id = arvregimen.clinic_enrolled_art\n" +
                    "WHERE encounter.id = arvregimen.id\n" +
                    "AND encounter.patient_id = ?\n" +
                    "ORDER BY encounter.date_visit DESC\n" +
                    "LIMIT 1;\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, patientId);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.error(ex);
        }
        return rs;
    }

    /**
     * Fetches all ARV visits - arvregimen table
     * @param conn
     * @param patientId
     * @return
     */
    protected static ResultSet getArvVisits(Connection conn, Long patientId) {

    	ResultSet rs = null;

    	try {
    		String sql = "SELECT regimen_visit_date, cd4tested, " +
    		"fe1.enumeration AS who_stage, fe2.enumeration AS referred_art_clinic,\n" +
    		"fe3.enumeration AS regimen\n, enrolled_in_art, site.site_name\n" +
    		"FROM zeprs.encounter, zeprs.arvregimen\n" +
    		"LEFT JOIN admin.field_enumeration fe1 on fe1.id = arvregimen.who_stage\n" +
    		"LEFT JOIN admin.field_enumeration fe2 on fe2.id = arvregimen.referred_art_clinic\n" +
    		"LEFT JOIN admin.field_enumeration fe3 on fe3.id = arvregimen.regimen\n" +
    		"LEFT JOIN admin.site site on site.id = arvregimen.clinic_enrolled_art\n" +
    		"WHERE encounter.id = arvregimen.id\n" +
    		"AND encounter.patient_id = ?\n" +
    		"ORDER BY encounter.date_visit ASC;\n";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setLong(1, patientId);
    		rs = ps.executeQuery();
    	} catch (Exception ex) {
    		log.error(ex);
    	}
    	return rs;
    }

    /**
     * Get list of previous ARV regimens
     * @param conn
     * @param patientId
     * @return
     */
    protected static ResultSet getPreviousArvRegimen(Connection conn, Long patientId) {

    	ResultSet rs = null;

    	try {
    		String sql = "SELECT fe.enumeration AS regimen\n" +
    		"FROM zeprs.encounter, zeprs.arvregimen\n" +
    		"LEFT JOIN admin.field_enumeration fe on fe.id = arvregimen.regimen\n" +
    		"WHERE encounter.id = arvregimen.id\n" +
    		"AND encounter.patient_id = ?\n" +
    		"ORDER BY encounter.date_visit DESC\n";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setLong(1, patientId);
    		rs = ps.executeQuery();
    	} catch (Exception ex) {
    		log.error(ex);
    	}
    	return rs;
    }

    /**
     * Fetch a list of AZT ARV regimen_visit_dates. Get the first one.
     * @param conn
     * @param patientId
     * @return
     */
    protected static ResultSet getAztRegimens(Connection conn, Long patientId) {

    	ResultSet rs = null;

    	try {
    		String sql = "SELECT regimen_visit_date AS regimen_visit_date\n" +
    		"FROM zeprs.encounter, zeprs.arvregimen\n" +
    		"WHERE encounter.id = arvregimen.id\n" +
    		"AND encounter.patient_id = ?\n" +
    		"AND arvregimen.regimen = 3221\n" +
    		"ORDER BY regimen_visit_date DESC\n";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setLong(1, patientId);
    		rs = ps.executeQuery();
    	} catch (Exception ex) {
    		log.error(ex);
    	}
    	return rs;
    }

    /**
     * Fetch a list of AZT ARV regimen_visit_dates. Get the first one.
     * @param conn
     * @param patientId
     * @return
     */
    protected static ResultSet getFirstAztRegimen(Connection conn, Long patientId) {

    	ResultSet rs = null;

    	try {
    		String sql = "SELECT regimen_visit_date AS regimen_visit_date\n" +
    		"FROM zeprs.encounter, zeprs.arvregimen\n" +
    		"WHERE encounter.id = arvregimen.id\n" +
    		"AND encounter.patient_id = ?\n" +
    		"AND (arvregimen.regimen = 3221 OR arvregimen.regimen = 3223)\n" +
    		"ORDER BY regimen_visit_date ASC\n" +
    		"LIMIT 1";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setLong(1, patientId);
    		rs = ps.executeQuery();
    	} catch (Exception ex) {
    		log.error(ex);
    	}
    	return rs;
    }


    /**
     * Get most recent CD4 lab test
     * @param conn
     * @param patientId
     * @return  ResultSet w/ dateLabRequest and cd4count
     */
    protected static ResultSet getRecentCD4Lab(Connection conn, Long patientId) {

        ResultSet rs = null;

        try {
            String sql = "SELECT dateLabRequest, cd4count\n" +
                    "FROM zeprs.encounter, zeprs.labtest\n" +
                    "WHERE encounter.id = labtest.id\n" +
                    "AND labType = 3042\n" +
                   // "AND labtest.cd4count is not null\n" +
                    "AND encounter.patient_id = ?\n" +
                    "ORDER BY encounter.date_visit DESC\n" +
                    "LIMIT 1;\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, patientId);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.error(ex);
        }
        return rs;
    }

    /**
     * Return list of patients where cd4 count <= 350 or is null
     * 9/9/09: Changed from 250 to 350 upon request from CIDRZ (Taniya.Tembo@cidrz.org)
     * @param conn
     * @param siteId
     * @param beginDate
     * @param endDate
     * @return
     */
    protected static ResultSet getLowCD4Patients(Connection conn, Long siteId, Date beginDate, Date endDate) {
    	ResultSet rs = null;
        String condition = "";
        if (siteId != null && siteId > 0) {
            //condition = "and encounter.site_id =?\n";
            condition = "and ((encounter.site_id =47 AND patient_status.site_id =?) OR (encounter.site_id =?))\n";
        }
    	try {
    		String sql = "SELECT DISTINCT(patient.id) AS patient_id, patient.district_patient_id,\n" +
            "CONCAT_WS(' ',patient.first_name,patient.surname) AS patient_name, dateLabRequest, " +
            "cd4count, patient_id\n" +
    		"FROM zeprs.patient, zeprs.encounter, zeprs.labtest, zeprs.patient_status\n" +
    		"WHERE encounter.id = labtest.id\n" +
            "AND patient.id = encounter.patient_id\n" +
            "AND patient.id = patient_status.id\n" +
    		"AND labType = 3042\n" +
    		"AND (cd4count <=350 OR cd4count IS NULL)\n" +
    		condition +
            "AND encounter.date_visit >= ?\n" +
            "AND encounter.date_visit <= ?\n" +
            "ORDER BY encounter.date_visit DESC, patient.surname, patient.first_name;\n";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		if (siteId > 0) {
                ps.setLong(1, siteId);
                ps.setLong(2, siteId);
                ps.setDate(3, beginDate);
                ps.setDate(4, endDate);
            } else {
                ps.setDate(1, beginDate);
                ps.setDate(2, endDate);
            }
    		rs = ps.executeQuery();
    	} catch (Exception ex) {
    		log.error(ex);
    	}
    	return rs;
    }

    /**
     * Gets most recent Hgb lab test
     * @param conn
     * @param patientId
     * @return ResultSet: hgb_date, hgb_result (dateLabRequest and resultsNumeric)
     */
     protected static ResultSet getRecentHgbLab(Connection conn, Long patientId) {

        ResultSet rs = null;

        try {
            String sql = "SELECT dateLabRequest, resultsNumeric\n" +
                    "FROM zeprs.encounter, zeprs.labtest\n" +
                    "WHERE encounter.id = labtest.id\n" +
                    "and (labType = 2925 OR labType = 2926)\n" +
                    "AND encounter.patient_id = ?\n" +
                    "ORDER BY encounter.date_visit DESC\n" +
                    "LIMIT 1;\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, patientId);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.error(ex);
        }
        return rs;
    }

    /**
     * Get first HIV positive test
     * @param conn
     * @param patientId
     * @return testDate - date of positive hiv test
     */
    protected static ResultSet getHivPositiveResult(Connection conn, Long patientId) {

        ResultSet rs = null;

        try {
            String sql = "SELECT testDate\n" +
                    "FROM zeprs.encounter, zeprs.smcounselingvisit\n" +
                    "WHERE encounter.id = smcounselingvisit.id\n" +
                    "AND hiv_test_result = '2940'\n" +
                    "AND encounter.patient_id = ?\n" +
                    "ORDER BY encounter.date_visit ASC\n" +
                    "LIMIT 1;\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, patientId);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            log.error(ex);
        }
        return rs;
    }

	/**
	 * Assembles DrugsDispensed record
	 * @param drugInterventionReport
	 * @return drugsDispensed
	 */
	public static DrugsDispensed assembleDrugsDispensed(DrugInterventionReport drugInterventionReport) {
		DrugsDispensed drugsDispensed = new DrugsDispensed();
		drugsDispensed.setId(drugInterventionReport.getId());
		if (drugInterventionReport.getDateDrugInterventionR() != null) {
			drugsDispensed.setDateDispensed(Date.valueOf(drugInterventionReport.getDateDrugInterventionR()));
		}
		boolean isDispensed = false;
		if (drugInterventionReport.getDispensedR() != null && drugInterventionReport.getDispensedR().equals("Yes")) {
			if (drugInterventionReport.getDrugTypeR() != null) {
				isDispensed = true;
				if (drugInterventionReport.getDrugTypeR().equals("Supplemental Iron")) {
					drugsDispensed.setIron(true);
				} else if (drugInterventionReport.getDrugTypeR().equals("Supplemental Folate")) {
					drugsDispensed.setFolic(true);
				} else if (drugInterventionReport.getDrugTypeR().equals("Deworming Medication")) {
					drugsDispensed.setDeworming(true);
				} else if (drugInterventionReport.getDrugTypeR().equals("Malaria SP1")) {
					drugsDispensed.setMalariaSp("SP1");
				} else if (drugInterventionReport.getDrugTypeR().equals("Malaria SP2")) {
					drugsDispensed.setMalariaSp("SP2");
				} else if (drugInterventionReport.getDrugTypeR().equals("Malaria SP3")) {
					drugsDispensed.setMalariaSp("SP3");
				} else if (drugInterventionReport.getDrugTypeR().equals("Multivitamin")) {
					drugsDispensed.setOther("Multivitamin");
				} else if (drugInterventionReport.getDrugTypeR().equals("Other")) {
					drugsDispensed.setOther("Other");
				}
			}
			if (drugInterventionReport.getDrug_type2R() != null) {
				isDispensed = true;
				if (drugInterventionReport.getDrug_type2R().equals("Supplemental Iron")) {
					drugsDispensed.setIron(true);
				} else if (drugInterventionReport.getDrug_type2R().equals("Supplemental Folate")) {
					drugsDispensed.setFolic(true);
				} else if (drugInterventionReport.getDrug_type2R().equals("Deworming Medication")) {
					drugsDispensed.setDeworming(true);
				} else if (drugInterventionReport.getDrug_type2R().equals("Malaria SP1")) {
					drugsDispensed.setMalariaSp("SP1");
				} else if (drugInterventionReport.getDrug_type2R().equals("Malaria SP2")) {
					drugsDispensed.setMalariaSp("SP2");
				} else if (drugInterventionReport.getDrug_type2R().equals("Malaria SP3")) {
					drugsDispensed.setMalariaSp("SP3");
				} else if (drugInterventionReport.getDrug_type2R().equals("Multivitamin")) {
					drugsDispensed.setOther("Multivitamin");
				} else if (drugInterventionReport.getDrug_type2R().equals("Other")) {
					drugsDispensed.setOther("Other");
				}
			}
			if (drugInterventionReport.getDrug_type3R() != null) {
				isDispensed = true;
				if (drugInterventionReport.getDrug_type3R().equals("Supplemental Iron")) {
					drugsDispensed.setIron(true);
				} else if (drugInterventionReport.getDrug_type3R().equals("Supplemental Folate")) {
					drugsDispensed.setFolic(true);
				} else if (drugInterventionReport.getDrug_type3R().equals("Deworming Medication")) {
					drugsDispensed.setDeworming(true);
				} else if (drugInterventionReport.getDrug_type3R().equals("Malaria SP1")) {
					drugsDispensed.setMalariaSp("SP1");
				} else if (drugInterventionReport.getDrug_type3R().equals("Malaria SP2")) {
					drugsDispensed.setMalariaSp("SP2");
				} else if (drugInterventionReport.getDrug_type3R().equals("Malaria SP3")) {
					drugsDispensed.setMalariaSp("SP3");
				} else if (drugInterventionReport.getDrug_type3R().equals("Multivitamin")) {
					drugsDispensed.setOther("Multivitamin");
				} else if (drugInterventionReport.getDrug_type3R().equals("Other")) {
					drugsDispensed.setOther("Other");
				}
			}
			if (drugInterventionReport.getDrug_type4R() != null) {
				isDispensed = true;
				if (drugInterventionReport.getDrug_type4R().equals("Supplemental Iron")) {
					drugsDispensed.setIron(true);
				} else if (drugInterventionReport.getDrug_type4R().equals("Supplemental Folate")) {
					drugsDispensed.setFolic(true);
				} else if (drugInterventionReport.getDrug_type4R().equals("Deworming Medication")) {
					drugsDispensed.setDeworming(true);
				} else if (drugInterventionReport.getDrug_type4R().equals("Malaria SP1")) {
					drugsDispensed.setMalariaSp("SP1");
				} else if (drugInterventionReport.getDrug_type4R().equals("Malaria SP2")) {
					drugsDispensed.setMalariaSp("SP2");
				} else if (drugInterventionReport.getDrug_type4R().equals("Malaria SP3")) {
					drugsDispensed.setMalariaSp("SP3");
				} else if (drugInterventionReport.getDrug_type4R().equals("Multivitamin")) {
					drugsDispensed.setOther("Multivitamin");
				} else if (drugInterventionReport.getDrug_type4R().equals("Other")) {
					drugsDispensed.setOther("Other");
				}
			}
			if (drugInterventionReport.getDrug_type5R() != null) {
				isDispensed = true;
				if (drugInterventionReport.getDrug_type5R().equals("Supplemental Iron")) {
					drugsDispensed.setIron(true);
				} else if (drugInterventionReport.getDrug_type5R().equals("Supplemental Folate")) {
					drugsDispensed.setFolic(true);
				} else if (drugInterventionReport.getDrug_type5R().equals("Deworming Medication")) {
					drugsDispensed.setDeworming(true);
				} else if (drugInterventionReport.getDrug_type5R().equals("Malaria SP1")) {
					drugsDispensed.setMalariaSp("SP1");
				} else if (drugInterventionReport.getDrug_type5R().equals("Malaria SP2")) {
					drugsDispensed.setMalariaSp("SP2");
				} else if (drugInterventionReport.getDrug_type5R().equals("Malaria SP3")) {
					drugsDispensed.setMalariaSp("SP3");
				} else if (drugInterventionReport.getDrug_type5R().equals("Multivitamin")) {
					drugsDispensed.setOther("Multivitamin");
				} else if (drugInterventionReport.getDrug_type5R().equals("Other")) {
					drugsDispensed.setOther("Other");
				}
			}
			if (drugInterventionReport.getDrug_type6R() != null) {
				isDispensed = true;
				if (drugInterventionReport.getDrug_type6R().equals("Supplemental Iron")) {
					drugsDispensed.setIron(true);
				} else if (drugInterventionReport.getDrug_type6R().equals("Supplemental Folate")) {
					drugsDispensed.setFolic(true);
				} else if (drugInterventionReport.getDrug_type6R().equals("Deworming Medication")) {
					drugsDispensed.setDeworming(true);
				} else if (drugInterventionReport.getDrug_type6R().equals("Malaria SP1")) {
					drugsDispensed.setMalariaSp("SP1");
				} else if (drugInterventionReport.getDrug_type6R().equals("Malaria SP2")) {
					drugsDispensed.setMalariaSp("SP2");
				} else if (drugInterventionReport.getDrug_type6R().equals("Malaria SP3")) {
					drugsDispensed.setMalariaSp("SP3");
				} else if (drugInterventionReport.getDrug_type6R().equals("Multivitamin")) {
					drugsDispensed.setOther("Multivitamin");
				} else if (drugInterventionReport.getDrug_type6R().equals("Other")) {
					drugsDispensed.setOther("Other");
				}
			}
			if (drugInterventionReport.getCommentsR() != null) {
				drugsDispensed.setOther(" Comments:" + drugInterventionReport.getCommentsR());
			}
		}
		if (isDispensed) {
			return drugsDispensed;
		} else {
			return null;
		}
	}


}
