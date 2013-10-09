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
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri='/WEB-INF/tlds/zeprs.tld' prefix='zeprs' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<template:insert template='/WEB-INF/templates/template-admin.jsp'>
    <template:put name='title' content='Admin: Compare Results' direct='true'/>
    <template:put name='header' content='Admin: Compare Results' direct='true'/>
    <template:put name='help' content='test' direct='true'/>

    <template:put name='content' direct='true'>
        <html:errors/>
<p>This report displays the results of comparing local patients to remote patients.</p>
<h2>Missing Remote Patients</h2>
	<p>
	<c:forEach var="missingRemotePatient" begin="0" items="${siteReport.missingRemotePatientMap}">
		<c:out value="${missingRemotePatient.key}" /><br>
	</c:forEach>
	</p>
<h2>Missing Remote Pregnancies</h2>
	<p>
	<c:forEach var="missingRemotePregnancy" begin="0" items="${siteReport.missingRemotePregnancyMap}">
		<c:out value="${missingRemotePregnancy.key}" /><br>
	</c:forEach>
	</p>

<h2>Incorrect Remote Pregnancy Encounter Results </h2>
	<p>
	<c:forEach var="incorrectRemotePregnancyEncounterResults" begin="0" items="${siteReport.incorrectRemotePregnancyEncounterResultsMap}">
		<c:out value="${incorrectRemotePregnancyEncounterResults.key}" /><br>
		<c:out value="${incorrectRemotePregnancyEncounterResults.value}" /><br>
	</c:forEach>
	</p>

<h2>Patient Sync List</h2>
<p>Local patients who need to be synced on the remote site. This list is based on the results of Missing Remote Pregnancies and Incorrect Remote Pregnancy Encounter Results lists. </p>
<c:forEach var="zeprsId" begin="0" items="${siteReport.patientSyncList}">
	<c:out value="${zeprsId}" /><br>
</c:forEach>

	</template:put>
</template:insert>