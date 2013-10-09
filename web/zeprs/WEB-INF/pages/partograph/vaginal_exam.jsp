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
<script type='text/javascript'>
    var currentStation = "";
</script>
<div id="partoStation">
<h2>Partograph: Vaginal Exam</h2>
<jsp:include page="toc.jsp"/>

<table border="1">
<tr>
<th>Time</th>
<th>Station of PP</th>
<th>Position</th>
<th>Cord</th>
<th>Vulva</th>
<th>Vagina</th>

</tr>
<c:forEach var="item" varStatus="loop" begin="1" end="6">
    <c:set var="pos" value="${loop.index}"/>
    <tr id="stationRow${pos}">
        <td id="timeObservationCell${pos}">
        <logic:notEmpty name="vaginalExam" property="timeObservation${pos}">
            <span id="timeObservationResults${pos}" onclick="toggle2('timeObservationSpan${pos}')"><bean:write name="vaginalExam" property="timeObservation${pos}"/></span>
            <br/>
            <span id="timeObservationSpan${pos}" style="display:none;">
            <html:text styleId="timeObservationField${pos}" size="7" maxlength="8" name="vaginalExam" property="timeObservation${pos}" title="Click to update Time" />
            <a href="#" onclick="copyTime('timeObservationField${pos}', '${fulldate}');">(Current Time)</a>
            <br/>
            <input type="button" name="_add" value="Done" onclick="insertVaginalExam(${pos},'timeObservationField${pos}', 'VaginalExamParto', 'timeObservation', replyTimeObservation, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId}); toggle2('timeObservationSpan${pos}')">
            </span>
        </logic:notEmpty>
        <logic:empty name="vaginalExam" property="timeObservation${pos}">
            <span id="timeObservationResults${pos}" onclick="toggle2('timeObservationSpan${pos}')"></span>
            <span id="timeObservationSpan${pos}" style="display:none;">
            <html:text styleId="timeObservationField${pos}" size="7" maxlength="8" name="vaginalExam" property="timeObservation${pos}" title="Click to update Time" />
            <a href="#" onclick="copyTime('timeObservationField${pos}', '${fulldate}');">(Current Time)</a>
            <input type="button" name="_add" value="Done" onclick="insertVaginalExam(${pos},'timeObservationField${pos}', 'VaginalExamParto', 'timeObservation', replyTimeObservation, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId}); toggle2('timeObservationSpan${pos}')">
            </span>
        </logic:empty>
        </td>
        <td id="stationCell${pos}" onclick="toggle2('stationField${pos}')">
        <logic:notEmpty name="vaginalExam" property="station${pos}">
            <span id="stationResults${pos}"><bean:write name="vaginalExam" property="station${pos}"/></span>
            <br/>
            <select name="station${pos}" id="stationField${pos}" onchange="insertVaginalExam(${pos},'stationField${pos}', 'VaginalExamParto', 'station', replyStation, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;">
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

            <script type='text/javascript'>
                currentStation = '<bean:write name="vaginalExam" property="station${pos}"/>';
            </script>
        </logic:notEmpty>
        <logic:empty name="vaginalExam" property="station${pos}">
            <span id="stationResults${pos}"></span>
            <select name="station${pos}" id="stationField${pos}" onchange="insertVaginalExam(${pos},'stationField${pos}', 'VaginalExamParto', 'station', replyStation, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})">
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
        </logic:empty>
        </td>
        <td id="positionCell${pos}" valign="top" onclick="toggle2('positionField${pos}')">
        <logic:notEmpty name="vaginalExam" property="position${pos}">
            <span id="positionResults${pos}"><bean:write name="vaginalExam" property="position${pos}"/></span>
            <br/>
            <select name="position${pos}" id="positionField${pos}"  onchange="insertVaginalExam(${pos}, 'positionField${pos}', 'VaginalExamParto', 'position', replyPosition, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;">
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
        </logic:notEmpty>
        <logic:empty name="vaginalExam" property="position${pos}">
            <span id="positionResults${pos}"></span>
            <select name="position${pos}" id="positionField${pos}" onchange="insertVaginalExam(${pos}, 'positionField${pos}', 'VaginalExamParto', 'position', replyPosition, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})">
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
        </logic:empty>
        </td>
        <td id="cordCell${pos}" valign="top" onclick="toggle2('cordField${pos}')">
        <logic:notEmpty name="vaginalExam" property="cord${pos}">
            <span id="cordResults${pos}"><bean:write name="vaginalExam" property="cord${pos}"/></span>
            <br/>
            <select name="cord${pos}" id="cordField${pos}"  onchange="insertVaginalExam(${pos}, 'cordField${pos}', 'VaginalExamParto', 'cord', replyCord, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;">
                <option value="">--</option>
                <option value="Not Palpable">Not Palpable</option>
                <option value="Palpable">Palpable</option>
                <option value="Prolapsed">Prolapsed</option>
            </select>

        </logic:notEmpty>
        <logic:empty name="vaginalExam" property="cord${pos}">
            <span id="cordResults${pos}"></span>
            <select name="cord${pos}" id="cordField${pos}"  onchange="insertVaginalExam(${pos}, 'cordField${pos}', 'VaginalExamParto', 'cord', replyCord, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})">
                <option value="">--</option>
                <option value="Not Palpable">Not Palpable</option>
                <option value="Palpable">Palpable</option>
                <option value="Prolapsed">Prolapsed</option>
            </select>
        </logic:empty>
        </td>
        <td id="vulvaCell${pos}" valign="top" onclick="toggle2('vulvaField${pos}')">
        <logic:notEmpty name="vaginalExam" property="vulva${pos}">
            <span id="vulvaResults${pos}"><bean:write name="vaginalExam" property="vulva${pos}"/></span>
            <br/>
            <select name="vulva${pos}" id="vulvaField${pos}"  onchange="insertVaginalExam(${pos}, 'vulvaField${pos}', 'VaginalExamParto', 'vulva', replyVulva, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;">
                <option value="">--</option>
                <option value="Normal">Normal</option>
                <option value="Warts">Warts</option>
                <option value="Ulcers">Ulcers</option>
                <option value="Edematous">Edematous</option>
            </select>
        </logic:notEmpty>
        <logic:empty name="vaginalExam" property="vulva${pos}">
            <span id="vulvaResults${pos}"></span>
            <select name="vulva${pos}" id="vulvaField${pos}"  onchange="insertVaginalExam(${pos}, 'vulvaField${pos}', 'VaginalExamParto', 'vulva', replyVulva, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})">
                <option value="">--</option>
                <option value="Normal">Normal</option>
                <option value="Warts">Warts</option>
                <option value="Ulcers">Ulcers</option>
                <option value="Edematous">Edematous</option>
            </select>
        </logic:empty>
        </td>
        <td id="vaginaCell${pos}" valign="top" onclick="toggle2('vaginaField${pos}')">
        <logic:notEmpty name="vaginalExam" property="vagina${pos}">
            <span id="vaginaResults${pos}"><bean:write name="vaginalExam" property="vagina${pos}"/></span>
            <br/>
            <select name="vagina${pos}" id="vaginaField${pos}"  onchange="insertVaginalExam(${pos}, 'vaginaField${pos}', 'VaginalExamParto', 'vagina', replyVagina, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none;">
                <option value="">--</option>
                <option value="Normal">Normal</option>
                <option value="Warm">Warm</option>
                <option value="Hot">Hot</option>
                <option value="Other">Other</option>
            </select>
        </logic:notEmpty>
        <logic:empty name="vaginalExam" property="vagina${pos}">
            <span id="vaginaResults${pos}"></span>
            <select name="vagina${pos}" id="vaginaField${pos}"  onchange="insertVaginalExam(${pos}, 'vaginaField${pos}', 'VaginalExamParto', 'vagina', replyVagina, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})">
                <option value="">--</option>
                <option value="Normal">Normal</option>
                <option value="Warm">Warm</option>
                <option value="Hot">Hot</option>
                <option value="Other">Other</option>
            </select>
        </logic:empty>
        </td>
    </tr>
