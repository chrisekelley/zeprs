<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='System Status Warning' direct='true'/>
<template:put name='header' content='System Status Warning' direct='true'/>
<template:put name='content' direct='true'>
<p>This application must be locked before you may save form changes.</p>
<p>Change state to STATUS_LOCKED on the <html:link href="/zeprs/admin/systemState.do">System Management</html:link> page before editing the forms.</p>
</template:put>
</template:insert>