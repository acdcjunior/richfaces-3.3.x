package org.richfaces.testng.rf7140;

import org.richfaces.SeleniumTestBase;
import org.testng.Assert;

public class Test extends SeleniumTestBase {
	private static final String FORM = "form";

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		clickAjaxCommandAndWait("//form[@id='" + FORM + "']/table[1]/thead/tr/th");
		String t1FirstRow = selenium.getTable("//form[@id='" + FORM + "']/table[1].1.0");
		String t1LastRow = selenium.getTable("//form[@id='" + FORM + "']/table[1].4.0");
		
		clickAjaxCommandAndWait("//form[@id='" + FORM + "']/table[1]/thead/tr/th");
		Assert.assertEquals(selenium.getTable("//form[@id='" + FORM + "']/table[1].1.0"), t1LastRow);
		Assert.assertEquals(selenium.getTable("//form[@id='" + FORM + "']/table[1].4.0"), t1FirstRow);
		
		clickAjaxCommandAndWait("//form[@id='" + FORM + "']/table[4]/thead/tr/th");
		String t4FirstRow = selenium.getTable("//form[@id='" + FORM + "']/table[4].1.0");
		String t4LastRow = selenium.getTable("//form[@id='" + FORM + "']/table[4].4.0");		
		
		clickAjaxCommandAndWait("//form[@id='" + FORM + "']/table[4]/thead/tr/th");
		Assert.assertEquals(selenium.getTable("//form[@id='" + FORM + "']/table[4].1.0"), t4LastRow);
		Assert.assertEquals(selenium.getTable("//form[@id='" + FORM + "']/table[4].4.0"), t4FirstRow);
		
	}

	@Override
	public String getTestUrl() {
		return "pages/rf7140.xhtml";
	}
}