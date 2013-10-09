<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<h1>ZEPRS Help</h1>
<p>Please click on the following links for information about the ZEPRS application.</p>
<ul>
    <li><html:link href="help.do?page=userManual">User Manual</html:link></li>
    <li><html:link href="help.do?page=referrals">Referrals</html:link></li>
    <li><html:link href="help.do?page=editing">Editing records</html:link></li>
    <logic:present role="CREATE_MEDICAL_STAFF_IDS_AND_PASSWORDS_FOR_MEDICAL_STAFF">
    <li><html:link href="help.do?page=userAdmin">User Administration</html:link></li>
    </logic:present>
</ul>