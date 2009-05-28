package org.richfaces.testng.rf4432;

import org.richfaces.SeleniumTestBase;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		AssertPresent("form:sdtItems");
		AssertNotPresent("form:hello");
		
		clickAjaxCommandAndWait("//input[@type='button' and @value='Go']");
		
		AssertNotPresent("form:sdtItems");
		AssertPresent("form:hello");
		
		clickAjaxCommandAndWait("//input[@type='button' and @value='Back to table']");
		
		AssertPresent("form:sdtItems");
		AssertNotPresent("form:hello");		
	}
		
	public String getTestUrl() {
		return "pages/rf4432.xhtml";
	}

}