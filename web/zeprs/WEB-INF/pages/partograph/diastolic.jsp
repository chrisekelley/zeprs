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
<tr id="diastolicRow${iRow.index}" style="display:none; border:none">
<c:if test="${row =='200'}">
<td rowspan="15" class="partoLabel"><p>Diast.: <img src="/zeprs/images/arrow_red_down.gif"></p>
<p>
<a href="#" onclick="toggleBP('systolicRow','diastolicRow','pulseRow')">Switch to Syst.</a>
</p>
<p>
<a href="#" onclick="toggleBP('pulseRow','systolicRow','diastolicRow')">Switch to Pulse</a>
</p>
</td>
</c:if>
<td class="partoLegend"><div>${row}</div></td>
<c:choose>
    <c:when test="${! empty bloodPressure}">
        <c:forEach var="item" varStatus="loop"  begin="${startRow}" end="${stopRow}">
            <c:set var="pos" value="${loop.index}"/>
            <logic:equal value="${row}" name="bloodPressure" property="diastolic${pos}">
                <td valign="bottom" onclick="insertDiastolic(${row}, ${pos},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="diastolicCell${row}.${pos}">
                    <div id="diastolicCellDiv${row}.${pos}">
                        <img alt="diastolicImg${row}.${pos}" id="diastolicImg${row}.${pos}" src="/zeprs/images/arrow_red_down.gif">
                        <c:if test="${! empty pulse}">
                            <logic:equal value="${row}" name="pulse" property="pulse${pos}">
                                <img alt="pulseImgBD${row}.${pos}" id="pulseImgBD${row}.${pos}" src="/zeprs/images/dot.gif">
                            </logic:equal>
                        </c:if>
                        <logic:equal value="${row}" name="bloodPressure" property="systolic${pos}">
                            <img alt="systolicImg${row}.${pos}" id="systolicImgBD${row}.${pos}" src="/zeprs/images/arrow_up.gif">
                        </logic:equal>
                    </div>
                </td>
            </logic:equal>
            <logic:notEqual value="${row}" name="bloodPressure" property="diastolic${pos}">
            <td valign="top" onclick="insertDiastolic(${row}, ${pos},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="diastolicCell${row}.${pos}">
                <div id="diastolicCellDiv${row}.${pos}">
                    <c:if test="${! empty pulse}">
                        <logic:equal value="${row}" name="pulse" property="pulse${pos}">
                            <img alt="pulseImgBD${row}.${pos}" id="pulseImgBD${row}.${pos}" src="/zeprs/images/dot.gif">
                        </logic:equal>
                    </c:if>
                    <logic:equal value="${row}" name="bloodPressure" property="systolic${pos}">
                        <img alt="systolicImg${row}.${pos}" id="systolicImgBD${row}.${pos}" src="/zeprs/images/arrow_up.gif">
                    </logic:equal>
                </div>
            </td>
            </logic:notEqual>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:forEach var="item" varStatus="loop"  begin="${startRow}" end="${stopRow}" >
            <c:set var="pos" value="${loop.index}"/>
            <td valign="top" onclick="insertDiastolic(${row}, ${loop.index},${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId} )" id="diastolicCell${row}.${loop.index}">&nbsp;
                <div id="diastolicCellDiv${row}.${pos}">
                    <c:if test="${! empty pulse}">
                        <logic:equal value="${row}" name="pulse" property="pulse${pos}">
                            <img alt="pulseImgBD${row}.${pos}" id="pulseImgBD${row}.${pos}" src="/zeprs/images/dot.gif">
                        </logic:equal>
                    </c:if>
                   <%-- <logic:equal value="${row}" name="bloodPressure" property="systolic${pos}">
                        <img alt="systolicImg${row}.${pos}" id="systolicImg${row}.${pos}" src="/zeprs/images/arrow_up.gif">
                    </logic:equal>--%>
                </div>
            </td>
        </c:forEach>
    </c:otherwise>
</c:choose>
</tr>
</c:forEach>

<script type='text/javascript'>

var replyDiastolic = function(data) {
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
    sitemImage.src = '/zeprs/images/arrow_down.gif';
    sitemImage.id = 'diastolicImg' + id;
    ditemImage = document.createElement('img');
    ditemImage.src = '/zeprs/images/arrow_red_down.gif';
    ditemImage.id = 'diastolicImgBS' + id;
    pitemImage = document.createElement('img');
    pitemImage.src = '/zeprs/images/arrow_down.gif';
    pitemImage.id = 'diastolicImgBP' + id;

    sitem.appendChild(sitemImage);
    ditem.appendChild(ditemImage);
    pitem.appendChild(pitemImage);
}
var replyDeleteDiastolic = function(data)
{
    var dvals = data.split("=");
    var row = trim(dvals[0]);
    var column =trim(dvals[1]);
    // String = new String(row);
    var systolic =  "systolicCellDiv" + row + "." + column;
    var diastolicId =  "diastolicCellDiv" + row + "." + column;
    var pulseId =  "pulseCellDiv" + row + "." + column;

    var systolicImg = "diastolicImgBS" + row + "." + column;
    var diastolicImg = "diastolicImg" + row + "." + column;
    var pulseImg = "diastolicImgBP" + row + "." + column;

    var space = document.createElement("&nbsp;");

    systolicdivObj = document.getElementById(systolic);
    diastolicDivObj = document.getElementById(diastolicId);
    pulseDivObj = document.getElementById(pulseId);

    systolicImgObject = document.getElementById(systolicImg);
    diastolicImgObject = document.getElementById(diastolicImg);
    pulseImgObject = document.getElementById(pulseImg);

    if (diastolicImgObject) {
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




