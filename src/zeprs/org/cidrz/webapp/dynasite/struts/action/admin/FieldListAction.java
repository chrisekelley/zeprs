/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.dao.FormFieldDAO;
import org.cidrz.webapp.dynasite.dao.PageItemDAO;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DataStructureUtils;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;
import java.sql.Connection;

/**
 * Get a list of all fields in ZEPRS
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Nov 7, 2005
 *         Time: 4:12:24 PM
 */
public class FieldListAction extends BaseAction {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(FieldListAction.class);

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	Boolean listRecords = null;

    	if (request.getParameter("listRecords") != null) {
    		listRecords = true;
        }
    	// Get a list of all fields
        Connection conn = DatabaseUtils.getAdminConnection();
        List fieldList = FormFieldDAO.getAllEnabled(conn);
        for (int i = 0; i < fieldList.size(); i++) {
            FormField formField = (FormField) fieldList.get(i);
            Long formFieldId = formField.getId();
            List pageItems = PageItemDAO.getAll(conn, formFieldId);
            boolean hasEnabledforms = false;
            if (pageItems.size() > 0) {
                for (int j = 0; j < pageItems.size(); j++) {
                    PageItem pageItem = (PageItem) pageItems.get(j);
                    Long formId = pageItem.getFormId();
                    Form thisForm;
                    thisForm = (Form) DynaSiteObjects.getForms().get(formId);
                    if (thisForm != null) {
                        hasEnabledforms = true;
                    }
                    pageItem.setForm(thisForm);
                    if (listRecords != null) {
                    	if (thisForm != null) {
                    		String tableName = thisForm.getName().toLowerCase();
                    		Long formRecordCount = PageItemDAO.countRecords(conn, tableName, "id");
                        	if (formRecordCount > 0) {
                        		thisForm.setRecordCount(formRecordCount);
                        	}
                    		if (!formField.getType().equals("Display")) {
                            	String columnName = formField.getStarSchemaName();
                            	Long recordCount = PageItemDAO.countRecords(conn, tableName, columnName);
                            	if (recordCount > 0) {
                            		pageItem.setRecordCount(recordCount);
                            	}
                    		}
                    	}
                    }
                }
                if (hasEnabledforms) {
                    Set pageItemSet = DataStructureUtils.listToSet(pageItems);
                    formField.setPageItems(pageItemSet);
                }
            }
        }
        request.setAttribute("fieldList", fieldList);

        return mapping.findForward(SUCCESS_FORWARD);
    }

}