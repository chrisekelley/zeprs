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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='Admin: Field List' direct='true'/>
<template:put name='header' content='Admin: Field List' direct='true'/>

<template:put name='content' direct='true'>
		<p>Fields that are currently being used have entries in the
		"Form/Table/PageItem input type" column.</p>
		<table cellpadding="2" cellspacing="0" bgColor="white"
			summary="Form list" border="1">
			<tr class="patientrowheader">
				<th>ID</th>
				<th>Form Field</th>
				<th>Form Field Type</th>
				<th>Form Name</th>
				<th>Form Field ID</th>
				<th>Table</th>
				<th>PageItem input type</th>
				<th>Column</th>
				<th>Field Record count</th>
				<th>Table Record count</th>
			</tr>
			<c:forEach var="field" items="${fieldList}" varStatus="status">
				<c:if test="${field.type != 'Display'}">
						<c:choose>
							<c:when test="${! empty field.pageItems}">
								<c:forEach var="pageItem" items="${field.pageItems}">
									<c:if test="${! empty pageItem.form}">
									<tr>
										<td>${field.id}</td>
										<td>${field.label}</td>
										<td>${field.type}</td>
										<td width="200"><c:if test="${! empty pageItem && ! empty pageItem.form}">
                                                     ${pageItem.form.label}
                                                 </c:if></td>
										<td><c:if test="${! empty pageItem && ! empty pageItem.form}">
                                                     ${pageItem.form.id}
                                                 </c:if></td>
										<td width="90"><c:if test="${! empty pageItem && ! empty pageItem.form}">
                                                 ${pageItem.form.name}
                                             </c:if></td>
										<td><c:if test="${! empty pageItem && ! empty pageItem.form}">
                                                 ${pageItem.inputType}
                                             </c:if>
                                             </td>
										<td>${field.starSchemaName}</td>
										<td>${pageItem.recordCount}</td>
										<td>${pageItem.form.recordCount}</td>
									</tr>
									</c:if>
								</c:forEach>

							</c:when>
							<%--
							<c:otherwise>
							<tr>
								<td>${field.id}</td>
								<td>${field.label}</td>
								<td>${field.type}</td>
								<td colspan="5">&nbsp;</td>
								<td>${field.starSchemaName}</td>
							</tr>
							</c:otherwise>
							 --%>

						</c:choose>


				</c:if>
			</c:forEach>
		</table>
	</template:put>
</template:insert>