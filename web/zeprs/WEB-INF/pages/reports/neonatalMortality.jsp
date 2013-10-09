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
<template:put name='title' direct='true'>Neonatal Mortality Register for ${register.siteName}&nbsp;${register.beginDate} - ${register.endDate}</template:put>
<template:put name='header' direct='true'>Neonatal Mortality Register for ${register.siteName}&nbsp;${register.beginDate} - ${register.endDate}</template:put>
<template:put name='content' direct='true'>
    <p>Notes:
        <ul>
            <li>Admissions data is taken from the problem/labour visit or observations visit where dilatation >=4. </li>
            <li>Last BP - Is this for the infant? We currently do not capture BP for infant.</li>
            <li>1st EGA: Gestational Age at First Visit - Taking the first record of EGA in Pregnancy Dating form.</li>
            <li>Outcome - Getting data about Still birth and neonatal death from Newborn Eval form.</li>
        </ul>
    </p>
<table border="1" cellspacing="0" cellpadding="3" class="enhancedtable">
<tr>
    <th colspan="11">Mother</th>
    <th colspan="6">Newborn</th>
</tr>
<tr>
    <th>
        Age
    </th>
    <th>
        Parity
    </th>
    <th>
        LMP
    </th>
    <th>
        EDD
    </th>
    <th>
        1st EGA
    </th>
    <th>
        # ANC Visits
    </th>
    <th>
        ANC Clinic(s)
    </th>
    <th>
        RPR
    </th>
    <th>
        Date/Time of Admission
    </th>
    <th>
        BP
    </th>
    <th>
        Dilation at Admission
    </th>
    <th>
        ZEPRS ID
    </th>
    <th>
        Fetal HR on Admission
    </th>
    <th>
        Date/Time of Birth
    </th>
    <th>
        Birthweight
    </th>
    <th>
        EGA
    </th>
    <th>
        Outcome
    </th>
</tr>

<c:set var="smRegisterString" value="unknown"/>
<c:set var="patientName" value="unknown"/>
<logic:iterate id="patient" name="register" property="patients">

<tr>
     <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.motherData}">
                <html:link action="/patientHome.do" paramId="patientId" paramName="patient" paramProperty="motherData.patientId">${patient.motherData.currentAge}</html:link>
            </c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.motherParity}">${patient.motherParity}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.currentPregnancyStatus}">${patient.currentPregnancyStatus.lmp}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.currentPregnancyStatus}">${patient.currentPregnancyStatus.edd}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
     <td align="center" class="small">
         <c:choose>
            <c:when test="${! empty patient.egaFirstVisit}">${patient.egaFirstVisit}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
     </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.numberMotherAncVisits}">${patient.numberMotherAncVisits}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.motherAncClinics}">${patient.motherAncClinics}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
     <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.rprResults}">${patient.rprResults}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.admissionLabour}">${patient.admissionLabour}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.bpMother}">${patient.bpMother}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.dilatation}">${patient.dilatation}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.infantData.patientIdNumber}">
            <html:link action="/patientHome.do" paramId="patientId" paramName="patient" paramProperty="infantData.patientId">${patient.infantData.patientIdNumber}</html:link></c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.fhr}">${patient.fhr}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.birthDate}">${patient.birthDate} &nbsp; ${patient.birthtime}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.birthweight}">${patient.birthweight}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small">
        <c:choose>
            <c:when test="${! empty patient.egaInfant}">${patient.egaInfant}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small"><c:choose>
            <c:when test="${! empty patient.outcome}">${patient.outcome}</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose> </td>
</tr>
</logic:iterate>
</table>

</template:put>
</template:insert>