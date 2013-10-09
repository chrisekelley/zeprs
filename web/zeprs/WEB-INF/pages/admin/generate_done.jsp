<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ page import="org.cidrz.webapp.dynasite.session.SessionUtil"%>
<%@ taglib uri= "/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri= "/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri= "/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/tlds/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="/WEB-INF/tlds/c-rt.tld" prefix="c" %>

<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='ZEPRS: Source Generate' direct='true'/>
<template:put name='header' content='ZEPRS: Source Generate' direct='true'/>
<template:put name='content' direct='true'>
    <c:url value="reload" var="reload"><c:param name="path" value="/zeprs"/></c:url>
<p>
<h2><bean:write name="report"/> Complete</h2>
    <p>
        <a href='<c:out value="/manager/html/${reload}"/>' onclick="return confirm('Reload ZEPRS app?');self.close();">Reload ZEPRS web application</a>
        in order to view changes.
    </p>
    <br/>
Start: <bean:write name="starttime"/><br/>
End: <bean:write name="endtime"/><br/>
<c:set var="diff" value="${difference}"/>
<%--Runtime in seconds milliseconds: <c:out value="${diff}"/><br/>--%>
<c:set var="seconds" value="${diff/1000}"/>
Runtime in seconds seconds: <c:out value="${seconds}"/> <br/>
Runtime in seconds minutes: <c:out value="${seconds/60}"/>
</p>

<script type="text/javascript">
alert ("Done! Generation took " + ${diff/1000} + " seconds.");
</script>

<c:if test="${! empty message}">
<br/>
<br/>
<pre><c:out value="${message}"/></pre>
</c:if>

</template:put>
</template:insert>