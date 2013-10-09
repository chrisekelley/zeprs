/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.cidrz.webapp.dynasite.dao.FormFieldDAO;
import org.cidrz.webapp.dynasite.dao.PageItemDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DataStructureUtils;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QueryBuilderAction extends BaseAction {
    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long fieldId = null;
        Long siteId = null;
        String bdate = null;
        String edate = null;
        Boolean addfield = null;
        String selectedField = null;
        Connection conn = DatabaseUtils.getAdminConnection();

        // Get a list of all fields
        DynaActionForm dynaForm = (DynaActionForm) form;
        if (dynaForm.get("fieldId") != null) {
            fieldId = (Long) dynaForm.get("fieldId");
        }
        if (dynaForm.get("fieldId") != null) {
            siteId = (Long) dynaForm.get("siteId");
        }
        if (dynaForm.get("fieldId") != null) {
            bdate = (String) dynaForm.get("bdate");
        }
        if (dynaForm.get("fieldId") != null) {
            edate = (String) dynaForm.get("edate");
        }
        if (dynaForm.get("fieldId") != null) {
            addfield = (Boolean) dynaForm.get("addfield");
        }

        if (fieldId != null) {
            List valueList = new ArrayList();
            List sqlStatementList = new ArrayList();
            String sqlStatement = null;
            // get the tables that this field appears in
            List pageItems = PageItemDAO.getAll(conn, fieldId);
            FormField formField = FormFieldDAO.getOne(conn, fieldId);
            selectedField = formField.getLabel();
            String fieldName = formField.getStarSchemaName();
            StringBuffer selectBuf = new StringBuffer();
            StringBuffer fromBuf = new StringBuffer();
            StringBuffer whereBuf = new StringBuffer();
            for (int i = 0; i < pageItems.size(); i++) {
                sqlStatement = "";
                PageItem pageItem = (PageItem) pageItems.get(i);
                Long formFieldId = formField.getId();
                Long formId = pageItem.getFormId();
                if (formId != null) {
                    Form thisForm = (Form) DynaSiteObjects.getForms().get(formId);
                    if (thisForm != null) {
                        String tableName = "zeprs." + thisForm.getName().toLowerCase();
                        // selectBuf.append(tableName + "." + fieldName + " AS " + fieldName + "." + i);
                        selectBuf.append(tableName + "." + fieldName);
                        if (i < pageItems.size() - 1) {
                            selectBuf.append(",");
                        }
                        fromBuf.append(tableName);
                        whereBuf.append(" AND encounter.id=" + tableName + ".id ");
                        if (i < pageItems.size() - 1) {
                            fromBuf.append(",");
                        }
                        if (formField.getType().equals("Enum") & (!pageItem.getInputType().equals("checkbox"))) {
                            sqlStatement = "SELECT encounter.id AS id, field_enumeration.enumeration AS value, " +
                                    "date_visit AS visitDate, " +
                                    "encounter.patient_id AS patientId, " +
                                    "district_patient_id AS zeprsId " +
                                    "FROM zeprs.encounter, zeprs.patient, field_enumeration,  " + tableName +
                                    " WHERE patient.id = encounter.patient_id " +
                                    " AND encounter.date_visit >='" + bdate +
                                    "' AND encounter.date_visit <='" + edate +
                                    "' AND encounter.id=" + tableName + ".id " +
                                    "AND field_enumeration.id=" + tableName + "." + fieldName;
                        } else {
                            sqlStatement = "SELECT encounter.id AS id, " + tableName + "." + fieldName + " AS value, " +
                                    "date_visit AS visitDate, " +
                                    "encounter.patient_id AS patientId, " +
                                    "district_patient_id AS zeprsId " +
                                    "FROM zeprs.encounter, zeprs.patient,  " + tableName +
                                    " WHERE patient.id = encounter.patient_id " +
                                    " AND encounter.date_visit >='" + bdate +
                                    "' AND encounter.date_visit <='" + edate +
                                    "' AND encounter.id=" + tableName + ".id";
                        }
                        if (siteId.intValue() > 0) {
                            sqlStatement = sqlStatement + " AND encounter.site_id=" + siteId;
                        }
                        sqlStatement = sqlStatement + " ORDER BY encounter.date_visit";
                        sqlStatementList.add(sqlStatement);
                    }
                }
            }
            for (int i = 0; i < sqlStatementList.size(); i++) {
                ArrayList values = new ArrayList();
                values = new ArrayList();
                String sql = (String) sqlStatementList.get(i);
                List list = DatabaseUtils.getList(conn, EncounterValue.class, sql, values);
                valueList.addAll(list);
            }

            /*sqlStatement = "SELECT " + selectBuf.toString() + " FROM encounter, " + fromBuf.toString() +
                    " WHERE encounter.date_visit>'" + bdate +
                    "' AND encounter.date_visit<'" + edate +
                    "' AND encounter.site_id=" + siteId + whereBuf.toString() + ";";*/

            // request.setAttribute("sqlStatementList", sqlStatementList);
            request.setAttribute("valueList", valueList);
            request.setAttribute("selectedField", selectedField);

        }

        //List fieldList = FormFieldDAO.getAllEnabledFields(conn);
        List fieldList = DynaSiteObjects.getActivefields();

        // Get a list of all fields

        // List fieldList = FormFieldDAO.getAllEnabled();
        ArrayList fieldList2 = new ArrayList();
        for (int i = 0; i < fieldList.size(); i++) {
            FormField formField = (FormField) fieldList.get(i);
            Long formFieldId = formField.getId();
            List pageItems = PageItemDAO.getAll(conn, formFieldId);
            boolean isFormed = true;
            if (pageItems.size() > 0) {
                for (int j = 0; j < pageItems.size(); j++) {
                    PageItem pageItem = (PageItem) pageItems.get(j);
                    Long formId = pageItem.getFormId();
                    if (formId != null) {
                        isFormed = true;
                        Form thisForm = (Form) DynaSiteObjects.getForms().get(formId);
                        if (thisForm != null) {
                            isFormed = true;
                            pageItem.setForm(thisForm);
                        } else {
                            isFormed = false;
                        }

                    } else {
                        isFormed = false;
                    }
                }
                Set pageItemSet = DataStructureUtils.listToSet(pageItems);
                formField.setPageItems(pageItemSet);
                if (isFormed) {
                    fieldList2.add(formField);
                }
            } else {
                formField.setPageItems(null);
                fieldList.remove(formField);
            }
        }

        request.setAttribute("selFieldId", fieldId);
        request.setAttribute("fieldList", fieldList2);
        List sites = null;
        //sites = SiteDAO.getAll("name");
        sites = DynaSiteObjects.getClinics();
        request.setAttribute("sites", sites);
        request.setAttribute("view", "yes");
        conn.close();
        return mapping.findForward("success");
    }
}
