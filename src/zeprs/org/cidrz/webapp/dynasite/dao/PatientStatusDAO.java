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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.PatientRegistration;
import org.cidrz.project.zeprs.valueobject.gen.PregnancyDating;
import org.cidrz.project.zeprs.valueobject.report.PatientStatusReport;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Pregnancy;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Aug 25, 2005
 *         Time: 10:52:22 AM
 */
public class PatientStatusDAO {
    /**
     * Update noPreviousPregnancies in patientStatusReport for prev. pregnancies form.
     * @param conn
     * @param patientId
     * @param userName
     * @param siteId
     * @return
     * @throws Exception
     * @throws ServletException
     */

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(PatientStatusDAO.class);

    public static int updateNoPreviousPregnancies(Connection conn, Long patientId, String userName, Long siteId) throws Exception, ServletException {
        ArrayList values = new ArrayList();
        // if flowId == history flow, don't update currentl flow.
        String sql = "UPDATE patient_status SET no_prev_pregnancies=?, last_modified=?, last_modified_by=?, site_id=? WHERE id=?;";
        // add no_prev_pregnancies to statusValues
        values.add(new Boolean(true));
        // add last_modified to statusValues
        values.add(new Timestamp(System.currentTimeMillis()));
        // add last_modified_by to statusValues
        values.add(userName);
        // add siteId to statusValues
        values.add(siteId);
        // set id field for statusValues
        values.add(patientId);
        int result = DatabaseUtils.update(conn, sql, values.toArray());
        return result;
    }

    /**
     * Fetch whole patient_status record for patient.
     * Used for xml record export
     * @param conn
     * @param patientId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static PatientStatusReport getOne(Connection conn, Long patientId) throws ServletException, SQLException, ObjectNotFoundException {
        ArrayList values = new ArrayList();
        String sql = "SELECT id, current_flow AS currentFlowId, current_flow_encounter_id AS currentFlowEncounterId, " +
                "current_pregnancy_id AS currentPregnancyId, current_pregnancy_encounter_id AS currentPregnancyEncounterId, " +
                "current_lmp_date AS currentLmpDate, current_lmp_date_encounter_id AS currentLmpDateEncounterId, " +
                "current_ega_days AS currentEgaDays, current_ega_days_encounter_id AS currentEgaDaysEncounterId, " +
                "last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, created_by AS createdBy, " +
                "site_id AS siteId, no_prev_pregnancies AS noPreviousPregnancies, firm AS firm, current_flow_encounter_uuid AS currentFlowEncounterUuid, " +
                "current_pregnancy_uuid AS currentPregnancyUuid, current_pregnancy_encounter_uuid AS currentPregnancyEncounterUuid, " +
                "current_lmp_date_encounter_uuid AS currentLmpDateEncounterUuid, current_ega_days_encounter_uuid AS currentEgaDaysEncounterUuid " +
                "FROM patient_status " +
                "WHERE id=?";
        // set id field for statusValues
        values.add(patientId);
        PatientStatusReport result = (PatientStatusReport) DatabaseUtils.getZEPRSBean(conn, PatientStatusReport.class, sql, values);
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
            sql = "SELECT id FROM patient_status WHERE created >= ? AND created < ? AND site_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, beginDate);
            ps.setDate(2, endDate);
            ps.setLong(3, siteId);
        } else {
            sql = "SELECT id FROM patient_status WHERE created < ? AND site_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, endDate);
            ps.setLong(2, siteId);
        }
        rs = ps.executeQuery();
        return rs;
    }

    /**
     * Updates entire patient_status record.
     * Used for xml record import
     * @param conn
     * @param queries
     * @param vo
     * @param patientId
     * @return int result
     * @throws Exception
     */
    public static int updatePatientStatusRecord(Connection conn, Map queries, PatientStatusReport vo, Long patientId) throws Exception {

        ArrayList updateStatusValues = new ArrayList();
        String sqlUpdateStatus = (String) queries.get("SQL_MODIFY_STATUS_FULL");
        updateStatusValues.add(vo.getCurrentPregnancyId());
        updateStatusValues.add(vo.getCurrentPregnancyUuid());
        updateStatusValues.add(vo.getCurrentPregnancyEncounterId());
        updateStatusValues.add(vo.getCurrentPregnancyEncounterUuid());
        updateStatusValues.add(vo.getCurrentFlowEncounterId());
        updateStatusValues.add(vo.getCurrentFlowEncounterUuid());
        updateStatusValues.add(vo.getCurrentFlowId());
        updateStatusValues.add(vo.getCurrentEgaDays());
        updateStatusValues.add(vo.getCurrentEgaDaysEncounterId());
        updateStatusValues.add(vo.getCurrentEgaDaysEncounterUuid());
        updateStatusValues.add(vo.getCurrentLmpDate());
        updateStatusValues.add(vo.getCurrentLmpDateEncounterId());
        updateStatusValues.add(vo.getCurrentLmpDateEncounterUuid());
        updateStatusValues.add(vo.getCreated());
        updateStatusValues.add(vo.getCreatedBy());
        updateStatusValues.add(vo.getLastModified());
        updateStatusValues.add(vo.getLastModifiedBy());
        updateStatusValues.add(vo.getSiteId());
        updateStatusValues.add(vo.getNoPreviousPregnancies());
        updateStatusValues.add(vo.getFirm());
        updateStatusValues.add(patientId);
        if (vo.getCurrentEgaDays() != null && vo.getCurrentEgaDaysEncounterId() == null) {
            String message = "vo.getCurrentEgaDaysEncounterId is null. PatientId: " + patientId;
            log.error(message);
            throw new Exception(message);
        }
        int result = DatabaseUtils.update(conn, sqlUpdateStatus, updateStatusValues.toArray());
        return result;
    }

