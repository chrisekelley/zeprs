<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<c:choose>
	<c:when test="${(! empty preview) && (! empty instaBean)}">
    <a href="javascript://" onclick="showCalendar('${yearnow}','5','10','dd/MM/yyyy','form${encounterForm.id}','field${field.id}',event, 2005,${yearnow});">
    <img alt="Select Date" border="0" src="/zeprs/images/calendar.gif" align="middle">&nbsp;&nbsp;</a><span id="spanfield${field.id}" class="dateDisplay" ></span> <html:hidden property="field${field.id}"  title="Select Date" value="${daynow}"  />
    <div id="slcalcodfield${field.id}" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
    <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","field${field.id}");</script></div>
    </c:when>
    <c:when test='${pageItem.inputType=="date_visit_dwr_label"}'>
        <c:choose>
        <c:when test="${! empty encounter}">
            <logic:notEmpty name="encounter" property="field${field.id}">
                <bean:define id="thisValue" name="encounter" property="encounterMap.field${field.id}" />
                <zeprs:date_visit_dwr_label pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
            </logic:notEmpty>
            <logic:empty name="encounter" property="field${field.id}">
                <zeprs:date_visit_dwr_label pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" formId="${pageItem.form.id}" siteId="${siteId}"/>
            </logic:empty>
        </c:when>
        <c:otherwise>
            <zeprs:date_visit_empty pageItem="${pageItem}"/>
        </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test='${param.id != null}'>
            <zeprs:date pageItem="${pageItem}" edit="true"/>
            </c:when>
            <c:otherwise>
            <zeprs:date pageItem="${pageItem}"/>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>