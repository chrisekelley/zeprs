<%@ page import="java.util.List,
                 org.cidrz.webapp.dynasite.valueobject.DynaSiteObjects"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="numFields" value="10"/>
<c:set var="emptyField" value="0"/>

<div id="partoDrugs">
<h2>Partograph: Drugs dispensed</h2>
<jsp:include page="toc.jsp"/>
<%
    List list = null;
    list = DynaSiteObjects.getDrugs();
    request.setAttribute("list", list);
    %>

    <div>
        <table border="1">
            <tr>
                <c:forEach var="item" varStatus="loop"  begin="1" end="17" >
                <c:set var="pos" value="${loop.index}"/>
                <tr>
                <td id="drugsDispensedCell${pos}" valign="top">
                    <c:choose>
                        <c:when test="${! empty drugsDispensed}">
                            <logic:notEmpty name="drugsDispensed" property="timeObservation${pos}">
                                <span id="timeObservationDrugsResults${pos}" onclick="toggle2('timeObservationSpan${pos}')" class="bold">
                                <bean:write name="drugsDispensed" property="timeObservation${pos}"/>
                                </span>
                                <br/>
                                <span id="timeObservationSpan${pos}" style="display:none;">
                                <html:text styleId="timeObservationField${pos}" size="7" maxlength="8" name="drugsDispensed" property="timeObservation${pos}" title="Click to update Time" />
                                <a href="#" onclick="copyTime('timeObservationField${pos}', '${fulldate}');">(Current Time)</a>
                                <br/>
                                <input type="button" name="_add" value="Done" onclick="insertDrugsDispensed(${pos},'timeObservationField${pos}', 'DrugsDispensed', 'timeObservation', replyTimeObservation, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId}); toggle2('timeObservationSpan${pos}')">
                                </span>
                            </logic:notEmpty>
                            <logic:empty name="drugsDispensed" property="timeObservation${pos}">
                                <span id="timeObservationDrugsResults${pos}" onclick="toggle2('timeObservationSpan${pos}')" class="bold"></span>
                                <span id="timeObservationSpan${pos}" style="display:none;">
                                <html:text styleId="timeObservationField${pos}" size="7" maxlength="8" name="drugsDispensed" property="timeObservation${pos}" title="Click to update Time" />
                                <a href="#" onclick="copyTime('timeObservationField${pos}', '${fulldate}');">(Current Time)</a>
                                <input type="button" name="_add" value="Done" onclick="insertDrugsDispensed(${pos},'timeObservationField${pos}', 'DrugsDispensed', 'timeObservation', replyTimeObservation, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId}); toggle2('timeObservationSpan${pos}')">
                                </span>
                            </logic:empty>
                            <logic:notEmpty name="drugsDispensed" property="drugsDispensed${pos}">
                                <span id="drugsDispensedResults${pos}" onclick="toggle2('drugsDispensedSpan${pos}')">
                                    <bean:define id="drugList" name="drugsDispensed" property="drugsDispensed${pos}"/>
                                    <c:set var="drugListLength" value="${fn:length(drugList)-1}"/>
                                    <c:forTokens items="${drugList}" delims="," var="drug" end="${drugListLength}" >
                                    <logic:iterate id="item" name="list">
                                        <c:if test='${item.id==drug}'>${item.name}<br/></c:if>
                                    </logic:iterate>
                                    </c:forTokens>
                                </span>
                                <form>
                                    <table id="drugsDispensedSpan${pos}" style="display:none;">
                                        <tr>
                                            <td>
                                                <select size="10" name="drugs${pos}">
                                                <logic:iterate id="item" name="list">
                                                    <c:if test='${item.id==thisItem}'>
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:if>
                                                </logic:iterate>
                                                </select>
                                                <input type="hidden" name="drugsDispensed${pos}" id="drugsDispensed${pos}">
                                            </td>
                                            <td>
                                                <table>
                                                    <tr>
                                                        <td>
                                                            <p><input type="button" name="_add" value="Add" onclick="addShared(_allitems${pos},drugs${pos}, drugsDispensed${pos} )">
                                                            </p>
                                                            <p><input type="button" name="_add" value="Save" onclick="insertDrugsDispensed(${pos},'drugsDispensed${pos}', 'DrugsDispensed', 'drugsDispensed', replyDrugsDispensed, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})">
                                                            </p>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td>Choices:<br/>
                                                <select size="10" name="_allitems${pos}">
                                                <c:forEach items="${list}" var="item">
                                                <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </logic:notEmpty>
                            <logic:empty name="drugsDispensed" property="drugsDispensed${pos}">
                            <c:set var="emptyField" value="${emptyField +1}"/>
                            <span id="timeObservationDrugsResults${pos}" onclick="toggle2('timeObservationSpan${pos}')" class="bold"></span>
                                <span id="timeObservationSpan${pos}" style="display:none;">
                                <html:text styleId="timeObservationField${pos}" size="7" maxlength="8" name="drugsDispensed" property="timeObservation${pos}" title="Click to update Time" />
                                <a href="#" onclick="copyTime('timeObservationField${pos}', '${fulldate}');">(Current Time)</a>
                                <input type="button" name="_add" value="Done" onclick="insertDrugsDispensed(${pos},'timeObservationField${pos}', 'DrugsDispensed', 'timeObservation', replyTimeObservation, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId}); toggle2('timeObservationSpan${pos}')">
                                </span>
                            <c:choose>
                                <c:when test="${emptyField == 1}">
                                <span id="drugsDispensedResults${pos}" onclick="toggle2('drugsDispensedSpan${pos}')"><a href="#">Add Drugs</a></span>
                                </c:when>
                                <c:otherwise>
                                <span id="drugsDispensedResults${pos}" onclick="toggle2('drugsDispensedSpan${pos}')" style="display:none;"><a href="#">Add Drugs</a></span>
                                </c:otherwise>
                            </c:choose>

                            <form>
                                <table id="drugsDispensedSpan${pos}" style="display:none;">
                                    <tr>
                                        <td>
                                            <select size="10" name="drugs${pos}">
                                            <logic:iterate id="item" name="list">
                                                <c:if test='${item.id==thisItem}'>
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:if>
                                            </logic:iterate>
                                            </select>
                                            <input type="hidden" name="drugsDispensed${pos}" id="drugsDispensed${pos}">
                                        </td>
                                        <td>
                                            <table>
                                                <tr>
                                                    <td>
                                                        <p><input type="button" name="_add" value="Add" onclick="addShared(_allitems${pos},drugs${pos}, drugsDispensed${pos} )">
                                                        </p>
                                                        <p><input type="button" name="_add" value="Save" onclick="insertDrugsDispensed(${pos},'drugsDispensed${pos}', 'DrugsDispensed', 'drugsDispensed', replyDrugsDispensed, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})">
                                                        </p>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td>Choices:<br/>
                                            <select size="10" name="_allitems${pos}">
                                            <c:forEach items="${list}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                </table>
                                </form>
                            </logic:empty>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${pos == 1}">
                                <span id="drugsDispensedResults${pos}" onclick="toggle2('drugsDispensedSpan${pos}')"><a href="#">Add Drugs</a></span>
                                </c:when>
                                <c:otherwise>
                                <span id="drugsDispensedResults${pos}" onclick="toggle2('drugsDispensedSpan${pos}')" style="display:none;"><a href="#">Add Drugs</a></span>
                                </c:otherwise>
                            </c:choose>
                            <span id="timeObservationDrugsResults${pos}" onclick="toggle2('timeObservationSpan${pos}')" class="bold"></span>
                                <span id="timeObservationSpan${pos}" style="display:none;">
