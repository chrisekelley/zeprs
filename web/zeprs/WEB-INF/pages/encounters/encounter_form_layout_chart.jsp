<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="encounterForm" scope="request" type="org.cidrz.webapp.dynasite.valueobject.Form" />

<c:set var="pos" value="0"/>
<c:set var="tblItem" value="0"/>
<c:set var="tblCols" value="0"/>
<c:set var="tdBackgroundColor" value="#fff"/>
<c:set var="collapsing" value="0"/>
<c:set var="tdClass" value="enhancedtabletighterCell"/>
<c:set var="dataEntry" value="1"/>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow" />
<fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow" />
<fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow" />
<c:set var="theDate" value="${now}"/>

<c:choose>
    <c:when test="${(zeprs_session.sessionPatient.currentFlowId ==9) || (zeprs_session.sessionPatient.currentFlowId ==1) || (zeprs_session.sessionPatient.currentFlowId ==2)
                        || (zeprs_session.sessionPatient.currentFlowId ==7) || (zeprs_session.sessionPatient.currentFlowId ==8)
                        || (zeprs_session.sessionPatient.currentFlowId ==103)}">
        <c:set var="dataEntry" value="1"/>
    </c:when>
    <c:when test="${zeprs_session.sessionPatient.currentPregnancyId == -1}">
        <c:set var="dataEntry" value="1"/>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${(encounterForm.flowId == 1) || (encounterForm.flowId == 2) || (encounterForm.flowId == 7)}">
                <c:set var="dataEntry" value="0"/>
                <c:if test="${encounterForm.id == 55}"><c:set var="dataEntry" value="1"/></c:if>
                <c:if test="${encounterForm.id == 2}"><c:set var="dataEntry" value="1"/></c:if>
            </c:when>
            <c:otherwise>
                <c:set var="dataEntry" value="1"/>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
<p>

<c:if test="${encounterForm.id == 2}">
    To view records from a previous ZEPRS pregnancy (if available), click on the folder icon in the ZEPRS Records row.
    <c:url value="patientHome.do" var="url"><c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/>
        <c:choose>
            <c:when test="${! empty currentPregId}"><c:param name="pregnancyId" value="${currentPregId}"/></c:when>
            <c:otherwise>
                <c:param name="pregnancyId" value="0"/>
            </c:otherwise>
        </c:choose>
    </c:url>
    <c:if test="${currentPregId != zeprs_session.sessionPatient.currentPregnancyId}">
        <br><a href='<c:out value="/zeprs/${url}"/>'>Return to Current Pregnancy</a>
    </c:if>
</c:if>

<c:choose>
<c:when test="${! empty zeprs_session.sessionPatient.datePregnancyEnd}">
    <c:set var="dataEntry" value="0"/>
</c:when>
<c:otherwise>
    <c:if test="${encounterForm.id == 2 && !empty zeprs_session.sessionPatient.datePregnancyBegin}">
    <br>You currently have an active pregnancy. Do you need to conclude it?
    <span style="text-decoration: underline; color: blue" onclick="toggle2('helpConclude')">Click here for more info.</span>
    <span id="helpConclude" style="display:none">If the pregnancy is complete due to neonatal death or if it has been over three months since delivery,
    click "Close" on the following line to open the Pregnancy Conclusion form, which creates a summary record for this pregnancy
    and returns you to the ZEPRS Home page.
    </br>Current Pregnancy: <html:link action="/form71/new.do" paramId="patientId" paramName="zeprs_session" paramProperty="sessionPatient.id">Close</html:link>
    </span>


    </c:if>
