<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ attribute name="pageItem" required="false" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />

<jsp:useBean id="sites" scope="request" type="java.util.List" />
${field.label}:<br/>
<html:select property="field${field.id}" styleId="site${field.id}">
<option value="">-- Select --</option>

<c:choose>
    <c:when test='${param.id != null}'>
        <c:forEach var="site" begin="0" items="${sites}">
            <c:if test="${site.inactive != 1}">
            <html:option value="${site.id}">${site.name}</html:option>
            </c:if>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:forEach var="site" begin="0" items="${sites}">
            <c:if test="${site.inactive != 1}">
            <c:choose>
            <c:when test="${patientSiteId==site.id}">
            <option value="${site.id}" selected="selected">${site.name}</option>
            </c:when>
            <c:otherwise>
            <html:option value="${site.id}">${site.name}</html:option>
            </c:otherwise>
            </c:choose>
            </c:if>
        </c:forEach>
    </c:otherwise>
</c:choose>
</html:select>

