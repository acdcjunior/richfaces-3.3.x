/**
 * License Agreement.
 *
 *  JBoss RichFaces - Ajax4jsf Component Library
 *
 * Copyright (C) 2007  Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package org.richfaces.renderkit;

import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.event.AjaxEvent;
import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.javascript.JSFunctionDefinition;
import org.ajax4jsf.javascript.JSReference;
import org.ajax4jsf.javascript.ScriptUtils;
import org.ajax4jsf.renderkit.AjaxRendererUtils;
import org.ajax4jsf.renderkit.RendererUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.TemplateComponent;
import org.richfaces.component.UICalendar;
import org.richfaces.component.util.ComponentUtil;
import org.richfaces.component.util.MessageUtil;
import org.richfaces.context.RequestContext;
import org.richfaces.event.CurrentDateChangeEvent;
import org.richfaces.json.JSONObject;

import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.event.PhaseId;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormatSymbols;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Nick Belaevski - mailto:nbelaevski@exadel.com created 08.06.2007
 * 
 */
public class CalendarRendererBase extends TemplateEncoderRendererBase {

	protected static final String MONTH_LABELS_SHORT = "monthLabelsShort";

	protected static final String MONTH_LABELS = "monthLabels";

	protected static final String WEEK_DAY_LABELS_SHORT = "weekDayLabelsShort";

	protected static final String WEEK_DAY_LABELS = "weekDayLabels";

	/**
	 * Constant "hours"
	 */
	private static String HOURS_VALUE = "hours";
	
	/**
	 * Constant "minutes"
	 */
	private static String MINUTES_VALUE = "minutes";

	/**
	 * The constant used to resolve id of hidden input placed on the page
	 * for storing current date in "MM/yyyy" format.
	 * Actual id of hidden input used on the page is #{clientId}InputCurrentDate
	 */
	public static final String CURRENT_DATE_INPUT = "InputCurrentDate";

	public static final String CURRENT_DATE_PRELOAD = "PreloadCurrentDate";

	protected static final String MARKUP_SUFFIX = "Markup";

	public static final String CALENDAR_BUNDLE = "org.richfaces.renderkit.calendar";

