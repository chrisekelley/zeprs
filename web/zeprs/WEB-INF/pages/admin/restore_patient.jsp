<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License"};
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='Restore a Patient' direct='true'/>
<template:put name='header' content='Restore a Patient' direct='true'/>
<template:put name='help' content='' direct='true'/>

<template:put name='content' direct='true'>

<c:if test="${! empty message}"><p>${message}</p></c:if>
<script type="text/javascript">
function loadPatient(filename) {
	//patientFileName = document.getElementById('patientFileName');
	//patientFileName.value = filename;
	var filearr = filename.split('.');
	var zeprsIdValue = '5040-' + filearr[2];
	zeprsId = document.getElementById('zeprsId');
	zeprsId.value = zeprsIdValue;
}
</script>
<html:form  action="admin/restorePatient" method="POST">
<c:choose>
<c:when test="${!empty patient}">
<html:hidden property="zeprsId" />
<html:hidden styleId="patientFileName" property="patientFileName" value="${patientFileName}"  />
<html:hidden property="pubDate" value="${pubDate}" />
<p>Here is a brief summary of the patient record:<br/>
Name: ${patient.surname}, ${patient.firstName}<br/>
ZEPRS ID: ${patient.districtPatientid}<br/>
Birthdate: ${patient.birthdate}<br/>
Record Last Modified: ${patient.patientStatusreport.lastModified}
</p>
<p>If this is the correct patient, press the Submit button.</p>
</c:when>
<c:otherwise>
<p>Instructions: Enter in the patient id (5040-NNN-NNNNN-N) for the missing patient. </p>
ZEPRS ID:<html:text styleId="zeprsId" property="zeprsId" size="35" />
</c:otherwise>
</c:choose>

<c:if test="${!empty patientFileName}">
	<c:if test="${!empty patient}">

	</c:if>
</c:if>

<html:submit value="Submit"/>
</html:form>
<c:if test="${!empty fileMap}">
<p>You may refine your choice from the following list.<br/>
 If you find a patient in the list you'd like to restore but do not know the id, you can figure it out from the filename.<br/>
 For example, if the filename is "smith.53.332-00003-3.xml", then the id is 5040-332-00003-3<br/>
 Click on the desired name to load the patient and click Submit.</p>
<table>
<th>Patient Record filename</th><th>Date last modified</th>
<c:forEach items="${fileMap}" var="file">
<tr><td><a href="#" onclick="loadPatient('${file.key}')">${file.key}</a></td><td>${file.value}</td>
</c:forEach>
</table>
</c:if>
<br/>
    </template:put>
</template:insert>