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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.gen.PartographStatus;
import org.cidrz.webapp.dynasite.session.SessionUtil;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.PatientRecordUtils;
import org.cidrz.webapp.dynasite.valueobject.*;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: Jun 7, 2005
 * Time: 2:53:05 PM
 * <p/>
 */
public class SessionPatientDAO {

    /**
     * If patientId is sent in the url, then we want to delete the patient in the session - may be leftover
     * from viewing another patient. But we need to check if there is a patient in the session.
     * If it's the same as patientId, no need to hit the db.
     * If ZEPRS gets to the point of having a patient record being updated by more than one person at the same time,
     * we may wish to have this query happen on every page view.
     * <p/>
     * This loads the sessionPatient properties, partographStatus, and children into SessionPatient
     *
     * @param request
     * @return
     * @throws SessionUtil.AttributeNotFoundException
     *
     * @throws SQLException
     * @throws ServletException
     */

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(SessionPatientDAO.class);

    /**
     * Loads sessionPatient if necessary. Has patient record been modified since sessionPatient was last loaded?
     *
     * @param conn
     * @param request
     * @return
     * @throws SessionUtil.AttributeNotFoundException
     *
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    static public Long getPatientId(Connection conn, HttpServletRequest request) throws SessionUtil.AttributeNotFoundException, SQLException, ServletException, ObjectNotFoundException {

        HttpSession session = request.getSession();
        Long patientId = null;
        Long sessionPatientId = null;
        Long pregnancyId = new Long("0");
        SessionPatient sessionPatient = null;
        Long sessionPregnancyId = null;

        if (request.getParameter("patientId") != null) {
            if (!request.getParameter("patientId").equals("")) {
                patientId = Long.decode(request.getParameter("patientId"));
            }
        }

        if (request.getParameter("pregnancyId") != null) {
            if (!request.getParameter("pregnancyId").equals("")) {
                pregnancyId = Long.decode(request.getParameter("pregnancyId"));
            }
        }

        try {
            sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
            sessionPatientId = sessionPatient.getId();
            sessionPregnancyId = sessionPatient.getCurrentPregnancyId();
        } catch (SessionUtil.AttributeNotFoundException e) {
            // need to initialise the sessionPatient
            if (patientId != null) {
                updateSessionPatient(conn, patientId, pregnancyId, session);
            }

        }

        if ((patientId != null) && (!patientId.equals(sessionPatientId))) {
            //clear the session data from the previous patient.
            SessionUtil.getInstance(session).setSessionPatient(null);
            // load the sessionPatient
            updateSessionPatient(conn, patientId, pregnancyId, session);
        } else {
            // has patient record been modified since sessionPatient was last loaded?
            Timestamp lastModified = null;
            try {
                lastModified = PatientDAO.getLastModified(conn, sessionPatientId).getLastModified();
                if (!sessionPatient.getLastModified().equals(lastModified)) {
                    // reload sessionPatient
                    updateSessionPatient(conn, sessionPatientId, sessionPregnancyId, session);
                }
            } catch (SQLException e) {
                log.error(e);
            } catch (ServletException e) {
                log.error(e);
            } catch (ObjectNotFoundException e) {
                // it's ok
            }
        }

        patientId = SessionUtil.getInstance(session).getSessionPatient().getId();

        if (pregnancyId != null) {
            // fetch the pregnancy
        }

        return patientId;
    }

    /**
     * Initialises/Updates the sessionPatient
     *
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param session
     * @throws ServletException
     * @throws SQLException
     */
    public static void updateSessionPatient(Connection conn, Long patientId, Long pregnancyId, HttpSession session) throws ServletException, SQLException, SessionUtil.AttributeNotFoundException, ObjectNotFoundException {
        SessionPatient sessionPatient = getSessionPatient(conn, patientId, pregnancyId);
        if (sessionPatient.getCurrentEgaDaysEncounterId() != null) {
            sessionPatient = PatientRecordUtils.updateCurrentEga(conn, sessionPatient.getCurrentEgaDaysEncounterId(), sessionPatient);
        }
        if (pregnancyId.intValue() == 0) {
            pregnancyId = sessionPatient.getCurrentPregnancyId();
        }
        if (pregnancyId != null && pregnancyId.intValue() > 0) {
            sessionPatient = updatePartographStatus(conn, patientId, sessionPatient);
            Boolean deliveryStatus = EncountersDAO.checkEncounter(conn, patientId, pregnancyId, new Long("66"));
            sessionPatient.setDeliveryCompleted(deliveryStatus);
            String uuid = sessionPatient.getUuid();
            List children = PatientDAO.getChildren(conn, uuid, pregnancyId);
            sessionPatient.setChildren(children);
            Pregnancy pregnancy = PregnancyDAO.getOne(conn, pregnancyId);
            sessionPatient.setPregnancy(pregnancy);
        } else {
            // create a fake empty pregnancy
            createEmptyPregnancy(sessionPatient);
        }
        // if ZEPRS patient has a parentId, then this patient is an infant of a ZEPRS patient.
        if (sessionPatient.getParentId() != null) {
            sessionPatient.setMotherStatus(Boolean.FALSE);
            Patient mother = null;
			try {
				mother = PatientDAO.getOne(conn, sessionPatient.getParentId());
				sessionPatient.setMother(mother);
	            if (mother.getHivPositive() != null && mother.getHivPositive().byteValue() == 1) {
	               sessionPatient.setMotherHivPositive(true);
	            }
			} catch (ObjectNotFoundException e) {
				log.debug("Mother not in database for patient: " + patientId + " and mother id: " + sessionPatient.getParentId());
			}
        }
        List longTermProblemsClassed = new ArrayList();
        List longTermProblems = OutcomeDAO.getLongTermProblems(conn, patientId);
        if (longTermProblems != null) {
            try {
                PatientRecordUtils.classOutcomes(longTermProblems, longTermProblemsClassed);
                sessionPatient.setLongTermProblems(longTermProblemsClassed);
            } catch (IllegalAccessException e) {
                log.error(e);
            } catch (InvocationTargetException e) {
                log.error(e);
            }
        }
        SessionUtil.getInstance(session).setSessionPatient(sessionPatient);
    }

