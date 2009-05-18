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

package org.richfaces.component.util;

import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;

import org.ajax4jsf.renderkit.RendererUtils;

import org.richfaces.component.EnclosingFormRequiredException;

/**
 * @author Filip Antonov - mailto:fantonov@exadel.com
 * created 08.02.2007
 * 
 */
public class FormUtil {
	
	public static void throwEnclFormReqExceptionIfNeed(FacesContext context, UIComponent component) throws EnclosingFormRequiredException {
		UIForm form= RendererUtils.getInstance().getNestingForm(context, component);
		//TODO nick -> nick - switchType checking can be harmful here
		String switchType = (String) component.getAttributes().get("switchType");
		boolean isSwitchTypeClient = switchType == null ? false : switchType.equalsIgnoreCase("client") ;
		if (form == null && !isSwitchTypeClient){
			throw new EnclosingFormRequiredException(component.getClass().toString()+" (id=\"" + component.getId() + "\") did not find parent form.");
		}		
	}

}
