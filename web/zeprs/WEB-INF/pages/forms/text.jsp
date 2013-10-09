<%@ page import="org.cidrz.webapp.dynasite.session.SessionUtil,
                 java.util.List,
                 org.cidrz.webapp.dynasite.dao.DistrictDAO"%>
<%@ page import="org.cidrz.webapp.dynasite.utils.WidgetUtils"%>
<%@ page import="org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<c:choose>
    <c:when test='${pageItem.inputType=="apgar"}'>
        <c:choose>
        <c:when test='${param.id != null}'>
        <zeprs:apgar pageItem="${pageItem}" edit="true"/>
        </c:when>
        <c:otherwise>
        <zeprs:apgar pageItem="${pageItem}"/>
        </c:otherwise>
        </c:choose>
    </c:when>
    <c:when test='${pageItem.inputType=="ega"}'>
        <zeprs:ega pageItem="${pageItem}"/>
    </c:when>

    <c:when test='${pageItem.inputType=="position"}'>
        <zeprs:position pageItem="${pageItem}"/>
    </c:when>
    <c:when test='${pageItem.inputType=="position-dropdown"}'>
        <zeprs:position-dropdown pageItem="${pageItem}"/>
    </c:when>

    <c:when test='${pageItem.inputType=="nrc"}'>
        <zeprs:nrc pageItem="${pageItem}"/>
    </c:when>
    <c:when test='${pageItem.inputType=="sites"}'>
        <%
        String patientSiteId = SessionUtil.getInstance(session).getClientSettings().getSiteId().toString();
        request.setAttribute("patientSiteId",patientSiteId);
        %>
        <zeprs:sites pageItem="${pageItem}"/>
    </c:when>
    <c:when test='${pageItem.inputType=="sites_not_selected"}'>
        <zeprs:sites_not_selected pageItem="${pageItem}"/>
    </c:when>

    <c:when test='${pageItem.inputType=="other_pharm_dropdown"}'>
        <zeprs:other_pharm_dropdown pageItem="${pageItem}"/>
    </c:when>

    <c:when test='${pageItem.inputType=="other_pharm_paeds_dropdown"}'>
        <zeprs:other_pharm_paeds_dropdown pageItem="${pageItem}"/>
    </c:when>

    <c:when test='${pageItem.inputType=="checkbox"}'>
       <zeprs:checkbox pageItem="${pageItem}"/>
   </c:when>

    <c:when test='${pageItem.inputType=="Yes/No"}'>
       <zeprs:yesno pageItem="${pageItem}"/>
   </c:when>

    <c:when test='${field.type=="Section title"}'>
       <zeprs:spacer pageItem="${pageItem}"/>
    </c:when>

    <c:when test='${pageItem.inputType=="textarea"}'>
        <zeprs:textarea pageItem="${pageItem}"/>
    </c:when>

    <c:when test='${pageItem.inputType=="textarea_dwr"}'>
        <zeprs:textarea_dwr pageItem="${pageItem}"/>
    </c:when>

    <c:when test='${pageItem.inputType=="patientid"}'>
        <zeprs:patientid pageItem="${pageItem}"/>
    </c:when>

     <c:when test='${pageItem.inputType=="district"}'>
        <%
        List districts = DynaSiteObjects.getDistricts();
        request.setAttribute("districts",districts);
        %>
        <zeprs:district pageItem="${pageItem}"/>
    </c:when>

    <c:when test='${pageItem.inputType=="patientid_districts"}'>
        <%
        List districts = DynaSiteObjects.getDistricts();
        request.setAttribute("districts",districts);
        String patientDistrictId = SessionUtil.getInstance(session).getClientSettings().getDistrictId().toString();
        request.setAttribute("patientDistrictId",patientDistrictId);
        %>
        <zeprs:patientid_districts pageItem="${pageItem}"/>
    </c:when>

    <c:when test='${pageItem.inputType=="patientid_sites"}'>
        <%
        String patientSiteId = SessionUtil.getInstance(session).getClientSettings().getSiteId().toString();
        request.setAttribute("patientSiteId",patientSiteId);
        %>
        <zeprs:patientid_sites pageItem="${pageItem}"/>
    </c:when>

    <c:when test="${pageItem.inputType=='display-header'}"></c:when>
    <c:when test="${pageItem.inputType=='display-header-row'}"></c:when>

    <c:when test="${pageItem.inputType=='lab_results'}">
        <%
            // Lab Studies
    List labResultEnums = WidgetUtils.getDynaSiteLabEnums();
    request.setAttribute("labResultEnums", labResultEnums);
        %>
        <zeprs:lab_results pageItem="${pageItem}"/>
    </c:when>

    <c:when test='${pageItem.inputType=="Yes/No"}'>
        <zeprs:yesno_only pageItem="${pageItem}"/>
    </c:when>
    <c:when test="${pageItem.inputType=='text-only'}"><zeprs:text-only pageItem="${pageItem}"/></c:when>

    <c:when test='${pageItem.inputType=="uterus_size"}'>
        <zeprs:uterus_size pageItem="${pageItem}"/>
    </c:when>
    <c:when test='${pageItem.inputType=="firm"}'>
        <zeprs:firm pageItem="${pageItem}"/>
    </c:when>
    <c:when test='${pageItem.inputType=="fundal_height"}'>
        <zeprs:fundal_height pageItem="${pageItem}"/>
    </c:when>
    <c:when test="${pageItem.inputType=='text-dwr'}">
        <zeprs:input pageItem="${pageItem}"/>
    </c:when>

    <c:otherwise>
        <zeprs:input pageItem="${pageItem}" value="${thisValue}" renderedValue="${renderedValue}" encounterId="${encounterId}"/>
    </c:otherwise>
</c:choose>