<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
    <template:put name='title' content='Rule List' direct='true'/>
    <template:put name='header' content='Rule List' direct='true'/>
    <template:put name='content' direct='true'>
        <p>Rules are enabled unless otherwise noted. "All Pregnancies" means that outcomes triggered by this rule display on all pregnancies.</p>
        <ul>
            <logic:iterate id="element" name="forms">
                <%--<p>${form.value["label"]}</p>--%>
                <bean:define id="form" name="element" property="value"/>
                <li><strong>${form.label}</strong>
                    <ul>
                        <logic:iterate id="pageItem" name="form" property="pageItems">
                            <c:set var="field" value="${pageItem.form_field}"/>
                            <c:if test="${! empty field.ruleDefinitions}">
                                <li>Field: <em>${field.label}</em> <c:if test="${field.shared == 'true'}"><strong>
                                    Shared</strong></c:if>
                                    <ul>
                                        <logic:iterate id="rule" name="field" property="ruleDefinitions">
                                            <li>Outcome
                                                Type: ${fn:replace(rule.outcomeClass,"org.cidrz.webapp.dynasite.rules.impl.","")}
                                                Rule
                                                Type: ${fn:replace(rule.ruleClass,"org.cidrz.webapp.dynasite.rules.impl.","")}
                                                <c:if test="${rule.enabled == false}">Disabled</c:if> <c:if test="${rule.allPregnancies == true}">All Pregnancies</c:if>
                                                <ul>
                                                    <li>Message:
                                                            <c:if test="${! empty rule.message}">${rule.message}</c:if>
                                                    <li>
                                                        <c:choose>
                                                            <c:when test="${rule.ruleClass == 'org.cidrz.webapp.dynasite.rules.impl.SimpleComparison'}">
                                                                <c:if test="${! empty rule.operator}">${rule.operator}</c:if>
                                                                <c:if test="${! empty rule.operand}">${rule.operand}</c:if>
                                                            </c:when>
                                                            <c:otherwise>${rule.operand}</c:otherwise>
                                                        </c:choose>
                                                    </li>
                                                </ul>
                                            </li>
                                        </logic:iterate>
                                    </ul>
                                </li>
                            </c:if>
                        </logic:iterate>
                    </ul>
                </li>
            </logic:iterate>
        </ul>
    </template:put>
</template:insert>