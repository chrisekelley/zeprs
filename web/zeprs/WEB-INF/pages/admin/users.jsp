<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='User Administration' direct='true'/>
<template:put name='header' content='User Administration' direct='true'/>
<template:put name='help' content='' direct='true'/>
<template:put name='content' direct='true'>
    <script language="JavaScript" type='text/javascript' src='/zeprs/dwruseradmin/util.js;jsessionid=${pageContext.request.session.id}'></script>
    <script language="JavaScript" type='text/javascript' src='/zeprs/dwruseradmin/interface/User.js;jsessionid=${pageContext.request.session.id}'></script>
    <script language="JavaScript" type="text/javascript" src="/zeprs/js/dwr-user-admin.js;jsessionid=${pageContext.request.session.id}"></script>
    <script language="JavaScript" type='text/javascript' src='/zeprs/dwrdisplay/interface/WidgetDisplay.js;jsessionid=${pageContext.request.session.id}'></script>
    <script language="JavaScript" type="text/javascript" src="/zeprs/js/dwr-generic.js;jsessionid=${pageContext.request.session.id}"></script>
    <script language="JavaScript" type="text/javascript" src="/zeprs/js/dwr-display.js;jsessionid=${pageContext.request.session.id}"></script>

    <script src="/zeprs/js/scriptaculous/prototype.js" type="text/javascript"></script>
	<script src="/zeprs/js/scriptaculous/scriptaculous.js" type="text/javascript"></script>
    <c:choose>
        <c:when test="${! empty param.rowCount}">
            <c:set var="rowCount" value="${param.rowCount}"/>
        </c:when>
        <c:otherwise>
            <c:set var="rowCount" value="15"/>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${! empty param.offset}">
            <c:set var="offset" value="${param.offset}"/>
        </c:when>
        <c:otherwise>
            <c:set var="offset" value="0"/>
        </c:otherwise>
    </c:choose>
    <!-- <div id="content-admin"> -->
        <p>Search for User (enter username only):
            <html:form action="admin/users.do">
                <input id="searchBox" type="text" size="15" maxlength="20" name="searchUsername"><html:submit value="Search!"/>
                <c:if test="${! empty error}"><p class="error">${error}</p></c:if>
            </html:form>
            </p>
            
        <c:url value="admin/records/list.do" var="userCreateLink"><c:param name="formId" value="125"/></c:url>
        <p><a href='<c:out value="/${appName}/${userCreateLink}"/>'>Create New User</a>
        <p>Click a field in a user record to modify it. For text entry fields, press the tab key to submit your change. </p>
        <p>The username cannot be modified. 
        <br/>
        <br/>
        Assign/modify a user's group by clicking in the Group field of the user record.</p>
        </p>
        <table class="enhancedtable">
            <tr>
                <th>Surname</th>
                <th>First names</th>
                <th>Username</th>
                <th>Password</th>
                <th>Phone</th>
                <th>Mobile</th>
                <th>E-mail</th>
                <th>Group</th>
            </tr>
        <logic:iterate id="user" name="users">
               <tr>
                   <td id="lastName${user.id}">
                       <span id="valuelastName${user.id}" class="stretchToCell" onclick="displayInputField('${user.id}', 'lastName')">${user.lastName}</span>
                       <span id="widgetlastName${user.id}"></span>
                   </td>
                   <td id="firstName${user.id}">
                       <span id="valuefirstName${user.id}" class="stretchToCell" onclick="displayInputField('${user.id}', 'firstName')">${user.firstName}</span>
                       <span id="widgetfirstName${user.id}"></span>
                   </td>
                   <td>${user.id}</td>
                   <td id="password${user.id}">
                       <span id="valuePassword${user.id}" class="stretchToCell" onclick="displayInputField('${user.id}', 'Password')"> --------- </span>
                       <span id="widgetPassword${user.id}"></span>
                   </td>
                   <td id="phone${user.id}">
                       <span id="valuephone${user.id}" class="stretchToCell" onclick="displayInputField('${user.id}', 'phone')">
                       		<c:choose>
                       			<c:when test="${! empty user.phone}"><p>${user.phone}</p></c:when>
                       			<c:otherwise>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:otherwise>
                   			</c:choose>
                       </span>
                       <span id="widgetphone${user.id}"></span>
                   </td>
                   <td id="mobile${user.id}">
                       <span id="valuemobile${user.id}" class="stretchToCell" onclick="displayInputField('${user.id}', 'mobile')">
                       <c:choose>
                   			<c:when test="${! empty user.mobile}"><p>${user.mobile}</p></c:when>
                   			<c:otherwise>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:otherwise>
               			</c:choose>
                       </span>
                       <span id="widgetmobile${user.id}"></span>
                   </td>
                    <td id="email${user.id}" class="stretchRow">
                       <span id="valueemail${user.id}" class="stretchToCell" onclick="displayInputField('${user.id}', 'email')">
                       <c:choose>
                   			<c:when test="${! empty user.email}"><p>${user.email}</p></c:when>
                   			<c:otherwise>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:otherwise>
               			</c:choose>
                       </span>
                       <span id="widgetemail${user.id}"></span>
                   </td>
                   <td id="group${user.id}">
                       <span id="value${user.id}" class="stretchToCell" onclick="callGroups('${user.id}')">${user.groupName}</span>
                       <span id="widget${user.id}"></span>
                   </td>
               </tr>
        </logic:iterate>
            <c:choose>
                <c:when test="${! empty search}">
                <tr>
                    <td colspan="8" align="center"><html:link href="users.do;jsessionid=${pageContext.request.session.id}">Browse User List</html:link></td>
                </tr>
                </c:when>
                <c:otherwise>
                 <tr>
                <c:choose>
                    <c:when test="${offset == 0}">
                        <td colspan="4">&nbsp;</td>
                    </c:when>
                    <c:otherwise>
                        <td colspan="4"><html:link href="users.do;jsessionid=${pageContext.request.session.id}?offset=${offset-rowCount}">Prev</html:link></td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${offset == 0}">
                        <td colspan="4" align="right"><html:link href="/zeprs/admin/users.do;jsessionid=${pageContext.request.session.id}?offset=${rowCount}">Next</html:link></td>
                    </c:when>
                    <c:when test="${offset == 'stop'}">
                        <td colspan="4" align="right">&nbsp;</td>
                    </c:when>
                    <c:otherwise>
                        <td colspan="4" align="right"><html:link href="/zeprs/admin/users.do;jsessionid=${pageContext.request.session.id}?offset=${offset + rowCount}">Next</html:link></td>
                    </c:otherwise>
                </c:choose>

            </tr>
                </c:otherwise>
            </c:choose>

    </table>
<!--     </div> -->
</template:put>
</template:insert>