<%@ page import="org.cidrz.webapp.dynasite.session.SessionUtil"%>

<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<jsp:useBean id="now" class="java.util.Date" />
<%
    // String hostname = request.getServerName();
    String hostname = "192.168.20.6";
    pageContext.setAttribute("hostname",hostname);
%>
<html>
    <head>
        <title>${fullName}&nbsp; - Site: ${siteName}&nbsp;<template:get name='title'/></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <script type="text/javascript" src="/zeprs/js/browser_detect.js;jsessionid=${pageContext.request.session.id}"></script>
        <script language="JavaScript" TYPE="text/javascript" src="/zeprs/js/validation.js;jsessionid=${pageContext.request.session.id}"></script>
        <script type="text/javascript" src="/zeprs/js/zeprs.js;jsessionid=${pageContext.request.session.id}"></script>
        <script language="JavaScript1.2" TYPE="text/javascript" src="/zeprs/js/menu_code.js;jsessionid=${pageContext.request.session.id}"></script>
	    <script language="JavaScript" TYPE="text/javascript" src="/zeprs/js/util.js;jsessionid=${pageContext.request.session.id}"></script>
	    <script language="JavaScript" TYPE="text/javascript" src="/zeprs/js/menu_settings.js;jsessionid=${pageContext.request.session.id}"></script>
        <script language="JavaScript" TYPE="text/javascript" src="/zeprs/config/javascript.js;jsessionid=${pageContext.request.session.id}"></script>
        <script type="text/javascript" src="/zeprs/js/dwr-admin.js;jsessionid=${pageContext.request.session.id}"></script>
        <script type='text/javascript' src='/zeprs/dwr/util.js;jsessionid=${pageContext.request.session.id}'></script>
        <script type='text/javascript' src='/zeprs/js/engine2.jsp;jsessionid=${pageContext.request.session.id}'></script>
        <script type="text/javascript" src="/zeprs/js/fat.js;jsessionid=${pageContext.request.session.id}"></script>

        <script type="text/javascript">
            //<![CDATA[
            var output = '';
            if (browser.isGecko)
            {
            output += '<link rel="stylesheet" href="/zeprs/css/styles-moz.css;jsessionid=${pageContext.request.session.id}" charset="ISO-8859-1" type="text/css">';
            }
            else
            {
            output += '<link rel="stylesheet" href="/zeprs/css/styles-ie.css;jsessionid=${pageContext.request.session.id}" charset="ISO-8859-1" type="text/css">';
            }
            document.write(output);
            //]]>
         </script>
    </head>
    <body onload="hide_toggles();DWRUtil.useLoadingMessage();">
    <%--
        <form action="${pageContext.request.contextPath}/search.do;jsessionid=${pageContext.request.session.id}" method="post"><input type="text" name="search_string">
    </form>
    --%>
    <div id="banner-home">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td valign="left" background="/zeprs/images/banner_bkgd.gif" height="46" width="100" ><span class="serviceHeader"><a href="/zeprs/home.do;jsessionid=${pageContext.request.session.id}" border="0">ZEPRS</a></span></td>
                <td background="/zeprs/images/banner_bkgd.gif" class="bannerLabel">&nbsp;</td>
                <td background="/zeprs/images/banner_bkgd.gif" align="right">
                <logic:present role="ALTER_PROGRAMS_AND_SCREEN_APPEARANCE">
                <html:link action="/admin/home">admin</html:link> &nbsp;&nbsp;&nbsp;
                </logic:present>
            </tr>
        </table>
    </div>
            <div id="content-admin">

            <h2><template:get name='header' ignore="true"/></h2>
            <template:get name='content'/>
            </div>
            <div id="adminHelp">
            <template:get name='help'/>
            </div>
    </body>
</html>

