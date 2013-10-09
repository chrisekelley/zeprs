<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
<select name="field${field.id}">
    <option value="" selected="selected"> -- </option>
    <option value="Occiput Posterior">OP</option>
    <option value="Right Occiput Posterior">ROP</option>
    <option value="Right Occiput Transverse">ROT</option>
    <option value="Right Occiput Anterior">ROA</option>
    <option value="Occiput Anterior">OA</option>
    <option value="Left Occiput Anterior">LOA</option>
    <option value="Left Occiput Transverse">LOT</option>
    <option value="Left Occiput Posterior">LOP</option>
</select>