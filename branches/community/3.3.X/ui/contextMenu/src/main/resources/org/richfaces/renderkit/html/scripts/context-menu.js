if (!window.Richfaces) window.Richfaces = {};
Richfaces.ContextMenu = Class.create();

Richfaces.ContextMenu.prototype = {
	
	initialize: function(id, delay, evaluator, options) {
		this.id = id;
		this.element = $(id);
        this.element.component = this;
		this.menuContent = null;

        this.options = options || {};
		this.evaluator = evaluator;
		this["rich:destructor"] = "destroy";

		this.doShow = this.show;
		this.doHide = this.hide;
		this.delay = delay;

        this.attachedToElementId = null;
        this.attachedTo = [];
	},
	
	destroy: function() {
        for (var elementId in this.attachedTo) {
            var element = $(elementId);
            if (element) {
                var attached = this.attachedTo[elementId];
                Event.stopObserving(element, attached['eventName'], attached['listener']);
            }
        }

		this.enableDefaultContextMenu();
		this.element.component = null;
		this.element = null;
		this.menuContent = null;
        this.attachedTo = [];
	},
	
	disableDefaultContextMenu: function (element, id, event, attachedToPerent) {
		if (event=="oncontextmenu") {
			this.attachedToElementId = id;
			this.attachedToParent = attachedToPerent;
			this.eventName = "contextmenu";
		
			Event.observe(element, this.eventName, Event.stop);
		}	
	},
	
	enableDefaultContextMenu: function () {
		if (this.eventName == "contextmenu" && this.attachedToElementId) {
			var element = $(this.attachedToElementId);
			if (!element && this.attachedToParent) {
				element = this.element;
				if (element) {
					element = element.parentNode;
				}
			}			
			Event.stopObserving(element, this.eventName, Event.stop);
		}	
	},
	
	// attach contextMenu to element specified by id
	attachToElementById : function(id, event, context) {
		var element = $(id);
		
		this.disableDefaultContextMenu(element, id, event, false);
		this.attachToElement(element, event, context);
	},
	
	// attach contextMenu to element specified by id
	// or to the parent fo the current element 
	attachToParent : function(id, event, context) {
		var element = $(id);
		if (!element) {
			element = this.element;
			if (element) {
				element = element.parentNode;
			}
		}
		this.disableDefaultContextMenu(element, id, event, true);
		this.attachToElement(element, event, context);
	},
	
	// attach contextMenu to specified element
	attachToElement : function(element, event, context) {
		if (!element) {
            return;
        }

			this.applyDecoration(element);

        var evnName = event.substr(2); //Strip 'on' here
			// http://jira.jboss.com/jira/browse/RF-3419
			if(evnName == 'contextmenu') {
				Richfaces.enableDefaultHandler('click');
			}

			var listener = this.show.bindAsEventListener(this, context);
			Event.observe(element, evnName, listener);
        if (element.id) {
            this.attachedTo[element.id] = {
                'eventName' : evnName,
                'listener' : listener
            };
		}


	},
	
	hide: function() {
		//Stub here
		RichFaces.Menu.Layers.shutdown();
	},
	
	show: function(event, context) {
		this.construct(context);
		event.parameters = context;
		var delayedMenu = new RichFaces.Menu.DelayedContextMenu(this.id + "_menu", event);
		window.setTimeout(delayedMenu.show, this.delay);
	},
	
	construct: function(context) {
		if (this.isNewContext(context)) {
			this.destroyMenu();
		}
		
		var div = document.createElement("div");
		div.id = this.id + ":_auto_created";
		div.style.zoom="1";
		this.element.appendChild(div);
		
		var html = this.evaluator.invoke('getContent', context||{}).join('');
		html = this.interpolate(html, context); 
		new Insertion.Top(div, html);
		
		this.menuContent = div;
	},
	
	interpolate: function (placeholders, context) {
		for(var k in context) {
			var v = context[k];
			var regexp = new RegExp("\\{" + k + "\\}", "g");
			placeholders = placeholders.replace(regexp, v);
		}
		return placeholders;
	},
	
	destroyMenu: function() {
		if (this.menuContent) {
			window.RichFaces.Memory.clean(this.menuContent);
			this.menuContent.parentNode.removeChild(this.menuContent);
			this.menuContent = null;
		}
	},
	
	isNewContext: function(context) {
		//TODO: Check whether contexts are the same and therefore
		// do not destroy menu 
		//var oldContext = this.context || {};
		return true;
	},
	
	applyDecoration : function(element) {
		$(element).addClassName("rich-cm-attached");
	}
};

Richfaces.disableDefaultHandler = function(event) {
	if (event.startsWith('on')) {
		event = event.substr(2);
	}
	Event.observe(document, event, Event.stop);	
};

Richfaces.enableDefaultHandler = function(event) {
	if (event.startsWith('on')) {
	event = event.substr(2);
	}
	Event.stopObserving(document, event, Event.stop);
};