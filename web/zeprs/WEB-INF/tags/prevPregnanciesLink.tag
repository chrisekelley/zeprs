<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ attribute name="value" required="false" type="java.lang.String" %>
<jsp:useBean id="now" class="java.util.Date" />
<c:set var="field" value="${pageItem.form_field}" />
<c:url value="patientHome.do" var="url">
    <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
    <c:param name="pregnancyId" value="${value}"/>
</c:url>
<c:choose>
    <c:when test="${! empty value}"><a href='<c:out value="/zeprs/${url}"/>'><img src="/zeprs/images/icon_folder_small.gif" border="0"></a></c:when>
    <c:otherwise></c:otherwise>
</c:choose>
