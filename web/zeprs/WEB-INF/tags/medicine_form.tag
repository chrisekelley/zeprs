<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ attribute name="form" required="true" type="org.cidrz.webapp.dynasite.valueobject.Form" %>

<jsp:useBean id="now" class="java.util.Date" />


<p>Items marked with an asterix (<span class="asterix">*</span>) are required.

<%--<br>PC Time: <input type="hidden" id="pcTime" size="7"class="disabled"  onfocus="this.blur()" />--%></p>
<table cellpadding="2" cellspacing="0" border="0">
<tr>
    <td valign="top">Date of Visit: </td>
    <td colspan="3" align="left">
    <a href="javascript://" onclick="showCalendar('${yearnow}','${monthnow}','${datenow}','dd/MM/yyyy','form${encounterForm.id}','field1',event,${lastTwoYears},${twoYears});">
    <img alt="Select Date" border="0" src="/zeprs/images/calendar.gif" align="middle">&nbsp;&nbsp;</a><span id="spanfield1" class="dateDisplay">${nicedateVisit}</span>
    <div id="slcalcodfield1" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
    <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","field1");</script></div>
    <img alt="spacer" src="/zeprs/images/clearpixel.gif">
    <html:hidden styleClass="disabled-date" onfocus="this.blur()" property="field1" value="${yearnow}-${monthnow}-${datenow}"/></td>
</tr>

<jsp:include page="../pages/forms/current_medicine_fields.jsp"/>

<c:choose>
<c:when test='${form.requireReauth}'>
<tr><td colspan="${thisColspan}">&nbsp;</td></tr>
<tr><td colspan="${thisColspan}"><strong>Re-Authorization</strong></td></tr>
<tr><td>Password:</td><td colspan="2"><input type="password" name="password"></td><td><html:submit onclick="bCancel=false;" /></td></tr>
</c:when>
<c:otherwise>
<tr><td colspan="${thisColspan}">&nbsp;</td><td align="right"><html:submit onclick="bCancel=false;" /></td><td>&nbsp;</td></tr>
</c:otherwise>
</c:choose>
<tr><td colspan="${thisColspan}">&nbsp;</td></tr>
<tr><td colspan="${thisColspan}">&nbsp;</td></tr>
</table>
<html:javascript formName="form${form.id}"/>