</c:forEach>
</table>

<table>
    <tr>
        <td valign="top"><h2>Positions:</h2></td>
        <td valign="top">
            <table width="100px" cellspacing="1" cellpadding="4" border="1">
                <tr>
                    <td align="center"><strong>Left Occiput Anterior</strong><br/>
                    <img id =" pos6" src="/zeprs/images/position/circle45.gif" alt="Left Occiput Anterior"></td>
                    <td align="center" colspan="2"><strong>Occiput Anterior</strong><br/>
                    <img id =" pos5" src="/zeprs/images/position/circle.gif" alt="Occiput Anterior"></td>
                    <td align="center"><strong>Right Occiput Anterior</strong><br/>
                    <img id =" pos4" src="/zeprs/images/position/circle315.gif" alt="Right Occiput Anterior"></td>
                </tr>
                <tr>
                    <td align="center"><strong>Left Occiput Transverse</strong><br/>
                    <img id =" pos7" src="/zeprs/images/position/circle90.gif" alt="Left Occiput Transverse"></td>
                    <td align="center" colspan="2">&nbsp;</td>
                    <td align="center"><strong>Right Occiput Transverse</strong><br/>
                    <img id =" pos3" src="/zeprs/images/position/circle270.gif" alt="Right Occiput Transverse"></td>
                </tr>
                <tr>
                    <td align="center"><strong>Left Occiput Posterior</strong><br/>
                    <img id =" pos8" src="/zeprs/images/position/circle135.gif" alt="Left Occiput Posterior"></td>
                    <td align="center" colspan="2"><strong>Occiput Posterior</strong><br/>
                    <img id =" pos1" src="/zeprs/images/position/circle180.gif" alt="Occiput Posterior"></td>
                    <td align="center"><strong>Right Occiput Posterior</strong>r<br/>
                    <img id =" pos2" src="/zeprs/images/position/circle225.gif" alt="Right Occiput Posterior"></td>
                </tr>
            </table>
        </td>
    </tr>
