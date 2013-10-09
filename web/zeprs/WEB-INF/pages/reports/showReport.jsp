<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<template:insert template='/WEB-INF/templates/template.jsp'>
<template:put name='title' direct='true'>${reportName}</template:put>
<template:put name='content' direct='true'>
<c:choose>
<c:when test="${reportType=='LUDHMBReport'}">
<jsp:include page="showLUDHMBMaternalReport.jsp"/>
</c:when>
<c:otherwise>
Here is the report!
</c:otherwise>
</c:choose>
</template:put>
</template:insert>