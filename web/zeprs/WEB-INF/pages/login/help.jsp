<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri='/WEB-INF/c.tld' prefix="c" %>
<template:insert template='/WEB-INF/templates/login-template.jsp'>
<template:put name='title' content='Login Help' direct='true'/>
<template:put name='content' direct='true'>

<c:set var="display_help" value="Yes" scope="session" />


<h3>Help</h3>
<p>If you are having difficulty logging-in, please contact your administrator. If you do not have a login account, please call the helpdesk (0).</p>
</template:put>
</template:insert>
