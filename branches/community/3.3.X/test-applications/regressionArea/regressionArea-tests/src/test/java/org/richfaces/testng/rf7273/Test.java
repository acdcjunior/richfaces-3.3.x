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

package org.richfaces.testng.rf7273;

import junit.framework.Assert;

import org.richfaces.SeleniumTestBase;

/**
 * @author Nick Belaevski
 * @since 3.3.2
 */
public class Test extends SeleniumTestBase{

	private static final String HANDLE_LOCATOR = "form:tree:0::node:handle";

	private int getNodesCount() {
		return selenium.getXpathCount("//*[contains(@class, 'rich-tree-node') and not(contains(@class, 'rich-tree-node-'))]").intValue();
	}
	
	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		AssertTextEquals("statePanel", "");
		Assert.assertEquals(1, getNodesCount());
		
		clickAjaxCommandAndWait(HANDLE_LOCATOR);
		Assert.assertEquals(2, getNodesCount());
		AssertTextEquals("statePanel", "expanded");

		clickAjaxCommandAndWait(HANDLE_LOCATOR);
		Assert.assertEquals(1, getNodesCount());
		AssertTextEquals("statePanel", "expanded collapsed");
	}
	
	@Override
	public String getTestUrl() {		
		return "pages/rf7273.xhtml";
	}

}
