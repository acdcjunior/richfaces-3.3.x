package org.richfaces.testng.rf4553;

import org.richfaces.SeleniumTestBase;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		AssertTextEquals("form:includeID:counter", "1");
		
		type("//div[@id='divID']/table/tbody/tr[1]/td[2]/input", "John");
		type("//div[@id='divID']/table/tbody/tr[2]/td[2]/input", "Smith");
		type("//div[@id='divID']/table/tbody/tr[3]/td[2]/input", "Exadel");
		
		clickAjaxCommandAndWait("//input[@type='button' and @value='No actions']");
		AssertTextEquals("form:includeID:counter", "1");
		clickAjaxCommandAndWait("//input[@type='button' and @value='Next >>']");
		AssertTextEquals("form:includeID:counter", "2");
		
		type("form:includeID:notes","Selenium test in process...");
		
		clickAjaxCommandAndWait("//input[@type='button' and @value='No actions']");
		AssertTextEquals("form:includeID:counter", "2");
		clickAjaxCommandAndWait("//input[@type='button' and @value='Next >>']");
		AssertTextEquals("form:includeID:counter", "3");
		
		clickAjaxCommandAndWait("//input[@type='button' and @value='No actions']");
		AssertTextEquals("form:includeID:counter", "3");
		clickAjaxCommandAndWait("//input[@type='button' and @value='<<Previous']");
		AssertTextEquals("form:includeID:counter", "4");
	}
		
	public String getTestUrl() {
		return "pages/rf4553.xhtml";
	}

}