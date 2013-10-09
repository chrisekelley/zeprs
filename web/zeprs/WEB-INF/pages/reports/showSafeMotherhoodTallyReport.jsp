<%@ page import="org.cidrz.project.zeprs.report.SafeMotherhoodTallyReport"%>
<%
	SafeMotherhoodTallyReport report = (SafeMotherhoodTallyReport) request.getAttribute("SafeMotherhoodTally");
%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<template:insert template='/WEB-INF/templates/template-simple.jsp'>
<template:put name='title' direct='true'>Safe Motherhood Attendance Tally Sheet - HIT.3</template:put>
<template:put name='header' direct='true'>Safe Motherhood Attendance Tally Sheet - HIT.3</template:put>
<template:put name='content' direct='true'>

<table border = "0" cellspacing="0" cellpadding="3" width="800">
<tr>
	<td colspan="2" align="right" valign="top">
		<b>HIT.3</b>
	</td>
</tr>
<tr>
	<td colspan="2" align="left" valign="top">
		<center><b>Health Institution<br>Safe Motherhood Attendance Tally Sheet</b></center>
	</td>
</tr>
<tr>
	<td colspan="2">
		&nbsp;<br>
	</td>
</tr>
<tr>
	<td width="75%" align="left" valign="top">
		District: Lusaka<br>
		Institution/Outreach Station: <%= report.getSiteName() %><br>
	</td>
	<td width="25%" align="left" valign="top">
		Month(s): <%= report.getReportMonth() %><br>
		Year: <%= report.getReportYear() %><br>
	</td>
<tr>
<tr>
	<td colspan="2">
		&nbsp;<br>
	</td>
</tr>
<tr>
	<td colspan="2">
		<table border="1" cellspacing="0" cellpadding="3" width="800">
		<tr>
			<td align="left" width="250">
				Total First Antenatal Attendances:
			</td>
			<td align="left" width="600">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= report.getFirstAnteAtt() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="250">
				Total Antenatal Reattendances: 
			</td>
			<td align="left" width="600">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= report.getAnteReatt() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="250">
				Total Pregnancies Protected by TT: 
			</td>
			<td align="left" width="600">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= report.getTotalProtectedPregs() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="250">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total TT2: 
			</td>
			<td align="left" width="600">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= report.getProtectedPregsTT2() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="250">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total TT3: 
			</td>
			<td align="left" width="600">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= report.getProtectedPregsTT3() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="250">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total TT4: 
			</td>
			<td align="left" width="600">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= report.getProtectedPregsTT4() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="250">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total TT5: 
			</td>
			<td align="left" width="600">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= report.getProtectedPregsTT5() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="250">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Previously Immunised: 
			</td>
			<td align="left" width="600">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= report.getPrevProtected() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="250">
				Total First Postnatal Visits: 
			</td>
			<td align="left" width="600">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= report.getFirstPostAtt() %>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>


</template:put>
</template:insert>
