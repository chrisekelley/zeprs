<%@ page import="org.cidrz.webapp.dynasite.session.SessionUtil,
                 org.cidrz.webapp.dynasite.valueobject.Patient"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:formatDate type="both" pattern="MMMM dd, yyyy HH:mm:ss" value="${now}" var="fulldate" />
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow" />
<fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow" />
<fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow" />
<div id="partoClose" style="display:none; border:none">
    <h2>Partograph: Delivery</h2>
    <jsp:include page="toc.jsp"/>


    <c:choose>
    <%--<c:when test="${(! empty partographStatus) && (! empty partographStatus.field1551)}">
    <span id="partographCell">
        <form>
        Re-open: <input type="button" value="Re-open" id="is_open" name="is_open" onclick="insertOpenPartograph(1551,'field1551',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" />
        <input type="hidden"  id="is_closed" value="on"/>
        </form>
    </span>
    <html:form action="form79/save.do" styleId="closeForm" style="display:none;">
            Date/Time Last Fetus Delivered: ${partographStatus.field1551} ${partographStatus.field1552}
            <input type="hidden" name="id" value="${partographStatus.id}"/>
            <input type="hidden" name="field1" value="${partographStatus.dateVisit}"/>
            <zeprs:date_visit_no_form dateVisit="${dateVisit}" element = "form79" field = "field1551"/>
            <input type="text" id="field1552" size="7" maxlength="8"  title="Click to update Time"/>
            <a href="#" onclick="copyTime('field1552', '${fulldate}');">(Current Time)</a>
            <input type="submit" value="Submit" onclick="bCancel=false;">
        </html:form>
    </c:when>--%>
    <c:when test="${(empty partographStatus) }">
        <html:form action="form79/save.do" styleId="closeForm">
        <input type="hidden" name="id" value="${partographStatus.id}"/>
        <c:choose>
        <c:when test="${!empty partographStatus.dateVisit}">
        <input type="hidden" name="field1" value="${partographStatus.dateVisit}"/>
        </c:when>
        <c:otherwise>
        <input type="hidden" name="field1" value="${yearnow}-${monthnow}-${datenow}"/>
        </c:otherwise>
        </c:choose>
        <p>Note:</strong> You will be unable to make new entries into the partograph once you make an entry for delivery.</p>
        <table>
            <tr>
                <td><p>Date/Time Last Fetus Delivered: ${partographStatus.field1551} ${partographStatus.field1552}</p></td>
                <td><zeprs:date_visit_no_form dateVisit="${dateVisit}" element = "form79" field = "field1551"/></td>
            </tr>

            <tr>
                <td><p>Time: </p></td>
                <td><input type="text" id="field1552" name="field1552" size="7" maxlength="8"  title="Click to update Time"/>
            <a href="#" onclick="copyTime('field1552', '${fulldate}');">(Current Time)</a></td>
            </tr>

            <tr>
                <td><p>Password: </p></td>
                <td><input type="password" name="password">
                    <input type="submit" value="Submit" onclick="bCancel=false;"></td>
            </tr>
        </table>
    </html:form>
    </c:when>
    <c:when test="${(! empty partographStatus) && (empty partographStatus.field1551)}">
        <html:form action="form79/save.do" styleId="closeForm">
        <input type="hidden" name="id" value="${partographStatus.id}"/>
        <input type="hidden" name="field1" value="${partographStatus.dateVisit}"/>
        <p>Note:</strong> You will be unable to make new entries into the partograph once you make an entry for delivery.</p>
        <table>
            <tr>
                <td><p>Date/Time Last Fetus Delivered: ${partographStatus.field1551} ${partographStatus.field1552}</p></td>
                <td><zeprs:date_visit_no_form dateVisit="${dateVisit}" element = "form79" field = "field1551"/></td>
            </tr>

            <tr>
                <td><p>Time: </p></td>
                <td><input type="text" id="field1552" name="field1552" size="7" maxlength="8"  title="Click to update Time"/>
            <a href="#" onclick="copyTime('field1552', '${fulldate}');">(Current Time)</a></td>
            </tr>

            <tr>
                <td><p>Password: </p></td>
                <td><input type="password" name="password">
                    <input type="submit" value="Submit" onclick="bCancel=false;"></td>
            </tr>
        </table>

    </html:form>
    </c:when>
    <%--<c:otherwise>
    <input type="hidden" id="is_closed" value="false"/>
    <html:form action="updatePartographStatus.do" styleId="closeForm">
    <p>Note:</strong> You will be unable to make new entries into the partograph once you  make an entry for delivery.</p>
        <p>Date/Time Last Fetus Delivered: ${partographStatus.field1551} ${partographStatus.field1552}</p>
        <input type="hidden" name="id" value="${partographStatus.id}"/>
        <input type="hidden" name="field1" value="${partographStatus.dateVisit}"/>
        <zeprs:date_visit_no_form dateVisit="${dateVisit}" element = "form79" field = "field1551"/> <br/>
        Time: <input type="text" id="field1552" size="7" maxlength="8"  title="Click to update Time"/>
        <a href="#" onclick="copyTime('field1552', '${fulldate}');">(Current Time)</a><br/>
        Password: <input type="password" name="password">
        <input type="submit" value="Submit" onclick="bCancel=false;">
    </html:form>
    </c:otherwise>--%>
    </c:choose>
    <span id="partographMessageCell" class="error">&nbsp;
    <c:if test="${! empty partographStatus}">
        <c:if test="${! empty partographStatus.field1551}">
        Delivery Completed.
        </c:if>
    </c:if>
    </span>
</div>


<script type='text/javascript'>
    var replyClosePartograph = function(data)
    {
    var dvals = data.split("=");
    var key = "partographMessageCell";
    var value =dvals[0];
    var closePartographValue = document.getElementById(key);
    closePartographValue.innerHTML = value;
    var reopenElement = document.getElementById("partographCell");
    reopenElement.innerHTML = 'Re-open: <input type="checkbox" id="is_open" name="is_open" onchange="insertOpenPartograph(1551,\'field1551\',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},\'${user}\',${siteId})"><input type="hidden"  id="is_closed" value="on"/>';
    }

    var replyOpenPartograph = function(data)
    {
    var dvals = data.split("=");
    var key = "partographMessageCell";
    var value =dvals[0];
    var closePartographValue = document.getElementById(key);
    closePartographValue.innerHTML = value;
    var reopenElement = document.getElementById("partographCell");
    var closeFormElement = document.getElementById("closeForm");
    closeFormElement.style.display = "";
    reopenElement.innerHTML = '<strong>Close:</strong>';
    }
  </script>






