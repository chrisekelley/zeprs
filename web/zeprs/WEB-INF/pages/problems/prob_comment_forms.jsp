<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="fn" uri='/WEB-INF/fn.tld' %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow"/>
<fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow"/>
<fmt:formatDate type="both" pattern="dd" value="${now}" var="daynow"/>
<c:set var="lastYear" value="${yearnow - 1}"/>
<c:set var="twoYears" value="${yearnow + 2}"/>

<div id="probForms">
    <c:choose>
         <c:when test="${param.mode=='create'}">
            <c:choose>
            <c:when test="${empty problemId}">
            <div id="encounterView">
            </c:when>
            <c:otherwise>
            <div id="probForm">
            </c:otherwise>
            </c:choose>
            <h2>Create a Problem</h2>
            <p>Items marked with an asterix (<span class="asterix">*</span>) are required.</p>
            <html:form action="problems/save" method="POST" onsubmit="return validateProblem(this);">
            <html:hidden property="id" />
            <html:hidden property="active" value="1"/>
            <html:hidden property="patientId" value="${zeprs_session.sessionPatient.id}" />
            <html:hidden property="pregnancyId" value="${zeprs_session.sessionPatient.currentPregnancyId}"/>
            <div class="row">
            <span class="label"><bean:message key="labels.problem.problemName"/>:&nbsp;<span class="asterix">*</span></span><span
            class="formw"><html:text property="problemName" size="30"/></span>
            </div>
            <div class="row">
            <span class="label"><bean:message key="labels.problem.onsetDate"/>: </span><span
            class="formw">
            <table>
            <tr>
            <td><zeprs:date_visit_problem/></td>
            </tr>
            </table>
            </span>
            </div>
            <div class="row">
            <span class="label"><bean:message key="labels.problem.commentText" />: </span><span
            class="formw"><html:textarea property="commentText" cols="20"/></span>
            </div>
            <div class="row">
            <span class="label"><bean:message key="labels.problem.actionPlan"/>: </span><span
            class="formw"><html:textarea property="actionPlan" cols="20"/></span>
            </div>
            <div class="row">
            <html:submit value="Save" /></p>
            </div>
            </html:form>
            </div>
            <div id="commentForm" style="visibility:hidden;">
            <h2>Add a Comment</h2>
            <html:form action="problems/comment/save" method="POST" onsubmit="return validateComment(this);">
            <html:hidden property="id" />
            <html:hidden property="patientId" value="${zeprs_session.sessionPatient.id}"/>
            <html:hidden property="pregnancyId" value="${zeprs_session.sessionPatient.currentPregnancyId}"/>
            <%--<logic:present name="problemId">
                <html:hidden property="problemId" value="${problemId}"/>
            </logic:present>--%>
            <c:choose>
            <c:when test="${problemType=='problem'}">
                <html:hidden property="problemId" value="${problemId}"/>
            </c:when>
            <c:when test="${problemType=='outcome'}">
                <html:hidden property="outcomeId" value="${problemId}"/>
            </c:when>
            </c:choose>
            <html:hidden property="problemType" value="${problemType}"/>
            <html:hidden property="status" value="${status}"/>
            <div class="row">
            <span class="label"><bean:message key="labels.problem.commentText" />: <span class="asterix">*</span></span><span
            class="formw"><html:textarea property="commentText" cols="20"/></span>
            </div>
            <div class="row">
            <span class="label"><bean:message key="labels.problem.actionPlan"/>: </span><span
            class="formw"><html:textarea property="actionPlan" cols="20"/></span>
            </div>
            <div class="row">
            <html:submit value="Save" /></p>
            </div>
            </html:form>
            </div>
        </c:when>
        <c:when test="${param.mode=='comment'}">
            <c:if test="${!empty problemId}"><p>
            <a href="#" onclick="toggleProblemForms('probForm','commentForm')">Create Problem</a> |
            <a href="#" onclick="toggleProblemForms('commentForm','probForm')">Add a Comment</a></p></c:if>
            <c:choose>
            <c:when test="${empty problemId}">
            <div id="encounterView">
            </c:when>
            <c:otherwise>
            <div id="probForm">
            </c:otherwise>
            </c:choose>
            <h2>Create a Problem</h2>
            <p>Items marked with an asterix (<span class="asterix">*</span>) are required.</p>
            <html:form action="problems/save" method="POST" onsubmit="return validateProblem(this);">
            <html:hidden property="id" />
            <html:hidden property="active" value="1"/>
            <html:hidden property="patientId" value="${zeprs_session.sessionPatient.id}"  />
            <html:hidden property="pregnancyId" value="${zeprs_session.sessionPatient.currentPregnancyId}"/>
            <div class="row">
            <span class="label"><bean:message key="labels.problem.problemName"/>:&nbsp;<span class="asterix">*</span></span><span
            class="formw"><html:text property="problemName" size="30"/></span>
            </div>
            <div class="row">
            <span class="label"><bean:message key="labels.problem.onsetDate"/>: </span><span
            class="formw">
            <table>
            <tr>
            <td><zeprs:date_visit_problem/></td>
            </tr>
            </table>
            </span>
            </div>
            <div class="row">
            <span class="label"><bean:message key="labels.problem.commentText" />: </span><span
            class="formw"><html:textarea property="commentText" cols="20"/></span>
            </div>
            <div class="row">
            <span class="label"><bean:message key="labels.problem.actionPlan"/>: </span><span
            class="formw"><html:textarea property="actionPlan" cols="20"/></span>
            </div>
            <div class="row">
            <html:submit value="Save" /></p>
            </div>
            </html:form>
            </div>
            <div id="commentForm" style="visibility:hidden;">
            <h2>Add a Comment</h2>
            <p>Items marked with an asterix (<span class="asterix">*</span>) are required.</p>
            <html:form action="problems/comment/save" method="POST" onsubmit="return validateComment(this);">
            <html:hidden property="id" />
            <html:hidden property="patientId" value="${zeprs_session.sessionPatient.id}"/>
            <html:hidden property="pregnancyId" value="${zeprs_session.sessionPatient.currentPregnancyId}"/>
            <%--<logic:present name="problemId">
                <html:hidden property="problemId" value="${problemId}"/>
            </logic:present>--%>
            <c:choose>
            <c:when test="${problemType=='problem'}">
                <html:hidden property="problemId" value="${problemId}"/>
            </c:when>
            <c:when test="${problemType=='outcome'}">
                <html:hidden property="outcomeId" value="${problemId}"/>
            </c:when>
            </c:choose>
            <html:hidden property="problemType" value="${problemType}"/>
            <html:hidden property="status" value="${status}"/>
            <div class="row">
            <span class="label"><bean:message key="labels.problem.commentText" />:&nbsp;<span class="asterix">*</span> </span><span
            class="formw"><html:textarea property="commentText" cols="20"/></span>
            </div>
            <div class="row">
            <span class="label"><bean:message key="labels.problem.actionPlan"/>: </span><span
            class="formw"><html:textarea property="actionPlan" cols="20"/></span>
            </div>
            <div class="row">
            <html:submit value="Save" />
            </div>
            </html:form>
            </div>
        </c:when>
        <c:otherwise>

            <c:url value="problem.do"  var="commentUrl">
                <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
                <logic:present name="problemId">
                <c:param name="problemId" value="${problemId}"/>
                </logic:present>
                <c:param name="problemType" value="comment"/>
                <c:param name="mode" value="comment"/>
            </c:url>

            <c:choose>
                <c:when test="${!empty problemId}">
                <div id="editprobForm">
                <h2>Edit this Problem</h2>
                <p>${currproblemName}</p>
                </c:when>
                <%--<c:when test="${!empty problemId && longTermProblem == true}">
                <div id="editprobForm">
                <h2>Current Problem</h2>
                <p>${currproblemName}</p>
                </c:when>--%>
                <c:when test="${!empty problemId}">
                <div id="editprobForm">
                <h2>Edit this Problem</h2>
                <p>${currproblemName}</p>
                </c:when>
                <c:otherwise>
                 <div id="probForm">
                <h2>Create a Problem</h2>
                </c:otherwise>
            </c:choose>

            <c:if test="${empty problemType}">
            <c:set var="problemType" value="problem"/>
            </c:if>
            <c:choose>
            <c:when test="${problemType=='problem'}">
            <p>Items marked with an asterix (<span class="asterix">*</span>) are required.</p>
                <html:form action="problems/correct" method="POST" onsubmit="return validateProblem(this);">
                <html:hidden property="id" value="${problemId}"/>
                <html:hidden property="patientId" value="${zeprs_session.sessionPatient.id}"/>
                <html:hidden property="problemType" value="${problemType}"/>
                <html:hidden property="pregnancyId" value="${zeprs_session.sessionPatient.currentPregnancyId}"/>
                <div class="row">
                <span class="label"><bean:message key="labels.problem.problemName"/>:&nbsp;<span class="asterix">*</span> </span><span
                class="formw"><html:text property="problemName" size="30" value="${currproblemName}" /></span>
                </div>
                <c:choose>
                    <c:when test="${empty problemId}">
                        <html:hidden property="active" value="1"/>
                    </c:when>
                    <c:otherwise>
                        <div class="row">
                        <span class="label"><bean:message key="labels.problem.active"/>:</span><span class="formw">
                        <c:choose>
                            <c:when test="${editStatus==true}">
                                <input type="radio" name="active" value="1" checked="checked"/>Active
                                <input type="radio" name="active" value="0" />Inactive
                            </c:when>
                            <c:otherwise>
                                <input type="radio" name="active" value="1"/>Active
                                <input type="radio" name="active" value="0" checked="checked"/>Inactive
                            </c:otherwise>
                        </c:choose>
                        </span>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="row">
                <span class="label"><bean:message key="labels.problem.onsetDate"/>: </span><span
                class="formw">
                <table>
                <tr>
                <td><zeprs:date_visit_problem onsetDateValue="${curronsetDate}"/></td>
                </tr>
                </table>
                </span>
                </div>
                <c:if test="${empty problemId}">
                    <div class="row">
                    <span class="label"><bean:message key="labels.problem.commentText"/>: </span><span
                    class="formw"><html:textarea property="commentText" cols="20"/></span>
                    </div>
                    <div class="row">
                    <span class="label"><bean:message key="labels.problem.actionPlan"/>: </span><span
                    class="formw"><html:textarea property="actionPlan"  cols="20"/></span>
                    </div>
                </c:if>
                <div class="row" align="right">
                <html:submit value="Save" />
                </div>
                </html:form>
            </c:when>
            <c:when test="${problemType=='outcome'}">
                <html:form action="outcome/correct" method="POST" onsubmit="return validateComment(this);">
                <html:hidden property="id" value="${problemId}"/>
                <html:hidden property="patientId" value="${zeprs_session.sessionPatient.id}"/>
                <html:hidden property="problemType" value="${problemType}"/>
                <html:hidden property="pregnancyId" value="${zeprs_session.sessionPatient.currentPregnancyId}"/>
                <div class="row">
                    <span class="label"><bean:message key="labels.problem.active"/>: </span>
                    <span class="formw">
                        <c:choose>
                        <c:when test="${curractive==true}">
                        <input type="radio" name="active" value="1" checked="checked"/>Active
                        <input type="radio" name="active" value="0" />Inactive
                        </c:when>
                        <c:otherwise>
                        <input type="radio" name="active" value="1"/>Active
                        <input type="radio" name="active" value="0" checked="checked"/>Inactive
                        </c:otherwise>
                        </c:choose>
                        <html:submit value="Save" />
                    </span>

                <%--<div class="row">
                <span class="label"><bean:message key="labels.problem.commentText"/>: </span><span
                class="formw"><html:textarea property="commentText" cols="20"/></span>
                </div>
                <div class="row">
                <span class="label"><bean:message key="labels.problem.actionPlan"/>: </span><span
                class="formw"><html:textarea property="actionPlan"  cols="20"/></span>
                </div>--%>
                <div class="row" align="right">
                    <p>&nbsp;</p>
                    <p>&nbsp;</p>
               <%-- <html:submit value="Save" />--%>
                </div>
                </html:form>
                </div>
            </c:when>
            </c:choose>
            </div>
            <c:if test="${!empty problemId}">
                <div id="commentForm">
                <h2>Add a Comment</h2>
                <html:form action="problems/comment/save" method="POST" onsubmit="return validateComment(this);">
                <html:hidden property="id" />
                <html:hidden property="patientId" value="${zeprs_session.sessionPatient.id}"/>
                <html:hidden property="pregnancyId" value="${zeprs_session.sessionPatient.currentPregnancyId}"/>
                <%--<logic:present name="problemId">
                    <html:hidden property="problemId" value="${problemId}"/>
                </logic:present>--%>
                <c:choose>
                <c:when test="${problemType=='problem'}">
                    <html:hidden property="problemId" value="${problemId}"/>
                </c:when>
                <c:when test="${problemType=='outcome'}">
                    <html:hidden property="outcomeId" value="${problemId}"/>
                </c:when>
                </c:choose>
                <html:hidden property="problemType" value="${problemType}"/>
                <html:hidden property="status" value="${status}"/>
                <div class="row">
                <span class="label"><bean:message key="labels.problem.commentText" />: <span class="asterix">*</span></span><span
                class="formw"><html:textarea property="commentText" cols="20"/></span>
                </div>
                <div class="row">
                <span class="label"><bean:message key="labels.problem.actionPlan"/>: </span><span
                class="formw"><html:textarea property="actionPlan" cols="20"/></span>
                </div>
                <div class="row" align="right">
                <html:submit value="Save"/>
                </div>
                </html:form>
                </div>
            </c:if>
        </c:otherwise>
    </c:choose>

</div>
<script type="text/javascript" language="Javascript1.1" src="/zeprs/js/validateProbs.js">
</script>
<script type="text/javascript" language="Javascript1.1" src="/zeprs/js/validateComment.js">
</script>
<%--<html:javascript formName="problem" src="/zeprs/js/validateProbs.js" dynamicJavascript="false" staticJavascript="true" />

<html:javascript formName="comment" src="/zeprs/js/validateOutcomes.js" dynamicJavascript="false" staticJavascript="true" />--%>

