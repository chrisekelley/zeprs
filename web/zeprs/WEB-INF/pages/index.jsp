<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="en"/>
<c:choose>
<c:when test="${empty site}">
<c:set var="theSite" value="${patientSiteId}"/>
<c:set var="site" value="${site}"/>
</c:when>
<c:otherwise>
<c:set var="theSite" value="${site}"/>
</c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${! empty param.rowCount}">
        <c:set var="rowCount" value="${param.rowCount}"/>
    </c:when>
    <c:otherwise>
        <c:set var="rowCount" value="11"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${! empty offset}">
        <c:set var="offset" value="${offset}"/>
    </c:when>
    <c:when test="${! empty param.offset}">
        <c:set var="offset" value="${param.offset}"/>
    </c:when>
    <c:otherwise>
        <c:set var="offset" value="0"/>
    </c:otherwise>
</c:choose>

<template:insert template='/WEB-INF/templates/template-home.jsp'>
<template:put name="title" content="Home" direct="true"/>
<template:put name='content' direct='true'>
<div id="home-full">
    <div id="search-results">
        <form name="searchForm" action="${pageContext.request.contextPath}/home.do;jsessionid=${pageContext.request.session.id}" method="post">
            <input type="hidden" id="first_surname" name="first_surname">
            <input type="hidden" id="max_rows" name="max_rows">
            <input type="hidden" id="start_row" name="start_row">
            <table>
                <tr>
                    <td><h1>Search for Patient</h1></td>
                    <td>
                        <table class="enhancedtable">
                            <tr>
                                <th colspan="4">
                                   First letter of surname:
            <c:forTokens items="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z" delims="," var="letter">
<%--
            <c:url value="home.do" var="url"><c:param name="search_string" value="${letter}"/><c:param name="site" value="${theSite}"/></c:url>
