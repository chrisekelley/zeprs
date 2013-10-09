<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ page import="org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri='/WEB-INF/tlds/zeprs.tld' prefix='zeprs' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='Admin: Edit Field' direct='true'/>
<template:put name='header' content='Admin: Edit Page Item' direct='true'/>
<template:put name='content' direct='true'>
<html:errors />
<c:if test="${subject.inputType == 'druglist'}" >
<%
    pageContext.setAttribute("drugs", DynaSiteObjects.getDrugs());
%>
</c:if>

<div id="admin1">
<h2>Field Options</h2>
<html:form action="admin/pageItem/save" method="POST">
<%--<c:if test="${empty form_id}" >
<bean:parameter id="form_id"  name="form_id" />
</c:if>--%>
<c:choose>
<c:when test="${!empty param.formId}">
<input type="hidden" name="form_id" value="${param.formId}">
</c:when>
<c:otherwise>
<input type="hidden" name="form_id" value="${formId}">
</c:otherwise>
</c:choose>
<html:hidden property="formId"/>
<%--<html:hidden property="form"/>--%>
<%--<input type="hidden" name="form" value="${param.formId}">--%>
<html:hidden property="form_field.id"/>
<div class="row">
<span class="label"><bean:message key="labels.admin.field.label" />: </span><span
class="formw"><html:text property="form_field.label" size="35" /></span>
</div>
<div class="row">
<span class="label"><bean:message key="labels.admin.field.starSchemaName" />: </span><span
class="formw"><html:text property="form_field.starSchemaName" /></span>
</div>
<div class="row">
<span class="label"><bean:message key="labels.admin.field.type" />: </span><span
class="formw"><html:select property="form_field.type">
<html:option value="Text"/>
<html:option value="Enum"/>
<html:option value="Integer"/>
<html:option value="Long"/>
<html:option value="Float"/>
<html:option value="Date"/>
<html:option value="Time"/>
<html:option value="Year"/>
<html:option value="Boolean"/>
<html:option value="Yes/No"/>
<html:option value="sex">Sex</html:option>
<html:option value="MultiEnum"/>
<html:option value="Display"/>
</html:select>
<a href="#" onclick="toggle('adminHelp','help');return false;" title="Click here for help" class="section"><img border="0" src="/zeprs/images/help.gif" id="help"></a></span>
</div>
<div class="row">
<span class="label"><bean:message key="labels.admin.field.required" />: </span><span
class="formw"><html:checkbox  property="form_field.required" /></span>
</div>
<div class="row">
<span class="label"><bean:message key="labels.admin.field.minValue" />: </span><span
class="formw"><html:text property="form_field.minValue" size="3" /></span>
</div>
<div class="row">
<span class="label"><bean:message key="labels.admin.field.maxValue" />: </span><span
class="formw"><html:text property="form_field.maxValue" size="3" /></span>
</div>
<div class="row">
<span class="label"><bean:message key="labels.admin.field.units" />: </span><span
class="formw"><html:text property="form_field.units" /></span>
</div>
<div class="row">
<span class="label"><bean:message key="labels.admin.field.patientProperty" />: </span><span
class="formw"><html:select property="form_field.patientProperty">
<html:option value="">--</html:option>
<html:option value="surname"/>
<html:option value="firstName"/>
<html:option value="nrcNumber"/>
<html:option value="districtPatientid"/>
<html:option value="sex"/>
<html:option value="age_at_admission"/>
<html:option value="birthdate"/>
<html:option value="timeOfBirth"/>
</html:select></span>
</div>
<div class="row">
<span class="label"><bean:message key="labels.admin.field.encounterRecordproperty" />: </span><span
class="formw"><html:select property="form_field.encounterRecordproperty">
<html:option value="">--</html:option>
<html:option value="dateVisit"/>
</html:select></span>
</div>
<div class="row">
<span class="label"><bean:message key="labels.admin.field.patientStatusproperty" />: </span>
<span class="formw"><html:select property="form_field.patientStatusproperty">
<html:option value="">--</html:option>
<html:option value="currentFlow"/>
<html:option value="currentPregnancy"/>
<html:option value="currentLmpDate"/>
<html:option value="currentEgaDays"/>
</html:select></span>
</div>
<%--<div class="row">
<span class="label"><bean:message key="labels.admin.field.patientLabproperty" />: </span>
<span class="formw"><html:select property="form_field.patientLabproperty">
<html:option value="">--</html:option>
<html:option value="cd4Count"/>
</html:select></span>
</div>--%>
<div class="row">
<span class="label"><bean:message key="labels.admin.field.enabled" />: </span><span
class="formw">
<c:choose>
    <c:when test="${empty subject}"><input type="checkbox" name="form_field.enabled" value="on" checked="checked"></c:when>
    <c:otherwise><html:checkbox property="form_field.enabled" /></c:otherwise>
