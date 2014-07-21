<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ page import="java.util.List"%>
<%@ page import="org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects"%>
<%@ page import="org.cidrz.webapp.dynasite.utils.DateUtils"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
    List sites = DynaSiteObjects.getClinics();
    pageContext.setAttribute("sites",sites);
    java.sql.Date dateNow = DateUtils.getNow();
    pageContext.setAttribute("dateNow", dateNow);
%>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='Admin menu' direct='true'/>
<template:put name='header' content='Admin menu' direct='true'/>
<template:put name='help' content='' direct='true'/>
<template:put name='content' direct='true'>
<strong>Clinic/UTH Admin Shortcuts</strong>
<ul>
    <li><html:link action="/admin/restorePatient">Restore Patient</html:link></li>
    <li><html:link action="/admin/users">Create and Modify Users and Group Assignments</html:link></li>
</ul>
<logic:present role="ALTER_PROGRAMS_AND_SCREEN_APPEARANCE">
<strong>Admin Shortcuts</strong>
 <ul>
    <li><html:link action="/admin/ruleList">View List of Rules</html:link></li>
    <li><html:link action="/archive">View Encounter Value archive</html:link></li>
    <li><html:link action="/admin/reportGen">Generate Reports</html:link></li>
    <li><html:link action="/admin/sql/new">SQL console</html:link></li>
    <li><html:link action="/admin/users">Create and Modify Users and Group Assignments</html:link></li>
    <li><html:link action="/admin/subscription/new">Site Subscriptions</html:link></li>
    <li><html:link action="/admin/publisher/new">Site Publisher Setup</html:link></li>
    <li><html:link action="/admin/appupdates/view">Application Update Job Log</html:link></li>
    <li><html:link action="/admin/lims">Lims Preview and Import</html:link></li>
<%--
    <li><html:link href="/zeprs/admin/sql/new.do;jsessionid=${pageContext.request.session.id}?mail=1">Refresh Mail Database</html:link></li>

    <li><html:link action="/admin/propogateUpdates" onclick="return confirm('Propogate updates to remote sites?');self.close();">Propogate Updates</html:link></li>
    --%>
</ul>
<p><strong>Scheduler</strong></p>

<p>The scheduler controls the generation (publishing) of patient XML files and the site's RSS feed, as well as subscription to external RSS feeds.<br/>
Normally the scheduler works automatically. Click the following links to change its behaviour.<br/>
The scheduler will reset to normal operation whenever the web application server is restarted.
Selecting "List" will list pending scheduled operations.
	<br/>
	<ul>
	    <c:url value="scheduler.do" var="start"><c:param name="action" value="start"/></c:url>
	    <c:url value="scheduler.do" var="stop"><c:param name="action" value="stop"/></c:url>
	    <c:url value="scheduler.do" var="list"><c:param name="action" value="list"/></c:url>
		<li><a href='<c:out value="/zeprs/admin/${start}"/>'>Start</a> | <a href='<c:out value="/zeprs/admin/${stop}"/>'>Stop</a>
		 | <a href='<c:out value="/zeprs/admin/${list}"/>'>List</a></li>
	</ul>
</p>
<p><strong>Site Admin Tools - be very careful with the following links!</strong></p>
<!--<p>In order to edit the forms, you must first place the system in "LOCKED" state.
Proceed to the "System State" link first.</p>-->
<ul>
    <%--<li><html:link href="systemState.do">System State</html:link></li>--%>
    <%--<c:choose>
        <c:when test="${systemState=='0'}" >
        <li><html:link href="/zeprs/admin/formList.do;jsessionid=${pageContext.request.session.id}?systemstate=1">Form Admin - Changes System state automatically. </html:link></li>
        <li><html:link action="/admin/formList" onclick="alert('Change System State if you are planning to edit!');">Form Admin (No system state change)</html:link></li>
        </c:when>
        <c:when test="${systemState=='2'}">--%>
        <li><html:link action="/admin/formList">Form Admin</html:link></li>
        <li><html:link action="/admin/form/import">Import Forms</html:link> - place XML in archive/import/new</li>
        <%--</c:when>
        <c:otherwise>
        <li><html:link action="/admin/formList" onclick="alert('System State set?');self.close();">Form Admin</html:link></li>
        </c:otherwise>
    </c:choose>--%>

    <%--<li><html:link href="reportList.do">Report Admin</html:link></li>--%>
    <%--
            <li><a href='<c:out value="/zeprs/admin/${convertRules}"/>' onclick="return confirm('Convert Rules Only ?');self.close();">Convert Rules Only</a></li>
