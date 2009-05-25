package org.richfaces.testng.rf6487;

import org.richfaces.SeleniumTestBase;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();		
			
		selenium.assignId("form:text", "text");	
		selenium.mouseMove("text");		
		
		selenium.fireEvent("text", "mouseover");		
		
		AssertTextEquals("form:toolTipID", "//");
		
		clickAjaxCommandAndWait("form:buttonID");
		
		selenium.mouseMove("text");		
		selenium.fireEvent("text", "mouseover");
		
		AssertTextEquals("form:toolTipID", "-add//");	
		
		
	}
	
	@Override
	public String getTestUrl() {		
		return "pages/rf6487.xhtml";
	}

}