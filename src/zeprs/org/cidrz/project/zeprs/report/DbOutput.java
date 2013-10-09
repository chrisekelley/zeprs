/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.report;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cidrz.project.zeprs.valueobject.report.gen.PrevPregnanciesReport;
import org.cidrz.project.zeprs.valueobject.report.PatientStatusReport;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.project.zeprs.valueobject.gen.PatientRegistration;
import org.cidrz.webapp.dynasite.dao.*;
import org.cidrz.webapp.dynasite.dao.partograph.PartographDAO;
import org.cidrz.webapp.dynasite.dao.reports.ReportDAO;
import org.cidrz.webapp.dynasite.utils.*;
import org.cidrz.webapp.dynasite.valueobject.*;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.rules.impl.InfoOutcome;
import org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome;
import org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome;
import org.cidrz.webapp.dynasite.rules.impl.OutcomeImpl;
import org.cidrz.webapp.dynasite.rules.Outcome;

import javax.servlet.ServletException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

/**
 * Example of using existing DAO's to create reports. These can be used w/ XmlUtils to output reports to xml
 *
 * @author <a href="mailto:ckelley@rti.org">Chris Kelley</a>
 *         Date: Aug 9, 2005
 *         Time: 5:59:25 PM
 */
public class DbOutput {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(DbOutput.class);



    /**
     * Generates list of patients.
     * Used for report generation - ReportGenAction
     * @param conn
     */
    public static void generatePatientList(Connection conn) {
        log.debug("Generating XML for PatientReport");
        List patients = getPatients(conn);
        String fileName = "PatientReport";
        String abbrev = "patient";
        Class clazz = org.cidrz.webapp.dynasite.valueobject.SessionPatient.class;
        try {
            // XmlUtils.save(patients, org.cidrz.webapp.dynasite.Constants.REPORTS_XML_PATH + "patient.xml");
            XmlUtils.save(patients, org.cidrz.webapp.dynasite.Constants.REPORTS_XML_PATH + fileName + ".xml", abbrev, clazz);
            // log.debug("Patient Listing created at " + org.cidrz.webapp.dynasite.Constants.REPORTS_XML_PATH + "PatientReport.xml");
        } catch (IOException e) {
            log.error(e);
        }
    }

     /**
     * Populates a list of patients of SessionPatient class. currentFlow is no longer being resolved since flow is no longer in zeprs db.
     * If you'd like flow resolved, add the following to the select part of the query:
     * <code>
     *     "f.name AS currentFlowName, " +
     * </code>
     * and then add the followng to the join:
     * <code>
     *     "LEFT JOIN admin.flow f ON s.current_flow=f.id " +
     * </code>
     * @return list of patients
     * @param conn
     */
    public static List getPatients(Connection conn) {
        List patients = new ArrayList();
        try {
            List patientList = PatientDAO.getAll(conn);
            for (int i = 0; i < patientList.size(); i++) {
                Patient patient = (Patient) patientList.get(i);
                SessionPatient sessionPatient = null;
                try {
                    sessionPatient = SessionPatientDAO.getSessionPatient(conn, patient.getId(), new Long("0"));
                } catch (ObjectNotFoundException e) {
                    //
                }
                if (sessionPatient != null) {
                    patients.add(sessionPatient);
                }
            }
        } catch (SQLException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        }
        return patients;
    }

    /**
     * Generates list of outcomes
     * Used in reports
     * @param conn
     */
    public static void generateOutcomesList(Connection conn) {
        log.debug("Generating XML for outcomes");
        List outcomes = OutcomeDAO.getAll(conn);
        String fileName = "OutcomeReport";
        Class clazz = OutcomeReport.class;
        XStream xstream = new XStream();
        xstream.alias("Outcome", OutcomeImpl.class);
        xstream.alias("Outcome", Outcome.class);
        xstream.alias("OutcomeReport", OutcomeReport.class);
        xstream.alias("InfoOutcome", InfoOutcome.class);
        xstream.alias("ReferralOutcome", ReferralOutcome.class);
        xstream.alias("EncounterOutcome", EncounterOutcome.class);
        try {
            Writer writer = new BufferedWriter(new FileWriter(org.cidrz.webapp.dynasite.Constants.REPORTS_XML_PATH + fileName + ".xml"));
            writer.write("<?xml version=\"1.0\"?>\n");
            writer.write("<?xml-stylesheet type=\"text/xsl\" href=\"../xsl/OutcomeReport.xsl\" ?>\n");
            xstream.toXML(outcomes, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            log.error(e);
        }
    }

    /**
     * Generates encounters list
     * Used in report xml file generation
     * Note the xsl stylesheet link.
     * @param conn
     */
    public static void generateEncounterDataList(Connection conn) {
        log.debug("Generating XML for encounters");
        List encounters = null;
        try {
            encounters = EncountersDAO.getAll(conn);
        } catch (ServletException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        }
        String fileName = "EncounterReport";
        XStream xstream = new XStream();
        xstream.alias("Encounter", EncounterData.class);
        try {
            Writer writer = new BufferedWriter(new FileWriter(org.cidrz.webapp.dynasite.Constants.REPORTS_XML_PATH + fileName + ".xml"));
            writer.write("<?xml version=\"1.0\"?>\n");
            writer.write("<?xml-stylesheet type=\"text/xsl\" href=\"../xsl/EncounterDataReport.xsl\" ?>\n");
            xstream.toXML(encounters, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            log.error(e);
        }
    }

}
