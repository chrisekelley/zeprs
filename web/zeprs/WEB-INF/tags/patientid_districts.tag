<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
<jsp:useBean id="districts" scope="request" type="java.util.List" />
<script type='text/javascript' src='/zeprs/dwr/util.js;jsessionid=${pageContext.request.session.id}'></script>
<script type='text/javascript' src='/zeprs/js/engine2.jsp;jsessionid=${pageContext.request.session.id}'></script>
<script type='text/javascript' src='/zeprs/dwr/interface/PatientId.js;jsessionid=${pageContext.request.session.id}'></script>
<script>

if (window.addEventListener) {
  window.addEventListener("load", useLoadingMessage, false);
}
else if (window.attachEvent) {
  window.attachEvent("onload", useLoadingMessage);
}
else {
  window.onload = useLoadingMessage;
}
</script>
<table width="100%" border="1">
    <tr>
        <td valign="top">District:<br>
        <html:select property="field${field.id}" styleId="district" onchange="calcPatientId()">
        <html:option value="">-- Select --</html:option>
        <c:choose>
            <c:when test='${param.id != null}'>
                <c:forEach var="district" begin="0" items="${districts}">
                    <html:option value="${district.districtId}">${district.districtId}</html:option>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach var="district" begin="0" items="${districts}">
                    <c:choose>
                    <c:when test="${patientDistrictId==district.id}">
                    <option value="${district.districtId}" selected="selected">* ${district.districtId} *</option>
                    </c:when>
                    <c:otherwise>
                    <html:option value="${district.districtId}">${district.districtId}</html:option>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
                    <html:option value="1000">Other</html:option>
            </c:otherwise>
        </c:choose>
        </html:select></td>
        <td valign="top">Clinic:<br/>
        <%--<select name="field${field.id}" id="site" onchange="calcPatientId()">--%>
            <select id="site" onchange="calcPatientId();">
                <option>-- Select --</option>
            <c:forEach var="site" begin="0" items="${sites}">
                <c:if test="${site.inactive != 1}">
                    <c:choose>
                        <c:when test="${patientSiteId==site.id}">
                            <option value="${fn:substring(site.siteAlphaId, 0, 2)}" selected="selected">
                                * ${fn:substring(site.siteAlphaId, 0, 2)} - ${fn:substring(site.name,0,13)}* </option>
                        </c:when>
                        <c:otherwise>
                            <option value="${fn:substring(site.siteAlphaId, 0, 2)}">${fn:substring(site.siteAlphaId, 0, 2)}
                                - ${fn:substring(site.name,0,13)}</option>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </c:forEach>
            </select>
            <select id="subsite" onchange="calcPatientId();">
                <option> -- </option>
                <c:choose>
                    <c:when test="${siteTypeId == 2}">
                        <c:forEach var="subsite" items="${uthSubsites}">
                            <c:choose>
                                <c:when test="${clinicId==subsite}">
                                    <option value="${subsite}" selected="selected">* ${subsite} *</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${subsite}">${subsite}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="subsite" begin="0" end="9">
                            <c:choose>
                                <c:when test="${clinicId==subsite}">
                                    <option value="${subsite}" selected="selected">* ${subsite} *</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${subsite}">${subsite}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </select>
        </td>
        <td align="center">
            <c:choose>
                <c:when test='${param.id != null}'>
                    <html:text size="${pageItem.size}" maxlength="16" property="field${field.id}"/>
                </c:when>
                <c:otherwise>
                <input class='ibutton' type='button' onclick='setPatientID("patient","Please select District and clinic fields.","patientid");' value='Get New ID' title='Get New ID'/>
                <%--<html:hidden styleId="patient" property="field${field.id}" />--%>
                </c:otherwise>
            </c:choose></td>
    </tr>
    <tr>
        <td colspan="2" align="right">ZEPRS ID: <span class="asterix">*</span></td><td align="center"><span id="spanpatient"></span></td>
    </tr>
    <tr id="patientIdRow">
        <td colspan="2">If patient already has an ID please enter the last five digits before the final check digit:</td>
        <td align="left">
            <span id="patientIdFields">
                <input id="patientid" type="text" name="patientid" size="4" maxlength="5" onchange="calcPatientId();checkPatientID('patient','Please select/enter all of the Patient ID fields. If you are entering the patient id by hand, make sure it has 5 digits.','patientid', 'manual');">
                <input id="checksum" type="hidden" name="checksum" onchange="calcPatientId()">
                <input id="checkPatientId" class='ibutton' type='button' onclick='checkPatientID("patient","Please select/enter all of the Patient ID fields.","patientid");' value='Check ID' title='Check ID'/>
            </span>
       </td>
    </tr>

    <tr>
        <td colspan="3"><span id='d1' class='reply'></span></td>
    </tr>
</table>
<script type='text/javascript'>
var reply1 = function(data)
{
  if (data.substring(0,5) == "Error") {
        alert(data);
        document.getElementById('spanpatient').innerHTML = "";
        document.getElementById('patient').value = "";
        document.getElementById('patientid').value = "";
        document.getElementById('checksum').value = "";
        document.getElementById('d1').innerHTML = data;
    } else {
    	document.getElementById('d1').innerHTML = data;
    }
}
var reply2 = function(data)
{
    if (data.substring(0,5) == "Error") {
        alert(data);
        document.getElementById('spanpatient').innerHTML = "";
        document.getElementById('patient').value = "";
        document.getElementById('patientid').value = "";
        document.getElementById('checksum').value = "";
        document.getElementById('d1').innerHTML = data;
    } else {
      document.getElementById('patientid').value = data;
      var sumData = new Number(data.substring(0,1)) + new Number(data.substring(1,2)) + new Number(data.substring(2,3)) + new Number(data.substring(3,4)) + new Number(data.substring(4,5));
      ck = calcMod(sumData,10);
      var checksum = ck;
      document.getElementById('checksum').value = checksum;
      calcPatientId();
      patientidField = document.getElementById('patientIdRow');
      patientidField.style.display = "none";
      document.getElementById('d1').innerHTML = "New ZEPRS ID: " + document.getElementById('patient').value;
    }
}
</script>