    /**
     * Updates flowId, pregnancyId, and timestamp for patient_status record.
     * Does not update encounterId's
     * Used for xml record import
     * @param conn
     * @param queries
     * @param vo
     * @param patientId
     * @return int result
     * @throws Exception
     */
    public static int updatePatientStatusRecordNoEnc(Connection conn, Map queries, PatientStatusReport vo, Long patientId) throws Exception {

        ArrayList updateStatusValues = new ArrayList();
        String sqlUpdateStatus = (String) queries.get("SQL_MODIFY_STATUS_NO_ENCID");
        updateStatusValues.add(vo.getCurrentFlowId());
        updateStatusValues.add(vo.getCurrentEgaDays());
        updateStatusValues.add(vo.getCurrentLmpDate());
        updateStatusValues.add(vo.getLastModified());
        updateStatusValues.add(vo.getLastModifiedBy());
        updateStatusValues.add(vo.getSiteId());
        updateStatusValues.add(vo.getNoPreviousPregnancies());
        updateStatusValues.add(vo.getFirm());
        updateStatusValues.add(patientId);
        int result = DatabaseUtils.update(conn, sqlUpdateStatus, updateStatusValues.toArray());
        return result;
    }

    /**
     * Updates patient_status table with fields from preg. dating form.
     * First form for a New pregnancy is pregnancy dating form.
     * Be sure to populate vo.pregnancy to persist pregnancy.uuid.
     *
     * @param conn
     * @param queries
     * @param vo
     * @param encounterId
     * @param userName
     * @param siteId
     * @param thisPatientId
     * @throws Exception
     */
    public static void updatePatientStatusNewPregDating(Connection conn, Map queries, EncounterData vo, Long pregnancyId, Long currentFlowId, Long encounterId, String userName, Long siteId, Long thisPatientId) throws Exception {
		PregnancyDating pregDat = (PregnancyDating) vo;
    	String sqlPregnancyDating = (String) queries.get("SQL_MODIFY_STATUS_NEW_PREGNANCY_DATING");
        ArrayList updatePregDatStatusValues = new ArrayList();
        // add pregnancyId to statusValues current_pregnancy_id
        updatePregDatStatusValues.add(pregnancyId);
        Pregnancy pregnancy = vo.getPregnancy();
        String pregnancyUuid = null;
        if (pregnancy != null) {
        	pregnancyUuid = pregnancy.getUuid();
        }
        updatePregDatStatusValues.add(pregnancyUuid);
        // add encounterId to statusValues current_pregnancy_encounter_id
        updatePregDatStatusValues.add(encounterId);
        updatePregDatStatusValues.add(vo.getUuid());
        // add encounterId to statusValues current_flow_encounter_id
        updatePregDatStatusValues.add(encounterId);
        updatePregDatStatusValues.add(vo.getUuid());
        // add current_flow to statusValues
        updatePregDatStatusValues.add(currentFlowId);
        // ega
        updatePregDatStatusValues.add(pregDat.getField129());
        updatePregDatStatusValues.add(encounterId);
        updatePregDatStatusValues.add(vo.getUuid());
        // lmp
        updatePregDatStatusValues.add(pregDat.getField127());
        updatePregDatStatusValues.add(encounterId);
        updatePregDatStatusValues.add(vo.getUuid());
        FormDAO.addModifiedAuditInfo(vo, updatePregDatStatusValues, userName, siteId);
        // id field for statusValues
        updatePregDatStatusValues.add(thisPatientId);
        DatabaseUtils.update(conn, sqlPregnancyDating, updatePregDatStatusValues.toArray());
    }

