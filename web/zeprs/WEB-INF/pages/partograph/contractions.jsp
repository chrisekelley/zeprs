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

<c:forEach var="row" items="5,4,3,2,1" varStatus="iRow">
<tr id="contractionsRow${iRow.index}">
<c:if test="${row =='5'}">
<td rowspan="5" class="partoLabel"><p>Contr. per 10 mins.</p></td>
</c:if>
<td class="partoLegend"><div>${row}</div></td>
<c:choose>
    <c:when test="${! empty contractions}">
        <c:forEach var="item" varStatus="loop"  begin="${startRowHour}" end="${stopRowHour}">
            <c:set var="pos" value="${loop.index}"/>
                <logic:notEmpty name="contractions" property="contractions${pos}">
                    <bean:define id="thisItem" name="contractions" property="contractions${pos}"/>
                    <c:set var="itemRow" value="${fn:substringBefore(thisItem,'/')}"/>
                    <c:set var="itemValue" value="${fn:substringAfter(thisItem,'/')}"/>
                </logic:notEmpty>
                <logic:empty name="contractions" property="contractions${pos}">
                    <c:set var="itemRow" value=""/>
                    <c:set var="itemValue" value=""/>
                </logic:empty>
                <c:choose>
                    <c:when test="${row == itemRow}">
                            <c:choose>
                                <c:when test="${itemValue == 'Mi'}">
                                    <td id="contractionsCell${row}.${pos}" colspan="2" onclick="toggle2('contractionsField${row}.${pos}')" class="contractionDots">&nbsp;
                                </c:when>
                                <c:when test="${itemValue == 'St'}">
                                    <td id="contractionsCell${row}.${pos}" colspan="2" onclick="toggle2('contractionsField${row}.${pos}')" class="chartFilled">&nbsp;
                                </c:when>
                                <c:when test="${itemValue == 'Me'}">
                                    <td id="contractionsCell${row}.${pos}" colspan="2" onclick="toggle2('contractionsField${row}.${pos}')" class="contractionLines">&nbsp;
                                </c:when>
                                <c:otherwise>
                                    <td id="contractionsCell${row}.${pos}" colspan="2" onclick="toggle2('contractionsField${row}.${pos}')">${itemValue}
                                    </c:otherwise>
                            </c:choose>

                        <div id="property${pos}">
                            <select name="contractions${pos}" id="contractionsField${row}.${pos}"
                                    onchange="insertContractions(${pos}, '${row}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})"
                                    style="display:none; border:none">
                                <option value="">--</option>
                                <option value="delete">del.</option>
                                <option value="Mild">Mild</option>
                                <option value="Medium">Medium</option>
                                <option value="Strong">Strong</option>
                            </select>
                        </div>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td id="contractionsCell${row}.${pos}" colspan="2" onclick="toggle2('contractionsField${row}.${pos}')">
                        <div id="contractionsValue${row}.${pos}"></div>

                        <div id="property${pos}">
                            <select name="contractions${pos}" id="contractionsField${row}.${pos}"
                                    onchange="insertContractions(${pos}, '${row}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})"
                                    style="display:none; border:none">
                                <option value="">--</option>
                                <option value="delete">del.</option>
                                <option value="Mild">Mild</option>
                                <option value="Medium">Medium</option>
                                <option value="Strong">Strong</option>
                            </select>
                        </div>
                        </td>
                    </c:otherwise>
                </c:choose>
            </td>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:forEach var="item" varStatus="loop"  begin="${startRowHour}" end="${stopRowHour}" >
            <c:set var="pos" value="${loop.index}"/>
            <td id="contractionsCell${row}.${pos}" colspan="2" onclick="toggle2('contractionsField${row}.${pos}')">
                <div id="contractionsValue${row}.${pos}"></div>
                <div id="property${pos}">
                    <select name="contractions${pos}" id="contractionsField${row}.${pos}"
                            onchange="insertContractions(${pos}, '${row}',${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})" 
                            style="display:none; border:none">
                        <option value="">--</option>
                        <option value="delete">del.</option>
                        <option value="Mild">Mild</option>
                        <option value="Medium">Medium</option>
                        <option value="Strong">Strong</option>
                    </select>
                </div>
            </td>
        </c:forEach>
    </c:otherwise>
</c:choose>
</tr>
</c:forEach>


<script type='text/javascript'>
var replyContractionsOLD = function(data) {
    var dvals = data.split("=");
    var row = dvals[0];
    var column =dvals[1];
    var id = "contractionsCell" + row + "." + column;
    var item = document.getElementById(id);
    // item.innerHTML = row;
    item.className = "chartFilled";
}
var replyDeleteContractions = function(data)
{
    var dvals = data.split("=");
    var row = dvals[0];
    var column =dvals[1];
    // var id =  row + "." + column;
    var id = "contractionsCell" + row + "." + column;
    document.getElementById(id).className = '';
}

var replyContractions = function(data)
{
    var dvals = data.split("=");
    // alert("dvals: " + dvals);
    var valueVars = dvals[0].split("/");
    var key = "contractionsCell" + valueVars[0] + "." + dvals[1];
    var value = valueVars[1];
    var contractionsCell = document.getElementById(key);
    // var dots = '<img src="/zeprs/images/dots.gif">';
    // alert("value: " + value);
    if (value == "Mild") {
        contractionsCell.className = "contractionDots";
    } else if (value == "Medium") {
        contractionsCell.className = "contractionLines";
    } else {
        contractionsCell.className = "chartFilled";
    }

}
var replyDeleteContractionsOLD = function(data)
{
    var dvals = data.split("=");
    var key = "contractionsValue" + dvals[1];

    document.getElementById(key).innerHTML = '&nbsp;';
}

</script>




