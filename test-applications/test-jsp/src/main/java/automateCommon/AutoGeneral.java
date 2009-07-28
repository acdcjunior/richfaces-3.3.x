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

import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import components.ImmediateComponentBean;

import util.BeanManager;
import util.parser.Attribute;
import util.parser.TLDParser;
import applicationStructure.DrawGrids;
import applicationStructure.PageContent;

/**
 * The AutoGeneral bean is used for automated test of the general attributes.
 */
public class AutoGeneral {

	/**
	 * The Constant autoGeneralAttrs keeps names of the attributes which can be
	 * tested automatically.
	 */
	private static final String[] autoGeneralAttrs = { "immediate",
			"validator", "validatorMessage", "required", "requiredMessage",
			"converter", "converterMessage", "valueChangeListener" };

	/** The result auto general grid. */
	private HtmlPanelGrid resultAutoGeneralGrid = null;

	/** The all automated general attributes for selected component. */
	private ArrayList<Attribute> allAutoGeneral = new ArrayList<Attribute>();

	/** The current component. */
	private String component = "";

	/**
	 * The drawGrids object generates the results panel grid using DrawGrids
	 * class methods.
	 */
	DrawGrids drawGrids = new DrawGrids();

	/**
	 * Draw automated general attributes grid for current component.
	 */
	public void drawAutoGeneralGrid() {
		PageContent pageContentBean = (PageContent) BeanManager
				.getManagedBeanFromSession("pageContent");

		component = pageContentBean.getComponent();

		TLDParser parser = new TLDParser(component);
		ArrayList<Attribute> allGeneral = parser.getAllAttributes()
				.getCommonAttributes();

		allAutoGeneral.clear();
		for (String attr : autoGeneralAttrs) {
			int index = allGeneral.indexOf(new Attribute(attr));
			if (index > -1) {
				allAutoGeneral.add(allGeneral.get(index));
			}
		}

		drawGrids.drawAutoGeneralGrid(resultAutoGeneralGrid, allAutoGeneral);
	}

	/**
	 * Runs the automated tests for current component.
	 */
	public void testAutoGeneral() {
		if (!allAutoGeneral.isEmpty()) {
			for (Attribute attr : allAutoGeneral) {
				if (attr.getName().equalsIgnoreCase("validator")
						|| attr.getName().equalsIgnoreCase("validatorMessage")
						|| attr.getName().equalsIgnoreCase("required")
						|| attr.getName().equalsIgnoreCase("requiredMessage")
						|| attr.getName().equalsIgnoreCase("immediate")) {

					Validation validationBean = (Validation) BeanManager
							.getManagedBeanFromSession("validation");
					ImmediateComponentBean compInt = (ImmediateComponentBean) BeanManager
							.getManagedBeanFromSession(component);

					if (attr.getName().equalsIgnoreCase("validator"))
						// run test for "validator" attribute
						validationBean.validatorCheck(attr, compInt
								.isImmediate());
					else if (attr.getName()
							.equalsIgnoreCase("validatorMessage"))
						// run test for "validatorMessage" attribute
						validationBean.validatorMessageCheck(attr);
					else if (attr.getName().equalsIgnoreCase("required"))
						// run test for "required" attribute
						validationBean.requiredCheck(attr);
					else if (attr.getName().equalsIgnoreCase("requiredMessage"))
						// run test for "requiredMessage" attribute
						validationBean.requiredMessageCheck(attr);
					else if (attr.getName().equalsIgnoreCase("immediate"))
						// run test for "immediate" attribute
						validationBean.immediateCheck(attr, compInt
								.isImmediate());
				} else if (attr.getName().equalsIgnoreCase("converter")
						|| attr.getName().equalsIgnoreCase("converterMessage")) {

					Convertion convertionBean = (Convertion) BeanManager
							.getManagedBeanFromSession("convertion");
					ImmediateComponentBean compInt = (ImmediateComponentBean) BeanManager
							.getManagedBeanFromSession(component);

					if (attr.getName().equalsIgnoreCase("converter"))
						// run test for "converter" attribute
						convertionBean.converterCheck(attr, compInt
								.isImmediate());
					else if (attr.getName()
							.equalsIgnoreCase("converterMessage"))
						// run test for "converterMessage" attribute
						convertionBean.converterMessageCheck(attr);
				} else if (attr.getName().equalsIgnoreCase("valueChangeListener")) {
					Listeners listenersBean = (Listeners) BeanManager
							.getManagedBeanFromSession("listeners");
					ImmediateComponentBean compInt = (ImmediateComponentBean) BeanManager
							.getManagedBeanFromSession(component);
					// run test for "valueChangeListener" attribute
					listenersBean.valueChangeListenerCheck(attr, compInt.isImmediate());
				}
			}
			// redraw the results grid with the last test results
			drawGrids
					.drawAutoGeneralGrid(resultAutoGeneralGrid, allAutoGeneral);
		}
	}

	/**
	 * Reset the following when different component is selected:
	 * 	1. results panel grid
	 * 	2. selected component
	 * 	3. submitted and local values of the hidden inputs which are used 
	 * 	   for messages testing
	 * 	4. validation bean
	 * 	5. convertion bean
	 */
	public void reset() {
		if (null != resultAutoGeneralGrid)
			resultAutoGeneralGrid.getChildren().clear();
		allAutoGeneral.clear();
		component = "";
		Validation validationBean = (Validation) BeanManager
				.getManagedBeanFromSession("validation");
		if (null != validationBean)
			validationBean.reset();

		Convertion convertionBean = (Convertion) BeanManager
				.getManagedBeanFromSession("convertion");
		if (null != convertionBean)
			convertionBean.reset();
		
		Listeners listenersBean = (Listeners) BeanManager
				.getManagedBeanFromSession("listeners");
		listenersBean.reset();

		UIViewRoot vr = FacesContext.getCurrentInstance().getViewRoot();
		UIInput inp = (UIInput) vr
				.findComponent("formID:autoGeneralSubviewID:hiddenValidatorInput");
		if (null != inp) {
			inp.setSubmittedValue(null);
			inp.setLocalValueSet(false);
		}
		inp = (UIInput) vr
				.findComponent("formID:autoGeneralSubviewID:hiddenConverterInput");
		if (null != inp) {
			inp.setSubmittedValue(null);
			inp.setLocalValueSet(false);
		}
		inp = (UIInput) vr
				.findComponent("formID:autoGeneralSubviewID:hiddenRequiredInput");
		if (null != inp) {
			inp.setSubmittedValue(null);
			inp.setLocalValueSet(false);
		}
	}

	/**
	 * Gets the result automated general attributes grid.
	 * 
	 * @return the result auto general grid
	 */
	public HtmlPanelGrid getResultAutoGeneralGrid() {
		return resultAutoGeneralGrid;
	}

	/**
	 * Sets the result automated general attributes grid.
	 * 
	 * @param resultAutoGeneralGrid
	 *            the new result auto general grid
	 */
	public void setResultAutoGeneralGrid(HtmlPanelGrid resultAutoGeneralGrid) {
		this.resultAutoGeneralGrid = resultAutoGeneralGrid;
	}
}
