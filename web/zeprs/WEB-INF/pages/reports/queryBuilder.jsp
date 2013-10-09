<%@ page import="java.util.Date"%>
<%@ page import="java.util.TimeZone"%>
<%@ page import="org.cidrz.webapp.dynasite.utils.DateUtils"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    java.util.Calendar c = java.util.Calendar.getInstance();
    c.add(java.util.Calendar.MONTH, -1);
    Date date1monthpast = c.getTime();
    String DATE_FORMAT = "yyyy-MM-dd";
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
    sdf.setTimeZone(TimeZone.getDefault());
    String date1monthpastStr = sdf.format(date1monthpast);
    java.sql.Date date1monthpastSql =  java.sql.Date.valueOf(date1monthpastStr);
    pageContext.setAttribute("date1monthpast", date1monthpast);
    pageContext.setAttribute("date1monthpastSql", date1monthpastSql);
    java.sql.Date dateNow = DateUtils.getNow();
    pageContext.setAttribute("dateNow", dateNow);
    // pageContext.setAttribute("date1monthpastD", c);
%>
<template:insert template='/WEB-INF/templates/template.jsp'>
<template:put name='title' direct='true'>ZEPRS Report Query Builder</template:put>
<h2>ZEPRS Report Query Builder</h2>
<template:put name='content' direct='true'>
<jsp:useBean id="now" class="java.util.Date" />
<c:set var="theDate" value="${now}"/>
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${date1monthpast}" var="dbdate1monthpast" />
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${theDate}" var="dbNow" />

<c:choose>
    <c:when test="${empty site}">
    <c:set var="theSite" value="${patientSiteId}"/>
    </c:when>
    <c:otherwise>
    <c:set var="theSite" value="${site}"/>
    </c:otherwise>
</c:choose>

<div id="widePage">
    <h2>Query Builder</h2>
<%--
    <p>Select the field you wish to query, then select dates and site. If you would like to display more fields, click "Add Field" checkbox.</p>
--%>
    <p>Select the field you wish to query, then select site and dates. The table(s) that hold the records are listed to the left of each field.</p>
    <html:form action="/reports/queryBuilder">
    <input type="hidden" name="task" value="run"/>
	<table cellspacing="0" cellpadding="5" class="enhancedtable">
	<tr>
		<th colspan="5">Field</th>
    </tr>
	<tr>
		<td colspan="5">
            <select name="fieldId">
                <option value=""> -- Select Field -- </option>
                <c:forEach var="field" begin="0" items="${fieldList}">
                    <c:if test="${! empty field.pageItems}">
                     <c:set var="forms" value=""/>
                    <c:forEach var="pageItem" items="${field.pageItems}">
                        <c:if test="${! empty pageItem && ! empty pageItem.form}">
                        <c:set var="forms" value="${forms} ${fn:substring(pageItem.form.name,0,20)},  "/>
                        </c:if>
                    </c:forEach>
                        <c:choose>
                            <c:when test="${! empty selFieldId && field.id == selFieldId}">
                                <option selected="selected" value="${field.id}">${fn:substring(field.label,0,30)} - ${fn:substring(forms,0,200)}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${field.id}">${fn:substring(field.label,0,30)} - ${fn:substring(forms,0,200)}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </c:forEach>
            </select>
		</td>
        </tr>
        <tr>
        <th>Site</th>
        <th>Begin Date</th>
        <th>End Date</th>
<%--
        <th>Add Field?</th>
--%>
        <th colspan="2">&nbsp;</th>
    </tr>
        <tr>
        <td>
            <select name="siteId">
                <c:choose>
                    <c:when test="${theSite=='all'}">
                            <option value="0" selected="selected" >All sites</option>
                        <c:forEach var="site" begin="0" items="${sites}">
                            <c:if test="${site.inactive != 1}">
                            <option value="${site.id}">${site.name}</option>
                            </c:if>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <option value="all">All sites</option>
                        <c:forEach var="site" begin="0" items="${sites}">
                            <c:if test="${site.inactive != 1}">
                            <c:choose>
                            <c:when test="${theSite==site.id}">
                            <option value="${site.id}" selected="selected">${site.name}</option>
                            </c:when>
                            <c:otherwise>
                            <option value="${site.id}">${site.name}</option>
                            </c:otherwise>
                            </c:choose>
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </select>

        </td>

        <td>
            <zeprs:date_visit_no_form_no_label element = "queryBuilder" dateVisit = "${date1monthpastSql}" field = "bdate"/>
        </td>

		<td>
            <zeprs:date_visit_no_form_no_label element = "queryBuilder" dateVisit = "${dateNow}" field = "edate"/>
        </td>

<%--
        <td><input type="checkbox" name="addfield" value="1"/></td>
--%>

        <td colspan="2">
			<center><html:submit/></center>
		</td>
	</tr>
	</table>
    </html:form>

    <c:if test="${! empty selectedField}">
        <h2>${selectedField}</h2>
        <table class="enhancedtable">
            <th>ZEPRS ID</th>
            <th>Date Visit</th>
            <th>Value</th>
            <tr>
                <c:forEach var="item" items="${valueList}">
                <c:if test="${item.value !=null}">
                    <td><c:url value="viewEncounter.do" var="url"><c:param name="id" value="${item.id}"/><c:param name="patientId" value="${item.patientId}"/></c:url>
                <a href='<c:out value="/zeprs/${url}"/>'>${item.zeprsId}</a></td>
                    <td>${item.visitDate}</td>
                    <td>${item.value}</td>
                </c:if>
            </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</template:put>
</template:insert>