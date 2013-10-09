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

<c:url value="partograph.do" var="part3"><c:param name="part" value="3"/></c:url>
<c:url value="partograph.do" var="close"><c:param name="part" value="close"/></c:url>
<c:url value="partograph.do" var="all"><c:param name="part" value="all"/></c:url>

<div id="partoStation" style="display:none; border:none">
<h2>Partograph: Vaginal Exam</h2>
<jsp:include page="toc.jsp"/>
<table border="0">
    <c:set var="pos" value=""/>
    <tr id="stationRow0">
        <td id="stationCell${pos}" valign="top">
            <div id="property${pos}">Station of PP:<br/>
            <select name="station${pos}" id="stationField${pos}">
                <option value="">--</option>
                <option value="-5/5">-5/5</option>
                <option value="-4/5">-4/5</option>
                <option value="-3/5">-3/5</option>
                <option value="-2/5">-2/5</option>
                <option value="-1/5">-1/5</option>
                <option value="0/5">0/5</option>
                <option value="1/5">1/5</option>
                <option value="2/5">2/5</option>
                <option value="3/5">3/5</option>
                <option value="4/5">4/5</option>
                <option value="5/5">5/5</option>
            </select>
            </div>
        </td>
        <td id="cordCell${pos}" valign="top">
            <div id="cord${pos}">Cord:<br/>
            <select name="cord${pos}" id="cordField${pos}">
                <option value="">--</option>
                <option value="Not Palpable">Not Palpable</option>
                <option value="Palpable">Palpable</option>
                <option value="Prolapsed">Prolapsed</option>
            </select>
            </div>
        </td>
     </tr>
     <tr>
        <td colspan="2" id="positionCell${pos}">
            <div id="property${pos}">
            <table width="200" cellspacing="1" cellpadding="4" border="1">
                 <tr>
                    <td colspan="4" align="center">
                    <input type="text" name="position${pos}" value="" onfocus="this.blur()" class="disabled" id="positionField${pos}"></td>
                </tr>
                <tr>
                    <td align="center"><strong>Occiput Anterior</strong><br/>
                    <img id =" pos5" src="/zeprs/images/position/circle180.gif" alt="Position 5" onclick="document.getElementById('positionField${pos}').value='Occiput Anterior';highlightImg('pos5');"></td>
                    <td align="center"><strong>Left Occiput Anterior</strong><br/>
                    <img id =" pos6" src="/zeprs/images/position/circle225.gif" alt="Position 6" onclick="document.getElementById('positionField${pos}').value='Left Occiput Anterior';highlightImg('pos6');"></td>
                    <td align="center"><strong>Right Occiput Anterior</strong><br/>
                    <img id =" pos4" src="/zeprs/images/position/circle135.gif" alt="Position 4" onclick="document.getElementById('positionField${pos}').value='Right Occiput Anterior';highlightImg('pos4');"></td>
                    <td align="center"><strong>Left Occiput Transverse</strong><br/>
                    <img id =" pos7" src="/zeprs/images/position/circle270.gif" alt="Position 7" onclick="document.getElementById('positionField${pos}').value='Left Occiput Transverse';highlightImg('pos7');"></td>
                </tr>
                <tr>
                    <td align="center"><strong>Right Occiput Transverse</strong><br/>
                    <img id =" pos3" src="/zeprs/images/position/circle90.gif" alt="Position 3" onclick="document.getElementById('positionField${pos}').value='Right Occiput Transverse';highlightImg('pos3');"></td>
                    <td align="center"><strong>Left Occiput Posterior</strong><br/>
                    <img id =" pos8" src="/zeprs/images/position/circle315.gif" alt="Position 8" onclick="document.getElementById('positionField${pos}').value='Left Occiput Posterior';highlightImg('pos8');"></td>
                    <td align="center"><strong>Occiput Posterior</strong><br/>
                    <img id =" pos1" src="/zeprs/images/position/circle.gif" alt="Occiput Posterior" onclick="document.getElementById('positionField${pos}').value='Occiput Posterior';highlightImg('pos1');"></td>
                    <td align="center"><strong>Right Occiput Posterior</strong>r<br/>
                    <img id =" pos2" src="/zeprs/images/position/circle45.gif" alt="Right Occiput Posterior" onclick="document.getElementById('positionField${pos}').value='Right Occiput Posterior';highlightImg('pos2');"></td>
                </tr>
            </table>
            </div>
        </td>
      </tr>
      <tr>
        <td colspan="2" id="cordCell${pos}" valign="bottom" align="right">
            <div id="cord${pos}"><input class='ibutton' type='button' title="Save" value="Save" onclick="insertStation(${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})">
            </div>
        </td>
     </tr>
</table>

