<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri= "/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
Uterus size in weeks: <br/>
<select id="uterusDropdown" name="field188">
    <option value=""> -- </option>
    <c:forEach begin="28" end="350" step="7" var="day">
        <c:set var="days" value="${day % 7}"/>
        <c:set var="weeks" value="${day/7}"/>
        <option value="${day}"><fmt:parseNumber value="${weeks}" integerOnly="true"/></option>
    </c:forEach>
</select>