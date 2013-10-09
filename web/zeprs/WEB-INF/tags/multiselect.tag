<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri= "/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<%@ attribute name="list" required="true" type="java.util.List" %>
<%@ attribute name="numFields" required="true" type="java.lang.Integer" %>
<c:set var="field" value="${pageItem.form_field}" />
${field.label}: <c:if test='${field.required}'><span class="asterix">*</span> </c:if><br>
<table>
    <tr>
        <td valign="top" class="formrowlabel">Current Selections:</td>
        <td valign="top">&nbsp;</td>
        <td valign="top" class="formrowlabel">Choices:</td>
    </tr>
    <tr>
        <td valign="top">
            <select size="10" name="_items">
            <c:forEach var="fpageItem" begin="0" items="${pageItems.form}" varStatus="lineItem">
                <c:set var="field" value="${fpageItem.form_field}" />
            <c:choose>
            <c:when test="${fpageItem.inputType=='currentMedicine'}">
            <bean:define id="thisItem" name="form${encounterForm.id}" property="field${field.id}"/>
            <logic:iterate id="item" name="list">
                <c:if test='${item.id==thisItem}'>
                    <option value="${item.id}">${item.name}</option>
                </c:if>
            </logic:iterate>
            </c:when>
            </c:choose>
            </c:forEach>
            </select>
        </td>
        <td valign="middle"><p>Select up to ${numFields} items. </p>
            <html:button property="_add" value="<< Add Item <<" onclick="addItem(_allitems,_items,${numFields})"/><br>
            <html:button property="_add" value=">> Remove >>" onclick="removeItem(_items,_allitems,${numFields})"/><br>
        </td>
        <td valign="top"> <select size="10" name="_allitems">
            <c:forEach items="${list}" var="item">
            <option value="${item.id}">${item.name}</option>
            </c:forEach>
            </select>
        </td>
    </tr>
</table>
