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

<tr id="antiDRow0">
<td class="partoLabel">Anti-D</td>
<td class="partoLabel">&nbsp;</td>
<c:choose>
    <c:when test="${! empty antiD}">
    <c:forEach var="item" varStatus="loop"  begin="4" end="20">
        <c:set var="pos" value="${loop.index-3}"/>
            <td colspan="2" id="antiDCell${pos}" onclick="toggle2('antiDField${pos}')">
            <logic:notEmpty name="antiD" property="antiD${pos}">
                <div id="property${pos}"><bean:write name="antiD" property="antiD${pos}"/>
                <select name="antiD${pos}" id="antiDField${pos}" onchange="insertAntiD(${pos},'antiDField${pos}',${patient.id}, ${zeprs_session.pregnancy.id},'${user}',${siteId})" style="display:none; border:none">
                    <option value="">--</option>
                    <option value="1">Yes</option>
                    <option value="0">No</option>
                </select>
                </div>
            </logic:notEmpty>
            <logic:empty name="antiD" property="antiD${pos}">
                <div id="property${pos}">&nbsp;&nbsp;&nbsp;
                <select name="antiD${pos}" id="antiDField${pos}" onchange="insertAntiD(${pos}, 'antiDField${pos}', ${patient.id}, ${zeprs_session.pregnancy.id},'${user}',${siteId})" style="display:none; border:none">
                    <option value="">--</option>
                    <option value="1">Yes</option>
                    <option value="0">No</option>
                </select>
                </div>
            </logic:empty>
            </td>

    </c:forEach>
    </c:when>
    <c:otherwise>
    <c:forEach var="item" varStatus="loop"  begin="1" end="17" >
    <c:set var="pos" value="${loop.index}"/>
            <td colspan="2" id="antiDCell${pos}" onclick="toggle2('antiDField${pos}')">
                <div id="property${pos}">&nbsp;&nbsp;&nbsp;
                <select name="antiD${pos}" id="antiDField${pos}" onchange="insertAntiD(${pos}, 'antiDField${pos}', ${patient.id}, ${zeprs_session.pregnancy.id},'${user}',${siteId})" style="display:none; border:none">
                    <option value="">--</option>
                    <option value="1">Yes</option>
                    <option value="0">No</option>
                </select>
                </div>
            </td>
    </c:forEach>
    </c:otherwise>
</c:choose>
</tr>

<script type='text/javascript'>
var replyAntiD = function(data)
{
    var dvals = data.split("=");
    var key = "antiDCell" + dvals[1];
    var value =dvals[0];
    var antiDValue = document.getElementById(key);
    if (antiDValue == "1") {
        antiDValue.innerHTML = "Yes";
    } else if (antiDValue == "0"){
        antiDValue.innerHTML = "No";
    } else {
    antiDValue.innerHTML = value;
    }
}
  </script>




