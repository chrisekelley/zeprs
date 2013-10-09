<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h2>Records from the Lab system (LIMS)</h2>
<table class="enhancedtable">
<tr>
<th>Test</th>
<th>Result</th>
<th>Unit</th>
<th>Reference Range</th>
<th>Status</th>
<th>Exceptions</th>
<c:forEach items="${limsResults}" var="limsResult" varStatus="item">
<tr>
<td>${limsResult.observationText} (${limsResult.observationIdentifier})</td>
<td>${limsResult.abnormalFlags} &nbsp; ${limsResult.observationValue}</td>
<td>${limsResult.units}</td>
<td>${limsResult.referencesRange}</td>
<td>${limsResult.exception_value}</td>
</tr>
</c:forEach>