
currentFieldId = null;

function clearEditArea() {
    displayResult("");
    // DWRUtil.removeAllRows("rules");
    DWRUtil.removeAllRows("ruleParams");
    // var editArea =  document.getElementById("ruleList");
    // editArea.style.display = "none";
    // editArea.style.visibility = "hidden";
    editArea =  document.getElementById("ruleParamList");
    editArea.style.display = "none";
    editArea.style.visibility = "hidden";
    hideElement("enumerationsCreation");
    hideElement("enumerationsEdit");
}

function hideElement(id) {
    element = $(id);
    element.style.display = "none";
    element.style.visibility = "hidden";
}

function dispatchPageItem(listField, id, event)
{
    if (listField == "fieldList") {
        // make sure addSharedField is not displayed
        var addSharedField = $("addSharedField");
        addSharedField.style.display = "none";
        addSharedField.style.visibility = "hidden";
        var fields = $("fieldList");
        // fields.className = "fieldWhite";
        var children = Sortable.sequence('fieldList');
        for (i = 0; i < children.length; i++) {
            var value = children[i];
            var item = $("item_" + value);
            item.className = "fieldWhite";
        }
        var thisItem = $("item_" + id);
        thisItem.className = "fieldHighlight";
        var pageItemId = id;
        Dynasite.getPageItem(displayPageItem, pageItemId);
        var editArea = document.getElementById("editArea");
        editArea.style.display = "";
        editArea.style.visibility = "visible";
        var ofy=document.body.scrollTop;
        // var calcTop = event.clientY + ofy - 200;
        var calcTop = ofy;
        // var calcTop = event.clientY - 200;
        // alert("clientY: " + event.clientY + " ofy: " + ofy +  "calcTop: " + calcTop);
        if (calcTop >100) {
            editArea.style.top = calcTop;
        } else {
            editArea.style.top = 100;
        }
        var editPageItem = document.getElementById("editPageItem");
        editPageItem.style.display = "";
        editPageItem.style.visibility = "visible";
        clearEditArea();

    } else {
        if (listField.selectedIndex == -1)
        {
            alert("No field selected.");
            return false;
        } else {
            var pageItemId = listField[listField.selectedIndex].value
            Dynasite.getPageItem(displayPageItem, pageItemId);
            var editArea = document.getElementById("editPageItem");
            editArea.style.display = "";
            editArea.style.visibility = "visible";
            clearEditArea();
        }
    }

}

function dispatchEnum(element)
{
    var enumerations = $(element);
    var enumId = DWRUtil.getValue(element);
    if (enumId != "0") {
        Dynasite.getFieldEnumeration(displayEnum, enumId);
        var editArea = document.getElementById("enumerationsEdit");
        editArea.style.display = "";
        editArea.style.visibility = "visible";
    }
}

function displayEnum(enumeration)
{
    DWRUtil.setValues(enumeration);
    Fat.fade_element("enumerationsEdit", 60, 3000, "#FFFF33", "#FFFFFF");
}

function dispatchCreateRules(link, listField)
{
    var pageItemElement = $("pageItem.id");
    var pageItemId = pageItemElement.innerHTML;
    if (pageItemId == "")
    {
        alert("No field selected.");
        link.href = "";
        return false;
    } else {
        // var pageItemId = listField[listField.selectedIndex].value
        link.href += "?pageItem=" + pageItemId + "&providerClass=org.cidrz.webapp.dynasite.valueobject.FormField";
        return true;
    }
}

