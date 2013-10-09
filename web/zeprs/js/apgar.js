var ns4 = (document.layers)? true : false;
var ie4 = (document.all)? true : false;
//  var ie4 = (document.all && !document.getElementById)? true : false;
//  var ie5 = (document.getElementById && document.all)? true : false;
var ns6 = (document.getElementById && !document.all)? true: false;
var w3c = (document.getElementById)? true : false;

var wMin, prevGray;
var maxMins = 5;


function bgclr(el,whatColor, field) {
	if (whatColor == 0) {whatColor = "";}
	if (whatColor == 1) {whatColor = "#ffffcc"}
	if (whatColor == 2) {whatColor = "#ccccff"}
	if (ie4 || ns6) {
		//  alert (el.style.backgroundColor);
		if (el.style.backgroundColor != "#ccccff" && el.style.backgroundColor != "rgb(204,204,255)") {
			el.style.backgroundColor = whatColor;
		}
	} else if (ns4) {	//  this doesn't work
		if (el.backgroundColor != "#ccccff") {
			el.backgroundColor = whatColor;
		}
	}

	if (whatColor == "#ccccff") {
		whatID = el.id;
		fixedID = whatID.replace(field,"")
		whatIDNum = parseInt(fixedID);
		numLoc = fixedID.indexOf(whatIDNum);
		nameID = fixedID.slice(0,numLoc);
		score = fixedID.charAt(fixedID.length-1);
        //alert(score);
		changebg(nameID, score);

		//  Display score
		if (!wMin) {wMin = 1}
		//alert("replaced: "+fixedID+" from "+whatID+" : "+getLabel(field+""+wMin + nameID));
		//alert("do we have " + field+""+wMin + nameID + "?");
		wScoreEl = eval(getLabel(field+""+wMin + nameID));
		if (wScoreEl.innerHTML != ""  && wScoreEl.innerHTML != "&nbsp;" && !(prevGray)) {
		// alert("wScoreEl = " + wScoreEl.id + "\nprevGray = " + prevGray);
			var n=1;
			while (n <= maxMins) {
			//alert("id:"+getLabel(field+""+n + nameID));
				checkScore = eval(getLabel(field+""+n + nameID));
				if (checkScore.innerHTML != ""  && checkScore.innerHTML != "&nbsp;") {
					checkScore.innerHTML = score;
					addScores(n, field);
					break;
				}
				if (n == maxMins) {
				//alert(getLabel(maxMins + nameID));
					checkScore = eval(getLabel(maxMins + nameID));
					checkScore.innerHTML = score;
					addScores(maxMins, field);
					break;
				}
				n += 5;
			}
		} else if (ie4 || ns6) {wScoreEl.innerHTML = score;}
		if (ns4) {}

		//  Add scores
		addScores(wMin, field);
		// var nScore = addScores(wMin);
		// if (nScore > 6 || isNaN(nScore)) {whatColor = "#ccccff"}
	}

	return true;
}

function scoreNum(nameID) {
	//  Identify score of element
	scoreEl = eval(getLabel(nameID));
	score = scoreEl.innerHTML;
	return score;
}

function addScores(thisMin, field) {
	var h = parseInt(scoreNum(field+""+thisMin + "h"));
	var r = parseInt(scoreNum(field+""+thisMin + "r"));
	var m = parseInt(scoreNum(field+""+thisMin + "m"));
	var x = parseInt(scoreNum(field+""+thisMin + "x"));
	var c = parseInt(scoreNum(field+""+thisMin + "c"));

	if (!(isNaN(h) || isNaN(r) || isNaN(m) || isNaN(x) || isNaN(c))) {
		var nScore = h + r + m + x + c;
		whatScoreEl = eval(getLabel(field+""+thisMin + "score"));
		whatScoreEl.innerHTML = nScore;
		if (document.getElementById("inputWidget"+field)) {
			document.getElementById("inputWidget"+field).value = nScore;
		}
	} else {return}

	//  Create new column, if score is less than 6
	if (nScore <= 6 && parseInt(thisMin) >= 5) {
		var nextMin = 5 + parseInt(thisMin);
		if (nextMin > maxMins) {addColumn(nextMin, "",field);}
	}

	//  Remove column, if score greater than 6
	if (nScore > 6 && parseInt(thisMin) >= 5 && maxMins > 5) {
		for (whatCols = (parseInt(thisMin) + 5); whatCols <= maxMins; whatCols += 5) {
			addColumn(whatCols, "none", field);
		}
		maxMins = thisMin;
		wMin = thisMin;
		whatMin(wMin + "c");
	}
	return;
}

