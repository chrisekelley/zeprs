<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<%@ attribute name="value" required="false" type="java.lang.String" %>
<c:set var="field" value="${pageItem.form_field}" />
<c:choose>
    <c:when test="${! empty value}">
    <input type="checkbox" name="field${field.id}" value="1" checked="checked"/>
    </c:when>
    <c:otherwise>
    <html:checkbox property="field${field.id}" value="1"/>
    </c:otherwise>
</c:choose>