    /**
     * Updates patient_status table with fields from preg. dating form.
     * @param conn
     * @param queries
     * @param vo
     * @param currentFlowId
     * @param encounterId
     * @param userName
     * @param siteId
     * @param thisPatientId
     * @throws Exception
     */
    public static void updatePatientStatusPregDating(Connection conn, Map queries, EncounterData vo, Long currentFlowId, Long encounterId, String userName, Long siteId, Long thisPatientId) throws Exception {
		PregnancyDating pregDat = (PregnancyDating) vo;
    	String sqlPregnancyDating = (String) queries.get("SQL_MODIFY_STATUS_PREGNANCY_DATING");
        ArrayList updatePregDatStatusValues = new ArrayList();
        // add encounterId to statusValues current_flow_encounter_id
        updatePregDatStatusValues.add(encounterId);
        updatePregDatStatusValues.add(vo.getUuid());
        // add current_flow to statusValues
        updatePregDatStatusValues.add(currentFlowId);
        // ega
        updatePregDatStatusValues.add(pregDat.getField129());
        updatePregDatStatusValues.add(encounterId);
        updatePregDatStatusValues.add(vo.getUuid());
        // lmp
        updatePregDatStatusValues.add(pregDat.getField127());
        updatePregDatStatusValues.add(encounterId);
        updatePregDatStatusValues.add(vo.getUuid());
        FormDAO.addModifiedAuditInfo(vo, updatePregDatStatusValues, userName, siteId);
        // id field for statusValues
        updatePregDatStatusValues.add(thisPatientId);
        DatabaseUtils.update(conn, sqlPregnancyDating, updatePregDatStatusValues.toArray());
    }

    /**
     * Updates the current current_flow_encounter_id and flow_id as well as modified AuditInfo in patient_status table.
     * @param conn
     * @param vo
     * @param currentFlowId
     * @param encounterId
     * @param userName
     * @param siteId
     * @param thisPatientId
     * @param sqlUpdateStatus
     * @throws Exception
     */
    public static void updatePatientStatus(Connection conn, EncounterData vo, Long currentFlowId, Long encounterId, String userName, Long siteId, Long thisPatientId, String sqlUpdateStatus) throws Exception {
        ArrayList updateStatusValues = new ArrayList();
        // add current_flow to statusValues
        updateStatusValues.add(currentFlowId);
        // add encounterId to statusValues current_flow_encounter_id
        updateStatusValues.add(encounterId);
        updateStatusValues.add(vo.getUuid());
        FormDAO.addModifiedAuditInfo(vo, updateStatusValues, userName, siteId);
        // set id field for statusValues
        updateStatusValues.add(thisPatientId);
        DatabaseUtils.update(conn, sqlUpdateStatus, updateStatusValues.toArray());
    }

