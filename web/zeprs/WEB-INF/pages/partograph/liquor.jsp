<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tr id="liquorRow0">
<td class="partoLabel"><p>Liquor</p></td>
<td class="partoLegend"><div>&nbsp;</div></td>
<c:choose>
    <c:when test="${! empty liquor}">
    <c:forEach var="item" varStatus="loop"  begin="${startRow}" end="${stopRow}">
        <c:set var="pos" value="${loop.index}"/>
            <td id="liquorCell.${pos}" onclick="toggle2('liquorField${pos}')">
            <logic:notEmpty name="liquor" property="liquor${pos}">
                <div id="liquorValue${pos}"><bean:write name="liquor" property="liquor${pos}"/></div>
                <div id="property${pos}">
                    <select name="liquor${pos}" id="liquorField${pos}" onchange="insertLiquor(${pos}, 'liquorField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none; border:none">
                        <option value="">--</option>
                        <option value="delete">del.</option>
                        <option value="I">I</option>
                        <option value="C">C</option>
                        <option value="B">B</option>
                        <option value="A">A</option>
                        <option value="M+">M+</option>
                        <option value="M++">M++</option>
                        <option value="M+++">M+++</option>
                    </select>
                </div>
            </logic:notEmpty>
            <logic:empty name="liquor" property="liquor${pos}">
                <div id="liquorValue${pos}"></div>
                <div id="property${pos}">&nbsp&nbsp&nbsp
                    <select name="liquor${pos}" id="liquorField${pos}" onchange="insertLiquor(${pos}, 'liquorField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none; border:none">
                        <option value="">--</option>
                        <option value="delete">del.</option>
                        <option value="I">I</option>
                        <option value="C">C</option>
                        <option value="B">B</option>
                        <option value="A">A</option>
                        <option value="M+">M+</option>
                        <option value="M++">M++</option>
                        <option value="M+++">M+++</option>
                    </select>
                </div>
            </logic:empty>
            </td>
    </c:forEach>
    </c:when>
    <c:otherwise>
    <c:forEach var="item" varStatus="loop"  begin="${startRow}" end="${stopRow}" >
    <c:set var="pos" value="${loop.index}"/>
            <td id="liquorCell.${pos}" onclick="toggle2('liquorField${pos}')">
                <div id="liquorValue${pos}"></div>
                <div id="property${pos}">&nbsp;&nbsp;&nbsp;
                    <select name="liquor${pos}" id="liquorField${pos}" onchange="insertLiquor(${pos}, 'liquorField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none; border:none">
                        <option value="">--</option>
                        <option value="delete">del.</option>
                        <option value="I">I</option>
                        <option value="C">C</option>
                        <option value="B">B</option>
                        <option value="A">A</option>
                        <option value="M+">M+</option>
                        <option value="M++">M++</option>
                        <option value="M+++">M+++</option>
                    </select>
                </div>
            </td>
    </c:forEach>
    </c:otherwise>
</c:choose>
</tr>

<script type='text/javascript'>
    var replyLiquor = function(data)
    {
    var dvals = data.split("=");
    var key = "liquorValue" + dvals[1];
    var value =dvals[0];
    var liquorValue = document.getElementById(key);
    liquorValue.innerHTML = value;
    }
    var replyDeleteLiquor = function(data)
{
    var dvals = data.split("=");
    var key = "liquorValue" + dvals[1];

    document.getElementById(key).innerHTML = '&nbsp;';
}
  </script>