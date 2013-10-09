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
<template:put name='title' direct='true'>Delivery Register - HIR.4</template:put>
<template:put name='header' direct='true'>Delivery Register - HIR.4</template:put>
<template:put name='content' direct='true'>

<table border = "0" cellspacing="0" cellpadding="3">
<tr>
	<td align="right" valign="top">
		<b>Delivery Register - HIR.4</b>
	</td>
</tr>
<tr>
	<td>
		&nbsp;<br>
	</td>
</tr>
<tr>
	<td align="center" valign="top">
		<b>Health Institution: </b> <c:out value="${report.siteName}"/>
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
			<td align="center" valign="bottom" rowspan="3" class="small">
				Delivery Register Number
			</td>
			<td align="center" valign="top" colspan="6" class="small">
				Delivery
			</td>
			<td align="center" valign="top" colspan="4" class="small">
				Baby
			</td>
			<td align="center" valign="top" colspan="2" class="small">
				Delivery Attended By
			</td>
			<td align="center" valign="top" colspan="2" class="small">
				Discharge
			</td>
		</tr>
		<tr>
			<td align="center" valign="bottom" rowspan="2" class="small">
				Characteristics
			</td>
			<td align="center" valign="bottom" rowspan="2" class="small">
				Intervention
			</td>
			<td align="center" valign="top" colspan="3" class="small">
				Third stage
			</td>
			<td align="center" valign="bottom" rowspan="2" class="small">
				Complications
			</td>
			<td align="center" valign="bottom" rowspan="2" class="small">
				Sex
			</td>
			<td align="center" valign="bottom" rowspan="2" class="small">
				Birth Weight
			</td>
			<td align="center" valign="bottom" rowspan="2" class="small">
				Live Birth, Fresh or Macerated Still Birth
			</td>
			<td align="center" valign="bottom" rowspan="2" class="small">
				Perinatal Problems
			</td>
			<td align="center" valign="bottom" rowspan="2" class="small">
				Name
			</td>
			<td align="center" valign="bottom" rowspan="2" class="small">
				Title
			</td>
			<td align="center" valign="bottom" rowspan="2" class="small">
				Vitamin K
			</td>
			<td align="center" valign="bottom" rowspan="2" class="small">
				Remarks
			</td>
		</tr>
		<tr>
			<td align="center" valign="bottom" class="small">
				placenta
			</td>
			<td align="center" valign="bottom" class="small">
				blood
			</td>
			<td align="center" valign="bottom" class="small">
				perineum
			</td>
		</tr>
		<tr>
			<td align="center" class="small">
				(a)
			</td>
			<td align="center" class="small">
				(n)
			</td>
			<td align="center" class="small">
				(o)
			</td>
			<td align="center" class="small">
				(p)
			</td>
			<td align="center" class="small">
				(q)
			</td>
			<td align="center" class="small">
				(r)
			</td>
			<td align="center" class="small">
				(s)
			</td>
			<td align="center" class="small">
				(t)
			</td>
			<td align="center" class="small">
				(u)
			</td>
			<td align="center" class="small">
				(v)
			</td>
			<td align="center" class="small">
				(w)
			</td>
			<td align="center" class="small">
				(x)
			</td>
			<td align="center" class="small">
				(y)
			</td>
			<td align="center" class="small">
				(z)
			</td>
			<td align="center" class="small">
				(aa)
			</td>
		</tr>

        <logic:iterate id="patient" name="report" property="patients" indexId="ind">
            <logic:notEmpty name="report" property="patientMap.patient${patient.patientID}">
                <bean:define id="deliveryPatient" name="report" property="patientMap.patient${patient.patientID}"/>
            </logic:notEmpty>

            <%--<bean:define id="deliverySum" name="deliveryPatient" property="deliverySum"/>--%>
        <tr>
			<td align="center" class="small">

				<%--<%= i+1 %>:
                <%= drp.getPatientID() %>--%>
                ${ind+1}: ${patient.patientID}

            </td>
			<td align="center" class="small">${patient.deliveryCharacteristics}&nbsp;
                <logic:notEmpty name="deliveryPatient">
                        <bean:write name="deliveryPatient" property="deliverySum.encounterMap.mode_of_delivery_447" ignore="true"/>
                </logic:notEmpty>
            </td>
			<td align="center" class="small">
				(o)
			</td>
			<td align="left" class="small">
				<nobr><b>Delivered at: </b>
                    <logic:present name="deliveryPatient" property="deliverySum.encounterMap.placenta_delivered_438">
                    <bean:write name="deliveryPatient" property="deliverySum.encounterMap.placenta_delivered_438"/></nobr><br>    
                    </logic:present>

				<nobr><b>Method: </b>
                <bean:write name="deliveryPatient" property="deliverySum.encounterMap.placenta_delivery_method_439" ignore="true"/></nobr><br>
				<nobr><b>Type: </b>
                    <bean:write name="deliveryPatient" property="deliverySum.encounterMap.placenta_type_440" ignore="true"/></nobr><br>
				<nobr><b>Colour: </b>
                    <bean:write name="deliveryPatient" property="deliverySum.encounterMap.colour_of_placenta_470" ignore="true"/></nobr><br>
                <nobr><b>Weight: </b>
                <c:if test="${patient.delivery3rdStagePlacentaWeight != -1}">${patient.delivery3rdStagePlacentaWeight}</c:if>
                     <bean:write name="deliveryPatient" property="deliverySum.encounterMap.weight_of_placenta_441" ignore="true"/>
                </nobr><br>
				<nobr><b>State: </b>
                <bean:write name="deliveryPatient" property="deliverySum.encounterMap.state_of_placenta_1204" ignore="true"/></nobr><br>
			</td>
			<td align="center" class="small">
                <bean:write name="deliveryPatient" property="deliverySum.encounterMap.blood_loss_est_462" ignore="true"/>&nbsp;</td>
			<td align="center" class="small">${patient.delivery3rdStagePerineum}&nbsp;</td>
			<td align="center" class="small">${patient.deliveryComplications}
                <bean:write name="deliveryPatient" property="deliverySum.encounterMap.complications_467" ignore="true"/>
                <bean:write name="deliveryPatient" property="deliverySum.encounterMap.if_complications_desc_468" ignore="true"/>&nbsp;</td>
            <td align="left" class="small" colspan="4">
            <c:if test="${! empty patient.babies}">
                <table width="100%">
                <logic:iterate id="baby" name="patient" property="babies" indexId="i">
                    <tr>
                        <td align="center" class="small">${baby.babySex}</td>
                        <td align="center" class="small">${baby.babyBirthWeight}</td>
                        <td align="center" class="small">${baby.babyBirthOutcome}</td>
                        <td align="center" class="small">${baby.babyPerinatalProbs}</td>
                    </tr>
                </logic:iterate>
                </table>
            </c:if>
            &nbsp;</td>

			<td align="center" class="small">
				(x)
			</td>
			<td align="center" class="small">
				(y)
			</td>
			<td align="center" class="small">
				(z)
			</td>
			<td align="center" class="small">
				(aa)
			</td>
		</tr>

         </logic:iterate>

        </table>
	</td>
</tr>
</table>


</template:put>
</template:insert>
