/*******************************************************************************
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *******************************************************************************/

package org.jboss.richfaces.integrationTest.panelMenu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
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
	private final String MSG_CONTENT_OF_PANEL_ON_LOAD = getMsg("CONTENT_OF_PANEL_ON_LOAD");
	private final String MSG_CONTENT_OF_PANEL = getMsg("CONTENT_OF_PANEL");
	private final String MSG_IMAGE_BEFORE_GROUP_NAME = getMsg("IMAGE_BEFORE_GROUP_NAME");
	private final String MSG_NAME_OF_GROUP = getMsg("NAME_OF_GROUP");
	private final String MSG_IMAGE_AFTER_GROUP_NAME_DOWN = getMsg("IMAGE_AFTER_GROUP_NAME_DOWN");
	private final String MSG_IMAGE_AFTER_GROUP_NAME_UP = getMsg("IMAGE_AFTER_GROUP_NAME_UP");
	private final String MSG_IMAGE_AFTER_GROUP_NAME_SPACER = getMsg("IMAGE_AFTER_GROUP_NAME_SPACER");
	private final String MSG_ITEM_M_N_NOT_VISIBLE = getMsg("ITEM_M_N_NOT_VISIBLE");
	private final String MSG_ITEM_M_N_VISIBLE = getMsg("ITEM_M_N_VISIBLE");
	private final String MSG_IMAGE_BEFORE_ITEM_GRID = getMsg("IMAGE_BEFORE_ITEM_GRID");
	private final String MSG_DISK_BEFORE_GROUP_NAME = getMsg("DISK_BEFORE_GROUP_NAME");
	private final String MSG_SUBITEM_M_NOT_VISIBLE = getMsg("SUBITEM_M_NOT_VISIBLE");
	private final String MSG_SUBITEM_M_VISIBLE = getMsg("SUBITEM_M_VISIBLE");

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

	private final String LOC_SUBITEM_M_IMAGE = getLoc("SUBITEM_M_IMAGE");
	private final String LOC_SUBITEM_M_TEXT = getLoc("SUBITEM_M_TEXT");
	private final String LOC_SUBITEM_M_STYLE = getLoc("SUBITEM_M_STYLE");

	/**
	 * Tests the first group. First it checks the image before group name, group
	 * name, and image after group name. Then it checks that all items from the
	 * group are hidden. Then it expands the group and verifies that the image
	 * right of group name changed and that all items are visible. In the end it
	 * checks that there is a grid in front of each item.
	 */
	@Test
	public void testFirstGroup() {
		abstractTestGroupN(0);
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
		abstractTestGroupN(1);
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
		abstractTestGroupN(2);
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
		
		selenium.click(format(LOC_GROUP_N_TEXT, 1));

		// image before subgroup name
		text = selenium.getAttribute(format(LOC_ITEM_M_N_IMAGE_BEFORE, 1, 3));
		assertTrue(text.contains("PanelMenuIconDisc"), MSG_DISK_BEFORE_GROUP_NAME);

		// subgroup name
		text = selenium.getText(format(LOC_ITEM_M_N_TEXT, 1, 3));
		assertEquals(text, "Group 2.4", MSG_NAME_OF_GROUP);

		// image after subgroup name
		text = selenium.getAttribute(format(LOC_ITEM_M_N_IMAGE_AFTER, 1, 3));
		assertTrue(text.contains("PanelMenuIconSpacer"), MSG_IMAGE_AFTER_GROUP_NAME_SPACER);

		for (int i = 0; i < 3; i++) {
			assertFalse(isDisplayed(format(LOC_SUBITEM_M_STYLE, i)), format(MSG_SUBITEM_M_NOT_VISIBLE, i + 1));
		}

		// click Group 2.4
		selenium.click(format(LOC_ITEM_M_N_TEXT, 1, 3));

		// image after subgroup name
		text = selenium.getAttribute(format(LOC_ITEM_M_N_IMAGE_AFTER, 1, 3));
		assertTrue(text.contains("PanelMenuIconSpacer"), MSG_IMAGE_AFTER_GROUP_NAME_SPACER);

		for (int i = 0; i < 3; i++) {
			// check that items are not hidden
			assertTrue(isDisplayed(format(LOC_SUBITEM_M_STYLE, i)), format(MSG_SUBITEM_M_VISIBLE, i + 1));
			
			// check the image left of the item
			text = selenium.getAttribute(format(LOC_SUBITEM_M_IMAGE, i));
			assertTrue(text.contains("PanelMenuIconGrid"), MSG_IMAGE_BEFORE_ITEM_GRID);
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
		for (int i = 0; i < 3; i++) {
		    final int newI = i;
		    selenium.click(format(LOC_GROUP_N_TEXT, i));
			
		    for (int j = 0; j < 3; j++) {
			    final int newJ = j;
			    selenium.click(format(LOC_ITEM_M_N_TEXT, i, j));
				
				Wait.until(new Condition() {
                    public boolean isTrue() {
                        return format("Item {0}.{1} selected", newI + 1, newJ + 1).equals(selenium.getText(LOC_PANEL));
                    }
                });
				
				text = selenium.getText(LOC_PANEL);
				assertEquals(text, format("Item {0}.{1} selected", i + 1, j + 1), MSG_CONTENT_OF_PANEL);
			}
		}

		// check items on the second level (2.4.1, 2.4.2, 2.4.3)
		selenium.click(format(LOC_ITEM_M_N_TEXT, 1, 3));
		for (int i = 0; i < 3; i++) {
		    final int newI = i;
		    selenium.click(format(LOC_SUBITEM_M_TEXT, i));
			
		    Wait.until(new Condition() {
                public boolean isTrue() {
                    return format("Item 2.4.{0} selected", newI + 1).equals(selenium.getText(LOC_PANEL));
                }
            });
			
		    text = selenium.getText(LOC_PANEL);
			assertEquals(text, format("Item 2.4.{0} selected", i + 1), MSG_CONTENT_OF_PANEL);
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
		text = selenium.getAttribute(format(LOC_GROUP_N_IMAGE_BEFORE, index));
		assertTrue(text.contains("PanelMenuIconSpacer"), MSG_IMAGE_BEFORE_GROUP_NAME);

		// group name
		text = selenium.getText(format(LOC_GROUP_N_TEXT, index));
		assertEquals(text, "Group " + (index + 1), MSG_NAME_OF_GROUP);

		// image after group name
		text = selenium.getAttribute(format(LOC_GROUP_N_IMAGE_AFTER, index));
		assertTrue(text.contains("PanelMenuIconChevronDown"), MSG_IMAGE_AFTER_GROUP_NAME_DOWN);

		for (int i = 0; i < 3; i++) {
			assertFalse(isDisplayed(format(LOC_ITEM_M_N_STYLE, index, i)), format(MSG_ITEM_M_N_NOT_VISIBLE, index + 1, i + 1));
		}

		selenium.click(format(LOC_GROUP_N_TEXT, index));

		// image after group name
		text = selenium.getAttribute(format(LOC_GROUP_N_IMAGE_AFTER, index));
		assertTrue(text.contains("PanelMenuIconChevronUp"), MSG_IMAGE_AFTER_GROUP_NAME_UP);

		for (int i = 0; i < 3; i++) {
			// check that items are not hidden
			assertTrue(isDisplayed(format(LOC_ITEM_M_N_STYLE, index, i)), format(MSG_ITEM_M_N_VISIBLE, index + 1, i + 1));
			
			// check the image left of the item
			text = selenium.getAttribute(format(LOC_ITEM_M_N_IMAGE_BEFORE, index, i));
			assertTrue(text.contains("PanelMenuIconGrid"), MSG_IMAGE_BEFORE_ITEM_GRID);
		}
	}

	/**
	 * Loads the page containing the component.
	 */
	protected void loadPage() {
	    openComponent("Panel Menu");
	    scrollIntoView(LOC_EXAMPLE_HEADER, true);
	}
}
