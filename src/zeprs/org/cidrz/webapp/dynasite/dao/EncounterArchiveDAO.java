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

import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.EncounterDataArchive;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 31, 2006
 *         Time: 1:31:39 PM
 */
public class EncounterArchiveDAO {

     /**
     * The table encounter_archive stores a record of deleted records. It's mostly a copy of the record from encounter.
     * Fetch all of the records in encounter_archive.
     * @param conn
     * @param patientId
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
                "created_site_id AS createdSiteId, import_encounter_id AS importEncounterId, " +
               "date_deleted AS dateDeleted, deleted_by AS deletedBy, uuid " +
                "FROM encounter_archive " +
                "WHERE patient_id=? " +
                "ORDER BY created";
        ArrayList values = new ArrayList();
        values.add(patientId);
        items = DatabaseUtils.getList(conn, EncounterDataArchive.class, sql, values);
        return items;
    }

    /**
     * Copies encounter record for safe-keeping before record deletion.
     * @param conn
     * @param userName
     * @param vo
     * @param dateDeleted
     * @return
     * @throws java.sql.SQLException
     */
    public static Long saveArchive(Connection conn, String userName, EncounterData vo, Timestamp dateDeleted) throws SQLException {
        ArrayList values;
        values = new ArrayList();
        String sql = "INSERT INTO encounter_archive " +
        "(id, patient_id , form_id, flow_id, date_visit, pregnancy_id, last_modified, created, " +
        "last_modified_by, created_by, site_id, created_site_id, import_encounter_id, " +
        "date_deleted, deleted_by, uuid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        values.add(vo.getId());
        values.add(vo.getPatientId());
        values.add(vo.getFormId());
        values.add(vo.getFlowId());
        values.add(vo.getDateVisit());
        values.add(vo.getPregnancyId());
        values.add(vo.getLastModified());
        values.add(vo.getCreated());
        values.add(vo.getLastModifiedBy());
        values.add(vo.getCreatedBy());
        values.add(vo.getSiteId());
        values.add(vo.getCreatedSiteId());
        values.add(vo.getImportEncounterId());
        values.add(dateDeleted);
        values.add(userName);
        values.add(vo.getUuid());
        Long id = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return id;
    }


}
