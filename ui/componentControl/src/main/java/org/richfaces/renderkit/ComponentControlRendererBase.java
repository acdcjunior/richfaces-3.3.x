/**
 * License Agreement.
 *
 * Rich Faces - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
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

import java.io.IOException;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.javascript.JSFunctionDefinition;
import org.ajax4jsf.javascript.JSReference;
import org.ajax4jsf.renderkit.HeaderResourcesRendererBase;
import org.ajax4jsf.renderkit.RendererUtils.HTML;
import org.ajax4jsf.resource.InternetResource;
import org.richfaces.component.UIComponentControl;
import org.richfaces.component.util.HtmlUtil;


public class ComponentControlRendererBase  extends HeaderResourcesRendererBase {

	/**
	 * Constant for "immediate" attach timing option
	 */
	private static final String IMMEDIATE = "immediate";

	/**
	 * Constant for "onAvailable" attach timing option
	 */
	private static final String ON_AVAILABLE = "onavailable";

	/**
	 * Constant for "onload" attach timing option
	 */
	private static final String ON_LOAD = "onload";

	protected Class<UIComponentControl> getComponentClass() {
		return UIComponentControl.class;
	}

	/**
	 * Additional scripts.
	 */
	private final InternetResource[] additionalScripts = { new org.ajax4jsf.javascript.PrototypeScript(),
			new org.ajax4jsf.javascript.AjaxScript(), getResource("/org/richfaces/renderkit/html/scripts/available.js") };

	/**
	 * Perform validation of the component control configuration. Throws FacesException in case validation fails.
	 * @param clientId - id of the component
	 * @param name - component name
	 * @param attachTiming - timing options
	 * @param forAttr - client ids of target components
	 * @param operation - operation performed on target components
	 */
	protected void checkValidity(String clientId, String name, String attachTiming, String forAttr, String operation) {
		if (!ON_LOAD.equals(attachTiming) && !IMMEDIATE.equals(attachTiming) && !ON_AVAILABLE.equals(attachTiming)) {
			throw new FacesException("The attachTiming attribute of the controlComponent  (id='" + clientId
					+ "') has an invalid value:'" + attachTiming + "'. It may have only the following values: '"
					+ IMMEDIATE + "', '" + ON_LOAD + "', '" + ON_AVAILABLE + "'");
		}

		if (operation == null || operation.trim().length() == 0) {
			throw new FacesException("The operation attribute of the controlComponent (id='" + clientId
					+ "') must be specified");
		}
	}

	protected String replaceClientIds(FacesContext context, UIComponent component, String selector) {
		return HtmlUtil.expandIdSelector(HtmlUtil.idsToIdSelector(selector), component, context);
	}

	/**
	 * Gets additional scripts.
	 * 
	 * @return array of resources
	 */
	protected InternetResource[] getScripts() {
		return additionalScripts;
	}

	/**
	 * Returns String representation of object. If object is null,
	 * returns empty String.
	 * @param obj - object
	 * @return String representation of object.
	 */
	private static String convertToString(Object obj ) {
		return ( obj == null ? "" : obj.toString() );
	}

	public void writeScript(FacesContext context, UIComponent component) throws IOException {

		UIComponentControl componentControl = (UIComponentControl) component;
		ResponseWriter writer = context.getResponseWriter();

		Map<String, Object> attributes = component.getAttributes();

		String name = convertToString(attributes.get("name"));
		String attachTo = convertToString(attributes.get("attachTo"));
		String forAttr = convertToString(attributes.get("for"));
		forAttr = replaceClientIds(context, component, forAttr);
		String operation = convertToString(attributes.get("operation"));
		String attachTiming = componentControl.getAttachTiming();
		checkValidity(componentControl.getClientId(context), name, attachTiming, forAttr, operation);
		String event = convertToString(attributes.get("event"));

		JSFunctionDefinition namedFunction = null;
		JSFunction eventFunction = null;

		if (name.trim().length() != 0) {
			JSFunction subFunction = new JSFunction("Richfaces.componentControl.performOperation");
			subFunction.addParameter(new JSReference("event"));
			componentControl.addOptions(subFunction, event, forAttr, operation);

			namedFunction = new JSFunctionDefinition("event");
			namedFunction.setName(name);
			namedFunction.addToBody(subFunction);
		}

		if (attachTo != null && attachTo.trim().length() != 0 && !"#".equals(attachTo)) {
			boolean isImmediate = attachTiming.equals(IMMEDIATE);
			boolean isOnLoad = attachTiming.equals(ON_LOAD);
			boolean isOnAvailable = attachTiming.equals(ON_AVAILABLE);

			if (isOnLoad) {
				eventFunction = new JSFunction("Richfaces.componentControl.attachReady");
			} else if (isOnAvailable) {
				eventFunction = new JSFunction("Richfaces.componentControl.attachAvailable");
			} else if (isImmediate) {
				eventFunction = new JSFunction("Richfaces.componentControl.attachEvent");
			} else {
				// unknown value of property "attachTiming"
				return;
			}

			// "attachTo" attribute may contain several ids splitted by ","
			String selector = replaceClientIds(context, component, attachTo);
			eventFunction.addParameter(selector);
			componentControl.addOptions(eventFunction, event, forAttr, operation);
		}

		if (eventFunction != null || namedFunction != null) {
			writer.startElement(HTML.SCRIPT_ELEM, componentControl);
			getUtils().writeAttribute(writer, HTML.TYPE_ATTR, "text/javascript");
			if (namedFunction != null) {
				writer.writeText(namedFunction.toScript(), component, null);
				writer.writeText(";", component, null);
			}
			if (eventFunction != null) {
				writer.writeText(eventFunction.toScript(), component, null);
			}
			writer.endElement(HTML.SCRIPT_ELEM);
		}
	}
}