</c:choose></span>
</div>
<div class="row">
<span class="label"><bean:message key="labels.admin.field.shared" />: </span><span
class="formw"><html:checkbox property="form_field.shared" /></span>
</div>
<div class="row">
<span class="label">Field ID: </span><span
class="formw">${subject.form_field.id}</span>
</div>
<div class="row">
<span class="label">Change to Shared field:</span>
<span class="formw">
<select name="newSharedField">
<option value="${subject.form_field.id}"selected>Current field: ${fn:substring(subject.form_field.label,0,30)}</option>
    <c:forEach items="${shared_fields}" var="field">
    <option value="${field.id}">${fn:substring(field.label,0,30)} (${field.id})
    <c:if test="${field.type=='display'}"> **DISPLAY** </c:if></option>
    </c:forEach>
</select>
</span>
</div>
<c:if test="${!empty subject && subject.form_field.type == 'Enum' }">
<div class="row">
<span class="label">Enumerations: (numericValue in parenthesis) </span>
</div>
<div class="row">
<span class="select-controls">
<html:button property="_up" value="Move Up" onclick="moveUpList(_enumerations,_enumerationOrder)"/><br/>
<html:button property="_down" value="Move Down" onclick="moveDownList(_enumerations,_enumerationOrder)"/><br/>
<html:link action="/admin/enumeration/edit" onclick="return addId(this, document.adminPageItem._enumerations, ${form_id},${subject.id});"><bean:message key="labels.admin.form.editEnumeration"/></html:link><br/>
<c:url value="admin/enumeration/new.do"  var="myUrl">
    <c:param name="fieldId" value="${subject.form_field.id}"/>
    <c:param name="pageItem" value="${subject.id}"/>
    <c:param name="formId" value="${param.formId}"/>
</c:url>
<a href='<c:out value="/zeprs/${myUrl}"/>'><bean:message key="labels.admin.form.addEnumeration"/></a><br/>
<%--
<html:link action="/admin/enumeration/new" paramId="fieldId" paramName="subject" paramProperty="form_field.id"><bean:message key="labels.admin.form.addEnumeration"/></html:link><br/>
--%>
<html:link action="/admin/enumeration/delete" onclick="return addPageItemId(this, document.adminPageItem._enumerations, ${form_id}, ${subject.id});" title="Click to delete this enumeration." ><bean:message key="labels.admin.form.deleteEnumeration"/></html:link>
</span>
<span class="select">
<html:select size="25" property="_enumerations">
<logic:iterate id="enumeration" name="subject" property="form_field.enumerations">
<html:option value="${enumeration.id}">${fn:substring(enumeration.enumeration,0,40)} (${fn:substring(enumeration.numericValue,0,40)})</html:option>
</logic:iterate>
</html:select></span>
</div>
</c:if>
</div>
<%--confirm('This will delete this enumeration from the database.\n\nSure you want to continue?')--%>
<div id="admin2">
<html:hidden property="_enumerationOrder"/>
<html:hidden property="id" />
<div class="row">
    <span class="label">Item Display Options</span>
    <c:if test="${! empty subject.id}">
    	<span class="formw">Page Item Id: ${subject.id}</span>
    </c:if>
