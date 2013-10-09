<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<%@ attribute name="yearList" required="false" type="java.util.ArrayList" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>

<c:set var="field" value="${pageItem.form_field}" />

<c:choose>
<c:when test="${field.minValue !=0}">
<c:set var="minYear" value="${field.minValue}" />
</c:when>
<c:otherwise>
<c:set var="minYear" value="1900" />
</c:otherwise>
</c:choose>
<jsp:useBean id="now" class="java.util.Date" />

<c:choose>
    <c:when test="${! empty yearList}">
        <html:select property="field${field.id}">
            <html:option value="">Not Recorded</html:option>
            <c:forEach items="${yearList}" var="year" varStatus="yearStatus">
                <c:if test="${year >= minYear}"><html:option value="${year}">${year}</html:option></c:if>
            </c:forEach>
        </html:select>
    </c:when>
    <c:otherwise>
        <fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow"/>
        <html:select property="field${field.id}">
            <html:option value="">Not Recorded</html:option>
            <%--<html:option value="100">N/A</html:option>--%>
            <c:forEach begin="${minYear}" end="${yearnow}" step="1" var="year" varStatus="yearStatus">
                <html:option value="${year}">${year}</html:option>&nbsp;&nbsp;
            </c:forEach>
        </html:select>
    </c:otherwise>
</c:choose>
