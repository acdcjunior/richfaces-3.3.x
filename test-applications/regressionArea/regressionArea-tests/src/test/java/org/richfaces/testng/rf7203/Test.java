package org.richfaces.testng.rf7203;

import org.richfaces.SeleniumTestBase;

public class Test extends SeleniumTestBase{

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		clickById("form:link");
		AssertVisible("form:contextMenu");
		
		clickAjaxCommandAndWait("form:menuItem");		
		String str = selenium.getAlert();
		System.out.println(str);
		
		try{
		if(str.equalsIgnoreCase("oncomplete")){
			throw new AssertionError("'oncomplete' alert still appears.");
		}}catch (Exception e) {
			System.err.println(e.getStackTrace());
		}		
		 
	}
	
	@Override
	public String getTestUrl() {		
		return "pages/rf7203.xhtml";
	}

}
