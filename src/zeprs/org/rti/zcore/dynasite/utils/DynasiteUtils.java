package org.rti.zcore.dynasite.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rti.zcore.ApplicationDefinition;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.FieldEnumeration;
import org.cidrz.webapp.dynasite.valueobject.Flow;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.rti.zcore.FormDomain;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.FormType;
import org.cidrz.webapp.dynasite.valueobject.MenuItem;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.cidrz.webapp.dynasite.valueobject.Site;
import org.cidrz.webapp.dynasite.dao.DistrictDAO;
import org.cidrz.webapp.dynasite.dao.DrugsDAO;
import org.cidrz.webapp.dynasite.dao.FieldEnumerationDAO;
import org.cidrz.webapp.dynasite.dao.FlowDAO;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.rti.zcore.dynasite.dao.FormDomainDAO;
import org.cidrz.webapp.dynasite.dao.FormFieldDAO;
import org.rti.zcore.dynasite.dao.FormImportDAO;
import org.cidrz.webapp.dynasite.dao.FormTypeDAO;
import org.cidrz.webapp.dynasite.dao.RuleDefinitionDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.valueobject.Publisher;
import org.rti.zcore.sync.utils.PubSubUtils;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.FileUtils;
import org.cidrz.webapp.dynasite.utils.StringManipulation;
import org.cidrz.webapp.dynasite.utils.XmlUtils;

public class DynasiteUtils {

    public static Log log = LogFactory.getFactory().getInstance(DynasiteUtils.class);


