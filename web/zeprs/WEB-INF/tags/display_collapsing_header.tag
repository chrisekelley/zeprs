<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ attribute name="pageItem" required="true" type="org.cidrz.webapp.dynasite.valueobject.PageItem" %>
<c:set var="field" value="${pageItem.form_field}"/>
<c:choose>
    <c:when test="${field.id == 1880}">
        <script type="text/javascript">
            function toggleLabCheck() {
                var master = document.getElementById("selectfield1845");
                if (master) {
                    masterValue = master[master.selectedIndex].value
                    if (masterValue == "") {
                        alert("Please enter a value for 'Type of Lab' in 'Request a Lab Test Section. If you need to enter results for a previously-requested test, enter the results in the Records section below.");
                    } else {
                        toggleField('link', 1, '${pageItem.visibleDependencies1}', ${field.id});
                    }
                }
            }
        </script>
        <a href="#" onclick="toggleLabCheck();" title="Click here to enter ${field.label}"><img border="0" src="/zeprs/images/plus.gif" id="plusminus${field.id}">&nbsp;${field.label}
        </a>
    </c:when>
    <c:otherwise>
        <a href="#" onclick="toggleField('link',1, '${pageItem.visibleDependencies1}',${field.id});" title="Click here to enter ${field.label}"><img border="0" src="/zeprs/images/plus.gif" id="plusminus${field.id}">&nbsp;${field.label}</a>

    </c:otherwise>
</c:choose>
