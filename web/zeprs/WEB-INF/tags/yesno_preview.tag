<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<%@ attribute name="showlabel" required="false" type="java.lang.String" %>
<c:set var="field" value="${pageItem.form_field}" />
<c:if test="${showlabel != '0'}">
<label>${field.label}: <c:if test='${field.required}'><span class="asterix">*</span></c:if></label>
</c:if>
<c:choose>
    <c:when test="${! empty pageItem.visibleEnumIdTrigger1}">
        <c:choose>
        <c:when test="${pageItem.visibleEnumIdTrigger1 == 1}">
            <c:choose>
            <c:when test="${pageItem.inputType == 'collapsing-tbl-for-yesno'}">
            <input type="radio" name="field${field.id}"  styleId="field${field.id}" value="1" onclick="toggleField('Yes/No Table',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', '${field.id}');" /><label for="field${field.id}">Yes</label>
            <input type="radio" name="field${field.id}"  styleId="field${field.id}" value="0" onclick="toggleField('Yes/No Table',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', '${field.id}');"/><label for="field${field.id}">No</label>
            <input type="radio" name="field${field.id}" value=""/><label for="field${field.id}">N/A</label>
            </c:when>
            <c:otherwise>
            <input type="radio" name="field${field.id}"  styleId="field${field.id}" value="1" onclick="toggleField('Yes/No',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', '${field.id}');" /><label for="field${field.id}">Yes</label>
            <input type="radio" name="field${field.id}"  styleId="field${field.id}" value="0" onclick="toggleField('Yes/No',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', '${field.id}');"/><label for="field${field.id}">No</label>
            <input type="radio" name="field${field.id}" value=""/><label for="field${field.id}">N/A</label>
            </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
        <input type="radio" name="field${field.id}"  styleId="field${field.id}Y" value="1" onclick="toggleField('Yes/No',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', '${field.id}');" /><label for="field${field.id}Y">Yes</label>
        <input type="radio" name="field${field.id}"  styleId="field${field.id}N" value="0" onclick="toggleField('Yes/No',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', '${field.id}');"/><label for="field${field.id}N">No</label>
        <input type="radio" name="field${field.id}" value=""/><label for="field${field.id}">N/A</label>
        </c:otherwise>
        </c:choose>
    </c:when>
    <c:when test="${! empty pageItem.visibleDependencies1}">
        <input type="radio" name="field${field.id}"  styleId="field${field.id}" value="1" onclick="toggleField('Yes/No',1, '${pageItem.visibleDependencies1}', '${field.id}');" /><label for="field${field.id}">Yes</label>
        <input type="radio" name="field${field.id}"  styleId="field${field.id}" value="0" onclick="toggleField('Yes/No',0, '${pageItem.visibleDependencies1}', '${field.id}');"/><label for="field${field.id}">No</label>
        <input type="radio" name="field${field.id}" value=""/><label for="field${field.id}">N/A</label>
    </c:when>
    <c:otherwise><%--<bean:define id="thisValue" name="form${encounterForm.id}" property="field${field.id}"/>${thisValue}--%>
        <input type="radio" name="field${field.id}"  styleId="field${field.id}"  value="1"/><label for="field${field.id}">Yes</label>
        <input type="radio" name="field${field.id}"  styleId="field${field.id}" value="0"/><label for="field${field.id}">No</label>
        <input type="radio" name="field${field.id}" value=""/><label for="field${field.id}">N/A</label>
    </c:otherwise>
</c:choose>