    /**
     * This query updates pregnancyId, which is used when it's a new pregnancy or newborn
     * This is only for patient registration form.
     * Be sure to populate vo.pregnancy to persist pregnancy.uuid.
     * @param conn
     * @param queries
     * @param vo
     * @param pregnancyId
     * @param encounterId
     * @param thisPatientId
     * @param userName
     * @param siteId
     * @param currentFlowId
     * @throws Exception
     */
    public static void updatePatientStatusPatientReg(Connection conn, Map queries, EncounterData vo, Long pregnancyId, Long encounterId, Long thisPatientId, String userName, Long siteId, Long currentFlowId) throws Exception {
        String sqlUpdateStatus = (String) queries.get("SQL_MODIFY_STATUS_PATIENT_REG");
        ArrayList updateStatusValues = new ArrayList();
        PatientRegistration patReg = (PatientRegistration) vo;
        String firm = patReg.getField1971();
        // add pregnancyId to statusValues current_pregnancy_id
        updateStatusValues.add(pregnancyId);
        Pregnancy pregnancy = vo.getPregnancy();
        String pregnancyUuid = null;
        if (pregnancy != null) {
        	pregnancyUuid = pregnancy.getUuid();
        }
        updateStatusValues.add(pregnancyUuid);
        // add encounterId to statusValues current_pregnancy_encounter_id
        updateStatusValues.add(encounterId);
        updateStatusValues.add(vo.getUuid());
        // add encounterId to statusValues current_flow_encounter_id
        updateStatusValues.add(encounterId);
        updateStatusValues.add(vo.getUuid());
        // add current_flow to statusValues current_flow
        updateStatusValues.add(currentFlowId);
        updateStatusValues.add(firm);
        FormDAO.addModifiedAuditInfo(vo, updateStatusValues, userName, siteId);
        // set id field for statusValues
        updateStatusValues.add(thisPatientId);

        DatabaseUtils.update(conn, sqlUpdateStatus, updateStatusValues.toArray());
    }

    /**
     * Updates patient_status table with fields from preg. dating form.
     * This one is used by newborn eval.
     * Be sure to populate vo.pregnancy to persist pregnancy.uuid.
     * @param conn
     * @param queries
     * @param vo
     * @param pregnancyId
     * @param encounterId
     * @param thisPatientId
     * @param userName
     * @param siteId
     * @param currentFlowId
     * @throws Exception
     */
    public static void updatePatientStatusNewPreg(Connection conn, Map queries, EncounterData vo, Long pregnancyId, Long encounterId, Long thisPatientId, String userName, Long siteId, Long currentFlowId) throws Exception {
        String sqlUpdateStatus = (String) queries.get("SQL_MODIFY_STATUS_PREGNANCY");
        ArrayList updateStatusValues = new ArrayList();
        // add pregnancyId to statusValues current_pregnancy_id
        updateStatusValues.add(pregnancyId);
        Pregnancy pregnancy = vo.getPregnancy();
        String pregnancyUuid = null;
        if (pregnancy != null) {
        	pregnancyUuid = pregnancy.getUuid();
        }
        updateStatusValues.add(pregnancyUuid);
        // add encounterId to statusValues current_pregnancy_encounter_id
        updateStatusValues.add(encounterId);
        updateStatusValues.add(vo.getUuid());
        // add encounterId to statusValues current_flow_encounter_id
        updateStatusValues.add(encounterId);
        updateStatusValues.add(vo.getUuid());
        // add current_flow to statusValues current_flow
        updateStatusValues.add(currentFlowId);
        //updateStatusValues.add(firm);
        FormDAO.addModifiedAuditInfo(vo, updateStatusValues, userName, siteId);
        // set id field for statusValues
        updateStatusValues.add(thisPatientId);

        DatabaseUtils.update(conn, sqlUpdateStatus, updateStatusValues.toArray());
    }

