<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="encounterForm" scope="request" type="org.cidrz.webapp.dynasite.valueobject.Form"/>

<logic:present name="drugs" scope="request">
    <jsp:useBean id="drugs" scope="request" type="java.util.List"/>
</logic:present>

<c:choose>
    <c:when test="${! empty param.template}">
        <c:set var="template" value="/WEB-INF/templates/template-print.jsp"/>
    </c:when>
    <c:otherwise>
        <c:set var="template" value="/WEB-INF/templates/template.jsp"/>
    </c:otherwise>
</c:choose>
<template:insert template='${template}'>
    <template:put name='title' content='${encounterForm.label}' direct='true'/>
    <template:put name="javascript" content=""/>
    <template:put name='content' direct='true'>
        <c:if test="${empty param.template}">
            <logic:present role="VIEW_INDIVIDUAL_PATIENT_RECORDS">
                <c:import url="../problems/problems_chart.jsp"/>
            </logic:present>
        </c:if>
        <c:choose>
            <c:when test="${encounterForm.id == 80}">
                <div id="fullpageForm">
                    <jsp:include page="encounter_form_layout_long.jsp"/>
                </div>
            </c:when>
            <c:when test="${encounterForm.id == 81}">
                <div id="fullpageForm">
                    <jsp:include page="encounter_form_layout_chart.jsp"/>
                </div>
            </c:when>
            <%--<c:when test="${(encounterForm.id == 92) || (encounterForm.id == 87) || (encounterForm.id == 90) || (encounterForm.id == 88) || (encounterForm.id == 91) || (encounterForm.id == 89)}">--%>
            <c:when test="${(encounterForm.id >= 87 && encounterForm.id <= 92) || (encounterForm.id >=101 && encounterForm.id <=104)}">           
                <jsp:include page="safe_motherhood.jsp"/>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${empty param.template}">
                        <div id="forms">
                            <jsp:include page="encounter_form_layout_long.jsp"/>
                            <p>&nbsp;</p>
                            <p>&nbsp;</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div id="form-print">
                            <jsp:include page="encounter_form_layout_long.jsp"/>
                            <p>&nbsp;</p>
                            <p>&nbsp;</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </template:put>
</template:insert>