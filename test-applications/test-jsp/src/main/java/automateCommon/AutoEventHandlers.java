/**
 * License Agreement.
 *
 * Ajax4jsf 1.1 - Natural Ajax for Java Server Faces (JSF)
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

package automateCommon;

import java.util.ArrayList;

import javax.faces.component.html.HtmlPanelGrid;

import applicationStructure.DrawGrids;
import applicationStructure.PageContent;

import util.BeanManager;
import util.parser.Attribute;
import util.parser.TLDParser;

/**
 * The AutoEventHandlers bean is used for automated test of the events'
 * handlers.
 */
public class AutoEventHandlers {

	/** The result event handlers grid. */
	private HtmlPanelGrid resultEventHandlersGrid = null;

	/** The all event handlers for selected component. */
	private ArrayList<Attribute> allEventHandlers;

	/**
	 * The drawGrids object generates the results panel grid using DrawGrids
	 * class methods.
	 */
	DrawGrids drawGrids = new DrawGrids();

	/**
	 * Draw event handlers grid for current component.
	 */
	public void drawEventHandlersGrid() {
		PageContent pageContentBean = (PageContent) BeanManager
				.getManagedBeanFromSession("pageContent");

		String component = pageContentBean.getComponent();

		TLDParser parser = new TLDParser(component);
		allEventHandlers = parser.getAllAttributes().getHandlers();

		drawGrids.drawEventHandlersGrid(resultEventHandlersGrid,
				allEventHandlers);
	}

	/**
	 * Reset results panel grid when different component is selected.
	 */
	public void reset() {
		if (null != resultEventHandlersGrid)
			resultEventHandlersGrid.getChildren().clear();
		allEventHandlers = null;
	}

	/**
	 * Gets the result event handlers grid.
	 * 
	 * @return the result event handlers grid
	 */
	public HtmlPanelGrid getResultEventHandlersGrid() {
		return resultEventHandlersGrid;
	}

	/**
	 * Sets the result event handlers grid.
	 * 
	 * @param resultEventHandlersGrid
	 *            the new result event handlers grid
	 */
	public void setResultEventHandlersGrid(HtmlPanelGrid resultEventHandlersGrid) {
		this.resultEventHandlersGrid = resultEventHandlersGrid;
	}
}
