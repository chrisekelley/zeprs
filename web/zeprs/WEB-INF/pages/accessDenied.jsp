<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<template:insert template='/WEB-INF/templates/template-home.jsp'>
<template:put name='title' content='Access denied' direct='true'/>

<template:put name='content' direct='true'>
<div id="home-full">
    <h2>Access denied</h2>
    <p>You do not have permission to view this page. Please hit your "Back" button and select another page.</p>
</div>
</template:put>
</template:insert>