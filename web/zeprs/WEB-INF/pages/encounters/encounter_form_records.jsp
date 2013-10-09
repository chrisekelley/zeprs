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

<script language="JavaScript" type="text/javascript" src="/zeprs/dwr/util.js;jsessionid=${pageContext.request.session.id}"></script>
<script language="JavaScript" type="text/javascript" src="/zeprs/js/engine2.jsp;jsessionid=${pageContext.request.session.id}"></script>
<script type='text/javascript' src='/zeprs/dwr/interface/WidgetDisplay.js;jsessionid=${pageContext.request.session.id}'></script>
<script type='text/javascript' src='/zeprs/dwr/interface/Encounter.js;jsessionid=${pageContext.request.session.id}'></script>
<script type="text/javascript" src="/zeprs/js/dwr-generic.js;jsessionid=${pageContext.request.session.id}"></script>
<script type="text/javascript" src="/zeprs/js/dwr-display.js;jsessionid=${pageContext.request.session.id}"></script>
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
    <script type='text/javascript'>
        var chartDates = new Array();

        function confirmChartDate(field, formId)
        {
            var input = document.getElementsByName(field).item(0);
            var date = fixDate(input.value, "-");
            //alert("date: " + date);
            var match = 0;
            for (var i = 0; i < chartDates.length; i++)
            {
                var theDate = chartDates[i];
                if (date == theDate) {
                    match = 1;
                }
            }
            if (match == 1) {
                alert("You are trying to enter duplicate information. Please change the date in the form or edit information directly on the record table below.");
                return false;
            } else {
                if (formId != null) {
                    var form = document.getElementById("form" + formId);
                    return validateForm${encounterForm.id}(form);
                } else {
                    return validateForm${encounterForm.id}(this);
                }

            }
        }

    </script>
<table class="enhancedtable">
<c:if test="${encounterForm.id == '90'}">
    <tr>
        <td class="enhancedtabletighterHeader" style="border-width:4px;">Request</td>
        <td class="enhancedtabletighterHeader" style="border-width:4px;" colspan="2">Result</td>
        <td class="enhancedtabletighterHeader" style="border-width:4px;" colspan="5">Treatment</td>
        <td class="enhancedtabletighterHeader" style="border-width:4px;">&nbsp;</td>
    </tr>
</c:if>
   <tr>
<%-- loop through the pageItems and build the chart--%>
<c:set var="numRows" value="0"/>
    <c:if test="${encounterForm.id >=101 && encounterForm.id <=104}">
        <th id="dateRequestedHeader" valign="middle" class="enhancedtabletighterCell"><strong>Request Date</strong></th>
        <th id="dateRequestedHeader" valign="middle" class="enhancedtabletighterCell"><strong>Result Date</strong></th>
    </c:if>
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
            <c:if test="${(pageItem.id != '3872') && (pageItem.id != '4164') && (pageItem.id != '4220') && (pageItem.id != '4357') && (pageItem.inputType != 'multiselect_enum') && (field.id !='2037')}">
                <th id="field${field.id}Cell${pos}" valign="middle" class="enhancedtabletighterCell"><strong>${field.label}</strong></th>
            </c:if>
    </c:if>