--%>
    <%-- <c:url value="dynagen.do" var="source"><c:param name="gen" value="1"/></c:url>
    <c:url value="dynagen.do" var="sql"><c:param name="gen" value="2"/></c:url>
    <c:url value="dynagen.do" var="sql2"><c:param name="gen" value="9"/></c:url>
    <c:url value="dynagen.do" var="struts"><c:param name="gen" value="3"/></c:url>
    <c:url value="dynagen.do" var="xml"><c:param name="gen" value="4"/></c:url>
    <c:url value="dynagen.do" var="all"><c:param name="gen" value="5"/></c:url>
    <c:url value="dynagen.do" var="hib"><c:param name="gen" value="6"/></c:url>
    <c:url value="dynagen.do" var="report"><c:param name="gen" value="8"/></c:url>
    <c:url value="dynagen.do" var="convertRules"><c:param name="gen" value="10"/></c:url>
    <li><a href='<c:out value="/zeprs/admin/${all}"/>' onclick="return confirm('Generate source, SQL, struts-config, and xml files?');self.close();">Generate Dynasite Source</a> - The following links generate source to the tomcat instance.
        <ul>
            <li><a href='<c:out value="/zeprs/admin/${source}"/>' onclick="return confirm('Generate Only Dynasite source files?');self.close();">Generate Source Only</a></li>
            <li><a href='<c:out value="/zeprs/admin/${hib}"/>' onclick="return confirm('Generate Only Hibernate mapping files? Be sure to restart the app before clicking this link after generating dynasource');self.close();">Generate Hibernate mapping files/update schema Only</a></li>
            <li><a href='<c:out value="/zeprs/admin/${sql}"/>' onclick="return confirm('Generate SQL Only');self.close();">Generate SQL Only</a></li>
            <li><a href='<c:out value="/zeprs/admin/${sql2}"/>' onclick="return confirm('Generate SQL Delete Script Only');self.close();">Generate SQL Delete Script Only</a></li>
            <li><a href='<c:out value="/zeprs/admin/${struts}"/>' onclick="return confirm('Generate struts-config Only ?');self.close();">Generate struts-config Only</a></li>
            <li><a href='<c:out value="/zeprs/admin/${xml}"/>' onclick="return confirm('Generate xml Only? Please note that if you made changes to the order of a form, you should also click link above to Generate SQL.');self.close();">Generate xml Only</a></li>
            <li><a href='<c:out value="/zeprs/admin/${report}"/>' onclick="return confirm('Generate Report Source Only ?');self.close();">Generate Report Source Only</a></li>
        </ul>
    </li>
    --%>
    <li>Developers Only - use the following links to generate dynasource code to your source target as well as to the tomcat instance. Adjust the appropriate fields in Constants.
        <ul>
         <c:url value="dynagen.do" var="source"><c:param name="gen" value="1"/><c:param name="dev" value="true"/></c:url>
    <c:url value="dynagen.do" var="sql"><c:param name="gen" value="2"/><c:param name="dev" value="true"/></c:url>
    <c:url value="dynagen.do" var="sql2"><c:param name="gen" value="9"/><c:param name="dev" value="true"/></c:url>
    <c:url value="dynagen.do" var="struts"><c:param name="gen" value="3"/><c:param name="dev" value="true"/></c:url>
    <c:url value="dynagen.do" var="xml"><c:param name="gen" value="4"/><c:param name="dev" value="true"/></c:url>
    <c:url value="dynagen.do" var="all"><c:param name="gen" value="5"/><c:param name="dev" value="true"/></c:url>
    <c:url value="dynagen.do" var="hib"><c:param name="gen" value="6"/><c:param name="dev" value="true"/></c:url>
    <c:url value="dynagen.do" var="report"><c:param name="gen" value="8"/><c:param name="dev" value="true"/></c:url>
    <c:url value="dynagen.do" var="formChanges"><c:param name="gen" value="10"/></c:url>
    <li><a href='<c:out value="/zeprs/admin/${formChanges}"/>' onclick="return confirm('Generate source, SQL, struts-config, and xml files for all changed forms?');self.close();">Generate Form Changes</a></li>
    <li><a href='<c:out value="/zeprs/admin/${all}"/>' onclick="return confirm('Generate source, SQL, struts-config, and xml files?');self.close();">Generate Dynasite Source for all forms</a>
        <ul>
            <li><a href='<c:out value="/zeprs/admin/${source}"/>' onclick="return confirm('Generate Only Dynasite source files?');self.close();">Generate Source Only</a></li>
            <li><a href='<c:out value="/zeprs/admin/${hib}"/>' onclick="return confirm('Generate Only Hibernate mapping files? Be sure to restart the app before clicking this link after generating dynasource');self.close();">Generate Hibernate mapping files/update schema Only</a></li>
            <li><a href='<c:out value="/zeprs/admin/${sql}"/>' onclick="return confirm('Generate SQL Only');self.close();">Generate SQL Only</a></li>
            <li><a href='<c:out value="/zeprs/admin/${sql2}"/>' onclick="return confirm('Generate SQL Delete Script Only');self.close();">Generate SQL Delete Script Only</a></li>
            <li><a href='<c:out value="/zeprs/admin/${struts}"/>' onclick="return confirm('Generate struts-config Only ?');self.close();">Generate struts-config Only</a></li>
            <li><a href='<c:out value="/zeprs/admin/${xml}"/>' onclick="return confirm('Generate xml Only? Please note that if you made changes to the order of a form, you should also click link above to Generate SQL.');self.close();">Generate xml Only</a></li>
            <li><a href='<c:out value="/zeprs/admin/${report}"/>' onclick="return confirm('Generate Report Source Only ?');self.close();">Generate Report Source Only</a></li>
