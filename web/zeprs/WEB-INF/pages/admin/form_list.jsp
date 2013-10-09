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
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='Admin: Form List' direct='true'/>
<template:put name='header' content='Admin: Form List' direct='true'/>
<template:put name='content' direct='true'>

<script language="JavaScript" type='text/javascript' src='/zeprs/js/engine2.jsp;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwrdyna/util.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwrdyna/interface/Dynasite.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwrdisplay/interface/WidgetDisplay.js;jsessionid=${pageContext.request.session.id}'></script>
    <script type="text/javascript">
        WidgetDisplay.dispatchFlows(initFlows);
        WidgetDisplay.dispatchFormTypes(initFormTypes);
    </script>
<c:choose>
    <c:when test="${param.formEnabled == 0}">
    <c:set var="sql" value="select f.id, f.label,f.name AS table_name, f.require_reauth, f.require_patient, f.is_enabled, f.form_type_id, fl.name AS flowName, fl.id AS flowId, f.flow_order, f.max_submissions, ft.name AS formType from form f, flow fl, form_type ft WHERE fl.id = f.flow_id AND ft.id = f.form_type_id  order by flowName, flow_order"/>
    </c:when>
    <c:otherwise>
    <c:set var="sql" value="select f.id, f.label,f.name AS table_name, f.require_reauth, f.require_patient, f.is_enabled, f.form_type_id, fl.name AS flowName, fl.id AS flowId, f.flow_order, f.max_submissions, ft.name AS formType from form f, flow fl, form_type ft where is_enabled=1 AND fl.id = f.flow_id AND ft.id = f.form_type_id order by flowName, flow_order"/>
    </c:otherwise>
</c:choose>
<sql:query var = "results" dataSource="jdbc/adminDB" sql="${sql}"/>
<h2>
<c:choose>
    <c:when test="${param.formEnabled == 0}">
    All forms |
    <html:link action="/admin/formList">Enabled Forms</html:link>
    </c:when>
    <c:otherwise>
    Enabled Forms |
    <html:link href="/zeprs/admin/formList.do;jsessionid=${pageContext.request.session.id}?formEnabled=0">All forms</html:link>
    </c:otherwise>
</c:choose>
 | <html:link action="/admin/form/new">Create a new form</html:link>
</h2>
    <p>Double-click value to edit. <a href="#" onclick="toggleOld('instructions')">Instructions</a> </p>
     <div id="instructions" style="display:none;">
        <h2>Instructions:</h2>
        <ul>

            <li>Label field can be descriptive - this is displayed at the top of the form.</li>
            <li>Table field should be short, one word name. It is used internally be the system.</li>
             <li>Enabled: Is the form enabled? </li>
            <li>Require Reauthentication: Do you need a username/password field displayed at bottom of form? This will
                force user to re-authenticate
                before the form will be processed. Most forms use this.</li>
            <li>Require Patient: Most forms require a patient. Only form that does not require a patient is
                Patient Registration.</li>
            <li>Flow: In which flow does this form belong?</li>
            <li>Order in Flow: Within its flow sequence, where does this form appear? If this is a new form, you need to
                make sure it's next in sequence.</li>
            <li>Form Type - usually, select Pregnancy.</li>
            <li>Max. Submissions - How many times may the form be submitted?</li>
        </ul>
    </div>
<table cellpadding="2" cellspacing="0" bgColor = "white" summary="Form list" border="1">
<tr class="patientrowheader">
<th>Form</th>
    <th>Fields</th>
<th>Table</th>
<th>Enabled?</th>
<th>Auth?</th>
<th>Patient?</th>
<th>Flow</th>
<th>Order</th>
<th>Form Type</th>
<th>Max Subs</th>

</tr>
    <c:forEach var="row" items="${results.rows}">
<tr>
    <td id="label${row.id}" ondblclick="getInputWidget('label${row.id}', 'form.label','${row.label}', 'label', '${row.id}', 40)">${row.label}</td>
    <td>
        <html:link action="/admin/form/edit" paramId="id" paramName="row" paramProperty="id">Fields</html:link>
    </td>
    <td id="name${row.id}" ondblclick="getInputWidget('name${row.id}', 'form.name','${row.table_name}', 'name', '${row.id}', 30)">${row.table_name}</td>
    <td id="enabled${row.id}" ondblclick="toggleFormElement('enabled${row.id}', 'form.enabled', 'is_enabled', '${row.id}')">${row.is_enabled}</td>
    <td id="requireReauth${row.id}" ondblclick="toggleFormElement('requireReauth${row.id}', 'form.requireReauth', 'require_reauth', '${row.id}')">${row.require_reauth}</td>
    <td id="requirePatient${row.id}" ondblclick="toggleFormElement('requirePatient${row.id}', 'form.requirePatient', 'require_patient', '${row.id}')">${row.require_patient}</td>
    <td id="flowId${row.id}" ondblclick="getSelectWidget('flowId${row.id}', 'form.flowId','${row.flowId}', 'flow_id', '${row.id}', flows)">${row.flowName}</td>
    <td id="flowOrder${row.id}" ondblclick="getInputWidget('flowOrder${row.id}', 'form.flowOrder','${row.flow_order}', 'flow_order', '${row.id}', 2)">${row.flow_order}</td>
    <td id="formTypeId${row.id}" ondblclick="getSelectWidget('formTypeId${row.id}', 'form.formTypeId','${row.form_type_id}', 'form_type_id', '${row.id}', formTypes)">${row.formType}</td>
    <td id="maxSubmissions${row.id}" ondblclick="getInputWidget('maxSubmissions${row.id}', 'form.maxSubmissions','${row.max_submissions}', 'max_submissions', '${row.id}', 2)">${row.max_submissions}</td>

</tr>
</c:forEach>
</table>

</template:put>
</template:insert>