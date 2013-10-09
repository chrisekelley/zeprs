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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: dave
 * Date: Mar 9, 2004
 * Time: 2:17:25 PM
 * @deprecated No longer used.
 */
public class StarSchemaGenerator {
    public static void main(String[] args) {
        try {
            new StarSchemaGenerator().getSchema();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getSchema() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        StringBuffer schema = null;
        DatabaseMetaData metadata = null;
        Connection currentConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zeprs", "root", "");

        String formSQL = "select form.name as form_name, concat(\"field\", form_field.id) as field_name from form, form_field where form.id=form_field.form_id order by form_name, form_field.id";
        PreparedStatement forms = currentConnection.prepareStatement(formSQL);

        ResultSet rs = forms.executeQuery();
        String currentTable = "";
        boolean firstField = true;

        Map prepInserts = new HashMap();
        StringBuffer insertSQL = null;

        while (rs.next()) {
            if (!rs.getString("form_name").equals(currentTable)) {
                //new table
                if (!currentTable.equals("")) {
                    //finish off previous table
                    finishTable(currentConnection, currentTable, schema, insertSQL, prepInserts);
                }
                schema = new StringBuffer();
                insertSQL = new StringBuffer();
                currentTable = rs.getString("form_name");
                schema.append("CREATE TABLE " + currentTable + "(\n");
                schema.append("    encounter_id INTEGER,");
                schema.append("    created DATETIME");
                insertSQL.append("INSERT INTO " + currentTable + " VALUES (?,?");
            }
            schema.append(",\n    " + rs.getString("field_name") + " VARCHAR(255)");
            insertSQL.append(",?");
            firstField = false;
        }
        if (!currentTable.equals("")) {
            finishTable(currentConnection, currentTable, schema, insertSQL, prepInserts);
        }

        String encounterSQL = "select f.name, value, e.id, e.created from form f, encounter_record e LEFT JOIN encounter_value v ON (e.id = v.encounter_id) where f.id = e.form_id order by f.id, e.id, v.field_id";
        PreparedStatement encounterValuesPS = currentConnection.prepareStatement(encounterSQL);
        rs = encounterValuesPS.executeQuery();
        int currentEncounter = -1;
        int index = 1;
        PreparedStatement insertPS = null;
        while (rs.next()) {
            if (rs.getInt(3) != currentEncounter) {
                if (insertPS != null) {
                    insertPS.execute();
                }
                insertPS = (PreparedStatement) prepInserts.get(rs.getString(1));
                insertPS.clearParameters();
                pad(insertPS);
                index = 1;
                currentEncounter = rs.getInt(3);
                insertPS.setInt(index++, currentEncounter);
                insertPS.setDate(index++, rs.getDate(4));
            }
            insertPS.setString(index++, rs.getString(2));
        }
        if (insertPS != null) {
            insertPS.execute();
        }
    }

    private void pad(PreparedStatement insertPS) {
        int index = 1;
        while (true) {
            try {
                insertPS.setString(index++, null);
            } catch (SQLException e) {
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
    }


    private void finishTable(Connection currentConnection, String currentTable, StringBuffer schema, StringBuffer insertSQL, Map prepInserts)
            throws SQLException {
        dropTable(currentConnection, currentTable);
        schema.append("\n);\n");
        insertSQL.append(")");
        //System.out.println(insertSQL.toString());
        prepInserts.put(currentTable, currentConnection.prepareStatement(insertSQL.toString()));
        //System.out.print(schema.toString());
        PreparedStatement createTables = currentConnection.prepareStatement(schema.toString());
        createTables.execute();
    }

    private void dropTable(Connection con, String currentTable) {
        try {
            Statement s = con.createStatement();
            s.execute("drop table " + currentTable);
        } catch (SQLException e) {
            System.out.println("failed to drop table " + currentTable + ". Ex: " + e.getMessage());
        }
    }

}
