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

/*
 * Created on 04.07.2006
 */
package org.richfaces.renderkit.html;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ajax4jsf.component.AjaxSupport;
import org.ajax4jsf.context.AjaxContext;
import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.javascript.JSReference;
import org.ajax4jsf.renderkit.AjaxRendererUtils;
import org.ajax4jsf.renderkit.RendererUtils;
import org.richfaces.component.UISimpleTogglePanel;
import org.richfaces.component.util.HtmlUtil;
import org.richfaces.event.SimpleToggleEvent;
import org.richfaces.event.SimpleTogglePanelSwitchEvent;

//public class SimpleTogglePanelRenderer extends AjaxCommandLinkRenderer {

public class SimpleTogglePanelRenderer extends org.ajax4jsf.renderkit.HeaderResourcesRendererBase {

	final static String NONE = "none";
    final static String EMPTY = "";
    
    protected Class<? extends UIComponent> getComponentClass() {
        return UISimpleTogglePanel.class;
    }

    public boolean getRendersChildren() {
        return true;
    }

	public void writeEventHandlerFunction(FacesContext context,
			UIComponent component, String eventName) throws IOException {
		RendererUtils.writeEventHandlerFunction(context, component, eventName);
	}
	
	        
    public void doDecode(FacesContext context, UIComponent component) {

		super.doDecode(context, component);
		ExternalContext exCtx = context.getExternalContext();

		Map <String, String> rqMap = exCtx.getRequestParameterMap();
		Object clnId = rqMap.get(component.getClientId(context));
		UISimpleTogglePanel panel = (UISimpleTogglePanel) component;

		if (clnId != null) {
			boolean currentState = panel.isOpened();
			boolean submittedState = false;

			// enqueue event here for this component or for component with Id
			// taken fro forId attribute
			String switchType = panel.getSwitchType();

			if (UISimpleTogglePanel.CLIENT_SWITCH_TYPE.equals(switchType)) {
				submittedState = Boolean.parseBoolean((String) clnId);
			} else {
				submittedState = !currentState;
			}

			if (currentState != submittedState) {
				SimpleToggleEvent event = new SimpleToggleEvent(panel, submittedState);
				event.queue();

				SimpleTogglePanelSwitchEvent stateEvent = new SimpleTogglePanelSwitchEvent(panel, submittedState);
				stateEvent.queue();
			}

			// in case of "ajax" request and "ajax" switch mode of toggle panel
			// set the regions to be rendered (reRendered) after operating this
			// "ajax" request
			if (AjaxRendererUtils.isAjaxRequest(context)
			        && panel.getSwitchType().equals(UISimpleTogglePanel.AJAX_SWITCH_TYPE)) {
				
			    // add toggle panel itself to rendered list of components
				AjaxRendererUtils.addRegionByName(context, panel, panel.getId());
				
				// add regions specified in the "reRender" attribute of toggle
				// panel to rendered list of components
				AjaxRendererUtils.addRegionsFromComponent(panel, context);

				AjaxContext.getCurrentInstance(context)
				    .addAreasToProcessFromComponent(context, panel);
			}

		}
	}

    public String getdivdisplay(FacesContext context, UIComponent component) {
    	UISimpleTogglePanel simpleTogglePanel = (UISimpleTogglePanel) component;
    	return simpleTogglePanel.isOpened() ? EMPTY : NONE;
// String Switch = Boolean.toString(((UISimpleTogglePanel)
// component).isOpened());
//        if (Switch == null || Switch.equals(Boolean.toString(UISimpleTogglePanel.EXPANDED)))
//        {
//            //xxxx by nick - denis - do not set "block" explicitly - that can break some elements, set "" for display. See Element.show() in prototype.js
//            return "";
//        }
//        return "none";
    }

    public String getOnClick(FacesContext context, UIComponent component) {
        UISimpleTogglePanel tgComp = (UISimpleTogglePanel) component;

        String switchType = tgComp.getSwitchType();
        StringBuffer onClick = new StringBuffer();
    	JSReference eventRef = new JSReference("event");
    	String panelId = tgComp.getClientId(context);

	if (UISimpleTogglePanel.CLIENT_SWITCH_TYPE.equals(switchType)) {
            // Client
            JSFunction function = new JSFunction("SimpleTogglePanelManager.toggleOnClient");
            function.addParameter(eventRef);
            function.addParameter(panelId);
            function.appendScript(onClick);
            onClick.append(";");
            
        } else if (UISimpleTogglePanel.AJAX_SWITCH_TYPE.equals(switchType)) {
//			Ajax
                 	
        	JSFunction function = new JSFunction("SimpleTogglePanelManager.toggleOnAjax");
        	function.addParameter(eventRef);
        	function.addParameter(panelId);
        	function.appendScript(onClick);
        	onClick.append(";");
        
        	JSFunction ajaxFunction = AjaxRendererUtils.buildAjaxFunction(tgComp, context);
        	ajaxFunction.addParameter(AjaxRendererUtils.buildEventOptions(context, tgComp, true));
           	ajaxFunction.appendScript(onClick);
           	
           	if (tgComp instanceof AjaxSupport) {
    			AjaxSupport support = (AjaxSupport) tgComp;
    			if (support.isDisableDefault()) {
    				onClick.append("; return false;");
    			}
    		}

        } else {
            // Server
        	JSFunction function = new JSFunction("SimpleTogglePanelManager.toggleOnServer");	
            function.addParameter(eventRef);
            function.addParameter(panelId);
            function.appendScript(onClick);
            onClick.append(";");        
            //.append(tgComp.getSwitch()==null?"'0'":"'" + tgComp.getSwitch() + "'")
            //.append("")
        }
        return onClick.toString();
    }

