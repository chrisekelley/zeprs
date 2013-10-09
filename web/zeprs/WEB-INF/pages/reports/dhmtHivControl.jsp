<%@ page import="org.cidrz.project.zeprs.report.CBoHQuarterlySelfAssessmentReport" %>
<%@ page import="org.cidrz.project.zeprs.report.DhmtHivControlReport"%>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%
    DhmtHivControlReport report = (DhmtHivControlReport) request.getAttribute("DhmtHivControl");
%>
<template:insert template='/WEB-INF/templates/template-report.jsp'>
    <template:put name='title' direct='true'>DHMT HIV Control for <%= report.getSiteName() %> <%= report.getBeginDate() %> - <%= report.getEndDate() %></template:put>
    <template:put name='header' direct='true'>DHMT HIV Control for <%= report.getSiteName() %> <%= report.getBeginDate() %> - <%= report.getEndDate() %></template:put>
    <template:put name='content' direct='true'>
        <table border="0" cellspacing="0" cellpadding="3" width="800">
                <tr>
                    <td colspan="2">
                        <table border="1" cellspacing="0" cellpadding="3" width="300" class="enhancedtable">
                            <tr align="left">
                                <td align="left">Women Treated for STDs</td>
                                <td align="left">
                                    <%= report.getWomenTreatedStds() %>
                                </td>
                            </tr>
                            <tr align="left">
                                <td align="left">Women with Genital Ulcers</td>
                                <td align="left">
                                    <%= report.getWomenGenitalUlcers() %>
                                </td>
                            </tr>
                            <tr align="left">
                                <td align="left">Women with Vaginal Discharges</td>
                                <td align="left">
                                    <%= report.getWomenVaginalDischarges() %>
                                </td>
                            </tr>
                            <tr align="left">
                                <td align="left">Pregnant Women New ANC</td>
                                <td align="left">
                                    <%= report.getWomenNewAnc() %>
                                </td>
                            </tr>
                            <tr align="left">
                                <td align="left">Women Attended PNC</td>
                                <td align="left">
                                    <%= report.getWomenAttendedPnc() %>
                                </td>
                            </tr>
                            <tr align="left">
                                <td align="left">Total No. of Deliveries</td>
                                <td align="left">
                                    <%= report.getTotalDeliveries() %>
                                </td>
                            </tr>
                            <tr align="left">
                                <td align="left">[Calc] No. women with genital ulcers / No. women treated for STDs</td>
                                <td align="left">
                                    <%= report.getWomenGenitalUlcersCalc() %>
                                </td>
                            </tr>
                            <tr align="left">
                                <td align="left">[Calc] No. women with vaginal discharges / No. women treated for STDs</td>
                                <td align="left">
                                    <%= report.getWomenVaginalDischargesCalc() %>
                                </td>
                            </tr>
                            <tr align="left">
                                <td align="left">[Calc] No. women attended PNC / Total no. deliveries</td>
                                <td align="left">
                                    <%= report.getWomenAttendedPncCalc() %>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
        </table>
    </template:put>
</template:insert>


