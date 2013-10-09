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

import org.cidrz.webapp.dynasite.valueobject.Task;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;

import javax.servlet.ServletException;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Nov 10, 2005
 *         Time: 3:15:02 PM
 */
public class RprTestDAO {

    /**
     * If rpr_result is null, then it's a pending lab test.
     * @param conn
     * @param patientId
     * @param pregnancyId
     * @return list of all pending lab studies
     * @throws javax.servlet.ServletException
     * @throws java.sql.SQLException
     */
    public static List getAll(Connection conn, Long patientId, Long pregnancyId) throws  ServletException, SQLException {
        List items;
        String sql = "SELECT 'RPR' AS label, true AS incomplete, encounter.id AS encounterId, encounter.patient_id AS patientId,\n" +
                "encounter.created AS 'auditInfo.created', site.site_name AS siteName\n" +
                "FROM encounter\n" +
                "JOIN rpr r ON encounter.id = r.id\n" +
                "LEFT JOIN site ON site.id=encounter.site_id\n" +
                "WHERE encounter.patient_id=?\n" +
                "AND encounter.pregnancy_id=?\n" +
                "AND r.rpr_date Is Null;";
        Class clazz = Task.class;
        ArrayList values = new ArrayList();
        values.add(patientId);
        values.add(pregnancyId);
        items = DatabaseUtils.getList(conn, clazz, sql, values);
        return items;
    }
}
