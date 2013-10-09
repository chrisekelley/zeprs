<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="provider" required="true" type="org.cidrz.webapp.dynasite.rules.RuleProvider" %>
<%@ attribute name="pageItem" required="false" type="java.lang.String" %>
<%@ attribute name="formId" required="true" type="java.lang.String" %>

<h3><bean:message key="headings.admin.rule.rules"/>:</h3>
<table border="1" cellpadding="1" cellspacing="1">
<tr class = "patientrowheader">
<th><bean:message key="headings.admin.rule.ruleClass"/></th>
<th><bean:message key="headings.admin.rule.outcomeClass"/></th>
<th>&nbsp;</th>
</tr>
<c:if test="${!empty provider}">
<logic:iterate id="rule" name="provider" property="ruleDefinitions" >
<c:url value="admin/rule/edit.do" var="url">
    <c:param name="pageItem" value="${pageItem}"/>
    <c:param name="formId" value="${formId}"/>
    <c:param name="id" value="${rule.id}"/>
</c:url>
<tr>
<td><bean:write name="rule" property="ruleClass"/></td>
<td><bean:write name="rule" property="outcomeClass"/></td>
<td><a href='<c:out value="/zeprs/${url}"/>'>Edit</a></td>
</tr>
</logic:iterate>
</c:if>
</table>
<c:if test="${!empty provider}">
<a href="${pageContext.request.contextPath}/admin/rule/new.do;jsessionid=${pageContext.request.session.id}?providerId=${provider.id}&providerClass=${provider.class.name}&formId=${formId}&pageItem=${pageItem}"><bean:message key="labels.admin.rule.addRule"/></a>
</c:if>