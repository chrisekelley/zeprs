<%--
  Created by IntelliJ IDEA.
  User: ckelley
  Date: Apr 11, 2005
  Time: 12:38:08 PM
--%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="now" class="java.util.Date" />

<logic:notEmpty name="form${encounterForm.id}" property="field1">
    <bean:define id="dateFromDb" name="form${encounterForm.id}" property="field1"/>
    <c:forTokens items="${dateFromDb}" delims="-" var="dateItem" varStatus="i" >
        <c:choose>
            <c:when test="${i.index==0}">
            <c:set var="yearnow" value="${dateItem}"/>
            </c:when>
            <c:when test="${i.index==1}">
            <c:set var="monthnow" value="${dateItem}"/>
            </c:when>
            <c:when test="${i.index==2}">
            <c:set var="datenow" value="${dateItem}"/>
            </c:when>
        </c:choose>
    </c:forTokens>
    <c:set var="theDate" value="${dateFromDb}"/>
    <c:set var="nicedateVisit" value="${datenow}/${monthnow}/${yearnow}"/>
    <c:set var="dbdateVisit" value="${theDate}"/>
</logic:notEmpty>
<logic:empty name="form${encounterForm.id}" property="field1">
    <fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow"/>
    <fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow"/>
    <fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow"/>
    <c:set var="theDate" value="${now}"/>
    <fmt:formatDate type="both" pattern="dd/MM/yyyy" value="${theDate}" var="nicedateVisit"/>
    <fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${theDate}" var="dbdateVisit"/>
</logic:empty>
<logic:notEmpty name="form${encounterForm.id}" property="field1962">
   <bean:define id="lmpDateFromDb" name="form${encounterForm.id}" property="field1962"/>
    <c:forTokens items="${lmpDateFromDb}" delims="-" var="lmpDateItem" varStatus="j" >
        <c:choose>
            <c:when test="${j.index==0}">
            <c:set var="lmpYearnow" value="${lmpDateItem}"/>
            </c:when>
            <c:when test="${j.index==1}">
            <c:set var="lmpMonthnow" value="${lmpDateItem}"/>
            </c:when>
            <c:when test="${j.index==2}">
            <c:set var="lmpDatenow" value="${lmpDateItem}"/>
            </c:when>
        </c:choose>
    </c:forTokens>
</logic:notEmpty>
<c:set var="dataEntry" value="1"/>
<c:choose>
    <c:when test="${(zeprs_session.sessionPatient.currentFlowId ==9) || (zeprs_session.sessionPatient.currentFlowId ==1) || (zeprs_session.sessionPatient.currentFlowId ==2)
                        || (zeprs_session.sessionPatient.currentFlowId ==7)}">
        <c:set var="dataEntry" value="1"/>
    </c:when>
    <c:when test="${zeprs_session.sessionPatient.currentPregnancyId == -1}">
        <c:set var="dataEntry" value="1"/>
    </c:when>
    <c:otherwise>
        <c:choose>
	         <c:when test="${empty zeprs_session.sessionPatient.datePregnancyEnd || empty zeprs_session.sessionPatient.currentEgaDaysDB}">
	         	<c:set var="dataEntry" value="1"/>
	         </c:when>
            <c:when test="${(encounterForm.flowId == 1) || (encounterForm.flowId == 2) || (encounterForm.flowId == 7)}">
                <c:set var="dataEntry" value="0"/>
            </c:when>
            <c:otherwise>
                <c:set var="dataEntry" value="1"/>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${! empty param.template}">
    <c:set var="template" value="/WEB-INF/templates/template-print.jsp"/>
    </c:when>
    <c:otherwise>
    <c:set var="template" value="/WEB-INF/templates/template.jsp"/>
    </c:otherwise>
</c:choose>
<template:insert template='${template}'>
<template:put name='title' content='Pregnancy Dating' direct='true'/>
<template:put name='content' direct='true'>
 <!--
            Pregnancy Dates Calculator
            February 28, 1999

            This calculator was created by Charles Hu for the Medical College of
            Wisconsin General Internal Medicine Clinic.  This calculator may not be
            copied without the consent from the author.  chuckhu@hotmail.com

            test
 -->

<script type='text/javascript' src='/zeprs/dwr/interface/GenericChart.js;jsessionid=${pageContext.request.session.id}'></script>
<script type='text/javascript' src='/zeprs/dwr/engine.js;jsessionid=${pageContext.request.session.id}'></script>
<script type='text/javascript' src='/zeprs/dwr/util.js;jsessionid=${pageContext.request.session.id}'></script>
<script type="text/javascript" src="/zeprs/js/dwr-generic.js;jsessionid=${pageContext.request.session.id}"></script>
<script type="text/javascript" language="Javascript1.1">

<!-- Begin

