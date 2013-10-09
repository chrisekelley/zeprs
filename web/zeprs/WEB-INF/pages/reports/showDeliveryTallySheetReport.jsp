<%@ page import="org.cidrz.project.zeprs.report.DeliveryTallySheetReport" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%
    DeliveryTallySheetReport report = (DeliveryTallySheetReport) request.getAttribute("DeliveryTallySheet");
%>
<template:insert template='/WEB-INF/templates/template-report.jsp'>
<template:put name='title' direct='true'>Health Centre Service Delivery Aggregation HIA.2</template:put>
<template:put name='header' direct='true'>Health Centre Service Delivery Aggregation HIA.2</template:put>
<template:put name='content' direct='true'>
<table border="0" cellspacing="0" cellpadding="3" width="800">
<tr>
<td width="75%" align="left" valign="top">
    <b>District:</b> Lusaka<br>
    <b>Health Institution: </b><%= report.getSiteName() %><br>
</td>
<td width="25%" align="left" valign="top">
    <b>Period: </b><%= report.getBeginDate() %> - <%= report.getEndDate() %><br>
</td>
<tr>
    <td colspan="2">
        Notes:
        <ul>
            <li>First ANC Attendances - count of submission of "Initial Physical Exam" form</li>
            <li>ANC Reattendances - count of patients who have submitted "Routine Antenatal visit" form more than once
                and have submitte a "Routine Antenatal visit" form at least once during this time period</li>
            <li>Total Visits - count of patients who have had any form submitted during this month. This includes
                submission of forms other than "Routine Antenatal visit"</li>
        </ul>
    </td>
</tr>
<tr>
<td colspan="2">
<table border="1" cellspacing="0" cellpadding="3" width="300" class="enhancedtable">
<tr>
    <td align="left" colspan="2" class="sectionHeader">
        <b>ANC</b>
    </td>
</tr>
<tr align="left">
    <td align="left">
        <span class="indentHeader">First ANC Attendances</span>
    </td>
    <td align="left">
        <%= report.getAncNew() %>
    </td>
</tr>
<tr>
    <td align="left">
        <span class="indentHeader">ANC Reattendances</span>
    </td>
    <td align="left">
        <%= report.getAncOld() %>
    </td>
</tr>
<tr>
    <td align="left" class="sectionHeader">
        <span>Total Visits</span>
    </td>
    <td align="left">
        <%= report.getTotalVisits() %>
    </td>
</tr>
<tr>
    <td align="left" width="850" colspan="2" class="sectionHeader">
        <b>Type of Delivery</b>
    </td>
</tr>
<tr>
    <td align="left">
        <span class="indentHeader">Normal Deliveries (1.2.1)</span>
    </td>
    <td align="left">
        <%= report.getNormalDeliveries() %>
    </td>
</tr>
<tr>
    <td align="left">
        <span class="indentHeader">Complicated Deliveries (1.2.2)</span>
    </td>
    <td align="left">
        <%= report.getComplicatedDeliveries() %>
    </td>
</tr>
<tr>
    <td align="left">
        <span class="indentHeader">All Deliveries</span>
    </td>
    <td align="left">
        <%= report.getTotalDeliveries() %>
    </td>
</tr>
<tr>
    <td align="left" width="850" colspan="2" class="sectionHeader">
        <b>Outcome</b>
    </td>
</tr>
<tr>
    <td align="left">
        <span class="indentHeader">Live Births (1.2.3)</span>
    </td>
    <td align="left">
        <%= report.getLiveBirths() %>
    </td>
</tr>
<tr>
    <td align="left">
        <span class="indentHeader">Still Births (1.2.4)</span>
    </td>
    <td align="left">
        <%= report.getStillBirths() %>
    </td>
</tr>
<tr>
    <td align="left" width="850" colspan="2" class="sectionHeader">
        Child
    </td>
</tr>
<tr>
    <td align="left">
        <span class="indentHeader">Low Birth Weight (1.3)</span>
    </td>
    <td align="left">
        <%= report.getLowBirthWeights() %>
    </td>
</tr>
<tr>
    <td align="left" width="850" colspan="2" class="sectionHeader">
        <b>Deaths</b>
    </td>
</tr>
<tr>
    <td align="left">
        <span class="indentHeader">Maternal</span>
    </td>
    <td align="left">
        <%= report.getMaternalDeaths() %>
    </td>
</tr>

<tr>
    <td align="left">
        <span class="indentHeader">Neonatal</span>
    </td>
    <td align="left">
        <%= report.getNeonatalDeaths() %>
    </td>
</tr>
<tr>
    <td align="left">
        <span class="indentHeader">Total</span>
    </td>
    <td align="left">
        <%= report.getTotalDeaths() %>
    </td>
</tr>
</table>
</td>
</tr>
</table>
</template:put>
</template:insert>