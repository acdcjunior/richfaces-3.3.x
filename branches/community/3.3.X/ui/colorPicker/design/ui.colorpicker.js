/*
 * jQuery UI Color Picker @VERSION
 *
 * Copyright (c) 2008 Stefan Petre, Paul Bakaus
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 *
 * http://docs.jquery.com/UI/colorPicker
 *
 * Depends:
 *	ui.core.js
 
 
 
 
var cw = getSizeElement().clientWidth;

nickbelaevski: function getSizeElement() {
var element;
var element;
if (RichFaces.navigatorType() != RichFaces.OPERA && document.compatMode=='CSS1Compat') {
element = document.documentElement;
} else {
element = document.body;
}

return element;
}

nickbelaevski: Richfaces.Position.getWindowDimensions = function() {
    var w =  self.innerWidth
                || document.documentElement.clientWidth
                || document.body.clientWidth
                || 0;
    var h =  self.innerHeight
                || document.documentElement.clientHeight
                || document.body.clientHeight
                || 0;
return {width:w, height: h};
};

Richfaces.Position.getWindowScrollOffset = function() {
    var dx =  window.pageXOffset
                || document.documentElement.scrollLeft
                || document.body.scrollLeft
                || 0;
    var dy =  window.pageYOffset
                || document.documentElement.scrollTop
                || document.body.scrollTop
                || 0;
return {leftx, top:
nickbelaevski: /richfaces-impl/src/main/resources/org/richfaces/renderkit/html/scripts/utils.js

 */
