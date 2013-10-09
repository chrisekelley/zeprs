<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<template:insert template='/WEB-INF/templates/template.jsp'>
<template:put name='title' content='RSS Feed' direct='true'/>
<h2>RSS Feed</h2>
<template:put name='content' direct='true'>
    <div id="widePage">
        <c:if test="${! empty message}"><p>${message}</p></c:if>
    </div>
</template:put>
</template:insert>