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
<c:if test="${! empty arvChartItems}">
    <c:set var="chartItems" value="arvChartItems"/>
    <c:set var="encounterForm" value="arvEncounterForm"/>
</c:if>
<script language="JavaScript" type="text/javascript" src="/${appName}/dwr/util.js;jsessionid=${pageContext.request.session.id}"></script>
<script language="JavaScript" type="text/javascript" src="/${appName}/js/engine2.jsp;jsessionid=${pageContext.request.session.id}"></script>
<script type='text/javascript' src='/${appName}/dwr/interface/WidgetDisplay.js;jsessionid=${pageContext.request.session.id}'></script>
<script type='text/javascript' src='/${appName}/dwr/interface/Encounter.js;jsessionid=${pageContext.request.session.id}'></script>
<script type="text/javascript" src="/${appName}/js/dwr-generic.js;jsessionid=${pageContext.request.session.id}"></script>
<script type="text/javascript" src="/${appName}/js/dwr-display.js;jsessionid=${pageContext.request.session.id}"></script>
<c:set var="dataEntry" value="1"/>
<table class="enhancedtable">
   <tr>
<%-- loop through the pageItems and build the chart--%>
<c:set var="numRows" value="0"/>
<logic:present role="DELETE_ARCHIVE_PATIENT_RECORDS">
    <th class="enhancedtabletighterCell"><strong>Del.</strong></th>
</logic:present>
<c:forEach var="pageItem" begin="0" items="${encounterForm.pageItems}" varStatus="lineInfo">
    <c:set var="field" value="${pageItem.form_field}" />
    <c:set var="currentField" value="${field.id}" scope="request"/>
    <c:if test='${pageItem.form_field.enabled ==true && pageItem.form_field.type != "Display"}'>
	    <c:if test='${pageItem.inputType != "hidden-no-listing"}'>
	        <c:choose>
	        <c:when test='${pageItem.inputType=="yesno_dwr"}'>
	        <c:set var="revealType" value="Span"/>
	        </c:when>
	        <c:otherwise>
	        <c:set var="revealType" value="Field"/>
	        </c:otherwise>
	        </c:choose>
			<c:if test="${(pageItem.id != '3872') && (pageItem.id != '4164') && (pageItem.id != '4220') && (pageItem.inputType != 'multiselect_enum') && (field.id !='2037')}">
			    <th id="${field.identifier}Cell${pos}" valign="middle" class="enhancedtabletighterCell"><strong><bean:message key="${encounterForm.classname}.${field.identifier}" bundle="${encounterForm.classname}Messages" /></strong></th>
			</c:if>
	    </c:if>
    </c:if>
</c:forEach>

</tr>
    <c:forEach items="${chartItems}" var="encounter" varStatus="item">
        <tr>
            <c:choose>
                <c:when test="${! empty encounter}">
                <c:set var="pos" value="${encounter.id}"/>
                </c:when>
                <c:otherwise>
                <c:set var="pos" value="0"/>
                </c:otherwise>
            </c:choose>
            <c:set var="numRows" value="${item.index+1}"/>
            <c:choose>
               	<c:when test="${encounterForm.id == 223}"><c:set var="currentEncounterId" value="${encounter.username}" scope="request"/></c:when>
               	<c:otherwise><c:set var="currentEncounterId" value="${encounter.id}" scope="request"/></c:otherwise>
            </c:choose>
            <logic:present role="DELETE_ARCHIVE_PATIENT_RECORDS">
                <c:url var="delUrl" value="admin/deleteEncounter.do">
               		<c:param name="encounterId" value="${encounter.id}"/>
               		<c:param name="formId" value="${encounterForm.id}"/>
                </c:url>
                <td valign="middle" class="${tdClass}"><a href='<c:out value="/${appName}/${delUrl}"/>' onclick="return confirm('Caution: Are you sure you want to delete this record?');self.close();">X</a></td>
            </logic:present>
            <c:forEach var="pageItem" begin="0" items="${encounterForm.pageItems}" varStatus="lineInfo">
			<c:if test="${(! empty param.highlight) && (param.highlight == pos)}">
                <c:set var="tdClass" value="problemCell"/>
            </c:if>
            <c:if test="${(! empty param.extendedTestId) && (param.extendedTestId == pos)}">
                <c:set var="tdClass" value="problemCell"/>
            </c:if>
            <c:if test='${pageItem.form_field.enabled ==true && pageItem.form_field.type != "Display" && pageItem.inputType != "multiselect_enum" && pageItem.inputType != "hidden-no-listing"}'>
                <c:set var="field" value="${pageItem.form_field}"/>
                <c:set var="currentField" value="${field.id}" scope="request"/>
                    <td id="cell${currentEncounterId}.${field.id}" valign="middle" class="${tdClass}" ondblclick="callChartWidget('${pageItem.id}', '${field.id}', '${pageItem.formId}', '${currentEncounterId}', '${pageItem.inputType}')">
                    <logic:notEmpty name="encounter" property="encounterMap.${field.identifier}">
                        <bean:define id="thisValue" name="encounter" property="encounterMap.${field.identifier}" />
                        <c:choose>
                            <c:when test="${! empty thisValue}">
                            	<c:choose>
                            		<c:when test="${field.identifier == 'password'}">
                            		<span id="value${currentEncounterId}.${field.id}">********</span>
                              <span id="widget${currentEncounterId}.${field.id}"></span>
                              </c:when>
                              <c:otherwise>
                              <span id="value${currentEncounterId}.${field.id}">${thisValue}</span>
                              <span id="widget${currentEncounterId}.${field.id}"></span>
                              </c:otherwise>
                         </c:choose>
                            </c:when>
                            <c:otherwise>
                            <span id="value${currentEncounterId}.${field.id}"></span>
                            <span id="widget${currentEncounterId}.${field.id}"></span>
                            </c:otherwise>
                        </c:choose>
                    </logic:notEmpty>
                    <logic:empty name="encounter" property="encounterMap.${field.identifier}">
                     <span id="value${currentEncounterId}.${field.id}"></span>
                     <span id="widget${currentEncounterId}.${field.id}"></span>
                    </logic:empty>
                    <c:if test="${(pageItem.inputType =='emptyDate') || (pageItem.inputType =='birthdate') || (pageItem.inputType =='dateToday')}">
                        <fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow" />
                        <fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow" />
                        <fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow" />
                        <c:set var="theDate" value="${now}"/>
                        <div id="slcalcodfield${currentEncounterId}.${field.id}" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
                            <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","field${currentEncounterId}.${field.id}");</script>
                        </div>
                    </c:if>
                    </td>
            </c:if>
             <c:set var="tdClass" value="enhancedtabletighterCell"/>
            </c:forEach>
        </tr>
        </c:forEach>
</table>