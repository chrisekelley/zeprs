/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.utils;

import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: chris
 * Date: Oct 21, 2004
 * Time: 11:14:25 AM
 * @deprecated No longer used.
 */

public class Migrate {
    public int getSchema() throws Exception, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zeprs", "root", "**pasword**");

        String encSQL = "SELECT v.id AS evid, er.patient_id, f.id AS field_id, er.last_modified, er.created, er.last_modified_by, er.created_by\n" +
                "    FROM encounter_record er, encounter_value v, page_item p,form_field f\n" +
                "    where v.encounter_id = er.id\n" +
                "    AND v.page_item_id = p.id\n" +
                "    AND p.form_field_id = f.id\n" +
                "    ORDER BY er.patient_id";
        System.out.println(encSQL);
        PreparedStatement encSTMT = con.prepareStatement(encSQL);
        ResultSet rs = encSTMT.executeQuery();
        PreparedStatement updateEV;
        String updateString = "UPDATE encounter_value " +
                "set patient_id=?, " +
                "field_id=?, " +
                "last_modified=?, " +
                "created=?, " +
                "last_modified_by=?, " +
                "created_by=? " +
                "WHERE id=?;";
        //+
        //"AND patient_id=1";
        updateEV = con.prepareStatement(updateString);
        System.out.println(updateString);
        int totalUpdates = 0;
        while (rs.next()) {
            //System.out.println(rs.getInt(1)+":"+rs.getInt(3)+":"+rs.getTimestamp(4));
            updateEV.setInt(1, rs.getInt(2));
            updateEV.setInt(2, rs.getInt(3));
            updateEV.setTimestamp(3, rs.getTimestamp(4));
            updateEV.setTimestamp(4, rs.getTimestamp(5));
            updateEV.setString(5, rs.getString(6));
            updateEV.setString(6, rs.getString(7));
            updateEV.setInt(7, rs.getInt(1));
            int n = updateEV.executeUpdate();
            totalUpdates += n;
        }

        return totalUpdates;
    }

}
