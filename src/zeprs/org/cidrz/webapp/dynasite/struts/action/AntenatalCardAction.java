/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.struts.action;

import java.io.StringReader;
import java.security.Principal;
import java.sql.Connection;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.project.zeprs.report.AntenatalHistoryReport;
import org.cidrz.project.zeprs.report.valueobject.AntenatalHistory;
import org.cidrz.project.zeprs.report.valueobject.CurrentPregnancyStatus;
import org.cidrz.project.zeprs.report.valueobject.DrugsDispensed;
import org.cidrz.project.zeprs.report.valueobject.HivStamp;
import org.cidrz.project.zeprs.report.valueobject.NewbornReport;
import org.cidrz.project.zeprs.report.valueobject.PregnancyReport;
import org.cidrz.project.zeprs.valueobject.gen.MedSurgHist;
import org.cidrz.project.zeprs.valueobject.report.PatientRegistrationClean;
import org.cidrz.project.zeprs.valueobject.report.gen.LabTestReport;
import org.cidrz.project.zeprs.valueobject.report.gen.MedSurgHistReport;
import org.cidrz.project.zeprs.valueobject.report.gen.PostnatalInfantReport;
import org.cidrz.project.zeprs.valueobject.report.gen.ProbLaborReport;
import org.cidrz.project.zeprs.valueobject.report.gen.RoutineAnteReport;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.utils.DatabaseUtils;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Form;
import org.cidrz.webapp.dynasite.valueobject.PageItem;

import com.thoughtworks.xstream.XStream;

public class AntenatalCardAction extends BaseAction {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getFactory().getInstance(AntenatalCardAction.class);

    protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// retrieve current settings from the session
        HttpSession session = request.getSession(true);
        Principal user = request.getUserPrincipal();
        String username = user.getName();
        Connection conn = null;
        try {
            conn = DatabaseUtils.getZEPRSConnection(username);
            Long patientId = null;
            try {
                patientId = Long.valueOf(request.getParameter("patientId"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            AntenatalHistory ante = AntenatalHistoryReport.generateAntenatalHistory(conn, patientId);
            // request.setAttribute("ante", ante);
            XStream xstream = new XStream();
            xstream.alias("AntenatalHistory", AntenatalHistory.class);
            xstream.alias("CurrentPregnancyStatus", CurrentPregnancyStatus.class);
            xstream.alias("PatientRegistrationClean", PatientRegistrationClean.class);
            xstream.alias("RoutineAnte", RoutineAnteReport.class);
            xstream.alias("RoutineAnte", RoutineAnteReport.class);
            xstream.alias("MedSurgHist", MedSurgHist.class);
            xstream.alias("LabTestReport", LabTestReport.class);
            xstream.alias("PregnancyReport", PregnancyReport.class);
            xstream.alias("NewbornReport", NewbornReport.class);
            xstream.alias("PostnatalInfantReport", PostnatalInfantReport.class);
            xstream.alias("log", org.apache.commons.logging.impl.Log4JLogger.class);
            xstream.alias("baby", org.cidrz.project.zeprs.valueobject.report.gen.NewbornEvalReport.class);
            xstream.alias("SMP", org.cidrz.project.zeprs.report.SafeMotherhoodRegisterPatient.class);
            xstream.alias("drug", DrugsDispensed.class);
            xstream.alias("hivStamp", HivStamp.class);
            xstream.alias("probLabor", ProbLaborReport.class);
            /*xstream.alias("infoOutcome", InfoOutcome.class);
           xstream.alias("encounterOutcome", EncounterOutcome.class);
           xstream.alias("referralOutcome", ReferralOutcome.class);
           xstream.alias("problem", Problem.class);
           xstream.alias("comment", Comment.class);*/
            Form medHistForm = (Form) DynaSiteObjects.getForms().get((long) 70);
            Set pageItems = medHistForm.getPageItems();
            xstream.setMode(XStream.NO_REFERENCES);
            for (Iterator iterator = pageItems.iterator(); iterator.hasNext();) {
                PageItem pageItem = (PageItem) iterator.next();
                String label = StringUtils.deleteWhitespace(pageItem.getForm_field().getLabel());
                label = label.replaceAll("/", "").replaceAll(",", "");
                xstream.aliasField(label.trim(), MedSurgHistReport.class, pageItem.getForm_field().getStarSchemaName() + "R");
            }
            // response.setContentType("text/html");
            // response.setContentType("application/vnd.ms-excel");
            // response.setContentType("text/xml");
            //  PrintWriter writer = response.getWriter();

            //   writer.write("<?xml version=\"1.0\"?>\n");
            //   writer.write("<?xml-stylesheet type=\"text/xsl\" href=\"xsl/AntenatalCard.xsl\" ?>\n");
            //  xstream.toXML(ante, writer);
            String output = xstream.toXML(ante);
            StringReader sr = new StringReader(output);
            Source xmlSource = new javax.xml.transform.stream.StreamSource(sr);
            // writer.flush();
            // writer.close();
            // use writer to render text
            response.setContentType("text/html; charset=UTF-8");
            // Output goes to the response PrintWriter.
            java.io.PrintWriter out = response.getWriter();
            // 1. Instantiate a TransformerFactory.
            javax.xml.transform.TransformerFactory tFactory = javax.xml.transform.TransformerFactory.newInstance();

            // 2. Use the TransformerFactory to process the stylesheet Source and generate a Transformer.
            String xslFile = "file:///" + Constants.REPORTS_XSL_PATH + "/AntenatalCard.xsl";
            try {
                // 3. Use the Transformer to transform an XML Source and send the output to a Result object.
                javax.xml.transform.Transformer transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource(xslFile));
                String sessionID = session.getId();
                transformer.setParameter("global.sessionID", ";jsessionid=" + sessionID);
                transformer.transform(xmlSource, new javax.xml.transform.stream.StreamResult(out));
                // WrappedRuntimeException is Sun proprietary API and may be removed in a future release
            //} catch (WrappedRuntimeException e) {
            //    log.error(e);
                //e.printStackTrace();
            } catch (TransformerException e) {
                log.error(e);
                //e.printStackTrace();
            }
            out.close();
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
