<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />
${field.label}: <c:if test='${field.required}'><span class="asterix">*</span> </c:if>
<span id="spanother"></span>
<br>
<select id="drug1" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <option value="AZT">AZT</option>
    <option value="3TC">3TC</option>
    <option value="EFV">EFV</option>
    <option value="NVP">NVP</option>
    <option value="D4T">D4T</option>
    <option value="D4T">ABC</option>
    <option value="LPV/r iii">LPV/r iii</option>
</select>
<select id="drug1num" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <c:forEach begin="10" end="90" step="10" var="tens" >
    <option value="${tens}">${tens}</option>
    </c:forEach>
    <c:forEach begin="100" end="1500" step="50" var="fifties" >
    <option value="${fifties}">${fifties}</option>
    </c:forEach>
</select>
<select id="drug1od" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <option value="BD">BD</option>
    <option value="OD">OD</option>
    <option value="TDS">TDS</option>
</select> +
<select id="drug2" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <option value="AZT">AZT</option>
    <option value="3TC">3TC</option>
    <option value="EFV">EFV</option>
    <option value="NVP">NVP</option>
    <option value="D4T">D4T</option>
</select>
<select id="drug2num" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <c:forEach begin="10" end="90" step="10" var="tens" >
    <option value="${tens}">${tens}</option>
    </c:forEach>
    <c:forEach begin="100" end="1500" step="50" var="fifties" >
    <option value="${fifties}">${fifties}</option>
    </c:forEach>
</select>
<select id="drug2od" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <option value="BD">BD</option>
    <option value="OD">OD</option>
    <option value="TDS">TDS</option>
</select> +
<select id="drug3" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <option value="AZT">AZT</option>
    <option value="3TC">3TC</option>
    <option value="EFV">EFV</option>
    <option value="NVP">NVP</option>
    <option value="D4T">D4T</option>
</select>
<select id="drug3num" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <c:forEach begin="10" end="90" step="10" var="tens" >
    <option value="${tens}">${tens}</option>
    </c:forEach>
    <c:forEach begin="100" end="1500" step="50" var="fifties" >
    <option value="${fifties}">${fifties}</option>
    </c:forEach>
</select>
<select id="drug3od" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <option value="BD">BD</option>
    <option value="OD">OD</option>
    <option value="TDS">TDS</option>
</select> +
<select id="drug4" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <option value="AZT">AZT</option>
    <option value="3TC">3TC</option>
    <option value="EFV">EFV</option>
    <option value="NVP">NVP</option>
    <option value="D4T">D4T</option>
</select>
<select id="drug4num" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <c:forEach begin="10" end="90" step="10" var="tens" >
    <option value="${tens}">${tens}</option>
    </c:forEach>
    <c:forEach begin="100" end="1500" step="50" var="fifties" >
    <option value="${fifties}">${fifties}</option>
    </c:forEach>
</select>
<select id="drug4od" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <option value="BD">BD</option>
    <option value="OD">OD</option>
    <option value="TDS">TDS</option>
</select> + <br/>
<select id="drug5" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <option value="AZT">AZT</option>
    <option value="3TC">3TC</option>
    <option value="EFV">EFV</option>
    <option value="NVP">NVP</option>
    <option value="D4T">D4T</option>
</select>
<select id="drug5num" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <c:forEach begin="10" end="90" step="10" var="tens" >
    <option value="${tens}">${tens}</option>
    </c:forEach>
    <c:forEach begin="100" end="1500" step="50" var="fifties" >
    <option value="${fifties}">${fifties}</option>
    </c:forEach>
</select>
<select id="drug5od" onchange="calcOtherRegimen()">
    <option value="">select</option>
    <option value="BD">BD</option>
    <option value="OD">OD</option>
    <option value="TDS">TDS</option>
</select>

<html:hidden styleId="other" property="field${field.id}"/>
