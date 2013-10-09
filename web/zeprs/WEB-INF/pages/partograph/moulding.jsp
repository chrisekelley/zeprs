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

<tr id="mouldingRow0" >
<td class="partoLabel"><p>Mould.</p></td>
<td class="partoLegend"><div>&nbsp;</div></td>
<c:choose>
    <c:when test="${! empty moulding}">
    <c:forEach var="item" varStatus="loop"  begin="${startRow}" end="${stopRow}">
        <c:set var="pos" value="${loop.index}"/>
            <td id="mouldingCell.${pos}" onclick="toggle2('mouldingField${pos}')">
            <logic:notEmpty name="moulding" property="moulding${pos}">
                <div id="mouldingValue${pos}"><bean:write name="moulding" property="moulding${pos}"/></div>
                <div id="property${pos}">
                <select name="moulding${pos}" id="mouldingField${pos}" onchange="insertMoulding(${pos},'mouldingField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none; border:none">
                    <option value="">--</option>
                    <option value="delete">del.</option>
                    <option value="0">0</option>
                    <option value="1+">1+</option>
                    <option value="2+">2+</option>
                    <option value="3+">3+</option>
                </select>
                </div>
            </logic:notEmpty>
            <logic:empty name="moulding" property="moulding${pos}">
                <div id="mouldingValue${pos}"></div>
                <div id="property${pos}">&nbsp;&nbsp;&nbsp;
                <select name="moulding${pos}" id="mouldingField${pos}" onchange="insertMoulding(${pos}, 'mouldingField${pos}', ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none; border:none">
                    <option value="">--</option>
                    <option value="delete">del.</option>
                    <option value="0">0</option>
                    <option value="1+">1+</option>
                    <option value="2+">2+</option>
                    <option value="3+">3+</option>
                </select>
                </div>
            </logic:empty>
            </td>

    </c:forEach>
    </c:when>
    <c:otherwise>
    <c:forEach var="item" varStatus="loop"  begin="${startRow}" end="${stopRow}" >
    <c:set var="pos" value="${loop.index}"/>
            <td id="mouldingCell.${pos}" onclick="toggle2('mouldingField${pos}')">
                <div id="mouldingValue${pos}"></div>
                <div id="property${pos}">&nbsp;&nbsp;&nbsp;
                <select name="moulding${pos}" id="mouldingField${pos}" onchange="insertMoulding(${pos}, 'mouldingField${pos}', ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none; border:none">
                    <option value="">--</option>
                    <option value="delete">del.</option>
                    <option value="0">0</option>
                    <option value="1+">1+</option>
                    <option value="2+">2+</option>
                    <option value="3+">3+</option>
                </select>
                </div>
            </td>
    </c:forEach>
    </c:otherwise>
</c:choose>
</tr>

<script type='text/javascript'>
var replyMoulding = function(data)
    {
    var dvals = data.split("=");
    var key = "mouldingValue" + dvals[1];
    var value =dvals[0];
    var mouldingValue = document.getElementById(key);
    mouldingValue.innerHTML = value;
    }
var replyDeleteMoulding = function(data)
    {
    var dvals = data.split("=");
    var key = "mouldingValue" + dvals[1];
    document.getElementById(key).innerHTML = '&nbsp;';
    }
  </script>