<%@ page import="org.cidrz.project.zeprs.report.ClinicWorkloadReport"%>
<%
	ClinicWorkloadReport report = (ClinicWorkloadReport) request.getAttribute("ClinicWorkload");
%>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<template:insert template='/WEB-INF/templates/template-report.jsp'>
<template:put name='title' direct='true'>Clinic Workload Report for <%= report.getSiteName() %> <%= report.getBeginDate() %> <%= report.getEndDate() %></template:put>
<template:put name='header' direct='true'>Clinic Workload Report for <%= report.getSiteName() %> <%= report.getBeginDate() %> <%= report.getEndDate() %></template:put>
<template:put name='content' direct='true'>
<table border="0" cellspacing="0" cellpadding="3" class="enhancedtable" width="250">

<tr>
	<th align="left" valign="top">
		Item
	</th>
	<th>
		Count
	</th>
</tr>
<!--<tr>
	<td align="left" valign="top">
		Admissions:
	</td>
	<td width="600">-->
		<%--<%= report.getAdmissions() %>--%>
<%--	</td>
</tr>--%>

<tr>
	<td align="left" valign="top">
		Discharges
	</td>
	<td>
		<%= report.getDischarges() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		Trans out to U.T.H.
	</td>
	<td>
		<%= report.getTotalReferrals() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B Block
	</td>
	<td>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i>unknown</i>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		B.B.A.
	</td>
	<td>
		<%= report.getBirthsBBA() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		Breech
	</td>
	<td>
		<%= report.getBirthsBreech() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		Face Presentation
	</td>
	<td>
		<i>unknown</i><%--<%= report.getBirthsFacePres() %>--%>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		Twins
	</td>
	<td>
		<%= report.getBirthsTwins() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		Premature
	</td>
	<td>
		<%= report.getBirthsPremature() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		Neonatal Death
	</td>
	<td>
		<%= report.getDeathsNeonatal() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		M.S.B.
	</td>
	<td>
		<%= report.getStillBirthsMacerated() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		F.S.B.
	</td>
	<td>
		<%= report.getStillBirthsFresh() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		Off Labour
	</td>
	<td>
		<%= report.getOffLabour() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		A.N.C. Old
	</td>
	<td>
		<%= report.getAncOld() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		A.N.C. New
	</td>
	<td>
		<%= report.getAncNew() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		No. Tested for H.B.
	</td>
	<td>
		<%= report.getHbTotal() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		H.B. Below 10Gm %
	</td>
	<td>
		<%= report.getHbBelow10g() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		No. Tested for R.P.R.
	</td>
	<td>
		<%= report.getRprTotal() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		Positive R.P.R. Results
	</td>
	<td>
		<%= report.getRprTotalPos() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		No. of Postives Treated
	</td>
	<td>
		<%= report.getRprTotalPosTreated() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		Post Natal 1st Week
	</td>
	<td>
		<%= report.getPostFirstWeekVisits() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		Post Natal 2nd Week
	</td>
	<td>
		<%= report.getPostSecondWeekVisits() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		Post Natal 3rd Week
	</td>
	<td>
		<%= report.getPostThirdWeekVisits() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		Post Natal 4th Week
	</td>
	<td>
		<%= report.getPostFourthWeekVisits() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		Post Natal 5th Week
	</td>
	<td>
		<%= report.getPostFifthWeekVisits() %>
	</td>
</tr>

<tr>
	<td align="left" valign="top">
		Post Natal 6th Week
	</td>
	<td>
		<%= report.getPostSixthWeekVisits() %>
	</td>
</tr>
</table>

<h2>Classification of patients transferred to U.T.H.</h2>
<p>This section displays data from problem forms where patient was referred to UTH and one of the items in the "Diagnosis" section was checked.</p>

<table border="0" cellspacing="0" cellpadding="3" class="enhancedtable" width="250">

<tr>
	<th align="left" valign="top">
		Reason for Referral
	</th>
	<th>
		Count
	</th>
</tr>
<tr>
	<td align="left" valign="top">False Labour
	</td>
	<td><%= report.getFalseLabour() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">True Labor
	</td>
	<td><%= report.getTrueLabor() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Rupture Of Membranes
	</td>
	<td><%= report.getRuptureofMembranes() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Intact Membranes
	</td>
	<td><%= report.getIntactMembranes() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Preeclamp Hypertension
	</td>
	<td><%= report.getPreeclampHypert() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Premature Labour
	</td>
	<td><%= report.getPrematureLabour() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Malaria Diagnosis
	</td>
	<td><%= report.getMalariaDiag() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Anaemia
	</td>
	<td><%= report.getAnaemia() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">High Bp Diagnosis
	</td>
	<td><%= report.getHighbpDiag() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Vaginal Bleeding Diagnosis
	</td>
	<td><%= report.getVaginalBleedingBiag() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Intrauterine Death
	</td>
	<td><%= report.getIntrauterineDeath() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Uti Diagnosis
	</td>
	<td><%= report.getUtiDiag() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Pneumonia Diagnosis
	</td>
	<td><%= report.getPneumoniaDiag() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Tb Diagnosis
	</td>
	<td><%= report.getTbDiag() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Vaginal Thrush Diagnosis
	</td>
	<td><%= report.getVaginalThrushDiag() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Oral Thrush Diagnosis
	</td>
	<td><%= report.getOralThrushDiag() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Eclampsia
	</td>
	<td><%= report.getEclampsia() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Abruptia Placenta
	</td>
	<td><%= report.getAbruptiaPlacenta() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Miscarriage
	</td>
	<td><%= report.getMiscarriage() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Diagnosis Other
	</td>
	<td><%= report.getDiagother() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Broken Episiotomy
	</td>
	<td><%= report.getBrokenepisiotomy() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Puerperal Sepsis
	</td>
	<td><%= report.getPuerperalsepsis() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Breast Engorgement
	</td>
	<td><%= report.getBreastengorgement() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Secondary PPH
	</td>
	<td><%= report.getSecondaryPph() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Mastitis
	</td>
	<td><%= report.getMastitis() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Breast Abscess
	</td>
	<td><%= report.getBreastAbscess() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Bowel Obstruction
	</td>
	<td><%= report.getBowelObstruction() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Indigestion
	</td>
	<td><%= report.getIndigestion() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Opthalmia Neonatorum
	</td>
	<td><%= report.getOpthalmiaNeonatorum() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Dehydration
	</td>
	<td><%= report.getDehydration() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Umbilical Infection
	</td>
	<td><%= report.getUmbilicalInfection() %>
	</td>
</tr>
<tr>
	<td align="left" valign="top">Diarrhoea
		</td>
		<td><%= report.getDiarrhoea() %>
		</td>
</tr>


</table>
</template:put>
</template:insert>