function submitForm() {

    if (eval(getLabel("days")).value == "") {
        alert("Please select Dating Method.")
    } else {
        bCancel=false;
        bCancel =  validateForm${encounterForm.id}(document.form${encounterForm.id});
        if (bCancel == true) {
            bCancel =  checkLmpValues();
            if (bCancel == true) {
            document.form${encounterForm.id}.submit();
             }
        }
    }
}

//End -->
</script>
<div id="widePage3">
<h2>${encounterForm.label}</h2>
<logic:messagesPresent>
   <ul>
   <html:messages id="error">
      <li class="valError"><bean:write name="error"/></li>
   </html:messages>
   </ul><hr>
 </logic:messagesPresent>
<c:if test="${! empty encounterForm}">
<html:form action="form${encounterForm.id}/save.do" onsubmit="return validateForm${encounterForm.id}(this);">
<c:if test="${empty chartItems}">
    <%-- Planned Pregnancy? --%>
    <p>
        <zeprs:yesno pageItem="${field135}"/><br/>
        <span id="field136"style="display:none"><%--Contraception Practiced? --%><zeprs:yesno pageItem="${field136}"/><br/></span>
        <span id="field137"style="display:none"><%--Contraceptive Choice: --%>Contraceptive Choice: <zeprs:select-only pageItem="${field137}"/><br/></span>
        <span id="field138"style="display:none"><%--Contraceptive Choice, Other: --%>Other: <zeprs:text-only pageItem="${field138}"/></span>
    </p>
</c:if>
<!-- old define tags-->

<table class="enhancedFormChart" cellspacing="0">
<tr>
    <th>Date of Visit</th>
<%--
    <th width="120px" >Planning/Contraception</th>
--%>
    <th width="200px">Method</th>
    <th width="40px">EGA</th>
    <th width="100px" >EDD</th>
    <th width="120px" >Dating Method by</th>
</tr>

<c:if test="${!empty chartItems}">
<c:forEach items="${chartItems}" var="encounter" varStatus="item">
<c:set var="currentEncounterId" value="${encounter.id}" scope="request"/>
<c:set var="pos" value="${encounter.id}"/>

<%@ include file="/WEB-INF/pages/encounters/preg_date_form_chart_dwr.jsp" %>

</c:forEach>
</c:if>

<c:if test="${dataEntry == 1}">
<c:set var="pos" value="0"/>
<tr>
    <td class="enhancedFormChartLeftBorderRed" rowspan="4">
    <span id="spanfield1" onclick="showCalendar('${yearnow}','${monthnow}','${datenow}','dd/MM/yyyy','form82','field1',event,2003,${yearnow});" class="dateDisplay">${nicedateVisit}</span>
    <div id="slcalcodfield1" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
    <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","field1");</script></div>
    <img alt="spacer" src="/zeprs/images/clearpixel.gif">
    <input type="hidden" name="field1" value="${yearnow}-${monthnow}-${datenow}" id="field1">
    </td>

    <td align="left" valign="top" class="lmp">
        <div id="lmpSelectChoice">
        Reliability of LMP:<br/>
        <html:select property="field126" styleId="selectfield126" onchange="checkReliability();"  >
            <html:option value="">--</html:option>
            <html:option value="67">Certain</html:option>
            <html:option value="599">Somewhat Certain</html:option>
            <html:option value="1097">Uncertain</html:option>
        </html:select>
        </div>
        <div id="lmpChoice">
        <p>LMP:<br/>
                <script language="JavaScript" type="text/javascript" src="/zeprs/js/pregnant.js;jsessionid=${pageContext.request.session.id}"></script>
                <input type=hidden name="lmpdate" id="lmpdate">
            <c:choose>
                <c:when test="${! empty lmpDateFromDb}">
                 <script language="JavaScript">
                <!--
                var ltoday = new Date('${lmpYearnow}/${lmpMonthnow}/${lmpDatenow}');
                var lmm = ltoday.getMonth();
                var ldd = ltoday.getDate();
                var lyy = ltoday.getYear();

                //  The '0' indicates this does NOT come from EDD Form
                 dateSelect(lmm,ldd,lyy,0,"lmpDbFieldlmp");
                -->
                </script>
                </c:when>
                <c:otherwise>
                 <script language="JavaScript">
                <!--
                var ltoday = new Date();
                var lmm = '99';
                var ldd = '99';
                var lyy = ltoday.getYear();
                 dateSelect(lmm,ldd,lyy,0,"lmpDbFieldlmp");
                -->
                </script>
                </c:otherwise>
            </c:choose>

        </p>
        </div>
    </td>
    <%-- EGA display --%>
    <td class="lmp">
    <span id="displayEGAlmp">&nbsp;</span>
    <html:hidden property="field1962" styleId="lmpDbFieldlmp"/>
    <input type="hidden" id="dayslmp">
    </td>
    <%-- EDD --%>
    <td class="lmp">
    <input type=hidden name="theDate" id="eddDate">
    <input type=hidden name="month1" id="month1">
    <input type=hidden name="date1" id="date1">
    <input type=hidden name="year1" id="year1">

    <span id="eddNicelmp">&nbsp;</span>
    <input type="hidden" id="eddFieldValuelmp">
    </td>
    <%-- Dating Method --%>
    <td rowspan="4" align="left" class="fcRight">
       <html:radio property="field1615" value="2805" styleId="lmpDatingMethod" onclick="checkReliability();copyPregDatingValues('lmpDbFieldlmp','dayslmp','eddFieldValuelmp','displayEGAlmp','eddNicelmp');">LMP</html:radio><br/>
       <html:radio property="field1615" value="2806" onclick="copyPregDatingValues('lmpDbFielduterus','daysuterus','eddFieldValueuterus','displayEGAuterus','eddNiceuterus');">Uterine size</html:radio><br/>
       <html:radio property="field1615" value="2807" onclick="copyPregDatingValues('lmpDbFieldultrasound','daysultrasound','eddFieldValueultrasound','displayEGAultrasound','eddNiceultrasound');">Ultrasound</html:radio>
    <p>
        EGA: <span id="displayEGAMaster">&nbsp;</span><br/>
        EDD: <span id="eddNiceMaster">&nbsp;</span>
    </p>
    <html:hidden styleId="lmpDbField" property="field127"/>
    <html:hidden styleId="days" property="field129"/>
    <html:hidden styleId="eddFieldValue" property="field128"/>
    </td>
