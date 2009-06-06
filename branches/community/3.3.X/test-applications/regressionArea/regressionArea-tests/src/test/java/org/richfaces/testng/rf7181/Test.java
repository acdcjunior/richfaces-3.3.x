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
package org.richfaces.testng.rf7181;

import org.richfaces.SeleniumTestBase;
import org.testng.Assert;

/**
 * @author Mikhail Vitenkov
 * @since 3.3.2
 */
public class Test extends SeleniumTestBase {
	
	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		//waiting for a4j:poll(interval="3000")
		pause(3500,null);
			
		boolean saTree1 = selenium.getText("//dl[@class='rich-messages']/dt[1]/span").contains("saTreeText");
		boolean saTree2 = selenium.getText("//dl[@class='rich-messages']/dt[2]/span").contains("saTreeText");
		boolean saTree3 = selenium.getText("//dl[@class='rich-messages']/dt[3]/span").contains("saTreeText");
		boolean saTree4 = selenium.getText("//dl[@class='rich-messages']/dt[4]/span").contains("saTreeText");
		boolean effectsScript = selenium.getText("//dl[@class='rich-messages']/dt[5]/span").contains("effectsScriptText");
		
		Assert.assertEquals(saTree1, true);
		Assert.assertEquals(saTree2, true);
		Assert.assertEquals(saTree3, true);
		Assert.assertEquals(saTree4, true);
		Assert.assertEquals(effectsScript, true);
	}
		
	public String getTestUrl() {
		return "pages/rf7181.xhtml";
	}

}