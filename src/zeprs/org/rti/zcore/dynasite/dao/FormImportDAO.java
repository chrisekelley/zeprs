package org.rti.zcore.dynasite.dao;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.FieldEnumeration;
import org.cidrz.webapp.dynasite.valueobject.Flow;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.FormType;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.RuleDefinition;
import org.cidrz.webapp.dynasite.valueobject.ApplicationUpdate;
import org.rti.zcore.dynasite.DynasiteChangesList;
import org.rti.zcore.dynasite.utils.DynasiteUtils;
import org.rti.zcore.utils.database.DatabaseCompatability;
import org.cidrz.webapp.dynasite.dao.ApplicationUpdateDAO;
import org.cidrz.webapp.dynasite.dao.FieldEnumerationDAO;
import org.cidrz.webapp.dynasite.dao.FlowDAO;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.dao.FormFieldDAO;
import org.cidrz.webapp.dynasite.dao.FormTypeDAO;
import org.cidrz.webapp.dynasite.dao.PageItemDAO;
import org.cidrz.webapp.dynasite.dao.RuleDefinitionDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.valueobject.Publisher;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.FileUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;

public class FormImportDAO {

    /**
     * Commons Logging instance.
     */

    private static Log log = LogFactory.getFactory().getInstance(FormImportDAO.class);

