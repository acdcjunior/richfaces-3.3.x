package org.richfaces.testng.rf7172;

import org.richfaces.SeleniumTestBase;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();

		// click 'click' link
		clickById("form:clickID");
		// the context menu should appears
		AssertVisible("form:contextMenu");
		// click menu item
		clickById("form:menuItem");
		// get alert message
		String str = selenium.getAlert();

		try {
			if (!str.equalsIgnoreCase("testValue"))
				throw new AssertionError("params test failed.");
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}
	}

	@Override
	public String getTestUrl() {
		return "pages/rf7172.xhtml";
	}
}
