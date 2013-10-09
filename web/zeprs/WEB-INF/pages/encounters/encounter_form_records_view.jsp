<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="pos" value="0"/>
<c:set var="tblItem" value="0"/>
<c:set var="tblCols" value="0"/>
<c:set var="tdBackgroundColor" value="#fff"/>
<c:set var="collapsing" value="0"/>
<c:set var="tdClass" value="enhancedtabletighterCell"/>
<c:set var="dataEntry" value="1"/>

<script language="JavaScript" type="text/javascript" src="/zeprs/dwr/util.js;jsessionid=${pageContext.request.session.id}"></script>
<script language="JavaScript" type="text/javascript" src="/zeprs/js/engine2.jsp;jsessionid=${pageContext.request.session.id}"></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwr/interface/WidgetDisplay.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type='text/javascript' src='/zeprs/dwr/interface/Encounter.js;jsessionid=${pageContext.request.session.id}'></script>
<script language="JavaScript" type="text/javascript" src="/zeprs/js/dwr-generic.js;jsessionid=${pageContext.request.session.id}"></script>
<script language="JavaScript" type="text/javascript" src="/zeprs/js/dwr-display.js;jsessionid=${pageContext.request.session.id}"></script>

<table class="enhancedtable">
   <tr>
<%-- loop through the pageItems and build the chart--%>
<c:set var="numRows" value="0"/>
<c:forEach var="pageItem" begin="0" items="${chartForm.pageItems}" varStatus="lineInfo">
    <c:set var="field" value="${pageItem.form_field}" />
    <c:set var="currentField" value="${field.id}" scope="request"/>
    <c:if test='${pageItem.form_field.enabled ==true && pageItem.form_field.type != "Display"}'>
        <c:choose>
        <c:when test='${pageItem.inputType=="yesno_dwr"}'>
        <c:set var="revealType" value="Span"/>
        </c:when>
        <c:otherwise>
        <c:set var="revealType" value="Field"/>
        </c:otherwise>
        </c:choose>
            <c:if test="${(pageItem.id != '3872') && (pageItem.id != '4164') && (pageItem.inputType != 'multiselect_enum')}">
                <th id="field${field.id}Cell${pos}" valign="middle" class="enhancedtabletighterCell"><strong>${field.label}</strong></th>
            </c:if>
    </c:if>
</c:forEach>

</tr>
    <c:forEach items="${chart}" var="encounter" varStatus="item">
        <tr>
            <c:choose>
                <c:when test="${! empty encounter}">
                <c:set var="pos" value="${encounter.id}"/>
                </c:when>
                <c:otherwise>
                <c:set var="pos" value="0"/>
                </c:otherwise>
            </c:choose>
            <c:if test="${encounter.formId == '80' && encounter.field1616 == '1'}">
                <c:set var="tdClass" value="problemCell"/>
            </c:if>
            <c:set var="numRows" value="${item.index+1}"/>
            <c:set var="currentEncounterId" value="${encounter.id}" scope="request"/>

            <c:forEach var="pageItem" begin="0" items="${chartForm.pageItems}" varStatus="lineInfo">
                <c:if test='${pageItem.form_field.enabled ==true && pageItem.form_field.type != "Display" && pageItem.inputType != "multiselect_enum"}'>
                    <c:set var="field" value="${pageItem.form_field}"/>
                    <c:set var="currentField" value="${field.id}" scope="request"/>
                    <c:if test="${(pageItem.id != '3872') && (pageItem.id != '4164')}"><%-- don't display second results or cd4 count --%>

                        <c:set var="value1858" value=""/>
                        <c:set var="value2004" value=""/>

                        <td id="cell${currentEncounterId}.${field.id}" valign="middle" class="${tdClass}">

                        <logic:notEmpty name="encounter" property="encounterMap.field${field.id}">
                            <bean:define id="thisValue" name="encounter" property="encounterMap.field${field.id}" />

                            <c:choose>
                                <c:when test="${! empty thisValue}">
                                <span id="value${currentEncounterId}.${field.id}">${thisValue}</span>
                                <span id="widget${currentEncounterId}.${field.id}"></span>
                                </c:when>
                                <c:otherwise>
                                <span id="value${currentEncounterId}.${field.id}"></span>
                                <span id="widget${currentEncounterId}.${field.id}"></span>
                                </c:otherwise>
                            </c:choose>
                        </logic:notEmpty>
                        <logic:empty name="encounter" property="encounterMap.field${field.id}">
                            <span id="value${currentEncounterId}.${field.id}"></span>
                            <span id="widget${currentEncounterId}.${field.id}"></span>
                        </logic:empty>
                        </td>
                    </c:if>
                </c:if>
             <c:set var="tdClass" value="enhancedtabletighterCell"/>
            </c:forEach>
        </tr>
        </c:forEach>
</table>
<p>&nbsp;</p>
