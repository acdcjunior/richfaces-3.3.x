package org.jboss.richfaces.integrationTest.contextMenu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.fail;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class ContextMenuTableTestCase extends AbstractSeleniumRichfacesTestCase {

	private String xpathPrefix;

	@Test
	public void testTableContextMenu() {
		xpathPrefix = "//fieldset[2]/div/form";

		// check that the context menu is not visible
		boolean present = selenium.isElementPresent(xpathPrefix + "/div/div");
		assertFalse(present, "Context menu should be invisible at start.");

		// check that the 'Last Menu Action' is empty
		String text = selenium.getText(xpathPrefix
				+ "/table/tbody/tr/td[2]/span/div/div[2]");
		assertEquals(text, "", "Last menu action should be empty.");

		// open context menu on third line, first column
		selenium.click(xpathPrefix
				+ "/table/tbody/tr/td[1]/table/tbody/tr[3]/td[1]");
		waitForElement(xpathPrefix + "/div/div/div[1]");
		text = selenium.getAttribute(xpathPrefix + "/div/div/div[1]@style");
		assertFalse(text.contains("display: none;"),
				"Context menu should be visible after clicking on first column.");

		// open context menu on sixth line, second column
		selenium.click(xpathPrefix
				+ "/table/tbody/tr/td[1]/table/tbody/tr[6]/td[2]");
		waitForElement(xpathPrefix + "/div/div/div[1]");
		text = selenium.getAttribute(xpathPrefix + "/div/div/div[1]@style");
		assertFalse(text.contains("display: none;"),
				"Context menu should be visible after clicking on second column.");

		// open context menu on first line, third column (16773)
		selenium.click(xpathPrefix
				+ "/table/tbody/tr/td[1]/table/tbody/tr[1]/td[3]");
		waitForElement(xpathPrefix + "/div/div/div[1]");
		text = selenium.getAttribute(xpathPrefix + "/div/div/div[1]@style");
		assertFalse(text.contains("display: none;"),
				"Context menu should be visible after clicking on third column.");
	}

	@Test
	public void testClickOnCarDetails() {
		xpathPrefix = "//fieldset[2]/div/form";
		String text = null;

		String producer = selenium.getText(xpathPrefix
				+ "/table/tbody/tr/td[1]/table/tbody/tr[3]/td[1]");
		String model = selenium.getText(xpathPrefix
				+ "/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]");

		// open context menu on third line, first column
		selenium.click(xpathPrefix
				+ "/table/tbody/tr/td[1]/table/tbody/tr[3]/td[1]");
		waitForElement(xpathPrefix + "/div/div/div[1]");
		text = selenium.getAttribute(xpathPrefix + "/div/div/div[1]@style");
		assertFalse(text.contains("display: none;"),
				"Context menu should be visible after clicking on first column.");

		// click '<car> details'
		selenium.click(xpathPrefix + "/div/div/div[1]/div/div[1]/span[2]");
		waitFor(400);
		text = selenium.getText(xpathPrefix
				+ "/table/tbody/tr/td[2]/span/div/div[2]");
		assertEquals(text, producer + " " + model + " details",
				"Details of car:");
	}

	@Test
	public void testClickOnPutCarToBasket() {
		clickAction(1);
	}

	@Test
	public void testClickOnReadComments() {
		clickAction(2);
	}

	@Test
	public void testClickOnGoToProducerSite() {
		clickAction(3);
	}

	@Test
	public void testContextMenuTableSource() {
		abstractTestSource(2, 1, "<", "f:subview");
	}

	private void clickAction(int index) {
		xpathPrefix = "//fieldset[2]/div/form";
		String text = null;

		String producer = selenium.getText(xpathPrefix
				+ "/table/tbody/tr/td[1]/table/tbody/tr[3]/td[1]");
		String model = selenium.getText(xpathPrefix
				+ "/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]");

		// open context menu on third line, first column
		selenium.click(xpathPrefix
				+ "/table/tbody/tr/td[1]/table/tbody/tr[3]/td[1]");
		waitForElement(xpathPrefix + "/div/div/div[1]");
		text = selenium.getAttribute(xpathPrefix + "/div/div/div[1]@style");
		assertFalse(text.contains("display: none;"),
				"Context menu should be visible after clicking on first column.");

		// click 'Actions'
		selenium.click(xpathPrefix + "/div/div/div[2]");
		waitFor(400);

		// click 'Put <car> To Basket'
		selenium.click(xpathPrefix + "/div/div/div[2]/div/div[" + index
				+ "]/span[2]");
		waitFor(400);
		text = selenium.getText(xpathPrefix
				+ "/table/tbody/tr/td[2]/span/div/div[2]");

		switch (index) {
		case 1:
			assertEquals(text, "Put " + producer + " " + model + " To Basket",
					"Action put to basket:");
			break;
		case 2:
			assertEquals(text, "Read Comments", "Action read comments:");
			break;
		case 3:
			assertEquals(text, "Go to " + producer + " site",
					"Action go to site:");
			break;
		default:
			fail("Wrong index.");
		}
	}

	/**
	 * Loads the needed page.
	 */
	@BeforeMethod
	private void loadPage() {
		super
				.loadPage(
						"richMenu",
						1,
						"RichFaces Context menu is a component that allows to organize the hierarchical context menus");
	}
}
