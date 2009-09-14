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

package org.richfaces.renderkit.html;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.renderkit.HeaderResourcesRendererBase;
import org.ajax4jsf.renderkit.RendererUtils;
import org.ajax4jsf.renderkit.RendererUtils.HTML;
import org.ajax4jsf.renderkit.RendererUtils.ScriptHashVariableWrapper;
import org.ajax4jsf.resource.InternetResource;
import org.richfaces.component.UIMenuGroup;
import org.richfaces.component.UIMenuItem;
import org.richfaces.component.UIMenuSeparator;
import org.richfaces.component.util.HtmlUtil;

/**
 * @author Maksim Kaszynski
 * 
 */
public abstract class AbstractMenuRenderer extends HeaderResourcesRendererBase {
    
    private final InternetResource[] scripts = {
            new org.ajax4jsf.javascript.PrototypeScript(),
            new org.ajax4jsf.javascript.AjaxScript(),
            getResource("scripts/menu.js") };
    
    @Override
    protected InternetResource[] getScripts() {
        return scripts;
    }
    
    @Override
    protected InternetResource[] getStyles() {
        return super.getStyles();
    }
    
    public void encodeScript(FacesContext context, UIComponent component)
            throws IOException {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append(getLayerScript(context, component));
        
        List<UIComponent> children = component.getChildren();
        List<Object> scriptObjects = new ArrayList<Object>(children.size());
        for (Iterator<UIComponent> it = children.iterator(); it.hasNext();) {
        	Object scriptObject = getItemScriptObject(context, it.next());
        	if(scriptObject != null) {
        		scriptObjects.add(scriptObject);
        	}
        }
		if (!scriptObjects.isEmpty()) {
			  buffer.append(".");
			  JSFunction function = new JSFunction("addItems");
			  function.addParameter(scriptObjects);
			  function.appendScript(buffer);
		}
        buffer.append(";");
        context.getResponseWriter().write(buffer.toString());
    }
    
