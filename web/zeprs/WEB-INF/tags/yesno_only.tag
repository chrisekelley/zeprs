<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
<c:choose>
    <c:when test="${! empty pageItem.visibleEnumIdTrigger1}">
        <c:choose>
	        <c:when test="${pageItem.visibleEnumIdTrigger1 == 1}">
				<html:radio property="field${field.id}" styleId="field${field.id}"  value="1"  onclick="toggleField('Yes/No',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', '${field.id}');" /><label for="field${field.id}Y">Yes</label>
				<html:radio property="field${field.id}" styleId="field${field.id}" value="0"  onclick="toggleField('Yes/No',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', '${field.id}');" /><label for="field${field.id}N">No</label>
				<input type="radio" name="field${field.id}" value=""/><label for="field${field.id}">N/A</label>
	        </c:when>
	        <c:otherwise>
	        <html:radio property="field${field.id}" styleId="field${field.id}"  value="1"/><label for="field${field.id}Y">Yes</label>
			<html:radio property="field${field.id}" styleId="field${field.id}" value="0"/><label for="field${field.id}N">No</label>
			<input type="radio" name="field${field.id}" value=""/><label for="field${field.id}">N/A</label>
	        </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <html:radio property="field${field.id}" styleId="field${field.id}"  value="1"/><label for="field${field.id}Y">Yes</label>
		<html:radio property="field${field.id}" styleId="field${field.id}" value="0"/><label for="field${field.id}N">No</label>
		<input type="radio" name="field${field.id}" value=""/><label for="field${field.id}">N/A</label>
    </c:otherwise>
</c:choose>

