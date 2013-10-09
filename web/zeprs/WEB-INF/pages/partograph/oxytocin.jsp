<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<tr id="oxytocinRow0">
<td class="partoLabel" rowspan="2"><p>Oxy</p></td>
<td class="partoLegend"><div>U/L:</div></td>
<c:choose>
    <c:when test="${! empty oxytocin}">
    <c:forEach var="item" varStatus="loop"  begin="${startRow}" end="${stopRow}">
        <c:set var="pos" value="${loop.index}"/>
            <td id="oxytocinCell.${pos}" onclick="justReveal('oxytocinField${pos}')" valign="top">
            <logic:notEmpty name="oxytocin" property="oxytocin${pos}">
                <div id="oxytocinValue${pos}"><bean:write name="oxytocin" property="oxytocin${pos}"/></div>
                <div id="property${pos}"><input type="text" name="oxytocin${pos}" id="oxytocinField${pos}" onchange="insertOxytocin(${pos},'oxytocinField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;" size="3" maxlength="4">
                </div>
            </logic:notEmpty>
            <logic:empty name="oxytocin" property="oxytocin${pos}">
                <div id="oxytocinValue${pos}"></div>
                <div id="property${pos}"><input type="text" name="oxytocin${pos}" id="oxytocinField${pos}" onchange="insertOxytocin(${pos},'oxytocinField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;" size="3" maxlength="4"></div>
            </logic:empty>
            </td>

    </c:forEach>
    </c:when>
    <c:otherwise>
    <c:forEach var="item" varStatus="loop"  begin="${startRow}" end="${stopRow}" >
    <c:set var="pos" value="${loop.index}"/>
            <td id="oxytocinCell.${pos}" onclick="justReveal('oxytocinField${pos}')" valign="top" >
                <div id="oxytocinValue${pos}"></div>
                <div id="property${pos}"><input type="text" name="oxytocin${pos}" id="oxytocinField${pos}" onchange="insertOxytocin(${pos},'oxytocinField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;" size="3" maxlength="4"></div>
            </td>
    </c:forEach>
    </c:otherwise>
</c:choose>
</tr>

<tr id="oxytocinRow1">
<td class="partoLegend"><div>dpm</div></td>
<c:choose>
    <c:when test="${! empty oxytocin}">
    <c:forEach var="item" varStatus="loop"  begin="${startRow+48}" end="${stopRow+48}">
        <c:set var="pos" value="${loop.index}"/>
            <td id="oxytocinCell.${pos}" onclick="justReveal('oxytocinField${pos}')" valign="top">
            <logic:notEmpty name="oxytocin" property="oxytocinDrops${pos}">
                <div id="oxytocinValue${pos}"><bean:write name="oxytocin" property="oxytocinDrops${pos}"/></div>
                <div id="property${pos}"><input type="text" name="oxytocin${pos}" id="oxytocinField${pos}" onchange="insertOxytocin(${pos},'oxytocinField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;" size="3" maxlength="4">
                </div>
            </logic:notEmpty>
            <logic:empty name="oxytocin" property="oxytocinDrops${pos}">
                <div id="oxytocinValue${pos}"></div>
                <div id="property${pos}"><input type="text" name="oxytocin${pos}" id="oxytocinField${pos}" onchange="insertOxytocin(${pos},'oxytocinField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;" size="3" maxlength="4"></div>
            </logic:empty>
            </td>
    </c:forEach>
    </c:when>
    <c:otherwise>
    <c:forEach var="item" varStatus="loop"  begin="${startRow+48}" end="${stopRow+48}" >
    <c:set var="pos" value="${loop.index}"/>
            <td id="oxytocinCell.${pos}" onclick="justReveal('oxytocinField${pos}')" valign="top" >
                <div id="oxytocinValue${pos}"></div>
                <div id="property${pos}"><input type="text" name="oxytocin${pos}" id="oxytocinField${pos}" onchange="insertOxytocin(${pos},'oxytocinField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;" size="3" maxlength="4"></div>
            </td>
    </c:forEach>
    </c:otherwise>
</c:choose>
</tr>

<script type='text/javascript'>
    var replyOxytocin = function(data)
    {
    var dvals = data.split("=");
    var key = "oxytocinValue" + dvals[1];
    var value =dvals[0];
    var oxytocinValue = document.getElementById(key);
    oxytocinValue.innerHTML = value;
    }
  </script>