</c:forEach>
    <logic:present role="DELETE_ARCHIVE_PATIENT_RECORDS">
        <th class="enhancedtabletighterCell"><strong>Del.</strong></th>
    </logic:present>
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
            <c:if test="${encounter.formId == '80' && encounter.field1616 == '1'}">
                <c:set var="tdClass" value="problemCell"/>
            </c:if>
            <c:set var="numRows" value="${item.index+1}"/>
            <c:set var="currentEncounterId" value="${encounter.id}" scope="request"/>

            <c:forEach var="pageItem" begin="0" items="${encounterForm.pageItems}" varStatus="lineInfo">
            <c:if test="${(param.highlight == pos) || (param.extendedTestId == pos)}">
                <c:set var="tdClass" value="problemCell"/>
            </c:if>
            <c:if test="${lineInfo.index == 1}">
                <c:if test="${encounterForm.id >=101 && encounterForm.id <=104}">
                    <td id="cellDateRequested" valign="middle"
                        class="${tdClass}">${encounter.encounterMap.dateRequested}</td>
                    <td id="cellDateResult" valign="middle"
                        class="${tdClass}">${encounter.encounterMap.dateResult}</td>
                </c:if>
            </c:if>
                <c:if test='${pageItem.form_field.enabled ==true && pageItem.form_field.type != "Display" && pageItem.inputType != "multiselect_enum"}'>
                    <c:set var="field" value="${pageItem.form_field}"/>
                    <c:set var="currentField" value="${field.id}" scope="request"/>
                    <c:if test="${(pageItem.id != '3872') && (pageItem.id != '4164') && (pageItem.id != '4220') && (pageItem.id != '4357') && (field.id !='2037')}"><%-- don't display second results or cd4 count --%>
                        <c:choose>
                            <c:when test="${pageItem.id == '3861'}">
                                <logic:notEmpty name="encounter" property="encounterMap.field1858">
                                    <bean:define id="value1858" name="encounter" property="encounterMap.field1858"/>
                                    <c:set var="thisValue" value=""/>
                                </logic:notEmpty>
                                <logic:empty name="encounter" property="encounterMap.field1858">
                                    <c:set var="value1858" value=""/>
                                </logic:empty>
                                <logic:notEmpty name="encounter" property="encounterMap.field2004">
                                    <bean:define id="value2004" name="encounter" property="encounterMap.field2004"/>
                                    <c:set var="thisValue" value=""/>
                                </logic:notEmpty>
                                <logic:empty name="encounter" property="encounterMap.field2004">
                                    <c:set var="value2004" value=""/>
                                </logic:empty>
                            </c:when>
                            <c:otherwise>
                                <c:set var="value1858" value=""/>
                                <c:set var="value2004" value=""/>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${! empty value1858}"><%-- results input--%>
                                <td id="cell${currentEncounterId}.1858" valign="middle" class="${tdClass}" ondblclick="callChartWidget('3872', '1858', '${pageItem.formId}', '${currentEncounterId}', '${pageItem.inputType}', 1858)">
                            </c:when>
                            <c:when test="${! empty value2004}"><%-- cd4 count input--%>
                                <td id="cell${currentEncounterId}.2004" valign="middle" class="${tdClass}" ondblclick="callChartWidget('4164', '2004', '${pageItem.formId}', '${currentEncounterId}', '${pageItem.inputType}', 2004)">
                            </c:when>
                            <c:when test="${pageItem.inputType == 'lab_results'}">
                                <bean:define id="value1845" name="encounter" property="encounterMap.field1845"/>
                                <c:set var="extendedLabId" value="0"/>
                                <c:if test="${! empty encounter.field2044}">
                                    <c:set var="extendedLabId" value="${encounter.field2044}"/>
                                </c:if>
                                <c:choose>
                                    <c:when test="${value1845 == 'Hematology'}">
                                        <td id="cell${currentEncounterId}.${field.id}" valign="middle" class="${tdClass}" ondblclick="redirect('hematology', '${pageContext.request.session.id}', '${encounter.patientId}', '${currentEncounterId}', ${extendedLabId})">
                                    </c:when>
                                    <c:when test="${value1845 == 'Chemistry'}">
                                        <td id="cell${currentEncounterId}.${field.id}" valign="middle" class="${tdClass}" ondblclick="redirect('chemistry', '${pageContext.request.session.id}', '${encounter.patientId}', '${currentEncounterId}', ${extendedLabId})">
                                    </c:when>
                                    <c:when test="${value1845 == 'Liver Function'}">
                                        <td id="cell${currentEncounterId}.${field.id}" valign="middle" class="${tdClass}" ondblclick="redirect('liver', '${pageContext.request.session.id}', '${encounter.patientId}', '${currentEncounterId}', ${extendedLabId})">
                                    </c:when>
                                    <c:when test="${value1845 == 'Urinalysis'}">
                                        <td id="cell${currentEncounterId}.${field.id}" valign="middle" class="${tdClass}" ondblclick="redirect('urinalysis', '${pageContext.request.session.id}', '${encounter.patientId}', '${currentEncounterId}', ${extendedLabId})">
                                    </c:when>
                                    <c:otherwise>
                                        <td id="cell${currentEncounterId}.${field.id}" valign="middle" class="${tdClass}" ondblclick="callChartWidget('${pageItem.id}', '${field.id}', '${pageItem.formId}', '${currentEncounterId}', '${pageItem.inputType}', 1858)">
                                    </c:otherwise>
                                </c:choose>
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
                                <logic:notEmpty name="encounter" property="encounterMap.field2004">
                                    <bean:define id="value2004" name="encounter" property="field2004" />
                                    <c:set var="thisValue" value=""/>
                                </logic:notEmpty>
                            </c:if>
                            <c:choose>
                                <c:when test="${! empty value1858}">
                                <span id="value${currentEncounterId}.1858">${value1858}</span>
                                <span id="widget${currentEncounterId}.1858"></span>
                                </c:when>
                                <c:when test="${! empty value2004}">
                                <span id="value${currentEncounterId}.2004">${value2004}</span>
                                <span id="widget${currentEncounterId}.2004"></span>
                                </c:when>
                                <c:when test="${pageItem.id == '3861'}">
                                <span id="value${currentEncounterId}.1858">${thisValue}</span>
                                <span id="widget${currentEncounterId}.1858"></span>
                                </c:when>
                                <c:when test="${pageItem.id == '3860'}">
                                <span id="value${currentEncounterId}.1846">
                                <fmt:parseDate pattern="yyyy-MM-dd" value="${thisValue}" var="formattedDate" />
                                <fmt:formatDate value="${formattedDate}" pattern="yyyy-MM-dd"/>
                                </span>
                                <span id="widget${currentEncounterId}.1846"></span>
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
                                    <span id="value${currentEncounterId}.2004">${value2004}</span>
                                    <span id="widget${currentEncounterId}.2004"></span>
                                </c:when>
                                <c:otherwise>
                                    <span id="value${currentEncounterId}.${field.id}"></span>
                                    <span id="widget${currentEncounterId}.${field.id}"></span>
                                </c:otherwise>
                            </c:choose>
                        </logic:empty>
                        <c:if test="${(pageItem.inputType =='emptyDate') || (pageItem.inputType =='birthdate') || (pageItem.inputType =='dateToday')}">
                            <fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow" />
                            <fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow" />
                            <fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow" />
                            <c:set var="theDate" value="${now}"/>
                            <div id="slcalcodfield${currentEncounterId}.${field.id}" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
                                <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","field${currentEncounterId}.${field.id}");</script>
                            </div>
                            <c:if test="${pageItem.inputType =='dateToday'}">
                                <script type='text/javascript'>
                                    chartDates[chartDates.length] = '${thisValue}';
                                </script>
                            </c:if>
                        </c:if>
                        </td>
                    </c:if>
                </c:if>

             <c:set var="tdClass" value="enhancedtabletighterCell"/>
            </c:forEach>
            <logic:present role="DELETE_ARCHIVE_PATIENT_RECORDS">
                <c:url var="delUrl" value="admin/deleteEncounter.do">
                    <c:param name="encounterId" value="${encounter.id}"/>
                    <c:param name="formId" value="${encounter.formId}"/>
                </c:url>
                <td valign="middle" class="${tdClass}"><a href='<c:out value="/zeprs/${delUrl}"/>' onclick="return confirm('Caution: Are you sure you want to delete this record from ZEPRS?');self.close();">X</a></td>
            </logic:present>

        </tr>
        </c:forEach>
</table>
<p>&nbsp;</p>