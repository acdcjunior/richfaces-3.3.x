package org.richfaces.testng.rf5772;

import org.richfaces.SeleniumTestBase;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception{
		renderPage();
		
		AssertPresent("form:listShuttleID");
		
		Number l1 = selenium.getElementPositionLeft("form:listShuttleID:0");		
		selenium.doubleClick("form:listShuttleID:0");
		Number l2 = selenium.getElementPositionLeft("form:listShuttleID:0");		
		
		if(l1.intValue()==l2.intValue()){
			throw new AssertionError("item didn't move to target");
		}
		
	}
	
	@Override
	public String getTestUrl() {		
		return "pages/rf5772.xhtml";
	}

}
