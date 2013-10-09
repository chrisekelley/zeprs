<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>

<%@ attribute name="pos" required="false" type="java.lang.Integer" %>
<%@ attribute name="remoteClass" required="false" type="java.lang.String" %>
<%@ attribute name="classname" required="false" type="java.lang.String" %>
<%@ attribute name="propertyName" required="false" type="java.lang.String" %>
<%@ attribute name="patientId" required="false" type="java.lang.Integer" %>
<%@ attribute name="pregnancyId" required="false" type="java.lang.Integer" %>
<%@ attribute name="user" required="false" type="java.lang.String" %>
<%@ attribute name="siteId" required="false" type="java.lang.Integer" %>
<%@ attribute name="formId" required="false" type="java.lang.Integer" %>
<%@ attribute name="value" required="false" type="java.lang.String" %>
<%@ attribute name="renderedValue" required="false" type="java.lang.String" %>
<%@ attribute name="encounterId" required="false" type="java.lang.Integer" %>
<c:set var="field" value="${pageItem.form_field}" />
<c:set var="scriptName" value="replyfield${field.id}"/>

<c:set var="field" value="${pageItem.form_field}" />

<c:choose>
    <c:when test="${field.id == 1862}"> <%-- Phase of pregnancy--%>
        <logic:present name="zeprs_session" property="sessionPatient">
            <c:choose>
                <c:when test="${! empty zeprs_session.sessionPatient.parentId}"/>
                <c:otherwise>${field.label}: <c:if test='${field.required}'><span class="asterix">*</span></c:if><br>
                </c:otherwise>
            </c:choose>
        </logic:present>
        <logic:notPresent name="zeprs_session" property="sessionPatient">
              ${field.label}: <c:if test='${field.required}'><span class="asterix">*</span></c:if><br>
        </logic:notPresent>
    </c:when>
    <c:otherwise>${field.label}: <c:if test='${field.required}'><span class="asterix">*</span></c:if><br></c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${field.id == 1677}">
        <html:select property="field${field.id}" styleId="selectfield${field.id}" onchange="toggleFieldSafeMotherhood('dropdown', ${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', ${pageItem.visibleEnumIdTrigger2}, '${pageItem.visibleDependencies2}','${field.id}');" >
            <html:option value="">No Information</html:option>
                <c:forEach var="enum" begin="0" items="${field.enumerations}">
                    <c:if test='${enum.enabled ==true}'>
                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                    </c:if>
                </c:forEach>
        </html:select>
    </c:when>
    <c:when test="${field.id == 224}">
        <html:select property="field${field.id}" styleId="selectfield${field.id}" onchange="validateBP();" >
            <html:option value="">No Information</html:option>
                <c:forEach var="enum" begin="0" items="${field.enumerations}">
                    <c:if test='${enum.enabled ==true}'>
                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                    </c:if>
                </c:forEach>
        </html:select>
    </c:when>
    <c:when test="${field.id == 52}"><%-- Pregnancy Course--%>
        <html:select property="field${field.id}" styleId="selectfield${field.id}" onchange="pregCourseAbort();" >
            <html:option value="">No Information</html:option>
                <c:forEach var="enum" begin="0" items="${field.enumerations}">
                    <c:if test='${enum.enabled ==true}'>
                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                    </c:if>
                </c:forEach>
        </html:select>
    </c:when>
    <c:when test="${field.id == 225}">
        <html:select property="field${field.id}" styleId="selectfield${field.id}" onchange="validateBP();" >
            <html:option value="">No Information</html:option>
                <c:forEach var="enum" begin="0" items="${field.enumerations}">
                    <c:if test='${enum.enabled ==true}'>
                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                    </c:if>
                </c:forEach>
        </html:select>
    </c:when>
    <c:when test="${field.id == 1931}"> <%--Date of test field in Counseling visit form--%>
        <jsp:useBean id="now" class="java.util.Date"/>
        <fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow"/>
        <fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow"/>
        <fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow"/>
        <c:set var="theDate" value="${now}"/>
        <fmt:formatDate type="both" pattern="dd/MM/yyyy" value="${theDate}" var="nicedateVisit"/>
        <fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${theDate}" var="dbdateVisit"/>
        <html:select property="field${field.id}" styleId="selectfield${field.id}"
                     onchange="toggleField('dropdown', ${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}','${field.id}');insertDate(1865, '${nicedateVisit}', '${dbdateVisit}');">
            <html:option value="">No Information</html:option>
            <c:forEach var="enum" begin="0" items="${field.enumerations}">
                <c:if test='${enum.enabled ==true}'>
                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                </c:if>
            </c:forEach>
        </html:select>
    </c:when>

    <c:when test="${field.id == 1845}">
        <html:select property="field${field.id}" styleId="selectfield${field.id}" onchange="selectLabResults();" >
            <html:option value="">No Information</html:option>
                <c:forEach var="enum" begin="0" items="${field.enumerations}">
                    <c:if test='${enum.enabled ==true}'>
                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                    </c:if>
                </c:forEach>
        </html:select>
    </c:when>

    <c:when test="${field.id == 1854}">
        <html:select property="field${field.id}" styleId="selectfield${field.id}" onchange="selectDrugEnum();" >
            <html:option value="">No Information</html:option>
                <c:forEach var="enum" begin="0" items="${field.enumerations}">
                    <c:if test='${enum.enabled ==true}'>
                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                    </c:if>
                </c:forEach>
        </html:select>
    </c:when>
    <c:when test="${field.id == 1862}"> <%-- Phase of pregnancy--%>
        <logic:present name="zeprs_session" property="sessionPatient">
            <c:choose>
                <c:when test="${! empty zeprs_session.sessionPatient.parentId}">
                    <html:hidden property="field${field.id}"/>
                </c:when>
                <c:otherwise>
                    <html:select property="field${field.id}" styleId="selectfield${field.id}">
                        <html:option value="">No Information</html:option>
                        <c:forEach var="enum" begin="0" items="${field.enumerations}">
                            <c:if test='${enum.enabled ==true}'>
                                <html:option value="${enum.id}">${enum.enumeration}</html:option>
                            </c:if>
                        </c:forEach>
                    </html:select>
                </c:otherwise>
            </c:choose>
        </logic:present>
        <logic:notPresent name="zeprs_session" property="sessionPatient">
            <html:select property="field${field.id}" styleId="selectfield${field.id}">
                <html:option value="">No Information</html:option>
                <c:forEach var="enum" begin="0" items="${field.enumerations}">
                    <c:if test='${enum.enabled ==true}'>
                        <html:option value="${enum.id}">${enum.enumeration}</html:option>
                    </c:if>
                </c:forEach>
            </html:select>
        </logic:notPresent>
    </c:when>

    <c:when test="${field.id == 1266}">
        <!-- dependencies-->
         <html:select property="field${field.id}" styleId="selectfield${field.id}" onchange="toggleField3DepsChoice('dropdown', ${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', ${pageItem.visibleEnumIdTrigger2}, '${pageItem.visibleDependencies2}','2910','1841','${field.id}');" >
            <html:option value="">No Information</html:option>
                <c:forEach var="enum" begin="0" items="${field.enumerations}">
                    <c:if test='${enum.enabled ==true}'>
                        <c:choose>
                            <c:when test="${enum.enumeration == 'Admit to UTH'}">true
                                <c:if test="${zeprs_session.clientSettings.site.siteTypeId ==2}">
                                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <html:option value="${enum.id}">${enum.enumeration}</html:option>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </c:forEach>
        </html:select>
    </c:when>
        <c:when test="${field.id == 1998}">
        <html:select property="field${field.id}" styleId="selectfield${field.id}" onchange="selectPatientEnrolledDropdown();" >
            <html:option value="">No Information</html:option>
                <c:forEach var="enum" begin="0" items="${field.enumerations}">
                    <c:if test='${enum.enabled ==true}'>
                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                    </c:if>
                </c:forEach>
        </html:select>
    </c:when>

    <c:otherwise>
        <c:choose>
            <c:when test="${(pageItem.visibleEnumIdTrigger2 > 0)}">
                <html:select property="field${field.id}" styleId="selectfield${field.id}" onchange="toggleField2DepsChoice('dropdown', ${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', ${pageItem.visibleEnumIdTrigger2}, '${pageItem.visibleDependencies2}','${field.id}');" >
                    <html:option value="">No Information</html:option>
                        <c:forEach var="enum" begin="0" items="${field.enumerations}">
                            <c:if test='${enum.enabled ==true}'>
                                <c:choose>
                                    <c:when test="${enum.enumeration == 'Admit to UTH'}">
                                        <c:if test="${zeprs_session.clientSettings.site.siteTypeId ==2}">
                                            <html:option value="${enum.id}">${enum.enumeration}</html:option>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <html:option value="${enum.id}">${enum.enumeration}</html:option>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </c:forEach>
                </html:select>
            </c:when>
            <c:when test="${(pageItem.visibleEnumIdTrigger1 > 0)}">
                <html:select property="field${field.id}" styleId="selectfield${field.id}" onchange="toggleField('dropdown', ${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}','${field.id}');" >
                    <html:option value="">No Information</html:option>
                        <c:forEach var="enum" begin="0" items="${field.enumerations}">
                            <c:if test='${enum.enabled ==true}'>
                                <c:choose>
                                    <c:when test="${enum.enumeration == 'Admit to UTH'}">
                                        <c:if test="${zeprs_session.clientSettings.site.siteTypeId ==2}">
                                            <html:option value="${enum.id}">${enum.enumeration}</html:option>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <html:option value="${enum.id}">${enum.enumeration}</html:option>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </c:forEach>
                </html:select>
            </c:when>
            <c:otherwise>
                <html:select property="field${field.id}">
                <html:option value="">No Information</html:option>
                    <c:forEach var="enum" begin="0" items="${field.enumerations}">
                        <c:if test='${enum.enabled ==true}'>
                            <c:choose>
                                <c:when test="${enum.enumeration == 'Admit to UTH'}">
                                    <c:if test="${zeprs_session.clientSettings.site.siteTypeId ==2}">
                                        <html:option value="${enum.id}">${enum.enumeration}</html:option>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <html:option value="${enum.id}">${enum.enumeration}</html:option>
                                </c:otherwise>
                            </c:choose>
                         </c:if>
                    </c:forEach>
            </html:select>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
${field.units}