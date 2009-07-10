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

package applicationStructure;

import java.util.ArrayList;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;

import util.parser.Attribute;

/**
 * The Class DrawGrids provides methods for dynamic generating of the results'
 * grids for events' handlers, styles, classes and common attributes.
 */
public class DrawGrids {

	/**
	 * Draw event handlers grid.
	 * 
	 * @param panelGrid
	 *            the panel grid in which event handlers are drawn
	 * @param events
	 *            the events attributes which are drawn in the panel grid
	 */
	public void drawEventHandlersGrid(final HtmlPanelGrid panelGrid,
			final ArrayList<Attribute> events) {
		panelGrid.getChildren().clear();

		HtmlOutputText attrNameHeader = new HtmlOutputText();
		attrNameHeader.setId("attrEventsHeaderID");
		attrNameHeader.setValue("EventHandlers");
		attrNameHeader.setStyle("font-weight: bold; font-size: large");

		HtmlOutputText attrStatusHeader = new HtmlOutputText();
		attrStatusHeader.setId("attrEventsStatusID");
		attrStatusHeader.setValue("Status");
		attrStatusHeader.setStyle("font-weight: bold; font-size: large");

		panelGrid.getChildren().add(attrNameHeader);
		panelGrid.getChildren().add(attrStatusHeader);

		for (Attribute a : events) {
			HtmlOutputText attrName = new HtmlOutputText();
			attrName.setId(a.getName() + "NameID");
			attrName.setValue(a.getName());
			attrName.setTitle(a.getDescription());
			HtmlInputText attrStatus = new HtmlInputText();
			attrStatus.setValue(a.getStatus());
			// id should not be changed in order that test execution is correct
			attrStatus.setId(a.getName() + "ID");
			attrStatus.setReadonly(true);

			switch (a.getStatus()) {
			case FAILED:
				attrStatus.setStyle("color: red");
				break;
			case PASSED:
				attrStatus.setStyle("color: green");
				break;
			case NOT_TESTED:
				attrStatus.setStyle("color: grey");
				break;
			}

			panelGrid.getChildren().add(attrName);
			panelGrid.getChildren().add(attrStatus);
		}
	}

	/**
	 * Draw styles and classes grid.
	 * 
	 * @param panelGrid
	 *            the panel grid in which styles and classes are drawn
	 * @param styles
	 *            the styles and classes which are drawn in the panel grid
	 */
	public void drawStylesClassesGrid(HtmlPanelGrid panelGrid,
			ArrayList<Attribute> styles) {
		panelGrid.getChildren().clear();

		HtmlOutputText attrNameHeader = new HtmlOutputText();
		attrNameHeader.setId("attrStylesHeaderID");
		attrNameHeader.setValue("StylesClasses");
		attrNameHeader.setStyle("font-weight: bold; font-size: large");

		HtmlOutputText attrStatusHeader = new HtmlOutputText();
		attrStatusHeader.setId("attrStylesStatusID");
		attrStatusHeader.setValue("Status");
		attrStatusHeader.setStyle("font-weight: bold; font-size: large");

		panelGrid.getChildren().add(attrNameHeader);
		panelGrid.getChildren().add(attrStatusHeader);

		String onclickFunctions = "";
		for (Attribute a : styles) {
			HtmlOutputText attrName = new HtmlOutputText();
			attrName.setId(a.getName() + "NameID");
			attrName.setValue(a.getName());
			attrName.setTitle(a.getDescription());
			HtmlInputText attrStatus = new HtmlInputText();
			attrStatus.setValue(a.getStatus());
			// id should not be changed in order that test execution is correct
			attrStatus.setId(a.getName() + "ID");
			attrStatus.setReadonly(true);

			// we should have a button which calls test function for each style
			// and class. Function is placed in jsTool.js file
			onclickFunctions += "markClassAsWorkable('" + a.getName() + "');";

			switch (a.getStatus()) {
			case FAILED:
				attrStatus.setStyle("color: red");
				break;
			case PASSED:
				attrStatus.setStyle("color: green");
				break;
			case NOT_TESTED:
				attrStatus.setStyle("color: grey");
				break;
			}

			panelGrid.getChildren().add(attrName);
			panelGrid.getChildren().add(attrStatus);
		}

		onclickFunctions += "return false;";
		HtmlCommandButton testButton = new HtmlCommandButton();
		testButton.setId("testButtonID");
		testButton.setValue("Test Classes");

		// pass all calls of the functions into onclick attribute of the button
		testButton.setOnclick(onclickFunctions);

		panelGrid.getChildren().add(testButton);
		panelGrid.getChildren().add(new HtmlPanelGroup());
	}

