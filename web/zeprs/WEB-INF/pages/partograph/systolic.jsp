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

<c:forEach var="row" items="200,190,180,170,160,150,140,130,120,110,100,90,80,70,60"  varStatus="iRow">
<tr id="systolicRow${iRow.index}">
<c:if test="${row =='200'}">
<td rowspan="15" class="partoLabel"><p>
Systol.: <img src="/zeprs/images/arrow_up_red.gif">
</p>
<p>
<a href="#" onclick="toggleBP('diastolicRow','systolicRow','pulseRow')">Switch to Diast.</a>
</p>
<p>
<a href="#" onclick="toggleBP('pulseRow','systolicRow','diastolicRow')">Switch to Pulse</a>
</p>
</td>
</c:if>
<td class="partoLegend"><div>${row}</div></td>
<c:choose>
    <c:when test="${! empty bloodPressure}">
        <c:forEach var="item" varStatus="loop" begin="${startRow}" end="${stopRow}">
            <c:set var="pos" value="${loop.index}"/>
            <logic:equal value="${row}" name="bloodPressure" property="systolic${pos}">
                <td valign="top" onclick="insertSystolic(${row}, ${pos},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="systolicCell${row}.${pos}">
                    <div id="systolicCellDiv${row}.${pos}">
                        <img alt="systolicImg${row}.${pos}" id="systolicImg${row}.${pos}" src="/zeprs/images/arrow_up_red.gif">
                        <c:if test="${! empty pulse}">
                            <logic:equal value="${row}" name="pulse" property="pulse${pos}">
                                <img alt="pulseImgBS${row}.${pos}" id="pulseImgBS${row}.${pos}" src="/zeprs/images/dot.gif">
                            </logic:equal>
                        </c:if>
                        <logic:equal value="${row}" name="bloodPressure" property="diastolic${pos}">
                            <img alt="diastolicImg${row}.${pos}" id="diastolicImgBS${row}.${pos}" src="/zeprs/images/arrow_down.gif">
                        </logic:equal>
                    </div>
                </td>
            </logic:equal>
            <logic:notEqual value="${row}" name="bloodPressure" property="systolic${pos}">
            <td valign="top" onclick="insertSystolic(${row}, ${pos},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="systolicCell${row}.${pos}">
                <div id="systolicCellDiv${row}.${pos}">
                    <c:if test="${! empty pulse}">
                            <logic:equal value="${row}" name="pulse" property="pulse${pos}">
                                <img alt="pulseImgBS${row}.${pos}" id="pulseImgBS${row}.${pos}" src="/zeprs/images/dot.gif">
                            </logic:equal>
                        </c:if>
                        <logic:equal value="${row}" name="bloodPressure" property="diastolic${pos}">
                            <img alt="diastolicImg${row}.${pos}" id="diastolicImgBS${row}.${pos}" src="/zeprs/images/arrow_down.gif">
                        </logic:equal>
                </div>
            </td>
            </logic:notEqual>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:forEach var="item" varStatus="loop"  begin="${startRow}" end="${stopRow}" >
            <c:set var="pos" value="${loop.index}"/>
            <td valign="top" onclick="insertSystolic(${row}, ${loop.index},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="systolicCell${row}.${loop.index}">
                <div id="systolicCellDiv${row}.${pos}">
                &nbsp;
                <c:if test="${! empty pulse}">
                    <logic:equal value="${row}" name="pulse" property="pulse${pos}">
                        <img alt="pulseImgBS${row}.${pos}" id="pulseImgBS${row}.${pos}" src="/zeprs/images/dot.gif">
                    </logic:equal>
                </c:if>
                </div>
            </td>
        </c:forEach>
    </c:otherwise>
</c:choose>
</tr>
</c:forEach>

<script type='text/javascript'>
var replySystolic = function(data) {
    var dvals = data.split("=");
    var row = dvals[0];
    var column =dvals[1];
    rowNum = new Number(trim(row));
    var id = rowNum + "." + column;
    var sid = "systolicCellDiv" + id;
    var did = "diastolicCellDiv" + id;
    var pid = "pulseCellDiv" + id;
    var sitem = document.getElementById(sid);
    var ditem = document.getElementById(did);
    var pitem = document.getElementById(pid);

    sitemImage = document.createElement('img');
    sitemImage.src = '/zeprs/images/arrow_up_red.gif';
    sitemImage.id = 'systolicImg' + id;
    ditemImage = document.createElement('img');
    ditemImage.src = '/zeprs/images/arrow_up.gif';
    ditemImage.id = 'systolicImgBD' + id;
    pitemImage = document.createElement('img');
    pitemImage.src = '/zeprs/images/arrow_up.gif';
    pitemImage.id = 'systolicImgBP' + id;

    sitem.appendChild(sitemImage);
    ditem.appendChild(ditemImage);
    pitem.appendChild(pitemImage);

    //sitem.innerHTML = '<img alt="systolicImg'  + row + "." + column + '" id="systolicImg'  + row + "." + column + '" src="/zeprs/images/arrow_up_red.gif">';
    //ditem.innerHTML = '<img alt="systolicImgB'  + row + "." + column + '" id="systolicImg'  + row + "." + column + '" src="/zeprs/images/arrow_up.gif">';
    //pitem.innerHTML = '<img alt="systolicImgB'  + row + "." + column + '" id="systolicImg'  + row + "." + column + '" src="/zeprs/images/arrow_up.gif">';
    //item.className = "chartFilled";
}
var replyDeleteSystolic = function(data)
{
    var dvals = data.split("=");
    var row = trim(dvals[0]);
    var column =trim(dvals[1]);
    // String = new String(row);
    var id =  "systolicCellDiv" + row + "." + column;
    var diastolicId =  "diastolicCellDiv" + row + "." + column;
    var pulseId =  "pulseCellDiv" + row + "." + column;

    var img = "systolicImg" + row + "." + column;
    var diastolicImg = "systolicImgBD" + row + "." + column;
    var pulseImg = "systolicImgBP" + row + "." + column;

    var space = document.createElement("&nbsp;");

    divObj = document.getElementById(id);
    diastolicDivObj = document.getElementById(diastolicId);
    pulseDivObj = document.getElementById(pulseId);

    imgObject = document.getElementById(img);
    diastolicImgObject = document.getElementById(diastolicImg);
    pulseImgObject = document.getElementById(pulseImg);

    if (imgObject) {
        imgObject.parentNode.removeChild(imgObject);
        diastolicImgObject.parentNode.removeChild(diastolicImgObject);
        pulseImgObject.parentNode.removeChild(pulseImgObject);
        if (divObj.firstChild == null) {
        divObj.appendChild(space);
        }
        if (diastolicImgObject.firstChild == null) {
        diastolicImgObject.appendChild(space);
        }
        if (pulseDivObj.firstChild == null) {
        pulseImgObject.appendChild(space);
        }
    }

    /*var timeKey = "cervixTime" + column;
    var timeElement = document.getElementById(timeKey);
    timeElement.innerHTML = "";*/
    // document.getElementById(id).removeChild(img);
}
</script>