<%--
            <li><a href='<c:out value="/zeprs/admin/${convertRules}"/>' onclick="return confirm('Convert Rules Only ?');self.close();">Convert Rules Only</a></li>
--%>
        </ul>
    </li>
        </ul>
    </li>
    <li><html:link action="/admin/pool">Check Connection Pool Status</html:link></li>
    <li><html:link action="/admin/fieldList">View Field List</html:link></li>
    <li><html:link action="/reports/queryBuilder">Build a query</html:link></li>
    <li><html:link action="/admin/createDirectories">Create directories in archive</html:link></li>
    <li><html:link action="/admin/cleanDirectories">Clean directories in archive (delete files)</html:link></li>
    </ul>

    <p><strong>Test patient generation</strong></p>
    <p>Do not use the following links on a live system!</p>
    <ul>
        <li>Create Single Test Patients:
         <ul>
             <li><html:link action="/admin/createPatient.do">Simple patient -> Problem/labour visit</html:link></li>
             <li><html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?patientType=labour">Simple patient -> Labour</html:link></li>
             <li><html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?patientType=partograph">Patient w/ partograph</html:link></li>
             <li><html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?patientType=infants">Patient w/ newborns</html:link></li>
             <li><html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?patientType=PosIinfant">HIV positive patient w/ newborn</html:link></li>
             <li><html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?patientType=NegInfant">HIV negative patient w/ newborn</html:link></li>
             <li><html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?patientType=NegInfantClose">HIV negative patient w/ newborn; Needs preg. conclusion</html:link></li>
             <li><html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?patientType=NegInfantEnd">HIV negative patient w/ newborn; Pregnancy Concluded</html:link></li>
             <li><html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?patientType=NegInfantEndNoPrevPregs">HIV negative patient w/ newborn; Pregnancy Concluded; No Prev. Pregs</html:link></li>
             <li><html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?patientType=NegInfantSB">HIV negative patient w/ stillbirth</html:link></li>
             <li><html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?patientType=NegInfantSBEnd">HIV negative patient w/ stillbirth; Pregnancy Concluded</html:link></li>
         </ul>
     </li>
     <li>Create Multiple Test Patients: <html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?number=1" onclick="return confirm('Create 10 test patients? This will take about a minute.');self.close();">10</html:link>
         | <html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?number=5" onclick="return confirm('Create 50 test patients? This will take a minute or so.');self.close();">50</html:link>
         | <html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?number=10" onclick="return confirm('Create 100 test patients? This will take a little while.');self.close();">100</html:link>
         | <html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?number=50" onclick="return confirm('Create 500 test patients? Phew! This will take a while.');self.close();">500</html:link>
         | <html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?number=100" onclick="return confirm('Create 1000 test patients? Phew! This will take a while.');self.close();">1000</html:link>
         | <html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?number=500" onclick="return confirm('Create 5000 test patients? Phew! This will take a while.');self.close();">5000</html:link></li>
     <li>Create Multiple Test Patients of a type:
         <ul>
             <li>Simple patient -> Problem/labour visit: <html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?number=1&patientType=basic" onclick="return confirm('Create 10 Simple patient -> Problem/labour visit patients? This will take a minute or so.');self.close();">10</html:link>
             | <html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?number=5&patientType=basic" onclick="return confirm('Create 50 Simple patient -> Problem/labour visit patients? This will take a minute or so.');self.close();">50</html:link>
             | <html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?number=50&patientType=basic" onclick="return confirm('Create 500 Simple patient -> Problem/labour visit patients? Phew! This will take a while.');self.close();">500</html:link></li>
             <li>HIV negative patient w/ newborn: <html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?number=1&patientType=NegInfant" onclick="return confirm('Create 10 HIV negative patient w/ newborn patients? This will take a minute or so.');self.close();">10</html:link>
             | <html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?number=5&patientType=NegInfant" onclick="return confirm('Create 50 HIV negative patient w/ newborn patients? This will take a minute or so.');self.close();">50</html:link>
             | <html:link href="createPatient.do;jsessionid=${pageContext.request.session.id}?number=50&patientType=NegInfant" onclick="return confirm('Create 500 HIV negative patient w/ newborn patients? Phew! This will take a while.');self.close();">500</html:link></li>
         </ul>
     </li>
    </ul>
