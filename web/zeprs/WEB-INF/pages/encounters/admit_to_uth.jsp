<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<jsp:useBean id="referral" scope="request" type="org.cidrz.webapp.dynasite.rules.Outcome" beanName="referral" />
<html:form action="admit.do" method="post">
    <table class="enhancedtable">
        <tr>
            <th>Referral</th>
            <th>UTH Admissions</th>
        </tr>
        <tr>
            <td>
                Date of Referral: <bean:write name="referral" property="created" format="dd MMM yyyy"/>
                <br/><bean:write name="referral" property="reason"/>
            </td>
            <logic:notEmpty name="referral" property="uthAdmissionDate">
                <td>
                    Date of Admission: <bean:write name="referral" property="uthAdmissionDate" format="dd MMM yyyy"/><br/>
                    Condition: <bean:write name="referral" property="arrivalCondition"/>
                    Ward: <c:choose>
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
            </logic:notEmpty>
            <c:choose>
                <c:when test="${zeprs_session.clientSettings.site.siteTypeId ==2}">
                    <logic:empty name="referral" property="uthAdmissionDate">
                        <input type="hidden" name="referralId" value="${param.referralId}"/>
                        <td>
                        <table style="border-collapse:inherit;">
                            <tr>
                                <td>Ward:</td>
                                <td>
                                    <select name="ward" title="UTH Ward">
                                        <option value="" selected="selected">No Information</option>
                                        <option value="2956">Outpatient Clinic</option>
                                        <c:choose>
                                            <c:when test="${empty zeprs_session.sessionPatient.parentId}">
                                                <option value="2916">Labor</option>
                                                <option value="2957">Gynecological</option>
                                                <option value="2914">Postnatal</option>
                                                <option value="2915">Antenatal</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="2921">NICU</option>
                                                <option value="2922">PEDS</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <option value="2958">Not Admitted</option>
                                    </select>
                                </td>
                                <td>Condition:</td>
                                <td>
                                    <select name="condition" title="Condition on Arrival">
                                        <option value="" selected="selected">No Information</option>
                                        <option value="Good">Good</option>
                                        <option value="Fair">Fair</option>
                                        <option value="Poor">Poor</option>
                                        <option value="Other">Other</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Firm:</td>
                                <td>
                                    <c:set var="firm" value="${zeprs_session.sessionPatient.firm}"/>
                                    <select name="firm" title="Firm">
                                        <option value="">No Information</option>
                                            <c:forEach items="A,B,C,D,E" var="thisFirm">
                                                <c:choose>
                                                    <c:when test="${thisFirm == firm}"><option value="${thisFirm}" selected="selected">${thisFirm}</option></c:when>
                                                    <c:otherwise><option value="${thisFirm}">${thisFirm}</option></c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                    </select>
                                </td>
                                <td colspan="2"><html:submit value="Submit"/></td>
                            </tr>
                        </table>
                        </td>
                    </logic:empty>
                </c:when>
                <c:otherwise>
                    <logic:empty name="referral" property="uthAdmissionDate">
                        <td><p>Patient has not yet been admitted to UTH.</p></td>
                    </logic:empty>
                </c:otherwise>
            </c:choose>

            <%--<c:if test="${! empty referral.uthAdmissionDate}"></c:if>--%>
        </tr>
    </table>

</html:form>