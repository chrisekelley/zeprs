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
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='Report Management' direct='true'/>
<template:put name='header' content='Report Management' direct='true'/>

<template:put name='content' direct='true'>
    <html:errors />
    <html:form action="admin/report/save" method="POST" onsubmit="return validateAdminForm(this);">
    <html:hidden property="id" />
    <table>
        <tr>
            <td valign="top"><bean:message key="labels.admin.report.label"/>: </td>
            <td valign="top"><html:text property="label" /></td>
        </tr>
        <tr>
            <td valign="top"><bean:message key="labels.admin.report.sqlQuery"/>: </td>
            <td valign="top"><html:textarea rows="6" cols="40" property="sqlQuery" /></td>
        </tr>
        <tr>
            <td valign="top"><bean:message key="labels.admin.report.colLabels"/>: </td>
            <td valign="top"><html:textarea rows="6" cols="40" property="colLabels" /></td>
        </tr>
        <tr>
            <td valign="top"><bean:message key="labels.admin.report.primaryCategoryColumn"/>: </td>
            <td valign="top"><html:text property="primaryCategoryColumn" /></td>
        </tr>
        <tr>
            <td valign="top"><bean:message key="labels.admin.report.secondaryCategoryColumn"/>: </td>
            <td valign="top"><html:text property="secondaryCategoryColumn" /></td>
        </tr>
        <tr>
            <td valign="top" align="right" colspan="2">
            <html:submit property="preview" value="Preview" onclick="bCancel=false;"/>
            <html:submit value="Save" onclick="bCancel=false;"/>
            <html:javascript formName="adminReport" dynamicJavascript="true" staticJavascript="false"/>
            </html:form></td>
        </tr>
    </table>
</template:put>
</template:insert>