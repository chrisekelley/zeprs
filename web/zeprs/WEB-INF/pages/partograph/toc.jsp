<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="partograph.do" var="parto1">
        <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
        <c:param name="page" value="1"/>
    </c:url>
<c:url value="partograph.do" var="vaginal">
    <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
    <c:param name="exam" value="vaginal"/>
</c:url>
<c:url value="partograph.do" var="drugs">
    <c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
    <c:param name="exam" value="drugs"/>
</c:url>

<p>
<span class="partoLinks">
<a href='<c:out value="/zeprs/${parto1}"/>'>Main</a> |
<a href='<c:out value="/zeprs/${vaginal}"/>'>Vaginal Exam</a> |
<a href='<c:out value="/zeprs/${drugs}"/>'>Drugs Dispensed</a>
</span></p>