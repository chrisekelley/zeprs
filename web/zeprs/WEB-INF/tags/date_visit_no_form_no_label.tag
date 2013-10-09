<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ attribute name="dateVisit" required="false" type="java.sql.Date" %>
<%@ attribute name="element" required="false" type="java.lang.String" %>
<%@ attribute name="field" required="false" type="java.lang.String" %>

<c:choose>
    <c:when test="${fn:contains(field,'|')}">
        <c:set var="fieldName" value='${fn:substringBefore(field, "|")}'/>
        <c:set var="classId" value='${fn:substringAfter(field, "|")}'/>
    </c:when>
    <c:otherwise>
        <c:set var="fieldName" value="${field}"/>
        <c:set var="classId" value="${field}"/>
    </c:otherwise>
</c:choose>

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
<c:if test="${empty dateVisit}">
    <c:set var="nicedateVisit" value=""/>
</c:if>
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${theDate}" var="dbdateVisit" />
<c:set var="lastThreeYears" value="${yearnow - 3}"/>
<c:set var="lastTwoYears" value="${yearnow - 2}"/>
<c:set var="lastYear" value="${yearnow - 1}"/>
<c:set var="twoYears" value="${yearnow + 2}"/>
<c:set var="sixtyYears" value="${yearnow - 60}"/>
    <a href="javascript://" onclick="showCalendar('${yearnow}','${monthnow}','${datenow}','dd/MM/yyyy','${element}','${classId}',event,${lastThreeYears},${twoYears});">
    <img alt="Select Date" border="0" src="/zeprs/images/calendar.gif" align="middle"></a>&nbsp;&nbsp;<span id="span${classId}" class="dateDisplay">${nicedateVisit}</span>
    <div id="slcalcod${classId}" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
    <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","${classId}");</script></div>
    <img alt="spacer" src="/zeprs/images/clearpixel.gif">
<c:choose>
    <c:when test="${! empty dateVisit}">
         <html:hidden styleId="${classId}" property="${fieldName}" value="${yearnow}-${monthnow}-${datenow}"/>
    </c:when>
    <c:otherwise>
         <html:hidden styleId="${classId}" property="${fieldName}"/>
    </c:otherwise>
</c:choose>

