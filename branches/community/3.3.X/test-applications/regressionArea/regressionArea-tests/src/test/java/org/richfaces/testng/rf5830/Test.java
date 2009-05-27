package org.richfaces.testng.rf5830;

import org.richfaces.SeleniumTestBase;

/**
 * @author Mikhail Vitenkov
 * @since 3.3.2
 */
public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		clickAjaxCommandAndWait("form:link");
		
		AssertTextEquals("//form[@id='form']//span[@class='rich-messages-label']", "message");
		
	}
	

	@Override
	public String getTestUrl() {
		return "pages/rf5830.xhtml";
	}

	
}