function displayPageItem(apageItem)
{
    // alert(apageItem.form_field.label);
    pageItem = apageItem;

    //alert(DWRUtil.toDescriptiveString(apageItem.form_field.enumerations, 3, 3) );
    var fieldType = apageItem.form_field.type;
    var fieldId =  apageItem.form_field.id;
	var inputType = pageItem.inputType;
    currentFieldId = fieldId;
    enumList = apageItem.form_field.enumerations;
    var enumerations = document.getElementById("enumerations");
    if (enumList.length >0) {
        enumerations.style.display = "";
        enumerations.style.visibility = "visible";
        DWRUtil.removeAllOptions("enums");
        DWRUtil.addOptions("enums", [ {name: 'Select to Edit ...', id: '0'}], "id", "name");
        DWRUtil.addOptions("enums", apageItem.form_field.enumerations, "id", "concatEnumNumeric");
        // visible trigger 1
        DWRUtil.removeAllOptions("pageItem.visibleEnumIdTrigger1");
        if (fieldType == "Yes/No") {
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", [ {name: 'Select Trigger ...', id: ''}], "id", "name");
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", [ {name: 'Yes', id: '1'}], "id", "name");
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", [ {name: 'No', id: '0'}], "id", "name");
        } else {
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", [ {name: 'Select Trigger ...', id: '0'}], "id", "name");
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", apageItem.form_field.enumerations, "id", "concatEnumNumeric");
        }
        DWRUtil.setValue("pageItem.visibleEnumIdTrigger1", apageItem.visibleEnumIdTrigger1);
        // visible trigger 2
        DWRUtil.removeAllOptions("pageItem.visibleEnumIdTrigger2");
        if (fieldType == "Yes/No") {
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger2", [ {name: 'Select Trigger ...', id: ''}], "id", "name");
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger2", [ {name: 'Yes', id: '1'}], "id", "name");
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger2", [ {name: 'No', id: '0'}], "id", "name");
        } else {
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger2", [ {name: 'Select Trigger ...', id: '0'}], "id", "name");
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger2", apageItem.form_field.enumerations, "id", "concatEnumNumeric");
        }
        DWRUtil.setValue("pageItem.visibleEnumIdTrigger2", apageItem.visibleEnumIdTrigger2);
    } else {
        if (fieldType == "Enum" && inputType != "multiselect_item") {
            renderEnumInputField(fieldId);
        } else if (fieldType == "Yes/No") {
			// visible trigger 1
        	DWRUtil.removeAllOptions("pageItem.visibleEnumIdTrigger1");
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", [ {name: 'Select Trigger ...', id: ''}], "id", "name");
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", [ {name: 'Yes', id: '1'}], "id", "name");
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", [ {name: 'No', id: '0'}], "id", "name");
			DWRUtil.setValue("pageItem.visibleEnumIdTrigger1", apageItem.visibleEnumIdTrigger1);
			// visible trigger 2
        	DWRUtil.removeAllOptions("pageItem.visibleEnumIdTrigger2");
			DWRUtil.addOptions("pageItem.visibleEnumIdTrigger2", [ {name: 'Select Trigger ...', id: ''}], "id", "name");
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger2", [ {name: 'Yes', id: '1'}], "id", "name");
            DWRUtil.addOptions("pageItem.visibleEnumIdTrigger2", [ {name: 'No', id: '0'}], "id", "name");
			DWRUtil.setValue("pageItem.visibleEnumIdTrigger2", apageItem.visibleEnumIdTrigger2);
			enumerations.style.display = "none";
            enumerations.style.visibility = "hidden";
			DWRUtil.removeAllOptions("enums");
        } else {
            enumerations.style.display = "none";
            enumerations.style.visibility = "hidden";
        }
    }
    DWRUtil.setValues(apageItem);
    setNestedValues(apageItem.form_field, "field.");
    setNestedValues(apageItem, "pageItem.");
    var deps1 = pageItem.visibleDependencies1;
    var deps2 = pageItem.visibleDependencies2;
    if (deps1 == null) {
       deps1 = "";
    }
    if (deps2 == null) {
       deps2 = "";
    }
    var valueList = deps1.split(",");
    var item = $("pageItem.visibleDependencies1");
    for (i = 0; i < valueList.length; i++)
    {
        var value = valueList[i];
        var optionsList;
        for (j = 0; j < item.options.length; j++)
        {
            var optionValue = item.options[j].value;
            if (optionValue == value) {
                item.options[j].selected = true;
            }
        }
    }
    valueList = deps2.split(",");
    item = $("pageItem.visibleDependencies2");
    for (i = 0; i < valueList.length; i++)
    {
        var value = valueList[i];
        var optionsList;
        for (j = 0; j < item.options.length; j++)
        {
            var optionValue = item.options[j].value;
            if (optionValue == value) {
                item.options[j].selected = true;
            }
        }
    }
    if (deps1 != null || deps2 != null) {
        item = $("dependencies");
        item.style.display = "";
        item.style.visibility = "visible";
    }
    DWRUtil.removeAllOptions("field.type");
    DWRUtil.addOptions("field.type", ["Please select ..."]);
    DWRUtil.addOptions("field.type", [ 'Text','Enum','Integer','Long','Float','Date','Time','Datetime','Timestamp','Year','Boolean','Yes/No','sex','MultiEnum','Display' ]);
    // alert("fieldType: " + fieldType);
    DWRUtil.setValue("field.type", fieldType);
    //var inputTypeDropdown = document.getElementById("pageItem.inputType");
    //DWRUtil.setValue("pageItem.inputType", inputTypeDropdown);
   var inputSize = document.getElementById("inputSize");
   var textareaSize = document.getElementById("textareaSize");
    if ((inputType == "text") || (inputType == "text-dwr") || (inputType == "multiselect_enum") || (inputType == "multiselect_enum_input") || (inputType == "multiselect_item")) {
        inputSize.style.display = "";
        inputSize.style.visibility = "visible";
        textareaSize.style.display = "none";
        textareaSize.style.visibility = "hidden";
    } else if (inputType == "textarea") {
        inputSize.style.display = "none";
        inputSize.style.visibility = "hidden";
        textareaSize.style.display = "";
        textareaSize.style.visibility = "visible";
    } else if (inputType == "display-tbl-begin") {
        inputSize.style.display = "none";
        inputSize.style.visibility = "hidden";
        textareaSize.style.display = "";
        textareaSize.style.visibility = "visible";
    } else {
        inputSize.style.display = "none";
        inputSize.style.visibility = "hidden";
        textareaSize.style.display = "none";
        textareaSize.style.visibility = "hidden";
    }

    DWREngine.setPreHook(function() {
        $('disabledZone').style.visibility = 'visible';
    });
    DWREngine.setPostHook(function() {
        $('disabledZone').style.visibility = 'hidden';
    });
}

function renderEnumInputField(fieldId) {
    var enumCreation = $("enumerationsCreation");
    enumCreation.style.display = "";
    enumCreation.style.visibility = "visible";
    enumCreation.innerHTML = "List enumerations (comma-separated): ";
    var input = document.createElement("input");
    input.setAttribute("id", "createEnums")
    input.setAttribute("size", "40")
    var onChange = 'createEnumerations(\'createEnums\', ' + fieldId + ')';
    input.setAttribute("onChange", onChange);
    var frag = document.createDocumentFragment();
    frag.appendChild(input);
    enumCreation.appendChild(frag);
    Fat.fade_element("enumerationsCreation", 60, 3000, "#FFFF33", "#38B0DE");
}

