<%@ page import="org.cidrz.project.zeprs.report.LUDHMBReport"%>

<html>
<head>
<title>LUDHMB Maternal and Neonatal Statistics</title>
<body>

<%
	LUDHMBReport report = (LUDHMBReport) request.getAttribute("LUDHMBReport");
%>	
	

<p>
<center>
	LUSAKA DISTRICT MANAGEMENT BOARD<br>
	MATERNAL AND NEONATAL STATISTICS
</center>

<table border="0" cellspacing="5" cellpadding="5">
<tr>
	<td align="left" width="50%">
		Own File:
	</td>
	<td align="left" width="50%">
		District (MCH): Lusaka
	</td>
</tr>
<tr>
	<td align="left" width="50%" colspan="2">
		Health Centre: <%= report.getSiteName() %>
	</td>
</tr>
<tr>
	<td align="left" colspan="2">
		Period: <%= report.getBeginDate() %> - <%= report.getEndDate() %>
	</td>
</tr>
<tr>
    <td align="left" width="100%" colspan="2">
        <hr width="100%">
    </td>
</tr>

<tr>
	<td align="left" width="400" valign="top">
		<table border="0" cellspacing="0" cellpadding="3" width="100%">
		<tr>
			<td align="left" width="100%" colspan="2">
				<u>ANTENATAL ATTENDANCES:</u>
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">
				New ANC : <%= report.getAaNewANC() %>
			</td>
			<td align="left" width="50%">
				Old ANC : <%= report.getAaOldANC() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">
				TOTAL ANC : <%= report.getAaTotalANC() %>
			</td>
			<td align="left" width="50%">
				Postnatal Attendances : <%= report.getAaPostnatalAtt() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="100%" colspan="2">
				<hr width="100%">
			</td>
		</tr>
		<tr>
			<td align="left" width="100%" colspan="2">
				<u>TOTAL NEW ANC SEEN IN:</u>
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">
				First Trimester : <%= report.getNewANCFirstTrimester() %>
			</td>
			<td align="left" width="50%">
				Second Trimester : <%= report.getNewANCSecondTrimester() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">
				Third Trimester : <%= report.getNewANCThirdTrimester() %>
			</td>
			<td align="left" width="50%">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td align="left" width="100%" colspan="2">
				<hr width="100%">
			</td>
		</tr>
		<tr>
			<td align="left" width="100%" colspan="2">
				<u>BIRTHS:</u>
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">
				Live : <%= report.getBirthsLive() %>
			</td>
			<td align="left" width="50%">
				Premature : <%= report.getBirthsPremature() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="100%" colspan="2">
				<hr width="100%">
			</td>
		</tr>
		<tr>
			<td align="left" width="100%" colspan="2">
				<u>STILL BIRTHS:</u>
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">
				Fresh Stillbirths : <%= report.getStillBirthsFresh() %>
			</td>
			<td align="left" width="50%">
				Macerated Stillbirths : <%= report.getStillBirthsMacerated() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="100%" colspan="2">
				<hr width="100%">
			</td>
		</tr>
		<tr>
			<td align="left" width="100%" colspan="2">
				<u>DEATHS:</u>
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">
				Neonatal : <%= report.getDeathsNeonatal() %>
			</td>
			<td align="left" width="50%">
				Maternal : <%= report.getDeathsMaternal() %>
			</td>
		</tr>
		</table>
	</td>

	<td align="left" width="400" valign="top">
		<table border="0" cellspacing="0" cellpadding="3" width="100%">
		<tr>
			<td align="left" colspan="2">
				<u>RPR Test 1:</u>
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">
				TOTAL RPR <br>
				Test 1 : <%= report.getRpr1Total() %>
			</td>
			<td align="left" width="50%">
				TOTAL RPR <br>
				Test 1 Positive : <%= report.getRpr1TotalPos() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">
				TOTAL Positive <br>
				Treated : <%= report.getRpr1TotalPosTreated() %>
			</td>
			<td align="left" width="50%">
				TOTAL Partner <br>
				Treated : <i>unknown</i>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="2">
				<hr width="100%">
			</td>
		</tr>
		<tr>
			<td align="left" colspan="2">
				<u>RPR Test 2:</u>
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">
				TOTAL RPR <br>
				Test 2 : <%= report.getRpr2Total() %>
			</td>
			<td align="left" width="50%">
				TOTAL RPR <br>
				Test 2 Positive : <%= report.getRpr2TotalPos() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="50%">
				TOTAL Positive <br>
				Treated : <%= report.getRpr2TotalPosTreated() %>
			</td>
			<td align="left" width="50%">
				TOTAL Partner <br>
				Treated : <i>unknown</i>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="2">
				<hr width="100%">
			</td>
		</tr>
		<tr>
			<td align="left" colspan="2">
				TOTAL Newborns Treated<br>
				Born to RPR Reactive Mothers : <%= report.getNewbornsTreated() %>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="2">
				<hr width="100%">
			</td>
		</tr>
		<tr>
			<td align="left" colspan="2">
				<u>Hb:</u>
			</td>
		</tr>
		<tr>
			<td align="left" width="50%" colspan="2">
				<%
					int hbtotal = report.getHbTotal();
					float hbpercent = 0;
					if (hbtotal != 0) {
						hbpercent = report.getHbBelow10g() / hbtotal;
					}
				%>
				
				Hb Below 10g% : <%= report.getHbBelow10g() %>
			</td>
		</tr>
		<tr>
			<td align="left" width="50%" colspan="2">
				Total Hb Done : <%= hbtotal %>
			</td>
		</tr>
		
		</table>
	</td>
</tr>
<tr>
    <td align="left" width="100%" colspan="2">
        <hr width="100%">
    </td>
</tr>
<tr>
    <td align="left" width="400" valign="top">
        <table border="0" cellspacing="0" cellpadding="3" width="100%">
            <tr>
                <td align="left" width="100%" colspan="5">
                    <u>Tetanus Toxoid:</u>
                </td>
            </tr>
            <tr>
                <td align="left" width="20%">
                    TT1 : <%= report.getTt1done() %>
                </td>
                <td align="left" width="20%">
                    TT2 : <%= report.getTt2done() %>
                </td>
                <td align="left" width="20%">
                    TT3 : <%= report.getTt3done() %>
                </td>
                <td align="left" width="20%">
                    TT4 : <%= report.getTt4done() %>
                </td>
                <td align="left" width="20%">
                    TT5 : <%= report.getTt5done() %>
                </td>

            </tr>
        </table>
    </td>
</tr>
<tr>
    <td align="left" width="400" valign="top">
        <table border="0" cellspacing="0" cellpadding="3" width="100%">
            <tr>
                <td align="left" width="100%" colspan="5">
                    <u>Fansidar:</u>
                </td>
            </tr>
            <tr>
                <td align="left">
                    SP1 : <%= report.getFansidar1() %>
                </td>
                <td align="left">
                    SP2 : <%= report.getFansidar2() %>
                </td>
                <td align="left">
                    SP3 : <%= report.getFansidar3() %>
                </td>
            </tr>
        </table>
    </td>
</tr>

</table>
		
</body>
</html>

