/*

JavaScript:  Pregnancy Calculator
February 28, 1999

This calculator was created by Charles Hu for the Medical College of
Wisconsin General Internal Medicine Clinic.  This calculator may not be
copied without consent from the author.  Please contact him at
chuckhu@hotmail.com or chuckhu@mcw.edu

*/

var ie4 = (document.all && !document.getElementById)? true : false;
var ns6 = (document.getElementById)? true : false;

var dayName = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
var monName = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

	//  Create array of maximum days
	var maxDays = new Array(12);

	maxDays[0] = 31;	// January has 31 days
	maxDays[1] = 28;
	maxDays[2] = 31;
	maxDays[3] = 30;
	maxDays[4] = 31;
	maxDays[5] = 30;
	maxDays[6] = 31;
	maxDays[7] = 31;
	maxDays[8] = 30;
	maxDays[9] = 31;
	maxDays[10] = 30;
	maxDays[11] = 31;

/*

Get values from LMP date dropdowns, calculates value for lmpfield (field127)

*/
function whatDay(form,isEdd,lmpfield) {

    var lmpReliability = eval(getLabel("selectfield126")).value;
    var month = eval(getLabel("month0")).selectedIndex -1;
    var date = eval(getLabel("date0")).selectedIndex;
	var year = eval(getLabel("year0")).selectedIndex;
	var yearVal = eval(getLabel("year0")).options[year].text;
	var today = new Date();
	thisYear = today.getYear();

    //  Year 2000 test
	if (thisYear < 300) {thisYear = thisYear + 1900}
	
	//  Change dates selection to reflect maxDays in selected month
	if (month == 1) {		//  February
		if ((yearVal / 4) == Math.floor(yearVal/4)) {
			if ((yearVal / 100) == Math.floor(yearVal/100)) {
				if ((yearVal / 400) == Math.floor(yearVal/400)) {newDays = 29;}
				else {newDays = 28;}
			} else {newDays = 29;}
		} else {newDays = 28;}
	} else {newDays = maxDays[month]}

	//daysObject = form.date;
	daysObject = eval(getLabel("date0"))
	curDays = daysObject.length-1;
	//alert("date: " + date + " year: " + year +  " curDays: " + curDays + " newDays: " + newDays);
	if (curDays > newDays) {
    	for (i=0; i<(curDays - newDays); i++) {
			daysObject.options[daysObject.options.length - 1] = null
		}
		// if (date > newDays) {daysObject.selectedIndex = (daysObject.options.length - 1);}
	}
	if (newDays > curDays) {
		for (i=curDays; i < newDays; i++) {
		    len = daysObject.options.length-1
		    // alert("i" + i + "len: " + len);
			newOption = new Option(len + 1);
			daysObject.options[i+1] = newOption;
		}
	}

	//  What year was selected?
	if (year == 1) {year = thisYear - 1;}	// Last year
		else {
			if ((isEdd == 2) && (year == 2)) {year = thisYear + 1}	// Next year
				else {year = thisYear;}		// This year
		}

	//  The choices for EDD year are off by 1, compared to LMP choices
	if (isEdd == 1) {year = year + 1}

	var whatDate = new Date(monName[month] + " " + date + ", " + year + " 12:00:00");
    // alert("chosen date: " + whatDate + " \nToday: " + today);
    if (whatDate > today) {
        alert("LMP date is in the future - please change to a date in the past.");
    }
    //fill out the lmpfield for the zeprs form
    // some formatting of month value

    if (month < 9)
    {
        var zeprslmpmonth = "0"+(month+1);
    }
    else
    {
        var zeprslmpmonth = (month+1);
    }
    if (date < 10)
    {
        var zeprslmpdate = "0"+(date);
    }
    else
    {
        var zeprslmpdate = (date) ;
    }
    var zeprslmpdate = year + "-" + zeprslmpmonth+ "-" + zeprslmpdate;
    //lmpfield.value = zeprslmpdate;
    eval(getLabel(lmpfield)).value = zeprslmpdate;

	var eddNum;
	if (form.name == "DueDate") {eddNum = 0} else
		if (form.name == "eddForm") {eddNum = 1} else
		if (form.name == "weeksPreg") {eddNum = 2}

	if (isEdd == 2) {
		return true
	} else {
		eval(getLabel("calcYet")).value = 0
			//  Since the LMP or EDD is being changed, we can't calculate EGA yet.
	}

    eval(getLabel("calcYet")).value = ""
	return true
}