</tr>

<tr class="highlightRowBorders">
    <td align="left" valign="top" class="uterus">
    <div id="uterineChoice">
        Uterus size in weeks: <br/>
        <html:select styleId="uterusDropdown" property="field188" onchange="calculateEDDfromEGA('uterusDropdown', 'lmpDbFielduterus', 'daysuterus', 'displayEGAuterus', 'eddFieldValueuterus', 'eddNiceuterus');">
            <option value=""> -- </option>
            <c:forEach begin="28" end="350" step="7" var="day">
                <c:set var="days" value="${day % 7}"/>
                <c:set var="weeks" value="${day/7}"/>
                <html:option value="${day}"><fmt:parseNumber value="${weeks}" integerOnly="true"/></html:option>
            </c:forEach>
        </html:select>
        </div>
    </td>
    <td class="uterus">
    <span id="displayEGAuterus">&nbsp;</span>
    <input type="hidden" id="lmpDbFielduterus">
    <input type="hidden" id="daysuterus">
    </td>
    <td class="uterus">
    <span id="eddNiceuterus">&nbsp;</span>
    <input type="hidden" id="eddFieldValueuterus">
    </td>
</tr>

<tr class="highlightRowBorders">
    <td align="left" valign="top" class="ultrasound">
        <div id="ultrasoundChoice">
            EGA from Ultrasound Results: <zeprs:ega_only pageItem="${field129}" egaToday ="${egaToday}"/>
        </div>
    </td>
    <td class="ultrasound">
    <span id="displayEGAultrasound">&nbsp;</span>
    <input type="hidden" id="lmpDbFieldultrasound">
    <input type="hidden" id="daysultrasound">
    </td>
    <td class="ultrasound">
    <span id="eddNiceultrasound">&nbsp;</span>
    <input type="hidden" id="eddFieldValueultrasound">
    </td>
</tr>
<tr class="highlightRowBorders">
    <td align="left" valign="top" class="quickening">
        <div id="quickeningChoice">
            Quickening: <input type="text" size="2" maxlength="2" name="field130" id="field130" autocomplete="off" value=""/> weeks
        </div>
        <td class="quickening" colspan="2">&nbsp;</td>
    </td>
</tr>
<c:if test="${! empty lmpDateFromDb}">
    <script language="JavaScript">
        <!--
        // In case of incorrect password, render previously-submitted lmp .
          whatDay(document.form82,0,'lmpDbFieldlmp');calculateEDD();
          var lmpDatingMethod = eval(getLabel("lmpDatingMethod")).checked;
            if (lmpDatingMethod == true) {
                checkReliability();copyPregDatingValues('lmpDbFieldlmp','dayslmp','eddFieldValuelmp','displayEGAlmp','eddNicelmp');
            }
        -->
    </script>

</c:if>
<tr>
    <td colspan="6" class="submitRow">
        <c:choose>
        <c:when test='${encounterForm.requireReauth}'>
            <zeprs:re_auth pageItem="${pageItem}"/>
            <input type="button" value="Submit" onclick="submitForm();"/>
        </c:when>
        <c:otherwise>
            <input type="button" value="Submit" onclick="submitForm();"/>
        </c:otherwise>
        </c:choose>
    </td>
</tr>
</c:if>
</table>
</html:form>
</div>
</c:if>

<c:import url="../problems/problems_chart.jsp" />
<html:javascript formName="form${encounterForm.id}" dynamicJavascript="true" staticJavascript="false"/>
</template:put>
</template:insert>