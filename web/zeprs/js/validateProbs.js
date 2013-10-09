<!-- Begin

     var bCancel = false;

    function validateProblem(form) {
    
        if (bCancel)
      return true;
        else
       return validateRequiredProb(form) && validateMaxLengthProb(form);
   }

    function requiredProb () {
     this.aa = new Array("problemName", "Name of Problem is required.", new Function ("varName", " return this[varName];"));
    }

    function maxlengthProb () {
     this.aa = new Array("commentText", "Comment can not be greater than 1000 characters.", new Function ("varName", "this.maxlength='1000';  return this[varName];"));
     this.ab = new Array("actionPlan", "Action/Plan can not be greater than 1000 characters.", new Function ("varName", "this.maxlength='1000';  return this[varName];"));
    }


function validateMaxLengthProb(form) {
                var isValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oMaxLength = new maxlengthProb();
                for (x in oMaxLength) {
                    var field = form[oMaxLength[x][0]];

                    if (field.type == 'text' ||
                        field.type == 'textarea') {

                        var iMax = parseInt(oMaxLength[x][2]("maxlength"));
                        if (field.value.length > iMax) {
                            if (i == 0) {
                                focusField = field;
                            }
                            fields[i++] = oMaxLength[x][1];
                            isValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return isValid;
            }
function validateRequiredProb(form) {
                var isValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oRequired = new requiredProb();
                for (x in oRequired) {
                	var field = form[oRequired[x][0]];

                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'file' ||
                        field.type == 'select-one' ||
                        field.type == 'radio' ||
                        field.type == 'password') {

                        var value = '';
						// get field's value
						if (field.type == "select-one") {
							var si = field.selectedIndex;
							if (si >= 0) {
								value = field.options[si].value;
							}
						} else {
							value = field.value;
						}

                        if (trim(value).length == 0) {

	                        if (i == 0) {
	                            focusField = field;
	                        }
	                        fields[i++] = oRequired[x][1];
	                        isValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return isValid;
            }

            // Trim whitespace from left and right sides of s.
            function trim(s) {
                return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
            }


//End -->