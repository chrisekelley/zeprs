<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri= "/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ attribute name="patientId" required="false" type="java.lang.String" %>
<%@ attribute name="user" required="false" type="java.lang.String" %>
<%@ attribute name="siteId" required="false" type="java.lang.String" %>
<%@ attribute name="pregnancyId" required="false" type="java.lang.String" %>
<%@ attribute name="seqFetus" required="false" type="java.lang.String" %>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate type="both" pattern="MMMM dd, yyyy HH:mm:ss" value="${now}" var="fulldate" />
<fmt:formatDate type="both" pattern="yyyy" value="${now}" var="yearnow" />
<fmt:formatDate type="both" pattern="MM" value="${now}" var="monthnow" />
<fmt:formatDate type="both" pattern="dd" value="${now}" var="datenow" />
<c:set var="theDate" value="${now}"/>
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${now}" var="daynow" />
<fmt:formatDate type="both" pattern="dd/MM/yyyy" value="${theDate}" var="nicedateVisit" />
<fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${theDate}" var="dbdateVisit" />
<c:set var="lastTwoYears" value="${yearnow - 2}"/>
<c:set var="lastYear" value="${yearnow - 1}"/>
<c:set var="twoYears" value="${yearnow + 2}"/>
<c:set var="fieldId" value="99999"/>

<script type='text/javascript' src='/zeprs/dwr/interface/Fetus.js;jsessionid=${pageContext.request.session.id}'></script>
<script type='text/javascript' src='/zeprs/js/engine2.jsp;jsessionid=${pageContext.request.session.id}'></script>
<script type='text/javascript' src='/zeprs/dwr/util.js;jsessionid=${pageContext.request.session.id}'></script>
<script type="text/javascript" src="/zeprs/js/dwr-generic.js;jsessionid=${pageContext.request.session.id}"></script>
<input type="hidden" name="examSequence" id="examSequence" value="${examSequence}"/>
<input type="hidden" name="patientId" id="patientId" value="${patientId}"/>
<input type="hidden" name="user" id="user" value="${user}"/>
<input type="hidden" name="siteId" id="siteId" value="${siteId}"/>
<input type="hidden" name="pregnancyId" id="pregnancyId" value="${pregnancyId}"/>
<input id="sequence" type="hidden" name="sequence" size="1" maxlength="1" value="${seqFetuses}" >
<table border="0" cellpadding="4" cellspacing="2" width="95%"  id="tbl99999999a" summary="3 col table" class="enhancedtableMargin2">
    <tr>
        <th>Condition of Foetus</th>
        <th>Lie</th>
        <th>Presentation</th>
    </tr>
    <tr>
        <td class="defaultCell">
            <select name="field964" id="condition"><!-- condition -->
                <option value="" selected="selected">No Information</option>
                <option value="511">Alive</option>
                <option value="1041">Dead</option>
            </select>
        </td>
        <td class="defaultCell">
            <select name="field313" id="lie"><!-- lie -->
                <option value="" selected="selected">No Information</option>
                <option value="168">Longitudinal</option>
                <option value="700">Transverse</option>
                <option value="1157">Oblique</option>
            </select>
        </td>
        <td class="defaultCell">
            <select name="field314" onchange="toggleField('dropdown', 2197, '1508','314');" id="selectfield314"><!-- presentation -->
                <option value="" selected="selected">No Information</option>
                <option value="169">Cephalic</option>
                <option value="701">Breech</option>
                <option value="1158">Shoulder</option>
                <option value="1464">Comp.</option>
                <option value="1688">Hand</option>
                <option value="1858">Face</option>
                <option value="1994">Brow</option>
                <option value="2102">Uncert.</option>
                <option value="2197">Other, Describe</option>
            </select>
             <textarea name="field1508" id="field1508" cols="32" rows="2" style="display:none"></textarea>
        </td>
    </tr>
</table>
<table border="0" cellpadding="4" cellspacing="2" width="95%"  id="tbl99999999a" summary="2 col table" class="enhancedtableMargin2">
    <tr>
        <th>Biparietal Diameter (BPD)</th>
        <th>Femur Length</th>
        <th>Fetal Ab. Circum.</th>
        <th>Fetal Weight</th>
        <th>&nbsp;</th>
    </tr>
    <tr>
        <td class="defaultCell">
            <input size="2" maxlength="4" name="field955" id="bpd" autocomplete="off" type="text" onChange="validateFloatMessage('bpd')"> cm (xx.x)
        </td>
        <td class="defaultCell">
            <input size="2" maxlength="2" name="field956" id="femur" autocomplete="off" type="text" onChange="validateIntegerMessage('femur')"> cm
        </td>
        <td class="defaultCell">
             <input size="2" maxlength="2" name="field957" id="circumference" autocomplete="off" type="text" onChange="validateIntegerMessage('circumference')"> cm
        </td>
        <td class="defaultCell">
             <input size="2" maxlength="4" name="field1947" id="weight" autocomplete="off" type="text" onChange="validateFloatMessage('weight')"> KG (x.xx)
        </td>

        <td valign="bottom" class="defaultCell"><input id="saveButton" class='ibutton' type='button' onclick='checkFetus("patient","Please enter all of the Fetus fields.","sequence");' value='Enter' title='Enter'/></td>
    </tr>
    <tr>
        <td colspan="5" class="defaultCell"><span id='newbornResults' class='reply'></span></td>
    </tr>
</table>


<script type='text/javascript'>
<c:if test="${! empty encounterId}">Fetus.createResponse(fillFetusForm, ${examSequence}, ${patientId}, ${pregnancyId});</c:if>    
    var reply1 = function(data)
    {
      document.getElementById('newbornResults').innerHTML = data;
    }
  </script>



