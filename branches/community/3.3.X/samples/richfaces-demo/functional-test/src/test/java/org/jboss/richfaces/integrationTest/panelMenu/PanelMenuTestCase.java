package org.jboss.richfaces.integrationTest.panelMenu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests panel menu.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class PanelMenuTestCase extends AbstractSeleniumRichfacesTestCase {

	// messages
	private final String MSG_COMPONENT_DESCRIPTION = getMess("COMPONENT_DESCRIPTION");
	private final String MSG_CONTENT_OF_PANEL_ON_LOAD = getMess("CONTENT_OF_PANEL_ON_LOAD");
	private final String MSG_CONTENT_OF_PANEL = getMess("CONTENT_OF_PANEL");
	private final String MSG_IMAGE_BEFORE_GROUP_NAME = getMess("IMAGE_BEFORE_GROUP_NAME");
	private final String MSG_NAME_OF_GROUP = getMess("NAME_OF_GROUP");
	private final String MSG_IMAGE_AFTER_GROUP_NAME_DOWN = getMess("IMAGE_AFTER_GROUP_NAME_DOWN");
	private final String MSG_IMAGE_AFTER_GROUP_NAME_UP = getMess("IMAGE_AFTER_GROUP_NAME_UP");
	private final String MSG_IMAGE_AFTER_GROUP_NAME_SPACER = getMess("IMAGE_AFTER_GROUP_NAME_SPACER");
	private final String MSG_ITEM_M_N_NOT_VISIBLE = getMess("ITEM_M_N_NOT_VISIBLE");
	private final String MSG_ITEM_M_N_VISIBLE = getMess("ITEM_M_N_VISIBLE");
	private final String MSG_IMAGE_BEFORE_ITEM_GRID = getMess("IMAGE_BEFORE_ITEM_GRID");
	private final String MSG_DISK_BEFORE_GROUP_NAME = getMess("DISK_BEFORE_GROUP_NAME");
	private final String MSG_SUBITEM_M_NOT_VISIBLE = getMess("SUBITEM_M_NOT_VISIBLE");
	private final String MSG_SUBITEM_M_VISIBLE = getMess("SUBITEM_M_VISIBLE");

	// locators
	private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");
	private final String LOC_PANEL = getLoc("PANEL");
	
	private final String LOC_GROUP_N_IMAGE_BEFORE = getLoc("GROUP_N_IMAGE_BEFORE");
	private final String LOC_GROUP_N_IMAGE_AFTER = getLoc("GROUP_N_IMAGE_AFTER");
	private final String LOC_GROUP_N_TEXT = getLoc("GROUP_N_TEXT");

	private final String LOC_ITEM_M_N_IMAGE_BEFORE = getLoc("ITEM_M_N_IMAGE_BEFORE");
	private final String LOC_ITEM_M_N_IMAGE_AFTER = getLoc("ITEM_M_N_IMAGE_AFTER");
	private final String LOC_ITEM_M_N_TEXT = getLoc("ITEM_M_N_TEXT");
	private final String LOC_ITEM_M_N_STYLE = getLoc("ITEM_M_N_STYLE");

	private final String LOC_SUBITEM_GROUP = getLoc("SUBITEM_GROUP");
	private final String LOC_SUBITEM_M_IMAGE_BEFORE = formatLoc("SUBITEM_M_IMAGE_BEFORE_RELATIVE_TO_GROUP", LOC_SUBITEM_GROUP);
	private final String LOC_SUBITEM_M_TEXT = formatLoc("SUBITEM_M_TEXT_RELATIVE_TO_GROUP", LOC_SUBITEM_GROUP);
	private final String LOC_SUBITEM_M_STYLE = formatLoc("SUBITEM_M_STYLE_RELATIVE_TO_GROUP", LOC_SUBITEM_GROUP);

	/**
	 * Tests the first group. First it checks the image before group name, group
	 * name, and image after group name. Then it checks that all items from the
	 * group are hidden. Then it expands the group and verifies that the image
	 * right of group name changed and that all items are visible. In the end it
	 * checks that there is a grid in front of each item.
	 */
	@Test
	public void testFirstGroup() {
		abstractTestGroupN(1);
	}

	/**
	 * Tests the second group. First it checks the image before group name,
	 * group name, and image after group name. Then it checks that all items
	 * from the group are hidden. Then it expands the group and verifies that
	 * the image right of group name changed and that all items are visible. In
	 * the end it checks that there is a grid in front of each item.
	 */
	@Test
	public void testSecondGroup() {
		abstractTestGroupN(2);
	}

	/**
	 * Tests the third group. First it checks the image before group name, group
	 * name, and image after group name. Then it checks that all items from the
	 * group are hidden. Then it expands the group and verifies that the image
	 * right of group name changed and that all items are visible. In the end it
	 * checks that there is a grid in front of each item.
	 */
	@Test
	public void testThirdGroup() {
		abstractTestGroupN(3);
	}

	/**
	 * Tests subgroup (2.4). First it checks the image before group name, group
	 * name, and image after group name. Then it checks that all items from the
	 * group are hidden. Then it expands the group and verifies that the image
	 * right of group name is the same and that all items are visible. In the
	 * end it checks that there is a grid in front of each item.
	 */
	@Test
	public void testSubgroup() {
		String text = null;
		
		selenium.click(String.format(LOC_GROUP_N_TEXT, 2));

		// image before subgroup name
		text = selenium.getAttribute(String.format(LOC_ITEM_M_N_IMAGE_BEFORE, 2, 4));
		assertTrue(text.contains("PanelMenuIconDisc"),
				MSG_DISK_BEFORE_GROUP_NAME);

		// subgroup name
		text = selenium.getText(String.format(LOC_ITEM_M_N_TEXT, 2, 4));
		assertEquals(text, "Group 2.4", MSG_NAME_OF_GROUP);

		// image after subgroup name
		text = selenium.getAttribute(String.format(LOC_ITEM_M_N_IMAGE_AFTER, 2,
				4));
		assertTrue(text.contains("PanelMenuIconSpacer"),
				MSG_IMAGE_AFTER_GROUP_NAME_SPACER);

		for (int i = 1; i < 4; i++) {
			text = selenium.getAttribute(String.format(LOC_SUBITEM_M_STYLE, i));
			assertFalse(!text.contains("display: none;"), String.format(
					MSG_SUBITEM_M_NOT_VISIBLE, i));
		}

		// click Group 2.4
		selenium.click(String.format(LOC_ITEM_M_N_TEXT, 2, 4));

		// image after subgroup name
		text = selenium.getAttribute(String.format(LOC_ITEM_M_N_IMAGE_AFTER, 2,
				4));
		assertTrue(text.contains("PanelMenuIconSpacer"),
				MSG_IMAGE_AFTER_GROUP_NAME_SPACER);

		for (int i = 1; i < 4; i++) {
			// check that items are not hidden
			try {
				text = selenium.getAttribute(String.format(LOC_SUBITEM_M_STYLE,
						i));
				assertTrue(!text.contains("display: none;"), String.format(
						MSG_SUBITEM_M_VISIBLE, i));
			} catch (Exception e) {
				// OK -- there is no style attribute
			}

			// check the image left of the item
			System.out.println("--------------"
					+ String.format(LOC_SUBITEM_M_IMAGE_BEFORE, i) + "-----");
			text = selenium.getAttribute(String.format(
					LOC_SUBITEM_M_IMAGE_BEFORE, i));
			System.out.println("--------------" + text + "-----");
			assertTrue(text.contains("PanelMenuIconGrid"),
					MSG_IMAGE_BEFORE_ITEM_GRID);
		}
	}

	/**
	 * Tests content of the panel. First it verifies the default string in the
	 * panel. Then it goes through first three items from each group and
	 * verifies the text in the panel. After that it clicks all items in group
	 * 2.4 and checks content of the panel again.
	 */
	@Test
	public void testPanel() {
		String text = selenium.getText(LOC_PANEL);
		assertEquals(text, "Nothing selected", MSG_CONTENT_OF_PANEL_ON_LOAD);

		// check items on the first level (1.1, 1.2, etc.)
		for (int i = 1; i < 4; i++) {
			selenium.click(String.format(LOC_GROUP_N_TEXT, i));
			for (int j = 1; j < 4; j++) {
				selenium.click(String.format(LOC_ITEM_M_N_TEXT, i, j));
				waitFor(500); // it use Ajax mode
				text = selenium.getText(LOC_PANEL);
				assertEquals(text, String.format("Item %d.%d selected", i, j),
						MSG_CONTENT_OF_PANEL);
			}
		}

		// check items on the second level (2.4.1, 2.4.2, 2.4.3)
		selenium.click(String.format(LOC_ITEM_M_N_TEXT, 2, 4));
		for (int i = 1; i < 4; i++) {
			selenium.click(String.format(LOC_SUBITEM_M_TEXT, i));
			waitFor(500); // it use Ajax mode
			text = selenium.getText(LOC_PANEL);
			assertEquals(text, String.format("Item 2.4.%d selected", i),
					MSG_CONTENT_OF_PANEL);
		}
	}

	/**
	 * Tests the "View Source". It checks that the source code is not visible,
	 * clicks on the link, and checks 7 lines of source code.
	 */
	@Test
	public void testExampleSource() {
		String[] strings = new String[] {
				"<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
				"<h:form id=\"form\">",
				"iconExpandedTopGroup=\"chevronUp\" iconGroupTopPosition=\"right\"",
				"<f:param name=\"current\" value=\"Item 1.1\"/>",
				"<rich:panel bodyClass=\"rich-laguna-panel-no-header\">",
				"<h:outputText value=\"#{panelMenu.current} selected\" id=\"current\"/>",
				"</ui:composition>", };

		abstractTestSource(1, "View Source", strings);
	}

	/**
	 * Tests one group.
	 * 
	 * @param index
	 *            number of group to be tested
	 */
	private void abstractTestGroupN(int index) {
		String text = null;
		
		// spacer before group name
		text = selenium.getAttribute(String.format(LOC_GROUP_N_IMAGE_BEFORE,
				index));
		assertTrue(text.contains("PanelMenuIconSpacer"),
				MSG_IMAGE_BEFORE_GROUP_NAME);

		// group name
		text = selenium.getText(String.format(LOC_GROUP_N_TEXT, index));
		assertEquals(text, "Group " + index, MSG_NAME_OF_GROUP);

		// image after group name
		text = selenium.getAttribute(String.format(LOC_GROUP_N_IMAGE_AFTER,
				index));
		assertTrue(text.contains("PanelMenuIconChevronDown"),
				MSG_IMAGE_AFTER_GROUP_NAME_DOWN);

		for (int i = 1; i < 4; i++) {
			text = selenium.getAttribute(String.format(LOC_ITEM_M_N_STYLE,
					index, i));
			assertFalse(!text.contains("display: none;"), String.format(
					MSG_ITEM_M_N_NOT_VISIBLE, index, i));
		}

		selenium.click(String.format(LOC_GROUP_N_TEXT, index));

		// image after group name
		text = selenium.getAttribute(String.format(LOC_GROUP_N_IMAGE_AFTER,
				index));
		assertTrue(text.contains("PanelMenuIconChevronUp"),
				MSG_IMAGE_AFTER_GROUP_NAME_UP);

		for (int i = 1; i < 4; i++) {
			// check that items are not hidden
			try {
				text = selenium.getAttribute(String.format(LOC_ITEM_M_N_STYLE,
						index, i));
				assertTrue(!text.contains("display: none;"), String.format(
						MSG_ITEM_M_N_VISIBLE, index, i));
			} catch (Exception e) {
				// OK -- there is no style attribute
			}

			// check the image left of the item
			text = selenium.getAttribute(String.format(
					LOC_ITEM_M_N_IMAGE_BEFORE, index, i));
			assertTrue(text.contains("PanelMenuIconGrid"),
					MSG_IMAGE_BEFORE_ITEM_GRID);
		}
	}

	/**
	 * Loads the page containing the component.
	 */
	@BeforeMethod
	private void loadPage() {
//		selenium.allowNativeXpath("true");
		super.loadPage("richOutputs", 5, 1, MSG_COMPONENT_DESCRIPTION);
		scrollIntoView(LOC_EXAMPLE_HEADER, true);
	}
}
