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
<c:set var="tdClass" value="enhancedtabletighterCell"/>
<c:choose>
    <c:when test="${(zeprs_session.sessionPatient.currentFlowId ==9) || (zeprs_session.sessionPatient.currentFlowId ==1) || (zeprs_session.sessionPatient.currentFlowId ==2)
                        || (zeprs_session.sessionPatient.currentFlowId ==7)}">
        <c:set var="dataEntry" value="1"/>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${(encounterForm.flowId == 1) || (encounterForm.flowId == 2) || (encounterForm.flowId == 7)}">
                <c:set var="dataEntry" value="0"/>
                <c:if test="${encounterForm.id == 55}"><c:set var="dataEntry" value="1"/></c:if>
            </c:when>
            <c:otherwise>
                <c:set var="dataEntry" value="1"/>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
<c:if test="${! empty chartItems}">
   <tr>
       <th valign="middle" class="enhancedtabletighterCell"><strong>Date/Site</strong></th>
<%-- loop through the pageItems and build the form--%>
<c:set var="numRows" value="0"/>
<c:forEach var="pageItem" begin="0" items="${encounterForm.pageItems}" varStatus="lineInfo">
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
            <c:if test="${pageItem.id != '3872' && pageItem.inputType != 'multiselect_enum'}">
                <th id="field${field.id}Cell${pos}" valign="middle" class="enhancedtabletighterCell"><strong>${field.label}</strong></th>
            </c:if>
    </c:if>
</c:forEach>
</tr>
    <c:forEach items="${chartItems}" var="encounter" varStatus="item">
    <c:if test="${encounter.field1616 == true}">
        <c:set var="tdClass" value="problemCell"/>
    </c:if>
        <tr class="${tdClass}">
            <c:choose>
                <c:when test="${! empty encounter}">
                <c:set var="pos" value="${encounter.id}"/>
                </c:when>
                <c:otherwise>
                <c:set var="pos" value="0"/>
                </c:otherwise>
            </c:choose>
            <c:set var="numRows" value="${item.index+1}"/>
            <c:set var="currentEncounterId" value="${encounter.id}" scope="request"/>
            <td valign="middle" class="${tdClass}">${encounter.dateVisit}<br/>${encounter.lastModifiedByName} at ${encounter.siteName}</td>
            <c:forEach var="pageItem" begin="0" items="${encounterForm.pageItems}" varStatus="lineInfo">
                <c:if test='${pageItem.form_field.enabled ==true && pageItem.form_field.type != "Display" && pageItem.inputType != "multiselect_enum"}'>
                    <c:set var="field" value="${pageItem.form_field}"/>
                    <c:set var="currentField" value="${field.id}" scope="request"/>
                    <c:if test="${pageItem.id != '3872'}">
                        <c:choose>
                        <c:when test="${pageItem.id == '3861'}">
                            <logic:notEmpty name="encounter" property="encounterMap.field1858">
                                <bean:define id="value1858" name="encounter" property="encounterMap.field1858" />
                                <c:set var="thisValue" value=""/>
                            </logic:notEmpty>
                            <logic:empty name="encounter" property="encounterMap.field1858">
                                <c:set var="value1858" value=""/>
                            </logic:empty>
                        </c:when>
                            <c:otherwise><c:set var="value1858" value=""/></c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${! empty value1858}">
                                <td id="cell${currentEncounterId}.1858" valign="middle" class="${tdClass}" ondblclick="callChartWidget('3872', '1858', '${pageItem.formId}', '${currentEncounterId}', '${pageItem.inputType}', 1858)">
                            </c:when>
                            <c:when test="${pageItem.inputType == 'lab_results'}">
                                <td id="cell${currentEncounterId}.${field.id}" valign="middle" class="${tdClass}" ondblclick="callChartWidget('${pageItem.id}', '${field.id}', '${pageItem.formId}', '${currentEncounterId}', '${pageItem.inputType}', 1858)">
                            </c:when>
                            <c:otherwise>
                                <td id="cell${currentEncounterId}.${field.id}" valign="middle" class="${tdClass}" ondblclick="callChartWidget('${pageItem.id}', '${field.id}', '${pageItem.formId}', '${currentEncounterId}', '${pageItem.inputType}')">
                            </c:otherwise>
                        </c:choose>
                        <logic:notEmpty name="encounter" property="encounterMap.field${field.id}">
                            <bean:define id="thisValue" name="encounter" property="encounterMap.field${field.id}" />
                            <c:if test="${pageItem.id == '3861'}">
                                <logic:notEmpty name="encounter" property="encounterMap.field1858">
                                    <bean:define id="value1858" name="encounter" property="field1858" />
                                    <c:set var="thisValue" value=""/>
                                </logic:notEmpty>
                            </c:if>
                            <c:choose>
                                <c:when test="${! empty value1858}">
                                <span id="value${currentEncounterId}.1858">${value1858}</span>
                                <span id="widget${currentEncounterId}.1858"></span>
                                </c:when>
                                <c:when test="${pageItem.id == '3861'}">
                                <span id="value${currentEncounterId}.1858">${thisValue}</span>
                                <span id="widget${currentEncounterId}.1858"></span>
                                </c:when>
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
                            <c:choose>
                                <c:when test="${pageItem.id == '3861'}">
                                   <span id="value${currentEncounterId}.1858">${value1858}</span>
                                    <span id="widget${currentEncounterId}.1858"></span>
                                </c:when>
                                <c:otherwise>
                                    <span id="value${currentEncounterId}.${field.id}"></span>
                                    <span id="widget${currentEncounterId}.${field.id}"></span>
                                </c:otherwise>
                            </c:choose>

                        </logic:empty>
                        <c:if test="${(pageItem.inputType =='emptyDate') || (pageItem.inputType =='birthdate') || (pageItem.inputType =='dateToday')}">
                            <jsp:useBean id="now" class="java.util.Date" />
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
                </c:if>
             <c:set var="tdClass" value="enhancedtabletighterCell"/>
            </c:forEach>
                </tr>
        </c:forEach>
