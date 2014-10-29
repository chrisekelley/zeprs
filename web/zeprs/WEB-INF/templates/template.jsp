<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:setLocale value="en_GB" scope="session"/>

<html>
    <head>
        <title>${zeprs_session.fullname} - Site: ${zeprs_session.clientSettings.site.name} - <template:get name='title'/> <jsp:include page="../pages/version.html"/></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <script language="JavaScript" type="text/javascript" src="/zeprs/js/browser_detect.js;jsessionid=${pageContext.request.session.id}"></script>
        <script language="JavaScript" type="text/javascript" src="/zeprs/js/fat.js;jsessionid=${pageContext.request.session.id}"></script>
        <script language="JavaScript" type="text/javascript" src="/zeprs/js/zeprs.js;jsessionid=${pageContext.request.session.id}"></script>
        <script language="JavaScript" type="text/javascript" src="/zeprs/config/javascript.js;jsessionid=${pageContext.request.session.id}"></script>
		<script language="JavaScript" type="text/javascript" src="/zeprs/js/validation.js;jsessionid=${pageContext.request.session.id}"></script>
        <!--
        <script language="JavaScript" type="text/javascript" src="/zeprs/dwr/util.js;jsessionid=${pageContext.request.session.id}"></script>
        <script language="JavaScript" type="text/javascript" src="/zeprs/js/engine2.jsp;jsessionid=${pageContext.request.session.id}"></script>
        -->
        <script language="JavaScript" type="text/javascript">
            //<![CDATA[
            var output = '';
            if (browser.isGecko || browser.isSafari) 
            {
            output += '<link rel="stylesheet" href="/zeprs/css/styles-moz.css;jsessionid=${pageContext.request.session.id}" charset="ISO-8859-1" type="text/css">';
            } else {
            output += '<link rel="stylesheet" href="/zeprs/css/styles-ie.css;jsessionid=${pageContext.request.session.id}" charset="ISO-8859-1" type="text/css">';
            }
            document.write(output);
            //]]>
         </script>
    </head>
    <c:choose>
    <c:when test="${!empty dwr}">
    <body onload="startClock();DWRUtil.useLoadingMessage();">
    </c:when>
    <c:otherwise>
    <body onload="startClock();">
    </c:otherwise>
    </c:choose>
    <div id="banner-home">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td align="center" valign="middle" background="/zeprs/images/banner_bkgd.gif" height="46" width="100" ><span class="serviceHeader"><a href="/zeprs/home.do;jsessionid=${pageContext.request.session.id}" border="0">ZEPRS</a></span><br/><span class="serviceHeaderSite">${fn:substring(zeprs_session.clientSettings.site.name,0,12)}</span></td>
            <td background="/zeprs/images/banner_bkgd.gif" align="right">&nbsp;</td>
        </tr>
    </table>
    </div>
    <logic:present name="zeprs_session" property="sessionPatient">
        <logic:present role="VIEW_INDIVIDUAL_PATIENT_RECORDS">
            <jsp:include page="sidenav-full.jsp"/>
        </logic:present>
        <logic:notPresent role="VIEW_INDIVIDUAL_PATIENT_RECORDS">
            <jsp:include page="sidenav-clerk.jsp"/>
        </logic:notPresent>
    </logic:present>
    <logic:notPresent name="zeprs_session" property="sessionPatient">
        <div id="patientStatus-thin">
        <table cellpadding="0" cellspacing="0" bgColor = "white" summary="Patient Status Bar" width="100%">
        <thead>
            <tr class="patientrowheader">
                <th>Staff</th>
                <th>Date/Time</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="patientrow">${zeprs_session.fullname}</td>
                <td class="patientrow"><fmt:formatDate type="both" pattern="dd/MM/yy" value="${now}" />&nbsp;<input type="text" id="pcTime" size="7" class="clock" onfocus="this.blur()"/></td>
            </tr>
        </tbody>
        </table>
    </div>
    <div id="sidebar-a">
        <div id="sidenavcontainer">
            <ul id="navlist">
                <li style="font-size: 11pt;"><a href="/zeprs/home.do;jsessionid=${pageContext.request.session.id}">Home</a></li>
                <logic:present role="CREATE_NEW_PATIENTS_AND_SEARCH">
                <li style="margin: 0px 0px 0px 5px;">&nbsp;</li>
                <li><html:link action="form1/new.do" onclick="alert('Please ensure that you have searched for this patient before creating a new record.');">New Patient</html:link></li>
                </logic:present>
                <logic:present role="VIEW_SELECTED_REPORTS_AND_VIEW_STATISTICAL_SUMMARIES">
                <li style="margin: 0px 0px 0px 5px;">&nbsp;</li>
                <li><html:link action="/reports">Reports</html:link></li>
                </logic:present>
                <li><html:link action="/help">Help</html:link></li>
                <logic:present role="ALTER_PROGRAMS_AND_SCREEN_APPEARANCE">
                <li style="margin: 0px 0px 0px 5px;">&nbsp;</li>
                <li><html:link action="/admin/home">Admin</html:link></li>
                </logic:present>
                <li><html:link action="/logout">Logout</html:link></li>
            </ul><!-- navlist -->
        </div> <!-- sidenavcontainer -->
    </div><!-- sidebar-a -->
    </logic:notPresent>
        <template:get name='content' ignore="true"/>
    </body>
</html>