function UpdateEnum() {

    var id = DWRUtil.getValue("id");
    var enumeration = DWRUtil.getValue("enumeration");
    var numericValue = DWRUtil.getValue("numericValue");
    var displayOrder = DWRUtil.getValue("displayOrder");
    var enabled = DWRUtil.getValue("enabled");
    Dynasite.updateEnum(reloadEditedField, id, enumeration, numericValue, displayOrder, enabled)
}
function DisplayTriggerEnum(fieldId) {

DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", [ {name: 'Select Trigger ...', id: '0'}], "id", "name");
DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", apageItem.form_field.enumerations, "id", "concatEnumNumeric");
}


function createEnumerations(element, fieldId) {
    enumList = $(element);
    // alert("enumList: " + enumList.value + " fieldId: " + fieldId);
    var values = enumList.value;
    Dynasite.createEnums(reloadEditedField, fieldId, values);
    var enumCreation = $("enumerationsCreation");
    enumCreation.style.visibility = "hidden";
    enumCreation.style.display = "none";
    //enumCreation.innerHTML = "Enumerations created";
    Fat.fade_element("enumerations", 60, 3000, "#FFFF33", "#FFFFFF");

    // window.location.reload()
}

function displayTrigger(fieldId, fieldType) {
	DWRUtil.removeAllOptions("pageItem.visibleEnumIdTrigger1");
	//alert("fieldType:" + fieldType);
    if (fieldType == "Yes/No") {
    	DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", [ {name: 'Select Trigger ...', id: ''}], "id", "name");
        DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", [ {name: 'Yes', id: '1'}], "id", "name");
        DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", [ {name: 'No', id: '0'}], "id", "name");
    } else {
	Dynasite.getEnums(displayTriggerEnum, fieldId);
	}
}

function displayTriggerEnum(enumList) {
	DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", [ {name: 'Select Trigger ...', id: '0'}], "id", "name");
	DWRUtil.addOptions("pageItem.visibleEnumIdTrigger1", enumList, "id", "concatEnumNumeric");
}

function reloadEditedField(message) {
    // dispatchPageItem(document.adminForm._fields);
    var currentField = $("pageItem.id");
	var pageItemId = currentField.innerHTML;
    dispatchPageItem('fieldList', currentField.innerHTML);
	var labelText = $("field.label");
	var listItem = $("label_"+pageItemId);
	//alert("Change listItem " + listItem.innerHTML + " to labelText " + labelText.value);
	listItem.innerHTML = ellipsis(labelText.value,30);
    var result =  document.getElementById("result");
    result.innerHTML = message
    result.style.display = "";
    result.style.visibility = "visible";
}


function loadpage() {
    //window.location.href=window.location.href;
    window.location.reload()
}

function dispatchRules()
{
    var pageItemElement = $("pageItem.id");
    var pageItemId = pageItemElement.innerHTML;
    if (pageItemId == "")
    {
        alert("No field selected.");
        return false;
    } else {
        // var pageItemId = listField[listField.selectedIndex].value
        clearEditArea();
        Dynasite.getRules(displayRules, pageItemId);
    }
}

var getRuleClass = function(rule) { return rule.ruleClass.replace("org.zeprs.rules.impl.", "") };
var getOutcomeClass = function(rule) { return rule.outcomeClass.replace("org.zeprs.rules.impl.", "") };
var getEdit = function(rule) {
    return '<input type="button" value="Edit" onclick="readRule('+rule+')"/>'
}
function displayRules(alist)
{
    if (!alist) {
        alert("No rules for this field.");
    } else {
    list = alist;
    // alert(DWRUtil.toDescriptiveString(alist, 3, 3) );
    // alert(DWRUtil.toDescriptiveString(alist, 3, 3))
    // DWRUtil.removeAllRows("rules");
    // DWRUtil.addRows("rules", alist, [ getRuleClass, getOutcomeClass, getEdit ])
    DWRUtil.removeAllRows("ruleParams");
    for ( i=0; i < alist.length; i++ ) {
        var rule = alist[i];
        var thisParam = rule.ruleParams
        thisParam.id=rule.id;
        //alert("rule: " + DWRUtil.toDescriptiveString(thisParam, 3, 3))
        // alert("message:" + thisParam.message);
        if (rule.outcomeClass =="org.zeprs.rules.impl.ReferralOutcome") {
            className = getRuleClass(rule);
            outcomeName = getOutcomeClass(rule);
            addRuleParamRow(className, "Referral Outcome", "ruleParams", thisParam, [ getReason, getOperator, getOperand ]);
        } else if (rule.outcomeClass =="org.zeprs.rules.impl.EncounterOutcome") {
            className = getRuleClass(rule);
            outcomeName = getOutcomeClass(rule);
            addRuleParamRow(className, "Encounter Outcome", "ruleParams", thisParam, [ getFormId, getOperator, getOperand ]);
        } else {
            className = getRuleClass(rule);
            outcomeName = getOutcomeClass(rule);
            allPregnancies = rule.allPregnancies;
            message = rule.message;
            operand = rule.operand;
            operator = rule.operator;
            formId = rule.formId;
            id = rule.id;
            addRuleParamRow(className, "Info Outcome", "ruleParams", thisParam, allPregnancies, message, operand, operator, formId);
        }
    }
    editArea =  document.getElementById("ruleParamList");
    editArea.style.display = "";
    editArea.style.visibility = "visible";
    }
}

