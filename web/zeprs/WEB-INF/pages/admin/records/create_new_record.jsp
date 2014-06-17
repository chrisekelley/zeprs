<c:forEach var="pageItem" begin="0" items="${encounterForm.pageItems}" varStatus="lineInfo">
    <c:if test='${pageItem.form_field.enabled ==true}'>
        <c:choose>
            <c:when test="${pageItem.colspan >1}">
                <c:set var="colspan" value="${pageItem.colspan}"/>
            </c:when>
            <c:otherwise>
                <c:set var="colspan" value="1"/>
            </c:otherwise>
        </c:choose>
        <c:choose><%--First setup special table formatting and hidden fields - items that don't appear in normal layout--%>
            <c:when test="${pageItem.inputType=='display-tbl-begin'}">
                <c:choose>
                    <c:when test="${(pageItem.visible=='false') && (empty visibility)}">
                        <table border="0" cellpadding="4" cellspacing="2" width="95%" id="tbl${pageItem.form_field.id}"
                        summary="${pageItem.cols} col table" style="display:none; border:none; margin:5px;">
                    </c:when>
                    <c:otherwise>
                        <table border="0" cellpadding="4" cellspacing="2" width="95%" id="tbl${pageItem.form_field.id}"
                        summary="${pageItem.cols} col table" class="formTable">
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
            <c:otherwise><%--Now set the rows for the normal fields--%>
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
                <c:choose>
                <c:when test="${pageItem.inputType=='display-subheader'}">
                    <c:set var="tdBackgroundColor" value="#E6E6FA"/>
                </c:when>
                <c:when test="${pageItem.inputType=='infotext'}">
                <c:set var="tdBackgroundColor" value="#E6E6FA"/>
                </c:when>
            </c:choose>
                <c:if test='${pageItem.form_field.enabled ==true && pageItem.inputType != "multiselect_enum"}'>
                    <c:set var="field" value="${pageItem.form_field}"/>
                    <c:set var="currentField" value="${field.id}" scope="request"/>
                    <c:choose>
                        <c:when test="${(pageItem.visible=='false') && (empty visibility)}">
                    		<td id="cell${pageItem.form_field.id}" colspan="${colspan}" style="background-color:${tdBackgroundColor};border: 1px solid silver;display:none">
                        </c:when>
                        <c:otherwise>
                            <td id="cell${pageItem.form_field.id}" colspan="${colspan}" style="background-color:${tdBackgroundColor};border: 1px solid silver;">
                        </c:otherwise>
                     </c:choose>
                        <%@ include file="/WEB-INF/pages/forms/fields.jsp" %>
                    </td>
                    <c:set var="tdBackgroundColor" value="#fff"/>
                </c:if>
            </c:otherwise>
        </c:choose>
        <c:choose><%--Close the row--%>
            <c:when test="${pageItem.closeRow==true}">
                </tr><c:set var="tblItem" value="0"/>
            </c:when>
            <c:when test="${tblItem==0 ||tblItem==tblCols }"><%--${field.label}:: ${tblItem}<br/>--%></tr></c:when>
        </c:choose>
    </c:if>
</c:forEach>

<div style="padding:5px;">
    <c:choose>
        <c:when test='${encounterForm.requireReauth}'>
            <zeprs:re_auth pageItem="${pageItem}"/>
        </c:when>
    </c:choose>
<html:submit onclick="bCancel=false;"/>
</div>