<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<template:insert template='/WEB-INF/templates/template.jsp'>
<template:put name='title' content='ZEPRS: Record Modification Listing' direct='true'/>
<template:put name='header' content='Record Modification Listing' direct='true'/>
<template:put name='content' direct='true'>
<div id="widePage4">
<p>Specify date range if searchng for all patients. Enter ZEPRS id (5040-NNN-NNNNN-N) to search for a patient.) If you do a patient search, you do not need to specify a date range.</p>
<html:form action="/archive">
    <input type="hidden" name="task" value="run"/>
	<table cellspacing="0" cellpadding="5" class="enhancedtabletighterCell">
	<tr>
        <th>Begin Date</th>
        <th>End Date</th>
        <th>ZEPRS id (optional)</th>
        <th>&nbsp;</th>
    </tr>
	<tr>
        <td>
            <zeprs:date_visit_no_form_no_label element = "chooseReportForm" dateVisit = "${date1weekpastSql}" field = "bdate|bdate1"/>
        </td>

		<td>
            <zeprs:date_visit_no_form_no_label element = "chooseReportForm" dateVisit = "${dateNow}" field = "edate|edate1"/>
        </td>

        <td>
        	<input type = "text" name="zeprsId"/>
        </td>

        <td>
			<center><html:submit/></center>
		</td>
	</tr>
	</table>
    </html:form>

<h2>Record Modifications</h2>
    <table cellpadding="2" border="1" cellspacing="1" bgColor = "white" summary="Record Modification Listing">
        <tr class="patientrowheader">
                <th>Encounter/Date of Visit</th>
                <th>Patient</th>
                <th>Previous Value</th>
                <th>New Value</th>
                <th>Modified by</th>
                <th>Date Modified</th>
                <%--<th>Created by</th>--%>
        </tr>
        <logic:iterate id="item" name="items">
            <c:url value="viewEncounter.do" var="url">
                <c:param name="patientId" value="${item.patientId}"/>
                <c:param name="id" value="${item.encounterId}"/></c:url>
        <tr>
<%--
            <td><html:link action="viewEncounter" paramId="id" paramName="item" paramProperty="encounterId"><bean:write name="item" property="formLabel"/>, <bean:write name="item" property="dateVisit"/></html:link></td>
--%>
            <td><a href='<c:out value="/zeprs/${url}"/>'><bean:write name="item" property="formLabel"/>, <bean:write name="item" property="dateVisit"/></a></td>
            <td><bean:write name="item" property="patientName"/></td>
            <td>
                <c:choose>
                    <c:when test="${fn:length(item.previousValue) > 40}">
                        <c:forTokens items="${item.previousValue}" delims=" " var="thisItem">
                            <c:choose>
                                <c:when test="${fn:length(thisItem) > 40}">
                                    ${fn:substring(thisItem, 0,40)} (Data truncated)
                                </c:when>
                                <c:otherwise>
                                    ${thisItem}
                                </c:otherwise>
                            </c:choose>
                        </c:forTokens>
                    </c:when>
                    <c:otherwise>${item.previousValue}</c:otherwise>
                </c:choose></td>
            <td><c:choose>
                <c:when test="${fn:length(item.value) > 40}">
                    <c:forTokens items="${item.value}" delims=" " var="thisItem">
                        <c:choose>
                            <c:when test="${fn:length(thisItem) > 40}">
                                ${fn:substring(thisItem, 0,40)} (Data truncated)
                            </c:when>
                            <c:otherwise>
                                ${thisItem}
                            </c:otherwise>
                        </c:choose>
                    </c:forTokens>
                </c:when>
                <c:otherwise>${item.value}</c:otherwise>
            </c:choose>
               </td>
            <td><bean:write name="item" property="modUserName"/></td>
            <td><bean:write name="item" property="lastModified"/></td>
            <%--<td><bean:write name="item" property="CreatedUserName"/></td>--%>
        </tr>
        </logic:iterate>
    </table>
</div>
</template:put>
</template:insert>