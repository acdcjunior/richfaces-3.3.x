if (!window.Richfaces) {
window.Richfaces = {};
}

Richfaces.processEffect = function(params) {
	new Effect[params.type]($(params.targetId),params);
};

Richfaces.effectEventOnOut = function(ename) {
	return ename.substr(0,2) == 'on' ? ename.substr(2) : ename;
};

if (!Richfaces.effect) {
	Richfaces.effect={};
}

Richfaces.effect.create = function (options) {
	/*	options:
	 		event,
	 		name,
	 		targetId,
	 		attachId,
	 		attachObj,
	 		targetObj,
	 		type,
	 		params */	

	if (!options) options = {};
	
	var params = options.params || {};
	options.params = null;
	
	var attachObj;
	var targetObj;
	var targetId = options.targetId;
	var attachId = options.attachId;
	
	if (options.attachObj) {
		try {
			attachObj = eval(options.attachObj);
		} catch (e) {}
		
		if (typeof attachObj == 'object') {
			attachId = attachObj;
		}
	}
	
	if (options.targetObj) {
		try {
			targetObj = eval(options.targetObj);
		} catch (e) {}
		
		if (typeof targetObj == 'object') targetId = targetObj;
	}
	
	if (!targetId) targetId = attachId;

	if (!params.targetId) params.targetId = targetId;
	params.type = options.type;
	
	if (!options.event) {
		// create user function
		with (window) {
			eval(options.name + "=function(){return Richfaces.processEffect(Object.extend(this,arguments[0]||{}));}.bind(params)"); // ?? or just use params instead of this and binding
		}
	} else {
		// attach eventListener
		var ename = Richfaces.effectEventOnOut(options.event || "");
		if (ename) {
			var bindedFunction = function(event){ return Richfaces.processEffect(this); }.bindAsEventListener(params);
			Event.observe(attachId, ename, bindedFunction, params.useCapture||false);
		}
	}
}