<h2>Delete patients</h2>
<p>Select Site and End date to delete patients. This will delete all patients whose record in the patient_status table was
created on a date less than (LT) the date selected, at the site selected. The query will delete all patients created or last modified at that site, even if the patient was created at a different site.
To further limit date range, select Begin date (greater than or equal to (GTE) the begin date selected). Careful!</p>
<html:form action="/admin/deletePatient">
	<table cellspacing="0" cellpadding="5" class="enhancedtabletighterCell">
		<tr>
	        <th>Site</th>
	        <th>Begin Date</th>
	        <th>End Date</th>
	        <th></th>
	    </tr>
	    <tr>
			<td>
			<select id="siteSelector" name="siteId">
			    <option value="">Select Site</option>
			            <option value="all">All sites</option>
			            <c:forEach var="site" begin="0" items="${sites}">
			                <c:if test="${site.inactive != 1}">
			                <option value="${site.id}">${site.name}</option>
			                </c:if>
			            </c:forEach>
			    <option value="42">old UTH-NICU</option>
			</select>
			</td>

			<td>
			    <zeprs:date_visit_no_form_no_label element = "chooseReportForm" field = "bdate|bdate1"/>
			</td>

			<td>
				<zeprs:date_visit_no_form_no_label element = "chooseReportForm" dateVisit = "${dateNow}" field = "edate|edate1"/>
			</td>

			<td>
			<center><html:submit/></center>
			</td>
		</tr>
	</table>
   </html:form>
<p><strong>Instructions</strong></p>
<p>Administration of the ZEPRS forms will follow a structured, deliberate process. First, the forms administrator will
design/edit the forms using a test version of the ZEPRS application on his or her own workstation.
After the changes have been approved, generate the distro and upload it to the server.</p>
</logic:present>
</template:put>
</template:insert>