/*
Gets values from the LMP date dropdowns;
sends them to display() function for display of EDD
*/

function dueDate(form, lmpField, egaField, eddField, eddNice) {

    var LMPmonth = eval(getLabel("month0")).selectedIndex - 1;
    var LMPdate = eval(getLabel("date0")).selectedIndex;
    var LMPyear = eval(getLabel("year0")).selectedIndex;

    if (LMPyear == -1) {
        alert("Please select a year.");
        form.year.focus();
        return false;
    }

    eval(getLabel("calcYet")).value = 1;
    //  Now that we're calculating EDD, we may also
    //  calculate EGA later

    var today = new Date();
    var field1 = eval(getLabel("field1")).value;

    if (field1 != "") {
        var dvals = field1.split("-");
        var year = dvals[0];
        var month = dvals[1];
        var day = dvals[2];
        today.setFullYear(year);
        today.setMonth(month - 1);
        today.setDate(day);
    }

    thisYear = today.getYear();
    //  Year 2000 test
    if (thisYear < 300) {
        thisYear = thisYear + 1900
    }

    //  What year was selected?
    if (LMPyear == 1) {
        LMPyear = thisYear - 1;
        // Last year
    } else {
        LMPyear = thisYear;
        // This year
    }

    if (!checkDate(LMPmonth, LMPdate, LMPyear)) {
        alert("dueDate function: " + monName[LMPmonth] + " has " + maxDays[LMPmonth] +
              " days.");
        form.date.focus();
        return false;
    }

    var lmp = new Date(monName[LMPmonth] + " " + LMPdate + ", " +
                       LMPyear + " 12:00:00");

    var lmpTime = lmp.getTime();

    eval(getLabel("lmpdate")).value = lmp.getTime();
    //var concep = 14;	// conception occurs at 2 weeks (14 days)
    //var first = 84;	// end of first trimester at 12 weeks
    //var second = 189;	// end of second trimester
    var edd = 280;
    // estimated due date

    msPerDay = 24 * 60 * 60 * 1000;
    // Number of milliseconds per day
    var elapsed = (today.getTime() - lmp.getTime()) / msPerDay;
    elapsed = Math.round(elapsed);
    // alert("field1: " + field1 + " today's date: " + (today.getMonth() + 1) + "/" + today.getDate() + " elapsed: " + elapsed);
    if (elapsed > 315) {
        alert("The value entered for LMP was > 45 weeks ago - please re-enter.");
    } else {
        //  The '1' at the third position indicates this is EDD
        //alert("lmp.getTime(): " + lmp.getTime() + " edd: " + edd + " elapsed: " + elapsed + " lmpField: " + lmpField + " egaField: " + egaField + " eddField: " + eddField + " eddNice: " + eddNice );
        displayEGA(lmpTime, edd, 1, 0, lmpField, "Date", egaField, eddField, eddNice);
        //displayEGA(lmpTime, edd, 1, 0);
    }
    return true;
}

