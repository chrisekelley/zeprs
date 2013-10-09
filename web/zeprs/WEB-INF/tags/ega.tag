<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri= "/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
EGA:<br/>
<div id="weeksPreg">
<input type=hidden name=calcYet id=calcYet value=0>
</div>
<input type=hidden name=weeks id=weeks size="3">
<%--
<html:text size="5" styleId="days" property="field${field.id}" onfocus="this.blur()" styleClass="disabled"/> days pregnant
--%>
<html:select property="field${field.id}" styleId="days">
    <option value=""> -- </option>
<c:forEach begin="1" end="350" step="1" var="day">
<c:set var="days"  value="${day % 7}" />
<c:set var="weeks" value="${day/7}" />
    <html:option  value="${day}"><fmt:parseNumber value="${weeks}" integerOnly="true" /> weeks, ${days}/ 7 days</html:option>
</c:forEach>
</html:select>