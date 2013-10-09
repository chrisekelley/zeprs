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
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='Report Management' direct='true'/>
<template:put name='header' content='Report Management' direct='true'/>
<template:put name='content' direct='true'>

<ul>
<logic:iterate id="report" name="subject">
<li>
<html:link action="/admin/report/edit" paramId="id" paramName="report" paramProperty="id"><bean:write name="report" property="label"/></html:link>
</li>
</logic:iterate>
</ul>

<html:link action="/admin/report/new">Create a new report</html:link><br/>

</template:put>
</template:insert>