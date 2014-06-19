function modifyUserGroup(username)
{
	var e = document.getElementById("groups" + username);
	var strGroup = e.options[e.selectedIndex].value;
	User.modifyUserGroup(displayUserGroup, username, strGroup);
}

function displayUserGroup(sequence)
{
    var dvals = sequence.split("=");
    var user = dvals[0];
    var group =dvals[1];
	var valueText = "value" + user;
    var valueLink =  document.getElementById(valueText);
    valueLink.innerHTML = group;
    valueLink.className = "renderedValue";
    valueLink.style.visibility = "visible";
	valueLink.style.display = "block";
	var widgetItem =  document.getElementById("widget" + user);
	widgetItem.style.display = "none";
	widgetItem.style.visibility = "hidden";
	widgetItem.innerHTML = "";
}

function callGroups(username) {
	var widgetItem =  document.getElementById("widget" + username);
	widgetItem.style.display = "block";
	widgetItem.style.visibility = "visible";
    WidgetDisplay.dispatchGroups(displayWidget, username);
    DWREngine.setPreHook(function() { $('disabledZone').style.visibility = 'visible'; });
    DWREngine.setPostHook(function() { $('disabledZone').style.visibility = 'hidden'; });
}

function displayInputField(username, fieldName) {
	var widgetItem =  document.getElementById("widget" + fieldName + username);
	widgetItem.style.display = "block";
	widgetItem.style.visibility = "visible";
	var widgetName = "input" + fieldName + username;
	var eventHandler = "";
//	if (fieldName == "Password") {
//		eventHandler =  "updateUserInfo('" + username + "', " + "'" + fieldName + "')\"" 
//	} else if (fieldname == "lastName") {
//		eventHandler =  "updateUserInfo('" + username + "', " + "'" + fieldName + "')\"" 
//	} 
	eventHandler =  "updateUserInfo('" + username + "', " + "'" + fieldName + "')\"" 
	var inputType = "text";
	if (fieldName == 'Password') {
		inputType = "password";
	}
    var inputField = "<input id=\"" + widgetName + "\" type=\"" + inputType + "\" name=\"" + widgetName + "\" size=\"20\" maxlength=\"50\" onchange = \""+ eventHandler + "/>\n";
//	var inputField = "<input id=\"" + widgetName + "\" type=\"text\" name=\"" + widgetName + "\" size=\"20\" maxlength=\"50\" />\n";
   //  \"modifyUserGroup('" + username + "', " + group.getId() + ")\">" 
    var identifier = fieldName + username;
    displayWidget(identifier + "=" + inputField);
//    DWREngine.setPreHook(function() { $('disabledZone').style.visibility = 'visible'; });
//	DWREngine.setPostHook(function() { $('disabledZone').style.visibility = 'hidden'; });
}

function updateUserInfo(username, fieldName) {
	var e = document.getElementById("input" + fieldName + username);
	var value = e.value
//	alert("value: " + value);
	User.updateUserInfo(displayResult, fieldName, username, value);
}

function displayResult(sequence)
{
    var dvals = sequence.split("=");
    var element = dvals[0];
    var result =dvals[1];
	var valueText = "value" + element;
    var valueLink =  document.getElementById(valueText);
    valueLink.innerHTML = result;
//    class="renderedValue" 
    valueLink.className = "renderedValue";
    valueLink.style.visibility = "visible";
	valueLink.style.display = "block";
	var widgetItem =  document.getElementById("widget" + element);
	widgetItem.style.display = "none";
	widgetItem.style.visibility = "hidden";
	widgetItem.innerHTML = "";
	

//	window.location.reload(true);
}