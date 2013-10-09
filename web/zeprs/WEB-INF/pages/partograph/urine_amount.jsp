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

<tr id="urineAmountRow0">
<td class="partoLabel"><div>Urine</div></td>
<td class="partoLegend"><div>ml</div></td>
<c:choose>
    <c:when test="${! empty urineAmount}">
    <c:forEach var="item" varStatus="loop"  begin="${startRowHour}" end="${stopRowHour}">
        <c:set var="pos" value="${loop.index}"/>
            <td colspan="2" id="urineAmountCell.${pos}" onclick="justReveal('urineAmountField${pos}')" valign="top">
            <logic:notEmpty name="urineAmount" property="urineAmount${pos}">
                 <div id="urineAmountValue${pos}"><bean:write name="urineAmount" property="urineAmount${pos}"/></div>
                <div id="property${pos}"><input type="text" name="urineAmount${pos}" id="urineAmountField${pos}" onchange="insertUrineAmount(${pos},'urineAmountField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;" size="3" maxlength="4">
                </div>
            </logic:notEmpty>
            <logic:empty name="urineAmount" property="urineAmount${pos}">
                <div id="urineAmountValue${pos}"></div>
                <div id="property${pos}"><input type="text" name="urineAmount${pos}" id="urineAmountField${pos}" onchange="insertUrineAmount(${pos},'urineAmountField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;" size="3" maxlength="4"></div>
            </logic:empty>
            </td>
    </c:forEach>
    </c:when>
    <c:otherwise>
    <c:forEach var="item" varStatus="loop"  begin="${startRowHour}" end="${stopRowHour}" >
    <c:set var="pos" value="${loop.index}"/>
            <td colspan="2" id="urineAmountCell.${pos}" onclick="justReveal('urineAmountField${pos}')" valign="top" >
                <div id="urineAmountValue${pos}"></div>
                <div id="property${pos}"><input type="text" name="urineAmount${pos}" id="urineAmountField${pos}" onchange="insertUrineAmount(${pos},'urineAmountField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;" size="3" maxlength="4"></div>
            </td>
    </c:forEach>
    </c:otherwise>
</c:choose>
</tr>

<script type='text/javascript'>
    var replyUrineAmount = function(data)
    {
    var dvals = data.split("=");
    var key = "urineAmountValue" + dvals[1];
    var value =dvals[0];
    var urineAmountValue = document.getElementById(key);
    urineAmountValue.innerHTML = value;
    }
  </script>




