function getDateVisit(pos) {
var dateVisit = document.getElementById("dateVisit" + pos);
    dateVisitValue = dateVisit.value;
    return dateVisitValue;
}

function isClosed() {
var isClosedElement = document.getElementById("is_closed");
    if (isClosedElement.checked == true) {
    return true
    }
}

function insertInputField(remoteClass, script, className, propertyName, column, field, patientId, pregnancyId, user, siteId, formId) {
    //if (isClosed()==true) {
    //    alert("This chart is closed - no data entry permitted.");
   // } else {
        var dateVisitValue = getDateVisit(column);

        var item = document.getElementById(field);
        itemValue = item.value;
        // alert(script);
        // alert("itemValue" + itemValue);
        eval(remoteClass).insertValue(script, "string",itemValue, column, className, propertyName, patientId, pregnancyId, user, siteId, dateVisitValue, formId)
   // }
}

function insertSelectField(remoteClass, script, className, propertyName, column, field, patientId, pregnancyId, user, siteId, formId) {
    //if (isClosed()==true) {
    //    alert("This chart is closed - no data entry permitted.");
   // } else {
        var dateVisitValue = getDateVisit(column);
        var item = document.getElementById(field);
        itemValue = item[item.selectedIndex].value
        // alert("remoteClass: " + remoteClass + ", column: " + column + ", field: " + field);
        eval(remoteClass).insertValue(script, "enum", itemValue, column, className, propertyName, patientId, pregnancyId, user, siteId, dateVisitValue, formId)
   // }
}

function insertSelectStringField(remoteClass, script, className, propertyName, column, field, patientId, pregnancyId, user, siteId, formId) {
    //if (isClosed()==true) {
    //    alert("This chart is closed - no data entry permitted.");
   // } else {
        var dateVisitValue = getDateVisit(column);
        var item = document.getElementById(field);
        itemValue = item[item.selectedIndex].value
        eval(remoteClass).insertValue(script, "string",itemValue, column, className, propertyName, patientId, pregnancyId, user, siteId, dateVisitValue)
   // }
}

function insertCheckboxField(remoteClass, script, className, propertyName, column, field, patientId, pregnancyId, user, siteId, formId) {
    //if (isClosed()==true) {
    //    alert("This chart is closed - no data entry permitted.");
   // } else {
        var dateVisitValue = getDateVisit(column);
        var item = document.getElementById(field);
        if (item.checked == true)
        {
        itemValue = true;
        } else {
        itemValue = false;
        }
        eval(remoteClass).insertValue(script, "string",itemValue, column, className, propertyName, patientId, pregnancyId, user, siteId, dateVisitValue, formId)
   // }
}

