<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<template:insert template='login-template.jsp'>
<template:put name='title' content='Login' direct='true'/>
<template:put name='header' content='Login Failed; try again' direct='true'/>
<template:put name='content' direct='true' >

<br><br><form action="j_security_check;jsessionid=${pageContext.request.session.id}" method=post>
<table>
<tr>
   <td align="center" >
   <table border="0">
   <tr>
   <td><b>Enter your name: </b></td>
   <td>
      <input type="text" size="15" name="j_username">
   </td>
   </tr>
   <tr>
   <td><b>Enter your password: </b></td>
   <td>
      <input type="password" size="15" name="j_password">
   </td>
   </tr>
   <tr>
   <td></td>
   <td align="right">
   <input type="submit" value="Submit">
   </td>
   </tr>
   <tr>
   <td><br></td>
   </tr>
</table>
</td>
</tr>
</table>
</form>
</template:put>
</template:insert>
