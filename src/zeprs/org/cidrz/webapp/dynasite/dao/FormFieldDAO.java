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

import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.utils.*;
import org.cidrz.webapp.dynasite.valueobject.*;
import org.cidrz.webapp.dynasite.Constants;
import org.rti.zcore.dynasite.utils.DynasiteUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.UUID;

/**
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jul 4, 2005
 *         Time: 9:22:16 AM
 */
public class FormFieldDAO {
    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(FormFieldDAO.class);

    /**
     * Returns one record
     *
     * @param conn
     * @param id
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static FormField getOne(Connection conn, Long id) throws SQLException, ServletException, ObjectNotFoundException {
        FormField item = null;
        String sql;
        ArrayList values;
        sql = "select id, label, type, required, min_value AS minValue, max_value AS maxValue, " +
                "units, display_order AS displayOrder, patient_property AS patientProperty, is_enabled AS enabled, " +
                "shared, star_schema_name AS starSchemaName, encounter_record_property AS encounterRecordproperty, " +
                "patient_status_property AS patientStatusproperty, patient_lab_property AS patientLabproperty " +
                "from admin.form_field " +
                "WHERE id=?";
        values = new ArrayList();
        values.add(id);
        item = (FormField) DatabaseUtils.getBean(conn, FormField.class, sql, values);
        return item;
    }
    
    /**
     * Returns one record
     *
     * @param conn
     * @param uuid
     * @return
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static FormField getOne(Connection conn, String uuid) throws SQLException, ServletException, ObjectNotFoundException {
    	FormField item = null;
    	String sql;
    	ArrayList values;
    	sql = "select id, label, type, required, min_value AS \"minValue\", max_value AS \"maxValue\", " +
    	"units, display_order AS displayOrder, patient_property AS patientProperty, is_enabled AS enabled, " +
    	"shared, star_schema_name AS starSchemaName, encounter_record_property AS encounterRecordproperty, " +
    	"patient_status_property AS patientStatusproperty, patient_lab_property AS patientLabproperty, import_id AS importId, " +
    	"openmrs_concept, openmrs_datatype, openmrs_name, openmrs_parent_concept, uuid, parent_uuid as parentUuid " +
    	", locale " +
    	"from ADMIN.form_field " +
    	"WHERE uuid=?";
    	values = new ArrayList();
    	values.add(uuid);
    	item = (FormField) DatabaseUtils.getBean(conn, FormField.class, sql, values);
    	return item;
    }

    /**
     * Fetch formField by importId - useful when importing forms
     * @param conn
     * @param importId
     * @return
     * @throws SQLException
     * @throws ServletException
     * @throws ObjectNotFoundException
     */
    public static FormField getOneByImportId(Connection conn, Long importId) throws SQLException, ServletException, ObjectNotFoundException {
    	FormField item = null;
    	String sql;
    	ArrayList values;
    	sql = "select id, label, type, required, min_value AS \"minValue\", max_value AS \"maxValue\", " +
    	"units, display_order AS displayOrder, patient_property AS patientProperty, is_enabled AS enabled, " +
    	"shared, star_schema_name AS starSchemaName, encounter_record_property AS encounterRecordproperty, " +
    	"patient_status_property AS patientStatusproperty, patient_lab_property AS patientLabproperty " +
        ", import_id AS importId " +
//    	"openmrs_concept, openmrs_datatype, openmrs_name, openmrs_parent_concept, uuid, parent_uuid as parentUuid, locale  " +
    	"from ADMIN.form_field " +
    	"WHERE import_id=?";
    	values = new ArrayList();
    	values.add(importId);
    	item = (FormField) DatabaseUtils.getBean(conn, FormField.class, sql, values);
    	return item;
    }