</table>
   <c:if test="{dataEntry == 1}">
<br/><strong>Create new record :</strong>
</c:if>
<zeprs:date_visit/>
<br/>
<c:forEach var="pageItem" begin="0" items="${encounterForm.pageItems}" varStatus="lineInfo">
    <c:if test='${pageItem.form_field.enabled ==true}'>
        <c:choose>
            <c:when test="${pageItem.colspan >1}">
                <c:set var="colspan" value="${pageItem.colspan}"/>
            </c:when>
            <c:otherwise>
                <c:set var="colspan" value="1"/>
            </c:otherwise>
        </c:choose>
        <c:choose><%--First setup special table formatting and hidden fields - items that don't appear in normal layout--%>
            <c:when test="${pageItem.inputType=='display-tbl-begin'}">
                <c:choose>
                    <c:when test="${(pageItem.visible=='false') && (empty visibility)}">
                        <table border="0" cellpadding="4" cellspacing="2" width="95%" id="tbl${pageItem.form_field.id}"
                        summary="${pageItem.cols} col table" style="display:none; border:none; margin:5px;">
                    </c:when>
                    <c:otherwise>
                        <table border="0" cellpadding="4" cellspacing="2" width="95%" id="tbl${pageItem.form_field.id}"
                        summary="${pageItem.cols} col table" class="formTable">
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${pageItem.cols >1}">
                        <c:set var="tblCols" value="${pageItem.cols}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="tblCols" value="1"/>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${pageItem.inputType=='display-tbl-end'}">
                </table>
                <c:set var="tblItem" value="0"/>
                <c:set var="tblCols" value="0"/>
            </c:when>
            <c:otherwise><%--Now setthe rows for the normal fields--%>
                <c:set var="tblItem" value="${tblItem+1}"/>
                <c:if test="${tblItem==1 || tblItem==tblCols+1}">
                    <c:choose>
                        <c:when test="${pageItem.inputType=='display-subheader'}">
                            <tr style="background-color:${tdBackgroundColor};" id="row${pageItem.form_field.id}">
                            <c:set var="tblItem" value="1"/>
                            <c:set var="tdBackgroundColor" value="silver"/>
                        </c:when>
                        <c:when test="${(pageItem.visible=='false') && (empty visibility)}">
                            <tr id="row${pageItem.form_field.id}">
                            <c:set var="tblItem" value="1"/>
                        </c:when>
                        <c:otherwise>
                            <tr id="row${pageItem.form_field.id}">
                            <c:set var="tblItem" value="1"/>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <c:choose>
                <c:when test="${pageItem.inputType=='display-subheader'}">
                    <c:set var="tdBackgroundColor" value="#E6E6FA"/>
                </c:when>
                <c:when test="${pageItem.inputType=='infotext'}">
                <c:set var="tdBackgroundColor" value="#E6E6FA"/>
                </c:when>
            </c:choose>
                <c:if test='${pageItem.form_field.enabled ==true && pageItem.inputType != "multiselect_enum"}'>
                    <c:set var="field" value="${pageItem.form_field}"/>
                    <c:set var="currentField" value="${field.id}" scope="request"/>
                    <td colspan="${colspan}" style="background-color:${tdBackgroundColor};border: 1px solid silver;">
                        <%@ include file="/WEB-INF/pages/forms/fields.jsp" %>
                    </td>
                    <c:set var="tdBackgroundColor" value="#fff"/>
                </c:if>
            </c:otherwise>
        </c:choose>
        <c:choose><%--Close the row--%>
            <c:when test="${pageItem.closeRow==true}">
                </tr><c:set var="tblItem" value="0"/>
            </c:when>
            <c:when test="${tblItem==0 ||tblItem==tblCols }"><%--${field.label}:: ${tblItem}<br/>--%></tr></c:when>
        </c:choose>
    </c:if>
</c:forEach>

<div style="padding:5px;">
    <c:choose>
        <c:when test='${encounterForm.requireReauth}'>
            <zeprs:re_auth pageItem="${pageItem}"/>
            <html:submit onclick="bCancel=false;"/>
        </c:when>
        <c:otherwise>
            <html:submit onclick="bCancel=false;"/>
        </c:otherwise>
    </c:choose>
</div>
</c:if>

<script type='text/javascript'>
var displayChartWidget = function(data)
{

    var delim = data.indexOf("=",0);
    var length = data.length;
    var fieldId = data.substr(0,delim);
    var key = "widget" + fieldId;
    var value =  data.substr(delim+1,length);
    var itemValue = document.getElementById(key);
    itemValue.innerHTML = value;
    var valueLink =  document.getElementById("value" + fieldId);
    valueLink.style.display = "none";
    valueLink.style.visibility = "hidden";
}
</script>
