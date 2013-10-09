<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ attribute name="pageItem" required="false" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
<jsp:useBean id="sites" scope="request" type="java.util.List" />
${field.label}:<br/>
<html:select property="field${field.id}" styleId="site${field.id}">
<option value="">-- Select --</option>
    <c:forEach var="site" begin="0" items="${sites}">
        <c:if test="${site.inactive != 1}">
        <html:option value="${site.id}">${site.name}</html:option>
        </c:if>
    </c:forEach>
</html:select>

