<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ attribute name="dateVisit" required="false" type="java.sql.Date" %>
<c:set var="field" value="${pageItem.form_field}" />

<jsp:useBean id="now" class="java.util.Date" />
<c:choose>
    <c:when test="${! empty dateVisit}">
        <fmt:formatDate type="both" pattern="yyyy" value="${dateVisit}" var="yearnow" />
        <fmt:formatDate type="both" pattern="M" value="${dateVisit}" var="monthnow" />
        <fmt:formatDate type="both" pattern="dd" value="${dateVisit}" var="datenow" />
    </c:when>
    <c:otherwise>
        <fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow" />
        <fmt:formatDate type="both" pattern="M" value="${now}" var="monthnow" />
        <fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow" />
    </c:otherwise>
</c:choose>

<fmt:formatDate type="both" pattern="dd/MM/yyyy" value="${dateVisit}" var="nicedateVisit" />
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${dateVisit}" var="dbdateVisit" />
<c:set var="twothousand" value="2000"/>
<c:set var="lastTwoYears" value="${yearnow - 2}"/>
<c:set var="lastYear" value="${yearnow - 1}"/>
<c:set var="twoYears" value="${yearnow + 2}"/>
<c:set var="sixtyYears" value="${yearnow - 60}"/>

<tr>
    <td width="15%" valign="middle" align="right">Date of Dispensation: </td>
    <td valign="top" colspan="3">
    <span class="asterix">*</span>
    <a href="javascript://" onclick="showCalendar('${yearnow}','${monthnow}','${datenow}','dd/MM/yyyy','form${encounterForm.id}','field1',event,${twothousand},${twoYears});">
    <img alt="Select Date" border="0" src="/zeprs/images/calendar.gif" align="middle">&nbsp;&nbsp;</a><span id="spanfield1" class="dateDisplay" >${nicedateVisit}</span>
    <div id="slcalcodfield1" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
    <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","field1");</script></div>
    <img alt="spacer" src="/zeprs/images/clearpixel.gif">
    <html:hidden styleClass="disabled-date" onfocus="this.blur()" property="field1" value="${dbdateVisit}"/> </td>
