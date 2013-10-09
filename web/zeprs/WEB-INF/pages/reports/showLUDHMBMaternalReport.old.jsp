<%--<jsp:useBean id="LUDHMBReport" scope="request" type="reports.LUDHMBReport"/>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="reportName" value="LUDHMB Maternal and Neonatal Statistics Monthly Return"/>

Displaying LUDHMB Maternal and Neonatal Statistics Monthly Return

<%
	//LUDHMBReport report = new LUDHMBReport();
	//report = request.getAttribute("LUDHMBReport");

//	out.print("Number of patients:" . ${LUDHMB.numPatients});
%>

Number of patients: ${LUDHMB.numPatients}

<% // = report.getNumPatients() %>

