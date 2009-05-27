package org.richfaces.testng.rf5758;

import org.richfaces.SeleniumTestBase;
import org.testng.Assert;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception{
		renderPage();		
		Assert.assertEquals(selenium.getValue("//form/span/input"),"Item 45");										
	}
	
	@Override
	public String getTestUrl() {		
		return "pages/rf5758.xhtml";
	}

}