<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<template:insert template='simple-template.jsp'>
<template:put name='title' content='Please login.' direct='true'/>
<template:put name="header" content="Please login." direct="true"/>
<template:put name='content' direct='true'>

<table>
	<tr>
	   <td align="center" >
		   <table border="0">
			   <tr>
			   <td>There is a problem with your session. Please click <a href="home.do" style="text-decoration: underline; font-weight: bold;">here</a> to continue.</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</template:put>
</template:insert>

