package org.richfaces.testng.rf5835;

import org.richfaces.SeleniumTestBase;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		clickAjaxCommandAndWait("form:tree:10::_defaultNodeFace:handle");
		
		//check if line is present in expanded childs
		Number childs = getHeightById("form:tree:10::_defaultNodeFace:childs");
		Number child = selenium.getElementHeight("//div[@id='form:tree:10::_defaultNodeFace:childs']/table[1]");
		int dif = 0;
		
		if(!((dif=(10*child.intValue() - childs.intValue())) == 0)){
			throw new AssertionError("white line is after the last child:" + dif);
		}
		//check if line is present after expanded childs
		if((dif = getHeightById("form:tree").intValue() - getHeightById("form:tree:childs").intValue()) != 0){
			throw new AssertionError("white line is after all childs:" + dif);
		}		
		
	}

	@Override
	public String getTestUrl() {
		return "pages/rf5835.xhtml";
	}
}