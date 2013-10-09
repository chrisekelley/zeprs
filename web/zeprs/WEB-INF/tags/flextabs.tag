<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="menu" required="true" type="java.util.List"%>

<ul id="tabs">
<c:forEach var="item" begin="0" items="${flextab}">

	        <c:if test="${item.enabled}">


	                    <c:set var="tabClass" value=""/>


	                    <c:choose>
	                    <c:when test='${item.type == "FORWARD"}'>
	                    <li><html:link styleClass="${tabClass}" forward="${item.textTarget}">${item.label}</html:link></li>
	                    </c:when>
	                    <c:when test='${item.type == "MENU"}'>
	                    <li><html:link styleClass="${tabClass}" action="menuList" paramId="target_id" paramName="item" paramProperty="id">${item.label}</html:link></li>
	                    </c:when>
	                    <c:when test='${item.type == "ENCOUNTER"}'>
	                    <li><html:link styleClass="${tabClass}" action="/form${item.targetId}/new">${item.label}</html:link></li>
	                     </c:when>
	                    <c:when test='${item.type == "SEARCH"}'>
	                    <li><html:link styleClass="${tabClass}" forward="search">${item.label}</html:link></li>
	                    </c:when>
	                    <c:when test='${item.type == "LINK"}'>
	                    <li><html:link styleClass="${tabClass}" href="${item.textTarget}">${item.label}</html:link></li>
	                    </c:when>

	                    <c:when test='${item.type == "TAB"}'>
	                        <c:url value="menuList.do" var="myUrl">
	                            <c:param name="target_id" value="${item.id}"/>
	                            <c:param name="subTabId" value="${item.targetId}"/>
	                        </c:url>
	                    <li><a href='<c:out value="/zeprs/${myUrl}"/>' class="${tabClass}">${item.label}</a></li>
	                </c:when>
	                    <c:otherwise>
	                    ${item.label}
	                    </c:otherwise>
	                    </c:choose>


	        </c:if>
</c:forEach>
</ul>



