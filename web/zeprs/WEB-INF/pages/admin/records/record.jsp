<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<c:set var="labelEnd"/>
<template:put name='title' content='${encounterForm.label}${labelEnd}' direct='true'/>
<template:put name='header' content='' direct='true'/>
<template:put name='content' direct='true'>
<script language="JavaScript" type='text/javascript' src='/${appName}/js/engine2.jsp;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/${appName}/dwr/util.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/${appName}/dwr/interface/WidgetDisplay.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/${appName}/dwr/interface/Encounter.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type="text/javascript" src="/${appName}/js/dwr-generic.js;jsessionid=${pageContext.request.session.id}"></script>
<script language="JavaScript" type="text/javascript" src="/${appName}/js/dwr-display.js;jsessionid=${pageContext.request.session.id}"></script>
<script type="text/javascript" language="Javascript1.1">

<!-- Begin

function submitForm() {
    bCancel=false;
    bCancel =  validate${encounterForm.classname}(document.${encounterForm.classname});
    if (bCancel == true) {
    document.${encounterForm.classname}.submit();
    }
}
function submitAddForm() {
    bCancel=false;
    bCancel =  validate${encounterForm.classname}(document.${encounterForm.classname});
    if (bCancel == true) {
    document.forms[0].forward.value="add";
    document.${encounterForm.classname}.submit();
    }
}

function submitNoneForm() {
    bCancel=false;
    bCancel =  validate${encounterForm.classname}(document.${encounterForm.classname});
    if (bCancel == true) {
    document.forms[0].forward.value="none";
    document.${encounterForm.classname}.submit();
    }
}
//End -->
</script>
<c:choose>
    <c:when test="${! empty param.template}">
        <c:set var="formClass" value="form-print"/>
    </c:when>
    <c:otherwise>
        <c:set var="formClass" value="forms"/>
    </c:otherwise>
</c:choose>

<div id="${formClass}">
    <h2>${encounterForm.label}${labelEnd}</h2>
    <c:if test="${! empty param.referralId}">
        <c:import url="admit_to_uth.jsp"/>
    </c:if>
<logic:messagesPresent>
   <ul>
   <html:messages id="error" bundle="ApplicationResources">
      <li class="valError"><bean:write  name="error"/></li>
   </html:messages>
   </ul>
</logic:messagesPresent>
<html:form action="form${encounterForm.id}/save.do" onsubmit="return validate${encounterForm.id}(this);">
<input type="hidden" name="forward"/>
	<p><strong>Create new ${encounterForm.label} record:</strong></p>
	<%@ include file="create_new_record.jsp" %>
<html:javascript formName="form${encounterForm.id}" dynamicJavascript="true" staticJavascript="false" />
</html:form>
<c:if test="${! empty chartItems}">
	<h2>${encounterForm.label} Listing<c:if test="${! empty detailName}">: ${detailName}</c:if></h2>
	<c:url var="nextUrl" value="admin/records/list.do">
	<c:param name="formId" value="${encounterForm.id}"/>
	<c:param name="offset" value="${offset}"/>
	</c:url>
	<c:url var="prevURL" value="admin/records/list.do">
	<c:param name="formId" value="${encounterForm.id}"/>
	<c:param name="prevRows" value="${prevRows}"/>
	</c:url>
	<c:if test="${empty noNavigationWidget}">
	<p><c:if test="${! empty prevRows}"><a href='<c:out value="/${appName}/${prevURL}"/>'">&lt;&lt;</a></c:if>
	<c:if test="${empty prevRows}">&lt;&lt;</c:if>
	<bean:message key="search.navigation" bundle="TemplateResources" />
	<c:if test="${! empty offset}"><a href='<c:out value="/${appName}/${nextUrl}"/>'">&gt;&gt;</a></c:if>
	<c:if test="${empty offset}">&gt;&gt;</c:if>
	</p>
	</c:if>
	<%@ include file="/WEB-INF/pages/admin/records/record_list.jsp" %>
	<c:if test="${empty noNavigationWidget}">
	<p><c:if test="${! empty prevRows}"><a href='<c:out value="/${appName}/${prevURL}"/>'">&lt;&lt;</a></c:if>
	<c:if test="${empty prevRows}">&lt;&lt;</c:if>
	<bean:message key="search.navigation" bundle="TemplateResources" />
	<c:if test="${! empty offset}"><a href='<c:out value="/${appName}/${nextUrl}"/>'">&gt;&gt;</a></c:if>
	<c:if test="${empty offset}">&gt;&gt;</c:if>
	</p>
	</c:if>
</c:if>
</div>
<p>&nbsp;</p>
<p>&nbsp;</p>
</template:put>
</template:insert>