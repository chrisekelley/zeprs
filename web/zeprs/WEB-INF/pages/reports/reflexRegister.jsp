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
<template:put name='title' direct='true'>ANC Reflex Register for ${register.siteName} ${register.beginDate} - ${register.endDate}</template:put>
<template:put name='header' direct='true'>ANC Reflex Register for ${register.siteName} ${register.beginDate} - ${register.endDate}</template:put>
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

<c:set var="pageBreakCount" value="0"/>
<table border="1" cellspacing="0" cellpadding="3" class="reportTablePrint">
    <tr>
        <th>Patient name</th>
        <th width="120px">ZEPRS ID</th>
        <th>Reactive Test Date</th>
        <th>Visit Date</th>
        <th>CD4 Requested?</th>
        <th>CD4 Request Date</th>
        <th>CD4 Result</th>
        <th>Hgb Request Date</th>
        <th>Hgb result</th>
        <th>WHO Screen</th>
        <th>Referral to ART</th>
        <th>PMTCT Regimen</th>
        <th>Current EGA (wks)</th>
        <th>Date of next visit</th>
    </tr>

<c:set var="smRegisterString" value="unknown"/>
<c:set var="patientName" value="unknown"/>
<logic:iterate id="patient" name="register" property="patients" indexId="cnt">
<c:set var="pageBreakCount" value="${pageBreakCount + 1}"/>
<tr>

    	<c:choose>
	    	<c:when test="${empty patient.districtPatientId && ! empty patient.regimenVisitDate}">
	    	<td colspan="2">&nbsp;</td>
	    	</c:when>
	    	<c:when test="${patient.labType=='c'}">
	    	<td colspan="2">&nbsp;</td>
	    	</c:when>
	    	<c:when test="${patient.labType=='h'}">
	    	<td colspan="2">&nbsp;</td>
	    	</c:when>
	    	<c:otherwise>
	    	<td align="center" class="small" width="120px">
	        <html:link action="/demographics.do" paramId="patientId" paramName="patient" paramProperty="id">${patient.patientName}</html:link>
	    	</td>
	    	<td align="center" >${patient.districtPatientId}</td>
	    	<!--<td>Summary</td>
	    	-->
	    	</c:otherwise>
    	</c:choose>
    	<td align="center" class="small" width="30px;"><fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${patient.hivPositiveTestDate}"/></td>
    	<td align="center" class="small" width="30px;"><fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${patient.regimenVisitDate}"/></td>
	    <td align="center" class="small" width="50px;"><c:if test="${patient.cd4Done == true}">Yes</c:if><c:if test="${patient.cd4Done == false}">No</c:if></td>
	    <td align="center" class="small" width="50px;"><fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${patient.cd4Date}"/></td>
	    <td align="center" class="small" width="50px;">${patient.cd4Result}</td>
	    <td align="center" class="small" width="80px;"><fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${patient.hgbDate}"/></td>
	    <td align="center" class="small" width="80px;">${patient.hgbResult}</td>
	    <td align="center" class="small" width="80px;">${patient.whoScreen}</td>
	    <td align="center" class="small" width="80px;">${patient.referralToArt}</td>
	    <td align="center" class="small" width="80px;">${patient.pmtctRegimen}</td>
	    <td align="center" class="small" width="80px;">${patient.egaWeeks}</td>
	    <td align="center" class="small" width="80px;"><fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${patient.dateNextVisit}"/></td>


</tr>
<c:if test="${pageBreakCount == 25}">
    <c:set var="pageBreakCount" value="0"/>
    </table>
    <table border="1" cellspacing="0" cellpadding="3" class="reportTablePrint">
    <tr>
    	<th>Patient name</th>
        <th width="120px">ZEPRS ID</th>
        <th>Reactive Test Date</th>
        <th>Visit Date</th>
        <th>CD4 Requested?</th>
        <th>CD4 Request Date</th>
        <th>CD4 Result</th>
        <th>Hgb Request Date</th>
        <th>Hgb result</th>
        <th>WHO Screen</th>
        <th>Referral to ART</th>
        <th>PMTCT Regimen</th>
        <th>Current EGA (wks)</th>
        <th>Date of next visit</th>
    </tr>
</c:if>
</logic:iterate>
</table>
</template:put>
</template:insert>
