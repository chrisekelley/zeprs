<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri= "/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<%@ attribute name="pos" required="true" type="java.lang.Integer" %>
<%@ attribute name="remoteClass" required="true" type="java.lang.String" %>
<%@ attribute name="classname" required="true" type="java.lang.String" %>
<%@ attribute name="propertyName" required="true" type="java.lang.String" %>
<%@ attribute name="patientId" required="true" type="java.lang.Integer" %>
<%@ attribute name="pregnancyId" required="true" type="java.lang.Integer" %>
<%@ attribute name="user" required="true" type="java.lang.String" %>
<%@ attribute name="siteId" required="true" type="java.lang.Integer" %>
<%@ attribute name="value" required="false" type="java.lang.String" %>
<%@ attribute name="formId" required="true" type="java.lang.Integer" %>
<c:set var="field" value="${pageItem.form_field}" />
<c:set var="scriptName" value="replyfield${field.id}"/>
<c:choose>
    <c:when test="${! empty value}">
        <c:set var="days"  value="${value % 7}" />
        <c:set var="weeks" value="${value/7}" />
        <html:link action="pregnancyDating.do"><fmt:parseNumber value="${weeks}" integerOnly="true" />, ${days}/7</html:link>
        <html:hidden property="field${field.id}" value="${value}" />
    </c:when>
    <c:otherwise>
        <c:set var="field" value="${pageItem.form_field}" />
        <c:set var="scriptName" value="replyfield${field.id}"/>
        <div id="weeksPreg">
        <input type=hidden name=calcYet id=calcYet value=0>
        </div>
        <input type=hidden name=weeks id=weeks size="3">
        <select id="dayDropdown">
        <%--<select name="field${field.id}" id="days">--%>
            <option value=""> -- </option>
            <%--<html:select property="field${field.id}" styleId="days">--%>
            <c:forEach begin="28" end="308" step="1" var="day">
                <c:set var="days"  value="${day % 7}" />
                <c:set var="weeks" value="${day/7}" />
                <c:choose>
                    <c:when test="${egaToday == day}">
                    <option selected value="${day}"><fmt:parseNumber value="${weeks}" integerOnly="true" />, ${days}/ 7</option>
                    </c:when>
                    <c:otherwise>
                    <option value="${day}"><fmt:parseNumber value="${weeks}" integerOnly="true" />, ${days}/ 7</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </c:otherwise>
</c:choose>
