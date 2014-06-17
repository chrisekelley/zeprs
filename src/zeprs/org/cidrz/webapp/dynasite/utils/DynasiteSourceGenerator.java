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

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.hibernate.tool.hbm2ddl.SchemaUpdateTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.FieldEnumeration;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;

import javax.servlet.ServletException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import xdoclet.modules.hibernate.HibernateDocletTask;

import com.sun.tools.javac.Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Extension of ActionServlet that modifies the XML configs based
 * on the Form/Field info stored in the database.
 */

public class DynasiteSourceGenerator {
    private static Log log = LogFactory.getFactory().getInstance(DynasiteSourceGenerator.class);

    public static void main(String[] args) {
        try {
            new DynasiteSourceGenerator().createSourceFiles("record", false, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Iterates through the forms (or a single form if formId is not null) from the database and makes java source files.
     * deploys both to dev and tomcat instance.
     * @param genType
     * @param dev - this is no longer necessary - dev path is set in Constants.
     * @param formId - null if you want to process all forms.
     */
    // go
    public void createSourceFiles(String genType, Boolean dev, Long formId) throws ServletException, PersistenceException, IOException, SQLException, ObjectNotFoundException, IllegalStateException {

        try {
            String pathname = null;
            String deployPathname = null;
            String genPackage = null;
            if (genType.equals("record")) {
            	if (dev) {
            		pathname = Constants.DEV_DYNASITE_FORMS_SOURCE;
            		deployPathname = Constants.DEPLOY_DYNASITE_FORMS_SOURCE;
            	} else {
            		pathname = Constants.DEPLOY_DYNASITE_FORMS_SOURCE;
            		deployPathname = Constants.DEPLOY_DYNASITE_FORMS_SOURCE;
            	}
            	genPackage = Constants.DYNASITE_FORMS_PACKAGE;
            } else if (genType.equals("report")) {
            	if (dev) {
            		pathname = Constants.DEV_DYNASITE_REPORTS_SOURCE;
            		deployPathname = Constants.DEPLOY_DYNASITE_REPORTS_SOURCE;
            	} else {
            		pathname = Constants.DEPLOY_DYNASITE_REPORTS_SOURCE;
            		deployPathname = Constants.DEPLOY_DYNASITE_REPORTS_SOURCE;
            	}
            	genPackage = Constants.DYNASITE_REPORTS_PACKAGE;
            }
            //log.debug("Source generated to: " + pathname);
            if (dev == true) {
            	//log.debug("Source also generated to: " + deployPathname);
            }
            Form form = null;
            if (formId != null) {
            	form = (Form) DynaSiteObjects.getForms().get(formId);
                generateSource(dev, genType, form, genPackage, pathname, deployPathname);
                log.debug("done with generation of source for form id: " + formId);
            } else {
            	Set formSet = DynaSiteObjects.getForms().entrySet();
                for (Iterator iterator = formSet.iterator(); iterator.hasNext();) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    form = (Form) entry.getValue();
                    generateSource(dev, genType, form, genPackage, pathname, deployPathname);
                }
               log.debug("done with generation of source");
            }
        } catch (NullPointerException e) {
            log.fatal("caught null pointer exception when building DOM from database. Likely database connection problem. Message: " + e.getMessage());
            throw new ServletException(e);
        }

    }

    /**
     * Generate source code for dynasite form
     * @param dev - if dev == false, compiles source.
     * @param genType
     * @param form
     * @param genPackage
     * @param pathname
     * @param deployPathname
     */
    public static void generateSource(Boolean dev, String genType, Form form, String genPackage, String pathname, String deployPathname) {
        String classname = null;
        String tableName = null;
        if (genType.equals("record")) {
            classname = StringManipulation.fixClassname(form.getName());
            tableName = form.getName().toLowerCase();
        } else if (genType.equals("report")) {
            classname = StringManipulation.fixClassname(form.getName()) + "Report";
            tableName = form.getName().toLowerCase();
        }

        String outputFilename = classname + ".java";
        // log.info("generating output Java file: " + outputFilename);
        try {
            StringBuffer sBuf = new StringBuffer();
            sBuf.append("package " + genPackage + ";\n\n");
            if (genType.equals("record")) {
                sBuf.append("import " + genPackage + ".*;\n" +
                        "import org.cidrz.project.zeprs.valueobject.EncounterData;\n" +
                        "import org.cidrz.webapp.dynasite.valueobject.Patient;\n" +
                        "import java.sql.Date;\n" +
                        "import java.util.Set;\n" +
                        "import java.sql.Time;\n" +
                        "import java.sql.Timestamp;\n" +
                        "import org.cidrz.webapp.dynasite.valueobject.AuditInfo;\n" +
                        "import java.util.TreeSet;\n\n");
            } else if (genType.equals("report")) {
                sBuf.append("import org.cidrz.project.zeprs.valueobject.EncounterData;\n" +
                        "import org.cidrz.webapp.dynasite.valueobject.Patient;\n" +
                        "import java.sql.Date;\n" +
                        "import java.util.Set;\n" +
                        "import java.sql.Time;\n" +
                        "import java.sql.Timestamp;\n" +
                        "import org.cidrz.webapp.dynasite.valueobject.AuditInfo;\n" +
                        "import java.util.TreeSet;\n\n");
            }

            String headerComment =
                    "/**\n"
                            + " * JavaBean " + StringManipulation.firstCharToUpperCase(classname) + " generated from database;\n"
                            + " * generated by DynasiteSourceGenerator, inspired by XslBeanGenerator by Klaus Berg.\n"
                            + " *\n"
                            + " * @author Chris Kelley\n"
                            + " *         Date: " + DateUtils.getNow() + "\n"
                            + " *         Time: " + DateUtils.getTime() + "\n"
                            + " *         Form Name: " + form.getLabel() + "\n"
                            + " *         Form Id: " + form.getId() + "\n"
                            + " */\n\n";
            sBuf.append(headerComment);

            String hibernateComment =
                    "/**\n" +
                            " * @hibernate.joined-subclass table=\"" + tableName + "\"\n" +
                            " * @hibernate.joined-subclass-key column=\"id\"\n" +
                            " */\n";
            sBuf.append(hibernateComment);

            sBuf.append("public class " + classname + " extends EncounterData" + " {\n" + "\n");

            Iterator dbPageItems = form.getPageItems().iterator();
            FormField formField = null;
            StringBuffer sbufVars = new StringBuffer(2048);
            StringBuffer sbufMethods = new StringBuffer(2048);
            String fieldType;

            while (dbPageItems.hasNext()) {
                PageItem pageItem = (PageItem) dbPageItems.next();
                formField = pageItem.getForm_field();
                Boolean enabled = formField.isEnabled();
                if (! enabled) {
                    fieldType = "private transient";
                } else {
                    if (genType.equals("report")) {
                        fieldType = "private transient";
                    } else {
                        fieldType = "private";
                    }
                }
                //  if (formField.isEnabled()) {
                char fieldTypeChar = Character.toLowerCase(pageItem.getForm_field().getType().charAt(0));
                String type = "";
                if (!formField.getType().equals("Display") && !pageItem.getInputType().equals("multiselect_enum"))
                {
                    String columnName = "";
                    if (formField.getStarSchemaName().equals("")) {
                        columnName = "field" + formField.getId();
                    } else {
                        columnName = formField.getStarSchemaName();
                    }
                    String fieldName = null;
                    if (genType.equals("record")) {
                        fieldName = "field" + formField.getId();
                    } else if (genType.equals("report")) {
                        if (formField.getStarSchemaName().equals("")) {
                            log.debug("Missing Star Schema name for table: " + form.getName() + " field id: " + formField.getId());
                            fieldName = "field" + formField.getId();
                        } else {
                            fieldName = StringManipulation.firstCharToLowerCase(formField.getStarSchemaName());
                        }
                    }

                    if (formField.getType().equals("Enum")) {
                        if (pageItem.getInputType().equals("checkbox")) {
                            fieldTypeChar = 'b';
                        } else if (pageItem.getInputType().equals("checkbox_dwr")) {
                            fieldTypeChar = 'b';
                        } else {
                            fieldTypeChar = 'e';
                        }
                    }
                    switch (fieldTypeChar) {
                        case 'b':
                            type = "Boolean";
                            sbufVars.append(fieldType + " Boolean " + fieldName + ";	//" + columnName + "\n");
                            //processBooleanParameter(sbufMethods, columnName, fieldName);
                            processParameter(type, sbufMethods, columnName, fieldName, enabled);
                            break;
                        case 'd':
                        	if (formField.getType().equals("Datetime")) {
                        		type = "Timestamp";
                                sbufVars.append(fieldType + " Timestamp " + fieldName + ";	//" + columnName + "\n");
                                processParameter(type, sbufMethods, columnName, fieldName, enabled);
                                break;
                        	} else {
                        		type = "Date";
                                sbufVars.append(fieldType + " Date " + fieldName + ";	//" + columnName + "\n");
                                processParameter(type, sbufMethods, columnName, fieldName, enabled);
                                break;
                        	}
                        case 'e':
                            type = "Integer";
                            sbufVars.append(fieldType + " Integer " + fieldName + ";	//" + columnName + "\n");
                            processParameter(type, sbufMethods, columnName, fieldName, enabled);
                            break;
                        case 'i':
                            type = "Integer";
                            sbufVars.append(fieldType + " Integer " + fieldName + ";	//" + columnName + "\n");
                            processParameter(type, sbufMethods, columnName, fieldName, enabled);
                            break;
                        case 'f':
                            type = "Float";
                            sbufVars.append(fieldType + " Float " + fieldName + ";	//" + columnName + "\n");
                            processParameter(type, sbufMethods, columnName, fieldName, enabled);
                            break;
                        case 'l':
                            type = "Long";
                            sbufVars.append(fieldType + " Long " + fieldName + ";	//" + columnName + "\n");
                            processParameter(type, sbufMethods, columnName, fieldName, enabled);
                            break;
                        case 's':
                            // sex
                            type = "Integer";
                            sbufVars.append(fieldType + " Integer " + fieldName + ";	//" + columnName + "\n");
                            processParameter(type, sbufMethods, columnName, fieldName, enabled);
                            break;
                        case 't':
                            if (formField.getType().equals("Text")) {
                                if (pageItem.getInputType().equals("textarea")) {
                                    type = "String";
                                    sbufVars.append(fieldType + " String " + fieldName + ";	//" + columnName + "\n");
                                    processParameterHibType(type, sbufMethods, columnName, fieldName, "text", enabled);
                                } else {
                                    type = "String";
                                    sbufVars.append(fieldType + " String " + fieldName + ";	//" + columnName + "\n");
                                    processParameter(type, sbufMethods, columnName, fieldName, enabled);
                                }
                            } else if (formField.getType().equals("Time")) {
                                type = "Time";
                                sbufVars.append(fieldType + " Time " + fieldName + ";	//" + columnName + "\n");
                                processParameter(type, sbufMethods, columnName, fieldName, enabled);
                            } else if (formField.getType().equals("Timestamp")) {
                            	type = "Timestamp";
                            	sbufVars.append(fieldType + " Timestamp " + fieldName + ";	//" + columnName + "\n");
                            	processParameter(type, sbufMethods, columnName, fieldName, enabled);
                            } else {
                                type = "String";
                                sbufVars.append(fieldType + " String " + fieldName + ";	//" + columnName + "\n");
                                processParameter(type, sbufMethods, columnName, fieldName, enabled);
                            }
                            break;
                        case 'y':
                            if (formField.getType().equals("Year")) {
                                type = "Integer";
                                sbufVars.append(fieldType + " Integer " + fieldName + ";	//" + columnName + "\n");
                                processParameter(type, sbufMethods, columnName, fieldName, enabled);
                            } else if (formField.getType().equals("Yes/No")) {
                                type = "Byte";
                                sbufVars.append(fieldType + " Byte " + fieldName + ";	//" + columnName + "\n");
                                processParameter(type, sbufMethods, columnName, fieldName, enabled);
                                // log.info("Processing " + formField.getType() + " field " + fieldName + " as Byte");
                            } else {
                                type = "String";
                                sbufVars.append(fieldType + " String " + fieldName + ";	//" + columnName + "\n");
                                processParameter(type, sbufMethods, columnName, fieldName, enabled);
                            }
                            break;
                    }
                    sbufMethods.append("\n\n");
                    if (genType.equals("report"))
                    {   // Reports need a duplicate field - it will hold enumerated string.
                        type = "String";
                        fieldName = fieldName + "R";
                        sbufVars.append("private String " + fieldName + ";\n");
                        processParameterNoHib(type, sbufMethods, fieldName);
                    }
                    sbufMethods.append("\n\n");
                }
                //   }
            }
            sBuf.append(sbufVars.toString());
            sBuf.append("\n\n");

            sBuf.append(sbufMethods.toString());
            sBuf.append("}" + "\n");
          //     if (1==2) {
            FileWriter fw = new FileWriter(pathname + outputFilename);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(sBuf.toString());
            writer.close();

            // if we are not in dev mode (app installed on a server), compile the source
            if (dev == false) {
                // log.debug("Classpath: " + System.getProperties());
                String classPath = Constants.DEPLOY_CLASSES_PATH;
                com.sun.tools.javac.Main javac = new com.sun.tools.javac.Main();
                int status = javac.compile(new String[]{
                        "-classpath", classPath,
                        "-d", Constants.DEPLOY_CLASSES_PATH,
                        deployPathname + outputFilename});
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void createMappingfiles() {
        log.debug("Beginning mapfile generation in ant");
        // File buildFile = new File("C:\\Documents and Settings\\ckelley\\IdeaProjects\\zeprs\\build.xml");
        File buildFile = new File(Constants.DYNASITE_BUILD_FILE);
        Project p = new Project();
        File baseDir = new File(buildFile.getParent());
        p.setBaseDir(baseDir);
        log.debug("baseDir: " + p.getBaseDir());
        DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(System.err);
        consoleLogger.setOutputPrintStream(System.out);
        consoleLogger.setMessageOutputLevel(Project.MSG_VERBOSE);
        p.addBuildListener(consoleLogger);

        try {
            p.fireBuildStarted();
            p.init();
            p.setUserProperty("ant.file", buildFile.getAbsolutePath());
            ProjectHelper helper = ProjectHelper.getProjectHelper();
            helper.parse(p, buildFile);
            try {
                p.checkTaskClass(HibernateDocletTask.class);
            } catch (BuildException e) {
                e.printStackTrace();
            }
            try {
                p.checkTaskClass(SchemaUpdateTask.class);
            } catch (BuildException e) {
                e.printStackTrace();
            }
            Vector targets = new Vector();
            // targets.add("hibGenXdoclet2");
            // targets.add("schemaupdate");
            // p.executeTargets(targets);
            log.debug("buildpath: " + buildFile.getAbsolutePath());
            log.debug("parent: " + buildFile.getParent());
            p.executeTarget("hibGenDynasite");
            // p.executeTarget("schemaupdateDynasite");
            // p.executeTarget("compile-zeprs");
            p.fireBuildFinished(null);
        } catch (BuildException e) {
            p.fireBuildFinished(e);
        }
        // log.debug("Running schemaupdate");
        // schemaUpdate();
    }

    public void schemaUpdate() {
        log.debug("Beginning schema update in ant");
        File buildFile = new File("C:\\source\\build.xml");
        Project p = new Project();

        DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(System.err);
        consoleLogger.setOutputPrintStream(System.out);
        consoleLogger.setMessageOutputLevel(Project.MSG_VERBOSE);
        p.addBuildListener(consoleLogger);

        try {
            p.fireBuildStarted();
            p.init();
            p.setUserProperty("ant.file", buildFile.getAbsolutePath());
            ProjectHelper helper = ProjectHelper.getProjectHelper();
            helper.parse(p, buildFile);
            try {
                p.checkTaskClass(HibernateDocletTask.class);
            } catch (BuildException e) {
                e.printStackTrace();
            }
            try {
                p.checkTaskClass(SchemaUpdateTask.class);
            } catch (BuildException e) {
                e.printStackTrace();
            }
            Vector targets = new Vector();
           // targets.add("hibGenXdoclet2");
            targets.add("schemaupdate");
            p.executeTargets(targets);;
            // p.executeTarget("hibGenXdoclet2");
            // p.executeTarget("schemaupdate");
            // p.executeTarget("compile-zeprs");
            p.fireBuildFinished(null);
        } catch (BuildException e) {
            p.fireBuildFinished(e);
        }
    }

    public void updateSchema() {
        /**
         * Loads the Hibernate configuration information, sets up
         * the database and the Hibernate session factory.
         */

        System.out.println("initialization");

        try {
            Configuration myConfiguration = new Configuration().configure();

            // Load the *.hbm.xml files as set in the
            // config, and set the dialect.
            new SchemaUpdate(myConfiguration)
                    .execute(true, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add getters/setters
     * @param type
     * @param sbufMethods
     * @param columnName
     * @param fieldName
     * @param enabled
     */
    private static void processParameter(String type, StringBuffer sbufMethods, String columnName, String fieldName, Boolean enabled) {
        if (enabled) {
            String methodFieldName = StringManipulation.firstCharToUpperCase(fieldName);
            String getter = "";
            getter =
                    " /**\n" +
                            "  * @return\n" +
                            "  * @hibernate.property column=\"" + columnName + "\"\n" +
                            "  */\n"
                            + "    public " + type + " get" + methodFieldName + "() {\n"
                            + "        return this." + fieldName + ";\n    }\n\n"
                            + "    public void set" + methodFieldName + "(" + type + " " + fieldName + ") {\n"
                            + "        this." + fieldName + " = " + fieldName + ";\n    }\n\n";
            sbufMethods.append(getter);
        }

    }

    /**
     * Useful when you need to specify data type
     * @param type
     * @param sbufMethods
     * @param columnName
     * @param fieldName
     * @param hibType
     * @param enabled
     */
    private static void processParameterHibType(String type, StringBuffer sbufMethods, String columnName, String fieldName, String hibType, Boolean enabled) {
        if (enabled) {
            String methodFieldName = StringManipulation.firstCharToUpperCase(fieldName);
            String getter = "";
            getter =
                    " /**\n" +
                            "  * @return\n" +
                            "  * @hibernate.property column=\"" + columnName + "\" type=\"" + hibType + "\"\n" +
                            "  */\n"
                            + "    public " + type + " get" + methodFieldName + "() {\n"
                            + "        return this." + fieldName + ";\n    }\n\n"
                            + "    public void set" + methodFieldName + "(" + type + " " + fieldName + ") {\n"
                            + "        this." + fieldName + " = " + fieldName + ";\n    }\n\n";
            sbufMethods.append(getter);
        }

    }

    private static void processParameterNoHib(String type, StringBuffer sbufMethods, String fieldName) {
        String methodFieldName = StringManipulation.firstCharToUpperCase(fieldName);
        String getter = "";
        getter =
                         "    public " + type + " get" + methodFieldName + "() {\n"
                        + "        return this." + fieldName + ";\n    }\n\n"
                        + "    public void set" + methodFieldName + "(" + type + " " + fieldName + ") {\n"
                        + "        this." + fieldName + " = " + fieldName + ";\n    }\n\n";
        sbufMethods.append(getter);
    }

    /**
     * Simple script to change data types of textarea's from varchar to text.
     * This is no longer needed, but serves as example
     */
    public void createTextSQL() {
        Form form = null;
        Set formSet = DynaSiteObjects.getForms().entrySet();
        String fileName = null;
        fileName = "C:\\source\\zeprs\\src\\zeprs\\resources\\textUpdate.sql";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            for (Iterator iterator = formSet.iterator(); iterator.hasNext();) {
                Map.Entry entry = (Map.Entry) iterator.next();
                form = (Form) entry.getValue();

                Iterator dbPageItems = form.getPageItems().iterator();
                FormField formField = null;
                while (dbPageItems.hasNext()) {
                    PageItem pageItem = (PageItem) dbPageItems.next();
                    formField = pageItem.getForm_field();
                    if (formField.isEnabled()) {
                        if (pageItem.getInputType().equals("textarea")) {
                            try {
                                writer.write("ALTER TABLE " + form.getName() + " MODIFY " + formField.getStarSchemaName() +
                                        " TEXT;\n");
                                // ALTER TABLE medsurghist MODIFY comments_1249 TEXT;
                            } catch (IOException e) {
                                log.error(e);
                            }
                        }

                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            log.error(e);
        }

    }
    
    /**
     * Initiates processing and creation of Locale Resource properties file(s).
     * @param dev
     * @param formId - null if you want to process all forms.
     * @return
     * @throws ObjectNotFoundException
     */
    public static String createLocaleResources(Boolean dev, Long formId) throws ObjectNotFoundException {
    	String message = null;
    	String pathname = null;
    	String deployPathname = null;
        if (dev == true) {
        	pathname = Constants.DEV_XML_PATH;
        	deployPathname = Constants.DYNASITE_XML_PATH;
        } else {
        	pathname = Constants.DYNASITE_XML_PATH;
        }

        Form form = null;
        if (formId != null) {
        	Connection conn = null;
        	//form = (Form) DynaSiteObjects.getForms().get(formId);
        	try {
            	conn = DatabaseUtils.getAdminConnection();
        		form = FormDisplayDAO.getFormGraphDb(conn, formId);
        		DynaSiteObjects.getForms().put(formId, form);
        		/*createLocaleResourceFile(dev, pathname, deployPathname, form);
                log.debug("done with generation of Locale Resource File for form id: " + formId);*/
            } catch (Exception e) {
                log.error(e);
            } finally {
    			try {
					conn.close();
				} catch (SQLException e) {
					log.debug(e);
				}
    		}
        	if (form != null) {
            	createLocaleResourceFile(dev, pathname, deployPathname, form);
                log.debug("done with generation of Locale Resource File for form id: " + formId);
        	} else {
        		message = "DynaSiteObjects.getForms() does not return a form for formId: " + formId + " Adding form to DynaSiteObjects";
                log.debug(message);
                try {
                	conn = DatabaseUtils.getAdminConnection();
            		form = FormDisplayDAO.getFormGraphDb(conn, formId);
            		DynaSiteObjects.getForms().put(formId, form);
            		createLocaleResourceFile(dev, pathname, deployPathname, form);
                    log.debug("done with generation of Locale Resource File for form id: " + formId);
                } catch (Exception e) {
                    log.error(e);
                } finally {
        			try {
						conn.close();
					} catch (SQLException e) {
						log.debug(e);
					}
        		}
                //throw new ObjectNotFoundException(message);
        	}
        } else {
        	Set formSet = DynaSiteObjects.getForms().entrySet();
            for (Iterator iterator = formSet.iterator(); iterator.hasNext();) {
                Map.Entry entry = (Map.Entry) iterator.next();
                form = (Form) entry.getValue();
            	createLocaleResourceFile(dev, pathname, deployPathname, form);
            }
           log.debug("done with generation of source");
        }
		return message;
    }

	/**
	 * Creates Locale Resource properties file
	 * @param dev
	 * @param pathname
	 * @param deployPathname
	 * @param form
	 */
	public static void createLocaleResourceFile(Boolean dev, String pathname, String deployPathname, Form form) {
		Iterator dbPageItems = form.getPageItems().iterator();
        FormField formField = null;
        Properties prop = new Properties();
		prop.setProperty(form.getClassname() + ".label", form.getLabel());
        while (dbPageItems.hasNext()) {
            PageItem pageItem = (PageItem) dbPageItems.next();
            formField = pageItem.getForm_field();
            if (formField.isEnabled()) {
            	prop.setProperty(form.getClassname() + "." + formField.getIdentifier(), formField.getLabel());
            }
            if (formField.getEnumerations() != null) {
            	Set<FieldEnumeration> fieldEnumerations = formField.getEnumerations();
            	for (FieldEnumeration fieldEnumeration : fieldEnumerations) {
            		prop.setProperty(form.getClassname() + "." + formField.getIdentifier() + "_" + fieldEnumeration.getId(), fieldEnumeration.getEnumeration());
				}
            }
            if (pageItem.getInputType().equals("dropdown-add-one")) {
        		String classNameString = StringManipulation.fixClassname(pageItem.getDropdownTable());
                Long inlineFormId = (Long) DynaSiteObjects.getFormNameMap().get(classNameString);
                if (inlineFormId != null) {
                	Form inlineForm = ((Form) DynaSiteObjects.getForms().get(new Long(inlineFormId)));
                    // Create a list of fieldnames for inline forms.
                    ArrayList<String> inlineFields = new ArrayList<String>();
                    for (Iterator iterator2 = inlineForm.getPageItems().iterator(); iterator2.hasNext();) {
                    	PageItem pageItem2 = (PageItem) iterator2.next();
                    	// filter out disabled fields and table-being tags (display-tbl-begin)
                        if (pageItem2.getForm_field().isEnabled() == true && !pageItem2.getInputType().startsWith("display")) {
                        	prop.setProperty(form.getClassname() + "." + pageItem2.getForm_field().getIdentifier(), pageItem2.getForm_field().getLabel());
                        }
                    }
                }
        	}
        }

        if (form.getLocale() == null) {
            String propertiesFilename = form.getClassname() +  ".properties";

            FileOutputStream fos = null;
    		try {
    			fos = new FileOutputStream(pathname + propertiesFilename);
    			prop.store(fos, null);
    		} catch (IOException e) {
    			log.error("Error: ", e);
    		} finally {
    			try {
    				fos.close();
    			} catch (IOException e) {
    				log.error("Error: ", e);
    			}
    		}

            // copy to tomcat as well if in dev mode
            if (dev) {
            	try {
    				FileUtils.copyQuick(pathname + propertiesFilename, deployPathname + propertiesFilename);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
            }

        } else {
        	String outputFilename = form.getClassname() + "_" + form.getLocale() + ".properties";
            String propertiesFilename = form.getClassname() +  ".properties";

            FileOutputStream fos = null;
    		try {
    			fos = new FileOutputStream(pathname + outputFilename);
    			prop.store(fos, null);
    		} catch (IOException e) {
    			log.error("Error: ", e);
    		} finally {
    			try {
    				fos.close();
    			} catch (IOException e) {
    				log.error("Error: ", e);
    			}
    		}

            // save the default properties file. This is used by browser set to a locale that the system does not have translations for.
            try {
    			FileUtils.copyQuick(pathname + outputFilename, pathname + propertiesFilename);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}

            // copy to tomcat as well if in dev mode
            if (dev) {
            	try {
    				FileUtils.copyQuick(pathname + outputFilename, deployPathname + outputFilename);
    				FileUtils.copyQuick(pathname + outputFilename, deployPathname + propertiesFilename);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
            }

        }
	}
}
