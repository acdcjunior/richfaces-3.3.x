package org.richfaces.testng.rf6338;

import org.richfaces.SeleniumTestBase;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		clickById("iconform:group1");		
		AssertVisible("iconform:item1_1");
		
		clickById("iconform:group2");		
		AssertVisible("iconform:item1_2");
		
		clickById("iconform:group1");		
		AssertNotVisible("iconform:item1_1");
		
		clickById("iconform:group2");		
		AssertNotVisible("iconform:item1_2");
	}

	@Override
	public String getTestUrl() {
		return "pages/rf6338.xhtml";
	}

}