    protected abstract JSFunction getMenuScriptFunction(FacesContext context, UIComponent component);

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
		if (component instanceof UIMenuGroup) {
			options = new HashMap<String, Object>();
			buffer.append(".");
			JSFunction subMenuFunction = new JSFunction("asSubMenu");
			subMenuFunction.addParameter(component.getParent().getClientId(context)+"_menu");
			subMenuFunction.addParameter(component.getClientId(context));
			utils.addToScriptHash(options, "evtName", component.getAttributes().get("event"), "onmouseover"); 
			utils.addToScriptHash(options, "direction", component.getAttributes().get("direction"), "auto"); 
			utils.addToScriptHash(options, "onopen", component.getAttributes().get("onopen"), null, ScriptHashVariableWrapper.EVENT_HANDLER); 
			utils.addToScriptHash(options, "onclose", component.getAttributes().get("onclose"), null, ScriptHashVariableWrapper.EVENT_HANDLER); 
		    if (!options.isEmpty()) {
		    	subMenuFunction.addParameter(options);
		    }
			subMenuFunction.appendScript(buffer);
		} else {
			buffer.append(".");
			JSFunction menuFunction = getMenuScriptFunction(context, component);
			Map<String, Object> menuOptions = getMenuOptions(component);
			if (!menuOptions.isEmpty()) {
				menuFunction.addParameter(menuOptions);
			}
			menuFunction.appendScript(buffer);
		}
		return buffer.toString();
    }
    
	protected Map<String, Object> getMenuOptions(UIComponent component) {
        Map<String, Object> options = new HashMap<String, Object>();
		RendererUtils utils = getUtils();
		utils.addToScriptHash(options, "direction", component.getAttributes().get("direction"), "auto"); 
		utils.addToScriptHash(options, "jointPoint", component.getAttributes().get("jointPoint"), "auto"); 
		utils.addToScriptHash(options, "verticalOffset", component.getAttributes().get("verticalOffset"), "0"); 
		utils.addToScriptHash(options, "horizontalOffset", component.getAttributes().get("horizontalOffset"), "0"); 
		utils.addToScriptHash(options, "oncollapse", component.getAttributes().get("oncollapse"), null, ScriptHashVariableWrapper.EVENT_HANDLER); 
		utils.addToScriptHash(options, "onexpand", component.getAttributes().get("onexpand"), null, ScriptHashVariableWrapper.EVENT_HANDLER); 
		utils.addToScriptHash(options, "onitemselect", component.getAttributes().get("onitemselect"), null, ScriptHashVariableWrapper.EVENT_HANDLER); 
		utils.addToScriptHash(options, "ongroupactivate", component.getAttributes().get("ongroupactivate"), null, ScriptHashVariableWrapper.EVENT_HANDLER);
		
		utils.addToScriptHash(options, "selectItemClass", component.getAttributes().get("selectItemClass"));
		utils.addToScriptHash(options, "itemClass", component.getAttributes().get("itemClass"));
		utils.addToScriptHash(options, "selectItemStyle", component.getAttributes().get("selectItemStyle"));
		utils.addToScriptHash(options, "itemStyle", component.getAttributes().get("itemStyle"));
		return options;
	}

	protected Object getItemScriptObject(FacesContext context, UIComponent kid) {
		if (!kid.isRendered()) {
			return null;
		}
		
        String itemId = null;
        List<Object> scriptObject = null;
        boolean closeOnClick = true;
        Integer flagGroup = null;
        boolean disabled = false;
        if (kid instanceof UIMenuItem) {
            UIMenuItem menuItem = (UIMenuItem) kid;
            itemId = kid.getClientId(context);
            disabled = menuItem.isDisabled();
            if (disabled) {
                closeOnClick = false;
            }
        } else if (kid instanceof UIMenuGroup) {
            UIMenuGroup menuGroup = (UIMenuGroup) kid;
            itemId = kid.getClientId(context);
            closeOnClick = false;
            if ((disabled = menuGroup.isDisabled())) {
                flagGroup = Integer.valueOf(2);
            } else {
                flagGroup = Integer.valueOf(1);
            }
        }
        if (itemId != null) {
        	scriptObject = new ArrayList<Object>();
            Map<String, Object> options = new HashMap<String, Object>(2);
            RendererUtils utils = getUtils();
            scriptObject.add(itemId);
            
			utils.addToScriptHash(options, "onmouseout", kid.getAttributes().get("onmouseout"), null, ScriptHashVariableWrapper.EVENT_HANDLER); 
			utils.addToScriptHash(options, "onmouseover", kid.getAttributes().get("onmouseover"), null, ScriptHashVariableWrapper.EVENT_HANDLER); 
			utils.addToScriptHash(options, "onselect", kid.getAttributes().get("onselect"), null, ScriptHashVariableWrapper.EVENT_HANDLER); 
			utils.addToScriptHash(options, "closeOnClick", closeOnClick, "true"); 
			utils.addToScriptHash(options, "flagGroup", flagGroup); 
			utils.addToScriptHash(options, "styleClass", kid.getAttributes().get("styleClass")); 
			utils.addToScriptHash(options, "style", kid.getAttributes().get("style")); 
			utils.addToScriptHash(options, "disabledItemClass", kid.getAttributes().get("disabledItemClass")); 
			utils.addToScriptHash(options, "disabledItemStyle", kid.getAttributes().get("disabledItemStyle")); 
			utils.addToScriptHash(options, "labelClass", kid.getAttributes().get("labelClass")); 
			utils.addToScriptHash(options, "selectedLabelClass", kid.getAttributes().get("selectedLabelClass")); 
			utils.addToScriptHash(options, "disabledLabelClass", kid.getAttributes().get("disabledLabelClass")); 
			utils.addToScriptHash(options, "selectClass", kid.getAttributes().get("selectClass")); 
			utils.addToScriptHash(options, "selectStyle", kid.getAttributes().get("selectStyle")); 
			utils.addToScriptHash(options, "iconClass", kid.getAttributes().get("iconClass")); 
			utils.addToScriptHash(options, "disabled", disabled); 
			if (!options.isEmpty()) {
				scriptObject.add(options);
			}
        }
        
        return scriptObject;
    }
    
    public boolean getRendersChildren() {
        return true;
    }
    
    public void encodeChildren(FacesContext context, UIComponent component)
            throws IOException {
        List<UIMenuGroup> flatListOfNodes = new LinkedList<UIMenuGroup>();
        String width = (String) component.getAttributes().get("popupWidth");
        
        flatten(component.getChildren(), flatListOfNodes);
        processLayer(context, component, width);
        
        for (Iterator<UIMenuGroup> iter = flatListOfNodes.iterator(); iter.hasNext();) {
            UIMenuGroup node = iter.next();
            processLayer(context, node, width);
        }
    }
    
    public void processLayer(FacesContext context, UIComponent layer,
            String width) throws IOException {
    	
    	if (!layer.isRendered()) {
    		return ;
    	}
    	
    	if (layer instanceof UIMenuGroup && ((UIMenuGroup) layer).isDisabled()) {
    		return ;
    	}

        String clientId = layer.getClientId(context);
        
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement(HTML.DIV_ELEM, layer);
        writer.writeAttribute(HTML.id_ATTRIBUTE, clientId + "_menu", null);
        processLayerStyles(context, layer, writer);
        writer.startElement(HTML.DIV_ELEM, layer);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "rich-menu-list-bg", null);
        encodeItems(context, layer);
        
        writer.startElement(HTML.DIV_ELEM, layer);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "rich-menu-list-strut",
                null);
        writer.startElement(HTML.DIV_ELEM, layer);
        writer.writeAttribute(HTML.class_ATTRIBUTE, "rich-menu-list-strut",
                null);
        writer.writeAttribute(HTML.style_ATTRIBUTE, width != null
                && width.length() > 0 ? "width: " + HtmlUtil.qualifySize(width)
                : "", null);
        writer.write("&#160;");
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.DIV_ELEM);
        writer.endElement(HTML.DIV_ELEM);
        
        writer.startElement(HTML.SCRIPT_ELEM, layer);
        writer.writeAttribute(HTML.id_ATTRIBUTE, clientId + "_menu_script",
                null);
        writer.writeAttribute(HTML.TYPE_ATTR, "text/javascript", null);
        encodeScript(context, layer);
        writer.endElement(HTML.SCRIPT_ELEM);
    }
    
    public void encodeItems(FacesContext context, UIComponent component)
            throws IOException {
        List<UIComponent> kids = component.getChildren();
        Iterator<UIComponent> it = kids.iterator();
        while (it.hasNext()) {
            UIComponent kid = (UIComponent) it.next();
            if (kid instanceof UIMenuGroup || kid instanceof UIMenuItem
                    || kid instanceof UIMenuSeparator) {
                renderChild(context, kid);
            }
        }
    }
    
    private void flatten(List<UIComponent> kids, List<UIMenuGroup> flatList) {
        if (kids != null) {
            for (Iterator<UIComponent> iter = kids.iterator(); iter.hasNext();) {
                UIComponent kid = (UIComponent) iter.next();
                if (kid instanceof UIMenuGroup) {
                    UIMenuGroup node = (UIMenuGroup) kid;
                    
                    if (node.isRendered()) {
                        flatList.add(node);
                        flatten(node.getChildren(), flatList);
                    }
                }
            }
        }
    }
    
    protected abstract void processLayerStyles(FacesContext context,
            UIComponent layer, ResponseWriter writer) throws IOException;
    
}