    /**
     * This is used for testing and creating test patients
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param session
     * @param sessionPatient
     * @return
     * @throws ServletException
     * @throws SQLException
     * @throws SessionUtil.AttributeNotFoundException
     * @throws ObjectNotFoundException
     */
    public static SessionPatient updateSessionPatient(Connection conn, Long patientId, Long pregnancyId, HttpSession session, SessionPatient sessionPatient) throws ServletException, SQLException, SessionUtil.AttributeNotFoundException, ObjectNotFoundException {
        sessionPatient = getSessionPatient(conn, patientId, pregnancyId);
        SessionUtil.getInstance(session).setSessionPatient(sessionPatient);
        if (sessionPatient.getCurrentEgaDaysEncounterId() != null) {
            sessionPatient = PatientRecordUtils.updateCurrentEga(conn, sessionPatient.getCurrentEgaDaysEncounterId(), sessionPatient);
        }
        sessionPatient = updatePartographStatus(conn, patientId, sessionPatient);
        Boolean deliveryStatus = EncountersDAO.checkEncounter(conn, patientId, pregnancyId, new Long("66"));
        sessionPatient.setDeliveryCompleted(deliveryStatus);
        String uuid = sessionPatient.getUuid();
        List children = PatientDAO.getChildren(conn, uuid, pregnancyId);
        SessionUtil.getInstance(session).getSessionPatient().setChildren(children);
        // if ZEPRS patient has a parentId, then this patient is an infant of a ZEPRS patient.
        if (sessionPatient.getParentId() != null) {
            sessionPatient.setMotherStatus(Boolean.FALSE);
            Patient mother = PatientDAO.getOne(conn, sessionPatient.getParentId());
            sessionPatient.setMother(mother);
        }
        return sessionPatient;
    }

