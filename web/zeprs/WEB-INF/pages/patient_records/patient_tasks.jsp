<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:choose>
    <c:when test="${empty param.flowId}">
    <c:set var="flowId" value="${tasklist.currentFlowId}"/>
    </c:when>
    <c:otherwise>
    <c:set var="flowId" value="${param.flowId}"/>
    </c:otherwise>
</c:choose>
<c:set var="labelEnd"/>
<c:if test="${! empty zeprs_session.sessionPatient.datePregnancyEnd}">
    <fmt:formatDate value="${zeprs_session.sessionPatient.datePregnancyBegin}" pattern="dd MMM yy" var="pregStart" />
    <fmt:formatDate value="${zeprs_session.sessionPatient.datePregnancyEnd}" pattern="dd MMM yy" var="pregEnd" />
    <c:set var="labelEnd" value=": ${pregStart} - ${pregEnd} Pregnancy"/>
</c:if>
<c:choose>
    <c:when test="${! empty param.template}">
    <c:set var="template" value="/WEB-INF/templates/template-print.jsp"/>
    </c:when>
    <c:otherwise>
    <c:set var="template" value="/WEB-INF/templates/template.jsp"/>
    </c:otherwise>
</c:choose>
<template:insert template='${template}'>
<template:put name='title' direct='true'>Patient Tasks</template:put>
<template:put name='content' direct='true'>
<c:choose>
    <c:when test="${! empty param.template}">
    <div id="form-print">
    </c:when>
    <c:otherwise>
    <div id="tasklist">
    </c:otherwise>
</c:choose>
<c:choose>
<c:when test="${flowId==1}"><h2>Antepartum${labelEnd}</h2></c:when>
<c:when test="${flowId==2}"><h2>History${labelEnd}</h2></c:when>
<c:when test="${flowId==3}"><h2>Intrapartum${labelEnd}</h2></c:when>
<c:when test="${flowId==4}"><h2>Delivery Summary/Postnatal${labelEnd}</h2></c:when>
<c:when test="${flowId==5}"><h2>NICU/Pediatrics${labelEnd}</h2></c:when>
<c:when test="${flowId==6}"><h2>UTH Admissions${labelEnd}</h2></c:when>
<c:when test="${flowId==7}"><h2>Problem or Labour Visit${labelEnd}</h2></c:when>
<c:when test="${flowId==8}"><h2>Ultrasound${labelEnd}</h2></c:when>
<c:when test="${flowId==102}"><h2>Labs${labelEnd}</h2></c:when>
<c:when test="${flowId==103}"><h2>Safe Motherhood Care${labelEnd}</h2></c:when>
<c:otherwise><h2>Tasks${labelEnd}</h2></c:otherwise>
</c:choose>
<c:if test="${(! empty tasklist.message) && (flowId == 7)}">
    <c:choose>
    <c:when test="${tasklist.message =='The partograph has been started.'}">
    <p class="error"><bean:write name="tasklist" property="message"></bean:write> Select: <html:link action="/partograph.do">Partograph</html:link></p>
    </c:when>
    <c:otherwise>
    <p class="error"><bean:write name="tasklist" property="message"></bean:write> </p>
    </c:otherwise>
    </c:choose>
