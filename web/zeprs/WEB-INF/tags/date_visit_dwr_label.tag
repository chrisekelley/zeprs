<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ attribute name="pageItem" required="false" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<%@ attribute name="pos" required="false" type="java.lang.Integer" %>
<%@ attribute name="remoteClass" required="true" type="java.lang.String" %>
<%@ attribute name="classname" required="true" type="java.lang.String" %>
<%@ attribute name="propertyName" required="true" type="java.lang.String" %>
<%@ attribute name="patientId" required="true" type="java.lang.Integer" %>
<%@ attribute name="pregnancyId" required="true" type="java.lang.Integer" %>
<%@ attribute name="user" required="true" type="java.lang.String" %>
<%@ attribute name="siteId" required="true" type="java.lang.Integer" %>
<%@ attribute name="value" required="false" type="java.sql.Date" %>
<%@ attribute name="formId" required="true" type="java.lang.Integer" %>
<c:set var="scriptName" value="replyfield1"/>
<jsp:useBean id="now" class="java.util.Date" />
<c:choose>
    <c:when test="${! empty value}">
       <fmt:formatDate type="both" pattern="yyyy" value="${value}" var="yearnow" />
        <fmt:formatDate type="both" pattern="MM" value="${value}" var="monthnow" />
        <fmt:formatDate type="both" pattern="dd" value="${value}" var="datenow" />
        <c:set var="theDate" value="${value}"/>
    </c:when>
    <c:otherwise>
        <fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow" />
        <fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow" />
        <fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow" />
        <c:set var="theDate" value="${now}"/>
    </c:otherwise>
</c:choose>
<fmt:formatDate type="both" pattern="dd/MM/yyyy" value="${theDate}" var="niceDate" />
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${theDate}" var="dbDate" />
<c:set var="lastTwoYears" value="${yearnow - 2}"/>
<c:set var="lastYear" value="${yearnow - 1}"/>
<c:set var="twoYears" value="${yearnow + 2}"/>
<c:set var="sixtyYears" value="${yearnow - 60}"/>

<%--
    <html:hidden styleId="dateVisit${pos}" styleClass="disabled-date" onfocus="this.blur()" property="dateVisit${pos}" value="${yearnow}-${monthnow}-${datenow}"/>
--%>

<c:choose>
<c:when test="${! empty value}">
    <%--<span id="field1Results${pos}" style="display:none;">${niceDate}</span>--%>
    <span id="spandateVisit${pos}" onclick="justReveal('correctDateVisit${pos}'); showCalendar('${yearnow}','${monthnow}','${datenow}','dd/MM/yyyy','form${pos}','dateVisit${pos}',event,${lastTwoYears},${twoYears});">${niceDate}</span>
    <div id="slcalcoddateVisit${pos}" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
    <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","dateVisit${pos}");</script></div>
    <span id="correctDateVisit${pos}" style="display:none;">
        <input type="hidden" id="dateVisit${pos}" class="disabled-date"  onfocus="this.blur()" name="dateVisit${pos}" value="${dbDate}" />
        <input type="button" name="_add" value="Change" onclick="insertInputField('${remoteClass}', ${scriptName}, '${classname}','dateVisit', ${pos},'dateVisit${pos}',${patientId}, ${pregnancyId},'${user}',${siteId})">
    </span>
</c:when>
<c:otherwise>
    <%--<span id="field1Results${pos}" style="display:none;"></span>--%>
    <span id="spandateVisit${pos}" onclick="justReveal('correctDateVisit${pos}'); showCalendar('${yearnow}','${monthnow}','${datenow}','dd/MM/yyyy','form${pos}','dateVisit${pos}',event,${lastTwoYears},${twoYears});">${niceDate}</span>
    <div id="slcalcoddateVisit${pos}" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
    <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","dateVisit${pos}");</script></div>
    <span id="correctDateVisit${pos}" style="display:none;">
        <input type="text" id="dateVisit${pos}" class="disabled-date"  onfocus="this.blur()" name="dateVisit${pos}" value="${dbDate}" />
        <input type="button" name="_add" value="Done" onclick="insertInputField('${remoteClass}', ${scriptName}, '${classname}','dateVisit', ${pos},'dateVisit${pos}',${patientId}, ${pregnancyId},'${user}',${siteId})">
    </span>
</c:otherwise>
</c:choose>
    <script type='text/javascript'>
        var ${scriptName} = function(data)
        {
        var dvals = data.split("=");
        var key = "spandateVisit" + dvals[1];
        var value =dvals[0];
        var itemValue = document.getElementById(key);
        itemValue.innerHTML = value;
        var input =  document.getElementById("correctDateVisit" + dvals[1]);
        input.style.display = "none";
        input.style.visibility = "hidden";
        }
    </script>
