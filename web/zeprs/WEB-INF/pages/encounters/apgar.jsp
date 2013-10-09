<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script language="JavaScript" type="text/javascript" src="/zeprs/js/apgar.js;jsessionid=${pageContext.request.session.id}"></script>
<p>Click a value from each row to calculate the Apgar score.</p>

<c:set var="id" value="${param.id}"/> 

<table border=1 cellspacing="0" cellpadding=2 id="${id}apgar">
	<tr bgcolor="#99ccff" id="${id}min">
		<th>Signs</th>
		<th>0</th>
		<th>1</th>

		<th>2</th>
		<th id="${id}1min" style="background-color: #0033cc; color: White;" onMouseOver="this.style.cursor='hand'" onClick="whatMin(this.id);"><br>score</th>
	</tr>

	<tr id="${id}h">
		<th align=right>Heart Rate</th>

		<td align=center id="${id}h0" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">Absent</td>
		<td align=center id="${id}h1" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">Below 100</td>
		<td align=center id="${id}h2" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">Above 100</td>
		<td class="score" id="${id}1h" onClick="whatMin(this.id,${id});">&nbsp;</td>
	</tr>

	<tr id="${id}r">
		<th align=right>Respiratory Effort</th>
		<td align=center id="${id}r0" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">Absent</td>

		<td align=center id="${id}r1" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">Slow, irregular</td>
		<td align=center id="${id}r2" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">Good, crying</td>
		<td class="score" id="${id}1r" onClick="whatMin(this.id,${id});">&nbsp;</td>
	</tr>

	<tr id="${id}m">
		<th align=right>Muscle Tone</th>
		<td align=center id="${id}m0" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">Nil (Limp)</td>
		<td align=center id="${id}m1" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">Some flexion<br>of extremities</td>

		<td align=center id="${id}m2" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">Active motion</td>
		<td class="score" id="${id}1m" onClick="whatMin(this.id,${id});">&nbsp;</td>

	</tr>

	<tr id="${id}x">
		<th align=right>Reflex irritability</th>
		<td align=center id="${id}x0" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">No response</td>

		<td align=center id="${id}x1" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">Grimace</td>
		<td align=center id="${id}x2" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">Vigorous Cry</td>
		<td class="score" id="${id}1x" onClick="whatMin(this.id,${id});">&nbsp;</td>
	</tr>

	<tr id="${id}c">
		<th align=right>Color</th>
		<td align=center id="${id}c0" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">Blue, pale</td>
		<td align=center id="${id}c1" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">Body pink,<br>extremities blue</td>

		<td align=center id="${id}c2" onMouseOver="bgclr(this,1); this.style.cursor='hand'" onMouseOut="bgclr(this,0);" onClick="bgclr(this,2,${id});">Completely pink</td>
		<td class="score" id="${id}1c" onClick="whatMin(this.id,${id});">&nbsp;</td>
	</tr>

	<tr id="${id}total">
		<th height="44px" colspan=4 align=right>
			TOTAL SCORE :&nbsp;
		</th>
		<td class="score" id="${id}1score" onClick="whatMin(this.id,${id});">&nbsp;</td>
	</tr>
</table>
