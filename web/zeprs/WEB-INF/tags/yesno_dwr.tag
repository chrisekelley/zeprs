<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri= "/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<%@ attribute name="pos" required="true" type="java.lang.Integer" %>
<%@ attribute name="remoteClass" required="true" type="java.lang.String" %>
<%@ attribute name="classname" required="true" type="java.lang.String" %>
<%@ attribute name="propertyName" required="true" type="java.lang.String" %>
<%@ attribute name="patientId" required="true" type="java.lang.Integer" %>
<%@ attribute name="pregnancyId" required="true" type="java.lang.Integer" %>
<%@ attribute name="user" required="true" type="java.lang.String" %>
<%@ attribute name="siteId" required="true" type="java.lang.Integer" %>
<%@ attribute name="value" required="false" type="java.lang.String" %>
<%@ attribute name="formId" required="true" type="java.lang.Integer" %>
<c:set var="field" value="${pageItem.form_field}" />
<c:set var="scriptName" value="replyfield${field.id}"/>
<c:choose>
    <c:when test="${value == true}">
    <span id="field${field.id}Results${pos}">Yes</span>
    <span id="field${field.id}Span${pos}" style="display:none;">
    <input type="radio" name="field${field.id}${pos}" id="field${field.id}YField${pos}" value="1" checked onchange="insertInputField('${remoteClass}', ${scriptName}, '${classname}','field${field.id}', ${pos},'field${field.id}YField${pos}',${patientId}, ${pregnancyId},'${user}',${siteId},${formId})"/><label for="field${field.id}Y">Yes</label>
    <input type="radio" name="field${field.id}${pos}" id="field${field.id}NField${pos}" value="0" onchange="insertInputField('${remoteClass}', ${scriptName}, '${classname}','field${field.id}', ${pos},'field${field.id}NField${pos}',${patientId}, ${pregnancyId},'${user}',${siteId},${formId})"/><label for="field${field.id}N">No</label>
        <input type="radio" name="field${field.id}${pos}" value=""/><label for="field${field.id}">N/A</label>
    </span>
    </c:when>
    <c:when test="${value == 'Yes'}">
    <span id="field${field.id}Results${pos}">Yes</span>
    <span id="field${field.id}Span${pos}" style="display:none;">
    <input type="radio" name="field${field.id}${pos}" id="field${field.id}YField${pos}" value="1" checked onchange="insertInputField('${remoteClass}', ${scriptName}, '${classname}','field${field.id}', ${pos},'field${field.id}YField${pos}',${patientId}, ${pregnancyId},'${user}',${siteId},${formId})"/><label for="field${field.id}Y">Yes</label>
    <input type="radio" name="field${field.id}${pos}" id="field${field.id}NField${pos}" value="0" onchange="insertInputField('${remoteClass}', ${scriptName}, '${classname}','field${field.id}', ${pos},'field${field.id}NField${pos}',${patientId}, ${pregnancyId},'${user}',${siteId},${formId})"/><label for="field${field.id}N">No</label>
        <input type="radio" name="field${field.id}${pos}" value=""/><label for="field${field.id}">N/A</label>
    </span>
    </c:when>
    <c:when test="${value == false}">
    <span id="field${field.id}Results${pos}">No</span>
    <span id="field${field.id}Span${pos}" style="display:none;">
    <input type="radio" name="field${field.id}${pos}" id="field${field.id}YField${pos}" value="1" onchange="insertInputField('${remoteClass}', ${scriptName}, '${classname}','field${field.id}', ${pos},'field${field.id}YField${pos}',${patientId}, ${pregnancyId},'${user}',${siteId},${formId})"/><label for="field${field.id}Y">Yes</label>
    <input type="radio" name="field${field.id}${pos}" id="field${field.id}NField${pos}" value="0" checked onchange="insertInputField('${remoteClass}', ${scriptName}, '${classname}','field${field.id}', ${pos},'field${field.id}NField${pos}',${patientId}, ${pregnancyId},'${user}',${siteId},${formId})"/><label for="field${field.id}N">No</label>
        <input type="radio" name="field${field.id}${pos}" value=""/><label for="field${field.id}">N/A</label>
    </span>
    </c:when>
    <c:when test="${value == 'No'}">
    <span id="field${field.id}Results${pos}">No</span>
    <span id="field${field.id}Span${pos}" style="display:none;">
    <input type="radio" name="field${field.id}${pos}" id="field${field.id}YField${pos}" value="1" onchange="insertInputField('${remoteClass}', ${scriptName}, '${classname}','field${field.id}', ${pos},'field${field.id}YField${pos}',${patientId}, ${pregnancyId},'${user}',${siteId},${formId})"/><label for="field${field.id}Y">Yes</label>
    <input type="radio" name="field${field.id}${pos}" id="field${field.id}NField${pos}" value="0" checked onchange="insertInputField('${remoteClass}', ${scriptName}, '${classname}','field${field.id}', ${pos},'field${field.id}NField${pos}',${patientId}, ${pregnancyId},'${user}',${siteId},${formId})"/><label for="field${field.id}N">No</label>
        <input type="radio" name="field${field.id}${pos}" value=""/><label for="field${field.id}">N/A</label>
    </span>
    </c:when>
    <c:otherwise>
    <span id="field${field.id}Results${pos}"></span>
    <span id="field${field.id}Span${pos}" style="display:none;">
    <input type="radio" name="field${field.id}${pos}" id="field${field.id}YField${pos}" value="1" onchange="insertInputField('${remoteClass}', ${scriptName}, '${classname}','field${field.id}', ${pos},'field${field.id}YField${pos}',${patientId}, ${pregnancyId},'${user}',${siteId},${formId})"/><label for="field${field.id}Y">Yes</label>
    <input type="radio" name="field${field.id}${pos}" id="field${field.id}NField${pos}" value="0" onchange="insertInputField('${remoteClass}', ${scriptName}, '${classname}','field${field.id}', ${pos},'field${field.id}NField${pos}',${patientId}, ${pregnancyId},'${user}',${siteId},${formId})"/><label for="field${field.id}N">No</label>
        <input type="radio" name="field${field.id}${pos}" value=""/><label for="field${field.id}">N/A</label>
    </span>
    </c:otherwise>
</c:choose>
<script type='text/javascript'>
    var ${scriptName} = function(data)
    {
    var dvals = data.split("=");
    var key = "field${field.id}Results" + dvals[1];
    var value =dvals[0];
    var itemValue = document.getElementById(key);

    if (value == 1) {
    alert("Yes value: " + value);
    itemValue.innerHTML = "Yes";
    }  else if (value == 0) {
    alert("No value: " + value);
    itemValue.innerHTML = "No";
    } else {
    alert("hmmm value: " + value);
    itemValue.innerHTML = value;
    }
    var input =  document.getElementById("field${field.id}Span" + dvals[1]);
    input.style.display = "none";
    input.style.visibility = "hidden";
    }
</script>



