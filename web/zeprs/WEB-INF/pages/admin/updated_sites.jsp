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
    <template:put name='title' content='Admin: Updated Sites' direct='true'/>
    <template:put name='header' content='Admin: Updated Sites' direct='true'/>
    <template:put name='content' direct='true'>
        <script type='text/javascript'
                src='/zeprs/js/engine2.jsp;jsessionid=${pageContext.request.session.id}'></script>
        <script type='text/javascript'
                src='/zeprs/dwrdyna/util.js;jsessionid=${pageContext.request.session.id}'></script>
        <script type='text/javascript'
                src='/zeprs/dwrdyna/interface/Dynasite.js;jsessionid=${pageContext.request.session.id}'></script>
        <html:errors/>
        <p>Site updates are not yet implemented. This is simply a placeholder.</p>
        <c:if test="${! empty message}">
             <p class="bold">${message}</p>
        </c:if>
        <%--
        <c:if test="${! empty subscriptions}">
            <div class="row">
                <p class="bold">Current subscriptions</p>

                <p>Clicking "Update" in the first column will import any new patients from the remote site to the
                    current ZEPRS installation.<br/>
                    <!--You normally do not need to do this, since the system automatically performs the import.--></p>
                <table class="enhancedtable">
                    <tr>
                        <th>Site</th>
                        <th>RSS file</th>
                        <th>Record listing</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach items="${subscriptions}" var="subscription">
                        <c:url value="admin/import.do" var="updateLink"><c:param name="url" value="${subscription.url}"/><c:param name="siteId" value="${subscription.id}"/></c:url>
                        <c:url value="admin/import.do" var="importLink"><c:param name="url" value="${subscription.url}"/><c:param name="siteId" value="${subscription.id}"/><c:param name="start" value="1"/></c:url>
                        <c:url value="admin/import.do" var="viewLink"><c:param name="url" value="${subscription.url}"/><c:param name="view" value="1"/><c:param name="siteId" value="${subscription.id}"/></c:url>
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${ ! empty subscription.site}"><a href='<c:out value="/zeprs/${updateLink}"/>'>Update ${subscription.site}</a></c:when>
                                    <c:otherwise><a href='<c:out value="/zeprs/${importLink}"/>'>Import</a></c:otherwise>
                                </c:choose></td>
                            <td><a href="${subscription.url}">${subscription.url}</a></td>
                            <td><a href='<c:out value="/zeprs/${viewLink}"/>'>Patients</a></td>
                            <td><html:link action="/admin/subscription/delete" paramId="id" paramName="subscription" paramProperty="id" onclick="return confirm('Caution: Are you sure you want to delete this subscription?');self.close();">X</html:link></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>

         <c:if test="${! empty logs}">
             <div class="row">
                  <p class="bold">Update log</p>
                  <p>Log of record imports</p>
                  <table class="enhancedtable" width="800px">
                    <tr>
                        <th>Updated</th>
                        <th>RSS file Build Date</th>
                        <th>Site</th>
                        <th>Comments</th>
                    </tr>
                    <c:forEach items="${logs}" var="log">
                        <tr>
                            <td>${log.updated}</td>
                            <td>${log.builddate}</td>
                            <td>${log.site}</td>
                            <td>${log.comments}</td>
                        </tr>
                    </c:forEach>
                </table>
             </div>
         </c:if>--%>
    </template:put>
</template:insert>