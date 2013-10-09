<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<template:insert template='/WEB-INF/templates/template.jsp'>
<template:put name='title' content='ZEPRS' direct='true'/>
<h2>Success!</h2>
<template:put name='content' direct='true'>
    <div id="widePage">
        <c:if test="${! empty message}"><p>${message}</p></c:if>
    </div>
</template:put>
</template:insert>