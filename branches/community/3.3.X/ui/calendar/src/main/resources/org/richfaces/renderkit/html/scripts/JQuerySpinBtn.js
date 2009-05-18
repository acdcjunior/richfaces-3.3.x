/* SpinButton control
 *
 * Adds bells and whistles to any ordinary textbox to
 * make it look and feel like a SpinButton Control.
 *
 * Originally written by George Adamson, Software Unity (george.jquery@softwareunity.com) August 2006.
 * - Added min/max options
 * - Added step size option
 * - Added bigStep (page up/down) option
 *
 * Modifications made by Mark Gibson, (mgibson@designlinks.net) September 2006:
 * - Converted to jQuery plugin
 * - Allow limited or unlimited min/max values
 * - Allow custom class names, and add class to input element
 * - Removed global vars
 * - Reset (to original or through config) when invalid value entered
 * - Repeat whilst holding mouse button down (with initial pause, like keyboard repeat)
 * - Support mouse wheel in Firefox
 * - Fix double click in IE
 * - Refactored some code and renamed some vars
 *
 * Tested in IE6, Opera9, Firefox 1.5
 * v1.0  11 Aug 2006 - George Adamson	- First release
 * v1.1     Aug 2006 - George Adamson	- Minor enhancements
 * v1.2  27 Sep 2006 - Mark Gibson		- Major enhancements
 * v1.3a 28 Sep 2006 - George Adamson	- Minor enhancements
 * rf1.3a 15 Nov 2007 - Pavel Yaschenko - some changes
 
 Sample usage:
 
	// Create group of settings to initialise spinbutton(s). (Optional)
	var myOptions = {
					min: 0,						// Set lower limit.
					max: 100,					// Set upper limit.
					step: 1,					// Set increment size.
					spinClass: mySpinBtnClass,	// CSS class to style the spinbutton. (Class also specifies url of the up/down button image.)
					upClass: mySpinUpClass,		// CSS class for style when mouse over up button.
					downClass: mySpinDnClass	// CSS class for style when mouse over down button.
					}
 
	$(document).ready(function(){

		// Initialise INPUT element(s) as SpinButtons: (passing options if desired)
		$("#myInputElement").SpinButton(myOptions);

	});
 
 */
