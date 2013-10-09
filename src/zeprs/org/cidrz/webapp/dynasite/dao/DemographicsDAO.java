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
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.report.PatientRegistrationClean;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

/**
 * Provide objects for Demographics page
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 13, 2005
 *         Time: 7:09:58 PM
 */
public class DemographicsDAO {
    /**
     * Get the id of this encounter.
     *
     * @param conn
     * @param patientId
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static Long getDemographicsId(Connection conn, Long patientId) throws SQLException, ServletException, ObjectNotFoundException {
        Long formId = (long) 1;
        EncounterData encounter = null;
        String sql;
        ArrayList values;
        sql = "select id from encounter where patient_id=? and form_id=?";
        values = new ArrayList();
        values.add(patientId);
        values.add(formId);
        encounter = (EncounterData) DatabaseUtils.getBean(conn, EncounterData.class, sql, values);
        return encounter.getId();
    }

    /**
     * Patient registration info w/ convenient fields - for reports
     *
     * @param conn
     * @param patientId
     * @return
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     * @throws ClassNotFoundException
     */
    public static Object getOne(Connection conn, Long patientId) throws ServletException, SQLException, ObjectNotFoundException, ClassNotFoundException {
        Object item;
        String sql = "SELECT encounter.id AS id, " +
                "surname_6 AS surname, \n" +
                "forenames_7 AS forenames, \n" +
                "nrc_no_9 AS nrcNo, \n" +
                "obstetric_record_number_1134 AS obstetricRecordNumber, \n" +
                "age_at_first_attendence_1135 AS ageAtFirstAttendence, \n" +
                "birth_date_17 AS birthDate, \n" +
                "education_st_11 AS educationStatus, \n" +
                "residential_19 AS address1, \n" +
                "residential_20 AS address2, \n" +
                "date_of_resi_21 AS dateOfResidenceChange, \n" +
                "marital_stat_10 AS maritalStatus, \n" +
                "occupation_12 AS occupation, \n" +
                "occupation_other AS occupationOther, \n" +
                "nearby_place_worship_39 AS nearbyPlaceWorship, \n" +
                "religion_1239 AS religion, \n" +
                "religion_other_1240 AS religionOther, \n" +
                "surname_p_father_24 AS surnameFather, \n" +
                "forenames_p_father_25 AS forenamesFather, \n" +
                "surname_husband_26 AS surnameHusband, \n" +
                "forenames_husband_27 AS forenamesHusband, \n" +
                "occupation_husband_28 AS occupationHusband, \n" +
                "tel_no_husband_32 AS telNoHusband, \n" +
                "surname_guardian_33 AS surnameGuardian, \n" +
                "forenames_guardian_34 AS forenamesGuardian, \n" +
                "surname_emerg_contact_35 AS surnameEmergContact, \n" +
                "forenames_emerg_contact_36 AS forenameEemergContact, \n" +
                "address_emerg_contact_37 AS addressEmergContact, \n" +
                "tel_no_emerg_contact_38 AS telNoEmergContact, \n" +
                "phone AS patientPhone, \n" +
                "patient_id AS patientId, \n" +
                "form_id AS formId, \n" +
                "flow_id AS flowId, \n" +
                "date_visit AS dateVisit, \n" +
                "pregnancy_id AS pregnancyId, \n" +
                "last_modified AS lastModified, \n" +
                "created AS created, \n" +
                "last_modified_by AS lastModifiedBy, \n" +
                "created_by AS createdBy, \n" +
                "site_id AS siteId, \n" +
                "site_name AS siteName, \n" +
                "sex_490 AS sex, \n" +
                "patient_id_number AS districtPatientid, \n" +
                "patient_id_number AS patientIdNumber, \n" +    // compatability with some reports
                "CONCAT_WS(', ', u2.lastname, u2.firstname) AS lastModifiedByName, \n" +
                "uth_referral_type AS uthReferralTypeId\n" +
                "FROM zeprs.patientregistration, \n" +
                "zeprs.encounter LEFT JOIN site ON site.id=encounter.site_id \n" +
                "LEFT JOIN userdata.address u2 ON u2.nickname = zeprs.encounter.last_modified_by \n" +
       //         "LEFT JOIN admin.field_enumeration fe1 on fe1.id = patientregistration.uth_referral_type\n" +
                "WHERE zeprs.encounter.id = zeprs.patientregistration.id AND zeprs.encounter.patient_id=?;";
        ArrayList values = new ArrayList();
        values.add(patientId);
        item = DatabaseUtils.getZEPRSBean(conn, PatientRegistrationClean.class, sql, values);
        return item;
    }
}