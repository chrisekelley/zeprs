
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ attribute name="form" required="true" type="org.cidrz.webapp.dynasite.valueobject.Form" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate type="both" pattern="dd/MM/yyyy" value="${now}" var="daynow" />

<script language="javascript">
var doses = new Array();
var brands = new Array();

doses['']    = new Array();
doses[''][0] = new Array('Not Recorded');
doses[''][1] = new Array('');

doses['d4T']    = new Array();
doses['d4T'][0] = new Array('40 mg','30 mg','20 mg','15 mg');
doses['d4T'][1] = new Array('40 mg','30 mg','20 mg','15 mg');

doses['d4T+3TC']    = new Array();
doses['d4T+3TC'][0] = new Array('40+150 mg','30+150 mg');
doses['d4T+3TC'][1] = new Array('40+150 mg','30+150 mg');

doses['d4T+3TC+NVP']    = new Array();
doses['d4T+3TC+NVP'][0] = new Array('40+150+200 mg','30+150+200 mg');
doses['d4T+3TC+NVP'][1] = new Array('40+150+200 mg','30+150+200 mg');

doses['AZT oral']    = new Array();
doses['AZT oral'][0] = new Array('50 mg/5 ml');
doses['AZT oral'][1] = new Array('50 mg/5 ml');

doses['AZT tab']    = new Array();
doses['AZT tab'][0] = new Array('300 mg');
doses['AZT tab'][1] = new Array('300 mg');

doses['AZT+3TC']    = new Array();
doses['AZT+3TC'][0] = new Array('300+150 mg');
doses['AZT+3TC'][1] = new Array('300+150 mg');

doses['AZT+3TC+NVP']    = new Array();
doses['AZT+3TC+NVP'][0] = new Array('300+150+200 mg');
doses['AZT+3TC+NVP'][1] = new Array('300+150+200 mg');

doses['ddI']    = new Array();
doses['ddI'][0] = new Array('200 mg','50 mg');
doses['ddI'][1] = new Array('200 mg','50 mg');

doses['3TC tabs']    = new Array();
doses['3TC tabs'][0] = new Array('150 mg');
doses['3TC tabs'][1] = new Array('150 mg');

doses['3TC oral']    = new Array();
doses['3TC oral'][0] = new Array('10 mg/ml');
doses['3TC oral'][1] = new Array('10 mg/ml');

doses['ABC tabs']    = new Array();
doses['ABC tabs'][0] = new Array('300 mg');
doses['ABC tabs'][1] = new Array('300 mg');

doses['ABC oral']    = new Array();
doses['ABC oral'][0] = new Array('20 mg/ml');
doses['ABC oral'][1] = new Array('20 mg/ml');

doses['NVP tabs']    = new Array();
doses['NVP tabs'][0] = new Array('200 mg');
doses['NVP tabs'][1] = new Array('200 mg');

doses['NVP oral']    = new Array();
doses['NVP oral'][0] = new Array('10 mg/ml');
doses['NVP oral'][1] = new Array('10 mg/ml');

doses['EFV']    = new Array();
doses['EFV'][0] = new Array('600 mg','200 mg','50 mg');
doses['EFV'][1] = new Array('600 mg','200 mg','50 mg');

doses['NFV']    = new Array();
doses['NFV'][0] = new Array('250 mg');
doses['NFV'][1] = new Array('250 mg');

doses['IDV']    = new Array();
doses['IDV'][0] = new Array('400 mg');
doses['IDV'][1] = new Array('400 mg');

doses['RTV']    = new Array();
doses['RTV'][0] = new Array('100 mg');
doses['RTV'][1] = new Array('100 mg');

doses['LPV+RTV oral']    = new Array();
doses['LPV+RTV oral'][0] = new Array('400+100 mg');
doses['LPV+RTV oral'][1] = new Array('400+100 mg');

doses['LPV+RTV oral']    = new Array();
doses['LPV+RTV oral'][0] = new Array('400mg+100mg/5mg');
doses['LPV+RTV oral'][1] = new Array('400mg+100mg/5mg');

