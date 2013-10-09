<%@ page import="java.util.*,
                 org.cidrz.project.zeprs.report.SafeMotherhoodRegisterReport,
                 org.cidrz.project.zeprs.report.SafeMotherhoodRegisterPatient" %>
<%@ page import="java.sql.Date" %>


<%
	SafeMotherhoodRegisterReport report = (SafeMotherhoodRegisterReport) request.getAttribute("SafeMotherhoodRegister");

	ArrayList patients = report.getPatients();
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
<template:put name='title' direct='true'>Safe Motherhood Register - HIR.3 for  ${report.siteName}</template:put>
<template:put name='header' direct='true'>Safe Motherhood Register - HIR.3 for  ${report.siteName}</template:put>
<template:put name='content' direct='true'>
<p><strong>Health Institution:</strong> ${report.siteName}</p>
<b>Health Institution: </b> <%= report.getSiteName() %>
<!--<link rel=StyleSheet href="./ZEPRSstyles.css" type="text/css">-->

<table border = "0" cellspacing="0" cellpadding="3" class="enhancedtable">
<tr>
	<td align="right" valign="top">
		<b>Safe Motherhood Register - HIR.3</b>
	</td>
</tr>
<tr>
	<td>
		&nbsp;<br>
	</td>
</tr>
<tr>
	<td align="center" valign="top">
		<b>Health Institution: </b> <%= report.getSiteName() %>
	</td>
</tr>
<tr>
	<td>
		&nbsp;<br>
	</td>
</tr>
<tr>
	<td align="left" valign="top">
		<table border="1" cellspacing="0" cellpadding="3">
		<tr>
			<td rowspan="2" align="center" valign="bottom" class="small">
				SM Register Number
			</td>
			<td rowspan="2" align="center" valign="bottom" class="small">
				Date of First Attendance
			</td>
			<td rowspan="2" align="left" valign="bottom" class="small">
				Name <br>
				<hr width="80%" align="left">
				Address
			</td>
			<%--<td rowspan="2" align="center" valign="bottom" class="small">
				Origin Code
			</td>--%>
			<td rowspan="2" align="center" valign="bottom" class="small">
				Age
			</td>
			<td rowspan="2" align="center" valign="bottom" class="small">
				Grav. no.
			</td>
			<td rowspan="2" align="center" valign="bottom" class="small">
				Para. no.
			</td>
			<td rowspan="2" align="center" valign="bottom" class="small">
				Date of Last Normal Menstruation
			</td>
			<td rowspan="2" align="center" valign="bottom" class="small">
				Expected date of delivery
			</td>
			<td rowspan="2" align="center" valign="bottom" class="small">
				RPR pos/neg
			</td>
			<td colspan="5" align="center" class="small">
				TT Status
			</td>
			<td colspan="7" align="center" class="small">
				Attendance in month of pregnancy
			</td>
		</tr>
		<tr>
			<td align="center" valign="bottom" class="small">
				TT1
			</td>
			<td align="center" valign="bottom" class="small">
				TT2
			</td>
			<td align="center" valign="bottom" class="small">
				TT3
			</td>
			<td align="center" valign="bottom" class="small">
				TT4
			</td>
			<td align="center" valign="bottom" class="small">
				TT5
			</td>
			<td align="center" valign="bottom" class="small">
				<nobr>1-3</nobr>
			</td>
			<td align="center" valign="bottom" class="small">
				4
			</td>
			<td align="center" valign="bottom" class="small">
				5
			</td>
			<td align="center" valign="bottom" class="small">
				6
			</td>
			<td align="center" valign="bottom" class="small">
				7
			</td>
			<td align="center" valign="bottom" class="small">
				8
			</td>
			<td align="center" valign="bottom" class="small">
				9
			</td>
		</tr>

