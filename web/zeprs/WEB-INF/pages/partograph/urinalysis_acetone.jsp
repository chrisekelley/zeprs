<%@ page import="org.cidrz.webapp.dynasite.session.SessionUtil,
                 org.cidrz.webapp.dynasite.valueobject.Patient"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tr id="urinalysisAcetoneRow0">
<td class="partoLabel"><div>Ace.</div></td>
<td class="partoLegend"><div>&nbsp;</div></td>
<c:choose>
    <c:when test="${! empty urinalysisAcetone}">
    <c:forEach var="item" varStatus="loop"  begin="${startRowHour}" end="${stopRowHour}">
        <c:set var="pos" value="${loop.index}"/>
            <td colspan="2" id="urinalysisAcetoneCell.${pos}" onclick="toggle2('urinalysisAcetoneField${pos}')">
            <logic:notEmpty name="urinalysisAcetone" property="urinalysisAcetone${pos}">
                <div id="urinalysisAcetoneValue${pos}"><bean:write name="urinalysisAcetone" property="urinalysisAcetone${pos}"/></div>
                <div id="property${pos}">
                <select name="urinalysisAcetone${pos}" id="urinalysisAcetoneField${pos}" onchange="insertUrinalysisAcetone(${pos},'urinalysisAcetoneField${pos}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none; border:none">
                    <option value="">--</option>
                    <option value="delete">del.</option>
                    <option value="0(-)">0(-)</option>
                    <option value="Tr.">Tr.</option>
                    <option value="1+">1+</option>
                    <option value="2+">2+</option>
                    <option value="3+">3+</option>
                    <option value="+4">+4</option>
                </select>
                </div>
            </logic:notEmpty>
            <logic:empty name="urinalysisAcetone" property="urinalysisAcetone${pos}">
                <div id="urinalysisAcetoneValue${pos}"></div>
                <div id="property${pos}">&nbsp;&nbsp;&nbsp;
                <select name="urinalysisAcetone${pos}" id="urinalysisAcetoneField${pos}" onchange="insertUrinalysisAcetone(${pos}, 'urinalysisAcetoneField${pos}', ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none; border:none">
                    <option value="">--</option>
                    <option value="delete">del.</option>
                    <option value="0(-)">0(-)</option>
                    <option value="Tr.">Tr.</option>
                    <option value="1+">1+</option>
                    <option value="2+">2+</option>
                    <option value="3+">3+</option>
                    <option value="+4">+4</option>
                </select>
                </div>
            </logic:empty>
            </td>

    </c:forEach>
    </c:when>
    <c:otherwise>
    <c:forEach var="item" varStatus="loop"  begin="${startRowHour}" end="${stopRowHour}" >
    <c:set var="pos" value="${loop.index}"/>
            <td colspan="2" id="urinalysisAcetoneCell.${pos}" onclick="toggle2('urinalysisAcetoneField${pos}')">
                <div id="urinalysisAcetoneValue${pos}"></div>
                <div id="property${pos}">&nbsp;&nbsp;&nbsp;
                <select name="urinalysisAcetone${pos}" id="urinalysisAcetoneField${pos}" onchange="insertUrinalysisAcetone(${pos}, 'urinalysisAcetoneField${pos}', ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" style="display:none; border:none">
                    <option value="">--</option>
                    <option value="delete">del.</option>
                    <option value="0(-)">0(-)</option>
                    <option value="Tr.">Tr.</option>
                    <option value="1+">1+</option>
                    <option value="2+">2+</option>
                    <option value="3+">3+</option>
                    <option value="+4">+4</option>
                </select>
                </div>
            </td>
    </c:forEach>
    </c:otherwise>
</c:choose>
</tr>

<script type='text/javascript'>
    var replyUrinalysisAcetone = function(data)
    {
    var dvals = data.split("=");
    var key = "urinalysisAcetoneValue" + dvals[1];
    var value =dvals[0];
    var urinalysisAcetoneValue = document.getElementById(key);
    urinalysisAcetoneValue.innerHTML = value;
    }
    var replyDeleteUrinalysisAcetone = function(data)
    {
    var dvals = data.split("=");
    var key = "urinalysisAcetoneValue" + dvals[1];
    document.getElementById(key).innerHTML = '&nbsp;';
    }
  </script>




