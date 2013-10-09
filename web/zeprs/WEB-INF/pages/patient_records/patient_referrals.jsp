<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="fn" uri='/WEB-INF/fn.tld' %>

<c:choose>
    <c:when test="${! empty param.template}">
    <c:set var="template" value="/WEB-INF/templates/template-print.jsp"/>
    <c:set var="print" value="-print"/>
    </c:when>
    <c:otherwise>
    <c:set var="template" value="/WEB-INF/templates/template.jsp"/>
    </c:otherwise>
</c:choose>

<template:insert template='${template}'>
<template:put name='title' direct='true'>UTH Registry</template:put>

<c:if test="${!empty problemId}">
<c:set var="problemId" value="${problemId}"/>
</c:if>
<c:choose>
    <c:when test="${!empty param.status}">
    <c:set var="status" value="${param.status}"/>
    </c:when>
    <c:otherwise>
    <c:set var="status" value="true"/>
    </c:otherwise>
</c:choose>
<c:if test="${! empty zeprs_session.sessionPatient.datePregnancyEnd}">
    <fmt:formatDate value="${zeprs_session.sessionPatient.datePregnancyBegin}" pattern="dd MMM yy" var="pregStart" />
    <fmt:formatDate value="${zeprs_session.sessionPatient.datePregnancyEnd}" pattern="dd MMM yy" var="pregEnd" />
    <c:set var="labelEnd" value=": ${pregStart} - ${pregEnd} Pregnancy"/>
</c:if>
<c:url value="problem.do" var="active"><c:param name="patientId" value="${param.patientId}"/></c:url>
<c:url value="problem.do" var="inactive"><c:param name="patientId" value="${param.patientId}"/><c:param name="status" value="false"/></c:url>
<template:put name='content' direct='true'>
<c:choose>
    <c:when test="${! empty param.template}">
    <div id="form-print">
    </c:when>
    <c:otherwise>
    <div id="probSum">
    </c:otherwise>
</c:choose>
 <c:url value="problem.do"  var="probUrl">
    <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
    <c:param name="problemType" value="problem"/>
    <c:param name="mode" value="create"/>
</c:url>
<h2>UTH Registry</h2>
The UTH Registry tracks the progress of the patient from clinic to UTH. If the patient was referred to UTH, a chart is visible below.
There are three main sections of this registry:
<ul>
    <li>Referral: The "Date Referred" links to the record that created the referral. Clinic displays the clinic that
        referred the patient; if it's a UTH admission, it will display which UTH section made the admission.
        The Reason for Referral column displays the diagosis made by the clinic that merited the referral.
        If a patient was admitted from UTH, this column will display "Patient admitted to UTH."</li>
    <li>Admission: This section varies depending if the record is being viewed at a clinic or at UTH:
        <ul>
            <li>Clinic: If the patient has been admitted to UTH, Admission date, Condition, and Ward are
                displayed. Otherwise, "Awaiting admission to UTH" is displayed. </li>
            <li>UTH: If the patient has not yet been admitted to UTH, the "Admit to UTH" link enables display of form to
                record condition of patient and the ward to which patient was admitted.</li>
        </ul>
    </li>
    <li>Discharge: This section varies depending if the record is being viewed at a clinic or at UTH:
        <ul>
            <li>Clinic: If the patient has been discharged, the discharge date links to the UTH discharge record.
                Otherwise, "Pending Admission" is displayed. </li>
            <li>UTH: If the patient has not yet been discharged, the discharge link enables UTH
                discharge.</li>
        </ul>
    </li>
