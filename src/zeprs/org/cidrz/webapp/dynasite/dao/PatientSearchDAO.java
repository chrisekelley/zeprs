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
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.StringManipulation;

import javax.management.Query;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;
import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Oct 18, 2005
 *         Time: 3:40:51 PM
 */
public class PatientSearchDAO {

    /**
     * Commons Logging instance.
     */

    static Log log = LogFactory.getFactory().getInstance(PatientSearchDAO.class);

    /**
     * provides search results to Home page search using jstl ResultSupport.toResult(rs);
     * @param conn
     * @param site
     * @param searchString
     * @param offset
     * @param rowCount
     * @param searchType - if searchType = "firstSurname", do search on first letters of surname; otherwise, searchtype is "keyword" and perform keyword search.
     * @param filter - display only patients in labour. Uses filterflow sql, below.
     * @param username
     * @return
     * @throws ServletException
     */
    public static Result getResults(Connection conn, String site, String searchString, int offset, int rowCount, String searchType, int filter, String username) throws ServletException {

        ResultSet rs = null;
        Result results = null;
        String sql = "";
        // some names have a "'" in them; also prevent bad chars from messing up the sql.
        String filteredString = StringManipulation.escapeString(searchString);
        String filterFlow = "   AND (ps.current_flow = 3 OR ps.current_flow = 4) \n" +
               "    AND ((DATEDIFF(CURDATE(), e3.created) IS NULL) OR (DATEDIFF(CURDATE(), e3.created) <=2)) \n" +
                "   AND DATEDIFF(CURDATE(), ps.last_modified) <=2 \n";

        sql="SELECT p.id, p.first_name,p.surname,p2.id AS parent_id,p.dead,p.hiv_positive,\n" +
                "       p2.first_name AS parent_firstname, p2.surname AS parent_surname, \n" +
                "       p.nrc_number,p.district_patient_id, p.alternate_id, ps.last_modified_by, ps.last_modified, ps.current_flow, \n" +
                "       ps.site_id, ps.firm, s.site_name, \n" +
                "       DATE_ADD(ps.current_lmp_date, INTERVAL 280 DAY) AS edd, u.firstname, u.lastname, \n" +
                "       pr.residential_19 AS address1, pr.residential_20 AS address2,\n" +
                "       (YEAR(CURDATE())-YEAR(pr.birth_date_17)) - (RIGHT(CURDATE(),5)<RIGHT(pr.birth_date_17,5)) AS age\n";
         if (filter == 1) {
             sql = sql + ", e2.created AS firstStage, DATEDIFF(CURDATE(), e3.created) AS dateDiff \n";
         }
        sql = sql + "FROM patient p\n";
         if (filter == 1) {
             sql = sql + "LEFT JOIN (encounter e3) ON e3.patient_id = p.id AND e3.form_id = 66\n" +
                     "LEFT JOIN (encounter e2) ON e2.patient_id = p.id AND e2.form_id = 79\n";
         }
        sql = sql + "LEFT JOIN (encounter e) ON (e.patient_id = p.id AND e.form_id=1)\n" +
                "LEFT JOIN (patientregistration pr) ON (pr.id = e.id)\n" +
                "JOIN (patient_status ps) ON (ps.id = p.id)\n" +
                "LEFT JOIN (userdata.address u) ON (u.nickname = ps.last_modified_by)\n" +
                "LEFT JOIN (patient p2) ON (p2.id = p.parent_id)\n" +
                "LEFT JOIN (site s) ON (s.id= ps.site_id)\n";

        if (!site.equals("all")) {
            sql = sql + "WHERE s.id = " + site + "\n";
            if (filter == 1) {
                sql = sql + filterFlow;
            }
        }
        if (searchType.equals("firstSurname") & site.equals("all")) {
            sql = sql + "WHERE ";
        } else if  (searchType.equals("firstSurname") & !site.equals("all")) {
            sql = sql + "AND ";
        }
        if (searchType.equals("firstSurname")) {
            sql = sql + "    p.surname like '" + filteredString + "%' \n";
            if (filter == 1) {
                sql = sql + "    p.surname like '" + filteredString + "%' \n" + filterFlow;
            }
            sql = sql + "ORDER BY p.surname, p.first_name\n";
        } else {
            if (!filteredString.equals("") & site.equals("all")) {
                sql = sql + "WHERE ";
            } else if (!filteredString.equals("") & !site.equals("all")) {
                sql = sql + "AND ";
            }
            if (!filteredString.equals("")) {
                sql = sql + " (p.surname like '%" + filteredString + "%'\n" +
                        "    OR p.first_name like '%" + filteredString + "%'\n" +
                        "    OR p.nrc_number like '%" + filteredString + "'\n" +
                        "    OR p.district_patient_id like '%" + filteredString + "%'\n" +
                		"    OR p.alternate_id like '%" + filteredString + "%')\n";
          //              "    OR ps.last_modified_by like '%" + filteredString + "%') \n";
                if (filter == 1 & (site.equals("all"))) {
                    sql = sql + filterFlow;
                }
            }

            if (filteredString.equals("")) {
                sql = sql + "ORDER BY ps.last_modified DESC \n";
            } else {
                sql = sql + "ORDER BY p.surname, p.first_name \n";
            }
        }

        sql = sql +  "LIMIT " + offset + "," + rowCount +";";

        try {
            Statement s = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery(sql);
            results = ResultSupport.toResult(rs);
            rs.close();
        }  catch (Exception ex) {
            log.info("Search sql: " + sql);
            log.error(ex);
            throw new ServletException("Cannot retrieve results:", ex);
        }
        return results;
    }
}
