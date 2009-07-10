var inputCorrect = false;
var textCorrect = false;
//variables for menu
var	closedItems=new Array(), //define var for items to be stored in cookie
	//name of the cookie for closed elements
	cookieClosedItems=document.title.replace(/\s*|\s*/g,'')+"_closedItems";
	cookieButtons=document.title.replace(/\s*|\s*/g,'')+"_Buttons";
	StateOfButtons=new Array();
	

function showPopup(_popupId) {
	document.getElementById(_popupId).style.display = "block";
	document.getElementById('timeOutDiv').style.display = "block";
	document.getElementById("feedback-maincontainer").style.display = "block";
	document.getElementById("guide_words").style.display = "block";

}

function hidePopup(_popupId, form, iFrame, but,  container) {
	document.getElementById(_popupId).style.display = "none";
	document.getElementById('timeOutDiv').style.display = "none";
	document.getElementById(iFrame).style.display = "none";
	document.getElementById(form).style.display = "block";
	document.getElementById(but).style.display = "inline";
	document.getElementById(container).style.left="30%";
	document.getElementById(container).style.top="20%";
	document.getElementById(container).style.width="500px";
	document.getElementById(container).style.height="440px";
}

function showIFrame(form, iFrame, but,  container){
	document.getElementById(form).style.display = "none";
	document.getElementById(but).style.display = "none";
	document.getElementById(iFrame).style.display = "block";
	document.getElementById(container).style.height="99%";
	document.getElementById(container).style.left="10%";
	document.getElementById(container).style.top="0";
	document.getElementById(container).style.width="80%";
	document.getElementById("guide_words").style.display = "none";
	
}
function fillForm(form){
	document.getElementById(form).attributes['action'].value = 
	document.getElementById(form).attributes['action'].value
	+'&priority='+document.getElementById('priority').value
	+'&summary='+document.getElementById('feedback-summary').value
	+'&description='+document.getElementById('feedback-description').value
	+'&environment='+document.getElementById('feedback-environment').value
	+'&components='+document.getElementById('components').value
	+'&versions='+document.getElementById('versions').value
	+'&customfield_12310031='+document.getElementById('customfield_12310031').value;
}

function submitForm(form, iFrame, but,  container){
	document.getElementById(form).submit();
	showIFrame(form, iFrame, but,  container);
}

function setFieldFlag(type, flag){
	if(type=="textarea"){
		textCorrect = flag;
	}else if(type=="text"){
		inputCorrect = flag;
	}
}
function countLeft(fieldToCheck, count, max) {
	var field = document.getElementById(fieldToCheck);
	var left = 'none';
	var char_count = field.value.length;
	var fullStr = field.value + " ";
	var initial_whitespace_rExp = /^[^A-Za-z0-9]+/gi;
	var left_trimmedStr = fullStr.replace(initial_whitespace_rExp, "");
	var non_alphanumerics_rExp = rExp = /[^A-Za-z0-9]+/gi;
	var cleanedStr = left_trimmedStr.replace(non_alphanumerics_rExp, " ");
	var splitString = cleanedStr.split(" ");
	var word_count = splitString.length -1;
	if (fullStr.length <2) {
		word_count = 0;
	}
	if (field.value.length > max){
		field.value = field.value.substring(0, max);
	}else if(count != "none"){
		left = document.getElementById(count);
		if(navigator.appName=="Microsoft Internet Explorer"){
			left.innerText = max - field.value.length;
		}else{
			left.textContent = max - field.value.length;
		}
		
	}
	if (word_count >= 1){
		setFieldFlag(field.type, true);
	}else{
		setFieldFlag(field.type, false);
	}
	if(inputCorrect && textCorrect){
		document.getElementById("feedback-submit").disabled=false;
		document.getElementById("feedback-submit").style.color="#415973";
	}else{
		document.getElementById("feedback-submit").disabled=true;
		document.getElementById("feedback-submit").style.color="#999";
	}
}


function init(input, textarea){
	document.getElementById("feedback-submit").disabled=true;
	countLeft(input, "left", 255);
	countLeft(textarea, "none", 500);
}

