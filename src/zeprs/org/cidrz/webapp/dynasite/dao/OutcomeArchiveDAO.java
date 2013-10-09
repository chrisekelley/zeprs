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

import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.rules.OutcomeArchive;
import org.cidrz.webapp.dynasite.rules.impl.OutcomeImpl;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Apr 3, 2006
 *         Time: 12:05:58 PM
 */
public class OutcomeArchiveDAO {

	/**
	 * Save the outcome record.
	 * @param conn
	 * @param outcome
	 * @param userName
	 * @param siteId
	 * @return
	 * @throws SQLException
	 */
    public static Long save(Connection conn, Outcome outcome, String userName, Long siteId) throws SQLException {
        Timestamp created = new Timestamp(System.currentTimeMillis());
        if (outcome.getCreated() != null) {
            created = outcome.getCreated();
        }
        String createdBy = userName;
        if (outcome.getCreatedBy() != null) {
            createdBy = outcome.getCreatedBy();
        }
        Timestamp lastModified = new Timestamp(System.currentTimeMillis());
        if (outcome.getLastModified() != null) {
            lastModified = outcome.getLastModified();
        }
        String lastModifiedBy = userName;
        if (outcome.getLastModifiedBy() != null) {
            lastModifiedBy = outcome.getLastModifiedBy();
        }
        Long currentSiteId = siteId;
        if (outcome.getSiteId() != null && outcome.getSiteId() != 0) {
            currentSiteId = outcome.getSiteId();
        }
        Long referringSiteId = siteId;
        if (outcome.getReferringSiteId() != null && outcome.getReferringSiteId() != 0) {
            referringSiteId = outcome.getReferringSiteId();
        }
        String outcomeType = "";
        String outcomeSQL = "";
        ArrayList values = new ArrayList();
        outcomeType = "encounter";
        outcomeSQL = "INSERT INTO outcome_archive " +
		"(id, active, encounter_id, patient_id, pregnancy_id, form_id, form_field_id, rule_definition_id, last_modified, created, " +
		"last_modified_by, created_by, outcome_type, site_id, reason, priority_of_referral, transport, uth_admission_date, arrival_condition, uth_ward_id," +
		"referring_site_id, message, required_form_id, import_outcome_id, firm, import_encounter_id, date_deleted, deleted_by, uuid, encounter_uuid, patient_uuid, pregnancy_uuid) " +
        "VALUES (?,?,?,?,?,?,?,?,?,?," +
        "?,?,?,?,?,?,?,?,?,?," +
        "?,?,?,?,?,?,?,?,?,?,?,?)";
        values.add(outcome.getId());
        values.add(outcome.isActive());
        values.add(outcome.getEncounterId());
        values.add(outcome.getPatientId());
        values.add(outcome.getPregnancyId());
        values.add(outcome.getFormId());
        values.add(outcome.getFormFieldId());
        values.add(outcome.getRuleDefinitionId());
        values.add(lastModified);
        values.add(created);
        values.add(lastModifiedBy);
        values.add(createdBy);
        values.add(outcomeType);
        values.add(currentSiteId);
        values.add(outcome.getReason());
        values.add(outcome.getPriority());
        values.add(outcome.getTransport());
        values.add(outcome.getUthAdmissionDate());
        values.add(outcome.getArrivalCondition());
        values.add(outcome.getWardId());
        values.add(referringSiteId);
        values.add(outcome.getMessage());
        values.add(outcome.getRequiredFormId());
        values.add(outcome.getImportOutcomeId());
        values.add(outcome.getFirm());
        values.add(outcome.getImportEncounterId());
        values.add(new Timestamp(System.currentTimeMillis()));
        values.add(userName);
        values.add(outcome.getOutcomeUuid());
        values.add(outcome.getEncounterUuid());
        values.add(outcome.getPatientUuid());
        values.add(outcome.getPregnancyUuid());
        Long encounterId = (Long) DatabaseUtils.create(conn, outcomeSQL, values.toArray());
        return encounterId;
    }

     /**
     *
     * @param conn
     * @param patientId
     * @return List of all deleted outcomes for patient
     */
    public static List getAll(Connection conn, Long patientId) {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select o.id, o.active, o.encounter_id AS encounterId, o.patient_id AS patientId, o.pregnancy_id AS pregnancyId, " +
                "o.form_id AS formId, o.form_field_id AS formFieldId, " +
                "o.rule_definition_id AS ruleDefinitionId, o.outcome_type AS outcomeType, o.last_modified AS 'lastModified', " +
                "o.created_by AS createdBy, o.last_modified_by AS lastmodifiedBy,  " +
                "o.created AS 'created', o.site_id AS siteId, o.referring_site_id AS referringSiteId, " +
                "import_outcome_id AS importOutcomeId, import_encounter_id AS importEncounterId, firm AS firm, " +
                "o.required_form_id AS requiredFormId, o.message AS message, o.reason AS reason, " +
                "priority_of_referral AS priority, o.transport AS transport, outcome_type AS outcomeType, uth_admission_date AS uthAdmissionDate, " +
                "arrival_condition AS arrivalCondition, uth_ward_id AS wardId, date_deleted AS dateDeleted, deleted_by AS deletedBy, uuid, " +
                "encounter_uuid AS encounterUuid, patient_uuid AS patientUuid, pregnancy_uuid AS pregnancyUuid " +
                "from outcome_archive o " +
                "where o.patient_id=?";
        values.add(patientId);
        try {
            list = DatabaseUtils.getList(conn, OutcomeArchive.class, sql, values);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