(function ($) {

$.widget("ui.colorPicker", {
	

	_fixColors: function(cff, mode) {
		cff.toLowerCase();
		var pattern = /[0-9]+/g;
		cff = cff.match(pattern); 
		
		if(mode == 'hsb'){
			result = {h:cff[0], s: cff[1], b: cff[2]};
			return result;
		} else if(mode == 'rgb'){
			result = {r:cff[0], g: cff[1], b: cff[2]};
			return result;
		}
	},
	
	_init: function() {

		this.charMin = 65;
		var o = this.options, self = this,
		
	tpl = $(o.clientId.toString()+"-ui");
	
		if(o.color.indexOf('hsb') > -1){
			this.color = this._fixHSB(this._fixColors(o.color, 'hsb'));
		} else if(o.color.indexOf('rgb') > -1){
			this.color = this._RGBToHSB(this._fixColors(o.color, 'rgb'));
		} else if(o.color.indexOf('#') > -1){
			this.color = this._HexToHSB(o.color);
		} else{
			return this;
		}
		
		/*
		if (typeof o.color == 'string') {
			this.color = this._HexToHSB(o.color);
		} else if (o.color.r != undefined && o.color.g != undefined && o.color.b != undefined) {
			this.color = this._RGBToHSB(o.color);
		} else if (o.color.h != undefined && o.color.s != undefined && o.color.b != undefined) {
			this.color = this._fixHSB(o.color);
		} else {
			return this;
		}
*/

		this.origColor = this.color;
		this.picker = $(tpl);

		if (o.flat) {
			this.picker.show();
		} 

		this.fields = this.picker.find('input')
						.bind('keydown', function(e) { return self._keyDown.call(self, e); })
						.bind('change', function(e) { return self._change.call(self, e); })
						.bind('blur', function(e) { return self._blur.call(self, e); })
						.bind('focus', function(e) { return self._focus.call(self, e); });

/*		this.picker.find('span').bind('mousedown', function(e) { return self._downIncrement.call(self, e); }); */

		this.selector = this.picker.find('div.rich-colorPicker-color').bind('mousedown', function(e) { return self._downSelector.call(self, e); });
		this.selectorIndic = this.selector.find('div div');
		this.hue = this.picker.find('div.rich-colorPicker-rainbow div');
		this.picker.find('div.rich-colorPicker-rainbow').bind('mousedown', function(e) { return self._downHue.call(self, e); });

		this.newColor = this.picker.find('div.rich-colorPicker-new-color');
		this.currentColor = this.picker.find('div.rich-colorPicker-current-color');
		this.iconColor = $('img.rich-colorPicker-icon');
		
		this.picker.find('.rich-colorPicker-submit')
			.bind('mouseenter', function(e) { return self._enterSubmit.call(self, e); })
			.bind('mouseleave', function(e) { return self._leaveSubmit.call(self, e); })
			.bind('click', function(e) { return self._clickSubmit.call(self, e); });

		this.picker.find('.rich-colorPicker-cancel')
			.bind('mouseenter', function(e) { return self._enterCancel.call(self, e); })
			.bind('mouseleave', function(e) { return self._leaveCancel.call(self, e); })
			.bind('click', function(e) { return self._clickCancel.call(self, e); });

		this._fillRGBFields(this.color);
		this._fillHSBFields(this.color);
		this._fillHexFields(this.color);
		this._setHue(this.color);
		this._setSelector(this.color);
		this._setCurrentColor(this.color);
		this._setIconColor(this.color);
		this._setNewColor(this.color);

		if (o.flat) {

			this.picker.css({
				position: 'absolute',
				display: 'block'
			});
			$(this.element).bind(o.eventName+".colorPicker", function(e) { return self._show.call(self, e); });
		} else {
			$(this.element).bind(o.eventName+".colorPicker", function(e) { return self._show.call(self, e); });
		}
		

	},

	
	
	destroy: function() {

		this.picker.remove();
		this.element.removeData("colorPicker").unbind(".colorPicker");

	},

	_fillRGBFields: function(hsb) {
		var rgb = this._HSBToRGB(hsb);
        
		this.fields
			.eq(1).val(rgb.r).end()
			.eq(2).val(rgb.g).end()
			.eq(3).val(rgb.b).end();
	},
	_fillHSBFields: function(hsb) {
		this.fields
			.eq(4).val(hsb.h).end()
			.eq(5).val(hsb.s).end()
			.eq(6).val(hsb.b).end();
	},
	_fillHexFields: function (hsb) {
		this.fields
			.eq(0).val(this._HSBToHex(hsb)).end();
	},
	_setSelector: function(hsb) {
		this.selector.css('backgroundColor', '#' + this._HSBToHex({h: hsb.h, s: 100, b: 100}));
		this.selectorIndic.css({
			left: parseInt(150 * hsb.s/100, 10),
			top: parseInt(150 * (100-hsb.b)/100, 10)
		});
	},
	_setHue: function(hsb) {
		this.hue.css('top', parseInt(150 - 150 * hsb.h/360, 10));
	},
	_setCurrentColor: function(hsb) {
		this.currentColor.css('backgroundColor', '#' + this._HSBToHex(hsb));
	},
	_setIconColor: function(hsb) {
		this.iconColor.css('backgroundColor', '#' + this._HSBToHex(hsb));
	},
	_setNewColor: function(hsb) {
		this.newColor.css('backgroundColor', '#' + this._HSBToHex(hsb));
	},
	_keyDown: function(e) {
		var pressedKey = e.charCode || e.keyCode || -1;
		if ((pressedKey > this.charMin && pressedKey <= 90) || pressedKey == 32) {
			return false;
		}
	},
	_change: function(e, target) {

		var col;
		target = target || e.target;
		
		if (target.parentNode.className.indexOf('-hex') > 0) {

			col = this._HexToHSB(this.fields.eq(0).val());
			
			this.color = col;
			this._fillHexFields(col);
			this._fillRGBFields(col);
			this._fillHSBFields(col);
		} else if (target.parentNode.className.indexOf('-hsb') > 0) {
			col = this._fixHSB({
				h: parseInt(this.fields.eq(4).val(), 10),
				s: parseInt(this.fields.eq(5).val(), 10),
				b: parseInt(this.fields.eq(6).val(), 10)
			});
			this.color = col;
			this._fillHSBFields(col);
			this._fillRGBFields(col);
			this._fillHexFields(col);
		} else {
			col = this._RGBToHSB(this._fixRGB({
				r: parseInt(this.fields.eq(1).val(), 10),
				g: parseInt(this.fields.eq(2).val(), 10),
				b: parseInt(this.fields.eq(3).val(), 10)
			}));
			this.color = col;
			this._fillRGBFields(col);
			this._fillHexFields(col);
			this._fillHSBFields(col);
		}
		this._setSelector(col);
		this._setHue(col);
		this._setNewColor(col);
		this._trigger('change', e, { options: this.options, hsb: col, hex: this._HSBToHex(col), rgb: this._HSBToRGB(col) });
	},
	_blur: function(e) {
/*
		var col = this.color;
		this._fillRGBFields(col);
		this._fillHSBFields(col);
		this._fillHexFields(col);
		this._setHue(col);
		this._setSelector(col);
		this._setNewColor(col);
		this._setIconColor(col);
*/
		this.fields.parent().removeClass('rich-colorPicker-focus');

	},
	_focus: function(e) {

		this.charMin = e.target.parentNode.className.indexOf('-hex') > 0 ? 70 : 65;
		this.fields.parent().removeClass('rich-colorPicker-focus');
		$(e.target.parentNode).addClass('rich-colorPicker-focus');

	},
/*
	_downIncrement: function(e) {

		var field = $(e.target).parent().find('input').focus(), self = this;
		this.currentIncrement = {
			el: $(e.target).parent().addClass('ui-colorPicker-slider'),
			max: e.target.parentNode.className.indexOf('-hsb-h') > 0 ? 360 : (e.target.parentNode.className.indexOf('-hsb') > 0 ? 100 : 255),
			y: e.pageY,
			field: field,
			val: parseInt(field.val(), 10)
		};
		$(document).bind('mouseup.cpSlider', function(e) { return self._upIncrement.call(self, e); });
		$(document).bind('mousemove.cpSlider', function(e) { return self._moveIncrement.call(self, e); });
		return false;

	},
	_moveIncrement: function(e) {
		this.currentIncrement.field.val(Math.max(0, Math.min(this.currentIncrement.max, parseInt(this.currentIncrement.val + e.pageY - this.currentIncrement.y, 10))));
		this._change.apply(this, [e, this.currentIncrement.field.get(0)]);
		return false;
	},
	_upIncrement: function(e) {
		this.currentIncrement.el.removeClass('ui-colorPicker-slider').find('input').focus();
		this._change.apply(this, [e, this.currentIncrement.field.get(0)]);
		$(document).unbind('mouseup.cpSlider');
		$(document).unbind('mousemove.cpSlider');
		return false;
	},
*/
	_downHue: function(e) {

		this.currentHue = {
			y: this.picker.find('div.rich-colorPicker-rainbow').offset().top
		};

		this._change.apply(this, [e, this
				.fields
				.eq(4)
				.val(parseInt(360*(150 - Math.max(0,Math.min(150,(e.pageY - this.currentHue.y))))/150, 10))
				.get(0)]);

		var self = this;
		$(document).bind('mouseup.cpSlider', function(e) { return self._upHue.call(self, e); });
		$(document).bind('mousemove.cpSlider', function(e) { return self._moveHue.call(self, e); });
		return false;

	},
	_moveHue: function(e) {

		this._change.apply(this, [e, this
				.fields
				.eq(4)
				.val(parseInt(360*(150 - Math.max(0,Math.min(150,(e.pageY - this.currentHue.y))))/150, 10))
				.get(0)]);

		return false;

	},
	_upHue: function(e) {
		$(document).unbind('mouseup.cpSlider');
		$(document).unbind('mousemove.cpSlider');
		return false;
	},
	_downSelector: function(e) {

		var self = this;
		this.currentSelector = {
			pos: this.picker.find('div.rich-colorPicker-color').offset()
		};

		this._change.apply(this, [e, this
				.fields
				.eq(6)
				.val(parseInt(100*(150 - Math.max(0,Math.min(150,(e.pageY - this.currentSelector.pos.top))))/150, 10))
				.end()
				.eq(5)
				.val(parseInt(100*(Math.max(0,Math.min(150,(e.pageX - this.currentSelector.pos.left))))/150, 10))
				.get(0)
		]);
		$(document).bind('mouseup.cpSlider', function(e) { return self._upSelector.call(self, e); });
		$(document).bind('mousemove.cpSlider', function(e) { return self._moveSelector.call(self, e); });
		return false;

	},
	_moveSelector: function(e) {

		this._change.apply(this, [e, this
				.fields
				.eq(6)
				.val(parseInt(100*(150 - Math.max(0,Math.min(150,(e.pageY - this.currentSelector.pos.top))))/150, 10))
				.end()
				.eq(5)
				.val(parseInt(100*(Math.max(0,Math.min(150,(e.pageX - this.currentSelector.pos.left))))/150, 10))
				.get(0)
		]);
		return false;

	},
	_upSelector: function(e) {
		$(document).unbind('mouseup.cpSlider');
		$(document).unbind('mousemove.cpSlider');
		return false;
	},
	_enterSubmit: function(e) {
		this.picker.find('.rich-colorPicker-submit').addClass('rich-colorPicker-focus');
	},
	_leaveSubmit: function(e) {
		this.picker.find('.rich-colorPicker-submit').removeClass('rich-colorPicker-focus');
	},
	_enterCancel: function(e) {
		this.picker.find('.rich-colorPicker-cancel').addClass('rich-colorPicker-focus');
	},
	_leaveCancel: function(e) {
		this.picker.find('.rich-colorPicker-cancel').removeClass('rich-colorPicker-focus');
	},
	_clickSubmit: function(e) {

		var col = this.color;
		this.origColor = col;
		this._setCurrentColor(col);
		this._setIconColor(col);
		var RGBCol = this._HSBToRGB(col);

		this._trigger("submit", e, { options: this.options, hex: '#'+this._HSBToHex(col), rgb: 'rgb('+RGBCol.r+', '+RGBCol.g+', '+RGBCol.b+')' });
//		this._trigger("submit", e, { options: this.options, hsb: col, hex: this._HSBToHex(col), rgb: this._HSBToRGB(col) });
		this.picker.hide();
		$(document).unbind('mousedown.colorPicker');
		return false;
	},
	_clickCancel: function(e) {
		this.picker.hide();
		$(document).unbind('mousedown.colorPicker');
		return false;
	},
	_show: function(e) {

		this._trigger("beforeShow", e, { options: this.options, hsb: this.color, hex: this._HSBToHex(this.color), rgb: this._HSBToRGB(this.color) });

		var top = 0;
		var left = 0;
		var viewPort = this._getWindowScrollOffset();
		var window = this._getWindowDimensions();

		if(window.height - (this.element.offset().top - viewPort.scrollTop)  < this.picker.height()){
			top = this.element[0].offsetTop - this.picker.height();
		}else{
			top = this.element[0].offsetTop + this.element[0].offsetHeight;
		}
		if(window.width - (this.element.offset().left - viewPort.scrollLeft)  < this.picker.width()){
			left = this.element[0].offsetLeft - this.picker.width() + this.element[0].offsetWidth;
		}else{
			left = this.element[0].offsetLeft;
		}

		this.picker.css({left: left + 'px', top: top + 'px'});
		if (this._trigger("show", e, { options: this.options, hsb: this.color, hex: this._HSBToHex(this.color), rgb: this._HSBToRGB(this.color) }) != false) {
			this.picker.show();
		}

		var self = this;
		$(document).bind('mousedown.colorPicker', function(e) { return self._hide.call(self, e); });
		return false;

	},

	_getWindowDimensions: function() {
	    var w =  self.innerWidth
		        || document.documentElement.clientWidth
		        || document.body.clientWidth
		        || 0;
	    var h =  self.innerHeight
		        || document.documentElement.clientHeight
		        || document.body.clientHeight
		        || 0;
	    return {width:w, height: h};
	},

	_getWindowScrollOffset: function() {
	    var x =  window.pageXOffset
		        || document.documentElement.scrollLeft
		        || document.body.scrollLeft
		        || 0;
	    var y =  window.pageYOffset
		        || document.documentElement.scrollTop
		        || document.body.scrollTop
		        || 0;
		return {scrollLeft:x, scrollTop:y};
	},

	_hide: function(e) {

		if (!this._isChildOf(this.picker[0], e.target, this.picker[0])) {
			if (this._trigger("hide", e, { options: this.options, hsb: this.color, hex: this._HSBToHex(this.color), rgb: this._HSBToRGB(this.color) }) != false) {
				this.picker.hide();
			}
			$(document).unbind('mousedown.colorPicker');
		}

	},
	_isChildOf: function(parentEl, el, container) {
		if (parentEl == el) {
			return true;
		}
		if (parentEl.contains && !$.browser.safari) {
			return parentEl.contains(el);
		}
		if ( parentEl.compareDocumentPosition ) {
			return !!(parentEl.compareDocumentPosition(el) & 16);
		}
		var prEl = el.parentNode;
		while(prEl && prEl != container) {
			if (prEl == parentEl)
				return true;
			prEl = prEl.parentNode;
		}
		return false;
	},
/*
	_getScroll: function() {
		var t,l,w,h,iw,ih;
		if (document.documentElement) {
			t = document.documentElement.scrollTop;
			l = document.documentElement.scrollLeft;
			w = document.documentElement.scrollWidth;
			h = document.documentElement.scrollHeight;
		} else {
			t = document.body.scrollTop;
			l = document.body.scrollLeft;
			w = document.body.scrollWidth;
			h = document.body.scrollHeight;
		}
		iw = self.innerWidth||document.documentElement.clientWidth||document.body.clientWidth||0;
		ih = self.innerHeight||document.documentElement.clientHeight||document.body.clientHeight||0;
		return { t: t, l: l, w: w, h: h, iw: iw, ih: ih };
	},
*/
	_fixHSB: function(hsb) {
		return {
			h: Math.min(360, Math.max(0, hsb.h)),
			s: Math.min(100, Math.max(0, hsb.s)),
			b: Math.min(100, Math.max(0, hsb.b))
		};
	},
	_fixRGB: function(rgb) {
		return {
			r: Math.min(255, Math.max(0, rgb.r)),
			g: Math.min(255, Math.max(0, rgb.g)),
			b: Math.min(255, Math.max(0, rgb.b))
		};
	},
	_HexToRGB: function (hex) {
		var hex = parseInt(((hex.indexOf('#') > -1) ? hex.substring(1) : hex), 16);
		return {r: hex >> 16, g: (hex & 0x00FF00) >> 8, b: (hex & 0x0000FF)};
	},
	_HexToHSB: function(hex) {
		return this._RGBToHSB(this._HexToRGB(hex));
	},
	_RGBToHSB: function(rgb) {
		var hsb = {};
		hsb.b = Math.max(Math.max(rgb.r,rgb.g),rgb.b);
		hsb.s = (hsb.b <= 0) ? 0 : Math.round(100*(hsb.b - Math.min(Math.min(rgb.r,rgb.g),rgb.b))/hsb.b);
		hsb.b = Math.round((hsb.b /255)*100);
		if((rgb.r==rgb.g) && (rgb.g==rgb.b)) hsb.h = 0;
		else if(rgb.r>=rgb.g && rgb.g>=rgb.b) hsb.h = 60*(rgb.g-rgb.b)/(rgb.r-rgb.b);
		else if(rgb.g>=rgb.r && rgb.r>=rgb.b) hsb.h = 60  + 60*(rgb.g-rgb.r)/(rgb.g-rgb.b);
		else if(rgb.g>=rgb.b && rgb.b>=rgb.r) hsb.h = 120 + 60*(rgb.b-rgb.r)/(rgb.g-rgb.r);
		else if(rgb.b>=rgb.g && rgb.g>=rgb.r) hsb.h = 180 + 60*(rgb.b-rgb.g)/(rgb.b-rgb.r);
		else if(rgb.b>=rgb.r && rgb.r>=rgb.g) hsb.h = 240 + 60*(rgb.r-rgb.g)/(rgb.b-rgb.g);
		else if(rgb.r>=rgb.b && rgb.b>=rgb.g) hsb.h = 300 + 60*(rgb.r-rgb.b)/(rgb.r-rgb.g);
		else hsb.h = 0;
		hsb.h = Math.round(hsb.h);
		return hsb;
	},
	_HSBToRGB: function(hsb) {
		var rgb = {};
		var h = Math.round(hsb.h);
		var s = Math.round(hsb.s*255/100);
		var v = Math.round(hsb.b*255/100);
		if(s == 0) {
			rgb.r = rgb.g = rgb.b = v;
		} else {
			var t1 = v;
			var t2 = (255-s)*v/255;
			var t3 = (t1-t2)*(h%60)/60;
			if(h==360) h = 0;
			if(h<60) {rgb.r=t1;	rgb.b=t2; rgb.g=t2+t3;}
			else if(h<120) {rgb.g=t1; rgb.b=t2;	rgb.r=t1-t3;}
			else if(h<180) {rgb.g=t1; rgb.r=t2;	rgb.b=t2+t3;}
			else if(h<240) {rgb.b=t1; rgb.r=t2;	rgb.g=t1-t3;}
			else if(h<300) {rgb.b=t1; rgb.g=t2;	rgb.r=t2+t3;}
			else if(h<360) {rgb.r=t1; rgb.g=t2;	rgb.b=t1-t3;}
			else {rgb.r=0; rgb.g=0;	rgb.b=0;}
		}
		return {r:Math.round(rgb.r), g:Math.round(rgb.g), b:Math.round(rgb.b)};
	},
	_RGBToHex: function(rgb) {
		var hex = [
			rgb.r.toString(16),
			rgb.g.toString(16),
			rgb.b.toString(16)
		];
		$.each(hex, function (nr, val) {
			if (val.length == 1) {
				hex[nr] = '0' + val;
			}
		});
		return hex.join('');
	},
	_HSBToHex: function(hsb) {
		return this._RGBToHex(this._HSBToRGB(hsb));
	},
	setColor: function(col) {
		if (typeof col == 'string') {
			col = this._HexToHSB(col);
		} else if (col.r != undefined && col.g != undefined && col.b != undefined) {
			col = this._RGBToHSB(col);
		} else if (col.h != undefined && col.s != undefined && col.b != undefined) {
			col = this._fixHSB(col);
		} else {
			return this;
		}

		this.color = col;
		this.origColor = col;
		this._fillRGBFields(col);
		this._fillHSBFields(col);
		this._fillHexFields(col);
		this._setHue(col);
		this._setSelector(col);
		this._setCurrentColor(col);
		this._setIconColor(col);
		this._setNewColor(col);

	}

});
})(jQuery);
