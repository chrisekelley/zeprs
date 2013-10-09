<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="encounterForm" scope="request" type="org.cidrz.webapp.dynasite.valueobject.Form" />

<c:choose>
    <c:when test="${subject.id != null}">
        <h2>${encounterForm.label}
            <logic:present role="DELETE_ARCHIVE_PATIENT_RECORDS">
                <c:url var="delUrl" value="admin/deleteEncounter.do">
                    <c:param name="encounterId" value="${subject.id}"/>
                    <c:param name="formId" value="${encounterForm.id}"/>
                </c:url>
             &nbsp;(<a href='<c:out value="/zeprs/${delUrl}"/>'onclick="return confirm('Caution: Are you sure you want to delete this record from ZEPRS?');self.close();" title="Delete this record.">X</a>)
            </logic:present>
        </h2>
    </c:when>
    <c:otherwise>
        <h2>${encounterForm.label}</h2>
    </c:otherwise>
</c:choose>


<c:if test="${encounterForm.id == '87'}">
    <p><a href="#" onClick="toggle2('instructionsText')">Instructions (click to display)</a>
        <ul id="instructionsText" style="display:none">
            <li>If you are requesting a lab test, please fill out the top section, "Request a Lab Test," enter your password, and Submit.</li>
            <li>Enter the results in the "Records" table below by double-clicking on the value. </li>
            <li>RPR tests results are entered on the RPR page. After submitting a request,
                you can click on the RPR link in the Problem List to complete the results when ready.</li>
        </ul>
    </p>
</c:if>
<c:if test="${encounterForm.id == '90'}">
    <p>Instructions:
        <ul>
            <li>Submit a request for an RPR Test from the <html:link action="labs.do">Labs</html:link> page.</li>
            <li>Enter the results in the "Records" table below by double-clicking on the value. </li>
        </ul>
    </p>
</c:if>
<c:if test="${encounterForm.id == '1' && subject.id != null}">
    <p>
        <c:choose>
            <c:when test="${!empty zeprs_session.sessionPatient && empty zeprs_session.sessionPatient.parentId}"><html:link
                    href="/zeprs/antenatalCard.do;jsessionid=${pageContext.request.session.id}?patientId=${patientId}">Antenatal Card</html:link> |
                    <html:link href="/zeprs/postnatalCard.do;jsessionid=${pageContext.request.session.id}?patientId=${patientId}">Postnatal Card</html:link> |
                    <html:link href="/zeprs/safeMotherhoodClinicCard.do;jsessionid=${pageContext.request.session.id}?patientId=${patientId}">Safe Motherhood Clinic Card</html:link>
                    </c:when>
            <c:when test="${!empty zeprs_session.sessionPatient && !empty zeprs_session.sessionPatient.parentId}"><html:link
                    href="/zeprs/childrensClinicCard.do;jsessionid=${pageContext.request.session.id}?patientId=${patientId}&parentId=${zeprs_session.sessionPatient.parentId}">
                Children's Clinic Card</html:link> |</c:when>
        </c:choose>
        <!-- <html:link href="/zeprs/generatePatientReport.do;jsessionid=${pageContext.request.session.id}?patientId=${patientId}"
                   onclick="alert('This is a demo on how to export a patient record. This link exports the record to XML. Next step would be to save to data to a smart card.')">
            Export Record</html:link>
            -->
        <logic:present role="DELETE_ARCHIVE_PATIENT_RECORDS"> |
            <html:link action="admin/deletePatient.do" paramId="patientId" paramName="patientId"
                       onclick="return confirm('Caution: Are you sure you want to delete this patient from ZEPRS?');self.close();">
                Delete Patient</html:link>
        </logic:present>
        <logic:present role="DELETE_ARCHIVE_PATIENT_RECORDS"> |
            <html:link action="admin/resetFlow.do" onclick="return confirm('Caution: Are you sure you want to reset patient status to Antenatal?');self.close();">
                Reset to Antenatal Status</html:link>
        </logic:present></p>
</c:if>

<c:if test="${! empty param.referralId}">
    <c:import url="admit_to_uth.jsp"/>
