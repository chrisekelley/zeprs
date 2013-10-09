<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<template:insert template='/WEB-INF/templates/template-report-wide.jsp'>
<template:put name='title' direct='true'>Safe Motherhood Register - Antenatal - HIR.3 for ${register.siteName} ${register.beginDate} - ${register.endDate}</template:put>
<template:put name='header' direct='true'>Safe Motherhood Register - Antenatal - HIR.3 for ${register.siteName} ${register.beginDate} - ${register.endDate}</template:put>
<template:put name='content' direct='true'>
<script language="javascript" type="text/javascript">
    function scrollUp() {
        var divY = document.getElementById('scrollBody').scrollTop;
        var newDivY = divY - 50;
        document.getElementById('scrollBody').scrollTop = newDivY;
    }

    function scrollDown() {
        var divY = document.getElementById('scrollBody').scrollTop;
        var newDivY = divY + 50;
        document.getElementById('scrollBody').scrollTop = newDivY;

    }
</script>

<c:set var="pageBreakCount" value="0"/>
<table border="1" cellspacing="0" cellpadding="3" class="reportTablePrint">
    <tr>
        <th rowspan="2" width="70px">Visit Date</th>
        <th rowspan="2" width="120px">SM #, Name, <br>Address</th>
        <th rowspan="2" width="30px;">Age</th>
        <th rowspan="2" width="50px;">Marital<br>Status</th>
        <th rowspan="2" width="50px;">Grav. #</th>
        <th rowspan="2" width="50px;">Para</th>
        <th rowspan="2" width="80px;">LMP</th>
        <th rowspan="2" width="80px;">EDD</th>
        <th rowspan="2" width="80px;">EGA</th>
        <th colspan="4">Lab</th>
        <th colspan="14">Treatment</th>
    </tr>
    <tr>
        <td align="center" valign="bottom" class="small" width="50px;">Hb</td>
        <td align="center" valign="bottom" class="small" width="50px;">Urine Prot.</td>
        <td align="center" valign="bottom" class="small" width="40px;">RPR 1</td>
        <td align="center" valign="bottom" class="small" width="40px;">RPR 2</td>
        <td align="center" valign="bottom" class="small" width="40px;">AntiH.</td>
        <td align="center" valign="bottom" class="small" width="40px;">SP 1</td>
        <td align="center" valign="bottom" class="small" width="40px;">SP 2</td>
        <td align="center" valign="bottom" class="small" width="40px;">SP 3</td>
        <td align="center" valign="bottom" class="small" width="40px;">Fol.</td>
        <td align="center" valign="bottom" class="small" width="40px;">Iron</td>
        <td align="center" valign="bottom" class="small" width="40px;">Multi</td>
        <td align="center" valign="bottom" class="small" width="40px;">B Peni.</td>
        <td align="center" valign="bottom" class="small" width="20px;">TT1</td>
        <td align="center" valign="bottom" class="small" width="20px;">TT2</td>
        <td align="center" valign="bottom" class="small" width="20px;">TT3</td>
        <td align="center" valign="bottom" class="small" width="20px;">TT4</td>
        <td align="center" valign="bottom" class="small" width="20px;">TT5</td>
        <td align="center" valign="bottom" class="small" width="20px;">ITN</td>
        <!--<td align="center" valign="bottom" class="small" width="60px;">from</td>
        <td align="center" valign="bottom" class="small" width="60px;">to</td>-->
    </tr>
