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

package org.richfaces.renderkit.html;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.renderkit.RendererUtils;
import org.ajax4jsf.renderkit.RendererUtils.HTML;
import org.ajax4jsf.renderkit.RendererUtils.ScriptHashVariableWrapper;
import org.richfaces.component.UIDropDownMenu;
import org.richfaces.component.UIMenuGroup;


public class DropDownMenuRendererBase extends AbstractMenuRenderer {

	protected Class<UIDropDownMenu> getComponentClass() {
		return UIDropDownMenu.class;
	}

	@Override
	protected String getLayerScript(FacesContext context, UIComponent component) {
		StringBuffer buffer = new StringBuffer();
        Map<String, Object> options = new HashMap<String, Object>();
        RendererUtils utils = getUtils();
		JSFunction function = new JSFunction("new RichFaces.Menu.Layer");
		function.addParameter(component.getClientId(context)+"_menu");
		utils.addToScriptHash(options, "delay", component.getAttributes().get("showDelay"), "300"); 
		utils.addToScriptHash(options, "hideDelay", component.getAttributes().get("hideDelay"), "300"); 
		utils.addToScriptHash(options, "selectedClass", component.getAttributes().get("selectedLabelClass")); 
      
        if (!options.isEmpty()) {
        	function.addParameter(options);
		}
    
		function.appendScript(buffer);
		
		options = new HashMap<String, Object>();
		
		if (component instanceof UIMenuGroup) {
			  buffer.append(".");
			  function = new JSFunction("asSubMenu");
			  function.addParameter(component.getParent().getClientId(context)+"_menu");
			  function.addParameter(component.getClientId(context));
			  utils.addToScriptHash(options, "evtName", component.getAttributes().get("event"), "onmouseover"); 
			  utils.addToScriptHash(options, "direction", component.getAttributes().get("direction"), "auto"); 
			  utils.addToScriptHash(options, "onopen", component.getAttributes().get("onopen"), null, ScriptHashVariableWrapper.EVENT_HANDLER); 
			  utils.addToScriptHash(options, "onclose", component.getAttributes().get("onclose"), null, ScriptHashVariableWrapper.EVENT_HANDLER); 
		      if (!options.isEmpty()) {
		    	  function.addParameter(options);
		      }
			  function.appendScript(buffer);
		} else {
			  buffer.append(".");
			  function = new JSFunction("asDropDown");
			  function.addParameter(component.getClientId(context));
			  utils.addToScriptHash(options, "onEvt", component.getAttributes().get("event"), "onmouseover"); 
			  utils.addToScriptHash(options, "direction", component.getAttributes().get("direction"), "auto"); 
			  utils.addToScriptHash(options, "jointPoint", component.getAttributes().get("jointPoint"), "auto"); 
			  utils.addToScriptHash(options, "verticalOffset", component.getAttributes().get("verticalOffset"), "0"); 
			  utils.addToScriptHash(options, "horizontalOffset", component.getAttributes().get("horizontalOffset"), "0"); 
			  utils.addToScriptHash(options, "oncollapse", component.getAttributes().get("oncollapse"), null, ScriptHashVariableWrapper.EVENT_HANDLER); 
			  utils.addToScriptHash(options, "onexpand", component.getAttributes().get("onexpand"), null, ScriptHashVariableWrapper.EVENT_HANDLER); 
			  utils.addToScriptHash(options, "onitemselect", component.getAttributes().get("onitemselect"), null, ScriptHashVariableWrapper.EVENT_HANDLER); 
			  utils.addToScriptHash(options, "ongroupactivate", component.getAttributes().get("ongroupactivate"), null, ScriptHashVariableWrapper.EVENT_HANDLER); 
			  utils.addToScriptHash(options, "disabled", component.getAttributes().get("disabled")); 
		      if (!options.isEmpty()) {
		    	  function.addParameter(options);
		      }
			  function.appendScript(buffer);

		}
		
		return buffer.toString();
	}

	public void encodeChildren(FacesContext context, UIComponent component) 
			throws IOException {
		if (!((org.richfaces.component.UIDropDownMenu)component).isDisabled())
			super.encodeChildren(context, component);
	}

	protected void processLayerStyles(FacesContext context, UIComponent layer, ResponseWriter writer) throws IOException {
		writer.writeAttribute(HTML.class_ATTRIBUTE, "rich-menu-list-border", null);
		writer.writeAttribute(HTML.style_ATTRIBUTE, "display: none; z-index: 2;", null);
	}

}