    /**
     * unused
     * @return List of objects
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAll(Connection conn) throws SQLException, ServletException {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, label, type, required, min_value AS minValue, max_value AS maxValue, " +
                "units, display_order AS displayOrder, patient_property AS patientProperty, is_enabled AS enabled, " +
                "shared, star_schema_name AS starSchemaName, encounter_record_property AS encounterRecordproperty, " +
                "patient_status_property AS patientStatusproperty, patient_lab_property AS patientLabproperty " +
                "from admin.form_field ";
        list = DatabaseUtils.getList(conn, FormField.class, sql, values);
        return list;
    }

    /**
     * @param conn
     * @return List of shared Formfields
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAllSharedFields(Connection conn) throws SQLException, ServletException {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id, label, type, required, min_value AS minValue, max_value AS maxValue, " +
                "units, display_order AS displayOrder, patient_property AS patientProperty, is_enabled AS enabled, " +
                "shared, star_schema_name AS starSchemaName, encounter_record_property AS encounterRecordproperty, " +
                "patient_status_property AS patientStatusproperty, patient_lab_property AS patientLabproperty " +
                "from admin.form_field " +
                "where shared=1 " +
                "ORDER BY label";
        list = DatabaseUtils.getList(conn, FormField.class, sql, values);
        return list;
    }

    /**
     * @param conn
     * @return List of formfield ID's
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getIds(Connection conn) throws SQLException, ServletException {

        List list = new ArrayList();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select id " +
                "from admin.form_field " +
                "ORDER BY id";
        list = DatabaseUtils.getList(conn, FormField.class, sql, values);
        return list;
    }

    /**
     * updates form field in admin
     *
     * @param conn
     * @param formfield
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Object updateFormfield(Connection conn, FormField formfield) throws SQLException, ServletException {
        ArrayList values = new ArrayList();
        values.add(formfield.getLabel());
        values.add(formfield.isShared());
        values.add(formfield.getStarSchemaName());
        values.add(formfield.getMinValue());
        values.add(formfield.getMaxValue());
        values.add(formfield.getType());
        values.add(formfield.getUnits());
        values.add(formfield.isRequired());
        values.add(formfield.isEnabled());

        values.add(formfield.getId());
        String sql = "UPDATE admin.form_field set label=?, shared=?, star_schema_name=?, min_value=?, max_value=?, type=?, " +
                "units=?, required=?, is_enabled=? " +
                "WHERE id=?";
        Long encId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return encId;
    }

    /**
     * Creates form field in admin
     * unused
     * @param formfield
     * @param pageItem
     * @param userName
     * @param siteId
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Object createFormfield(Connection conn, FormField formfield, PageItem pageItem, String userName, Long siteId) throws SQLException, ServletException {
        String sql = "insert into admin.form_field (label, type, required, min_value, max_value, units, display_order, " +
                "patient_property, is_enabled, " +
                "last_modified, created, last_modified_by, created_by, site_id, " +
                "shared, star_schema_name, encounter_record_property, patient_status_property, patient_lab_property) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ArrayList values = new ArrayList();
        values.add(formfield.getLabel());
        values.add(formfield.getType());
        values.add(formfield.isRequired());
        values.add(formfield.getMinValue());
        values.add(formfield.getMaxValue());
        values.add(formfield.getUnits());
        values.add(formfield.getDisplayOrder());
        values.add(formfield.getPatientProperty());
        values.add(formfield.isEnabled());
        FormDAO.AddAuditInfo(values, userName, siteId);
        values.add(formfield.isShared());
        values.add(formfield.getStarSchemaName());
        values.add(formfield.getEncounterRecordproperty());
        values.add(formfield.getPatientStatusproperty());
        values.add(formfield.getPatientLabproperty());
        Long formFieldId = (Long) DatabaseUtils.create(conn, sql, values.toArray());

        sql = "insert into admin.page_item (form_field_id, display_order, input_type, close_row, column_number, " +
                "last_modified, created, last_modified_by, created_by, site_id, " +
                "size, maxlength, rows, cols, visible, visible_enum_id_trigger1, visible_dependencies1, " +
                "visible_enum_id_trigger2, visible_dependencies2, selected_enum, form_id, colspan) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        values.clear();
        values.add(formFieldId);
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
        Long pageItemId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        return pageItemId;
    }
    
    /**
     * Delete formField from db
     * @param conn
     * @param id
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static Object deleteFormfield(Connection conn, Long id) throws SQLException, ServletException {
    	ArrayList values = new ArrayList();
    	values.add(id);
    	String sql = "DELETE FROM ADMIN.form_field " +
    	"WHERE id=?";
    	Long encId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
    	return encId;
    }

    /**
     * Creates formField and pageItem in admin, also adds to  DynaSiteObjects.getFieldToForms()
     * @param formfield
     * @param pageItem
     * @param userName
     * @param siteId
     * @param createFormField TODO
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static PageItem createFormfield(Connection conn, FormField formfield, PageItem pageItem, String userName, Long siteId, Boolean createFormField) throws SQLException, ServletException {

    	if (formfield.getUuid() == null) {
    		UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            formfield.setUuid(uuidStr);
    	}

    	if (createFormField == true) {
    	String sql = "insert into ADMIN.form_field (label, type, required, min_value, max_value, units, display_order, " +
                "patient_property, is_enabled, " +
                "last_modified, created, last_modified_by, created_by, site_id, " +
                "shared, star_schema_name, encounter_record_property, patient_status_property, patient_lab_property " +
                 ", import_id )" +
//                "openmrs_concept, openmrs_datatype, openmrs_name, openmrs_parent_concept, uuid, parent_uuid, locale ) " +
//                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    			"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ArrayList values = new ArrayList();
        values.add(formfield.getLabel());
        values.add(formfield.getType());
        values.add(formfield.isRequired());
        values.add(formfield.getMinValue());
        values.add(formfield.getMaxValue());
        values.add(formfield.getUnits());
        values.add(formfield.getDisplayOrder());
        values.add(formfield.getPatientProperty());
        values.add(formfield.isEnabled());
        DynasiteUtils.AddAuditInfo(values, userName, siteId);
        values.add(formfield.isShared());
        values.add(formfield.getStarSchemaName());
        values.add(formfield.getEncounterRecordproperty());
        values.add(formfield.getPatientStatusproperty());
        values.add(formfield.getPatientLabproperty());
        values.add(formfield.getImportId());
//        values.add(formfield.getOpenmrs_concept());
//        values.add(formfield.getOpenmrs_datatype());
//        values.add(formfield.getOpenmrs_name());
//        values.add(formfield.getOpenmrs_parent_concept());
//        values.add(formfield.getUuid());
//        values.add(formfield.getParentUuid());
//        values.add(formfield.getLocale());

        Long formFieldId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        pageItem.setFormFieldId(formFieldId);

        List<Long> forms = new ArrayList();
        forms.add(pageItem.getFormId());
        DynaSiteObjects.getFieldToForms().put(formFieldId, forms);
    	}
    	// removed reserved word escaping for "rows" for ZEPRS since it uses mysql.

        String sql = "insert into ADMIN.page_item (form_field_id, display_order, input_type, close_row, column_number, " +
                "last_modified, created, last_modified_by, created_by, site_id, " +
                "size, maxlength, rows, cols, visible, visible_enum_id_trigger1, visible_dependencies1, " +
                "visible_enum_id_trigger2, visible_dependencies2, selected_enum, form_id, colspan, roles, import_id) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ArrayList values = new ArrayList();
        values.add(pageItem.getFormFieldId());
        values.add(pageItem.getDisplayOrder());
        values.add(pageItem.getInputType());
        values.add(pageItem.isCloseRow());
        values.add(pageItem.getColumnNumber());
        DynasiteUtils.AddAuditInfo(values, userName, siteId);
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
        values.add(pageItem.getRoles());
        // store the imported page_item's id as importId
        values.add(pageItem.getId());
        Long pageItemId = (Long) DatabaseUtils.create(conn, sql, values.toArray());
        pageItem.setId(pageItemId);
        return pageItem;
    }

    /**
     * This is for admin - provide list of fields
     *
     * @param conn
     * @return List of objects
     * @throws java.sql.SQLException
     * @throws javax.servlet.ServletException
     */
    public static List getAllEnabled(Connection conn) throws SQLException, ServletException {

        List list;
        ArrayList values;
        values = new ArrayList();
        String sql = "select id, label, type, required, min_value AS minValue, max_value AS maxValue, " +
                "units, display_order AS displayOrder, patient_property AS patientProperty, is_enabled AS enabled, " +
                "shared, star_schema_name AS starSchemaName, encounter_record_property AS encounterRecordproperty, " +
                "patient_status_property AS patientStatusproperty, patient_lab_property AS patientLabproperty " +
                "from admin.form_field " +
                "WHERE is_enabled=1 " +
                "ORDER BY label";
        list = DatabaseUtils.getList(conn, FormField.class, sql, values);
        return list;
    }

