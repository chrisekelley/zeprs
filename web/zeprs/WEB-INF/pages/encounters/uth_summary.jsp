<%@ page import="java.util.List,
                 org.cidrz.webapp.dynasite.logic.PersistenceManagerFactory,
                 org.cidrz.webapp.dynasite.valueobject.Site,
                 org.cidrz.webapp.dynasite.valueobject.Drugs,
                 org.cidrz.project.zeprs.valueobject.DrugRegimen,
                 org.cidrz.webapp.dynasite.session.SessionUtil,
                 org.cidrz.webapp.dynasite.valueobject.District"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="encounterForm" scope="request" type="org.cidrz.webapp.dynasite.valueobject.Form" />

<logic:present name="drugs" scope="request">
    <jsp:useBean id="drugs" scope="request" type="java.util.List" />
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
<template:put name='content' direct='true'>
<c:if test="${empty param.template}">
    <c:if test="${encounterForm.id != 1}">
        <c:import url="../problems/problems_chart.jsp" />
        <logic:present name="tasks" scope="request">
            <div id="tasks">
            <h2>${encounterForm.flow.name}</h2>
            <p>The following list displays encounters within this stage.</p>
            <table class="enhancedtable">
            <thead>
            <th colspan="2">Form</th>
            <th colspan="2">Status</th>
            </thead>
            <tbody>
            <logic:iterate id="task" name="tasks">
            <c:choose>
                <c:when test="${task.submissions >= 1}">
                <c:set var="doneDialogue" value="Available"/>
                </c:when>
                <c:otherwise>
                <c:set var="doneDialogue" value="Not Done"/>
                </c:otherwise>
             </c:choose>
            <c:if test="${task.flowId ==encounterForm.flow.id}">
                <c:choose>
                <c:when test="${task.active=='true'}">
                    <c:choose>
                        <c:when test="${task.formId == 80}">
                            <tr>
                                <td><html:link action="/patientAnte.do" styleClass="noBorder"><img src="/zeprs/images/checkbox.gif" height="15px" width="15px"/></html:link></td>
                                <td><html:link action="/patientAnte.do"><bean:write name="task" property="label"/></html:link></td>
                                <td colspan="4" class="error">${doneDialogue}</td>
                            </tr>
                        </c:when>
                        <c:when test="${task.formId == 4}">
                            <tr>
                                <td><html:link action="/patientSafeMother.do" styleClass="noBorder"><img src="/zeprs/images/checkbox.gif" height="15px" width="15px"/></html:link></td>
                                <td><html:link action="/patientSafeMother.do"><bean:write name="task" property="label"/></html:link></td>
                                <td colspan="4" class="error">${doneDialogue}</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td><img src="/zeprs/images/checkbox.gif" height="15px" width="15px"/></td>
                                <td><html:link action="/form${task.formId}/new.do"><bean:write name="task" property="label"/></html:link></td>
                                <td colspan="2" class="error">${doneDialogue}</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                <c:if test="${!empty task.encounterId}">
                <c:choose>
                    <c:when test="${task.encounterId == 0}">
                    <tr>
                        <td><img src="/zeprs/images/checkboxChecked.gif" height="15px" width="15px"/></td>
                        <td><html:link action="/form${task.formId}/new.do">Create Newborn Record</html:link></td>
                        <td>Done</td><td><bean:write name="task" property="label"/>: <fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.created}" /></td>
                    </tr>
                    </c:when>
                    <c:when test="${task.formId == 80}"></c:when>
                    <c:when test="${task.formId == 4}">
                    <tr>
                        <td><img src="/zeprs/images/checkboxChecked.gif" height="15px" width="15px"/></td>
                        <td><html:link action="/patientSafeMother.do"><bean:write name="task" property="label"/></html:link></td><td>Available</td><td><fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.created}" /></td>
                    </tr>
                    </c:when>
                    <c:otherwise>
                    <tr>
                        <td><img src="/zeprs/images/checkboxChecked.gif" height="15px" width="15px"/></td>
                        <td><bean:write name="task" property="label"/></td><td>Done</td><td><html:link action="/viewEncounter.do" paramId="id" paramName="task" paramProperty="encounterId"><fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.created}" /></html:link></td>
                    </tr>
                    </c:otherwise>
                </c:choose>
                </c:if>
                </c:otherwise>
                </c:choose>
            </c:if>
            </logic:iterate>
            </tbody>
            </table>
            </div>
        </logic:present>
    </c:if>
</c:if>
    <c:choose>
    <c:when test="${encounterForm.id == 1}">
    <div id="midpageForm">
    <jsp:include page="encounter_form_layout_long.jsp"/>
    </div>
    </c:when>
    <c:when test="${encounterForm.id == 80}">
    <div id="fullpageForm">
    <jsp:include page="encounter_form_layout_dwr.jsp"/>
    </div>
    </c:when>
    <c:when test="${encounterForm.id == 81}">
    <div id="fullpageForm">
    <jsp:include page="encounter_form_layout_chart.jsp"/>
    </div>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${empty param.template}">
            <div id="forms">
            <jsp:include page="encounter_form_layout_long.jsp"/>
            </div>
            </c:when>
            <c:otherwise>
            <div id="form-print">
            <jsp:include page="encounter_form_layout_long.jsp"/>
            </div>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
    </c:choose>
</template:put>
</template:insert>