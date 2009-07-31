package org.richfaces.renderkit;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.javascript.JSFunctionDefinition;
import org.ajax4jsf.javascript.ScriptUtils;
import org.ajax4jsf.renderkit.ComponentVariables;
import org.ajax4jsf.renderkit.ComponentsVariableResolver;
import org.richfaces.component.UIColorPicker;
import org.richfaces.component.util.MessageUtil;

public abstract class ColorPickerRendererBase  extends InputRendererBase {

	public static final String BUNDLE = ColorPickerRendererBase.class.getPackage().getName() + ".colorPicker";

	public void addPopupToAjaxRendered(FacesContext context, UIColorPicker component) {
		AjaxContext ajaxContext = AjaxContext.getCurrentInstance(context);
		Set<String> ajaxRenderedAreas = ajaxContext.getAjaxRenderedAreas();
		String clientId = component.getClientId(context);
		if (ajaxContext.isAjaxRequest() && ajaxRenderedAreas.contains(clientId)) {
			ajaxRenderedAreas.add(clientId + "-colorPicker-popup");
			ajaxRenderedAreas.add(clientId + "-colorPicker-script");
		}
	}
	
	private static final String[] EVENT_ATTRIBUTES = {
		"onchange", "onbeforeshow", "onshow", "onhide", "onselect"
	};
	
	public String encodeEvents(FacesContext context, UIComponent component) {
		StringBuilder builder = new StringBuilder();
		for (String eventName: EVENT_ATTRIBUTES) {
			JSFunctionDefinition handler = getUtils().getAsEventHandler(context, component, eventName, null);
			if (handler != null) {
				builder.append(".bind(");

				builder.append("'");
				builder.append("colorPicker");
				builder.append(eventName.substring(2));
				builder.append("', ");
				builder.append(ScriptUtils.toScript(handler));
				builder.append(")");
			}
		}
		return builder.toString();
	}
	
	private static final Pattern[] COLOR_PATTERNS = {
		Pattern.compile("(?:rgb|hsb)(?:\\D+\\d+){3}"),
		Pattern.compile("^#[0-9A-Fa-f]{6}")
	};
	
	public void validateColorString(FacesContext context, UIComponent component, String value) {
		for (Pattern colorPattern : COLOR_PATTERNS) {
			if (colorPattern.matcher(value).find()) {
				return ;
			}
		}

		throw new IllegalArgumentException("Illegal color value: [" + value + "] for component " + 
			MessageUtil.getLabel(context, component));
	}
	
	public void initButtonLabels(FacesContext context, UIComponent component){
		ComponentVariables variables = ComponentsVariableResolver.getVariables(this, component);
		Locale locale = getLocale(context);
		ClassLoader loader = getClassLoader();
		ResourceBundle bundleApplication = null;
		ResourceBundle bundleExternal = null;
		String messageBundle = context.getApplication().getMessageBundle();
		if (messageBundle != null) {
			bundleApplication = ResourceBundle.getBundle(messageBundle, locale,
					loader);
		}
		try {
			bundleExternal = ResourceBundle.getBundle(BUNDLE, locale, loader);
		} catch (MissingResourceException e) {}
		String[] names = {"Apply", "Cancel"};
		for (String name : names) {
			String label = null;
		    String bundleKey = "RICH_COLORPICKER_" + name.toUpperCase() + "_LABEL";
		    if (bundleApplication != null) {
		    	try {
		    		label = bundleApplication.getString(bundleKey);
		    	} catch (MissingResourceException mre) {
			    // Current key was not found, ignore this exception;
		    	}
		    }
		    if (label == null && bundleExternal != null) {
		    	try {
		    		label = bundleExternal.getString(bundleKey);
		    	} catch (MissingResourceException mre) {
			    // Current key was not found, ignore this exception;
		    	}
		    }
		    if (label == null) {
		    	label = name;
		    }
		    variables.setVariable(name, label);
		}
	}
	
	private static Locale getLocale(FacesContext context) {
		Locale locale = null;
		UIViewRoot viewRoot = context.getViewRoot();
		if (viewRoot != null) {
			locale = viewRoot.getLocale();
		}
		if (locale == null) {
			locale = Locale.getDefault();
		}
		return locale;
	}
	
	private static ClassLoader getClassLoader() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = ClassLoader.getSystemClassLoader();
		}
		return loader;
	}
}