var rule = { id:-1, ruleClass:null, outcomeClass:null, allPregnancies:null };
var ruleParam = {message:null, reason:null, operator:null, operand:null};
var getReason = function(ruleParam) {
    return '<input size="50" type="text"  id="reason' + ruleParam.id + '"value="' + ruleParam.reason + '" onChange="updateRule(\'reason\', ' + ruleParam.id +')"/>'
};
var getFormId = function(ruleParam) {
    return 'Form Id: <input size="3" type="text"  id="formId' + ruleParam.id + '"value="' + ruleParam.formId + '" onChange="updateRule(\'formId\', ' + ruleParam.id +')"/>'
};
var getAllPregnancies = function(id, allPregnancies) {
    if (allPregnancies == true) {
        return '<input type="checkbox" id="all_pregs' + id + '" checked onChange="updateRule(\'all_pregs\', ' + id + ', \'checkbox\')"/>'
    } else {
        return '<input type="checkbox" id="all_pregs' + id + '" onChange="updateRule(\'all_pregs\', ' + id + ', \'checkbox\')"/>'
    }
};
var getMessage = function(id, message) {
    return '<input size="45" type="text"  id="message' + id + '"value="' + message + '" onChange="updateRule(\'message\', ' + id +')"/>'
};
var getOperator = function(id, operator) {
    var operatorValue = '<input size="2" type="text"  id="operator' + id + '"value="' + operator + '" onChange="updateRule(\'operator\', ' + id +')"/>' +
                        ' Choices: eq(==), gt(&gt;), gte(&gt;=), lt(&lt;), lte(<=), ne(!=)'
    return operatorValue;
};
var getOperand = function(id, operand) {
    return '<textarea id="operand' + id + '"  cols="35" rows="3" onChange="updateRule(\'operand\', ' + id +')">' + operand + '</textarea>';
};

function updateRule(element, id, type) {
    ele = $(element+id);
    if (type=="checkbox") {
        value = 0
        if (ele.checked) {
            // alert("ele: "+ ele.checked)
            value = 1;
        }
    } else {
        value = ele.value;
    }
    Dynasite.updateRule(displayResult, id, element, value);
}

var getInputWidget = function(widgetLocation, name, value, column, id, size) {
    var widget =  '<input size="' + size + '" type="text" value="' + value + '" id="' + name + '" onChange="updateForm(\'' + widgetLocation + '\',\'' + name + '\', \'input\', \'' + column + '\', \'' + id + '\')"/>';
    var element = document.getElementById(widgetLocation);
    element.innerHTML = widget;
};



function WidgetDispatcher() { }

flows = new Object();
formTypes = new Object();

var getSelectWidget = function(widgetLocation, name, value, column, id, object) {
    // flows = WidgetDisplay.dispatchFlows(initFlows);
    // alert("flows: " +flows)
    //DWRUtil.toDescriptiveString(flows, 3, 3)
    // alert(DWRUtil.toDescriptiveString(flows, 3, 3) );
    var ele = $(widgetLocation);
    var frag = document.createDocumentFragment();
    select = document.createElement("select");
    select.setAttribute("id", name)
    var onChange= 'updateForm(\'' + widgetLocation + '\',\'' + name + '\', \'select\', \'' + column + '\', \'' + id + '\')';
    select.setAttribute("onChange", onChange)
    DWRUtil.addOptions(select, ["Please select ..."]);
    DWRUtil.addOptions(select, object, "id", "name");
    frag.appendChild(select);
    ele.innerHTML = "";
    ele.appendChild(frag);
    DWRUtil.setValue(name, value);
    // var widget =  '<input size="40" type="text" value="' + value + '" id="' + name + '" onChange="updateForm(\'' + widgetLocation + '\',\'' + name + '\', \'input\', \'' + column + '\', \'' + id + '\')"/>';
    // var element = document.getElementById(widgetLocation);
    // element.innerHTML = widget;
};

function initFlows(data)
{
    flows = data;
    // alert(typeof flows);
    return flows;
}

function initFormTypes(data)
{
    formTypes = data;
    return formTypes;
}

// Set value opposite the current value.
var toggleFormElement = function(widgetLocation, name, column, id) {
    var answer = confirm("Are you sure you want to change this value??")
    if (answer) {
        var element = $(widgetLocation);
        var value = DWRUtil.getValue(element);
        var type = "checkbox";
        var text = null;
        if (value == "true") {
            value = "0"
        } else {
            value = "1"
        }
        // alert("name: " + name + " value:" + value + " column: " + column + " id" + id);
        Dynasite.updateElement(displayFormUpdate, widgetLocation, name, type, column, id, value, text);
    }
}

var updateForm = function(widgetLocation, name, type, column, id) {
    var element = $(name);
    var value = DWRUtil.getValue(element);
    var text = null;
    if (type == "select") {
        text = DWRUtil.getText(element);
    }
    // alert("name: " + name + " value:" + value + " column: " + column + " id" + id);
     Dynasite.updateElement(displayFormUpdate, widgetLocation, name, type, column, id, value, text);
}


