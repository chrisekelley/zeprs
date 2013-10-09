/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action.report;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.utils.XmlUtils;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.valueobject.Pregnancy;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.Principal;
import java.sql.Connection;


public final class GeneratePatientReportAction extends BaseAction {

    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    /**
     *
     * Displays XML for the patient record. Does not output the file to the filesystem.
     *
     * @param mapping  The ActionMapping used to select this instance
     * @param form     The optional ActionForm bean for this request (if any)
     * @param request  The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @return Action to forward to
     * @throws Exception if an input/output error or servlet exception occurs
     */
    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Principal user = request.getUserPrincipal();
    	String username = user.getName();
    	Connection conn = null;
    	try {
    		conn = DatabaseUtils.getZEPRSConnection(username);
    		Long patientId = Long.valueOf(request.getParameter("patientId"));
    		String outputType = Constants.PATIENT_RECORD_OUTPUT;
    		String contentType = "text/" + outputType;
    		response.setContentType(contentType);
    		XStream xstream = null;
    		if (outputType.equals("xml")) {
    			xstream = new XStream();
        		response.setContentType(contentType);
    		} else if (outputType.equals("js")) {
    			xstream = new XStream(new JsonHierarchicalStreamDriver());
    			// this will enable rendering to the screen.
        		response.setContentType("text/plain");
    		}
    		xstream.setMode(XStream.NO_REFERENCES);
        	//xstream.addImplicitCollection(Patient.class, "pregnancyList", Pregnancy.class);
    		xstream.alias("log", org.apache.commons.logging.impl.Log4JLogger.class);
    		PrintWriter writer = response.getWriter();
    		if (outputType.equals("xml")) {
    			writer.write("<?xml version=\"1.0\"?>\n");
    		}
    		boolean niceFieldNames = false;
    		Patient patient = XmlUtils.generatePatient(conn, patientId, niceFieldNames);
    		xstream.toXML(patient, writer);
    		writer.flush();
    		writer.close();
    	} catch (ServletException e) {
    		log.error(e);
    	} finally {
    		if (conn != null && !conn.isClosed()) {
    			conn.close();
    		}
    	}
    	return (null);
    }
}