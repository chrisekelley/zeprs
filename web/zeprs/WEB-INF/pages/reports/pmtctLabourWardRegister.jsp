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
<template:put name='title' direct='true'>PMTCT Labour Ward Register for ${register.siteName} ${register.beginDate} - ${register.endDate}</template:put>
<template:put name='header' direct='true'>PMTCT Labour Ward Register for ${register.siteName} ${register.beginDate} - ${register.endDate}</template:put>
<template:put name='content' direct='true'>
    <p>Notes:
        <ul>
            <li>Mother ARV data from Delivery Summary form. Date ARV Taken column data same as Visit date field. </li>
            <li>"Baby Dose given" column gets its data from the "Received baby dose?" field from Newborn Eval.
                form</li>
            <li>"Regimen Delivery" column gets its data from the "Regimen Delivery" field from Newborn Eval. form</li>
        </ul></p>
<table border="1" cellspacing="0" cellpadding="3" class="reportTable">
<tr>
    <th colspan="8">
        Mother
    </th>
    <th colspan="5">
        Infant
    </th>
</tr>
    <tr>
        <th>Visit Date</th>
        <th>SM #</th>
        <th>Name, <br>Address</th>
        <th>Marital<br>Status</th>
        <th>ARV Taken?</th>
        <th>Regimen</th>
        <th>Place ARV Taken</th>
        <th>Date ARV Taken</th>
        <th>Multiple Births?</th>
        <th>Birth Type</th>
        <th>Baby Dose given</th>
        <th>Regimen at Delivery</th>
        <th>Nurse at Delivery</th>
    </tr>
<c:set var="smRegisterString" value="unknown"/>
<c:set var="patientName" value="unknown"/>
<logic:iterate id="patient" name="register" property="patients">
    <tr>
        <td>${patient.dateDelivery}</td>
        <td>
            <c:choose>
                <c:when test="${! empty patient.smRegisterNumber}">
                    <c:set var="smRegisterString" value="${patient.smRegisterNumber}"/>
                </c:when>
                <c:otherwise></c:otherwise>
            </c:choose>
            <html:link action="/safeMotherhood.do" paramId="patientId" paramName="patient"
                       paramProperty="patientId">${smRegisterString}</html:link>
        </td>
        <td>
            <c:choose>
                <c:when test="${! empty patient.name}">
                    <c:set var="patientName" value="${patient.name}"/>
                </c:when>
            </c:choose>
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
        <td>${patient.maritalStatus}</td>
        <td align="center"><c:choose>
            <c:when test="${patient.arvReceived == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose></td>
        <td>${patient.regimenMother}</td>
        <td>${patient.placeArvReceived}</td>
        <td>${patient.dateArvReceived}</td>
        <td align="center"><c:choose>
            <c:when test="${patient.multipleBirth == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose></td>
        <td>${patient.birthType}</td>
        <td align="center"><c:choose>
            <c:when test="${patient.babyDoseGiven == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
        <td align="center">${patient.babyRegimenDelivery}</td>
        <td>${patient.deliveryNurse}</td>
        </td>
    </tr>
    </logic:iterate>
</table>

</template:put>
</template:insert>
