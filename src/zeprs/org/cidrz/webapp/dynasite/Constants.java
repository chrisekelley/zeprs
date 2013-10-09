/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Settings for ZEPRS app.
 * Edit the file at Constants.APP_PROPERTIES and Constants.DEV_PROPERTIES for your local installation.
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Jun 14, 2005
 *         Time: 11:17:13 AM
 */
public class Constants {

	/**
     * Commons Logging instance.
     */

    public static Log log = LogFactory.getFactory().getInstance(Constants.class);

    public static final String pathToCatalinaHome = System.getProperty("catalina.home");
    public static final String pathSep = File.separator;

    public static final String LOCAL_DEV_PATH = getDevProperties("source",Constants.DEV_PROPERTIES);
    public static final String APP_NAME = getProperties("app.name",Constants.APP_PROPERTIES);
    public static final String APP_TEMPLATEDIR = getProperties("app.templateDir",Constants.APP_PROPERTIES);
    public static final String APP_TITLE = getProperties("app.title",Constants.APP_PROPERTIES);
    public static final String APP_PORT = getProperties("app.port",Constants.APP_PROPERTIES);
    public static final String DATABASE_TYPE = getProperties("database.type",Constants.APP_PROPERTIES);
    public static final String DATABASE_NAME = getProperties("database.name",Constants.APP_PROPERTIES);
    public static final String USERINFO_TABLE = getProperties("userinfo.table",Constants.APP_PROPERTIES);
    public static final String USERINFO_USERNAME = getProperties("userinfo.username",Constants.APP_PROPERTIES);
    public static final String INSTALL_DIR = getProperties("install",Constants.DEV_PROPERTIES);
    public static final String PATIENT_RECORD_OUTPUT = getProperties("patient.record.output",Constants.APP_PROPERTIES);
    public static final String BACKUP_DIR = INSTALL_DIR + "backup" + pathSep;
    public static final String BACKUP_TEMP_DIR = INSTALL_DIR + "backup" + pathSep + "temp" + pathSep;
    public static final String POOL_NAME = "zeprs";
    public static final String SQL_CREATE = "SQL_CREATE";
    public static final String SQL_CREATEBRIDGE = "SQL_CREATEBRIDGE";
    public static final String SQL_RETRIEVE = "SQL_RETRIEVE";
    public static final String SQL_GENERATED_PROPERTIES = "resources/generatedSQL-" + DATABASE_TYPE + ".properties";
    public static final String SQL_DEMO_PROPERTIES = "resources/generatedSQL.properties";
    public static final String SQL_PARTO_PROPERTIES = "resources/partographSQL.properties";
    public static final String SQL_PATIENT_PROPERTIES = "resources/patientSQL.properties";
    public static final String DEV_PROPERTIES = "resources/dev.properties";
    public static final String APP_PROPERTIES = "resources/application.properties";
    public static final String SQL_DEV_PROPERTIES = "resources/dev.properties";
    public static final String KEY_ZEPRS_LIST = "KEY_ZEPRS_LIST";
    // public static final String SOURCE_PATH = LOCAL_DEV_PATH + "C:\\source\\zeprs\\src\\";
    // public static final String FORM_XML_PATH = SOURCE_PATH + "zeprs\\resources\\xml\\forms\\";
    //public static final String FORM_XML_PATH = pathToCatalinaHome + "/webapps/zeprs/WEB-INF/classes/resources/xml/forms/";
    public static final String DYNASITE_SOURCE_PATH = pathToCatalinaHome + "\\webapps\\zeprs\\WEB-INF\\src\\";
    public static final String DEV_SOURCE_PATH = LOCAL_DEV_PATH + "src\\zeprs\\";
    public static final String DYNASITE_RESOURCES_PATH = pathToCatalinaHome + "/webapps/zeprs/WEB-INF/classes/resources/";
    public static final String DEV_RESOURCES_PATH = LOCAL_DEV_PATH + "src\\zeprs\\resources\\";
    public static final String DEV_XML_PATH = DEV_SOURCE_PATH + "resources\\xml\\forms\\";
    public static final String DYNASITE_XML_PATH = pathToCatalinaHome + "/webapps/zeprs/WEB-INF/classes/resources/xml/forms/";
    public static final String PARTO_XML_PATH = pathToCatalinaHome + "/webapps/zeprs/WEB-INF/classes/org/cidrz/project/zeprs/valueobject/partograph/";
    public static final String REPORTS_XML_PATH = pathToCatalinaHome + "/webapps/zeprs/data/";
    public static final String REPORTS_XSL_PATH = pathToCatalinaHome + "/webapps/zeprs/xsl/";
    public static final String REPORTS_LOCAL_XML_PATH = LOCAL_DEV_PATH + "web\\zeprs\\data\\";
    public static final String LOCAL_CLASSES_PATH = LOCAL_DEV_PATH + "web\\zeprs\\WEB-INF\\classes";
    public static final String DEPLOY_CLASSES_PATH = Constants.pathToCatalinaHome + "\\webapps\\zeprs\\WEB-INF\\classes\\";
    public static final String DEPLOY_DYNASITE_PATH = DEPLOY_CLASSES_PATH + "org\\cidrz\\project\\zeprs\\valueobject\\gen\\";
    public static final String DEPLOY_DYNASITE_REPORTS_PATH = DEPLOY_CLASSES_PATH + "org\\cidrz\\project\\zeprs\\valueobject\\report\\gen\\";
    public static final String WEBINF_PATH = Constants.pathToCatalinaHome + "/webapps/zeprs/WEB-INF/";
    public static final String ERROR404_PATH  = Constants.pathToCatalinaHome  + pathSep + "webapps"  + pathSep + "zeprs" + pathSep+ "pages" + pathSep+ "errors" + pathSep+ "404.jsp";
    public static final String DYNASITE_WEBINF_PATH = Constants.pathToCatalinaHome + "/webapps/zeprs/WEB-INF/";
    public static final String DEV_WEBINF_PATH = LOCAL_DEV_PATH + "web\\zeprs\\WEB-INF\\";
    public static final String ARCHIVE_PATH = pathToCatalinaHome  + pathSep + "webapps"  + pathSep + "archive" + pathSep;
    public static final String ARCHIVE_PATH_SERVERS = ARCHIVE_PATH + "servers" + pathSep;
    public static final String ARCHIVE_PATH_LOGS = pathToCatalinaHome + "/webapps/archive/logs";
    public static final String ARCHIVE_PATH_LOGS_APPUPDATES = pathToCatalinaHome + "/webapps/archive/appupdates";
    public static final String ARCHIVE_PATH_FORM = ARCHIVE_PATH + "forms" + pathSep;
    public static final String ARCHIVE_PATH_FORM_UPDATES = ARCHIVE_PATH_FORM + "updates" + pathSep;
    public static final String ARCHIVE_PATH_FORM_DELETIONS = ARCHIVE_PATH_FORM + "deletions" + pathSep;
    public static final String ARCHIVE_PATH_FORMS_IMPORT = ARCHIVE_PATH_FORM + "import" + pathSep;
    public static final String ARCHIVE_PATH_FORMS_IMPORT_NEW = ARCHIVE_PATH_FORMS_IMPORT + "new" + pathSep;
    public static final String ARCHIVE_PATH_FORMS_IMPORT_COMPLETE = ARCHIVE_PATH_FORMS_IMPORT + "complete" + pathSep;
    public static final String ARCHIVE_PATH_FORMS_IMPORT_ERRORS = ARCHIVE_PATH_FORMS_IMPORT + "errors" + pathSep;
    public static final String CACHE_PATH = pathToCatalinaHome + "/webapps/archive/cache/";
    public static final String PATIENTS_DELETED_PATH = pathToCatalinaHome + "/webapps/archive/patients_deleted/";
    public static final String PATIENTS_RESTORED_PATH = pathToCatalinaHome + "/webapps/archive/patients_restored/";
    public static final String PATIENTS_IMPORTED_PATH = ARCHIVE_PATH + "patients_imported" + pathSep;
    // public static final String LOCALHOST_PATH = "http://localhost/archive/";
    //public static final String INETPUB_FILEPATH = "C:\\Inetpub\\wwwroot\\archive";
    public static final String DYNASITE_RECORDS_PATH = Constants.pathToCatalinaHome + "/webapps/zeprs/WEB-INF/records/";
    public static final String DYNASITE_BUILD_FILE = pathToCatalinaHome + "/webapps/zeprs/WEB-INF/build.xml";
    // public static final String TEST_URL =  "http://192.168.0.11:8080";
    // public static final String ZEPRS_URL =  "http://192.168.20.6:8080";
    public static final String DEV_DYNASITE_FORMS_SOURCE = Constants.DEV_SOURCE_PATH + "org\\cidrz\\project\\zeprs\\valueobject\\gen\\";
    public static final String DEPLOY_DYNASITE_FORMS_SOURCE = Constants.DYNASITE_SOURCE_PATH + "org\\cidrz\\project\\zeprs\\valueobject\\gen\\";
    public static final String DEV_DYNASITE_REPORTS_SOURCE = Constants.DEV_SOURCE_PATH + "org\\cidrz\\project\\zeprs\\valueobject\\report\\gen\\";
    public static final String DEPLOY_DYNASITE_REPORTS_SOURCE = Constants.DYNASITE_SOURCE_PATH + "org\\cidrz\\project\\zeprs\\valueobject\\report\\gen\\";
    public static final String DYNASITE_FORMS_PACKAGE = "org.cidrz.project.zeprs.valueobject.gen";
    public static final String DYNASITE_REPORTS_PACKAGE = "org.cidrz.project.zeprs.valueobject.report.gen";
    public static final String WEBAPPS_PATH = pathToCatalinaHome  + pathSep + "webapps"  + pathSep;
    public static final String MASTER_ARCHIVE_PATH = WEBAPPS_PATH + "master_archive" + pathSep;
    public static final String MASTER_ARCHIVE_ZIP_FILENAME = "master_archive.zip";
    public static final String MASTER_ARCHIVE_CHECKSUM_FILENAME = "master_archive_checksum.txt";
    public static final String MASTER_ARCHIVE_AIR_ZIP_FILENAME = "master_archive_air.zip";
    public static final String MASTER_ARCHIVE_ZIP_PATH = ARCHIVE_PATH + MASTER_ARCHIVE_ZIP_FILENAME;
    public static final String MASTER_ARCHIVE_AIR_ZIP = ARCHIVE_PATH + MASTER_ARCHIVE_AIR_ZIP_FILENAME;
    public static final String MASTER_ARCHIVE_CHECKSUM_PATH = ARCHIVE_PATH + MASTER_ARCHIVE_CHECKSUM_FILENAME;
    public static final String MASTER_ARCHIVE_INDEX = ARCHIVE_PATH + "master_archive_index.txt";
    public static final String MASTER_ARCHIVE_INDEX_XML_FILENAME = "master_archive_index.xml";
    public static final String MASTER_ARCHIVE_INDEX_XML = ARCHIVE_PATH + MASTER_ARCHIVE_INDEX_XML_FILENAME;
    public static final String MASTER_ARCHIVE_INDEX_JS_FILENAME = "master_archive_index.js";
    public static final String MASTER_ARCHIVE_INDEX_JS = ARCHIVE_PATH + MASTER_ARCHIVE_INDEX_JS_FILENAME;
    public static final String PATIENT_ID_LIST_FILENAME = "patient_id_list.js";
    public static final String PATIENT_GET_URL = getProperties("patient.get.url",Constants.APP_PROPERTIES);