brands['']    = new Array();
brands[''][0] = new Array('Not Recorded');
brands[''][1] = new Array('');

brands['d4T']    = new Array();
brands['d4T'][0] = new Array('Zerit','Stavir','Avostav','Stadine');
brands['d4T'][1] = new Array('Zerit','Stavir','Avostav','Stadine');

brands['d4T+3TC']    = new Array();
brands['d4T+3TC'][0] = new Array('Lamivir S','Coviro');
brands['d4T+3TC'][1] = new Array('Lamivir S','Coviro');

brands['d4T+3TC+NVP']    = new Array();
brands['d4T+3TC+NVP'][0] = new Array('Triomune','Triviro');
brands['d4T+3TC+NVP'][1] = new Array('Triomune','Triviro');

brands['AZT oral']    = new Array();
brands['AZT oral'][0] = new Array('Retrovir sol.','Zidovir sol.');
brands['AZT oral'][1] = new Array('Retrovir sol.','Zidovir sol.');

brands['AZT tab']    = new Array();
brands['AZT tab'][0] = new Array('Retrovir','Zidovir','Aviro-Z','Zidine');
brands['AZT tab'][1] = new Array('Retrovir','Zidovir','Aviro-Z','Zidine');

brands['AZT+3TC']    = new Array();
brands['AZT+3TC'][0] = new Array('Combivir','Duovir','Avocomb','Lazid');
brands['AZT+3TC'][1] = new Array('Combivir','Duovir','Avocomb','Lazid');

brands['AZT+3TC+NVP']    = new Array();
brands['AZT+3TC+NVP'][0] = new Array('Duovir-N');
brands['AZT+3TC+NVP'][1] = new Array('Duovir-N');

brands['ddI']    = new Array();
brands['ddI'][0] = new Array('Videx','Divir(100mg)');
brands['ddI'][1] = new Array('Videx','Divir(100mg)');

brands['3TC tabs']    = new Array();
brands['3TC tabs'][0] = new Array('Epivir','Lamivir','Avacomb','Lavir');
brands['3TC tabs'][1] = new Array('Epivir','Lamivir','Avacomb','Lavir');

brands['3TC oral']    = new Array();
brands['3TC oral'][0] = new Array('Epivir sol.');
brands['3TC oral'][1] = new Array('Epivir sol.');

brands['ABC tabs']    = new Array();
brands['ABC tabs'][0] = new Array('Ziagen','Abamune');
brands['ABC tabs'][1] = new Array('Ziagen','Abamune');

brands['ABC oral']    = new Array();
brands['ABC oral'][0] = new Array('Ziagen sol.');
brands['ABC oral'][1] = new Array('Ziagen sol.');

brands['NVP tabs']    = new Array();
brands['NVP tabs'][0] = new Array('Viramune','Nevimune','Nevipan','Nevir');
brands['NVP tabs'][1] = new Array('Viramune','Nevimune','Nevipan','Nevir');

brands['NVP oral']    = new Array();
brands['NVP oral'][0] = new Array('Viramune sol.','Nevimune sol.');
brands['NVP oral'][1] = new Array('Viramune sol.','Nevimune sol.');

brands['EFV']    = new Array();
brands['EFV'][0] = new Array('Sustiva','Efavir','Aviranz','Stocrin');
brands['EFV'][1] = new Array('Sustiva','Efavir','Aviranz','Stocrin');

brands['NFV']    = new Array();
brands['NFV'][0] = new Array('Viracept','Nelfinavir');
brands['NFV'][1] = new Array('Viracept','Nelfinavir');

brands['IDV']    = new Array();
brands['IDV'][0] = new Array('Crixivan','Indivan-400','Avirodin');
brands['IDV'][1] = new Array('Crixivan','Indivan-400','Avirodin');

brands['RTV']    = new Array();
brands['RTV'][0] = new Array('Norvir','Ritomune');
brands['RTV'][1] = new Array('Norvir','Ritomune');

