<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:forEach var="row" items="200,190,180,170,160,150,140,130,120,110,100,90,80,70,60"  varStatus="iRow">
<tr id="pulseRow${iRow.index}" style="display:none; border:none">
<c:if test="${row =='200'}">
<td rowspan="15" class="partoLabel"><p>Pulse: <img src="/zeprs/images/dot_red.gif"></p>
<p>
<a href="#" onclick="toggleBP('systolicRow','diastolicRow','pulseRow')">Switch to Syst.</a>
</p>
<p>
<a href="#" onclick="toggleBP('diastolicRow','systolicRow','pulseRow')">Switch to Diast.</a>
</p>
</td>
</c:if>
<td class="partoLegend"><div>${row}</div></td>
<c:choose>
    <c:when test="${! empty pulse}">
        <c:forEach var="item" varStatus="loop"  begin="${startRow}" end="${stopRow}">
            <c:set var="pos" value="${loop.index}"/>
            <logic:equal value="${row}" name="pulse" property="pulse${pos}">
                <td valign="top" onclick="insertPulse(${row}, ${pos},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="pulseCell${row}.${pos}">
                    <div id="pulseCellDiv${row}.${pos}">
                        <img alt="pulseImg${row}.${pos}" id="pulseImg${row}.${pos}"src="/zeprs/images/dot_red.gif">
                        <c:if test="${! empty bloodPressure}">
                            <logic:equal value="${row}" name="bloodPressure" property="systolic${pos}">
                                <img alt="systolicImg${row}.${pos}" id="systolicImgBP${row}.${pos}" src="/zeprs/images/arrow_up.gif">
                            </logic:equal>
                            <logic:equal value="${row}" name="bloodPressure" property="diastolic${pos}">
                                <img alt="diastolicImg${row}.${pos}" id="diastolicImgBP${row}.${pos}" src="/zeprs/images/arrow_down.gif">
                            </logic:equal>
                        </c:if>
                    </div>
                    </td>
            </logic:equal>
            <logic:notEqual value="${row}" name="pulse" property="pulse${pos}">
                <td valign="top" onclick="insertPulse(${row}, ${pos},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="pulseCell${row}.${pos}">
                    <div id="pulseCellDiv${row}.${pos}">
                        <c:if test="${! empty bloodPressure}">
                            <logic:equal value="${row}" name="bloodPressure" property="systolic${pos}">
                                <img alt="systolicImg${row}.${pos}" id="systolicImgBP${row}.${pos}" src="/zeprs/images/arrow_up.gif">
                            </logic:equal>
                            <logic:equal value="${row}" name="bloodPressure" property="diastolic${pos}">
                                <img alt="diastolicImg${row}.${pos}" id="diastolicImgBP${row}.${pos}" src="/zeprs/images/arrow_down.gif">
                            </logic:equal>
                        </c:if>
                    </div>
                </td>
            </logic:notEqual>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:forEach var="item" varStatus="loop"  begin="${startRow}" end="${stopRow}">
            <c:set var="pos" value="${loop.index}"/>
            <td valign="top" onclick="insertPulse(${row}, ${loop.index},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="pulseCell${row}.${loop.index}">
                <div id="pulseCellDiv${row}.${pos}">&nbsp;
                    <c:if test="${! empty bloodPressure}">
                        <logic:equal value="${row}" name="bloodPressure" property="systolic${pos}">
                            <img alt="systolicImg${row}.${pos}" id="systolicImgBP${row}.${pos}" src="/zeprs/images/arrow_up.gif">
                        </logic:equal>
                        <logic:equal value="${row}" name="bloodPressure" property="diastolic${pos}">
                            <img alt="diastolicImg${row}.${pos}" id="diastolicImgBP${row}.${pos}" src="/zeprs/images/arrow_down.gif">
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
var replyPulseOLD = function(data) {
    var dvals = data.split("=");
    var row = dvals[0];
    var column =dvals[1];
    //var id = "pulseCell" + row + "." + column;
    //var item = document.getElementById(id);
    //item.innerHTML = '<img src="/zeprs/images/dot.gif">';
    //item.className = "chartFilled";
    var sid = "systolicCell" + row + "." + column;
    var did = "diastolicCell" + row + "." + column;
    var pid = "pulseCell" + row + "." + column;
    var sitem = document.getElementById(sid);
    var ditem = document.getElementById(did);
    var pitem = document.getElementById(pid);
    sitem.innerHTML = '<img src="/zeprs/images/dot.gif">';
    ditem.innerHTML = '<img src="/zeprs/images/dot.gif">';
    pitem.innerHTML = '<img src="/zeprs/images/dot_red.gif">';
}

var replyPulse = function(data) {
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
    sitemImage.src = '/zeprs/images/dot.gif';
    sitemImage.id = 'pulseImgBS' + id;
    ditemImage = document.createElement('img');
    ditemImage.src = '/zeprs/images/dot.gif';
    ditemImage.id = 'pulseImgBD' + id;
    pitemImage = document.createElement('img');
    pitemImage.src = '/zeprs/images/dot_red.gif';
    pitemImage.id = 'pulseImg' + id;

    sitem.appendChild(sitemImage);
    ditem.appendChild(ditemImage);
    pitem.appendChild(pitemImage);
}
var replyDeletePulse = function(data)
{
    var dvals = data.split("=");
    var row = trim(dvals[0]);
    var column =trim(dvals[1]);
    // String = new String(row);
    var systolic =  "systolicCellDiv" + row + "." + column;
    var diastolicId =  "diastolicCellDiv" + row + "." + column;
    var pulseId =  "pulseCellDiv" + row + "." + column;

    var systolicImg = "pulseImgBS" + row + "." + column;
    var diastolicImg = "pulseImgBD" + row + "." + column;
    var pulseImg = "pulseImg" + row + "." + column;

    var space = document.createElement("&nbsp;");

    systolicdivObj = document.getElementById(systolic);
    diastolicDivObj = document.getElementById(diastolicId);
    pulseDivObj = document.getElementById(pulseId);

    systolicImgObject = document.getElementById(systolicImg);
    diastolicImgObject = document.getElementById(diastolicImg);
    pulseImgObject = document.getElementById(pulseImg);

    if (pulseImgObject) {
        systolicImgObject.parentNode.removeChild(systolicImgObject);
        diastolicImgObject.parentNode.removeChild(diastolicImgObject);
        pulseImgObject.parentNode.removeChild(pulseImgObject);

        if (systolicdivObj.firstChild == null) {
            systolicdivObj.appendChild(space);
        }
        if (diastolicImgObject.firstChild == null) {
            diastolicImgObject.appendChild(space);
        }
        if (pulseDivObj.firstChild == null) {
            pulseImgObject.appendChild(space);
        }
    }
}
</script>




