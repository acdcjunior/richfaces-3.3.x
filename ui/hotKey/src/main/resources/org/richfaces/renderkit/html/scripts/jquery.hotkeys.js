/******************************************************************************************************************************

 * @ Original idea by by Binny V A, Original version: 2.00.A 
 * @ http://www.openjs.com/scripts/events/keyboard_shortcuts/
 * @ Original License : BSD
 
 * @ jQuery Plugin by Tzury Bar Yochay 
        mail: tzury.by@gmail.com
        blog: evalinux.wordpress.com
        face: facebook.com/profile.php?id=513676303
        
        (c) Copyrights 2007
        
 * @ jQuery Plugin version Beta (0.0.2)
 * @ License: jQuery-License.
 
TODO:
    add queue support (as in gmail) e.g. 'x' then 'y', etc.
    add mouse + mouse wheel events.

USAGE:
    $.hotkeys.add('Ctrl+c', function(){ alert('copy anyone?');});
    $.hotkeys.add('Ctrl+c', {target:'div#editor', type:'keyup', propagate: true},function(){ alert('copy anyone?');});>
    $.hotkeys.remove('Ctrl+c'); 
    $.hotkeys.remove('Ctrl+c', {target:'div#editor', type:'keypress'}); 
    
******************************************************************************************************************************/
var hack;
(function (jQuery){
    this.version = '(beta)(0.0.3)';

    this.special_keys = {
        27: 'esc', 9: 'tab', 32:'space', 13: 'return', 8:'backspace', 145: 'scroll', 20: 'capslock', 
        144: 'numlock', 19:'pause', 45:'insert', 36:'home', 46:'del',35:'end', 33: 'pageup', 
        34:'pagedown', 37:'left', 38:'up', 39:'right',40:'down', 112:'f1',113:'f2', 114:'f3', 
        115:'f4', 116:'f5', 117:'f6', 118:'f7', 119:'f8', 120:'f9', 121:'f10', 122:'f11', 123:'f12'};
        
    this.shift_nums = { "`":"~", "1":"!", "2":"@", "3":"#", "4":"$", "5":"%", "6":"^", "7":"&", 
        "8":"*", "9":"(", "0":")", "-":"_", "=":"+", ";":":", "'":"\"", ",":"<", 
        ".":">",  "/":"?",  "\\":"|" };
    
    this.HOTKEYS_DATA = "jQuery.hotkeys." + (+new Date());
    
    this.buttonInputTypes = /^(submit|button|reset)$/i;
    
    this.add = function(combi, options, callback) {
        if (jQuery.isFunction(options)){
            callback = options;
            options = {};
        }
        var opt = {},
            defaults = {type: 'keydown', propagate: false, disableInInput: false, disableInInputTypes: 'all', target: jQuery('html')[0], checkParent: true},
            that = this;
        opt = jQuery.extend( opt , defaults, options || {} );
        combi = combi.toLowerCase();        
        
        // inspect if keystroke matches
        var inspector = function(event) {
            event = jQuery.event.fix(event); // jQuery event normalization.
            var element = event.target;
            // @ TextNode -> nodeType == 3
            element = (element.nodeType==3) ? element.parentNode : element;
            
            if(opt['disableInInput']) { // Disable shortcut keys in Input, Textarea fields
                var target = jQuery(element);
                var types = opt['disableInInputTypes'];

                if (target.is("input")) {
                	if ('all' == types) {
                		return;
                	} else {
                    	if (that.buttonInputTypes.test(target.attr('type'))) {
                    		if ('buttons' == types) {
                    			return;
                    		}
                    	} else {
                    		if ('texts' == types) {
                    			return;
                    		}
                    	}
                	}
                } else if (target.is("textarea")) {
                	if ('texts' == types || 'all' == types) {
                		return;
                	}
                } else if (target.is("button")) {
                	if ('buttons' == types || 'all' == types) {
                		return;
                	}
                }
            }
            var code = event.which,
                type = event.type,
                character = String.fromCharCode(code).toLowerCase(),
                special = that.special_keys[code],
                shift = event.shiftKey,
                ctrl = event.ctrlKey,
                alt= event.altKey,
                propagate = true, // default behaivour
                mapPoint = null;
            
            // in opera + safari, the event.target is unpredictable.
            // for example: 'keydown' might be associated with HtmlBodyElement 
            // or the element where you last clicked with your mouse.
            /*if (jQuery.browser.opera || jQuery.browser.safari || opt.checkParent){
                while (!that.all[that._checkUniqueID(element)] && element.parentNode){
                    element = element.parentNode;
                }
            }
            
            var cbMap = that.all[that._checkUniqueID(element)].events[type].callbackMap;
            if(!shift && !ctrl && !alt) { // No Modifiers
                mapPoint = cbMap[special] ||  cbMap[character]
			}
            // deals with combinaitons (alt|ctrl|shift+anything)
            else{
                var modif = '';
                if(alt) modif +='alt+';
                if(ctrl) modif+= 'ctrl+';
                if(shift) modif += 'shift+';
                // modifiers + special keys or modifiers + characters or modifiers + shift characters
                mapPoint = cbMap[modif+special] || cbMap[modif+character] || cbMap[modif+that.shift_nums[character]]
            }
            if (mapPoint){
                mapPoint.cb(event);
                if(!mapPoint.propagate) {
                    event.stopPropagation();
                    event.preventDefault();
                    return false;
                }
            }*/
            var validateMapPoint = function(element, type, special, character,cbMap) {
		        if(!shift && !ctrl && !alt) { // No Modifiers
		        	var mapPoint = cbMap[special] ||  cbMap[character]
				} else {
					// deals with combinaitons (alt|ctrl|shift+anything)
		        	var modif = '';
		            if(alt) modif +='alt+';
		            if(ctrl) modif+= 'ctrl+';
		            if(shift) modif += 'shift+';
		            // modifiers + special keys or modifiers + characters or modifiers + shift characters
		            var mapPoint = cbMap[modif+special] || cbMap[modif+character] || cbMap[modif+that.shift_nums[character]]
		        }	
		        return mapPoint;
			};
            // in opera + safari, the event.target is unpredictable.
            // for example: 'keydown' might be associated with HtmlBodyElement 
            // or the element where you last clicked with your mouse.
            
			var elementData = jQuery.data(element, that.HOTKEYS_DATA);
			
            if (elementData) {
            	var cbMap = elementData.events[type].callbackMap;
               	mapPoint = validateMapPoint(element, type, special, character,cbMap);
            }
                		
           if (jQuery.browser.opera || jQuery.browser.safari || opt.checkParent){
                while ((element && element.parentNode && !(elementData = jQuery.data(element, that.HOTKEYS_DATA))) || (!mapPoint && element.parentNode)){
                	element = element.parentNode;
                	elementData = jQuery.data(element, that.HOTKEYS_DATA);
                	if (elementData) {
                		var cbMap = elementData.events[type].callbackMap;
                		mapPoint = validateMapPoint(element, type, special, character,cbMap);
                	}
                }
            }
            
            if (mapPoint){
                mapPoint.cb(event);
                if(!mapPoint.propagate) {
                    event.stopPropagation();
                    event.preventDefault();
                    return false;
                }
            }
		};        
		
		var target = opt.target;
		var targetData = jQuery.data(target, this.HOTKEYS_DATA);
		
        // first hook for this element
        if (!targetData){
            targetData = {events:{}};
            jQuery.data(target, this.HOTKEYS_DATA, targetData);
        }
        if (!targetData.events[opt.type]){
            targetData.events[opt.type] = {callbackMap: {}, inspector: inspector};
            jQuery.event.add(target, opt.type, inspector);
        }        
        targetData.events[opt.type].callbackMap[combi] = {cb: callback, propagate:opt.propagate};                

        return jQuery;
	};    
	
	this._isEmpty = function(object, skipList) {
		var name = "";
		for (name in object) {
			if (!skipList || !(name in skipList)) {
				break;
			} else {
				name = "";
			}
		}
		
		return !name;
	};
	
	this._deleteEmptyProperty = function(object, property, skipList) {
		if (this._isEmpty(object[property], skipList)) {
			delete object[property];
		}
	};
	
    this.remove = function(exp, opt) {
        opt = opt || {};
        target = opt.target || jQuery('html')[0];
        type = opt.type || 'keydown';
		exp = exp.toLowerCase();        
		
		var element = jQuery.data(target, this.HOTKEYS_DATA);
		if (element) {
			var events = element['events'];
			if (events) {
				var event = events[type];
				var callbackMap = event['callbackMap'];
				
				delete callbackMap[exp];
			
				this._deleteEmptyProperty(event, 'callbackMap');
				
				var inspector = events[type].inspector;
				this._deleteEmptyProperty(events, type, {inspector: true});
				
				if (!events[type] && inspector) {
		            jQuery.event.remove(target, type, inspector);
				}
				
				this._deleteEmptyProperty(element, 'events');
			}
			
			if (this._isEmpty(element)) {
				jQuery.removeData(target, this.HOTKEYS_DATA);
			}
		}
		
        return jQuery;
	};
    jQuery.hotkeys = this;
    return jQuery;    
})(jQuery);
