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
package org.richfaces.testng.rf3341;

import org.richfaces.SeleniumTestBase;
import org.testng.Assert;

/**
 * @author Mikhail Vitenkov
 * @since 3.3.2
 */
public class Test extends SeleniumTestBase {
	private static final String FORM = "form";
	private static final String OUT1 = FORM + ":out1";
	private static final String OUT2 = FORM + ":out2";
	private static final String COUNTER1 = FORM + ":counter1";
	private static final String COUNTER2 = FORM + ":counter2";
	
	private String getIdSelector(String id) {
		return "//*[@id='" + id + "']";
	}

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		Assert.assertEquals(selenium.getAttribute("//input[@type='text']@value"), "name");
		AssertTextEquals(getIdSelector(OUT2), "name");
		AssertTextEquals(getIdSelector(COUNTER2), "0");
		
		type("//input[@type='text']", "test-1");		
		selenium.fireEvent("//input[@type='text']", "blur");
		waitForAjaxCompletion();
		selenium.submit("//form[@id='form']");
		waitForPageToLoad();
		
		Assert.assertEquals(selenium.getAttribute("//input[@type='text']@value"), "test-1");		
		AssertTextEquals(getIdSelector(OUT2), "test-1");
		AssertTextEquals(getIdSelector(COUNTER2), "1");
		
		selenium.check("//input[@type='checkbox']");
		selenium.submit("//form[@id='form']");
		waitForPageToLoad();
		
		Assert.assertEquals(selenium.getAttribute("//input[@type='text']@value"), "test-1");		
		AssertTextEquals(getIdSelector(OUT1), "test-1");
		AssertTextEquals(getIdSelector(COUNTER1), "1");
		
		type("//input[@type='text']", "test-2");
		selenium.fireEvent("//input[@type='text']", "blur");
		waitForAjaxCompletion();
		selenium.submit("//form[@id='form']");		
		waitForPageToLoad();
		
		Assert.assertEquals(selenium.getAttribute("//input[@type='text']@value"), "test-2");		
		AssertTextEquals(getIdSelector(OUT1), "test-2");
		AssertTextEquals(getIdSelector(COUNTER1), "1");		 
	}
		
	public String getTestUrl() {
		return "pages/rf3341.xhtml";
	}

}