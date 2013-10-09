<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

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
    request.setAttribute("date1monthpast", c.getTime());
%>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' direct='true'>
<bean:message key="app.title"/>
</template:put>
<template:put name='header' direct='true'>
<bean:message key="app.title"/>
</template:put>
<template:put name='content' direct='true'>

<jsp:useBean id="now" class="java.util.Date" />
<c:set var="theDate" value="${now}"/>
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${date1monthpast}" var="dbdate1monthpast" />
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${theDate}" var="dbNow" />

<div id="changelog">

<p>If you need to generate a report on-demand, choose a report from the following list. Note that report generation takes a few minutes.
You may continue with your browsing after issuing a report generation request.</p>

<p><a href="/zeprs/admin/reportGen.do;jsessionid=${pageContext.request.session.id}?patients=yeah">Patient Listing</a> -
generate patient report - filename: PatientReport.xml .
    <p><a href="/zeprs/admin/reportGen.do;jsessionid=${pageContext.request.session.id}?outcomes=yeah">Outcomes Listing</a> -
generate outcomes report - filename: OutcomeReport.xml .
    <p><a href="/zeprs/admin/reportGen.do;jsessionid=${pageContext.request.session.id}?encounter=yeah">Encounters Listing</a> -
generate outcomes report - filename: EncounterReport.xml .
<p><a href="/zeprs/admin/reportGen.do;jsessionid=${pageContext.request.session.id}?all=yeah">Generate all db's</a> -
generate xml for all tables.

<%--    <html:form action="/ChooseReportAction">
    <input type="hidden" name="task" value="generate"/>
	<table cellspacing="0" cellpadding="5">
	<tr>
		<td colspan="4">Select a report from the following list:</td>
	</tr>
	<tr>
		<td colspan="4">
			<html:select property="report" size="1">
				<html:option value="1"><bean:message key="app.chooseReport.report1"/></html:option>
				<html:option value="2"><bean:message key="app.chooseReport.report2"/></html:option>
				<html:option value="3"><bean:message key="app.chooseReport.report3"/></html:option>
				<html:option value="4"><bean:message key="app.chooseReport.report4"/></html:option>
				<html:option value="5"><bean:message key="app.chooseReport.report5"/></html:option>
				<html:option value="6"><bean:message key="app.chooseReport.report6"/></html:option>
				<html:option value="7"><bean:message key="app.chooseReport.report7"/></html:option>
				<html:option value="8"><bean:message key="app.chooseReport.report8"/></html:option>
				<html:option value="9"><bean:message key="app.chooseReport.report9"/></html:option>
				<html:option value="10"><bean:message key="app.chooseReport.report10"/></html:option>
				<html:option value="11"><bean:message key="app.chooseReport.report11"/></html:option>
				<html:option value="12"><bean:message key="app.chooseReport.report12"/></html:option>
				<html:option value="13"><bean:message key="app.chooseReport.report13"/></html:option>
				<html:option value="14"><bean:message key="app.chooseReport.report14"/></html:option>
				<html:option value="15"><bean:message key="app.chooseReport.report15"/></html:option>
				<html:option value="16"><bean:message key="app.chooseReport.report16"/></html:option>
				<html:option value="17"><bean:message key="app.chooseReport.report17"/></html:option>
				<html:option value="18"><bean:message key="app.chooseReport.report18"/></html:option>
				<html:option value="19"><bean:message key="app.chooseReport.report19"/></html:option>
				<html:option value="20"><bean:message key="app.chooseReport.report20"/></html:option>
				<html:option value="21"><bean:message key="app.chooseReport.report21"/></html:option>
			</html:select>
		</td>
	</tr>
	<tr>
		<td>Begin Date:</td>
		<td>
			<html:text property="bdate" size="10" value="${dbdate1monthpast}"/>
		</td>
		<td>End Date:</td>
		<td>
			<html:text property="edate" size="10" value="${dbNow}"/>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<center><html:submit/></center>
		</td>
	</tr>
	</table>

</html:form>--%>

</div>
</template:put>
</template:insert>