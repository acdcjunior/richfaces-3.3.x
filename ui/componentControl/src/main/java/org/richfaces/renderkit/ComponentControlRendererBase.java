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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.javascript.JSFunctionDefinition;
import org.ajax4jsf.javascript.JSReference;
import org.ajax4jsf.renderkit.HeaderResourcesRendererBase;
import org.ajax4jsf.renderkit.RendererUtils;
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
    
    private static String convertToString(List array, String separator ) {
        StringBuilder sb = new StringBuilder();
        boolean close = false;
        Iterator<Object> i = array.iterator();
        while (i.hasNext())
        {
        	Object item = i.next();
        	if (close) {
        		sb.append(separator);
        	} else {
        		close = true;
        	}
        	sb.append(item);
        }
        return sb.toString();
    }
    
    public void getScript(FacesContext context, UIComponent component) throws IOException {
    	
    	UIComponentControl componentControl = (UIComponentControl) component;
    	ResponseWriter writer = context.getResponseWriter();
    	
    	Map<String, Object> attributes = component.getAttributes();
    	
    	JSFunctionDefinition function = null;
    	List<JSFunction> functionArray = new ArrayList<JSFunction>();
    	
    	String name = convertToString(attributes.get("name"));
    	String attachTo = convertToString(attributes.get("attachTo"));
    	String forAttr = convertToString(attributes.get("for"));
    	forAttr = replaceClientIds(context, component, forAttr);
    	String operation = convertToString(attributes.get("operation"));
    	String attachTiming = componentControl.getAttachTiming();
    	checkValidity(componentControl.getClientId(context), name, attachTiming, forAttr, operation);
    	String event = convertToString(attributes.get("event"));
    	
    	String performScript = null;
    	
    	if (!"".equals(name.trim())) {
    		JSFunction subFunction = new JSFunction("Richfaces.componentControl.performOperation");
    		subFunction.addParameter(new JSReference("cevent"));
    		subFunction.addParameter(event);
    		subFunction.addParameter(forAttr);
    		subFunction.addParameter(operation);
    		componentControl.addOptions(subFunction);
    		
    		function = new JSFunctionDefinition("cevent");
    		function.setName(name);
    		function.addToBody(subFunction);
    		performScript = function.toString();
    	}
    	
    	if (attachTo != null && attachTo.trim().length() != 0 && !"#".equals(attachTo)) {
    			
	        boolean isImmediate = attachTiming.equals(IMMEDIATE);
	        boolean isOnLoad = attachTiming.equals(ON_LOAD);
	        boolean isOnAvailable = attachTiming.equals(ON_AVAILABLE);
	        
	        if (!(isImmediate || isOnLoad || isOnAvailable)) {
	            // unknown value of property "attachTiming"
	            return;
	        }
	        
            String pattern = "\\s*,\\s*";
            // "attachTo" attribute may contain several ids splitted by ","
            String[] result = attachTo.split(pattern);
            for (int i = 0; i < result.length; i++) {
            	JSFunction mainFunction = null;
                if (isOnLoad) {
                	mainFunction = new JSFunction("jQuery(document).ready");
                } else if (isOnAvailable) {
                    UIComponent target = RendererUtils.getInstance().findComponentFor(context, component, result[i]);
                    String clientId = (target != null) ? target.getClientId(context) : result[i];
                    mainFunction = new JSFunction("Richfaces.onAvailable",clientId);
                } else if (isImmediate) {
                }
                
                if (mainFunction!=null)
                {
                    JSFunction subFunction = new JSFunction("Richfaces.componentControl.attachEvent");
                    subFunction.addParameter(replaceClientIds(context, component, result[i]));
        	        subFunction.addParameter(event);
            		subFunction.addParameter(forAttr);
            		subFunction.addParameter(operation);
            		componentControl.addOptions(subFunction);
            		
            		function = new JSFunctionDefinition("");
            		function.addToBody(subFunction);
            		
                    mainFunction.addParameter(function);
                	functionArray.add(mainFunction);
                }
            }
    	}
    	
    	if (!functionArray.isEmpty() || performScript!=null) {
    		writer.startElement("script", componentControl);
            getUtils().writeAttribute(writer, "type", "text/javascript");
            writer.writeText("//", null);
            writer.write("<![CDATA[\n");
            if (performScript!= null) {
            	 writer.write(performScript);
            	 writer.write(";");
            }
            if (!functionArray.isEmpty()) {
            	writer.write(convertToString(functionArray, ";"));
            	writer.write(";");
            }
            writer.writeText("\n//", null);
            writer.write("]]>");
            writer.endElement("script");
    	}
    }
}