    /**
     * Used for the query builder.
     *
     * @param conn
     * @return
     * @throws SQLException
     * @throws ServletException
     */
    public static List getAllEnabledFields(Connection conn) throws SQLException, ServletException {

        List list;
        ArrayList values;
        values = new ArrayList();
        String sql = "select id, label, type, required, min_value AS minValue, max_value AS maxValue, " +
                "units, display_order AS displayOrder, patient_property AS patientProperty, is_enabled AS enabled, " +
                "shared, star_schema_name AS starSchemaName, encounter_record_property AS encounterRecordproperty, " +
                "patient_status_property AS patientStatusproperty, patient_lab_property AS patientLabproperty " +
                "from admin.form_field " +
                "WHERE is_enabled=1 " +
                "AND type !='Display' " +
                "ORDER BY label";
        list = DatabaseUtils.getList(conn, FormField.class, sql, values);
        return list;
    }

    /**
     * Provides a list of form id's for a form field.
     * @param conn
     * @return a list of form id's for a form field.
     */
    public static List<Form> getFormIdList(Connection conn, Long formFieldId) throws SQLException, ServletException {

        List<Form> list = new ArrayList<Form>();
        ArrayList values = new ArrayList();
        values = new ArrayList();
        String sql = "select pi.form_id AS id from page_item pi, form_field ff\n" +
        "where ff.id = pi.form_field_id\n" +
        "AND ff.id=?";
        values.add(formFieldId);
        list = DatabaseUtils.getList(conn, Form.class, sql, values);
        return list;
    }
    
