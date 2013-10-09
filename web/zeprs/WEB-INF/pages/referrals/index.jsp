<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:choose>
    <c:when test="${! empty param.template}">
    <c:set var="template" value="/WEB-INF/templates/template-print.jsp"/>
    </c:when>
    <c:otherwise>
    <c:set var="template" value="/WEB-INF/templates/template-home.jsp"/>
    </c:otherwise>
</c:choose>
<template:insert template='${template}'>
<template:put name='title' direct='true'>
ZEPRS: Referrals
</template:put>
<template:put name='header' direct='true'>
Current Referrals
</template:put>
<c:choose>
    <c:when test="${! empty param.rowCount}">
        <c:set var="rowCount" value="${param.rowCount}"/>
    </c:when>
    <c:otherwise>
        <c:set var="rowCount" value="15"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${! empty offset}">
        <c:set var="offset" value="${offset}"/>
    </c:when>
    <c:when test="${! empty param.offset}">
        <c:set var="offset" value="${param.offset}"/>
    </c:when>
    <c:otherwise>
        <c:set var="offset" value="0"/>
    </c:otherwise>
</c:choose>
 <%
//  Set up alternating row colors
String classRow = "evenRow";
%>
<template:put name='content' direct='true'>
<c:choose>
    <c:when test="${! empty param.template}">
    <div id="form-print">
    </c:when>
    <c:otherwise>
    <div id="widePage2">
    </c:otherwise>
</c:choose>
<h2>Active Referrals</h2>
<c:if test="${zeprs_session.clientSettings.site.siteTypeId ==2}">
    <p>To admit a patient, click the "Admit" link under the Admission Date column.</p>
</c:if>
<p>Click the link under the "Referral Date" column to view the patient visit that triggered the referral.</p>
    <p>Only active referrals appear on this list. To query a patient's referral status, use the Search tool on the Home page to find a specific patient.
    A patient's referrals - active and disposed - appear in the UTH Registry section on the left navigation strip.</p>
<%--<p>Currently only the first 100 rows are displayed.</p>--%>
<table cellpadding="2" cellspacing="4" class="enhancedtable">
<%-- Get the value of each column while iterating over rows --%>
<tr>
    <th>Patient</th>
    <th nowrap="true">Referral Date</th>
    <th>Reason</th>
    <th>Clinic</th>
    <th>Priority</th>
    <th>Transport</th>
    <th>Admission Date</th>
    <th>Condition</th>
    <th>Ward/Firm</th>
</tr>
<c:set var="reasonCount" value="0"/>
<c:forEach var="row" items="${results.rows}">
<%
//  Set up alternating row colors
classRow = classRow.equals("evenRow")? "oddRow" : "evenRow";
%>
<c:url value="viewEncounter.do" var="encounter">
    <c:param name="patientId" value="${row.id}"/>
    <c:param name="id" value="${row.encounter_id}"/>
    <c:param name="referralId" value="${row.referralId}"/>
</c:url>