<c:set var="smRegisterString" value="unknown"/>
<c:set var="patientName" value="unknown"/>
<logic:iterate id="patient" name="register" property="patients" indexId="cnt">
<c:set var="pageBreakCount" value="${pageBreakCount + 1}"/>
<tr>
    <td align="center" class="small" width="70px">
        <c:choose>
            <c:when test="${fn:length(patient.encounters) >0}">
                ${patient.encounters[0].dateVisit}
                <c:if test="${! empty patient.encounters[0].staffName}"><br/>
                Attended:<br/>
                ${patient.encounters[0].staffName}</c:if>
                <c:if test="${! empty patient.encounters[0].antenatalReferralFrom}"><br/>
                Referred:<br/>
                ${patient.encounters[0].antenatalReferralFrom}</c:if>
                <c:if test="${! empty patient.encounters[0].antenatalReferralTo}"><br/>
                Admitted:<br/>
                ${patient.encounters[0].antenatalReferralTo}</c:if>
            </c:when>
            <c:otherwise>
                ${patient.firstAttDate}<c:if test="${! empty patient.staffName}"><br/>
                Attended:<br/>
                ${patient.staffName}</c:if>
            </c:otherwise>
        </c:choose>       
    </td>
    <td align="center" class="small" width="120px">
        <c:choose>
            <c:when test="${! empty patient.smRegisterNumber}">
                <c:set var="smRegisterString" value="${patient.smRegisterNumber}"/>
            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>
        <html:link action="/safeMotherhood.do" paramId="patientId" paramName="patient"
                   paramProperty="patientId">${smRegisterString}</html:link>
        <c:choose>
            <c:when test="${! empty patient.name}">
                <c:set var="patientName" value="${patient.name}"/>
            </c:when>
        </c:choose>
        <html:link action="/demographics.do" paramId="patientId" paramName="patient"
                   paramProperty="patientId">${patient.name}</html:link>
        <br/>
        <c:choose>
            <c:when test="${! empty patient.address1}">${patient.address1}</c:when>
        </c:choose>
        <c:choose>
            <c:when test="${! empty patient.address2}"><br/>${patient.address2}</c:when>
        </c:choose>
    </td>

    <td align="center" class="small" width="30px;">${patient.age}</td>
    <td align="center" class="small" width="50px;">${patient.maritalStatus}</td>
    <td align="center" class="small" width="50px;">${patient.gravida}</td>
    <td align="center" class="small" width="50px;">${patient.parity}</td>
    <td align="center" class="small" width="80px;">
        <c:choose>
            <c:when test="${! empty patient.lastMenstDate}">${patient.lastMenstDate}</c:when>
            <c:otherwise>unknown</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small" width="80px;">
        <c:choose>
            <c:when test="${! empty patient.estDateDelivery}">${patient.estDateDelivery}</c:when>
            <c:otherwise>unknown</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small" width="80px;">${patient.egaWeeks}</td>

<c:choose>
    <c:when test="${fn:length(patient.encounters) >0}">
        <td align="center" class="small" width="50px;">${patient.encounters[0].labHaemoglobin1}${patient.encounters[0].labHaemoglobin2}</td>
        <td align="center" class="small" width="50px;">${patient.encounters[0].labUrineProtein}</td>
        <td align="center" class="small" width="40px;">${patient.encounters[0].labRPR1}</td>
        <td align="center" class="small" width="40px;">${patient.encounters[0].labRPR2}</td>
        <td align="center" class="small" width="40px;"><c:if test="${patient.encounters[0].treatmentAntihelmenthes == true}">X</c:if></td>
        <td align="center" class="small" width="40px;"><c:if test="${patient.encounters[0].treatmentSP1 == true}">X</c:if></td>
        <td align="center" class="small" width="40px;"><c:if test="${patient.encounters[0].treatmentSP2 == true}">X</c:if></td>
        <td align="center" class="small" width="40px;"><c:if test="${patient.encounters[0].treatmentSP3 == true}">X</c:if></td>
        <td align="center" class="small" width="40px;"><c:if test="${patient.encounters[0].treatmentFolate == true}">X</c:if></td>
        <td align="center" class="small" width="40px;"><c:if test="${patient.encounters[0].treatmentIron == true}">X</c:if></td>
        <td align="center" class="small" width="40px;"><c:if test="${patient.encounters[0].treatmentMultivitamin == true}">X</c:if></td>
        <td align="center" class="small" width="40px;"><c:if test="${patient.encounters[0].treatmentBPenicillin == true}">X</c:if></td>
    </c:when>
    <c:otherwise>
        <td align="center" class="small" colspan="12">
    </c:otherwise>
</c:choose>

    <td align="center" class="small" width="20px;">
        <c:choose>
            <c:when test="${patient.tt1 == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small" width="20px;">
        <c:choose>
            <c:when test="${patient.tt2 == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small" width="20px;">
        <c:choose>
            <c:when test="${patient.tt3 == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small" width="20px;">
        <c:choose>
            <c:when test="${patient.tt4 == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small" width="20px;">
        <c:choose>
            <c:when test="${patient.tt5 == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
    <td align="center" class="small" width="20px;">
        <c:choose>
            <c:when test="${patient.antenatalMotherITNUse == true}">X</c:when>
            <c:otherwise>&nbsp;</c:otherwise>
        </c:choose>
    </td>
