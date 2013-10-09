<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ attribute name="form" required="true" type="org.cidrz.webapp.dynasite.valueobject.Form" %>

<div class="heading"><a name="${form.id}">${form.label}</a></div>
<table>
<form action="?action=3" method="post">
<input type="hidden" name="fo_form_name" value="${form.name}">
  <c:forEach var="field" begin="0" items="${form.formFields}">
	<tr><td>${field.label}<c:if test='${field.required==1}'>*</c:if>: </td>
	<td>
	<c:choose>
    <c:when test='${field.type=="radio-yn"}'>
	Yes <input type="radio" name="${field.id}" value="1">
	No <input type="radio" name="${field.id}" value="0">
	</c:when><c:when test='${field.type=="radio-nr"}'>
	R <input type="radio" "name=${field.id}" value="0">
	NR <input type="radio" "name=${field.id}" value="1">
	</c:when><c:when test='${field.type=="radio-hiv"}'>
	R <input type="radio" "name=${field.id}" value="0">
	NR <input type="radio" "name=${field.id}" value="1">
	HIV <input type="radio" "name=${field.id}" value="2">
	</c:when><c:when test='${field.type=="checkbox"}'>
	<input type=Checkbox "name=${field.id}">
	</c:when><c:when test='${field.type=="Date"}'>
	<input type="text" size="20" name="${field.id}" value="">
	</c:when><c:when test='${field.type=="Time"}'>
	<input type="text" size="20" name="${field.id}" value="">
	</c:when><c:when test='${field.type=="Text 2 lines"}'>
	<textarea name="${field.id}" cols=40></textarea>
	</c:when><c:when test='${field.type=="Enum"}'>
		<select name="${field.id}">
			<c:forEach var="enum" begin="0" items="${field.fieldEnumerations}">
				<option value="${enum.id}">${enum.enumeration}</option>
			</c:forEach>
		</select>
	</c:when><c:when test='${field.type=="Integer"}'>
		<input type="text" size="20" name="${field.id}">
        <c:choose>
			<c:when test='${field.minValue!="" && field.maxValue!=""}'>
				( ${field.minValue} - ${field.maxValue} )
            </c:when>
			<c:when test='${field.minValue=="" && field.maxValue!=""}'>
				( < ${field.maxValue} )
            </c:when>
			<c:when test='${field.minValue!="" && field.maxValue==""}'>
				( > ${field.minValue} )
            </c:when>
        </c:choose>
	</c:when>
    <c:otherwise>
	<input type="text" size="20" name="${field.id}">
	</c:otherwise>
    </c:choose>
	${field.units}
	</td></tr>

  </c:forEach>

  <c:if test='${form.requireReauth==1}'>
	<tr><td>
    Username:<input type="text" name="username"><br>Password: <input type="password" name="password">
    </td></tr>  
</c:if>

<p><input type="submit" value="Submit"></p>
</form>
</table>



