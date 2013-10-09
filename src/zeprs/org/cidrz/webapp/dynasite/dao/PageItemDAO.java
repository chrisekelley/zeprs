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
import org.cidrz.webapp.dynasite.utils.sort.DisplayOrderComparator;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 4, 2005
 *         Time: 9:22:16 AM
 */
public class PageItemDAO {

    /**
     * Returns one record
     *
     * @param conn
     * @param id
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static PageItem getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        PageItem item = null;
        String sql;
        ArrayList values;
        sql = "select id, form_field_id AS formFieldId, display_order AS displayOrder, input_type AS inputType, " +
                "close_row AS closeRow, column_number AS columnNumber, size, maxlength, rows, cols, visible, " +
                "visible_enum_id_trigger1 AS visibleEnumIdTrigger1, visible_dependencies1 AS visibleDependencies1, " +
                "visible_enum_id_trigger2 AS visibleEnumIdTrigger2, visible_dependencies2 AS visibleDependencies2, " +
                "selected_enum AS selectedEnum, form_id AS formId, colspan, highlight AS highlightCell, roles " +
                "from admin.page_item " +
                "WHERE id=? ";
        values = new ArrayList();
        values.add(id);
        item = (PageItem) DatabaseUtils.getBean(conn, PageItem.class, sql, values);
        return item;
    }

    /**
     * Fetches formId and stuffs it into a pageItem.
     * @param conn
     * @param id of pageitem
     * @return PageItem with formId value
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static PageItem getFormId(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        PageItem item = null;
        String sql;
        ArrayList values;
        sql = "select form_id AS formId " +
                "from admin.page_item " +
                "WHERE id=? ";
        values = new ArrayList();
        values.add(id);
        item = (PageItem) DatabaseUtils.getBean(conn, PageItem.class, sql, values);
        return item;
    }

    /**
         * Returns one pageItem by formFieldId
         * unused
         * @param formFieldId
         * @return
         * @throws java.sql.SQLException
         * @throws javax.servlet.ServletException
         */
        public static PageItem getOneByFormField(Connection conn, Long formFieldId) throws SQLException, ServletException, ObjectNotFoundException {
            PageItem item = null;
            String sql;
            ArrayList values;
            sql = "select id, form_field_id AS formFieldId, display_order AS displayOrder, input_type AS inputType, " +
                    "close_row AS closeRow, column_number AS columnNumber, size, maxlength, rows, cols, visible, " +
                    "visible_enum_id_trigger1 AS visibleEnumIdTrigger1, visible_dependencies1 AS visibleDependencies1, " +
                    "visible_enum_id_trigger2 AS visibleEnumIdTrigger2, visible_dependencies2 AS visibleDependencies2, " +
                    "selected_enum AS selectedEnum, form_id AS formId, colspan, highlight AS highlightCell, roles " +
                    "from admin.page_item " +
                    "WHERE form_field_id=? ";
            values = new ArrayList();
            values.add(formFieldId);
            item = (PageItem) DatabaseUtils.getBean(conn, PageItem.class, sql, values);
            return item;
        }


