package org.richfaces.testng.rf7064;

import org.richfaces.SeleniumTestBase;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		selenium.assignId("form:toogleModes:1", "textArea");		
		clickAjaxCommandAndWait("textArea");		
		
		clickAjaxCommandAndWait("form:useSeamText");		
		type("form:editorTextArea", "<h1>header</h1>");
		
		clickAjaxCommandAndWait("form:toogleModes:0");		
		
		AssertTextEquals("//*[@id='tinymce']/h1", "header");		
		
	}

	@Override
	public String getTestUrl() {
		return "pages/rf7064.xhtml";
	}
}