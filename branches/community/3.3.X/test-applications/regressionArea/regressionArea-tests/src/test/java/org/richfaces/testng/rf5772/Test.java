package org.richfaces.testng.rf5772;

import org.richfaces.SeleniumTestBase;
import org.testng.Assert;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception{
		renderPage();
		
		AssertPresent("form:listShuttleID");
		
		Number l1 = selenium.getElementPositionLeft("form:listShuttleID:0");		
		selenium.doubleClick("form:listShuttleID:0");
		Number l2 = selenium.getElementPositionLeft("form:listShuttleID:0");		
		
		Assert.assertNotSame(l2, l1);		
	}
	
	@Override
	public String getTestUrl() {		
		return "pages/rf5772.xhtml";
	}

}