$(document).ready(function () {
	var togglers = $("span.expand_collapse_toc:contains('+')");
	if(togglers.length != 0){
	    $("#expand_collapse").toggle(
	      function () {
		$("span.expand_collapse_toc:contains('+')").each(function (i, node) {			
			toc.expand($(node)[0]);
		});
		$(this).html('collapse all');
		$(this).css('background', 'url(images/arrowsExpand.png) no-repeat scroll 0 4px');
		StateOfButtons[1]="expand";
		jQuery.cookie(cookieButtons, StateOfButtons, {  expires: 365 });		
	      },
	      function () {
	       $("span.expand_collapse_toc:contains('-')").each(function (i, node) {			
			toc.collapse($(node)[0]);
			});
			StateOfButtons[1]="collapse";
			
		  
			$(this).html('expand all');
			$(this).css('background', 'transparent url(images/arrowsCollapse.png) no-repeat scroll 0 4px');
			jQuery.cookie(cookieButtons, StateOfButtons, {  expires: 365 });
	      });
	}else{
	      $("#expand_collapse").css('display', 'none');
      	}
      	
      	$(".arrowWrapper a").toggle(
	      function () {
		var table = $(this).parents().get(4);
		$(table).children('tbody').hide();
		$(this).children('img').attr('src', 'images/plus.gif');
	      },
	      function () {
		var table = $(this).parents().get(4);
		$(table).children('tbody').show();
		$(this).children('img').attr('src', 'images/minus.gif');
       });       
       
	var target=new Object();
	var itext=" ";
	if (jQuery.cookie) {
		
		  /*if no cookie called "closedItems" exists, then create one with
		  elements you want hidden by default.
		  '1' is given as first value to prevent cookie from being
		  deleted if it contains no ids*/
		//read cookie for closed elements
		if (!jQuery.cookie(cookieClosedItems)) {
			jQuery.cookie(cookieClosedItems, 'start_cookie_file, collapse', {  expires: 365 });
		}
		//if cookie called "closedItems" exists
		if (jQuery.cookie(cookieClosedItems)) {


			//split cookie into array
			closedItems = jQuery.cookie(cookieClosedItems).split(',');

			//iterate through array and expand each element within it
			for (var i = 0; i < closedItems.length; ++i) {
																	
				target=jQuery("a[href='"+closedItems[i]+"']")[0];							
				if(target){
					target=target.parentNode.previousSibling.previousSibling;
				 	toc.show(toc.findDD(target))
					toc.hide(target);
					toc.show(target.nextSibling);					
				 }
			}		
		}
		//read cookie for buttons
		if (!jQuery.cookie(cookieButtons)) {
			jQuery.cookie(cookieButtons, 'start_cookie_file', {  expires: 365 });
		}
		if (jQuery.cookie(cookieButtons)) {


			//split cookie into array
			StateOfButtons = jQuery.cookie(cookieButtons).split(',');
			idButtons="#expand_collapse"
			

			//iterate through array and expand each element within it
			if(StateOfButtons[1])
			{
				if(StateOfButtons[1]=="expand"){
					$(idButtons).html('collapse all')
								.css('background', 'url(images/arrowsExpand.png) no-repeat scroll 0 4px')
								.one("click", function(){
								$("span.expand_collapse_toc:contains('-')").each(function (i, node) {			
									toc.collapse($(node)[0]);
									});
									StateOfButtons[1]="collapse";									
									jQuery.cookie(cookieButtons, StateOfButtons, {  expires: 365 });
									$(this).html('expand all');
									$(this).css('background', 'transparent url(images/arrowsCollapse.png) no-repeat scroll 0 4px');
									
								});
	 							
      							

				}
				if(StateOfButtons[1]=="collapse")
				{
					$(idButtons).html('expand all')
								.css('background', 'transparent url(images/arrowsCollapse.png) no-repeat scroll 0 4px');
				}
			}	
		}
		
		
	}
	
      	
});
/*
$(document).ready(function () {
	$("#search").focus(function () {
		$("#search").val('');
	});
	$("#search_button").click(function () {
	  $("div.time_out_div").css('display','block');	
	  $("div.time_out_div").text('Please, wait...');
		setTimeout(searchElements,0);
	});
});


function searchElements(){
	var patt=new RegExp($("#search").val().toString(),"i", "g");	
	$("div.toc a").each(function (i, el) {
			var tocEl = $(el).text();		
			var dt = $(el).parents().get(1);
			if(patt.test(tocEl)==true){
				$(el).css('background-color','#B6CBE7');
				$("span.expand_collapse_toc:contains('+')").each(function (i, node) {			
					toc.expand($(node)[0]);
				});
			}else{
				$(el).css('background','none');
				//$(dt).children("span.expand_collapse_toc:contains('-')").each(function (i, node) {			
				//	toc.collapse($(node)[0]);
				//});
			}
		});
	$("div.time_out_div").css('display','none');
  $("div.time_out_div").text('');
}
*/

function dbToggle(node, expandText, collapseText) {
	var dt = node.parentNode;
	if (dt.nodeName.toLowerCase() == 'dt') {
		var dd =  dt.nextSibling;
		
		if (dd && dd.nodeName.toLowerCase() == 'dd') {
			
			if (dd.style && dd.style.display == 'none') {
				dd.style.display = '';
				node.innerHTML = collapseText;
			} else {
				dd.style.display = 'none';
				node.innerHTML = expandText;
			}
		
		}
		
	}
	
}


var toc = {
	expand: function(node) {
		toc.show(toc.findDD(node))
		toc.hide(node);
		toc.show(node.nextSibling);
		toc.updateArray(node.nextSibling.nextSibling.getElementsByTagName('*'), "expand");
	}, 
	collapse : function(node) {
		toc.hide(toc.findDD(node))
		toc.hide(node);
		toc.show(node.previousSibling);
		toc.updateArray(node.nextSibling.getElementsByTagName('*'), "collapse");
	}, 
	
	findDD : function(node) {
		return node.parentNode.nextSibling;
		//return node.parent().next();
	},
	
	hide : function(node) {
		node.style.display = "none";
		//node.attr("style","display: none ;");
		
	},
	show : function(node) {
		node.style.display = "";
		//node.attr("style","display: ;");
	},
	updateArray : function(node, collapse_expand) {
	
		//get id of element clicked for adding to closed items array
		var eId = jQuery(node).attr("href");

		//look for element in cookie array
		var arrPos = jQuery.inArray(eId, closedItems);
		//arrPos=-1;

		if (arrPos == -1 ) {

			//element is not in array, so add it
			if(collapse_expand!="collapse") closedItems.push(eId);

		} else {

			//element is in array, so remove it
			if(collapse_expand!="expand") closedItems.splice(arrPos, 1);

		}

		//update cookie
		jQuery.cookie(cookieClosedItems, closedItems, {  expires: 365 });		
	}
	
};