</c:if>
<table class="enhancedtable">
<thead>
<th colspan="2">Form</th>
<th>Status</th>
<th>Staff Member</th>
<th>Site</th>
<th>Date Modified</th>
</thead>
<tbody>
<c:set var="doneDialogue" value="Not Done"/>
<logic:iterate id="task" name="tasklist" property="taskList">
<c:choose>
    <c:when test="${empty zeprs_session.sessionPatient.datePregnancyEnd}">
         <c:choose>
            <c:when test="${task.submissions >= 1}">
            <c:set var="doneDialogue" value="Available"/>
            </c:when>
            <c:when test="${task.formId == 23 && ! empty zeprs_session.sessionPatient.children}">
            <c:set var="doneDialogue" value="Available"/>
            </c:when>
            <c:otherwise>
            <c:set var="doneDialogue" value="Not Done"/>
            </c:otherwise>
        </c:choose>
        <c:choose>
	        <c:when test="${task.active=='true'}">
	            <c:choose>
		            <c:when test="${task.formId == 79}">
		            <tr>
		                <td><html:link action="/partograph.do" styleClass="noBorder" paramId="patientId" paramName="task" paramProperty="patientId"><img src="/zeprs/images/checkbox.gif" height="15px" width="15px"/></html:link></td>
		                <td><html:link action="/partograph.do" paramId="patientId" paramName="task" paramProperty="patientId"><bean:write name="task" property="label"/></html:link></td>
		                <td colspan="4" class="error">
		                    <c:choose>
		                        <c:when test="${! empty tasklist.message}">
		                        <bean:write name="tasklist" property="message"></bean:write>
		                        </c:when>
		                        <c:otherwise>Not Done</c:otherwise>
		                    </c:choose>
		                </td>
		            </tr>
		            </c:when>
		            <c:when test="${task.formId == 2}">
		            <tr>
		                <td><html:link action="/previousPregnancies.do" styleClass="noBorder" paramId="patientId" paramName="task" paramProperty="patientId"><img src="/zeprs/images/checkbox.gif" height="15px" width="15px"/></html:link></td>
		                <td><html:link action="/previousPregnancies.do" paramId="patientId" paramName="task" paramProperty="patientId"><bean:write name="task" property="label"/></html:link></td>
		                <c:choose>
		                    <c:when test="${! empty task.auditInfo.lastModified}">
		                    <td>Recent Entry:</td>
		                    <td>${task.auditInfo.lastModifiedBy.lastName}, ${task.auditInfo.lastModifiedBy.firstName}</td>
		                    <td>${task.siteName}</td>
		                    <td><fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.lastModified}" /></td>
		                    </c:when>
		                    <c:when test="${! empty zeprs_session.sessionPatient.noPreviousPregnancies && empty prevZeprsPregs}">
		                        <td colspan="4" class="error">No Previous Pregnancies</td>
		                    </c:when>
		                    <c:when test="${! empty zeprs_session.sessionPatient.noPreviousPregnancies && ! empty prevZeprsPregs}">
		                        <td colspan="4">Previous ZEPRS Pregnancies: ${prevZeprsPregs}</td>
		                    </c:when>
		                    <c:otherwise>
		                    <td colspan="4" class="error">Not Done</td>
		                    </c:otherwise>
		                </c:choose>
		            </tr>
		            </c:when>

		            <c:when test="${task.formId == 17}">
		            <tr>
		                <td><html:link action="/labourObservations.do" styleClass="noBorder" paramId="patientId" paramName="task" paramProperty="patientId"><img src="/zeprs/images/checkbox.gif" height="15px" width="15px"/></html:link></td>
		                <td><html:link action="/labourObservations.do" paramId="patientId" paramName="task" paramProperty="patientId"><bean:write name="task" property="label"/></html:link></td>
		                <c:choose>
		                <c:when test="${! empty task.auditInfo.lastModified}">
		                <td>Recent Entry:</td>
		                <td>${task.auditInfo.lastModifiedBy.lastName}, ${task.auditInfo.lastModifiedBy.firstName}</td>
		                <td>${task.siteName}</td>
		                <td><fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.lastModified}" /></td>
		                </c:when>
		                <c:otherwise>
		                <td colspan="4" class="error">Not Done</td>
		                </c:otherwise>
		                </c:choose>
		            </tr>
		            </c:when>
		            <c:when test="${task.formId == 23}">
		             <tr>
		                <td><c:url value="form23/new.do" var="url">
		                		<c:param name="patientId" value="${task.patientId}"/>
		                	</c:url>
		                        <a href='<c:out value="/zeprs/${url}"/>' class='noBorder'><img src="/zeprs/images/checkboxChecked.gif" height="15px" width="15px"/></a></td>
		                <td><a href='<c:out value="/zeprs/${url}"/>'><bean:write name="task" property="label"/><c:if test='${! empty task.sex}'> Sex: <c:if test='${task.sex=="1"}'>Female</c:if><c:if test='${task.sex=="2"}'>Male</c:if></c:if></a></td>
		                <td colspan="4" class="error">Not Done</td>
		                </tr>
		            </c:when>
		            <c:when test="${task.formId == 55}">
		            <tr>
		                <td><html:link action="/currentMedicine.do" styleClass="noBorder" paramId="patientId" paramName="task" paramProperty="patientId"><img src="/zeprs/images/checkbox.gif" height="15px" width="15px"/></html:link></td>
		                <td><html:link action="/currentMedicine.do" paramId="patientId" paramName="task" paramProperty="patientId"><bean:write name="task" property="label"/></html:link></td>
		                <c:choose>
		                <c:when test="${! empty task.auditInfo.lastModified}">
		                <td>Recent Entry:</td>
		                <td>${task.auditInfo.lastModifiedBy.lastName}, ${task.auditInfo.lastModifiedBy.firstName}</td>
		                <td>${task.siteName}</td>
		                <td><fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.lastModified}" /></td>
		                </c:when>
		                <c:otherwise>
		                <td colspan="4" class="error">Not Done</td>
		                </c:otherwise>
		                </c:choose>
		            </tr>
		            </c:when>
		            <c:when test="${task.formId == 80}">
		            <tr>
		                <td><html:link action="/patientAnte.do" paramId="patientId" paramName="task" paramProperty="patientId" styleClass="noBorder"><img src="/zeprs/images/checkbox.gif" height="15px" width="15px"/></html:link></td>
		                <td><html:link action="/patientAnte.do" paramId="patientId" paramName="task" paramProperty="patientId"><bean:write name="task" property="label"/></html:link></td>
		                <c:choose>
		                <c:when test="${! empty task.auditInfo.lastModified}">
		                <td>Recent Entry:</td>
		                <td>${task.auditInfo.lastModifiedBy.lastName}, ${task.auditInfo.lastModifiedBy.firstName}</td>
		                <td>${task.siteName}</td>
		                <td><fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.lastModified}" /></td>
		                </c:when>
		                <c:otherwise>
		                <td colspan="4" class="error">Not Done</td>
		                </c:otherwise>
		                </c:choose>
		            </tr>
		            </c:when>
		            <c:when test="${task.formId == 81}">
		            <tr>
		                <td><html:link action="/patientPuer.do" paramId="patientId" paramName="task" paramProperty="patientId" styleClass="noBorder"><img src="/zeprs/images/checkbox.gif" height="15px" width="15px"/></html:link></td>
		                <td><html:link action="/patientPuer.do" paramId="patientId" paramName="task" paramProperty="patientId"><bean:write name="task" property="label"/></html:link></td>
		                <c:choose>
		                <c:when test="${! empty task.auditInfo.lastModified}">
		                <td>Recent Entry:</td>
		                <td>${task.auditInfo.lastModifiedBy.lastName}, ${task.auditInfo.lastModifiedBy.firstName}</td>
		                <td>${task.siteName}</td>
		                <td><fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.lastModified}" /></td>
		                </c:when>
		                <c:otherwise>
		                <td colspan="4" class="error">Not Done</td>
		                </c:otherwise>
		                </c:choose>
		            </tr>
		            </c:when>
		            <%--<c:when test="${task.formId == 68}">
		            <tr>
		                <td><html:link action="/maternalDischargeAction.do" paramId="patientId" paramName="task" paramProperty="patientId" styleClass="noBorder"><img src="/zeprs/images/checkbox.gif" height="15px" width="15px"/></html:link></td>
		                <td><html:link action="/maternalDischargeAction.do" paramId="patientId" paramName="task" paramProperty="patientId"><bean:write name="task" property="label"/></html:link></td>
		                <td colspan="4" class="error">${doneDialogue}</td>
		            </tr>
		            </c:when>--%>
		            <c:when test="${task.formId == 82}">
		            <tr>
		                <td><html:link action="/pregnancyDating.do" paramId="patientId" paramName="task" paramProperty="patientId" styleClass="noBorder"><img src="/zeprs/images/checkbox.gif" height="15px" width="15px"/></html:link></td>
		                <td><html:link action="/pregnancyDating.do" paramId="patientId" paramName="task" paramProperty="patientId"><bean:write name="task" property="label"/></html:link></td>
		                <c:choose>
		                <c:when test="${! empty task.auditInfo.lastModified}">
		                <td>Recent Entry:</td>
		                <td>${task.auditInfo.lastModifiedBy.lastName}, ${task.auditInfo.lastModifiedBy.firstName}</td>
		                <td>${task.siteName}</td>
		                <td><fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.lastModified}" /></td>
		                </c:when>
		                <c:otherwise>
		                <td colspan="4" class="error">Not Done</td>
		                </c:otherwise>
		                </c:choose>
		            </tr>
		            </c:when>
		            <%--Don't display newborn record form--%>
		            <c:when test="${task.formId == 109}"></c:when>
		            <c:otherwise>
		            <tr>
		                <td><html:link action="/form${task.formId}/new.do" paramId="patientId" paramName="task" paramProperty="patientId" styleClass="noBorder"><img src="/zeprs/images/checkbox.gif" height="15px" width="15px"/></html:link></td>
		                <td><html:link action="/form${task.formId}/new.do" paramId="patientId" paramName="task" paramProperty="patientId"><bean:write name="task" property="label"/></html:link></td>
		                <td colspan="4" class="error">${doneDialogue}</td>
		            </tr>
		            </c:otherwise>
	            </c:choose>
	        </c:when>

	        <c:otherwise>
		        <%--tasks that are not active--%>
		        <%-- forms that have encounters --%>
		        <c:if test="${!empty task.encounterId}">
		        	<c:choose>
			            <c:when test="${task.formId == 79}">
			                <tr>
			                    <td><html:link action="/partograph.do" paramId="patientId" paramName="task" paramProperty="patientId" styleClass="noBorder"><img src="/zeprs/images/checkbox.gif" height="15px" width="15px"/></html:link></td>
			                    <td><html:link action="/partograph.do" paramId="patientId" paramName="task" paramProperty="patientId"><bean:write name="task" property="label"/></html:link></td>
			                    <td class="error">
			                        <c:if test="${! empty tasklist.message}">
			                        <bean:write name="tasklist" property="message"></bean:write>
			                        </c:if>
			                    </td>
			                    <td>${task.auditInfo.lastModifiedBy.lastName}, ${task.auditInfo.lastModifiedBy.firstName} </td>
			                <td>${task.siteName}</td>
			                <td><fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.lastModified}" /></td>
			                </tr>
			            </c:when>
			            <c:when test="${task.formId == 2}"></c:when>
			            <c:when test="${task.formId == 17}"></c:when>
			            <c:when test="${task.formId == 55}"></c:when>
			            <c:when test="${task.formId == 80}"></c:when>
			            <c:when test="${task.formId == 81}"></c:when>
			            <c:when test="${task.formId == 82}"></c:when>
			            <%--Don't display newborn record form--%>
			            <c:when test="${task.formId == 109}"></c:when>
			            <c:when test="${task.formId == 23}">
			             <tr>
			                <td><c:url value="viewEncounter.do" var="url"><c:param name="id" value="${task.encounterId}"/><c:param name="class" value="${task.className}"/></c:url>
			                        <a href='<c:out value="/zeprs/${url}"/>' class='noBorder'><img src="/zeprs/images/checkboxChecked.gif" height="15px" width="15px"/></a></td>
			                <td><bean:write name="task" property="label"/><c:if test='${! empty task.sex}'> Sex: <c:if test='${task.sex=="1"}'>Female</c:if><c:if test='${task.sex=="2"}'>Male</c:if></c:if></td>
			                <td>Done</td>
			                <td>${task.auditInfo.lastModifiedBy.lastName}, ${task.auditInfo.lastModifiedBy.firstName} </td>
			                <td>${task.siteName}</td>
			                <td>
			                <c:url value="viewEncounter.do" var="url">
			                    <c:param name="id" value="${task.encounterId}"/>
			                    <c:param name="patientId" value="${task.patientId}"/>
			                </c:url>
			                <a href='<c:out value="/zeprs/${url}"/>'><fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.lastModified}" /></a>
			                </td>
			                </tr>
			            </c:when>

			            <c:otherwise>
			                <tr>
			                <td><c:url value="viewEncounter.do" var="url"><c:param name="id" value="${task.encounterId}"/><c:param name="class" value="${task.className}"/></c:url>
			                        <a href='<c:out value="/zeprs/${url}"/>' class='noBorder'><img src="/zeprs/images/checkboxChecked.gif" height="15px" width="15px"/></a></td>
			                <td><bean:write name="task" property="label"/></td>
			                <td>Done</td>
			                <td>${task.auditInfo.lastModifiedBy.lastName}, ${task.auditInfo.lastModifiedBy.firstName}</td>
			                <td>${task.siteName}</td>
			                <td>
			                <c:url value="viewEncounter.do" var="url">
			                    <c:param name="id" value="${task.encounterId}"/>
			                    <c:param name="patientId" value="${task.patientId}"/>
			                </c:url>
			                <a href='<c:out value="/zeprs/${url}"/>'><fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.lastModified}" /></a>
			                </td>
			                </tr>
			            </c:otherwise>
		            </c:choose>
		        </c:if>
	        </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise> <%--Pregnancies that have ended --%>
        <c:choose>
           <c:when test="${!empty task.encounterId}">
               <c:choose>
                    <c:when test="${task.formId == 2}"></c:when>
                    <%--Don't display newborn record form--%>
            		<c:when test="${task.formId == 109}"></c:when>
                    <c:when test="${task.formId == 23}">
                        <tr>
                            <td><c:url value="viewEncounter.do" var="url"><c:param name="id" value="${task.encounterId}"/><c:param name="class" value="${task.className}"/></c:url>
                                    <a href='<c:out value="/zeprs/${url}"/>' class='noBorder'><img src="/zeprs/images/checkboxChecked.gif" height="15px" width="15px"/></a></td>
                            <td><bean:write name="task" property="label"/></td>
                            <td>
                                <c:choose>
                                    <c:when test='${! empty task.sex}'><c:if test='${task.sex=="1"}'>Female</c:if><c:if test='${task.sex=="2"}'>Male</c:if></c:when>
                                    <c:otherwise>Done</c:otherwise>
                                </c:choose>
                            </td>
                            <td>${task.auditInfo.lastModifiedBy.lastName}, ${task.auditInfo.lastModifiedBy.firstName} </td>
                            <td>${task.siteName}</td>
                            <td>
                            <c:url value="viewEncounter.do" var="url">
                                <c:param name="id" value="${task.encounterId}"/>
                                <c:param name="patientId" value="${task.patientId}"/>
                            </c:url>
                            <a href='<c:out value="/zeprs/${url}"/>'><fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.lastModified}" /></a>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                     <tr>
                    <td><c:url value="viewEncounter.do" var="url"><c:param name="id" value="${task.encounterId}"/><c:param name="class" value="${task.className}"/></c:url>
                            <a href='<c:out value="/zeprs/${url}"/>' class='noBorder'><img src="/zeprs/images/checkboxChecked.gif" height="15px" width="15px"/></a></td>
                    <td><bean:write name="task" property="label"/></td>
                    <td>Done</td>
                    <td>${task.auditInfo.lastModifiedBy.lastName}, ${task.auditInfo.lastModifiedBy.firstName}</td>
                    <td>${task.siteName}</td>
                    <td><c:url value="viewEncounter.do" var="url"><c:param name="id" value="${task.encounterId}"/><c:param name="class" value="${task.className}"/></c:url>
                            <a href='<c:out value="/zeprs/${url}"/>'><fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.lastModified}" /></a></td>
                    </tr>
                    </c:otherwise>
                </c:choose>
           </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${task.formId == 2}">
                    <tr>
                        <td><html:link action="/previousPregnancies.do" styleClass="noBorder" paramId="patientId" paramName="task" paramProperty="patientId"><img src="/zeprs/images/checkboxChecked.gif" height="15px" width="15px"/></html:link></td>
                        <td><bean:write name="task" property="label"/></td>
                        <c:choose>
                            <c:when test="${! empty task.auditInfo.lastModified}">
                            <td>Done</td>
                            <td>${task.auditInfo.lastModifiedBy.lastName}, ${task.auditInfo.lastModifiedBy.firstName}</td>
                            <td>${task.siteName}</td>
                            <td><html:link action="/previousPregnancies.do" paramId="patientId" paramName="task" paramProperty="patientId"><fmt:formatDate type="both" pattern="dd MMM yyyy  HH:mm" value="${task.auditInfo.lastModified}"/></html:link></td>
                            </c:when>
                            <c:when test="${! empty zeprs_session.sessionPatient.noPreviousPregnancies && empty zeprs_session.sessionPatient.datePregnancyEnd}">
                                <td colspan="4" class="error">No Previous Pregnancies</td>
                            </c:when>
                            <c:when test="${! empty zeprs_session.sessionPatient.noPreviousPregnancies && ! empty zeprs_session.sessionPatient.datePregnancyEnd}">
                            <td colspan="4" class="error">No Previous Pregnancies
                                <c:url value="patientHome.do" var="url">
                                    <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
                                    <c:param name="pregnancyId" value="0"/>
                                </c:url>
                                <p><a href='<c:out value="/zeprs/${url}"/>'>Return to Current Pregnancy</a></p>
                            </td>
                            </c:when>
                            <c:otherwise>
                            <td colspan="4" class="error">Not Done</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:when>
            </c:choose>
            </c:otherwise>

        </c:choose>
        <c:if test="${!empty task.encounterId}">

         </c:if>
    </c:otherwise>
