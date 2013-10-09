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

<template:insert template='/WEB-INF/templates/template-report-wide.jsp'>
<template:put name='title' direct='true'>Eligible for ARV's Register for ${register.siteName}&nbsp;${register.beginDate} - ${register.endDate}</template:put>
<template:put name='header' direct='true'>Eligible for ARV's for ${register.siteName}&nbsp;${register.beginDate} - ${register.endDate}</template:put>
<template:put name='content' direct='true'>
<script language="javascript" type="text/javascript">
    function scrollUp() {
        var divY = document.getElementById('scrollBody').scrollTop;
        var newDivY = divY - 50;
        document.getElementById('scrollBody').scrollTop = newDivY;
    }

    function scrollDown() {
        var divY = document.getElementById('scrollBody').scrollTop;
        var newDivY = divY + 50;
        document.getElementById('scrollBody').scrollTop = newDivY;

    }
</script>
<!--test-->

<c:set var="pageBreakCount" value="0"/>
<table border="1" cellspacing="0" cellpadding="3" class="reportTablePrint">
    <tr>
        <th>Patient name</th>
        <th width="120px">ZEPRS ID</th>
        <th>Date CD4 Result Requested</th>
        <th>CD4 Result</th>
        <th>Date of Most Recent Regimen Visit</th>
		<th>Referred to ART Clinic - First Visit?</th>
        <th>Referred to ART Clinic - Ever?</th>
        <th>Patient enrolled in ART?</th>
        <th>If enrolled, which clinic?</th>
        <th>Patient Phone Number</th>
        <th>Residential Address</th>
        <th>Forname of Emergency Contact</th>
        <th>Tel. No. of Emergency Contact</th>
    </tr>

<c:set var="smRegisterString" value="unknown"/>
<c:set var="patientName" value="unknown"/>
<logic:iterate id="patient" name="register" property="patients" indexId="cnt">
<c:set var="pageBreakCount" value="${pageBreakCount + 1}"/>
	<tr>
    	<td align="center" class="small" width="120px">
        <html:link action="/demographics.do" paramId="patientId" paramName="patient" paramProperty="id">${patient.patientName}</html:link>
    	</td>
    	<td align="center" >${patient.districtPatientId}</td>
    	<td align="center" class="small" width="50px;"><fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${patient.cd4Date}"/></td>
	    <td align="center" class="small" width="50px;">${patient.cd4Result}</td>
	    <td align="center" class="small" width="50px;">${patient.regimenVisitDate}</td>
	    <td align="center" class="small" width="80px;">${patient.referralToArtFirstVisit}</td>
	    <td align="center" class="small" width="80px;">${patient.referralToArt}</td>
	    <td align="center" class="small" width="80px;">${patient.enrolled}</td>
	    <td align="center" class="small" width="80px;">${patient.artClinic}</td>
	    <td align="center" class="small" width="80px;">${patient.phone}</td>
	    <td align="center" class="small" width="80px;">${patient.address}</td>
	    <td align="center" class="small" width="80px;">${patient.contact}</td>
	    <td align="center" class="small" width="80px;">${patient.contactPhone}</td>
</tr>
<c:if test="${pageBreakCount == 25}">
    <c:set var="pageBreakCount" value="0"/>
    </table>
    <table border="1" cellspacing="0" cellpadding="3" class="reportTablePrint">
    <tr>
        <th>Patient name</th>
        <th width="120px">ZEPRS ID</th>
        <th>Date CD4 Result Requested</th>
        <th>CD4 Result</th>
        <th>Date of Most Recent Regimen Visit</th>
        <th>Referred to ART Clinic - First Visit?</th>
        <th>Referred to ART Clinic - Ever?</th>
        <th>Patient enrolled in ART?</th>
        <th>If enrolled, which clinic?</th>
        <th>Patient Phone Number</th>
        <th>Residential Address</th>
        <th>Forname of Emergency Contact</th>
        <th>Tel. No. of Emergency Contact</th>
    </tr>
</c:if>
</logic:iterate>
</table>
</template:put>
</template:insert>
