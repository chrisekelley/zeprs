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

<tr>
    <td class="enhancedFormChartLeftBorderBK">
    <logic:notEmpty name="encounter" property="dateVisit">
        <bean:define id="thisValue" name="encounter" property="dateVisit" />
        <zeprs:date_visit_dwr pageItem="${dateVisit}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="dateVisit" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" value="${thisValue}"/>
        ${encounter.siteName}, ${encounter.lastModifiedByName}
    </logic:notEmpty>
    <%--<logic:empty name="encounter" property="dateVisit">
        <zeprs:date_visit_dwr pageItem="${dateVisit}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="dateVisit" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}"/>
    </logic:empty>--%>
    </td>


    <td class="enhancedFormChartCellBorder">
        <%--<logic:notEmpty name="encounter" property="encounterMap.field1615">
            <bean:define id="methodCheck" name="encounter" property="encounterMap.field1615"/>
        </logic:notEmpty>--%>
        <logic:notEmpty name="encounter" property="encounterMap.field126">
            Reliability of LMP: <bean:define id="thisValue" name="encounter" property="encounterMap.field126" />${thisValue}
<%--
            <zeprs:select-dwr pageItem="${field126}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="lmp_reliability_126" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="82" value="${thisValue}"/>
--%>
        </logic:notEmpty>
        <%--<logic:empty name="encounter" property="encounterMap.field126">Reliability of LMP:
            <zeprs:select-dwr pageItem="${field126}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="lmp_reliability_126" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="82"/>
        </logic:empty>--%>
        <br/>

        <logic:notEmpty name="encounter" property="encounterMap.field127"> LMP (calc.):
            <bean:define id="thisValue" name="encounter" property="encounterMap.field127" />
            <span id="field${field.id}Results${pos}">${thisValue}</span><br/>
<%--
            <zeprs:text-dwr pageItem="${field127}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="lmp_127" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
--%>
        </logic:notEmpty>

        <logic:notEmpty name="encounter" property="encounterMap.field1962"> LMP (widget):
            <bean:define id="thisValue" name="encounter" property="encounterMap.field1962" />
            <span id="field${field.id}Results${pos}">${thisValue}</span><br/>
<%--
            <zeprs:text-dwr pageItem="${field127}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="lmp_127" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
--%>
        </logic:notEmpty>
<%--        <logic:empty name="encounter" property="field127">

            <zeprs:text-dwr pageItem="${field127}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="lmp_127" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
--%>
<%--
            <zeprs:date_visit_dwr pageItem="${field127}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="lmp_127" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}"/>

        </logic:empty>--%>
        <logic:notEmpty name="encounter" property="encounterMap.field188"> Uterus:
            <bean:define id="thisValue" name="encounter" property="encounterMap.field188"/>
            <span id="field${field.id}Results${pos}">${thisValue/7}</span> weeks<br/>
        </logic:notEmpty>
        <logic:notEmpty name="encounter" property="encounterMap.field1908"> Ultrasound:
            <bean:define id="thisValue" name="encounter" property="encounterMap.field1908"/>
            <span id="field${field.id}Results${pos}">${thisValue}</span> weeks<br/>
        </logic:notEmpty>
        <logic:notEmpty name="encounter" property="encounterMap.field130"> Quickening:
            <bean:define id="thisValue" name="encounter" property="encounterMap.field130"/>
            <span id="field${field.id}Results${pos}">${thisValue}</span> weeks
        </logic:notEmpty>
&nbsp;
    </td>
    <%-- EGA display --%>
    <td class="enhancedFormChartCellBorder">
    <logic:notEmpty name="encounter" property="field129">
        <bean:define id="thisValue" name="encounter" property="field129" />
        <c:set var="days"  value="${thisValue % 7}" />
        <c:set var="weeks" value="${thisValue/7}" />
        <span id="field${field.id}Results${pos}"><fmt:parseNumber value="${weeks}" integerOnly="true" />, ${days}/7</span>
<%--
        <zeprs:ega_dwr pageItem="${field129}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="ega_129" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
--%>
    </logic:notEmpty>
    <%--<logic:empty name="encounter" property="field129">
        <zeprs:text-dwr pageItem="${field129}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="ega_129" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
    </logic:empty>--%>
    </td>
    <%-- EDD --%>
    <td class="enhancedFormChartCellBorder">
    <logic:notEmpty name="encounter" property="field128">
        <bean:define id="thisValue" name="encounter" property="field128" /> ${thisValue}
<%--
        <zeprs:text-dwr pageItem="${field128}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="edd_128" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
--%>
    </logic:notEmpty>
   <%-- <logic:empty name="encounter" property="field128">
        <zeprs:text-dwr pageItem="${field128}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="edd_128" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
    </logic:empty>--%>
    </td>
    <%-- Dating Method --%>
    <td class="enhancedFormChartCellBorder">
        <logic:notEmpty name="encounter" property="field1615">
            <bean:define id="thisValue" name="encounter" property="encounterMap.field1615"/>${thisValue}
<%--
            <zeprs:select-dwr pageItem="${field1615}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="dating_method" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="82" value="${thisValue}"/>
--%>
        </logic:notEmpty>
       <%-- <logic:empty name="encounter" property="field1615">
            <zeprs:select-dwr pageItem="${field1615}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="dating_method" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="82"/>
        </logic:empty>--%>
    &nbsp;</td>

</tr>