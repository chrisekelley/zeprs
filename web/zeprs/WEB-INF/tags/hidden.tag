<%@ tag import="org.cidrz.webapp.dynasite.session.SessionUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<jsp:useBean id="now" class="java.util.Date" />
<c:set var="field" value="${pageItem.form_field}" />
<%
  String siteId = SessionUtil.getInstance(session).getClientSettings().getSiteId().toString();
  request.setAttribute("siteId",siteId);
%>

<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${now}" var="daynow" />
<c:choose>
     <c:when test='${pageItem.inputType=="hidden-empty"}'>
        <html:hidden property="field${field.id}"/>
    </c:when>
    <c:when test='${pageItem.inputType=="hidden-1"}'>
        <html:hidden property="field${field.id}" value="1"/>
    </c:when>
    <c:when test='${pageItem.inputType=="hidden-site"}'>
        <html:hidden property="field${field.id}" value="${siteId}"/>
    </c:when>
    <c:when test='${pageItem.inputType=="hidden-district"}'>
        <html:hidden property="field${field.id}" value="5040"/>
    </c:when>
    <c:when test='${pageItem.inputType=="hidden-subsite"}'>
        <html:hidden property="field${field.id}" value="${subsiteId}"/>
    </c:when>
    <c:when test='${pageItem.inputType=="hidden-date4"}'>
        <html:hidden property="field${field.id}" value="${daynow}" />
    </c:when>
    <c:when test='${pageItem.inputType=="hidden-clinic-appt"}'>
        <html:hidden property="field${field.id}" value="1" />
    </c:when>
    <c:when test='${pageItem.inputType=="hidden-pharm-appt"}'>
        <html:hidden property="field${field.id}" value="2" />
    </c:when>
    <c:when test='${pageItem.inputType=="hidden-enrollment"}'>
        <c:choose>
            <c:when test='${param.id != null}'>
                <html:hidden property="field${field.id}" value="" />
            </c:when>
            <c:otherwise>
                <html:hidden property="field${field.id}"/>
            </c:otherwise>
        </c:choose>
    </c:when>
</c:choose>