brands['LPV+RTV oral']    = new Array();
brands['LPV+RTV oral'][0] = new Array('Kaletra');
brands['LPV+RTV oral'][1] = new Array('Kaletra');

brands['LPV+RTV oral']    = new Array();
brands['LPV+RTV oral'][0] = new Array('Kaletra sol.');
brands['LPV+RTV oral'][1] = new Array('Kaletra sol.');

</script>

<c:if test='${param.id != null}'>
<p><b>You are in Edit mode.</b> You need to re-fill all of the items on this form.</p>
</c:if>
<p>Items marked with an asterix (<span class="asterix">*</span>) are required.</p>

<%--<html:form action="form${form.id}/save.do" onsubmit="return validateForm${form.id}(this);" >--%>

    <table cellpadding="2" cellspacing="0" border="1" width="100%">


<c:forEach var="field" begin="0" items="${form.fields}" varStatus="lineInfo">

<c:if test="${field.type=='Drug:type'}">

<script language="javascript">

// This function goes through the options for the given
// drop down box and removes them in preparation for
// a new set of values

function emptyListfield${field.id}( box ) {
	// Set each option to null thus removing it
	while ( box.options.length ) box.options[0] = null;

}

// This function assigns new drop down options to the given
// drop down box from the list of lists specified

function fillListfield${field.id}( box, arr ) {
	// arr[0] holds the display text
	// arr[1] are the values


	for ( i = 0; i < arr[0].length; i++ ) {

		// Create a new drop down option with the
		// display text and value from arr

		option = new Option( arr[0][i], arr[1][i] );

		// Add to the end of the existing options

		box.options[box.length] = option;
	}

	// Preselect option 0

	box.selectedIndex=0;
}

// This function performs a drop down list option change by first
// emptying the existing option list and then assigning a new set

