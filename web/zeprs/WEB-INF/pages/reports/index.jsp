<%@ page import="java.util.Date"%>
<%@ page import="java.util.TimeZone"%>
<%@ page import="org.cidrz.webapp.dynasite.utils.DateUtils"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    java.util.Calendar c = java.util.Calendar.getInstance();
    c.add(java.util.Calendar.MONTH, -1);
    Date date1monthpast = c.getTime();
    String DATE_FORMAT = "yyyy-MM-dd";
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
    sdf.setTimeZone(TimeZone.getDefault());
    String date1monthpastStr = sdf.format(date1monthpast);
    java.sql.Date date1monthpastSql =  java.sql.Date.valueOf(date1monthpastStr);
    pageContext.setAttribute("date1monthpast", date1monthpast);
    pageContext.setAttribute("date1monthpastSql", date1monthpastSql);
    java.sql.Date dateNow = DateUtils.getNow();
    pageContext.setAttribute("dateNow", dateNow);
    // week
    java.util.Calendar c2 = java.util.Calendar.getInstance();
    c2.add(java.util.Calendar.WEEK_OF_YEAR, -1);
    Date date1weekpast = c2.getTime();
    java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat(DATE_FORMAT);
    sdf2.setTimeZone(TimeZone.getDefault());
    String date1weekpastStr = sdf2.format(date1weekpast);
    java.sql.Date date1weekpastSql =  java.sql.Date.valueOf(date1weekpastStr);
    pageContext.setAttribute("date1weekpastSql", date1weekpastSql);
    // quarter
    java.util.Calendar c3 = java.util.Calendar.getInstance();
    c3.add(java.util.Calendar.MONTH, -3);
    Date quarterPast = c3.getTime();
    java.text.SimpleDateFormat sdf3 = new java.text.SimpleDateFormat(DATE_FORMAT);
    sdf2.setTimeZone(TimeZone.getDefault());
    String quarterPastStr = sdf3.format(quarterPast);
    java.sql.Date quarterPastSql =  java.sql.Date.valueOf(quarterPastStr);
    pageContext.setAttribute("quarterPastSql", quarterPastSql);
    // previous day
    java.util.Calendar c4 = java.util.Calendar.getInstance();
    c4.add(java.util.Calendar.DATE, -1);
    Date date1daypast = c4.getTime();
    java.text.SimpleDateFormat sdf4 = new java.text.SimpleDateFormat(DATE_FORMAT);
    sdf4.setTimeZone(TimeZone.getDefault());
    String date1daypastStr = sdf4.format(date1daypast);
    java.sql.Date date1daypastSql =  java.sql.Date.valueOf(date1daypastStr);
    pageContext.setAttribute("date1daypastSql", date1daypastSql);
%>
<template:insert template='/WEB-INF/templates/template.jsp'>
<template:put name='title' direct='true'>ZEPRS Registers and Reports</template:put>

<template:put name='content' direct='true'>
<jsp:useBean id="now" class="java.util.Date" />
<c:set var="theDate" value="${now}"/>
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${date1monthpast}" var="dbdate1monthpast" />
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${theDate}" var="dbNow" />

<c:choose>
    <c:when test="${empty site}">
    <c:set var="theSite" value="${patientSiteId}"/>
    </c:when>
    <c:otherwise>
    <c:set var="theSite" value="${site}"/>
    </c:otherwise>
</c:choose>

