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

import util.BeanManager;
import util.parser.Attribute;
import util.parser.TLDParser;
import applicationStructure.DrawGrids;
import applicationStructure.PageContent;

/**
 * The AutoStylesClasses bean is used for automated test of the styles and
 * classes.
 */
public class AutoStylesClasses {

	/** The result styles classes grid. */
	private HtmlPanelGrid resultStylesClassesGrid = null;

	/** The all styles and classes for selected component. */
	private ArrayList<Attribute> allStylesClasses;

	/**
	 * The drawGrids object generates the results panel grid using DrawGrids
	 * class methods.
	 */
	DrawGrids drawGrids = new DrawGrids();

	/**
	 * Draw styles and classes grid for current component.
	 */
	public void drawStylesClassesGrid() {
		PageContent pageContentBean = (PageContent) BeanManager
				.getManagedBeanFromSession("pageContent");

		String component = pageContentBean.getComponent();

		TLDParser parser = new TLDParser(component);
		allStylesClasses = parser.getAllAttributes().getStyles();

		drawGrids.drawStylesClassesGrid(resultStylesClassesGrid,
				allStylesClasses);
	}

	/**
	 * Reset results panel grid when different component is selected.
	 */
	public void reset() {
		if (null != resultStylesClassesGrid)
			resultStylesClassesGrid.getChildren().clear();
		allStylesClasses = null;
	}

	/**
	 * Gets the result styles classes grid.
	 * 
	 * @return the result styles classes grid
	 */
	public HtmlPanelGrid getResultStylesClassesGrid() {
		return resultStylesClassesGrid;
	}

	/**
	 * Sets the result styles classes grid.
	 * 
	 * @param resultStylesClassesGrid
	 *            the new result styles classes grid
	 */
	public void setResultStylesClassesGrid(HtmlPanelGrid resultStylesClassesGrid) {
		this.resultStylesClassesGrid = resultStylesClassesGrid;
	}
}
