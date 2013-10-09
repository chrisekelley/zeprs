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
<c:set var="labelEnd"/>
<c:if test="${! empty zeprs_session.sessionPatient.datePregnancyEnd}">
    <fmt:formatDate value="${zeprs_session.sessionPatient.datePregnancyBegin}" pattern="dd MMM yy" var="pregStart" />
    <fmt:formatDate value="${zeprs_session.sessionPatient.datePregnancyEnd}" pattern="dd MMM yy" var="pregEnd" />
    <c:set var="labelEnd" value=": ${pregStart} - ${pregEnd} Pregnancy"/>
</c:if>
<c:choose>
<c:when test="${! empty param.template}">
<c:set var="template" value="/WEB-INF/templates/template-print.jsp"/>
</c:when>
<c:otherwise>
<c:set var="template" value="/WEB-INF/templates/template.jsp"/>
</c:otherwise>
</c:choose>
<template:insert template='${template}'>
<template:put name='title' direct='true'>Routine Antenatal Visits${labelEnd}</template:put>

<script type="text/javascript">
</script>
<script type="text/javascript" language="Javascript1.1">
<!-- // Begin

function submitForm() {
    bCancel=false;
    bCancel =  validateForm${encounterForm.id}(document.form${encounterForm.id});
    if (bCancel == true) {
    document.form${encounterForm.id}.submit();
    }
}
//End -->
</script>

<c:set var="row" value="0" />
<%
//  Set up alternating row colors
String classRow = "evenRow";
%>

<template:put name='content' direct='true'>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwr/interface/Antenatal.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/js/engine2.jsp;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwr/util.js;jsessionid=${pageContext.request.session.id}'></script>

<script language="JavaScript" type='text/javascript' src='/zeprs/dwr/interface/WidgetDisplay.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwr/interface/Encounter.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type="text/javascript" src="/zeprs/js/dwr-generic.js;jsessionid=${pageContext.request.session.id}"></script>
<script language="JavaScript" type="text/javascript" src="/zeprs/js/dwr-display.js;jsessionid=${pageContext.request.session.id}"></script>

<logic:empty name="sessionPatient" property="parentId">
<c:choose>
    <c:when test="${! empty param.template}">
    <div id="patientStatusChart-print">
    </c:when>
    <c:otherwise>
    <div id="patientStatusChart">
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${empty zeprs_session.sessionPatient.datePregnancyEnd}">
    <table cellpadding="0" cellspacing="0" bgColor = "white" summary="Patient Status Chart Bar" width="100%">
    <thead>
        <tr class="patientrowheader">
            <th>First Pregnancy Visit</th>
            <th>EDD</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td class="patientrow"><c:if test="${!empty sessionPatient.pregnancyBeginEncounterId}"><html:link action="viewEncounter" paramId="id" paramName="sessionPatient" paramProperty="pregnancyBeginEncounterId" title="View the first record for this pregnancy."><fmt:formatDate type="both" pattern="dd/MM/yy" value="${sessionPatient.datePregnancyBegin}" /></html:link></c:if></td>
            <td class="patientrow"><c:if test="${!empty sessionPatient.currentEgaDateCalc}"><html:link action="viewEncounter" paramId="id" paramName="sessionPatient" paramProperty="currentLmpDateEncounterId" title="View the record for this value."><fmt:formatDate type="both" pattern="dd/MM/yy" value="${sessionPatient.currentEgaDateCalc}" /></html:link></c:if></td>
        </tr>
    </tbody>
    </table>
    </c:when>
    <c:otherwise>
    <table cellpadding="0" cellspacing="0" bgColor = "white" summary="Patient Status Chart Bar" width="100%">
    <thead>
        <tr class="patientrowheader">
            <th>&nbsp;</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td class="patientrow">&nbsp;</td>
        </tr>
    </tbody>
    </table>
    </c:otherwise>
</c:choose>
</div>
</logic:empty>

<div id="formChart">
<strong>Routine Antenatal Visits${labelEnd}</strong><br/>
<logic:messagesPresent>
   <ul>
   <html:messages id="error">
      <li class="valError"><bean:write name="error"/></li>
   </html:messages>
   </ul>
</logic:messagesPresent>

<html:form action="form${encounterForm.id}/save.do?patientId=${zeprs_session.sessionPatient.id}" onsubmit="return validateForm${encounterForm.id}(this);">
    <c:choose>
        <c:when test="${! empty chartItems}">
            <table cellpadding="1" cellspacing="1" class="enhancedtabletighter">
        </c:when>
        <c:otherwise>
            <table cellpadding="1" cellspacing="1" class="enhancedtabletighter">
        </c:otherwise>
    </c:choose>
<%@ include file="/WEB-INF/pages/encounters/encounter_form_layout_chart.jsp" %>
</table>
<html:javascript formName="form${encounterForm.id}" dynamicJavascript="true" staticJavascript="false"/>
</html:form>
<p>&nbsp;</p>
<p>&nbsp;</p>
</div>
<c:if test="${empty param.template}">
<c:import url="../problems/problems_chart.jsp" />
</c:if>

</template:put>
</template:insert>