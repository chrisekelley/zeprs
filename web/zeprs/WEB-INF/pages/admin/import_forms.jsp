<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License"};
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/tlds/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='Form Import' direct='true'/>
<template:put name='header' content='Form Import' direct='true'/>
<template:put name='help' content='' direct='true'/>
<template:put name='content' direct='true'>

<c:if test="${! empty message}"><p>${message}</p></c:if>
<c:if test="${! empty comments}">
<p>
<c:url value="admin/form/edit.do?id=${comments}" var="formUrl"></c:url>
	<a href='<c:out value="/${appName}/${formUrl}"/>'>Edit and Preview New Form</a>
</p>
</c:if>
<c:if test="${!empty fileList}">
<p>Here is a list of the forms available for import into the system. <br/>
 Click on the desired name to import.</p>
<table>
<tr>
<th>Form label</th>
</tr>
<c:forEach items="${fileList}" var="file">
<c:url value="admin/form/import.do" var="url"><c:param name="fileName" value="${file}"/></c:url>
<tr><td><a href='<c:out value="/${appName}/${url}"/>'>${file}</a></td>
</c:forEach>
</table>
</c:if>
    </template:put>
</template:insert>