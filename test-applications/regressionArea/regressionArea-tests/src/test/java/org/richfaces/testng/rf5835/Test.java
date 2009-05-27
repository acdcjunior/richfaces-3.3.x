package org.richfaces.testng.rf5835;

import org.richfaces.SeleniumTestBase;
import org.testng.Assert;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		clickAjaxCommandAndWait("form:tree:10::_defaultNodeFace:handle");
		
		//check if line is present in expanded childs
		Number actual = getHeightById("form:tree:10::_defaultNodeFace:childs");
		Number expected = selenium.getElementHeight("//div[@id='form:tree:10::_defaultNodeFace:childs']/table[1]");		
		
		Assert.assertEquals(actual.intValue(), 10*expected.intValue());
		
		//check if line is present after expanded childs
		actual = getHeightById("form:tree");
		expected = getHeightById("form:tree:childs");
		
		Assert.assertEquals(actual.intValue(), expected.intValue());			
		
	}

	@Override
	public String getTestUrl() {
		return "pages/rf5835.xhtml";
	}
}