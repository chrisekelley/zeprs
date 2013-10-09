<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri='/WEB-INF/tlds/zeprs.tld' prefix='zeprs' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    java.net.InetAddress i = java.net.InetAddress.getLocalHost();
    String ipAddress = i.getHostAddress();
    pageContext.setAttribute("ipAddress", ipAddress);
%>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
    <template:put name='title' content='Admin: Publisher' direct='true'/>
    <template:put name='header' content='Admin: Publisher' direct='true'/>
    <template:put name='help' content='test' direct='true'/>

    <template:put name='content' direct='true'>
        <html:errors/>
            <c:if test="${! empty generateStatusDate}">
            <p class="error">System is currently processing or generating a feed.<c:if test="${! empty statusMessage}"> Status: ${statusMessage}</c:if></p>
            </c:if>
        <c:if test="${! empty publisher}">
            <p>This server is currently publishing the site <strong>${publisher.site.name}</strong>.</p>
        </c:if>
        <p><html:link action="/admin/subscription/new">Site Subscriptions</html:link></p>
        <div>
            <html:form action="admin/publisher/save" method="POST">
                <div class="row">
                    <span class="label">Site to publish:</span>
                    <span class="formw">
                        <select name="siteId">
                            <c:forEach var="site" begin="0" items="${sites}">
                                <c:if test="${site.inactive != 1}">
                                    <c:choose>
                                        <c:when test="${site.id == publisher.siteId}">
                                            <option value="${site.id}" selected="selected">${site.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${site.id}">${site.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </c:forEach>
                        </select></span>
                </div>

                <div class="row">
                    <span class="label">Server IP or name:</span>
                    <span class="formw">
                        <c:choose>
                            <c:when test="${! empty publisher.url}">
                                <input type="text" name="url" value="${publisher.url}"/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" name="url" value="${ipAddress}"/>
                            </c:otherwise>
                        </c:choose>

                    </span>
                </div>

                <div class="row">
                    <span class="label"></span>
                    <span class="formw">
                        <html:submit value="Save"/></span>
                </div>
            </html:form>
        </div>

        <div class="row">
            <h2>Manual record export</h2>
            <p>Export patient records to XML and create RSS file.
			<ul>
	            <li><html:link action="admin/export.do">Update exported records</html:link>: Export any patient records
	            that have been modified since the last export. Best for sites with small numbers of patients.</li>
	            <c:url value="scheduler.do" var="update"><c:param name="action" value="update"/></c:url>
	            <li><a href='<c:out value="/zeprs/admin/${update}"/>'>Update exported records</a>: Export any patient records
	            that have been modified since the last export by triggering a scheduled job.
	            It is the safest way to update records, especially if you have alot.</li>
	            <li><html:link href="../export.do;jsessionid=${pageContext.request.session.id}?checkXml=true">Update exported records and render missing xml files</html:link>:
	            Export any patient records that have been modified since the last export and
	                check if there are any missing xml patient files and generate them.</li>
	            <c:url value="scheduler.do" var="export"><c:param name="action" value="export"/></c:url>
			    <li><a href='<c:out value="/zeprs/admin/${export}"/>'>Export All</a>: Export all patient records by triggering
			    	a scheduled job. This will generate XML for all patients. It is the safest way to export records, especially if you have alot.
	            	It will delete the current archive log.</li>
	            	<li>
				<c:set var="all" value="1"/>
	                <html:link action="admin/export.do" paramId="all" paramName="all">Export All</html:link>: Export all patient records. Better for small number of records. It will delete the current archive log.
	                </li>
	            <c:url value="scheduler.do" var="export"><c:param name="action" value="exportPatientIdentifierList"/></c:url>
	            <li><a href='<c:out value="/zeprs/admin/${export}"/>'>Export Patient Identifier List</a>: Export Patient Identifier List by triggering
			    	a scheduled job. This will generate JSON for all patient idenitifiers, which is used to sync a new site - helps to prevent duplicate id's from being issued.</li>
			    	<c:url value="scheduler.do" var="importPatientIdentifiers"><c:param name="action" value="importPatientIdentifiers"/></c:url>
		        <li><a href='<c:out value="/zeprs/admin/${importPatientIdentifiers}"/>'>Import Patient Identifier List</a>: Import Patient Identifier List by triggering
				    a scheduled job.
					This will import a JSON-formatted list of all patient identifiers and insert any patient id's that have already been assigned.
					This helps to prevent duplicate id's from being issued.</li>
	            <c:url value="scheduler.do" var="updatearchive"><c:param name="action" value="updatearchive"/></c:url>
	            <li><a href='<c:out value="/zeprs/admin/${updatearchive}"/>'>Update Archive Zip</a>: Updates Archive Zip by triggering
			    	a scheduled job. This will create a zip (${masterZip}) of all patient records that were modified on the previous day.</li>
            </ul>
        </div>

        <div class="row">
            <h2>Manual Archive Creation</h2>
            <p>Create Zip archive of site RSS and XML files.
			<ul>
                <c:url value="scheduler.do" var="archive"><c:param name="action" value="archive"/></c:url>
		    <li><a href='<c:out value="/zeprs/admin/${archive}"/>'>Archive All</a>: Zips all site records into .zip file.</li>
                <c:url value="scheduler.do" var="archiveAir"><c:param name="action" value="archiveAir"/></c:url>
		    <li><a href='<c:out value="/zeprs/admin/${archiveAir}"/>'>Archive one site - AIR</a>: Zips site records for Airport into .zip file.</li>
                </ul>
        </div>

        <div class="row">
            <h2>Scheduler</h2>

            <p>Normally the scheduler works automatically. click the following links to change its behaviour.
            The scheduler will reset to normal operation whenever the web application server is restarted.
            Selecting "List" will list pending scheduled operations.

                <br/>
                <br/>
		        <c:url value="scheduler.do" var="start"><c:param name="action" value="start"/></c:url>
		        <c:url value="scheduler.do" var="stop"><c:param name="action" value="stop"/></c:url>
		        <c:url value="scheduler.do" var="list"><c:param name="action" value="list"/></c:url>
		    	<a href='<c:out value="/zeprs/admin/${start}"/>'>Start</a> | <a href='<c:out value="/zeprs/admin/${stop}"/>'>Stop</a>
		    	 | <a href='<c:out value="/zeprs/admin/${list}"/>'>List</a>
		    </p>
        </div>

        <div class="row">
            <h2>Set Server status</h2>
            <p>Set certain server status variables.
                <ul>
                <li>Halt-Feed-Imports:
		        <c:url value="setServerStatus.do" var="haltImports"><c:param name="status" value="Halt-Feed-Imports"/></c:url>
		        <c:url value="setServerStatus.do" var="restartImports"><c:param name="status" value="Halt-Feed-Imports"/><c:param name="message" value="false"/></c:url>
		    	<a href='<c:out value="/zeprs/admin/${haltImports}"/>'>Stop import of feeds.</a> |
		    	<a href='<c:out value="/zeprs/admin/${restartImports}"/>'>Restart import of feeds</a>
		    	</li>
                <li>RSS-Gen-date:
		        <c:url value="setServerStatus.do" var="removeRssGen"><c:param name="status" value="RSS-Gen-date"/><c:param name="message" value="remove"/></c:url>
		    	<a href='<c:out value="/zeprs/admin/${removeRssGen}"/>'>Remove from Status map</a> - Used when the import feed process is stuck.
		    	</li>
		    	<li>Halt-Database-Comparison:
		        <c:url value="setServerStatus.do" var="haltDbComparison"><c:param name="status" value="Halt-Database-Comparison"/></c:url>
		        <c:url value="setServerStatus.do" var="restartDbComparison"><c:param name="status" value="Halt-Database-Comparison"/><c:param name="message" value="false"/></c:url>
		    	<a href='<c:out value="/zeprs/admin/${haltDbComparison}"/>'>Stop db comparison.</a> |
		    	<a href='<c:out value="/zeprs/admin/${restartDbComparison}"/>'>Restart db comparison</a>
		    	</li>
		    </p>
        </div>

        <div class="row">
            <c:choose>
            <c:when test="${publisher.site.abbreviation == 'ZEP'}">
            <h2>RSS feeds and XML File Check</h2>
            <p>"RSS" links to RSS feed for the site. "Check XML" checks if each patient in seach has an XML file and renders it if necessary.</p>
            <ul>
            <c:forEach var="site" begin="0" items="${sites}">
                <c:if test="${site.inactive != 1 && site.abbreviation != 'ZEP'}">
	                <li>${site.name}: <a href="http://${publisher.url}:8080/archive/${site.abbreviation}/rss.xml">RSS</a> |
	                <html:link href="../export.do;jsessionid=${pageContext.request.session.id}?checkXml=true&siteId=${site.id}">Check XML</html:link> |
	                </li>
                </c:if>
            </c:forEach>
            </ul>
            </c:when>
            <c:otherwise>
            <h2>Local RSS feed</h2>
             <p>
                <a href="http://${publisher.url}:8080/archive/${publisher.site.abbreviation}/rss.xml">rss feed</a>
            </p>
            </c:otherwise>
            </c:choose>
        </div>

    </template:put>
</template:insert>