    /**
     * Creates field in database, creates ApplicationUpdate, and serializes ApplicationUpdate to xml
     * @param adminConn
     * @param appConn
     * @param formField
     * @param formId
     * @param pageItem
     * @return
     * @throws Exception
     */
    public static FormField createColumn(Connection adminConn, Connection appConn, FormField formField, Long formId, PageItem pageItem) throws Exception {
        String sql = null;
		String database = Constants.DATABASE_TYPE;
        Form form = FormDisplayDAO.getOne(adminConn, formId);
        String inputType = pageItem.getInputType();
        String fieldName = formField.getStarSchemaName();
        String dataType = null;
        String fieldType = formField.getType();
        if (! fieldType.equals("Display") && ! inputType.equals("multiselect_enum")) {
        	if (fieldType.equals("Text")) {
        		dataType = "VARCHAR(255)";
        		if (pageItem.getInputType().equals("textarea")) {
        			if (database.equals("derby")) {
        				dataType = "LONG VARCHAR";
        			} else if (database.equals("mssql")) {
        				dataType = "TEXT";
        			} else if (database.equals("mysql")) {
        				dataType = "VARCHAR";
        			}
        		}
        	} else if (fieldType.equals("Enum")) {
        		dataType = "INTEGER";
        	} else if (fieldType.equals("Integer")) {
        		dataType = "INTEGER";
        	} else if (fieldType.equals("Long")) {
        		dataType = "bigint";
        	} else if (fieldType.equals("Float")) {
        		dataType = "float";
        	} else if (fieldType.equals("Double")) {
        		if (database.equals("derby")) {
        			dataType = "double";
        		} else if (database.equals("mssql")) {
        			dataType = "DOUBLE PRECISION";
        		} else if (database.equals("mysql")) {
        			dataType = "double";
        		}
        	} else if (fieldType.equals("Date")) {
        		if (database.equals("derby")) {
        			dataType = "date";
        		} else if (database.equals("mssql")) {
        			dataType = "DATETIME ";
        		} else if (database.equals("mysql")) {
        			dataType = "date";
        		}
        	} else if (fieldType.equals("Time")) {
        		if (database.equals("derby")) {
        			dataType = "time";
        		} else if (database.equals("mssql")) {
        			dataType = "DATETIME";
        		} else if (database.equals("mysql")) {
        			dataType = "time";
        		}
        	} else if (fieldType.equals("Datetime")) {
        		dataType = "timestamp";
        	} else if (fieldType.equals("Timetamp")) {
        		dataType = "timestamp";
        	} else if (fieldType.equals("Year")) {
        		if (database.equals("derby")) {
        			dataType = "SMALLINT";
        		} else if (database.equals("mssql")) {
        			dataType = "TINYINT";
        		} else if (database.equals("mysql")) {
        			dataType = "TINYINT";
        		}
        	} else if (fieldType.equals("Boolean")) {
        		if (database.equals("derby")) {
        			dataType = "SMALLINT";
        		} else if (database.equals("mssql")) {
        			dataType = "TINYINT";
        		} else if (database.equals("mysql")) {
        			dataType = "TINYINT(1)";
        		}
        	} else if (fieldType.equals("Yes/No")) {
        		if (database.equals("derby")) {
        			dataType = "SMALLINT";
        		} else if (database.equals("mssql")) {
        			dataType = "TINYINT";
        		} else if (database.equals("mysql")) {
        			dataType = "TINYINT";
        		}
        	} else if (fieldType.equals("sex")) {
        		if (database.equals("derby")) {
        			dataType = "SMALLINT";
        		} else if (database.equals("mssql")) {
        			dataType = "TINYINT";
        		} else if (database.equals("mysql")) {
        			dataType = "TINYINT";
        		}
        	} else if (fieldType.equals("MultiEnum")) {
        		dataType = "INTEGER";
        	}

        	// if (subject.getId() == 0) {
        	if (database.equals("derby")) {
            	sql = "ALTER TABLE " + form.getName().toLowerCase() + " add column " + fieldName + " " + dataType + "";
			} else if (database.equals("mssql")) {
	        	sql = "ALTER TABLE " + form.getName().toLowerCase() + " add " + fieldName + " " + dataType + "";
			} else if (database.equals("mysql")) {
	        	sql = "ALTER TABLE " + form.getName().toLowerCase() + " add column " + fieldName + " " + dataType + "";
			}

        	DatabaseUtils.create(appConn, sql);
        	ApplicationUpdateDAO.createApplicationUpdate(adminConn, sql, null, "S");
        	log.debug("New field. SQL: " + sql);
        	// Create update for demo database
        	if (Constants.DATABASE_TYPE.equals("mysql")) {
        		String sqlDemo = "ALTER TABLE zeprsdemo." + form.getName().toLowerCase() + " add column " + fieldName + " " + dataType + "";
        		ApplicationUpdateDAO.createApplicationUpdate(adminConn, sql, null, "S");
        		log.debug("New field. SQL: " + sqlDemo);
        	}
            //genSource(form, adminConn);
        }
        return formField;
    }

