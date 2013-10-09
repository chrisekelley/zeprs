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
<template:put name='title' direct='true'>Problem List</template:put>

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
    <%--<c:param name="problemId" value="${problemId}"/>--%>
    <c:param name="problemType" value="problem"/>
    <c:param name="mode" value="create"/>
</c:url>
<h2>Problem List${labelEnd}</h2>

<div id="probList${print}">
   <table class="enhancedtable" width="100%">
        <tr>
            <th colspan="2">Active Problems</th>
        </tr>
       <c:if test="${empty zeprs_session.sessionPatient.datePregnancyEnd}">
       <tr>
           <td colspan="2">
            <strong><a href='<c:out value="/zeprs/${probUrl}"/>'>Create New Problem</a></strong>
            </td>
       </tr>
       </c:if>
    </table>
   <div id="probListItems${print}">
        <logic:empty name="activeProblems">
        <table class="enhancedtable" width="100%">
        <tr><td colspan="2"><span style="color:red">No active problems</span></td></tr></logic:empty>

        <logic:notEmpty name="activeProblems">
        <table class="enhancedtable" width="240px">
        <tr>
            <td class="enhancedtableheader">Problem</td>
            <td class="enhancedtableheader">Date Modified</td>
        </tr>

        <logic:iterate id="thisproblem" name="activeProblems">

        <c:url value="problem.do" var="myUrl">
            <c:choose>
                <c:when test="${thisproblem.class.name=='org.cidrz.webapp.dynasite.valueobject.Problem'}">
                    <c:param name="problemId" value="${thisproblem.id}"/>
                    <c:param name="patientId" value="${thisproblem.patientId}"/>
                    <c:param name="problemType" value="problem"/>
                </c:when>
                <c:otherwise>
                    <c:param name="problemId" value="${thisproblem.id}"/>
                    <c:param name="patientId" value="${thisproblem.patientId}"/>
                    <c:param name="problemType" value="outcome"/>
                </c:otherwise>
            </c:choose>
            <c:param name="mode" value="create"/>
            <c:if test="${status=='false'}">
                <c:param name="status" value="false"/>
            </c:if>
        </c:url>
        <c:url value="problem.do" var="editUrl">
            <c:choose>
                <c:when test="${thisproblem.class.name=='org.cidrz.webapp.dynasite.valueobject.Problem'}">
                    <c:param name="problemId" value="${thisproblem.id}"/>
                    <c:param name="patientId" value="${thisproblem.patientId}"/>
                    <c:param name="problemType" value="problem"/>
                </c:when>
                <c:otherwise>
                    <c:param name="problemId" value="${thisproblem.id}"/>
                    <c:param name="patientId" value="${thisproblem.patientId}"/>
                    <c:param name="problemType" value="outcome"/>
                </c:otherwise>
            </c:choose>
            <c:param name="mode" value="edit"/>
            <c:if test="${status=='false'}">
                <c:param name="status" value="false"/>
            </c:if>
        </c:url>
        <c:url var="delOutcomeUrl" value="deleteOutcome.do">
            <c:param name="outcomeId" value="${thisproblem.id}"/>
        </c:url>
        <c:url var="delProblemUrl" value="deleteProblem.do">
            <c:param name="id" value="${thisproblem.id}"/>
        </c:url>

        <c:choose>
            <c:when test="${thisproblem.class.name=='org.cidrz.webapp.dynasite.valueobject.Problem'}">
                <c:choose>
                <c:when test="${problemId==thisproblem.id && outcome!=1}">
                    <c:set var="currproblemName" value="${thisproblem.problemName}" scope="request"/>
                    <c:set var="curronsetDate" value="${thisproblem.onsetDate}" scope="request"/>
                    <c:set var="curractive" value="${thisproblem.active}" scope="request"/>
                    <tr class="currentProblem">
                        <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${thisproblem.problemName}</a></td>
                        <td><fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/>
                            <a href='<c:out value="/zeprs/${delProblemUrl}"/>'onclick="return confirm('Caution: Are you sure you want to delete this problem from ZEPRS?');self.close();" title="Delete this Problem">(X)</a>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${thisproblem.problemName}</a></td>
                        <td width="50px" ><fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/>
                            <a href='<c:out value="/zeprs/${delProblemUrl}"/>'onclick="return confirm('Caution: Are you sure you want to delete this problem from ZEPRS?');self.close();" title="Delete this Problem">(X)</a>
                        </td>
                    </tr>
                </c:otherwise>
             </c:choose>
        </c:when>
            <c:when test="${thisproblem.class.name=='org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome'}">
                <c:set var="currproblemName" value="${thisproblem.message}" scope="request"/>
                <c:choose>
                    <c:when test="${problemId==thisproblem.id && outcome==1}">
                    <c:set var="curractive" value="${thisproblem.active}" scope="request"/>
                    <c:set var="currform" value="${thisproblem.formId}" scope="request"/>
                    <c:set var="currencounterId" value="${thisproblem.encounterId}" scope="request"/>
                    <c:set var="currclass" value="${thisproblem.class.name}" scope="request"/>
                    <tr class="currentProblem">
                        <td><a href='<c:out value="/zeprs/${editUrl}"/>'>Required Form: ${currproblemName}</a></td>
                        <td width="50px"><html:link action="/viewEncounter" paramId="id" paramName="thisproblem" paramProperty="encounterId">
                                <fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/>
                            </html:link>
                            <a href='<c:out value="/zeprs/${delOutcomeUrl}"/>'onclick="return confirm('Caution: Are you sure you want to delete this problem from ZEPRS?');self.close();" title="Delete this Problem">(X)</a>
                            </td>
                    </tr>
                    </c:when>
                    <c:otherwise>
                    <tr>
                        <td><a href='<c:out value="/zeprs/${editUrl}"/>'>Required Form: ${currproblemName}</a></td>
                        <td width="50px" ><html:link action="/viewEncounter" paramId="id" paramName="thisproblem" paramProperty="encounterId">
                                <fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/>
                            </html:link>
                            <a href='<c:out value="/zeprs/${delOutcomeUrl}"/>'onclick="return confirm('Caution: Are you sure you want to delete this problem from ZEPRS?');self.close();" title="Delete this Problem">(X)</a>
                            </td>
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
                        <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${thisproblem.reason}</a></td>
                        <td width="50px">
                            <html:link action="/viewEncounter" paramId="id" paramName="thisproblem" paramProperty="encounterId">
                                <fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/>
                            </html:link>
                            <a href='<c:out value="/zeprs/${delOutcomeUrl}"/>'onclick="return confirm('Caution: Are you sure you want to delete this problem from ZEPRS?');self.close();" title="Delete this Problem">(X)</a>
                        </td>
                    </tr>
                    </c:when>
                    <c:otherwise>
                    <tr>
                        <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${thisproblem.reason}</a></td>
                        <td width="50px">
                            <html:link action="/viewEncounter" paramId="id" paramName="thisproblem" paramProperty="encounterId">
                                <fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/>
                            </html:link>
                            <a href='<c:out value="/zeprs/${delOutcomeUrl}"/>'onclick="return confirm('Caution: Are you sure you want to delete this problem from ZEPRS?');self.close();" title="Delete this Problem">(X)</a>
                            </td>
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
                            <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${thisproblem.message}</a></td>
                            <td width="50px">
                                <html:link action="/viewEncounter" paramId="id" paramName="thisproblem" paramProperty="encounterId">
                                    <fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/></html:link>
                                <a href='<c:out value="/zeprs/${delOutcomeUrl}"/>'onclick="return confirm('Caution: Are you sure you want to delete this problem from ZEPRS?');self.close();" title="Delete this Problem">(X)</a>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${thisproblem.message}</a></td>
                            <td width="50px"><html:link action="/viewEncounter" paramId="id" paramName="thisproblem" paramProperty="encounterId">
                                    <fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/></html:link>
                                <a href='<c:out value="/zeprs/${delOutcomeUrl}"/>'onclick="return confirm('Caution: Are you sure you want to delete this problem from ZEPRS?');self.close();" title="Delete this Problem">(X)</a>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${thisproblem.class.name=='org.cidrz.webapp.dynasite.valueobject.Task'}">
                <c:choose>
                    <c:when test="${thisproblem.messageType == 'missing'}">
                        <tr>
                            <td colspan="2"><html:link action="form${thisproblem.formId}/new.do">Missing
                                Form: ${thisproblem.label}</html:link></td>
                        </tr>
                    </c:when>
                    <c:when test="${thisproblem.messageType == 'longterm'}">
                        <tr>
                            <td colspan="2" class="sectionHeader">${thisproblem.label}</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="2">${thisproblem.label}</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
        </logic:iterate>

        </logic:notEmpty>
        </table>
        </div>
    </div>


