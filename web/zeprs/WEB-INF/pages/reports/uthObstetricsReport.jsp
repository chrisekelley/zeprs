<%@ page import="org.cidrz.project.zeprs.report.UthObstetricsReport" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>

<template:insert template='/WEB-INF/templates/template-simple.jsp'>
<template:put name='title' direct='true'>UTH Obstetrics and Neonatology Statistics</template:put>
<template:put name='header' direct='true'>UTH Obstetrics and Neonatology Statistics</template:put>
<template:put name='content' direct='true'>
<%
    UthObstetricsReport report = (UthObstetricsReport) request.getAttribute("UthObstetricsReport");
%>
<p><strong>Period:</strong> <%= report.getBeginDate() + " - " + report.getEndDate() %></p>
<table border="0" cellspacing="5" cellpadding="5" class="enhancedtable">

<tr>
<td align="left" width="400" valign="top">
    <table border="0" cellspacing="0" cellpadding="3" width="100%">
        <tr>
            <td align="left">
                Total Deliveries at UTH : <%= report.getTotalDeliveries() %><br/>
                Referred Cases :  <%= report.getReferredCases() %><br/>
                Referred Cases/Total :  <%= report.getReferredCasesTotalDeliveriesCalc() %><br/>
                Postnatal Hospitalizations at UTH : <%= report.getAaPostnatalAtt() %><br/>
                Maternal Deaths : <%= report.getMaternalDeaths() %><br/>
                Maternal Deaths/Total :  <%= report.getMaternalDeathsTotalDeliveriesCalc() %>
            </td>
            </td>
        </tr>
        </table>
    <table border="0" cellspacing="0" cellpadding="3" width="100%">
        <tr>
            <td align="left" width="100%">
                <u>DELIVERIES</u>
            </td>
        </tr>
        </table>
    <table border="0" cellspacing="0" cellpadding="3" width="100%" class="enhancedtable">
        <tr>
            <th>Type</th>
            <th>Amount</th>
            <th>% of Total Deliveries</th>
        </tr>
        <tr>
            <td>Spontaneous Deliveries</td>
            <td><%= report.getSpontaneousVertexDeliveries() %></td>
            <td><%= report.getSpontaneousVertexDeliveriesTotalDeliveriesCalc() %></td>
        </tr>
        <tr>
            <td>Breech Deliveries</td>
            <td><%= report.getBreechDeliveries() %></td>
            <td><%= report.getBreechDeliveriesTotalDeliveriesCalc() %></td>
        </tr>
        <tr>
            <td>Forceps Deliveries </td>
            <td><%= report.getForcepsDeliveries() %></td>
            <td><%= report.getForcepsDeliveriesTotalDeliveriesCalc() %></td>
        </tr>
        <tr>
            <td>Vacuum Extraction Deliveries</td>
            <td><%= report.getVacuumExtractionDeliveries() %></td>
            <td><%= report.getVacuumExtractionDeliveriesTotalDeliveriesCalc() %></td>
        </tr>
        <tr>
            <td>Caesarian Section Deliveries</td>
            <td><%= report.getCaesarianSectionDeliveries() %></td>
            <td><%= report.getCaesarianSectionDeliveriesTotalDeliveriesCalc() %></td>
        </tr>
        <tr>
            <td>Face Presentation Deliveries</td>
            <td><%= report.getFacePresentationDeliveries() %></td>
            <td><%= report.getFacePresentationDeliveriesTotalDeliveriesCalc() %></td>
        </tr>
        <tr>
            <td>BBAs (Born Before Arrival)</td>
            <td><%= report.getbBAs() %></td>
            <td><%= report.getbBAsTotalDeliveriesCalc() %></td>
        </tr>
    </table>
