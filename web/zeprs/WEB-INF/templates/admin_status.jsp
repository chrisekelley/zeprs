<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="loginStatus-print">
    <table cellpadding="0" cellspacing="0" bgColor = "white" summary="Patient Status Bar" width="100%">
    <thead>
        <tr class="patientrowheader">
            <th><bean:message key="template-home.staff" bundle="TemplateResources"/></th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td class="patientrow">${fullName}</td>
        </tr>
    </tbody>
    </table>
</div>