/*
Calculates EDD and displays it
*/
function displayEGA(whatDate,numDays,isEdd,isLMP,lmpField,queryType,egaField,eddField,eddNice) {
	var today = new Date();
    var field1 = eval(getLabel("field1")).value;

    if (field1 != "") {
        var dvals = field1.split("-");
        var year = dvals[0];
        var month = dvals[1];
        var day = dvals[2];
        today.setFullYear(year);
        today.setMonth(month-1);
        today.setDate(day);
    }
    if (queryType == "Date") {
	var lmp = new Date(whatDate);
        // /alert ("whatDate: "+whatDate+" numDays"+numDays+" isEdd: "+isEdd+" isLMP: "+isLMP+ " lmpField: " + lmpField + " egaField: " + egaField + " eddField: " + eddField + " eddNice: " + eddNice );
    // var futureDate = lmp.getTime() + (numDays * 24*60*60*1000);
	} else {
        // alert("non-date");
    var egaSeconds = today.getTime() - (whatDate * 24*60*60*1000);
	var lmp = new Date(egaSeconds);
	var egalmpyear = "20" + String(lmp.getYear()).substring(1,3);
	var egalmpmonth = lmp.getMonth()+1;
	if (egalmpmonth <10) {
	egalmpmonthFixed = "0" + egalmpmonth;
	} else {
	egalmpmonthFixed = egalmpmonth;
	}
	if (lmp.getDate() <10) {
	egalmpdate = "0" + lmp.getDate();
	} else {
	egalmpdate = lmp.getDate();
	}
	eval(getLabel("lmpdate")).value = lmp.getTime();
	var zeprslmpdate = egalmpyear + "-" + egalmpmonthFixed+ "-" + egalmpdate;
    eval(getLabel(lmpField)).value = zeprslmpdate;
	}
	var futureDate = lmp.getTime() + (numDays * 24*60*60*1000);

	//  convert lmp to milliseconds, and add appropriate msec
    if (queryType == "Date") {
	var future = new Date(whatDate);
    } else {
    var future = new Date();
    }
	future.setTime(futureDate);  // convert from msec to actual date
	future.setHours(12);		// set the time to 12:00 pm

	var futureDay = dayName[future.getDay()];
	var futureMonth = monName[future.getMonth()];
	var futureYear = future.getYear();
    // alert(futureDay+"/"+futureMonth+"/"+futureYear);
	if (isEdd == 1) {		// Change the EDD selection boxes
        eval(getLabel("month1")).value = future.getMonth()+1;
         // eval(getLabel("date1")).value = future.getDate() - 1;
        eval(getLabel("date1")).value = future.getDate();
         //eval(getLabel("year1")).value = yearChoice;
        // eval(getLabel("year1")).value = futureYear;
        eval(getLabel("year1")).value = "20" + String(futureYear).substring(1,3);
        eval(getLabel("eddDate")).value = future.getTime();
        displayEDD(eddField, eddNice)
	}
	//alert("eddField = "+eddFieldval+" eddFieldval = " + eval(getLabel(eddFieldval)));

	//  Calculate # weeks pregnant, based on today's date
	if ((isEdd == 1) || (isLMP == 1)) {
		// First, reset all values in the purple box

        eval(getLabel("weeks")).value = "";
		today.setHours(13);

        /* Since LMP is set to 12:00 noon, we must set
           today's time temporarily to 1:00 pm, so that
           if today date = LMP date, then
           today's time is GREATER than LMP time
        */

		var todaydate = today.getTime();
		//var lmpdate = document.DueDate.theDate.value;
		var lmpdate = eval(getLabel("lmpdate")).value;
		//var edd = document.eddForm.theDate.value;
		var edd = eval(getLabel("eddDate")).value;

		edd = Number(edd) + 10 *7*24*60*60*1000;
				//  Allow for up to 50 weeks EGA

		if ((todaydate > lmpdate) && 
			(todaydate < edd)) {
				// Check that today's date is BETWEEN 
				// the LMP and EDD
                // Don't do this when using the EGA dropdown for ultrasound
                if (queryType == "Date") {
                 eval(getLabel("weeks")).value = calcEGA(lmpdate,todaydate);
                 eval(getLabel(egaField)).value = calcEGAdays(lmpdate,todaydate);
                 } else {
                 eval(getLabel("weeks")).value = calcEGA(lmpdate,todaydate);
                 }
		    }
		return true
	}		

	//  Year-2000 compliance test
	if (futureYear < 300) {
		futureYear = futureYear + 1900
	}

	var futureDisplay = futureDay + ", " + futureMonth + " " +
		future.getDate() + ", " + futureYear;

	return futureDisplay;
}




