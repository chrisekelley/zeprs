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
<template:put name='title' direct='true'>Integrated VCT Register for  ${register.siteName}, ${register.beginDate} - ${register.endDate}</template:put>
<template:put name='header' direct='true'>Integrated VCT Register for  ${register.siteName}, ${register.beginDate} - ${register.endDate}</template:put>
<template:put name='content' direct='true'>
<table border="1" cellspacing="0" cellpadding="1" class="enhancedtabletighter">
<tr>
    <th rowspan="2">
        SM #
    </th>
    <th colspan="2">
        ANC<br/>
        Visit
    </th>
    <th colspan="2">
        Took<br/>
        Test
    </th>
    <th rowspan="2">
        Test <br>
        Result
    </th>
    <th colspan="9">
        Age Group in years
    </th>
    <th colspan="2">
        Marital Status
    </th>
</tr>
<tr>
    <td align="center" valign="bottom" class="small">
        1st
    </td>
    <td align="center" valign="bottom" class="small">
        Return
    </td>
    <td align="center" valign="bottom" class="small">
        1st
    </td>
    <td align="center" valign="bottom" class="small">
        Return
    </td>
    <td align="center" valign="bottom" class="small">&lt;5</td>
    <td align="center" valign="bottom" class="small">1-14</td>
    <td align="center" valign="bottom" class="small">15-19</td>
    <td align="center" valign="bottom" class="small">20-24</td>
    <td align="center" valign="bottom" class="small">25-34</td>
    <td align="center" valign="bottom" class="small">35-39</td>
    <td align="center" valign="bottom" class="small">40-44</td>
    <td align="center" valign="bottom" class="small">45-49</td>
    <td align="center" valign="bottom" class="small">50+</td>
    <td align="center" valign="bottom" class="small">Single</td>
    <td align="center" valign="bottom" class="small">Married</td>
</tr>
<c:set var="smRegisterString" value="unknown"/>
<c:set var="patientName" value="unknown"/>
<logic:iterate id="patient" name="register" property="patients">
<tr>
    <td align="center" class="small">
        <html:link action="/patientHome.do" paramId="patientId" paramName="patient" paramProperty="patientId">${patient.smRegisterNumber}</html:link>
       <%-- <%= smrp.getSmRegisterNumber() %>--%>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.newAncClient == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.repeatAncClient == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.tookTestFirstVisit == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.tookTestRevisit == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.testResult}">${patient.testResult}</c:when>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.ageGroupUnder5 == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.ageGroup1_14 == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.ageGroup15_19 == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.ageGroup20_24 == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.ageGroup25_34 == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.ageGroup35_39 == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.ageGroup40_44 == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.ageGroup45_49 == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.ageGroup50plus == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.maritalStatus_Single_F == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${patient.maritalStatus_Married_F == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
</tr>
</logic:iterate>
</table>

</template:put>
</template:insert>