<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type='text/javascript'>
    var startCervix = "";
    var currentCervix = "";
    var alertRow = "";
    var alertPos = "";
</script>
<c:set var="currentCervixValue"/>
<c:set var="alertRow"/>
<c:set var="alertPos"/>
<c:set var="currentCervixValue"/>
<c:choose>
    <c:when test="${! empty cervix.actionStartRow}">
        <c:set var="alertRow" value="${cervix.actionStartRow}"/>
        <c:set var="alertPos" value="${cervix.actionStartColumn}"/>
        <script type='text/javascript'>
            alertRow = "${alertRow}";
            alertPos = "${alertPos}";
        </script>
    </c:when>
    <c:otherwise>
        <c:forEach var="row" items="10,9,8,7,6,5,4,3,2,1,0" varStatus="iRow">
            <c:forEach var="item" varStatus="loop" begin="${startRowHour}" end="${stopRowHour}">
                <c:if test="${! empty cervix}">
                    <c:set var="pos" value="${loop.index}"/>
                    <logic:equal value="${row}" name="cervix" property="cervix${pos}">
                        <c:if test="${empty alertRow || row < alertRow}">
                            <c:if test="${row >3}">
                                <c:set var="alertRow" value="${row}"/>
                                <c:if test="${empty alertPos || pos < alertPos}">
                                    <c:set var="alertPos" value="${pos}"/>
                                </c:if>
                            </c:if>
                        </c:if>
                    </logic:equal>
                </c:if>
            </c:forEach>
        </c:forEach>
        <script type='text/javascript'>
            alertRow = "${alertRow}";
            alertPos = "${alertPos}";
        </script>
    </c:otherwise>
</c:choose>

<c:forEach var="row" items="10,9,8,7,6,5,4,3,2,1,0" varStatus="iRow">
<tr id="cervixRow${iRow.index}">
<c:if test="${row =='10'}">
<td rowspan="13" class="partoLabel">
    <div>
        <p>Cervix <img src="/zeprs/images/dot_red.gif"><br/>
        (cm)</p>
        <p>
            <a href="#" onclick="toggleDescent()">Switch to Desc. Plot</a> <img src="/zeprs/images/triangle.gif">
        </p>
    </div>
</td>
</c:if>
<td class="partoLegend"><div>${row}</div></td>
<c:choose>
    <c:when test="${! empty cervix}">
        <c:forEach var="item" varStatus="loop"  begin="${startRowHour}" end="${stopRowHour}">
            <c:set var="pos" value="${loop.index}"/>
            <logic:equal value="${row}" name="cervix" property="cervix${pos}">
                <td align="center" colspan="2" onclick="insertCervix(${row}, ${pos},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="cervixCell${row}.${pos}" width="14">
                    <div id="cervixCellDiv${row}.${pos}">
                        <img alt="CervixImg${row}.${pos}" id="CervixImg${row}.${pos}" src="/zeprs/images/dot_red.gif">
                        <script type='text/javascript'>
                        currentCervix = '<bean:write name="cervix" property="cervix${pos}"/>';
                        </script>
                        <c:if test="${! empty descent}">
                        <logic:equal value="${row}" name="descent" property="descent${pos}">
                            <img id="DescentImgB${row}.${pos}" src="/zeprs/images/triangle.gif"></logic:equal>
                        </c:if>
                        </div></td>
            </logic:equal>
            <logic:notEqual value="${row}" name="cervix" property="cervix${pos}">
            <td align="center" colspan="2" onclick="insertCervix(${row}, ${pos},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="cervixCell${row}.${pos}" width="14">
                <div id="cervixCellDiv${row}.${pos}">
                    <c:if test="${! empty descent}">
                    <logic:equal value="${row}" name="descent" property="descent${pos}">
                        <img id="DescentImgB${row}.${pos}" src="/zeprs/images/triangle.gif"></logic:equal>
                    </c:if>
                </div>
            </td>
            </logic:notEqual>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:forEach var="item" varStatus="loop"  begin="${startRowHour}" end="${stopRowHour}">
            <c:set var="pos" value="${loop.index}"/>
            <td align="center" colspan="2" onclick="insertCervix(${row}, ${loop.index},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="cervixCell${row}.${loop.index}" width="14">
                <div id="cervixCellDiv${row}.${pos}">
                     <c:if test="${! empty descent}">
                        <logic:equal value="${row}" name="descent" property="descent${pos}">
                            <img id="DescentImgB${row}.${pos}" src="/zeprs/images/triangle.gif">
                        </logic:equal>
                     </c:if>
                </div>
            </td>
        </c:forEach>
    </c:otherwise>
</c:choose>
</tr>
</c:forEach>

<tr id="cervixRow11">
<td class="partoLegend"><div>Hours</div></td>
<c:if test="${! empty started}">
    <fmt:formatDate var="startHour" value="${started}" pattern="HH" type="both" scope="page"/>
    <fmt:formatDate var="startMinutes" value="${started}" pattern="mm" scope="page"/>
</c:if>
<c:set var="timing"/>
<c:forEach var="item" varStatus="loop"  begin="${startRowHour}" end="${stopRowHour}">
    <c:set var="start" value="${(item-1) + startHour}"/>
    <c:if test="${start > 23}">
         <c:set var="start" value="${start - 24}"/>
    </c:if>
    <c:if test="${! empty started}">
    <c:set var="timing" value="${start}:${startMinutes}"/>
    </c:if>
<td colspan="2"><div id="partoTiming${loop.index}">${timing}</div></td>
</c:forEach>
</tr>

<tr id="cervixRow12">
<td class="partoLegend"><div>Time</div></td>
<c:forEach var="item" varStatus="loop"  begin="${startRowHour}" end="${stopRowHour}">
<td colspan="2">
    <div id="cervixTime${item}">
    <logic:notEmpty name="cervix">
        <logic:notEmpty name="cervix" property="cervix${item}">
        <bean:write name="cervix" property="timeObservation${item}" format="HH:mm"/>
        </logic:notEmpty>
    </logic:notEmpty>
    </div>
</td>
</c:forEach>
</tr>

<script type='text/javascript'>

if (alertRow != "") {
    startCervix = "cervix," + alertRow + "," + alertPos;
}

var currentStation = "";
<logic:notEmpty name="vaginalExam">
<c:forEach var="item" varStatus="loop" begin="1" end="6">
<c:set var="pos" value="${loop.index}"/>
<logic:notEmpty name="vaginalExam" property="station${pos}">
currentStation = '<bean:write name="vaginalExam" property="station${pos}"/>';
</logic:notEmpty>
</c:forEach>
</logic:notEmpty>
    // initializes Cervix alert row when page is first loaded.
if (startCervix!="") {
    var cerVals = startCervix.split(",");
    var type = cerVals[0];
    var row = cerVals[1];
    var column =cerVals[2];
    createAlertRow(type, row, column);
}

function createActionLineValues(row, column) {
    Partograph.updateActionLineValues(null, row, column, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId}, '${user}');
}
function updateActionLineValues(row, column) {
    Partograph.updateActionLineValues(replyUpdateActionLines, row, column, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId}, '${user}');
}
</script>