    public static FormField createColumn(Connection adminConn, FormField formField, Long formId, PageItem pageItem) throws Exception {
        String sql;
        Form form = FormDisplayDAO.getOne(adminConn, formId);
        /*HashMap formFieldMap = (HashMap) DynaSiteObjects.getFieldToPageItem().get(formId);
        Long pid = (Long) formFieldMap.get(formField.getId());
        PageItem pageItem = (PageItem) DynaSiteObjects.getPageItems().get(pid);*/
        String inputType = pageItem.getInputType();
        String fieldName = formField.getStarSchemaName();
        String dataType = null;
        String fieldType = formField.getType();
        if (! fieldType.equals("Display") && ! inputType.equals("multiselect_enum")) {
            if (fieldType.equals("Text")) {
                dataType = "VARCHAR(255)";
            } else if (fieldType.equals("Enum")) {
                dataType = "int(11)";
            } else if (fieldType.equals("Integer")) {
                dataType = "int(11)";
            } else if (fieldType.equals("Long")) {
                dataType = "bigint(20)";
            } else if (fieldType.equals("Float")) {
                dataType = "float";
            } else if (fieldType.equals("Date")) {
                dataType = "date";
            } else if (fieldType.equals("Time")) {
                dataType = "time";
            } else if (fieldType.equals("Datetime")) {
            	dataType = "datetime";
            } else if (fieldType.equals("Timetamp")) {
            	dataType = "timestamp";
            } else if (fieldType.equals("Year")) {
                dataType = "int(5)";
            } else if (fieldType.equals("Boolean")) {
                dataType = "tinyint(1)";
            } else if (fieldType.equals("Yes/No")) {
                dataType = "tinyint(4)";
            } else if (fieldType.equals("sex")) {
                dataType = "tinyint(4)";
            } else if (fieldType.equals("MultiEnum")) {
                dataType = "int(11)";
            }
           // if (subject.getId() == 0) {
            sql = "ALTER TABLE zeprs." + form.getName().toLowerCase() + " add column " + fieldName + " " + dataType + ";\n";
            DatabaseUtils.create(adminConn, sql);
            ApplicationUpdate appUpdate = new ApplicationUpdate();
            Timestamp datePosted = new Timestamp(System.currentTimeMillis());
            String type = "S";
            appUpdate.setDateposted(datePosted);
            appUpdate.setJob(sql);
            appUpdate.setType(type);
            Long id = (Long) ApplicationUpdateDAO.save(adminConn, "admin", null, datePosted, null, type, sql);
            appUpdate.setId(id);
            // XML version is a safety in case the admin db get wiped out.
            XmlUtils.save(appUpdate, Constants.ARCHIVE_PATH_LOGS_APPUPDATES + "/update" + id + ".xml");
            // Create update for demo database
            String sqlDemo = "ALTER TABLE zeprsdemo." + form.getName().toLowerCase() + " add column " + fieldName + " " + dataType + ";\n";
            ApplicationUpdate appUpdateDemo = new ApplicationUpdate();
            appUpdateDemo.setDateposted(datePosted);
            appUpdateDemo.setJob(sqlDemo);
            appUpdateDemo.setType(type);
            Long idDemo = (Long) ApplicationUpdateDAO.save(adminConn, "admin", null, datePosted, null, type, sqlDemo);
            appUpdateDemo.setId(idDemo);
            XmlUtils.save(appUpdateDemo, Constants.ARCHIVE_PATH_LOGS_APPUPDATES + "/update" + id + ".xml");
            log.debug("New field. SQL: " + sql);
            log.debug("New field. SQL: " + sqlDemo);
            //genSource(form, adminConn);
        }
        return formField;
    }

