<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ taglib uri= "/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri= "/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri= "/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/tlds/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="/WEB-INF/tlds/c-rt.tld" prefix="c" %>

<template:insert template='/WEB-INF/templates/template-report.jsp'>
    <template:put name='title' direct='true'>LIMS Report
        for &nbsp;${LimsReport.siteName}&nbsp; ${LimsReport.beginDate}
        - ${LimsReport.endDate}</template:put>
    <template:put name='header' direct='true'>LIMS Report
        for &nbsp;${LimsReport.siteName}&nbsp; ${LimsReport.beginDate}
        - ${LimsReport.endDate}</template:put>
<template:put name='content' direct='true'>
<logic:present role="DELETE_ARCHIVE_PATIENT_RECORDS">
<p><html:link action="/admin/lims">Lims Preview and Import</html:link></p>
</logic:present>

<p>Most Recent LIMS Import: ${LimsReport.lastImportedRecord.dateCompletedImport}</p>

	<table class="enhancedtable">
		<tr>
			<th>Patient names</th>
			<th>ZEPRS ID</th>
			<th>Date Lab Request</th>
			<th>Lab Type</th>
			<th>Date Lab Results</th>
			<th>Hb</th>
			<th>CD4 count</th>
			<th>Exception</th>
			<th>Comments</th>
			<th>LIMS import id</th>
		</tr>
		<logic:iterate id="record" name="LimsReport" property="labTests" indexId="ind">
			<tr>
				<td>
				    <c:url value="labs.do" var="labs">
				    <c:param name="patientId" value="${record.patientId}"/>
				    </c:url>
				<a href='<c:out value="/zeprs/${labs}"/>'">${record.surname}, ${record.firstName}</a></td>
				<td>${record.zeprsId}</td>
				<td>${record.field1844}</td>
				<td><c:choose>
					<c:when test="${record.field1845 == 3042}">CD4</c:when>
					<c:when test="${record.field1845 == 2925}">Hb</c:when>
					<c:when test="${record.field1845 == 2926}">Hb</c:when>
				</c:choose></td>
				<td>${record.field1846}</td>
				<td>${record.field1858}</td>
				<td>${record.field2004}</td>
				<td>${record.field2149}</td>
				<td>${record.field1849}</td>
				<td>${record.field2143}</td>
			</tr>
		</logic:iterate>
	</table>

<c:if test="${! empty message}">
<br/>
<br/>
<pre><c:out value="${message}"/></pre>
</c:if>

</template:put>
</template:insert>