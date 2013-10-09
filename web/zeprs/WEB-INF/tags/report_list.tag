<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="reports" required="true" type="java.util.List" %>
<%@ attribute name="allReports" required="true" type="java.util.List" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<h3>Reports:</h3>

<c:if test="${!empty reports}">
<p>This field persists to the following reports:</p>
<table border="1" cellpadding="1" cellspacing="1">
<tr class = "patientrowheader">
<th>Report</th><th>Property</th><th>Shared</th>
</tr>
<logic:iterate id="report" name="reports" >
<tr><td><bean:write name="report" property="liveReport.reportName" /></td>
<td><bean:write name="report" property="reportProperty"/></td>
<td><bean:write name="report" property="shared"/></td></tr>
</logic:iterate>
</c:if>
</table>
<p>Enter the corresponding property and report class you want to use.</p>
<html:form action="admin/reportField/save" method="POST">
<html:hidden property="id" />
<input type="hidden" name="pageItem" value="${pageItem.id}"/>
<input type="hidden" name="formField" value="${pageItem.form_field.id}"/>
<input type="hidden" name="formId" value="${pageItem.form.id}"/>
<table border="1" cellpadding="1" cellspacing="1">
<tr class = "patientrowheader">
<th>Report</th><th>Property</th><th>Shared</th>
</tr>
<tr>
<td><select name="liveReport">
    <logic:iterate id="report" name="allReports">
    <option value="${report.id}">${report.reportName}</option>
    </logic:iterate>
</select></td>
<td><input type="text" name="reportProperty"/></td>
<td><input type="checkbox" name="shared"></td>
</tr>
</table>

<%--
<html:hidden name="formField" property="pageItem"/>
--%>
<html:submit value="Save New Report Field" onclick="bCancel=false;"/>
<%--
<a href="${pageContext.request.contextPath}/admin/reportField/new.do;jsessionid=${pageContext.request.session.id}?report=${report.id}">Add field to report</a>
--%>
</html:form>
