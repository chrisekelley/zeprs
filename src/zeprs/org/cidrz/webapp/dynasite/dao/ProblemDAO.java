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
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;

import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Comment;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Pregnancy;
import org.cidrz.webapp.dynasite.valueobject.Problem;
import org.cidrz.webapp.dynasite.valueobject.Site;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Jun 8, 2005
 * Time: 2:46:22 PM
 */
public class ProblemDAO {

    /**
     * Returns one record
     *
     * @param conn
     * @param problemId
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static Problem getOne(Connection conn, Long problemId) throws SQLException, ServletException, ObjectNotFoundException {
        Problem item = null;
        String sql;
        ArrayList values;
        sql = "select p.id, p.patient_id AS patientId, p.pregnancy_id AS pregnancyId, " +
                "p.problemName, p.onset_date AS onsetDate, active AS active, " +
                "p.last_modified AS 'lastModified', p.created AS 'created',  p.import_problem_id AS importProblemId, " +
            	"p.uuid AS uuid, p.patient_uuid AS patientUuid, p.pregnancy_uuid AS pregnancyUuid, " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedBy', " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedBy' " +
                "from problem p, userdata.address u " +
                "where p.last_modified_by=u.nickname " +
                "and p.id=? ";
        values = new ArrayList();
        values.add(problemId);
        item = (Problem) DatabaseUtils.getBean(conn, Problem.class, sql, values);
        return item;
    }

    /**
     * Returns one record
     * @param conn
     * @param uuid
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Problem getOne(Connection conn, String uuid) throws SQLException, ServletException, ObjectNotFoundException {
    	Problem item = null;
    	String sql;
    	ArrayList values;
    	sql = "select p.id, p.patient_id AS patientId, p.pregnancy_id AS pregnancyId, " +
    	"p.problemName, p.onset_date AS onsetDate, active AS active, " +
    	"p.last_modified AS 'lastModified', p.created AS 'created',  p.import_problem_id AS importProblemId, " +
    	"p.uuid AS uuid, p.patient_uuid AS patientUuid, p.pregnancy_uuid AS pregnancyUuid, " +
    	"CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedBy', " +
    	"CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedBy' " +
    	"from problem p, userdata.address u " +
    	"where p.last_modified_by=u.nickname " +
    	"and p.uuid=? ";
    	values = new ArrayList();
    	values.add(uuid);
    	item = (Problem) DatabaseUtils.getBean(conn, Problem.class, sql, values);
    	return item;
    }

    /**
     * Get imported problem
     * @param conn
     * @param importedProblemId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Problem getOneImported(Connection conn, Long importedProblemId) throws SQLException, ServletException, ObjectNotFoundException {
        Problem item = null;
        String sql;
        ArrayList values;
        sql = "select p.id, p.patient_id AS patientId, p.pregnancy_id AS pregnancyId,  p.problemName, " +
                "p.onset_date AS onsetDate, active AS active, " +
                "p.last_modified AS 'lastModified', p.created AS 'created', p.import_problem_id AS importProblemId, " +
            	"p.uuid AS uuid, p.patient_uuid AS patientUuid, p.pregnancy_uuid AS pregnancyUuid " +
                "from problem p " +
                "where p.import_problem_id=? ";
        values = new ArrayList();
        values.add(importedProblemId);
        item = (Problem) DatabaseUtils.getBean(conn, Problem.class, sql, values);
        return item;
    }

    /**
     * List of problems for this pregnancyR
     * esolves lastModifiedByName
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param status
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getList(Connection conn, Long patientId, Long pregnancyId, Boolean status) throws SQLException, ServletException {

        List list;
        ArrayList values = new ArrayList();
        String sql = "select p.id, p.problemName, p.onset_date AS onsetDate, active AS active, p.patient_id AS patientId, " +
                "p.last_modified AS 'lastModified', " +
                "p.created AS 'created', " +
                "p.last_modified_by AS 'lastModifiedBy', " +
                "p.created_by AS 'createdBy', p.import_problem_id AS importProblemId, " +
            	"p.uuid AS uuid, p.patient_uuid AS patientUuid, p.pregnancy_uuid AS pregnancyUuid, " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedByName' " +
                "from problem p " +
                "LEFT JOIN userdata.address u ON p.last_modified_by=u.nickname " +
                "where p.patient_id=? " +
                "and p.pregnancy_id=? " +
                "and p.active=? " +
                "order by p.last_modified DESC";
        values.add(patientId);
        values.add(pregnancyId);
        values.add(status);
        /*BeanProcessor beanprocessor = new AuditInfoBeanProcessor();
        RowProcessor convert = new ZEPRSRowProcessor(beanprocessor);*/
        list = DatabaseUtils.getList(conn, Problem.class, sql, values);
        return list;
    }

    /**
     * List of all problems for all of patient's pregnancies
     * resolves lastModifiedByName
     * @param conn
     * @param patientId
     * @return  list of problems
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAll(Connection conn, Long patientId) throws SQLException, ServletException {

        List list;
        ArrayList values = new ArrayList();
        String sql = "select p.id, p.problemName, p.onset_date AS onsetDate, active AS active, p.patient_id AS patientId, " +
                "p.last_modified AS 'lastModified', " +
                "p.created AS 'created', " +
                "p.last_modified_by AS 'lastModifiedBy', " +
                "p.created_by AS 'createdBy', p.import_problem_id AS importProblemId, " +
            	"p.uuid AS uuid, p.patient_uuid AS patientUuid, p.pregnancy_uuid AS pregnancyUuid, " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedByName' " +
                "from problem p " +
                "LEFT JOIN userdata.address u ON p.last_modified_by=u.nickname " +
                "where p.patient_id=? " +
                "order by p.created DESC";
        values.add(patientId);
        list = DatabaseUtils.getList(conn, Problem.class, sql, values);
        return list;
    }

    /**
     * Saves a problem.
     * @param conn
     * @param problem
     * @param userName
     * @param siteId
     * @param problemUuid
     * @param patientUuid
     * @param pregnancyUuid
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Long save(Connection conn, Problem problem, String userName, Long siteId, String problemUuid, String patientUuid, String pregnancyUuid) throws SQLException, ServletException {
    	Timestamp created = new Timestamp(System.currentTimeMillis());
        if (problem.getCreated() != null) {
            created = problem.getCreated();
        }
        String createdBy = userName;
        if (problem.getCreatedBy() != null) {
            createdBy = problem.getCreatedBy();
        }
        Timestamp lastModified = new Timestamp(System.currentTimeMillis());
        if (problem.getLastModified() != null) {
            lastModified = problem.getLastModified();
        }
        String lastModifiedBy = userName;
        if (problem.getLastModifiedBy() != null) {
            lastModifiedBy = problem.getLastModifiedBy();
        }
        Long currentSiteId = siteId;
        if (problem.getSiteId() != null && problem.getSiteId() != 0) {
            currentSiteId = problem.getSiteId();
        }
        ArrayList values = new ArrayList();
        String sql = "INSERT INTO problem " +
        "(patient_id, problemName, active, onset_date, pregnancy_id, last_modified, " +
        "created, last_modified_by, created_by, site_id, import_problem_id, uuid, patient_uuid, pregnancy_uuid) " +
        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        values.add(problem.getPatientId());
        values.add(problem.getProblemName());
        values.add(problem.isActive());
        values.add(problem.getOnsetDate());
        values.add(problem.getPregnancyId());
        values.add(lastModified);
        values.add(created);
        values.add(lastModifiedBy);
        values.add(createdBy);
        values.add(currentSiteId);
        values.add(problem.getImportProblemId());
        values.add(problemUuid);
        values.add(patientUuid);
        values.add(pregnancyUuid);
        Long problemId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return problemId;
    }

    public static Long update(Connection conn, Problem problem, String userName, Long siteId) throws SQLException, ServletException {
        String sql = "UPDATE problem " +
                "SET problemName='" + problem.getProblemName() +
                "', active=" + problem.isActive() +
                ", onset_date='" + problem.getOnsetDate() +
                "', last_modified='" + new Timestamp(System.currentTimeMillis()) +
                "', last_modified_by='" + userName +
                "', site_id=" + siteId +
                "  WHERE id=" + problem.getId() + ";";
        ArrayList values = new ArrayList();
        Long problemId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return problemId;
    }

    /**
     * Update timestamp on problem
     * Used when adding a new comment to a problem.
     *
     * @param conn
     * @param problemId
     * @param userName
     * @param siteId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Long touchProblem(Connection conn, Long problemId, String userName, Long siteId) throws SQLException, ServletException {
        String sql = "UPDATE problem " +
                "SET last_modified='" + new Timestamp(System.currentTimeMillis()) +
                "', last_modified_by='" + userName +
                "', site_id=" + siteId +
                "  WHERE id=" + problemId + ";";
        ArrayList values = new ArrayList();
        problemId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return problemId;
    }

    /**
     * Saves new problem and its child comment. Assigns values to uuid-related fields.
     *
     * @param conn
     * @param problem
     * @param userName
     * @param site
     * @throws SQLException
     * @throws ServletException
     */
    public static void saveNewProblem(Connection conn, Problem problem, String userName, Site site) throws Exception, ServletException {
    	// create the uuid
    	UUID uuid = UUID.randomUUID();
    	String problemUuid = uuid.toString();
        Comment comment = problem.getComment();
        Long problemId = null;
        Long pregnancyId = problem.getPregnancyId();
        Pregnancy pregnancy = PregnancyDAO.getOne(conn, pregnancyId);
        Patient patient = PatientDAO.getOne(conn, problem.getPatientId());
        String patientUuid = patient.getUuid();
    	String pregnancyUuid = pregnancy.getUuid();
        problemId = ProblemDAO.save(conn, problem, userName, site.getId(), problemUuid, patientUuid, pregnancyUuid);
        //Long commentId = null;
        comment.setProblemId(problemId);
        comment.setPatientUuid(patientUuid);
        comment.setPregnancyUuid(pregnancyUuid);
        comment.setProblemUuid(problemUuid);
        UUID uuid2 = UUID.randomUUID();
    	String commentUuid = uuid2.toString();
        comment.setUuid(commentUuid);
        CommentDAO.save(conn, comment, userName, site.getId());
        PatientStatusDAO.touchPatientStatus(conn, null, userName, site.getId(), problem.getPatientId());
    }

    public static String delete(Connection conn, Long patientId) {
        String result = "Problems deleted.";
        // delete the associated patient_status table
        String sql = "delete from problem where patient_id=?";
        ArrayList values = new ArrayList();
        // add infantId to values
        values.add(patientId);
        try {
            DatabaseUtils.update(conn, sql, values.toArray());
        } catch (Exception e) {
            result = "Error while deleting Problems.";
            e.printStackTrace();
        }
        return result;
    }

    /**
     * delete a single Problem
     * @param conn
     * @param id
     * @return status message
     */
    public static String deleteOne(Connection conn, Long id) {
        String result = "Problem deleted.";
        Statement stmt;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.execute("START TRANSACTION;");
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
            String sql = "delete from problem where id=" + id;
            stmt.execute(sql);
            stmt.execute("Commit");
        } catch (Exception e) {
            result = "Error while deleting Problem.";
            e.printStackTrace();
        }
        return result;
    }

    /**
     * delete a single Problem
     * @param conn
     * @param uuid
     * @return
     */
    public static String deleteOne(Connection conn, String uuid) {
    	String result = "Problem deleted.";
    	Statement stmt;
    	try {
    		conn.setAutoCommit(false);
    		stmt = conn.createStatement();
    		stmt.execute("START TRANSACTION;");
    		stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
    		String sql = "delete from problem where uuid=" + uuid;
    		stmt.execute(sql);
    		stmt.execute("Commit");
    	} catch (Exception e) {
    		result = "Error while deleting Problem.";
    		e.printStackTrace();
    	}
    	return result;
    }

    /**
     * Checks if an imported Problem exists
     * @param conn
     * @param patientId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Long checkImportedProblem(Connection conn, Long patientId, Long outcomeId) throws SQLException, ServletException, ObjectNotFoundException {
        Long id = null;
        Problem item = null;
        String sql;
        ArrayList values;
        sql = "select id from problem where patient_id=? and import_problem_id=?";
        values = new ArrayList();
        values.add(patientId);
        values.add(outcomeId);
        item = (Problem) DatabaseUtils.getZEPRSBean(conn, Problem.class, sql, values);
        id = item.getId();
        return id;
    }

}