</tr>
    <logic:iterate id="sme" name="patient" property="encounters" offset="1">
        <tr>
            <td align="center" class="small" width="70px">${sme.dateVisit}</td>
            <td colspan="8" valign="top">&nbsp;
                        <c:if test="${! empty sme.staffName}">
                Attended: ${sme.staffName} </c:if>
                <c:if test="${! empty sme.antenatalReferralFrom}">
                Referred: ${sme.antenatalReferralFrom} </c:if>
                <c:if test="${! empty sme.antenatalReferralTo}">
                Admitted: ${sme.antenatalReferralTo}</c:if></td>
            <td align="center" class="small" width="50px;">${sme.labHaemoglobin1}${sme.labHaemoglobin2}</td>
            <td align="center" class="small" width="50px;">${sme.labUrineProtein}</td>
            <td align="center" class="small" width="40px;">${sme.labRPR1}</td>
            <td align="center" class="small" width="40px;">${sme.labRPR2}</td>
            <td align="center" class="small" width="40px;"><c:if test="${sme.treatmentAntihelmenthes == true}">X</c:if></td>
            <td align="center" class="small" width="40px;"><c:if test="${sme.treatmentSP1 == true}">X</c:if></td>
            <td align="center" class="small" width="40px;"><c:if test="${sme.treatmentSP2 == true}">X</c:if></td>
            <td align="center" class="small" width="40px;"><c:if test="${sme.treatmentSP3 == true}">X</c:if></td>
            <td align="center" class="small" width="40px;"><c:if test="${sme.treatmentFolate == true}">X</c:if></td>
            <td align="center" class="small" width="40px;"><c:if test="${sme.treatmentIron == true}">X</c:if></td>
            <td align="center" class="small" width="40px;"><c:if test="${sme.treatmentMultivitamin == true}">X</c:if></td>
            <td align="center" class="small" width="40px;"><c:if test="${sme.treatmentBPenicillin == true}">X</c:if></td>
            <td colspan="5">&nbsp;</td>
        </tr>
    </logic:iterate>
<c:if test="${pageBreakCount == 9}">
    <c:set var="pageBreakCount" value="0"/>
    </table>
    <table border="1" cellspacing="0" cellpadding="3" class="reportTablePrint">
    <tr>
        <th rowspan="2" width="70px">Visit Date</th>
        <th rowspan="2" width="120px">SM #, Name, <br>Address</th>
        <th rowspan="2" width="30px;">Age</th>
        <th rowspan="2" width="50px;">Marital<br>Status</th>
        <th rowspan="2" width="50px;">Grav. #</th>
        <th rowspan="2" width="50px;">Para</th>
        <th rowspan="2" width="80px;">LMP</th>
        <th rowspan="2" width="80px;">EDD</th>
        <th rowspan="2" width="80px;">EGA</th>
        <th colspan="4">Lab</th>
        <th colspan="14">Treatment</th>
    </tr>
    <tr>
        <td align="center" valign="bottom" class="small" width="50px;">Hb</td>
        <td align="center" valign="bottom" class="small" width="50px;">Urine Prot.</td>
        <td align="center" valign="bottom" class="small" width="40px;">RPR 1</td>
        <td align="center" valign="bottom" class="small" width="40px;">RPR 2</td>
        <td align="center" valign="bottom" class="small" width="40px;">AntiH.</td>
        <td align="center" valign="bottom" class="small" width="40px;">SP 1</td>
        <td align="center" valign="bottom" class="small" width="40px;">SP 2</td>
        <td align="center" valign="bottom" class="small" width="40px;">SP 3</td>
        <td align="center" valign="bottom" class="small" width="40px;">Fol.</td>
        <td align="center" valign="bottom" class="small" width="40px;">Iron</td>
        <td align="center" valign="bottom" class="small" width="40px;">Multi</td>
        <td align="center" valign="bottom" class="small" width="40px;">B Peni.</td>
        <td align="center" valign="bottom" class="small" width="20px;">TT1</td>
        <td align="center" valign="bottom" class="small" width="20px;">TT2</td>
        <td align="center" valign="bottom" class="small" width="20px;">TT3</td>
        <td align="center" valign="bottom" class="small" width="20px;">TT4</td>
        <td align="center" valign="bottom" class="small" width="20px;">TT5</td>
        <td align="center" valign="bottom" class="small" width="20px;">ITN</td>
    </tr>
</c:if>
</logic:iterate>
</table>
</template:put>
</template:insert>
