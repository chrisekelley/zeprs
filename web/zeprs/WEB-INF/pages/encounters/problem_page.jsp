<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<template:insert template='/WEB-INF/templates/template.jsp'>
<template:put name='title' content='Problem Page: Items to Resolve' direct='true'/>
<template:put name='content' direct='true'>
<div id="history" >
<h2>Problem Page: Items to Resolve</h2>
<p>The patient has conditions that require further investigations or exams. Please complete the following forms or referrals.
After you complete them, please return to this list and click "Dismiss" to clear them from this list.</p>

<table cellpadding="2" cellspacing="4" summary="Problem List" width="100%">
    <tbody>
        <tr>
            <td> <strong>Outstanding Issues:</strong></td>
        </tr>
        <tr>
            <td>
            <c:choose>
                <c:when test="${!empty patient.outcomes}">
                 <ul>
                 <c:forEach items="${patient.outcomes}" var="outcome">
                    <c:if test="${outcome.active==true}">
                    <li>
                    <c:choose>
                    <c:when test="${outcome.class.name=='org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome'}">
                       <fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm"   value="${outcome.auditInfo.lastModified}" />: <html:link action="/form${outcome.formId}/new" paramName="outcome">${outcome.form.label}</html:link>
                    </c:when>
                    <c:when test="${outcome.class.name=='org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome'}">
                       <fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm"   value="${outcome.auditInfo.lastModified}" />: <a href="http://blastforge.com/referral">Proceed to referral application</a>
                        <br/>
                        Reason: ${outcome.reason}
                    </c:when>
                    <c:otherwise>
                        ${outcome.message}
                    </c:otherwise>
                    </c:choose>
                    <html:link action="/inactivateOutcome" paramName="outcome" paramId="id" paramProperty="id"  >[dismiss]</html:link>
                    </li>
                    </c:if>
                 </c:forEach>
                 </ul>
                </c:when>
                <c:otherwise>
                No Outstanding Issues to resolve.
                </c:otherwise>
            </c:choose>
            </td>
        </tr>
     </tbody>
</table>
<p>The following list provides a history of resolved patient issues.</p>

<table cellpadding="2" cellspacing="4" summary="Problem List" width="100%">
    <tbody>
        <tr>
            <td> <strong>Resolved Issues:</strong></td>
        </tr>
        <tr>
            <td>
            <c:choose>
                <c:when test="${!empty patient.outcomes}">
                 <ul>
                 <c:forEach items="${patient.outcomes}" var="outcome">
                    <c:if test="${outcome.active==false}">
                    <li>
                    <c:choose>
                    <c:when test="${outcome.class.name=='org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome'}">
                       <fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm"   value="${outcome.auditInfo.lastModified}" />:${outcome.form.label}
                    </c:when>
                    <c:when test="${outcome.class.name=='org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome'}">
                       <fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm"   value="${outcome.auditInfo.lastModified}" />: Proceed to referral application
                        <br/>
                        Reason: ${outcome.reason}
                    </c:when>
                    <c:otherwise>
                        ${outcome.message}
                    </c:otherwise>
                    </c:choose>
                    <html:link action="/inactivateOutcome" paramName="outcome" paramId="id" paramProperty="id"  >[dismiss]</html:link>
                    </li>
                    </c:if>
                 </c:forEach>
                 </ul>
                </c:when>
                <c:otherwise>
                No resolved issues.
                </c:otherwise>
            </c:choose>
            </td>
        </tr>
    </tbody>
</table>
</div>
</template:put>
</template:insert>