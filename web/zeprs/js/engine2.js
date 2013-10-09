







function DWREngine()
{
}








DWREngine.XMLHttpRequest = 1;








DWREngine.IFrame = 2;









DWREngine.setErrorHandler = function(handler)
{
DWREngine._errorHandler = handler;
};









DWREngine.setWarningHandler = function(handler)
{
DWREngine._warningHandler = handler;
};







DWREngine.setPreHook = function(handler)
{
DWREngine._preHook = handler;
};







DWREngine.setPostHook = function(handler)
{
DWREngine._postHook = handler;
};







DWREngine.setMethod = function(newmethod)
{
if (newmethod != DWREngine.XMLHttpRequest && newmethod != DWREngine.IFrame)
{
if (DWREngine._errorHandler)
{
DWREngine._errorHandler("Remoting method must be one of DWREngine.XMLHttpRequest or DWREngine.IFrame");
}

return;
}

DWREngine._method = newmethod;
};






DWREngine.setVerb = function(verb)
{
if (verb != "GET" && verb != "POST")
{
if (DWREngine._errorHandler)
{
DWREngine._errorHandler("Remoting verb must be one of GET or POST");
}

return;
}

DWREngine._verb = verb;
};










DWREngine.setOrdered = function(ordered)
{
DWREngine._ordered = ordered;
};







DWREngine.defaultMessageHandler = function(message)
{
if (typeof message == "object" && message.name == "Error" && message.description)
{
alert("Error: " + message.description);
}
else
{
alert(message);
}
};






DWREngine.beginBatch = function()
{
if (DWREngine._batch)
{
if (DWREngine._errorHandler)
{
DWREngine._errorHandler("Batch already started.");
}

return;
}


DWREngine._batch = {};
DWREngine._batch.map = {};
DWREngine._batch.paramCount = 0;
DWREngine._batch.map.callCount = 0;
DWREngine._batch.metadata = {};
};





DWREngine.endBatch = function()
{
if (DWREngine._batch == null)
{
if (DWREngine._errorHandler)
{
DWREngine._errorHandler("No batch in progress.");
}

return;
}




if (!DWREngine._ordered)
{
DWREngine._sendData(DWREngine._batch);
DWREngine._batches[DWREngine._batches.length] = DWREngine._batch;
}
else
{
if (DWREngine._batches.length == 0)
{

DWREngine._sendData(DWREngine._batch);
DWREngine._batches[DWREngine._batches.length] = DWREngine._batch;
}
else
{

DWREngine._batchQueue[DWREngine._batchQueue.length] = DWREngine._batch;
}
}

DWREngine._batch = null;
};









DWREngine._errorHandler = DWREngine.defaultMessageHandler;





DWREngine._warningHandler = DWREngine.defaultMessageHandler;





DWREngine._preHook = null;





DWREngine._postHook = null;





DWREngine._batches = [];






DWREngine._batchQueue = [];





DWREngine._callbacks = {};





DWREngine._method = DWREngine.XMLHttpRequest;





DWREngine._verb = "POST";






DWREngine._ordered = false;





DWREngine._batch = null;








DWREngine._handleResponse = function(id, reply)
{
var func = DWREngine._callbacks[id];


DWREngine._callbacks[id] = null;

if (func)
{


try
{
func(reply);
}
catch (ex)
{
if (DWREngine._errorHandler)
{
DWREngine._errorHandler(ex);
}
}
}
else
{


if (reply)
{
if (DWREngine._warningHandler)
{
DWREngine._warningHandler("Internal Error: Missing callback for id='" + id + "'");
}
}
}
};






DWREngine._handleError = function(id, reason)
{
if (DWREngine._errorHandler)
{
DWREngine._errorHandler(reason);
}
};






DWREngine._finalize = function(batch)
{
DWREngine._removeNode(batch.div);
DWREngine._removeNode(batch.iframe);
DWREngine._removeNode(batch.form);

if (DWREngine._postHook)
{
DWREngine._postHook();
}


for (var i = 0; i < DWREngine._batches.length; i++)
{
if (DWREngine._batches[i] == batch)
{
DWREngine._batches.splice(i, 1);
break;
}
}




if (DWREngine._batchQueue.length != 0)
{
var batch = DWREngine._batchQueue.shift();
DWREngine._sendData(batch);
DWREngine._batches[DWREngine._batches.length] = batch;
}
};






