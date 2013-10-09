<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ attribute name="dateVisit" required="false" type="java.sql.Date" %>
<%@ attribute name="element" required="false" type="java.lang.String" %>
<%@ attribute name="field" required="false" type="java.lang.String" %>
<jsp:useBean id="now" class="java.util.Date" />
<c:choose>
    <c:when test="${! empty dateVisit}">
        <fmt:formatDate type="both" pattern="yyyy" value="${dateVisit}" var="yearnow" />
        <fmt:formatDate type="both" pattern="MM" value="${dateVisit}" var="monthnow" />
        <fmt:formatDate type="both" pattern="dd" value="${dateVisit}" var="datenow" />
        <c:set var="theDate" value="${dateVisit}"/>
    </c:when>
    <c:otherwise>
        <fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow" />
        <fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow" />
        <fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow" />
        <c:set var="theDate" value="${now}"/>
    </c:otherwise>
</c:choose>
<fmt:formatDate type="both" pattern="dd/MM/yyyy" value="${theDate}" var="nicedateVisit" />
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${theDate}" var="dbdateVisit" />
<c:set var="lastTwoYears" value="${yearnow - 2}"/>
<c:set var="lastYear" value="${yearnow - 1}"/>
<c:set var="twoYears" value="${yearnow + 2}"/>
<c:set var="sixtyYears" value="${yearnow - 60}"/>
<strong>Date:</strong> <span class="asterix">*</span>
    <a href="javascript://" onclick="showCalendar('${yearnow}','${monthnow}','${datenow}','dd/MM/yyyy','${element}','${field}',event,${lastTwoYears},${twoYears});">
    <img alt="Select Date" border="0" src="/zeprs/images/calendar.gif" align="middle"></a>&nbsp;&nbsp;<span id="span${field}" class="dateDisplay">${nicedateVisit}</span>
    <div id="slcalcod${field}" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
    <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","${field}");</script></div>
    <img alt="spacer" src="/zeprs/images/clearpixel.gif">
    <html:hidden styleId="${field}"  styleClass="disabled-date" onfocus="this.blur()" property="${field}" value="${yearnow}-${monthnow}-${datenow}"/>

