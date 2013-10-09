<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="field" value="${pageItem.form_field}" />
<c:choose><%--Now show the page items--%>

    <c:when test='${field.type == "Enum"}'>
        <c:choose>
            <c:when test='${pageItem.inputType=="checkbox"}'>
               <zeprs:checkbox_only pageItem="${pageItem}"/>
           </c:when>

            <c:when test='${pageItem.inputType=="radio"}'>
                <zeprs:select-only pageItem="${pageItem}"/>
            </c:when>

          <c:when test='${pageItem.inputType=="checkbox_dwr"}'>
               <c:choose>
                <c:when test="${pos == '0'}">
                    <zeprs:checkbox_only pageItem="${pageItem}"/>
                </c:when>
                <c:otherwise>
                    <c:choose>
                    <c:when test="${! empty encounter}">
                        <logic:notEmpty name="encounter" property="field${field.id}">
                            <bean:define id="thisValue" name="encounter" property="field${field.id}" />
                            <zeprs:checkbox_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
                        </logic:notEmpty>
                        <logic:empty name="encounter" property="field${field.id}">
                            <zeprs:checkbox_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                        </logic:empty>
                    </c:when>
                    <c:otherwise>
                    <zeprs:checkbox_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                    </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
           </c:when>

            <c:when test='${pageItem.inputType=="select-only"}'>
                <zeprs:select-only pageItem="${pageItem}"/>
            </c:when>

            <c:when test='${pageItem.inputType=="select-dwr"}'>

            <c:choose>
                <c:when test="${pos == '0'}">
                    <zeprs:select-only pageItem="${pageItem}"/>
                </c:when>
                <c:otherwise>
                    <c:choose>
                    <c:when test="${! empty encounter}">
                        <logic:notEmpty name="encounter" property="field${field.id}">
                            <bean:define id="thisValue" name="encounter" property="encounterMap.field${field.id}" />
                            <zeprs:select-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
                        </logic:notEmpty>
                        <logic:empty name="encounter" property="field${field.id}">
                            <zeprs:select-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" formId="${pageItem.form.id}" siteId="${siteId}"/>
                        </logic:empty>
                    </c:when>
                    <c:otherwise>
                        <zeprs:select-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" formId="${pageItem.form.id}" siteId="${siteId}"/>
                    </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>

            </c:when>

            <c:when test='${pageItem.inputType=="select"}'>

            <c:choose>
                <c:when test="${pos == '0'}">
                    <zeprs:select-only pageItem="${pageItem}"/>
                </c:when>
                <c:otherwise>
                    <c:choose>
                    <c:when test="${! empty encounter}">
                        <logic:notEmpty name="encounter" property="field${field.id}">
                            <bean:define id="thisValue" name="encounter" property="encounterMap.field${field.id}" />
                            <zeprs:select-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
                        </logic:notEmpty>
                        <logic:empty name="encounter" property="field${field.id}">
                            <zeprs:select-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" formId="${pageItem.form.id}" siteId="${siteId}"/>
                        </logic:empty>
                    </c:when>
                    <c:otherwise>
                        <zeprs:select-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" formId="${pageItem.form.id}" siteId="${siteId}"/>
                    </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>

            </c:when>

           <c:otherwise>${pageItem.inputType}: tbd -  no widget assigned</c:otherwise>
        </c:choose>
    </c:when>

    <c:when test='${field.type=="Year"}'>
             <zeprs:year_no_label pageItem="${pageItem}"/>
     </c:when>

     <c:when test='${field.type=="sex"}'>
          <zeprs:sex pageItem="${pageItem}"/>
     </c:when>

    <c:when test='${field.type=="Yes/No"}'>
        <c:choose>
        <c:when test='${pageItem.inputType=="yesno_dwr"}'>
            <c:choose>
                <c:when test="${pos == '0'}">
                    <zeprs:yesno_only pageItem="${pageItem}"/>
                </c:when>
                <c:otherwise>
                    <c:choose>
                    <c:when test="${! empty encounter}">
                        <logic:notEmpty name="encounter" property="field${field.id}">
                            <bean:define id="thisValue" name="encounter" property="field${field.id}" />
                            <zeprs:yesno_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
                        </logic:notEmpty>
                        <logic:empty name="encounter" property="field${field.id}">
                            <zeprs:yesno_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                        </logic:empty>
                    </c:when>
                    <c:otherwise>
                    <zeprs:yesno_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                    </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
           </c:when>

           <c:when test='${pageItem.inputType=="select"}'>
            <c:choose>
                <c:when test="${pos == '0'}">
                    <zeprs:yesno pageItem="${pageItem}" showlabel="0"/>
                </c:when>
                <c:otherwise>
                    <c:choose>
                    <c:when test="${! empty encounter}">
                        <logic:notEmpty name="encounter" property="field${field.id}">
                            <bean:define id="thisValue" name="encounter" property="field${field.id}" />
                            <zeprs:yesno_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
                        </logic:notEmpty>
                        <logic:empty name="encounter" property="field${field.id}">
                            <zeprs:yesno_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                        </logic:empty>
                    </c:when>
                    <c:otherwise>
                    <zeprs:yesno_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                    </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
           </c:when>
           <c:otherwise>
               <c:choose>
                   <c:when test='${pageItem.inputType=="checkbox"}'>
                       <zeprs:checkbox pageItem="${pageItem}"/>
                   </c:when>
                   <c:when test='${pageItem.inputType=="yesno_only"}'>
                       <zeprs:yesno_only pageItem="${pageItem}"/>
                   </c:when>
                   <c:otherwise><zeprs:yesno pageItem="${pageItem}"/></c:otherwise>
               </c:choose>
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
    <c:when test='${field.type=="Time"}'>
         <zeprs:time_no_label pageItem="${pageItem}"/>
    </c:when>
    <c:when test='${pageItem.inputType=="checkbox_dwr"}'>
               <c:choose>
                <c:when test="${pos == '0'}">
                    <zeprs:checkbox_only pageItem="${pageItem}"/>
                </c:when>
                <c:otherwise>
                    <c:choose>
                    <c:when test="${! empty encounter}">
                        <logic:notEmpty name="encounter" property="field${field.id}">
                            <bean:define id="thisValue" name="encounter" property="field${field.id}" />
                            <zeprs:checkbox_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
                        </logic:notEmpty>
                        <logic:empty name="encounter" property="field${field.id}">
                            <zeprs:checkbox_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                        </logic:empty>
                    </c:when>
                    <c:otherwise>
                    <zeprs:checkbox_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                    </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
           </c:when>
    <c:when test='${pageItem.inputType=="ega_pregnancyDating"}'>
        <c:choose>
        <c:when test="${pos == '0'}">
            <c:choose>
            <c:when test="${! empty egaToday}">
            <c:set var="days"  value="${egaToday % 7}" />
            <c:set var="weeks" value="${egaToday/7}" />
            <input type="hidden" name="field129" value="${egaToday}"/>
            <html:link action="pregnancyDating.do"><fmt:parseNumber value="${weeks}" integerOnly="true" />, ${days}/7</html:link>
            </c:when>
            </c:choose>
        </c:when>
        <c:otherwise>
            <c:choose>
            <c:when test="${! empty encounter}">
                <logic:notEmpty name="encounter" property="field${field.id}">
                    <bean:define id="thisValue" name="encounter" property="encounterMap.field${field.id}" />
                    <zeprs:ega_pregnancyDating pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
                </logic:notEmpty>
                <logic:empty name="encounter" property="field${field.id}">
                    <zeprs:ega_pregnancyDating pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                </logic:empty>
            </c:when>
            <c:otherwise>
                <zeprs:ega_pregnancyDating pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
            </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
    </c:when>
    <c:when test='${pageItem.inputType=="position"}'>
        <zeprs:position pageItem="${pageItem}"/>
    </c:when>
    <c:when test='${pageItem.inputType=="position-dropdown"}'>
        <zeprs:position-dropdown pageItem="${pageItem}"/>
    </c:when>
    <c:when test='${pageItem.inputType=="position-dropdown-dwr"}'>
        <c:choose>
        <c:when test="${pos == '0'}">
            <zeprs:position-dropdown pageItem="${pageItem}"/>
        </c:when>
        <c:otherwise>
              <c:choose>
                <c:when test="${! empty encounter}">
                    <logic:notEmpty name="encounter" property="field${field.id}">
                        <bean:define id="thisValue" name="encounter" property="encounterMap.field${field.id}" />
                        <zeprs:position-dropdown-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
                    </logic:notEmpty>
                    <logic:empty name="encounter" property="field${field.id}">
                        <zeprs:position-dropdown-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                    </logic:empty>
                </c:when>
                <c:otherwise>
                    <zeprs:position-dropdown-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
        </c:choose>
    </c:when>

    <c:when test='${pageItem.inputType=="checkbox"}'>
       <zeprs:checkbox_only pageItem="${pageItem}"/>
   </c:when>

   <c:when test="${pageItem.inputType=='month'}">
            <zeprs:month pageItem="${pageItem}"/>
    </c:when>

     <c:when test="${pageItem.inputType=='month_no_label'}">
            <zeprs:month_no_label pageItem="${pageItem}"/>
    </c:when>

    <c:when test="${pageItem.inputType=='text-only'}"><zeprs:text-only pageItem="${pageItem}"/></c:when>

    <c:when test="${pageItem.inputType=='text-dwr'}">
        <c:choose>
        <c:when test="${pos == '0'}">
            <zeprs:text-only pageItem="${pageItem}"/>
        </c:when>
        <c:otherwise>
            <c:choose>
            <c:when test="${! empty encounter}">
                <logic:notEmpty name="encounter" property="field${field.id}">
                    <bean:define id="thisValue" name="encounter" property="field${field.id}" />
                    <zeprs:text-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
                </logic:notEmpty>
                <logic:empty name="encounter" property="field${field.id}">
                    <zeprs:text-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                </logic:empty>
            </c:when>
            <c:otherwise>
            <zeprs:text-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
            </c:otherwise>
            </c:choose>
        </c:otherwise>
        </c:choose>
    </c:when>

    <c:when test="${pageItem.inputType=='hidden_dwr'}">
        <c:choose>
        <c:when test="${pos == '0'}">
            <zeprs:text-only pageItem="${pageItem}"/>
        </c:when>
        <c:otherwise>
            <c:choose>
            <c:when test="${! empty encounter}">
                <logic:notEmpty name="encounter" property="field${field.id}">
                    <bean:define id="thisValue" name="encounter" property="field${field.id}" />
                    <zeprs:hidden_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
                </logic:notEmpty>
                <logic:empty name="encounter" property="field${field.id}">
                    <zeprs:hidden_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                </logic:empty>
            </c:when>
            <c:otherwise>
            <zeprs:hidden_dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
            </c:otherwise>
            </c:choose>
        </c:otherwise>
        </c:choose>
    </c:when>

    <c:when test="${pageItem.inputType=='text'}">
        <c:choose>
        <c:when test="${pos == '0'}">
            <zeprs:text-only pageItem="${pageItem}"/>
        </c:when>
        <c:otherwise>
            <c:choose>
            <c:when test="${! empty encounter}">
                <logic:notEmpty name="encounter" property="field${field.id}">
                    <bean:define id="thisValue" name="encounter" property="field${field.id}" />
                    <zeprs:text-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
                </logic:notEmpty>
                <logic:empty name="encounter" property="field${field.id}">
                    <zeprs:text-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                </logic:empty>
            </c:when>
            <c:otherwise>
            <zeprs:text-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
            </c:otherwise>
            </c:choose>
        </c:otherwise>
        </c:choose>
    </c:when>

    <c:when test="${pageItem.inputType=='textarea'}">
        <c:choose>
        <c:when test="${pos == '0'}">
            <zeprs:text-only pageItem="${pageItem}"/>
        </c:when>
        <c:otherwise>
            <c:choose>
            <c:when test="${! empty encounter}">
                <logic:notEmpty name="encounter" property="field${field.id}">
                    <bean:define id="thisValue" name="encounter" property="field${field.id}" />
                    <zeprs:text-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}" value="${thisValue}"/>
                </logic:notEmpty>
                <logic:empty name="encounter" property="field${field.id}">
                    <zeprs:text-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
                </logic:empty>
            </c:when>
            <c:otherwise>
            <zeprs:text-dwr pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"/>
            </c:otherwise>
            </c:choose>
        </c:otherwise>
        </c:choose>
    </c:when>

    <c:when test="${pageItem.inputType=='prevPregnanciesLink'}"></c:when>
    <c:when test='${pageItem.inputType=="fundal_height"}'><zeprs:fundal_height pageItem="${pageItem}"/></c:when>
    <c:otherwise>${pageItem.inputType} widget - no tag assigned</c:otherwise>
</c:choose>