	 /**
     * Persist a form. Inserts form into admin db, creates new table into app db, creates ApplicationUpdate (optional).
	 * @param form
	 * @param userName
	 * @param siteId
	 * @param logAppUpdate - true if you want to log to appupdates log. You probably do.
	 * @param createSchemaOnly - execute only schema alterations - don't insert new records into the Admin tables.
	 * @param publisher - used to log app updates - identify remote site updates.
     * @return formId
     * @throws Exception
     * @throws Exception
     */
    public static Long saveNewFormOnly(Connection connAdmin, Connection connApp, Form form, String userName, Long siteId, Boolean logAppUpdate, Boolean createSchemaOnly, Publisher publisher) throws Exception {

    	Long formId = null;
    	//	try {
    	String name = form.getName();
    	String label = form.getLabel();
    	boolean reauth = form.isRequireReauth();
    	boolean requirePatient = form.isRequirePatient();
    	boolean enabled = form.isEnabled();
    	Timestamp lastModified = new Timestamp(System.currentTimeMillis());
    	Timestamp created = new Timestamp(System.currentTimeMillis());
    	Long flowId = form.getFlowId();
    	Integer flowOrder = form.getFlowOrder();
    	Integer maxSubmissions = form.getMaxSubmissions();
    	Integer reauthInt = null;
    	Integer requirePatientInt = null;
    	Integer enabledInt = null;
    	String globalIdentifierName = form.getGlobalIdentifierName();
    	// store the imported form's id as importId
    	Long importId = form.getImportId();
    	Long formDomainId = form.getFormDomainId();

    	if (reauth == true) {
    		reauthInt = 1;
    	} else if (reauth == false) {
    		reauthInt = 0;
    	}
    	if (requirePatient == true) {
    		requirePatientInt = 1;
    	} else if (requirePatient == false) {
    		requirePatientInt = 0;
    	}
    	if (enabled == true) {
    		enabledInt = 1;
    	} else if (enabled == false) {
    		enabledInt = 0;
    	}

    	// Create table in app db
    	String tableName = form.getName();
    	String fkName =  "FK" + tableName;
    	String sql = null;

    	Long formTypeId = form.getFormTypeId();

    	if (Constants.DATABASE_TYPE.equals("mssql")) {
    		if (formTypeId == 5) {	// admin form
    			sql="CREATE TABLE " + tableName.toUpperCase() + " (ID BIGINT IDENTITY(1,1) NOT NULL, PRIMARY KEY (id));";
    		} else if (formTypeId == 6) {	// patient bridge table
    			sql="CREATE TABLE " + tableName.toUpperCase() + " (ID BIGINT IDENTITY(1,1) NOT NULL, ENCOUNTER_ID BIGINT, PRIMARY KEY (id));";
    		} else {
    			sql="CREATE TABLE " + tableName.toUpperCase() + " (ID BIGINT IDENTITY(1,1) NOT NULL, " +
    					"CONSTRAINT [" + fkName.toUpperCase() + "] FOREIGN KEY([ID]) REFERENCES [APP].[ENCOUNTER] ([ID]), " +
    					"PRIMARY KEY (id));";
    		}
    	} else {
    		if (formTypeId == 5) {			
    			if (Constants.DATABASE_NAME.equals("mysql")) {
    				sql = "CREATE TABLE " + tableName + " (\n" +
    		    			"  ID bigint NOT NULL AUTO_INCREMENT,\n" +
    		    			"  PRIMARY KEY (id))";
    			} else {
    				sql = "CREATE TABLE " + tableName + " (\n" +
    		    			"  ID bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
    		    			"  PRIMARY KEY (id))";
    			}
    		} else if (formTypeId == 6) {
    			sql = "CREATE TABLE " + tableName + " (\n" +
    			"  ID bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
    			"  ENCOUNTER_ID BIGINT,\n" +
    			"  PRIMARY KEY (id))\n";
    			//	"  CONSTRAINT " + fkName + " FOREIGN KEY (ENCOUNTER_ID) REFERENCES encounter (id)\n";
    		} else {
    			sql = "CREATE TABLE " + tableName + " (\n" +
    			"  ID BIGINT NOT NULL default 0,\n" +
    			"  PRIMARY KEY (id),\n" +
    			//  "  KEY " + fkName + " (id),\n" +
    			"  CONSTRAINT " + fkName + " FOREIGN KEY (id) REFERENCES encounter (id)\n" +
    			")";
    		}
    	}
    	DatabaseUtils.create(connApp, sql);

//    	if (logAppUpdate == true) {
//    		ApplicationUpdate appUpdate = new ApplicationUpdate();
//        	Timestamp datePosted = new Timestamp(System.currentTimeMillis());
//        	String type = "S";
//        	appUpdate.setDateposted(datePosted);
//        	appUpdate.setJob(sql);
//        	appUpdate.setType(type);
//        	if (publisher != null) {
//        		String message = publisher.toString();
//            	appUpdate.setMessage(message);
//        	}
//        	//Long id = (Long) ApplicationUpdateDAO.save(connAdmin, "ADMIN", null, datePosted, null, type, sql);
//        	Long id = (Long) ApplicationUpdateDAO.save(connAdmin, "ADMIN", appUpdate);
//        	appUpdate.setId(id);
//        	// XML version is a safety in case the admin db get wiped out.
//        	XmlUtils.save(appUpdate, Constants.ARCHIVE_PATH_LOGS_APPUPDATES + "/update" + id + ".xml");
//    	}

    	if (createSchemaOnly == false) {

    		// check if fk constrains are satisfied
    		//flow
    		Flow flow = form.getFlow();
    		if (flow == null) {
    			flow = (Flow) DynaSiteObjects.getFlowMap().get(Long.valueOf("100"));
    		}
    		String flowUUID = null;
    		try {
    			flowUUID = flow.getGlobalIdentifierName();
    		} catch (NullPointerException e1) {
    			// xml schema may not have a uuid
    		}
    		if (flowUUID != null ) {
    			try {
    				FlowDAO.getOneByUUID(connAdmin, flowUUID);
    			} catch (ObjectNotFoundException e) {
    				//persist this formType.
    				Long id = FlowDAO.save(connAdmin,flow);
    				form.setFlowId(id);
    				flowId = id;
    				form.setFlow(flow);
    				DynasiteUtils.refreshFlows(flow, connAdmin, id, true);
    			}
    		} else {
    			try {
    				FlowDAO.getOne(connAdmin, flowId);
    			} catch (ObjectNotFoundException e) {
    				//create a uuid
    				log.debug("Flow not found - id: " + flowId);
    				UUID uuid = UUID.randomUUID();
    				String uuidStr = uuid.toString();
    				flow.setGlobalIdentifierName(uuidStr);
    				Long id = FlowDAO.save(connAdmin,flow);
    				form.setFlowId(id);
    				form.setFlow(flow);
    				DynasiteUtils.refreshFlows(flow, connAdmin, id, true);
    			}
    		}


    		//form_type
    		FormType formType = form.getFormType();
    		// todo: enable following code once ready to support uuid's for formTypes.
    		/*String formTypeUUID = null;
		try {
			formTypeUUID = formType.getGlobalIdentifierName();
		} catch (NullPointerException e1) {
			//xml schema may not have a uuid
		}
		if (formTypeUUID != null) {
			try {
				FormTypeDAO.getOneByUUID(connAdmin, formTypeUUID);
			} catch (ObjectNotFoundException e) {
				//persist this formType.
				Long id = FormTypeDAO.save(connAdmin,formType);
				form.setFormTypeId(id);
				form.setFormType(formType);
			}
		} else {
			try {
				FormTypeDAO.getOne(connAdmin, formTypeId);
			} catch (ObjectNotFoundException e) {
				//create a uuid
				UUID uuid = UUID.randomUUID();
	            String uuidStr = uuid.toString();
				formType.setGlobalIdentifierName(uuidStr);
				Long id = FormTypeDAO.save(connAdmin,formType);
				form.setFormTypeId(id);
				form.setFormType(formType);
				DynasiteUtils.refreshFormTypes(formType, connAdmin, id);
			}
		}*/

    		//todo: disable following code once ready to support uuid's for formTypes.
    		// see if we have this formType already
    		try {
    			FormType dynasiteFormType = FormTypeDAO.getOne(connAdmin, formTypeId);
    		} catch (ObjectNotFoundException e) {
    			//set formTypeId to 1
    			Long basicFormTypeId = Long.valueOf("1");
    			FormType basicFormType = FormTypeDAO.getOne(connAdmin, basicFormTypeId);
    			form.setFormTypeId(basicFormTypeId);
    			form.setFormType(basicFormType);
    			formTypeId = basicFormTypeId;
    		}

    		sql = "insert into ADMIN.form (name, label, require_reauth, require_patient, is_enabled, " +
    		"last_modified, created, last_modified_by, created_by, site_id, " +
    		"flow_id, flow_order, form_type_id, max_submissions, global_identifier_name, import_id, form_domain_id, " +
    		"start_new_event, event_type, import_type, locale) " +
    		"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    		ArrayList values = new ArrayList();
    		values.add(name);
    		values.add(label);
    		values.add(reauthInt);
    		values.add(requirePatientInt);
    		values.add(enabledInt);
    		values.add(lastModified);
    		values.add(created);
    		values.add(userName);
    		values.add(userName);
    		values.add(siteId);
    		values.add(flowId);
    		values.add(flowOrder);
    		values.add(formTypeId);
    		values.add(maxSubmissions);
    		values.add(globalIdentifierName);
    		values.add(importId);
    		values.add(formDomainId);
    		values.add(form.getStartNewEvent());
    		values.add(form.getEventType());
    		values.add(form.getImportType());
    		values.add(form.getLocale());
    		DatabaseUtils.create(connAdmin, sql, values.toArray());
    		log.debug("New form. SQL: " + sql + " Parameters: " + values.toString());
    		values.clear();
    		String databaseType = Constants.DATABASE_TYPE;
    		String idSql = DatabaseCompatability.selectLastIdentityString(databaseType, null);
    		/*if (Constants.DATABASE_TYPE.equals("mysql")) {
    			idSql = "SELECT LAST_INSERT_ID() AS id;";
    		} else if (Constants.DATABASE_TYPE.equals("mssql")) {
    			idSql = "SELECT LAST_INSERT_ID() AS id;";
    		} else {
    			idSql = "SELECT IDENT_CURRENT('ADMIN.FORM')";
    		}*/
    		BigDecimal result = (BigDecimal) DatabaseUtils.getScalar(connAdmin, idSql);
    		formId = Long.valueOf(result.toString());
    		if (logAppUpdate == true) {
    			ApplicationUpdate appUpdate = new ApplicationUpdate();
    			Timestamp datePosted = new Timestamp(System.currentTimeMillis());
    			String type = "S";
    			appUpdate.setDateposted(datePosted);
    			appUpdate.setJob(sql + " Parameters: " + values.toString());
    			appUpdate.setType(type);
    			appUpdate.setValues(values);
    			//Long id = (Long) ApplicationUpdateDAO.save(connAdmin, "ADMIN", null, datePosted, null, type, sql);
    			Long id = (Long) ApplicationUpdateDAO.save(connAdmin, "ADMIN", appUpdate);
    			appUpdate.setId(id);
    			// XML version is a safety in case the admin db get wiped out.
    			XmlUtils.save(appUpdate, Constants.ARCHIVE_PATH_LOGS_APPUPDATES + "/update" + id + ".xml");
    		}
    	} else {
    		formId = form.getId();
    	}

    	//log.debug("New table. SQL: " + sql);

    	/*if (Constants.DATABASE_NAME.equals("mysql")) {
    		// now create table for zeprsdemo
    		sql = "CREATE TABLE zeprsdemo." + tableName + " (\n" +
    		"  id bigint(20) NOT NULL default '0',\n" +
    		"  PRIMARY KEY  (`id`),\n" +
    		"  KEY " + fkName + " (id),\n" +
    		"  CONSTRAINT " + fkName + " FOREIGN KEY (id) REFERENCES zeprs.encounter (id)\n" +
    		") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
    		DatabaseUtils.create(connAdmin, sql);
    	}*/
    	return formId;
    }