	private static final String LOCALES_KEY = "rich:locales";
	private final static Log log = LogFactory
			.getLog(CalendarRendererBase.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ajax4jsf.framework.renderer.RendererBase#getComponentClass()
	 */
	protected Class<? extends UIComponent> getComponentClass() {
		return UICalendar.class;
	}

	public void addPopupToAjaxRendered(FacesContext context,
			UICalendar component) {

		AjaxContext ajaxContext = AjaxContext.getCurrentInstance(context);
		Set<String> ajaxRenderedAreas = ajaxContext.getAjaxRenderedAreas();
		String clientId = component.getClientId(context);

		if (ajaxContext.isAjaxRequest() && ajaxRenderedAreas.contains(clientId)) {
			ajaxRenderedAreas.add(clientId + "Popup");

			ajaxRenderedAreas.add(clientId + "IFrame");

			ajaxRenderedAreas.add(clientId + "Script");
		}
	}

	@Override
	public Object getConvertedValue(FacesContext context, UIComponent component, Object submittedValue)
	throws ConverterException {
	    
	    if ((context == null) || (component == null)) {
	            throw new NullPointerException();
	    }
	    
	    // skip conversion of already converted date
	    if (submittedValue instanceof Date) {
		return (Date) submittedValue;
	    }

	    // Store submitted value in the local variable as a string
	    String newValue = (String) submittedValue;
	    // if we have no local value, try to get the valueExpression.
	    ValueExpression valueExpression = component.getValueExpression("value");
	    Converter converter = null;
	    
	    UICalendar calendar = (UICalendar) component;
	    converter = calendar.getConverter();
	    
	    if ((converter == null) && (valueExpression != null)) {
		Class<? extends Object> converterType = valueExpression.getType(context.getELContext());
		if((converterType != null) && (converterType != Object.class)) {
		    // if getType returns a type for which we support a default
	            // conversion, acquire an appropriate converter instance.
		    converter = getConverterForClass(converterType, context);
		}
	    }

	    // in case the converter hasn't been set, try to use default DateTimeConverter
	    if (converter == null) {		
		converter = createDefaultConverter();
	    }
	    setupDefaultConverter(converter, calendar);
	    
	    return converter.getAsObject(context, component, newValue);
	}
	
    private Converter getConverterForClass(Class <? extends Object> converterClass, FacesContext context) {
        if (converterClass == null) {
            return null;
        }

        try {            
            Application application = context.getApplication();
            return (application.createConverter(converterClass));
        } catch (Exception e) {
            log.warn(e.getLocalizedMessage(), e);
			return null;
        }
    }
    
    /**
     * Returns hours and minutes from "defaultTime" attribute as a String with
     * special format: hours:"value_hours",minutes:"value_minutes"
     * 
     * @param component - UICalendar
     * 
     * @return hours and minutes from "defaultTime" attribute
     */
    public Map<String, Object> getPreparedDefaultTime(UICalendar component) {
	    Date date = component.getFormattedDefaultTime();
	    Map<String, Object> result = new HashMap<String, Object>();
		if (date != null) {
		    Calendar calendar = component.getCalendar();
		    calendar.setTime(date);
		    int hours = calendar.get(Calendar.HOUR_OF_DAY);
		    int minutes = calendar.get(Calendar.MINUTE);
		    
		    if (hours != 12 || minutes != 0) {
		    	result.put(HOURS_VALUE, hours);
		    	result.put(MINUTES_VALUE, minutes);
			}
	    }
	    return result;   
    } 

	/**
	 * Overloads getFormattedValue to take a advantage of a previously
	 * obtained converter.
	 * @param context the FacesContext for the current request
	 * @param component UIComponent of interest
	 * @param currentValue the current value of <code>component</code>
	 * @param converter the component's converter
	 * @return the currentValue after any associated Converter has been
	 *  applied
	 *
	 * @throws ConverterException if the value cannot be converted
	 */
	protected String getFormattedValue(FacesContext context, UIComponent component, Object currentValue,
		Converter converter) throws ConverterException {

	    // formatting is supported only for components that support
	    // converting value attributes.
	    if (!(component instanceof UICalendar)) {
		if (currentValue != null) {
		    return currentValue.toString();
		}
		return null;
	    }
	    UICalendar calendar = (UICalendar) component;
	    
	    // if value is null and no converter attribute is specified, then
	    // return a zero length String.
	    if(currentValue == null) {
		return "";
	    }
	    
	    if (converter == null) {
		// If there is a converter attribute, use it to to ask application
		// instance for a converter with this identifier.
		converter = calendar.getConverter();
	    }
	    
	    if (converter == null) {
		// Do not look for "by-type" converters for Strings
		if (currentValue instanceof String) {
		    return (String) currentValue;
		}

		// if converter attribute set, try to acquire a converter
		// using its class type.
		Class<? extends Object> converterType = currentValue.getClass();
		converter = getConverterForClass(converterType, context);

		// if there is no default converter available for this identifier,
		// assume the model type to be String.
		if (converter == null) {
		    // in case the converter hasn't been set, try to use default DateTimeConverter
		    converter = createDefaultConverter();
		}
	    }
	    setupDefaultConverter(converter, calendar);
	    
	    return converter.getAsString(context, calendar, currentValue);
	}


	/**
	 * @param context the FacesContext for the current request
	 * @param component UIComponent of interest
	 * @param currentValue the current value of <code>component</code>
	 *
	 * @return the currentValue after any associated Converter has been
	 *  applied
	 *
	 * @throws ConverterException if the value cannot be converted
	 */
	protected String getFormattedValue(FacesContext context, UIComponent component, Object currentValue)
				throws ConverterException {
	    return getFormattedValue(context, component, currentValue, null);
	}
	
	/**
	 * Creates default <code>DateTimeConverter</code> for the calendar
	 * 
	 * @return created converter
	 */
	protected static Converter createDefaultConverter() {
	    return new DateTimeConverter();
	}

	/**
	 * Setup the default converter provided by JSF API
	 * (<code>DateTimeConverter</code>) with the component settings 
	 * @param converter
	 * @param calendar
	 * @return
	 */
	protected static Converter setupDefaultConverter(Converter converter, UICalendar calendar) {
	    // skip id converter is null
	    if(converter == null) {
		return null;
	    }
	    
	    if(converter instanceof DateTimeConverter) {
		DateTimeConverter defaultConverter = (DateTimeConverter) converter;
		defaultConverter.setPattern(calendar.getDatePattern());
		defaultConverter.setLocale(calendar.getAsLocale(calendar.getLocale()));
		defaultConverter.setTimeZone(calendar.getTimeZone());
	    }
	    
	    return converter;
	}

	@Override
	protected void doDecode(FacesContext context, UIComponent component) {
		// TODO Auto-generated method stub
		super.doDecode(context, component);

		String clientId = component.getClientId(context);

		Map<String, String> requestParameterMap = context.getExternalContext()
				.getRequestParameterMap();

		String currentDateString = (String) requestParameterMap.get(clientId + CURRENT_DATE_INPUT);

		if (currentDateString != null) {
			CurrentDateChangeEvent ev = new CurrentDateChangeEvent(component,
					currentDateString);
			ev.setPhaseId(PhaseId.PROCESS_VALIDATIONS);
			ev.queue();
		}

		if (requestParameterMap.get(clientId + CURRENT_DATE_PRELOAD) != null) {
			// TODO nick - nick - queue this event when ValueChangeEvent is
			// queued?
			new AjaxEvent(component).queue();

			AjaxContext ajaxContext = AjaxContext.getCurrentInstance(context);
			if (ajaxContext.isAjaxRequest(context)) {
				ajaxContext.addAreasToProcessFromComponent(context, component);
			}
		}
		
		String selectedDateString = (String) requestParameterMap.get(clientId
				+ "InputDate");
		if (selectedDateString != null) {
		    	((UICalendar) component).setSubmittedValue(selectedDateString);
		}
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent calendar)
			throws IOException {

	}

	public JSReference getIsDayEnabled(FacesContext context, UIComponent component) {
		UICalendar calendar = (UICalendar) component;
		String isDayEnabled = (String) calendar.getAttributes().get(
				"isDayEnabled");
		if (isDayEnabled != null && isDayEnabled.length() != 0) {
			return new JSReference(isDayEnabled); //new JSFunction(isDayEnabled);
		}
		
		return null;
	}
	
	public JSReference getDayStyleClass(FacesContext context, UIComponent component) {
		UICalendar calendar = (UICalendar) component;
		String dayStyleClass = (String) calendar.getAttributes().get(
				"dayStyleClass");
		if (dayStyleClass != null && dayStyleClass.length() != 0) {
			return new JSReference(dayStyleClass);
		}
		
		return null;
	}

    public String getMarkupScriptBody(FacesContext context, UIComponent component, boolean children)
        throws IOException {

        ResponseWriter writer = context.getResponseWriter();
        Writer dumpingWriter = new StringWriter();
        ResponseWriter clonedWriter = writer.cloneWithWriter(dumpingWriter);
        context.setResponseWriter(clonedWriter);

        writeScriptBody(context, component, children);

        clonedWriter.flush();
        context.setResponseWriter(writer);

        return dumpingWriter.toString();
    }

    public String getOptionalFacetMarkupScriptBody(FacesContext context,
			UIComponent component, String facetName) throws IOException {

		UIComponent facet = component.getFacet(facetName);
		if (facet != null && facet.isRendered()) {
            return getMarkupScriptBody(context, facet, false);
		}

        return null;
	}

	public void dayCellClass(FacesContext context, UIComponent component)
			throws IOException {
		// if cellWidth/Height is set send dayCellClass to script
		String cellwidth = (String) component.getAttributes().get("cellWidth");
		String cellheight = (String) component.getAttributes()
				.get("cellHeight");
		ResponseWriter writer = context.getResponseWriter();
		String clientId = component.getClientId(context);
		String divStyle = "";
		if (cellwidth != null && cellwidth.length() != 0) {
			if (cellwidth.contains("px") || cellwidth.contains("%")) {
				divStyle = divStyle + "width:" + cellwidth + ";";
			} else {
				divStyle = divStyle + "width:" + cellwidth + "px;";
			}
		}
		if (cellheight != null && cellheight.length() != 0) {
			if (cellheight.contains("px") || cellheight.contains("%")) {
				divStyle = divStyle + "height:" + cellheight.toString() + ";";
			} else {
				divStyle = divStyle + "height:" + cellheight.toString() + "px;";
			}
		}

		if (divStyle.length() != 0) {
			writer.startElement("style", component);
			getUtils().writeAttribute(writer, "type", "text/css");
			writer.writeText("." + clientId.replace(':', '_') + "DayCell{"
					+ divStyle + "}", null);
			writer.endElement("style");
		}
	}

	public String getDayCellClass(FacesContext context, UIComponent component) {

		String cellwidth = (String) component.getAttributes().get("cellWidth");
		String cellheight = (String) component.getAttributes().get("cellHeight");
		if (cellwidth != null && cellwidth.length() != 0 || cellheight != null
				&& cellheight.length() != 0) {
			String clientId = component.getClientId(context);
			String value = clientId.replace(':', '_') + "DayCell";
			return value;
		}
		
		return null;
	}

    public void writeFacetMarkup(FacesContext context, UIComponent component) throws IOException {
        Map<String, String> jsonMap = new HashMap();
        if (component.getChildCount() != 0) {
            jsonMap.put("dayListMarkup", getMarkupScriptBody(context, component, true));
        }

        addFacetMarkupScriptBody(context, component, jsonMap, "optionalHeader");
        addFacetMarkupScriptBody(context, component, jsonMap, "optionalFooter");

        addFacetMarkupScriptBody(context, component, jsonMap, "weekDay");
        addFacetMarkupScriptBody(context, component, jsonMap, "weekNumber");
        addFacetMarkupScriptBody(context, component, jsonMap, "header");
        addFacetMarkupScriptBody(context, component, jsonMap, "footer");

        context.getResponseWriter().write(new JSONObject(jsonMap).toString());
    }

    private void addFacetMarkupScriptBody(FacesContext context, UIComponent component, Map<String, String> jsonMap, String facetName) throws IOException {
        String res = getOptionalFacetMarkupScriptBody(context, component, facetName);
        if (res != null) {
            jsonMap.put(facetName, res);
	}
    }

	public void writePreloadBody(FacesContext context, UICalendar calendar)
			throws IOException {
		Object preload = calendar.getPreload();
		if (preload != null) {
			ResponseWriter writer = context.getResponseWriter();
			writer.write(ScriptUtils.toScript(preload));
		}
	}

	public Object getSubmitFunction(FacesContext context, UICalendar calendar)
			throws IOException {
		
		if (!UICalendar.AJAX_MODE.equals(calendar.getAttributes().get("mode"))) return null;

		JSFunction ajaxFunction = AjaxRendererUtils.buildAjaxFunction(calendar,
				context, AjaxRendererUtils.AJAX_FUNCTION_NAME);
		ajaxFunction.addParameter(JSReference.NULL);
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(calendar.getClientId(context) + CURRENT_DATE_PRELOAD, Boolean.TRUE);
		
		Map<String, Object> options = AjaxRendererUtils.buildEventOptions(context, calendar, params, true);
		options.put("calendar", JSReference.THIS);

		String oncomplete = AjaxRendererUtils.getAjaxOncomplete(calendar);
		JSFunctionDefinition oncompleteDefinition = new JSFunctionDefinition();
		oncompleteDefinition.addParameter("request");
		oncompleteDefinition.addParameter("event");
		oncompleteDefinition.addParameter("data");
		oncompleteDefinition.addToBody("this.calendar.load(data, true);");
		if (oncomplete != null) {
			oncompleteDefinition.addToBody(oncomplete);
		}

		options.put("oncomplete", oncompleteDefinition);
		JSReference requestValue = new JSReference("requestValue");
		ajaxFunction.addParameter(options);
		JSFunctionDefinition definition = new JSFunctionDefinition();
		definition.addParameter(requestValue);
		definition.addToBody(ajaxFunction);
		return definition;
	}

	public String getInputValue(FacesContext context, UIComponent component) {
		UICalendar calendar = (UICalendar) component;
		// Fix for myFaces 1.1.x RF-997
		String returnValue = null;
		Object value = calendar.getSubmittedValue();
		if (value != null) {
		    try {
			returnValue =  getFormattedValue(context, calendar, value); 
		    } catch (Exception e) {
			if (log.isDebugEnabled()) {
			    log.debug(" InputValue: " + e.toString(), e);
			}
			returnValue = (String)value;
		    }
        	} else {
        	    returnValue =  getFormattedValue(context, calendar, calendar.getValue());  
        	}
		
		return returnValue;
	}

	public void writeDefaultSymbols(FacesContext facesContext, UICalendar calendar)
			throws IOException {
		RequestContext context = RequestContext.getInstance(facesContext);
		Set<String> locales = (Set<String>)context.getAttribute(LOCALES_KEY);
		if (locales == null) {
			locales = new HashSet<String>(1);
			context.setAttribute(LOCALES_KEY, locales);
		}
		String locale = calendar.getAsLocale().toString();
		if (!locales.contains(locale)) {
			ResponseWriter writer = facesContext.getResponseWriter();
			writer.writeText("Richfaces.Calendar.addLocale('" + locale + "', ", null);
			writer.writeText(ScriptUtils.toScript(getDefaultSymbolsMap(calendar)), null);
			writer.writeText(");\n", null);
			locales.add(locale);
		}
	}

	private static String[] shiftDates(int minimum, int maximum, String[] labels) {
		if (minimum == 0 && (maximum - minimum == labels.length - 1)) {
			return labels;
		}

		String[] shiftedLabels = new String[maximum - minimum + 1];
		System.arraycopy(labels, minimum, shiftedLabels, 0, maximum - minimum
				+ 1);

		return shiftedLabels;
	}

	protected Map<String, Object> getSymbolsMap(FacesContext facesContext, UICalendar calendar) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		RendererUtils utils = getUtils();
		utils.addToScriptHash(map, WEEK_DAY_LABELS, ComponentUtil.asArray(calendar.getWeekDayLabels()));
		utils.addToScriptHash(map, WEEK_DAY_LABELS_SHORT, ComponentUtil.asArray(calendar.getWeekDayLabelsShort()));
		utils.addToScriptHash(map, MONTH_LABELS, ComponentUtil.asArray(calendar.getMonthLabels()));
		utils.addToScriptHash(map, MONTH_LABELS_SHORT, ComponentUtil.asArray(calendar.getMonthLabelsShort()));
		int day = calendar.getFirstWeekDay();
		if (0 <= day && day <= 6) {
			utils.addToScriptHash(map, "firstWeekDay", day);			
		} else if (day != Integer.MIN_VALUE) {
			facesContext.getExternalContext()
				.log(day + " value of firstWeekDay attribute is not a legal one for component: "
						+ MessageUtil.getLabel(facesContext, calendar) + ". Default value was applied.");
		}

		return map;
	}

