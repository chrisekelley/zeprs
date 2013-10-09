<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<template:insert template='/WEB-INF/templates/template-simple.jsp'>
<template:put name='title' content='Home' direct='true'/>
<template:put name='header' content='System Status Warning' direct='true'/>
<template:put name='content' direct='true'>
<c:url value="dynagen.do" var="all"><c:param name="gen" value="5"/><c:param name="dev" value="true"/></c:url>
<p>The application is locked or being prepared for locking.
During this time, only certain operations are permitted.
The action you requested is not permitted.
</p>
    <logic:present role="ALTER_PROGRAMS_AND_SCREEN_APPEARANCE">
    <p>
        If you are have been editing forms, you may
        <a href='<c:out value="/zeprs/admin/${all}"/>' onclick="return confirm('Generate source, SQL, struts-config, and xml files?');self.close();">generate Dynasite source</a>.
    </p>
    </logic:present>
</template:put>
</template:insert>