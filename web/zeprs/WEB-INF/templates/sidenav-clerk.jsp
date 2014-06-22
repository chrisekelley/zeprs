<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="patientStatus">
        <table cellpadding="0" cellspacing="0" bgColor = "white" summary="Patient Status Bar" width="100%">
        <thead>
            <tr class="patientrowheader">
                <th>Staff</th>
                <th>Patient</th>
                <th>NRC Number</th>
                <th>District ID</th>
                <c:if test="${empty zeprs_session.sessionPatient.parentId}">
                    <c:if test="${(zeprs_session.sessionPatient.currentFlowId ==1) || (zeprs_session.sessionPatient.currentFlowId ==2)
                    || (zeprs_session.sessionPatient.currentFlowId ==7)}">
                    <th>EGA</th>
                    </c:if>
                </c:if>
                <th>Date/Time</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="patientrow">${zeprs_session.fullname}</td>
                <td class="patientrow"><html:link styleClass="patient" action="patientHome" paramId="patientId" paramName="zeprs_session" paramProperty="sessionPatient.id" >
        ${fn:substring(zeprs_session.sessionPatient.surname,0,12)},${fn:substring(zeprs_session.sessionPatient.firstName,0,8)}</html:link></td>
                <td class="patientrow">${zeprs_session.sessionPatient.nrcNumber}</td>
                <td class="patientrow">${zeprs_session.sessionPatient.districtPatientid}</td>
                <c:if test="${empty zeprs_session.sessionPatient.parentId}">
                    <c:if test="${(zeprs_session.sessionPatient.currentFlowId ==1) || (zeprs_session.sessionPatient.currentFlowId ==2)
                    || (zeprs_session.sessionPatient.currentFlowId ==7)}">
                        <c:choose>
                            <c:when test="${! empty zeprs_session.sessionPatient.currentEgaDaysDB}">
                            <c:set var="days"  value="${zeprs_session.sessionPatient.currentEgaCalc % 7}" />
                            <c:set var="weeks" value="${zeprs_session.sessionPatient.currentEgaCalc/7}" />
                            <td class="patientrow"><fmt:parseNumber value="${weeks}" integerOnly="true" /> ${days}/7</td>
                            </c:when>
                            <c:otherwise>
                            <td class="patientrow">n/a</td>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </c:if>
                <td class="patientrow"><fmt:formatDate type="both" pattern="dd/MM/yy" value="${now}" />&nbsp;<input type="text" id="pcTime" size="7" class="clock" onfocus="this.blur()"/></td>
            </tr>
        </tbody>
        </table>
</div>
<div id="sidebar-a">
    <div id="sidenavcontainer">
        <ul id="navlist">
            <li style="font-size: 11pt;"><a href="/zeprs/home.do;jsessionid=${pageContext.request.session.id}">Home</a></li>
            <li style="margin: 0px 0px 0px 5px;">&nbsp;</li>
            <li><html:link action="form1/new.do" onclick="alert('Please ensure that you have searched for this patient before creating a new record.');">New Patient</html:link></li>
            <logic:present role="VIEW_SELECTED_REPORTS_AND_VIEW_STATISTICAL_SUMMARIES">
            <li style="margin: 0px 0px 0px 5px;">&nbsp;</li>
            <li><html:link  href="/zeprs/reports.do">Reports</html:link></li>
            </logic:present>
            <li><html:link  href="/zeprs/referrals.do">Referrals</html:link></li>
            <li><html:link  href="/zeprs/help.do">Help</html:link></li>

           <%-- <li style="margin: 0px 0px 0px 5px;">&nbsp;</li>
       <li><a href='<%= response.encodeURL(printTemplateURL) %>'>Print Version</a></li>--%>

            <logic:present role="ALTER_PROGRAMS_AND_SCREEN_APPEARANCE">
            <li style="margin: 0px 0px 0px 5px;">&nbsp;</li>
            <li><html:link  href="/zeprs/admin/home.do">Admin</html:link></li>
            </logic:present>
            <li><html:link  href="/zeprs/logout.do">Logout</html:link></li>
        </ul><!-- navlist -->
    </div> <!-- sidenavcontainer -->
</div><!-- sidebar-a -->