<%--
                                <html:text styleId="timeObservationField${pos}" size="7" maxlength="8" name="drugsDispensed" property="timeObservation${pos}" title="Click to update Time" />
--%>
                                <input type="text" id="timeObservationField${pos}" size="7" maxlength="8" name="timeObservation${pos}" title="Click to update Time" />
                                <a href="#" onclick="copyTime('timeObservationField${pos}', '${fulldate}');">(Current Time)</a>
                                <input type="button" name="_add" value="Done" onclick="insertDrugsDispensed(${pos},'timeObservationField${pos}', 'DrugsDispensed', 'timeObservation', replyTimeObservation, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId}); toggle2('timeObservationSpan${pos}')">
                                </span>
                            <form>
                            <table id="drugsDispensedSpan${pos}" style="display:none;">
                                <tr>
                                    <td>
                                        <select size="10" name="drugs${pos}">
                                        <logic:iterate id="item" name="list">
                                            <c:if test='${item.id==thisItem}'>
                                                <option value="${item.id}">${item.name}</option>
                                            </c:if>
                                        </logic:iterate>
                                        </select>
                                        <input type="hidden" name="drugsDispensed${pos}" id="drugsDispensed${pos}">
                                    </td>
                                    <td>
                                        <table>
                                            <tr>
                                                <td>
                                                    <p><input type="button" name="_add" value="Add" onclick="addShared(_allitems${pos},drugs${pos}, drugsDispensed${pos} )">
                                                    </p>
                                                    <p><input type="button" name="_add" value="Save" onclick="insertDrugsDispensed(${pos},'drugsDispensed${pos}', 'DrugsDispensed', 'drugsDispensed', replyDrugsDispensed, ${zeprs_session.sessionPatient.id}, ${zeprs_session.sessionPatient.currentPregnancyId},'${user}',${siteId})">
                                                    </p>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td>Choices:<br/>
                                        <select size="10" name="_allitems${pos}">
                                        <c:forEach items="${list}" var="item">
                                        <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<script type='text/javascript'>
    var replyTimeObservation = function(data)
    {
    var dvals = data.split("=");
    var key = "timeObservationDrugsResults" + dvals[1];
    var value =dvals[0];
    var element = document.getElementById(key);
    element.innerHTML = value;
    }
  </script>

<script type='text/javascript'>
    var replyDrugsDispensed = function(data)
    {
    var dvals = data.split("=");
    var key = "drugsDispensedResults" + dvals[1];
    var value =dvals[0];
    var drugsDispensedValue = document.getElementById(key);
    drugsDispensedValue.innerHTML = value;
    var timeElement = document.getElementById("timeObservationDrugsResults" + dvals[1]);
    var timeValue =dvals[2];
    timeElement.innerHTML = timeValue;
    var results =  document.getElementById("drugsDispensedSpan" + dvals[1]);
    results.style.display = "none";
    var nextDrug = new Number(dvals[1]) + 1;
    var addDrugs =  document.getElementById("drugsDispensedResults" + nextDrug);
    addDrugs.style.display = "";
    }
  </script>