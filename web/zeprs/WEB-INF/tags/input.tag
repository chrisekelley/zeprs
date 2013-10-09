<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri= "/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<%@ attribute name="pos" required="false" type="java.lang.Integer" %>
<%@ attribute name="remoteClass" required="false" type="java.lang.String" %>
<%@ attribute name="classname" required="false" type="java.lang.String" %>
<%@ attribute name="propertyName" required="false" type="java.lang.String" %>
<%@ attribute name="patientId" required="false" type="java.lang.Integer" %>
<%@ attribute name="pregnancyId" required="false" type="java.lang.Integer" %>
<%@ attribute name="user" required="false" type="java.lang.String" %>
<%@ attribute name="siteId" required="false" type="java.lang.Integer" %>
<%@ attribute name="formId" required="false" type="java.lang.Integer" %>
<%@ attribute name="value" required="false" type="java.lang.String" %>
<%@ attribute name="renderedValue" required="false" type="java.lang.String" %>
<%@ attribute name="encounterId" required="false" type="java.lang.Integer" %>
<c:set var="field" value="${pageItem.form_field}" />
<bean:define id="valueFromDb" name="form${encounterForm.id}" property="field${field.id}"/>
${field.label}: <c:if test='${field.required}'><span class="asterix">*</span> </c:if><br>
<c:choose>
    <c:when test="${! empty value}">
    <span id="value${field.id}" onclick="display('${value}', '${pageItem.id}', '${field.id}', '${className}', '${pageItem.formId}', '${encounterId}')" >${renderedValue}</span>
    <span id="widget${field.id}"></span>
    </c:when>
<c:otherwise>
    <c:choose>
    <c:when test="${empty pageItem.size}">
    <html:text size="20" property="field${field.id}" styleId="field${field.id}"/> ${field.units}
    </c:when>
    <c:when test="${pageItem.maxlength==0}">
    <html:text size="20" property="field${field.id}" styleId="field${field.id}"/> ${field.units}
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${field.id == '1171'}">
                <c:choose>
                    <c:when test='${param.id != null}'>
                        <html:text size="${pageItem.size}" maxlength="${pageItem.maxlength}" property="field${field.id}" styleId="field${field.id}"/> ${field.units}
                   </c:when>
                   <c:otherwise>
                    <zeprs:patientid pageItem="${pageItem}"/>
                   </c:otherwise>
                </c:choose>
           </c:when>
           <c:otherwise>
               <input type="text" size="${pageItem.size}" maxlength="${pageItem.maxlength}" name="field${field.id}" id="field${field.id}" autocomplete="off" value="${valueFromDb}"/> ${field.units}
           </c:otherwise>
        </c:choose>
    </c:otherwise>
    </c:choose>
</c:otherwise>
</c:choose>