    public static SessionPatient getSessionPatient(Connection conn, Long patientId, Long pregnancyId) throws ServletException, SQLException, ObjectNotFoundException {
        SessionPatient sessionPatient;
        ArrayList values = new ArrayList();
        String sql;
        if (pregnancyId.longValue() == 0) {
            // use currentPregnancy value
            sql = "select p.id, p.first_name AS firstName, p.surname AS surname, p.nrc_number AS nrcNumber, " +
                    "p.district_patient_id AS districtPatientid, p.birthdate AS birthdate, p.time_of_birth AS timeOfBirth, " +
                    "p.sex, p.hiv_positive AS hivPositive, p.uuid AS uuid, p.parent_uuid AS parentUuid, " +
                    "s.current_ega_days_encounter_id AS currentEgaDaysEncounterId, " +
                    "s.current_ega_days AS currentEgaDaysDB, " +
                    "s.current_lmp_date AS currentLmpDate, " +
                    "s.current_lmp_date_encounter_id AS currentLmpDateEncounterId, " +
                    "s.current_pregnancy_id AS currentPregnancyId, " +
                    "preg.date_pregnancy_begin AS datePregnancyBegin, " +
                    "preg.date_pregnancy_end AS datePregnancyEnd, " +
                    "preg.pregnancy_begin_encounter_id AS pregnancyBeginEncounterId, " +
                    "preg.pregnancy_end_encounter_id AS pregnancyEndEncounterId, " +
                    "preg.labour_admission_encounter_id AS labourAdmissionEncounterId, " +
                    "preg.date_labour_admission AS dateActiveLabour, " +
                    "s.current_flow AS currentFlowId, " +
                    "s.current_flow_encounter_id AS currentFlowEncounterId, " +
                    "s.last_modified AS lastModified, " +
                    "s.no_prev_pregnancies AS noPreviousPregnancies, " +
                    "s.firm AS firm, " +
                    "e.form_id AS currentFormId, parent_id AS parentId " +
                    "FROM patient p, patient_status s " +
                    "LEFT JOIN encounter e ON s.current_flow_encounter_id = e.id " +
                    "LEFT JOIN pregnancy preg ON s.current_pregnancy_id=preg.id " +
                    "where p.id=s.id " +
                    "and p.id=?;";
            values.add(patientId);
        } else if (pregnancyId.longValue() == -1) {
            // use currentPregnancy value
            sql = "select p.id, p.first_name AS firstName, p.surname AS surname, p.nrc_number AS nrcNumber, " +
                    "p.district_patient_id AS districtPatientid, p.birthdate AS birthdate, p.time_of_birth AS timeOfBirth, " +
                    "p.sex, p.hiv_positive AS hivPositive, p.uuid AS uuid, p.parent_uuid AS parentUuid, " +
                    "s.last_modified AS lastModified, " +
                    "parent_id AS parentId " +
                    "FROM patient p, patient_status s " +
                    "LEFT JOIN encounter e ON s.current_flow_encounter_id = e.id " +
                    "LEFT JOIN pregnancy preg ON s.current_pregnancy_id=preg.id " +
                    "where p.id=s.id " +
                    "and p.id=?;";
            values.add(patientId);
        } else {
            sql = "SELECT p.id, p.first_name AS firstName, p.surname AS surname, p.nrc_number AS nrcNumber, " +
                    "p.district_patient_id AS districtPatientid, p.birthdate AS birthdate, p.time_of_birth AS timeOfBirth, " +
                    "p.sex, p.hiv_positive AS hivPositive, p.uuid AS uuid, p.parent_uuid AS parentUuid, " +
                    "s.current_ega_days_encounter_id AS currentEgaDaysEncounterId, " +
                    "s.current_ega_days AS currentEgaDaysDB, " +
                    "s.current_lmp_date AS currentLmpDate, " +
                    "s.current_lmp_date_encounter_id AS currentLmpDateEncounterId, " +
                    "preg.id AS currentPregnancyId, " +
                    "preg.date_pregnancy_begin AS datePregnancyBegin, " +
                    "preg.date_pregnancy_end AS datePregnancyEnd, " +
                    "preg.pregnancy_begin_encounter_id AS pregnancyBeginEncounterId, " +
                    "preg.pregnancy_end_encounter_id AS pregnancyEndEncounterId, " +
                    "preg.labour_admission_encounter_id AS labourAdmissionEncounterId, " +
                    "preg.date_labour_admission AS dateActiveLabour, " +
                    "s.current_flow AS currentFlowId, " +
                    "s.current_flow_encounter_id AS currentFlowEncounterId, " +
                    "s.last_modified AS lastModified, " +
                    "s.no_prev_pregnancies AS noPreviousPregnancies, " +
                    "s.firm AS firm, " +
                    "e.form_id AS currentFormId, parent_id AS parentId " +
                    "FROM pregnancy preg " +
                    "LEFT JOIN (patient_status s, patient p) ON s.id = p.id " +
                    "LEFT JOIN encounter e ON e.id = s.current_flow_encounter_id " +
                    "WHERE p.id=? " +
                    "and preg.id=?";
            values.add(patientId);
            values.add(pregnancyId);
        }
        sessionPatient = (SessionPatient) DatabaseUtils.getZEPRSBean(conn, SessionPatient.class, sql, values);
        return sessionPatient;
    }

