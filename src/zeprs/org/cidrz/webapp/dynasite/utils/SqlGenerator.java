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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.project.zeprs.valueobject.partograph.PartographMapping;

import javax.servlet.ServletException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 13, 2005
 *         Time: 6:50:04 PM
 */
public class SqlGenerator {
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());


    /**
     * Iterates through the forms from the database and makes sql files used by dynasite.
     * @param dev
     */
    public void createSourceFiles(Boolean dev) throws ServletException, PersistenceException, IOException, SQLException {
        String sqlPathname = null;
        //sqlPathname = System.getProperty("CATALINA_HOME") + File.pathSeparator + "webapps" + File.pathSeparator + "zeprs" + File.pathSeparator + "WEB-INF" + File.pathSeparator + "classes" + File.pathSeparator + "org" + File.pathSeparator + "cidrz" + File.pathSeparator + "project" + File.pathSeparator + "zeprs" + File.pathSeparator + "valueobject";
        if (dev) {
            sqlPathname = Constants.DEV_RESOURCES_PATH + "generatedSQL.properties";
        } else {
            sqlPathname = Constants.DYNASITE_RESOURCES_PATH + "generatedSQL.properties";
        }
        log.info("generating output SQL file: " + sqlPathname);
        try {
            Connection conn = DatabaseUtils.getAdminConnection();
            List dbForms = new ArrayList();
            List forms = FormDisplayDAO.getAllFormIds(conn);
            for (Iterator iterator = forms.iterator(); iterator.hasNext();) {
                Form form = (Form) iterator.next();
                Form wholeForm = (Form) FormDisplayDAO.getFormGraph(conn, form.getId());
                dbForms.add(wholeForm);
            }
            Form form = null;
            BufferedWriter writer = new BufferedWriter(new FileWriter(sqlPathname));
            for (int i = 0; i < dbForms.size(); i++) {
                form = (Form) dbForms.get(i);
                //String classname = StringManipulation.fixClassname(form.getName());
                String tableName = form.getName().toLowerCase();
                try {
                    Iterator dbPageItems2 = form.getPageItems().iterator();
                    int piCount = 0;
                    while (dbPageItems2.hasNext()) {
                        PageItem pageItem2 = (PageItem) dbPageItems2.next();
                        FormField formField2 = pageItem2.getForm_field();
                        if (formField2.isEnabled()) {
                            if (!formField2.getType().equals("Display") && !pageItem2.getInputType().equals("multiselect_enum")) {
                                piCount++;
                            }
                        }
                    }
                    Iterator dbPageItems = form.getPageItems().iterator();
                    FormField formField = null;

                    // setup the auto-generated sql
                    StringBuffer sbufInserts1 = new StringBuffer(2048);
                    StringBuffer sbufInserts2 = new StringBuffer(2048);
                    StringBuffer sbufSelects = new StringBuffer(2048);
                    StringBuffer sbufSelectsPregs = new StringBuffer(2048);   // all pregnancies for patient
                    StringBuffer sbufSelectsId = new StringBuffer(2048);
                    StringBuffer sbufSelectsReportId = new StringBuffer(2048);   // by id, reports - uses starschemaname
                    StringBuffer sbufSelectsAll = new StringBuffer(2048);   // used to generate xml from reports
                    StringBuffer sbufSelectsReport = new StringBuffer(2048);   // takes date range and site id
                    StringBuffer sbufSelectsReportAllSites = new StringBuffer(2048);  // takes date range only
                    StringBuffer sbufSelectsReportPregs = new StringBuffer(2048);   // all pregs. for patient, with report fields
                    StringBuffer sbufSelectsReportPatient = new StringBuffer(2048);   // for patient, this pregnancy, with report fields - takes patient id and pregnancy id

                    sbufInserts1.append("SQL_CREATE" + form.getId() + "=INSERT INTO " + tableName + "(id, ");
                    sbufSelects.append("SQL_RETRIEVE" + form.getId() + "=SELECT encounter.id AS id, ");
                    sbufSelectsPregs.append("SQL_RETRIEVEPREGS" + form.getId() + "=SELECT encounter.id AS id, ");
                    sbufSelectsId.append("SQL_RETRIEVEID" + form.getId() + "=SELECT encounter.id AS id, ");
                    sbufSelectsReportId.append("SQL_RETRIEVE_REPORT_ID" + form.getId() + "=SELECT encounter.id AS id, ");
                    sbufSelectsAll.append("SQL_RETRIEVEALL" + form.getId() + "=SELECT encounter.id AS id, ");
                    sbufSelectsReport.append("SQL_RETRIEVE_REPORT" + form.getId() + "=SELECT encounter.id AS id, ");
                    sbufSelectsReportAllSites.append("SQL_RETRIEVE_REPORT_SITES" + form.getId() + "=SELECT encounter.id AS id, ");
                    sbufSelectsReportPregs.append("SQL_RETRIEVE_REPORT_PREGS" + form.getId() + "=SELECT encounter.id AS id, ");
                    sbufSelectsReportPatient.append("SQL_RETRIEVE_REPORT_PATIENT" + form.getId() + "=SELECT encounter.id AS id, ");
                    int j = 0;
                    // int dbPageItemsSize = form.getPageItems().size();
                    while (dbPageItems.hasNext()) {
                        PageItem pageItem = (PageItem) dbPageItems.next();

                        formField = pageItem.getForm_field();
                        if (formField.isEnabled()) {
                            if (!formField.getType().equals("Display") && !pageItem.getInputType().equals("multiselect_enum")) {
                                j++;
                                String columnName = "";
                                if (formField.getStarSchemaName().equals("")) {
                                    columnName = "field" + formField.getId();
                                } else {
                                    columnName = formField.getStarSchemaName();
                                }
                                String fieldName = "field" + formField.getId();
                                sbufInserts1.append(columnName);
                                sbufInserts2.append("? ");
                                sbufSelects.append(columnName + " AS " + fieldName);
                                sbufSelectsPregs.append(columnName + " AS " + fieldName);
                                sbufSelectsId.append(columnName + " AS " + fieldName);
                                sbufSelectsReportId.append(tableName + "." + columnName + " AS " + columnName+"R");
                                sbufSelectsAll.append(columnName + " AS " + fieldName);
                                sbufSelectsReport.append(tableName + "." + columnName + " AS " + columnName);
                                sbufSelectsReportAllSites.append(tableName + "." + columnName + " AS " + columnName);
                                sbufSelectsReportPregs.append(columnName + " AS " + columnName +"R");
                                sbufSelectsReportPatient.append(columnName + " AS " + columnName +"R");
                                if (j < piCount) {
                                    sbufInserts1.append(", ");
                                    sbufInserts2.append(", ");
                                    sbufSelects.append(", ");
                                    sbufSelectsPregs.append(", ");
                                    sbufSelectsId.append(", ");
                                    sbufSelectsReportId.append(", ");
                                    sbufSelectsAll.append(", ");
                                    sbufSelectsReport.append(", ");
                                    sbufSelectsReportAllSites.append(", ");
                                    sbufSelectsReportPregs.append(", ");
                                    sbufSelectsReportPatient.append(", ");
                                }
                            }
                        }
                    }
                    sbufSelects.append(", patient_id AS patientId, " +
                            "form_id AS formId, flow_id AS flowId, date_visit AS dateVisit, pregnancy_id AS pregnancyId, " +
                            "last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, " +
                            "created_by AS createdBy, site_id AS siteId, site_name AS siteName,  " +
                            "CONCAT_WS(', ', u2.lastname, u2.firstname) AS lastModifiedByName, uuid " +
                            "FROM " + tableName + ", encounter " +
                            "LEFT JOIN site ON site.id=encounter.site_id " +
                            "LEFT JOIN userdata.address u2 ON u2.nickname =encounter.last_modified_by " +
                            "WHERE encounter.id = " + tableName + ".id " +
                            "AND encounter.patient_id=? AND encounter.pregnancy_id=?");
                    sbufSelectsPregs.append(", patient_id AS patientId, " +
                            "form_id AS formId, flow_id AS flowId, date_visit AS dateVisit, pregnancy_id AS pregnancyId, " +
                            "last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, " +
                            "created_by AS createdBy, site_id AS siteId, site_name AS siteName,  " +
                            "CONCAT_WS(', ', u2.lastname, u2.firstname) AS lastModifiedByName, uuid " +
                            "FROM " + tableName + ", encounter " +
                            "LEFT JOIN site ON site.id=encounter.site_id " +
                            "LEFT JOIN userdata.address u2 ON u2.nickname =encounter.last_modified_by " +
                            "WHERE encounter.id = " + tableName + ".id " +
                            "AND encounter.patient_id=?");
                    sbufSelectsId.append(", patient_id AS patientId, " +
                            "form_id AS formId, flow_id AS flowId, date_visit AS dateVisit, pregnancy_id AS pregnancyId, " +
                            "last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, " +
                            "created_by AS createdBy, site_id AS siteId, site_name AS siteName, " +
                            "CONCAT_WS(', ', u.lastname, u.firstname) AS createdByName, " +
                            "CONCAT_WS(', ', u2.lastname, u2.firstname) AS lastModifiedByName, uuid " +
                            "FROM " + tableName + ", encounter " +
                            "LEFT JOIN site ON site.id=encounter.site_id " +
                            "LEFT JOIN userdata.address u ON u.nickname =encounter.created_by " +
                            "LEFT JOIN userdata.address u2 ON u2.nickname =encounter.last_modified_by " +
                            "WHERE encounter.id = " + tableName + ".id " +
                            "AND encounter.id=?");
                    sbufSelectsReportId.append(", patient_id AS patientId, " +
                            "form_id AS formId, flow_id AS flowId, date_visit AS dateVisit, pregnancy_id AS pregnancyId, " +
                            "last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, " +
                            "created_by AS createdBy, site_id AS siteId, site_name AS siteName, " +
                            "CONCAT_WS(', ', u.lastname, u.firstname) AS createdByName, " +
                            "CONCAT_WS(', ', u2.lastname, u2.firstname) AS lastModifiedByName " +
                            "FROM " + tableName + ", encounter " +
                            "LEFT JOIN site ON site.id=encounter.site_id " +
                            "LEFT JOIN userdata.address u ON u.nickname =encounter.created_by " +
                            "LEFT JOIN userdata.address u2 ON u2.nickname =encounter.last_modified_by " +
                            "WHERE encounter.id = " + tableName + ".id " +
                            "AND encounter.id=?");
                    sbufSelectsAll.append(", patient_id AS patientId, " +
                            "form_id AS formId, flow_id AS flowId, date_visit AS dateVisit, pregnancy_id AS pregnancyId, " +
                            "last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, " +
                            "created_by AS createdBy, site_id AS siteId, uuid " +
                            " FROM " + tableName + ", encounter " +
                            "WHERE encounter.id = " + tableName + ".id ");
                    sbufSelectsReport.append(", patient_id AS patientId, " +
                            "encounter.form_id AS formId, encounter.flow_id AS flowId, encounter.date_visit AS dateVisit, encounter.pregnancy_id AS pregnancyId, " +
                            "encounter.last_modified AS lastModified, encounter.created AS created, encounter.last_modified_by AS lastModifiedBy, " +
                            "encounter.created_by AS createdBy, encounter.site_id AS site, " +
                            "patient.surname as surname, patient.first_name AS firstName, " +
                            "patient.district_patient_id AS zeprsId, patient.parent_id AS parentId, " +
                            "CONCAT_WS(',',userdata.address.lastname,userdata.address.firstname) AS staffName " +
                            " FROM " + tableName + ", encounter, patient, userdata.address " +
                            "WHERE encounter.id = " + tableName + ".id " +
                            "AND patient.id = encounter.patient_id " +
                            "AND date_visit >= ? AND date_visit <= ? " +
                            "AND userdata.address.nickname = encounter.last_modified_by " +
                            "AND encounter.site_id = ? " +
                            "ORDER BY date_visit");
                    sbufSelectsReportAllSites.append(", patient_id AS patientId, " +
                            "encounter.form_id AS formId, encounter.flow_id AS flowId, encounter.date_visit AS dateVisit, encounter.pregnancy_id AS pregnancyId, " +
                            "encounter.last_modified AS lastModified, encounter.created AS created, encounter.last_modified_by AS lastModifiedBy, " +
                            "encounter.created_by AS createdBy, encounter.site_id AS site, " +
                            "patient.surname as surname, patient.first_name AS firstName, " +
                            "patient.district_patient_id AS zeprsId, patient.parent_id AS parentId, " +
                            "CONCAT_WS(',',userdata.address.lastname,userdata.address.firstname) AS staffName " +
                            " FROM " + tableName + ", encounter, patient, userdata.address " +
                            "WHERE encounter.id = " + tableName + ".id " +
                            "AND patient.id = encounter.patient_id " +
                            "AND date_visit >= ? AND date_visit <= ? " +
                            "AND userdata.address.nickname = encounter.last_modified_by " +
                            "ORDER BY date_visit");
                    sbufSelectsReportPregs.append(", patient_id AS patientId, " +
                            "form_id AS formId, flow_id AS flowId, date_visit AS dateVisit, pregnancy_id AS pregnancyId, " +
                            "last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, " +
                            "created_by AS createdBy, site_id AS siteId, site_name AS siteName,  " +
                            "CONCAT_WS(', ', u2.lastname, u2.firstname) AS lastModifiedByName " +
                            "FROM " + tableName + ", encounter " +
                            "LEFT JOIN site ON site.id=encounter.site_id " +
                            "LEFT JOIN userdata.address u2 ON u2.nickname =encounter.last_modified_by " +
                            "WHERE encounter.id = " + tableName + ".id " +
                            "AND encounter.patient_id=?");
                    sbufSelectsReportPatient.append(", patient_id AS patientId, " +
                            "form_id AS formId, flow_id AS flowId, date_visit AS dateVisit, pregnancy_id AS pregnancyId, " +
                            "last_modified AS lastModified, created AS created, last_modified_by AS lastModifiedBy, " +
                            "created_by AS createdBy, site_id AS siteId, site_name AS siteName,  " +
                            "CONCAT_WS(', ', u2.lastname, u2.firstname) AS lastModifiedByName " +
                            "FROM " + tableName + ", encounter " +
                            "LEFT JOIN site ON site.id=encounter.site_id " +
                            "LEFT JOIN userdata.address u2 ON u2.nickname =encounter.last_modified_by " +
                            "WHERE encounter.id = " + tableName + ".id " +
                            "AND encounter.patient_id=? AND encounter.pregnancy_id=?");
                    writer.write(sbufInserts1.toString() + ") VALUES (LAST_INSERT_ID(), " + sbufInserts2.toString() + ")");
                    writer.write("\n");
                    writer.write(sbufSelects.toString());
                    writer.write("\n");
                    writer.write(sbufSelectsPregs.toString());
                    writer.write("\n");
                    writer.write(sbufSelectsId.toString());
                    writer.write("\n");
                    writer.write(sbufSelectsReportId.toString());
                    writer.write("\n");
                    writer.write(sbufSelectsAll.toString());
                    writer.write("\n");
                    writer.write(sbufSelectsReport.toString());
                    writer.write("\n");
                    writer.write(sbufSelectsReportAllSites.toString());
                    writer.write("\n");
                    writer.write(sbufSelectsReportPregs.toString());
                    writer.write("\n");
                    writer.write(sbufSelectsReportPatient.toString());
                    writer.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    // System.exit(1);
                }
            }
            HashMap partoQueries = DynaSiteObjects.getPartoQueries();
            List classNames = PartographMapping.partoClassNameList();
            for (int i = 0; i < classNames.size(); i++) {
                String className = (String) classNames.get(i);
                String query = (String) partoQueries.get(className);
                StringBuffer sbuf = new StringBuffer(2048);
                sbuf.append("SQL_CREATE_PARTO_" + className.toUpperCase() + "=");
                sbuf.append(query);
                writer.write(sbuf.toString());
                writer.write("\n");
            }
            writer.close();
            if (dev) {
                sqlPathname = Constants.DEV_RESOURCES_PATH + "generatedSQL.properties";
                String sqlPathname2 = Constants.DYNASITE_RESOURCES_PATH + "generatedSQL.properties";
                FileUtils.copyQuick(sqlPathname, sqlPathname2);
            }
            log.debug("done with generation of SQL");
        } catch (NullPointerException e) {
            log.fatal("caught null pointer exception when building SQL from database. Likely database connection problem. Message: " + e.getMessage());
            log.fatal("Also check the file path: " + sqlPathname);
            throw new ServletException(e);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Iterates through the forms from the database and makes sql delete script for cleaning up the db.
     * Only useful in dev mode.
     * @throws ServletException
     * @throws PersistenceException
     * @throws IOException
     * @throws SQLException
     */
    public void createSqlDeleteScript() throws ServletException, PersistenceException, IOException, SQLException {

        String sqlScript = null;
        Boolean dev = DynaSiteObjects.getDev();
        if (dev) {
            sqlScript = Constants.LOCAL_DEV_PATH + "conf\\template\\sql\\deleteGen.sql";
            log.info("generating SQL clean file: " + sqlScript);
            try {
                Connection conn = DatabaseUtils.getAdminConnection();
                List dbForms = new ArrayList();
                List forms = FormDisplayDAO.getAllFormIds(conn);
                for (Iterator iterator = forms.iterator(); iterator.hasNext();) {
                    Form form = (Form) iterator.next();
                    Form wholeForm = FormDisplayDAO.getFormGraph(conn, form.getId());
                    dbForms.add(wholeForm);
                }
                StringBuffer sbufSql = new StringBuffer(2048);
                StringBuffer sbufSqlGen = new StringBuffer(2048);
                sbufSql.append("SET FOREIGN_KEY_CHECKS = 0;\n" +
                        "\n");
                sbufSql.append("TRUNCATE TABLE comment;\n" +
                        "TRUNCATE TABLE anteexamvisits;\n" +
                        "TRUNCATE TABLE archivelog;\n" +
                        "TRUNCATE TABLE client_setting;\n" +
                        "TRUNCATE TABLE hiv_report;\n" +
                        "TRUNCATE TABLE infantproblem;\n" +
                        "TRUNCATE TABLE safemotherhood;\n" +
                        "TRUNCATE TABLE subscription;\n" +
                        "TRUNCATE TABLE ttimmunisation;\n" +
                        "TRUNCATE TABLE updatelog;\n" +
                        "TRUNCATE TABLE district_id;\n" +
                        "TRUNCATE TABLE encounter;\n" +
                      //  "TRUNCATE TABLE encounter_record;\n" +
                      //  "TRUNCATE TABLE encounter_value;\n" +
                        "TRUNCATE TABLE encounter_value_archive;\n" +
                        "TRUNCATE TABLE encounter_archive;\n" +
                     //   "TRUNCATE TABLE `info_outcome`;\n" +
                        "TRUNCATE TABLE `outcome_archive`;\n" +
                        "TRUNCATE TABLE `outcome`;\n" +
                        "TRUNCATE TABLE `parto_blood_pressure`;\n" +
                        "TRUNCATE TABLE `parto_cervix`;\n" +
                        "TRUNCATE TABLE `parto_contractions`;\n" +
                        "TRUNCATE TABLE `parto_descent`;\n" +
                        "TRUNCATE TABLE `parto_drugs_dispensed`;\n" +
                        "TRUNCATE TABLE `parto_fetal_hr`;\n" +
                        "TRUNCATE TABLE `parto_liquor`;\n" +
                        "TRUNCATE TABLE `parto_moulding`;\n" +
                        "TRUNCATE TABLE `parto_oxytocin`;\n" +
                        "TRUNCATE TABLE `parto_pulse`;\n" +
                        "TRUNCATE TABLE `parto_respiration`;\n" +
                        "TRUNCATE TABLE `parto_temperature`;\n" +
                        "TRUNCATE TABLE `parto_urinalysis_acetone`;\n" +
                        "TRUNCATE TABLE `parto_urinalysis_glucose`;\n" +
                        "TRUNCATE TABLE `parto_urinalysis_protein`;\n" +
                        "TRUNCATE TABLE `parto_urine_amount`;\n" +
                        "TRUNCATE TABLE `parto_vaginal_exam`;\n" +
                        "TRUNCATE TABLE `partographstatus`;\n" +
                        "TRUNCATE TABLE `patient`;\n" +
                  //      "TRUNCATE TABLE `patient_labs`;\n" +
                        "TRUNCATE TABLE `patient_status`;\n" +
                        "TRUNCATE TABLE `pregnancy`;\n" +
                        "TRUNCATE TABLE `problem`;\n" +
                        "TRUNCATE TABLE `problem_archive`;\n" +
                        "TRUNCATE TABLE `referral`;\n" +
                        "TRUNCATE TABLE `referral_reasons`;\n" +
            			"TRUNCATE TABLE `user_group_membership`;\n");
                Form form = null;
                BufferedWriter writer = new BufferedWriter(new FileWriter(sqlScript));
                writer.write(sbufSql.toString());
                for (int i = 0; i < dbForms.size(); i++) {
                    form = (Form) dbForms.get(i);
                    String tableName = form.getName().toLowerCase();
                    sbufSqlGen.append("TRUNCATE TABLE " + tableName + ";\n");
                }
                writer.write(sbufSqlGen.toString());
                writer.close();
                log.debug("done with generation of SQL");
            } catch (NullPointerException e) {
                log.fatal("caught null pointer exception when building SQL from database. Likely database connection problem. Message: " + e.getMessage());
                log.fatal("Also check the file path: " + sqlScript);
                throw new ServletException(e);
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
