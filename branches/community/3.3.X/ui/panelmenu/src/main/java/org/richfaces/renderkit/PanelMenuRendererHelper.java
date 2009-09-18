/*
 * JBoss, Home of Professional Open Source
 * Copyright ${year}, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */ 

package org.richfaces.renderkit;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.richfaces.component.UIPanelMenu;
import org.richfaces.context.RequestContext;

/**
 * @author Nick Belaevski
 */
public final class PanelMenuRendererHelper {

	private static final String CONTEXT_ATTRIBUTE_NAME = PanelMenuRendererHelper.class.getName();
	
	public static final class PanelMenuState {
		
		private boolean childMarked = false;
		
		private String selectedChildName;

		public PanelMenuState(String selectedChildName) {
			super();
			this.selectedChildName = selectedChildName;
		}
		
		/**
		 * Queries whether the child with such name is the currently selected item.
		 * Marks in state that selected child has been encoded.
		 * @param childName
		 * @return <code>true</code> if the named is selected, <code>false</code> otherwise
		 */
		public boolean queryAndMarkSelection(String childName) {
			if (selectedChildName == null || selectedChildName.length() == 0) {
				return false;
			}

			boolean selected = false;

			if (selectedChildName.equals(childName)) {
				this.childMarked = true;
				selected = true;
			}
			
			return selected;
		}
		
		/**
		 * @return the selectedChildName
		 */
		public String getSelectedItemInputValue() {
			return childMarked ? selectedChildName : null;
		}
	}
	
	private PanelMenuRendererHelper() {
	}

	public static PanelMenuState getOrCreateState(FacesContext context, UIPanelMenu panelMenu) {
		RequestContext requestContext = RequestContext.getInstance(context);
		Map<String, Object> panelMenuStatesMap = requestContext.getOrCreateNestedMap(CONTEXT_ATTRIBUTE_NAME);
	
		String clientId = panelMenu.getClientId(context);
		PanelMenuState panelMenuState = (PanelMenuState) panelMenuStatesMap.get(clientId);
		if (panelMenuState == null) {
			panelMenuState = new PanelMenuState(panelMenu.getSelectedName());
			panelMenuStatesMap.put(clientId, panelMenuState);
		}
		
		return panelMenuState;
	}
}
