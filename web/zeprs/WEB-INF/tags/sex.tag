<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
${field.label}: <c:if test='${field.required}'><span class="asterix">*</span> </c:if><br>
<c:choose>
    <c:when test="${field.id==490}">
        <html:select property="field${field.id}">
            <html:option value="">No Information</html:option>
            <html:option value="1">Female</html:option>
            <html:option value="2">Male</html:option>
        </html:select>
    </c:when>
    <c:otherwise>
        <html:radio property="field${field.id}" value="1">Female</html:radio>
        <html:radio property="field${field.id}" value="2">Male</html:radio>
    </c:otherwise>
</c:choose>

