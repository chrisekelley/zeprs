<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri= "/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<%@ attribute name="multiValues" required="true" type="java.util.ArrayList" %>
<c:set var="field" value="${pageItem.form_field}" />
<c:set var="numFields" value="${pageItem.size}"/>
<%--<c:set var="listField" value="${field.enumerations}" scope="request"/>--%>
${field.label}: <c:if test='${field.required}'><span class="asterix">*</span> </c:if><br>
<input type="hidden" name="selectionList${field.id}" id="selectionList${field.id}"/>
<table>
    <tr>
        <td valign="top" class="formrowlabel">Choices:</td>
        <td valign="top">&nbsp;</td>
        <td valign="top" class="formrowlabel">Current Selections:</td>
    </tr>
    <tr>
        <td valign="top"> <select size="7" name="_allitems${field.id}" id="_allitems${field.id}">
            <c:forEach items="${field.enumerations}" var="item">
            <option value="${item.id}">${item.enumeration}</option>
            </c:forEach>
            </select>
        </td>
        <td valign="middle"><p>Select up to ${numFields} items. </p>
            <html:button property="_add" value=">> Add Item >>" onclick="addItem(${field.id},${numFields})" /><br>
            <html:button property="_add" value="<< Remove <<" onclick="removeItem(${field.id},${numFields})"/><br>
        </td>
        <td valign="top">
            <select size="7" name="_items${field.id}" id="_items${field.id}">

            <c:forEach var="multiValue" begin="0" items="${multiValues}" varStatus="lineInfo">

            <logic:iterate id="item" name="field" property="enumerations">
                    <c:if test='${item.id==multiValue}'>
                        <option value="${item.id}">${item.enumeration}</option>
                    </c:if>
                </logic:iterate>

            </c:forEach>

            <%--<c:forEach var="fpageItem" begin="0" items="${pageItems}" varStatus="lineItem">
                <c:choose>
                <c:when test="${ (fpageItem.inputType=='currentMedicine') || (fpageItem.inputType=='multiselect_item') }">
                <bean:define id="thisItem" name="form${encounterForm.id}" property="field${field.id}"/>
                <logic:iterate id="item" name="field" property="enumerations">
                    <c:if test='${item.id==thisItem}'>
                        <option value="${item.id}">${item.enumeration}</option>
                    </c:if>
                </logic:iterate>
                </c:when>
                </c:choose>
            </c:forEach>--%>
            </select>
        </td>
    </tr>
</table>
