package org.richfaces.testng.rf6896;

import org.richfaces.SeleniumTestBase;
import org.testng.Assert;

public class Test extends SeleniumTestBase {

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();		
		
		clickById("form:inplaceSelect");		
		
		int inputTop = Integer.parseInt(runScript("getTop('form:inplaceSelectinplaceTmpValue')"));
		int inputHeight = getHeightById("form:inplaceSelectinplaceTmpValue").intValue();
		int listTop = Integer.parseInt(runScript("getTop('form:inplaceSelectlist')"));
		int componentStyleTop = findTop(selenium.getAttribute("//input[@id='form:inplaceSelectinplaceTmpValue']@style"));
		
		Assert.assertEquals(listTop, inputTop+inputHeight+componentStyleTop);	
	}
	
	public int findTop(String str){
		int startTop = str.indexOf("top");
		int endTop = str.indexOf("px", startTop);
		return Integer.parseInt(str.substring(startTop+"top: ".length(), endTop));
	}
	
	@Override
	public String getTestUrl() {		
		return "pages/rf6896.xhtml";
	}

}