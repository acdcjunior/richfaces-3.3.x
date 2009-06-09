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
package org.richfaces.testng.rf7323;

import org.richfaces.SeleniumTestBase;
import org.testng.Assert;

/**
 * @author Mikhail Vitenkov
 * @since 3.3.2
 */
public class Test extends SeleniumTestBase {
	private static final String FORM = "form";
	private static final String TREE_NODES = FORM + ":policyComponentsTreeBase:childs";
	private static final String PANEL = FORM + ":policyComponentsSelectionPanel";
	
	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		selenium.click("//div[@id='"+TREE_NODES+"']/table/tbody/tr/td[1]/div/a");
		
		//avoid mouse down node, before it became expanded(for switchType="client")
		pause(1500,null);
		
		selenium.mouseDown("//div[@id='"+TREE_NODES+"']/div/table[1]/tbody/tr/td[2]");
		waitForAjaxCompletion();
		String dir1 = selenium.getText("//table[@id='"+PANEL+"']/tbody/tr/td");		
		
		Assert.assertEquals(dir1.contains("org.richfaces.component.html.HtmlTree"), true);
		
		selenium.mouseDown("//div[@id='"+TREE_NODES+"']/div/table[2]/tbody/tr/td[2]");
		waitForAjaxCompletion();
		String dir2 = selenium.getText("//table[@id='"+PANEL+"']/tbody/tr/td");
		
		Assert.assertEquals(dir2.contains("org.richfaces.component.html.HtmlTree"), true);
		
		Assert.assertEquals(dir1.equals(dir2), false);
	}
		
	public String getTestUrl() {
		return "pages/rf7323.xhtml";
	}

}