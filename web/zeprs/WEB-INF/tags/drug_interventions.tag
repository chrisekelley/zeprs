<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ attribute name="pageItem" required="false" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}" />

<jsp:useBean id="drugInterventionEnums" scope="request" type="java.util.List" />
${field.label}:<br/>
<html:select property="field${field.id}" styleId="result${field.id}">
<option value="">-- Select Type of Drug Intervention first --</option>
</html:select>

<script type="text/javascript">
    function populate(fieldId) {
        var selbox = document.getElementById("result${field.id}");
        selbox.options.length = 0;
        <c:set var="currentFieldId" value="0"/>
    <c:forEach var="result" begin="0" items="${drugInterventionEnums}" varStatus="i">
        <c:choose>
            <c:when test="${result.fieldId == currentFieldId}">
              selbox.options[selbox.options.length] = new Option('${result.enumeration}','${result.id}');
            </c:when>
            <c:otherwise>
            <c:set var="currentFieldId" value="${result.fieldId}"/>

                <c:if test="${i.count != 1}">}</c:if>
               if (fieldId == ${result.fieldId}) {
                   selbox.options[selbox.options.length] = new Option('${result.enumeration}','${result.id}');
            </c:otherwise>
        </c:choose>
    </c:forEach>
                   }
    }
</script>