function checkDate(month,day,year) {
	//  Reset February's "maxdays"
	maxDays[1] = 28;

	//  Check February leap year
	//  Years divisible by 100 are NOT leap years (1700, 1800, 1900), 
	//  	except years divisible by 400 (2000, 2400) are leap years.

	if ((month == 1) && ((year % 4) == 0)) {
		if (((year % 100) == 0) && ((year % 400) != 0)) {
			maxDays[1] = 28;
		} else {
			maxDays[1] = 29;
		}
	}

	if (day > maxDays[month]) {
	// alert("month: " + month + " day: " + day +" maxDays[month]: " + maxDays[month]);
		return false;
	}

	return true;
}



function calcEGA(lmpDate,desDate) {
	var daysEGA = (desDate - lmpDate) / (1000*60*60*24);
	var EGA = Math.floor(daysEGA / 7);
	var EGAfrac = Math.round(daysEGA % 7);
		//  Daylight Savings Time (DST) makes this calculation
		//  veer slightly off from being integer.

	if (EGAfrac == 7) {	//  Another correction for DST
		EGAfrac = 0;
		EGA = EGA + 1;
	}

	if (EGAfrac == 0) {
		return EGA
	}
		
	EGA = EGA + " " + EGAfrac + "/7";
	//alert("lmpDate: "+lmpDate+" desDate:"+desDate+" EGA:"+EGA);
	return EGA
}

function calcEGAweeksDays(lmpDate,desDate) {
	var daysEGA = (desDate - lmpDate) / (1000*60*60*24);
	var EGA = Math.floor(daysEGA / 7);
	var EGAfrac = Math.round(daysEGA % 7);
		//  Daylight Savings Time (DST) makes this calculation
		//  veer slightly off from being integer.

	if (EGAfrac == 7) {	//  Another correction for DST
		EGAfrac = 0;
		EGA = EGA + 1;
	}

	if (EGAfrac == 0) {
		return EGA
	}

	EGA = EGA + " " + EGAfrac + "/7";
	//alert("lmpDate: "+lmpDate+" desDate:"+desDate+" EGA:"+EGA);
	return EGA
}

function calcEGAdays(lmpDate,desDate) {
	var daysEGA = (desDate - lmpDate) / (1000*60*60*24);
	daysEGA = Math.round(daysEGA)
	return daysEGA
}

function weekstoDate(form) {
	if (document.weeksPreg.calcYet.value == 0) {
		alert("Please calculate a \"Last Menstrual Period\" " +
			"or an \"Estimated Due Date\" first.");
		return false
	}

	var add = form.weeks.value;

	if (add.indexOf("/") != -1) {		// Is "add" a fraction?
		add = evalFrac(add);
		if (add == false) {
			return false
		}
	}

	if (add == "pi") {
		alert("Pi = 3.14159265358979323...  " +
		"This calculator was created by Charles Hu.");
	}

        if ((add == "") || (isNaN(add)) || (add < 0) || (add > 50)) {
                alert("WARNING: Please enter a valid number of weeks.");
                form.weeks.value = "";
                form.weeks.focus();
                return false
        }

	//  Adding milliseconds
	//var lmpDate = Number(document.DueDate.theDate.value);
	var lmpDate = Number(eval(getLabel("lmpdate")));
	var egaMsec = lmpDate + (add * 7*24*60*60*1000);

	var today = new Date();
	var todayYr = today.getYear();

	//  Convert the EGA to a date
	var ega = new Date();
	ega.setTime(egaMsec);
	var egaYr = ega.getYear();

	if (egaYr < 300) {egaYr = egaYr + 1900}
	if (todayYr < 300) {todayYr = todayYr + 1900}

	var egaYear = (egaYr - todayYr) + 1;

	form.month.selectedIndex = ega.getMonth();
	form.date.selectedIndex = ega.getDate() - 1;
	form.year.selectedIndex = egaYear;
	return true
}



