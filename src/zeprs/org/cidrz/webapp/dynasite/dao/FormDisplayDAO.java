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
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.DataStructureUtils;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.sort.DisplayOrderComparator;
import org.cidrz.webapp.dynasite.valueobject.*;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.*;

/**
 * Simple queries for patient record data that must be presented on a form
 * Created by IntelliJ IDEA.
 * User: ckelley
 * Date: May 27, 2005
 * Time: 1:11:34 PM
 */
public class FormDisplayDAO {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(FormDisplayDAO.class);

    /**
     * Returns one record
     *
     * @param conn
     * @param id
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static Form getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        Form item = null;
        String sql;
        ArrayList values;
        sql = "select id, name, label, require_reauth AS requireReauth, require_patient AS requirePatient, " +
                "is_enabled AS enabled, flow_id AS flowId, flow_order AS flowOrder, form_type_id AS formTypeId, " +
                "max_submissions AS maxSubmissions " +
                "from admin.form " +
                "WHERE id=?";
        values = new ArrayList();
        values.add(id);
        item = (Form) DatabaseUtils.getBean(conn, Form.class, sql, values);
        return item;
    }

    /**
     * Fetches parts of object graph from db, but enums come from dynasite.
     * @param conn
     * @param formId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Form getFormGraph(Connection conn, Long formId) throws SQLException, ServletException, ObjectNotFoundException {

        Form form = FormDisplayDAO.getOne(conn, formId);
        // get the flow
        Flow flow = FlowDAO.getOne(conn, form.getFlowId());
        form.setFlow(flow);
        // get the formType
        FormType formType = FormTypeDAO.getOne(conn, form.getFormTypeId());
        form.setFormType(formType);
        // get the pageItems
        List pi = PageItemDAO.getAllforForm(conn, formId);
        for (int i = 0; i < pi.size(); i++) {
            PageItem pageItem = (PageItem) pi.get(i);
            Long formFieldId = pageItem.getFormFieldId();
            FormField formfield = FormFieldDAO.getOne(conn, formFieldId);
            // get the enumerations from Dyna
            //List fieldEnum = FieldEnumerationDAO.getAll(formFieldId);
            if (formfield.getType().equals("Enum")) {
                TreeMap fieldEnum = null;
                try {
                    fieldEnum = (TreeMap) DynaSiteObjects.getFieldEnumerationsByField().get(formfield.getId());
                    if (fieldEnum.size() != 0) {
                        Set fieldEnumSet = fieldEnum.entrySet();
                        // Set enumSet = DataStructureUtils.listToSet(fieldEnum);
                        Set enumSet = new TreeSet(new DisplayOrderComparator());
                        for (Iterator iterator = fieldEnumSet.iterator(); iterator.hasNext();) {
                            Map.Entry entry = (Map.Entry) iterator.next();
                            FieldEnumeration fieldEnumeration = (FieldEnumeration) entry.getValue();
                            enumSet.add(fieldEnumeration);
                        }
                        // enumSet.addAll(fieldEnum);
                        formfield.setEnumerations(enumSet);
                    }
                } catch (NullPointerException e) {
                    // it's ok - does not have an enum
                } catch (ClassCastException e) {
                    log.error(e);
                }
            }

            // get the ruleDefs
            //List ruleDefEnum = RuleDefinitionDAO.getAll(formFieldId);
            List ruleDefEnum = (List) DynaSiteObjects.getRules().get(formFieldId);
            if (ruleDefEnum != null) {
                formfield.setRuleDefinitions(ruleDefEnum);
            }
            pageItem.setForm_field(formfield);

            // load the dependency maps
            if (pageItem.getVisibleDependencies1() != null) {
                if (pageItem.getVisibleDependencies1().length() != 0) {
                    HashMap depMap1 = new HashMap();
                    for (StringTokenizer stringTokenizer = new StringTokenizer(pageItem.getVisibleDependencies1()); stringTokenizer.hasMoreTokens();) {
                        String dependency = stringTokenizer.nextToken();
                        depMap1.put(dependency, pageItem.getId());
                    }
                    pageItem.setDempMap1(depMap1);
                }
            }

            if (pageItem.getVisibleDependencies2() != null) {
                int dep2length = 0;
                dep2length = pageItem.getVisibleDependencies2().length();
                if (dep2length != 0) {
                    HashMap depMap2 = new HashMap();
                    for (StringTokenizer stringTokenizer = new StringTokenizer(pageItem.getVisibleDependencies2()); stringTokenizer.hasMoreTokens();) {
                        String dependency = stringTokenizer.nextToken();
                        depMap2.put(dependency, pageItem.getId());
                    }
                    pageItem.setDempMap2(depMap2);
                }
            }
        }
        Set piSet = DataStructureUtils.listToSet(pi);
        form.setPageItems(piSet);
        return form;
    }

    /**
     * Assembles form elements from db for previewing a form
     * @param conn
     * @param formId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Form getFormDisplayGraph(Connection conn, Long formId) throws SQLException, ServletException, ObjectNotFoundException {

        Form form = FormDisplayDAO.getOne(conn, formId);
        // get the flow
        Flow flow = FlowDAO.getOne(conn, form.getFlowId());
        form.setFlow(flow);
        // get the formType
        FormType formType = FormTypeDAO.getOne(conn, form.getFormTypeId());
        form.setFormType(formType);
        // get the pageItems
        List pi = PageItemDAO.getAllforForm(conn, formId);
        for (int i = 0; i < pi.size(); i++) {
            PageItem pageItem = (PageItem) pi.get(i);
            Long formFieldId = pageItem.getFormFieldId();
            FormField formfield = FormFieldDAO.getOne(conn, formFieldId);
            if (formfield.getType().equals("Enum")) {
                List fieldEnum = null;
                try {
                    fieldEnum = FieldEnumerationDAO.getAll(conn, formfield.getId());
                    if (fieldEnum.size() != 0) {
                        Set enumSet = new TreeSet(new DisplayOrderComparator());
                        for (int j = 0; j < fieldEnum.size(); j++) {
                            FieldEnumeration fieldEnumeration = (FieldEnumeration) fieldEnum.get(j);
                            enumSet.add(fieldEnumeration);
                        }
                        formfield.setEnumerations(enumSet);
                    }
                } catch (NullPointerException e) {
                    // it's ok - does not have an enum
                } catch (ClassCastException e) {
                    log.error(e);
                }
            }

            pageItem.setForm_field(formfield);

            // load the dependency maps
            if (pageItem.getVisibleDependencies1() != null) {
                if (pageItem.getVisibleDependencies1().length() != 0) {
                    HashMap depMap1 = new HashMap();
                    for (StringTokenizer stringTokenizer = new StringTokenizer(pageItem.getVisibleDependencies1()); stringTokenizer.hasMoreTokens();) {
                        String dependency = stringTokenizer.nextToken();
                        depMap1.put(dependency, pageItem.getId());
                    }
                    pageItem.setDempMap1(depMap1);
                }
            }

            if (pageItem.getVisibleDependencies2() != null) {
                int dep2length = 0;
                dep2length = pageItem.getVisibleDependencies2().length();
                if (dep2length != 0) {
                    HashMap depMap2 = new HashMap();
                    for (StringTokenizer stringTokenizer = new StringTokenizer(pageItem.getVisibleDependencies2()); stringTokenizer.hasMoreTokens();) {
                        String dependency = stringTokenizer.nextToken();
                        depMap2.put(dependency, pageItem.getId());
                    }
                    pageItem.setDempMap2(depMap2);
                }
            }
        }
        Set piSet = DataStructureUtils.listToSet(pi);
        form.setPageItems(piSet);
        return form;
    }
    
    /**
     * Get full form graph from database
     * @param conn
     * @param formId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static Form getFormGraphDb(Connection conn, Long formId) throws SQLException, ServletException, ObjectNotFoundException {

    	Form form = FormDisplayDAO.getOne(conn, formId);
    	// get the flow
    	Flow flow = FlowDAO.getOne(conn, form.getFlowId());
    	form.setFlow(flow);
    	// get the formType
    	FormType formType = FormTypeDAO.getOne(conn, form.getFormTypeId());
    	form.setFormType(formType);
    	// get the pageItems
    	List pi = PageItemDAO.getAllforForm(conn, formId);
    	for (int i = 0; i < pi.size(); i++) {
    		PageItem pageItem = (PageItem) pi.get(i);
    		Long formFieldId = pageItem.getFormFieldId();
    		FormField formfield = FormFieldDAO.getOne(conn, formFieldId);
    		if (formfield.getType().equals("Enum")) {
    			List fieldEnum = null;
    			try {
//    				fieldEnum = FieldEnumerationDAO.getAll(conn, formfield.getId(), null);
    				// changed method for ZEPRS
    				fieldEnum = FieldEnumerationDAO.getAll(conn, formfield.getId());
    				if (fieldEnum.size() != 0) {
    					Set enumSet = new TreeSet(new DisplayOrderComparator());
    					for (int j = 0; j < fieldEnum.size(); j++) {
    						FieldEnumeration fieldEnumeration = (FieldEnumeration) fieldEnum.get(j);
    						enumSet.add(fieldEnumeration);
    					}
    					formfield.setEnumerations(enumSet);
    				}
    			} catch (NullPointerException e) {
    				// it's ok - does not have an enum
    			} catch (ClassCastException e) {
    				log.error(e);
    			}
    		}

    		// get the ruleDefs
            List ruleDefEnum = RuleDefinitionDAO.getAll(conn, formFieldId);
            if (ruleDefEnum != null) {
                formfield.setRuleDefinitions(ruleDefEnum);
            }

    		pageItem.setForm_field(formfield);

    		// load the dependency maps
    		if (pageItem.getVisibleDependencies1() != null) {
    			if (pageItem.getVisibleDependencies1().length() != 0) {
    				HashMap depMap1 = new HashMap();
    				for (StringTokenizer stringTokenizer = new StringTokenizer(pageItem.getVisibleDependencies1()); stringTokenizer.hasMoreTokens();) {
    					String dependency = stringTokenizer.nextToken();
    					depMap1.put(dependency, pageItem.getId());
    				}
    				pageItem.setDempMap1(depMap1);
    			}
    		}

    		if (pageItem.getVisibleDependencies2() != null) {
    			int dep2length = 0;
    			dep2length = pageItem.getVisibleDependencies2().length();
    			if (dep2length != 0) {
    				HashMap depMap2 = new HashMap();
    				for (StringTokenizer stringTokenizer = new StringTokenizer(pageItem.getVisibleDependencies2()); stringTokenizer.hasMoreTokens();) {
    					String dependency = stringTokenizer.nextToken();
    					depMap2.put(dependency, pageItem.getId());
    				}
    				pageItem.setDempMap2(depMap2);
    			}
    		}
    	}
    	Set piSet = DataStructureUtils.listToSet(pi);
    	form.setPageItems(piSet);
    	return form;
    }

    /**
     * Fetches a simple list of form id's for use in creating the forms stored in the application space upon startup
     *
     * @return
     * @throws SQLException
     * @throws ServletException
     * @param conn
     */
    public static List getAllFormIds(Connection conn) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        String sql = "select f.id from form f where f.is_enabled=true ";
        list = DatabaseUtils.getList(conn, Form.class, sql, values);
        return list;
    }
    
    /**
     * Fetches a simple list of enabled (active) forms for use in creating the forms stored in the application space upon startup
     * Also used in creating the application definition.
     *
     * @return
     * @throws SQLException
     * @throws ServletException
     * @param conn
     */
    public static List<Form> getEnabledFormList(Connection conn) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        String sql = "select name, label, require_reauth AS requireReauth, require_patient AS requirePatient, " +
        		"flow_id AS flowId, flow_order AS flowOrder, form_type_id AS formTypeId, " +
        		"created AS \"auditInfo.created\", " +
                "last_modified AS \"auditInfo.lastModified\", " +
                "created_by AS \"auditInfo.createdBy.id\", " +
                "last_modified_by AS \"auditInfo.lastModifiedBy.id\", " +
                "global_identifier_name AS globalIdentifierName, import_id AS importId, form_domain_id AS formDomainId, " +
                "import_type as importType , locale, id " +
        		"from ADMIN.form  where is_enabled=1 ";
        list = DatabaseUtils.getList(conn, Form.class, sql, values);
        return list;
    }

    /**
     * Fetches a simple list of form names for use in creating the forms stored in the application space upon startup
     *
     * @return
     * @throws SQLException
     * @throws ServletException
     * @param conn
     */
    public static List getAllFormNames(Connection conn) throws SQLException, ServletException {
        List list = new ArrayList();
        ArrayList values = new ArrayList();
        String sql = "select f.name from form f where f.is_enabled=true ";
        list = DatabaseUtils.getList(conn, Form.class, sql, values);
        return list;
    }

    /**
     * @param fieldId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @deprecated use FieldEnumerationDAO getAll(fieldId) instead
     */
    public static List getFieldEnumerations(Long fieldId) throws SQLException, ServletException {
        // get the enumerations
        String enumSql = "select id, field_id AS fieldId, enumeration, numeric_value AS numericValue, is_enabled AS enabled " +
                "from field_enumeration " +
                "WHERE field_id=? " +
                "ORDER BY display_order asc";
        ArrayList enumValues = new ArrayList();
        enumValues.add(fieldId);
        List list = DatabaseUtils.getList(FieldEnumeration.class, enumSql, enumValues);
        return list;
    }

    /**
     * Just query the form table - not the whole form graph. This is used for testing.
     *
     * @param formId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getFormTop(Long formId) throws SQLException, ServletException {
        // get list of forms
        String sql = "select id, name, label, require_reauth AS requireReauth, require_patient AS requirePatient, " +
                "is_enabled AS enabled, flow_id AS flowId, flow_order AS flowOrder, form_type_id AS formTypeId, " +
                "max_submissions AS maxSubmissions " +
                "from form ";
        ArrayList enumValues = new ArrayList();
        // enumValues.add(formId);
        List list = DatabaseUtils.getList(Form.class, sql, enumValues);
        return list;
    }

    public static Object updateForm(Connection conn, Form item) throws SQLException, ServletException {
        ArrayList values = new ArrayList();
        String sql = "UPDATE form set name=?, label=?, require_reauth=?, require_patient=?, is_enabled=?, flow_id=?, " +
                "flow_order=?, form_type_id=?, max_submissions=? " +
                "WHERE id=?";
        values.add(item.getName());
        values.add(item.getLabel());
        values.add(item.isRequireReauth());
        values.add(item.isRequirePatient());
        values.add(item.isEnabled());
        values.add(item.getFlowId());
        values.add(item.getFlowOrder());
        values.add(item.getFormTypeId());
        values.add(item.getMaxSubmissions());
        values.add(item.getId());
        Long encId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return encId;
    }

    /**
     * Updates admin db schema
     * @param conn
     * @param column
     * @param value
     * @param id
     * @return
     * @throws SQLException
     */
    public static Object updateFormElement(Connection conn, String column, String value, Long id) throws SQLException {
        ArrayList values = new ArrayList();
        String sql = "UPDATE form SET " + column + " =? WHERE id=" + id;
        values.add(value);
        Long encId = null;
        encId =    (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return encId;
    }
}
