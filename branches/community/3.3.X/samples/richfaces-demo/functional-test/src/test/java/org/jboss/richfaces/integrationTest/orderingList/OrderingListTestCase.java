package org.jboss.richfaces.integrationTest.orderingList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsNoOrder;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * <ul>
 * <li><b>TODO</b> check that the number of lines does not change on Up, Down,
 * First, Last (+Ctrl/Shift)</li>
 * <li><b>TODO</b> check that all buttons are disabled if no lines selected</li>
 * <li><b>TODO</b> refactor tests so that the common functionality is in one
 * method</li>
 * </ul>
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class OrderingListTestCase extends AbstractSeleniumRichfacesTestCase {

	private final String table = "//fieldset[1]/div/form/table/tbody/tr/td[1]/div/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody";
	private final String buttons = "//fieldset[1]/div/form/table/tbody/tr/td[1]/div/table/tbody/tr[2]/td[2]/div";
	private final String selection = "//fieldset[1]/div/form/table/tbody/tr/td[2]/div/div[2]";

	@Test
	public void testOrderingList() {
		int count = selenium.getXpathCount(table + "/tr").intValue();
		assertTrue(count > 0, "There are no lines in the table.");

		waitFor(1000);
		String text = selenium.getText(selection);
		assertEquals(text, "No Songs Selected",
				"Selection should not contain any name of song.");

	}

	@Test
	public void testClickSingleFile() {
		selenium.click(table + "/tr[1]");
		String text = selenium.getText(table + "/tr[1]/td[1]");

		waitFor(1000);
		String text2 = selenium.getText(selection);

		assertEquals(text2, text,
				"Selected song is not shown in the selection panel.");
	}

	@Test
	public void testClickMultipleFilesCtrl() {
		String[] fromTable = new String[3];
		String[] fromPanel = new String[3];

		// click lines nr. 1, 3, and 5 in the table
		selenium.click(table + "/tr[1]");
		selenium.controlKeyDown();
		selenium.click(table + "/tr[3]");
		selenium.click(table + "/tr[5]");
		selenium.controlKeyUp();

		fromTable[0] = selenium.getText(table + "/tr[1]/td[1]");
		fromTable[1] = selenium.getText(table + "/tr[3]/td[1]");
		fromTable[2] = selenium.getText(table + "/tr[5]/td[1]");

		waitFor(1000);

		// get all selections from the panel on the right
		for (int i = 0; i < 3; i++) {
			fromPanel[i] = selenium.getText(selection + "/ul/li[" + (i + 1)
					+ "]");
		}

		assertEqualsNoOrder(fromPanel, fromTable,
				"Items chosen in table should be also in the right panel.");
	}

	@Test
	public void testClickMultipleFilesShift() {
		String[] fromTable = new String[3];
		String[] fromPanel = new String[3];

		// click lines nr. 1 and 3 in the table
		selenium.click(table + "/tr[1]");
		selenium.shiftKeyDown();
		selenium.click(table + "/tr[3]");
		selenium.shiftKeyUp();

		fromTable[0] = selenium.getText(table + "/tr[1]/td[1]");
		fromTable[1] = selenium.getText(table + "/tr[2]/td[1]");
		fromTable[2] = selenium.getText(table + "/tr[3]/td[1]");

		waitFor(1000);

		// get all selections from the panel on the right
		for (int i = 0; i < 3; i++) {
			fromPanel[i] = selenium.getText(selection + "/ul/li[" + (i + 1)
					+ "]");
		}

		assertEqualsNoOrder(fromPanel, fromTable,
				"Items chosen in table should be also in the right panel.");
	}

	@Test
	public void testUpSingleFile() {
		String[] before = new String[2];
		String[] after = new String[2];

		before[0] = selenium.getText(table + "/tr[5]/td[1]");
		before[1] = selenium.getText(table + "/tr[6]/td[1]");

		// click line nr. 6 in the table
		selenium.click(table + "/tr[6]");
		// click the 'Up' button
		selenium.click(buttons + "/div[3]");

		after[0] = selenium.getText(table + "/tr[5]/td[1]");
		after[1] = selenium.getText(table + "/tr[6]/td[1]");

		assertEquals(after[0], before[1], "The sixth line should be now fifth.");
		assertEquals(after[1], before[0], "The fifth line should be now sixth.");
	}

	@Test
	public void testUpMultipleFilesShift() {
		String[] before = new String[3];
		String[] after = new String[3];

		before[0] = selenium.getText(table + "/tr[5]/td[1]");
		before[1] = selenium.getText(table + "/tr[6]/td[1]");
		before[2] = selenium.getText(table + "/tr[7]/td[1]");

		// click line nr. 6 and 7 in the table
		selenium.click(table + "/tr[6]");
		selenium.shiftKeyDown();
		selenium.click(table + "/tr[7]");
		selenium.shiftKeyUp();

		// click the 'Up' button (5,6,7 -> 6,7,5)
		selenium.click(buttons + "/div[3]");

		after[0] = selenium.getText(table + "/tr[5]/td[1]");
		after[1] = selenium.getText(table + "/tr[6]/td[1]");
		after[2] = selenium.getText(table + "/tr[7]/td[1]");

		assertEquals(after[0], before[1], "The sixth line should be now fifth.");
		assertEquals(after[1], before[2],
				"The seventh line should be now sixth.");
		assertEquals(after[2], before[0],
				"The fifth line should be now seventh.");
	}

	@Test
	public void testUpMultipleFilesCtrl() {
		String[] before = new String[3];
		String[] after = new String[3];

		before[0] = selenium.getText(table + "/tr[5]/td[1]");
		before[1] = selenium.getText(table + "/tr[6]/td[1]");
		before[2] = selenium.getText(table + "/tr[7]/td[1]");

		// click line nr. 6 and 7 in the table
		selenium.click(table + "/tr[6]");
		selenium.controlKeyDown();
		selenium.click(table + "/tr[7]");
		selenium.controlKeyUp();

		// click the 'Up' button (5,6,7 -> 6,7,5)
		selenium.click(buttons + "/div[3]");

		after[0] = selenium.getText(table + "/tr[5]/td[1]");
		after[1] = selenium.getText(table + "/tr[6]/td[1]");
		after[2] = selenium.getText(table + "/tr[7]/td[1]");

		assertEquals(after[0], before[1], "The sixth line should be now fifth.");
		assertEquals(after[1], before[2],
				"The seventh line should be now sixth.");
		assertEquals(after[2], before[0],
				"The fifth line should be now seventh.");
	}

	@Test
	public void testDownSingleFile() {
		String[] before = new String[2];
		String[] after = new String[2];

		before[0] = selenium.getText(table + "/tr[6]/td[1]");
		before[1] = selenium.getText(table + "/tr[7]/td[1]");

		// click line nr. 6 in the table
		selenium.click(table + "/tr[6]");
		// click the 'Up' button
		selenium.click(buttons + "/div[5]");

		after[0] = selenium.getText(table + "/tr[6]/td[1]");
		after[1] = selenium.getText(table + "/tr[7]/td[1]");

		assertEquals(after[0], before[1],
				"The sixth line should be now seventh.");
		assertEquals(after[1], before[0],
				"The seventh line should be now sixth.");
	}

	@Test
	public void testDownMultipleFilesShift() {
		String[] before = new String[3];
		String[] after = new String[3];

		before[0] = selenium.getText(table + "/tr[5]/td[1]");
		before[1] = selenium.getText(table + "/tr[6]/td[1]");
		before[2] = selenium.getText(table + "/tr[7]/td[1]");

		// click line nr. 5 and 6 in the table
		selenium.click(table + "/tr[5]");
		selenium.shiftKeyDown();
		selenium.click(table + "/tr[6]");
		selenium.shiftKeyUp();

		// click the 'Up' button (5,6,7 -> 7,5,6)
		selenium.click(buttons + "/div[5]");

		after[0] = selenium.getText(table + "/tr[5]/td[1]");
		after[1] = selenium.getText(table + "/tr[6]/td[1]");
		after[2] = selenium.getText(table + "/tr[7]/td[1]");

		assertEquals(after[0], before[2],
				"The seventh line should be now fifth.");
		assertEquals(after[1], before[0], "The fifth line should be now sixth.");
		assertEquals(after[2], before[1],
				"The sixth line should be now seventh.");
	}

	@Test
	public void testDownMultipleFilesCtrl() {
		String[] before = new String[3];
		String[] after = new String[3];

		before[0] = selenium.getText(table + "/tr[5]/td[1]");
		before[1] = selenium.getText(table + "/tr[6]/td[1]");
		before[2] = selenium.getText(table + "/tr[7]/td[1]");

		// click line nr. 5 and 6 in the table
		selenium.click(table + "/tr[5]");
		selenium.controlKeyDown();
		selenium.click(table + "/tr[6]");
		selenium.controlKeyUp();

		// click the 'Up' button (5,6,7 -> 7,5,6)
		selenium.click(buttons + "/div[5]");

		after[0] = selenium.getText(table + "/tr[5]/td[1]");
		after[1] = selenium.getText(table + "/tr[6]/td[1]");
		after[2] = selenium.getText(table + "/tr[7]/td[1]");

		assertEquals(after[0], before[2],
				"The seventh line should be now fifth.");
		assertEquals(after[1], before[0], "The fifth line should be now sixth.");
		assertEquals(after[2], before[1],
				"The sixth line should be now seventh.");
	}

	@Test
	public void testLastSingleFile() {
		String[] before = new String[3];
		String[] after = new String[3];
		int countOfLines = selenium.getXpathCount(table + "/tr").intValue();

		before[0] = selenium.getText(table + "/tr[1]/td[1]");
		before[1] = selenium.getText(table + "/tr[2]/td[1]");
		before[2] = selenium.getText(table + "/tr[" + countOfLines + "]/td[1]");

		// click line nr. 1 in the table
		selenium.click(table + "/tr[1]");
		// click the 'Last' button (1,2,..,last-1,last --> 2,..,last,1)
		selenium.click(buttons + "/div[7]");

		after[0] = selenium.getText(table + "/tr[1]/td[1]");
		after[1] = selenium.getText(table + "/tr[" + (countOfLines - 1)
				+ "]/td[1]");
		after[2] = selenium.getText(table + "/tr[" + countOfLines + "]/td[1]");

		assertEquals(after[0], before[1],
				"The second line should be now first.");
		assertEquals(after[1], before[2],
				"The last line should be now last but one.");
		assertEquals(after[2], before[0], "The first line should be now last.");
	}

	@Test
	public void testLastMultipleFilesShift() {
		String[] before = new String[4];
		String[] after = new String[4];
		int countOfLines = selenium.getXpathCount(table + "/tr").intValue();

		before[0] = selenium.getText(table + "/tr[1]/td[1]");
		before[1] = selenium.getText(table + "/tr[2]/td[1]");
		before[2] = selenium.getText(table + "/tr[3]/td[1]");
		before[3] = selenium.getText(table + "/tr[" + countOfLines + "]/td[1]");

		// click line nr. 1 and 2 in the table
		selenium.click(table + "/tr[1]");
		selenium.shiftKeyDown();
		selenium.click(table + "/tr[2]");
		selenium.shiftKeyUp();

		// click the 'Last' button (1,2,3,..,last --> 3,..,last,1,2)
		selenium.click(buttons + "/div[7]");

		after[0] = selenium.getText(table + "/tr[1]/td[1]");
		after[1] = selenium.getText(table + "/tr[" + (countOfLines - 2)
				+ "]/td[1]");
		after[2] = selenium.getText(table + "/tr[" + (countOfLines - 1)
				+ "]/td[1]");
		after[3] = selenium.getText(table + "/tr[" + countOfLines + "]/td[1]");

		assertEquals(after[0], before[2], "The third line should be now first.");
		assertEquals(after[1], before[3],
				"The last line should be now last but two.");
		assertEquals(after[2], before[0],
				"The first line should be now last but one.");
		assertEquals(after[3], before[1], "The second line should be now last.");
	}

	@Test
	public void testLastMultipleFilesCtrl() {
		String[] before = new String[4];
		String[] after = new String[4];
		int countOfLines = selenium.getXpathCount(table + "/tr").intValue();

		before[0] = selenium.getText(table + "/tr[1]/td[1]");
		before[1] = selenium.getText(table + "/tr[2]/td[1]");
		before[2] = selenium.getText(table + "/tr[3]/td[1]");
		before[3] = selenium.getText(table + "/tr[" + countOfLines + "]/td[1]");

		// click line nr. 1 and 2 in the table
		selenium.click(table + "/tr[1]");
		selenium.controlKeyDown();
		selenium.click(table + "/tr[2]");
		selenium.controlKeyUp();

		// click the 'Last' button (1,2,3,..,last --> 3,..,last,1,2)
		selenium.click(buttons + "/div[7]");

		after[0] = selenium.getText(table + "/tr[1]/td[1]");
		after[1] = selenium.getText(table + "/tr[" + (countOfLines - 2)
				+ "]/td[1]");
		after[2] = selenium.getText(table + "/tr[" + (countOfLines - 1)
				+ "]/td[1]");
		after[3] = selenium.getText(table + "/tr[" + countOfLines + "]/td[1]");

		assertEquals(after[0], before[2], "The third line should be now first.");
		assertEquals(after[1], before[3],
				"The last line should be now last but two.");
		assertEquals(after[2], before[0],
				"The first line should be now last but one.");
		assertEquals(after[3], before[1], "The second line should be now last.");
	}

	@Test
	public void testFirstSingleFile() {
		String[] before = new String[3];
		String[] after = new String[3];
		int countOfLines = selenium.getXpathCount(table + "/tr").intValue();

		before[0] = selenium.getText(table + "/tr[1]/td[1]");
		before[1] = selenium.getText(table + "/tr[" + (countOfLines - 1)
				+ "]/td[1]");
		before[2] = selenium.getText(table + "/tr[" + countOfLines + "]/td[1]");

		// click the last line in the table
		selenium.click(table + "/tr[" + countOfLines + "]");
		// click the 'First' button (1...last-1,last --> last,1...last-1)
		selenium.click(buttons + "/div[1]");

		after[0] = selenium.getText(table + "/tr[1]/td[1]");
		after[1] = selenium.getText(table + "/tr[2]/td[1]");
		after[2] = selenium.getText(table + "/tr[" + countOfLines + "]/td[1]");

		assertEquals(after[0], before[2],
				"The second line should be now first.");
		assertEquals(after[1], before[0],
				"The first line should be now second.");
		assertEquals(after[2], before[1],
				"The last but one line should be now last.");
	}

	@Test
	public void testFirstMultipleFilesShift() {
		String[] before = new String[4];
		String[] after = new String[4];
		int countOfLines = selenium.getXpathCount(table + "/tr").intValue();

		before[0] = selenium.getText(table + "/tr[1]/td[1]");
		before[1] = selenium.getText(table + "/tr[" + (countOfLines - 2)
				+ "]/td[1]");
		before[2] = selenium.getText(table + "/tr[" + (countOfLines - 1)
				+ "]/td[1]");
		before[3] = selenium.getText(table + "/tr[" + countOfLines + "]/td[1]");

		// click last 2 lines in the table
		selenium.click(table + "/tr[" + (countOfLines - 1) + "]");
		selenium.shiftKeyDown();
		selenium.click(table + "/tr[" + countOfLines + "]");
		selenium.shiftKeyUp();

		// click the 'First' button (1...last-2,last-1,last -->
		// last-1,last,1...last-2)
		selenium.click(buttons + "/div[1]");

		after[0] = selenium.getText(table + "/tr[1]/td[1]");
		after[1] = selenium.getText(table + "/tr[2]/td[1]");
		after[2] = selenium.getText(table + "/tr[3]/td[1]");
		after[3] = selenium.getText(table + "/tr[" + countOfLines + "]/td[1]");

		assertEquals(after[0], before[2],
				"The last but one line should be now first.");
		assertEquals(after[1], before[3], "The last line should be now second.");
		assertEquals(after[2], before[0], "The first line should be now third.");
		assertEquals(after[3], before[1],
				"The last but two line should be now last.");
	}

	@Test
	public void testFirstMultipleFilesCtrl() {
		String[] before = new String[4];
		String[] after = new String[4];
		int countOfLines = selenium.getXpathCount(table + "/tr").intValue();

		before[0] = selenium.getText(table + "/tr[1]/td[1]");
		before[1] = selenium.getText(table + "/tr[" + (countOfLines - 2)
				+ "]/td[1]");
		before[2] = selenium.getText(table + "/tr[" + (countOfLines - 1)
				+ "]/td[1]");
		before[3] = selenium.getText(table + "/tr[" + countOfLines + "]/td[1]");

		// click last 2 lines in the table
		selenium.click(table + "/tr[" + (countOfLines - 1) + "]");
		selenium.controlKeyDown();
		selenium.click(table + "/tr[" + countOfLines + "]");
		selenium.controlKeyUp();

		// click the 'First' button (1...last-2,last-1,last -->
		// last-1,last,1...last-2)
		selenium.click(buttons + "/div[1]");

		after[0] = selenium.getText(table + "/tr[1]/td[1]");
		after[1] = selenium.getText(table + "/tr[2]/td[1]");
		after[2] = selenium.getText(table + "/tr[3]/td[1]");
		after[3] = selenium.getText(table + "/tr[" + countOfLines + "]/td[1]");

		assertEquals(after[0], before[2],
				"The last but one line should be now first.");
		assertEquals(after[1], before[3], "The last line should be now second.");
		assertEquals(after[2], before[0], "The first line should be now third.");
		assertEquals(after[3], before[1],
				"The last but two line should be now last.");
	}

	@Test
	public void testOrderingListSource() {
		abstractTestSource(1, 1, "<", "ui:composition");
	}

	/**
	 * Loads the needed page and makes us sure that no lines are selected.
	 */
	@BeforeMethod
	private void loadPage() {
		super.loadPage("richSelect", 2,
				"orderingList component allows to reorder items in a list.");

		// ensure that no lines will be selected
		selenium.click(table + "/tr[1]");
		selenium.controlKeyDown();
		selenium.click(table + "/tr[1]");
		selenium.controlKeyUp();
	}
}
