<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<c:forEach var="row" items="190,180,170,160,150,140,130,120,110,100,90,80,70,60" varStatus="iRow">
<tr id="fetalHrRow${iRow.index}">
<c:if test="${row =='190'}"><td rowspan="14" class="partoLabel"><p>Fetal Heart Rate</p></td></c:if>
<td class="partoLegend"><div>${row}</div></td>
<c:choose>
    <c:when test="${! empty fetalHr}">
        <c:forEach var="item" varStatus="iCell"  begin="${startRow}" end="${stopRow}">
            <logic:equal value="${row}" name="fetalHr" property="fetalHeartRate${iCell.index}"><td onclick="insertFHR(${row}, ${iCell.index},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="${row}.${iCell.index}"><img src="/zeprs/images/dot.gif"></td>
            </logic:equal>
            <logic:notEqual value="${row}" name="fetalHr" property="fetalHeartRate${iCell.index}"><td onclick="insertFHR(${row}, ${iCell.index},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="${row}.${iCell.index}">&nbsp;</td>
            </logic:notEqual>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:forEach var="item" varStatus="iCell"  begin="${startRow}" end="${stopRow}" >
            <td onclick="insertFHR(${row}, ${iCell.index},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="${row}.${iCell.index}">&nbsp;</td>
        </c:forEach>
    </c:otherwise>
</c:choose>
</tr>
</c:forEach>

<script type='text/javascript'>
var replyFHR = function(data)
{
    var dvals = data.split("=");
    var row = dvals[0];
    var column =dvals[1];
    if (row < 120) {
        alert("Foetal heart rate < 120; please refer to UTH.")
    }
    if (row > 160) {
        alert("Foetal heart rate > 160; please refer to UTH.")
    }
    var id =  row + "." + column;
    document.getElementById(id).innerHTML = '<img src="/zeprs/images/dot.gif">';
}

var replyDeleteFHR = function(data)
{
    var dvals = data.split("=");
    var row = dvals[0];
    var column =dvals[1];
    var id =  row + "." + column;
    document.getElementById(id).innerHTML = '&nbsp;';
}
</script>




