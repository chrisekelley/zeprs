<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2><bean:write name="encounterRecord" property="formName"/>
<logic:present name="drugs" scope="request">
    <jsp:useBean id="drugs" scope="request" type="java.util.List" />
</logic:present>

<html:link action="form${encounterRecord.formId}/correct" paramName="encounterRecord" paramProperty="id" paramId="id">(edit)</html:link>
</h2>
<%
//  Set up alternating row colors
String classRow = "oddRow";
%>
<table cellpadding="2" cellspacing="4" bgColor = "white">

    <tr>
        <td class="encounterlabel">Date of visit:</td>
        <td><fmt:formatDate value="${encounterRecord.dateVisit}"  pattern="dd MMM yyyy"/></td>
    </tr>
    <tr>
        <td class="encounterlabel">Record Created:</td>
        <td><fmt:formatDate value="${encounterRecord.auditInfo.created}"  pattern="dd MMM yyyy  HH:mm"/></td>
    </tr>
    <tr>
        <td class="encounterlabel">Record Modified</td>
        <td><fmt:formatDate value="${encounterRecord.auditInfo.lastModified}" pattern="dd MMM yyyy  HH:mm"/></td>
    </tr>
    <tr>
        <td class="encounterlabel">Site:</td>
        <td>${encounterRecord.auditInfo.site.name}</td>
    </tr>

    <logic:iterate id="encounterValue" name="encounterRecord" property="encounterMap" >
    <tr>
        <c:choose>
        <c:when test="${empty encounterValue.value}">
        <td class="encounterlabel" colspan="2">${encounterValue.key}</td>
        </c:when>
        <c:otherwise>
        <td>${encounterValue.key}</td>
        <td>${encounterValue.value}</td>
        </c:otherwise>
        </c:choose>
    </tr>
    </logic:iterate>
   <%-- <logic:iterate id="encounterValue" name="encounterRecord" property="encounterValues">
    <%
    //  Set up alternating row colors
    classRow = classRow.equals("evenRow")? "oddRow" : "evenRow";
    %>
    <tr class = "<%= classRow %>" >
    <td class="encounterlabel">
    <bean:write name="encounterValue" property="pageItem.form_field.label"/>
    </td>
    <td class="encountervalue">
	<c:choose>
        <c:when test='${encounterValue.pageItem.form_field.type=="year"}'>
                 <bean:write name="encounterValue" property="value"/> <br>
        </c:when>
        <c:when test='${encounterValue.pageItem.form_field.type=="Enum"}'>
            <c:choose>
                <c:when test='${encounterValue.pageItem.inputType=="druglist"}'>
                <c:forTokens items="${encounterValue.value}" delims="," var="thisDrug" >
                <logic:iterate id="drug" name="drugs">
                    <c:if test='${drug.id==thisDrug}'>
                        <bean:write name="drug" property="name"/> <br>
                    </c:if>
                    </logic:iterate>
                </c:forTokens>
                </c:when>
                <c:when test='${encounterValue.pageItem.inputType=="currentMedicine"}'>
                <%
                List drugs = null;
                drugs = PersistenceManagerFactory.getInstance(Drugs.class).getAll();
                request.setAttribute("drugs", drugs);
                %>
                <logic:iterate id="drug" name="drugs">
                    <c:if test='${drug.id==encounterValue.value}'>
                        <bean:write name="drug" property="name"/> <br>
                    </c:if>
                    </logic:iterate>
                </c:when>
                <c:when test='${encounterValue.pageItem.inputType=="currentImmunizations"}'>
                <%
                List items = null;
                items = PersistenceManagerFactory.getInstance(Drugs.class).getAll();
                request.setAttribute("items", items);
                %>
                <logic:iterate id="item" name="items">
                    <c:if test='${item.id==encounterValue.value}'>
                        <bean:write name="item" property="name"/> <br>
                    </c:if>
                    </logic:iterate>
                </c:when>
                <c:when test='${encounterValue.pageItem.inputType=="multiselect_enum"}'>
                <bean:define id="multiEnum" name="encounterValue" property="pageItem.form_field.enumerations"/>
                </c:when>
                <c:when test='${encounterValue.pageItem.inputType=="multiselect_item"}'>
                <logic:iterate id="item" name="multiEnum">
                    <c:if test='${item.id==encounterValue.value}'>
                        <bean:write name="item" property="enumeration"/> <br>
                    </c:if>
                    </logic:iterate>
                </c:when>
                <c:when test='${encounterValue.pageItem.inputType=="Yes/No"}'>
            <c:choose>
                <c:when test='${encounterValue.value=="1"}'>Yes</c:when>
                <c:when test='${encounterValue.value=="0"}'>No</c:when>
            </c:choose>
        </c:when>
                <c:otherwise>
                <c:if test="${! empty encounterValue.value}">
                <logic:iterate id="fenum" name="encounterValue" property="pageItem.form_field.enumerations" >
                    <c:if test='${fenum.id==encounterValue.value}'>
                        <bean:write name="fenum" property="enumeration"/> <br>
                    </c:if>
                </logic:iterate>
                </c:if>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:when test='${encounterValue.pageItem.form_field.type=="sex"}'>
            <c:if test='${encounterValue.value=="1"}'>
                 Female
            </c:if>
            <c:if test='${encounterValue.value=="2"}'>
                 Male
            </c:if>
         </c:when>
        <c:when test='${encounterValue.pageItem.form_field.type=="Yes/No"}'>
            <c:choose>
                <c:when test='${encounterValue.value=="1"}'>Yes</c:when>
                <c:when test='${encounterValue.value=="0"}'>No</c:when>
            </c:choose>
        </c:when>
        <c:otherwise>
            <bean:write name="encounterValue" property="value"/> <br>
        </c:otherwise>
    </c:choose>
    </td>
    </tr>
    </logic:iterate>--%>
</table>
<%--<%
    if ( System.currentTimeMillis()-encounterRecord.getAuditInfo().getLastModified().getTime() < 3600000 )
    {
        if ( encounterRecord.getAuditInfo().getLastModifiedBy().equals(request.getRemoteUser()))
        {
            %>--%>
<%--            <html:link action="form${encounterRecord.form.id}/correct" paramName="encounterRecord" paramProperty="id" paramId="id">Make a correction</html:link>--%>
           <%-- <%
        }
    }
%>--%>