<%--Now list Inactive Problems--%>

<div id="inactiveProbList${print}">
    <table class="enhancedtable" width="100%">
        <tr>
            <th colspan="2">Inactive Problems</th>
        </tr>
    </table>
   <div id="inactiveProbListItems${print}">
    <logic:empty name="inactiveProblems">
    <table class="enhancedtable" width="100%">
    <tr><td colspan="2"><span style="color:red">No inactive problems</span></td></tr>
    </logic:empty>
    <logic:notEmpty name="inactiveProblems">
    <table class="enhancedtable" width="240px">
    <tr>
        <td class="enhancedtableheader">Problem</td>
        <td class="enhancedtableheader">Date Modified</td>
    </tr>
<logic:iterate id="thisproblem" name="inactiveProblems">
<c:url value="problem.do"  var="myUrl">
    <c:choose>
        <c:when test="${thisproblem.class.name=='org.cidrz.webapp.dynasite.valueobject.Problem'}">
        <c:param name="problemId" value="${thisproblem.id}"/>
        <c:param name="patientId" value="${thisproblem.patientId}"/>
        <c:param name="problemType" value="problem"/>
        </c:when>
        <c:otherwise>
        <c:param name="problemId" value="${thisproblem.id}"/>
        <c:param name="patientId" value="${thisproblem.patientId}"/>
        <c:param name="problemType" value="outcome"/>
        </c:otherwise>
    </c:choose>
    <c:param name="mode" value="create"/>
    <c:if test="${status=='false'}">
     <c:param name="status" value="false"/>
    </c:if>