    /**
     * Returns one record
     *
     * @param conn
     * @param id
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static PageItem getPageItemGraph(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        PageItem item = null;
        // Connection conn = DatabaseUtils.getAdminConnection();
        String sql;
        ArrayList values;
        sql = "select id, form_field_id AS formFieldId, display_order AS displayOrder, input_type AS inputType, " +
                "close_row AS closeRow, column_number AS columnNumber, size, maxlength, rows, cols, visible, " +
                "visible_enum_id_trigger1 AS visibleEnumIdTrigger1, visible_dependencies1 AS visibleDependencies1, " +
                "visible_enum_id_trigger2 AS visibleEnumIdTrigger2, visible_dependencies2 AS visibleDependencies2, " +
                "selected_enum AS selectedEnum, form_id AS formId, colspan, highlight AS highlightCell, roles " +
                "from admin.page_item " +
                "WHERE id=? ";
        values = new ArrayList();
        values.add(id);
        item = (PageItem) DatabaseUtils.getBean(conn, PageItem.class, sql, values);
        Long formFieldId = item.getFormFieldId();

        FormField formfield = FormFieldDAO.getOne(conn, formFieldId);
        // get the enumerations
        List fieldEnum = FieldEnumerationDAO.getAll(conn, formFieldId);
        if (fieldEnum.size() != 0) {
            // Set enumSet = DataStructureUtils.listToSet(fieldEnum);
            Set enumSet = new TreeSet(new DisplayOrderComparator());
            //Set enumSet = DataStructureUtils.listToSet(fieldEnum);
            enumSet.addAll(fieldEnum);
            formfield.setEnumerations(enumSet);
        }
        // get the ruleDefs
        List ruleDefEnum = RuleDefinitionDAO.getAll(conn, formFieldId);
        if (ruleDefEnum != null) {
            formfield.setRuleDefinitions(ruleDefEnum);
        }
        item.setForm_field(formfield);

        return item;
    }

    /**
     * Gets all pageItems
     * unused
     * @return List of objects
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAll(Connection conn) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, form_field_id AS formFieldId, display_order AS displayOrder, input_type AS inputType, " +
                "close_row AS closeRow, column_number AS columnNumber, size, maxlength, rows, cols, visible, " +
                "visible_enum_id_trigger1 AS visibleEnumIdTrigger1, visible_dependencies1 AS visibleDependencies1, " +
                "visible_enum_id_trigger2 AS visibleEnumIdTrigger2, visible_dependencies2 AS visibleDependencies2, " +
                "selected_enum AS selectedEnum, form_id AS formId, colspan, highlight AS highlightCell, roles " +
                "from admin.page_item ";
        list = DatabaseUtils.getList(conn, PageItem.class, sql, values);
        return list;
    }

    /**
     * @return List of PageItem for this formFieldId
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAll(Connection conn, Long formFieldId) throws SQLException, ServletException {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        String sql = "select id, form_field_id AS formFieldId, display_order AS displayOrder, input_type AS inputType, " +
                "close_row AS closeRow, column_number AS columnNumber, size, maxlength, rows, cols, visible, " +
                "visible_enum_id_trigger1 AS visibleEnumIdTrigger1, visible_dependencies1 AS visibleDependencies1, " +
                "visible_enum_id_trigger2 AS visibleEnumIdTrigger2, visible_dependencies2 AS visibleDependencies2, " +
                "selected_enum AS selectedEnum, form_id AS formId, colspan, highlight AS highlightCell, roles " +
                "from admin.page_item " +
                "WHERE form_field_id=? ";
        values = new ArrayList();
        values.add(formFieldId);
        list = DatabaseUtils.getList(conn, PageItem.class, sql, values);
        return list;
    }

    /**
     * List of pageitems for a form
     * unused
     * @param id  - form id
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAll(Connection conn, String id) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, form_field_id AS formFieldId, display_order AS displayOrder, input_type AS inputType, " +
                "close_row AS closeRow, column_number AS columnNumber, size, maxlength, rows, cols, visible, " +
                "visible_enum_id_trigger1 AS visibleEnumIdTrigger1, visible_dependencies1 AS visibleDependencies1, " +
                "visible_enum_id_trigger2 AS visibleEnumIdTrigger2, visible_dependencies2 AS visibleDependencies2, " +
                "selected_enum AS selectedEnum, form_id AS formId, colspan, highlight AS highlightCell, roles " +
                "from admin.page_item " +
                "WHERE id=? ";
        values.add(id);
        list = DatabaseUtils.getList(conn, PageItem.class, sql, values);
        return list;
    }

    /**
     * List of objects
     *
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAllforForm(Connection conn, Long formId) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, form_field_id AS formFieldId, display_order AS displayOrder, input_type AS inputType, " +
                "close_row AS closeRow, column_number AS columnNumber, size, maxlength, rows, cols, visible, " +
                "visible_enum_id_trigger1 AS visibleEnumIdTrigger1, visible_dependencies1 AS visibleDependencies1, " +
                "visible_enum_id_trigger2 AS visibleEnumIdTrigger2, visible_dependencies2 AS visibleDependencies2, " +
                "selected_enum AS selectedEnum, form_id AS formId, colspan, highlight AS highlightCell, roles " +
                "from admin.page_item " +
                "WHERE form_id=? " +
                "ORDER BY display_order";
        values.add(formId);
        list = DatabaseUtils.getList(conn, PageItem.class, sql, values);
        return list;
    }

    /**
     * Deletes pageItem record. Will not delete the associated formField.
     * @param conn
     * @param id
     * @return
     * @throws SQLException
     */
    public static Object delete(Connection conn, Long id) throws SQLException {
        Object result = 0;
        ArrayList values;
        values = new ArrayList();
        String sql = "DELETE FROM admin.page_item " +
                "WHERE id=?";
        values.add(id);
        result = DatabaseUtils.create(conn, sql, values.toArray());
        return result;
    }

