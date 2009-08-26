package org.jboss.richfaces.integrationTest.dataList;

import junit.framework.Assert;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class DataListTestCase extends AbstractSeleniumRichfacesTestCase {

	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.open(format("{0}/{1}", contextPath, PAGE));

		scrollIntoView(header, true);
	}

	private final String PAGE = "richfaces/dataLists.jsf?c=dataList&tab=usage";
	private final String header = getLoc("data-list--header");
	
	@Test
	public void staticContentTest() {
		openPage();
		
		Assert.assertTrue(selenium.isTextPresent(getMess("data-list--part1")));
		Assert.assertTrue(selenium.isTextPresent(getMess("data-list--part2")));
	}
}