<div id="partoStationResults">
<h2>Results</h2>
<c:if test="${! empty station}">
<table border="1">
<c:forEach var="item" varStatus="loop" begin="4" end="13">
    <c:set var="pos" value="${loop.index-3}"/>
    <tr id="stationRow${pos}">
        <td id="stationCell${pos}" onclick="toggle2('stationField${pos}')">
        <logic:notEmpty name="station" property="station${pos}">
            <div id="property${pos}"><bean:write name="station" property="station${pos}"/>
            <select name="station${pos}" id="stationField${pos}" onchange="insertStation(${pos},'stationField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none; border:none">
                <option value="">--</option>
                <option value="-5/5">-5/5</option>
                <option value="-4/5">-4/5</option>
                <option value="-3/5">-3/5</option>
                <option value="-2/5">-2/5</option>
                <option value="-1/5">-1/5</option>
                <option value="0/5">0/5</option>
                <option value="1/5">1/5</option>
                <option value="2/5">2/5</option>
                <option value="3/5">3/5</option>
                <option value="4/5">4/5</option>
                <option value="5/5">5/5</option>
            </select>
            </div>
        </logic:notEmpty>
        <logic:empty name="station" property="station${pos}">
            <div id="property${pos}">&nbsp;&nbsp;&nbsp;
            <select name="station${pos}" id="stationField${pos}" onchange="insertStation(${pos}, 'stationField${pos}', ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none; border:none">
                <option value="">--</option>
                <option value="-5/5">-5/5</option>
                <option value="-4/5">-4/5</option>
                <option value="-3/5">-3/5</option>
                <option value="-2/5">-2/5</option>
                <option value="-1/5">-1/5</option>
                <option value="0/5">0/5</option>
                <option value="1/5">1/5</option>
                <option value="2/5">2/5</option>
                <option value="3/5">3/5</option>
                <option value="4/5">4/5</option>
                <option value="5/5">5/5</option>
            </select>
            </div>
        </logic:empty>
        </td>
        <td id="cordCell${pos}" valign="top" onclick="toggle2('cordField${pos}')">
        <logic:notEmpty name="station" property="cord${pos}">
            <div id="cord${pos}"><bean:write name="station" property="cord${pos}"/>
            <select name="cord${pos}" id="cordField${pos}">
                <option value="">--</option>
                <option value="Not Palpable">Not Palpable</option>
                <option value="Palpable">Palpable</option>
                <option value="Prolapsed">Prolapsed</option>
            </select>
            </div>
        </logic:notEmpty>
        <logic:empty name="station" property="cord${pos}">
            <div id="cord${pos}">
            <select name="cord${pos}" id="cordField${pos}">
                <option value="">--</option>
                <option value="Not Palpable">Not Palpable</option>
                <option value="Palpable">Palpable</option>
                <option value="Prolapsed">Prolapsed</option>
            </select>
            </div>
        </logic:empty>
        </td>
        <td id="positionCell${pos}" valign="top" onclick="toggle2('positionField${pos}')">
        <logic:notEmpty name="station" property="position${pos}">
            <div id="position${pos}"><bean:write name="station" property="position${pos}"/>
            <select name="position${pos}" id="positionField${pos}">
                <option value="">--</option>
                <option value="Occiput Anterior">Occiput Anterior</option>
                <option value="Left Occiput Anterior">Left Occiput Anterior</option>
                <option value="Right Occiput Anterior">Right Occiput Anterior</option>
                <option value="Left Occiput Transverse">Left Occiput Transverse</option>
                <option value="Right Occiput Transverse">Right Occiput Transverse</option>
                <option value="Left Occiput Posterior">Left Occiput Posterior</option>
                <option value="Occiput Posterior">Occiput Posterior</option>
                <option value="Right Occiput Posteriorr">Right Occiput Posteriorr</option>
            </select>
            </div>
        </logic:notEmpty>
        <logic:empty name="station" property="cord${pos}">
            <div id="position${pos}">
            <select name="position${pos}" id="positionField${pos}">
                <option value="">--</option>
                <option value="Occiput Anterior">Occiput Anterior</option>
                <option value="Left Occiput Anterior">Left Occiput Anterior</option>
                <option value="Right Occiput Anterior">Right Occiput Anterior</option>
                <option value="Left Occiput Transverse">Left Occiput Transverse</option>
                <option value="Right Occiput Transverse">Right Occiput Transverse</option>
                <option value="Left Occiput Posterior">Left Occiput Posterior</option>
                <option value="Occiput Posterior">Occiput Posterior</option>
                <option value="Right Occiput Posteriorr">Right Occiput Posteriorr</option>
            </select>
            </div>
        </logic:empty>
        </td>
    </tr>
</c:forEach>
</table>
</c:if>
</div>
<script type='text/javascript'>
    var replyStation = function(data)
    {
    var dvals = data.split("=");
    var key = "stationCell" + dvals[1];
    var value =dvals[0];
    var stationValue = document.getElementById(key);
    stationValue.innerHTML = value;
    }
  </script>




