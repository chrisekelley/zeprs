<%@ page import="org.cidrz.project.zeprs.report.CBoHQuarterlySelfAssessmentReport"%>
<%@ page import="org.cidrz.project.zeprs.report.DeliveryTallySheetReport"%>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%
	CBoHQuarterlySelfAssessmentReport report = (CBoHQuarterlySelfAssessmentReport) request.getAttribute("CBoHQuarterly");

	int numPatients = report.getNewPatients();
%>
<template:insert template='/WEB-INF/templates/template-report.jsp'>
<template:put name='title' direct='true'>CBoH Health Centre Quarterly Self-Assessment HIQ.1: <%= report.getBeginDate() %> - <%= report.getEndDate() %></template:put>
<template:put name='header' direct='true'>CBoH Health Centre Quarterly Self-Assessment HIQ.1</template:put>
<template:put name='content' direct='true'>
<table border="0" cellspacing="0" cellpadding="3" width="800">
<tr>
<td width="75%" align="left" valign="top">
    <b>District:</b> Lusaka<br>
    <b>Health Institution: </b><%= report.getSiteName() %><br>
</td>
<td width="25%" align="left" valign="top">
<p><strong>Period:</strong> <%= report.getBeginDate() %> - <%= report.getEndDate() %></p>
</td>
<tr>
    <td colspan="2">
        Notes:
        <ul>
            <li>First ANC Attendances - count of submission of "Initial Physical Exam" form</li>
        </ul>
    </td>
</tr>
<tr>
<td colspan="2">
<table border="1" cellspacing="0" cellpadding="3" width="300" class="enhancedtable">
<tr align="left">
    <td align="left">First ANC Attendances</td>
    <td align="left">
        <%= report.getNewPatients() %>
    </td>
</tr>
    <tr align="left">
        <td align="left">Total Deliveries</td>
        <td align="left">
            <%= report.getTotalDeliveries() %>
        </td>
    </tr>
    <tr align="left">
        <td align="left">STD Cases this period</td>
        <td align="left">
            <%= report.getRprTotalPos() %>
        </td>
    </tr>
    <tr align="left">
        <td align="left">STD Cases this period, last year</td>
        <td align="left">
            <%= report.getRprTotalPosYtd() %>
        </td>
    </tr>
    <tr>
    <td align="left" colspan="2" class="sectionHeader">
        <b>Pregnancies Protected against Tetanus</b>
    </td>
</tr>
    <tr align="left">
        <td align="left">
            <span class="indentHeader">TT1 done</span>
        </td>
        <td align="left">
            <%= report.getTt1done() %>
        </td>
    </tr>
    <tr align="left">
        <td align="left">
            <span class="indentHeader">TT2 done</span>
        </td>
        <td align="left">
            <%= report.getTt2done() %>
        </td>
    </tr>
    <tr align="left">
        <td align="left">
            <span class="indentHeader">TT3 done</span>
        </td>
        <td align="left">
            <%= report.getTt3done() %>
        </td>
    </tr>
    <tr align="left">
        <td align="left">
            <span class="indentHeader">TT4 done</span>
        </td>
        <td align="left">
            <%= report.getTt4done() %>
        </td>
    </tr>
    <tr align="left">
        <td align="left">
            <span class="indentHeader">TT5 done</span>
        </td>
        <td align="left">
            <%= report.getTt5done() %>
        </td>
    </tr>

</table>
</td>
</tr>
</table>
</template:put>
</template:insert>


