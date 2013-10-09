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
import org.cidrz.webapp.dynasite.valueobject.Problem;
import org.cidrz.webapp.dynasite.valueobject.ProblemArchive;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Apr 4, 2006
 *         Time: 6:41:25 AM
 */
public class ProblemArchiveDAO {

    /**
     * Save problem archive record
     * @param conn
     * @param problem
     * @param userName
     * @param siteId
     * @return id of new record
     * @throws SQLException
     */
    public static Long save(Connection conn, Problem problem, String userName, Long siteId) throws SQLException {
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
        String sql = "INSERT INTO problem_archive " +
        "(id, patient_id, problemName, active, onset_date, pregnancy_id, last_modified, " +
        "created, last_modified_by, created_by, site_id, import_problem_id, date_deleted, deleted_by, uuid, patient_uuid, pregnancy_uuid) " +
        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        values.add(problem.getId());
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
        values.add(new Timestamp(System.currentTimeMillis()));
        values.add(userName);
        values.add(problem.getUuid());
        values.add(problem.getPatientUuid());
        values.add(problem.getPregnancyUuid());
        Long problemId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return problemId;
    }

    /**
     * List of all problem deletions for all of patient's pregnancies
     *
     * @param conn
     * @param patientId
     * @return list of problems
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAll(Connection conn, Long patientId) throws SQLException, ServletException {

        List list;
        ArrayList values = new ArrayList();
        String sql = "select p.id, p.problemName, p.onset_date AS onsetDate, active AS active, " +
                "p.patient_id AS patientId, " +
                "p.last_modified AS 'lastModified', " +
                "p.created AS 'created', " +
                "p.last_modified_by AS 'lastModifiedBy', " +
                "p.created_by AS 'createdBy', p.uuid AS uuid, p.patient_uuid AS patientUuid, p.pregnancy_uuid AS pregnancyUuid " +
                "from problem_archive p " +
                "where p.patient_id=? " +
                "order by p.created DESC";
        values.add(patientId);
        list = DatabaseUtils.getList(conn, ProblemArchive.class, sql, values);
        return list;
    }
}
