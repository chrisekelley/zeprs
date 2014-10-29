<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<%@ attribute name="edit" required="true" type="java.lang.String" %>
<c:set var="field" value="${pageItem.form_field}" />
<div class="row"><span class="labelNoBold">${field.label}: <c:if test='${field.required}'><span class="asterix">*</span> </c:if></span>
<span class="form">
<c:forEach var="enum" begin="0" items="${field.enumerations}"  varStatus="ctr">
    <c:choose>
           <%--<c:when test="${(pageItem.visibleEnumIdTrigger1 > 0) && (pageItem.visibleEnumIdTrigger2 > 0)}">
                <html:radio property="field${field.id}" styleId="field${field.id}${enum.id}" value="${enum.id}" onchange="toggleField2Deps('radio',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}',${pageItem.visibleEnumIdTrigger2}, '${pageItem.visibleDependencies2}','${field.id}${enum.id}');"/> ${enum.enumeration}
            </c:when>--%>
            <c:when test="${field.id == 1487}">
            <html:radio property="field${field.id}" styleId="field${field.id}${enum.id}" value="${enum.id}" onchange="toggleField2DepsChoice('radio', ${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}', ${pageItem.visibleEnumIdTrigger2}, '${pageItem.visibleDependencies2}','${field.id}${enum.id}');"/> ${enum.enumeration}
                <%--<c:if test="${(enum.id == pageItem.selectedEnum) && (edit == '0')}">
                <script type="text/javascript" language="Javascript1.1">
                <!-- Begin
                 var selRadio = document.getElementById("field${field.id}${enum.id}");
                 selRadio.checked=true;
                //End -->
                </script>
                </c:if>--%>
            </c:when>
            <c:when test="${(pageItem.visibleEnumIdTrigger1 > 0) && (pageItem.visibleEnumIdTrigger1 == enum.id)}">
               <html:radio property="field${field.id}" styleId="field${field.id}${enum.id}" value="${enum.id}" onchange="toggleField2Deps('radio',${pageItem.visibleEnumIdTrigger1}, '${pageItem.visibleDependencies1}','${field.id}${enum.id}');"/> ${enum.enumeration}
                <c:if test="${(enum.id == pageItem.selectedEnum) && (edit == '0')}">
                <script type="text/javascript" language="Javascript1.1">
                <!-- Begin
                 var selRadio = document.getElementById("field${field.id}${enum.id}");
                 selRadio.checked=true;
                //End -->
                </script>
                </c:if>
            </c:when>
            <c:when test="${(pageItem.visibleEnumIdTrigger2 > 0) && (pageItem.visibleEnumIdTrigger2 == enum.id)}">
                <html:radio property="field${field.id}" styleId="field${field.id}${enum.id}" value="${enum.id}" onchange="toggleField2Deps('radio',${pageItem.visibleEnumIdTrigger2}, '${pageItem.visibleDependencies2}','${field.id}${enum.id}');"/> ${enum.enumeration}
            </c:when>
            <c:otherwise>
               <html:radio property="field${field.id}" value="${enum.id}"/> ${enum.enumeration}
            </c:otherwise>
     </c:choose>
</c:forEach>
</span>
</div>