<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
${field.label}: <c:if test='${field.required}'><span class="asterix">*</span> </c:if><br>
<html:radio value="1" property="field${field.id}">1</html:radio>
<html:radio value="2" property="field${field.id}">2</html:radio>
<html:radio value="3" property="field${field.id}">3</html:radio>
<html:radio value="4" property="field${field.id}">4</html:radio>
