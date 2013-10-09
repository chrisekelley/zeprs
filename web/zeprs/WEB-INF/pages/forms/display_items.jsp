<%@ page import="java.util.List,
                 org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects,
                 org.cidrz.webapp.dynasite.session.SessionUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>                 
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<c:choose>
    <c:when test='${pageItem.inputType=="infotext"}'>
        <c:choose>
            <c:when test="${! empty subject && field.id ==1512}">Change the "Site" field to move a patient to another clinic. (Advanced users only)</c:when>
            <c:otherwise><zeprs:infotext pageItem="${pageItem}"/></c:otherwise>
        </c:choose>
    </c:when>
    <c:when test='${pageItem.inputType=="display-spacer"}'>
    <zeprs:spacer/>
    </c:when>
    <c:when test='${pageItem.inputType=="display_collapsing_add_item_link"}'>
    <zeprs:display_collapsing_add_item_link pageItem="${pageItem}"/>
    </c:when>
    <c:when test='${pageItem.inputType=="display-subheader"}'>
    <zeprs:display_subheader pageItem="${pageItem}"/>
    <c:set var="tblItem" value="1"/>
    </c:when>
    <c:when test='${pageItem.inputType=="multiselect_drugs"}'>
        <c:choose>
            <c:when test="${! empty subject}"></c:when>
            <c:otherwise>
            <%
            List list = null;
            // list = DynaSiteObjects.getDrugs();
            list = (List) request.getAttribute("drugs");
            request.setAttribute("list", list);
            %>
        <zeprs:multiselect pageItem="${pageItem}" list="${list}" numFields="10"/>
            </c:otherwise>
        </c:choose>    
    </c:when>
    <c:when test='${pageItem.inputType=="multiselect_immun"}'>
    <%
        List list = null;
        //list = PersistenceManagerFactory.getInstance(Immunization.class).getAll();
        list = DynaSiteObjects.getImmunizations();
        request.setAttribute("list", list);
        %>
    <zeprs:multiselect pageItem="${pageItem}" list="${list}" numFields="5"/>
    </c:when>

    <c:when test='${pageItem.inputType=="newborn"}'>
    <%
        String patientId = null;
        String user = null;
        String siteId = null;
        String pregnancyId = null;
            patientId = patientId = SessionUtil.getInstance(session).getSessionPatient().getId().toString();

        user = request.getUserPrincipal().getName();
        try {
            siteId = SessionUtil.getInstance(session).getClientSettings().getSiteId().toString();
        } catch (SessionUtil.AttributeNotFoundException e) {
           // it's ok - we're in admin mode.
        }
        pregnancyId = SessionUtil.getInstance(session).getSessionPatient().getCurrentPregnancyId().toString();
        request.setAttribute("user",user);
        request.setAttribute("patientId",patientId);
        request.setAttribute("siteId",siteId);
        request.setAttribute("pregnancyId",pregnancyId);
            %>
    <zeprs:newborn patientId = "${patientId}" user = "${user}" siteId = "${siteId}" pregnancyId = "${pregnancyId}" />
    </c:when>
</c:choose>
