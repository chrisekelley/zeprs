<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ page import="org.cidrz.webapp.dynasite.session.SessionUtil,
                 org.cidrz.webapp.dynasite.session.SessionUtil"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<template:insert template='/WEB-INF/templates/template.jsp'>
<template:put name='title' direct='true'>
Clone Form
</template:put>

<c:set var="getPageitems_sql">
select form_field_id,display_order,input_type,use_table,column_number,last_modified,created,last_modified_by,
created_by,size,maxlength,rows,cols,visible,visible_enum_id_trigger,visible_dependencies,form_id
from page_item where form_id=8
</c:set>
<sql:query var = "getPageitems"   dataSource="jdbc/zeprsDB" sql="${getPageitems_sql}" />


<sql:query var = "total_patients" dataSource="jdbc/zeprsDB">
SELECT COUNT(DISTINCT  p.id)
FROM patient p
</sql:query>

<c:forEach var="row" items="${getPageitems.rowsByIndex}" varStatus="lineInfo">
<sql:query var="insert_pageItems" dataSource="jdbc/zeprsDB">
insert into page_item_tmp
set form_field_id
</sql:query>
</c:forEach>
</template:insert>