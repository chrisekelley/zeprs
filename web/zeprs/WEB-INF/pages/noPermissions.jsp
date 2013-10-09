<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<template:insert template='/WEB-INF/templates/template-simple.jsp'>
<template:put name='title' content='Home' direct='true'/>
<template:put name='header' content='Access Denied' direct='true'/>
<template:put name='content' direct='true'>
<p>
   You do not have permission to view ZEPRS. Please click <a href="/zeprs/home.do">"this link"</a> to login again
    or contact support for help.
</p>
</template:put>
</template:insert>