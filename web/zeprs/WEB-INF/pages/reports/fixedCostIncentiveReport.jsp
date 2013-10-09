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
    <template:put name='title' direct='true'>District Fixed Cost Incentive Report
        for &nbsp;${FixedCostIncentive.siteName}&nbsp; ${FixedCostIncentive.beginDate}
        - ${FixedCostIncentive.endDate}</template:put>
    <template:put name='header' direct='true'>District Fixed Cost Incentive Report
        for &nbsp;${FixedCostIncentive.siteName}&nbsp; ${FixedCostIncentive.beginDate}
        - ${FixedCostIncentive.endDate}</template:put>
    <template:put name='content' direct='true'>
        <table border="1" cellspacing="0" cellpadding="3" width="300" class="enhancedtable">
            <tr>
                <th>Date</th>
                <th>Antenatal HIV Tested</th>
                <th>Maternal NVP prescribed</th>
                <th>Maternal AZT - new starts</th>
                <th>Maternal AZT - refills</th>
                <th>Deliveries</th>
                <th>HIV Tested on LW</th>
                <th>Routine Uterotonic</th>
            </tr>
            <c:set var="totalANCAttendees" value="0"/>
            <c:set var="hivTestedNewAnc" value="0"/>
            <c:set var="hivTestedRevisit" value="0"/>
            <c:set var="hivTested" value="0"/>
            <c:set var="hivPositive" value="0"/>
            <c:set var="hivNegative" value="0"/>
            <c:set var="hivIndeterminate" value="0"/>
            <c:set var="maternalNVP" value="0"/>
            <c:set var="maternalAztNewStart" value="0"/>
            <c:set var="maternalAztRefills" value="0"/>
            <c:set var="hivTestedInLabourWard" value="0"/>
            <c:set var="deliveries" value="0"/>
            <c:set var="routineUterotonicGiven" value="0"/>
            <logic:iterate id="summary" name="FixedCostIncentive" property="dailySummaries" indexId="ind">
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
                <c:set var="hivTested" value="${hivTested + summary.value.hivTested}"/>
                <c:set var="hivPositive" value="${hivPositive + summary.value.hivPositive}"/>
                <c:set var="hivNegative" value="${hivNegative + summary.value.hivNegative}"/>
                <c:set var="hivIndeterminate" value="${hivIndeterminate + summary.value.hivIndeterminate}"/>
                <c:set var="referredArt" value="${referredArt + summary.value.referredArt}"/>
                <c:set var="maternalNVP" value="${maternalNVP + summary.value.maternalNVP}"/>
                <c:set var="maternalAztNewStart" value="${maternalAztNewStart + summary.value.maternalAztNewStart}"/>
                <c:set var="maternalAztRefills" value="${maternalAztRefills + summary.value.maternalAztRefills}"/>
                <c:set var="hivTestedInLabourWard" value="${hivTestedInLabourWard + summary.value.hivTestedInLabourWard}"/>
                <c:set var="deliveries" value="${deliveries + summary.value.deliveries}"/>
                <c:set var="routineUterotonicGiven" value="${routineUterotonicGiven + summary.value.routineUterotonicGiven}"/>
            </logic:iterate>
            <tr>
                <td class="reportRowHeader">Total</td>
                <td>${hivTested}</td>
                <td>${maternalNVP}</td>
                <td>${maternalAztNewStart}</td>
                <td>${maternalAztRefills}</td>
                <td>${deliveries}</td>
                <td>${hivTestedInLabourWard}</td>
                <td>${routineUterotonicGiven}</td>
            </tr>
            <c:if test="${empty FixedCostIncentive.reportProperties}">
            <logic:iterate id="summary" name="FixedCostIncentive" property="dailySummaries" indexId="ind">
                <tr>
                    <td class="reportRowHeader">${summary.key}</td>
                    <td>${summary.value.hivTested}</td>
                    <td>${summary.value.maternalNVP}</td>
                    <td>${summary.value.maternalAztNewStart}</td>
                    <td>${summary.value.maternalAztRefills}</td>
                    <td>${summary.value.deliveries}</td>
                    <td>${summary.value.hivTestedInLabourWard}</td>
                    <td>${summary.value.routineUterotonicGiven}</td>
                </tr>
            </logic:iterate>

            <tr>
                <td class="reportRowHeader">Total</td>
                <td>${hivTested}</td>
                <td>${maternalNVP}</td>
                <td>${maternalAztNewStart}</td>
                <td>${maternalAztRefills}</td>
                <td>${deliveries}</td>
                <td>${hivTestedInLabourWard}</td>
                <td>${routineUterotonicGiven}</td>
            </tr>
        </c:if>
        </table>
    </template:put>
</template:insert>