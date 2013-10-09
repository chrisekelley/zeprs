<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
${field.label}: <c:if test='${field.required}'><span class="asterix">*</span> </c:if><br>
<html:select property="field${field.id}">
        <c:forEach begin="1" end="31" step="1" var="day">
            <c:choose>
            <c:when test="${day == 1}">
            <option value="${day}" selected="yes">${day}</option>&nbsp;&nbsp;
            </c:when>
            <c:otherwise>
            <html:option value="${day}">${day}</html:option>&nbsp;&nbsp;
            </c:otherwise>
            </c:choose>
        </c:forEach>
</html:select>