function evalFrac(frac) {
	var slash = frac.indexOf("/");

	if (frac.charAt(slash + 1) != 7) {	// Is denominator 7?
		alert("Weeks are expressed in fractions of 7.");
		document.weeksPreg.weeks.focus();
		return false;
	}

	//  Check numerator
	if ((Number(frac.charAt(slash-1)) > 6) || 
	   //isNaN(chaz) || frac.chuckhu(swish)
	   (isNaN(frac.charAt(slash-1)))) {
		alert("Please enter a proper fraction.");
		document.weeksPreg.weeks.focus();
		return false;
	}


	if ((frac.charAt(slash-2) != " ") && (frac.indexOf("/") != 1))
		{alert("Please enter a proper fraction.");
		document.weeksPreg.weeks.focus();
		return false;
	}


	//  Evaluate the fraction
	var top = Number(frac.charAt(slash-1));
	var fraction = top / 7;


	//  Anything before the fraction?
	if (frac.indexOf("/") == 1) {
		if (fraction == 0) {
			fraction = 0.001
				//  Prevents script from interpreting
				//  this as a 'false' value
		}
		return fraction
	}

	var fullNum = frac.substring(0,frac.indexOf(" "));
	if (isNaN(fullNum)) {
		alert("Please enter a proper number of weeks.");
		document.weeksPreg.weeks.focus();
		return false
	}

	frac = Number(fullNum) + fraction;
		if (frac == 0) {frac = 0.001}
	return frac;
}	



function getLMP(form) {
	var eddMonth = form.month.selectedIndex-1;
	var eddDate = form.date.selectedIndex + 1;
	var eddYear = form.year.selectedIndex;

	if (eddYear == -1) {
		alert("Please select a year.");
		form.year.focus();
		return false;
	}

	document.weeksPreg.calcYet.value = 1;
		//  Now that we're calculating LMP, we may also 
		//  calculate EGA later

	var today = new Date();
	thisYear = today.getYear();

	//  Year 2000 test
	if (thisYear < 300) {
		thisYear = thisYear + 1900
	}

	//  What year was selected?
	
	if (eddYear == 0) {
		eddYear = thisYear;	// This year
	} else {
		eddYear = thisYear + 1;	// Next year
	}


	if (!checkDate(eddMonth,eddDate,eddYear)) {
		alert(monName[eddMonth] + " has " + maxDays[eddMonth] + 
			" days.");
		form.date.focus();
		return false;
	}
	
	var edd = new Date(monName[eddMonth] + " " + eddDate + ", " +
		eddYear + " 12:00:00");

	form.theDate.value = edd.getTime();

	var lmp = -280;		// estimated due date
	//var concep = -266;	// conception occurs at 2 weeks (14 days)
	//var first = -196;	// end of first trimester at 12 weeks
	//var second = -91;	// end of second trimester

	//  The last "1" indicates this is LMP
	displayEGA(edd.getTime(),lmp,0,1,"Date");

	return true;
}


function resetForm() {
	document.DueDate.reset();
	document.eddForm.reset();
	document.weeksPreg.reset();
	return true
}