function changeListfield${field.id}( box ) {
	// Isolate the appropriate list by using the value
	// of the currently selected option

	dose = doses[box.options[box.selectedIndex].value];
    brand = brands[box.options[box.selectedIndex].value];
	// Next empty the slave list
	// Then assign the new list values
</c:if>
<c:if test="${field.type=='Drug:type'}">
<c:choose>
<c:when test="${field.id=='1314'}">
    emptyListfield${field.id}( box.form.field1319 );
	fillListfield${field.id}( box.form.field1319, dose );
    emptyListfield${field.id}( box.form.field1324 );
	fillListfield${field.id}( box.form.field1324, brand );
}
</c:when>
<c:when test="${field.id=='1315'}">
    emptyListfield${field.id}( box.form.field1320 );
    fillListfield${field.id}( box.form.field1320, dose );
    emptyListfield${field.id}( box.form.field1325 );
	fillListfield${field.id}( box.form.field1325, brand );
}
</c:when>
<c:when test="${field.id=='1316'}">
    emptyListfield${field.id}( box.form.field1321 );
    fillListfield${field.id}( box.form.field1321, dose );
    emptyListfield${field.id}( box.form.field1326 );
	fillListfield${field.id}( box.form.field1326, brand );
}
</c:when>
<c:when test="${field.id=='1317'}">
    emptyListfield${field.id}( box.form.field1322 );
    fillListfield${field.id}( box.form.field1322, dose );
    emptyListfield${field.id}( box.form.field1327 );
	fillListfield${field.id}( box.form.field1327, brand );
}
</c:when>
<c:when test="${field.id=='1318'}">
    emptyListfield${field.id}( box.form.field1323 );
    fillListfield${field.id}( box.form.field1323, dose );
    emptyListfield${field.id}( box.form.field1328 );
	fillListfield${field.id}( box.form.field1328, brand );
}
</c:when>
</c:choose>
</script>
</c:if>
    <c:if test='${field.enabled ==true}'>

        <c:choose>
        <c:when test="${field.type=='Drug:dosage'}"></c:when>
        <c:when test="${field.type=='Drug:brand'}"></c:when>
        <c:when test='${field.displayHint.inputType=="date4"}'></c:when>
        <c:when test="${field.type=='Enum'}"></c:when>
        <c:when test='${field.displayHint.inputType=="hidden1"}'></c:when>
        <c:otherwise>
            <tr>
                <td width="15%" valign="middle" align="right">
        </c:otherwise>
        </c:choose>

        <c:if test='${field.type=="Section title"}'><strong></c:if>
        <c:choose>
            <c:when test="${field.type=='Drug:brand'}"></c:when>
            <c:when test='${field.displayHint.inputType=="date4"}'></c:when>
            <c:when test="${field.type=='Drug:dosage'}"></c:when>
            <c:when test='${field.displayHint.inputType=="hidden1"}'></c:when>
            <c:otherwise>${field.label}:</c:otherwise>
        </c:choose>
        <c:if test='${field.type=="Section title"}'></strong></c:if>
        <c:choose>
            <c:when test="${field.type=='Drug:brand'}"></c:when>
            <c:when test='${field.displayHint.inputType=="date4"}'></c:when>
            <c:when test='${field.type=="hidden1"}'></c:when>
            <c:otherwise><c:if test='${field.required}'><span class="asterix">*</span> </c:if></td></c:otherwise>
        </c:choose>

        <c:choose>
             <c:when test='${field.type=="Date"}'>
                <c:choose>
                    <c:when test='${field.displayHint.inputType=="text"}'>
                        <td colspan="5" align="left"><html:text size="10" maxlength="10" property="field${field.id}" value="${daynow}" /> (Format: DD/MM/YYYY)</td>
                    </c:when>
                    <c:when test='${field.displayHint.inputType=="date1"}'>
                        <td colspan="5" align="left"><table cellpadding="0" cellspacing="0"><tr><td><a href="javascript:cal_field${field.id}.popup();">
                        <img src="/art/images/cal.gif" align="middle" width="26" height="20" border="0" alt="Click Here to Pick up the timestamp"></a></td><td>&nbsp;<html:text size="10" maxlength="10"  onfocus="this.blur()" styleClass="disabled" property="field${field.id}" /></td></tr></table></td>
                    </c:when>
                    <c:when test='${field.displayHint.inputType=="date2"}'>
                        <td colspan="5" align="left"><table cellpadding="0" cellspacing="0"><tr><td><a href="javascript:cal_field${field.id}.popup();">
                        <img src="/art/images/cal.gif" align="middle" width="26" height="20" border="0" alt="Click Here to Pick up the timestamp"></a></td><td>&nbsp;<html:text size="10" maxlength="10" onfocus="this.blur()" styleClass="disabled" property="field${field.id}" value="${daynow}" /></td></tr></table></td>
                    </c:when>
                    <c:when test='${field.displayHint.inputType=="date3"}'>
                        <td colspan="5" align="left"><table cellpadding="0" cellspacing="0"><tr><td><a href="javascript:cal_field${field.id}.popup();">
                        <img src="/art/images/cal.gif" align="middle" width="26" height="20" border="0" alt="Click Here to Pick up the timestamp"></a></td><td>&nbsp;<html:text size="10" maxlength="10"  onfocus="this.blur()" styleClass="disabled" property="field${field.id}" value="${fmtdate1weekfuture}"  /></td></tr></table></td>
                    </c:when>
                    <c:when test='${field.displayHint.inputType=="date5"}'>
                        <td colspan="5" align="left"><table cellpadding="0" cellspacing="0"><tr><td>
                            <select name="theDate" onchange="this.form.field${field.id}.value = theDate[this.selectedIndex].value;" >
                                <option value="${fmtdate1weekfuture}">1 week</option>
                                <option value="${fmtdate2weekfuture}">2 weeks</option>
                                <option value="${fmtdate1monthfuture}">1 month</option>
                                <option value="${fmtdate2monthfuture}">2 months</option>
                                <option value="${fmtdate3monthfuture}">3 months</option>
                                <option value="${fmtdate6monthfuture}">6 months</option>
                            </select></td>
                        <td>&nbsp;<a href="javascript:cal_field${field.id}.popup();">
                        <img src="/art/images/cal.gif" align="middle" width="26" height="20" border="0" alt="Click Here to Pick up the timestamp"></a></td><td>&nbsp;<html:text size="10" maxlength="10"  onfocus="this.blur()" styleClass="disabled" property="field${field.id}" value="${fmtdate1weekfuture}"  /></td></tr></table></td>
                    </c:when>
                    <c:when test='${field.displayHint.inputType=="date4"}'>
                        <c:choose>
                            <c:when test="${field.id == 1255}">
                                <c:if test="${select_drug_disp_form.rowCount > 0}">
                                 <html:hidden property="field${field.id}" value="" />
                                </c:if>
                                <c:if test="${select_drug_disp_form.rowCount == 0}">
                                 <html:hidden property="field${field.id}" value="${daynow}" />
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <html:hidden property="field${field.id}" value="${daynow}" />
                            </c:otherwise>
                        </c:choose>                        
                    </c:when>
                </c:choose>
            </c:when>
            <c:when test='${field.displayHint.inputType=="hidden1"}'>
                <html:hidden property="field${field.id}" value="1" />
            </c:when>

            <c:when test='${field.type=="Drug:type"}'>

                <td>
                <html:select onchange="changeListfield${field.id}(this)" property="field${field.id}" >
                    <option value="">Not Recorded</option>
                    <option value="d4T">d4T</option>
                    <option value="d4T+3TC">d4T+3TC</option>
                    <option value="d4T+3TC+NVP">d4T+3TC+NVP</option>
                    <option value="AZT oral">AZT oral</option>
                    <option value="AZT tab">AZT tab</option>
                    <option value="AZT+3TC">AZT+3TC</option>
                    <option value="AZT+3TC+NVP">AZT+3TC+NVP</option>
                    <option value="ddI">ddI</option>
                    <option value="3TC tabs">3TC tabs</option>
                    <option value="3TC oral">3TC oral</option>
                    <option value="ABC tabs">ABC tabs</option>
                    <option value="ABC oral">ABC oral</option>
                    <option value="NVP tabs">NVP tabs</option>
                    <option value="NVP oral">NVP oral</option>
                    <option value="EFV">EFV</option>
                    <option value="NFV">NFV</option>
                    <option value="IDV">IDV</option>
                    <option value="RTV">RTV</option>
                    <option value="LPV+RTV oral">LPV+RTV caps</option>
                    <option value="LPV+RTV oral">LPV+RTV oral</option>
                </html:select>
                </td>
            </c:when>

            <c:when test='${field.type=="Drug:dosage"}'>
                    <td>
                    <html:select property="field${field.id}" >
                        <html:option value="">Not Recorded</html:option>
                    </html:select>
                    </td>
            </c:when>

            <c:when test='${field.type=="Drug:brand"}'>
                    <td>
                    <html:select property="field${field.id}" >
                        <html:option value="">Not Recorded</html:option>
                    </html:select>
                    </td>
                    </tr>
            </c:when>
        </c:choose>
        <c:choose>

            <c:when test='${field.type=="Drug:dosage"}'></c:when>
            <c:when test='${field.type=="Drug:type"}'></c:when>
            <c:when test='${field.displayHint.inputType=="date4"}'></c:when>
            <c:when test='${field.displayHint.inputType=="hidden1"}'></c:when>

            <c:otherwise>
                </tr>
            </c:otherwise>
        </c:choose>
    </c:if>
</c:forEach>
<c:if test='${form.requireReauth}'>
<tr><td colspan="4">&nbsp;</td></tr>
<tr><td colspan="4">Re-Authorization</td></tr>
<tr><td colspan="4" align="center">
    <table>
    <tr>
        <td>Username:</td><td><input type="text" name="username"></td></tr>
        <td>Password:</td><td><input type="password" name="password"></td></tr>
    </td></tr>
    </table>
</td></tr>
</c:if>
</table>

<p><html:submit onclick="bCancel=false;"/></p>
<html:javascript formName="form${form.id}"/>



