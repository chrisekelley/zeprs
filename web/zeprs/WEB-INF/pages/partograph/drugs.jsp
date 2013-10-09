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

<tr id="drugsDispensedRow0">
<td class="partoLabel">Drugs and IV fluids</td>
<td class="partoLabel">&nbsp;</td>
<c:choose>
    <c:when test="${! empty drugsDispensed}">
        <c:forEach var="item" varStatus="loop"  begin="4" end="20">
            <c:set var="pos" value="${loop.index-3}"/>
            <logic:equal value="${row}" name="drugsDispensed" property="drugsDispensed${pos}"><td colspan="2" onclick="insertDrugsDispensed(${row}, ${pos},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="drugsDispensedCell${row}.${pos}" class="chartFilled">&nbsp;</td>
            </logic:equal>
            <logic:notEqual value="${row}" name="drugsDispensed" property="drugsDispensed${pos}">
            <td colspan="2" onclick="insertDrugsDispensed(${row}, ${pos},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="drugsDispensedCell${row}.${pos}">&nbsp;</td>
            </logic:notEqual>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:forEach var="item" varStatus="loop"  begin="1" end="17" >
            <td colspan="2" onclick="insertDrugsDispensed(1, ${loop.index},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="drugsDispensedCell${row}.${loop.index}">&nbsp;</td>
        </c:forEach>
    </c:otherwise>
</c:choose>
</tr>
<script type='text/javascript'>
var replyDrugsDispensed = function(data) {
    var dvals = data.split("=");
    var row = dvals[0];
    var column =dvals[1];
    var id = "drugsDispensedCell" + row + "." + column;
    var item = document.getElementById(id);
    item.innerHTML = row;
    item.className = "chartFilled";
}
</script>




