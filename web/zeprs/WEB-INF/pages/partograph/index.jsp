<%--
  Created by IntelliJ IDEA.
  User: ckelley
  Date: Apr 11, 2005
  Time: 12:38:08 PM
--%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
    <c:when test="${! empty param.template}">
    <c:set var="template" value="/WEB-INF/templates/template-print.jsp"/>
    </c:when>
    <c:otherwise>
    <c:set var="template" value="/WEB-INF/templates/template.jsp"/>
    </c:otherwise>
</c:choose>
<template:insert template='${template}'>
<template:put name='title' content='' direct='true'/>
<template:put name='content' direct="true">
<script language="JavaScript" type="text/javascript" src="/zeprs/dwr/util.js;jsessionid=${pageContext.request.session.id}"></script>
<script language="JavaScript" type="text/javascript" src="/zeprs/js/engine2.jsp;jsessionid=${pageContext.request.session.id}"></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwr/interface/Partograph.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" TYPE="text/javascript" src="/zeprs/js/partograph.js;jsessionid=${pageContext.request.session.id}"></script>
<c:url value="partograph.do" var="part1"><c:param name="part" value="1"/></c:url>
<c:url value="partograph.do" var="part2"><c:param name="part" value="2"/></c:url>
<c:url value="partograph.do" var="part3"><c:param name="part" value="3"/></c:url>
<c:url value="partograph.do" var="close"><c:param name="part" value="close"/></c:url>
<c:url value="partograph.do" var="all"><c:param name="part" value="all"/></c:url>
<c:choose>
<c:when test="${! empty param.part}">
<c:set var="showPart" value="${param.part}"/>
</c:when>
<c:otherwise>
<c:set var="showPart" value="all"/>
</c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${! empty param.template}">
    <div id="form-print">
    </c:when>
    <c:otherwise>
    <div id="partograph">
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${! empty param.page}">
    <c:set var="partoPage" value="${param.page}"/>
    </c:when>
    <c:otherwise>
    <c:set var="partoPage" value="1"/>
    </c:otherwise>
</c:choose>
<script type='text/javascript'>
    var partographClosed = false;
    var uthSite = false;
     <c:if test="${zeprs_session.clientSettings.site.siteTypeId ==2}">
         uthSite = true;
     </c:if>
    var startRow = 1;
</script>
<c:choose>
    <c:when test="${partoPage == 1}">
        <c:set var="startRow" value="1" scope="request"/>
        <c:set var="stopRow" value="16" scope="request"/>
        <c:set var="startRowHour" value="1" scope="request"/>
        <c:set var="stopRowHour" value="8" scope="request"/>
        <script type='text/javascript'>startRow = 1;</script>
    </c:when>
    <c:when test="${partoPage == 2}">
        <c:set var="startRow" value="17" scope="request"/>
        <c:set var="stopRow" value="32" scope="request"/>
        <c:set var="startRowHour" value="9" scope="request"/>
        <c:set var="stopRowHour" value="16" scope="request"/>
        <script type='text/javascript'>startRow = 17;</script>
    </c:when>
    <c:when test="${partoPage == 3}">
        <c:set var="startRow" value="33" scope="request"/>
        <c:set var="stopRow" value="48" scope="request"/>
        <c:set var="startRowHour" value="17" scope="request"/>
        <c:set var="stopRowHour" value="24" scope="request"/>
        <script type='text/javascript'>startRow = 33;</script>
    </c:when>
    <c:otherwise>
        <c:set var="startRow" value="1" scope="request"/>
        <c:set var="stopRow" value="16" scope="request"/>
        <c:set var="startRowHour" value="1" scope="request"/>
        <c:set var="stopRowHour" value="8" scope="request"/>
        <script type='text/javascript'>startRow = 1;</script>
    </c:otherwise>
</c:choose>
<c:if test="${! empty started}">
    <fmt:formatDate var="startHour" value="${started}" pattern="HH" type="both" scope="page"/>
    <fmt:formatDate var="startMinutes" value="${started}" pattern="mm" scope="page"/>
</c:if>