</div>
<div class="row">
<span class="label">Input Type: </span>
<span class="formw">
<html:select property="inputType">
<html:option value="text">Text</html:option>
<html:option value="text-only">Text - no label</html:option>
<html:option value="text-dwr">Text - DWR</html:option>
<html:option value="radio">Radio Button</html:option>
<html:option value="checkbox">Checkbox</html:option>
<html:option value="checkbox_dwr">Checkbox - DWR</html:option>
<html:option value="Yes/No">Yes/No</html:option>
<html:option value="yesno_dwr">Yes/No - DWR</html:option>
<html:option value="yesno_br">Yes/No - br after label</html:option>
<html:option value="yesno_no_na">Yes/No - no N/A choice</html:option>
<html:option value="textarea">Text Area</html:option>
<html:option value="textarea_dwr">Text Area - DWR - Label</html:option>
<html:option value="select">Drop-down</html:option>
<html:option value="select-only">Drop-down - no label</html:option>
<html:option value="select-dwr">Drop-down - DWR</html:option>
<html:option value="select-dwr-label">Drop-down - DWR - Label</html:option>
<html:option value="druglist">Drug List</html:option>
<html:option value="lab_results">Lab Results</html:option>
<html:option value="drug_interventions">Drug Intervention Enums</html:option>
<html:option value="currentMedicine">Current Medicine</html:option>
<html:option value="currentImmunizations">Current Immunizations</html:option>
<html:option value="position">Position diagrams</html:option>
<html:option value="position-dropdown">Position dropdown</html:option>
<html:option value="position-dropdown-dwr">Position dropdown - DWR</html:option>
<html:option value="apgar">Apgar Score</html:option>
<html:option value="sites">Site dropdown</html:option>
<html:option value="birthdate">Birth date widget (60 year option)</html:option>
<html:option value="emptyDate">Date (Empty) widget</html:option>
<html:option value="dateToday">Date (Prefilled w/ today's date)</html:option>
<html:option value="date_visit_dwr_label">Date (Empty) - DWR</html:option>
<html:option value="month">Month dropdown</html:option>
<html:option value="month_no_label">Month (no label) dropdown</html:option>
<html:option value="lmp">LMP Date widget</html:option>
<html:option value="edd">EDD Date widget</html:option>
<html:option value="ega">EGA Date widget</html:option>
<html:option value="ega_dwr">EGA Date widget - DWR</html:option>
<html:option value="ega_pregnancyDating">EGA Date widget - Pregnancy Dating</html:option>
<html:option value="display-header">Section Header</html:option>
<html:option value="display-subheader">Sub-section Header</html:option>
<html:option value="collapsing-display-header-begin">Begin Collapsing Section Header</html:option>
<html:option value="collapsing-display-header-end">End Collapsing Section Header</html:option>
<html:option value="display-tbl-begin">Begin table</html:option>
<html:option value="display-tbl-end">End table</html:option>
<html:option value="display-tbl-right-begin">Begin right div table</html:option>
<html:option value="display-tbl-right-end">End right div table</html:option>
<html:option value="display-spacer-field">Field spacer (blank td)</html:option>
<html:option value="multiselect_enum">Multiselect: Enum</html:option>
<html:option value="multiselect_item">Multiselect: Item</html:option>
<html:option value="multiselect_drugs">Multiselect: Drugs</html:option>
<html:option value="multiselect_immun">Multiselect: Immunisations</html:option>
<html:option value="collapsing-tbl-for-yesno">Collapsing table for Yes/No</html:option>
<html:option value="display_collapsing_add_item_link">Collapsing "Add item" link</html:option>
<html:option value="nrc">NRC Number Widget</html:option>
<html:option value="patientid">Patient ID: hidden field</html:option>
<html:option value="patientid_districts"> -- Patient ID: widget</html:option>
<html:option value="patientid_sites"> -- Patient ID: Site hidden field</html:option>
<html:option value="infotext">Informational Text</html:option>
<html:option value="newborn">Create Newborn table</html:option>
<html:option value="hidden_dwr">Hidden field - DWR</html:option>
<html:option value="hidden-empty">Hidden field - empty</html:option>
<html:option value="prevPregnanciesLink">Previous Pregnancies link</html:option>
<html:option value="uterus_size">Uterus Size in Weeks</html:option>
<html:option value="firm">Firm (UTH Firm)</html:option>
</html:select></span>
</div>
<div class="row">
<span class="label">Visible:</span>
<span class="formw"><c:choose>
    <c:when test="${empty subject}"><input type="checkbox" name="visible" value="on" checked="checked"></c:when>
    <c:otherwise><html:checkbox property="visible" /></c:otherwise>
</c:choose></span>
</div>

<div class="row">
    <span class="label">Dependent Field(s) for Visibility, Enum 1:</span>
    <%--<bean:write name="subject" property="displayHint.visibleDependencies"/>--%>
    <span class="formw">
    <html:select property="_dependencies1" size="10" multiple="true" >
    <html:option value="">Not Recorded </html:option>
    <logic:iterate id="pageItem" name="encounterForm" property="pageItems">
    <option
    <logic:present name="subject" property="visibleDependencies1"><c:forTokens items="${subject.visibleDependencies1}" delims="," var="dep"><c:if test="${dep==pageItem.form_field.id}">selected</c:if></c:forTokens></logic:present>
    value="${pageItem.form_field.id}"><c:if test="${pageItem.form_field.shared ==true}">*</c:if>${fn:substring(pageItem.form_field.label,0,80)}<c:if test="${pageItem.form_field.shared ==true}">*</c:if> </option>
    </logic:iterate>
    </html:select>
    </span>
</div>
<c:choose>
    <c:when test="${!empty subject && subject.form_field.type == 'Enum' }">
    <div class="row">
    <span class="label">Trigger Enum 1 for Dependent Field:</span>
    <span class="select">
    <html:select property="visibleEnumIdTrigger1">
    <html:option value="-1" >Not Recorded</html:option>
    <c:choose>
    <c:when test="${! empty drugs}">
    <c:forEach var="drug" begin="0" items="${drugs}">
        <html:option value="${drug.id}">${fn:substring(drug.name,0,40)}</html:option>&nbsp;&nbsp;
    </c:forEach>
    </c:when>
    <c:otherwise>
    <html:optionsCollection name="subject" property="form_field.enumerations" value="id" label="enumeration"/>
    </c:otherwise>
    </c:choose>
    </html:select></span>
    </div>
    </c:when>
    <c:when test="${!empty subject && subject.form_field.type == 'Yes/No' }">
    <div class="row">
    <span class="label">Trigger Enum 1 for Dependent Field: </span>
    <span class="select">
    <html:select property="visibleEnumIdTrigger1">
    <html:option value="" >Not Recorded</html:option>
    <html:option value="1">Yes</html:option>
    <html:option value="0">No</html:option>
    </html:select></span>
    </div>
    </c:when>
</c:choose>

<div class="row">
    <span class="label">Dependent Field(s) for Visibility, Enum 2:</span>
    <%--<bean:write name="subject" property="displayHint.visibleDependencies"/>--%>
    <span class="formw">
    <html:select property="_dependencies2" size="10" multiple="true" >
    <html:option value="">Not Recorded </html:option>
    <%--<html:option value="" >Not Recorded </html:option>--%>
    <logic:iterate id="pageItem" name="encounterForm" property="pageItems">
    <option
    <logic:present name="subject" property="visibleDependencies2"><c:forTokens items="${subject.visibleDependencies2}" delims="," var="dep"><c:if test="${dep==pageItem.form_field.id}">selected</c:if></c:forTokens></logic:present>
    value="${pageItem.form_field.id}"><c:if test="${pageItem.form_field.shared ==true}">*</c:if>${fn:substring(pageItem.form_field.label,0,80)}<c:if test="${pageItem.form_field.shared ==true}">*</c:if></option>
    </logic:iterate>
    </html:select>
    </span>
</div>
<c:choose>
    <c:when test="${!empty subject && subject.form_field.type == 'Enum' }">
    <div class="row">
    <span class="label">Trigger Enum 2 for Dependent Field:</span>
    <span class="select">
    <html:select property="visibleEnumIdTrigger2">
    <html:option value="-1">Not Recorded</html:option>
    <c:choose>
    <c:when test="${! empty drugs}">
    <c:forEach var="drug" begin="0" items="${drugs}">
        <html:option value="${drug.id}">${fn:substring(drug.name,0,40)}</html:option>&nbsp;&nbsp;
    </c:forEach>
    </c:when>
    <c:otherwise>
    <html:optionsCollection name="subject" property="form_field.enumerations" value="id" label="enumeration"/>
    </c:otherwise>
    </c:choose>
    </html:select></span>
    </div>
    </c:when>
    <c:when test="${!empty subject && subject.form_field.type == 'Yes/No' }">
    <div class="row">
    <span class="label">Trigger Enum 2 for Dependent Field: </span>
    <span class="select">
    <html:select property="visibleEnumIdTrigger2">
    <html:option value="">Not Recorded</html:option>
    <html:option value="1">Yes</html:option>
    <html:option value="0">No</html:option>
    </html:select></span>
    </div>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="${!empty subject && subject.form_field.type == 'Enum' }">
    <div class="row">
    <span class="label">Selected Enum:</span>
    <span class="select">
    <html:select property="selectedEnum">
    <html:option value="-1">Not Recorded</html:option>
    <c:choose>
    <c:when test="${! empty drugs}">
    <c:forEach var="drug" begin="0" items="${drugs}">
        <html:option value="${drug.id}">${fn:substring(drug.name,0,40)}</html:option>&nbsp;&nbsp;
    </c:forEach>
    </c:when>
    <c:otherwise>
    <html:optionsCollection name="subject" property="form_field.enumerations" value="id" label="enumeration"/>
    </c:otherwise>
    </c:choose>
    </html:select></span>
    </div>
    </c:when>
    <c:when test="${!empty subject && subject.form_field.type == 'Yes/No' }">
    <div class="row">
    <span class="label">Selected Enum</span>
    <span class="select">
    <html:select property="selectedEnum">
    <html:option value="">Not Recorded</html:option>
    <html:option value="1">Yes</html:option>
    <html:option value="0">No</html:option>
    </html:select></span>
    </div>
    </c:when>
</c:choose>

<div class="row">
<span class="label">If Text field:</span>
</div>
<div class="row">
<span class="label">Size: </span>
<span class="formw"><html:text property="size" size="3" /></span>
</div>
<div class="row">
<span class="label">Max Length: </span>
<span class="formw"><html:text property="maxlength" size="3" /></span>
</div>
</p>
<p>
<div class="row">
<span class="label">
If Textarea: </span><span
class="formw">For a bigger textarea, use Rows: 5, Cols:40. Otherwise, leave blank.</span>
</div>
<div class="row">
<span class="label">Rows: </span><span
class="formw"><html:text property="rows" size="3"/></span>
</div>
<div class="row">
<span class="label">Cols:</span><span
class="formw"><html:text property="cols" size="3"/></span>
</div>
</p>
<div class="row">
<span class="label">Column Number: </span><span
class="formw"><html:select property="columnNumber">
<html:option value="">-- Select --</html:option>
<html:option value="1"/>
<html:option value="2"/>
<html:option value="3"/>
</html:select></span>
</div>
<div class="row">
<span class="label">Colspan: </span><span
class="formw"><html:text property="colspan" size="2" maxlength="2"/></span>
</div>
<div class="row">
<span class="label">Close row: </span><span
class="formw"><html:checkbox property="closeRow"/></span>
</div>
<div class="row">
<p style="text-align:right;">&nbsp;
<html:submit value="Save" /> <html:link action="/admin/form/edit?id=${form_id}">Return to Form</html:link></p>
</div>
<div class="row">
<zeprs:rule_list provider="${subject.form_field}" formId="${param.formId}" pageItem = "${subject.id}"/>
</div>
</html:form>
<%--<p>&nbsp;</p>
<logic:present name="allReports">
<zeprs:report_list reports = "${reports}" allReports = "${allReports}" pageItem = "${subject}"/>
</logic:present>--%>
</div>
</template:put>
<template:put name='help'>
    <h2>Instructions: </h2>
    <p>The choice of <strong>Field Type</strong> determines how the form field is rendered and/or how it is validated.
    Some of the less obvious selections:</p>
    <ul>
    <li>Text: Text-entry box. Under Display options be sure to choose either "Text" or "Text area."</li>
    <li>Enum: Drop-down with Enumerations that have been manually populated. Under Display options be sure to choose either "Radio Button","Checkbox", or "Drop Down."</li>
    <li>Integer: Make sure you enter the Validation Min and Max. Renders as a text input box.</li>
    <li>Date: This will render as a Date widget (tbd)</li>
    <li>Year: Drop-down with Years starting from what is entered in Validation Min to this year.</li>
    </ul>
</template:put>
</template:insert>