</ul>

   <table class="enhancedtable" width="100%">
        <tr>
            <th colspan="3">Referral</th>
            <th colspan="3">Admission</th>
            <th>Discharge</th>
        </tr>
        <logic:empty name="referrals">
            <tr>
                <td colspan="3"><span style="color:red">No referrals to UTH</span></td>
            </tr>
        </logic:empty>
        <logic:notEmpty name="referrals">
            <tr>
                <td class="enhancedtableheader">Date Referred</td>
                <td class="enhancedtableheader">Clinic</td>
                <td class="enhancedtableheader">Reason for Referral</td>
                <td class="enhancedtableheader">Date Admitted</td>
                <td class="enhancedtableheader">Condition</td>
                <td class="enhancedtableheader">Ward</td>
                <td class="enhancedtableheader">Date Discharge</td>
            </tr>
            <logic:iterate id="referral" name="referrals">
                <c:url value="viewEncounter.do" var="encounter">
                    <c:param name="patientId" value="${referral.patientId}"/>
                    <c:param name="id" value="${referral.encounterId}"/>
                    <c:param name="referralId" value="${referral.id}"/>
                </c:url>
                <c:url value="problem.do"  var="myUrl">
                    <c:param name="problemId" value="${referral.id}"/>
                    <c:param name="patientId" value="${referral.patientId}"/>
                    <c:param name="problemType" value="outcome"/>
                    <c:param name="mode" value="create"/>
                </c:url>
                <c:url value="problem.do"  var="editUrl">
                    <c:param name="problemId" value="${referral.id}"/>
                    <c:param name="patientId" value="${referral.patientId}"/>
                    <c:param name="problemType" value="outcome"/>
                    <c:param name="mode" value="edit"/>
                </c:url>
                <c:url value="form63/new.do"  var="antenatalUrl">
                    <c:param name="referralId" value="${referral.id}"/>
                    <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
                </c:url>
                <c:url value="form74/new.do"  var="postnatalUrl">
                    <c:param name="referralId" value="${referral.id}"/>
                    <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
                </c:url>
                <c:url value="form76/new.do"  var="nicuUrl">
                    <c:param name="referralId" value="${referral.id}"/>
                    <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
                </c:url>
                <tr>
                    <td width="50px"><a href='<c:out value="/zeprs/${encounter}"/>'><fmt:formatDate type="both" pattern="dd MMM yy" value="${referral.created}"/></a></td>
                    <td>${referral.referringSiteName}</td>
                    <td>${referral.reason}</td>
                    <c:choose>
                        <c:when test="${empty referral.uthAdmissionDate && zeprs_session.clientSettings.site.siteTypeId == 2}">
                            <c:url value="viewEncounter.do" var="admitUrl">
                                <c:param name="id" value="${referral.encounterId}"/>
                                <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
                                <c:param name="referralId" value="${referral.id}"/>
                            </c:url>
                        <td colspan="3"><a href='<c:out value="/zeprs/${admitUrl}"/>'>Admit to UTH</a></td>
                        </c:when>
                        <c:when test="${empty referral.uthAdmissionDate && zeprs_session.clientSettings.site.siteTypeId != 2}">
                        <td colspan="3">Awaiting admission to UTH</td>
                        </c:when>
                        <c:otherwise>
                            <td><fmt:formatDate type="both" pattern="dd MMM yy" value="${referral.uthAdmissionDate}" /></td>
                            <td>${referral.arrivalCondition}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${referral.wardId == '2956'}">Outpatient Clinic</c:when>
                                    <c:when test="${referral.wardId == '2916'}">Labor</c:when>
                                    <c:when test="${referral.wardId == '2957'}">Gynecological</c:when>
                                    <c:when test="${referral.wardId == '2914'}">Postnatal</c:when>
                                    <c:when test="${referral.wardId == '2915'}">Antenatal</c:when>
                                    <c:when test="${referral.wardId == '2921'}">NICU</c:when>
                                    <c:when test="${referral.wardId == '2922'}">PEDS</c:when>
                                    <c:when test="${referral.wardId == '2958'}">Not Admitted</c:when>
                                </c:choose>
                            </td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${! empty referral.dischargeId}">
                            <c:url value="viewEncounter.do" var="dischargeUrl">
                                <c:param name="id" value="${referral.dischargeId}"/>
                                <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
                            </c:url>
                        <td><a href='<c:out value="/zeprs/${dischargeUrl}"/>'>${referral.dischargeDate}</a></td>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${zeprs_session.clientSettings.site.siteTypeId != 2}">
                                    <td>Pending Discharge</td>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${!empty zeprs_session.sessionPatient.parentId == true}">
                                            <td><a href='<c:out value="/zeprs/${nicuUrl}"/>'>NICU Discharge</a></td>
                                        </c:when>
                                        <c:when test="${zeprs_session.sessionPatient.deliveryCompleted == true}">
                                            <td><a href='<c:out value="/zeprs/${postnatalUrl}"/>'>Postnatal Discharge</a></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><a href='<c:out value="/zeprs/${antenatalUrl}"/>'>Antenatal Discharge</a></td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
</table>
</template:put>
</template:insert>