	/**
	 * Draw automated general attributes' grid.
	 * 
	 * @param panelGrid
	 *            the panel grid in which automated general attributes are drawn
	 * @param attrs
	 *            the attributes which are drawn in the panel grid
	 */
	public void drawAutoGeneralGrid(HtmlPanelGrid panelGrid,
			ArrayList<Attribute> attrs) {
		panelGrid.getChildren().clear();

		HtmlOutputText attrNameHeader = new HtmlOutputText();
		attrNameHeader.setId("attrHeaderID");
		attrNameHeader.setValue("AutoAttribute");
		attrNameHeader.setStyle("font-weight: bold; font-size: large");

		HtmlOutputText attrStatusHeader = new HtmlOutputText();
		attrStatusHeader.setId("attrStatusID");
		attrStatusHeader.setValue("Status");
		attrStatusHeader.setStyle("font-weight: bold; font-size: large");

		panelGrid.getChildren().add(attrNameHeader);
		panelGrid.getChildren().add(attrStatusHeader);

		for (Attribute a : attrs) {
			HtmlOutputText attrName = new HtmlOutputText();
			attrName.setValue(a.getName());
			attrName.setId(a.getName() + "NameID");
			attrName.setTitle(a.getDescription());
			HtmlInputText attrStatus = new HtmlInputText();
			attrStatus.setValue(a.getStatus());
			// id should not be changed in order that test execution is correct
			attrStatus.setId(a.getName() + "ID");
			attrStatus.setReadonly(true);

			switch (a.getStatus()) {
			case FAILED:
				attrStatus.setStyle("color: red");
				break;
			case PASSED:
				attrStatus.setStyle("color: green");
				break;
			case NOT_TESTED:
				attrStatus.setStyle("color: grey");
				break;
			}

			panelGrid.getChildren().add(attrName);

			// we should have a possibility to change immediate and required
			// attributes for their testing
			if (a.getName().equalsIgnoreCase("immediate")
					|| a.getName().equalsIgnoreCase("required")) {
				HtmlCommandButton button = new HtmlCommandButton();
				button.setId(a.getName() + "ButtonID");

				MethodExpression actionExpression;
				if (a.getName().equalsIgnoreCase("immediate")) {
					button.setValue("Change Immediate");
					actionExpression = getMethodExpression("#{utilListeners.immediateAction}");
				} else {
					button.setValue("Change Required");
					actionExpression = getMethodExpression("#{utilListeners.requiredAction}");
				}
				MethodExpressionActionListener meActionListener = new MethodExpressionActionListener(
						actionExpression);

				button.addActionListener(meActionListener);
				HtmlPanelGroup pg = new HtmlPanelGroup();
				pg.setId(a.getName() + "PanelGroupID");
				pg.getChildren().add(attrStatus);
				pg.getChildren().add(button);
				panelGrid.getChildren().add(pg);
			} else
				panelGrid.getChildren().add(attrStatus);
		}
	}

	/**
	 * Gets the method expression.
	 * 
	 * @param name
	 *            the name of EL expression
	 * 
	 * @return the method expression
	 */
	private MethodExpression getMethodExpression(String name) {
		Class[] argtypes = new Class[1];
		argtypes[0] = ActionEvent.class;
		FacesContext facesCtx = FacesContext.getCurrentInstance();
		ELContext elContext = facesCtx.getELContext();
		return facesCtx.getApplication().getExpressionFactory()
				.createMethodExpression(elContext, name, null, argtypes);
	}
}
