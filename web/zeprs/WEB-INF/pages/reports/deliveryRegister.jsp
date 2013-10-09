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
<template:put name='title' direct='true'>Delivery Register - HIR.4: ${siteName}</template:put>
<template:put name='header' direct='true'>Delivery Register - HIR.4: ${siteName}</template:put>
<template:put name='content' direct='true'>
<table border="1" cellspacing="0" cellpadding="1" class="enhancedtable">
<tr>
        <%--<th align="center" valign="bottom" rowspan="3" class="small">
                  Item
              </th>--%>
    <th align="center" valign="bottom" rowspan="3">
        Delivery Register Number
    </th>
    <th align="center" valign="bottom" rowspan="3">
        Age
    </th>
    <th align="center" valign="bottom" rowspan="3">
        EGA
    </th>
    <th align="center" valign="bottom" rowspan="3">
    Grav. #
    </th>
    <th align="center" valign="bottom" rowspan="3">
        Para
    </th>
    <th align="center" valign="top" colspan="5">
        Delivery
    </th>
    <th align="center" valign="top" colspan="4">
        Baby
    </th>
    <th align="center" valign="top" colspan="1">
        Delivery Nurse
    </th>
    <th align="center" valign="top" colspan="2">
        Discharge
    </th>
</tr>
<tr>
    <td align="center" valign="bottom" rowspan="2">
        Characteristics
    </td>
    <!--<td align="center" valign="bottom" rowspan="2">
                 Intervention
             </td>-->
    <td align="center" valign="top" colspan="3">
        Third stage
    </td>
    <td align="center" valign="bottom" rowspan="2">
        Complications
    </td>
    <td rowspan="2">
        Sex
    </td>
    <td rowspan="2">
        Birth Weight
    </td>
    <td rowspan="2">
        Alive/SB
    </td>
    <td rowspan="2">
        Perinatal Problems
    </td>
    <td align="center" valign="bottom" rowspan="2">
        Staff
    </td>
        <%--<td align="center" valign="bottom" rowspan="2">
                  Vitamin K
              </td>--%>
    <td align="center" valign="bottom" rowspan="2">
        Remarks
    </td>
</tr>
<tr>
    <td align="center" valign="bottom">
        Placenta and Delivery
    </td>
    <td align="center" valign="bottom">
        Blood
    </td>
    <td align="center" valign="bottom">
        Perineum
    </td>
</tr>


