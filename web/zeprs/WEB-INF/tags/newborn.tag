<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri= "/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ attribute name="patientId" required="false" type="java.lang.String" %>
<%@ attribute name="user" required="false" type="java.lang.String" %>
<%@ attribute name="siteId" required="false" type="java.lang.String" %>
<%@ attribute name="pregnancyId" required="false" type="java.lang.String" %>
<%@ attribute name="seqChildren" required="false" type="java.lang.String" %>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate type="both" pattern="MMMM dd, yyyy HH:mm:ss" value="${now}" var="fulldate" />
<fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow" />
<fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow" />
<fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow" />
<c:set var="theDate" value="${now}"/>
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${now}" var="daynow" />
<fmt:formatDate type="both" pattern="dd/MM/yyyy" value="${theDate}" var="nicedateVisit" />
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${theDate}" var="dbdateVisit" />
<c:set var="lastTwoYears" value="${yearnow - 2}"/>
<c:set var="lastYear" value="${yearnow - 1}"/>
<c:set var="twoYears" value="${yearnow + 2}"/>
<c:set var="fieldId" value="99999"/>

<script type='text/javascript' src='/zeprs/dwr/interface/Newborn.js;jsessionid=${pageContext.request.session.id}'></script>
<script type='text/javascript' src='/zeprs/js/engine2.jsp;jsessionid=${pageContext.request.session.id}'></script>
<script type='text/javascript' src='/zeprs/dwr/util.js;jsessionid=${pageContext.request.session.id}'></script>
<script type="text/javascript" src="/zeprs/js/dwr-generic.js;jsessionid=${pageContext.request.session.id}"></script>
<input type="hidden" name="patientId" id="patientId" value="${patientId}"/>
<input type="hidden" name="user" id="user" value="${user}"/>
<input type="hidden" name="siteId" id="siteId" value="${siteId}"/>
<input type="hidden" name="pregnancyId" id="pregnancyId" value="${pregnancyId}"/>
<input id="sequence" type="hidden" name="sequence" size="1" maxlength="1" value="${seqChildren}" >
<table width="100%" id="tbl99999999">
    <tr>
        <td>Delivery Date:</td>
        <td>Time of Birth:</td>
        <td>Sex:</td>
        <td>Weight:</td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td>
        <a href="javascript://" onclick="showCalendar('${yearnow}','${monthnow}','${datenow}','dd/MM/yyyy','form${encounterForm.id}','newbornDateField',event, ${lastTwoYears},${twoYears});">
        <img alt="Select Date" border="0" src="/zeprs/images/calendar.gif" align="middle">&nbsp;&nbsp;</a><span id="spannewbornDateField" class="dateDisplay">${nicedateVisit}</span>
        <div id="slcalcodnewbornDateField" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
        <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","newbornDateField");</script></div>
        <input type="hidden" name="newbornDateField" id="newbornDateField" title="Select Date" value="${yearnow}-${monthnow}-${datenow}"/>
        </td>
        <td>
        <%--<input id="birthtime" type="text" name="birthtime" size="7" maxlength="8" title="Click to update Time">
        <a href="#" onclick="copyTime('birthtime', '${fulldate}');">(Current Time)</a>--%>

        <select id="hour${fieldId}" onchange="calcTime('${fieldId}')">
                <option value="">--</option>
                <c:forEach begin="0" end="23" step="1" var="hour">
                    <c:choose>
                        <c:when test="${hour < 10}">
                            <c:set var="hourValue" value="0${hour}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="hourValue" value="${hour}"/>
                        </c:otherwise>
                    </c:choose>
                    <option value="${hourValue}">${hourValue}</option>
                </c:forEach>
            </select>
            <select id="minute${fieldId}" onchange="calcTime('${fieldId}')">
                <option value="">--</option>
                <c:forEach begin="0" end="59" step="1" var="minute">
                    <c:choose>
                        <c:when test="${minute < 10}">
                            <c:set var="minuteValue" value="0${minute}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="minuteValue" value="${minute}"/>
                        </c:otherwise>
                    </c:choose>
                    <option value="${minuteValue}">${minuteValue}</option>
                </c:forEach>
            </select>

<%--
            <html:hidden property="field${fieldId}" styleId="field${fieldId}" />
--%>
            <input type="hidden" id="field${fieldId}"/>
            <a href="#" onclick="setTimeField('${fieldId}');">(Current Time)</a>
        </td>
        <td><input type="radio" name="sex" id="sex" value="1">Female</input><br/>
        <input type="radio" name="sex" id="sex" value="2">Male</input></td>
        <td><input type="text" id="weight" size="2" maxlength="4"/></td>
        <td valign="bottom"><input id="saveButton" class='ibutton' type='button' onclick='checkNewborn("patient","Please enter all of the fields in the Newborn Delivery section.","sequence");' value='Enter' title='Enter'/></td>
    </tr>
    <tr>
        <td colspan="5"><span id='newbornResults' class='reply'>${newbornList}</span></td>
    </tr>
</table>

<script type='text/javascript'>
    // Newborn.createResponse(fillForm, ${patientId}, ${pregnancyId});

    // This is not called - fillform is used instead.
    var reply1 = function(data)
    {
        document.getElementById('newbornResults').innerHTML = data;
    }
  </script>