    /**
     * Generate source after creating a form field.
     * @deprecated unused
     * @param form
     * @param adminConn
     * @throws ServletException
     * @throws SQLException
     * @throws ObjectNotFoundException
     * @throws IOException
     */
    public static void genSource(Form form, Connection adminConn) throws ServletException, SQLException, ObjectNotFoundException, IOException {
        // gen source
        String pathname = null;
        String deployPathname = null;
        String genPackage = null;
        pathname = Constants.DEV_DYNASITE_FORMS_SOURCE;
        genPackage = Constants.DYNASITE_FORMS_PACKAGE;
        deployPathname = Constants.DEPLOY_DYNASITE_FORMS_SOURCE;
        DynasiteSourceGenerator.generateSource(false, "record", form, genPackage, pathname, deployPathname);
        pathname = Constants.DEV_DYNASITE_REPORTS_SOURCE;
        genPackage = Constants.DYNASITE_REPORTS_PACKAGE;
        deployPathname = Constants.DEPLOY_DYNASITE_REPORTS_SOURCE;
        DynasiteSourceGenerator.generateSource(false, "report", form, genPackage, pathname, deployPathname);
        // update struts-config
        new GenerateStrutsConfig().init(Boolean.TRUE);
        // generate form xml
        Form formObj = FormDisplayDAO.getFormGraph(adminConn, form.getId());
        String classname = StringManipulation.fixClassname(formObj.getName());
        DynaSiteObjects.getForms().put(formObj.getId(), formObj);
        XmlUtils.save(formObj, Constants.DEV_XML_PATH + classname + ".xml");
    }


}
