<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
<c:when test='${param.id != null}'>
<c:set var="url" value="saveCorrection"/>
</c:when>
<c:otherwise>
<c:set var="url" value="save"/>
</c:otherwise>
</c:choose>

<c:set var="tblItem" value="0"/>
<c:set var="tblCols" value="0"/>
<c:set var="tdBackgroundColor" value="#fff"/>
<c:set var="collapsing" value="0"/>
    <c:choose>
        <c:when test="${! empty encounter}">
        <tr>
        </c:when>
        <c:otherwise>
        <tr class="highlightRow">
        </c:otherwise>
    </c:choose>
        <c:if test="${encounterForm.id == '80'}">
        <td class="enhancedtabletighterCell">
        <c:choose>
            <c:when test="${! empty encounter}">
                <logic:notEmpty name="encounter" property="dateVisit">
                    <bean:define id="thisValue" name="encounter" property="dateVisit" />
                    <zeprs:date_visit_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" value="${thisValue}"/>
                </logic:notEmpty>
                <logic:empty name="encounter" property="dateVisit">
                    <zeprs:date_visit_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}"/>
                </logic:empty>
            </c:when>
            <c:otherwise>
                <zeprs:date_visit_only pageItem="${pageItem}" pos="${pos}"/>
            </c:otherwise>
        </c:choose>
        </td>
        </c:if>
        <%-- loop through the pageItems and build the form--%>
        <c:set var="numRows" value="0"/>
        <c:forEach var="pageItem" begin="0" items="${encounterForm.pageItems}" varStatus="lineInfo">
            <c:if test='${pageItem.form_field.enabled ==true && pageItem.form_field.type != "Display"}'>
            <c:set var="numRows" value="${lineInfo.index+1}"/>
            <c:set var="field" value="${pageItem.form_field}" />
            <c:set var="currentField" value="${field.id}" scope="request"/>
                <c:choose>
                    <c:when test="${! empty encounter}">
            <td id="field${field.id}Cell${pos}" onclick="justReveal('field${field.id}Field${pos}')" valign="middle" class="enhancedtabletighterCell">
                    </c:when>
                    <c:otherwise>
            <td id="field${field.id}Cell${pos}" valign="middle" class="enhancedtabletighterCell">
                    </c:otherwise>
                </c:choose>
                <%@ include file="/WEB-INF/pages/encounters/dwr_form_fields.jsp" %>
            </c:if>
        </c:forEach>
    </tr>
    <c:if test="${empty encounter}">
    <tr class="highlightRow">
        <td colspan="${numRows}">
        <c:choose>
        <c:when test='${encounterForm.requireReauth}'>
            <zeprs:re_auth pageItem="${pageItem}"/>
            <%--<html:submit onclick="bCancel=false;" />--%>
            <input type="button" value="Submit" onclick="submitForm();"/>
        </c:when>
        <c:otherwise>
            <input type="button" value="Submit" onclick="submitForm();"/>
        </c:otherwise>
        </c:choose>
        </td>
    </tr>
    </c:if>

