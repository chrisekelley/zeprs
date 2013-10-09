<%@ page import="java.util.List,
org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects"%>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<template:insert template='/WEB-INF/templates/template-simple.jsp'>
<template:put name='title' content='System Setup' direct='true'/>
<template:put name='header' content='System Setup' direct='true'/>
<template:put name='content' direct='true'>
<p>The ZEPRS application retains configuration data about each ZEPRS PC. During this test period, this data is purged
whenever the ZEPRS application is updated. Once this application is deployed, this configuration task - to be performed
by a systems administrator -  will usually happen only once.</p>
<h3>Select site</h3>

<form action="${pageContext.request.contextPath}/setup.do;jsessionid=${pageContext.request.session.id}" method="post">

   <select name="site_id">
   <%
      List sites = DynaSiteObjects.getClinics();
      pageContext.setAttribute("sites",sites);
   %>
   <c:forEach var="site" begin="0" items="${sites}">
      <c:if test="${site.inactive != 1}"><option value="${site.id}">${site.name}</option></c:if>
   </c:forEach>
   </select>
   <input type="submit" value="Submit">
</form>
</template:put>
</template:insert>