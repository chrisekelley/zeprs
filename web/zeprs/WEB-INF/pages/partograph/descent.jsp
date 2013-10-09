<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:forEach var="row" items="10,9,8,7,6,5,4,3,2,1,0" varStatus="iRow">

    <tr id="descentRow${iRow.index}" style="display:none; border:none">
        <c:if test="${row =='10'}">
            <td rowspan="13" class="partoLabel">
                <div>
                    <p>Desc. Plot <img src="/zeprs/images/triangle_red.gif"></p>
                    <p>
                        <a href="#" onclick="toggleDescent()">Switch to Cervix</a> <img src="/zeprs/images/dot.gif">
                    </p>
                </div>
            </td>
        </c:if>
        <td class="partoLegend"><div>${row}</div></td>
        <c:choose>
            <c:when test="${! empty descent}">
                <c:forEach var="item" varStatus="loop" begin="${startRowHour}" end="${stopRowHour}">
                    <c:set var="pos" value="${loop.index}"/>
                    <logic:equal value="${row}" name="descent" property="descent${pos}">
                        <td align="center" colspan="2"
                            onclick="insertDescent(${row}, ${pos},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )"
                            id="descentCell${row}.${pos}">
                        <div id="descentCellDiv${row}.${pos}">
                            <img id="DescentImg${row}.${pos}" src="/zeprs/images/triangle_red.gif">
                            <c:if test="${! empty cervix}">
                                <logic:equal value="${row}" name="cervix" property="cervix${pos}">
                                    <img  id="CervixImgB${row}.${pos}" src="/zeprs/images/dot.gif">
                                </logic:equal>
                            </c:if>
                        </div></td>
                    </logic:equal>
                    <logic:notEqual value="${row}" name="descent" property="descent${pos}">
                        <td align="center" colspan="2"
                            onclick="insertDescent(${row}, ${pos},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )"
                            id="descentCell${row}.${pos}">
                            <div id="descentCellDiv${row}.${pos}">
                                <c:if test="${! empty cervix}">
                                    <logic:equal value="${row}" name="cervix" property="cervix${pos}">
                                        <img  id="CervixImgB${row}.${pos}" src="/zeprs/images/dot.gif">
                                    </logic:equal>
                                </c:if>
                            </div>
                        </td>
                    </logic:notEqual>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach var="item" varStatus="loop" begin="${startRowHour}" end="${stopRowHour}">
                    <c:set var="pos" value="${loop.index}"/>
                    <td align="center" colspan="2"
                        onclick="insertDescent(${row}, ${loop.index},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )"
                        id="descentCell${row}.${loop.index}">
                        <div id="descentCellDiv${row}.${pos}">
                            <c:if test="${! empty cervix}">
                                <logic:equal value="${row}" name="cervix" property="cervix${pos}">
                                    <img id="cervixImgB${row}.${pos}"  src="/zeprs/images/dot.gif">
                                </logic:equal>
                            </c:if>
                        </div>
                    </td>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </tr>
</c:forEach>

<tr id="descentRow11" style="display:none; border:none">
    <td class="partoLegend"><div>Hours</div></td>
    <c:if test="${! empty started}">
    <fmt:formatDate var="startHour" value="${started}" pattern="HH" type="both" scope="page"/>
    <fmt:formatDate var="startMinutes" value="${started}" pattern="mm" scope="page"/>
</c:if>
<c:set var="timing"/>
    <c:forEach var="item" varStatus="loop" begin="${startRowHour}" end="${stopRowHour}">
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

<tr id="descentRow12" style="display:none; border:none">
    <td class="partoLegend"><div>Time</div></td>
    <c:forEach var="item" varStatus="loop" begin="${startRowHour}" end="${stopRowHour}">
        <td colspan="2" id="descentTime${item}">
            <div>
                <logic:notEmpty name="descent">
                    <logic:notEmpty name="descent" property="descent${item}">
                        <bean:write name="descent" property="timeObservation${item}" format="HH:mm"/>
                    </logic:notEmpty>
                </logic:notEmpty>
            </div>
        </td>
    </c:forEach>
</tr>


<script type='text/javascript'>
    var replyDescent = function(data) {
    var dvals = data.split("=");
    var row = dvals[0];
    var column =dvals[1];
    rowNum = new Number(trim(row));
    var id = rowNum + "." + column;
    // alert("data: " + data + " id: " + id);
    var citem = document.getElementById("cervixCellDiv" + id);
    var ditem = document.getElementById("descentCellDiv" + id);
    citemImage = document.createElement('img');
    citemImage.src = '/zeprs/images/triangle.gif';
    citemImage.id = 'DescentImgB' + id;
    ditemImage = document.createElement('img');
    ditemImage.src = '/zeprs/images/triangle_red.gif';
    ditemImage.id = 'DescentImg' + id;
    citem.appendChild(citemImage);
    ditem.appendChild(ditemImage);
    var timeKey = "descentTime" + dvals[1];
    var timeElement = document.getElementById(timeKey);
    var timeValue =dvals[2];
    timeElement.innerHTML = timeValue;
}

var replyDeleteDescent = function(data)
{
    var dvals = data.split("=");
    var row = trim(dvals[0]);
    var column =trim(dvals[1]);
    var id =  "descentCellDiv" + row + "." + column;
    var cervixId =  "cervixCellDiv" + row + "." + column;
    var img = "DescentImg" + row + "." + column;
    var cervixImg = "DescentImgB" + row + "." + column;
    var space = document.createElement("&nbsp;");
    divObj = document.getElementById(id);
    cervixDivObj = document.getElementById(cervixId);
    imgObject = document.getElementById(img);
    cervixImgObject = document.getElementById(cervixImg);
    if (imgObject) {
        imgObject.parentNode.removeChild(imgObject);
    }
    if (cervixImgObject) {
        cervixImgObject.parentNode.removeChild(cervixImgObject);
    }    
    if (divObj.firstChild == null) {
    divObj.appendChild(space);
    }
    if (cervixDivObj.firstChild == null) {
    cervixDivObj.appendChild(space);
    }
    var timeKey = "descentTime" + column;
    var timeElement = document.getElementById(timeKey);
    timeElement.innerHTML = "";
}
</script>