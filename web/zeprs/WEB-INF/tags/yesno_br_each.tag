<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
<label>${field.label}: <c:if test='${field.required}'><span class="asterix">*</span></label></c:if>
<br/>
<c:choose>
    <c:when test="${! empty pageItem.visibleEnumIdTrigger1}">
        <c:choose>
        <c:when test="${pageItem.visibleEnumIdTrigger1 == 1}">
            <c:choose>
            <c:when test="${pageItem.inputType == 'collapsing-tbl-for-yesno'}">
            <html:radio property="field${field.id}" styleId="field${field.id}" value="1" onclick="toggleField('Yes/No Table',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', '${field.id}');" /><label for="field${field.id}">Yes</label><br/>
            <html:radio property="field${field.id}" styleId="field${field.id}" value="0" onclick="toggleField('Yes/No Table',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', '${field.id}');"/><label for="field${field.id}">No</label><br/>
            <input type="radio" name="field${field.id}" value=""/><label for="field${field.id}">N/A</label>
            </c:when>
            <c:otherwise>
            <html:radio property="field${field.id}" styleId="field${field.id}" value="1" onclick="toggleField('Yes/No',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', '${field.id}');" /><label for="field${field.id}">Yes</label><br/>
            <html:radio property="field${field.id}" styleId="field${field.id}" value="0" onclick="toggleField('Yes/No',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', '${field.id}');"/><label for="field${field.id}">No</label><br/>
            <input type="radio" name="field${field.id}" value=""/><label for="field${field.id}">N/A</label>
            </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${! empty pageItem.visibleDependencies1}">
                    <html:radio property="field${field.id}" styleId="field${field.id}" value="1" onclick="toggleField('Yes/No',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', '${field.id}');" /><label for="field${field.id}">Yes</label><br/>
                    <html:radio property="field${field.id}" styleId="field${field.id}" value="0" onclick="toggleField('Yes/No',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', '${field.id}');"/><label for="field${field.id}">No</label><br/>
                    <input type="radio" name="field${field.id}" value=""/><label for="field${field.id}">N/A</label>
                </c:when>
                <c:otherwise>
                    <html:radio property="field${field.id}" styleId="field${field.id}" value="1"/><label for="field${field.id}">Yes</label><br/>
                    <html:radio property="field${field.id}" styleId="field${field.id}" value="0"/><label for="field${field.id}">No</label><br/>
                    <input type="radio" name="field${field.id}" value=""/><label for="field${field.id}">N/A</label>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
        </c:choose>
    </c:when>
    <c:when test="${! empty pageItem.visibleDependencies1}">
        <html:radio property="field${field.id}" styleId="field${field.id}" value="1" onclick="toggleField('Yes/No',1, '${pageItem.visibleDependencies1}', '${field.id}');" /><label for="field${field.id}">Yes</label><br/>
        <html:radio property="field${field.id}" styleId="field${field.id}" value="0" onclick="toggleField('Yes/No',1, '${pageItem.visibleDependencies1}', '${field.id}');"/><label for="field${field.id}">No</label><br/>
        <input type="radio" name="field${field.id}" value=""/><label for="field${field.id}">N/A</label>
    </c:when>
    <c:otherwise><%--<bean:define id="thisValue" name="form${encounterForm.id}" property="field${field.id}"/>${thisValue}--%>
        <html:radio property="field${field.id}" styleId="field${field.id}"  value="1"/><label for="field${field.id}">Yes</label><br/>
        <html:radio property="field${field.id}" styleId="field${field.id}" value="0"/><label for="field${field.id}">No</label><br/>
        <input type="radio" name="field${field.id}" value=""/><label for="field${field.id}">N/A</label>
    </c:otherwise>
</c:choose>