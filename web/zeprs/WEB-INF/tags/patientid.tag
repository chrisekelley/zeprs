<%@ tag import="org.cidrz.webapp.dynasite.session.SessionUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri= "/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />

<c:choose>
    <c:when test='${param.id != null}'>
        <html:text size="${pageItem.size}" maxlength="16" property="field${field.id}"/>
    </c:when>
    <c:otherwise>
        <html:hidden styleId="patient" property="field${field.id}" />
    </c:otherwise>
</c:choose>