    /**
     * Updates last_modified, last_modified_by, and site_id in patient_status.
     * Use this for new encounters - it properly handles importing patient records from xml
     * @param conn
     * @param vo
     * @param userName
     * @param siteId
     * @param patientId
     * @return
     * @throws Exception
     */
    public static String touchPatientStatus(Connection conn, EncounterData vo, String userName, Long siteId, Long patientId) throws Exception {

        Timestamp lastModified = new Timestamp(System.currentTimeMillis());
        if (vo != null && vo.getLastModified() != null) {
            lastModified = vo.getLastModified();
        }
        String lastModifiedBy = userName;
        if (vo != null && vo.getLastModifiedBy() != null) {
            lastModifiedBy = vo.getLastModifiedBy();
        }
        Long currentSiteId = siteId;
        if (vo != null && vo.getSiteId() != null) {
            currentSiteId = vo.getSiteId();
        }
        ArrayList updateStatusValues = new ArrayList();
        String sqlUpdateStatus;
        // if flowId == history or labs flow, don't update current flow.
        sqlUpdateStatus = "UPDATE patient_status SET last_modified=?, last_modified_by=?, site_id=? WHERE id=?;";
        updateStatusValues.add(lastModified);
        updateStatusValues.add(lastModifiedBy);
        updateStatusValues.add(currentSiteId);
        updateStatusValues.add(patientId);
        DatabaseUtils.update(conn, sqlUpdateStatus, updateStatusValues.toArray());
        return sqlUpdateStatus;
    }

    /**
     * Used when you don't want to update the siteId and potentially throw off patient seaches
     * e.g.: LIMS import
     * @param conn
     * @param vo
     * @param userName
     * @param patientId
     * @return
     * @throws Exception
     */
    public static String touchPatientStatus(Connection conn, EncounterData vo, String userName, Long patientId) throws Exception {

    	Timestamp lastModified = new Timestamp(System.currentTimeMillis());
    	if (vo != null && vo.getLastModified() != null) {
    		lastModified = vo.getLastModified();
    	}
    	String lastModifiedBy = userName;
    	if (vo != null && vo.getLastModifiedBy() != null) {
    		lastModifiedBy = vo.getLastModifiedBy();
    	}
    	ArrayList updateStatusValues = new ArrayList();
    	String sqlUpdateStatus;
    	// if flowId == history or labs flow, don't update current flow.
    	sqlUpdateStatus = "UPDATE patient_status SET last_modified=?, last_modified_by=? WHERE id=?;";
    	updateStatusValues.add(lastModified);
    	updateStatusValues.add(lastModifiedBy);
    	updateStatusValues.add(patientId);
    	DatabaseUtils.update(conn, sqlUpdateStatus, updateStatusValues.toArray());
    	return sqlUpdateStatus;
    }

    /**
     * Updates patient status when admitting a patient.
     * This would not be used when importing a patient record - this is why it takes current values for timestamps.
     * @param conn
     * @param userName
     * @param siteId
     * @param patientId
     * @param firm
     * @return
     * @throws Exception
     */
    public static String updatePatientStatusFirm(Connection conn, String userName, Long siteId, Long patientId, String firm) throws Exception {

        ArrayList updateStatusValues = new ArrayList();
        String sqlUpdateStatus;
        // if flowId == history or labs flow, don't update current flow.
        sqlUpdateStatus = "UPDATE patient_status SET last_modified=?, last_modified_by=?, site_id=?, firm=? WHERE id=?;";
        // add last_modified to statusValues
        updateStatusValues.add(new Timestamp(System.currentTimeMillis()));
        // add last_modified_by to statusValues
        updateStatusValues.add(userName);
        // add siteId to statusValues
        updateStatusValues.add(siteId);
        // set firm
        updateStatusValues.add(firm);
        // set patientId field for statusValues
        updateStatusValues.add(patientId);
        DatabaseUtils.update(conn, sqlUpdateStatus, updateStatusValues.toArray());
        return sqlUpdateStatus;
    }