</c:otherwise>
</c:choose>
</p>
<c:choose>
    <c:when test="${encounterForm.id == 2}"><zeprs:date_visit_hidden dateVisit="${dateVisit}"/></c:when>
    <c:otherwise>
        <c:forEach items="${chartItems}" var="encounter" varStatus="item">
            <c:choose>
            <c:when test="${! empty encounter}">
            <c:set var="pos" value="${encounter.id}"/>
            </c:when>
            <c:otherwise>
            <c:set var="pos" value="0"/>
            </c:otherwise>
            </c:choose>
            <c:if test="${encounter.formId == '80' && encounter.field1616 == true}">
                <c:set var="tdClass" value="problemCell"/>
            </c:if>
            <c:set var="currentEncounterId" value="${encounter.id}" scope="request"/>
            <c:if test="${item.first && encounterForm.id != 55}">
                <tr>
                <td valign="middle" class="enhancedtabletighterCell"><strong>Date</strong></td>
            </c:if>
            <td id="field${field.id}Cell${pos}" valign="middle" class="${tdClass}">
                <c:choose>
                    <c:when test="${! empty encounter}">
                        <logic:notEmpty name="encounter" property="dateVisit">
                            <bean:define id="thisValue" name="encounter" property="dateVisit" />
                            <zeprs:date_visit_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" value="${thisValue}"/>
                        </logic:notEmpty>
                        <logic:empty name="encounter" property="dateVisit">
                            <zeprs:date_visit_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}"/>
                        </logic:empty>
                         <logic:present role="DELETE_ARCHIVE_PATIENT_RECORDS">
                            <c:url var="delUrl" value="admin/deleteEncounter.do">
                                <c:param name="encounterId" value="${encounter.id}"/>
                                <c:param name="formId" value="${encounter.formId}"/>
                            </c:url>
                            <br/>
                            <a href='<c:out value="/zeprs/${delUrl}"/>'onclick="return confirm('Caution: Are you sure you want to delete this record from ZEPRS?');self.close();">X</a>
                        </logic:present>
                    </c:when>
                </c:choose>
              </td>
            <c:if test="${item.last && dataEntry == 1}">
            <c:set var="pos" value="0"/>
            <td id="field${field.id}Cell${pos}" valign="middle" class="highlightRow"><zeprs:date_visit_only pageItem="${pageItem}" pos="${pos}"/></td>
            </tr>
            <c:set var="pos" value="${encounter.id}"/>
            </c:if>
            <c:set var="tdClass" value="enhancedtabletighterCell"/>
        </c:forEach>
    </c:otherwise>
