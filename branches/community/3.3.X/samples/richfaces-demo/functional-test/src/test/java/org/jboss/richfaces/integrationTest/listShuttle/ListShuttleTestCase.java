/*
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
 */

package org.jboss.richfaces.integrationTest.listShuttle;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case for list shuttle.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class ListShuttleTestCase extends AbstractSeleniumRichfacesTestCase {

    private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");
    private final String LOC_LEFT_TABLE_LINES = getLoc("LEFT_TABLE_LINES");
    private final String LOC_RIGHT_TABLE_LINES = getLoc("RIGHT_TABLE_LINES");
    private final String LOC_LEFT_TABLE_LINE_1 = getLoc("LEFT_TABLE_LINE_1");
    private final String LOC_RIGHT_TABLE_LINE_PREFORMATTED = getLoc("RIGHT_TABLE_LINE_PREFORMATTED");
    private final String LOC_TOOLBAR_ITEMS = getLoc("TOOLBAR_ITEMS");
    private final String LOC_TOOLBAR_ITEM_PREFORMATTED = getLoc("TOOLBAR_ITEM_PREFORMATTED");

    private final String LOC_COPY_ALL_BUTTON = getLoc("COPY_ALL_BUTTON");
    private final String LOC_COPY_BUTTON = getLoc("COPY_BUTTON");
    private final String LOC_REMOVE_BUTTON = getLoc("REMOVE_BUTTON");
    private final String LOC_REMOVE_ALL_BUTTON = getLoc("REMOVE_ALL_BUTTON");
    private final String LOC_FIRST_BUTTON = getLoc("FIRST_BUTTON");
    private final String LOC_UP_BUTTON = getLoc("UP_BUTTON");
    private final String LOC_DOWN_BUTTON = getLoc("DOWN_BUTTON");
    private final String LOC_LAST_BUTTON = getLoc("LAST_BUTTON");

    /**
     * Tests copy button. Fist it clicks on the first item in left table, checks
     * that copy button is enabled, and clicks the button. Then it checks that
     * one item diappeared from left table, one appeared in right table and one
     * item appeared in toolbar.
     */
    @Test
    public void testCopyButton() {
        int leftCount = selenium.getXpathCount(LOC_LEFT_TABLE_LINES).intValue();
        int rightCount = selenium.getXpathCount(LOC_RIGHT_TABLE_LINES).intValue();

        selenium.click(LOC_LEFT_TABLE_LINE_1);
        assertTrue(isDisplayed(LOC_COPY_BUTTON), "Copy button should be enabled.");

        selenium.click(LOC_COPY_BUTTON);

        int tmpInt = selenium.getXpathCount(LOC_LEFT_TABLE_LINES).intValue();
        assertEquals(tmpInt, leftCount - 1, "In left table, one item should be removed.");

        tmpInt = selenium.getXpathCount(LOC_RIGHT_TABLE_LINES).intValue();
        assertEquals(tmpInt, rightCount + 1, "In right table, one item should be added.");

        // check that it was added also to the toolbar
        tmpInt = selenium.getXpathCount(LOC_TOOLBAR_ITEMS).intValue();
        assertEquals(tmpInt, rightCount + 1, "Number of items in toolbar.");
    }

    // @Test
    // public void testCopyWithShift() {
    // fail("TODO");
    // }

    // @Test
    // public void testCopyWithCtrl() {
    // fail("TODO");
    // }

    /**
     * Tests copy all button. First it checks that the button is enabled, clicks
     * on it and verifies that all items from left table were moved to the
     * right. Then it checks that toolbar was modified.
     */
    @Test
    public void testCopyAllButton() {
        int leftCount = selenium.getXpathCount(LOC_LEFT_TABLE_LINES).intValue();
        int rightCount = selenium.getXpathCount(LOC_RIGHT_TABLE_LINES).intValue();

        assertTrue(isDisplayed(LOC_COPY_ALL_BUTTON), "Copy all button should be enabled.");

        selenium.click(LOC_COPY_ALL_BUTTON);

        int count = selenium.getXpathCount(LOC_LEFT_TABLE_LINES).intValue();
        assertEquals(count, 0, "All items from left table should be removed.");

        count = selenium.getXpathCount(LOC_RIGHT_TABLE_LINES).intValue();
        assertEquals(count, rightCount + leftCount, "All items should be added to the right table.");

        // check that they were added also to the toolbar
        count = selenium.getXpathCount(LOC_TOOLBAR_ITEMS).intValue();
        assertEquals(count, rightCount + leftCount, "Number of items in toolbar.");
    }

    /**
     * Test remove button. First it clicks on the first item on the left and
     * verifies that remove button is enabled. Then it clicks the button and
     * verifies the count of items on the left, on the right, and in the
     * toolbar.
     */
    @Test
    public void testRemoveButton() {
        int leftCount = selenium.getXpathCount(LOC_LEFT_TABLE_LINES).intValue();
        int rightCount = selenium.getXpathCount(LOC_RIGHT_TABLE_LINES).intValue();

        selenium.click(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 1));
        assertTrue(isDisplayed(LOC_REMOVE_BUTTON), "Remove button should be enabled.");

        selenium.click(LOC_REMOVE_BUTTON);

        int tmpInt = selenium.getXpathCount(LOC_LEFT_TABLE_LINES).intValue();
        assertEquals(tmpInt, leftCount + 1, "One item should be added to the left table.");

        tmpInt = selenium.getXpathCount(LOC_RIGHT_TABLE_LINES).intValue();
        assertEquals(tmpInt, rightCount - 1, "One item should be removed from the right table.");

        // check that one item was removed from toolbar
        tmpInt = selenium.getXpathCount(LOC_TOOLBAR_ITEMS).intValue();
        assertEquals(tmpInt, rightCount - 1, "Number of items in toolbar.");
    }

    // @Test
    // public void testRemoveWithShift() {
    // fail("TODO");
    // }

    // @Test
    // public void testRemoveWithCtrl() {
    // fail("TODO");
    // }

    /**
     * Tests remove all button. First it verifies that the button is enabled and
     * clicks it. Then it verifies count of items on the left, on the right, and
     * in toolbar.
     */
    @Test
    public void testRemoveAllButton() {
        int leftCount = selenium.getXpathCount(LOC_LEFT_TABLE_LINES).intValue();
        int rightCount = selenium.getXpathCount(LOC_RIGHT_TABLE_LINES).intValue();

        assertTrue(isDisplayed(LOC_REMOVE_ALL_BUTTON), "Remove all button should be enabled.");

        selenium.click(LOC_REMOVE_ALL_BUTTON);

        int tmpInt = selenium.getXpathCount(LOC_LEFT_TABLE_LINES).intValue();
        assertEquals(tmpInt, leftCount + rightCount, "All items should be added to the left table.");

        tmpInt = selenium.getXpathCount(LOC_RIGHT_TABLE_LINES).intValue();
        assertEquals(tmpInt, 0, "All items from right table should be removed.");

        // check that everything was removed from toolbar
        tmpInt = selenium.getXpathCount(LOC_TOOLBAR_ITEMS).intValue();
        assertEquals(tmpInt, 0, "Number of items in toolbar.");
    }

    /**
     * Tests button "up". First it clicks the second item on the right and
     * clicks the button. Then it verifies that the item was moved both in table
     * and in toolbar.
     */
    @Test
    public void testUpButton() {
        String firstItemFromTable = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 1));
        String firstItemFromToolbar = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 1));
        String secondItemFromTable = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 2));
        String secondItemFromToolbar = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 2));

        assertEquals(secondItemFromToolbar, secondItemFromTable,
                "The second item in right table and toolbar should be the same.");

        selenium.click(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 2));
        selenium.click(LOC_UP_BUTTON);

        // check items in the table
        String tmpString = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 1));
        assertEquals(tmpString, secondItemFromTable, "The first and the second item in the table should be swapped.");
        tmpString = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 2));
        assertEquals(tmpString, firstItemFromTable, "The first and the second item in the table should be swapped.");

        // check items in the toolbar
        tmpString = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 1));
        assertEquals(tmpString, secondItemFromToolbar,
                "The first and the second item in the toolbar should be swapped.");
        tmpString = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 2));
        assertEquals(tmpString, firstItemFromToolbar, "The first and the second item in the toolbar should be swapped.");
    }

    // @Test
    // public void testUpWithShift() {
    // fail("TODO");
    // }

    // @Test
    // public void testUpWithCtrl() {
    // fail("TODO");
    // }

    /**
     * Tests button "first". First it clicks the third item on the right and
     * clicks the button. Then it verifies that the item was moved both in table
     * and in toolbar.
     */
    @Test
    public void testFirstButton() {
        String firstItemFromTable = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 1));
        String firstItemFromToolbar = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 1));
        String secondItemFromTable = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 2));
        String secondItemFromToolbar = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 2));
        String thirdItemFromTable = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 3));
        String thirdItemFromToolbar = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 3));

        assertEquals(secondItemFromToolbar, secondItemFromTable,
                "The second item in right table and toolbar should be the same.");

        selenium.click(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 3));
        selenium.click(LOC_FIRST_BUTTON);

        // check items in the table
        String tmpString = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 2));
        assertEquals(tmpString, firstItemFromTable, "The first item from the table should be now the second.");
        tmpString = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 3));
        assertEquals(tmpString, secondItemFromTable, "The second item from the table should be now the third.");
        tmpString = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 1));
        assertEquals(tmpString, thirdItemFromTable, "The third item from the table should be now the first.");

        // check items in the toolbar
        tmpString = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 2));
        assertEquals(tmpString, firstItemFromToolbar, "The first item from the toolbar should be now the second.");
        tmpString = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 3));
        assertEquals(tmpString, secondItemFromToolbar, "The second item from the toolbar should be now the third.");
        tmpString = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 1));
        assertEquals(tmpString, thirdItemFromToolbar, "The third item from the toolbar should be now the first.");
    }

    /**
     * Tests button "down".First it clicks the first item on the right and
     * clicks the button. Then it verifies that the item was moved both in table
     * and in toolbar.
     */
    @Test
    public void testDownButton() {
        String firstItemFromTable = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 1));
        String firstItemFromToolbar = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 1));
        String secondItemFromTable = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 2));
        String secondItemFromToolbar = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 2));

        assertEquals(secondItemFromToolbar, secondItemFromTable,
                "The second item in right table and toolbar should be the same.");

        selenium.click(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 1));
        selenium.click(LOC_DOWN_BUTTON);

        // check items in the table
        String tmpString = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 1));
        assertEquals(tmpString, secondItemFromTable, "The first and the second item in the table should be swapped.");
        tmpString = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 2));
        assertEquals(tmpString, firstItemFromTable, "The first and the second item in the table should be swapped.");

        // check items in the toolbar
        tmpString = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 1));
        assertEquals(tmpString, secondItemFromToolbar,
                "The first and the second item in the toolbar should be swapped.");
        tmpString = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 2));
        assertEquals(tmpString, firstItemFromToolbar, "The first and the second item in the toolbar should be swapped.");
    }

    // @Test
    // public void testDownWithShift() {
    // fail("TODO");
    // }

    // @Test
    // public void testDownWithCtrl() {
    // fail("TODO");
    // }

    /**
     * Tests the button "last". First it clicks the first item on the right and
     * clicks the button. Then it verifies that the item was moved both in table
     * and in toolbar.
     */
    @Test
    public void testLastButton() {
        String firstItemFromTable = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 1));
        String firstItemFromToolbar = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 1));
        String secondItemFromTable = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 2));
        String secondItemFromToolbar = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 2));
        String thirdItemFromTable = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 3));
        String thirdItemFromToolbar = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 3));

        assertEquals(secondItemFromToolbar, secondItemFromTable,
                "The second item in right table and toolbar should be the same.");

        selenium.click(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 1));
        selenium.click(LOC_LAST_BUTTON);

        // check items in the table
        String tmpString = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 3));
        assertEquals(tmpString, firstItemFromTable, "The first item from the table should be now last.");
        tmpString = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 1));
        assertEquals(tmpString, secondItemFromTable, "The second item from the table should be now the first.");
        tmpString = selenium.getText(format(LOC_RIGHT_TABLE_LINE_PREFORMATTED, 2));
        assertEquals(tmpString, thirdItemFromTable, "The third item from the table should be now the second.");

        // check items in the toolbar
        tmpString = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 3));
        assertEquals(tmpString, firstItemFromToolbar, "The first item from the toolbar should be now last.");
        tmpString = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 1));
        assertEquals(tmpString, secondItemFromToolbar, "The second item from the toolbar should be now the first.");
        tmpString = selenium.getText(format(LOC_TOOLBAR_ITEM_PREFORMATTED, 2));
        assertEquals(tmpString, thirdItemFromToolbar, "The third item from the toolbar should be now the second.");
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks 5 lines of source code.
     */
    @Test
    public void testExampleSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<rich:toolBar id=\"toolBar\" itemSeparator=\"line\" height=\"28px\">",
                "<c:forEach items=\"#{toolBar.items}\" var=\"item\">", "<h:panelGroup>",
                "<h:graphicImage value=\"#{item.iconURI}\" styleClass=\"pic\" />",
                "<h:outputLink value=\"#\" style=\"color:#{a4jSkin.generalTextColor}; text-decoration:none;\" >",
                "<rich:listShuttle sourceValue=\"#{toolBar.freeItems}\"",
                "targetValue=\"#{toolBar.items}\" var=\"items\" listsHeight=\"150\"",
                "sourceListWidth=\"130\" targetListWidth=\"130\" sourceCaptionLabel=\"Available Items\"",
                "targetCaptionLabel=\"Currently Active Items\"", "converter=\"listShuttleconverter\"> ",
                "<rich:column width=\"18\"> ", "<h:graphicImage value=\"#{items.iconURI}\"></h:graphicImage>",
                "<h:outputText value=\"#{items.label}\"></h:outputText>",
                "<a4j:support event=\"onlistchanged\" reRender=\"toolBar\" />",
                "<a4j:support event=\"onorderchanged\" reRender=\"toolBar\" />", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Loads the needed page.
     */
    @SuppressWarnings("unused")
    @BeforeMethod
    private void loadPage() {
        openComponent("List Shuttle");
        scrollIntoView(LOC_EXAMPLE_HEADER, true);
    }
}
