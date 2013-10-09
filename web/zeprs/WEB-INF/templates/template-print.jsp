<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="now" class="java.util.Date" />
 <%
     String pageURL = request.getRequestURL().toString();
     pageContext.setAttribute("pageURL",pageURL);

    // String hostname = request.getServerName();
    String hostname = "192.168.20.6";
    pageContext.setAttribute("hostname",hostname);
%>

<html>
    <head>
        <title>${zeprs_session.fullname} - Site: ${zeprs_session.clientSettings.site.name} - <template:get name='title'/> <jsp:include page="../pages/version.html"/></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <script type="text/javascript" src="/zeprs/js/browser_detect.js;jsessionid=${pageContext.request.session.id}"></script>
        <script language="JavaScript" TYPE="text/javascript" src="/zeprs/js/validation.js;jsessionid=${pageContext.request.session.id}"></script>
        <script type="text/javascript" src="/zeprs/js/zeprs.js;jsessionid=${pageContext.request.session.id}"></script>
        <script language="JavaScript" type="text/javascript" src="/zeprs/config/javascript.js;jsessionid=${pageContext.request.session.id}"></script>
        <script language="JavaScript" type="text/javascript" src="/zeprs/js/validation.js;jsessionid=${pageContext.request.session.id}"></script>
        <template:get name='javascript'/>
        <script type="text/javascript">
            //<![CDATA[
            var output = '';
            if (browser.isGecko)
            {
            output += '<link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css">';
            }
            else
            {
            output += '<link rel="stylesheet" href="/zeprs/css/styles-ie.css" charset="ISO-8859-1" type="text/css">';
            }
            document.write(output);
            //]]>
         </script>
    </head>
    <c:choose>
    <%--<c:when test="${fn:endsWith(pageURL, 'patientHome.do')}">
    <body onload="startClock();hide_patient_summary('chartSum','drugSum','labSum','histSum','anteSum','intraSum','postSum','nicuSum','gynSum');">
    </c:when>
    <c:when test="${fn:endsWith(pageURL, 'saveCorrection.do')}">
    <body onload="startClock();hide_patient_summary('chartSum','drugSum','labSum','histSum','anteSum','intraSum','postSum','nicuSum','gynSum');">
    </c:when>--%>
    <c:when test="${fn:endsWith(pageURL, 'problem.do')}">
        <c:choose>
        <c:when test="${param.mode=='problem'}">
        <body onload="startClock();toggleProblemForms('probForm','commentForm');">
        </c:when>
        <c:when test="${param.mode=='comment'}">
        <body onload="startClock();toggleProblemForms('commentForm','probForm');">
        </c:when>
        <c:when test="${param.mode=='edit'}">
        <body onload="startClock()">
        </c:when>
        <c:otherwise>
        <body onload="startClock();toggleProblemForms('probForm','commentForm');">
        </c:otherwise>
        </c:choose>
    </c:when>
    <c:when test="${fn:endsWith(pageURL, 'problem.do')}">
        <c:choose>
        <c:when test="${param.mode=='problem'}">
        <body onload="startClock();toggleProblemForms('probForm','commentForm');">
        </c:when>
        <c:when test="${param.mode=='comment'}">
        <body onload="startClock();toggleProblemForms('commentForm','probForm');">
        </c:when>
        <c:when test="${param.mode=='edit'}">
        <body onload="startClock()">
        </c:when>
        <c:otherwise>
        <body onload="startClock();toggleProblemForms('probForm','commentForm');">
        </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
    <body onload="startClock()">
    </c:otherwise>
    </c:choose>

<logic:present name="zeprs_session" property="sessionPatient">

        <%--<c:if test="${(encounter.form.id !=1)}"></c:if>--%>
    <%--<fmt:formatDate type="both" pattern="dd/MM/yyyy" value="${recent_edd_calc}" var="fmt_recent_edd_calc" />--%>
    <div id="patientStatus-print">

        <table cellpadding="0" cellspacing="0" bgColor = "white" summary="Patient Status Bar" width="100%">
        <thead>
            <tr class="patientrowheader">
                <th>Staff</th>
                <th>Patient</th>
                <th>NRC Number</th>
                <th>District ID</th>
                <c:if test="${empty zeprs_session.sessionPatient.parentId}"><th>EGA (calc.)</th></c:if>
                <th>Date/Time</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="patientrow">${fullname}</td>
                <td class="patientrow"><html:link styleClass="patient" action="patientHome" paramId="patientId" paramName="zeprs_session" paramProperty="sessionPatient.id" >
        ${fn:substring(zeprs_session.sessionPatient.surname,0,12)},${fn:substring(zeprs_session.sessionPatient.firstName,0,8)}</html:link></td>
                <td class="patientrow">${zeprs_session.sessionPatient.nrcNumber}</td>
                <td class="patientrow">${zeprs_session.sessionPatient.districtPatientid}</td>
                <c:if test="${empty zeprs_session.sessionPatient.parentId}">
                <c:choose>
                    <c:when test="${! empty zeprs_session.sessionPatient.currentEgaDays}">
                    <%
                        //String egaToday = String.valueOf(patient.getPatientStatusreport().calcEgaToday());
                        //pageContext.setAttribute("egaToday", egaToday);
                    %>
                    <c:set var="days"  value="${egaToday % 7}" />
                    <c:set var="weeks" value="${egaToday/7}" />
                    <td class="patientrow"><html:link action="pregnancyDating.do"><fmt:parseNumber value="${weeks}" integerOnly="true" /> ${days}/7</html:link></td>
                    </c:when>
                    <c:otherwise>
                    <td class="patientrow">n/a</td>
                    </c:otherwise>
                </c:choose>
                </c:if>
                <td class="patientrow"><fmt:formatDate type="both" pattern="dd/MM/yy" value="${now}" />&nbsp;<input type="text" id="pcTime" size="7" class="clock" onfocus="this.blur()"/></td>
            </tr>
        </tbody>
        </table>
    </div>
        </logic:present>
        <h2><template:get name='header' ignore="true"/></h2>
        <template:get name='content'/>
    </body>
</html>

