package org.jboss.richfaces.integrationTest.dataScroller;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class DataScrollerTestCase extends AbstractDataIterationTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.open(format("{0}/{1}", contextPath, PAGE));

		scrollIntoView(header, true);
	}

	private final String PAGE = "richfaces/dataTableScroller.jsf?c=dataTableScroller&tab=usage";
	private final String header = getLoc("data-scroller--header");
	private final String[] numberedPages = StringUtils.split(getMess("data-scroller--numbered-pages"), ',');

	@Test
	public void numberedPageTest() {
		openPage();
		String tableText = getTableText();
		for (String page : numberedPages) {
			gotoPage(format(buttonPagePreformatted, Integer.valueOf(page)));
			tableText = checkThatTextDiffersAndReturn(tableText);
		}
		gotoFirstPage();
		checkThatTextDiffersAndReturn(tableText);
		checkFirstPage();
	}
	
	@Test(dependsOnMethods = "numberedPageTest")
	public void remembersActivePage() {
		openPage();
		gotoFirstPage();
		Integer page = 2;
		gotoPage(format(buttonPagePreformatted, page));
		selenium.refresh();
		Assert.assertTrue(page.equals(getActivePage()));
		openPage();
		Assert.assertTrue(page.equals(getActivePage()));
	}
	
	@Test(dependsOnMethods = "numberedPageTest")
	public void lastPageTest() {
		openPage();
		gotoFirstPage();
		String tableText = getTableText();
		gotoPage(buttonLastPage);
		checkThatTextDiffersAndReturn(tableText);
		checkLastPage();
	}

	@Test(dependsOnMethods = "lastPageTest")
	public void firstPageTest() {
		openPage();
		gotoFirstPage();
		gotoPage(buttonLastPage);
		String tableText = getTableText();
		gotoPage(buttonFirstPage);
		checkThatTextDiffersAndReturn(tableText);
		checkFirstPage();
	}

	@Test(dependsOnMethods = "numberedPageTest")
	public void nextPageTest() {
		openPage();
		gotoFirstPage();
		String tableText = getTableText();
		for (int page = 2; page < getLastVisiblePage(); page++) {
			gotoPage(buttonNextPage);
			checkNonExtremePage();
			tableText = checkThatTextDiffersAndReturn(tableText);
		}
		gotoPage(buttonNextPage);
		checkThatTextDiffersAndReturn(tableText);
		checkLastPage();
	}

	@Test(dependsOnMethods = "lastPageTest")
	public void previousPageTest() {
		openPage();
		gotoFirstPage();
		gotoPage(buttonLastPage);
		String tableText = getTableText();
		for (int page = getActivePage() - 1; page > 1; page--) {
			gotoPage(buttonPreviousPage);
			checkNonExtremePage();
			tableText = checkThatTextDiffersAndReturn(tableText);
		}
		gotoPage(buttonPreviousPage);
		checkThatTextDiffersAndReturn(tableText);
		checkFirstPage();
	}

	private void checkFirstPage() {
		Assert.assertTrue(belongsClass("rich-datascr-button-dsbld",
				buttonFirstPage));
		Assert.assertTrue(belongsClass("rich-datascr-button-dsbld",
				buttonPreviousPage));
		Assert.assertTrue(belongsClass("rich-datascr-button", buttonNextPage));
		Assert.assertTrue(belongsClass("rich-datascr-button", buttonLastPage));
		Assert.assertTrue("1".equals(selenium.getText(outputActivePage)));
	}

	private void checkNonExtremePage() {
		Assert.assertTrue(belongsClass("rich-datascr-button", buttonFirstPage));
		Assert.assertTrue(belongsClass("rich-datascr-button",
				buttonPreviousPage));
		Assert.assertTrue(belongsClass("rich-datascr-button", buttonNextPage));
		Assert.assertTrue(belongsClass("rich-datascr-button", buttonLastPage));
		String activePage = selenium.getText(outputActivePage);
		Assert.assertFalse("1".equals(activePage));
		Assert.assertFalse(getLastVisiblePage().toString().equals(activePage));
	}

	private void checkLastPage() {
		Assert.assertTrue(belongsClass("rich-datascr-button", buttonFirstPage));
		Assert.assertTrue(belongsClass("rich-datascr-button",
				buttonPreviousPage));
		Assert.assertTrue(belongsClass("rich-datascr-button-dsbld",
				buttonNextPage));
		Assert.assertTrue(belongsClass("rich-datascr-button-dsbld",
				buttonLastPage));
		Assert.assertTrue(getLastVisiblePage().toString().equals(
				selenium.getText(outputActivePage)));
	}

	private void gotoFirstPage() {
		gotoPage(buttonFirstPage);
	}
	
	private String checkThatTextDiffersAndReturn(String lastTableText) {
		String tableText = getTableText();
		Assert.assertFalse(lastTableText.equals(tableText));
		return tableText;
	}
}