<div id="widePage">
    <h2>ZEPRS Registers and Reports</h2>
    <p>The registers may be queried by date and site. You may also export the registers to Excel by clicking the "Export" checkbox.</p>
    <html:form action="/ChooseReportAction">
    <input type="hidden" name="task" value="run"/>
	<table cellspacing="0" cellpadding="5" class="enhancedtabletighterCell">
	<tr>
		<th>Register</th>
        <th>Site</th>
        <th>Begin Date</th>
        <th>End Date</th>
        <th>Export?</th>
        <th>&nbsp;</th>
    </tr>
	<tr>
		<td>
			<html:select property="report" size="1">

				<html:option value="5"><!--Delivery Register--><bean:message key="app.chooseReport.report5"/></html:option>
                <html:option value="32"><!--Safe Motherhood Register - mothers --print--><bean:message key="app.chooseReport.report17"/> - Antenatal - print</html:option>
                <html:option value="17"><!--Safe Motherhood Register - mothers--><bean:message key="app.chooseReport.report17"/> - Antenatal - full</html:option>
				<html:option value="31"><!--Safe Motherhood Register - newborns--><bean:message key="app.chooseReport.report17"/> - Postnatal</html:option>
				<html:option value="33"><!--Integrated VctRegister-->Integrated VCT Register - print</html:option>
				<html:option value="22"><!--Integrated VctRegister-->Integrated VCT Register - all</html:option>
                <html:option value="35">Daily Reflex Register - print</html:option>
				<html:option value="36">Daily Reflex Register - all</html:option>
                <html:option value="41">Eligible for ART Register</html:option>
                <html:option value="24">Maternal Mortality Register</html:option>
				<html:option value="15">Neonatal Mortality Register</html:option>
				<html:option value="25">Birth Record</html:option>
				<html:option value="27">PMTCT Labour Ward Register</html:option>
                <html:option value="1">CBoH Health Centre Self-Assessment HIQ.1</html:option>
				<html:option value="3"><!--Clinic Workload Report--><bean:message key="app.chooseReport.report3"/></html:option>
                <html:option value="4"><!--formerly known as Delivery Tally Sheet-->Health Centre Service Delivery Aggregation HIA.2</html:option>
                <html:option value="28">Antenatal Monthly Summary Sheet</html:option>
                <html:option value="34">Antenatal Monthly Summary Sheet - print</html:option>
                <html:option value="40">Fixed Cost Incentive Report</html:option>
                <html:option value="26">Delivery Summary Sheet</html:option>
                <html:option value="13">LUDHMB Maternal And Neonatal Stats</html:option>
                <html:option value="29">DHMT HIV Control</html:option>
                <html:option value="42">LIMS import report</html:option>
            </html:select>
		</td>

		<td>
            <select id="siteSelector" name="siteId">
                <option value="">Select Site</option>
                <c:choose>
                    <c:when test="${theSite=='all'}">
                            <option value="0" selected="selected" >All sites</option>
                        <c:forEach var="site" begin="0" items="${sites}">
                            <c:if test="${site.inactive != 1}">
                            <option value="${site.id}">${site.name}</option>
                            </c:if>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <option value="0">All sites</option>
                        <c:forEach var="site" begin="0" items="${sites}">
                            <c:if test="${site.inactive != 1}">
                            <c:choose>
                            <c:when test="${theSite==site.id}">
                            <option value="${site.id}" selected="selected">${site.name}</option>
                            </c:when>
                            <c:otherwise>
                            <option value="${site.id}">${site.name}</option>
                            </c:otherwise>
                            </c:choose>
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </select>

        </td>

        <td>
            <zeprs:date_visit_no_form_no_label element = "chooseReportForm" dateVisit = "${date1monthpastSql}" field = "bdate|bdate1"/>
        </td>

		<td>
            <zeprs:date_visit_no_form_no_label element = "chooseReportForm" dateVisit = "${dateNow}" field = "edate|edate1"/>
        </td>

        <td><input type="checkbox" name="isXml" value="1"/></td>

        <td>
			<center><html:submit/></center>
		</td>
	</tr>
	</table>
    </html:form>


    <h2>Reports w/ set time periods</h2>
    This site:
    <ul>
        <li><html:link action="ChooseReportAction?bdate=${dateNow}&edate=${dateNow}&siteId=${theSite}&report=32">Daily Safe Motherhood Register - print version</html:link></li>
        <li><html:link action="ChooseReportAction?bdate=${dateNow}&edate=${dateNow}&siteId=${theSite}&report=17">Daily Safe Motherhood Register - full version</html:link></li>
        <li><html:link action="ChooseReportAction?bdate=${dateNow}&edate=${dateNow}&siteId=${theSite}&report=33">Integrated VCT Daily - print version</html:link></li>
        <li><html:link action="ChooseReportAction?bdate=${dateNow}&edate=${dateNow}&siteId=${theSite}&report=22">Integrated VCT Daily - full version</html:link></li>
        <li><html:link action="ChooseReportAction?bdate=${dateNow}&edate=${dateNow}&siteId=${theSite}&report=35">Daily Reflex Register - print version</html:link></li>
        <li><html:link action="ChooseReportAction?bdate=${dateNow}&edate=${dateNow}&siteId=${theSite}&report=36">Daily Reflex Register - full version</html:link></li>
        <li><html:link action="ChooseReportAction?bdate=${date1weekpastSql}&edate=${dateNow}&siteId=${theSite}&report=24">Weekly Maternal Mortality Report</html:link></li>
        <li><html:link action="ChooseReportAction?bdate=${date1weekpastSql}&edate=${dateNow}&siteId=${theSite}&report=15">Weekly Neonatal Mortality Report</html:link></li>
        <li><html:link action="ChooseReportAction?bdate=${quarterPastSql}&edate=${dateNow}&siteId=${theSite}&report=1">CBoH Health Centre Quarterly Self-Assessment HIQ.1</html:link></li>
        <li><html:link action="ChooseReportAction?bdate=${date1monthpastSql}&edate=${dateNow}&siteId=${theSite}&report=13">LUDHMB Maternal And Neonatal Stats Monthly</html:link></li>

    </ul>
    All sites:
    <ul>
        <li><html:link action="ChooseReportAction?bdate=${date1weekpastSql}&edate=${dateNow}&siteId=0&report=24">Weekly Maternal Mortality Report</html:link></li>
        <li><html:link action="ChooseReportAction?bdate=${date1weekpastSql}&edate=${dateNow}&siteId=0&report=15">Weekly Neonatal Mortality Report</html:link></li>
        <li><html:link action="ChooseReportAction?bdate=${quarterPastSql}&edate=${dateNow}&siteId=0&report=1">CBoH Health Centre Quarterly Self-Assessment HIQ.1</html:link></li>
        <li><html:link action="ChooseReportAction?bdate=${date1monthpastSql}&edate=${dateNow}&siteId=0&report=13">LUDHMB Maternal And Neonatal Stats Monthly</html:link></li>
        <li><html:link action="ChooseReportAction?bdate=${date1daypastSql}&edate=${dateNow}&siteId=0&report=42">LIMS import report - past day</html:link></li>
   </ul>

    <p><html:link action="ChooseReportAction?bdate=${date1monthpastSql}&edate=${dateNow}&siteId=0&report=30">UTH Obstetrics and Neonatology Statistics</html:link></p>

    <p><html:link action="archive">Record Modification Listing</html:link> - provides staff name and time that a record has been modified</p>

