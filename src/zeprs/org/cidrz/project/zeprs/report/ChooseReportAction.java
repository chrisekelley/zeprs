/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * Created on Mar 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.webapp.dynasite.Constants;
import org.cidrz.webapp.dynasite.struts.action.generic.BaseAction;
import org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects;
import org.cidrz.webapp.dynasite.valueobject.Site;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * @author ericl
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ChooseReportAction extends BaseAction {

    /**
     * Commons Logging instance.
     */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

     protected ActionForward doExecute(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response)
            throws Exception {

        String task = "";
        if (request.getParameter("task") != null) {
            task = request.getParameter("task");
        }
        boolean isXml = false;
        if (request.getParameter("isXml") != null) {
            isXml = true;
        }

        ZEPRSReport report = null;
        ZEPRSRegister register = null;

        int reportID = Integer.parseInt(((chooseReportForm) form).getReport());
        if (request.getParameter("report") != null) {
             reportID = (Integer.valueOf(request.getParameter("report").toString()));
         }
        // determine start and end dates for queries
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//		Date beginDate = sdf.parse(((chooseReportForm)form).getBdate());
//		Date endDate = sdf.parse(((chooseReportForm)form).getEdate());
       // Date beginDate = Date.valueOf(((chooseReportForm) form).getBdate());
      //  Date endDate = Date.valueOf(((chooseReportForm) form).getEdate());
         Date beginDate = null;
         Date endDate = null;
         if (request.getParameter("bdate") != null) {
             beginDate = Date.valueOf(String.valueOf(request.getParameter("bdate")));
         }
         if (request.getParameter("edate") != null) {
             endDate = Date.valueOf(String.valueOf(request.getParameter("edate")));
         }
         // Name of the forward to use - see ChooseReportAction in struts-config
         // Most of the reports have thier own jsp page. Name sets the name of this file.
         String name = "none";
        // int siteId = ((chooseReportForm) form).getSiteId();
         int siteId = 0;
         if (request.getParameter("siteId") != null) {
             siteId = (Integer.valueOf(request.getParameter("siteId").toString()));
         }
        // boolean  isXml= ((chooseReportForm) form).isXml();
        String siteName;
        if (siteId == 0) {
            siteName = "All sites";
        } else {
            Site site = (Site) DynaSiteObjects.getClinicMap().get((long) siteId);
            siteName = site.getName();
        }
        request.setAttribute("siteName", siteName);
        List records = new ArrayList();
        String abbrev = "enc";
        Class clazz = EncounterData.class;
        switch (reportID) {
             /**/
            case 1:
                report = new CBoHQuarterlySelfAssessmentReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "CBoHQuarterlySelfAssessment";
                try {
                        report.loadReport(beginDate, endDate);
                } catch (Exception e) {
                    log.error("Error running the CBoHQuarterly report: " + e.getMessage());
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                }
                request.setAttribute("CBoHQuarterly", report);
                break;

            case 2:
                report = new CBoHCommunicableDiseaseWeeklyReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "CBoHCommunicableDisease";
                break;

            case 3:
                report = new ClinicWorkloadReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "ClinicWorkload";
                try {
                    if (task.equals("view")) {
                        report = report.loadClass(name);
                    } else if (task.equals("run")) {
                        report.loadReport(beginDate, endDate);
                    } else if (task.equals("generate")) {
                        report.loadReport(beginDate, endDate);
                    }
                } catch (Exception e) {
                    log.error("Error running the ClinicWorkload report: " + e.getMessage());
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                }
                request.setAttribute("ClinicWorkload", (ClinicWorkloadReport) report);
                break;

            case 4:
                report = new DeliveryTallySheetReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "DeliveryTally";
                if (task.equals("view")) {
                    report = report.loadClass(name);
                } else if (task.equals("run")) {
                    report.loadReport(beginDate, endDate);
                } else if (task.equals("generate")) {
                    report.loadReport(beginDate, endDate);
                }
                request.setAttribute("DeliveryTallySheet", report);
                break;

            case 5:
                register = new DeliveryRegisterReport();
                register.setSiteName(siteName);
                register.getPatientRegister(beginDate, endDate, siteId);
                register.setBeginDate(beginDate);
                register.setEndDate(endDate);
                request.setAttribute("register", register);
                name = "DeliveryRegister";
                abbrev = "DR";
                clazz = DeliveryRegisterReport.class;
                break;

            case 6:
                report = new DHMT1HIVControlReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "DHMT1HIV";
                break;

            case 7:
                report = new DiseaseAggregationReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "DiseaseAggregation";
                break;

            case 8:
                report = new DistrictServiceDeliveryAggregationReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "DistrictServiceDeliveryAggregation";
                break;

            case 9:
                report = new HealthServiceDeliveryAggregationReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "HealthServiceDeliveryAggregation";
                break;

            case 10:
                report = new HospitalServiceDeliveryAggregationReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "HospitalServiceDeliveryAggregation";
                break;

            case 11:
                report = new InpatientAdmissionTallyReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "InpatientAdmission";
                break;

            case 12:
                report = new InpatientDiagnosisAndDeathTallyReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "InpatientDiagnosis";
                break;
                 /**/
            case 13:
                report = new LUDHMBReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "LUDHMBMaternalAndNeonatalStats";
                /*if (task.equals("view")) {
                    report = report.loadClass(name);
                } else if (task.equals("generate")) {*/
                    report.loadReport(beginDate, endDate);
               // }
                request.setAttribute("LUDHMBReport", report);
                break;
                 /**/
            case 14:
                report = new LUDHMTTallySheetForMDHImmunisationsReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "LUDHMTTallySheet";
                break;

            case 15:
                register = new NeonatalMortality();
                register.setSiteName(siteName);
                register.getPatientRegister(beginDate, endDate, siteId);
                register.setBeginDate(beginDate);
                register.setEndDate(endDate);
                request.setAttribute("register", register);
                name = "NeonatalMortality";
                abbrev = "Mort";
                clazz = NeonatalMortality.class;
                break;

            case 16:
                report = new OutpatientDiagnosisTallyReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "OutpatientDiagnosis";
                break;

            case 17:
                register = new SafeMotherhoodRegisterReport();
                register.setType("antenatal");
                register.setSiteName(siteName);
                register.getPatientRegister(beginDate, endDate, siteId);
                register.setBeginDate(beginDate);
                register.setEndDate(endDate);
                request.setAttribute("register", register);
                name = "SafeMotherhoodRegisterAntenatal";
                abbrev = "SM";
                clazz = SafeMotherhoodRegisterReport.class;
                break;

            case 18:
                report = new SafeMotherhoodTallyReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "SafeMotherhoodTally";
                if (task.equals("view")) {
                    report = report.loadClass(name);
                } else if (task.equals("run")) {
                    report.loadReport(beginDate, endDate);
                } else if (task.equals("generate")) {
                    report.loadReport(beginDate, endDate);
                }
                request.setAttribute("SafeMotherhoodTally", report);
                abbrev = "SMT";
                break;

            case 19:
                report = new Under1ImmunisationTallyReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "Under1Immunisation";
                break;

            case 20:
                report = new WeeklyMaternalMortalityReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "WeeklyMaternalMortality";
                break;

            case 21:
                report = new WeeklyNeonatalMortalityReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "WeeklyNeonatalMortality";
                break;
            case 22:
                register = new IntegratedVctRegister();
                register.setSiteName(siteName);
                register.getPatientRegister(beginDate, endDate, siteId);
                register.setBeginDate(beginDate);
                register.setEndDate(endDate);
                request.setAttribute("register", register);
                name = "IntegratedVctRegister";
                abbrev = "VCT";
                clazz = IntegratedVctRegister.class;
                break;
           /* case 23:
                register = new SafeMotherhoodRegisterReport();
                register.setType("postnatal");
                register.setSiteName(siteName);
                register.getPatientRegister(beginDate, endDate, siteId);
                register.setBeginDate(beginDate);
                register.setEndDate(endDate);
                request.setAttribute("register", register);
                name = "SafeMotherhoodRegisterPostnatal";
                abbrev = "SMP";
                clazz = SafeMotherhoodRegisterReport.class;
                break;*/
           case 24:
                register = new MaternalMortalityRegister();
                register.setSiteName(siteName);
                register.getPatientRegister(beginDate, endDate, siteId);
                register.setBeginDate(beginDate);
                register.setEndDate(endDate);
                request.setAttribute("register", register);
                name = "MaternalMortalityRegister";
                abbrev = "Mort";
                clazz = MaternalMortalityRegister.class;
                break;
            case 25:
                register = new BirthRecordRegister();
                register.setSiteName(siteName);
                register.getPatientRegister(beginDate, endDate, siteId);
                register.setBeginDate(beginDate);
                register.setEndDate(endDate);
                request.setAttribute("register", register);
                name = "BirthRecordRegister";
                abbrev = "Birth";
                clazz = BirthRecordRegister.class;
                break;
            case 26:
                report = new DeliverySummarySheetReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "DeliverySummary";
                if (task.equals("view")) {
                    report = report.loadClass(name);
                } else if (task.equals("run")) {
                    report.loadReport(beginDate, endDate);
                } else if (task.equals("generate")) {
                    report.loadReport(beginDate, endDate);
                }
                request.setAttribute("DailyDeliverySummary", report);
                break;
             case 27:
                register = new PmtctLabourWardRegisterReport();
                register.setSiteName(siteName);
                register.getPatientRegister(beginDate, endDate, siteId);
                register.setBeginDate(beginDate);
                register.setEndDate(endDate);
                request.setAttribute("register", register);
                name = "PmtctLabourWardRegister";
                abbrev = "PMTCT";
                clazz = PmtctLabourWardRegisterReport.class;
                break;
             case 28:
                report = new DailyAntenatalSummaryReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "DailyAntenatalSummary";
                report.loadReport(beginDate, endDate);
                request.setAttribute("DailyAntenatalSummary", report);
                break;
             case 29:
                report = new DhmtHivControlReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "DhmtHivControl";
                try {
                        report.loadReport(beginDate, endDate);
                } catch (Exception e) {
                    log.error("Error running the DhmtHivControl report: " + e.getMessage());
                    request.setAttribute("exception", e);
                    return mapping.findForward("error");
                }
                request.setAttribute("DhmtHivControl", report);
                break;
              case 30:
                report = new UthObstetricsReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                name = "UthObstetricsReport";
                report.loadReport(beginDate, endDate);
                request.setAttribute("UthObstetricsReport", report);
                break;
              case 31:
                register = new SafeMotherhoodRegisterReport();
                register.setType("postnatal");
                register.setSiteName(siteName);
                register.getPatientRegister(beginDate, endDate, siteId);
                register.setBeginDate(beginDate);
                register.setEndDate(endDate);
                request.setAttribute("register", register);
                name = "SafeMotherhoodRegisterPostnatal";
                abbrev = "SM";
                clazz = SafeMotherhoodRegisterReport.class;
                break;
              case 32:
                register = new SafeMotherhoodRegisterReport();
                register.setType("antenatalPrint");
                register.setSiteName(siteName);
                register.getPatientRegister(beginDate, endDate, siteId);
                register.setBeginDate(beginDate);
                register.setEndDate(endDate);
                request.setAttribute("register", register);
                name = "SafeMotherhoodRegisterAntenatal";
                abbrev = "SM";
                clazz = SafeMotherhoodRegisterReport.class;
                break;
               case 33:
                register = new IntegratedVctRegister();
                register.setType("ignoreRepeatNoTest");
                register.setSiteName(siteName);
                register.getPatientRegister(beginDate, endDate, siteId);
                register.setBeginDate(beginDate);
                register.setEndDate(endDate);
                request.setAttribute("register", register);
                name = "IntegratedVctRegister";
                abbrev = "VCT";
                clazz = IntegratedVctRegister.class;
                break;
            case 34:
                report = new DailyAntenatalSummaryReport();
                report.setSiteId(siteId);
                report.setSiteName(siteName);
                HashMap reportProps = new HashMap();
                reportProps.put("print", Boolean.TRUE);
                report.setReportProperties(reportProps);
                name = "DailyAntenatalSummary";
                report.loadReport(beginDate, endDate);
                request.setAttribute("DailyAntenatalSummary", report);
                break;
             case 35:
                register = new ReflexRegisterReport();
                register.setType("print");   // not (longer) view version
                register.setSiteName(siteName);
                register.getPatientRegister(beginDate, endDate, siteId);
                register.setBeginDate(beginDate);
                register.setEndDate(endDate);
                request.setAttribute("register", register);
                name = "ReflexRegister";
                abbrev = "RR";
                clazz = ReflexRegisterReport.class;
                break;
             case 36:
                register = new ReflexRegisterReport();
                register.setType("view");   // extended view version
                register.setSiteName(siteName);
                register.getPatientRegister(beginDate, endDate, siteId);
                register.setBeginDate(beginDate);
                register.setEndDate(endDate);
                request.setAttribute("register", register);
                name = "ReflexRegister";
                abbrev = "RR";
                clazz = ReflexRegisterReport.class;
                break;
             case 37:
            	 report = new MonthlyAncReport();
                 report.setSiteId(siteId);
                 report.setSiteName(siteName);
                 reportProps = new HashMap();
                 reportProps.put("print", Boolean.TRUE);
                 report.setReportProperties(reportProps);
                 name = "MonthlyAncReport";
                 report.loadReport(beginDate, endDate);
                 request.setAttribute("MonthlyAncReport", report);
            	 break;
             case 38:
                 report = new MonthlyAncReport();
                 report.setSiteId(siteId);
                 report.setSiteName(siteName);
                 reportProps = new HashMap();
                 reportProps.put("print", Boolean.TRUE);
                 reportProps.put("year", Boolean.TRUE);
                 report.setReportProperties(reportProps);
                 name = "MonthlyAntenatalSummary";
                 report.loadReport(beginDate, endDate);
                 request.setAttribute("DailyAntenatalSummary", report);
                 break;
             case 39:
            	 report = new MonthlyAncReport();
                 report.setSiteId(siteId);
                 report.setSiteName(siteName);
                 reportProps = new HashMap();
                 reportProps.put("print", Boolean.TRUE);
                 reportProps.put("year", Boolean.TRUE);
                 reportProps.put("allSites", Boolean.TRUE);
                 report.setReportProperties(reportProps);
                 name = "AllSitesMonthlyAntenatalSummary";
                 report.loadReport(beginDate, endDate);
                 request.setAttribute("DailyAntenatalSummary", report);
                 break;
             case 40:
                 report = new FixedCostIncentiveReport();
                 report.setSiteId(siteId);
                 report.setSiteName(siteName);
                 name = "FixedCostIncentiveReport";
                 report.loadReport(beginDate, endDate);
                 request.setAttribute("FixedCostIncentive", report);
                 break;
             case 41:
            	 register = new EligibleForArtReport();
                 register.setType("print");   // not (longer) view version
                 register.setSiteName(siteName);
                 register.getPatientRegister(beginDate, endDate, siteId);
                 register.setBeginDate(beginDate);
                 register.setEndDate(endDate);
                 request.setAttribute("register", register);
                 name = "EligibleForArtReport";
                 abbrev = "EA";
                 clazz = EligibleForArtReport.class;
                 break;
             case 42:
            	 report = new LimsReport();
                 report.setSiteId(siteId);
                 report.setSiteName(siteName);
                 name = "LimsReport";
                 report.loadReport(beginDate, endDate);
                 request.setAttribute("LimsReport", report);
            	 break;
                 /**/
            default:
                break;

        }    // end switch

        if (isXml) {
            String reportType = null;

            if (records.size() > 0) {
               reportType = "records";
            } else if (register != null) {
               reportType = "register";
            }

            if (reportType != null) {
                XStream xstream = new XStream();
                xstream.alias(abbrev, clazz);
                xstream.alias("log", org.apache.commons.logging.impl.Log4JLogger.class);
                xstream.alias("baby", org.cidrz.project.zeprs.valueobject.report.gen.NewbornEvalReport.class);
                xstream.alias("SMP", org.cidrz.project.zeprs.report.SafeMotherhoodRegisterPatient.class);
                // response.setContentType("text/html");
                response.setContentType("text/xml");
                response.setContentType("application/vnd.ms-excel");
                PrintWriter writer = response.getWriter();
                writer.write("<?xml version=\"1.0\"?>\n");
                if (reportType.equals("records") ) {
                    xstream.toXML(records, writer);
                } else {
                    xstream.toXML(register, writer);
                }
                //writer.write(xml);
                writer.flush();
                writer.close();
                // use writer to render text
                return(null);
                // log.info("saved register " + path + name + "Register.xml");
            }
        }
        if (task.equals("generate")) {
            if (report != null) {
                XStream xstream = new XStream();
                String path = Constants.REPORTS_XML_PATH;
                Writer writer = new BufferedWriter(new FileWriter(path + name + "Report.xml"));
                writer.write("<?xml version=\"1.0\"?>\n");
                xstream.toXML(report, writer);
                //writer.write(xml);
                writer.flush();
                writer.close();
                log.info("saved report " + path + name + "Report.xml");
            } else if (register != null) {
                XStream xstream = new XStream();
                String path = Constants.REPORTS_XML_PATH;
                Writer writer = new BufferedWriter(new FileWriter(path + name + "Report.xml"));
                writer.write("<?xml version=\"1.0\"?>\n");
                xstream.toXML(register, writer);
                //writer.write(xml);
                writer.flush();
                writer.close();
                log.info("saved register " + path + name + "Report.xml");
            }

        }

        return mapping.findForward(name);

    }

}