</c:if>
<c:if test='${subject.id != null && empty preview}'>
<script language="JavaScript" type="text/javascript" src="/zeprs/dwr/util.js;jsessionid=${pageContext.request.session.id}"></script>
<script language="JavaScript" type="text/javascript" src="/zeprs/js/engine2.jsp;jsessionid=${pageContext.request.session.id}"></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwr/interface/WidgetDisplay.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwr/interface/Encounter.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type="text/javascript" src="/zeprs/js/dwr-generic.js;jsessionid=${pageContext.request.session.id}"></script>
<script language="JavaScript" type="text/javascript" src="/zeprs/js/dwr-display.js;jsessionid=${pageContext.request.session.id}"></script>
<c:if test="${encounterForm.id == 1}">
<script type='text/javascript' src='/zeprs/dwr/interface/PatientId.js;jsessionid=${pageContext.request.session.id}'></script>
<script type='text/javascript'>
var reply1 = function(data)
{
  document.getElementById('d1').innerHTML = data;
    var dataSt = new String(data);
    if (dataSt.substring(0,5) == "Error") {

    } else {
       updateRecord('patientid', 1513 ,3158 ,1, ${subject.id} , 'Form');
    }
}
var reply2 = function(data)
{
    if (data.substring(0,4) == "Error") {
        alert(data);
    } else {
      document.getElementById('patientid').value = data;
      var sumData = new Number(data.substring(0,1)) + new Number(data.substring(1,2)) + new Number(data.substring(2,3)) + new Number(data.substring(3,4)) + new Number(data.substring(4,5));
      ck = calcMod(sumData,10);
      var checksum = ck;
      document.getElementById('checksum').value = checksum;
      calcPatientId();
      patientidField = document.getElementById('patientIdRow');
      patientidField.style.display = "none";
      updateRecord('patientid', 1513 ,3158 ,1, ${subject.id} , 'Form');
    }
}
function copyPatientId() {
    var patientid = document.getElementById('patientid');
    var patient = document.getElementById('patient');
    patient.value = patientid.value;
}
</script>
</c:if>
<table border="0" cellpadding="4" cellspacing="2" width="95%"  id="recordMetaData" summary="4 col table" class="formTable">
    <tr class="sectionHeader">
        <th class="encounterlabel">Date of visit:</th>
        <th class="encounterlabel">Record Created:</th>
        <th class="encounterlabel">Record Modified</th>
        <th class="encounterlabel">Site:</th>
    </tr>
    <jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow" />
<fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow" />
<fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow" />
<c:set var="theDate" value="${now}"/>
    <tr>
        <td id="celldateVisit" ondblclick="callMetaDataWidget('dateVisit', '${subject.id}')">
        <span id="valuedateVisit" class="renderedValue">
        <fmt:formatDate value="${subject.dateVisit}"  pattern="dd MMM yyyy"/></span>
        <div id="slcalcodfield1" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
<script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","field1");</script>
</div>
        <span id="widgetdateVisit"></span>
        </td>
        <td><fmt:formatDate value="${subject.created}"  pattern="dd MMM yyyy  HH:mm"/>
        <br/>${subject.createdByName}</td>
        <td><fmt:formatDate value="${subject.lastModified}" pattern="dd MMM yyyy  HH:mm"/>
        <br/>${subject.lastModifiedByName}</td>
        <td id="cellSiteId" ondblclick="callMetaDataWidget('SiteId', '${subject.id}')">
        <span id="valueSiteId" class="renderedValue">${subject.siteName}</span>
        <span id="widgetSiteId"></span>
        </td>
    </tr>
</table>
</c:if>

<c:if test='${empty preview && encounterForm.id == 92}'>

    <table border="0" cellpadding="4" cellspacing="2" width="95%" id="recordMetaData" summary="1 col table"
           class="formTable">
        <tr class="sectionHeader">
            <th class="encounterlabel">Status</th>
        </tr>

        <tr>
            <c:choose>
                <c:when test="${! empty zeprs_session.sessionPatient.hivPositive && zeprs_session.sessionPatient.hivPositive == 0}">
                    <td>Non-reactive</td>
                </c:when>
                <c:when test="${! empty zeprs_session.sessionPatient.hivPositive && zeprs_session.sessionPatient.hivPositive == 1}">
                    <td><span class="error">Reactive</span></td>
                </c:when>
                <c:when test="${! empty zeprs_session.sessionPatient.hivPositive && zeprs_session.sessionPatient.hivPositive == 2}">
                    <td><span class="error">Indeterminate</span></td>
                </c:when>
               <c:otherwise><td><span class="error">Not Tested</span></td></c:otherwise>
            </c:choose>
        </tr>
    </table>
</c:if>

<logic:messagesPresent>
   <bean:message key="errors.header"/>
   <html:messages id="error">
      <li class="valError"><bean:write name="error"/></li>
   </html:messages>
   <bean:message key="errors.footer"/>
</logic:messagesPresent>