	/**
	 * Saves an imported form.
	 * @param userName
	 * @param connAdmin
	 * @param fileName
	 * @param newForm
	 * @param locale
	 * @param createSchemaOnly - execute only schema alterations - don't insert new records into the Admin tables.
	 * @param publisher Used for logging updates - helps identify if an update was done on a remote instance.
	 * @return
	 * @throws Exception
	 * @throws ServletException
	 * @throws SQLException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static StringBuffer saveImportedForm(String userName,
			Connection connAdmin, String fileName, Form newForm, String locale, Boolean createSchemaOnly, Publisher publisher) throws Exception, ServletException, SQLException,
			NumberFormatException, IOException {

		StringBuffer comments = new StringBuffer();
		ArrayList<String> visibleEnumIdTriggers1 = new ArrayList<String>();
		ArrayList<String> visibleEnumIdTriggers2 = new ArrayList<String>();
		ArrayList<String> visibleEnumIdTriggersErrors = new ArrayList<String>();	// unable to find fieldEnumeration for items in this list
		ArrayList<String> visibleDependencies1 = new ArrayList<String>();
		ArrayList<String> visibleDependencies2 = new ArrayList<String>();
		ArrayList<String> visibleDependenciesErrors = new ArrayList<String>();	// unable to find formField for items in this list

		String newPath = Constants.ARCHIVE_PATH_FORMS_IMPORT_NEW;
		String completePath = Constants.ARCHIVE_PATH_FORMS_IMPORT_COMPLETE;
		String errorPath = Constants.ARCHIVE_PATH_FORMS_IMPORT_ERRORS;

		Long formId = null;

		String gid = null;
		try {
			gid = newForm.getGlobalIdentifierName();
		} catch (NullPointerException e1) {
			// xml schema may not have one of these
		}
		if (gid == null) {
			// assign this a new gid
			UUID uuid = UUID.randomUUID();
			String uuidStr = uuid.toString();
			newForm.setGlobalIdentifierName(uuidStr);
		}
		if (newForm.getLocale() == null){
			newForm.setLocale(locale);
		}

		Long siteId = newForm.getSiteId();

		// save the form
		try {
			formId = saveNewFormOnly(connAdmin, connAdmin, newForm, userName, siteId, true, createSchemaOnly, publisher);
		} catch (SQLException e) {
			comments.append("Failure: ");
			comments.append(e.getMessage());
			log.debug(e);
			if (e.getSQLState().endsWith("X0Y32")) {
				String tableName = newForm.getName();
				String message = "A table with the same name as the table (" + tableName + ") of this imported form already exists. Please delete or rename the table.";
				throw new Exception(message);
			} else {
				throw new Exception(e);
			}
			//request.setAttribute("exception", e);
			//return mapping.findForward("error");
		}
		//loop through the pageItems
		Set<PageItem> pageItems = newForm.getPageItems();
		for (PageItem pageItem : pageItems) {
			pageItem.setFormId(formId);
			FormField formfield = pageItem.getForm_field();
			// save this formfield
			try {
				FormFieldDAO.createColumn(connAdmin, connAdmin, formfield, formId, pageItem);
				Long importId = formfield.getImportId();
				Boolean createFormField = true;
				// see if this is a shared field - it may already be in the database.
				try {
					FormField field = FormFieldDAO.getOneByImportId(connAdmin, importId);
					pageItem.setFormFieldId(field.getId());
					createFormField = false;
				} catch (ObjectNotFoundException e) {
					// process normally
				}
				if (createSchemaOnly == false) {
					pageItem = FormFieldDAO.createFormfield(connAdmin, formfield, pageItem, userName, siteId, createFormField);
				}
			} catch (SQLException e) {
				//comments.append(e.getMessage());
				log.debug(e);
				comments = new StringBuffer();
				comments.append("Failure: " + e);
				break;
			}

			if (createSchemaOnly == false) {
				Set<FieldEnumeration> enums = formfield.getEnumerations();
				for (FieldEnumeration fieldEnumeration : enums) {
					if (fieldEnumeration != null) {
						fieldEnumeration.setFieldId(pageItem.getFormFieldId());
						try {
							Integer numericalValue = null;
							if (fieldEnumeration.getNumericValue() != null) {
								if (!fieldEnumeration.getNumericValue().equals("")) {
									numericalValue = Integer.valueOf(fieldEnumeration.getNumericValue());
								}
							}
							//FieldEnumerationDAO.create(connAdmin, fieldEnumeration.getFieldId(), fieldEnumeration.getEnumeration(), numericalValue, Integer.valueOf(1), fieldEnumeration.getDisplayOrder(), fieldEnumeration.getId(), fieldEnumeration.getOpenmrs_concept());
							// store the imported enum's id as importId.
							FieldEnumerationDAO.create(connAdmin, fieldEnumeration);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				// check if there are any visibleEnumIdTriggers - need to change from imported id to new id.
				String visibleEnumIdTrigger1 = pageItem.getVisibleEnumIdTrigger1();
				String visibleEnumIdTrigger2 = pageItem.getVisibleEnumIdTrigger2();
				String fieldType = formfield.getType();
				String pageItemType = pageItem.getInputType();
				if (visibleEnumIdTrigger1 != null && !visibleEnumIdTrigger1.equals("")) {
					if (fieldType.equals("Enum")) {
						if ((!pageItemType.equals("checkbox")) && (!pageItemType.equals("currentMedicine"))) {
							visibleEnumIdTriggers1.add(visibleEnumIdTrigger1);
						}
					}
				}
				if (visibleEnumIdTrigger2 != null && !visibleEnumIdTrigger2.equals("")) {
					if (fieldType.equals("Enum")) {
						if ((!pageItemType.equals("checkbox")) && (!pageItemType.equals("currentMedicine"))) {
							visibleEnumIdTriggers2.add(visibleEnumIdTrigger2);
						}
					}
				}

				String visibleDependency1 = pageItem.getVisibleDependencies1();
				String visibleDependency2 = pageItem.getVisibleDependencies2();
				if (visibleDependency1 != null && !visibleDependency1.equals("")) {
					for (StringTokenizer stringTokenizer = new StringTokenizer(visibleDependency1); stringTokenizer.hasMoreTokens();) {
						String dependency = stringTokenizer.nextToken();
						visibleDependencies1.add(dependency);
					}
				}
				if (visibleDependency2 != null && !visibleDependency2.equals("")) {
					for (StringTokenizer stringTokenizer = new StringTokenizer(visibleDependency2); stringTokenizer.hasMoreTokens();) {
						String dependency = stringTokenizer.nextToken();
						visibleDependencies2.add(dependency);
					}
				}
				List<RuleDefinition> rules = formfield.getRuleDefinitions();
				for (RuleDefinition rule : rules) {
					try {
						// store the imported rule's id as importId.
						rule.setFieldId(pageItem.getFormFieldId());
						rule.setFormId(formId);
						Long ruleId = (Long) RuleDefinitionDAO.insertRule(connAdmin, rule.getRuleClass(), rule.getOutcomeClass(), rule.getFormId(), rule.getFieldId(), rule.isEnabled(), rule.isAllPregnancies(), userName, siteId, rule.getMessage(), rule.getOperand(), rule.getOperator(), rule.getId());
					} catch (SQLException e) {
						comments.append("Failure: ");
						comments.append(e.getMessage());
						log.debug(e);
					}
				}
			}
		}

		if (createSchemaOnly == false) {
			// Loop through visibleEnumIdTriggers and change the id's
			for (String triggerId : visibleEnumIdTriggers1) {
				// fetch the new enum
				Long importId = Long.valueOf(triggerId);
				try {
					FieldEnumeration fieldEnumeration = FieldEnumerationDAO.getOneByImportId(connAdmin, importId);
					Long newId = fieldEnumeration.getId();
					//update the pageItem
					PageItemDAO.updateVisibleEnumIdTrigger(connAdmin, importId.toString(), newId.toString(), 1);
				} catch (ObjectNotFoundException e) {
					visibleEnumIdTriggersErrors.add(triggerId);
				}
			}
			for (String triggerId : visibleEnumIdTriggers2) {
				// fetch the new enum
				Long importId = Long.valueOf(triggerId);
				try {
					FieldEnumeration fieldEnumeration = FieldEnumerationDAO.getOneByImportId(connAdmin, importId);
					Long newId = fieldEnumeration.getId();
					//update the pageItem
					PageItemDAO.updateVisibleEnumIdTrigger(connAdmin, importId.toString(), newId.toString(), 2);
				} catch (ObjectNotFoundException e) {
					visibleEnumIdTriggersErrors.add(triggerId);
				}
			}

			// also need to remap the fieldDependencies - use formField importId to get the new id.
			for (String depId : visibleDependencies1) {
				// fetch the new enum
				Long importId = Long.valueOf(depId);
				try {
					FormField formField = FormFieldDAO.getOneByImportId(connAdmin, importId);
					Long newId = formField.getId();
					//update the pageItem
					PageItemDAO.updateVisibleDependency(connAdmin, importId.toString(), newId.toString(), 1);
				} catch (ObjectNotFoundException e) {
					visibleEnumIdTriggersErrors.add(depId);
				}
			}
			for (String depId : visibleDependencies2) {
				// fetch the new enum
				Long importId = Long.valueOf(depId);
				try {
					FormField formField = FormFieldDAO.getOneByImportId(connAdmin, importId);
					Long newId = formField.getId();
					//update the pageItem
					PageItemDAO.updateVisibleDependency(connAdmin, importId.toString(), newId.toString(), 2);
				} catch (ObjectNotFoundException e) {
					visibleEnumIdTriggersErrors.add(depId);
				}
			}

			Form dynasiteform = FormDisplayDAO.getFormGraphDb(connAdmin, formId);
        	DynaSiteObjects.getForms().put(formId, dynasiteform);

			// Now add this form to the FormChanges list.
			DynasiteChangesList.makeDirty(formId, "Form");
			File src = new File(newPath.concat(fileName));
			File tgt = new File(completePath.concat(fileName));

			if (comments.toString().startsWith("Failure")) {
				comments.append("<p><strong>Next Steps:</strong> File has not been processed succesfully. Its current location is " + newPath.concat(fileName) + ". Be sure to delete the form from the Admin form listing as well as the table before atempting to re-run the import process.</p>");
			} else {
				try {
					FileUtils.renameTo(src, tgt);
				} catch (ObjectNotFoundException e) {
					// it's ok.
				}
			}
			if (comments.toString().equals("")) {
				comments.append(formId.toString());
			}
		}

		return comments;
	}

}
