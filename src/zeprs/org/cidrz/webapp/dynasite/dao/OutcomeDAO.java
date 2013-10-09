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

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.cidrz.project.zeprs.report.OutcomeReport;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.rules.Outcome;
import org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome;
import org.cidrz.webapp.dynasite.rules.impl.InfoOutcome;
import org.cidrz.webapp.dynasite.rules.impl.OutcomeImpl;
import org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome;
import org.cidrz.webapp.dynasite.utils.AuditInfoBeanProcessor;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.ZEPRSRowProcessor;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.RuleDefinition;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Jun 8, 2005
 * Time: 2:46:22 PM
 */
public class OutcomeDAO {

    /**
     * Returns one outcome
     * 5/5/2006 - remove checking for long-term problems. Don't want to query admin database. Use in-memory dataset instead.
     * @param conn
     * @param problemId
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static Outcome getOne(Connection conn, Long problemId) throws SQLException, ServletException, ObjectNotFoundException {
        Outcome item = null;
        String sql;
        ArrayList values;
        sql = "SELECT o.id, o.encounter_id AS encounterId, o.patient_id AS patientId, o.pregnancy_id AS pregnancyId, " +
                "o.form_id AS formId, o.form_field_id AS formFieldId, " +
                "o.rule_definition_id AS ruleDefinitionId, o.outcome_type AS outcomeType, o.last_modified AS 'lastModified', " +
                "o.created AS 'created', " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedBy', " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedBy', " +
                "o.required_form_id AS requiredFormId, o.message AS message, o.reason AS reason, " +
                "priority_of_referral AS priority, o.transport AS transport, " +
                "o.uth_admission_date AS uthAdmissionDate, o.arrival_condition AS arrivalCondition, " +
                "o.uuid AS outcomeUuid, o.encounter_uuid AS encounterUuid, o.patient_uuid AS patientUuid, o.pregnancy_uuid AS pregnancyUuid, " +
              //  "uth_ward_id AS wardId, r.all_pregs AS longTermProblem " +
                "uth_ward_id AS wardId  " +
               // "from outcome o, userdata.address u, admin.rule_definition r " +
                "FROM outcome o " +
                "LEFT JOIN userdata.address u ON u.nickname = o.last_modified_by " +
                "WHERE o.id=?";
        values = new ArrayList();
        values.add(problemId);
        item = (Outcome) DatabaseUtils.getZEPRSBean(conn, OutcomeImpl.class, sql, values);
        return item;
    }

    /**
     * Returns one outcome
     * @param conn
     * @param uuid
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Outcome getOne(Connection conn, String uuid) throws SQLException, ServletException, ObjectNotFoundException {
    	Outcome item = null;
    	String sql;
    	ArrayList values;
    	sql = "SELECT o.id, o.encounter_id AS encounterId, o.patient_id AS patientId, o.pregnancy_id AS pregnancyId, " +
    	"o.form_id AS formId, o.form_field_id AS formFieldId, " +
    	"o.rule_definition_id AS ruleDefinitionId, o.outcome_type AS outcomeType, o.last_modified AS 'lastModified', " +
    	"o.created AS 'created', " +
    	"CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedBy', " +
    	"CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedBy', " +
    	"o.required_form_id AS requiredFormId, o.message AS message, o.reason AS reason, " +
    	"priority_of_referral AS priority, o.transport AS transport, " +
    	"o.uth_admission_date AS uthAdmissionDate, o.arrival_condition AS arrivalCondition, " +
    	"o.uuid AS outcomeUuid, o.encounter_uuid AS encounterUuid, o.patient_uuid AS patientUuid, o.pregnancy_uuid AS pregnancyUuid, " +
    	//  "uth_ward_id AS wardId, r.all_pregs AS longTermProblem " +
    	"uth_ward_id AS wardId  " +
    	// "from outcome o, userdata.address u, admin.rule_definition r " +
    	"FROM outcome o " +
    	"LEFT JOIN userdata.address u ON u.nickname = o.last_modified_by " +
    	"WHERE o.uuid=?";
    	values = new ArrayList();
    	values.add(uuid);
    	item = (Outcome) DatabaseUtils.getZEPRSBean(conn, OutcomeImpl.class, sql, values);
    	return item;
    }

    public static Outcome getOneImported(Connection conn, Long outcomeId) throws SQLException, ServletException, ObjectNotFoundException {
        Outcome item = null;
        String sql;
        ArrayList values;
        sql = "select o.id, o.encounter_id AS encounterId, o.patient_id AS patientId, o.pregnancy_id AS pregnancyId, " +
                "o.form_id AS formId, o.form_field_id AS formFieldId, " +
                "o.rule_definition_id AS ruleDefinitionId, o.outcome_type AS outcomeType, o.last_modified AS 'lastModified', " +
                "o.created AS 'created', " +
                "o.required_form_id AS requiredFormId, o.message AS message, o.reason AS reason, " +
                "priority_of_referral AS priority, o.transport AS transport, " +
                "o.uth_admission_date AS uthAdmissionDate, o.arrival_condition AS arrivalCondition, " +
                "o.uuid AS outcomeUuid, o.encounter_uuid AS encounterUuid, o.patient_uuid AS patientUuid, o.pregnancy_uuid AS pregnancyUuid, " +
                "uth_ward_id AS wardId,import_outcome_id AS importOutcomeId, import_encounter_id AS importEncounterId " +
                "from outcome o " +
                "where o.import_outcome_id=?";
        values = new ArrayList();
        values.add(outcomeId);
        item = (Outcome) DatabaseUtils.getZEPRSBean(conn, OutcomeImpl.class, sql, values);
        return item;
    }

    /**
     *
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param status
     * @return List of all outcomes in pregnancy
     */
    public static List getAll(Connection conn, Long patientId, Long pregnancyId, Boolean status) {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select o.id, o.encounter_id AS encounterId, o.patient_id AS patientId, o.pregnancy_id AS pregnancyId, " +
                "o.form_id AS formId, o.form_field_id AS formFieldId, " +
                "o.rule_definition_id AS ruleDefinitionId, o.outcome_type AS outcomeType, o.last_modified AS 'lastModified', " +
                "o.created_by AS createdBy, o.last_modified_by AS lastmodifiedBy,  " +
                "o.created AS 'created', o.site_id AS siteId, o.referring_site_id AS referringSiteId, " +
                "import_outcome_id AS importOutcomeId, import_encounter_id AS importEncounterId, firm AS firm, " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedByName', " +
                "o.required_form_id AS requiredFormId, o.message AS message, o.reason AS reason, " +
                "priority_of_referral AS priority, o.transport AS transport, uth_admission_date AS uthAdmissionDate, " +
                "o.uuid AS outcomeUuid, o.encounter_uuid AS encounterUuid, o.patient_uuid AS patientUuid, o.pregnancy_uuid AS pregnancyUuid, " +
                "arrival_condition AS arrivalCondition, uth_ward_id AS wardId, o.active AS active " +
                "from outcome o, userdata.address u " +
                "where o.last_modified_by=u.nickname " +
                "and o.patient_id=? " +
                "and o.pregnancy_id=? " +
                "and o.active=? " +
                "order by o.created DESC";
        values.add(patientId);
        values.add(pregnancyId);
        values.add(status);
        try {
            list = DatabaseUtils.getList(conn, OutcomeImpl.class, sql, values);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * List of all outcomes for a patient/pregnancy
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @return
     */
    public static List getAll(Connection conn, Long patientId, Long pregnancyId) {

    	List list = new ArrayList();
    	ArrayList values = new ArrayList();
    	values = new ArrayList();
    	String sql = "select o.id, o.encounter_id AS encounterId, o.patient_id AS patientId, o.pregnancy_id AS pregnancyId, " +
    	"o.form_id AS formId, o.form_field_id AS formFieldId, " +
    	"o.rule_definition_id AS ruleDefinitionId, o.outcome_type AS outcomeType, o.last_modified AS 'lastModified', " +
    	"o.created_by AS createdBy, o.last_modified_by AS lastmodifiedBy,  " +
    	"o.created AS 'created', o.site_id AS siteId, o.referring_site_id AS referringSiteId, " +
    	"import_outcome_id AS importOutcomeId, import_encounter_id AS importEncounterId, firm AS firm, " +
    	"CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedByName', " +
    	"o.required_form_id AS requiredFormId, o.message AS message, o.reason AS reason, " +
    	"priority_of_referral AS priority, o.transport AS transport, uth_admission_date AS uthAdmissionDate, " +
        "o.uuid AS outcomeUuid, o.encounter_uuid AS encounterUuid, o.patient_uuid AS patientUuid, o.pregnancy_uuid AS pregnancyUuid, " +
    	"arrival_condition AS arrivalCondition, uth_ward_id AS wardId, o.active AS active " +
    	"from outcome o, userdata.address u " +
    	"where o.last_modified_by=u.nickname " +
    	"and o.patient_id=? " +
    	"and o.pregnancy_id=? " +
    	"order by o.created DESC";
    	values.add(patientId);
    	values.add(pregnancyId);
    	try {
    		list = DatabaseUtils.getList(conn, OutcomeImpl.class, sql, values);
    	} catch (ServletException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return list;
    }

    /**
     * Reporting
     * @return list of all outcomes
     * @param conn
     */
    public static List getAll(Connection conn) {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select o.id, o.encounter_id AS encounterId, o.patient_id AS patientId, o.pregnancy_id AS pregnancyId, " +
                "o.form_id AS formId, o.form_field_id AS formFieldId, " +
                "o.rule_definition_id AS ruleDefinitionId, o.outcome_type AS outcomeType, o.last_modified AS 'lastModified', " +
                "o.created AS 'created', s.site_name AS clinicName, " +
                "CONCAT_WS(', ', u.lastName, u.firstName) AS 'lastModifiedBy', " +
                "o.required_form_id AS requiredFormId, o.message AS message, o.reason AS reason, " +
                "priority_of_referral AS priority, o.transport AS transport, " +
                "o.uuid AS outcomeUuid, o.encounter_uuid AS encounterUuid, o.patient_uuid AS patientUuid, o.pregnancy_uuid AS pregnancyUuid, " +
                "CONCAT_WS(', ', p.surname, p.first_name) AS 'patientName' " +
                "from zeprs.outcome o, userdata.address u, zeprs.patient p, zeprs.site s " +
                "where o.last_modified_by=u.nickname " +
                "AND p.id=o.patient_id " +
                "AND s.id = o.site_id " +
                "ORDER BY o.patient_id";
        try {
            list = DatabaseUtils.getList(conn, OutcomeReport.class, sql, values);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * List problems that should be displayed in all pregnancies, such as eclampsia and pph.
     * @param conn
     * @param patientId
     * @return list of problems for all pregnancies
     */
    public static List getLongTermProblems(Connection conn, Long patientId) {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select o.id, o.encounter_id AS encounterId, o.patient_id AS patientId, o.pregnancy_id AS pregnancyId, " +
                "o.form_id AS formId, o.form_field_id AS formFieldId, " +
                "o.rule_definition_id AS ruleDefinitionId, o.outcome_type AS outcomeType, o.last_modified AS 'lastModified', " +
                "o.created AS 'created', " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedBy', " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedBy', " +
                "o.required_form_id AS requiredFormId, o.message AS message, o.reason AS reason, " +
                "o.uuid AS outcomeUuid, o.encounter_uuid AS encounterUuid, o.patient_uuid AS patientUuid, o.pregnancy_uuid AS pregnancyUuid, " +
                "priority_of_referral AS priority, o.transport AS transport " +
                "from outcome o, userdata.address u " +
                "where o.last_modified_by=u.nickname " +
                "and o.patient_id=? ";
        values.add(patientId);
        try {
            list = DatabaseUtils.getList(conn, OutcomeImpl.class, sql, values);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List longTermProblems = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            OutcomeImpl problem = (OutcomeImpl) list.get(i);
            Long ruleId = problem.getRuleDefinitionId();
            RuleDefinition rule = (RuleDefinition) DynaSiteObjects.getAllPregnanciesRules().get(ruleId);
            if (rule != null) {  // if this rule is not in the allPregnanciesRules map, discard it.
                longTermProblems.add(problem);
            }
        }
        return longTermProblems;
    }

    public static List getAllforEncounter(Connection conn, Long encounterId, Long patientId, Long pregnancyId, Boolean status) {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select o.id, o.active, o.encounter_id AS encounterId, o.patient_id AS patientId, o.pregnancy_id AS pregnancyId, " +
                "o.form_id AS formId, o.form_field_id AS formFieldId, " +
                "o.uuid AS outcomeUuid, o.encounter_uuid AS encounterUuid, o.patient_uuid AS patientUuid, o.pregnancy_uuid AS pregnancyUuid, " +
                "o.rule_definition_id AS ruleDefinitionId, o.outcome_type AS outcomeType, o.last_modified AS 'auditInfo.lastModified' " +
                "from outcome o  " +
                "where o.encounter_id=? " +
                "and o.patient_id=? " +
                "and o.pregnancy_id=? " +
                "and o.active=?";
        values.add(encounterId);
        values.add(patientId);
        values.add(pregnancyId);
        values.add(status);
        BeanProcessor beanprocessor = new AuditInfoBeanProcessor();
        RowProcessor convert = new ZEPRSRowProcessor(beanprocessor);
        try {
            list = DatabaseUtils.getList(conn, OutcomeImpl.class, sql, values, convert);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Get all outcomes for this encounter - active or inactive.
     * @param conn
     * @param encounterId
     * @return list of outcomes
     */
    public static List getAllforEncounter(Connection conn, Long encounterId) {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "SELECT o.id, o.active, o.encounter_id AS encounterId, o.patient_id AS patientId, o.pregnancy_id AS pregnancyId, " +
                "o.form_id AS formId, o.form_field_id AS formFieldId, " +
                "o.uuid AS outcomeUuid, o.encounter_uuid AS encounterUuid, o.patient_uuid AS patientUuid, o.pregnancy_uuid AS pregnancyUuid, " +
                "o.rule_definition_id AS ruleDefinitionId, o.outcome_type AS outcomeType, o.last_modified AS 'auditInfo.lastModified' " +
                "FROM outcome o  " +
                "WHERE o.encounter_id=?";
        values.add(encounterId);
        BeanProcessor beanprocessor = new AuditInfoBeanProcessor();
        RowProcessor convert = new ZEPRSRowProcessor(beanprocessor);
        try {
            list = DatabaseUtils.getList(conn, OutcomeImpl.class, sql, values, convert);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Save an outcome. Sets uuid if null.
     * @param conn
     * @param outcome
     * @param userName
     * @param siteId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Long save(Connection conn, Outcome outcome, String userName, Long siteId) throws SQLException, ServletException {

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
        if (outcome.getOutcomeUuid() == null) {
            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            outcome.setOutcomeUuid(uuidStr);
        }
        String outcomeType = "";
        String outcomeSQL = "";
        ArrayList values = new ArrayList();
        if (outcome.getClass().toString().equals("class org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome")) {
            EncounterOutcome encounterOutcome = (EncounterOutcome) outcome;
            outcomeType = "encounter";
            outcomeSQL = "INSERT INTO outcome " +
            "(encounter_id, patient_id, pregnancy_id, form_id, form_field_id, rule_definition_id," +
            " active, last_modified, created, last_modified_by, created_by, outcome_type, site_id, " +
            "referring_site_id, message, required_form_id, import_outcome_id, firm, import_encounter_id, " +
            "uuid, encounter_uuid, patient_uuid, pregnancy_uuid) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            values.add(outcome.getEncounterId());
            values.add(outcome.getPatientId());
            values.add(outcome.getPregnancyId());
            values.add(outcome.getFormId());
            values.add(outcome.getFormFieldId());
            values.add(outcome.getRuleDefinitionId());
            values.add(outcome.isActive());
            values.add(lastModified);
            values.add(created);
            values.add(lastModifiedBy);
            values.add(createdBy);
            values.add(outcomeType);
            values.add(currentSiteId);
            values.add(referringSiteId);
            values.add(encounterOutcome.getMessage());
            values.add(encounterOutcome.getRequiredFormId());
            values.add(encounterOutcome.getImportOutcomeId());
            values.add(encounterOutcome.getFirm());
            values.add(encounterOutcome.getImportEncounterId());
            values.add(outcome.getOutcomeUuid());
            values.add(outcome.getEncounterUuid());
            values.add(outcome.getPatientUuid());
            values.add(outcome.getPregnancyUuid());

        } else if (outcome.getClass().toString().equals("class org.cidrz.webapp.dynasite.rules.impl.InfoOutcome")) {
            InfoOutcome infoOutcome = (InfoOutcome) outcome;
            outcomeType = "info";
            outcomeSQL = "INSERT INTO outcome " +
            "(encounter_id, patient_id, pregnancy_id, form_id, form_field_id, rule_definition_id, " +
            "active, last_modified, created, last_modified_by, created_by, outcome_type, site_id, " +
            "referring_site_id, message, import_outcome_id, firm, import_encounter_id, " +
            "uuid, encounter_uuid, patient_uuid, pregnancy_uuid) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            values.add(outcome.getEncounterId());
            values.add(outcome.getPatientId());
            values.add(outcome.getPregnancyId());
            values.add(outcome.getFormId());
            values.add(outcome.getFormFieldId());
            values.add(outcome.getRuleDefinitionId());
            values.add(outcome.isActive());
            values.add(lastModified);
            values.add(created);
            values.add(lastModifiedBy);
            values.add(createdBy);
            values.add(outcomeType);
            values.add(currentSiteId);
            values.add(referringSiteId);
            values.add(infoOutcome.getMessage());
            values.add(infoOutcome.getImportOutcomeId());
            values.add(infoOutcome.getFirm());
            values.add(infoOutcome.getImportEncounterId());
            values.add(outcome.getOutcomeUuid());
            values.add(outcome.getEncounterUuid());
            values.add(outcome.getPatientUuid());
            values.add(outcome.getPregnancyUuid());
        } else if (outcome.getClass().toString().equals("class org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome")) {
            ReferralOutcome referralOutcome = (ReferralOutcome) outcome;
            outcomeType = "referral";
            outcomeSQL = "INSERT INTO outcome " +
            "(encounter_id, patient_id, pregnancy_id, form_id, form_field_id, rule_definition_id, " +
            "active, last_modified, created, last_modified_by, created_by, outcome_type, site_id, " +
            "referring_site_id, reason, priority_of_referral, transport, uth_admission_date, " +
            "arrival_condition, uth_ward_id, import_outcome_id, firm, import_encounter_id, " +
            "uuid, encounter_uuid, patient_uuid, pregnancy_uuid) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            values.add(outcome.getEncounterId());
            values.add(outcome.getPatientId());
            values.add(outcome.getPregnancyId());
            values.add(outcome.getFormId());
            values.add(outcome.getFormFieldId());
            values.add(outcome.getRuleDefinitionId());
            values.add(outcome.isActive());
            values.add(lastModified);
            values.add(created);
            values.add(lastModifiedBy);
            values.add(createdBy);
            values.add(outcomeType);
            values.add(currentSiteId);
            values.add(referringSiteId);
            values.add(referralOutcome.getReason());
            values.add(referralOutcome.getPriority());
            values.add(referralOutcome.getTransport());
            values.add(referralOutcome.getUthAdmissionDate());
            values.add(referralOutcome.getArrivalCondition());
            values.add(referralOutcome.getUthWardId());
            values.add(referralOutcome.getImportOutcomeId());
            values.add(referralOutcome.getFirm());
            values.add(referralOutcome.getImportEncounterId());
            values.add(outcome.getOutcomeUuid());
            values.add(outcome.getEncounterUuid());
            values.add(outcome.getPatientUuid());
            values.add(outcome.getPregnancyUuid());
        }
        Long encounterId = (Long) DatabaseUtils.create(conn, outcomeSQL, values.toArray());
        return encounterId;
    }

    /**
     * Update an outcome
     * @param conn
     * @param outcome
     * @param userName
     * @param siteId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Long update(Connection conn, Outcome outcome, String userName, Long siteId) throws SQLException, ServletException {
        String outcomeType = "";
        String outcomeSQL = "";
        if (outcome.getClass().toString().equals("class org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome")) {
            EncounterOutcome encounterOutcome = (EncounterOutcome) outcome;
            outcomeType = "encounter";
            outcomeSQL = "UPDATE outcome " +
                    "SET active=" + outcome.isActive() +
                    ", last_modified='" + new Timestamp(System.currentTimeMillis()) +
                    "', last_modified_by='" + userName +
                    "', site_id=" + siteId +
                    " WHERE id=" + encounterOutcome.getId() + "; ";
        } else if (outcome.getClass().toString().equals("class org.cidrz.webapp.dynasite.rules.impl.InfoOutcome")) {
            InfoOutcome infoOutcome = (InfoOutcome) outcome;
            outcomeType = "info";
            outcomeSQL = "UPDATE outcome " +
                    "SET active=" + outcome.isActive() +
                    ", last_modified='" + new Timestamp(System.currentTimeMillis()) +
                    "', last_modified_by='" + userName +
                    "', site_id=" + siteId +
                    ", message='" + infoOutcome.getMessage() +
                    "' WHERE id=" + outcome.getId() + "; ";
        } else if (outcome.getClass().toString().equals("class org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome")) {
            ReferralOutcome referralOutcome = (ReferralOutcome) outcome;
            outcomeType = "referral";
            outcomeSQL = "UPDATE outcome " +
                    "SET active=" + outcome.isActive() +
                    ", last_modified='" + new Timestamp(System.currentTimeMillis()) +
                    "', last_modified_by='" + userName +
                    "', site_id=" + siteId +
                    "', priority_of_referral='" + referralOutcome.getPriority() +
                    "', transport='" + referralOutcome.getTransport() +
                    "' WHERE id=" + outcome.getId() + "; ";
        } else if (outcome.getClass().toString().equals("class org.cidrz.webapp.dynasite.rules.impl.OutcomeImpl")) {
            outcomeSQL = "UPDATE outcome " +
                    "SET active=" + outcome.isActive() +
                    ", last_modified='" + new Timestamp(System.currentTimeMillis()) +
                    "', last_modified_by='" + userName +
                    "', site_id=" + siteId +
                    " WHERE id=" + outcome.getId() + "; ";
        }
        ArrayList values = new ArrayList();
        Long encounterId = (Long) DatabaseUtils.create(conn, outcomeSQL, values.toArray());
        return encounterId;
    }

    public static Long touchOutcome(Connection conn, Long outcomeId, String userName, Long siteId) throws SQLException, ServletException {
        String outcomeSQL = "";
        outcomeSQL = "UPDATE outcome " +
                "SET last_modified='" + new Timestamp(System.currentTimeMillis()) +
                "', last_modified_by='" + userName +
                "', site_id=" + siteId +
                " WHERE id=" + outcomeId + "; ";
        ArrayList values = new ArrayList();
        Long encounterId = (Long) DatabaseUtils.create(conn, outcomeSQL, values.toArray());
        return encounterId;
    }

    public static Long admit(Connection conn, Long referralId, String condition, String userName, Long siteId, Integer ward, String firm) throws SQLException, ServletException {
        String outcomeSQL = "";
        outcomeSQL = "UPDATE outcome SET last_modified=?, last_modified_by=?, site_id=?, uth_admission_date=?, " +
                "arrival_condition=?, uth_ward_id=?, firm=? WHERE id=?; ";
        ArrayList values = new ArrayList();
        values.add(new Timestamp(System.currentTimeMillis()));
        values.add(userName);
        values.add(siteId);
        values.add(new Timestamp(System.currentTimeMillis()));
        values.add(condition);
        values.add(ward);
        values.add(firm);
        values.add(referralId);
        Long encounterId = (Long) DatabaseUtils.create(conn, outcomeSQL, values.toArray());
        return encounterId;
    }

    /**
     * Updates modified info in outcome table.
     * @param conn
     * @param lastModified
     * @param referralId
     * @param lastModifiedBy
     * @param siteId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Long discharge(Connection conn, Timestamp lastModified, Long referralId, String lastModifiedBy, Long siteId) throws SQLException, ServletException {
    	String outcomeSQL = "";
    	outcomeSQL = "UPDATE outcome " +
    	"SET last_modified='" + lastModified +
    	"', last_modified_by='" + lastModifiedBy +
    	"', site_id=" + siteId +
    	", active=0" +
    	" WHERE id=" + referralId + "; ";
    	ArrayList values = new ArrayList();
    	Long encounterId = (Long) DatabaseUtils.create(conn, outcomeSQL, values.toArray());
    	return encounterId;
    }

    /**
     * Used for UTH Summary
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @return Referrals for this patient/pregnancy. Exclude encounters in left join that are form 94, reasons for referral.
     */
    public static List getReferrals(Connection conn, Long patientId, Long pregnancyId) {

        List list = new ArrayList();
        ArrayList values;
        values = new ArrayList();
        String sql = "select o.id, o.encounter_id AS encounterId, o.patient_id AS patientId, o.pregnancy_id AS pregnancyId, " +
                "o.form_id AS formId, o.form_field_id AS formFieldId, " +
                "o.rule_definition_id AS ruleDefinitionId, o.outcome_type AS outcomeType, o.last_modified AS 'lastModified', " +
                "o.created AS 'created', " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedBy', " +
                "CONCAT_WS(' ', u.lastName, u.firstName) AS 'lastModifiedBy', " +
                "o.required_form_id AS requiredFormId, o.message AS message, o.reason AS reason, " +
                "priority_of_referral AS priority, o.transport AS transport, e.id AS dischargeId, e.date_visit AS dischargeDate,  " +
                "o.uth_admission_date AS uthAdmissionDate, o.arrival_condition AS arrivalCondition, uth_ward_id AS wardId, " +
                "o.referring_site_id AS referringSiteId, s.site_name AS referringSiteName, o.site_id AS siteId " +
                "from outcome o \n" +
                "LEFT JOIN encounter e ON e.referral_id = o.id and e.form_id !=94 \n" +      // exclude reasons for referral form
                "JOIN userdata.address u ON o.last_modified_by=u.nickname \n" +
                "JOIN  site s  ON o.referring_site_id = s.id \n" +
                "WHERE o.outcome_type='referral' \n" +
                "AND o.patient_id=? \n" +
                "AND o.pregnancy_id=?";
        values.add(patientId);
        values.add(pregnancyId);
        //values.add(status);
        try {
            list = DatabaseUtils.getList(conn, OutcomeImpl.class, sql, values);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * delete all outcomes for a patient.
     * @param conn
     * @param patientId
     * @return status message
     */
    public static String deleteAll(Connection conn, Long patientId) {
        String result = "Outcomes deleted.";
        Statement stmt;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.execute("START TRANSACTION;");
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
            String sql = "delete from outcome where patient_id=" + patientId;
            stmt.execute(sql);
            stmt.execute("Commit");
        } catch (Exception e) {
            result = "Error while deleting Outcomes.";
            e.printStackTrace();
        }

        return result;
    }

    /**
     * delete a single outcome
     * @param conn
     * @param outcomeId
     * @return status message
     */
    public static String deleteOne(Connection conn, Long outcomeId) {
        String result = "Outcomes deleted.";
        Statement stmt;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.execute("START TRANSACTION;");
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
            String sql = "delete from outcome where id=" + outcomeId;
            stmt.execute(sql);
            stmt.execute("Commit");
        } catch (Exception e) {
            result = "Error while deleting Outcomes.";
            e.printStackTrace();
        }
        return result;
    }

    /**
     * delete a single outcome
     * @param conn
     * @param uuid
     * @return status message
     */
    public static String deleteOne(Connection conn, String uuid) {
    	String result = "Outcomes deleted.";
    	Statement stmt;
    	try {
    		conn.setAutoCommit(false);
    		stmt = conn.createStatement();
    		stmt.execute("START TRANSACTION;");
    		stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
    		String sql = "delete from outcome where uuid='" + uuid + "'";
    		stmt.execute(sql);
    		stmt.execute("Commit");
    	} catch (Exception e) {
    		result = "Error while deleting Outcomes.";
    		e.printStackTrace();
    	}
    	return result;
    }

    /**
     * Checks if an imported outcome exists
     * @param conn
     * @param patientId
     * @param outcomeId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Long checkImportedOutcome(Connection conn, Long patientId, Long outcomeId) throws SQLException, ServletException, ObjectNotFoundException {
        Long id = null;
        OutcomeImpl item = null;
        String sql;
        ArrayList values;
        sql = "select id from outcome where patient_id=? and import_outcome_id=?";
        values = new ArrayList();
        values.add(patientId);
        values.add(outcomeId);
        item = (OutcomeImpl) DatabaseUtils.getZEPRSBean(conn, OutcomeImpl.class, sql, values);
        id = item.getId();
        return id;
    }

    /**
     *
     * Checks if an imported outcome exists
     * @param conn
     * @param uuid
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Long checkImportedOutcome(Connection conn, String uuid) throws SQLException, ServletException, ObjectNotFoundException {
    	Long id = null;
    	OutcomeImpl item = null;
    	String sql;
    	ArrayList values;
    	sql = "select id from outcome where uuid=?";
    	values = new ArrayList();
    	values.add(uuid);
    	item = (OutcomeImpl) DatabaseUtils.getZEPRSBean(conn, OutcomeImpl.class, sql, values);
    	id = item.getId();
    	return id;
    }

}