	protected Map<String, Object> getDefaultSymbolsMap(UICalendar calendar) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		Locale locale = calendar.getAsLocale();
		Calendar cal = calendar.getCalendar();
		int maximum = cal.getActualMaximum(Calendar.DAY_OF_WEEK);
		int minimum = cal.getActualMinimum(Calendar.DAY_OF_WEEK);

		int monthMax = cal.getActualMaximum(Calendar.MONTH);
		int monthMin = cal.getActualMinimum(Calendar.MONTH);

		DateFormatSymbols symbols = new DateFormatSymbols(locale);
		String[] weekDayLabels = symbols.getWeekdays();
		weekDayLabels = shiftDates(minimum, maximum, weekDayLabels);

		String[] weekDayLabelsShort = symbols.getShortWeekdays();
		weekDayLabelsShort = shiftDates(minimum, maximum, weekDayLabelsShort);

		String[] monthLabels  = symbols.getMonths();
		monthLabels = shiftDates(monthMin, monthMax, monthLabels);

		String[] monthLabelsShort  = symbols.getShortMonths();
		monthLabelsShort = shiftDates(monthMin, monthMax, monthLabelsShort);

		map.put(WEEK_DAY_LABELS, weekDayLabels);
		map.put(WEEK_DAY_LABELS_SHORT, weekDayLabelsShort);
		map.put(MONTH_LABELS, monthLabels);
		map.put(MONTH_LABELS_SHORT, monthLabelsShort);
		map.put("minDaysInFirstWeek", cal.getMinimalDaysInFirstWeek());
		map.put("firstWeekDay", cal.getFirstDayOfWeek() - cal.getActualMinimum(Calendar.DAY_OF_WEEK));