</c:choose>

        <c:forEach items="${chartItems}" var="encounter" varStatus="item">
            <c:choose>
            <c:when test="${! empty encounter}">
            <c:set var="pos" value="${encounter.id}"/>
            </c:when>
            <c:otherwise>
            <c:set var="pos" value="0"/>
            </c:otherwise>
            </c:choose>
            <c:if test="${encounter.formId == '80' && encounter.field1616 == true}">
                <c:set var="tdClass" value="problemCell"/>
            </c:if>
            <c:set var="currentEncounterId" value="${encounter.id}" scope="request"/>
            <c:if test="${item.first && encounterForm.id != 55}">
                <tr>
                <td valign="middle" class="enhancedtabletighterCell"><strong>Site</strong></td>
            </c:if>
            <td id="field${field.id}Cell${pos}" valign="middle" class="${tdClass}" ondblclick="callMetaDataWidget('SiteId.${encounter.id}', '${encounter.id}')">
                <c:choose>
                    <c:when test="${! empty encounter}">
                    	<span id="valueSiteId.${encounter.id}" class="renderedValue">${encounter.siteName}</span>
        				<span id="widgetSiteId.${encounter.id}"></span>
        			</c:when>
                </c:choose>
                <c:if test="${encounterForm.id == 2}">
                    <c:choose>
                    <c:when test="${! empty encounter}">
                        <%--<logic:notEmpty name="encounter" property="dateVisit">
                            <bean:define id="thisValue" name="encounter" property="dateVisit" />
                            <zeprs:date_visit_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" value="${thisValue}"/>
                        </logic:notEmpty>
                        <logic:empty name="encounter" property="dateVisit">
                            <zeprs:date_visit_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}"/>
                        </logic:empty>--%>
                         <logic:present role="DELETE_ARCHIVE_PATIENT_RECORDS">
                            <c:url var="delUrl" value="admin/deleteEncounter.do">
                                <c:param name="encounterId" value="${encounter.id}"/>
                                <c:param name="formId" value="${encounter.formId}"/>
                            </c:url>
                            <br/>
                            <a title="Delete record?" href='<c:out value="/zeprs/${delUrl}"/>'onclick="return confirm('Caution: Are you sure you want to delete this record from ZEPRS?');self.close();">X</a>
                        </logic:present>
                    </c:when>
                </c:choose>
                </c:if>
              </td>
            <c:if test="${item.last && dataEntry == 1}">
            <td id="field${field.id}Cell${pos}" valign="middle" class="highlightRow">&nbsp;</td>
            </tr>
            <c:set var="pos" value="${encounter.id}"/>
            </c:if>
            <c:set var="tdClass" value="enhancedtabletighterCell"/>
        </c:forEach>

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
         <%-- loop through the chartItems and display the values--%>
        <c:forEach items="${chartItems}" var="encounter" varStatus="item">
            <c:choose>
                <c:when test="${! empty encounter}">
                <c:set var="pos" value="${encounter.id}"/>
                </c:when>
                <c:otherwise>
                <c:set var="pos" value="0"/>
                </c:otherwise>
            </c:choose>
            <c:if test="${encounter.formId == '80' && encounter.field1616 == true}">
                <c:set var="tdClass" value="problemCell"/>
            </c:if>
            <c:set var="numRows" value="${item.index+1}"/>
            <c:set var="currentEncounterId" value="${encounter.id}" scope="request"/>
            <c:if test="${item.first && encounterForm.id != 55}">

            <tr>
            <td id="field${field.id}Label${pos}" valign="middle" class="enhancedtabletighterCell"><strong>${field.label}</strong>
                <c:if test="${! empty field.units}">(${field.units})</c:if>
            </td>
            </c:if>
            <logic:notEmpty name="encounter" property="encounterMap.field${field.id}">
                <td id="cell${currentEncounterId}.${field.id}" valign="middle" class="${tdClass}" ondblclick="callChartWidget('${pageItem.id}', '${field.id}', '${pageItem.formId}', '${currentEncounterId}', '${pageItem.inputType}')">
                <bean:define id="thisValue" name="encounter" property="encounterMap.field${field.id}" />
                <%--<td id="field${field.id}Cell${pos}" valign="middle" class="${tdClass}">--%>
                <c:choose>
                    <c:when test="${! empty thisValue}">
                        <c:choose>
                            <c:when test="${pageItem.inputType=='prevPregnanciesLink'}">
                                <zeprs:prevPregnanciesLink value="${thisValue}"/>
                            </c:when>
                            <c:otherwise>
                                <span id="value${currentEncounterId}.${field.id}">${thisValue}</span>
                                <span id="widget${currentEncounterId}.${field.id}"></span>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${pageItem.inputType=='prevPregnanciesLink'}"></c:when>
                            <c:otherwise>
                                <span id="value${currentEncounterId}.${field.id}"></span>
                                <span id="widget${currentEncounterId}.${field.id}"></span>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                    <c:if test="${(pageItem.inputType =='emptyDate') || (pageItem.inputType =='birthdate') || (pageItem.inputType =='dateToday')}">
                        <div id="slcalcodfield${currentEncounterId}.${field.id}" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
                            <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","field${currentEncounterId}.${field.id}");</script>
                        </div>
                    </c:if>
                </td>
            </logic:notEmpty>
            <logic:empty name="encounter" property="encounterMap.field${field.id}">
                <c:choose>
                    <c:when test="${pageItem.inputType=='prevPregnanciesLink'}"><td>&nbsp;</td></c:when>
                    <c:otherwise>
                        <td id="cell${currentEncounterId}.${field.id}" valign="middle" class="${tdClass}" ondblclick="callChartWidget('${pageItem.id}', '${field.id}', '${pageItem.formId}', '${currentEncounterId}', '${pageItem.inputType}')">
                            <span id="value${currentEncounterId}.${field.id}"></span>
                            <span id="widget${currentEncounterId}.${field.id}"></span>
                            <c:if test="${(pageItem.inputType =='emptyDate') || (pageItem.inputType =='birthdate') || (pageItem.inputType =='dateToday')}">
                                <div id="slcalcodfield${currentEncounterId}.${field.id}" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
                                    <script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","field${currentEncounterId}.${field.id}");</script>
                                </div>
                            </c:if>
                        </td>
                    </c:otherwise>
                </c:choose>
            </logic:empty>

            <c:if test="${item.last && dataEntry == 1}">
                <c:set var="pos" value="0"/>
                <c:choose>
                    <c:when test="${encounterForm.id == '55'}">
                        <c:if test="${lineInfo.first}">
                            <td id="field${field.id}Cell${pos}" valign="middle" class="highlightRow" rowspan="${fn:length(encounterForm.pageItems)}">
                            <%-- form 55, current medicine --%>
                                <table>
                                   <jsp:include page="../forms/current_medicine_fields.jsp"/>
                                </table>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                    	<c:choose>
    						<c:when test="${pageItem.visible=='false'}">
    							<td id="field${field.id}Cell${pos}" valign="middle" class="highlightRow" style="visibility:hidden" >
    						</c:when>
    						<c:otherwise>
    							<td id="field${field.id}Cell${pos}" valign="middle" class="highlightRow">
    						</c:otherwise>
    					</c:choose>
                        <%@ include file="/WEB-INF/pages/encounters/dwr_form_fields.jsp" %>
                        </td>
                    </c:otherwise>
                </c:choose>
                </tr>
                <c:set var="pos" value="${encounter.id}"/>
             </c:if>
             <c:set var="tdClass" value="enhancedtabletighterCell"/>
        </c:forEach>
    </c:if>
