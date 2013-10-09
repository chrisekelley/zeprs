function callWidget(pageItemId, formFieldId, formId, encounterId) {
    var item = document.getElementById("field" + formFieldId);
    if (! item) {
            var item = document.getElementById("inputWidget" + formFieldId);
        }
    if (! item) {
        var item = document.getElementById("value" + formFieldId);
        var widget = document.getElementById("widget" + formFieldId);
        if (item.style.display == "none") {
			itemValue = widget.innerHTML;
			if (itemValue.substring(0,6) == "Error:") {
				itemValue = item.innerHTML;
		    }
        } else {
            itemValue = item.innerHTML;
        }
        WidgetDisplay.dispatch(displayWidget, itemValue, pageItemId, formFieldId, formId, encounterId);
        DWREngine.setPreHook(function() { $('disabledZone').style.visibility = 'visible'; });
        DWREngine.setPostHook(function() { $('disabledZone').style.visibility = 'hidden'; });
    }
}

function callChartWidget(pageItemId, formFieldId, formId, encounterId, inputType, displayField) {
    if (displayField != "") {
        //alert("inputWidget" + encounterId + "." + displayField);  // enum: 1847, float: 1858
        var item = document.getElementById("inputWidget" + encounterId + "." + displayField);
        if (! item) {
            var item = document.getElementById("inputWidget" + encounterId + "." + formFieldId);
        }
    } else {
        var item = document.getElementById("inputWidget" + encounterId + "." + formFieldId);
    }
    if (! item) {
      //  alert("2 inputWidget" + encounterId + "." + displayField);  // enum: 1847, float: 1858
        var item = document.getElementById("value" + encounterId + "." + formFieldId);
        var widget = document.getElementById("widget" + encounterId + "." + formFieldId);
        if (! item) {
            var item = document.getElementById("value" + encounterId + "." + displayField);
            var widget = document.getElementById("widget" + encounterId + "." + displayField);
        }
        if (item.style.display == "none") {
            itemValue = widget.innerHTML;
        } else {
            itemValue = item.innerHTML;
        }
        if (inputType =="lab_results") {
            // if (widget.innerHTML =="") {
                WidgetDisplay.dispatchChartLabs(displayWidget, itemValue, pageItemId, formFieldId, formId, encounterId, displayField);
                DWREngine.setPreHook(function() { $('disabledZone').style.visibility = 'visible'; });
                DWREngine.setPostHook(function() { $('disabledZone').style.visibility = 'hidden'; });
            // } else {
                // alert("widget.innerHTML|" + widget.innerHTML+ "|");
           //  }
        } else {
            WidgetDisplay.dispatchChart(displayWidget, itemValue, pageItemId, formFieldId, formId, encounterId);
            DWREngine.setPreHook(function() { $('disabledZone').style.visibility = 'visible'; });
            DWREngine.setPostHook(function() { $('disabledZone').style.visibility = 'hidden'; });
        }
    }
}

/**
 * Calls widget for editing encounter record metadata such as site id.
 * @param {Object} item
 * @param {Object} encounterId
 */
function callMetaDataWidget(metadataItem, encounterId) {
	var item = document.getElementById("value" + metadataItem);
    var widget = document.getElementById("widget" + metadataItem);
    if (item.style.display == "none") {
        itemValue = widget.innerHTML;
    } else {
        itemValue = item.innerHTML;
    }
    WidgetDisplay.dispatchMetaData(displayWidget, itemValue, metadataItem, encounterId);
    DWREngine.setPreHook(function() { $('disabledZone').style.visibility = 'visible'; });
    DWREngine.setPostHook(function() { $('disabledZone').style.visibility = 'hidden'; });
}

function updateRecord(inputType, formFieldId, pageItemId, formId, encounterId, widgetType) {
    DWREngine.setPreHook(function() { $('disabledZone').style.visibility = 'visible'; });
    DWREngine.setPostHook(function() { $('disabledZone').style.visibility = 'hidden'; Fat.fade_element("widget" + formFieldId, 60, 2000, "#FFFF33", "#AFEEEE") });
    itemValue = getValue(formFieldId, "inputWidget", inputType);
    var displayFunction = displayWidget;
    if (widgetType == "Chart") {
       displayFunction = displayWidget;
    }
    var displayField = formFieldId;
    if (itemValue == "") {
        if (inputType == "checkbox") {
        Encounter.update(displayFunction, inputType, itemValue, pageItemId, formId, encounterId, widgetType, displayField);
        } else {
        alert("No value entered. Please enter a value.");
        }
    } else {
        var formName = "form"+formId;
        // var thisForm = document.getElementsByTagName("form")[0];
        var thisForm = document.getElementById("form"+formId);
        // alert("thisForm = "+ thisForm);
        //var validate = eval("validateForm" + formId + "(this)");
        //var validate = eval("validateForm" + formId + "("+ thisForm + ")");
        var validate = eval("validateForm" + formId + "(document.getElementById('form" +formId + "'))");
        if (validate) {
            Encounter.update(displayFunction, inputType, itemValue, pageItemId, formId, encounterId, widgetType, displayField);
        }
    }
}