</table>



<script type='text/javascript'>
     var currentCervix = "";
    <c:if test="${! empty cervix}">
        <c:forEach var="item" varStatus="loop"  begin="1" end="24">
            <c:set var="pos" value="${loop.index}"/>
            <logic:notEmpty name="cervix" property="cervix${pos}">
                currentCervix = '<bean:write name="cervix" property="cervix${pos}"/>';
            </logic:notEmpty>
        </c:forEach>
    </c:if>
    if (currentStation == "5/5" && currentCervix >=3) {
      alert("No descent of baby. Station: " + currentStation + " and Cervix: " + currentCervix + "; Refer to UTH.")
    }

    var replyTimeObservation = function(data)
    {
        var dvals = data.split("=");
        var key = "timeObservationResults" + dvals[1];
        var value = dvals[0];
        var element = document.getElementById(key);
        element.innerHTML = value;
    }

    var replyStation = function(data)
    {
        var dvals = data.split("=");
        var key = "stationResults" + dvals[1];
        var value = dvals[0];
        var element = document.getElementById(key);
        element.innerHTML = value;
        // update the time value
        var timeKey = "timeObservationResults" + dvals[1];
        var timeElement = document.getElementById(timeKey);
        var timeValue = dvals[2];
        timeElement.innerHTML = timeValue;
        currentStation = value;       
        if (currentStation == "5/5" && currentCervix >= 3) {
            alert("No descent of baby. Station: " + currentStation + " and Cervix: " + currentCervix + "; Refer to UTH.")
        }
    }

    var replyCord = function(data)
    {
        var dvals = data.split("=");
        var key = "cordResults" + dvals[1];
        var value = dvals[0];
        var element = document.getElementById(key);
        element.innerHTML = value;
        var timeElement = document.getElementById("timeObservationResults" + dvals[1]);
        var timeValue = dvals[2];
        timeElement.innerHTML = timeValue;
    }

    var replyPosition = function(data)
    {
        var dvals = data.split("=");
        var key = "positionResults" + dvals[1];
        var value = dvals[0];
        var element = document.getElementById(key);
        element.innerHTML = value;
        var timeElement = document.getElementById("timeObservationResults" + dvals[1]);
        var timeValue = dvals[2];
        timeElement.innerHTML = timeValue;
    }

    var replyVulva = function(data)
    {
        var dvals = data.split("=");
        var key = "vulvaResults" + dvals[1];
        var value = dvals[0];
        var element = document.getElementById(key);
        element.innerHTML = value;
        var timeElement = document.getElementById("timeObservationResults" + dvals[1]);
        var timeValue = dvals[2];
        timeElement.innerHTML = timeValue;
    }

    var replyVagina = function(data)
    {
        var dvals = data.split("=");
        var key = "vaginaResults" + dvals[1];
        var value = dvals[0];
        var element = document.getElementById(key);
        element.innerHTML = value;
        var timeElement = document.getElementById("timeObservationResults" + dvals[1]);
        var timeValue = dvals[2];
        timeElement.innerHTML = timeValue;
    }
</script>
</div>