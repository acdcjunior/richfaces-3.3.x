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

import javax.faces.component.UICommand;
import javax.faces.event.ActionEvent;

import components.ImmediateComponentBean;
import components.RequiredComponentBean;

import util.BeanManager;

import applicationStructure.PageContent;

/**
 * The Class UtilListeners is used for changing "required" and "immediate"
 * attributes of the tested component. Listeners are added to the corresponding
 * buttons when results grids are generated.
 */
public class UtilListeners {

	/**
	 * Immediate action change "immediate" attribute of the current component.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void immediateAction(ActionEvent actionEvent) {
		PageContent pageContentBean = (PageContent) BeanManager
				.getManagedBeanFromSession("pageContent");
		// get current selected component
		String component = pageContentBean.getComponent();

		ImmediateComponentBean compInt = (ImmediateComponentBean) BeanManager
				.getManagedBeanFromSession(component);
		// change button name
		UICommand c = (UICommand) actionEvent.getComponent();
		if (compInt.isImmediate()) {
			compInt.setImmediate(false);
			c.setValue("Now NOT Immediate");
		} else {
			compInt.setImmediate(true);
			c.setValue("Now Immediate");
		}
	}

	/**
	 * Required action change "immediate" attribute of the current component.
	 * 
	 * @param actionEvent
	 *            the action event
	 */
	public void requiredAction(ActionEvent actionEvent) {
		PageContent pageContentBean = (PageContent) BeanManager
				.getManagedBeanFromSession("pageContent");
		// get current selected component
		String component = pageContentBean.getComponent();

		RequiredComponentBean compInt = (RequiredComponentBean) BeanManager
				.getManagedBeanFromSession(component);
		// change button name
		UICommand c = (UICommand) actionEvent.getComponent();
		if (compInt.isRequired()) {
			compInt.setRequired(false);
			c.setValue("Now NOT Required");
		} else {
			compInt.setRequired(true);
			c.setValue("Now Required");
		}
	}
}