	/**
	 * Adds the new site to DynaSiteObjects, outputs site and clinic XML, creates new directories in archive filesystem.
	 * @param site
	 * @param conn
	 * @param siteId
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void refreshSites(Site site, Connection conn, Long siteId) throws SQLException, ServletException,IOException {
		DynaSiteObjects.getClinicMap().put(siteId, site);
		DynaSiteObjects.getClinics().add(site);
		DynaSiteObjects.getSiteList().add(site);
		// re-render site-related xml files.
		refreshSites(conn);
	}

	/**
	 * Outputs site and clinic XML, creates new directories in archive filesystem.
	 * @param conn
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void refreshSites(Connection conn) throws SQLException, ServletException, IOException {
		String path = null;
		String path2 = null;
		Boolean dev = DynaSiteObjects.getDev();
		if (dev == true) {
			path = Constants.DEV_XML_PATH;
			path2 = Constants.DYNASITE_XML_PATH;
		} else {
			path = Constants.DYNASITE_XML_PATH;
		}
		StringBuffer sbuf = new StringBuffer();
		XmlUtils.outputClinics(dev, conn, path, path2, sbuf);
		XmlUtils.outputSites(dev, conn, path, path2, sbuf);
		//FileUtils.createArchiveFilesystem();
		// changed for ZEPRS
		FileUtils.createArchive();
	}

	/**
	 * Adds the new flow to DynaSiteObjects, outputs flow XML
	 * @param flow
	 * @param conn
	 * @param flowId
	 * @param addFlow TODO
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void refreshFlows(Flow flow, Connection conn, Long flowId, boolean addFlow) throws SQLException, ServletException, IOException {
		if (addFlow) {
			DynaSiteObjects.getFlows().add(flow);
			DynaSiteObjects.getFlowMap().put(flowId, flow);
		} else {
			DynaSiteObjects.getFlows().remove(flow);
			DynaSiteObjects.getFlowMap().remove(flowId);
		}

		// re-render site-related xml files.
		String path = null;
		String path2 = null;
		Boolean dev = DynaSiteObjects.getDev();
		if (dev == true) {
			path = Constants.DEV_XML_PATH;
			path2 = Constants.DYNASITE_XML_PATH;
		} else {
			path = Constants.DYNASITE_XML_PATH;
		}
		StringBuffer sbuf = new StringBuffer();
		XmlUtils.outputFlow(dev, conn, path, path2, sbuf);
	}

	/**
	 * Adds the new formType to DynaSiteObjects, outputs formType XML
	 * @param formType
	 * @param conn
	 * @param formTypeId
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void refreshFormTypes(FormType formType, Connection conn, Long formTypeId) throws SQLException, ServletException, IOException {
		DynaSiteObjects.getFormTypes().add(formType);
		// re-render FormType-related xml files.
		String path = null;
		String path2 = null;
		Boolean dev = DynaSiteObjects.getDev();
		if (dev == true) {
			path = Constants.DEV_XML_PATH;
			path2 = Constants.DYNASITE_XML_PATH;
		} else {
			path = Constants.DYNASITE_XML_PATH;
		}
		StringBuffer sbuf = new StringBuffer();
		XmlUtils.outputFormTypes(dev, conn, path, path2, sbuf);
	}

	/**
	 * Creates an ApplicationDefinition and saves to file.
	 * @param localList - list of locales
	 * @param defaultLocale TODO
	 * @return
	 */
//	public static ApplicationDefinition generateApplicationDefinition(ArrayList<String> localList, String defaultLocale){
//		String message = null;
//    	String pathName = null;
//    	String deployPathname = null;
//
//    	Boolean dev = DynaSiteObjects.getDev();
//
//        if (dev == true) {
//        	pathName = Constants.DEV_XML_PATH;
//        	deployPathname = Constants.DYNASITE_XML_PATH;
//        } else {
//        	pathName = Constants.DYNASITE_XML_PATH;
//        }
//
//        UUID uuid = UUID.randomUUID();
//		String uuidStr = uuid.toString();
//		String url = Constants.APPLICATION_DEFINITION_URL;
//		String dynaSitePath = Constants.DYNASITE_XML_PATH;
//		Boolean problemListEnabled= DynaSiteObjects.getProblemListEnabled();
//
//		String dynasiteOutputFormatExtension = Constants.DYNASITE_FORMAT_EXTENSION;
//
//        ApplicationDefinition applicationDefinition = new ApplicationDefinition(null, uuidStr, Constants.APP_NAME, Constants.APP_TITLE, url, "Forms"  + dynasiteOutputFormatExtension, "Flows"  + dynasiteOutputFormatExtension, localList, defaultLocale, problemListEnabled, null);
//        DynaSiteObjects.setApplicationDefinition(applicationDefinition);
//
//        try {
//			XmlUtils.saveJson(applicationDefinition, pathName + Constants.APPLICATION_DEFINITION_FILENAME, null);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// copy to tomcat as well if in dev mode
//        if (dev) {
//        	try {
//				FileUtils.copyQuick(pathName + Constants.APPLICATION_DEFINITION_FILENAME, deployPathname + Constants.APPLICATION_DEFINITION_FILENAME);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//        }
//		return applicationDefinition;
//	}