DWREngine._removeNode = function(node)
{
if (node)
{
node.parentNode.removeChild(node);
}
};













DWREngine._execute = function(path, scriptName, methodName, vararg_params)
{
var singleShot = false;
if (DWREngine._batch == null)
{
DWREngine.beginBatch();
singleShot = true;
}


var args = [];
for (var i = 0; i < arguments.length - 3; i++)
{
args[i] = arguments[i + 3];
}


if (DWREngine._batch.path == null)
{
DWREngine._batch.path = path;
}
else
{
if (DWREngine._batch.path != path)
{
if (DWREngine._errorHandler)
{
DWREngine._errorHandler("Can't batch requests to multiple DWR Servlets.");
}

return;
}
}



var func;
var params;
var metadata;

var firstArg = args[0];
var lastArg = args[args.length - 1];

if (typeof firstArg == "function")
{
func = args.shift();
params = args;
metadata = {};
}
else if (typeof lastArg == "function")
{
func = args.pop();
params = args;
metadata = {};
}
else if (typeof lastArg == "object" && lastArg.callback != null && typeof lastArg.callback == "function")
{
metadata = args.pop();
params = args;
func = metadata.callback;
}
else if (firstArg == null)
{



if (lastArg == null && args.length > 2)
{
if (DWREngine._warningHandler)
{
DWREngine._warningHandler("Ambiguous nulls at start and end of parameter list. Which is the callback function?");
}
}

func = args.shift();
params = args;
metadata = {};
}
else if (lastArg == null)
{
func = args.pop();
params = args;
metadata = {};
}
else
{
if (DWREngine._warningHandler)
{
DWREngine._warningHandler("Missing callback function or metadata object.");
}

return;
}


var random = Math.floor(Math.random() * 10001);
var id = (random + "_" + new Date().getTime()).toString();



DWREngine._callbacks[id] = func;

var prefix = "c" + DWREngine._batch.map.callCount + "-";


if (metadata != null)
{
for (var prop in metadata)
{
DWREngine._batch.metadata[prop] = metadata[prop];
}
}

DWREngine._batch.map[prefix + "scriptName"] = scriptName;
DWREngine._batch.map[prefix + "methodName"] = methodName;
DWREngine._batch.map[prefix + "id"] = id;


DWREngine._addSerializeFunctions();
for (i = 0; i < params.length; i++)
{
DWREngine._serializeAll(DWREngine._batch, [], params[i], prefix + "param" + i);
}
DWREngine._removeSerializeFunctions();


DWREngine._batch.map.callCount++;

if (singleShot)
{
DWREngine.endBatch();
}
};






DWREngine._abortRequest = function(batch)
{
if (batch && batch.metadata && batch.completed != true)
{
batch.completed = true;
if (batch.req != null)
{
batch.req.abort();

if (batch.metadata.errorHandler)
{
if (typeof batch.metadata.errorHandler == "string")
{
eval(batch.metadata.errorHandler);
}
else if (typeof batch.metadata.errorHandler == "function")
{
batch.metadata.errorHandler();
}
else
{
if (DWREngine._warningHandler)
{
DWREngine._warningHandler("errorHandler is neither a string (for eval()) or a function.");
}
}
}
}
}
};






