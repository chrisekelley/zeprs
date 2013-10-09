/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.servlet;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.utils.admin.UpdateDatabase;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.FormChanges;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * Extension of ActionServlet that places forms and other webapp objects into Application memory
 */
public class DynasiteConfigServlet extends ActionServlet {
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

/*    *//**
     * Custom init to modify the XML config files before normal processing by the
     * superclass.
     *
     * @throws ServletException
     *//*
    public void initOLD() throws ServletException {
        log.debug("Initialising DynasiteConfigServlet. ");
        initInternal();
        initOther();
        initServlet();

        loadMemory();
        initModuleConfig();
        log.debug("Finished w/ Servlet init.");
    }*/

    /**
     * <p>Initialize this servlet.  Most of the processing has been factored into
     * support methods so that you can override particular functionality at a
     * fairly granular level.</p>
     *
     * @exception ServletException if we cannot configure ourselves correctly
     */
    public void init() throws ServletException {

        // Wraps the entire initialization in a try/catch to better handle
        // unexpected exceptions and errors to provide better feedback
        // to the developer
        try {
            initInternal();
            initOther();
            initServlet();

            // ZEPRS DynaSiteObjects
            loadMemory();
            try {
                loadUpdates();
            } catch (SQLException e) {
                log.error("ApplicationUpdate SQL error" + e);
            } catch (Exception e2) {
            	e2.printStackTrace();
            }
            // uncomment to load Jmdns
            // loadJmdns();

            getServletContext().setAttribute(Globals.ACTION_SERVLET_KEY, this);
            initModuleConfigFactory();
            // Initialize modules as needed
            ModuleConfig moduleConfig = initModuleConfig("", config);
            initModuleMessageResources(moduleConfig);
            initModuleDataSources(moduleConfig);
            initModulePlugIns(moduleConfig);
            moduleConfig.freeze();

            Enumeration names = getServletConfig().getInitParameterNames();
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                if (!name.startsWith("config/")) {
                    continue;
                }
                String prefix = name.substring(6);
                moduleConfig = initModuleConfig
                    (prefix, getServletConfig().getInitParameter(name));
                initModuleMessageResources(moduleConfig);
                initModuleDataSources(moduleConfig);
                initModulePlugIns(moduleConfig);
                moduleConfig.freeze();
            }

            this.initModulePrefixes(this.getServletContext());

            this.destroyConfigDigester();
        } catch (UnavailableException ex) {
            throw ex;
        } catch (Throwable t) {

            // The follow error message is not retrieved from internal message
            // resources as they may not have been able to have been
            // initialized
            log.error("Unable to initialize Struts ActionServlet due to an "
                + "unexpected exception or error thrown, so marking the "
                + "servlet as unavailable.  Most likely, this is due to an "
                + "incorrect or missing library dependency.", t);
            t.printStackTrace();
            
            throw new UnavailableException(t.getMessage());
        }
    }

