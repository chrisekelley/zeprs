<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
${field.label}: <c:if test='${field.required}'><span class="asterix">*</span> </c:if><br>
<c:choose>
    <c:when test="${! empty value}">${value}</c:when>
    <c:otherwise>
<c:choose>
    <c:when test="${(empty pageItem.cols) || (pageItem.cols==0)}">
    <html:textarea property="field${field.id}" cols="36" rows="2" style="visibility:hidden; display:none;" styleId="field${field.id}"/>
    </c:when>
    <c:otherwise>
    <html:textarea cols="${pageItem.cols}" rows="${pageItem.rows}" property="field${field.id}" style="visibility:hidden; display:none;" styleId="field${field.id}"/>
    </c:otherwise>
</c:choose>

    </c:otherwise>
</c:choose>