	/**
	 * Saves the list of menuItems to the filesystem using the list in DynaSiteObjects.
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
//	public static void refreshMenuItemList() throws IOException, ServletException, SQLException {
//		// Get list of these menuItems
//		ArrayList<MenuItem> menuItemList = DynaSiteObjects.getMenuItemList();
//		Boolean dev = DynaSiteObjects.getDev();
//		String pathName = null;
//		String deployPathname = null;
//		if (dev == true) {
//			pathName = Constants.DEV_XML_PATH;
//			deployPathname = Constants.DYNASITE_XML_PATH;
//		} else {
//			pathName = Constants.DYNASITE_XML_PATH;
//		}
//		XmlUtils.saveJson(menuItemList, pathName + Constants.MENUITEM_LIST_FILENAME, null);
//		// copy to tomcat as well if in dev mode
//		if (dev) {
//			try {
//				FileUtils.copyQuick(pathName + Constants.MENUITEM_LIST_FILENAME, deployPathname + Constants.MENUITEM_LIST_FILENAME);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		// DynaSiteObjects.setMenuItemList(menuItemList);
//	}

	/**
	 * Queries the database metadata for a list of tables
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String> getTables (Connection conn) throws SQLException {
		ArrayList<String> list = new ArrayList<String>();
		DatabaseMetaData metadata = conn.getMetaData();
		String[] types = {"TABLE"};
		ResultSet tables = metadata.getTables(null, null, "%", types);
		while (tables.next())  {
			list.add(tables.getString("TABLE_NAME"));
		}
		return list;
	}

	/**
	 * Renders many of the files used by Dynasite - Fields, Rules, Forms, etc.
	 * @param dev - if dev output to both DEV_XML_PATH amd DYNASITE_XML_PATH
	 * @return
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	public static String outputDynaSiteConfig(Boolean dev) throws SQLException, ServletException, IOException {
		String message = null;
		Connection conn = null;
		try {
			conn = DatabaseUtils.getAdminConnection();
			String dynasiteOutputFormat = Constants.DYNASITE_FORMAT;
			String dynasiteOutputFormatExtension = Constants.DYNASITE_FORMAT_EXTENSION;
			String path = null;
			String path2 = null;
			if (dev == true) {
				path = Constants.DEV_XML_PATH;
				path2 = Constants.DYNASITE_XML_PATH;
			} else {
				path = Constants.DYNASITE_XML_PATH;
			}
			StringBuffer sbuf = new StringBuffer();
			sbuf.append("Running outputDynaSiteConfig:\n");
			//log.debug("Running outputDynaSiteConfig:\n");
			sbuf.append("Path: " + path + "\n");
			//log.debug("Path: " + path + "\n");
			sbuf.append("Dynasite Output Format: " + dynasiteOutputFormat + "\n");
			List fieldEnumList = FieldEnumerationDAO.getAll(conn);
			if (dev == true) {
				XmlUtils.saveAndCopy(fieldEnumList, path + "Fields" + dynasiteOutputFormatExtension, path2 + "Fields" + dynasiteOutputFormatExtension);
				sbuf.append("Copied to Path: " + path2 + "\n");
			} else {
				XmlUtils.save(fieldEnumList, path + "Fields" + dynasiteOutputFormatExtension);
			}
			sbuf.append(" - Fields output to xml.\n");
			//log.debug(" - Fields output to xml.\n");
			HashMap rules = RuleDefinitionDAO.getAll(conn);
			if (dev == true) {
				XmlUtils.saveAndCopy(rules, path + "Rules" + dynasiteOutputFormatExtension, path2 + "Rules" + dynasiteOutputFormatExtension);
			} else {
				XmlUtils.save(rules, path + "Rules" + dynasiteOutputFormatExtension);
			}
			sbuf.append(" - Rules output to xml.\n");
			//log.debug(" - Rules output to xml.\n");
			List<Form> formList = FormDisplayDAO.getEnabledFormList(conn);
			for (Form form : formList) {
				// get the flow
				Flow flow = null;
				try {
					flow = FlowDAO.getOne(conn, form.getFlowId());
				} catch (ObjectNotFoundException e) {
					log.debug(e);
				}
				form.setFlow(flow);
				// get the formType
				FormType formType = null;
				try {
					formType = FormTypeDAO.getOne(conn, form.getFormTypeId());
				} catch (ObjectNotFoundException e) {
					// TODO Auto-generated catch block
					log.debug(e);
				}
				form.setFormType(formType);
				// get the formType
				FormDomain formDomain = null;
				try {
					formDomain = FormDomainDAO.getOne(conn, form.getFormDomainId());
				} catch (ObjectNotFoundException e) {
					// TODO Auto-generated catch block
					log.debug(e);
				}
				form.setFormDomain(formDomain);
			}
			if (dev == true) {
				XmlUtils.saveAndCopy(formList, path + "Forms" + dynasiteOutputFormatExtension, path2 + "Forms" + dynasiteOutputFormatExtension);
			} else {
				XmlUtils.save(formList, path + "Forms" + dynasiteOutputFormatExtension);
			}
			sbuf.append(" - Forms output to xml.\n");
			//log.debug(" - Forms output to xml.\n");
			List activeFields = FormFieldDAO.getAllEnabledFields(conn);
			if (dev == true) {
				XmlUtils.saveAndCopy(activeFields, path + "ActiveFields" + dynasiteOutputFormatExtension, path2 + "ActiveFields" + dynasiteOutputFormatExtension);
			} else {
				XmlUtils.save(activeFields, path + "ActiveFields" + dynasiteOutputFormatExtension);
			}
			sbuf.append(" - ActiveFields output to xml.\n");
			List drugs = DrugsDAO.getAll(conn);
			if (dev == true) {
				XmlUtils.saveAndCopy(drugs, path + "Drugs" + dynasiteOutputFormatExtension, path2 + "Drugs" + dynasiteOutputFormatExtension);
			} else {
				XmlUtils.save(drugs, path + "Drugs" + dynasiteOutputFormatExtension);
			}
			sbuf.append(" - Drugs output to xml.\n");
			XmlUtils.outputClinics(dev, conn, path, path2, sbuf);
			sbuf.append(" - Clinics output to xml.\n");
			XmlUtils.outputSites(dev, conn, path, path2, sbuf);
			sbuf.append(" - Sites output to xml.\n");
			//log.debug(" - Sites output to xml.\n");
			List districts = DistrictDAO.getAll(conn, "districtId");
			if (dev == true) {
				XmlUtils.saveAndCopy(districts, path + "Districts" + dynasiteOutputFormatExtension, path2 + "Districts" + dynasiteOutputFormatExtension);
			} else {
				XmlUtils.save(districts, path + "Districts" + dynasiteOutputFormatExtension);
			}
			sbuf.append(" - Districts output to xml.\n");
			XmlUtils.outputFlow(dev, conn, path, path2, sbuf);
			sbuf.append(" - Flows output to xml.\n");
			XmlUtils.outputFormTypes(dev, conn, path, path2, sbuf);
			sbuf.append(" - FormTypes output to xml.\n");
			//log.debug(" - FormTypes output to xml.\n");
			sbuf.append("Done!\n");
			//log.debug("Done!\n");
			message = sbuf.toString();
		} catch (ServletException e) {
			log.error(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return message;
	}

	/**
	     * Outputs forms to XML, which is used to render forms.
	     * @param dev - if dev output to both DEV_XML_PATH amd DYNASITE_XML_PATH
	     * @param conn
	     * @param formId - used when rendering a single form; otherwise render all forms.
	     * @return output messages from rendering process
	     * @throws SQLException
	     * @throws ServletException
	     * @throws IOException
	     * @throws ObjectNotFoundException
	     */
	    public static String outputForms(Boolean dev, Connection conn, Long formId) throws SQLException, ServletException, IOException, org.cidrz.webapp.dynasite.exception.ObjectNotFoundException {
	        String path = null;
	        List<Form> forms = null;
	        if (dev == true) {
	            path = Constants.DEV_XML_PATH;
	        } else {
	            path = Constants.DYNASITE_XML_PATH;
	        }
	        String dynasiteOutputFormat = Constants.DYNASITE_FORMAT;
	        String dynasiteOutputFormatExtension = Constants.DYNASITE_FORMAT_EXTENSION;
	        StringBuffer sbuf = new StringBuffer();
	        sbuf.append("Dynasite Forms : " + path + "\n");
	        // reset the rules in case rules were edited in the db
	        HashMap rules = RuleDefinitionDAO.getAll(conn);
	        DynaSiteObjects.setRules(rules);
	        // reset the enumerations
	        List fieldEnumList = FieldEnumerationDAO.getAll(conn);
	        HashMap fieldEnumerations = new HashMap();
	        for (int i = 0; i < fieldEnumList.size(); i++) {
	            FieldEnumeration fieldEnum = (FieldEnumeration) fieldEnumList.get(i);
	            fieldEnumerations.put(fieldEnum.getId(), fieldEnum);
	        }
	        DynaSiteObjects.setFieldEnumerations(fieldEnumerations);
	        DynaSiteObjects.setFieldEnumerationsByField(null);
	        DynaSiteObjects.getFieldEnumerationsByField();
	        if (formId != null) {
	        	forms = new ArrayList<Form>();
	        	Form form = new Form();
	        	form.setId(formId);
	        	forms.add(form);
	        } else {
	        	forms = FormDisplayDAO.getAllFormIds(conn);
	        }
	        /*
	         * Bad - this will make  DynaSiteObjects.getForms include only the forms being processed.
	         * log.debug("Reset DynaSiteObjects.getForms\n");
	        DynaSiteObjects.getForms().clear();
	        // re-initialise the forms
		    DynaSiteObjects.setForms(null);
		    //DynaSiteObjects.getForms();
	*/
	        sbuf.append("Forms output to " + dynasiteOutputFormat + ":\n");

	        for (int i = 0; i < forms.size(); i++) {
	            Form simpleForm = (Form) forms.get(i);
	            Form formObj = null;
				try {
					formObj = FormDisplayDAO.getFormGraph(conn, simpleForm.getId());
					String classname = StringManipulation.fixClassname(formObj.getName());
		            DynaSiteObjects.getForms().put(formObj.getId(), formObj);
		            if (dev == true) {
		                path = Constants.DEV_XML_PATH;
		                String path2 = Constants.DYNASITE_XML_PATH;
		                XmlUtils.saveAndCopy(formObj, path + classname + dynasiteOutputFormatExtension, path2 + classname + dynasiteOutputFormatExtension);
		            } else {
		                path = Constants.DYNASITE_XML_PATH;
		                XmlUtils.save(formObj, path + classname + dynasiteOutputFormatExtension);
		            }
		            sbuf.append(" - added " + classname + "\n");
				} catch (ObjectNotFoundException e) {
					log.debug("Form not found - id: " + simpleForm.getId());
				}
	        }
	        sbuf.append("Done!\n");
	        sbuf.append("DynaSiteObjects.getForms size: " + DynaSiteObjects.getForms().size() + "\n");
	        log.debug("DynaSiteObjects.getForms size: " + DynaSiteObjects.getForms().size() + "\n");
	        String message = sbuf.toString();
	        return message;
	    }

