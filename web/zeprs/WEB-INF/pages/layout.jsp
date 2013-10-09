<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html>
<header><title>test</title>

	<%-- include header --%>
	<tiles:insert attribute="header"/>

    <tiles:insert attribute="menu"/>

	<%-- include body --%>
	<tiles:insert attribute="body"/>
	
	<%-- include footer --%>
	<tiles:insert attribute="footer"/>

</body>

</html>