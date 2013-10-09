<%@ page import="org.apache.commons.dbcp.SQLNestedException"%>
<%@ page import="java.sql.SQLException"%>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<template:insert template='/WEB-INF/templates/template-error.jsp'>
<template:put name='title' content='Error' direct='true'/>
<template:put name='header' content='Error' direct='true'/>
<template:put name='content' direct='true'>
<div id="changelog">
<p>
<%
    try
    {
        Exception ex = (Exception) request.getAttribute("exception");
        if (request.getAttribute("message") !=null) {
            out.print("<p>" + request.getAttribute("message") + "</p>");
        }
        if (ex.getMessage() !=null) {
            out.print("<p>Message: " + ex.getMessage() + "</p>");
        } else {
          //  out.print("<p>No Message provided.</p>");
        }
        out.print("<p>" + ex.getClass().toString() + "</p>");

        if (ex.getCause() !=null) {
        out.print("<p>Cause: " + ex.getCause() + "</p>");
            } else {
        //out.print("<p>No cause provided.</p>");
        }


        if (ex.getClass().toString().equals("class javax.servlet.ServletException")) {
            ServletException e = (ServletException) ex;
            if (e.getRootCause().getClass().toString().equals("class org.apache.commons.dbcp.SQLNestedException")) {
                SQLNestedException sqlE = (SQLNestedException) e.getRootCause();
                out.print("<p>SQLState: " + sqlE.getSQLState() + "</p>");
            }
            out.print("<p>Class: " + e.getClass() + "</p>");
            out.print("<p>RootCause Cause: " + e.getRootCause() + "</p>");
            out.print("<p>Stacktrace:</p>");
            out.print("<ul>");
            StackTraceElement[] est = e.getStackTrace();
            for (int i = 0; i < est.length; i++) {
                StackTraceElement este = est[i];
                out.println("<li> in class:" + este.getClassName()
                        + "<br> in source file: " + este.getFileName()
                        + "<br> in method: " + este.getMethodName()
                        + "<br> at line: " + este.getLineNumber()
                        + " " + (este.isNativeMethod() ? "native" : ""));
            }
            out.print("</ul>");
        } else if (ex.getClass().toString().equals("class java.sql.SQLException")) {
            SQLException e = (SQLException) ex;
            if (e.getSQLState() != null) {
                // SQLNestedException sqlE = (SQLNestedException) e.getRootCause();
                out.print("<p>SQLState: " + e.getSQLState() + "</p>");
            }
            out.print("<p>Class: " + e.getClass() + "</p>");
            // out.print("<p>RootCause Cause: " + e.getRootCause() + "</p>");
            out.print("<p>Stacktrace:</p>");
            out.print("<ul>");
            StackTraceElement[] est = e.getStackTrace();
            for (int i = 0; i < est.length; i++) {
                StackTraceElement este = est[i];
                out.println("<li> in class:" + este.getClassName()
                        + "<br> in source file: " + este.getFileName()
                        + "<br> in method: " + este.getMethodName()
                        + "<br> at line: " + este.getLineNumber()
                        + " " + (este.isNativeMethod() ? "native" : ""));
            }
            out.print("</ul>");
        }


        if (ex.getCause().getCause() !=null) {
        out.print("<p>Nested Cause: " + ex.getCause().getCause() + "</p>");
            }
        out.print("<p>Stacktrace:</p>");
        out.print("<ul>");
        StackTraceElement[] es = ex.getStackTrace();
        for ( int i=0; i<es.length; i++ )
           {
           StackTraceElement e = es[i];
           out.println( "<li> in class:" + e.getClassName()
                                + "<br> in source file: " + e.getFileName()
                                + "<br> in method: " + e.getMethodName()
                                + "<br> at line: " + e.getLineNumber()
                                + " " + ( e.isNativeMethod() ? "native" : "" ) );
           }
        out.print("</ul>");

    }
    catch (Exception ex)
    {

        out.print("<p>" + request.getAttribute("exception") + "<p>");
    }
%>
</p>
</div>
</template:put>
</template:insert>