<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/tlds/struts-tiles.tld' prefix='template' %>
<%@ taglib uri='/WEB-INF/tlds/zeprs.tld' prefix='zeprs' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
    <template:put name='title' content='Admin: Action Output Messages' direct='true'/>
    <template:put name='header' content='Admin: Action Output Messages' direct='true'/>
    <template:put name='content' direct='true'>
        <html:errors/>
        <c:if test="${! empty message}">
             <p class="bold">${message}</p>
        </c:if>

        <c:if test="${! empty messageList}">
        	<p>
             <c:forEach items="${messageList}" var="message">
             ${message}<br/>
             </c:forEach>
             </p>
        </c:if>
        <c:if test="${! empty nextLink}">
        <c:url value="${nextLink}" var="next"><c:param name="${nextLinkParam}" value="${nextLinkParamValue}"/></c:url>
             <p>
             <a href='<c:out value="/${appName}/${next}"/>'>${nextLinkText}</a></p>
        </c:if>
        <div>
           <c:if test="${resultUpdate >0}"><p>Query successful: ${resultUpdate} SQL statements executed.</p></c:if>
        </div>
    </template:put>
</template:insert>