</c:forEach>

<c:if test="${empty chartItems}">
<tr>
<td valign="middle" class="enhancedtabletighterCell">Date</td>
<td id="field${field.id}Cell${pos}" valign="middle" class="highlightRow"><zeprs:date_visit_only pageItem="${pageItem}" pos="${pos}"/></td>
</tr>

<c:choose>
<c:when test="${encounterForm.id == '55'}">
<%-- form 55, current medicine --%>
    <tr>
        <td>&nbsp;</td>
        <td id="field${field.id}Cell${pos}" valign="middle" class="highlightRow">
            <table>
               <jsp:include page="../forms/current_medicine_fields.jsp"/>
            </table>
        </td>
    </tr>
</c:when>
<c:otherwise>
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
            <c:set var="pos" value="0"/>
            <tr>
                <td id="field${field.id}Label${pos}" valign="middle" class="enhancedtabletighterCell"><strong>${field.label}</strong>
                <c:if test="${! empty field.units}">(${field.units})</c:if>
                </td>
                <c:choose>
					<c:when test="${pageItem.visible=='false'}">
						<td id="field${field.id}Cell${pos}" valign="middle" class="highlightRow" style="visibility:hidden" >
					</c:when>
					<c:otherwise>
						<td id="field${field.id}Cell${pos}" valign="middle" class="highlightRow">
					</c:otherwise>
				</c:choose>

    <c:set var="field" value="${pageItem.form_field}" />
    <c:choose><%--Now show the page items--%>

        <c:when test='${field.type == "Enum"}'>
            <c:choose>
                <c:when test='${pageItem.inputType=="checkbox"}'>
                   <zeprs:checkbox_only pageItem="${pageItem}"/>
               </c:when>

               <c:when test='${pageItem.inputType=="checkbox_dwr"}'>
                    <c:if test="${field.id == '1655'}">
                        <logic:present name="form${encounterForm.id}" property="field1655">
                        <bean:define id="thisValue" name="form${encounterForm.id}" property="field${field.id}"/>
                        </logic:present>
                     </c:if>
                     <zeprs:checkbox_only pageItem="${pageItem}" value="${thisValue}"/>
               </c:when>

                <c:when test='${pageItem.inputType=="select-only"}'>
                    <zeprs:select-only pageItem="${pageItem}"/>
                </c:when>

                <c:when test='${pageItem.inputType=="select-dwr"}'>
                        <zeprs:select-only pageItem="${pageItem}"/>
                </c:when>

               <c:otherwise>
                    <zeprs:select-only pageItem="${pageItem}"/>
               </c:otherwise>
            </c:choose>
        </c:when>

        <c:when test='${field.type=="Date"}'>
            <c:choose>
            <c:when test='${pageItem.inputType=="emptyDate"}'>
                <zeprs:date_visit_empty pageItem="${pageItem}"/>
            </c:when>
            <c:otherwise>${pageItem.inputType} widget - no tag assigned</c:otherwise>
            </c:choose>
        </c:when>

        <c:when test='${field.type=="Yes/No"}'>
            <c:choose>
            <c:when test='${pageItem.inputType=="yesno_dwr"}'>
                <zeprs:yesno_only pageItem="${pageItem}"/>
            </c:when>
            <c:otherwise>
                <zeprs:yesno_only pageItem="${pageItem}"/>
            </c:otherwise>
            </c:choose>
        </c:when>

        <c:when test='${field.type=="Time"}'>
            <zeprs:time_no_label pageItem="${pageItem}"/>
        </c:when>

        <c:when test='${field.type=="sex"}'>
              <zeprs:sex pageItem="${pageItem}"/>
         </c:when>
        <c:when test='${pageItem.inputType=="ega_pregnancyDating"}'>
            <zeprs:ega_pregnancyDating pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"  value="${egaToday}"/>
        </c:when>
        <c:when test='${field.type=="Year"}'>
                 <zeprs:year_no_label pageItem="${pageItem}"/>
         </c:when>
        <c:when test='${pageItem.inputType=="position"}'>
            <zeprs:position pageItem="${pageItem}"/>
        </c:when>
        <c:when test='${pageItem.inputType=="position-dropdown"}'>
            <zeprs:position-dropdown pageItem="${pageItem}"/>
        </c:when>
        <c:when test='${pageItem.inputType=="position-dropdown-dwr"}'>
                <zeprs:position-dropdown pageItem="${pageItem}"/>
        </c:when>

        <c:when test='${pageItem.inputType=="checkbox"}'>
           <zeprs:checkbox pageItem="${pageItem}"/>
       </c:when>

        <c:when test='${pageItem.inputType=="Yes/No"}'>
                <zeprs:yesno_only pageItem="${pageItem}"/>
        </c:when>

        <c:when test="${pageItem.inputType=='text-only'}"><zeprs:text-only pageItem="${pageItem}"/></c:when>
        <c:when test="${pageItem.inputType=='text-dwr'}"><zeprs:text-only pageItem="${pageItem}"/></c:when>
        <c:when test="${pageItem.inputType=='hidden_dwr'}"><zeprs:text-only pageItem="${pageItem}"/></c:when>

        <c:when test="${pageItem.inputType=='month'}">
                <zeprs:month pageItem="${pageItem}"/>
        </c:when>

        <c:when test="${pageItem.inputType=='month_no_label'}">
                <zeprs:month_no_label pageItem="${pageItem}"/>
        </c:when>
        <c:when test="${pageItem.inputType=='prevPregnanciesLink'}"></c:when>
        <c:when test="${pageItem.inputType=='fundal_height'}"><zeprs:fundal_height pageItem="${pageItem}"/></c:when>
        <c:otherwise>
            <zeprs:text-only pageItem="${pageItem}"/>
        </c:otherwise>
    </c:choose>