</c:choose>
</logic:iterate>

</tbody>
</table>

<c:if test="${! empty longTermProblems}">
<h2>Long Term Problems/Conditions</h2>
<p>Problems/Conditions that have occurred in previous pregnancies</p>

<table class="enhancedtable">
    <tr>
        <td class="enhancedtableheader">Problem</td>
        <td class="enhancedtableheader">Date</td>
    </tr>
    <logic:iterate id="thisproblem" name="longTermProblems">
        <c:url value="problem.do" var="editUrl">
            <c:param name="problemId" value="${thisproblem.id}"/>
            <c:param name="patientId" value="${thisproblem.patientId}"/>
            <c:param name="problemType" value="outcome"/>
            <c:param name="mode" value="edit"/>
            <c:if test="${status=='false'}">
                <c:param name="status" value="false"/>
            </c:if>
        </c:url>
        <c:choose>
            <c:when test="${thisproblem.class.name=='org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome'}">
                <c:set var="currproblemName" value="${thisproblem.message}" scope="request"/>
                <c:choose>
                    <c:when test="${problemId==thisproblem.id && outcome==1}">

                        <c:set var="curractive" value="${thisproblem.active}" scope="request"/>
                        <c:set var="currform" value="${thisproblem.formId}" scope="request"/>
                        <c:set var="currencounterId" value="${thisproblem.encounterId}" scope="request"/>
                        <c:set var="currclass" value="${thisproblem.class.name}" scope="request"/>
                        <tr class="currentProblem">
                            <td><html:link action="form${thisproblem.requiredFormId}/new.do" paramId="patientId"
                                           paramName="thisproblem" paramProperty="patientId">Required
                                Form: ${currproblemName}</html:link></td>
                            <td width="50px"><fmt:formatDate type="both" pattern="dd MMM yy"
                                                             value="${thisproblem.lastModified}"/></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td><html:link action="form${thisproblem.requiredFormId}/new.do" paramId="patientId"
                                           paramName="thisproblem" paramProperty="patientId">Required
                                Form: ${currproblemName}</html:link></td>
                            <td width="50px"><fmt:formatDate type="both" pattern="dd MMM yy"
                                                             value="${thisproblem.lastModified}"/></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${thisproblem.class.name=='org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome'}">
                <c:choose>
                    <c:when test="${problemId==thisproblem.id && outcome==1}">
                        <c:set var="currproblemName" value="${thisproblem.reason}" scope="request"/>
                        <c:set var="curractive" value="${thisproblem.active}" scope="request"/>
                        <c:set var="currencounterId" value="${thisproblem.encounterId}" scope="request"/>
                        <c:set var="currclass" value="${thisproblem.class.name}" scope="request"/>
                        <tr class="currentProblem">
                            <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${thisproblem.reason}</a> <a
                                    href='<c:out value="/zeprs/${editUrl}"/>'>(e)</a></td>
                            <td width="50px"><fmt:formatDate type="both" pattern="dd MMM yy"
                                                             value="${thisproblem.lastModified}"/></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${thisproblem.reason}</a></td>
                            <td width="50px"><fmt:formatDate type="both" pattern="dd MMM yy"
                                                             value="${thisproblem.lastModified}"/></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${thisproblem.class.name=='org.cidrz.webapp.dynasite.rules.impl.InfoOutcome'}">
                <c:choose>
                    <c:when test="${problemId==thisproblem.id && outcome==1}">
                        <c:set var="currproblemName" value="${thisproblem.message}" scope="request"/>
                        <c:set var="curractive" value="${thisproblem.active}" scope="request"/>
                        <c:set var="currencounterId" value="${thisproblem.encounterId}" scope="request"/>
                        <c:set var="currclass" value="${thisproblem.class.name}" scope="request"/>
                        <tr class="currentProblem">
                            <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${special}${thisproblem.message}</a></td>
                            <td width="50px"><fmt:formatDate type="both" pattern="dd MMM yy"
                                                             value="${thisproblem.lastModified}"/></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${special}${thisproblem.message}</a></td>
                            <td width="50px"><fmt:formatDate type="both" pattern="dd MMM yy"
                                                             value="${thisproblem.lastModified}"/></td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
    </logic:iterate>
</table>
</c:if>
</div>
<c:import url="../problems/problems_chart.jsp" />
</template:put>
</template:insert>