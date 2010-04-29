window.RichFaces = window.RichFaces || {};

window.RichFaces.FullCalendar = (function() {
	var formatDateParam = function(date) {
		return Math.round(date.getTime() / 1000);
	}
	
	return function(id, ajaxFunc) {
		this.id = id;
		this.ajaxFunc = ajaxFunc;
		var elt = document.getElementById(id);
		if (!elt) {
			//TODO handle missing element
			return ;
		}

		var _this = this;
		var fillCalendarFunction = function(startDate, endDate, callback) {
			var event = {};
			_this.ajaxFunc({} /* stub event */, 
				formatDateParam(startDate), 
				formatDateParam(endDate), 
				function(request, event, data) {
					callback(data);	
				}
			);
		};
		
		jQuery(document).ready(function() {
			
			jQuery(elt).fullCalendar({
			
				editable: true,
				
				events: fillCalendarFunction,
				
				eventDrop: function(event, delta) {
					alert(event.title + ' was moved ' + delta + ' days\n' +
						'(should probably update your database)');
				},
				
				loading: function(bool) {
					var loadingElt = document.getElementById(id + ":loading");
					if (loadingElt) {
						if (bool) {
							jQuery(loadingElt).show();
						} else {
							jQuery(loadingElt).hide();
						}
					}
				}
				
			});
			
		});
	};

}());
	
	
