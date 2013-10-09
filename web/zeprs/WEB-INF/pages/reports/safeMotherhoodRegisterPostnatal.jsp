<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<template:insert template='/WEB-INF/templates/template-wide.jsp'>
<template:put name='title' direct='true'>Safe Motherhood Register - Postnatal - HIR.3</template:put>
<template:put name='header' direct='true'>Safe Motherhood Register - Postnatal - HIR.3</template:put>
<template:put name='content' direct='true'>
<p><strong>Health Institution:</strong> ${register.siteName}</p>
<p><strong>Period:</strong> ${register.beginDate} - ${register.endDate}</p>

<!--<link rel=StyleSheet href="./ZEPRSstyles.css" type="text/css">-->

<table border="1" cellspacing="0" cellpadding="3" class="enhancedtable">
<tr>
    <th rowspan="2">
        Postnatal
        Date of Visit
    </th>
    <th colspan="10">
        Mother
    </th>
    <th colspan="6">
        Baby
    </th>
</tr>
<tr>
    <th align="center" valign="bottom" class="small">SM #, Name, <br>Address</th>
    <td align="center" valign="bottom" class="small">
        BP
    </td>
    <td align="center" valign="bottom" class="small">
        Temperature
    </td>
    <td align="center" valign="bottom" class="small">
        Pulse
    </td>
    <td align="center" valign="bottom" class="small">
        Urine
    </td>
    <td align="center" valign="bottom" class="small">
        Pallor
    </td>
    <td align="center" valign="bottom" class="small">
        Uterus
    </td>
    <td align="center" valign="bottom" class="small">
        Lochia
    </td>
    <td align="center" valign="bottom" class="small">
        Breast
    </td>
    <td align="center" valign="bottom" class="small">
        ITN
    </td>
    <td align="center" valign="bottom" class="small">
        Feeds
    </td>
    <td align="center" valign="bottom" class="small">
        Cord
    </td>
    <td align="center" valign="bottom" class="small">
        Stump
    </td>
    <td align="center" valign="bottom" class="small">
        Temperature
    </td>
    <td align="center" valign="bottom" class="small">
        Respiration
    </td>
    <td align="center" valign="bottom" class="small">
        Weight
    </td>
</tr>
<c:set var="smRegisterString" value="unknown"/>
<c:set var="patientName" value="unknown"/>
<logic:iterate id="patient" name="register" property="patients">
    <logic:iterate id="encounter" name="patient" property="postnatalMap">
        <bean:define id="sme" name="encounter" property="value"/>
        <tr>
            <td align="center" class="small"><bean:write name="encounter" property="key"/></td>
            <td align="center" class="small" width="120px">
                <c:choose>
                    <c:when test="${! empty patient.smRegisterNumber}">
                        <c:set var="smRegisterString" value="${patient.smRegisterNumber}"/>
                    </c:when>
                    <c:otherwise></c:otherwise>
                </c:choose>
                <html:link action="/safeMotherhood.do" paramId="patientId" paramName="patient"
                           paramProperty="patientId">${smRegisterString}</html:link>
                <c:choose>
                    <c:when test="${! empty patient.name}">
                        <c:set var="patientName" value="${patient.name}"/>
                    </c:when>
                </c:choose>
                <br/>
                <html:link action="/demographics.do" paramId="patientId" paramName="patient"
                           paramProperty="patientId">${patient.name}</html:link>
                <br/>
                <c:choose>
                    <c:when test="${! empty patient.address1}">${patient.address1}</c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${! empty patient.address2}"><br/>${patient.address2}</c:when>
                </c:choose>
            </td>
            <td align="center" class="small">${sme.postnatalMotherBp}</td>
            <td align="center" class="small">${sme.postnatalMotherTemperature}</td>
            <td align="center" class="small">${sme.postnatalMotherPulse}</td>
            <td align="center" class="small">${sme.labUrineProtein}</td>
            <td align="center" class="small">${sme.postnatalMotherPallor}</td>
            <td align="center" class="small">${sme.postnatalMotherUterusContracted}</td>
            <td align="center" class="small">${sme.postnatalMotherLochia}</td>
            <td align="center" class="small">${sme.postnatalMotherBreastCondition}</td>
            <td align="center" class="small"><c:if test="${sme.itnUsed == true}">X</c:if></td>
            <td align="center" class="small">${sme.postnatalBabyFeeds}</td>
            <td align="center" class="small">${sme.postnatalBabyCordStump}</td>
            <td align="center" class="small">${sme.postnatalBabyCordStump}</td>
            <td align="center" class="small">${sme.postnatalBabyTemperature}</td>
            <td align="center" class="small">${sme.postnatalBabyRespiration}</td>
            <td align="center" class="small">${sme.postnatalBabyWeight}</td>
        </tr>
    </logic:iterate>
</logic:iterate>
</table>

</template:put>
</template:insert>
