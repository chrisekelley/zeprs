<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<%@ attribute name="edit" required="false" type="java.lang.String" %>
<c:set var="field" value="${pageItem.form_field}" />
${field.label}: <br/>
<c:choose>
<c:when test="${edit=='true'}">
<strong>Score:</strong> <html:text styleId="apgar${field.id}" property="field${field.id}" size="1"/>
You may re-calculate the Apgar Score if desired.<br/>
<jsp:include page="apgar.jsp" flush="true" ><jsp:param name="id" value="${field.id}"/></jsp:include>
</c:when>
<c:otherwise>
<jsp:include page="apgar.jsp" flush="true" ><jsp:param name="id" value="${field.id}"/></jsp:include>
<html:text styleId="inputWidget${field.id}" property="field${field.id}" size="2"/>
</c:otherwise>
</c:choose>