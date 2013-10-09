<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ page import="org.cidrz.webapp.dynasite.session.SessionUtil"%>
<%@ taglib uri= "/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri= "/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri= "/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/tlds/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="/WEB-INF/tlds/c-rt.tld" prefix="c" %>

<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='ZEPRS: LIMS import' direct='true'/>
<template:put name='header' content='ZEPRS: LIMS import' direct='true'/>
<template:put name='content' direct='true'>
<p>
<strong>LIMS import operations:</strong>
    <c:url value="lims.do" var="limsPreviewAll"><c:param name="previewImport" value="true"/></c:url>
    <c:url value="lims.do" var="limsPreviewZEPRS"><c:param name="previewImport" value="true"/><c:param name="zeprsOnly" value="true"/></c:url>
    <c:url value="lims.do" var="limsExecute"><c:param name="previewImport" value="false"/><c:param name="zeprsOnly" value="true"/></c:url>
    <ul>
    <li><a href='<c:out value="/zeprs/admin/${limsPreviewAll}"/>'">Lims Import - Preview only (no records saved)</a></li>
    <li><a href='<c:out value="/zeprs/admin/${limsPreviewZEPRS}"/>'">Lims Import - Preview only (no records saved) - displays ZEPRS patients only</a></li>
    <li><a href='<c:out value="/zeprs/admin/${limsExecute}"/>'">Lims Import - Save records</a></li>
    </ul>
    </p>

    <p>
	<%-- Start: <bean:write name="starttime"/><br/>
	End: <bean:write name="endtime"/><br/> --%>
	<c:set var="diff" value="${difference}"/>
	<%--Runtime in seconds milliseconds: <c:out value="${diff}"/><br/>--%>
	<c:set var="seconds" value="${diff/1000}"/>
	Runtime in seconds : <c:out value="${seconds}"/> <br/>
	Runtime in minutes: <c:out value="${seconds/60}"/>
</p>
<c:if test="${! empty list}">
<h2><bean:write name="report"/>&nbsp;LIMS import complete</h2>
<p>Patients whose names are blank are not ZEPRS patients and will not be imported into the system.</p>

	<table class="enhancedtable">
		<tr>
			<th>Patient names</th>
			<th>ZEPRS ID</th>
			<th>date LabRequest</th>
			<th>lab Type</th>
			<th>Date Lab Results</th>
			<th>Results</th>
			<th>Results Numeric</th>
			<th>cd4 count</th>
			<th>Comments</th>
			<th>LIMS import id</th>
			<th>Exception</th>
		</tr>
		<logic:iterate id="record" name="list">
			<tr>
				<td><c:if test="${! empty record.sessionPatient.surname}">
				    <c:url value="labs.do" var="labs">
				    <c:param name="patientId" value="${record.sessionPatient.id}"/>
				    </c:url>
				<a href='<c:out value="/zeprs/${labs}"/>'">${record.sessionPatient.surname}, ${record.sessionPatient.firstName}</a></c:if></td>
				<td>${record.sessionPatient.districtPatientid}</td>
				<td>${record.field1844}</td>
				<td><c:choose>
					<c:when test="${record.field1845 == 3042}">CD4</c:when>
					<c:when test="${record.field1845 == 2925}">Hb</c:when>
					<c:when test="${record.field1845 == 2926}">Hb</c:when>
				</c:choose></td>
				<td>${record.field1846}</td>
				<td>${record.field1847}</td>
				<td>${record.field1858}</td>
				<td>${record.field2004}</td>
				<td>${record.field1849}</td>
				<td>${record.field2143}</td>
				<td>${record.field2149}</td>
			</tr>
		</logic:iterate>
	</table>
</c:if>
<c:if test="${! empty message}">
<br/>
<br/>
<pre><c:out value="${message}"/></pre>
</c:if>

</template:put>
</template:insert>