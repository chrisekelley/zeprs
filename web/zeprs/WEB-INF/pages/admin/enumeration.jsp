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
<template:put name='title' content='Admin: Edit Enum' direct='true'/>
<template:put name='header' content='Admin: Edit Enum' direct='true'/>

<template:put name='content' direct='true'>
    <html:errors />
    <html:form action="admin/enumeration/save" method="POST" onsubmit="return validateAdminForm(this);">
    <html:hidden property="id" />
    <html:hidden property="fieldId" />
    <input type="hidden" name="pageItem" value="${param.pageItem}"/>
    <input type="hidden" name="formId" value="${param.formId}"/>
    <table>
        <tr>
            <td valign="top"><bean:message key="labels.admin.enumeration.enumeration"/>: </td>
            <td valign="top"><html:text property="enumeration" /></td>
        </tr>
        <tr>
            <td valign="top"><bean:message key="labels.admin.enumeration.numericValue"/>: </td>
            <td valign="top"><html:text property="numericValue" /></td>
        </tr>
        <tr>
            <td valign="top"><bean:message key="labels.admin.enumeration.enabled"/>: </td>
            <td valign="top"><html:checkbox property="enabled"/></td>
        </tr>
        <tr>
            <td valign="top" align="right" colspan="2">
            <html:submit value="Save" onclick="bCancel=false;"/>
            <html:javascript formName="adminEnumeration" dynamicJavascript="true" staticJavascript="false"/>
            </html:form></td>
        </tr>
    </table>
</template:put>
</template:insert>