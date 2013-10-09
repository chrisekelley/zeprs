<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
${field.label}: <c:if test='${field.required}'><span class="asterix">*</span> </c:if>
<span id="spanother"></span>
<br>
<select id="drug1" onchange="calcOtherRegimenPaeds()">
    <option value="">select</option>
    <option value="AZT">AZT</option>
    <option value="3TC">3TC</option>
    <option value="EFV">EFV</option>
    <option value="NVP">NVP</option>
    <option value="D4T">D4T</option>
    <option value="D4T">ABC</option>
    <option value="LPV/r iii">LPV/r iii</option>
</select>
<select id="drug1od" onchange="calcOtherRegimenPaeds()">
    <option value="">select</option>
    <option value="BD">BD</option>
    <option value="OD">OD</option>
    <option value="TDS">TDS</option>
</select> +
<select id="drug2" onchange="calcOtherRegimenPaeds()">
    <option value="">select</option>
    <option value="AZT">AZT</option>
    <option value="3TC">3TC</option>
    <option value="EFV">EFV</option>
    <option value="NVP">NVP</option>
    <option value="D4T">D4T</option>
</select>
<select id="drug2od" onchange="calcOtherRegimenPaeds()">
    <option value="">select</option>
    <option value="BD">BD</option>
    <option value="OD">OD</option>
    <option value="TDS">TDS</option>
</select> +
<select id="drug3" onchange="calcOtherRegimenPaeds()">
    <option value="">select</option>
    <option value="AZT">AZT</option>
    <option value="3TC">3TC</option>
    <option value="EFV">EFV</option>
    <option value="NVP">NVP</option>
    <option value="D4T">D4T</option>
</select>
<select id="drug3od" onchange="calcOtherRegimenPaeds()">
    <option value="">select</option>
    <option value="BD">BD</option>
    <option value="OD">OD</option>
    <option value="TDS">TDS</option>
</select> +
<select id="drug4" onchange="calcOtherRegimenPaeds()">
    <option value="">select</option>
    <option value="AZT">AZT</option>
    <option value="3TC">3TC</option>
    <option value="EFV">EFV</option>
    <option value="NVP">NVP</option>
    <option value="D4T">D4T</option>
</select>
<select id="drug4od" onchange="calcOtherRegimenPaeds()">
    <option value="">select</option>
    <option value="BD">BD</option>
    <option value="OD">OD</option>
</select> + 
<select id="drug5" onchange="calcOtherRegimenPaeds()">
    <option value="">select</option>
    <option value="AZT">AZT</option>
    <option value="3TC">3TC</option>
    <option value="EFV">EFV</option>
    <option value="NVP">NVP</option>
    <option value="D4T">D4T</option>
</select>
<select id="drug5od" onchange="calcOtherRegimenPaeds()">
    <option value="">select</option>
    <option value="BD">BD</option>
    <option value="OD">OD</option>
</select>

<html:hidden styleId="other" property="field${field.id}"/>
