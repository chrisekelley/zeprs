<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='ZEPRS: DB update' direct='true'/>
<template:put name='header' content='DB update' direct='true'/>
<template:put name='content' direct='true'>

<p>
<h2><bean:write name="report"/> Complete</h2><br/>
Start: <bean:write name="starttime"/><br/>
End: <bean:write name="endtime"/><br/>
<c:set var="diff" value="${difference}"/>
<c:set var="seconds" value="${diff/1000}"/>
Runtime in seconds seconds: <c:out value="${seconds}"/> <br/>
Runtime in seconds minutes: <c:out value="${seconds/60}"/>
</p>
Total records changed: <bean:write name="totals"/><br/>
<script type="text/javascript">
alert ("Done! Generation took " + ${diff/1000} + " seconds.");
</script>

</template:put>
</template:insert>

<%--

<sql:update var="updateCount" dataSource="jdbc/zeprsReportDB">
UPDATE encounter_value
set patient_id=?,
<sql:param value="${enc.patient_id}"/>
field_id=?,
<sql:param value="${field}"/>
last_modified=?,
<sql:param value="${enc.last_modified}"/>
created=?,
<sql:param value="${enc.created}"/>
last_modified_by=?,
<sql:param value="${enc.last_modified_by}"/>
created_by=?
<sql:param value="${enc.created_by}"/>
WHERE encounter_value.encounter_id = ?
<sql:param value="${enc.erid}"/>
</sql:update>
${i.count}:${field} <br/>
</c:forEach>--%>
