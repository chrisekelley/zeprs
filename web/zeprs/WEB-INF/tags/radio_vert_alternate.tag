<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
${field.label}: <c:if test='${field.required}'><span class="asterix">*</span> </c:if><br>
<table>
<c:forEach var="enum" begin="0" items="${field.enumerations}"  varStatus="ctr">
	<c:if test="${ctr.index % 2 == 0}"><tr></c:if>
    <c:choose>
           <c:when test="${(pageItem.visibleEnumIdTrigger1 > 0)}">
                <td><html:radio property="field${field.id}" styleId="field${field.id}${enum.id}" value="${enum.id}" onchange="toggleField('radio',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}','${field.id}${enum.id}');"/> ${enum.enumeration}</td>
            </c:when>
            <c:otherwise>
               <td><html:radio property="field${field.id}" value="${enum.id}"/> ${enum.enumeration}</td>
            </c:otherwise>
     </c:choose>
     <c:if test="${ctr.index % 2 != 0}"></tr></c:if>
</c:forEach>
</table>