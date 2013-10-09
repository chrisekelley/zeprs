<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ attribute name="menu" required="true" type="java.util.List" %>
<%@ attribute name="patient" required="true" type="org.cidrz.webapp.dynasite.valueobject.Patient" %>

<ul id="maintabs">

<c:forEach var="item" begin="0" items="${menu}">
<!--logic:present role="${item.role}"-->
<c:if test="${(!item.requirePatient) || (!empty patient) || (item.enabled)}">
<li>
    <c:choose>
    <c:when test='${item.type == "FORWARD"}'>
    <html:link forward="${item.textTarget}">${item.label}</html:link>
    </c:when>
    <c:when test='${item.type == "MENU"}'>
    <html:link action="menuList" paramId="target_id" paramName="item" paramProperty="id">${item.label}</html:link>
    </c:when>
    <c:when test='${item.type == "ENCOUNTER"}'>
    <html:link action="/form${item.targetId}/new">${item.label}</html:link>
	 </c:when>
    <c:when test='${item.type == "SEARCH"}'>
    <html:link forward="search">${item.label}</html:link>
    </c:when>
    <c:when test='${item.type == "LINK"}'>
    <html:link href="${item.textTarget}">${item.label}</html:link>
    </c:when>
    <c:otherwise>
    ${item.label}
    </c:otherwise>
    </c:choose>
</li>
</c:if>
<!--/logic:present-->
</c:forEach>
<logic:present role="ALTER_PROGRAMS_AND_SCREEN_APPEARANCE">
<li><html:link  href="/zeprs/admin/home.do">Admin</html:link></li>
</logic:present>
<logic:present role="VIEW_INDIVIDUAL_PATIENT_RECORDS">
<li class="form">Search: <form action="${pageContext.request.contextPath}/search.do;jsessionid=${pageContext.request.session.id}" method="post"><input type="text" name="search_string">
</form></li>
</logic:present>
</ul>



