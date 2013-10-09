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
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.RuleDefinition;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.*;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 12, 2005
 *         Time: 8:54:17 AM
 */
public class RuleDefinitionDAO {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(RuleDefinitionDAO.class);

    /**
     * Returns whole graph - including rule params
     *
     * @param conn
     * @param fieldId
     * @return list of rule defs for form field id
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAll(Connection conn, Long fieldId) throws SQLException, ServletException {
        List list = new ArrayList();
        String sql;
        ArrayList<Long> values;
        sql = "select ruledef.id as id," +
                "ruledef.rule_class as ruleClass," +
                "ruledef.outcome_class as outcomeClass," +
                "ruledef.form_id as formId," +
                "ruledef.field_id as fieldId," +
                "ruledef.is_enabled as enabled, " +
                "ruledef.message as message, " +
                "ruledef.operand as operand, " +
                "ruledef.operator as operator, " +
                "ruledef.all_pregs as allPregnancies " +
                "from rule_definition ruledef " +
                "WHERE field_id=?";
        values = new ArrayList<Long>();
        values.add(fieldId);
        list = DatabaseUtils.getList(conn, RuleDefinition.class, sql, values);
        for (int j = 0; j < list.size(); j++) {
            RuleDefinition ruleDefinition = (RuleDefinition) list.get(j);
            // get the RuleDefParams
            String ruleParamSql = "Select ruleparams.param_value as value, " +
                    "ruleparams.param_name as name " +
                    "from rule_definition_param ruleparams " +
                    "where ruleparams.rule_definition_id=?";
            ArrayList<Long> ruleParamValues = new ArrayList<Long>();
            ruleParamValues.add(ruleDefinition.getId());
            Map ruleParamEnum = DatabaseUtils.getArrayList(conn, ruleParamSql, ruleParamValues);
            if (ruleParamEnum != null) {
                ruleDefinition.setRuleParams(ruleParamEnum);
            }
        }
        return list;
    }

    /**
     * Create map of rules in format (formfieldId, rule)
     *
     * @return
     * @throws SQLException
     * @throws ServletException
     * @param conn
     */
    public static HashMap getAll(Connection conn) throws SQLException, ServletException {
        HashMap ruleMap = new HashMap();
        //List fieldRules = new ArrayList();
        List formfields = FormFieldDAO.getIds(conn);
        for (int i = 0; i < formfields.size(); i++) {
            FormField formField = (FormField) formfields.get(i);
            Long formFieldId = formField.getId();
            List list = new ArrayList();
            String sql;
            sql = "select ruledef.id as id," +
                    "ruledef.rule_class as ruleClass," +
                    "ruledef.outcome_class as outcomeClass," +
                    "ruledef.form_id as formId," +
                    "ruledef.field_id as fieldId," +
                    "ruledef.is_enabled as enabled, " +
                    "ruledef.message as message, " +
                    "ruledef.operand as operand, " +
                    "ruledef.operator as operator, " +
                    "ruledef.all_pregs as allPregnancies " +
                    "from rule_definition ruledef " +
                    "WHERE field_id=? " +
                    "ORDER BY ruledef.field_id";
            ArrayList<Long> values = new ArrayList<Long>();
            values.add(formFieldId);
            list = DatabaseUtils.getList(conn, RuleDefinition.class, sql, values);
            if (list.size() > 0) {
                ruleMap.put(formFieldId, list);
            }
        }

        return ruleMap;
    }

