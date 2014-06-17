<%@ page import="org.rti.zcore.utils.DateUtils"%>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <title>${fullName} - Site: ${siteName} - <template:get name='title'/></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <script type="text/javascript" src="/${appName}/js/browser_detect.js;jsessionid=${pageContext.request.session.id}"></script>
        <script type="text/javascript" src="/${appName}/js/validation.js;jsessionid=${pageContext.request.session.id}"></script>
        <script type="text/javascript" src="/${appName}/js/zeprs.js;jsessionid=${pageContext.request.session.id}"></script>
	    <script type="text/javascript" src="/${appName}/js/util.js;jsessionid=${pageContext.request.session.id}"></script>
        <script type="text/javascript" src="/${appName}/js/calendar/javascript.js;jsessionid=${pageContext.request.session.id}"></script>
        <script type="text/javascript" src="/${appName}/js/dwr-admin.js;jsessionid=${pageContext.request.session.id}"></script>
        <script type='text/javascript' src='/${appName}/dwr/util.js;jsessionid=${pageContext.request.session.id}'></script>
        <script type='text/javascript' src='/${appName}/js/engine2.jsp;jsessionid=${pageContext.request.session.id}'></script>
        <script type="text/javascript" src="/${appName}/js/fat.js;jsessionid=${pageContext.request.session.id}"></script>
        <script type="text/javascript" src="/${appName}/js/scriptaculous/prototype.js;jsessionid=${pageContext.request.session.id}"></script>
        <script type="text/javascript">
            //<![CDATA[
            var output = '';
            if (browser.isGecko)
            {
            output += '<link rel="stylesheet" href="/${appName}/css/${appTemplateDir}/styles-moz.css;jsessionid=${pageContext.request.session.id}" charset="ISO-8859-1" type="text/css">';
            }
            else
            {
            output += '<link rel="stylesheet" href="/${appName}/css/${appTemplateDir}/styles-ie.css;jsessionid=${pageContext.request.session.id}" charset="ISO-8859-1" type="text/css">';
            }
            document.write(output);
            //]]>
         </script>
    </head>
    <body onload="DWRUtil.useLoadingMessage();">
		<div id="pagewidth">
	    <div id="banner-home">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		            <td align="center" valign="middle" background="/${appName}/images/banner_bkgd.gif" height="46" width="100" ><span class="serviceHeader"><a href="/${appName}/home.do;jsessionid=${pageContext.request.session.id}" border="0">${appTitle}</a></span></td>
		            <td background="/${appName}/images/banner_bkgd.gif" align="right">&nbsp;</td>
		        </tr>
		    </table>
	    </div>
	    <%@ include file="admin_status.jsp" %>
		<div id="twocols" class="clearfix">
		<template:get name='content' ignore="true"/>
		</div>
	    <%-- <%@ include file="sidenav-dynamic.jsp" %> --%>
		</div><!-- pagewidth -->
    </body>
</html>