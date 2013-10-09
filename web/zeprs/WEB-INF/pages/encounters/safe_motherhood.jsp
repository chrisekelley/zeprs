<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="encounterForm" scope="request" type="org.cidrz.webapp.dynasite.valueobject.Form" />
<div id="forms">
        <c:if test="${!empty zeprs_session.sessionPatient}">
            <c:if test="${empty zeprs_session.sessionPatient.parentId}">
                <p>
                    <html:link action="/safeMotherhood" paramId="patientId" paramName="zeprs_session" paramProperty="sessionPatient.id">Safe Motherhood</html:link> |
                    <html:link action="/labs.do" paramId="patientId" paramName="zeprs_session" paramProperty="sessionPatient.id">Labs</html:link> |
                    <html:link action="/rpr.do" paramId="patientId" paramName="zeprs_session" paramProperty="sessionPatient.id">RPR</html:link> |
                    <html:link action="/counsel.do" paramId="patientId" paramName="zeprs_session" paramProperty="sessionPatient.id">Counsel</html:link> |
                    <html:link action="/arv.do" paramId="patientId" paramName="zeprs_session" paramProperty="sessionPatient.id">ARV</html:link> |
                    <html:link action="/hematology.do" paramId="patientId" paramName="zeprs_session" paramProperty="sessionPatient.id">Hematology</html:link> |
                    <html:link action="/chemistry.do" paramId="patientId" paramName="zeprs_session" paramProperty="sessionPatient.id">Chemistry</html:link> |
                    <html:link action="/liver.do" paramId="patientId" paramName="zeprs_session" paramProperty="sessionPatient.id">Liver Function</html:link> |
                    <html:link action="/urinalysis.do" paramId="patientId" paramName="zeprs_session" paramProperty="sessionPatient.id">Urinalysis</html:link>
                </p>
            </c:if>
        </c:if>
        <c:if test="${empty zeprs_session.sessionPatient.datePregnancyEnd || ! empty subject }">
            <jsp:include page="encounter_form_layout_long.jsp"/>
        </c:if>
<%--
        <c:if test="${(encounterForm.id == 87) || (encounterForm.id == 90) || (encounterForm.id == 88) || (encounterForm.id == 91) || (encounterForm.id == 89)}">
--%>
            <c:if test="${(encounterForm.id >= 87 && encounterForm.id <= 91) || (encounterForm.id >=101 && encounterForm.id <=104)}">

            <h2>${encounterForm.label} Records</h2>
            <c:choose>
                <c:when test="${! empty chartItems}">
                    <c:if test="${encounterForm.id == '87'}">
                        <p>If you are entering the results of a lab request, enter the results in this table by
                            double-clicking on the field in the record's row.
                            If there were any problems with the lab, they will be listed in the "Exceptions" column.</p>
                    </c:if>
                    <table cellpadding="1" cellspacing="1" class="enhancedtabletighter">
                        <jsp:useBean id="now" class="java.util.Date"/>
                        <%@ include file="/WEB-INF/pages/encounters/encounter_form_records.jsp" %>
                    </table>
                </c:when>
                <c:otherwise>
                    <script type='text/javascript'>
                        function confirmChartDate(field, formId)
                        {
                             if (formId != null) {
                                 var form = document.getElementById("form" + formId);
                                 return validateForm${encounterForm.id}(form);
                             } else {
                                return validateForm${encounterForm.id}(this);
                             }
                        }
                    </script>
                    <c:if test="${empty chartItems}"><p><em>No records</em></p></c:if>
                </c:otherwise>
            </c:choose>
            <c:if test="${! empty limsResults}">
            <%@ include file="/WEB-INF/pages/encounters/lims_records.jsp" %>
            </c:if>
        </c:if>
    </div>