<%--<%@ include file="/WEB-INF/pages/encounters/dwr_form_fields.jsp" %>--%></td>
        </tr>
    </c:if>
</c:forEach>
</c:otherwise>
</c:choose>
</c:if>
<c:if test="${dataEntry == 1}">
    <tr>
        <c:choose>
            <c:when test="${encounterForm.id == 55}">
            <td colspan="${numRows}" align="center">&nbsp;
            </c:when>
            <c:otherwise>
            <td colspan="${numRows+1}" align="center">&nbsp;
            </c:otherwise>
        </c:choose>
    <c:if test="${encounterForm.id == '2'}">
    If more pregnancies click "Add". <br/>
    If no more pregnancies click "Done".
    </c:if>

        </td>
        <td class="highlightRow">
        <c:choose>
        <c:when test='${encounterForm.requireReauth}'>
            <zeprs:re_auth pageItem="${pageItem}"/>
            <%--<html:submit onclick="bCancel=false;" />--%>
            <input type="button" value="Done" onclick="submitForm();"/> &nbsp;
            <c:if test="${encounterForm.id == '2'}">
            <input type="button" value="Add" onclick="submitAddForm();"/>
            </c:if>
        </c:when>
        <c:otherwise>
            <input type="button" value="Submit" onclick="submitForm();"/>
            <c:if test="${encounterForm.id == '2'}">
            <input type="button" value="Add" onclick="submitAddForm();"/>
            </c:if>
        </c:otherwise>
        </c:choose>
        </td>
    </tr>
</c:if>