    /**
     * Fetch properties from the dev.properties file
     * @param property to be fetched.
     * @return
     */
    public static String getProperties(String property, String filename) {
        String result = null;
        try {
            Map queries = null;
			try {
				queries = QueryLoader.instance().load("/" + filename);
			} catch (IllegalArgumentException e) {
				// it's ok
			}
            if (queries != null) {
            	result = (String) queries.get(property);
            } else {
            	if (APP_NAME == null) {
            		log.error("Please initialize the app.name property in resources/application.properties.");
            	}
            	result = pathToCatalinaHome + "/webapps/" + APP_NAME + "/WEB-INF/";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getDevProperties(String property, String filename) {
    	String result = null;
    	try {
    		Map queries = null;
    		try {
    			queries = QueryLoader.instance().load("/" + filename);
    		} catch (IllegalArgumentException e) {
    			// it's ok
    		}
    		if (queries != null) {
    			result = (String) queries.get(property);
    		} else {
    			if (APP_NAME == null) {
    				log.error("Please initialize the app.name property in resources/application.properties.");
    			}
    		}
    		File test = new File(result);
    		boolean exists = test.exists();
    	    if (!exists) {
    			//result = pathToCatalinaHome + "/webapps/" + APP_NAME + "/WEB-INF/";
    	    	result = Constants.pathToCatalinaHome + File.separator + "webapps" + File.separator + Constants.APP_NAME + File.separator + "WEB-INF" + File.separator;
    	    }
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return result;
    }

}
