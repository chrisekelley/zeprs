<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri='/WEB-INF/tlds/zeprs.tld' prefix='zeprs' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='Admin: Create Form' direct='true'/>
<template:put name='header' content='Admin: Create Form' direct='true'/>
<template:put name='content' direct='true'>
<script type='text/javascript' src='/zeprs/js/engine2.jsp;jsessionid=${pageContext.request.session.id}'></script>
<script type='text/javascript' src='/zeprs/dwrdyna/util.js;jsessionid=${pageContext.request.session.id}'></script>
<script type='text/javascript' src='/zeprs/dwrdyna/interface/Dynasite.js;jsessionid=${pageContext.request.session.id}'></script>
<html:errors />
<%--<h2><bean:message key="headings.admin.form.formOptions"/></h2>--%>
<div id="admin1">
<html:form action="admin/form/save" method="POST" onsubmit="return validateAdminForm(this);">
<html:hidden property="id"/>
<input type="hidden" name="formId" value="${form_id}">
<logic:present parameter="formId" >
<bean:parameter id="form_id" name="formId" />
</logic:present>
<logic:notPresent parameter="formId">
    <logic:present parameter="id" ><bean:parameter id="form_id" name="id" /></logic:present>
</logic:notPresent>
<table>
    <tr>
        <td class="formrowlabel"><bean:message key="labels.admin.form.name"/>: </td>
        <td><html:text property="name" size="10" /></td>
        <td class="formrowlabel"><bean:message key="labels.admin.form.label"/>: </td>
        <td><html:text property="label" size="35"/></td>
    </tr>
</table>
<table border="1" cellpadding="2" cellspacing="1">
    <tr>
        <td class="formrowlabel"><bean:message key="labels.admin.form.requireReauth"/>: </td>
        <td><bean:message key="labels.global.yes"/> <html:radio property="requireReauth" value="true" />
        <bean:message key="labels.global.no"/> <html:radio property="requireReauth" value="false" /></td>
    </tr>
    <tr>
        <td class="formrowlabel"><bean:message key="labels.admin.form.requirePatient"/>:</td>
        <td><bean:message key="labels.global.yes"/> <html:radio property="requirePatient" value="true" />
<bean:message key="labels.global.no"/> <html:radio property="requirePatient" value="false" /></td>
    </tr>
    <tr>
        <td class="formrowlabel"><bean:message key="labels.admin.form.enabled"/>: </td>
        <td><bean:message key="labels.global.yes"/> <html:radio property="enabled" value="true" />
<bean:message key="labels.global.no"/> <html:radio property="enabled" value="false" /> </td>
    </tr>
    <tr>
        <td class="formrowlabel"><bean:message key="labels.admin.form.flow"/>: </td>
        <%--<td><html:select property="flow.id">--%>
        <td><html:select property="flowId">
        <logic:iterate id="flow" name="flows">
        <html:option value="${flow.id}">${flow.name}</html:option>
        </logic:iterate>
        </html:select></td>
    </tr>
    <tr>
        <td class="formrowlabel"><bean:message key="labels.admin.form.flowOrder"/>: </td>
        <td><html:text property="flowOrder" size="3"/></td>
    </tr>
    <tr>
        <td class="formrowlabel"><bean:message key="labels.admin.form.formType"/>: </td>
        <%--<td><html:select property="formType.id">--%>
        <td><html:select property="formTypeId">
            <c:forEach items="${formTypes}" var="type">
            <html:option value="${type.id}">${type.name}</html:option>
            </c:forEach>
            </html:select></td>
    </tr>
    <tr>
        <td class="formrowlabel"><bean:message key="labels.admin.form.maxSubmissions"/>: </td>
        <td><html:text property="maxSubmissions" size="3"/></td>
    </tr>
    <tr>
        <td class="formrowlabel" colspan="2" align="right"><html:submit value="Save" onclick="bCancel=false;"/></td>
    </tr>
</table></div>
<%--<zeprs:rule_list provider="${subject}" formId="${form_id}"/>--%>
<div id="admin2">
<h2>Instructions:</h2>
<ul>
<li>Name field should be short, one word name. It is used internally be the system.</li>
<li>Label field can be more descriptive - this is displayed at the top of the form.</li>
<li>Require Reauthentication: Do you need a username/password field displayed at bottom of form?
This will force user to re-authenticate before the form will be processed. </li>
<!-- In the PTS system, this field is not used. -->
<li>Require Patient: Most forms require a patient. click Yes. Only form that does not require a patient is Enrollment.</li>
<li>Enabled: Is the form enabled? Click Yes.</li>
<li>Flow: In which flow does this form belong?</li>
<li>Order in Flow: Within its flow sequence, where does this form appear? If this is a new form, you need to make sure it's next in sequence.</li>
<li>Form Type - usually, select Pregnancy or Mother and Infant.</li>
<li>Max. Submissions - How many times may the form be submitted for each patient? Enter "1" if you want the the form to be submitted only once for a patient.</li>
</ul>
<html:javascript formName="adminForm" dynamicJavascript="true" staticJavascript="false"/>
</html:form>
</div>
</template:put>
</template:insert>