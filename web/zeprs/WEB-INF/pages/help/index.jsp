<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<template:insert template='/WEB-INF/templates/template.jsp'>
<template:put name='title' content='Help' direct='true'/>
    <h2>Help</h2>
<template:put name='content' direct='true'>
    <div id="widePage">
    <c:choose>
        <c:when test="${! empty page}">
        <c:import url="${page}.html"/>
        </c:when>
        <c:otherwise>
        <c:import url="toc.jsp"/>
        </c:otherwise>
    </c:choose>
</div>
</template:put>
</template:insert>