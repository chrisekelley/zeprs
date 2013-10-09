<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
<c:choose>
    <c:when test="${field.id == 52}"><%-- Pregnancy Course--%>
        <html:select property="field${field.id}" styleId="selectfield${field.id}" onchange="pregCourseAbort();" >
            <html:option value="">No Information</html:option>
                <c:forEach var="enum" begin="0" items="${field.enumerations}">
                    <c:if test='${enum.enabled ==true}'>
                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                    </c:if>
                </c:forEach>
        </html:select>
    </c:when>
    <c:when test="${field.id == 224}">
        <html:select property="field${field.id}" styleId="selectfield${field.id}" onchange="validateBP();" >
            <html:option value="">No Information</html:option>
                <c:forEach var="enum" begin="0" items="${field.enumerations}">
                    <c:if test='${enum.enabled ==true}'>
                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                    </c:if>
                </c:forEach>
        </html:select>
    </c:when>
    <c:when test="${field.id == 225}">
        <html:select property="field${field.id}" styleId="selectfield${field.id}" onchange="validateBP();" >
            <html:option value="">No Information</html:option>
                <c:forEach var="enum" begin="0" items="${field.enumerations}">
                    <c:if test='${enum.enabled ==true}'>
                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                    </c:if>
                </c:forEach>
        </html:select>
    </c:when>
<c:when test="${(pageItem.visibleEnumIdTrigger1 > 0)}">
    <html:select property="field${field.id}" styleId="selectfield${field.id}" onchange="toggleField('dropdown',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}','${field.id}');" >
        <html:option value=""> -- </html:option>
            <c:forEach var="enum" begin="0" items="${field.enumerations}">
                <c:if test='${enum.enabled ==true}'>
                <html:option value="${enum.id}">${enum.enumeration}</html:option>
                </c:if>
            </c:forEach>
    </html:select>
</c:when>
<c:otherwise>
    <html:select property="field${field.id}" styleId="selectfield${field.id}" >
        <html:option value=""> -- </html:option>
            <c:forEach var="enum" begin="0" items="${field.enumerations}">
                <c:if test='${enum.enabled ==true}'>
                    <c:choose>
                    <c:when test="${pageItem.form.id =='80'}">
                    <c:if test="${enum.enumeration != 'Other, Describe'}">
                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                    </c:if>
                    </c:when>
                    <c:otherwise>
                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                    </c:otherwise>
                    </c:choose>
                </c:if>
            </c:forEach>
    </html:select>
</c:otherwise>
</c:choose>