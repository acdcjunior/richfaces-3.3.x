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
import java.util.List;

import javax.faces.model.SelectItem;

import org.richfaces.VersionBean;

import util.BeanManager;

import automateCommon.AutoEventHandlers;
import automateCommon.AutoGeneral;
import automateCommon.AutoStylesClasses;

/**
 * The PageContent bean contains information about test page content.
 */
public class PageContent {

	/** The Constant page extension. */
	private static final String EXT = ".jsp";

	/** The all RichFaces components which exist in this application. */
	private List<SelectItem> allComponents;

	/** The all available RichFaces skins. */
	private List<SelectItem> allSkins;

	/** The RichFaces version. */
	private final String version = VersionBean.Version._versionInfo;

	/** The current component. */
	private String component = "_blank";

	/** The current skin. */
	private String skin = "blueSky";

	/** The current test type. */
	private TestType testType = TestType.MANUAL_GENERAL_ATTRIBUTES;

	/**
	 * Instantiates a new page content.
	 */
	public PageContent() {
		allComponents = new ArrayList<SelectItem>();
		allComponents.add(new SelectItem("_blank"));
		for (String component : ComponentsSkinsBuffers.componentsBuffer) {
			allComponents.add(new SelectItem(component));
		}

		allSkins = new ArrayList<SelectItem>();
		for (String skin : ComponentsSkinsBuffers.skinsBuffer) {
			allSkins.add(new SelectItem(skin));
		}
	}

	/**
	 * Call Reset methods in all auto test beans.
	 */
	public void reset() {
		testType = TestType.MANUAL_GENERAL_ATTRIBUTES;
		AutoEventHandlers autoEventHandlersBean = (AutoEventHandlers) BeanManager
				.getManagedBeanFromSession("autoEventHandlers");
		autoEventHandlersBean.reset();

		AutoStylesClasses autoStylesClassesBean = (AutoStylesClasses) BeanManager
				.getManagedBeanFromSession("autoStylesClasses");
		autoStylesClassesBean.reset();

		AutoGeneral autoGeneralBean = (AutoGeneral) BeanManager
				.getManagedBeanFromSession("autoGeneral");
		autoGeneralBean.reset();
	}

	/**
	 * Gets the current component.
	 * 
	 * @return the component
	 */
	public String getComponent() {
		return component;
	}

	/**
	 * Sets the current component.
	 * 
	 * @param component
	 *            the new component
	 */
	public void setComponent(String component) {
		// if different component is selected reset all data from automated
		// beans: panel grids, statuses etc...
		if (!component.equals(this.component))
			this.reset();
		this.component = component;
	}

	/**
	 * Gets the current test type.
	 * 
	 * @return the test type
	 */
	public TestType getTestType() {
		return testType;
	}

	/**
	 * Sets the current test type.
	 * 
	 * @param testType
	 *            the new test type
	 */
	public void setTestType(TestType testType) {
		this.testType = testType;
	}

	/**
	 * Gets the current component's path.
	 * 
	 * @return the current component 's path
	 */
	public String getCurrentComponent() {
		if (component.equals("_blank"))
			return "/components/_blank/_blank" + EXT;
		else
			return "/components/" + component + "/" + component + EXT;
	}

	/**
	 * Gets the current test page's path.
	 * 
	 * @return the current test page's path
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public String getCurrentTestPage() throws Exception {
		if (component.equals("_blank"))
			return "/components/_blank/_blankTestPage" + EXT;
		switch (testType) {
		case MANUAL_GENERAL_ATTRIBUTES:
			return "/components/" + component + "/" + component
					+ "ManualGeneral" + EXT;
		case AUTO_GENERAL_ATTRIBUTES:
			return "/auto/autoGeneral" + EXT;
		case AUTO_EVENTHANDLERS:
			return "/auto/autoEventHandlers" + EXT;
		case AUTO_STYLES_CLASSES:
			return "/auto/autoStylesClasses" + EXT;
		default: {
			throw new Exception("Incorrect testType");
		}
		}
	}

	/**
	 * Gets the RichFaces version.
	 * 
	 * @return the RichFaces version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Gets the all RiceFaces components.
	 * 
	 * @return the all RichFaces components
	 */
	public List<SelectItem> getAllComponents() {
		return allComponents;
	}

	/**
	 * Gets the current skin.
	 * 
	 * @return the current skin
	 */
	public String getSkin() {
		return skin;
	}

	/**
	 * Sets the current skin.
	 * 
	 * @param skin
	 *            the new skin
	 */
	public void setSkin(String skin) {
		this.skin = skin;
	}

	/**
	 * Gets the all skins.
	 * 
	 * @return the all skins
	 */
	public List<SelectItem> getAllSkins() {
		return allSkins;
	}
}