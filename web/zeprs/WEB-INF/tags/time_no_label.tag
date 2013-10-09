<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
<logic:notEmpty name="form${encounterForm.id}" property="field${field.id}">
    <bean:define id="timeValue" name="form${encounterForm.id}" property="field${field.id}"/>
    <c:set var="hourPresetValue" value="${fn:substring(timeValue,0,2)}" />
    <c:set var="minutePresetValue" value="${fn:substring(timeValue,3,5)}" />
</logic:notEmpty>

<select id="hour${field.id}" onchange="calcTime('${field.id}')">
    <option value="">--</option>
    <c:forEach begin="0" end="23" step="1" var="hour">
        <c:choose>
            <c:when test="${hour < 10}">
                <c:set var="hourValue" value="0${hour}"/>
            </c:when>
            <c:otherwise>
                <c:set var="hourValue" value="${hour}"/>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${hourPresetValue == hourValue}">
                <option value="${hourValue}" selected="selected">${hourValue}</option>
            </c:when>
            <c:otherwise>
                <option value="${hourValue}">${hourValue}</option>
            </c:otherwise>
        </c:choose>

    </c:forEach>
</select>
<select id="minute${field.id}" onchange="calcTime('${field.id}')">
    <option value="">--</option>
    <c:forEach begin="0" end="59" step="1" var="minute">
        <c:choose>
            <c:when test="${minute < 10}">
                <c:set var="minuteValue" value="0${minute}"/>
            </c:when>
            <c:otherwise>
                <c:set var="minuteValue" value="${minute}"/>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${minutePresetValue == minuteValue}">
                <option value="${minuteValue}" selected="selected">${minuteValue}</option>
            </c:when>
            <c:otherwise>
                <option value="${minuteValue}">${minuteValue}</option>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</select>

<html:hidden property="field${field.id}" styleId="field${field.id}" />

<a href="#" onclick="setTimeField('${field.id}');">(Current Time)</a>