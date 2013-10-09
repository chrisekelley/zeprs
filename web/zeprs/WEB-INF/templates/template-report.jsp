<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%
    String pageURL = request.getRequestURL().toString();
    pageContext.setAttribute("pageURL", pageURL);

    String queryString = request.getQueryString();
    pageContext.setAttribute("queryString", queryString);

    String printTemplateURL = "";
    if (queryString != null) {
        printTemplateURL = pageURL + "?" + queryString + "&template=print";
    } else {
        printTemplateURL = pageURL + "?template=print";
    }

    pageContext.setAttribute("printTemplateURL", printTemplateURL);

    // String hostname = request.getServerName();
    String hostname = "192.168.20.6";
    pageContext.setAttribute("hostname", hostname);

%>
<html>
<head>
    <title><template:get name='title'/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <script type="text/javascript"
            src="/zeprs/js/browser_detect.js;jsessionid=${pageContext.request.session.id}"></script>
    <script language="JavaScript" TYPE="text/javascript" src="/zeprs/js/validation.js;jsessionid=${pageContext.request.session.id}"></script>
    <script type="text/javascript" src="/zeprs/js/zeprs.js;jsessionid=${pageContext.request.session.id}"></script>
    <script type="text/javascript">
        //<![CDATA[
        var output = '';
        if (browser.isGecko)
        {
            output += '<link rel="stylesheet" href="/zeprs/css/styles-moz.css" charset="ISO-8859-1" type="text/css">';
        }
        else
        {
            output += '<link rel="stylesheet" href="/zeprs/css/styles-ie.css" charset="ISO-8859-1" type="text/css">';
        }
        document.write(output);
        //]]>
    </script>
</head>

<body>
<h1><template:get name='header' ignore="true"/></h1>
<template:get name='content'/>
</body>
</html>