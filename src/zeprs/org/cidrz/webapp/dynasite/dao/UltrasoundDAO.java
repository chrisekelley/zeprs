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

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Oct 10, 2005
 *         Time: 1:59:25 PM
 */
public class UltrasoundDAO {



    /**
     * Fetch all fetal ultrasound exams for this patient/pregnancy/ultrasound encounter using the exam sequence number.
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @param formId
     * @param clazz
     * @return
     * @throws java.io.IOException
     * @throws ServletException
     * @throws SQLException
     */
    public static List getExams(Connection conn, Integer examSequence, Long patientId, Long pregnancyId, Long formId, Class clazz) throws IOException, ServletException, SQLException {
        List items;
        String sql = "SELECT encounter.id AS id, exam_sequence_number AS field1916, sequence_number_fetus AS field1915, " +
                "condition_of_foetus_964 AS field964, lie_313 AS field313, presentation_314 AS field314, " +
                "presentation_other AS field1508, biparietal_diameter_955 AS field955, femur_length_956 AS field956, " +
                "fetal_abdomi_957 AS field957, weight AS field1947, patient_id AS patientId, form_id AS formId, flow_id AS flowId, " +
                "date_visit AS dateVisit, pregnancy_id AS pregnancyId, last_modified AS lastModified, created AS created, " +
                "last_modified_by AS lastModifiedBy, created_by AS createdBy, site_id AS siteId  " +
                "FROM ultrasoundfetuseval, encounter " +
                "WHERE encounter.id = ultrasoundfetuseval.id " +
                "AND encounter.patient_id=? " +
                "AND encounter.pregnancy_id=? " +
                "AND ultrasoundfetuseval.exam_sequence_number=?";
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        values.add(examSequence);
        items = DatabaseUtils.getList(conn, clazz, sql, values);
        return items;
    }


}