function dateSelect(Month,dd,Year,isEdd,lmpField) {
var today = new Date();

//
// JavaScript:  Choose Year
//
// we don't need whatDay for edd dropdown
if (isEdd == 0)
{
document.writeln("<select id=\"year" + isEdd + "\" name=\"year\" " +
	"onChange=\"whatDay(this.form," + isEdd + ",'" + lmpField + "');calculateEDD();\">\n");
}
else
{
    document.writeln("<select id=\"year" + isEdd + "\" name=\"year\" onChange=\"displayEDD()\">");
}
//	For dates before the year 2000 (ex. 1999), the year
//	is stored as "xx" (ex. 99).  However, for dates on or
//	after the year 2000 (ex. 2003), the year is stored as
//	"xxxx" (ex. 2003).

if (Year < 300) {
	Year = Year + 1900
	}

    var thisYear = today.getYear() + 1900;
    // IE calcluates date differently.
    if (today.getYear() >= 300) {
        thisYear = today.getYear();
    }

var YearMinus1 = thisYear - 1;

if (isEdd == 2) {Year = Year + 1}  //  This allows for 3 choices

document.writeln("\n<option value=''>--</option>");
// alert("today.getYear(): " + today.getYear() + ", thisYear: " + thisYear);
for (var yrCount = YearMinus1; yrCount < (thisYear +1) ; yrCount++) {
	if (Month !="99" && yrCount == Year) {
		document.writeln("<option selected>" + yrCount + "</option>\n")
	} else {
		document.writeln("<option>" + yrCount + "</option>\n")
	}
}

document.writeln("</select>\n");    

//
// JavaScript:  Choose Month
//

if (isEdd == 0)
{
    document.writeln("<select id=\"month" + isEdd + "\" name=\"month\" onChange=\"whatDay(this.form," + isEdd + ",'" + lmpField + "');calculateEDD();\">");
}
else
{
    document.writeln("<select id=\"month" + isEdd + "\" name=\"month\" onChange=\"displayEDD()\">");
}

//	Select the names for the months

document.writeln("<option value=''>--</option>");

for (var i=0; i < 12; i++) {
	if (i == Month) {
		document.writeln("<option selected>" + monName[i] + "</option>");
	} else {
		document.writeln("<option>" + monName[i] + "</option>");
	}
}

document.writeln("</select>");

//
// JavaScript:  Choose Date
//

// we don't need whatDay for edd dropdown
if (isEdd == 0)
{
document.writeln("<select id=\"date" + isEdd + "\"  name=\"date\" " +
	"onChange=\"whatDay(this.form," + isEdd + ",'" + lmpField + "');calculateEDD();\">\n");
}
else
{
    document.writeln("<select id=\"date" + isEdd + "\" name=\"date\" onChange=\"displayEDD()\">\n");
}

document.writeln("\n<option value=''>--</option>");

for (var d = 1; d < 32; d++) {
	if (d == dd) {
		document.writeln("<option selected>" + dd + "</option>\n");
	} else {
		document.writeln("<option>" + d + "</option>\n");
	}
}

document.writeln("</select>\n ");
return true;
}

function showDate(triNum, dateTri) {
	var whichTri;
	whichTri.innerHTML = dateTri;
	whichTri.style.display = "";

	return;
}

function getLabel(nameID) {
	var whatTag;
	
	if (ie4) {whatTag = "document.all[\"" + nameID + "\"]";}
	if (ns6) {whatTag = "document.getElementById(\"" + nameID + "\")";}
	return whatTag;	
}

