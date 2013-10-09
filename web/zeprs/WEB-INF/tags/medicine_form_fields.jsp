<tr>
    <td width="20%" valign="top">Current Medicine:</td>
        <td colspan="${thisColspan}" align="left">
            <table>
                <tr>
                    <td valign="top" class="formrowlabel">Drugs Selected:</td>
                    <td valign="top">&nbsp;</td>
                    <td valign="top" class="formrowlabel">List of all drugs:</td>
                </tr>
                <tr>
                    <td valign="top">
                        <select size="10" name="_drugs">
                        <c:forEach var="pageItem" begin="0" items="${form.pageItems}" varStatus="lineItem">
                            <c:set var="field" value="${pageItem.form_field}" />
                        <c:choose>
                        <c:when test="${pageItem.inputType=='currentMedicine'}">
                        <bean:define id="thisDrug" name="form${encounterForm.id}" property="field${field.id}"/>
                        <logic:iterate id="drug" name="drugs">
                            <c:if test='${drug.id==thisDrug}'>
                                <option value="${drug.id}">${drug.name}</option>
                            </c:if>
                        </logic:iterate>
                        </c:when>
                        </c:choose>
                        </c:forEach>
                        </select>
                    </td>
                    <td valign="middle"><p>Select up to ${numFields} drugs. </p>
                        <html:button property="_add" value="<< Add Drug <<" onclick="addItem(_alldrugs,_drugs,${numFields})"/><br>
                        <html:button property="_add" value=">> Remove >>" onclick="removeItem(_drugs,_alldrugs,${numFields})"/><br>
                    </td>
                    <td valign="top"> <select size="10" name="_alldrugs">
                        <c:forEach items="${drugs}" var="drug">
                        <option value="${drug.id}">${drug.name}</option>
                        </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
<%--
            <html:hidden property="field${field.id}" styleId="_fieldOrder"/>
--%>
        </td>
</tr>

<tr>
    <td width="20%" valign="top">Other Medicine, if not in list:</td>
    <td colspan="${thisColspan}" align="left">
    <c:forEach var="pageItem" begin="0" items="${form.pageItems}" varStatus="lineItem">
    <c:if test="${pageItem.form_field.enabled =='true'}">
        <c:set var="field" value="${pageItem.form_field}" />
    <c:choose>
    <c:when test="${pageItem.inputType != 'currentMedicine'}">
    <html:text size="40" maxlength="255" property="field${field.id}"/> ${field.units}
    </c:when>
    </c:choose>
    </c:if>
    </c:forEach>
    </td>
</tr>