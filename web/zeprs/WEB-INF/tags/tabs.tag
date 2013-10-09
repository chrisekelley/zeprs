<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ attribute name="menu" required="true" type="java.util.List"%>
<%@ attribute name="subtab" required="true" type="java.util.List"%>
<%@ attribute name="subtab2" required="false" type="java.util.List"%>
<%@ attribute name="patient" required="true" type="org.cidrz.webapp.dynasite.valueobject.Patient" %>

<ul id="tabs">
<c:forEach var="item" begin="0" items="${menu}">

    <c:if test="${item.enabled}">

         <c:if test="${(!item.requirePatient) || (!empty patient)}">

                <c:choose>
                <c:when test='${item.id == param.target_id}'>
                <c:set var="tabClass" value="here"/>
                </c:when>
                <c:otherwise>
                <c:set var="tabClass" value=""/>
                </c:otherwise>
                </c:choose>

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
                <c:when test='${item.type == "RECORD"}'>
                <c:url value="patientRecord.do" var="myUrl">
                        <c:param name="target_id" value="${item.id}"/>
                        <c:param name="subTabId" value="${item.targetId}"/>
                    </c:url>
                <li><a href='<c:out value="/zeprs/${myUrl}"/>' class="${tabClass}">${item.label}</a></li>
                </c:when>
                <c:when test='${item.type == "TAB"}'>
                    <c:url value="menuList.do" var="myUrl">
                        <c:param name="target_id" value="${item.id}"/>
                        <%--<c:param name="subTabId" value="${item.targetId}"/>--%>
                    </c:url>
                <li><a href='<c:out value="/zeprs/${myUrl}"/>' class="${tabClass}">${item.label}</a></li>
                <c:choose>
                    <c:when test='${item.id == param.target_id}'>

                        <c:set var="subtabClass" value=""/>
                        <ul>
                        <c:forEach var="item" begin="0" items="${subtab}">
                            <c:choose>
                                <c:when test='${item.id == param.subTabId}'>
                                <c:set var="tabClass" value="here"/>
                                </c:when>
                                <c:otherwise>
                                <c:set var="tabClass" value=""/>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test='${item.type == "FORWARD"}'>
                                <li><html:link styleClass="${subtabClass}" forward="${item.textTarget}">${item.label}</html:link></li>
                                </c:when>
                                <c:when test='${item.type == "MENU"}'>
                                <li><html:link styleClass="${subtabClass}" action="menuList" paramId="target_id" paramName="item" paramProperty="id">${item.label}</html:link></li>
                                </c:when>
                                <c:when test='${item.type == "ENCOUNTER"}'>
                                <c:url value="/form${item.targetId}/new.do" var="myUrl">
                                        <c:param name="target_id" value="${param.target_id}"/>
                                        <c:param name="subTabId" value="${param.subTabId}"/>
                                    </c:url>
                                    <li><a href='<c:out value="${myUrl}"/>' class="${tabClass}">${item.label}</a></li>
                                 </c:when>
                                <c:when test='${item.type == "SEARCH"}'>
                                <li><html:link styleClass="${subtabClass}" forward="search">${item.label}</html:link></li>
                                </c:when>
                                <c:when test='${item.type == "LINK"}'>
                                <li><html:link styleClass="${subtabClass}" href="${item.textTarget}">${item.label}</html:link></li>
                                </c:when>
                                <c:when test='${item.type == "TAB"}'>
                                    <c:url value="/menuList.do" var="myUrl">
                                        <%--<c:param name="target_id" value="${item.id}"/>--%>
                                        <c:param name="target_id" value="${param.target_id}"/>
                                        <%--<c:param name="subTabId" value="${item.targetId}"/>--%>
                                        <c:param name="subTabId" value="${item.id}"/>
                                        <%--<c:param name="subTab2Id" value="${item.targetId}"/>--%>
                                    </c:url>
                                    <li><a href='<c:out value="${myUrl}"/>' class="${tabClass}">${item.label}</a></li>
                                    <c:choose>
                                        <c:when test='${!empty param.subTabId}'>

                                            <c:set var="subtabClass" value=""/>
                                            <ul id="tabs">
                                            <c:forEach var="item" begin="0" items="${subtab2}">
                                                <c:choose>
                                                    <c:when test='${item.id == param.subTab2Id}'>
                                                    <c:set var="tabClass" value="here"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <c:set var="tabClass" value=""/>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test='${item.type == "FORWARD"}'>
                                                    <li><html:link styleClass="${subtabClass}" forward="${item.textTarget}">${item.label}</html:link></li>
                                                    </c:when>
                                                    <c:when test='${item.type == "MENU"}'>
                                                    <li><html:link styleClass="${subtabClass}" action="menuList" paramId="target_id" paramName="item" paramProperty="id">${item.label}</html:link></li>
                                                    </c:when>
                                                    <c:when test='${item.type == "ENCOUNTER"}'>
                                                    <c:url value="/form${item.targetId}/new.do" var="myUrl">
                                                            <c:param name="target_id" value="${param.target_id}"/>
                                                            <c:param name="subTabId" value="${param.subTabId}"/>
                                                            <c:param name="subTab2Id" value="${item.id}"/>
                                                        </c:url>
                                                        <li><a href='<c:out value="${myUrl}"/>' class="${tabClass}">${item.label}</a></li>
                                                     </c:when>
                                                    <c:when test='${item.type == "SEARCH"}'>
                                                    <li><html:link styleClass="${subtabClass}" forward="search">${item.label}</html:link></li>
                                                    </c:when>
                                                    <c:when test='${item.type == "LINK"}'>
                                                    <li><html:link styleClass="${subtabClass}" href="${item.textTarget}">${item.label}</html:link></li>
                                                    </c:when>
                                                    <c:when test='${item.type == "TAB"}'>
                                                        <c:url value="menuList.do" var="myUrl">
                                                            <%--<c:param name="target_id" value="${item.id}"/>--%>
                                                            <c:param name="target_id" value="${param.target_id}"/>
                                                            <c:param name="subTabId" value="${item.targetId}"/>
                                                        </c:url>
                                                        <li><a href='<c:out value="${myUrl}"/>' class="${tabClass}">${item.label}</a></li>
                                                    </c:when>
                                                </c:choose>
                                            </c:forEach>
                                            </ul>
                                        </c:when>
                                        <c:otherwise>
                                           <c:if test='${ (empty param.target_id) || (param.target_id == 115)}'>
                                           <c:if test="${empty tabhelp}">
                                           <c:set var="tabhelp" value="1" scope="page" />
                                                <ul>
                                                    <li class="tabhelp">To add an Encounter, select an item from the Menu above.</li>
                                                </ul>
                                           </c:if>
                                           </c:if>
                                         </c:otherwise>
                                    </c:choose>


                                </c:when>
                            </c:choose>
                        </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                       <c:if test='${ (empty param.target_id) || (param.target_id == 115)}'>
                       <c:if test="${empty tabhelp}">
                       <c:set var="tabhelp" value="1" scope="page" />
                            <ul>
                                <li class="tabhelp">To add an Encounter, select an item from the Menu above.</li>
                            </ul>
                       </c:if>
                       </c:if>
                     </c:otherwise>
                </c:choose>
            </c:when>
                <c:otherwise>
                ${item.label}
                </c:otherwise>
                </c:choose>

        </c:if>
    </c:if>
</c:forEach>
</ul>



