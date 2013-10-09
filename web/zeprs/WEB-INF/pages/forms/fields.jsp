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
    <c:when test='${field.type == "Display"}'>
        <%@ include file="/WEB-INF/pages/forms/display_items.jsp" %>
    </c:when>
    <c:when test='${field.type == "Enum"}'>
        <%@ include file="/WEB-INF/pages/forms/enum.jsp" %>
    </c:when>
    <c:when test='${field.type=="Year"}'>
             <zeprs:year pageItem="${pageItem}"/>
     </c:when>

     <c:when test='${field.type=="Day"}'>
            <zeprs:day pageItem="${pageItem}"/>
     </c:when>

     <c:when test='${field.type=="Month"}'>
            <zeprs:month pageItem="${pageItem}"/>
     </c:when>
    <c:when test='${field.type=="Yes/No"}'>
        <c:choose>
           <c:when test='${pageItem.inputType=="checkbox"}'>
               <zeprs:checkbox pageItem="${pageItem}"/>
           </c:when>
            <c:when test='${pageItem.inputType=="yesno_no_na"}'>
            	<zeprs:yesno_no_na pageItem="${pageItem}"/>
           </c:when>
           <c:when test='${pageItem.inputType=="yesno_br"}'>
               <zeprs:yesno_br pageItem="${pageItem}"/>
           </c:when>
            <c:when test='${pageItem.inputType=="yesno_br_each"}'>
               <zeprs:yesno_br_each pageItem="${pageItem}"/>
           </c:when>
            <c:when test='${pageItem.inputType=="yesno_nolabel"}'>
               <zeprs:yesno_nolabel pageItem="${pageItem}"/>
           </c:when>
           <c:otherwise>
               <c:choose>
	               <c:when test='${pageItem.roles=="DELETE_ARCHIVE_PATIENT_RECORDS"}'>
	            		<logic:present role="DELETE_ARCHIVE_PATIENT_RECORDS">
	            	    <zeprs:yesno pageItem="${pageItem}"/>
	            	    </logic:present>
	                </c:when>
	                <c:otherwise>
	               		<zeprs:yesno pageItem="${pageItem}"/>
	               	</c:otherwise>
           		</c:choose>
           </c:otherwise>
        </c:choose>
     </c:when>
     <c:when test='${field.type=="whoStage"}'>
          <zeprs:whostage pageItem="${pageItem}"/>
    </c:when>
     <c:when test='${field.type=="sex"}'>
          <zeprs:sex pageItem="${pageItem}"/>
     </c:when>
    <c:when test='${pageItem.inputType=="ega_pregnancyDating"}'>
        <c:if test="${! empty sessionPatient}">
            EGA: <zeprs:ega_pregnancyDating pageItem="${pageItem}" pos="${pos}" remoteClass="${remoteClass}" classname="${classname}" propertyName="${pageItem.form_field.starSchemaName}" patientId="${zeprs_session.sessionPatient.id}" pregnancyId="${zeprs_session.sessionPatient.currentPregnancyId}" user = "${user}" siteId="${siteId}" formId="${pageItem.form.id}"  value="${egaToday}"/>
        </c:if>
        </c:when>
     <c:when test='${field.type=="adherenceRate"}'>
         <zeprs:adherence pageItem="${pageItem}"/>
     </c:when>
     <c:when test='${field.type=="Date"}'>
        <%@ include file="/WEB-INF/pages/forms/date.jsp" %>
    </c:when>

    <c:when test='${field.type=="Time"}'>
         <zeprs:time pageItem="${pageItem}"/>
    </c:when>

    <c:otherwise>
        <%@ include file="/WEB-INF/pages/forms/text.jsp" %>
    </c:otherwise>
</c:choose>