/**
 * Update an encounter record metadata field such as siteId amd dateVisit.
 * @param {Object} inputType
 * @param {Object} encounterId
 *
 */
function updateRecordMetadata(inputType, encounterId) {
    DWREngine.setPreHook(function() { $('disabledZone').style.visibility = 'visible'; });
    DWREngine.setPostHook(function() { $('disabledZone').style.visibility = 'hidden'; Fat.fade_element("widget" + inputType, 60, 2000, "#FFFF33", "#AFEEEE") });
	if (inputType == 'dateVisit') {
		    itemValue = getValue(1, "inputWidget", inputType);
	} else {
		    itemValue = getValue(0, "inputWidget", inputType);
	}
    var displayFunction = displayWidget;
    if (itemValue == "") {
        alert("No value entered. Please enter a value.");
    } else {
		//alert("itemValue: " + itemValue);
        Encounter.updateMetadata(displayFunction, inputType, itemValue, encounterId);
    }
}

function updateRecordChart(inputType, formFieldId, pageItemId, formId, encounterId, widgetType, displayField) {
    DWREngine.setPreHook(function() { $('disabledZone').style.visibility = 'visible'; });
    DWREngine.setPostHook(function() { $('disabledZone').style.visibility = 'hidden';
    var fadeElementName =  "widget"+ encounterId + "." + formFieldId;
    var fadeElement =  document.getElementById(fadeElementName);
    if (!fadeElement) {
       fadeElementName =  "widget"+ encounterId + "." + displayField;
    }
    Fat.fade_element(fadeElementName, 60, 2000, "#FFFF33", "#AFEEEE") });
    var inputWidgetName =  encounterId + "." + formFieldId;
    if (formFieldId == 1858) {  // HB screen lab test
        if (displayField) {
        inputWidgetName =  encounterId + "." + displayField;
        }
        itemValue = getValue(inputWidgetName, "inputWidget", inputType);
		var isValid = simpleValidateFloat(itemValue, 0.5, 20);
		if (isValid == false) {
			alert("Hemoglobin test results should be between 0.5 - 20.");
			DWRUtil.setValue("inputWidget" + inputWidgetName, "");
			return null;
		}
    } else if (formFieldId == 2004) {  // CD4 count lab test
        if (displayField) {
        inputWidgetName =  encounterId + "." + displayField;
        }
        itemValue = getValue(inputWidgetName, "inputWidget", inputType);
    } else {
        itemValue = getValue(inputWidgetName, "inputWidget", inputType);
    }

    var displayFunction = displayWidget;
    if ((formFieldId == 1563) || (formFieldId == 1565)) {  // RPR Result or RPR Drug
        displayFunction = displayChartDateWidget;
    }
    if (displayField != "") {
        displayFunction = displayChartWidget;
    }
    if (itemValue == "") {
        if (inputType == "checkbox" || inputType == "checkbox_dwr") {
        Encounter.update(displayFunction, inputType, itemValue, pageItemId, formId, encounterId, widgetType, displayField);
        } else {
        alert("No value entered. Please enter a value.");
        }
    } else {
        // cek 2/20/2006 - disabled vlaidation - causing problems w/ labs form.
        // var validate = eval("validateForm" + formId + "(this)");
       // if (validate) {
            Encounter.update(displayFunction, inputType, itemValue, pageItemId, formId, encounterId, widgetType, displayField);
       // }
    }
}


function updateRecordIdName(inputType, formFieldId, pageItemId, formId, encounterId, idName, widgetType) {
    if (!widgetType) {
        widgetType = "Form";
    }
    displayField = formFieldId;
    idNamePrefix = "field" + idName;
    itemValue = getValue(formFieldId, idNamePrefix, inputType);
    Encounter.update(displayWidget, inputType, itemValue, pageItemId, formId, encounterId, widgetType, displayField);
}

/**
 * Fetch value of rendered widget
 * @param {Object} formFieldId
 * @param {Object} prefix
 * @param {Object} inputType
 */
