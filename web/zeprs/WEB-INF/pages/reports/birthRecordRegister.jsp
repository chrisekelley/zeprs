<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<template:insert template='/WEB-INF/templates/template-report.jsp'>
<template:put name='title' direct='true'>Birth Record Register for  ${register.siteName} ${register.beginDate} - ${register.endDate}</template:put>
<template:put name='header' direct='true'>Birth Record Register for  ${register.siteName} ${register.beginDate} - ${register.endDate}</template:put>
<template:put name='content' direct='true'>
<table border="1" cellspacing="0" cellpadding="3" class="enhancedtable">
<tr>
    <th>
        Place of Birth
    </th>
    <th>
        Child Name
    </th>
    <th>
        Sex
    </th>
    <th>
        Date of Birth
    </th>
    <th>
        Time of Birth
    </th>
    <th>
        Father's Name, Address
    </th>
    <th>
        Father's Occupation
    </th>
    <th>
        Name of Mother
    </th>
</tr>

<c:set var="smRegisterString" value="unknown"/>
<c:set var="patientName" value="unknown"/>
<logic:iterate id="patient" name="register" property="patients">
<tr>

     <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.placeOfBirth}">${patient.placeOfBirth}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <html:link action="/patientHome.do" paramId="patientId" paramName="patient" paramProperty="patientId">${patient.infantPatientRegistration.surname}, ${patient.infantPatientRegistration.forenames}</html:link>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.sex}">${patient.sex}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.infantPatientRegistration.birthDate}">${patient.infantPatientRegistration.birthDate}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.infantPatientRegistration.timeOfBirth}">${patient.infantPatientRegistration.timeOfBirth}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.motherPatientRegistration.surnameFather}">${patient.motherPatientRegistration.surnameFather}, ${patient.motherPatientRegistration.forenamesFather}
            <br/>${patient.motherPatientRegistration.address1}, ${patient.motherPatientRegistration.nearbyPlaceWorship}, ${patient.motherPatientRegistration.religion}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.motherPatientRegistration.occupationHusband}">${patient.motherPatientRegistration.occupationHusband}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.motherPatientRegistration.surname}">${patient.motherPatientRegistration.surname}, ${patient.motherPatientRegistration.forenames}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
</tr>
</logic:iterate>
</table>

</template:put>
</template:insert>