/**
* Internal function to help rendering tables.
* @see DWRUtil.addRows(ele, data, cellFuncs)
*/
addHashMapRow = function(ele, data, cellFuncs)
{
    var orig = ele;
    ele = $(ele);
    var frag = document.createDocumentFragment();
    DWRUtil._addRowInner(frag, data, cellFuncs);
    ele.appendChild(frag);
};
addRuleParamRow = function(className, outcomeName, ele, row, allPregnancies, message, operand, operator, formId)
{
    var orig = ele;
    ele = $(ele);
    var frag = document.createDocumentFragment();
    tr = document.createElement("tr");
    td = document.createElement("td");
    var text = document.createTextNode("Rule id: " + row.id + ", " + outcomeName);
    td.appendChild(text);
    td.className = "ruleHeader";
    tr.className = "ruleHeader";
    tr.appendChild(td);
    frag.appendChild(tr);
    // allPregnancies element
    tr = document.createElement("tr");
    td = document.createElement("td");
    var item = getAllPregnancies(row.id, allPregnancies);
    td.innerHTML= "Display outcome in all pregnancies?" + item;
    tr.appendChild(td);
    frag.appendChild(tr);
    // message
    tr = document.createElement("tr");
    td = document.createElement("td");
    item = getMessage(row.id, message);
    td.innerHTML= "Message :" + item;
    tr.appendChild(td);
    frag.appendChild(tr);
    // operator
    tr = document.createElement("tr");
    td = document.createElement("td");
    item = getOperator(row.id, operator);
    td.innerHTML= "Operator :" + item;
    tr.appendChild(td);
    frag.appendChild(tr);
    // operand
    tr = document.createElement("tr");
    td = document.createElement("td");
    item = getOperand(row.id, operand);
    td.innerHTML= "Operand :" + item;
    tr.appendChild(td);
    frag.appendChild(tr);

    /*for (var j = 0; j < cellFuncs.length; j++)
    {
        var func = cellFuncs[j];
        var td;

        if (typeof func == "string")
        {
            tr = document.createElement("tr");
            td = document.createElement("td");
            var text = document.createTextNode(func);
            td.appendChild(text);
            tr.appendChild(td);
            frag.appendChild(tr);
        }
        else
        {
            var reply = func(row);
            if (DWRUtil._isHTMLElement(reply, "td"))
            {
                td = reply;
            }
            else if (DWRUtil._isHTMLElement(reply))
            {
                tr = document.createElement("tr");
                td = document.createElement("td");
                td.appendChild(reply);
                tr.appendChild(td);
                frag.appendChild(tr);
            }
            else
            {
                tr = document.createElement("tr");
                td = document.createElement("td");
                td.innerHTML = reply;
                tr.appendChild(td);
                frag.appendChild(tr);
            }

            // tr.appendChild(td);
        }
    }*/
    ele.appendChild(frag);
};



function readRule(fieldId, ruleId, ruleparamList)
{
    // Demo.getRule(fillForm, id);

    // alert("fieldId: " + fieldId + ", ruleId: " + ruleId + ", ruleParams: " + ruleParams);
    DWRUtil.removeAllRows("ruleParams");
    DWRUtil.addRows("ruleparams", ruleparamList, [ getMessage, getOperator, getOperand ])
}

/**
* Given a map, call getValue() for all the entries in the map using the key
* of each entry as an id.
* prefix is handy if you're using a nested object and have fields in superclass w/ same name.
* @param map The map of values to set to various elements
*/
setNestedValues = function(map, prefix)
{
    for (var property in map)
    {
        var ele = $(prefix +property);
        if (ele != null)
        {
            var value = map[property];
            DWRUtil.setValue(prefix + property, value);
        }
    }
};

/**
 * Given a map, call getValue() for all the entries in the map using the key
 * of each entry as an id.
 * prefix is handy if you're using a nested object and have fields in superclass w/ same name.
 * @param map The map of values to set to various elements
 */
getNestedValues = function(map, prefix)
{
    for (var property in map)
    {
        var ele = $(prefix +property);
        if (ele != null)
        {
            var value = DWRUtil.getValue(prefix + property);
            map[property] = value;
            if (property == "visibleEnumIdTrigger1") {
                var trigger1 = $("pageItem.visibleEnumIdTrigger1");
                if (trigger1.options.length >0) {
                    var trigger1Value = trigger1.options[trigger1.selectedIndex].value;
                    map[property] = trigger1Value;
                }
                // alert("property: " + property + " value: " + trigger1Value);
            } else if (property == "visibleEnumIdTrigger2") {
                var trigger2 = $("pageItem.visibleEnumIdTrigger2");
                if (trigger2.options.length > 0) {
                    var trigger2Value = trigger2.options[trigger2.selectedIndex].value;
                    map[property] = trigger2Value;
                }
                // alert("property: " + property + " value: " + trigger1Value);
            }
        }
    }
};

function writePageItem()
{
    getNestedValues(pageItem.form_field, "field.");
    getNestedValues(pageItem, "pageItem.");
    // alert("fields: " + DWRUtil.toDescriptiveString(pageItem, 2));
    var deps1 = $("pageItem.visibleDependencies1");
    pageItem.visibleDependencies1 =  fetchMultipleSelectValues(deps1);
    var deps2 = $("pageItem.visibleDependencies2");
    pageItem.visibleDependencies2 =  fetchMultipleSelectValues(deps2);
    var trigger1 = $("pageItem.visibleEnumIdTrigger1");
    var trigger2 = $("pageItem.visibleEnumIdTrigger2");
    if (trigger1.options.length >0) {
        var trigger1Value = trigger1.options[trigger1.selectedIndex].value;
    }
    if (trigger2.options.length >0) {
        var trigger1Value = trigger2.options[trigger2.selectedIndex].value;
    }

    // alert("trigger1" + trigger1Value);
    Dynasite.updatePageItem(reloadEditedField, pageItem);
}

