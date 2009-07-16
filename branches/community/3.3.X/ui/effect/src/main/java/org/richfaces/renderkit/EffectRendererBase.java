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
import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.javascript.JSLiteral;
import org.ajax4jsf.javascript.JSReference;
import org.ajax4jsf.renderkit.HeaderResourcesRendererBase;
import org.ajax4jsf.renderkit.RendererUtils;
import org.ajax4jsf.renderkit.RendererUtils.HTML;
import org.ajax4jsf.renderkit.RendererUtils.ScriptHashVariableWrapper;
import org.richfaces.component.UIEffect;
import org.richfaces.json.JSONTokener;

/**
 * @author Nick Belaevski
 *         mailto:nbelaevski@exadel.com
 *         created 09.08.2007
 *
 */
public class EffectRendererBase extends HeaderResourcesRendererBase {

	/* (non-Javadoc)
	 * @see org.ajax4jsf.renderkit.RendererBase#getComponentClass()
	 */
	protected Class getComponentClass() {
		return UIEffect.class;
	}
	
	public String convertParameters(FacesContext context, UIEffect effect) throws IOException {
		String params = effect.getParams();
		if (params == null) {
			return null;
		}

		StringBuffer buffer = new StringBuffer("{" + params + "}");
		try {
			replace(context, effect, buffer);
			return buffer.toString();
		} catch (Exception e) {
			IOException exception = new IOException(e.getMessage());
			exception.initCause(e);
			
			throw exception;
		}
	}
	
	private static void replace(FacesContext context, UIComponent effect, StringBuffer s) throws Exception {
		JSONTokener x = new JSONTokener(s.toString());
        char c;
        String key;

        if (x.nextClean() != '{') {
            throw x.syntaxError("A JSONObject text must begin with '{'");
        }
        for (;;) {
            int idx;
        	c = x.nextClean();
            switch (c) {
            case 0:
                throw x.syntaxError("A JSONObject text must end with '}'");
            case '}':
                return;
            default:
                x.back();
            	idx = x.getMyIndex();
            	//System.out.println(s.substring(x.getMyIndex()));
                key = x.nextValue().toString();
            }

            /*
             * The key is followed by ':'. We will also tolerate '=' or '=>'.
             */

            c = x.nextClean();
            if (c == '=') {
                if (x.next() != '>') {
                    x.back();
                }
            } else if (c != ':') {
                throw x.syntaxError("Expected a ':' after a key");
            }
            
            if ("id".equals(key)) {
            	Object value = x.nextValue();
				UIComponent component = RendererUtils.getInstance().findComponentFor(effect, value.toString());
				if (component != null) {
					value = component.getClientId(context);
				}

            	
            	s.replace(idx, x.getMyIndex(), "'id': '" + value + "'");
            	
            	return ;
            } else {
            	x.nextValue();
            }

            /*
             * Pairs are separated by ','. We will also tolerate ';'.
             */

            switch (x.nextClean()) {
            case ';':
            case ',':
                if (x.nextClean() == '}') {
                    return;
                }
                x.back();
                break;
            case '}':
                return;
            default:
                throw x.syntaxError("Expected a ',' or '}'");
            }
        }
	}	
	
	private String findComponentId(String id, FacesContext context, UIComponent component) {
		String result = null;
		if (! "".equals(id)) {
			UIComponent comp = getUtils().findComponentFor(component,id);
        	if (comp != null) {
        		String cid= comp.getClientId(context);
        		result = cid;
        	}
		}
		return result;
	}
	
	public void writeScript (FacesContext context, UIComponent component) throws IOException {
		
		Map<String, Object> attributes = component.getAttributes();
		String attachObj = "";
		String attachId = "";
		
		String id = (String) attributes.get("for");
		if (! "".equals(id)) {
			attachId = findComponentId(id, context, component);
			if (attachId == null) {
	    		// if no corresponded component id,  may be it is non-jsf id.
	        	// So, returning the id as is
				attachId = id;
				 // it might be the DOM object
				attachObj = id;				
			}
		}
		
		String event = (String)attributes.get("event");
        Boolean needsFunction = new Boolean(! "".equals(attributes.get("name")) && "".equals(event));
        Boolean needsObserver = new Boolean(! "".equals(event) && ! "".equals(attachId) );
        
		if (needsFunction || needsObserver) {
		
			String targetObj = "";
			String targetId = "";
			
			id = (String) attributes.get("targetId");
			targetId = findComponentId(id, context, component);
			if (targetId == null) {
				// if no corresponded component id,  may be it is non-jsf id.
				// So, returning the id as is
				targetId = id;
				// it might be the DOM object
				targetObj = id;
			}
			
			Map<String, Object> options = new HashMap<String, Object>();
			getUtils().addToScriptHash(options, "targetObj", targetObj,  null , ScriptHashVariableWrapper.DEFAULT);
			getUtils().addToScriptHash(options, "attachObj", attachObj,  null , ScriptHashVariableWrapper.DEFAULT);
			getUtils().addToScriptHash(options, "targetId", targetId,  null , ScriptHashVariableWrapper.DEFAULT);
			getUtils().addToScriptHash(options, "attachId", attachId,  null , ScriptHashVariableWrapper.DEFAULT);
			getUtils().addToScriptHash(options, "type", attributes.get("type"),  null , ScriptHashVariableWrapper.DEFAULT);
			getUtils().addToScriptHash(options, "event", attributes.get("event"),  null , ScriptHashVariableWrapper.DEFAULT);
			getUtils().addToScriptHash(options, "name", attributes.get("name"),  null , ScriptHashVariableWrapper.DEFAULT);
			getUtils().addToScriptHash(options, "params", new JSLiteral(convertParameters(context, (UIEffect)component)),  null , ScriptHashVariableWrapper.DEFAULT);
			
			JSFunction function = new JSFunction("Richfaces.effect.create");
			if (!options.isEmpty()) {
				function.addParameter(options);
			}
			
			ResponseWriter writer = context.getResponseWriter();
			
			writer.startElement(HTML.SCRIPT_ELEM, component);
			getUtils().writeAttribute(writer, HTML.TYPE_ATTR, "text/javascript");
			writer.writeText(function.toScript(), component, null);
			writer.endElement(HTML.SCRIPT_ELEM);
		}
	}
}
