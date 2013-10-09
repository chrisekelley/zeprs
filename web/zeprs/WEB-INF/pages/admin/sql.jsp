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
    <template:put name='title' content='Admin: SQL Console' direct='true'/>
    <template:put name='header' content='Admin: SQL Console' direct='true'/>
    <template:put name='content' direct='true'>
        <html:errors/>
        <c:if test="${! empty message}">
             <p class="bold">${message}</p>
        </c:if>
        <div>
            <p>Be very careful - incorrect sql statements can corrupt the database. Usage:
            <ul>
                <li>Select: Run a select statement, returns result set.</li>
                <li>Update/insert: Execute an update,insert, or delete statement. </li>
                <li>Batch: Execute several sql statements. Each line should contain a single SQL statement.
                There should be no line breaks in each SQL statement. Follow each SQL statement with a line feed.
                No blank lines between SQL statements. You may use this in lieu of Update/insert - it will handle one-line statements.</li>
            </ul></p>
            <p>Click "log" checkbox if you'd like this sql statement to be run when the application is updated in remote ZEPRS servers.
            Log only works for Update/Inserts and Batch statements - not Batch files.</p>
            <p>Check the <html:link action="/admin/appupdates/view">Application Update Job Log</html:link> to make sure your update will be propogated to the remote servers.
            <html:form action="admin/sql/save" method="POST">
                <div class="row">
                    <span class="label">SQL type:</span>
                    <span class="formw"><html:select property="type">
                        <html:option value="select">Select</html:option>
                        <html:option value="update">Update/Insert</html:option>
                        <html:option value="batch">Batch (multiple statements)</html:option>
                        <html:option value="batchfile">Batch file (enter filename below)</html:option>
                    </html:select>
                    </span>
                </div>
                <div class="row">
                    <span class="label">Log:</span>
                    <span class="formw"><html:checkbox property="logJob"/>
                    </span>
                </div>
                <div class="row">
                    <span class="label">SQL statement:</span>
                    <span class="formw"><html:textarea property="job" rows="15" cols="65"/>
                        <html:submit value="Save"/></span>
                </div>
            </html:form>
        </div>
        <%--<c:if test="${! empty result}">
            <c:forEach var="column" items="${results.columnNames}">
                  ${column}
            </c:forEach>
        </c:if>--%>
        <c:if test="${resultUpdate != 0}"><p>Query successful: ${resultUpdate}</p></c:if>
        <c:if test="${fn:length(resultBatch) >0}"><p>Query successful: ${fn:length(resultBatch)} SQL statements</p></c:if>
        <c:if test="${! empty result}">
        <br clear="all"/>
        <div>
            <p class="bold">SQL results:</p>
            <table border="1">
                <tr>
                    <c:forEach items="${result.columnNames}" var="hdr">
                        <th><c:out value="${hdr}"/></th>
                    </c:forEach>
                </tr>
                <c:forEach items="${result.rowsByIndex}" var="row">
                    <tr>
                        <c:forEach items="${row}" var="field">
                            <td><c:out value="${field}"/></td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </div>
        </c:if>
    </template:put>
</template:insert>