function fetchMultipleSelectValues(items) {
    var list = "";
    for (var i = 0; i < items.length; i++) {
        if (items[i].selected == true) {
            var itemValue = items[i].value;
            // alert("itemValue: " + itemValue + " items.length: " + items.length + " i " + i);
            list = list + "" + itemValue + ",";
        }
    }
    return list.substring(0, list.length-1);
}

function displayResult(message)
{
    var result =  document.getElementById("result");
    result.innerHTML = message
    result.style.display = "";
    result.style.visibility = "visible";
}

function displayFormUpdate(message)
{
    var dvals = message.split("=");
    var widget = dvals[0];
    var value =dvals[1];
    DWREngine.setPreHook(function() { $('disabledZone').style.visibility = 'visible'; });
    DWREngine.setPostHook(function() { $('disabledZone').style.visibility = 'hidden'; Fat.fade_element(widget, 60, 2000, "#FFFF33", "#FFFFFF") });

    var result =  document.getElementById(widget);
    result.innerHTML = value
    //result.style.display = "";
    //result.style.visibility = "visible";
}

function callGroups(username) {
        WidgetDisplay.dispatchGroups(displayWidget, username);
        DWREngine.setPreHook(function() { $('disabledZone').style.visibility = 'visible'; });
        DWREngine.setPostHook(function() { $('disabledZone').style.visibility = 'hidden'; });
}

function moveUpList(listField, summaryField) {
   if ( listField.length == -1) {  // If the list is empty
      alert("There are no values which can be moved!");
   } else {
      var selected = listField.selectedIndex;
      if (selected == -1) {
         alert("You must select an entry to be moved!");
      } else {  // Something is selected
         if ( listField.length == 0 ) {  // If there's only one in the list
            alert("There is only one entry!\nThe one entry will remain in place.");
         } else {  // There's more than one in the list, rearrange the list order
            if ( selected == 0 ) {
               alert("The first entry in the list cannot be moved up.");
            } else {
               // Get the text/value of the one directly above the hightlighted entry as
               // well as the highlighted entry; then flip them
               var moveText1 = listField[selected-1].text;
               var moveText2 = listField[selected].text;
               var moveValue1 = listField[selected-1].value;
               var moveValue2 = listField[selected].value;
               listField[selected].text = moveText1;
               listField[selected].value = moveValue1;
               listField[selected-1].text = moveText2;
               listField[selected-1].value = moveValue2;
               listField.selectedIndex = selected-1; // Select the one that was selected before
            }  // Ends the check for selecting one which can be moved
         }  // Ends the check for there only being one in the list to begin with
      }  // Ends the check for there being something selected
   }  // Ends the check for there being none in the list
   syncHiddenField(listField, summaryField);
   fieldOrderTest();
}

function moveDownList(listField, summaryField) {
   if ( listField.length == -1) {  // If the list is empty
      alert("There are no values which can be moved!");
   } else {
      var selected = listField.selectedIndex;
      if (selected == -1) {
         alert("You must select an entry to be moved!");
      } else {  // Something is selected
         if ( listField.length == 0 ) {  // If there's only one in the list
            alert("There is only one entry!\nThe one entry will remain in place.");
         } else {  // There's more than one in the list, rearrange the list order
            if ( selected == listField.length-1 ) {
               alert("The last entry in the list cannot be moved down.");
            } else {
               // Get the text/value of the one directly below the hightlighted entry as
               // well as the highlighted entry; then flip them
               var moveText1 = listField[selected+1].text;
               var moveText2 = listField[selected].text;
               var moveValue1 = listField[selected+1].value;
               var moveValue2 = listField[selected].value;
               listField[selected].text = moveText1;
               listField[selected].value = moveValue1;
               listField[selected+1].text = moveText2;
               listField[selected+1].value = moveValue2;
               listField.selectedIndex = selected+1; // Select the one that was selected before
            }  // Ends the check for selecting one which can be moved
         }  // Ends the check for there only being one in the list to begin with
      }  // Ends the check for there being something selected
   }  // Ends the check for there being none in the list
   syncHiddenField(listField, summaryField);
   fieldOrderTest();
}

function orderModule(down, col)
// this is from yahoo - not currently using
{
  sl = document.adminForm[col].selectedIndex;
  if (sl != -1 && document.adminForm[col].options[sl].value > "") {
    oText = document.adminForm[col].options[sl].text;
    oValue = document.adminForm[col].options[sl].value;
    if (document.adminForm[col].options[sl].value > "" && sl > 0 && down == 0) {
      document.adminForm[col].options[sl].text = document.adminForm[col].options[sl-1].text;
      document.adminForm[col].options[sl].value = document.adminForm[col].options[sl-1].value;
      document.adminForm[col].options[sl-1].text = oText;
      document.adminForm[col].options[sl-1].value = oValue;
      document.adminForm[col].selectedIndex--;
    } else if (sl < document.adminForm[col].length-1 && document.adminForm[col].options[sl+1].value > "" && down == 1) {
      document.adminForm[col].options[sl].text = document.adminForm[col].options[sl+1].text;
      document.adminForm[col].options[sl].value = document.adminForm[col].options[sl+1].value;
      document.adminForm[col].options[sl+1].text = oText;
      document.adminForm[col].options[sl+1].value = oValue;
      document.adminForm[col].selectedIndex++;
    }
  } else {
    alert("Please select an item first");
  }

  return false;
}

