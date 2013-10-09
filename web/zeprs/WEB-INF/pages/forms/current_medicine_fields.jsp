<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<c:if test="${empty form}">
    <c:set var="form" value="${encounterForm}"/>
</c:if>
<c:choose>
    <c:when test="${! empty dateVisit}">
        <fmt:formatDate type="both" pattern="yyyy" value="${dateVisit}" var="yearnow" />
        <fmt:formatDate type="both" pattern="MM" value="${dateVisit}" var="monthnow" />
        <fmt:formatDate type="both" pattern="dd" value="${dateVisit}" var="datenow" />
        <c:set var="theDate" value="${dateVisit}"/>
    </c:when>
    <c:otherwise>
        <fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow" />
        <fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow" />
        <fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow" />
        <c:set var="theDate" value="${now}"/>
    </c:otherwise>
</c:choose>

<fmt:formatDate type="both" pattern="dd/MM/yyyy" value="${theDate}" var="nicedateVisit" />
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${theDate}" var="dbdateVisit" />

<c:set var="lastTwoYears" value="${yearnow - 2}"/>
<c:set var="lastYear" value="${yearnow - 1}"/>
<c:set var="twoYears" value="${yearnow + 2}"/>
<c:set var="sixtyYears" value="${yearnow - 60}"/>

<c:set var="numFields" value="0"/>

<c:forEach var="pageItem" begin="0" items="${form.pageItems}" varStatus="lineItem">
    <c:set var="field" value="${pageItem.form_field}" />
    <c:choose>
        <c:when test="${pageItem.inputType == 'currentMedicine'}">
            <c:set var="numFields" value="${numFields +1}"/>
            <html:hidden property="field${field.id}" styleId="item${lineItem.index}"/>
        </c:when>
    </c:choose>
</c:forEach>

    <tr>
        <!--<td width="20%" valign="top">Current Medicine:</td>-->
        <td colspan="${thisColspan}" align="left">
            <table>
                <tr>
                    <td valign="top" class="formrowlabel">List of all drugs:</td>
                    <td valign="top">&nbsp;</td>
                    <td valign="top" class="formrowlabel">Drugs Selected:</td>                       
                </tr>
                <tr>
                    <td valign="top"> <select size="10" name="_alldrugs" id="_allitems${field.id}">
                        <c:forEach items="${drugs}" var="drug">
                            <c:choose>
                                <c:when test="${! empty drug.teratogenic}">
                                    <option value="${drug.id}" class="teratogenicAlert">${drug.name} *${drug.teratogenic}*</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${drug.id}">${drug.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        </select>
                    </td>
                    <td valign="middle"><p>Select up to ${numFields} drugs. </p>
                        <html:button property="_add" value=">> Add Drug >>" onclick="addItem(${field.id},${numFields},'currentMed')"/><br>
                        <html:button property="_add" value="<< Remove <<" onclick="removeItem(${field.id},${numFields},'currentMed')"/><br>
                    </td>
                    <td valign="top">
                        <select size="10" name="_drugs" id="_items${field.id}">
                        <c:forEach var="pageItem" begin="0" items="${form.pageItems}" varStatus="lineItem">
                            <c:set var="field" value="${pageItem.form_field}" />
                        <c:choose>
                        <c:when test="${pageItem.inputType=='currentMedicine'}">
                        <bean:define id="thisDrug" name="form${encounterForm.id}" property="field${field.id}"/>
                        <logic:iterate id="drug" name="drugs">
                            <c:if test='${drug.id==thisDrug}'>
                                <option value="${drug.id}">${drug.name} *${drug.teratogenic}*</option>
                            </c:if>
                        </logic:iterate>
                        </c:when>
                        </c:choose>
                        </c:forEach>
                        </select>
                    </td>                      
                </tr>
            </table>
<%--
            <html:hidden property="field${field.id}" styleId="_fieldOrder"/>
--%>
        </td>
    </tr>

    <tr>
        <%--<td width="20%" valign="top">Other Medicine, if not in list:</td>--%>
        <td colspan="${thisColspan}" align="left">
        <c:forEach var="pageItem" begin="0" items="${form.pageItems}" varStatus="lineItem">
        <c:if test="${pageItem.form_field.enabled =='true'}">
            <c:set var="field" value="${pageItem.form_field}" />
        <c:choose>
        <c:when test="${pageItem.inputType != 'currentMedicine'}">Other Medicine: 
        <html:text size="40" maxlength="255" property="field${field.id}"/> ${field.units}
        </c:when>
        </c:choose>
        </c:if>
        </c:forEach>
        </td>
    </tr>
