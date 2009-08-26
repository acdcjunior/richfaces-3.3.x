package org.jboss.richfaces.integrationTest.tabPanel;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.Test;

/**
 * Test case that tests tab panel. Tests all three tabs -- usage, customization,
 * and deletion.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class TabPanelTestCase extends AbstractSeleniumRichfacesTestCase {

	// messages -- Usage
	private final String MSG_USAGE_COMPONENT_DESCRIPTION = getMess("USAGE_COMPONENT_DESCRIPTION");
	private final String MSG_USAGE_CONTENT_OF_PANEL_N = getMess("USAGE_CONTENT_OF_PANEL_N");
	private final String MSG_USAGE_SECOND_TAB_SHOULD_BE_DISABLED = getMess("USAGE_SECOND_TAB_SHOULD_BE_DISABLED");

	// messages -- Look Customization
	private final String MSG_CUST_COMPONENT_DESCRIPTION = getMess("CUST_COMPONENT_DESCRIPTION");
	private final String MSG_CUST_TABS_RIGHT_ALIGNED = getMess("CUST_TABS_RIGHT_ALIGNED");

	// messages -- Tabs Deletion
	private final String MSG_DELET_COMPONENT_DESCRIPTION = getMess("DELET_COMPONENT_DESCRIPTION");
	private final String MSG_DELET_NUMBER_OF_TABS = getMess("DELET_NUMBER_OF_TABS");

	// locators -- Usage
	private final String LOC_USAGE_EXAMPLE_HEADER = getLoc("USAGE_EXAMPLE_HEADER");
	private final String LOC_USAGE_PANEL_N_TAB_M = getLoc("USAGE_PANEL_N_TAB_M");
	private final String LOC_USAGE_PANEL_N_TEXT = getLoc("USAGE_PANEL_N_TEXT");
	private final String LOC_USAGE_PANEL_3_N_TEXT = getLoc("USAGE_PANEL_3_N_TEXT");

	// locators -- Look Customization
	private final String LOC_CUST_EXAMPLE_HEADER = getLoc("CUST_EXAMPLE_HEADER");
	private final String LOC_CUST_TABS_ALIGN = getLoc("CUST_TABS_ALIGN");
	private final String LOC_CUST_PANEL_TEXT = getLoc("CUST_PANEL_TEXT");
	private final String LOC_CUST_PANEL_TAB_N = getLoc("CUST_PANEL_TAB_N");

	// locators -- Tabs Deletion
	private final String LOC_DELET_EXAMPLE_HEADER = getLoc("DELET_EXAMPLE_HEADER");
	private final String LOC_DELET_PANEL_TABS = getLoc("DELET_PANEL_TABS");
	private final String LOC_DELET_PANEL_TAB_N_CLOSE = getLoc("DELET_PANEL_TAB_N_CLOSE");
	private final String LOC_DELET_RESET_BUTTON = getLoc("DELET_RESET_BUTTON");

	/**
	 * Tests three tab panels from the usage tab. It click on each tab and
	 * verifies that the content of panel changed appropriately.
	 */
	@Test
	public void testTabPanelExample() {
		super.loadPage("richOutputs", 10, 1, MSG_USAGE_COMPONENT_DESCRIPTION);
		String text;

		// test the first tab panel
		for (int i = 3; i > 0; i--) {
			scrollIntoView(LOC_USAGE_EXAMPLE_HEADER, true); // scrolling is
															// forgotten after
															// page reload
			selenium.click(String.format(LOC_USAGE_PANEL_N_TAB_M, 1, i));
			waitFor(2000);
			text = selenium.getText(String.format(LOC_USAGE_PANEL_N_TEXT, 1));
			assertEquals(text, "Here is tab #" + i, String.format(
					MSG_USAGE_CONTENT_OF_PANEL_N, 1));
		}

		// test the second tab panel
		scrollIntoView(LOC_USAGE_EXAMPLE_HEADER, true);
		selenium.click(String.format(LOC_USAGE_PANEL_N_TAB_M, 2, 3));
		waitFor(2000);
		text = selenium.getText(String.format(LOC_USAGE_PANEL_N_TEXT, 2));
		assertEquals(text, "Here is tab #" + 3, String.format(
				MSG_USAGE_CONTENT_OF_PANEL_N, 2));

		selenium.click(String.format(LOC_USAGE_PANEL_N_TAB_M, 2, 1));
		waitFor(2000);
		text = selenium.getText(String.format(LOC_USAGE_PANEL_N_TEXT, 2));
		assertEquals(text, "Here is tab #" + 1, String.format(
				MSG_USAGE_CONTENT_OF_PANEL_N, 2));

		// test the disabled tab
		boolean isEnabled = true;
		try {
			selenium.getAttribute(
					String.format(LOC_USAGE_PANEL_N_TAB_M, 2, 2) + "@class")
					.contains("rich-tab-diabled");
			assertFalse(isEnabled, MSG_USAGE_SECOND_TAB_SHOULD_BE_DISABLED);
		} catch (Exception e) {
			// OK - there is no class attribute
		}
		selenium.click(String.format(LOC_USAGE_PANEL_N_TAB_M, 2, 2));
		waitFor(2000);
		text = selenium.getText(String.format(LOC_USAGE_PANEL_N_TEXT, 2));
		assertEquals(text, "Here is tab #1", String.format(
				MSG_USAGE_CONTENT_OF_PANEL_N, 2));

		// test the third tab panel -- client type work different
		scrollIntoView(LOC_USAGE_EXAMPLE_HEADER, true);
		for (int i = 3; i > 0; i--) {
			selenium.click(String.format(LOC_USAGE_PANEL_N_TAB_M, 3, i));
			waitFor(2000);
			for (int j = 1; j < 4; j++) {
				if (i == j) {
					assertTrue(isDisplayed(String.format(
							LOC_USAGE_PANEL_3_N_TEXT, j)));
				} else {
					assertFalse(isDisplayed(String.format(
							LOC_USAGE_PANEL_3_N_TEXT, j)));
				}
			}
		}
	}

	/**
	 * Tests tab panel customization. It clicks every tab and verifies that the
	 * content of panel changed. Then it checks that the tabs are aligned to the
	 * right.
	 */
	@Test
	public void testCustomization() {
		super.loadPage("richOutputs", 10, 2, MSG_CUST_COMPONENT_DESCRIPTION);
		scrollIntoView(LOC_CUST_EXAMPLE_HEADER, true);

		String text;

		for (int i = 3; i > 0; i--) {
			selenium.click(String.format(LOC_CUST_PANEL_TAB_N, i));
			waitFor(2000);
			for (int j = 1; j < 4; j++) {
				if (i == j) {
					assertTrue(isDisplayed(String
							.format(LOC_CUST_PANEL_TEXT, j)));
				} else {
					assertFalse(isDisplayed(String.format(LOC_CUST_PANEL_TEXT,
							j)));
				}
			}
		}

		text = selenium.getAttribute(LOC_CUST_TABS_ALIGN);
		assertEquals(text, "right", MSG_CUST_TABS_RIGHT_ALIGNED);
	}

	/**
	 * Tests tabs deletion. First it check that there are 3 tabs, then deletes
	 * the third, the second, and the first tab. After each deletion it verifies
	 * the number of remaining tabs. In the end it clicks the reset button and
	 * checks that all tabs were restored.
	 */
	@Test
	public void testTabsDeletion() {
		super.loadPage("richOutputs", 10, 3, MSG_DELET_COMPONENT_DESCRIPTION);
		scrollIntoView(LOC_DELET_EXAMPLE_HEADER, true);

		int count = selenium.getXpathCount(LOC_DELET_PANEL_TABS).intValue();
		assertEquals(count - 4, 3, MSG_DELET_NUMBER_OF_TABS);

		for (int i = 3; i > 0; i--) {
			selenium.click(String.format(LOC_DELET_PANEL_TAB_N_CLOSE, i));
			waitFor(2000);
			count = selenium.getXpathCount(LOC_DELET_PANEL_TABS).intValue();
			assertEquals(count / 2, i - 1, MSG_DELET_NUMBER_OF_TABS);
		}

		selenium.click(LOC_DELET_RESET_BUTTON);
		waitFor(2000);
		count = selenium.getXpathCount(LOC_DELET_PANEL_TABS).intValue();
		assertEquals(count - 4, 3, MSG_DELET_NUMBER_OF_TABS);
	}

	/**
	 * Tests the "View Source". It checks that the source code is not visible,
	 * clicks on the link, and checks 5 lines of source code.
	 */
	@Test
	public void testUsageTabExampleSource() {
		String[] strings = new String[] {
				"<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
				"<rich:tabPanel>", "<rich:tab label=\"First\">",
				"Here is tab #2", "<rich:tabPanel switchType=\"ajax\">",
				"<rich:tab label=\"Second\" disabled=\"true\">",
				"<rich:tabPanel switchType=\"client\">", };

		abstractTestSource(1, "View Source", strings);
	}

}