function addColumn (minCol,dispWhat, field) {
	var pMin = getLabel(field+""+minCol + "min");
	//alert(field+""+minCol + "min");
	pMin = eval(pMin);
	pMin.style.display = dispWhat;

	var pH = getLabel(field+""+minCol + "h");
	//alert(field+""+minCol + "h");
	pH = eval(pH);
	pH.style.display = dispWhat;

	var pR = getLabel(field+""+minCol + "r");
	pR = eval(pR);
	pR.style.display = dispWhat;

	var pM = getLabel(field+""+minCol + "m");
	pM = eval(pM);
	pM.style.display = dispWhat;

	var pX = getLabel(field+""+minCol + "x");
	pX = eval(pX);
	pX.style.display = dispWhat;

	var pC = getLabel(field+""+minCol + "c");
	pC = eval(pC);
	pC.style.display = dispWhat;

	var pScore = getLabel(field+""+minCol + "score");
	pScore = eval(pScore);
	pScore.style.display = dispWhat;

/*	var tableID = "apgar";
	var table = eval(getLabel(tableID));
	var numRows = table.rows.length;
	var row, cell;

	for (var z=0; z < numRows; z++) {
		row = table.rows[z];
		cell = row.insertCell();
		if (z == 0) {
			cell.innerHTML = minCol + "-minute<br>score";
			cell.id = minCol + "min";
			cell.style.backgroundColor = "#0033cc";
			cell.style.color = "white";
			cell.style.fontWeight = "bold";
			// cell.onclick = whatMin(cell.id);
		}
		if (z == 1) {cell.id = minCol + "h";
			// alert("cell.id = " + cell.id);
		}
		if (z == 2) {cell.id = minCol + "r"}
		if (z == 3) {cell.id = minCol + "m"}
		if (z == 4) {cell.id = minCol + "x"}
		if (z == 5) {cell.id = minCol + "c"}
		if (z == 6) {cell.id = minCol + "score"}
		if (z != 0) {
			cell.innerHTML = "&nbsp;";
			cell.className = "score";
			cell.style.backgroundColor = "";
			cell.onclick = "whatMin(\"" + cell.id + "\");"
		}
	}
	alert ("onclick = " + cell.onclick);
*/
	if (dispWhat == "") {
		wMin = minCol;
		maxMins = minCol;
		whatMin(pC.id);
	}


/*	if (document.getElementById) {
			//NN6 bug inserting cells in wrong sequence
			for (var i = arguments.length - 1; i >= 1; i--) {
				var cell = row.insertCell(arguments.length - 1 - i);
				cell.appendChild(document.createTextNode(arguments[i]));
			}
	}
*/
	return;
}

function getLabel(nameID) {
	var whatTag;

	if (ie4) {whatTag = "document.all[\"" + nameID + "\"]";}
	if (ns6) {whatTag = "document.getElementById(\"" + nameID + "\")";}
	return whatTag;
}

function changebg(nameID, idNum) {
	//  Change color of previously-selected element to blank
	for (i=0; i <= 2; i++) {
		if (ie4) {tempEl = eval("document.all." + nameID + i);}
			else if (ns6) {tempEl = eval("document.getElementById(\"" + nameID + i + "\")");}
		//  alert (tempEl)
		if ((ie4 || ns6) && tempEl) {
			if ((tempEl.style.backgroundColor == "#ccccff" || tempEl.style.backgroundColor == "rgb(204,204,255)") && (i != idNum)) {
				// alert("i = " + i + "\nidNum = " + idNum);
				tempEl.style.backgroundColor = "";
			}
		}
	}
	return;
}

