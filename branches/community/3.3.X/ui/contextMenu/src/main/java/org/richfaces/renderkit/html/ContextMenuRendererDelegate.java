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

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ajax4jsf.javascript.JSFunction;
import org.ajax4jsf.renderkit.RendererUtils.HTML;
import org.ajax4jsf.resource.InternetResource;
import org.richfaces.component.UIContextMenu;

/**
 * @author Maksim Kaszynski
 *
 */
public class ContextMenuRendererDelegate extends AbstractMenuRenderer {

	@Override
	protected JSFunction getMenuScriptFunction(FacesContext context, UIComponent component) {
		return new JSFunction("asContextMenu");
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.renderkit.RendererBase#getComponentClass()
	 */
	protected Class<UIContextMenu> getComponentClass() {
		return UIContextMenu.class;
	}


	protected InternetResource[] getStyles() {
		return super.getStyles();
	}

	protected void processLayerStyles(FacesContext context, UIComponent layer, ResponseWriter writer) throws IOException {
		Object style = layer.getAttributes().get(HTML.style_ATTRIBUTE);
		Object styleClass = layer.getAttributes().get(HTML.STYLE_CLASS_ATTR);
		
		if (null == style) {
			style = "";
		}
		if (null == styleClass) {
			styleClass = "";
		}

        getUtils().writeAttribute(writer, HTML.onmousemove_ATTRIBUTE, layer.getAttributes().get("onmousemove"));
        getUtils().writeAttribute(writer, HTML.onmouseout_ATTRIBUTE, layer.getAttributes().get("onmouseout"));
        getUtils().writeAttribute(writer, HTML.onmouseover_ATTRIBUTE, layer.getAttributes().get("onmouseover"));
        
		writer.writeAttribute(HTML.class_ATTRIBUTE, "rich-menu-list-border " + styleClass, null);
		writer.writeAttribute(HTML.style_ATTRIBUTE, "display: none; z-index: 2; " + style, null);
	}
}
