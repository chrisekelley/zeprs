<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tr id="temperatureRow0">
<td class="partoLabel"><div>Temp.</div></td>
<td class="partoLegend"><div>&#176;C</div></td>
<c:choose>
    <c:when test="${! empty temperature}">
    <c:forEach var="item" varStatus="loop"  begin="${startRowHour}" end="${stopRowHour}">
        <c:set var="pos" value="${loop.index}"/>
            <td colspan="2" id="temperatureCell.${pos}" onclick="justReveal('temperatureField${pos}')" valign="top" width="14">
            <logic:notEmpty name="temperature" property="temperature${pos}">
                <div id="temperatureValue${pos}"><bean:write name="temperature" property="temperature${pos}"/></div>
                <div id="property${pos}"><input type="text" name="temperature${pos}" id="temperatureField${pos}" onchange="insertTemperature(${pos},'temperatureField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;" size="3" maxlength="4"></div>
            </logic:notEmpty>
            <logic:empty name="temperature" property="temperature${pos}">
                <div id="temperatureValue${pos}"></div>
                <div id="property${pos}"><input type="text" name="temperature${pos}" id="temperatureField${pos}" onchange="insertTemperature(${pos},'temperatureField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;" size="3" maxlength="4"></div>
            </logic:empty>
            </td>
    </c:forEach>
    </c:when>
    <c:otherwise>
    <c:forEach var="item" varStatus="loop"  begin="${startRowHour}" end="${stopRowHour}" >
    <c:set var="pos" value="${loop.index}"/>
            <td colspan="2" id="temperatureCell.${pos}" onclick="justReveal('temperatureField${pos}')" valign="top"  width="14">
                <div id="temperatureValue${pos}"></div>
                <div id="property${pos}"><input type="text" name="temperature${pos}" id="temperatureField${pos}" onchange="insertTemperature(${pos},'temperatureField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;" size="3" maxlength="4"></div>
            </td>
    </c:forEach>
    </c:otherwise>
</c:choose>
</tr>

<script type='text/javascript'>
    var replyTemperature = function(data)
    {
    var dvals = data.split("=");
    var key = "temperatureValue" + dvals[1];
    var value =dvals[0];
    var temperatureValue = document.getElementById(key);
    temperatureValue.innerHTML = value;
    }
  </script>




