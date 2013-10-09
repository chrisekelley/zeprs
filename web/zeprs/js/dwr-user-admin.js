function modifyUserGroup(username, group)
{
   User.modifyUserGroup(displayUserGroup, username, group);
}

function displayUserGroup(sequence)
{
    var dvals = sequence.split("=");
    var user = dvals[0];
    var group =dvals[1];
	var valueText = "value" + user;
    var valueLink =  document.getElementById(valueText);
    valueLink.innerHTML = group;
    valueLink.style.visibility = "visible";
	valueLink.style.display = "block";
	var widgetItem =  document.getElementById("widget" + user);
	widgetItem.style.display = "none";
	widgetItem.style.visibility = "hidden";
	widgetItem.innerHTML = "";
	window.location.reload(true);
}

function callGroups(username) {
        WidgetDisplay.dispatchGroups(displayWidget, username);
        DWREngine.setPreHook(function() { $('disabledZone').style.visibility = 'visible'; });
        DWREngine.setPostHook(function() { $('disabledZone').style.visibility = 'hidden'; });
}