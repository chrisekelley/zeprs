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

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Mar 30, 2006
 *         Time: 10:10:56 PM
 */
public class PatientRegistrationDAO {

    /**
     * Updates value for firm when admitting a patient
     * @param conn
     * @param encounterId
     * @param firm
     * @throws Exception
     */
        public static void updateFirm(Connection conn, Long encounterId, String firm) throws Exception {
        ArrayList values = new ArrayList();
        String sql;
        sql = "UPDATE patientregistration p, encounter e " +
                "SET p.firm=? " +
                "WHERE p.id=e.id AND e.patient_id=?;";
        values.add(firm);
        values.add(encounterId);
        DatabaseUtils.update(conn, sql, values.toArray());
    }

}
