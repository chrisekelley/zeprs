<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
<c:choose>
    <c:when test="${! empty pageItem.visibleDependencies1}">
    	<html:checkbox property="field${field.id}" styleId="field${field.id}" value="1" onclick="toggleField('checkbox','1','${pageItem.visibleDependencies1}', '${field.id}');"/>  ${field.label} <c:if test='${field.required}'><span class="asterix">*</span></c:if>
    </c:when>
    <c:otherwise><%--<bean:define id="thisValue" name="${encounterForm.classname}" property="${field.identifier}"/>${thisValue}--%>
		<html:checkbox property="field${field.id}" styleId="field${field.id}" value="1"/>  ${field.label} <c:if test='${field.required}'><span class="asterix">*</span></c:if>
    </c:otherwise>
</c:choose>