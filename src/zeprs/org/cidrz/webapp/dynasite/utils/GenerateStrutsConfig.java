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

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.dao.FormDisplayDAO;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.FormField;
import org.cidrz.webapp.dynasite.valueobject.PageItem;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * Extension of ActionServlet that modifies the XML configs based
 * on the Form/Field info stored in the database.
 */


public class GenerateStrutsConfig {
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    public static void main(String[] args) {
        Boolean dev = false;
        try {
            new GenerateStrutsConfig().init(dev);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Custom init to modify the XML config files
     * @param dev
     */
    public void init(Boolean dev) throws ServletException {
        modifyConfigFiles(dev);
    }


    /**
     * Iterates through the forms from the database and makes mods to XML files.
     * If dev, copies foo and bar to tomcat
     * @param dev
     */
    private void modifyConfigFiles(Boolean dev) throws ServletException {

        Document strutsConfigXML = null;
        Document validatorXML = null;
        String strutsPathname = null;
        String validationPathname = null;
        String fooPathname = null;
        String barPathname = null;
        if (dev) {
            strutsPathname = Constants.DEV_WEBINF_PATH + "struts-config.xml";
            validationPathname = Constants.DEV_WEBINF_PATH + "validation.xml";
            fooPathname = Constants.DEV_WEBINF_PATH + "foo.xml";
            barPathname = Constants.DEV_WEBINF_PATH + "bar.xml";
        } else {
            strutsPathname = Constants.DYNASITE_WEBINF_PATH + "struts-config.xml";
            validationPathname = Constants.DYNASITE_WEBINF_PATH + "validation.xml";
            fooPathname = Constants.DYNASITE_WEBINF_PATH + "foo.xml";
            barPathname = Constants.DYNASITE_WEBINF_PATH + "bar.xml";
        }
        try {
            Connection conn = DatabaseUtils.getAdminConnection();
            //strutsConfigXML = getDocument("/WEB-INF/struts-config.xml");
            //validatorXML = getDocument("/WEB-INF/validation.xml");
			strutsConfigXML = getDocument(strutsPathname);
			validatorXML = getDocument(validationPathname);

            log.info("successfully loaded struts-config and validator config documents");
            //log.info("System: "+System.getProperties().toString());
            // log.info("Environment: "+Environment.getProperties().toString());

            Element formBeans;
            Element actionMappings;
            Element formSet;

            formBeans = strutsConfigXML.getRootElement().element("form-beans");
            actionMappings = strutsConfigXML.getRootElement().element("action-mappings");
            formSet = validatorXML.getRootElement().element("formset");

            Element thisFormBean;
            Element thisActionMapping;
            Element thisValidatorForm;

            //clear the cache first, so we get the current database state.
            try {
                List dbForms = new ArrayList();
                List forms = FormDisplayDAO.getAllFormIds(conn);
                for (Iterator iterator = forms.iterator(); iterator.hasNext();) {
                    Form form = (Form) iterator.next();
                    Form wholeForm = FormDisplayDAO.getFormGraph(conn, form.getId());
                    dbForms.add(wholeForm);
                }
                conn.close();
                //log.info("loaded Forms");
                Form form = null;
                for (int i = 0; i < dbForms.size(); i++) {
                    Object newclass = new Object();
                    newclass.getClass();
                    form = (Form) dbForms.get(i);
                    // log.debug("modifyConfigFiles() - processing form: " + form.getName());
                    thisFormBean = addFormBean(formBeans, form);
                    thisValidatorForm = addValidatorForm(formSet, form);
                    addAction(actionMappings, form);
                    Iterator dbPageItems = form.getPageItems().iterator();
                    FormField formField = null;
                    //log.info("loaded Form:" + form.getLabel());
                    // adding Date of Visit to every form
                    FormField visitDate = new FormField();
                    visitDate.setLabel("Date of visit");
                    visitDate.setId(new Long(1));
                    visitDate.setType("Date");
                    visitDate.setRequired(true);
                    
                    if (form.getFormTypeId() < 5) {
                    	addField(thisFormBean, "field1");
                        addValidatorField(thisValidatorForm, visitDate);
                    }
                    
                    //FieldMappingUtil.getEncounterRecordFieldMap().put("field1", "visitDate");
                    while (dbPageItems.hasNext()) {
                        PageItem pageItem = (PageItem) dbPageItems.next();
                        formField = pageItem.getForm_field();
                        //formField = (FormField) dbFormFields.next();
                        if (!formField.getType().equals("Display")) {
                            if (formField.isEnabled() == true) {
                                addField(thisFormBean, formField);
                                if (!pageItem.getInputType().equals("hidden-empty")) {
                                    addValidatorField(thisValidatorForm, formField);
                                }

                                /*String patientProperty = formField.getPatientProperty();
                                if (patientProperty != null && !patientProperty.equals("")) {
                                    FieldMappingUtil.getPatientFieldMap().put("field" + formField.getId(), patientProperty);
                                }
                                String encounterRecordproperty = formField.getEncounterRecordproperty();
                                if (encounterRecordproperty != null && !encounterRecordproperty.equals("")) {
                                    FieldMappingUtil.getEncounterRecordFieldMap().put("field" + formField.getId(), encounterRecordproperty);
                                }*/
                            }
                        }
                    }
                    //addField(thisFormBean, "id", "java.lang.Long");
                    // writeHtml(form.getId().toString());
                }
                //log.debug("done with modifications in memory");
            } catch (NullPointerException e) {
                log.fatal("caught null pointer exception when building DOM from database. Likely database connection problem. Message: " + e.getMessage());
                throw new ServletException(e);
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
            }

            // lets write to a file
            OutputFormat format = OutputFormat.createPrettyPrint();
            // XMLWriter writer = new XMLWriter(new FileWriter(getServletContext().getRealPath(getServletConfig().getInitParameter("config"))), format);
            // XMLWriter writer = new XMLWriter(new FileWriter(Constants.WEBINF_PATH + "foo.xml"), format);
            XMLWriter writer = new XMLWriter(new FileWriter(fooPathname), format);
            writer.write(strutsConfigXML);
            writer.close();
            if (dev) {
                fooPathname = Constants.DEV_WEBINF_PATH + "foo.xml";
                String fooPathname2 = Constants.DYNASITE_WEBINF_PATH + "foo.xml";
                FileUtils.copyQuick(fooPathname, fooPathname2);
            }
            log.debug("saved modified struts-config.");

            // writer = new XMLWriter(new FileWriter(getServletContext().getRealPath(getServletConfig().getInitParameter("validator-config"))), format);
            // writer = new XMLWriter(new FileWriter(Constants.WEBINF_PATH + "bar.xml"), format);
            writer = new XMLWriter(new FileWriter(barPathname), format);
            writer.write(validatorXML);
            writer.close();
            if (dev) {
                barPathname = Constants.DEV_WEBINF_PATH + "bar.xml";
                String barPathname2 = Constants.DYNASITE_WEBINF_PATH + "bar.xml";
                FileUtils.copyQuick(barPathname, barPathname2);
            }
            log.debug("saved modified validation config.");
        } catch (MalformedURLException e) {
            log.fatal(e.getClass().getName() + " thrown in modifyConfigFiles: " + e.getMessage());
            throw new ServletException(e);
        } catch (DocumentException e) {
            if (e.getNestedException() != null && (e.getNestedException() instanceof java.net.UnknownHostException)) {
                log.fatal("unknown host--probably can't find the DTDs: " + e.getNestedException().getMessage());
            }
            log.fatal("strutsPathname: " + strutsPathname + " barPathname: " + barPathname);
            log.fatal(e.getClass().getName() + " thrown in modifyConfigFiles: " + e.getMessage());
            throw new ServletException(e);
        } catch (IOException e) {
            log.fatal(e.getClass().getName() + " thrown in modifyConfigFiles: " + e.getMessage());
            throw new ServletException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeHtml(String theURL) throws IOException {
        // lets write to a file
        URL theServlet = new URL(theURL);
        HttpURLConnection theConnection = (HttpURLConnection) theServlet.openConnection();
        theConnection.setRequestMethod("POST");
        theConnection.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(theConnection.getInputStream()));
        String inputLine;
        Vector v = null;
        while ((inputLine = in.readLine()) != null) {
            v.addElement(inputLine);
        }
        in.close();
    }

    /**
     * Loads an XML file into a DOM Document object.
     *
     * @param file The file to load
     * @return The DOM Document represented in the file.
     * @throws java.net.MalformedURLException
     * @throws org.dom4j.DocumentException
     */
    private Document getDocument(String filename)
            throws MalformedURLException, DocumentException {
        Document document;
        //use dom4j to get a handle on the struts-config document
        // URL url = getServletContext().getResource(file);
        //InputSource is = new InputSource(url.toExternalForm());
        String urlFilename = filename.replaceAll(" ", "%20");
        InputSource is = new InputSource(urlFilename);
        SAXReader reader = new SAXReader();
        reader.setValidation(false);
        reader.setEntityResolver(new LocalEntityResolver());
        document = reader.read(is);
        return document;
    }

    private Element addValidatorForm(Element formSet, Form form) {
        Element thisValidatorForm;
        thisValidatorForm = formSet.addElement("form");
        thisValidatorForm.addAttribute("name", "form" + form.getId());
        return thisValidatorForm;
    }

    private void addDepends(StringBuffer depends, String newDepends) {
        if (depends.length() > 0) {
            depends.append(",");
        }
        depends.append(newDepends);
    }

    private void addValidatorField(Element thisValidatorForm, FormField formField) {
        Element thisValidatorField;
        thisValidatorField = thisValidatorForm.addElement("field");
        thisValidatorField = thisValidatorField.addAttribute("property", "field" + formField.getId());
        StringBuffer depends = new StringBuffer();
        if (formField.isRequired()) {
            addDepends(depends, "required");
        }
        if (formField.getType().equals("Time")) {
            addDepends(depends, "time");
        }
        if (formField.getType().equals("Integer")) {
            boolean rangeAdded = false;
            addDepends(depends, "integer");
            if (formField.getMinValue() != null) {
                rangeAdded = true;
                addDepends(depends, "intRange");
                addRangeMin(thisValidatorField, formField.getMinValue());
                if (formField.getMaxValue() != null) {
                    addRangeMax(thisValidatorField, new Integer(Integer.MAX_VALUE));
                }
            }
            if ((formField.getMaxValue() != null) & (formField.getMaxValue() != 0)) {
                if (!rangeAdded) {
                    addDepends(depends, "intRange");
                    addRangeMin(thisValidatorField, new Integer(Integer.MIN_VALUE));
                }
                addRangeMax(thisValidatorField, formField.getMaxValue());
            }
        }
        if (formField.getType().equals("Float")) {
            boolean rangeAdded = false;
            addDepends(depends, "float");
            if (formField.getMinValue() == 0 && formField.getMaxValue() == 0) {
                // do nothing
            } else {
                if (formField.getMinValue() != null) {
                    // if (formField.getMinValue() != 0 & (formField.getMaxValue() != null && formField.getMaxValue() != 0)) {
                    rangeAdded = true;
                    addDepends(depends, "floatRange");
                    addRangeMin(thisValidatorField, new Float(formField.getMinValue().toString()));
                    if (formField.getMaxValue() != null) {
                        if (formField.getMaxValue() != 0) {
                            addRangeMax(thisValidatorField, new Float(formField.getMaxValue().toString()));
                        } else {
                            addRangeMax(thisValidatorField, new Float(Float.MAX_VALUE));
                        }
                    } else {
                        addRangeMax(thisValidatorField, new Float(Float.MAX_VALUE));
                    }
                    //  }
                }
                if (formField.getMaxValue() != null) {
                    if (formField.getMaxValue() != 0) {
                        if (!rangeAdded) {
                            addDepends(depends, "floatRange");
                            addRangeMin(thisValidatorField, new Float(Float.MIN_VALUE));
                            addRangeMax(thisValidatorField, new Float(formField.getMaxValue().intValue()));
                        }
                    }
                }
            }

        }
        if (depends.length() != 0) {
            thisValidatorField = thisValidatorField.addAttribute("depends", depends.toString());
        }
        Element arg0 = thisValidatorField.addElement("arg0");
        arg0.addAttribute("key", formField.getLabel());
        arg0.addAttribute("resource", "false");
    }

    private void addRangeMin(Element thisValidatorField, Integer value) {
        Element arg = thisValidatorField.addElement("arg1");
        arg.addAttribute("name", "intRange");
        arg.addAttribute("key", "${var:min}");
        arg.addAttribute("resource", "false");
        Element var = thisValidatorField.addElement("var");
        var.addElement("var-name").addText("min");
        var.addElement("var-value").addText(value.toString());
    }

    private void addRangeMin(Element thisValidatorField, Float value) {
        Element arg = thisValidatorField.addElement("arg1");
        arg.addAttribute("name", "floatRange");
        arg.addAttribute("key", "${var:min}");
        arg.addAttribute("resource", "false");
        Element var = thisValidatorField.addElement("var");
        var.addElement("var-name").addText("min");
        var.addElement("var-value").addText(value.toString());
    }

    private void addRangeMax(Element thisValidatorField, Integer value) {
        Element arg = thisValidatorField.addElement("arg2");
        arg.addAttribute("name", "intRange");
        arg.addAttribute("key", "${var:max}");
        arg.addAttribute("resource", "false");
        Element var = thisValidatorField.addElement("var");
        var.addElement("var-name").addText("max");
        var.addElement("var-value").addText(value.toString());
    }

    private void addRangeMax(Element thisValidatorField, Float value) {
        Element arg = thisValidatorField.addElement("arg2");
        arg.addAttribute("name", "floatRange");
        arg.addAttribute("key", "${var:max}");
        arg.addAttribute("resource", "false");
        Element var = thisValidatorField.addElement("var");
        var.addElement("var-name").addText("max");
        var.addElement("var-value").addText(value.toString());
    }

    private Element addFormBean(Element formBeans, Form form) {
        Element thisFormBean;
        thisFormBean = formBeans.addElement("form-bean");
        thisFormBean.addAttribute("name", "form" + form.getId());
        thisFormBean.addAttribute("type", "org.apache.struts.validator.DynaValidatorForm");
        return thisFormBean;
    }

    private void addField(Element thisFormBean, FormField formField) {
        addField(thisFormBean, "field" + formField.getId());
    }

    private void addField(Element thisFormBean, String name) {
        addField(thisFormBean, name, "java.lang.String");
    }

    private void addField(Element thisFormBean, String name, String type) {
        Element thisFormProperty;
        thisFormProperty = thisFormBean.addElement("form-property");
        thisFormProperty = thisFormProperty.addAttribute("name", name);
        thisFormProperty = thisFormProperty.addAttribute("type", type);
    }

    private void addAction(Element actionMappings, Form form) {
        Element thisActionMapping;
        //first add the action mapping for the input form
        thisActionMapping = actionMappings.addElement("action");
        thisActionMapping.addAttribute("path", "/form" + form.getId() + "/new");
//        thisActionMapping.addAttribute("type", "org.cidrz.webapp.dynasite.struts.action.FormDisplayAction");
        switch (form.getFormTypeId().intValue()) {
		case 5:
			String displayListFormAction = "org.rti.zcore.struts.action.records.ListAction";
            thisActionMapping.addAttribute("type", displayListFormAction);
			break;
		default:
            String displayFormAction = "org.rti.zcore.struts.action.FormDisplayAction";
            thisActionMapping.addAttribute("type", displayFormAction);
			break;
		}
        //thisActionMapping.addAttribute("name", "form" + form.getId());
        thisActionMapping.addAttribute("name", "form" + form.getId());
        thisActionMapping.addAttribute("validate", "false");
        thisActionMapping.addAttribute("parameter", String.valueOf(form.getId()));
        thisActionMapping.addAttribute("scope", "request");

        Element success = thisActionMapping.addElement("forward");
        success.addAttribute("name", "success");
        success.addAttribute("path", "/WEB-INF/pages/encounters/encounter_form.jsp");

        //then add the action mapping for the form submission
        thisActionMapping = actionMappings.addElement("action");
        thisActionMapping.addAttribute("path", "/form" + form.getId() + "/save");
        thisActionMapping.addAttribute("type", "org.cidrz.webapp.dynasite.struts.action.FormAction");
        thisActionMapping.addAttribute("name", "form" + form.getId());
        thisActionMapping.addAttribute("validate", "true");
        // when there is an error on form 80 (routine ante) - send user back to the chart.
        if (form.getId() == 80) {
            thisActionMapping.addAttribute("input", "/patientAnte.do" );
        } else {
            thisActionMapping.addAttribute("input", "/form" + form.getId() + "/new.do");
        }

        thisActionMapping.addAttribute("parameter", String.valueOf(form.getId()));
        thisActionMapping.addAttribute("scope", "request");

        //first add the action mapping for the input form
        thisActionMapping = actionMappings.addElement("action");
        thisActionMapping.addAttribute("path", "/form" + form.getId() + "/correct");
        thisActionMapping.addAttribute("type", "org.cidrz.webapp.dynasite.struts.action.FormDisplayAction");
        thisActionMapping.addAttribute("name", "form" + form.getId());
        thisActionMapping.addAttribute("validate", "false");
        thisActionMapping.addAttribute("parameter", String.valueOf(form.getId()));
        thisActionMapping.addAttribute("scope", "request");

        success = thisActionMapping.addElement("forward");
        success.addAttribute("name", "success");
//        success.addAttribute("path", "/WEB-INF/pages/encounters/encounter_form.jsp");
        switch (form.getFormTypeId().intValue()) {
		case 5:
	        success.addAttribute("path", "/WEB-INF/pages/admin/records/record.jsp");
			break;
		default:
	        success.addAttribute("path", "/WEB-INF/pages/encounters/encounter_form.jsp");
			break;
		}
    }

    /**
     * support for offline running.
     */
    private class LocalEntityResolver implements EntityResolver {
        public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
            if (systemId.equals("http://jakarta.apache.org/struts/dtds/struts-config_1_2.dtd")) {
                // http://jakarta.apache.org/struts/dtds/struts-config_1_2.dtd
                //URL url = getServletContext().getResource("/WEB-INF/dtds/struts-config_1_1.dtd");
                String strutsConfigDtd = "file:///" + Constants.WEBINF_PATH + "dtds/struts-config_1_2.dtd";
                // return new InputSource(url.toExternalForm());
                return new InputSource(strutsConfigDtd);
            } else if (systemId.equals("http://jakarta.apache.org/commons/dtds/validator_1_0.dtd")) {
                //URL url = getServletContext().getResource("/WEB-INF/dtds/validation_1_1.dtd");
                // http://jakarta.apache.org/commons/dtds/validator_1_0.dtd
                String strutsValidatorDtd = "file:///" + Constants.WEBINF_PATH + "dtds/validation_1_1.dtd";
                return new InputSource(strutsValidatorDtd);
            }
            return null;
        }
    }


}