    /**
     * Create list of rules for admin display ("field" + formfieldId, ruleList for formfieldId)
     *
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static HashMap getRuleList() throws SQLException, ServletException {
        Connection conn = DatabaseUtils.getAdminConnection();
        HashMap ruleMap = new HashMap();
        //List fieldRules = new ArrayList();
        List formfields = FormFieldDAO.getIds(conn);
        for (int i = 0; i < formfields.size(); i++) {
            FormField formField = (FormField) formfields.get(i);
            Long formFieldId = formField.getId();
            List list = new ArrayList();
            String sql;
            sql = "select ruledef.id as id," +
                    "ruledef.rule_class as ruleClass," +
                    "ruledef.outcome_class as outcomeClass," +
                    "ruledef.form_id as formId," +
                    "ruledef.field_id as fieldId," +
                    "field.label as fieldName," +
                    "ruledef.is_enabled as enabled " +
                    "from rule_definition ruledef, form_field field " +
                    "WHERE ruleDef.field_id = field.id " +
                    "AND field_id=? " +
                    "ORDER BY ruledef.form_id, ruledef.field_id";
            ArrayList<Long> values = new ArrayList<Long>();
            values.add(formFieldId);
            list = DatabaseUtils.getList(conn, RuleDefinition.class, sql, values);
            if (list.size() > 0) {
                for (int j = 0; j < list.size(); j++) {
                    RuleDefinition ruleDefinition = (RuleDefinition) list.get(j);
                    // get the RuleDefParams
                    String ruleParamSql = "Select ruleparams.param_value as value, " +
                            "ruleparams.param_name as name " +
                            "from rule_definition_param ruleparams " +
                            "where ruleparams.rule_definition_id=?";
                    ArrayList<Long> ruleParamValues = new ArrayList<Long>();
                    ruleParamValues.add(ruleDefinition.getId());
                    Map ruleParamEnum = DatabaseUtils.getArrayList(conn, ruleParamSql, ruleParamValues);
                    if (ruleParamEnum != null) {
                        ruleDefinition.setRuleParams(ruleParamEnum);
                    }
                }
                ruleMap.put("field" + formFieldId, list);
            }
        }

        return ruleMap;
    }

    public static Object updateRule(Connection conn, Long id, String element, String value) throws SQLException, ServletException {
        ArrayList values = new ArrayList();
        String sql = "UPDATE rule_definition " +
                "SET " + element + "='" + value + "' WHERE id=" + id;
        Long encId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return encId;
    }

    /**
     * This is a one-time-only method to convert the schema for rule definitions
     * @throws SQLException
     * @throws ServletException
     */
    public static void updateRules() throws SQLException, ServletException {

        HashMap ruleMap = DynaSiteObjects.getRules();
        Set ruleSet = ruleMap.entrySet();
        for (Iterator iterator = ruleSet.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            // Long formFieldId = (Long) entry.getKey();
            List rules = (List) entry.getValue();
            for (int i = 0; i < rules.size(); i++) {
                RuleDefinition ruleDefinition = (RuleDefinition) rules.get(i);
                Map ruleParams = ruleDefinition.getRuleParams();
                Long formId = null;
                if (ruleParams.get("formId") != null) {
                 formId =  new Long((String) ruleParams.get("formId"));
                }
                String message = (String) ruleParams.get("message");
                String operand = (String) ruleParams.get("operand");
                String operator = (String) ruleParams.get("operator");

                Long id = ruleDefinition.getId();
                ArrayList values = new ArrayList();
                String sql = null;
                if (formId == null) {
                    sql = "UPDATE rule_definition " +
                            "SET message=?, operand=?, operator=? " +
                            "WHERE id=?;";
                } else {
                    sql = "UPDATE rule_definition " +
                            "SET form_id=?, message=?, operand=?, operator=? " +
                            "WHERE id=?;";
                    values.add(formId);
                }

                values.add(message);
                values.add(operand);
                values.add(operator);
                values.add(id);
                DatabaseUtils.create(sql, values.toArray());
            }
        }
    }

    /**
     * inserts a rule
     * unused
     * @param conn
     * @param ruleClazz
     * @param outcomeClazz
     * @param formId
     * @param fieldId
     * @param enabled
     * @param allPregnancies
     * @param userName
     * @param siteId
     * @param message
     * @param operand
     * @param operator
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Object insertRule(Connection conn, String ruleClazz, String outcomeClazz, Long formId, Long fieldId, Boolean enabled, Boolean allPregnancies, String userName, Long siteId, String message, String operand, String operator) throws SQLException, ServletException {
        ArrayList values = new ArrayList();
        String sql = "INSERT INTO admin.rule_definition " +
                "SET rule_class=?, outcome_class=?, form_id=?, field_id=?, is_enabled=?, last_modified=?, created=?, " +
                "last_modified_by=?, created_by=?, site_id=?, message=?, operand=?, operator=?, all_pregs=?;";
        values.add(ruleClazz);
        values.add(outcomeClazz);
        values.add(formId);
        values.add(fieldId);
        values.add(enabled);
        FormDAO.AddAuditInfo(values, userName, siteId);
        values.add(message);
        values.add(operand);
        values.add(operator);
        values.add(allPregnancies);
        Long encId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return encId;
    }

}
