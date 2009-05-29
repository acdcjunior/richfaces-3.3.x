package org.richfaces.testng.rf4432;

import org.richfaces.SeleniumTestBase;
import org.testng.Assert;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		AssertPresent("form:sdtItems");
		AssertNotPresent("form:hello");
		
		selenium.click("//tr[@id='form:sdtItems:n:3']/td[1]");
		
		clickAjaxCommandAndWait("//input[@type='button' and @value='Go']");
		
		AssertNotPresent("form:sdtItems");
		AssertPresent("form:hello");
		
		clickAjaxCommandAndWait("//input[@type='button' and @value='Back to table']");
		
		AssertPresent("form:sdtItems");
		AssertNotPresent("form:hello");
		
		//check sdt header
		AssertPresent("form:sdtItems:hcb_0");
		AssertPresent("form:sdtItems:hcb_1");
		
		//check selection
		Assert.assertEquals(selenium.getAttribute("//tr[@id='form:sdtItems:n:3']@class"), "dr-sdt-rb rich-sdt-row dr-sdt-row-active rich-sdt-row-active dr-sdt-row-selected rich-sdt-row-selected"); 
	}
		
	public String getTestUrl() {
		return "pages/rf4432.xhtml";
	}

}