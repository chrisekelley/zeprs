<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${(empty zeprs_session.sessionPatient.currentPregnancyId) || (!empty zeprs_session.sessionPatient.datePregnancyEnd)}">
<c:url value="patientHome.do" var="url"><c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/><c:param name="pregnancyId" value="-1"/></c:url>
    <p><a href='<c:out value="/zeprs/${url}"/>'>New Pregnancy</a></p>
</c:if>