    /**
     * Set the partograph in the session
     *
     * @param conn
     * @param patientId
     * @param sessionPatient
     */
    public static SessionPatient updatePartographStatus(Connection conn, Long patientId, SessionPatient sessionPatient) {
        PartographStatus partographStatus = null;
        Long pregnancyId = sessionPatient.getCurrentPregnancyId();
        try {
            // partographStatus = (PartographStatus) DatabaseUtils.getBean(PartographStatus.class, sql, values);
            partographStatus = (PartographStatus) EncountersDAO.getMostRecentRecord(conn, patientId, pregnancyId, new Long("79"), PartographStatus.class);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } catch (ObjectNotFoundException e) {
            // it's ok
        } catch (IOException e) {
            log.error(e);
        }
        if (partographStatus != null) {
            sessionPatient.setPartographStatus(partographStatus);
        }
        return sessionPatient;
    }

    /**
     * You are able to view data from a patient's different pregnancies.
     * This query put data about a particular pregnancy into the sessionPatient object.
     * unused
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param session
     * @throws ServletException
     * @throws SQLException
     * @throws SessionUtil.AttributeNotFoundException
     *
     */
    public static void updateCurrentPregnancy(Connection conn, Long patientId, Long pregnancyId, HttpSession session) throws ServletException, SQLException, SessionUtil.AttributeNotFoundException, ObjectNotFoundException {
        String sql;
        ArrayList values;
        SessionPatient qrySessionPatient = new SessionPatient();
        SessionPatient sessionPatient = SessionUtil.getInstance(session).getSessionPatient();
        sql = "select date_pregnancy_began, date_pregnancy_end from pregnancy where id=? and patient_id=?";
        values = new ArrayList();
        values.add(pregnancyId);
        values.add(patientId);
        sessionPatient = (SessionPatient) DatabaseUtils.getBean(conn, SessionPatient.class, sql, values);
        //BeanUtils.copyProperties();
    }


    private static void createEmptyPregnancy(SessionPatient sessionPatient) throws SessionUtil.AttributeNotFoundException {
        Pregnancy pregnancy;
        Long pregnancyId;
        pregnancy = new Pregnancy();
        pregnancy.setId(Long.valueOf("-1"));
        pregnancyId = Long.decode("-1");
        sessionPatient.setCurrentPregnancyId(pregnancyId);
        sessionPatient.setDatePregnancyBegin(null);
        sessionPatient.setDatePregnancyEnd(null);
        List children = new ArrayList();
        sessionPatient.setChildren(children);
    }
}