<logic:iterate id="patient" name="register" property="patients" indexId="ind">
    <%--<bean:define id="deliverySumReport" name="deliveryPatient" property="deliverySumReport"/>--%>
    <c:choose>
        <c:when test="${! empty patient.babies}">
            <c:set var="numBabies" value="${fn:length(patient.babies)}"/>
        </c:when>
        <c:otherwise><c:set var="numBabies" value="1"/></c:otherwise>
    </c:choose>

    <tr>
            <%--<td align="center" rowspan="${numBabies}">
                ${ind+1}:
            </td>--%>
        <td align="center" rowspan="${numBabies}">
            <c:url value="viewEncounter.do" var="url">
                <c:param name="id" value="${patient.patientRegistration.id}"/>
                <c:param name="patientId" value="${patient.patientRegistration.patientId}"/>
                <c:param name="pregnancyId" value="${patient.patientRegistration.pregnancyId}"/>
            </c:url>
            <a href='<c:out value="/zeprs/${url}"/>'>${patient.patientRegistration.surname}, ${patient.patientRegistration.forenames}<br/>${patient.patientRegistration.patientIdNumber}</a>
            <c:if test="${! empty patient.patientRegistration.address1}"><br/>${patient.patientRegistration.address1}</c:if>
            <c:if test="${! empty patient.patientRegistration.address2}"><br/>${patient.patientRegistration.address2}</c:if>
            <%--deliverySumReport: ${patient.deliverySumReport.id}--%>
        </td>
        <td align="center" rowspan="${numBabies}">${patient.patientRegistration.currentAge}</td>
        <td align="center" rowspan="${numBabies}">${patient.ega}</td>
        <td align="center" rowspan="${numBabies}">${patient.currentPregnancyStatus.gravida}</td>
        <td align="center" rowspan="${numBabies}">${patient.currentPregnancyStatus.parity}</td>
        <td align="center" rowspan="${numBabies}"><%--${patient.deliveryCharacteristics}&nbsp;--%>
            First stage began: <bean:write name="patient" property="deliverySumReport.encounterMap.date_first_stage_beganR" ignore="true"/>
            <bean:write name="patient" property="deliverySumReport.encounterMap.first_stage_began_431R" ignore="true"/>

        </td>
        <!--<td align="center">
                  (o)
              </td>-->
        <td align="left" rowspan="${numBabies}">
            <nobr><b>Delivered at: </b>
                <bean:write name="patient" property="deliverySumReport.encounterMap.date_placenta_deliveredR" ignore="true"/>
             <bean:write name="patient" property="deliverySumReport.encounterMap.placenta_delivered_438R" ignore="true"/></nobr><br>
            <b>Mode of Delivery: </b>
            <bean:write name="patient" property="deliverySumReport.encounterMap.mode_of_delivery_447R" ignore="true"/><br/>
            <logic:present name="patient" property="deliverySumReport.encounterMap.episiotomy_performed_452R">
                  <b>Episiotomy Performed: </b><bean:write name="patient" property="deliverySumReport.encounterMap.episiotomy_performed_452R" ignore="true"/><br/>
            </logic:present>
            <nobr><b>Method: </b>
                <bean:write name="patient" property="deliverySumReport.encounterMap.placenta_delivery_method_439R" ignore="true"/></nobr>
            <br>
            <nobr><b>Type: </b>
                <bean:write name="patient" property="deliverySumReport.encounterMap.placenta_type_440R" ignore="true"/></nobr><br>
            <nobr><b>Colour: </b>
                <bean:write name="patient" property="deliverySumReport.encounterMap.colour_of_placenta_470R" ignore="true"/></nobr><br>
            <nobr><b>Weight: </b>
                <bean:write name="patient" property="deliverySumReport.encounterMap.weight_of_placenta_441R" ignore="true"/>
            </nobr><br>
            <nobr><b>State: </b>
                <bean:write name="patient" property="deliverySumReport.encounterMap.state_of_placenta_1204R" ignore="true"/></nobr><br>
            <c:if test="${patient.durationOfLabour >0}">
                Duration of Labour: ${patient.durationOfLabour}<br/>
            </c:if>
                  <b>Episiotomy Performed: </b><bean:write name="patient" property="deliverySumReport.encounterMap.episiotomy_performed_452R" ignore="true"/><br/>
        </td>
        <td align="center" rowspan="${numBabies}">
            <bean:write name="patient" property="deliverySumReport.encounterMap.blood_loss_est_462R" ignore="true"/>&nbsp;</td>
        <td align="center" rowspan="${numBabies}"><%--${patient.delivery3rdStagePerineum}&nbsp;--%></td>
        <td align="center" rowspan="${numBabies}">
            <bean:write name="patient" property="deliverySumReport.encounterMap.complications_467R" ignore="true"/>
            <bean:write name="patient" property="deliverySumReport.encounterMap.if_complications_desc_468R" ignore="true"/>&nbsp;</td>
        <c:choose>
            <c:when test="${! empty patient.babies}">
                <logic:iterate id="baby" name="patient" property="babies" length="1">
                    <td align="center">
                        <c:url value="viewEncounter.do" var="url"><c:param name="id" value="${baby.newbornEvalReport.id}"/><c:param
                                name="patientId" value="${baby.patientId}"/></c:url>
                        <a href='<c:out value="/zeprs/${url}"/>'>${baby.newbornEvalReport.encounterMap.sex_490R}</a>
                    </td>
                    <td align="center">${baby.newbornEvalReport.weight_at_birth_491R}</td>
                    <td align="center">${baby.newbornEvalReport.encounterMap.alive_sb_493R}</td>
                    <td align="center">${baby.newbornEvalReport.encounterMap.trauma_492R}</td>
                </logic:iterate>
            </c:when>
            <c:otherwise><td colspan="4">No infant</td></c:otherwise>
        </c:choose>

        <td align="center" rowspan="${numBabies}">
                ${patient.deliverySumReport.encounterMap.nurse_deliveringR}
        </td>
            <%--<td align="center" rowspan="${numBabies}">
                   (y)
               </td>--%>
        <td align="center" rowspan="${numBabies}"></td>
    </tr>
    <c:choose>
        <c:when test="${! empty patient.babies}">
            <logic:iterate id="baby" name="patient" property="babies" offset="1">
                <tr>
                    <td align="center">
                        <c:url value="viewEncounter.do" var="url"><c:param name="id" value="${baby.newbornEvalReport.id}"/><c:param
                                name="patientId" value="${baby.patientId}"/></c:url>
                        <a href='<c:out value="/zeprs/${url}"/>'>${baby.newbornEvalReport.encounterMap.sex_490R}</a>
                    </td>
                    <td align="center">${baby.newbornEvalReport.weight_at_birth_491R}</td>
                    <td align="center">${baby.newbornEvalReport.encounterMap.alive_sb_493R}</td>
                    <td align="center">${baby.newbornEvalReport.encounterMap.trauma_492R}</td>
                </tr>
            </logic:iterate>
        </c:when>
        <%--<c:otherwise><tr><td colspan="4">No infant &nbsp;</td></tr></c:otherwise>--%>
    </c:choose>

</logic:iterate>

</table>
</template:put>
</template:insert>
