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
import org.cidrz.webapp.dynasite.valueobject.Comment;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 9, 2005
 *         Time: 10:25:51 PM
 */
public class CommentDAO {

    /**
     * Get most recent comment
     * Does not include all of the fields, especially uuid-related fields.
     *
     * @param conn
     * @param patientId
     * @param status
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Object getMostRecent(Connection conn, Long patientId, Boolean status) throws SQLException, ServletException, ObjectNotFoundException {
        Object result = null;
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select c.id, c.encounter_id AS encounterId, c.patient_id AS patientId, c.pregnancy_id AS pregnancyId, " +
                "c.problem_id AS problemId, c.comment_text AS commentText, " +
                "c.action_plan AS actionPlan, c.outcome_id AS outcomeId, " +
                "c.last_modified AS 'lastModified', " +
                "c.created AS 'created', " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedBy' " +
                "from comment c, problem p, userdata.address u " +
                "where c.problem_id = p.id " +
                "and c.last_modified_by=u.nickname " +
                "and c.patient_id=? " +
                "and p.active=? " +
                "order by c.last_modified DESC";
        values.add(patientId);
        values.add(status);
        result = DatabaseUtils.getBean(conn, Comment.class, sql, values);
        return result;
    }

    /**
     * Fetch list of comments for this problem
     * Looks up lastModifiedByName
     *
     * @param conn
     * @param patientId
     * @param problemId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAllforProblemFullname(Connection conn, Long patientId, Long problemId) throws SQLException, ServletException {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        String sql = "select c.id, c.encounter_id AS encounterId, c.patient_id AS patientId, c.pregnancy_id AS pregnancyId, " +
                "c.problem_id AS problemId, c.comment_text AS commentText, " +
                "c.action_plan AS actionPlan, c.outcome_id AS outcomeId, c.last_modified AS 'lastModified', " +
                "c.created AS 'created', c.last_modified_by AS 'lastModifiedBy', c.created_by AS 'createdBy', " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedByName', c.site_id AS siteId, " +
                "c.import_comment_id AS importCommentId, " +
                "c.uuid AS uuid, c.patient_uuid AS patientUuid, c.pregnancy_uuid AS pregnancyUuid, c.problem_uuid AS problemUuid, c.outcome_uuid AS outcomeUuid, c.encounter_uuid AS encounterUuid " +
                "from comment c " +
                "LEFT JOIN userdata.address u ON c.last_modified_by=u.nickname " +
                "where c.patient_id=? " +
                "and c.problem_id=? " +
                "order by c.last_modified DESC";
        values.add(patientId);
        values.add(problemId);
        list = DatabaseUtils.getList(conn, Comment.class, sql, values);
        return list;
    }

    /**
     * Fetch list of comments for this problem
     *
     * @param conn
     * @param problemId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAllforProblem(Connection conn, Long problemId) throws SQLException, ServletException {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        String sql = "select c.id, c.encounter_id AS encounterId, c.patient_id AS patientId, c.pregnancy_id AS pregnancyId, " +
                "c.problem_id AS problemId, c.comment_text AS commentText, " +
                "c.action_plan AS actionPlan, c.outcome_id AS outcomeId, c.last_modified AS 'lastModified', " +
                "c.created AS 'created', c.last_modified_by AS 'lastModifiedBy', c.created_by AS 'createdBy', " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedByName', c.site_id AS siteId, " +
                "c.import_comment_id AS importCommentId, " +
                "c.uuid AS uuid, c.patient_uuid AS patientUuid, c.pregnancy_uuid AS pregnancyUuid, c.problem_uuid AS problemUuid, c.outcome_uuid AS outcomeUuid, c.encounter_uuid AS encounterUuid " +
                "from comment c " +
                "LEFT JOIN userdata.address u ON c.last_modified_by=u.nickname " +
                "where c.problem_id=? " +
                "order by c.last_modified DESC";
        values.add(problemId);
        list = DatabaseUtils.getList(conn, Comment.class, sql, values);
        return list;
    }

    /**
     * Fetch list of comments for this problem
     * @param conn
     * @param problemUuid
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAllforProblem(Connection conn, String problemUuid) throws SQLException, ServletException {
    	List list = new ArrayList();
    	ArrayList values = new ArrayList();
    	String sql = "select c.id, c.encounter_id AS encounterId, c.patient_id AS patientId, c.pregnancy_id AS pregnancyId, " +
    	"c.problem_id AS problemId, c.comment_text AS commentText, " +
    	"c.action_plan AS actionPlan, c.outcome_id AS outcomeId, c.last_modified AS 'lastModified', " +
    	"c.created AS 'created', c.last_modified_by AS 'lastModifiedBy', c.created_by AS 'createdBy', " +
    	"CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedByName', c.site_id AS siteId, " +
    	"c.import_comment_id AS importCommentId, " +
        "c.uuid AS uuid, c.patient_uuid AS patientUuid, c.pregnancy_uuid AS pregnancyUuid, c.problem_uuid AS problemUuid, c.outcome_uuid AS outcomeUuid, c.encounter_uuid AS encounterUuid " +
    	"from comment c " +
    	"LEFT JOIN userdata.address u ON c.last_modified_by=u.nickname " +
    	"where c.problem_uuid=? " +
    	"order by c.last_modified DESC";
    	values.add(problemUuid);
    	list = DatabaseUtils.getList(conn, Comment.class, sql, values);
    	return list;
    }


    /**
     * Fetch all comments for a patient
     * Used in record import
     *
     * @param conn
     * @param patientId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAll(Connection conn, Long patientId) throws SQLException, ServletException {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        String sql = "select c.id, c.encounter_id AS encounterId, c.patient_id AS patientId, c.pregnancy_id AS pregnancyId, " +
                "c.problem_id AS problemId, c.comment_text AS commentText, " +
                "c.action_plan AS actionPlan, c.outcome_id AS outcomeId, c.last_modified AS 'lastModified', " +
                "c.created AS 'created', c.last_modified_by AS 'lastModifiedBy', c.created_by AS 'createdBy', " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedByName', c.site_id AS siteId, " +
                "c.import_comment_id AS importCommentId, " +
                "c.uuid AS uuid, c.patient_uuid AS patientUuid, c.pregnancy_uuid AS pregnancyUuid, c.problem_uuid AS problemUuid, c.outcome_uuid AS outcomeUuid, c.encounter_uuid AS encounterUuid " +
                "from comment c " +
                "LEFT JOIN userdata.address u ON c.last_modified_by=u.nickname " +
                "where c.patient_id=? " +
                "order by c.created DESC";
        values.add(patientId);
        list = DatabaseUtils.getList(conn, Comment.class, sql, values);
        return list;
    }

    /**
     * Fetch list of comments for this outcome
     * Includes full staff name who last modified record
     *
     * @param conn
     * @param outcomeId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAllforOutcomeFullName(Connection conn, Long outcomeId) throws SQLException, ServletException {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select c.id, c.encounter_id AS encounterId, c.patient_id AS patientId, c.pregnancy_id AS pregnancyId, " +
                "c.problem_id AS problemId, c.comment_text AS commentText, " +
                "c.action_plan AS actionPlan, c.outcome_id AS outcomeId, c.last_modified AS 'lastModified', " +
                "c.created AS 'created', c.site_id AS siteId, c.last_modified_by AS 'lastModifiedBy', c.created_by AS 'createdBy', " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedByName', " +
                "c.import_comment_id AS importCommentId, " +
                "c.uuid AS uuid, c.patient_uuid AS patientUuid, c.pregnancy_uuid AS pregnancyUuid, c.problem_uuid AS problemUuid, c.outcome_uuid AS outcomeUuid, c.encounter_uuid AS encounterUuid " +
                "from comment c " +
                "LEFT JOIN (userdata.address u) ON (u.nickname = c.last_modified_by) " +
                "where c.outcome_id=? " +
                "order by c.last_modified DESC";
        values.add(outcomeId);
        list = DatabaseUtils.getList(conn, Comment.class, sql, values);
        return list;
    }

    /**
     * Fetch list of comments for this outcome
     *
     * @param conn
     * @param outcomeId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAllforOutcome(Connection conn, Long outcomeId) throws SQLException, ServletException {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select c.id, c.encounter_id AS encounterId, c.patient_id AS patientId, c.pregnancy_id AS pregnancyId, " +
                "c.problem_id AS problemId, c.comment_text AS commentText, " +
                "c.action_plan AS actionPlan, c.outcome_id AS outcomeId, c.last_modified AS 'lastModified', " +
                "c.created AS 'created', c.site_id AS siteId, c.last_modified_by AS 'lastModifiedBy', c.created_by AS 'createdBy', " +
                "c.import_comment_id AS importCommentId, " +
                "c.uuid AS uuid, c.patient_uuid AS patientUuid, c.pregnancy_uuid AS pregnancyUuid, c.problem_uuid AS problemUuid, c.outcome_uuid AS outcomeUuid, c.encounter_uuid AS encounterUuid " +
                "from comment c " +
                "where c.outcome_id=? " +
                "order by c.last_modified DESC";
        values.add(outcomeId);
        list = DatabaseUtils.getList(conn, Comment.class, sql, values);
        return list;
    }

    /**
     * Fetch list of comments for this outcome
     * @param conn
     * @param outcomeUuid
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAllforOutcome(Connection conn, String outcomeUuid) throws SQLException, ServletException {
    	List list = new ArrayList();
    	ArrayList values = new ArrayList();
    	values = new ArrayList();
    	String sql = "select c.id, c.encounter_id AS encounterId, c.patient_id AS patientId, c.pregnancy_id AS pregnancyId, " +
    	"c.problem_id AS problemId, c.comment_text AS commentText, " +
    	"c.action_plan AS actionPlan, c.outcome_id AS outcomeId, c.last_modified AS 'lastModified', " +
    	"c.created AS 'created', c.site_id AS siteId, c.last_modified_by AS 'lastModifiedBy', c.created_by AS 'createdBy', " +
    	"c.import_comment_id AS importCommentId, " +
        "c.uuid AS uuid, c.patient_uuid AS patientUuid, c.pregnancy_uuid AS pregnancyUuid, c.problem_uuid AS problemUuid, c.outcome_uuid AS outcomeUuid, c.encounter_uuid AS encounterUuid " +
    	"from comment c " +
    	"where c.outcome_uuid=? " +
    	"order by c.last_modified DESC";
    	values.add(outcomeUuid);
    	list = DatabaseUtils.getList(conn, Comment.class, sql, values);
    	return list;
    }

    /**
     * Saves a comment. Sets uuid if null.
     * @param conn
     * @param comment
     * @param userName
     * @param siteId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Long save(Connection conn, Comment comment, String userName, Long siteId) throws SQLException, ServletException {
    	if (comment.getUuid() == null) {
    		// create the uuid for the record
    		UUID uuidRand = UUID.randomUUID();
    		comment.setUuid(uuidRand.toString());
    	}
        Timestamp created = new Timestamp(System.currentTimeMillis());
        if (comment.getCreated() != null) {
            created = comment.getCreated();
        }
        Timestamp lastModified = new Timestamp(System.currentTimeMillis());
        if (comment.getLastModified() != null) {
            lastModified = comment.getLastModified();
        }
        String sql = "INSERT INTO comment " +
        "(patient_id, problem_id, comment_text, action_plan, pregnancy_id, outcome_id, " +
        "encounter_id, last_modified, created, last_modified_by, created_by, site_id, " +
        "import_comment_id, uuid, patient_uuid, pregnancy_uuid, problem_uuid, outcome_uuid, encounter_uuid) " +
        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        ArrayList values = new ArrayList();
        values.add(comment.getPatientId());
        values.add(comment.getProblemId());
        values.add(comment.getCommentText());
        values.add(comment.getActionPlan());
        values.add(comment.getPregnancyId());
        values.add(comment.getOutcomeId());
        values.add(comment.getEncounterId());
        values.add(lastModified);
        values.add(created);
        values.add(userName);
        values.add(userName);
        values.add(siteId);
        values.add(comment.getImportCommentId());
        values.add(comment.getUuid());
        values.add(comment.getPatientUuid());
        values.add(comment.getPregnancyUuid());
        values.add(comment.getProblemUuid());
        values.add(comment.getOutcomeUuid());
        values.add(comment.getEncounterUuid());
        Long commentId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return commentId;
    }

    public static String delete(Connection conn, Long patientId) {
        String result = "Comments deleted.";
        // delete the associated patient_status table
        String sql = "delete from comment where patient_id=?";
        ArrayList values = new ArrayList();
        // add infantId to values
        values.add(patientId);
        try {
            DatabaseUtils.update(conn, sql, values.toArray());
        } catch (Exception e) {
            result = "Error while deleting comments.";
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Delete comments for an outcome
     *
     * @param conn
     * @param outcomeId
     */
    public static void deleteOutcomeComments(Connection conn, Long outcomeId) {
        String sql = "delete from comment where outcome_id=?";
        ArrayList values = new ArrayList();
        values.add(outcomeId);
        try {
            DatabaseUtils.update(conn, sql, values.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete comments for an outcome
     * @param conn
     * @param uuid - outcome_uuid
     */
    public static void deleteOutcomeComments(Connection conn, String uuid) {
    	String sql = "delete from comment where outcome_uuid=?";
    	ArrayList values = new ArrayList();
    	values.add(uuid);
    	try {
    		DatabaseUtils.update(conn, sql, values.toArray());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    /**
     * Delete comments for an outcome
     *
     * @param conn
     * @param outcomeId
     */
    public static void deleteProblemComments(Connection conn, Long outcomeId) {
        String sql = "delete from comment where problem_id=?";
        ArrayList values = new ArrayList();
        // add infantId to values
        values.add(outcomeId);
        try {
            DatabaseUtils.update(conn, sql, values.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete comments for a problem
     * @param conn
     * @param uuid
     */
    public static void deleteProblemComments(Connection conn, String uuid) {
    	String sql = "delete from comment where problem_uuid=?";
    	ArrayList values = new ArrayList();
    	// add infantId to values
    	values.add(uuid);
    	try {
    		DatabaseUtils.update(conn, sql, values.toArray());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

}