function displayEDD(eddField, eddNice) {
    var today = new Date();
	thisYear = today.getYear();
     var month1 = eval(getLabel("month1")).value;
	 var year1 = eval(getLabel("year1")).value;;
	 var eddDate = eval(getLabel("eddDate")).value;
	 var date1 = eval(getLabel("date1")).value;
	 //  What year was selected?
	// if (year1 == 0) {year1 = thisYear - 1;}	// Last year
	// 	else {year1 = thisYear;}		// This year


	//  The choices for EDD year are off by 1, compared to LMP choices
	// year1 = year1 + 1;
	//fill out the zeprsedd field for the zeprs form
    // some formatting of month value
    if (month1 <= 9)
    {
        //var zeprsmonth = "0"+(month1+1)
        var zeprsmonth = "0"+(month1)
    }
    else
    {
        var zeprsmonth = (month1)
    }
    if (date1 <= 9)
    {
        //var zeprsdate = "0"+(date1+1)
        var zeprsdate = "0"+(date1)
    }
    else
    {
        //var zeprsdate = (date1+1)
        var zeprsdate = (date1)
    }
    //var zeprsedddate = zeprsmonth + "/" + zeprsdate+ "/" + year1;
    var zeprsedddate = year1 + "-" + zeprsmonth+ "-" + zeprsdate;
    // eval(getLabel("eddFieldValuelmp")).value = zeprsedddate;
    eval(getLabel(eddField)).value = zeprsedddate;
    //eval(getLabel("eddNicelmp")).innerHTML = zeprsdate + "/" + zeprsmonth + "/" + year1;
    eval(getLabel(eddNice)).innerHTML = zeprsdate + "/" + zeprsmonth + "/" + year1;
}

function updateEGAdisplay(egaField,egaDisplay) {
var egaWeeksValue = eval(getLabel('weeks')).value;
var egaDaysValue = eval(getLabel(egaField)).value;
eval(getLabel(egaDisplay)).innerHTML = egaWeeksValue;
// eval(getLabel('field129')).innerHTML = egaDaysValue;
}

/*
Used for the LMP widget
*/
function calculateEDD() {
    var LMPmonth = eval(getLabel("month0")).selectedIndex;
    var LMPdate = eval(getLabel("date0")).selectedIndex;
    var LMPyear = eval(getLabel("year0")).selectedIndex;
    // alert(" month: " + LMPmonth +  " date: " + LMPdate)
    if (isNumber(LMPdate) && isNumber(LMPmonth) && isNumber(LMPyear)) {
        dueDate(this.form, 'lmpDbFieldlmp', 'dayslmp', 'eddFieldValuelmp', 'eddNicelmp');
        updateEGAdisplay('days', 'displayEGAlmp');
    }
}

function calculateEDDfromEGA(id, lmpField, egaField, egaDisplay, eddField, eddNice) {
    eval(getLabel("calcYet")).value = 1;
    var egaDays = eval(getLabel(id)).value;
    // copy this value to egaField
    eval(getLabel(egaField)).value = egaDays;
    // calculate LMP
    displayEGA(egaDays,"280",1,0,lmpField,"Integer",egaField, eddField, eddNice);
    updateEGAdisplay(egaField, egaDisplay);
}

function checkReliability() {
    var lmpReliability = eval(getLabel("selectfield126")).value;
    var el = document.getElementById("lmpChoice");
    var lmpDatingMethod = eval(getLabel("lmpDatingMethod")).checked;
}

function copyPregDatingValues(lmpDbField,days,eddFieldValue,displayEGA,eddNice) {
    eval(getLabel("lmpDbField")).value =  eval(getLabel(lmpDbField)).value;
    eval(getLabel("days")).value =  eval(getLabel(days)).value;
    eval(getLabel("eddFieldValue")).value =  eval(getLabel(eddFieldValue)).value;
    eval(getLabel('displayEGAMaster')).innerHTML = eval(getLabel(displayEGA)).innerHTML;
    eval(getLabel('eddNiceMaster')).innerHTML = eval(getLabel(eddNice)).innerHTML;
}

function checkLmpValues() {
    var lmpDatingMethod = eval(getLabel("lmpDatingMethod")).checked;
    if (lmpDatingMethod == true) {
        if (eval(getLabel("lmpDbFieldlmp")).value != eval(getLabel("lmpDbField")).value) {
            // alert("Please select Dating Method again - the system needs to re-calculate the EGA.");
            checkReliability();copyPregDatingValues('lmpDbFieldlmp','dayslmp','eddFieldValuelmp','displayEGAlmp','eddNicelmp');
            return true;
        } else {
            return true;
        }
    } else {
        return true;
    }
}