<%
		for (int i = 0; i < patients.size(); i++) {
			SafeMotherhoodRegisterPatient smrp = (SafeMotherhoodRegisterPatient) patients.get(i);
%>
		<tr>
			<td align="center" class="small">
                <%= smrp.getSmRegisterNumber() %>
			</td>
			<td align="center" class="small">
			<%
				Date firstAtt = smrp.getFirstAttDate();
				if (firstAtt != null) {
			%>
					<%= firstAtt.toString() %>
			<%
				} else {
			%>
					<i>unknown</i>
			<%
				}
			%>
			</td>
			<td align="left" class="small">
				<nobr><%= smrp.getName() %></nobr><br>
				<hr width="80%" align="left">
				<%= smrp.getAddress1() %>
			</td>
			<%--<td align="center" class="small">
				(d)
			</td>--%>
			<td align="center" class="small">
				<%= smrp.getAge() %>
			</td>
			<td align="center" class="small">
				(f)
			</td>
			<td align="center" class="small">
				(g)
			</td>
			<td align="center" class="small">
			<%
				Date lmp = smrp.getLastMenstDate();
				if (lmp != null) {
			%>
					<%= lmp.toString() %>
			<%
				} else {
			%>
					<i>unknown</i>
			<%
				}
			%>
			</td>
			<td align="center" class="small">
			<%
				Date edd = smrp.getEstDateDelivery();
				if (edd != null) {
			%>
					<%= edd.toString() %>
			<%
				} else {
			%>
					<i>unknown</i>
			<%
				}
			%>
			</td>
			<td align="center" class="small">
				(j)
			</td>
			<td align="center" class="small">
			<%
				if (smrp.isTt1()) {
			%>
					X
			<%
				} else {
			%>
                &nbsp;
            <%
				}
			%>
			</td>
			<td align="center" class="small">
			<%
				if (smrp.isTt2()) {
			%>
					X
			<%
				} else {
			%>
                &nbsp;
            <%
				}
			%>
			</td>
			<td align="center" class="small">
			<%
				if (smrp.isTt3()) {
			%>
					X
			<%
				} else {
			%>
                &nbsp;
            <%
				}
			%>
			</td>
			<td align="center" class="small">
			<%
				if (smrp.isTt4()) {
			%>
					X
			<%
				} else {
			%>
                &nbsp;
            <%
				}
			%>
			</td>
			<td align="center" class="small">
			<%
				if (smrp.isTt5()) {
			%>
					X
			<%
				} else {
			%>
                &nbsp;
            <%
				}
			%>
			</td>
			<td align="center" class="small">
			<%
				if (smrp.isAttMonth123()) {
			%>
					X
			<%
				} else {
			%>
                &nbsp;
            <%
				}
			%>
			</td>
			<td align="center" class="small">
			<%
				if (smrp.isAttMonth4()) {
			%>
					X
			<%
				} else {
			%>
                &nbsp;
            <%
				}
			%>
			</td>
			<td align="center" class="small">
			<%
				if (smrp.isAttMonth5()) {
			%>
					X
			<%
				} else {
			%>
                &nbsp;
            <%
				}
			%>
			</td>
			<td align="center" class="small">
			<%
				if (smrp.isAttMonth6()) {
			%>
					X
			<%
				} else {
			%>
                &nbsp;
            <%
				}
			%>
			</td>
			<td align="center" class="small">
			<%
				if (smrp.isAttMonth7()) {
			%>
					X
			<%
				} else {
			%>
                &nbsp;
            <%
				}
			%>
			</td>
			<td align="center" class="small">
			<%
				if (smrp.isAttMonth8()) {
			%>
					X
			<%
				} else {
			%>
                &nbsp;
            <%
				}
			%>
			</td>
			<td align="center" class="small">
			<%
				if (smrp.isAttMonth9()) {
			%>
					X
			<%
				} else {
			%>
                &nbsp;
            <%
				}
			%>
			</td>
		</tr>
<%
		}
%>
		</table>
	</td>
<tr>
</table>

</template:put>
</template:insert>
