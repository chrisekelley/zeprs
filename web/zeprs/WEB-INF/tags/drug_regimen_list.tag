<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ attribute name="pageItem" required="false" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
<jsp:useBean id="drugRegimenList" scope="request" type="java.util.List" />
<select name="field${field.id}">
<option value="">-- Select --</option>
<c:forEach var="regimen" begin="0" items="${drugRegimenList}">
<option value="${regimen.id}">${regimen.name}</option>
</c:forEach>
<option value="1000">Other</option>
</select>

