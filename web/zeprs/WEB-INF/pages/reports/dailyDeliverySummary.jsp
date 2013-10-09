
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
<template:put name='title' direct='true'>Daily Delivery Summary Report for ${DailyDeliverySummary.siteName} &nbsp; ${DailyDeliverySummary.beginDate} - ${DailyDeliverySummary.endDate}</template:put>
<template:put name='header' direct='true'>Daily Delivery Summary Report for ${DailyDeliverySummary.siteName} &nbsp; ${DailyDeliverySummary.beginDate} - ${DailyDeliverySummary.endDate}</template:put>
<template:put name='content' direct='true'>
    <table border="1" cellspacing="0" cellpadding="3" width="300" class="enhancedtable">
        <tr>
            <th>Date</th>
            <th>Deliveries: </th>
            <th>HIV Tests Performed during Labor:</th>
            <th>HIV Positive Mothers: </th>
            <th>Maternal NVP Ingested: </th>
            <th>Women Opting for Formula Feeding: </th>
            <th>Women Opting for Mixed Feeding: </th>
            <th>Women Opting for Breast Feeding: </th>
            <th>Mother Alive: </th>
            <th>Mother Died: </th>
            <th>Mother to Hospital </th>
            <th>Infant Alive: </th>
            <th>Infant Died (NND): </th>
            <th>Infant to Hospital: </th>
            <th>MSB: </th>
            <th>FSB: </th>
            <th>Routine Uterotonic Given: </th>
        </tr>
        <logic:iterate id="summary" name="DailyDeliverySummary" property="dailySummaries" indexId="ind">
            <tr>
                <td class="reportRowHeader">${summary.key}</td>
                <td>${summary.value.deliveries}</td>
                <td>${summary.value.mothersTestedInLabour}</td>
                <td>${summary.value.hivPosMothers}</td>
                <td>${summary.value.maternalNvpIngested}</td>
                <td>${summary.value.womenFormulaFeeding}</td>
                <td>${summary.value.womenMixedFeeding}</td>
                <td>${summary.value.womenBreastFeeding}</td>
                <td>${summary.value.motherAlive}</td>
                <td>${summary.value.motherDied}</td>
                <td>${summary.value.motherToHospital}</td>
                <td>${summary.value.infantAlive}</td>
                <td>${summary.value.infantDied}</td>
                <td>${summary.value.infantToHospital}</td>
                <td>${summary.value.msb}</td>
                <td>${summary.value.fsb}</td>
                <td>${summary.value.routineUterotonicGiven}</td>
            </tr>
        </logic:iterate>
    </table>

</template:put>
</template:insert>