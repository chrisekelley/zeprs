<%--
  Created by IntelliJ IDEA.
  User: ckelley
  Date: Apr 11, 2005
  Time: 12:38:08 PM
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

<c:choose>
    <c:when test="${! empty param.template}">
    <c:set var="template" value="/WEB-INF/templates/template-print.jsp"/>
    </c:when>
    <c:otherwise>
    <c:set var="template" value="/WEB-INF/templates/template.jsp"/>
    </c:otherwise>
</c:choose>
<c:set var="labelEnd"/>
<c:if test="${! empty zeprs_session.sessionPatient.datePregnancyEnd}">
    <fmt:formatDate value="${zeprs_session.sessionPatient.datePregnancyBegin}" pattern="dd MMM yy" var="pregStart" />
    <fmt:formatDate value="${zeprs_session.sessionPatient.datePregnancyEnd}" pattern="dd MMM yy" var="pregEnd" />
    <c:set var="labelEnd" value=": ${pregStart} - ${pregEnd} Pregnancy"/>
</c:if>
<template:insert template='${template}'>
<template:put name='title' content='${encounterForm.label}${labelEnd}' direct='true'/>
<template:put name='content' direct='true'>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwr/interface/GenericChart.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/js/engine2.jsp;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwr/util.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwr/interface/WidgetDisplay.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwr/interface/Encounter.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type="text/javascript" src="/zeprs/js/dwr-generic.js;jsessionid=${pageContext.request.session.id}"></script>
<script language="JavaScript" type="text/javascript" src="/zeprs/js/dwr-display.js;jsessionid=${pageContext.request.session.id}"></script>
<script type="text/javascript" language="Javascript1.1">

<!-- Begin

function submitForm() {
    //var fetuses = $("selectfield63");
    if ($("selectfield53")) {
        var outcomeValue = DWRUtil.getValue("selectfield53")
        var fetusesValue = DWRUtil.getValue("selectfield63")
        if (outcomeValue == 1830) {
            if (fetusesValue == "--") {
                DWRUtil.setValue("selectfield63", 28);   // set Num. fetuses this preg. - required field.
            }
        }
    }
    bCancel=false;
    bCancel =  validateForm${encounterForm.id}(document.form${encounterForm.id});
    if (bCancel == true) {
    document.form${encounterForm.id}.submit();
    }
}

function pregCourseAbort() {
    if ($("selectfield52")) {
        var pregCourseValue = DWRUtil.getValue("selectfield52")
        if (pregCourseValue == 1410 || pregCourseValue == 1646) {
        	DWRUtil.setValue("selectfield63", 3206);   // set Num. fetuses this preg. - required field.
            if (pregCourseValue == 1410) {	// miscarriage
            	DWRUtil.setValue("selectfield53", 1830);   // set Outcome of Pregnancy
            }
        }
    }
}

// prev pregnancies form
function submitAddForm() {
    var outcome = document.getElementById("selectfield53");
        var fetuses = document.getElementById("selectfield63");
        var outcomeValue = outcome.value;
        var fetusesValue = fetuses.value;
        if (outcomeValue == 1830) {
            if (fetusesValue == "") {
                fetusesValue == 28;   // set Num. fetuses this preg. - required field.
            }
        }
    bCancel=false;
    bCancel =  validateForm${encounterForm.id}(document.form${encounterForm.id});
    if (bCancel == true) {
    document.forms[0].forward.value="add";
    document.form${encounterForm.id}.submit();
    }
}

function submitNoneForm() {
    document.forms[0].field63.value="28";   // set Num. fetuses this preg. - required field.
    bCancel=false;
    bCancel =  validateForm${encounterForm.id}(document.form${encounterForm.id});
    if (bCancel == true) {
    document.forms[0].forward.value="none";
    document.form${encounterForm.id}.submit();
    }
}

//End -->
</script>
<c:choose>
    <c:when test="${! empty param.template}">
        <c:set var="formClass" value="form-print"/>
    </c:when>
    <c:otherwise>
        <c:set var="formClass" value="widePage3"/>
    </c:otherwise>
</c:choose>

<div id="${formClass}">
    <%--<c:if test="${(encounterForm.id == '2') && (! empty zeprs_session.sessionPatient)}">
         <c:import url="../patient_records/previous_pregnancies.jsp" />
    </c:if>--%>
    <h2>${encounterForm.label}${labelEnd}</h2>
    <c:if test="${encounterForm.id == 55}"><p>Please be aware that Medications are also recorded on
        the <html:link action="/patientAnte.do" paramId="patientId" paramName="zeprs_session" paramProperty="sessionPatient.id">Antepartum</html:link> page during routine ANC visits.</p></c:if>
    <c:if test="${! empty param.referralId}">
        <c:import url="admit_to_uth.jsp"/>
    </c:if>
<logic:messagesPresent>
   <ul>
   <html:messages id="error">
      <li class="valError"><bean:write name="error"/></li>
   </html:messages>
   </ul>
</logic:messagesPresent>
<html:form action="form${encounterForm.id}/save.do?patientId=${zeprs_session.sessionPatient.id}" onsubmit="return validateForm${encounterForm.id}(this);">
<input type="hidden" name="forward"/>
<c:choose>
    <c:when test="${encounterForm.id == '2' && empty chartItems}">
        <p>Previous Pregnancies?:
            <input type="radio" name="prev" value="1"> Yes <input type="radio" name="prev" value="1" onclick="submitNoneForm();"> No
        </p>
    </c:when>
</c:choose>

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
    <c:if test="${encounterForm.id == 55}">
    <p>&nbsp;</p>
    <h2>ARV's Dispensed</h2>

    <p>You may edit ARV records on the <span style="text-decoration: underline;"><html:link action="arv">
        ARV</html:link></span> page.</p>
    <c:choose>
    <c:when test="${fn:length(chart) > 0}">
    <table cellpadding="1" cellspacing="1" class="enhancedtabletighter">
        <%@ include file="/WEB-INF/pages/encounters/encounter_form_records_view.jsp" %>
    </table>
    </c:when>
    <c:otherwise>
    <p><em>No ARV's have been dispensed for this patient.</em></p>
    </c:otherwise>
    </c:choose>
    </c:if>
<p>&nbsp;</p>
<p>&nbsp;</p>
</div>
<c:import url="../problems/problems_chart.jsp" />
</template:put>
</template:insert>