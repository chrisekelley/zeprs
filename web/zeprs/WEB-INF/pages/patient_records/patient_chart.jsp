<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:choose>
    <c:when test="${empty param.flow}">
    <c:set var="flow" value="All"/>
    </c:when>
    <c:otherwise>
    <c:set var="flow" value="${param.flow}"/>
    </c:otherwise>
</c:choose>
<template:insert template='/WEB-INF/templates/template.jsp'>
<template:put name='title' direct='true'>Patient Record</template:put>
<c:set var="row" value="0" />
<%
//  Set up alternating row colors
String classRow = "evenRow";
%>

<template:put name='content' direct='true'>
<c:choose>
<c:when test="${! empty encounterForm}">
<div id="chartSum">
</c:when>
<c:otherwise>
<div id="chartFullPage">
</c:otherwise>
</c:choose>
<logic:empty name="patient" property="parent">
<div id="patientStatusChart">
    <table cellpadding="0" cellspacing="0" bgColor = "white" summary="Patient Status Chart Bar" width="100%">
    <thead>
        <tr class="patientrowheader">
            <th>First Pregnancy Visit</th>
            <th>Pregnancy Status</th>
            <%--<th>Most Recent Form</th>--%>
            <th>EDD</th>
        </tr>
    </thead>

    <tbody>
        <tr>
            <td class="patientrow"><c:if test="${!empty pregnancy.pregnancyBeginEncounter}"><html:link action="viewEncounter" paramId="id" paramName="pregnancy" paramProperty="pregnancyBeginEncounter.id" title="View the first record for this pregnancy."><fmt:formatDate type="both" pattern="dd/MM/yy" value="${pregnancy.datePregnancyBegin}" /></html:link></c:if></td>
            <td class="patientrow">
            <logic:notEmpty name="patient" property="patientStatusreport">
                <logic:notEmpty name="patient" property="patientStatusreport.currentFlow">
                <c:url value="patientTask.do" var="url"><c:param name="patientId" value="${patient.id}"/><c:param name="flowId" value="${patient.patientStatusreport.currentFlow.id}"/></c:url>
                <a href='<c:out value="/zeprs/${url}"/>' title="Jump to this phase.">${patient.patientStatusreport.currentFlow.name}</a>
                </logic:notEmpty>
            </logic:notEmpty></td>
            <%--<td class="patientrow">
            <logic:notEmpty name="mostRecentEncounter">
            <c:url value="viewEncounter.do" var="url"><c:param name="id" value="${mostRecentEncounter.id}"/><c:param name="class" value="${mostRecentEncounter.form.name}"/></c:url>
                <a href='<c:out value="/zeprs/${url}"/>' title="View this record.">${fn:substring(mostRecentEncounter.form.label,0,20)}<c:if test="${fn:length(mostRecentEncounter.form.label) >20}">...</c:if></a>
            </logic:notEmpty></td>--%>
            <td class="patientrow"><c:if test="${!empty patient.patientStatusreport.currentLmpDateEncounter}"><html:link action="viewEncounter" paramId="id" paramName="patient" paramProperty="patientStatusreport.currentLmpDateEncounter.id" title="View the record for this value."><fmt:formatDate type="both" pattern="dd/MM/yy" value="${eddDate}" /></html:link></c:if></td>
        </tr>
    </tbody>
    </table>
</div>
</logic:empty>
<c:choose>
<c:when test="${empty pregnancy.datePregnancyEnd}">
<h2>Chart Summary
</h2></c:when>
<c:otherwise>
<h2>Chart Summary for Pregnancy: <fmt:formatDate value="${pregnancy.datePregnancyBegin}" pattern="dd MMM yy"/> - <fmt:formatDate value="${pregnancy.datePregnancyEnd}" pattern="dd MMM yy"/></h2>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${!empty chartItems}">
<table class="enhancedtable" align="center">
<tr>
    <th>Date</th>
    <th>EGA:</th>
    <th>Fundal Height:</th>
    <th>ACE:</th>
    <th>ALB:</th>
    <th>GLU:</th>
    <th>Weight:</th>
    <th>hB:</th>
    <th>BP:</th>
    <th>Oedema:</th>
</tr>
<c:forEach items="${chartItems}" var="encounter">
<c:if test="${!empty encounter.egaDays}">
    <c:set var="days"  value="${encounter.egaDays % 7}" />
    <c:set var="weeks" value="${encounter.egaDays/7}" />
</c:if>
<c:choose>
<c:when test="${(encounter.encounter.form.id ==65) || (encounter.encounter.form.id ==69)}"><tr class="chartProblem"></c:when>
<c:otherwise><tr></c:otherwise>
</c:choose>
    <td width="50px"><html:link action="/viewEncounter.do" paramId="id" paramName="encounter" paramProperty="encounter.id" title="${encounter.encounter.form.label}" ><fmt:formatDate type="both" pattern="dd MMM yy" value="${encounter.encounter.dateVisit}" /></html:link></td>
    <td><c:if test="${!empty encounter.egaDays}"><fmt:parseNumber value="${weeks}" integerOnly="true" /> ${days}/7</c:if></td>
    <td>${encounter.fundalHeight}</td>
    <td>${encounter.aceValue}</td>
    <td>${encounter.albValue}</td>
    <td>${encounter.gluValue}</td>
    <td>${encounter.weight}</td>
    <td>${encounter.hb}</td>
    <td><c:if test="${! empty encounter.bpDiastolicValue}">${encounter.bpDiastolicValue}/${encounter.bpSystolicValue}</c:if></td>
    <td>${encounter.oedemaValue}</td>
</tr>
</c:forEach>
</table>
</c:when>
<c:otherwise>
<p>No chart data available for this patient.</p>
</c:otherwise>
</c:choose>
</div>
<c:choose>
<c:when test="${! empty parent}">
<div id="probChart">
    <h2>Parent:</h2>
    <html:link action="patientHome" paramId="patientId" paramName="parent" paramProperty="id">${parent.surname}, ${parent.firstName}</html:link>
</div>
</c:when>
<c:otherwise>
<c:import url="../problems/problems_chart.jsp" />
</c:otherwise>
</c:choose>
</template:put>
</template:insert>                                                                                                             