<%--<c:set var="url" value="saveCorrection"/>--%>
<c:set var="url" value="save"/>
<c:choose>
    <c:when test="${encounterForm.id=='91'}">
       <c:set var="submitJs" value="return confirmChartDate('field1882', ${encounterForm.id});"/>
       <c:set var="actionValue" value="form${encounterForm.id}/${url}.do"/>
    </c:when>
    <c:when test="${encounterForm.id=='89'}">
       <c:set var="submitJs" value="return confirmChartDate('field1881', ${encounterForm.id});"/>
        <c:set var="actionValue" value="form${encounterForm.id}/${url}.do"/>
    </c:when>
    <c:when test="${encounterForm.id=='90'}">
       <c:set var="submitJs" value="return confirmChartDate('field1562', ${encounterForm.id});"/>
        <c:set var="actionValue" value="form${encounterForm.id}/${url}.do"/>
    </c:when>
    <c:when test="${encounterForm.id=='66'}">
        <c:set var="submitJs" value="return checkNewbornSubmitted('Please enter all of the fields in the Newborn Delivery section.');"/>
    </c:when>
    <c:otherwise>
       <c:set var="submitJs" value="return validateForm${encounterForm.id}(this);"/>
    </c:otherwise>
</c:choose>
<c:choose>
<c:when test="${(! empty preview) && (! empty instaBean)}">
      <c:set var="actionValue" value="/admin/form/preview.do"/>
</c:when>
    <c:otherwise>
       <c:set var="actionValue" value="form${encounterForm.id}/${url}.do"/>
    </c:otherwise>
</c:choose>
<html:form action="${actionValue}" onsubmit="${submitJs}" styleId="form${encounterForm.id}">
<c:if test="${! empty next}">
    <input type="hidden" name="next" value="${next}"/>
</c:if>
<c:if test="${! empty referralId}">
    <input type="hidden" name="referralId" value="${referralId}"/>
</c:if>

<c:choose>
<c:when test="${(encounterForm.id == '55') && (subject.id == null)}">
<%-- form 55, current medicine --%>
<%--<c:if test='${subject.id == null}'>--%>
<zeprs:medicine_form form="<%=encounterForm%>"/>
<%--</c:if>--%>
</c:when>
<c:otherwise>
<c:set var="trFields"/>
<c:set var="tblItem" value="0"/>
<c:choose>
    <%--<c:when test='${param.id != null}'>--%>
    <c:when test="${(! empty preview) && (! empty instaBean)}">
    <a href="javascript://" onclick="showCalendar('${yearnow}','5','10','dd/MM/yyyy','form${encounterForm.id}','field${field.id}',event, 2005,${yearnow});">
    <img alt="Select Date" border="0" src="/zeprs/images/calendar.gif" align="middle">&nbsp;&nbsp;</a><span id="spanfield${field.id}" class="dateDisplay" ></span> <html:hidden property="field${field.id}"  title="Select Date" value="${daynow}"  />
    <div id="slcalcodfield${field.id}" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
    <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","field${field.id}");</script></div>
    </c:when>
    <c:when test='${! empty subject}'>
        <input type="hidden" name="id" value="${subject.id}"/>
    </c:when>
    <c:when test="${encounterForm.id == '1'}">
        <zeprs:date_visit_initial/>
    </c:when>
    <c:when test="${encounterForm.id == 63 || encounterForm.id == 74 || encounterForm.id == 4 || (encounterForm.id >= 87 && encounterForm.id <= 91)
     || (encounterForm.id >= 101 && encounterForm.id <= 104)}">
    <zeprs:date_visit_hidden dateVisit="${dateVisit}"/>
    </c:when>
    <c:otherwise>
        <zeprs:date_visit/>
    </c:otherwise>
</c:choose>
<c:set var="tblItem" value="0"/>
<c:set var="tblCols" value="0"/>
<c:set var="tdBackgroundColor" value="#fff"/>
<c:set var="collapsing" value="0"/>

<c:if test="${encounterForm.id == 44}">
    <p><strong>Instructions:</strong> This form has two sections. The Fetus Evaluation section collects data about each fetus.
        Please enter the requested information, press "Enter", and view the results for each fetus as it appears below this section.
        Next, continue to the Ultrasound Evaluation section below and submit it when you are finished..</p>
<table border="0" cellpadding="4" cellspacing="2" width="95%"  id="tbl1334" summary="1 col table" class="enhancedtableMargin2">
    <tr class="sectionHeader">
        <td colspan="1">
        Fetus Evaluation
        </td>
    </tr>