<tr class ="<%= classRow %>">
    <td valign="top"><html:link action="/patientHome" paramId="patientId" paramName="row" paramProperty="id" >${row.surname}, ${row.first_name}</html:link></td>
    <td valign="top"><a href='<c:out value="/zeprs/${encounter}"/>'><bean:write name="row" property="created" format="dd MMM yyyy"/></a>&nbsp;</td>
    <td valign="top">${row.reason}:

        <c:if test="${! empty row.false_labour}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/>False Labour</c:when>
                <c:otherwise>, False Labour<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
        </c:if>

        <c:if test="${! empty row.true_labor}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/>True Labour</c:when>
                <c:otherwise>, True Labour<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${! empty row.rupture_of_membranes}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/>Rupture of membranes</c:when>
                <c:otherwise>, Rupture of membranes<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${! empty row.intact_membranes}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/>Intact Membranes</c:when>
                <c:otherwise>, Intact Membranes<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${! empty row.preeclamp_hypert_1265}">
            <c:choose>
                <c:when test="${reasonCount ==0}">Preeclampsia/Hypertension<c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>, Preeclampsia/Hypertension<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${! empty row.premature_labour}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>Premature Labour</c:if>
        <c:if test="${! empty row.malaria_diag}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>Malaria
        </c:if>
        <c:if test="${! empty row.anaemia}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>Anaemia
        </c:if>
        <c:if test="${! empty row.high_bp_diag}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            High Blood Pressure
        </c:if>
        <c:if test="${! empty row.vaginal_bleeding_diag}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Vaginal Bleeding
        </c:if>
        <c:if test="${! empty row.intrauterine_death}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Intrauterine Death
        </c:if>
        <c:if test="${! empty row.uti_diag}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise> <c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            UTI
        </c:if>
        <c:if test="${! empty row.pneumonia_diag}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Pneumonia
        </c:if>
        <c:if test="${! empty row.tb_diag}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise> <c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            TB
        </c:if>
        <c:if test="${! empty row.vaginal_thrush_diag}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Vaginal Thrush
        </c:if>
        <c:if test="${! empty row.oral_thrush_diag}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Oral Thrush
        </c:if>
        <c:if test="${! empty row.eclampsia}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Eclampsia
        </c:if>
        <c:if test="${! empty row.abruptia_placenta}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Abruptio Placenta
        </c:if>
        <c:if test="${! empty row.miscarriage}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Miscarriage
        </c:if>
        <c:if test="${! empty row.broken_episiotomy}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Broken Episiotomy
        </c:if>
        <c:if test="${! empty row.puerperal_sepsis}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Puerperal Sepsis
        </c:if>
        <c:if test="${! empty row.breast_engorgement}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Breast Engorgement
        </c:if>
        <c:if test="${! empty row.secondary_pph}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Secondary PPH
        </c:if>
        <c:if test="${! empty row.mastitis}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Mastitis
        </c:if>
        <c:if test="${! empty row.breast_abscess}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Breast Abscess
        </c:if>

        <c:if test="${! empty row.bowel_obstruction}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Bowel obstruction
        </c:if>
        <c:if test="${! empty row.indigestion}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Indigestion
        </c:if>
        <c:if test="${! empty row.opthalmia_neonatorum}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Opthalmia neonatorum
        </c:if>
        <c:if test="${! empty row.dehydration}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Dehydration
        </c:if>
        <c:if test="${! empty row.umbilical_infection}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Umbilical infection
        </c:if>
        <c:if test="${! empty row.diarrhoea}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Diarrhoea
        </c:if>
        <c:if test="${! empty row.diag_other}">
            <c:choose>
                <c:when test="${reasonCount ==0}"><c:set var="reasonCount" value="1"/></c:when>
                <c:otherwise>,<c:set var="reasonCount" value="${reasonCount + 1}"/></c:otherwise>
            </c:choose>
            Other: ${row.diag_other}
        </c:if>
    </td>
    <c:set var="reasonCount" value="0"/>
     <td valign="top"><c:if test="${row.site_name !='null'}">
        ${row.site_name}
    </c:if>&nbsp;
        </td>
    <td valign="top"><c:if test="${row.priority_of_referral !='null'}">
        ${row.priority_of_referral}
    </c:if>&nbsp;
        </td>
    <td valign="top"><c:if test="${row.transport !='null'}">
    ${row.transport}
        </c:if>&nbsp;</td>
    <td valign="top" align="center">
        <c:choose>
            <c:when test="${! empty row.uth_admission_date}">
                <bean:write name="row" property="uth_admission_date" format="dd MMM yyyy"/>
            </c:when>
            <c:otherwise>
                <c:if test="${zeprs_session.clientSettings.site.siteTypeId ==2}"><a href='<c:out value="/zeprs/${encounter}"/>'>Admit</a></c:if>
            </c:otherwise>
        </c:choose>
        &nbsp;</td>
    <td valign="top"><c:if test="${row.arrival_condition !='null'}">
    ${row.arrival_condition}
        </c:if>&nbsp;</td>
    <td valign="top"><c:if test="${! empty row.uth_ward_id}">
        <c:choose>
            <c:when test="${row.uth_ward_id == '2956'}">Outpatient Clinic</c:when>
            <c:when test="${row.uth_ward_id == '2916'}">Labor</c:when>
            <c:when test="${row.uth_ward_id == '2957'}">Gynecological</c:when>
            <c:when test="${row.uth_ward_id == '2914'}">Postnatal</c:when>
            <c:when test="${row.uth_ward_id == '2915'}">Antenatal</c:when>
            <c:when test="${row.uth_ward_id == '2921'}">NICU</c:when>
            <c:when test="${row.uth_ward_id == '2922'}">PEDS</c:when>
            <c:when test="${row.uth_ward_id == '2958'}">Not Admitted</c:when>
        </c:choose>
        </c:if><c:if test="${! empty row.uth_ward_id}"><br/>Firm ${row.firm}</c:if>
        &nbsp;</td>
</tr>
</c:forEach>
<tr>
<c:choose>
    <c:when test="${offset == 0}">
        <td class="prevNext">&nbsp;</td>
    </c:when>
    <c:otherwise>
        <td class="prevNext"><html:link href="referrals.do;jsessionid=${pageContext.request.session.id}?offset=${offset-rowCount}&search_string=${searchString}&site=${site}">Previous</html:link></td>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${next == 0}">
        <td class="prevNext" colspan="7">&nbsp;</td><td class="prevNext">&nbsp;</td>
    </c:when>
    <c:when test="${offset == 0}">
        <td class="prevNext" colspan="7">&nbsp;</td>
        <td class="prevNext"><html:link href="referrals.do;jsessionid=${pageContext.request.session.id}?offset=${rowCount}&next=${next}&search_string=${searchString}&site=${site}">Next</html:link></td>
    </c:when>
    <c:otherwise>
        <td class="prevNext" colspan="7">&nbsp;</td>
        <td class="prevNext"><html:link href="referrals.do;jsessionid=${pageContext.request.session.id}?offset=${offset + rowCount}&next=${next}&search_string=${searchString}&site=${site}">Next</html:link></td>
    </c:otherwise>
</c:choose>
</tr>
</table>
<p>&nbsp;</p>
</div>
</template:put>
</template:insert>