		return map;
	}

	public String getCurrentDateAsString(FacesContext context,
			UICalendar calendar, Date date) throws IOException {

		Format formatter = new SimpleDateFormat("MM/yyyy");
		return formatter.format(date);
	}

	public Object getCurrentDate(FacesContext context, UICalendar calendar,
			Date date) throws IOException {
		return formatDate(date);
	}

	public Object getSelectedDate(FacesContext context, UICalendar calendar)
			throws IOException {
	     	Object returnValue = null;
	     	
	     	if(calendar.isValid()) {
	     	    Date date;
	     	    Object value = calendar.getValue();
	     	    date = calendar.getAsDate(value);
	     	    if(date != null) {
	     		returnValue = formatSelectedDate(calendar.getTimeZone(), date);  
	     	    }
	     	}
	     	return returnValue;    
	}

	public static Object formatDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		JSFunction result = new JSFunction("new Date");
		result.addParameter(Integer.valueOf(calendar.get(Calendar.YEAR)));
		result.addParameter(Integer.valueOf(calendar.get(Calendar.MONTH)));
		result.addParameter(Integer.valueOf(calendar.get(Calendar.DATE)));

		return result;
	}

	public static Object formatSelectedDate(TimeZone timeZone, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(timeZone);
		calendar.setTime(date);
		JSFunction result = new JSFunction("new Date");
		result.addParameter(Integer.valueOf(calendar.get(Calendar.YEAR)));
		result.addParameter(Integer.valueOf(calendar.get(Calendar.MONTH)));
		result.addParameter(Integer.valueOf(calendar.get(Calendar.DATE)));
		result
				.addParameter(Integer.valueOf(calendar
						.get(Calendar.HOUR_OF_DAY)));
		result.addParameter(Integer.valueOf(calendar.get(Calendar.MINUTE)));
		result.addParameter(new Integer(0));
		return result;
	}

	public Map<String, Object> getLabels(FacesContext context, UICalendar calendar) {

		ResourceBundle bundle1 = null;
		ResourceBundle bundle2 = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String messageBundle = context.getApplication().getMessageBundle();
		Object locale = calendar.getLocale();
		if (null != messageBundle) {
			bundle1 = ResourceBundle.getBundle(messageBundle, calendar.getAsLocale(locale), loader);
		} 
		try {
			bundle2 = ResourceBundle.getBundle(CALENDAR_BUNDLE, calendar.getAsLocale(locale), loader);

		} catch (MissingResourceException e) {
				//No external bundle was found, ignore this exception.				
		}
		
		Map<String, Object> labels = new HashMap<String, Object>();
		
		if (null != bundle1 || null != bundle2) {
			// TODO: make one function call
			String[] names = {"apply", "today", "clean", "cancel", "ok", "close"};
			RendererUtils utils= getUtils();
			
			for (String name : names) {
			    String label = null;
			    String bundleKey = "RICH_CALENDAR_" + name.toUpperCase() + "_LABEL";
			    
			    if (bundle1 != null) {
			    	try {
			    		label = bundle1.getString(bundleKey);
			    	} catch (MissingResourceException mre) {
				    // Current key was not found, ignore this exception;
			    	}
			    }
			    
			    // Current key wasn't found in application bundle, use CALENDAR_BUNDLE,
			    // if it is not null
			    if((label == null) && (bundle2 != null)) {
			    	try {
			    		label = bundle2.getString(bundleKey);
			    	} catch (MissingResourceException mre) {
				    // Current key was not found, ignore this exception;
			    	}
			    }
			    utils.addToScriptHash(labels, name, label); 			
			}
		}
		return labels;
	}
}
