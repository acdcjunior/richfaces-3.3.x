package org.richfaces.testng.rf4553;

import org.richfaces.SeleniumTestBase;

public class Test extends SeleniumTestBase {
	
	public void testDefault() {
		renderPage();

		AssertTextEquals("form:defaultIncludeID:counter", "1");

		type("//div[@id='divID']/table/tbody/tr[1]/td[2]/input", "John");
		type("//div[@id='divID']/table/tbody/tr[2]/td[2]/input", "Smith");
		type("//div[@id='divID']/table/tbody/tr[3]/td[2]/input", "Exadel");

		clickAjaxCommandAndWait("//input[@type='button' and @value='No actions']");
		AssertTextEquals("form:defaultIncludeID:counter", "1");
		clickAjaxCommandAndWait("//input[@type='button' and @value='Next >>']");
		AssertTextEquals("form:defaultIncludeID:counter", "2");

		type("form:defaultIncludeID:notes", "Selenium test in process...");

		clickAjaxCommandAndWait("//input[@type='button' and @value='No actions']");
		AssertTextEquals("form:defaultIncludeID:counter", "2");
		clickAjaxCommandAndWait("//input[@type='button' and @value='Next >>']");
		AssertTextEquals("form:defaultIncludeID:counter", "3");

		clickAjaxCommandAndWait("//input[@type='button' and @value='No actions']");
		AssertTextEquals("form:defaultIncludeID:counter", "3");
		clickAjaxCommandAndWait("//input[@type='button' and @value='<<Previous']");
		AssertTextEquals("form:defaultIncludeID:counter", "4");
	}
	
	public void testAjaxRenderedFalse() {
		renderPage();					
		
		selenium.check("form:useAjaxRendered");
		
		selenium.submit("form");
		waitForPageToLoad();
		
		AssertTextEquals("form:ajaxRenderedIncludeID:counter", "7");
		
		clickAjaxCommandAndWait("//input[@type='button' and @value='No actions']");
		AssertTextEquals("form:ajaxRenderedIncludeID:counter", "7");
		clickAjaxCommandAndWait("//input[@type='button' and @value='Next >>']");
		AssertTextEquals("form:ajaxRenderedIncludeID:counter", "7");						
	}	
	
	public void testAjaxRenderedTrue() {
		renderPage();		
				
		selenium.check("form:ajaxRendered");	
		
		AssertTextEquals("form:ajaxRenderedIncludeID:counter", "9");
		
		clickAjaxCommandAndWait("//input[@type='button' and @value='No actions']");
		AssertTextEquals("form:ajaxRenderedIncludeID:counter", "10");
		clickAjaxCommandAndWait("//input[@type='button' and @value='Next >>']");
		AssertTextEquals("form:ajaxRenderedIncludeID:counter", "11");						
	}
	
	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		testDefault();
		testAjaxRenderedFalse();
		testAjaxRenderedTrue();
	}

	public String getTestUrl() {
		return "pages/rf4553.xhtml";
	}

}