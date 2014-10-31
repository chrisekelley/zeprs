<%@ page import="org.cidrz.webapp.dynasite.session.SessionUtil"%>
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
<c:set var="tdBackgroundColor" value="#fff"/>
<!-- loop through the pageItems and build the form-->
<c:forEach var="pageItem" begin="0" items="${encounterForm.pageItems}" varStatus="lineInfo">
<logic:present name="visiblePageItems">
    <logic:notEmpty name="visiblePageItems" property="pageItem${pageItem.id}">
        <c:set var="visibility" value="1"/>
    </logic:notEmpty>
    <logic:empty name="visiblePageItems" property="pageItem${pageItem.id}">
        <c:set var="visibility" value=""/>
    </logic:empty>
</logic:present>
<c:set var="field" value="${pageItem.form_field}" />
<c:set var="collapsing" value="1"/>
    <c:if test='${pageItem.form_field.enabled ==true}'>
    <c:choose>
        <c:when test="${pageItem.colspan >1}">
        <c:set var="colspan" value="${pageItem.colspan}"/>
        </c:when>
        <c:otherwise>
        <c:set var="colspan" value="1"/>
        </c:otherwise>
    </c:choose>
	<!-- ${pageItem.inputType} : tblItem: ${tblItem} -->
    <c:choose><%--First setup special table formatting and hidden fields - items that don't appear in normal layout--%>
        <c:when test="${pageItem.inputType=='display-tbl-begin'}">
                <c:choose>
                    <c:when test="${(pageItem.visible=='false') && (empty visibility)}">
                    <table border="0" cellpadding="4" cellspacing="2" width="95%"  id="tbl${pageItem.form_field.id}" summary="${pageItem.cols} col table" style="display:none; border:none; margin:5px;">
                    </c:when>
                    <c:otherwise>
                     <table border="0" cellpadding="4" cellspacing="2" width="95%"  id="tbl${pageItem.form_field.id}" summary="${pageItem.cols} col table" class="formTable">
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

        <c:when test="${pageItem.inputType=='display-tbl-right-begin'}">
                <div id="rightCol">
                <c:choose>
                    <c:when test="${(pageItem.visible=='false') && (empty visibility)}">
                    <table border="0" cellpadding="4" cellspacing="2" width="95%"  id="tbl${pageItem.form_field.id}" summary="${pageItem.cols} col table" style="display:none; border:none; margin:5px;">
                    </c:when>
                    <c:otherwise>
                     <table border="0" cellpadding="4" cellspacing="2" width="95%"  id="tbl${pageItem.form_field.id}" summary="${pageItem.cols} col table" class="formTable">
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
        <c:when test="${pageItem.inputType=='display-tbl-right-end'}">
        </table>
        </div>
        <c:set var="tblItem" value="0"/>
        <c:set var="tblCols" value="0"/>
        </c:when>

        <c:when test='${fn:substringBefore(pageItem.inputType,"-")=="hidden"}'>
        <zeprs:hidden pageItem="${pageItem}"/>
        </c:when>

        <c:when test='${pageItem.inputType=="patientid" && param.patientId == null}'>
                <zeprs:patientid pageItem="${pageItem}"/>
            </c:when>

        <c:when test='${pageItem.inputType=="patientid_sites" && param.patientId == null}'>
            <%
            String patientSiteId = SessionUtil.getInstance(session).getClientSettings().getSiteId().toString();
            request.setAttribute("patientSiteId",patientSiteId);
            %>
            <zeprs:patientid_sites pageItem="${pageItem}"/>
        </c:when>

        <c:when test='${empty subject && pageItem.inputType=="currentMedicine"}'>
           <zeprs:currentMedicine pageItem="${pageItem}"/>
       </c:when>
       <c:when test='${empty subject && pageItem.inputType=="currentImmunizations"}'>
           <zeprs:currentImmunizations pageItem="${pageItem}"/>
       </c:when>
        <c:when test='${empty subject && pageItem.inputType=="multiselect_item"}'>
            <zeprs:multiselect_item pageItem="${pageItem}"/>
        </c:when>
        <c:when test="${pageItem.inputType=='display-header'}">
        <c:set var="collapsing" value="0"/>
        <tr class="sectionHeader"><td colspan="${tblCols}"><zeprs:display_header pageItem="${pageItem}"/></td></tr>
        <c:set var="tblItem" value="0"/>
        </c:when>
        <c:when test="${pageItem.inputType=='display-subheader'}">
        <c:set var="collapsing" value="0"/>
        <tr style="background-color:${tdBackgroundColor};"><td colspan="${tblCols}" style="background-color:#E6E6FA;"><zeprs:display_subheader_row pageItem="${pageItem}"/></td></tr>
        <c:set var="tblItem" value="0"/>
        </c:when>
        <c:when test="${pageItem.inputType=='collapsing-display-header-begin'}">
        <c:set var="collapsing" value="1"/>
        <c:set var="tblItem" value="0"/>
        <table border="0" cellpadding="4" cellspacing="2" width="95%"  id="tbl${pageItem.form_field.id}" summary="${pageItem.cols} col table" class="formTable">
        <tr class="sectionHeader"><td colspan="${colspan}"><zeprs:display_collapsing_header pageItem="${pageItem}"/></td></tr>
        </table>
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
            <!-- ${pageItem.inputType} : tblItem: ${tblItem} -->
            <c:choose>
                <c:when test="${pageItem.inputType=='display-subheader'}">
                    <c:set var="tdBackgroundColor" value="#E6E6FA"/>
                </c:when>
                <c:when test="${pageItem.inputType=='infotext'}">
                    <c:set var="tdBackgroundColor" value="#E6E6FA"/>
                </c:when>
                <c:when test="${pageItem.highlightCell == 'true'}">
                    <c:set var="tdBackgroundColor" value="#FFFF33"/>
                </c:when>
                <c:when test="${pageItem.highlightCell == false}">
                    <c:set var="tdBackgroundColor" value="#fff"/>
                </c:when>
            </c:choose>
            <c:choose><%--Now open the table cell--%>
                <c:when test="${! empty subject && field.type != 'Display'}">
                        <td id="cell${pageItem.form_field.id}" colspan="${colspan}" valign="top" style="background-color:${tdBackgroundColor};border: 1px solid silver;" ondblclick="callWidget('${pageItem.id}', '${field.id}', '${pageItem.formId}', '${subject.id}')">
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${(pageItem.visible=='false') && (empty visibility)}">
                            <c:choose>
                            <c:when test="${collapsing == '1'}">
                            <c:set var="tdBackgroundColor" value="#fff"/>
                            <td id="field${pageItem.form_field.id}" colspan="${colspan}" class="defaultCell" style="display:none" valign="top">
                            </c:when>
                            <c:otherwise>
                            <c:set var="tdBackgroundColor" value="#fff"/>
                            <td id="field${pageItem.form_field.id}" colspan="${colspan}" style="display:none" valign="top">
                            </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <td id="cell${pageItem.form_field.id}" valign="top" colspan="${colspan}" style="background-color:${tdBackgroundColor};border: 1px solid silver;" onclick="justReveal('field${pageItem.form_field.id}')">
                            <c:set var="tdBackgroundColor" value="#fff"/>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        <%--${pageItem.inputType}: ${pageItem.id}--%>
            <c:choose>
            <c:when test='${! empty subject}'>
                <c:set var="encounterId" value="${subject.id}"/>
                <c:if test='${field.type != "Display"}'>
                    <logic:notPresent name="form${encounterForm.id}" property="field${field.id}">
                        <bean:define id="thisValue" value=""/>
                    </logic:notPresent>
                    <logic:present name="form${encounterForm.id}" property="field${field.id}">
                        <bean:define id="thisValue" name="form${encounterForm.id}" property="field${field.id}"/>
                    </logic:present>
                    <logic:empty name="subject" property="encounterMap.field${field.id}">
                        <bean:define id="renderedValue" value="" />
                    </logic:empty>
                    <logic:notEmpty name="subject" property="encounterMap.field${field.id}">
                        <bean:define id="renderedValue" name="subject" property="encounterMap.field${field.id}" />
                    </logic:notEmpty>
                </c:if>
            </c:when>
            <c:otherwise>
                <c:set var="encounterId" value=""/>
                <c:set var="thisValue" value=""/>
                <c:set var="renderedValue" value=""/>
            </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${! empty subject}">
                <c:choose>
                    <c:when test='${field.type == "Display"}'>
                        <%@ include file="/WEB-INF/pages/forms/display_items.jsp" %>
                    </c:when>
                    <c:otherwise>
                    <c:if test="${encounterForm.id == '55'}"><br/></c:if>
                    <c:choose>
		               <c:when test='${pageItem.roles=="DELETE_ARCHIVE_PATIENT_RECORDS"}'>
		            		<logic:present role="DELETE_ARCHIVE_PATIENT_RECORDS">
								<strong>${field.label}:</strong>
			                    <span id="value${field.id}" class="renderedValue">${renderedValue}</span>
			                    <span id="widget${field.id}"></span>
			                    <em>${field.units}</em>
			                    <c:if test="${(pageItem.inputType =='emptyDate') || (pageItem.inputType =='birthdate')|| (pageItem.inputType =='dateToday')}">
			                        <jsp:useBean id="now" class="java.util.Date" />
			                        <fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow" />
			                        <fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow" />
			                        <fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow" />
			                        <c:set var="theDate" value="${now}"/>
			                    <div id="slcalcodfield${field.id}" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
			<script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","field${field.id}");</script></div>
			                    </c:if>
		            	    </logic:present>
		                </c:when>
		                <c:otherwise>
				            <strong>${field.label}:</strong>
		                    <span id="value${field.id}" class="renderedValue">${renderedValue}</span>
		                    <span id="widget${field.id}"></span>
		                    <em>${field.units}</em>
		                    <c:if test="${(pageItem.inputType =='emptyDate') || (pageItem.inputType =='birthdate')|| (pageItem.inputType =='dateToday')}">
		                        <jsp:useBean id="nownow" class="java.util.Date" />
		                        <fmt:formatDate type="both" pattern="yyyy" value="${nownow}" var="yearnow" />
		                        <fmt:formatDate type="both" pattern="MM" value="${nownow}" var="monthnow" />
		                        <fmt:formatDate type="both" pattern="dd" value="${nownow}" var="datenow" />
		                        <c:set var="theDate" value="${nownow}"/>
		                    <div id="slcalcodfield${field.id}" style="position:absolute; left:100px; top:100px; z-index:10; visibility:hidden;">
		<script type="text/javascript">printCalendar("Sun","Mon","Tue","Wed","Thu","Fri","Sat","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","${datenow}","${monthnow}","${yearnow}","field${field.id}");</script></div>
		                    </c:if>
		               	</c:otherwise>
	           		</c:choose>
                    </c:otherwise>
                </c:choose>
                </c:when>
                <c:otherwise>
                    <%@ include file="/WEB-INF/pages/forms/fields.jsp" %>
                </c:otherwise>
            </c:choose>
            </td>
            <c:choose><%--Close the row--%>
            <c:when test="${pageItem.closeRow==true}">
            </tr><c:set var="tblItem" value="0"/>
            </c:when>
            <c:when test="${tblItem==0 ||tblItem==tblCols }"></tr></c:when>
            </c:choose>
            </c:otherwise>
        </c:choose>
    </c:if>
</c:forEach>