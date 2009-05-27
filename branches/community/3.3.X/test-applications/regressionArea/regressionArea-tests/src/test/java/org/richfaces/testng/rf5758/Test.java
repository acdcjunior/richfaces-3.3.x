package org.richfaces.testng.rf5758;

import org.richfaces.SeleniumTestBase;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception{
		renderPage();
		String str;
		if((str = selenium.getValue("//form/span/input")).compareTo("Item 45") != 0){
			throw new AssertionError("Expected: <Item 45>; Actual: <" + str + ">");
		}								
	}
	
	@Override
	public String getTestUrl() {		
		return "pages/rf5758.xhtml";
	}

}