</table>
    <c:choose>
        <c:when test="${subject.id == null}">
            <zeprs:fetus patientId = "${patientId}" user = "${user}" siteId = "${siteId}" pregnancyId = "${pregnancyId}" seqFetus = "${seqFetus}"/>
        </c:when>
        <c:otherwise>
            <table width="60%" id="tbl99999999a" class="enhancedtableMargin">
                <tbody>
                <tr>
                    <th>#</th>
                    <th>Condition</th>
                    <th>Lie</th>
                    <th>Presentation</th>
                    <th>BPD</th>
                    <th>Femur</th>
                    <th>Abdom.</th>
                </tr>
                    <c:if test="${! empty fetusMapList}">
                        <logic:iterate id="fetus" name="fetusMapList">
                        <tr>
                            <td><bean:write name="fetus" property="field1915" ignore="true"/></td>
                            <td><bean:write name="fetus" property="field964" ignore="true"/></td>
                            <td><bean:write name="fetus" property="field313" ignore="true"/></td>
                            <td><bean:write name="fetus" property="field314" ignore="true"/>
                            <c:if test="${! empty fetus.field1508}"><br/><bean:write name="fetus" property="field1508" ignore="true"/></c:if></td>
                            <td><bean:write name="fetus" property="field955" ignore="true"/></td>
                            <td><bean:write name="fetus" property="field956" ignore="true"/></td>
                            <td><bean:write name="fetus" property="field957" ignore="true"/></td>
                        </tr>
                        </logic:iterate>
                    </c:if>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</c:if>
<c:if test="${encounterForm.id == 66}">

<table border="0" cellpadding="4" cellspacing="2" width="95%"  id="tbl1334" summary="1 col table" class="formTable">
    <tr class="sectionHeader">
        <td colspan="1">
        <a href="#" onclick="toggleField('link',1, '99999999','Newborn');" title="Click here to add a newborn"><img border="0" src="/zeprs/images/minus.gif" id="plusminusNewborn">&nbsp;Newborn Delivery Date, Time, and Sex</a>
        </td>
    </tr>
</table>
    <c:choose>
        <c:when test="${subject.id == null}">
            <zeprs:newborn patientId = "${patientId}" user = "${user}" siteId = "${siteId}" pregnancyId = "${pregnancyId}" seqChildren = "${seqChildren}"/>
        </c:when>
        <c:otherwise>
            <zeprs:newborn patientId = "${patientId}" user = "${user}" siteId = "${siteId}" pregnancyId = "${pregnancyId}" seqChildren = "${seqChildren}"/>
        </c:otherwise>
    </c:choose>
</c:if>

<c:choose>
    <c:when test="${encounterForm.id == 90}">
    </c:when>
    <c:when test="${(encounterForm.id >=101 && encounterForm.id <=104) && param.extendedTestId !=0}">
    </c:when>
    <c:otherwise>
        <jsp:include page="encounter_form_fields.jsp"/>
        <c:choose>
            <c:when test="${empty subject}">
                    <div style="padding:5px;">
                    <c:choose>
                        <c:when test='${encounterForm.requireReauth}'>
                            <zeprs:re_auth pageItem="${pageItem}"/>
                            <html:submit onclick="bCancel=false;" />
                        </c:when>
                        <c:otherwise>
                            <html:submit onclick="bCancel=false;" />
                        </c:otherwise>
                    </c:choose>
                    </div>

            </c:when>
            <c:otherwise>
                <c:if test="${encounterForm.id == '4'}">
                <c:url value="patientAnte.do" var="patientAnte"><c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/></c:url>
                <div style="padding:5px;"><input type="button" value="Back to Antepartum Chart" onclick="window.location.href='/zeprs/${patientAnte}';"/></div>
                </c:if>
            </c:otherwise>
        </c:choose>
        <c:if test='${links != null}'>Links:
            <ul>
                <li>${links.labs}</li>
            </ul>
        </c:if>
        <c:choose>
            <c:when test="${(! empty preview) && (! empty instaBean)}">
            </c:when>
            <c:otherwise>
                <html:javascript formName="form${encounterForm.id}" dynamicJavascript="true" staticJavascript="false"/>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
</c:otherwise>
</c:choose>
</html:form>

<c:if test='${subject.id != null}'>
    <c:if test="${encounterForm.id ==23 && empty zeprs_session.sessionPatient.datePregnancyEnd}">
        <html:form action="forward.do">
        <input type="hidden" name="forward" value="completed"/>
        <input type="hidden" name="id" value="${subject.id}"/>
        <input type="hidden" name="formId" value="${encounterForm.id}"/>
        &nbsp;&nbsp;<input type="submit" value="Completed"/>
        </html:form>
    </c:if>
</c:if>

