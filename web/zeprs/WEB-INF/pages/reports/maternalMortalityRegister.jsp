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
<template:put name='title' direct='true'>Maternal Mortality Register for &nbsp; ${register.siteName} &nbsp; ${register.beginDate} - ${register.endDate} </template:put>
<template:put name='header' direct='true'>Maternal Mortality Register for  &nbsp; ${register.siteName} &nbsp; ${register.beginDate} - ${register.endDate} </template:put>
<template:put name='content' direct='true'>
    <p>Note: Some of the fields are not currently defined in the ZEPRS system - they are placeholders.
        The Date of Death and Comments fields are taken from the date visit field in Maternal discharge form.</p>
<table border="1" cellspacing="0" cellpadding="3" class="enhancedtable">
<tr>
    <th>
        ZEPRS ID
    </th>
    <th>
        Date of Death
    </th>
    <th>
        Time of Death
    </th>
    <th>
        Age
    </th>
    <th>
        Cause of Death
    </th>
    <th>
        Postmortem Confirmation
    </th>
    <th>
        Date of Body Removal
    </th>
    <th>
        Comments
    </th>
</tr>

<c:set var="smRegisterString" value="unknown"/>
<c:set var="patientName" value="unknown"/>
<logic:iterate id="patient" name="register" property="patients">
<tr>
    <td align="center" class="small">
        <html:link action="/patientHome.do" paramId="patientId" paramName="patient" paramProperty="patientId">${patient.patientRegistration.patientIdNumber}</html:link>
    </td>
     <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.dateDeath}">${patient.dateDeath}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.timeDeath}">${patient.timeDeath}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.dateDeath}">${patient.patientRegistration.currentAge}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>

    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.causeOfDeath}">${patient.causeOfDeath}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.postMortemConfirmation}">${patient.postMortemConfirmation}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.removalOfBody}">${patient.removalOfBody}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.comments}">${patient.comments}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>

</tr>
</logic:iterate>
</table>

</template:put>
</template:insert>