--%>
            <span class="searchCaps"><a href="#" onclick="processSearch('${letter}')">&nbsp;&nbsp;${letter}&nbsp;&nbsp;</a></span></c:forTokens>
                                </th>
                                <th>Labour</th>
                            </tr>
                            <tr>
                                <td>
                                    <input type="text" id="search_string" name="search_string">
                                </td>
                                <td>
                                    <select name="site">
                                        <c:choose>
                                            <c:when test="${theSite=='all'}">
                                                    <option value="all" selected="selected" >All sites</option>
                                                <c:forEach var="site" begin="0" items="${sites}">
                                                    <c:if test="${site.inactive != 1}"><option value="${site.id}">${site.name}</option></c:if>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="all">All sites</option>
                                                <c:forEach var="site" begin="0" items="${sites}">
                                                    <c:if test="${site.inactive != 1}"><c:choose>
                                                        <c:when test="${theSite==site.id}">
                                                            <option value="${site.id}" selected="selected">${site.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${site.id}">${site.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    </c:if>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </td>

                                <td>
                                    <input type="submit" value="Search">
                                </td>
                                <td>
                                Search keywords:
                                family name, first names,<%-- address,--%> ZEPRS ID, NRC number, and the username of the health worker
                                who last modified the patient's record.
                                </td>
                                <td><input type="checkbox" name="labour" alt="Labour"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    <div id="search-results-body">
    <c:choose>
        <c:when test="${!empty results}">
            <%
            //  Set up alternating row colors
            String classRow = "evenRow";
            %>

            <c:choose>
            <c:when test="${empty searchString}">
            <c:set var="search" value="All Patients"/>
            </c:when>
            <c:otherwise>
            <c:set var="search" value="${searchString}"/>
            </c:otherwise>
            </c:choose>
            Search term: <i>${search}</i>,
            <c:choose>
            <c:when test="${empty searchString}">sorted by date record last modified</c:when>
            <c:otherwise>sorted by surname</c:otherwise>
            </c:choose>
            <br/>
            <div id="search-results-list">
            <c:choose>
            <c:when test="${results.rowCount == 0}">
            <p>Patient cannot be found with this search term; please try again.</p>
            </c:when>
            <c:otherwise>
                <table cellpadding="2" cellspacing="4" bgColor="white">
                    <tr class="rowheader">
                        <th>Patient</th>
                        <th>Mother</th>
                        <th>NRC Number</th>
                        <th>ZEPRS ID</th>
                        <th>Age</th>
                        <th>EDD</th>
                        <th>Dead?</th>
                        <logic:notPresent role="CREATE_NEW_PATIENTS_AND_SEARCH">
                        <th>SMC</th>
                        </logic:notPresent>
                        <th>Address</th>
                        <th>Health Worker</th>
                        <th>Last Site</th>
                        <th>Firm</th>
                        <th>Last Modified</th>
                    </tr>
                    <c:forEach var="row" items="${results.rows}" varStatus="i">

                        <%
                            //  Set up alternating row colors
                            classRow = classRow.equals("evenRow") ? "oddRow" : "evenRow";
                        %>
                        <tr class="<%= classRow %>">
                            <td><html:link action="/patientHome" paramId="patientId" paramName="row"
                                           paramProperty="id">${row.surname}, ${row.first_name}</html:link></td>
                            <td><html:link action="/patientHome" paramId="patientId" paramName="row"
                                           paramProperty="parent_id"><logic:present name="row" property="parent_surname">${row.parent_surname}
                                , ${row.parent_firstname}</logic:present></html:link></td>
                            <td><bean:write name="row" property="nrc_number"/></td>
                            <td><bean:write name="row" property="district_patient_id"/>
                            <c:if test="${search == row.alternate_id}">
                            <br/><span style="color: Red;">Alt Id: <bean:write name="row" property="alternate_id"/></span></c:if>
                            </td>
                            <td><bean:write name="row" property="age"/></td>
                            <td>
                                <fmt:parseDate type="date" pattern="yyyy-MM-dd" value="${row.edd}" var="eddDate"/>

                                <c:if test="${row.current_flow == 3}"><span
                                        class="valError">Active Labour started: <fmt:formatDate type="time" pattern="HH:mm" value="${row.firstStage}"/></span><br/>EDD: </c:if>
                                <c:if test="${row.current_flow == 4 && empty row.parent_surname}">Delivered</c:if>
                                <c:if test="${row.current_flow != 4 && empty row.parent_surname}"><fmt:formatDate
                                        type="both" pattern="dd/MM/yy" value="${eddDate}"/></c:if>
                            </td>
                            <td><c:if test="${row.dead == true}">Dead</c:if></td>
                            <logic:notPresent role="CREATE_NEW_PATIENTS_AND_SEARCH">
                                <td><c:if test="${row.hiv_positive == true}">R</c:if></td>
                            </logic:notPresent>
                            <td><bean:write name="row" property="address1"/>
                                <c:if test="${! empty row.address2}"><bean:write name="row" property="address2"/>
                                </c:if></td>
                            <td>${row.firstname} ${row.lastname}</td>
                            <td>${row.site_name}</td>
                            <td>${row.firm}</td>
                            <fmt:formatDate value="${row.last_modified}" pattern="dd MMM yy" var="last_modified"/>
                            <td><c:out value="${last_modified}"/></td>
                        </tr>
                    </c:forEach>
            </table>
            </c:otherwise>
            </c:choose>
            </div>
        <div id="search-results-list-pager">
        <table cellpadding="2" cellspacing="4" bgColor="white" width="100%">
                <tr>
                <c:choose>
                    <c:when test="${offset == 0}">
                        <td class="prevNext" width="80px">&nbsp;</td>
                    </c:when>
                    <c:otherwise>
                        <td class="prevNext" width="80px"><html:link
                                href="/zeprs/home.do;jsessionid=${pageContext.request.session.id}?offset=${offset-rowCount}&search_string=${searchString}&site=${site}&first_surname=${firstSurname}&labour=${labour}">
                            Previous</html:link></td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${next == 0}">
                        <td class="prevNext" colspan="10">&nbsp;</td><td class="prevNext">&nbsp;</td>
                    </c:when>
                    <c:when test="${offset == 0}">
                        <td class="prevNext" colspan="10">&nbsp;</td>
                        <td class="prevNext" width="80px"><html:link
                                href="/zeprs/home.do;jsessionid=${pageContext.request.session.id}?offset=${rowCount}&next=${next}&search_string=${searchString}&site=${site}&first_surname=${firstSurname}&labour=${labour}">
                            Next</html:link></td>
                    </c:when>
                    <c:otherwise>
                        <td class="prevNext" colspan="10">&nbsp;</td>
                        <td class="prevNext" width="80px"><html:link
                                href="/zeprs/home.do;jsessionid=${pageContext.request.session.id}?offset=${offset + rowCount}&next=${next}&search_string=${searchString}&site=${site}&first_surname=${firstSurname}&labour=${labour}">
                            Next</html:link></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </table>
        </div>
        </c:when>
        <c:otherwise>
        <p>No patients were found in the system based on your query. Please choose one of the following:
            <ul>
                <li><html:link action="search">Perform another search for a patient</html:link></li>
                <li><html:link action="form1/new.do">Create New Patient</html:link></li>
                </ul>
            </p>
        </c:otherwise>
    </c:choose>
    </div>
    </div>
</div>
</template:put>
</template:insert>