</c:url>
<c:url value="problem.do"  var="editUrl">
    <c:choose>
        <c:when test="${thisproblem.class.name=='org.cidrz.webapp.dynasite.valueobject.Problem'}">
        <c:param name="problemId" value="${thisproblem.id}"/>
        <c:param name="patientId" value="${thisproblem.patientId}"/>
        <c:param name="problemType" value="problem"/>
        </c:when>
        <c:otherwise>
        <c:param name="problemId" value="${thisproblem.id}"/>
        <c:param name="patientId" value="${thisproblem.patientId}"/>
        <c:param name="problemType" value="outcome"/>
        </c:otherwise>
    </c:choose>
    <c:param name="mode" value="edit"/>
    <c:if test="${status=='false'}">
     <c:param name="status" value="false"/>
    </c:if>
</c:url>
    <c:choose>
        <c:when test="${thisproblem.class.name=='org.cidrz.webapp.dynasite.valueobject.Problem'}">
            <c:choose>
            <c:when test="${problemId==thisproblem.id && outcome!=1}">
                <c:set var="currproblemName" value="${thisproblem.problemName}" scope="request"/>
                <c:set var="curronsetDate" value="${thisproblem.onsetDate}" scope="request"/>
                <c:set var="curractive" value="${thisproblem.active}" scope="request"/>
                <tr class="currentProblem">
                    <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${thisproblem.problemName}</a></td>
                    <td><fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/></td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr>
                    <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${thisproblem.problemName}</a></td>
                    <td width="50px" ><fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/></td>
                </tr>
            </c:otherwise>
         </c:choose>
    </c:when>
        <c:when test="${thisproblem.class.name=='org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome'}">
            <c:set var="currproblemName" value="${thisproblem.message}" scope="request"/>
            <c:choose>
                <c:when test="${problemId==thisproblem.id && outcome==1}">
                <c:set var="curractive" value="${thisproblem.active}" scope="request"/>
                <c:set var="currform" value="${thisproblem.formId}" scope="request"/>
                <c:set var="currencounterId" value="${thisproblem.encounterId}" scope="request"/>
                <c:set var="currclass" value="${thisproblem.class.name}" scope="request"/>
                <tr class="currentProblem">
                    <td><a href='<c:out value="/zeprs/${editUrl}"/>'>Required Form: ${currproblemName}</a></td>
                    <td width="50px"><html:link action="/viewEncounter" paramId="id" paramName="thisproblem" paramProperty="encounterId">
                                <fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/>
                            </html:link></td>
                </tr>
                </c:when>
                <c:otherwise>
                <tr>
                    <td><a href='<c:out value="/zeprs/${editUrl}"/>'>Required Form: ${currproblemName}</a></td>
                    <td width="50px"><html:link action="/viewEncounter" paramId="id" paramName="thisproblem" paramProperty="encounterId">
                                <fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/>
                            </html:link></td>
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
                    <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${thisproblem.reason}</a></td>
                    <td width="50px"><html:link action="/viewEncounter" paramId="id" paramName="thisproblem" paramProperty="encounterId">
                                <fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/>
                            </html:link></td>
                </tr>
                </c:when>
                <c:otherwise>
                <tr>
                    <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${thisproblem.reason}</a></td>
                    <td width="50px"><html:link action="/viewEncounter" paramId="id" paramName="thisproblem" paramProperty="encounterId">
                                <fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/>
                            </html:link></td>
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
                        <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${thisproblem.message}</a></td>
                        <td width="50px"><html:link action="/viewEncounter" paramId="id" paramName="thisproblem" paramProperty="encounterId">
                                <fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/>
                            </html:link></td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td><a href='<c:out value="/zeprs/${editUrl}"/>'>${thisproblem.message}</a></td>
                        <td width="50px"><html:link action="/viewEncounter" paramId="id" paramName="thisproblem" paramProperty="encounterId">
                                <fmt:formatDate type="both" pattern="dd MMM yy" value="${thisproblem.lastModified}"/>
                            </html:link></td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:when>
</c:choose>
</logic:iterate>
</logic:notEmpty>
            </table>
        </div>
    </div>
<c:import url="comments.jsp" />
</div>
    <c:if test="${empty param.template}">
        <c:import url="prob_comment_forms.jsp" />
    </c:if>
</template:put>
</template:insert>