	/**
	 * This should not be used for patient records - use the one that takes the vo
	 * @param values
	 * @param userName
	 * @param siteId
	 */
	public static void AddAuditInfo(ArrayList values, String userName, Long siteId) {
	    // last modified
	    values.add(new Timestamp(System.currentTimeMillis()));
	    // created
	    values.add(new Timestamp(System.currentTimeMillis()));
	    // last_modified_by
	    values.add(userName);
	    // created_by
	    values.add(userName);
	    // site_id
	    values.add(siteId);
	}

	/**
	 * @param conn
	 * @param username
	 * @param execute - if null,, do not execute
	 * @param locale
	 * @return list of missing cols
	 * @throws IOException
	 * @throws SQLException
	 * @throws ServletException
	 * @should return ArrayList
	 */
	public static ArrayList<String> verifySchema(Connection connAdmin, Connection connZeprs, String username, String execute, String localeString)
			throws IOException, SQLException, ServletException, Exception {
		//StringBuffer sbuf = new StringBuffer();
		// Hack for ZEPRS
//		Connection connAdmin = conn;
		ArrayList<String> missingCols = new ArrayList<String>();
		String database = Constants.DATABASE_TYPE;
		Publisher publisher = PubSubUtils.getPublisher();
		List<Form> forms = null;
		forms = FormDisplayDAO.getAllFormIds(connAdmin);
		for (int i = 0; i < forms.size(); i++) {
			Form simpleForm = (Form) forms.get(i);
			Form formObj = null;
			try {
				formObj = (Form) DynaSiteObjects.getForms().get(simpleForm.getId());
				String tableName = formObj.getName().toUpperCase();
				if (database.equals("mysql")) {
    				tableName = formObj.getName();
    			}
				String sql = null;
				if (database.equals("derby")) {
					sql = "SELECT systabs.TABLENAME FROM SYS.SYSTABLES systabs " +
					"WHERE systabs.TABLENAME = ?";
				} else if (database.equals("mssql")) {
					sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES systabs WHERE systabs.TABLE_NAME = ?";
				} else if (database.equals("mysql")) {
					sql = "SELECT systabs.TABLE_NAME FROM INFORMATION_SCHEMA.TABLES systabs WHERE systabs.TABLE_NAME = ?";
//					log.error("Must create SQL for MySQL");
				}

				ArrayList values = new ArrayList();
				values.add(tableName);
				String dbTable = (String) DatabaseUtils.getScalar(connAdmin, sql, values);

				// if this table does not exist, import it.
				if (dbTable == null) {
					if (execute != null) {
						String fileName = formObj.getClassname() + ".xml";
						StringBuffer comments = FormImportDAO.saveImportedForm(username, connAdmin, connZeprs, fileName, formObj, localeString, true, publisher);
						if (comments.toString().startsWith("Failure")) {
							//request.setAttribute("exception", comments.toString());
							//return mapping.findForward("error");
							throw new Exception(comments.toString());
						}
					} else {
						missingCols.add("Missing table: " + tableName);
					}
				}

				HashMap<String,String> columnMap = new HashMap<String,String>();
				//populate the table col map
				if (database.equals("derby")) {
					sql = "SELECT syscols.COLUMNNAME FROM SYS.SYSCOLUMNS syscols,SYS.SYSTABLES systabs " +
					"WHERE syscols.REFERENCEID = systabs.TABLEID AND systabs.TABLENAME = ?";
				} else if (database.equals("mssql")) {
					sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?";
				} else if (database.equals("mysql")) {
					sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?";
				}
				values = new ArrayList();
				values.add(tableName);
				ResultSetHandler h = new ArrayListHandler();
		        QueryRunner run = new QueryRunner();
		        List results = (List) run.query(connAdmin, sql, values.toArray(), h);
		        for (int j = 0; j < results.size(); j++) {
		            Object[] keyVal = (Object[]) results.get(j);
		            //map.put(keyVal[1], keyVal[0]);
		            String keyValStrArr = keyVal[0].toString().toUpperCase();
					columnMap.put(keyValStrArr, keyValStrArr);
		        }

				// loop through each form field and see if it exists in the form table.

				Set<PageItem> pageItems = formObj.getPageItems();
				for (PageItem pageItem : pageItems) {
					FormField formField = pageItem.getForm_field();
					if (formField.isEnabled() && !formField.getType().equals("Display")) {
						String columnName = formField.getStarSchemaName().toUpperCase();
						if (database.equals("mysql")) {
							columnName = formField.getStarSchemaName();
	        			}
						if (columnMap.get(columnName.toUpperCase()) == null) {
							missingCols.add(tableName + ":" + columnName);
							if (execute != null) {
								try {
									FormFieldDAO.createColumn(connAdmin, connZeprs, formField, simpleForm.getId(), pageItem);
									columnMap.put(columnName, columnName);
								} catch (Exception e) {
									log.debug("Error while processing " + columnMap + ":" + columnName);
									log.debug(e);
									e.printStackTrace();
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.debug(e);
				e.printStackTrace();
				//throw new Exception(e);
				/*request.setAttribute("exception", e);
				return mapping.findForward("error");*/
			}
		}
		return missingCols;
	}
}
