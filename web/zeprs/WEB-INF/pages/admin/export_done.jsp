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
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs" %>
<
<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='Export RSS feed' direct='true'/>
<template:put name='header' content='Export RSS feed' direct='true'/>
<template:put name='content' direct='true'>
<c:if test="${! empty message}"><p>${message}</p></c:if>
    </template:put>
</template:insert>