<table border="0" cellspacing="0" cellpadding="3" width="100%">
        <tr>
            <td align="left" width="100%">
                <u>DELIVERIES BY SEX</u>
            </td>
        </tr>
        </table>
    <table border="0" cellspacing="0" cellpadding="3" width="100%" class="enhancedtable">
        <tr>
            <th>Type</th>
            <th>Female</th>
            <th>Male</th>
            <th>Total</th>
        </tr>
        <tr>
            <td>Total Infants Born</td>
            <td><%= report.getTotalInfantsBornFemale() %></td>
            <td><%= report.getTotalInfantsBornMale() %></td>
            <td><%= report.getTotalInfantsBornTotal() %></td>
        </tr>
        <tr>
            <td>Twins</td>
            <td><%= report.getTwinsFemale() %></td>
            <td><%= report.getTwinsMale() %></td>
            <td><%= report.getTwinsTotal() %></td>
        </tr>
        <tr>
            <td>Triplets</td>
            <td><%= report.getTripletsFemale() %></td>
            <td><%= report.getTripletsMale() %></td>
            <td><%= report.getTripletsTotal() %></td>
        </tr>
        <tr>
            <td>Premature Live Births</td>
            <td><%= report.getPrematureLiveBirthsFemale() %></td>
            <td><%= report.getPrematureLiveBirthsMale() %></td>
            <td><%= report.getPrematureLiveBirthsTotal() %></td>
        </tr>
    </table>
</td>

<td align="left" width="400" valign="top">
    <table border="0" cellspacing="0" cellpadding="3" width="100%">
        <tr>
            <td align="left" width="100%" colspan="2">
                <u>NEONATAL DEATHS AND STILL BIRTHS</u>
            </td>
        </tr>
        <tr>
            <td align="left" width="100%" colspan="2">
                <table border="0" cellspacing="0" cellpadding="3" width="100%" class="enhancedtable">
        <tr>
            <th>Type</th>
            <th>Female</th>
            <th>Male</th>
            <th>Total</th>
        </tr>
        <tr>
            <td>Neonatal Deaths</td>
            <td><%= report.getNeonatalDeathsFemale() %></td>
            <td><%= report.getNeonatalDeathsMale() %></td>
            <td><%= report.getNeonatalDeathsTotal() %></td>
        </tr>
                    <tr>
            <td>Maternal Deaths</td>
            <td><%= report.getMaternalDeaths() %></td>
            <td>&nbsp;</td>
            <td><%= report.getMaternalDeathsTotalDeliveriesCalc() %></td>
        </tr>
        <tr>
            <td>Fresh Stillbirths</td>
            <td><%= report.getFreshStillbirthsFemale() %></td>
            <td><%= report.getFreshStillbirthsMale() %></td>
            <td><%= report.getFreshStillbirthsMale() %></td>
        </tr>
         <tr>
            <td>Macerated Stillbirths</td>
            <td><%= report.getMaceratedStillbirthsFemale() %></td>
            <td><%= report.getMaceratedStillbirthsMale() %></td>
            <td><%= report.getMaceratedStillbirthsTotal() %></td>
        </tr>
           <tr>
            <td>Total Stillbirths</td>
            <td><%= report.getStillBirthsFemale() %></td>
            <td><%= report.getStillBirthsMale() %></td>
            <td><%= report.getStillBirthsTotal() %></td>
        </tr>
    </table>
            </td>
        </tr>


        <tr>
            <td align="left" width="100%" colspan="2">
                <u>POSTNATAL HOSPITALIZATION</u>
            </td>
        </tr>
        <tr>
            <td align="left">
                <table border="0" cellspacing="0" cellpadding="3" width="100%" class="enhancedtable">
                    <tr>
                        <th>Type</th>
                        <th>Amount</th>
                        <th>% of Total Deliveries</th>
                    </tr>
                    <tr>
                        <td>Ruptured Uterus</td>
                        <td><%= report.getRupturedUterus() %></td>
                        <td><%= report.getRupturedUterusTotalDeliveriesCalc() %></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</td>
</tr>
</table>

</template:put>
</template:insert>

