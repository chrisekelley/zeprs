<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<template:insert template='/WEB-INF/templates/template-report.jsp'>
    <template:put name='title' direct='true'>Save Summary
        for ${DailyAntenatalSummary.siteName} ${DailyAntenatalSummary.beginDate}
        - ${DailyAntenatalSummary.endDate}</template:put>
    <template:put name='header' direct='true'>Save Summary
        for ${DailyAntenatalSummary.siteName} ${DailyAntenatalSummary.beginDate}
        - ${DailyAntenatalSummary.endDate}</template:put>
    <template:put name='content' direct='true'>

<p>Report on Summary:</p>
        <table border="1" cellspacing="0" cellpadding="3" width="300" class="enhancedtable">
            <tr>
                <th>Date</th>
                <th>&nbps;</th>
            </tr>
        </table>
    </template:put>
</template:insert>