var sbjQuery = oldJQuery;
sbjQuery.fn.SpinButton = function(cfg){
	return this.each(function(){

		// Apply specified options or defaults:
		// (Ought to refactor this some day to use $.extend() instead)
		this.spinCfg = {
			//min: cfg && cfg.min ? Number(cfg.min) : null,
			//max: cfg && cfg.max ? Number(cfg.max) : null,
			min: cfg && !isNaN(parseFloat(cfg.min)) ? Number(cfg.min) : null,	// Fixes bug with min:0
			max: cfg && !isNaN(parseFloat(cfg.max)) ? Number(cfg.max) : null,
			step: cfg && cfg.step ? Number(cfg.step) : 1,
			page: cfg && cfg.page ? Number(cfg.page) : 10,
			upClass: cfg && cfg.upClass ? cfg.upClass : 'up',
			downClass: cfg && cfg.downClass ? cfg.downClass : 'down',
			reset: cfg && cfg.reset ? cfg.reset : this.value,
			delay: cfg && cfg.delay ? Number(cfg.delay) : 500,
			interval: cfg && cfg.interval ? Number(cfg.interval) : 100,
			_btn_width: 20,
			_btn_height: 12,
			_direction: null,
			_delay: null,
			_repeat: null,
			
			digits: cfg && cfg.digits ? Number(cfg.digits) : 1			
		};
		
		this.adjustValue = function(i){
			var v = this.value.toLowerCase();
			if (v=="am") 
			{
				this.value="PM";
				return; 
			}
			else if (v=="pm") {
				this.value="AM";
				return;
			} 
			v = (isNaN(this.value) ? this.spinCfg.reset : Number(this.value)) + Number(i);
			if (this.spinCfg.min !== null) v = (v<this.spinCfg.min ? (this.spinCfg.max != null ? this.spinCfg.max : this.spinCfg.min) : v);
			if (this.spinCfg.max !== null) v = (v>this.spinCfg.max ? (this.spinCfg.min != null ? this.spinCfg.min : this.spinCfg.max) : v);

			var value = String(v);
			while (value.length<this.spinCfg.digits) value="0"+value;
			
			this.value = value;
		};
		
		sbjQuery(this)
//		.addClass(cfg && cfg.spinClass ? cfg.spinClass : 'spin-button')
//		
//		.mousemove(function(e){
//			// Determine which button mouse is over, or not (spin direction):
//			var x = e.pageX || e.x;
//			var y = e.pageY || e.y;
//			var el = e.target || e.srcElement;
//			var direction = 
//				(x > coord(el,'offsetLeft') + el.offsetWidth - this.spinCfg._btn_width)
//				? ((y < coord(el,'offsetTop') + this.spinCfg._btn_height) ? 1 : -1) : 0;
//			
//			if (direction !== this.spinCfg._direction) {
//				// Style up/down buttons:
//				switch(direction){
//					case 1: // Up arrow:
//						sbjQuery(this).removeClass(this.spinCfg.downClass).addClass(this.spinCfg.upClass);
//						break;
//					case -1: // Down arrow:
//						sbjQuery(this).removeClass(this.spinCfg.upClass).addClass(this.spinCfg.downClass);
//						break;
//					default: // Mouse is elsewhere in the textbox
//						sbjQuery(this).removeClass(this.spinCfg.upClass).removeClass(this.spinCfg.downClass);
//				}
//				
//				// Set spin direction:
//				this.spinCfg._direction = direction;
//			}
//		})
//		
//		.mouseout(function(){
//			// Reset up/down buttons to their normal appearance when mouse moves away:
//			sbjQuery(this).removeClass(this.spinCfg.upClass).removeClass(this.spinCfg.downClass);
//			this.spinCfg._direction = null;
//		})
		
//		.mousedown(function(e){
//			if (this.spinCfg._direction != 0) {
//				// Respond to click on one of the buttons:
//				var self = this;
//				var adjust = function() {
//					self.adjustValue(self.spinCfg._direction * self.spinCfg.step);
//				};
//			
//				adjust();
//				
//				// Initial delay before repeating adjustment
//				self.spinCfg._delay = window.setTimeout(function() {
//					adjust();
//					// Repeat adjust at regular intervals
//					self.spinCfg._repeat = window.setInterval(adjust, self.spinCfg.interval);
//				}, self.spinCfg.delay);
//			}
//		})
//		
//		.mouseup(function(e){
//			// Cancel repeating adjustment
//			window.clearInterval(this.spinCfg._repeat);
//			window.clearTimeout(this.spinCfg._delay);
//		})
//		
//		.dblclick(function(e) {
//			if (sbjQuery.browser.msie)
//				this.adjustValue(this.spinCfg._direction * this.spinCfg.step);
//		})
		
		.keydown(function(e){
			// Respond to up/down arrow keys.
			switch(e.keyCode){
				case 38: this.adjustValue(this.spinCfg.step);  break; // Up
				case 40: this.adjustValue(-this.spinCfg.step); break; // Down
				case 33: this.adjustValue(this.spinCfg.page);  break; // PageUp
				case 34: this.adjustValue(-this.spinCfg.page); break; // PageDown
			}
		})

		.bind("mousewheel", function(e){
			// Respond to mouse wheel in IE. (It returns up/dn motion in multiples of 120)
			if (e.wheelDelta >= 120)
				this.adjustValue(this.spinCfg.step);
			else if (e.wheelDelta <= -120)
				this.adjustValue(-this.spinCfg.step);
			
			e.preventDefault();
		})
		
		.change(function(e){
			this.adjustValue(0);
		});
		
		var self = this;
		
		var btnUp = $(this.id + 'BtnUp');
		sbjQuery(btnUp)
			.mousedown(function(e){
				// Respond to click on one of the buttons:
				var adjust = function() {
					self.adjustValue(self.spinCfg.step);
				};
			
				adjust();
				
				// Initial delay before repeating adjustment
				self.spinCfg._delay = window.setTimeout(function() {
					adjust();
					// Repeat adjust at regular intervals
					self.spinCfg._repeat = window.setInterval(adjust, self.spinCfg.interval);
				}, self.spinCfg.delay);
				self.spinCfg._repeater = true;
				return false;
			})
			
			.mouseup(function(e){
				// Cancel repeating adjustment
				self.spinCfg._repeater = false;
				window.clearInterval(self.spinCfg._repeat);
				window.clearTimeout(self.spinCfg._delay);
			})
			
			.dblclick(function(e) {
				if (sbjQuery.browser.msie)
					self.adjustValue(self.spinCfg.step);
			})
			.mouseout(function(e){
				// Cancel repeating adjustment
				if (self.spinCfg._repeater)
				{
					self.spinCfg._repeater = false
					window.clearInterval(self.spinCfg._repeat);
					window.clearTimeout(self.spinCfg._delay);
				}
			});
		
		var btnDown = $(this.id + 'BtnDown');
		sbjQuery(btnDown)
			.mousedown(function(e){
				// Respond to click on one of the buttons:
				var adjust = function() {
					self.adjustValue(-self.spinCfg.step);
				};
			
				adjust();
				
				// Initial delay before repeating adjustment
				self.spinCfg._delay = window.setTimeout(function() {
					adjust();
					// Repeat adjust at regular intervals
					self.spinCfg._repeat = window.setInterval(adjust, self.spinCfg.interval);
				}, self.spinCfg.delay);
				self.spinCfg._repeater = true;
				return false;
			})
			
			.mouseup(function(e){
				// Cancel repeating adjustment
				self.spinCfg._repeater = false;
				window.clearInterval(self.spinCfg._repeat);
				window.clearTimeout(self.spinCfg._delay);
			})
			
			.dblclick(function(e) {
				if (sbjQuery.browser.msie)
					self.adjustValue(-self.spinCfg.step);
			})
			.mouseout(function(e){
				// Cancel repeating adjustment
				if (self.spinCfg._repeater)
				{
					self.spinCfg._repeater = false
					window.clearInterval(self.spinCfg._repeat);
					window.clearTimeout(self.spinCfg._delay);
				}
			});
			
		
		if (this.addEventListener) {
			// Respond to mouse wheel in Firefox
			this.addEventListener('DOMMouseScroll', function(e) {
				if (e.detail > 0)
					this.adjustValue(-this.spinCfg.step);
				else if (e.detail < 0)
					this.adjustValue(this.spinCfg.step);
				
				e.preventDefault();
			}, false);
		}
	});
	
	function coord(el,prop) {
		var c = el[prop], b = document.body;
		
		while ((el = el.offsetParent) && (el != b)) {
			if (!sbjQuery.browser.msie || (el.currentStyle.position != 'relative'))
				c += el[prop];
		}
		
		return c;
	}
};
