<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
<a href="#" onclick="revealItem('${pageItem.visibleDependencies1}');" title="Click here to add ${field.label}">
<img border="0" src="/zeprs/images/plus.gif" id="plusminus">${field.label}</a>