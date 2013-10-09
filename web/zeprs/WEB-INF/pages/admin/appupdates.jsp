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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
    <template:put name='title' content='Admin: Application Updates Job Log' direct='true'/>
    <template:put name='header' content='Admin: Application Updates Job Log' direct='true'/>
    <template:put name='content' direct='true'>
        <html:errors/>
        <c:if test="${! empty message}">
             <p class="bold">${message}</p>
        </c:if>
        <p>
            List of all updates follow. The first listing is from the Admin db - scheduled updates appear here first.
            Updates that have been applied to a local server appear in the second Zeprs listing. <br/>
            To create a new job, go to the <html:link action="/admin/sql/new">SQL console</html:link>
            and be sure that "Log" is checked. It is checked by default.
        </p>
        <c:choose>
            <c:when test="${! empty appUpdates}">
                <p>App updates: You may delete a pending app update if it has not already been installed; a link will be available in the Action column.</p>
                <table border="1">
                    <tr>
                        <th>id</th>
                        <th>Posted</th>
                        <th>Installed</th>
                        <th>Type</th>
                        <th>Job</th>
                        <th>Action</th>
                    </tr>
                    <logic:iterate id="job" name="appUpdates">
                        <tr>
                            <td>${job.id}</td>
                            <td><fmt:formatDate type="both" value="${job.dateposted}"/></td>
                            <td><fmt:formatDate type="both" value="${job.dateinstalled}"/></td>
                            <td>${job.type}</td>
                            <td>${job.job}</td>
                            <td><c:if test="${empty job.dateinstalled}">
                                <html:link
                                        href="/zeprs/admin/appupdates/delete.do;jsessionid=${pageContext.request.session.id}?id=${job.id}&delete=1"
                                        onclick="confirm('Delete this item?')">Del.</html:link>
                            </c:if>
                            </td>
                        </tr>
                    </logic:iterate>
                </table>
            </c:when>
            <c:otherwise>
                <p>No application updates.</p>
            </c:otherwise>
        </c:choose>



    </template:put>
</template:insert>