    /**
     * inserts patient_status record for a new patient.
     * @param queries
     * @param patientId
     * @param currentFlowId
     * @param vo
     * @param userName
     * @param siteId
     * @param conn
     * @throws SQLException
     */
    public static void save(Map queries, Long patientId, Long currentFlowId, EncounterData vo, String userName, Long siteId, Connection conn) throws SQLException {
        String sqlCreateStatus = (String) queries.get("SQL_CREATE_STATUS");
        ArrayList statusValues = new ArrayList();
        // add patient_id to statusValues
        statusValues.add(patientId);
        // add current_flow to statusValues
        statusValues.add(currentFlowId);
        FormDAO.AddAuditInfo(vo, statusValues, userName, siteId);
        // insert patient_status
        DatabaseUtils.create(conn, sqlCreateStatus, statusValues.toArray());
    }

    /**
     * Updates patient_status at end of importing an xml patient record
     * Use newIdMap to resolve import id's to new id's
     * @param patient
     * @param newIdMap
     * @param newPregnancyId
     * @param conn
     * @param queries
     * @param newPatientId
     */
    public static void update(Patient patient, Map newIdMap, Long newPregnancyId, Connection conn, Map queries, Long newPatientId) {
        PatientStatusReport psr = patient.getPatientStatusreport();
        if (patient.getParentId() == null) {
            Long egaId = psr.getCurrentEgaDaysEncounterId();
            if (egaId != null) {
            	if (newIdMap.get(egaId) != null) {
                    psr.setCurrentEgaDaysEncounterId((Long) newIdMap.get(egaId));
            	}
            }
        }

        Long flowId = psr.getCurrentFlowId();
        if (flowId != null) {
            psr.setCurrentFlowId(flowId);
        }

        Long flowEncounterId = psr.getCurrentFlowEncounterId();
        if (flowEncounterId != null) {
            //psr.setCurrentFlowEncounterId(flowId);
            psr.setCurrentFlowEncounterId((Long) newIdMap.get(flowEncounterId));
        } else {
            log.debug("flowEncounterId is Null: patientId: " + patient.getId() + " newPatientId: " + newPatientId);
        }
        Long lmpId = psr.getCurrentLmpDateEncounterId();
        if (lmpId != null) {
            //psr.setCurrentLmpDateEncounterId(lmpId);
            psr.setCurrentLmpDateEncounterId((Long) newIdMap.get(lmpId));
        }
        // This patient may have concluded most recent pregnancy and may not yet have a new pregnancy. Check.
        Long currentPregnancyEncounterId = psr.getCurrentPregnancyEncounterId();
        if (currentPregnancyEncounterId != null) {
            psr.setCurrentPregnancyEncounterId((Long) newIdMap.get(currentPregnancyEncounterId));
        }
        Long currentPregnancyId = psr.getCurrentPregnancyId();
        // What starting a new pregnancy, the currentPregnancyId is null. It's OK.
        if (currentPregnancyId != null) {
            psr.setCurrentPregnancyId(newPregnancyId);
        }
        try {
            updatePatientStatusRecord(conn, queries, psr, newPatientId);
        } catch (Exception e) {
            log.debug("Error updating PatientStatusRecord: " + e);
        }
    }

    /**
     * Updates patient_status at end of importing an xml patient record
     * no longer used
     * @param patient
     * @param newPregnancyId
     * @param conn
     * @param queries
     * @param newPatientId
     */
    public static void update(Patient patient, Long newPregnancyId, Connection conn, Map queries, Long newPatientId) {
        PatientStatusReport psr = patient.getPatientStatusreport();
        psr.setCurrentPregnancyId(newPregnancyId);
        try {
            updatePatientStatusRecordNoEnc(conn, queries, psr, newPatientId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