function getValue(formFieldId, prefix, inputType) {
     //alert ("prefix + formFieldId: " + prefix + formFieldId + " inputType: " + inputType);
	if (formFieldId == 0) {	// record metadata field
		var item = document.getElementById(prefix + inputType);
		if (inputType == "SiteId") {
			itemValue = item[item.selectedIndex].value;
		}
	} else {
		var item = document.getElementById(prefix + formFieldId);
	}

    if (inputType == "patientid") {
        item = document.getElementById("patient");
        // alert("item.value: " + item.value)
    }
    if (inputType == "select") {
        itemValue = item[item.selectedIndex].value;
    } else if (inputType == "textarea") {
        itemValue = item.value;
    } else if (inputType == "checkbox" || inputType == "checkbox_dwr") {
        if (item.checked == true) {
            // cek changed 2/1/2006 - problems w/ routine ante form, problem field
        itemValue = 1;
        } else {
        itemValue = 0;
        }
    } else {
        itemValue = item.value;
    }
	// alert("prefix + formFieldId: " + prefix + formFieldId + ", itemValue: " + itemValue);
    return itemValue;
}

/**
 * cek 11 aug 2005
 * Uncomment the following line to check if user is logged in
 * test("http://" + hostname + ":8080/zeprs/dwr;jsessionid=" + jsessionid);
 * @param {Object} url
 */
function test(url) {

    if (window.XMLHttpRequest)
    {
        xmlhttp = new XMLHttpRequest();
    }

    else if (window.ActiveXObject && !(navigator.userAgent.indexOf('Mac') >= 0 && navigator.userAgent.indexOf("MSIE") >= 0))
    {
        xmlhttp = new window.ActiveXObject("Microsoft.XMLHTTP");
    }

     xmlhttp.open("GET", url,true);
     xmlhttp.onreadystatechange=function() {
     // alert("xmlhttp.readyState: " + xmlhttp.readyState + " xmlhttp.status: " + xmlhttp.status + " xmlhttp.statusText: " + xmlhttp.statusText)
     if (xmlhttp.readyState==4) {
     var txt = xmlhttp.responseText
        if (xmlhttp.status==200) {
            var titleLoc = txt.indexOf("title",0);
            var subs = txt.substring(titleLoc+6,35);
            if (subs !="DWR Test Index") {
            alert("Your session has expired and you have been logged out of the ZEPRS application. \n\n Please refresh this page and login again.")
            }
        }
        else if (xmlhttp.status==404) {
            alert("URL doesn't exist!")
        }
        else {
            alert("Status is "+xmlhttp.status)
        }
      }
     }
     xmlhttp.send(null)
}

var displayWidget = function(data)
{
	var delim = data.indexOf("=",0);
    var length = data.length;
    var fieldId = data.substr(0,delim);
    var key = "widget" + fieldId;
    var value =  data.substr(delim+1,length);
    // alert("length: " + length + "; key: " + key + "; value: " + value );
    var itemValue = document.getElementById(key);
    itemValue.innerHTML = value;
    var valueLink =  document.getElementById("value" + fieldId);
    valueLink.style.display = "none";
    valueLink.style.visibility = "hidden";
	if (value.substring(0,6) == "Error:") {
		alert(value);
    }
}

var displayChartWidget = function(data)
{
    var delim = data.indexOf("=",0);
    var length = data.length;
    var fieldId = data.substr(0,delim);
    var key = "widget" + fieldId;
    var value =  data.substr(delim+1,length);
    var itemValue = document.getElementById(key);
    itemValue.innerHTML = value;
    var valueLink =  document.getElementById("value" + fieldId);
    valueLink.style.display = "none";
    valueLink.style.visibility = "hidden";
}

// Updates value and date
var displayChartDateWidget = function(data)
{
    var dvals = data.split("=");
    var fieldId = dvals[0];
    var value =  dvals[1];
    var dateFieldId =  dvals[2];
    var dateValue =  dvals[3];
    var key = "widget" + fieldId;
    var itemValue = document.getElementById(key);
    itemValue.innerHTML = value;
    var valueLink =  document.getElementById("value" + fieldId);
    valueLink.style.display = "none";
    valueLink.style.visibility = "hidden";
    if (dateValue != "") {
        DWRUtil.setValue("widget" + dateFieldId, dateValue);
    }

}

function redirect(url, jsessionId, patientId, labtest_id, extendedTestId) {
    var dateResultsField = "widget" + labtest_id + ".1846";
    var dateResultsFieldV = "value" + labtest_id + ".1846";
    var dateResults = DWRUtil.getValue(dateResultsField);
    var dateResultsV = DWRUtil.getValue(dateResultsField);
    if (dateResults == "" && dateResultsFieldV == '') {
        alert("Please enter Date of Lab Results first.")
    } else {
        window.location.href = "/zeprs/" + url +".do;jsessionid=" + jsessionId + "?patientId=" + patientId + "&field2037=" + labtest_id + "&extendedTestId=" + extendedTestId;
    }
}