function moveUpList5(listField, summaryField) {
   if ( listField.length == -1) {  // If the list is empty
      alert("There are no values which can be moved!");
   } else {
      var selected = listField.selectedIndex;
      if (selected == -1) {
         alert("You must select an entry to be moved!");
      } else {  // Something is selected
         if ( listField.length == 0 ) {  // If there's only one in the list
            alert("There is only one entry!\nThe one entry will remain in place.");
         } else {  // There's more than one in the list, rearrange the list order
            if ( selected == 0 ) {
               alert("The first entry in the list cannot be moved up.");
            } else {
               // Get the text/value of the one directly above the hightlighted entry as
               // well as the highlighted entry; then flip them
               var moveTextNeg5 = listField[selected-5].text;
               var moveTextNeg4 = listField[selected-4].text;
               var moveTextNeg3 = listField[selected-3].text;
               var moveTextNeg2 = listField[selected-2].text;
               var moveTextNeg1 = listField[selected-1].text;
               var moveText0 = listField[selected].text;
               var moveValueNeg5 = listField[selected-5].value;
               var moveValueNeg4 = listField[selected-4].value;
               var moveValueNeg3 = listField[selected-3].value;
               var moveValueNeg2 = listField[selected-2].value;
               var moveValueNeg1 = listField[selected-1].value;
               var moveValue0 = listField[selected].value;

               listField[selected].value = moveValueNeg1;
               listField[selected-1].value = moveValueNeg2;
               listField[selected-2].value = moveValueNeg3;
               listField[selected-3].value = moveValueNeg4;
               listField[selected-4].value = moveValueNeg5;

               listField[selected].text = moveTextNeg1;
               listField[selected-1].text = moveTextNeg2;
               listField[selected-2].text = moveTextNeg3;
               listField[selected-3].text = moveTextNeg4;
               listField[selected-4].text = moveTextNeg5;

               listField[selected-5].value = moveValue0;
                listField[selected-5].text = moveText0;

               //listField[selected-1].text = moveText2;
               //listField[selected-1].value = moveValue2;
               listField.selectedIndex = selected-5; // Select the one that was selected before
            }  // Ends the check for selecting one which can be moved
         }  // Ends the check for there only being one in the list to begin with
      }  // Ends the check for there being something selected
   }  // Ends the check for there being none in the list
   syncHiddenField(listField, summaryField);
   fieldOrderTest();
}

function moveDownList5(listField, summaryField) {
   if ( listField.length == -1) {  // If the list is empty
      alert("There are no values which can be moved!");
   } else {
      var selected = listField.selectedIndex;
      if (selected == -1) {
         alert("You must select an entry to be moved!");
      } else {  // Something is selected
         if ( listField.length == 0 ) {  // If there's only one in the list
            alert("There is only one entry!\nThe one entry will remain in place.");
         } else {  // There's more than one in the list, rearrange the list order
            if ( selected == listField.length-1 ) {
               alert("The last entry in the list cannot be moved down.");
            } else {
               // Get the text/value of the one directly below the hightlighted entry as
               // well as the highlighted entry; then flip them

               var moveTextPlus5 = listField[selected+5].text;
               var moveTextPlus4 = listField[selected+4].text;
               var moveTextPlus3 = listField[selected+3].text;
               var moveTextPlus2 = listField[selected+2].text;
               var moveTextPlus1 = listField[selected+1].text;
               var moveText0 = listField[selected].text;
               var moveValuePlus5 = listField[selected+5].value;
               var moveValuePlus4 = listField[selected+4].value;
               var moveValuePlus3 = listField[selected+3].value;
               var moveValuePlus2 = listField[selected+2].value;
               var moveValuePlus1 = listField[selected+1].value;
               var moveValue0 = listField[selected].value;

               listField[selected].value = moveValuePlus1;
               listField[selected+1].value = moveValuePlus2;
               listField[selected+2].value = moveValuePlus3;
               listField[selected+3].value = moveValuePlus4;
               listField[selected+4].value = moveValuePlus5;

               listField[selected].text = moveTextPlus1;
               listField[selected+1].text = moveTextPlus2;
               listField[selected+2].text = moveTextPlus3;
               listField[selected+3].text = moveTextPlus4;
               listField[selected+4].text = moveTextPlus5;

               listField[selected+5].value = moveValue0;
                listField[selected+5].text = moveText0;


               listField.selectedIndex = selected+5; // Select the one that was selected before

               selSELECT(selected+5,listField)
            }  // Ends the check for selecting one which can be moved
         }  // Ends the check for there only being one in the list to begin with
      }  // Ends the check for there being something selected
   }  // Ends the check for there being none in the list
   syncHiddenField(listField, summaryField);
   fieldOrderTest();
}

function selSELECT(which,listField) {
/*
   Script by Joe Crawford http://www.artlung.com/
*/
//alert(which);
//document.forms[0]._fields.selectedIndex = listField.options[which];
listField.options[document.forms[0]._fields.selectedIndex].selected=true
//alert(document.forms[0]._fields.selectedIndex);
//istField.selectedIndex.selected = true;
//listField.options[which].selected = true;
//listField.selectedIndex = which;
listField.multiple=true;
}

