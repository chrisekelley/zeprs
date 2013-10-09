<%@ page import="java.util.List,
                 java.util.LinkedList,
                 java.util.ListIterator"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:choose>
    <c:when test="${empty param.flow}">
    <c:set var="flow" value="All"/>
    </c:when>
    <c:otherwise>
    <c:set var="flow" value="${param.flow}"/>
    </c:otherwise>
</c:choose>
<template:insert template='/WEB-INF/templates/template.jsp'>
<template:put name='title' direct='true'>Patient Record</template:put>
<%--<template:put name='header' direct='true'>
${flow} Patient Records
</template:put>--%>


<c:set var="row" value="0" />
<%
//  Set up alternating row colors
String classRow = "evenRow";
%>
<c:set var="trackingField1_enabled" value="0" />
<c:set var="trackingField2_enabled" value="0" />
<logic:iterate id="encounter" indexId="j" name="patient" property="encounters">
<c:choose>
    <c:when test="${encounter.form.id==10}">
    <c:set var="trackingField1_enabled" value="1" />
    </c:when>
    <c:when test="${encounter.form.id==28}">
    <c:set var="trackingField1_enabled" value="1" />
    </c:when>
</c:choose>
</logic:iterate>
<template:put name='content' direct='true'>
<div id="history" >
<h2>Patient History: ${flow}</h2>
<%
//  Set up alternating row colors
String classRow2 = "evenRow";
%>
<table cellpadding="2" border="0" cellspacing="4" bgColor = "white" class="ruler" summary="Patient Encounters">
        <tr class="patientrowheader">
                <th>Encounter</th>
                <th>Date of Visit</th>
        </tr>
        <logic:iterate id="encounter" indexId="i" name="patient" property="encounters">
         <% //  Set up alternating row colors
        classRow2 = classRow2.equals("evenRow")? "oddRow" : "evenRow";
        %>
            <c:choose>
            <c:when test='${!empty param.flow}'>
                <logic:iterate id="flow" indexId="i" name="encounter" property="form.flows">
                <c:if test="${flow.name == param.flow}">
                <tr class ="<%=classRow2 %>">
                <td><html:link action="viewEncounter" paramId="id" paramName="encounter" paramProperty="id">
                <bean:write name="encounter" property="form.label"/>
                </html:link></td>
                <td><fmt:formatDate type="both" pattern="dd/MM/yy"   value="${encounter.auditInfo.created}" /></td>
                </tr>
                </c:if>
                </logic:iterate>
            </c:when>
            <c:otherwise>
                <tr class ="<%=classRow2 %>" >
                <td><html:link action="viewEncounter" paramId="id" paramName="encounter" paramProperty="id">
                <bean:write name="encounter" property="form.label"/>
                </html:link></td>
                <td><fmt:formatDate type="both" pattern="dd/MM/yy"   value="${encounter.auditInfo.created}" /></td>
                </tr>
            </c:otherwise>
            </c:choose>
        </logic:iterate>
</table>
</div>

</template:put>


</template:insert>