<c:choose>
    <c:when test="${subject.id != null}">
        <h2>Partograph
            <logic:present role="DELETE_ARCHIVE_PATIENT_RECORDS">
                <c:url var="delUrl" value="admin/deleteEncounter.do">
                    <c:param name="encounterId" value="${subject.id}"/>
                    <c:param name="formId" value="${formId}"/>
                </c:url>
             &nbsp;(<a href='<c:out value="/zeprs/${delUrl}"/>'onclick="return confirm('Caution: Are you sure you want to delete this record from ZEPRS?');self.close();" title="Delete this record.">X</a>)
            </logic:present>
        </h2>
    </c:when>
    <c:otherwise>
        <h2>Partograph</h2>
    </c:otherwise>
</c:choose>

<c:if test="${! empty delivSum}">
    <p class="error" id="partoClosed">Partograph is closed - no data entry is permitted.</p>
   <script type='text/javascript'>
        partographClosed = true;
   </script>
</c:if>
    <c:url value="partograph.do" var="parto1">
        <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
        <c:param name="page" value="1"/>
    </c:url>
    <c:url value="partograph.do" var="parto2">
        <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
        <c:param name="page" value="2"/>
    </c:url>
<c:url value="partograph.do" var="parto3">
        <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
        <c:param name="page" value="3"/>
    </c:url>
<jsp:include page="toc.jsp"/>
<table width="100%">
    <tr>
        <td><form name="dateVisit">
        <zeprs:date_visit_no_form dateVisit="${dateVisit}" element = "dateVisit" field = "field1"/>
        </form></td>
        <c:if test="${! empty started}"><td>Partograph started: <fmt:formatDate value="${started}" pattern="dd MMM yyyy  HH:mm"/></td></c:if>
        <td valign="right">
        <c:choose>
            <c:when test="${partoPage == 1}">
            Hours 1-8 | <a href='<c:out value="/zeprs/${parto2}"/>'>Hours 9-16</a> | <a href='<c:out value="/zeprs/${parto3}"/>'>Hours 17-24</a>
            </c:when>
            <c:when test="${partoPage == 2}">
            <a href='<c:out value="/zeprs/${parto1}"/>'>Hours 1-8</a> | Hours 9-16 | <a href='<c:out value="/zeprs/${parto3}"/>'>Hours 17-24</a>
            </c:when>
            <c:when test="${partoPage == 3}">
            <a href='<c:out value="/zeprs/${parto1}"/>'>Hours 1-8</a> | <a href='<c:out value="/zeprs/${parto2}"/>'>Hours 9-16</a> | Hours 17-24
            </c:when>
            <c:otherwise>
            <a href='<c:out value="/zeprs/${parto1}"/>'>Hours 1-8</a> | <a href='<c:out value="/zeprs/${parto2}"/>'>Hours 9-16</a> | <a href='<c:out value="/zeprs/${parto3}"/>'>Hours 17-24</a>
            </c:otherwise>
        </c:choose>
        </td>
    </tr>