function addId(link, listField, formId, pageItem)
{
    if ( listField.selectedIndex == -1 )
    {
        alert("No field selected.");
        return false;
    }
    link.href += "?id=" + listField[listField.selectedIndex].value + "&formId=" + formId + "&pageItem=" + pageItem;
    return true;
}

function addPageItemId(link, listField, formId)
{
    if ( listField.selectedIndex == -1 )
    {
        alert("No field selected.");
        return false;
    }
    link.href += "?id=" + listField[listField.selectedIndex].value + "&formId=" + formId;
    return true;
}

// kudos: http://www.aspdeveloper.net/tiki-index.php?page=HTMLTipsCheckIfFormDirty
function el_is_modified(el)
{
	var opt, hasDefault, i = 0, j;
	//while (el = oForm.elements[i++]) {
		switch (el.type) {
			case 'text' :
                   	case 'textarea' :
                   	case 'hidden' :
                         	if (!/^\s*$/.test(el.value) && el.value != el.defaultValue) return true;
                         	break;
                   	case 'checkbox' :
                   	case 'radio' :
                         	if (el.checked != el.defaultChecked) return true;
                         	break;
                   	case 'select-one' :
                   	case 'select-multiple' :
                         	j = 0, hasDefault = false;
                         	while (opt = el.options[j++])
                                	if (opt.defaultSelected) hasDefault = true;
                         	j = hasDefault ? 0 : 1;
                         	while (opt = el.options[j++])
                                	if (opt.selected != opt.defaultSelected) {
                                        alert("modified")
                                        return true;
                                    }
                         	break;
		}
//	}
	return false;
}

function fieldOrderChanged() {
    // var origFieldOrder = $("origFieldOrder");
    var fieldOrder = $("fieldOrder").value;
    // alert("fieldOrder"+ fieldOrder);
    // var newFieldOrder = fieldOrder.value;
    var newFieldOrderArray = Sortable.sequence('fieldList');
    //var newFieldOrder = "";
    var dvals = fieldOrder.split(",");
    // alert("newFieldOrderArray: " + newFieldOrderArray);
    for (i = 0; i < newFieldOrderArray.length; i++) {
        var value = newFieldOrderArray[i];
        var originalOrder = dvals[i];
        if (value != originalOrder) {
            // alert("out of order: value: " + value + " i: " + i + " originalOrder: " + originalOrder);
            Dynasite.updateDisplayOrder(confirmFieldOrderUpdate, i, value);
        }
    }
    // origFieldOrder = newFieldOrderArray;
    $("fieldOrder").value =  newFieldOrderArray;
    /*if (origFieldOrder != newFieldOrder) {
        // alert("selectwidget: " + selectwidget + "not eq: origFieldOrder:" + origFieldOrder + " fieldOrder.value: " + fieldOrder.value + " length: " + selectwidget.length)
        // save the pageitems's displayOrder.
        var newlist = "";
        var dvals = origFieldOrder.split(",");
        // var widget = dvals[0];
        for (i = 0; i < selectwidget.length; i++)
        {
            var value = selectwidget[i].value;
            var originalOrder = dvals[i];
            if (value != originalOrder) {
                // alert("out of order: value: " + value + " i: " + i + " originalOrder: " + originalOrder);
                Dynasite.updateDisplayOrder(nullCallback, i, value);
            }
        }
        // alert("newlist:" + newlist)
    }*/
}

function fieldOrderTest() {
    // var origFieldOrder = $("origFieldOrder");
    var fieldOrder = $("fieldOrder");
    var selectwidget = $("selectwidget");
    var newFieldOrder = fieldOrder.value;
    var saveOrderBtn = $("saveOrderBtn");
    if (origFieldOrder != newFieldOrder) {
        saveOrderBtn.className = "btnRed";
    } else {
        saveOrderBtn.className = "btn";
        // saveOrderBtn.className = "";
    }
}

function nullCallback(data)
{

}

function confirmFieldOrderUpdate()
{
    new Effect.Highlight('fieldList', {startcolor:'#ffff99', endcolor:'#ffffff', restorecolor:'#ffffff' });
    var result = $("result");
    result.innerHTML = "Field Order Updated."
}

function echoOrder(data)
{
    var order = $("order");
    // alert("data: " + data)
    order.innerHTML = data;
}

function addSharedField(fieldId, numPageItems, formId) {
    var selected = DWRUtil.getValue(fieldId);
    if (selected == -1) {
        alert("You must select an entry to be moved!");
    } else {  // Something is selected
        // alert("Selected field: " + selected + " numPageItems: " + numPageItems + " formId: " + formId);
        Dynasite.saveSharedField(confirmSharedFieldAdded, numPageItems + 1, selected, formId);
    }
}

function confirmSharedFieldAdded()
{
    new Effect.Highlight('fieldList', {startcolor:'#ffff99', endcolor:'#ffffff', restorecolor:'#ffffff' });
    var result = $("sharedResult");
    result.innerHTML = "Shared field added."
    window.location.reload()
}

function importPatient(url, jsessionId) {
    var patientId = DWRUtil.getValue("patientId");
    window.location.href = "/zeprs/admin/import.do;jsessionid=" + jsessionId + "?url=" + url + "&patientId=" + patientId;
}