function whatMin(el, field) {
	var char1 = el.charAt(1);
	if (isNaN(char1)) {wMin = el.charAt(0);}
		else {wMin = el.substring(0,2);}
//	alert ("el = " + el + "\nwMin = " + wMin);
	if (prevGray) {
		var pChar1 = prevGray.charAt(1);
		if (isNaN(pChar1)) {prevMin = prevGray.charAt(0);}
			else {prevMin = prevGray.substring(0,2);}
		if (prevMin != wMin) {
			//  Convert previous gray boxes to no-color
			var pH = getLabel(field+""+prevMin + "h");
			pH = eval(pH);
			pH.style.backgroundColor = "";

			var pR = getLabel(field+""+prevMin + "r");
			pR = eval(pR);
			pR.style.backgroundColor = "";

			var pM = getLabel(field+""+prevMin + "m");
			pM = eval(pM);
			pM.style.backgroundColor = "";

			var pX = getLabel(field+""+prevMin + "x");
			pX = eval(pX);
			pX.style.backgroundColor = "";

			var pC = getLabel(field+""+prevMin + "c");
			pC = eval(pC);
			pC.style.backgroundColor = "";
		}
	}
	prevGray = el;

	//  Convert selected column to gray boxes
	//alert(field+""+wMin + "h");
	pH = getLabel(field+""+wMin + "h");

	pH = eval(pH);
	pH.style.backgroundColor = "#c6c6c6";
	changeScore(pH.id);

	pR = getLabel(field+""+wMin + "r");
	pR = eval(pR);
	pR.style.backgroundColor = "#c6c6c6";
	changeScore(pR.id);

	pM = getLabel(field+""+wMin + "m");
	pM = eval(pM);
	pM.style.backgroundColor = "#c6c6c6";
	changeScore(pM.id);

	pX = getLabel(field+""+wMin + "x");
	pX = eval(pX);
	pX.style.backgroundColor = "#c6c6c6";
	changeScore(pX.id);

	pC = getLabel(field+""+wMin + "c");
	pC = eval(pC);
	pC.style.backgroundColor = "#c6c6c6";
	changeScore(pC.id);

//	el = getLabel(el);
//	el = eval(el);
//	el.style.backgroundColor = "#c6c6c6";

	return;
}

function changeScore(whatID1) {
	var char1, idNum, scoreID1, whatScore, checkBox;

	char1 = whatID1.charAt(1);
	if (isNaN(char1)) {idNum = whatID1.charAt(0);}
		else {idNum = whatID1.substring(0,2);}
	idName = whatID1.charAt(whatID1.length-1);

	// alert("idNum = " + idNum + "\nidName = " + idName);

	scoreID1 = getLabel(idNum + idName);
	scoreID1 = eval(scoreID1);
	var whatScore = scoreID1.innerHTML;
	for (var q=0; q < 3; q++) {
		checkBox = getLabel(idName + q);
		checkBox = eval(checkBox);
		if (whatScore == q) {checkBox.style.backgroundColor = "#ccccff";}
			else {checkBox.style.backgroundColor = "";}
	}

	return;
}

function resetAll() {


	return;
}


/*
//  This code from http://www.woram.com/TESTS/JAVA-08.HTM
function buttonCheck(btn)
{
var browser = navigator.appName.substring (0,4 );
var mouseButton = 0;
if (browser=="Micr") mouseButton=event.button;
else
if (browser=="Nets") mouseButton=btn.which;

if ((browser=="Micr" && mouseButton==4) || (browser=="Nets" && mouseButton==2)) {alert(mouseButton + ". Middle Button");}
else
if ((browser=="Micr" && mouseButton==2) || (browser=="Nets" && mouseButton==3)){alert(mouseButton + ". Secondary Button");}
else
if (mouseButton==1) {alert(mouseButton + ". Primary Button");}
}

function imageCheck()
{
if (document.images)
{for (var pic=0; pic<document.images.length; pic++) document.images[pic].onmousedown = buttonCheck;}
}
window.onload=imageCheck;

// -----------------------------------------------------------
//  This code from http://javascript.internet.com/page-details/no-right-click.html
function right(e) {
if (navigator.appName == 'Netscape' && e.which > 1) return false;
	else if (navigator.appName == 'Microsoft Internet Explorer' && event.button > 1) {
		alert("Sorry, you do not have permission to right click.");
		return false;
	}
return true;
}

document.onmousedown=right;
// document.onmouseup=right;
if (document.layers) window.captureEvents(Event.MOUSEDOWN);
// if (document.layers) window.captureEvents(Event.MOUSEUP);
window.onmousedown=right;
// window.onmouseup=right;
// ////////////////////////////////////////////////////////////////////////
*/






function checkForm() {
	var ktv = document.ktv;
	var height = ktv.height.value;

	if (!checkError(height, "height", "patient's height")) return false;
	return true;
}

function checkError(whatVar, varName, varText) {
	if ((whatVar == "") || (whatVar <= 0) || (isNaN(whatVar))) {
		alert("Please enter the " + varText + ".");
		eval("document.ktv." + varName + ".focus();");
		eval("document.ktv." + varName + ".select();");
		return false;
	}
	return true;
}

function roundNum(thisNum,dec) {
	thisNum = thisNum * Math.pow(10,dec);
	thisNum = Math.round(thisNum);
	thisNum = thisNum / Math.pow(10,dec);
	return thisNum;
}