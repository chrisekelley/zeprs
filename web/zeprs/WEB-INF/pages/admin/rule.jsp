<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <c:choose>
    <c:when test="${! empty param.providerId}">
        <c:set var="providerId" value="${param.providerId}"/>
    </c:when>
    <c:otherwise>
        <c:set var="providerId" value="${providerId}"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${! empty param.pageItem}">
        <c:set var="pageItemId" value="${param.pageItem}"/>
    </c:when>
    <c:otherwise>
        <c:set var="pageItemId" value="${pageItem}"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${! empty param.formId}">
        <c:set var="formId" value="${param.formId}"/>
    </c:when>
    <c:otherwise>
        <c:set var="formId" value="${formId}"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${! empty param.formId}">
        <c:set var="providerId" value="${param.providerId}"/>
    </c:when>
    <c:otherwise>
        <c:set var="providerId" value="${providerId}"/>
    </c:otherwise>
</c:choose>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='Create Rule' direct='true'/>
<template:put name='header' content='Create Rule' direct='true'/>
<template:put name='content' direct='true'>
<html:form action="admin/rule/save" method="POST">


<html:hidden property="id"/>
<html:hidden property="providerClass"/>
<input type="hidden" name="providerId" value="${providerId}"/>
<input type="hidden" name="pageItem" value="${pageItemId}"/>
<input type="hidden" name="formId" value="${formId}"/>
<input type="hidden" name="fieldId" value="${providerId}"/>
    <table>
        <tr>
            <td valign="top"><h2><bean:message key="labels.admin.rule.ruleClass"/>:</h2>

                <p>Most of the time, you should choose Simple Comparison.
                    <br/>
                    If you have more complex requirements, choose Script rule and<br/>
                    fill out the Script Rule Java Expression below.</p>
                Select <bean:message key="labels.admin.rule.ruleClass"/>: <html:select property="ruleClass">
                <html:option value="org.cidrz.webapp.dynasite.rules.impl.SimpleComparison">Simple
                    Comparison</html:option>
                <html:option value="org.cidrz.webapp.dynasite.rules.impl.ScriptRule">Script Rule</html:option>
            </html:select></td>
            <td valign="top"><h2><bean:message key="labels.admin.rule.outcomeClass"/>:</h2>
                <ul>

                    <li>Encounter: If you need the nurse to fill out another form.</li>
                    <li>Referral: Display reason why this patient needs to be referred.</li>
                    <li>Info: The info you need to display.</li>
                </ul>
                Select <bean:message key="labels.admin.rule.outcomeClass"/>:
                <html:select property="outcomeClass">
                    <html:option value="org.cidrz.webapp.dynasite.rules.impl.InfoOutcome">Info</html:option>
                    <html:option value="org.cidrz.webapp.dynasite.rules.impl.EncounterOutcome">Encounter</html:option>
                    <html:option value="org.cidrz.webapp.dynasite.rules.impl.ReferralOutcome">Referral</html:option>
                </html:select>
            </td>
        </tr>
        <tr>
            <td valign="top"><h2>Outcome Result Dialogue or Form ID:</h2>
                <ul>
                    <li>Encounter: Enter the form you wish the user to complete.</li>
                    <li>Referral: Enter reason why this patient needs to be referred.</li>
                    <li>Info: Enter the info you need to display.</li>
                </ul>

                <p>Enter Outcome Result Dialogue or Form ID:</p>
                <html:text maxlength="255" size="60" property="outcomeArg"/>
            </td>
            <td valign="top"><bean:message key="labels.admin.rule.enabled"/>:
                <c:choose>
                    <c:when test="${empty subject}"><input type="checkbox" name="enabled" value="on"
                                                           checked="checked"></c:when>
                    <c:otherwise><html:checkbox property="enabled"/></c:otherwise>
                </c:choose>
                <br/>
                Display problems trigger by this rule for All Pregnancies?: <html:checkbox
                    property="allPregnancies"/></td>
        </tr>
        <tr>
            <td valign="top" colspan="2"><h2>Rule:</h2>
                <ul>
                    <li>If Simple Comparison: <em>Select operator and enter operand.
                        If field is an enum, enter the "numericValue" of the enumeration in the Operand input box..</em>
                    </li>
                    <li>If Script Rule: <em>Don't select an Operator. Enter the java expression in the Operand text box.
                        2 fields are available: sessionPatient and subjectValue.
                        SubjectValue is the value of the field you're testing.</em><br/>
                    Example: sessionPatient.isMotherHivPositive() == true && subjectValue ==0</li>
                </ul></td>
        </tr>
        <tr>
            <td valign="top" colspan="2">
                <table>
                    <tr>
                        <td valign="top">Operator:</td>
                        <td valign="top">
                            <html:select property="operator">
                                <html:option value="">-- Select --</html:option>
                                <html:option value="eq">==</html:option>
                                <html:option value="gt">&gt;</html:option>
                                <html:option value="gte">&gt;=</html:option>
                                <html:option value="lt">&lt;</html:option>
                                <html:option value="lte">&lt;=</html:option>
                                <html:option value="ne">!=</html:option>
                            </html:select></td>
                        <td valign="top">Operand:</td>
                        <td valign="top"><html:textarea property="operand" cols="80" rows="2"/></td>
                        <td valign="top"><html:submit value="Save"/></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</html:form>

</template:put>
</template:insert>