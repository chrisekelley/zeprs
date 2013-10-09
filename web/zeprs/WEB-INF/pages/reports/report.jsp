<%@ page import="java.util.List,
                 java.util.LinkedList,
                 java.util.ListIterator,
                 java.sql.ResultSetMetaData"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<template:insert template='/WEB-INF/templates/template.jsp'>

<%--<jsp:useBean id="resultset" scope="request" type="java.sql.ResultSet" />--%>
<jsp:useBean id="report_spec" scope="request" type="org.cidrz.webapp.dynasite.valueobject.ReportSpec" />
<template:put name='title' direct='true'>
ZEPRS: <bean:write name="report_spec" property="label"/>
</template:put>
<template:put name='header' direct='true'>
<h2><bean:write name="report_spec" property="label"/></h2>
</template:put>
<template:put name='content' direct='true'>
<div id="probSum">
 <%
//  Set up alternating row colors
String classRow = "oddRow";
%>

<sql:query var = "results" dataSource="jdbc/zeprsDB">
${report_spec.sqlQuery}
</sql:query>
<c:set var="colNames" value="${results.columnNames}"/>
<c:set var="majorCatColumn" value="${report_spec.primaryCategoryColumn}"/>
<c:set var="secondaryCategoryColumn" value="${report_spec.secondaryCategoryColumn}"/>
<c:set var="colLabels" value="${report_spec.colLabels}"/>

<table class="report" border="1" >
<%--<c:forEach var="row" items="${results.rows}" varStatus="lineInfo">
<%
        //  Set up alternating row colors
        classRow = classRow.equals("evenRow")? "oddRow" : "evenRow";
        %>
<tr>

<td><bean:write name="row" property="patient_id"/></td>
<td><bean:write name="row" property="encounter_id"/></td>
<td><bean:write name="row" property="page_item_id"/></td>
<td><bean:write name="row" property="value"/></td>
<td><bean:write name="row" property="field_id"/></td>
<td><bean:write name="row" property="visit_date"/></td>
<td><bean:write name="row" property="site_id"/></td>
</tr>
</c:forEach>--%>

 <%-- Get the column names for the header of the table --%>
<c:set var="colNumber"/>
<c:forEach var="columnName" items="${results.columnNames}" varStatus="i">
<th><c:out value="${columnName}"/></th>

<%--<c:forTokens items="${colLabels}" delims="," var="colLabel" varStatus="i" >
<th>${colLabel}</th>
<c:set var="colNumber" value="${i.count}"/>
</c:forTokens>--%>
<%-- Get the value of each column while iterating over rows --%>
<%--<c:forEach var="row" items="${results.rowsByIndex}">
<tr>
<c:forEach var="column" items="${row}">
<td>${column} = <c:out value="${column}"/></td>
</c:forEach>
</tr>--%>
</c:forEach>

<%-- Get the value of each column while iterating over rows --%>
<c:forEach var="row" items="${results.rows}">
<tr>
<c:choose>
<c:when test="${row.Site != currCol}">
<th colspan="${colNumber}">${majorCatColumn}: ${row.Site}</th></tr>
<tr>
</c:when>
</c:choose>
<c:set var="currCol" value="${row.Site}"/>
<c:forEach var="column" items="${row}">

<c:forTokens items="${colLabels}" delims="," var="colLabel" varStatus="i" >
<c:if test="${colLabel ==column.key}">
<td>${column.value}</td>
</c:if>
</c:forTokens>

</c:forEach>
<%--<c:forEach var="column" items="${row}">
<td>${column.value}</td>
</c:forEach>--%>
</tr>
</c:forEach>


<%-- <%
    ResultSetMetaData rsmd = resultset.getMetaData();
    int numCols = rsmd.getColumnCount()-1;
    String majorCatColumn = report_spec.getPrimaryCategoryColumn();
    String currentMajorCatColumnValue = "";
    boolean oddRow = true;
    //labels

    out.println("<tr class=\"tableLabelRow\">");

   // if (!report_spec.getColLabels().equals(null)) {
    String[] col = report_spec.getColLabels().split(",");

    int j = 0;

  / for ( int i=1; i<=numCols; i++)
    {
             if (!rsmd.getColumnName(i).equals(majorCatColumn))
             {
                out.println("<td class=\"tableLabel\">");
                if ((col.length > j) && (!col[j].trim().equals("")))
                {
                    out.println(col[j++].trim());
                }
                else
                {
                    try
                    {
                        out.println(rsmd.getColumnName(i));
                    }
                    catch (Exception ex)
                    {
                        break;
                    }
                }
                 out.println("</td>");
            }
    }
    out.println("</tr>");
            // end labels
    while ( resultset.next() )
    {
        if (oddRow)
        {
            out.println("<tr class=\"oddRow\">");
        }
        else
        {
            out.println("<tr class=\"evenRow\">");
        }
        oddRow = !oddRow;

        if ( !currentMajorCatColumnValue.equals(resultset.getString(majorCatColumn)) )
        {
            oddRow = true;
            //new cat
            currentMajorCatColumnValue = resultset.getString(majorCatColumn);
            out.println("<td colspan=\"" + numCols + "\" class=\"majorColumn\">" + currentMajorCatColumnValue + "</td>");
            out.println("</tr>\n<tr>");


        }
        for ( int i=1; i<=numCols; i++)
        {
            if ( !rsmd.getColumnName(i).equals(majorCatColumn) )
            {
                try
                {
                    out.println("<td>");
                    out.println(resultset.getString(i));
                    out.println("</td>");
                }
                catch (Exception ex)
                {
                    break;
                }
            }
        }
        out.println("</tr>\n");
    }
    resultset.close();
    resultset=null;
%>--%>
</table>
</div>
</template:put>
</template:insert>