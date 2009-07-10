// Function for testing eventHandlers
var count = 0;
function markEventAsWorkable(eventName) {
	var col = document.getElementById('formID:autoEventHandlersSubviewID:' + eventName + 'ID');	
	if (col != null) {
		col.style.color = 'green';
		col.value = 'PASSED - ' + count;
		count++;
	}
	return true;
}

// Function for testing classes
function markClassAsWorkable(className) {
	if ((className.indexOf('Style', 1) == (className.length - 5))||(className == 'style'))
		var selector = '.' + className + ':' + className;
	else
		var selector = '.' + className + '-' + className;
	var counter = jQuery(selector).length;
	var col = document.getElementById('formID:autoStylesClassesSubviewID:' + className + 'ID');	
	if (col != null) {
		if (counter > 0) {
			col.style.color = 'green';
			col.value = 'PASSED - ' + counter;
		} else {
			var arrJS = jQuery('script');
			var i = 0;
			var found = false;
			if ((className.indexOf('Style', 1) > -1)||(className == 'style'))
				var txtSelector = className + ':' + className;
			else
				var txtSelector = className + '-' + className;
			while ((!found)&&(i < arrJS.length)) {
				if (arrJS[i].text.indexOf(txtSelector) > -1) {
					found = true;
					col.style.color = 'blue';
					col.value = 'PASSED (found in JS)';
				}
				i++;
			}
			if (!found) {
				var eHTML = jQuery('html');
				if (eHTML[0].innerHTML.indexOf(txtSelector) > -1) {
					found = true;
					col.style.color = 'blue';
					col.value = 'PASSED (found in HTML)';
				}
			}
			if (!found) {
				col.style.color = 'red';
				col.value = 'FAILED';
			}
		}
	}
}

// Function for testing messages
function checkMessage() {
	var mes = document.getElementById('formID:messagesID');
	if (mes != null) {
		var mesText = mes.innerHTML;
		if (mesText.indexOf('validator')>-1) {
			var inp = document.getElementById('formID:autoGeneralSubviewID:hiddenValidatorInput');
		} else if (mesText.indexOf('converter')>-1) {
			var inp = document.getElementById('formID:autoGeneralSubviewID:hiddenConverterInput');
		} else if (mesText.indexOf('required')>-1) {
			var inp = document.getElementById('formID:autoGeneralSubviewID:hiddenRequiredInput');
		}
		if (inp != null) {
			inp.value = mesText;
		}
	}
}