<p><html:link action="reports/queryBuilder">Query Builder</html:link> - ad-hoc query interface</p>

    <%--<p>The reports linked below are provided in XML format - they can be opened in Excel as well as viewed in the browser. Right-click and save to your
        pc, then open in Excel. They are currently generated at 7 AM every day.</p>

    <p>There are two versions of most reports:
        <ul>
            <li>Plain version: Enumerations (dropdowns) have an integer value</li>
            <li>Resolved version: "Resolved" appended to filename. Enumerations are in English.</li>
        </ul>
    </p>

    <p>Special reports:
        <ul>
            <li>PatientReport.xml is a special report that provides a listing of all patients currently in the system, with their
        stage of pregnancy and other useful data</li>
            <li>OutcomeReport.xml is a large file - wait for it to load.</li>
            <li>EncounterReport.xml This report lists data about each enounter, sorted by patient id and date created.
                This could be useful for tracking how long it takes a clinician to complete records for a new patient.
            </li>
        </ul>
    </p>

<table class="enhancedtable">
    <tr>
        <th>File name</th>
        <th>Date created</th>
        <th>File size</th>
    </tr>
<logic:iterate id="report" name="reports">
    <tr>
        <td><a href="data/${report.fileName};jsessionid=${pageContext.request.session.id}">${report.fileName}</a></td>
        <td><fmt:formatDate type="both" pattern="dd MMM yy hh:mm" value="${report.lastModified}" /></td>
        <td><fmt:formatNumber value="${report.length/1024}" maxFractionDigits="2"/>  kb</td>
    </tr>
</logic:iterate>
</table>--%>
<p>&nbsp;</p>
</div>
</template:put>
</template:insert>