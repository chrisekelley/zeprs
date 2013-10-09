<%@ tag import="org.cidrz.webapp.dynasite.session.SessionUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="pageItem" required="false" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
<c:choose>
    <c:when test='${param.id != null}'>
        <html:text size="${pageItem.size}" maxlength="16" property="field${field.id}"/>
    </c:when>
    <c:otherwise>
        <html:hidden styleId="siteId" property="field${field.id}" value="${siteAlphaId}"/>
    </c:otherwise>
</c:choose>


