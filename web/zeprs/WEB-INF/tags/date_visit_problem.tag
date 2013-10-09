<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ attribute name="onsetDateValue" required="false" type="java.sql.Date" %>
<jsp:useBean id="now" class="java.util.Date" />
<c:choose>
    <c:when test="${! empty onsetDateValue}">
       <fmt:formatDate type="both" pattern="yyyy" value="${onsetDateValue}" var="yearnow" />
        <fmt:formatDate type="both" pattern="MM" value="${onsetDateValue}" var="monthnow" />
        <fmt:formatDate type="both" pattern="dd" value="${onsetDateValue}" var="datenow" />
        <c:set var="theDate" value="${onsetDateValue}"/>
    </c:when>
    <c:otherwise>
        <fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow" />
        <fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow" />
        <fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow" />
        <c:set var="theDate" value="${now}"/>
    </c:otherwise>
</c:choose>
<fmt:formatDate type="both" pattern="dd/MM/yyyy" value="${theDate}" var="niceonsetDate" />
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${theDate}" var="dbonsetDate" />
<c:set var="twothousand" value="2000"/>
<c:set var="lastTwoYears" value="${yearnow - 2}"/>
<c:set var="lastYear" value="${yearnow - 1}"/>
<c:set var="twoYears" value="${yearnow + 2}"/>
<c:set var="sixtyYears" value="${yearnow - 60}"/>
    <a href="javascript://" onclick="showCalendar('${yearnow}','${monthnow}','${datenow}','dd/MM/yyyy','problem','onsetDate',event,${twothousand},${twoYears});">
    <img alt="Select Date" border="0" src="/zeprs/images/calendar.gif" align="middle">&nbsp;&nbsp;</a><span id="spanonsetDate" class="dateDisplay">${niceonsetDate}</span>
    <div id="slcalcodonsetDate" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
    <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","onsetDate");</script></div>
    <img alt="spacer" src="/zeprs/images/clearpixel.gif">
    <html:hidden styleClass="disabled-date" onfocus="this.blur()" property="onsetDate" value="${yearnow}-${monthnow}-${datenow}"/>

