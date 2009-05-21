package org.richfaces.testng.rf7203;

import org.richfaces.SeleniumTestBase;

public class Test extends SeleniumTestBase{

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		clickCommandAndWait("form:link");
		AssertVisible("form:contextMenu");
		clickAjaxCommandAndWait("form:contextMenu:menuItem");		
	}
	
	@Override
	public String getTestUrl() {		
		return "pages/rf7203.xhtml";
	}

}
