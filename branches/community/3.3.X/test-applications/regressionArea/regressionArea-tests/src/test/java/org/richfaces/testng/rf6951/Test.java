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
package org.richfaces.testng.rf6951;

import org.richfaces.SeleniumTestBase;

/**
 * @author Mikhail Vitenkov
 * @since 3.3.2
 */
public class Test extends SeleniumTestBase {
	private static final String FORM = "form";
	private static final String PANEL = FORM + ":panel";
	private static final String LIST_SHUTTLE = FORM + ":LineConfigs";

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		selenium.doubleClick("//*[@id='"+ LIST_SHUTTLE +":0']");
		selenium.click("//form[@id='"+ FORM +"']/input[@type='button' and @value='Submit']");
		waitForAjaxCompletion();
		
		AssertTextEquals("//table[@id='"+ PANEL +"']/tbody/tr[1]/td[2]", "[Line 2, Line 3]");
		AssertTextEquals("//table[@id='"+ PANEL +"']/tbody/tr[2]/td[2]", "[Line 1]");
	}	
	
	@Override
	public String getTestUrl() {
		return "pages/rf6951.xhtml";
	}
}