DWREngine._sendData = function(batch)
{

if (DWREngine._preHook)
{
DWREngine._preHook();
}


if (batch.metadata && batch.metadata.timeout)
{
var funcReq = function() { DWREngine._abortRequest(batch); };
setTimeout(funcReq, batch.metadata.timeout);
}


if (DWREngine._method == DWREngine.XMLHttpRequest)
{
if (window.XMLHttpRequest)
{
batch.req = new XMLHttpRequest();
}

else if (window.ActiveXObject && !(navigator.userAgent.indexOf('Mac') >= 0 && navigator.userAgent.indexOf("MSIE") >= 0))
{
batch.req = new window.ActiveXObject("Microsoft.XMLHTTP");
}
}


var statsInfo;
if (batch.map.callCount == 1)
{
statsInfo = batch.map["c0-scriptName"] + "." + batch.map["c0-methodName"];
}
else
{
statsInfo = "Multiple." + batch.map.callCount;
}

var query = "";
var prop;

if (batch.req)
{
batch.map.xml = true;


batch.req.onreadystatechange = function() { DWREngine._stateChange(batch); };


if (DWREngine._verb == "GET" || navigator.userAgent.indexOf('Safari') >= 0)
{
for (prop in batch.map)
{
query += encodeURIComponent(prop) + "=" + encodeURIComponent(batch.map[prop]) + "&";
}
query = query.substring(0, query.length - 1);

try
{
batch.req.open("GET", batch.path + "/exec/" + statsInfo + "?" + query);
batch.req.send(null);
}
catch (ex)
{
if (DWREngine._errorHandler)
{
DWREngine._errorHandler(ex);
}
}
}
else
{
for (prop in batch.map)
{
query += prop + "=" + batch.map[prop] + "\n";
}

try
{
batch.req.open("POST", batch.path + "/exec/" + statsInfo, true);
batch.req.send(query);
}
catch (ex)
{
if (DWREngine._errorHandler)
{
DWREngine._errorHandler(ex);
}
}
}
}
else
{
batch.map.xml = false;

var idname = "dwr-if-" + batch.map["c0-id"];


batch.div = document.createElement('div');
batch.div.innerHTML = "<iframe id='" + idname + "' name='" + idname + "'></iframe>";
document.body.appendChild(batch.div);
batch.iframe = document.getElementById(idname);
batch.iframe.setAttribute('style', 'width:0px; height:0px; border:0px;');

if (DWREngine._verb == "GET")
{
for (prop in batch.map)
{
query += encodeURIComponent(prop) + "=" + encodeURIComponent(batch.map[prop]) + "&";
}
query = query.substring(0, query.length - 1);

batch.iframe.setAttribute('src', batch.path + "/exec/" + statsInfo + "?" + query);
document.body.appendChild(batch.iframe);
}
else
{
batch.form = document.createElement('form');
batch.form.setAttribute('id', 'dwr-form');
batch.form.setAttribute('action', batch.path + "/exec" + statsInfo);
batch.form.setAttribute('target', idname);
batch.form.target = idname;
batch.form.setAttribute('method', 'post');
for (prop in batch.map)
{
var formInput = document.createElement('input');
formInput.setAttribute('type', 'hidden');
formInput.setAttribute('name', prop);
formInput.setAttribute('value', batch.map[prop]);
batch.form.appendChild(formInput);
}

document.body.appendChild(batch.form);
batch.form.submit();
}
}
};





DWREngine._stateChange = function(batch)
{
if (batch.req.readyState == 4)
{
try
{
if (batch.req.status && batch.req.status == 200)
{
batch.completed = true;
eval(batch.req.responseText);
}
else
{
if (batch.metadata != null)
{
DWREngine._abortRequest(batch);
}
else if (DWREngine._errorHandler)
{
DWREngine._errorHandler(batch.req.responseText);
}
}
}
catch (ex)
{
if (batch.metadata != null)
{
DWREngine._abortRequest(batch);
}
else if (DWREngine._errorHandler)
{
DWREngine._errorHandler(ex);
}
}

DWREngine._finalize(batch);
}
};






DWREngine._addSerializeFunctions = function()
{
Object.prototype.dwrSerialize = DWREngine._serializeObject;
Array.prototype.dwrSerialize = DWREngine._serializeArray;
Boolean.prototype.dwrSerialize = DWREngine._serializeBoolean;
Number.prototype.dwrSerialize = DWREngine._serializeNumber;
String.prototype.dwrSerialize = DWREngine._serializeString;
Date.prototype.dwrSerialize = DWREngine._serializeDate;
};






