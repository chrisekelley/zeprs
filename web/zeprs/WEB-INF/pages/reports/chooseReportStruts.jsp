<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<template:insert template='/WEB-INF/templates/template.jsp'>
<template:put name='title' direct='true'>Choose report</template:put>
<template:put name='content' direct='true'>
This page allows the user to select which report to run.
<p>

<html:form action="/ChooseReportAction">

	<html:select property="report" size="1">
	
		<html:option value="1"><bean:message key="app.chooseReport.report1"/></html:option>

	</html:select>
	
	<html:submit/>
</html:form>
</template:put>
</template:insert>