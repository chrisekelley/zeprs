<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<template:insert template='/WEB-INF/templates/template-report.jsp'>
    <template:put name='title' direct='true'>Antenatal Monthly Summary Sheet
        for All Sites ${DailyAntenatalSummary.beginDate}
        - ${DailyAntenatalSummary.endDate}</template:put>
    <template:put name='header' direct='true'></template:put>
    <template:put name='content' direct='true'>
<logic:iterate id="siteSummary" name="DailyAntenatalSummary" property="siteSummaries" indexId="ind">

<p>Antenatal Monthly Summary Sheet
        for ${siteSummary.key} ${DailyAntenatalSummary.beginDate}
        - ${DailyAntenatalSummary.endDate}</p>
<table border="1" cellspacing="0" cellpadding="3" width="300" class="enhancedtable">
            <tr>
                <th>Date</th>
                <th>New ANC Client</th>
                <c:choose>
                    <c:when test="${!empty DailyAntenatalSummary.reportProperties}">
                        <th>Total ANC Attendees</th>
                    </c:when>
                    <c:otherwise>
                        <th>Missing ANC Forms</th>
                    </c:otherwise>
                </c:choose>
                <th>Revisits</th>
                <th>Counseled New ANC</th>
                <th>Counseled Revisit</th>
                <th>Pre-Test Counseled</th>
                <th>HIV Tested New ANC</th>
                <th>HIV Tested Revisit</th>
                <th>Total Tested</th>
                <th>Refused Testing</th>
                <th>Received Results</th>
                <c:choose>
                    <c:when test="${!empty DailyAntenatalSummary.reportProperties}">
                    </c:when>
                    <c:otherwise>
                        <th>Uncollect. Results</th>
                    </c:otherwise>
                </c:choose>
                <th>HIV Positive:</th>
                <th>HIV Negative</th>
                <th>HIV Indeterm.</th>
                <th>Referred ARV Clinic</th>
                <th>Maternal NVP</th>
                <th>Maternal AZT</th>
                <th>Infant NVP</th>
                <th>RPR Tested</th>
                <th>RPR Positive</th>
                <th>RPR Negative</th>
                <th>Syphilis Treated</th>
            </tr>

            <logic:iterate id="monthlySummary" name="siteSummary"  property="value" indexId="ind">
            <c:set var="newANCClient" value="0"/>
            <c:set var="revisits" value="0"/>
            <c:set var="counselledNew" value="0"/>
            <c:set var="counselledRevisits" value="0"/>
            <c:set var="totalANCAttendees" value="0"/>
            <c:set var="hivTestedNewAnc" value="0"/>
            <c:set var="hivTestedRevisit" value="0"/>
            <c:set var="preTestCounseled" value="0"/>
            <c:set var="hivTested" value="0"/>
            <c:set var="refusedTesting" value="0"/>
            <c:set var="receivedResults" value="0"/>
            <c:set var="uncollectedResults" value="0"/>
            <c:set var="hivPositive" value="0"/>
            <c:set var="hivNegative" value="0"/>
            <c:set var="hivIndeterminate" value="0"/>
            <c:set var="referredArt" value="0"/>
            <c:set var="maternalNVP" value="0"/>
            <c:set var="maternalAzt" value="0"/>
            <c:set var="infantNVP" value="0"/>
            <c:set var="rprTested" value="0"/>
            <c:set var="rprPositive" value="0"/>
            <c:set var="rprNegative" value="0"/>
            <c:set var="syphilisTreated" value="0"/>

            <logic:iterate id="summary" name="monthlySummary"  property="value" indexId="ind">
                <c:set var="newANCClient" value="${newANCClient + summary.value.newANCClient}"/>
                <c:set var="noRoutineAnteVisit" value="${noRoutineAnteVisit + summary.value.noRoutineAnteVisit}"/>
                <c:set var="noInitialVisit" value="${noInitialVisit + summary.value.noInitialVisit}"/>
                <c:set var="noSafeMotherhoodForm" value="${noSafeMotherhoodForm + summary.value.noSafeMotherhoodForm}"/>
                <c:set var="revisits" value="${revisits + summary.value.revisits}"/>
                <c:set var="counselledNew" value="${counselledNew + summary.value.counselledNew}"/>
                <c:set var="counselledRevisits" value="${counselledRevisits + summary.value.counselledRevisits}"/>
                <c:set var="totalANCAttendees" value="${totalANCAttendees + summary.value.totalANCAttendees}"/>
                <c:set var="hivTestedNewAnc" value="${hivTestedNewAnc + summary.value.hivTestedNewAnc}"/>
                <c:set var="hivTestedRevisit" value="${hivTestedRevisit + summary.value.hivTestedRevisit}"/>
                <c:set var="preTestCounseled" value="${preTestCounseled + summary.value.preTestCounseled}"/>
                <c:set var="hivTested" value="${hivTested + summary.value.hivTested}"/>
                <c:set var="refusedTesting" value="${refusedTesting + summary.value.refusedTesting}"/>
                <c:set var="receivedResults" value="${receivedResults + summary.value.receivedResults}"/>
                <c:set var="uncollectedResults" value="${uncollectedResults + summary.value.uncollectedResults}"/>
                <c:set var="hivPositive" value="${hivPositive + summary.value.hivPositive}"/>
                <c:set var="hivNegative" value="${hivNegative + summary.value.hivNegative}"/>
                <c:set var="hivIndeterminate" value="${hivIndeterminate + summary.value.hivIndeterminate}"/>
                <c:set var="referredArt" value="${referredArt + summary.value.referredArt}"/>
                <c:set var="maternalNVP" value="${maternalNVP + summary.value.maternalNVP}"/>
                <c:set var="maternalAzt" value="${maternalAzt + summary.value.maternalAzt}"/>
                <c:set var="infantNVP" value="${infantNVP + summary.value.infantNVP}"/>
                <c:set var="rprTested" value="${rprTested + summary.value.rprTested}"/>
                <c:set var="rprPositive" value="${rprPositive + summary.value.rprPositive}"/>
                <c:set var="rprNegative" value="${rprNegative + summary.value.rprNegative}"/>
                <c:set var="syphilisTreated" value="${syphilisTreated + summary.value.syphilisTreated}"/>
            </logic:iterate>


            <tr>
                <td class="reportRowHeader"><bean:write name="monthlySummary" property="key"/></td>
                <td>${newANCClient}</td>
                 <c:choose>
                    <c:when test="${!empty DailyAntenatalSummary.reportProperties}">
                        <td>${newANCClient + revisits}</td>
                    </c:when>
                    <c:otherwise>
                        <td>${noRoutineAnteVisit + noInitialVisit + noSafeMotherhoodForm}</td>
                    </c:otherwise>
                </c:choose>
                <td>${revisits}</td>
                <td>${counselledNew}</td>
                <td>${counselledRevisits}</td>
                <td>${preTestCounseled}</td>
                <td>${hivTestedNewAnc}</td>
                <td>${hivTestedRevisit}</td>
                <td>${hivTested}</td>
                <td>${refusedTesting}</td>
                <td>${receivedResults}</td>
                <c:choose>
                    <c:when test="${!empty DailyAntenatalSummary.reportProperties}">
                    </c:when>
                    <c:otherwise>
                        <td>${uncollectedResults}</td>
                    </c:otherwise>
                </c:choose>
                <td>${hivPositive}</td>
                <td>${hivNegative}</td>
                <td>${hivIndeterminate}</td>
                <td>${referredArt}</td>
                <td>${maternalNVP}</td>
                <td>${maternalAzt}</td>
                <td>${infantNVP}</td>
                <td>${rprTested}</td>
                <td>${rprPositive}</td>
                <td>${rprNegative}</td>
                <td>${syphilisTreated}</td>
            </tr>
            </logic:iterate>
        </table>

        </logic:iterate>
    </template:put>
</template:insert>