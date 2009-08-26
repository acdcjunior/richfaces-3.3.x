package org.jboss.richfaces.integrationTest.pickList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * <ul>
 * <li><b>TODO</b> check that buttons are enabled and disabled properly</li>
 * <li><b>TODO</b> implement tests for multiple files with shift and control key
 * </li>
 * <li><b>TODO</b> check not only one table, but both</li>
 * <li><b>TODO</b> check the header of panel with capitals</li>
 * </ul>
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class PickListTestCase extends AbstractSeleniumRichfacesTestCase {

	private final String leftUpperTable = "//fieldset[1]/div/table/tbody/tr/td[1]/div/table/tbody/tr/td/div/div/table/tbody";
	private final String upperButtons = "//fieldset[1]/div/table/tbody/tr/td[2]/div";
	private final String rightUpperTable = "//fieldset[1]/div/table/tbody/tr/td[3]/div/table/tbody/tr/td/div/div/table/tbody";

	private final String leftLowerTable = "//fieldset[2]/div/form/table/tbody/tr/td[1]/table/tbody/tr/td[1]/div/table/tbody/tr/td/div/div/table/tbody";
	private final String lowerButtons = "//fieldset[2]/div/form/table/tbody/tr/td[1]/table/tbody/tr/td[2]/div";
	private final String rightLowerTable = "//fieldset[2]/div/form/table/tbody/tr/td[1]/table/tbody/tr/td[3]/div/table/tbody/tr/td/div/div/table/tbody";
	private final String chosenOptions = "//fieldset[2]/div/form/table/tbody/tr/td[2]/div/div[2]";

	@Test
	public void testUpperPickList() {
		int count = selenium.getXpathCount(leftUpperTable + "/tr").intValue();
		assertTrue(count > 0, "There are no lines in the upper left table.");

		count = selenium.getXpathCount(rightUpperTable + "/tr").intValue();
		assertEquals(count, 0,
				"There should be no lines in the upper right table.");
	}

	@Test
	public void testUpperCopySingleFile() {
		int count = selenium.getXpathCount(leftUpperTable + "/tr").intValue();

		// click the first line
		selenium.click(leftUpperTable + "/tr[1]");
		// click 'Copy'
		selenium.click(upperButtons + "/div[3]");

		int newCount = selenium.getXpathCount(leftUpperTable + "/tr")
				.intValue();
		assertEquals(newCount, count - 1,
				"There should be less lines in the left table.");
	}

	// @Test
	// public void testUpperCopyMultipleFilesShift() {
	// fail("TODO");
	// }
	//	
	// @Test
	// public void testUpperCopyMultipleFilesCtrl() {
	// fail("TODO");
	// }

	@Test
	public void testUpperCopyAll() {
		int count = selenium.getXpathCount(leftUpperTable + "/tr").intValue();

		// click 'Copy All'
		selenium.click(upperButtons + "/div[1]");

		int newCount = selenium.getXpathCount(leftUpperTable + "/tr")
				.intValue();
		assertEquals(newCount, 0, "There should be no lines in the left table.");

		newCount = selenium.getXpathCount(rightUpperTable + "/tr").intValue();
		assertEquals(newCount, count,
				"All items from left table should be now in the right table.");
	}

	@Test
	public void testUpperRemoveSingleFile() {
		// click 'Copy All'
		selenium.click(upperButtons + "/div[1]");

		int count = selenium.getXpathCount(rightUpperTable + "/tr").intValue();

		// click the first line
		selenium.click(rightUpperTable + "/tr[1]");
		// click 'Remove'
		selenium.click(upperButtons + "/div[5]");

		int newCount = selenium.getXpathCount(rightUpperTable + "/tr")
				.intValue();
		assertEquals(newCount, count - 1,
				"There should be less lines in the right table.");
	}

	// @Test
	// public void testUpperRemoveMultipleFilesShift() {
	// fail("TODO");
	// }
	//	
	// @Test
	// public void testUpperRemoveMultipleFilesCtrl() {
	// fail("TODO");
	// }

	@Test
	public void testUpperRemoveAll() {
		int count = selenium.getXpathCount(leftUpperTable + "/tr").intValue();

		// click 'Copy All'
		selenium.click(upperButtons + "/div[1]");

		// click 'Remove All'
		selenium.click(upperButtons + "/div[7]");

		int newCount = selenium.getXpathCount(rightUpperTable + "/tr")
				.intValue();
		assertEquals(newCount, 0,
				"There should be no lines in the right table.");

		newCount = selenium.getXpathCount(leftUpperTable + "/tr").intValue();
		assertEquals(newCount, count,
				"All items from right table should be now in the left table.");
	}

	@Test
	public void testLowerPickList() {
		int count = selenium.getXpathCount(leftLowerTable + "/tr").intValue();
		assertTrue(count > 0, "There are no lines in the lower left table.");

		count = selenium.getXpathCount(rightLowerTable + "/tr").intValue();
		assertEquals(count, 0,
				"There should be no lines in the lower right table.");

		boolean empty = !selenium.isElementPresent(chosenOptions + "/ul");
		assertTrue(empty, "Chosen options should not contain any items.");
	}

	@Test
	public void testLowerCopySingleFile() {
		int count = selenium.getXpathCount(leftLowerTable + "/tr").intValue();

		// click the first line
		selenium.click(leftLowerTable + "/tr[1]");
		// click 'Copy'
		selenium.click(lowerButtons + "/div[3]");

		int newCount = selenium.getXpathCount(leftLowerTable + "/tr")
				.intValue();
		assertEquals(newCount, count - 1,
				"There should be less lines in the left table.");

		count = selenium.getXpathCount(chosenOptions + "/ul/li").intValue();
		assertEquals(count, 1, "There should be only one capital city.");

		String capital = selenium.getText(chosenOptions + "/ul/li[1]");
		assertEquals(capital, "Montgomery", "Capital of Alaska.");
	}

	// @Test
	// public void testLowerCopyMultipleFilesShift() {
	// fail("TODO");
	// }
	//	
	// @Test
	// public void testLowerCopyMultipleFilesCtrl() {
	// fail("TODO");
	// }

	@Test
	public void testLowerCopyAll() {
		int count = selenium.getXpathCount(leftLowerTable + "/tr").intValue();

		// click 'Copy All'
		selenium.click(lowerButtons + "/div[1]");

		int newCount = selenium.getXpathCount(leftLowerTable + "/tr")
				.intValue();
		assertEquals(newCount, 0, "There should be no lines in the left table.");

		newCount = selenium.getXpathCount(rightLowerTable + "/tr").intValue();
		assertEquals(newCount, count,
				"All items from left table should be now in the right table.");

		newCount = selenium.getXpathCount(chosenOptions + "/ul/li").intValue();
		assertEquals(newCount, count,
				"Not all capital cities were displayed in the panel.");
	}

	@Test
	public void testLowerRemoveSingleFile() {
		// click 'Copy All'
		selenium.click(lowerButtons + "/div[1]");

		int count = selenium.getXpathCount(rightLowerTable + "/tr").intValue();

		// click the first line
		selenium.click(rightLowerTable + "/tr[1]");
		// click 'Remove'
		selenium.click(lowerButtons + "/div[5]");

		int newCount = selenium.getXpathCount(rightLowerTable + "/tr")
				.intValue();
		assertEquals(newCount, count - 1,
				"There should be less lines in the right table.");

		newCount = selenium.getXpathCount(chosenOptions + "/ul/li").intValue();
		assertEquals(newCount, count - 1,
				"The capital city was not removed from panel.");
	}

	// @Test
	// public void testLowerRemoveMultipleFilesShift() {
	// fail("TODO");
	// }
	//	
	// @Test
	// public void testLowerRemoveMultipleFilesCtrl() {
	// fail("TODO");
	// }

	@Test
	public void testLowerRemoveAll() {
		int count = selenium.getXpathCount(leftLowerTable + "/tr").intValue();

		// click 'Copy All'
		selenium.click(lowerButtons + "/div[1]");

		// click 'Remove All'
		selenium.click(lowerButtons + "/div[7]");

		int newCount = selenium.getXpathCount(rightLowerTable + "/tr")
				.intValue();
		assertEquals(newCount, 0,
				"There should be no lines in the right table.");

		newCount = selenium.getXpathCount(leftLowerTable + "/tr").intValue();
		assertEquals(newCount, count,
				"All items from right table should be now in the left table.");

		count = selenium.getXpathCount(chosenOptions + "/ul/li").intValue();
		assertEquals(count, 0, "All capitals from the panel should be removed.");
	}

	@Test
	public void testUpperPickListSource() {
		abstractTestSource(1, 1, "<", "ui:composition");
	}

	@Test
	public void testLowerPickListSource() {
		abstractTestSource(2, 1, "<", "ui:composition");
	}

	/**
	 * Loads the needed page.
	 */
	@BeforeMethod
	private void loadPage() {
		super.loadPage("richSelect", 3,
				"Pick List component is a simple selection component");

		// click 'Remove All'
		selenium.click(upperButtons + "/div[7]");
		selenium.click(lowerButtons + "/div[7]");
	}
}