    protected String getValueAsString(FacesContext context, UISimpleTogglePanel Panel) {
        return getUtils().getValueAsString(context, Panel);
    }

    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        UISimpleTogglePanel comp = (UISimpleTogglePanel) component;
        String switchType = comp.getSwitchType();
        if (UISimpleTogglePanel.CLIENT_SWITCH_TYPE.equals(switchType) || comp.isOpened()) {
            super.encodeChildren(context, component);
        }
    }
    
    public void encodeDivStart(ResponseWriter writer,
            FacesContext context, UISimpleTogglePanel component) throws IOException {
        String clientId = component.getClientId(context);
        writer.startElement("div", component);
        getUtils().writeAttribute(writer, "class", "rich-stglpanel " + convertToString(component.getAttributes().get("styleClass")) );
        getUtils().writeAttribute(writer, "id", clientId );
        
        String style = convertToString(component.getAttributes().get("style"));
        String width = convertToString(component.getAttributes().get("width"));
        if (!isEmpty(width)) {
            width = "width: " + convertToString(width);
        }
        
        style = HtmlUtil.concatStyles(style, width);
        if (!isEmpty(style)) {
            getUtils().writeAttribute(writer, "style",  style); 
        }

        getUtils().encodeAttributesFromArray(context,component,new String[] {
                "align",
                "dir",
                "lang",
                "onclick",
                "ondblclick",
                "onkeydown",
                "onkeypress",
                "onkeyup",
                "onmousedown",
                "onmousemove",
                "onmouseout",
                "onmouseover",
                "onmouseup",
                "title",
                "xml:lang" });
    }
    
    public void encodeBodyDivStart(ResponseWriter writer,
            FacesContext context, UISimpleTogglePanel component) throws IOException {
        String clientId = component.getClientId(context);
        writer.startElement("div", component);
        getUtils().writeAttribute(writer, "class", "rich-stglpanel-body " + convertToString(component.getAttributes().get("bodyClass")) );
        getUtils().writeAttribute(writer, "id", convertToString(clientId) + "_body" );
        
        String display = "";
        if (!component.isOpened()) {
            display = "display: none";
        }

        String height = convertToString(component.getAttributes().get("height"));
        if (!isEmpty(height)) {
            height = "height: " + height;
        }
        
        String style = HtmlUtil.concatStyles(display, height);
        if (!isEmpty(style)) {
            getUtils().writeAttribute(writer, "style", style);
        }
    }
    
    public void encodeSwitchOnDivStart(ResponseWriter writer,
            FacesContext context, UISimpleTogglePanel component) throws IOException {
        encodeSwitchDivStart(writer, context, component, true);
    }

    public void encodeSwitchOffDivStart(ResponseWriter writer,
            FacesContext context, UISimpleTogglePanel component) throws IOException {
        encodeSwitchDivStart(writer, context, component, false);
    }

    private void encodeSwitchDivStart(ResponseWriter writer,
            FacesContext context, UISimpleTogglePanel component, boolean isSwitchOn)
            throws IOException {
        String clientId = component.getClientId(context);
        writer.startElement("div", component);
        
        getUtils().writeAttribute(writer, "class", "rich-stglpnl-marker" );
        getUtils().writeAttribute(writer, "id", convertToString(clientId) + "_switch_" + (isSwitchOn ? "on" : "off"));
        
        String display = convertToString(getSwitchStatus(context, component, isSwitchOn)).trim();
        if (!isEmpty(display)) {
            display = "display: " + display;
        }
        getUtils().writeAttribute(writer, "style", display);
    }
    
    public String getSwitchStatus(FacesContext context, UIComponent component, boolean isSwitchOn) {
        return ((UISimpleTogglePanel) component).isOpened() ^ isSwitchOn ? NONE : EMPTY;
    }
    
    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    
    private String convertToString(Object obj ) {
        return ( obj == null ? "" : obj.toString() );
    }
    
    public void encodeDivEnd(ResponseWriter writer) throws IOException {
        writer.endElement("div");
    }
}
