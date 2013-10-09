<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<%@ attribute name="yearList" required="false" type="java.util.ArrayList" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<c:set var="field" value="${pageItem.form_field}" />
<html:select property="field${field.id}">
    <html:option value=""> -- </html:option>
    <html:option value="6">< 12</html:option>
    <c:forEach begin="12" end="55" var="item" varStatus="status">
       <html:option value="${item}">${item}</html:option>
    </c:forEach>
</html:select>