    /**
     * updates pageItem in admin
     *
     * @param conn
     * @param item
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Object update(Connection conn, PageItem item) throws SQLException, ServletException {
        ArrayList values = new ArrayList();
        String sql = "UPDATE admin.page_item set input_type=?, visible=?, close_row=?, size=?, maxlength=?, rows=?, " +
                "cols=?, visible_enum_id_trigger1=?, visible_enum_id_trigger2=?, visible_dependencies1=?, visible_dependencies2=?,  " +
                "colspan=?, highlight=? WHERE id=?";
        values.add(item.getInputType());
        values.add(item.isVisible());
        values.add(item.isCloseRow());
        values.add(item.getSize());
        values.add(item.getMaxlength());
        values.add(item.getRows());
        values.add(item.getCols());
        values.add(item.getVisibleEnumIdTrigger1());
        values.add(item.getVisibleEnumIdTrigger2());
        values.add(item.getVisibleDependencies1());
        values.add(item.getVisibleDependencies2());
        values.add(item.getColspan());
        values.add(item.isHighlightCell());
        values.add(item.getId());
        Long encId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return encId;
    }

    /**
     * Updates displayOrder when re-ordering pageitems in admin
     * @param conn
     * @param displayOrder
     * @param id
     * @return
     * @throws SQLException
     */
    public static Object updateDisplayOrder(Connection conn, Long displayOrder, Long id) throws SQLException {
        ArrayList values = new ArrayList();
        String sql = "UPDATE admin.page_item set display_order=? WHERE id=?";
        values.add(displayOrder);
        values.add(id);
        Long encId = null;
        encId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return encId;
    }

    /**
     * Saves shared field by copying a pageItem that uses this field to the current form.
     * @param conn
     * @param databaseName
     * @param formId
     * @param formFieldId
     * @param userName
     * @param siteId
     * @param displayOrder
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Object saveSharedfield(Connection conn, String databaseName, Long formId, Long formFieldId, String userName, Long siteId, Integer displayOrder) throws SQLException, ServletException {

        Object result = null;
        // Fetch a list of pageitems that have this formFieldId
        List pageItems = PageItemDAO.getAll(conn, formFieldId);
        // get the first one
        PageItem pageItem = (PageItem) pageItems.get(0);
        pageItem.setFormId(formId);  // changes it to the current form.
        pageItem.setDisplayOrder(displayOrder);
        result = save(conn, databaseName, pageItem, userName, siteId);
        return result;
    }

    /**
     * saves pageItem
     *
     * @param conn
     * @param pageItem
     * @return
     * @throws SQLException
     */
    public static Object save(Connection conn, String databaseName, PageItem pageItem, String userName, Long siteId) throws SQLException {
        ArrayList values = new ArrayList();
        String  sql = "insert into " + databaseName + ".page_item (form_field_id, display_order, input_type, close_row, column_number, " +
                "last_modified, created, last_modified_by, created_by, site_id, " +
                "size, maxlength, rows, cols, visible, visible_enum_id_trigger1, visible_dependencies1, " +
                "visible_enum_id_trigger2, visible_dependencies2, selected_enum, form_id, colspan, highlight) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        values.add(pageItem.getFormFieldId());
        values.add(pageItem.getDisplayOrder());
        values.add(pageItem.getInputType());
        values.add(pageItem.isCloseRow());
        values.add(pageItem.getColumnNumber());
        FormDAO.AddAuditInfo(values, userName, siteId);
        values.add(pageItem.getSize());
        values.add(pageItem.getMaxlength());
        values.add(pageItem.getRows());
        values.add(pageItem.getCols());
        values.add(pageItem.isVisible());
        values.add(pageItem.getVisibleEnumIdTrigger1());
        values.add(pageItem.getVisibleDependencies1());
        values.add(pageItem.getVisibleEnumIdTrigger2());
        values.add(pageItem.getVisibleDependencies2());
        values.add(pageItem.getSelectedEnum());
        values.add(pageItem.getFormId());
        values.add(pageItem.getColspan());
        values.add(pageItem.isHighlightCell());
        Long pageItemId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return pageItemId;
    }

    /**
     * Get a count of records in a table for a field
     * @param conn
     * @param tableName
     * @param columnName
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Long countRecords(Connection conn, String tableName, String columnName) throws SQLException, ServletException {
    	ArrayList values = new ArrayList();
    	String sql = "SELECT COUNT(" + columnName + ") from zeprs." + tableName + " where " + columnName + " IS NOT NULL";
        Long recordCount = null;
        recordCount = (Long) DatabaseUtils.getScalar(conn, sql);
        return recordCount;
    }

}
