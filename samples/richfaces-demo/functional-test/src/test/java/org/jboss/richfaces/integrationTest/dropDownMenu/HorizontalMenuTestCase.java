package org.jboss.richfaces.integrationTest.dropDownMenu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class HorizontalMenuTestCase extends AbstractSeleniumRichfacesTestCase {

	private String xpathPrefix;

	@Test
	public void testHorizontalMenu() {
		xpathPrefix = "//fieldset[1]/div/form/table/tbody/tr/td[1]";

		String text = selenium.getAttribute(xpathPrefix
				+ "/div/div[2]/div[1]@style");
		assertTrue(text.contains("display: none;"),
				"Menu should not be expanded at start.");

		// FIXME menu does not show after click or fireEvent
		// click on 'File'
		// selenium.click(xpathPrefix + "/div/div[1]/img");
		// selenium.fireEvent(xpathPrefix + "/div/div[1]/img", "click");
		// waitFor(1000);
		// text = selenium.getAttribute(xpathPrefix +
		// "/div/div[2]/div[1]@style");
		// System.out.println("------------- " + text + " ------------------");
		// assertFalse(text.contains("display: none;"),
		// "Menu should be expanded after clicking on File.");

		// check that the 'Last Menu Action' is empty
		text = selenium.getText("//fieldset[1]/div/span[1]/span[1]");
		assertEquals(text, "", "Last menu action should be empty.");
	}

	@Test
	public void testMenuItem() {
		xpathPrefix = "//fieldset[1]/div/form/table/tbody/tr/td[1]/div/div[2]/div/div/div[1]";
		String text = null;

		selenium.click(xpathPrefix);
		waitFor(400);

		text = selenium.getText("//fieldset[1]/div/span/span");
		assertEquals(text, "New", "Text shown in 'Current Selection:'");
	}

	@Test
	public void testMenuGroup() {
		xpathPrefix = "//fieldset[1]/div/form/table/tbody/tr/td[1]/div/div[2]/div[2]/div/div[2]";
		String text = null;

		selenium.click(xpathPrefix);
		waitFor(400);

		text = selenium.getText("//fieldset[1]/div/span/span");
		assertEquals(text, "Save All", "Text shown in 'Current Selection:'");
	}

	@Test
	public void testSeparator() {
		xpathPrefix = "//fieldset[1]/div/form/table/tbody/tr/td[1]/div/div[2]/div[1]/div/div[5]";
		String text = null;

		text = selenium.getAttribute(xpathPrefix + "@class");
		assertTrue(text.equals("rich-menu-separator"),
				"There should be a separator in the menu.");
	}

	@Test
	public void testIcon() {
		xpathPrefix = "//fieldset[1]/div/form/table/tbody/tr/td[1]/div/div[1]/img";

		int width = selenium.getElementWidth(xpathPrefix).intValue();
		int height = selenium.getElementHeight(xpathPrefix).intValue();

		assertTrue(width > 0,
				"Menu item 'File' should have a visible icon (width > 0).");
		assertTrue(height > 0,
				"Menu item 'File' should have a visible icon (height > 0).");
	}

	@Test
	public void testLinkRFHomePage() {
		xpathPrefix = "//fieldset[1]/div/form/table/tbody/tr/td[2]/div/div[2]/div/div/div[1]/span[2]/a";

		String location = selenium.getAttribute(xpathPrefix + "@href");
		assertTrue(location.contains("http://"), "Href has to contain http://");
	}

	@Test
	public void testLinkRFForum() {
		xpathPrefix = "//fieldset[1]/div/form/table/tbody/tr/td[2]/div/div[2]/div/div/div[2]/span[2]/a";

		String location = selenium.getAttribute(xpathPrefix + "@href");
		assertTrue(location.contains("http://"), "Href has to contain http://");
	}

	@Test
	public void testListShuttleSource() {
		abstractTestSource(1, 1, "<", "ui:composition");
	}

	/**
	 * Loads the needed page.
	 */
	@BeforeMethod
	private void loadPage() {
		super
				.loadPage(
						"richMenu",
						2,
						"RichFaces Drop Down menu is a component that allows to organize the hierarchical menu");
	}
}
