<%--
  ~    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
  ~
  ~    Licensed under the Apache License, Version 2.0 (the "License");
  ~    you may not use this file except in compliance with the License.
  ~    You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  --%>

<%@ page import="java.lang.management.*" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<template:insert template='/WEB-INF/templates/template-admin.jsp'>
<template:put name='title' content='Pool status' direct='true'/>
<template:put name='header' content='Pool status' direct='true'/>
<template:put name='content' direct='true'>

<p>${poolStatus}</p>
<!-- http://www.theserverside.com/news/thread.tss?thread_id=36743 -->
<table border="0" width="100%">
<tr><td colspan="2" align="center"><h3>Memory MXBean</h3></td></tr>
<tr><td width="200">Heap Memory Usage</td><td><%= ManagementFactory.getMemoryMXBean().getHeapMemoryUsage() %></td></tr>
<tr><td>Non-Heap Memory Usage</td><td><%= ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage() %></td></tr></body>
<tr><td colspan="2">&nbsp;</td></tr>
<tr><td colspan="2" align="center"><h3>Memory Pool MXBeans</h3></td></tr>
<%
        Iterator iter = ManagementFactory.getMemoryPoolMXBeans().iterator();
        while (iter.hasNext()) {
            MemoryPoolMXBean item = (MemoryPoolMXBean) iter.next();
%>
<tr><td colspan="2">
<table border="0" width="100%" style="border: 1px #98AAB1 solid;">
<tr><td colspan="2" align="center"><b><%= item.getName() %></b></td></tr>
<tr><td width="200">Type</td><td><%= item.getType() %></td></tr>
<tr><td>Usage</td><td><%= item.getUsage() %></td></tr>
<tr><td>Peak Usage</td><td><%= item.getPeakUsage() %></td></tr>
<tr><td>Collection Usage</td><td><%= item.getCollectionUsage() %></td></tr>
</table>
</td></tr>
<tr><td colspan="2">&nbsp;</td></tr>
<%
}
%>

</table>
</template:put>
</template:insert>