/*    private void initModuleConfig()
            throws ServletException {
        // Initialize modules as needed
        // log.debug("Running initModuleConfig to load struts modules correctly");
        getServletContext().setAttribute(Globals.ACTION_SERVLET_KEY, this);
        ModuleConfig moduleConfig = initModuleConfig("", config);
        initModuleMessageResources(moduleConfig);
        initModuleDataSources(moduleConfig);
        initModulePlugIns(moduleConfig);
        moduleConfig.freeze();
        Enumeration names = getServletConfig().getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            if (!name.startsWith("config")) {
                continue;
            }
            String prefix = name.substring(6);
            moduleConfig = initModuleConfig
                    (prefix, getServletConfig().getInitParameter(name));
            initModuleMessageResources(moduleConfig);
            initModuleDataSources(moduleConfig);
            initModulePlugIns(moduleConfig);
            moduleConfig.freeze();
        }
        destroyConfigDigester();
    }*/

    /**
     * Iterates through the forms from the database and makes mods to XML files.
     */
    private void loadMemory() throws ServletException {
        //log.debug("Loading objects in memory from DynasiteConfigServlet.");

        // field enums are used by getForms, so let's do it first.
        DynaSiteObjects.getFieldEnumerationsByField();
        //log.info("DynaSiteObjects.getFieldEnumerationsByField");
        // so are the rules
        DynaSiteObjects.getRules();
        //log.info("DynaSiteObjects.getRules");
        Boolean isDev = true;
        String localDevPath = Constants.getProperties("source",Constants.DEV_PROPERTIES);
        //if (localDevPath.equals(Constants.pathToCatalinaHome + "/webapps/zeprs/WEB-INF/")) {
        String serverPath = Constants.pathToCatalinaHome + File.separator + "webapps" + File.separator + Constants.APP_NAME + File.separator + "WEB-INF" + File.separator;
        if (localDevPath.equals(serverPath)) {
        	isDev = false;
        	log.debug("ZEPRS installed in server config, not for source code dev - dev = false.");
        } else {
            log.debug("ZEPRS installed in source code dev config - dev = true.");
        }
        DynaSiteObjects.setDev(isDev);
        //log.debug("Begin processing form changes");
        FormChanges.processFormChanges(isDev);
        //log.debug("End processing form changes");
        // re-initialise the forms
        DynaSiteObjects.setForms(null);
        DynaSiteObjects.getMasterArchiveIndex();
        DynaSiteObjects.getForms();
        if (DynaSiteObjects.getForms().size() < 1) {
            log.fatal("Forms did not build.");
            throw new ServletException();
        }
        try {
            DynaSiteObjects.getDrugs();
            //log.info("DynaSiteObjects.getDrugs");
            DynaSiteObjects.getClinics();
            //log.info("DynaSiteObjects.getClinics");
            DynaSiteObjects.getClinicMap();
            DynaSiteObjects.getClinicKeyAlphaMap();
            //log.info("DynaSiteObjects.getClinicMap");
            DynaSiteObjects.getSiteList();
            //log.info("DynaSiteObjects.getSiteList");
            DynaSiteObjects.getDistricts();
            DynaSiteObjects.getDistrictsMap();
            //log.info("DynaSiteObjects.getDistricts");
            DynaSiteObjects.getImmunizations();
            //log.info("DynaSiteObjects.getImmunizations");
            DynaSiteObjects.getFlows();
            //log.info("DynaSiteObjects.getFlows");
            DynaSiteObjects.getPartoFields();
            //log.info("DynaSiteObjects.getPartoFields");
            DynaSiteObjects.getPartoTables();
            //log.info("DynaSiteObjects.getPartoTables");
            DynaSiteObjects.getPageItems();
            //log.info("DynaSiteObjects.getPageItems");
            DynaSiteObjects.getFormSections();
            //log.info("DynaSiteObjects.getFormSections");
            DynaSiteObjects.getCollapsingSections();
            //log.info("DynaSiteObjects.getCollapsingSections");
            DynaSiteObjects.getFormDependencies();
            //log.info("DynaSiteObjects.getFormDependencies");
            DynaSiteObjects.getCollapsingDependencies();
            //log.info("DynaSiteObjects.getCollapsingDependencies");
            DynaSiteObjects.getFieldToPageItem();
            // log.info("DynaSiteObjects.getFieldToPageItem");
            DynaSiteObjects.getActivefields();   // used for querybuilder
            DynaSiteObjects.getTasksForFlow();
            DynaSiteObjects.getRulesToForms();
            DynaSiteObjects.getRuleMap();
            DynaSiteObjects.getAllPregnanciesRules();
            DynaSiteObjects.getFormIdToClassNames();
        } catch (Exception e) {
            log.debug("Error loading DynaSiteObjects" + e);
            e.printStackTrace();
        }
        //log.info("Loaded other DynaSiteObjects");
        //log.debug("done with modifications in memory");
        /*  } catch (NullPointerException e) {
              log.fatal("caught null pointer exception when building DOM from database. Likely database connection problem. Message: " + e.getMessage());
              throw new ServletException(e);
          }*/

    }

    /**
     * Loads updates to the application
     * @throws SQLException
     */
    private void loadUpdates() throws SQLException {
        UpdateDatabase.execute();
    }


    /**
     * Loads an XML file into a DOM Document object.
     *
     * @param file The file to load
     * @return The DOM Document represented in the file.
     * @throws MalformedURLException
     * @throws DocumentException
     */
    private Document getDocument(String file)
            throws MalformedURLException, DocumentException {
        Document document;
        //use dom4j to get a handle on the struts-config document
        URL url = getServletContext().getResource(file);
        InputSource is = new InputSource(url.toExternalForm());
        SAXReader reader = new SAXReader();
        reader.setValidation(false);
        reader.setEntityResolver(new LocalEntityResolver());
        document = reader.read(is);
        return document;
    }


    /**
     * support for offline running.
     */
    private class LocalEntityResolver implements EntityResolver {
        public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
            if (systemId.equals("http://jakarta.apache.org/struts/dtds/struts-config_1_2.dtd")) {
                URL url = getServletContext().getResource("/WEB-INF/dtds/struts-config_1_2.dtd");
                return new InputSource(url.toExternalForm());
            } else if (systemId.equals("http://jakarta.apache.org/commons/dtds/validator_1_0.dtd")) {
                URL url = getServletContext().getResource("/WEB-INF/dtds/validation_1_1.dtd");
                return new InputSource(url.toExternalForm());
            }
            return null;
        }
    }


}