</table>
<c:choose>
    <c:when test="${param.exam == 'vaginal'}">
        <jsp:include page="vaginal_exam.jsp"/>
    </c:when>
    <c:when test="${param.exam == 'drugs'}">
        <jsp:include page="drugs_dispensed.jsp"/>
    </c:when>
    <c:otherwise>
        <table cellpadding="0" cellspacing="0" class="partoForm">

            <tr>
                <td colspan="18" valign="top" class="partoSpacer">
                    <a href="#" onclick="togglePartoSection('fetalHrRow', '14', '', '', '', '', '', '', '', '');"
                       class="partoSpacer"><span id="fetalHrRowToggle" class="sectionToggle">&nbsp;-&nbsp;</span>&nbsp;Fetal
                        Heart Rate</a>
                </td>
            </tr>
            <jsp:include page="fetal_heartrate.jsp"/>

            <tr>
                <td colspan="18" class="partoSpacer">
                    <a href="#"
                       onclick="togglePartoSection('liquorRow', '1', 'mouldingRow', '1', '', '', '', '', '', '');"
                       class="partoSpacer"><span id="liquorRowToggle" class="sectionToggle">&nbsp;-&nbsp;</span>&nbsp;Liquor/Moulding</a>
                </td>
            </tr>
            <jsp:include page="liquor.jsp"/>
            <jsp:include page="moulding.jsp"/>

            <tr>
                <td colspan="18" class="partoSpacer">
                    <a href="#"
                       onclick="togglePartoSection('cervixRow', '13', 'descentRow', '13', '', '', '', '', '', '');"
                       class="partoSpacer"><span id="cervixRowToggle" class="sectionToggle">&nbsp;-&nbsp;</span>&nbsp;Cervix/Descent
                        Plot</a>
                </td>
            </tr>
            <jsp:include page="cervix.jsp"/>
            <jsp:include page="descent.jsp"/>
            <tr>
                <td colspan="18" class="partoSpacer">
                    <a href="#"
                       onclick="togglePartoSection('contractionsRow', '5', 'oxytocinRow', '2', '', '', '', '', '', '');"
                       class="partoSpacer"><span id="contractionsRowToggle" class="sectionToggle">&nbsp;-&nbsp;</span>&nbsp;Contractions/Oxytocin</a>
                </td>
            </tr>
            <jsp:include page="contractions.jsp"/>
            <jsp:include page="oxytocin.jsp"/>

            <tr>
                <td colspan="18" class="partoSpacer">
                    <a href="#"
                       onclick="togglePartoSection('systolicRow', '16', 'diastolicRow', '16', 'pulseRow', '16', '', '', '', '');"
                       class="partoSpacer"><span id="systolicRowToggle" class="sectionToggle">&nbsp;-&nbsp;</span>&nbsp;Blood
                        Pressure/Pulse</a>
                </td>
            </tr>
            <jsp:include page="systolic.jsp"/>
            <jsp:include page="diastolic.jsp"/>
            <jsp:include page="pulse.jsp"/>

            <tr>
                <td colspan="18" class="partoSpacer">
                    <a href="#"
                       onclick="togglePartoSection('temperatureRow', '1', 'respirationRow', '1', 'urineAmountRow', '1', 'urinalysisProteinRow', '1', 'urinalysisAcetoneRow', '1', 'urinalysisGlucoseRow', '1');"
                       class="partoSpacer"><span id="temperatureRowToggle" class="sectionToggle">&nbsp;-&nbsp;</span>&nbsp;Temperature/Respiration/Urine
                        Amount/Protein/Acetone/Glucose</a>
                </td>
            </tr>
            <jsp:include page="temperature.jsp"/>
            <jsp:include page="respiration.jsp"/>
            <jsp:include page="urine_amount.jsp"/>
            <jsp:include page="urinalysis_protein.jsp"/>
            <jsp:include page="urinalysis_acetone.jsp"/>
            <jsp:include page="urinalysis_glucose.jsp"/>
            <tr>
                <td colspan="18">
                    <c:choose>
                        <c:when test="${! empty delivSum}">
                            <p class="error">Partograph is closed - no data entry is permitted.</p>
                        </c:when>
                        <c:otherwise>
                            <c:url value="form66/new.do" var="deliverySummary"><c:param name="patientId"
                                                                                        value="${zeprs_session.sessionPatient.id}"/>
                            </c:url>
                            <div style="padding:5px;"><input type="button" value="When delivery occurs, click here."
                                                             onclick="window.location.href='/zeprs/${deliverySummary}';"/>
                            </div>
                        </c:otherwise>
                    </c:choose>

                </td>
            </tr>
            <tr>
                <td colspan="18" class="partoSpacer">&nbsp;</td>
            </tr>
        </table>
        <script type='text/javascript'>
        if (currentStation == "5/5" && currentCervix >= 3) {
            alert("No descent of baby. Station: " + currentStation + " and Cervix: " + currentCervix + "; Refer to UTH.")
        }
        </script>
    </c:otherwise>
</c:choose>

</div>

<div id="elapsedTime">
<span id="timeDisplay"></span>
</div>
<c:if test="${! empty started}">
    <script type='text/javascript'>
        var startDate = new Date(${startedLong});
        time_object.this_start_time = startDate;
        UpdateTiming();
    </script>
</c:if>
</template:put>
</template:insert>