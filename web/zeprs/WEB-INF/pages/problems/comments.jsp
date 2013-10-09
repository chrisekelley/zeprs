<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="fn" uri='/WEB-INF/fn.tld' %>

<c:if test="${! empty param.template}">
<c:set var="print" value="-print"/>
</c:if>

<div id="commentList${print}">
        <div id="commentListItems${print}">
            <table class="enhancedtable" width="302px">
                <tr>
                    <th colspan="4">Comments for:
                        <logic:present name="outcome">
                            <strong>${currproblemName}</strong>
                            <c:choose>
                                <c:when test="${currclass=='org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome'}">
                                    <html:link action="/form${currform}/new">(Form Request)</html:link>
                                </c:when>
                            </c:choose>
                        </logic:present>
                        <logic:notPresent name="outcome">
                            <strong>${currproblemName}</strong>
                            <br/>Date of Onset: ${curronsetDate}
                        </logic:notPresent>
                    </th>
                </tr>
                <tr>
                    <td class="enhancedtableheader">Date</td>
                    <td class="enhancedtableheader">Comment</td>
                    <td class="enhancedtableheader">Action/Plan</td>
                    <td class="enhancedtableheader">Staff</td>
                </tr>

                <logic:iterate id="comment" name="comments">
                <c:if test="${comment.id!=0}">
                <tr>
                    <td><fmt:formatDate type="both" pattern="dd MMM yy" value="${comment.lastModified}" /></td>
                    <c:choose>
                    <c:when test="${fn:length(comment.commentText) > 300}">
                    <td><span id="shortcomment${comment.id}" onclick="toggleProblemForms('longcomment${comment.id}','shortcomment${comment.id}')">${fn:substring(comment.commentText,0,300)} <a href="#" onclick="toggleProblemForms('longcomment${comment.id}','shortcomment${comment.id}')">More</a></span>
                    <span id="longcomment${comment.id}" style="position:relative;background:white;visibility:hidden;display:none" onclick="toggleProblemForms('shortcomment${comment.id}','longcomment${comment.id}')">${comment.commentText} <a href="#" onclick="toggleProblemForms('shortcomment${comment.id}','longcomment${comment.id}')">Less</a></span>
                    </td>
                    </c:when>
                    <c:otherwise>
                    <td>${comment.commentText}</td>
                    </c:otherwise>
                    </c:choose>
                    <c:choose>
                    <c:when test="${fn:length(comment.actionPlan) > 300}">
                    <td><span id="shortactionPlan${comment.id}" onclick="toggleProblemForms('longactionPlan${comment.id}','shortactionPlan${comment.id}')">${fn:substring(comment.actionPlan,0,300)} <a href="#" onclick="toggleProblemForms('longactionPlan${comment.id}','shortactionPlan${comment.id}')">More</a></span>
                    <span id="longactionPlan${comment.id}" style="position:relative;background:white;visibility:hidden;display:none" onclick="toggleProblemForms('shortactionPlan${comment.id}','longactionPlan${comment.id}')">${comment.actionPlan} <a href="#" onclick="toggleProblemForms('shortactionPlan${comment.id}','longactionPlan${comment.id}')">Less</a></span>
                    </td>
                    </c:when>
                    <c:otherwise>
                    <td>${comment.actionPlan}</td>
                    </c:otherwise>
                    </c:choose>
                    <td>${comment.lastModifiedBy}</td>
                </tr>
               </c:if>
                </logic:iterate>
            </table>

        </div>
    </div>
