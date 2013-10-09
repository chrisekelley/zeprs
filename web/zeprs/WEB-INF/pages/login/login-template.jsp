<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri='/WEB-INF/c.tld' prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%
   // String hostname = request.getServerName();
    String hostname = "192.168.20.6";
    pageContext.setAttribute("hostname",hostname);
%>
<html>
<head>
<title><template:get name='title'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META HTTP-EQUIV="Refresh" CONTENT="600">
<script type="text/javascript" src="/zeprs/js/browser_detect.js"></script>
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
<div id="banner-home">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td align="left" valign="middle" height="46px;"><span class="serviceHeader"><a href="/zeprs/home.do;jsessionid=${pageContext.request.session.id}" border="0">ZEPRS</a></span></td>
                <td align="right" height="46px;"><a href="http://${hostname}/referral" target="_top">referral</a> | <a href="http://${hostname}/mail" target="_top">webmail</a> | <a href="http://${hostname}/training" target="_top">training</a>&nbsp;&nbsp;</td>
            </tr>
        </table>
    </div>
       <div id="maincontent">
            <h2><template:get name='header' ignore="true"/></h2>
            <template:get name='content'/>
        </div>

</body>
</html>

