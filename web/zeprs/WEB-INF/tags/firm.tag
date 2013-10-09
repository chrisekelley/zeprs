<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
${field.label}: <c:if test='${field.required}'><span class="asterix">*</span> </c:if><br>
<html:select property="field${field.id}">
    <html:option value="">No Information</html:option>
    <html:option value="A">A</html:option>
    <html:option value="B">B</html:option>
    <html:option value="C">C</html:option>
    <html:option value="D">D</html:option>
    <html:option value="E">E</html:option>
</html:select>