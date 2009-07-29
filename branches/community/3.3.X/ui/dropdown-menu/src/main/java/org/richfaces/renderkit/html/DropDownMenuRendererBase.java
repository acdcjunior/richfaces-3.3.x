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
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.renderkit.RendererUtils;
import org.ajax4jsf.renderkit.RendererUtils.HTML;
import org.richfaces.component.UIDropDownMenu;


public class DropDownMenuRendererBase extends AbstractMenuRenderer {

	protected Class<UIDropDownMenu> getComponentClass() {
		return UIDropDownMenu.class;
	}

	@Override
	protected JSFunction getMenuScriptFunction(FacesContext context, UIComponent component) {
		JSFunction function = new JSFunction("asDropDown");
		function.addParameter(component.getClientId(context));
		return function;
	}

	@Override
	protected Map<String, Object> getMenuOptions(UIComponent component) {
		Map<String, Object> options = super.getMenuOptions(component);
		RendererUtils utils = getUtils();
		utils.addToScriptHash(options, "onEvt", component.getAttributes().get("event"), "onmouseover"); 
		utils.addToScriptHash(options, "disabled", component.getAttributes().get("disabled")); 
		return options;
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
