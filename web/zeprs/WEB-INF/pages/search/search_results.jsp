<%@ page import="java.util.List,
                 org.cidrz.webapp.dynasite.valueobject.Patient,
                 org.cidrz.webapp.dynasite.session.SessionUtil"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<template:insert template='/WEB-INF/templates/template.jsp'>
<template:put name='title' content='Search Results' direct='true'/>
    <h2>Patient Search Results:</h2>
<template:put name='content' direct='true'>

<sql:query var = "results" dataSource="jdbc/zeprsDB">
select p.id, p.first_name,p.surname,p.nrc_number,p.district_patient_id, p.last_modified_by, u.firstname, u.lastname
from zeprs.patient p, userdata.address u
where p.last_modified_by = u.nickname
and (p.surname like '%${searchString}%'
or p.first_name like '%${searchString}%'
or p.nrc_number like '%${searchString}%'
or p.district_patient_id like '%${searchString}%'
or p.last_modified_by like '%${searchString}%')
</sql:query>
<div id="history">
    <%--<jsp:useBean id="patientList" scope="request" type="java.util.List" />--%>
    <c:choose>
        <c:when test="${!empty results}">
            <%
            //  Set up alternating row colors
            String classRow = "evenRow";
            %>
            
            <p>Select the patient based on the details that follow. </p>

            <table cellpadding="2" cellspacing="4" bgColor = "white">
                 <tr class = "rowheader">
                    <th>Patient</th>
                    <th>NRC Number</th>
                    <th>District Patient ID</th>
                   <%-- <th>Address</th>--%>
                    <th>Health Worker</th>
                    <%--<th>Last Modified</th>--%>
                </tr>
                <c:forEach var="row" items="${results.rows}">

                <%
                //  Set up alternating row colors
                classRow = classRow.equals("evenRow")? "oddRow" : "evenRow";
                %>
                <tr class = "<%= classRow %>" >
                    <td><html:link action="/patientHome" paramId="patientId" paramName="row" paramProperty="id" >${row.surname}, ${row.first_name}</html:link></td>
                    <td><bean:write name="row" property="nrc_number"/></td>
                    <td><bean:write name="row" property="district_patient_id"/></td>
                    <%--<td><bean:write name="row" property="address"/></td>--%>
                    <td>${row.firstname} ${row.lastname}</td>
                    <%--<td><fmt:formatDate value="${patient.auditInfo.lastModified}" /></td>--%>
                </tr>
                </c:forEach>
            </table>
           <div id="forms"><p>If you are unable to find the correct patient, please choose one of the following:
            <ul>
                <li><html:link action="search">Perform another search for a patient</html:link></li>
                <li><html:link action="form1/new.do">Create New Patient</html:link></li>
                </ul>
            </p>
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
</template:put>
</template:insert>