DWREngine._removeSerializeFunctions = function()
{
delete Object.prototype.dwrSerialize;
delete Array.prototype.dwrSerialize;
delete Boolean.prototype.dwrSerialize;
delete Number.prototype.dwrSerialize;
delete String.prototype.dwrSerialize;
delete Date.prototype.dwrSerialize;
};









DWREngine._serializeAll = function(batch, referto, data, name)
{
if (data == null)
{
batch.map[name] = "null:null";
return;
}

switch (typeof data)
{
case "boolean":
batch.map[name] = "boolean:" + data;
break;

case "number":
batch.map[name] = "number:" + data;
break;

case "string":
batch.map[name] = "string:" + encodeURIComponent(data);
break;

case "object":
if (data.dwrSerialize)
{
batch.map[name] = data.dwrSerialize(batch, referto, data, name);
}
else
{
if (DWREngine._warningHandler)
{
DWREngine._warningHandler("Object without dwrSerialize: " + typeof data + ", attempting default converter.");
}
batch.map[name] = "default:" + data;
}
break;

case "function":

break;

default:
if (DWREngine._warningHandler)
{
DWREngine._warningHandler("Unexpected type: " + typeof data + ", attempting default converter.");
}
batch.map[name] = "default:" + data;
break;
}
};












DWREngine._lookup = function(referto, data, name)
{
var lookup;
for (var i = 0; i < referto.length; i++)
{
if (referto[i].data == data)
{
lookup = referto[i];
break;
}
}

if (lookup)
{
return "reference:" + lookup.name;
}

referto.push({ data:data, name:name });
return null;
};






DWREngine._serializeObject = function(batch, referto, data, name)
{
var ref = DWREngine._lookup(referto, this, name);
if (ref)
{
return ref;
}


var reply = "Object:{";
var element;
for (element in this)
{
if (element != "dwrSerialize")
{
batch.paramCount++;
var childName = "c" + DWREngine._batch.map.callCount + "-e" + batch.paramCount;
DWREngine._serializeAll(batch, referto, this[element], childName);

reply += encodeURIComponent(element);
reply += ":reference:";
reply += childName;
reply += ", ";
}
}
reply = reply.substring(0, reply.length - 2);
reply += "}";

return reply;
};






DWREngine._serializeArray = function(batch, referto, data, name)
{
var ref = DWREngine._lookup(referto, this, name);
if (ref)
{
return ref;
}

var reply = "Array:[";
for (var i = 0; i < this.length; i++)
{
if (i != 0)
{
reply += ",";
}

batch.paramCount++;
var childName = "c" + DWREngine._batch.map.callCount + "-e" + batch.paramCount;
DWREngine._serializeAll(batch, referto, this[i], childName);
reply += "reference:";
reply += childName;
}
reply += "]";

return reply;
};






DWREngine._serializeBoolean = function(batch, referto, data, name)
{
return "Boolean:" + this;
};






DWREngine._serializeNumber = function(batch, referto, data, name)
{
return "Number:" + this;
};






DWREngine._serializeString = function(batch, referto, data, name)
{
return "String:" + encodeURIComponent(this);
};






DWREngine._serializeDate = function(batch, referto, data, name)
{
return "Date:[ " +
this.getUTCFullYear() + ", " +
this.getUTCMonth() + ", " +
this.getUTCDate() + ", " +
this.getUTCHours() + ", " +
this.getUTCMinutes() + ", " +
this.getUTCSeconds() + ", " +
this.getUTCMilliseconds() + "]";
};







DWREngine._unserializeDocument = function(xml)
{
var parser = new DOMParser();
var dom = parser.parseFromString(xml, "text/xml");

if (!dom.documentElement || dom.documentElement.tagName == "parsererror")
{
var message = dom.documentElement.firstChild.data;
message += "\n" + dom.documentElement.firstChild.nextSibling.firstChild.data;
throw message;
}

return dom;
};






DWREngine._deprecated = function()
{
if (DWREngine._warningHandler)
{
DWREngine._warningHandler("dwrXxx() functions are deprecated. Please convert to DWREngine.xxx()");
}
};


