package org.richfaces.testng.rf6338;

import org.richfaces.SeleniumTestBase;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();

		selenium.assignId("iconform:group1", "group1");
		selenium.assignId("iconform:group2", "group2");
		clickById("group1");
		pause(500, null);
		AssertVisible("iconform:item1_1");
		clickById("group2");
		pause(500, null);
		AssertVisible("iconform:item1_2");
		clickById("group1");
		pause(500, null);
		AssertNotVisible("iconform:item1_1");
		clickById("group2");
		pause(500, null);
		AssertNotVisible("iconform:item1_2");
	}

	@Override
	public String getTestUrl() {
		return "pages/rf6338.xhtml";
	}

}
