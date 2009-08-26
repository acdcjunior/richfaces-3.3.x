/**
 * License Agreement.
 *
 *  JBoss RichFaces
 *
 * Copyright (C) 2009  Red Hat, Inc.
 *
 * This code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this code; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package org.jboss.richfaces.integrationTest.listShuttle;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class ListShuttleTestCase extends AbstractSeleniumRichfacesTestCase {

    private final String toolbar = "//fieldset[1]/div/form/table[1]/tbody/tr[1]";
    private final String leftTable = "//fieldset[1]/div/form/table[2]/tbody/tr[2]/td[1]/div/table/tbody/tr/td/div/div/table/tbody";
    private final String leftButtons = "//fieldset[1]/div/form/table[2]/tbody/tr[2]/td[2]/div";
    private final String rightTable = "//fieldset[1]/div/form/table[2]/tbody/tr[2]/td[3]/div/table/tbody/tr/td/div/div/table/tbody";
    private final String rightButtons = "//fieldset[1]/div/form/table[2]/tbody/tr[2]/td[4]/div";

    @Test
    public void testCopyButton() {
        // get the count of items in left table
        int leftCount = selenium.getXpathCount(leftTable + "/tr").intValue();
        // get the count of items in right table
        int rightCount = selenium.getXpathCount(rightTable + "/tr").intValue();

        // click the first item in left table
        selenium.click(leftTable + "/tr[1]");

        // check that the copy button is enabled
        try {
            String text = selenium.getAttribute(leftButtons + "/div[3]@style");
            assertFalse(text.contains("display: none;"), "Copy button should not have style 'display: none;'");
        } catch (Exception e) {
            // OK
        }

        // click 'Copy' button
        selenium.click(leftButtons + "/div[3]");

        int tmpInt = selenium.getXpathCount(leftTable + "/tr").intValue();
        assertEquals(tmpInt, leftCount - 1, "In left table, one item should be removed.");

        tmpInt = selenium.getXpathCount(rightTable + "/tr").intValue();
        assertEquals(tmpInt, rightCount + 1, "In right table, one item should be added.");

        // check that it was added also to the toolbar
        tmpInt = selenium.getXpathCount(toolbar + "/td[contains(@class,'rich-toolbar-item')]").intValue();
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

    @Test
    public void testCopyAllButton() {
        // click 'Remove All' button
        selenium.click(leftButtons + "/div[7]");

        // get the count of items in left table
        int leftCount = selenium.getXpathCount(leftTable + "/tr").intValue();
        // get the count of items in right table
        int rightCount = selenium.getXpathCount(rightTable + "/tr").intValue();

        // check that the 'Copy All' button is enabled
        try {
            String text = selenium.getAttribute(leftButtons + "/div[1]@style");
            assertFalse(text.contains("display: none;"), "Copy all button should not have style 'display: none;'");
        } catch (Exception e) {
            // OK
        }

        // click 'Copy All' button
        selenium.click(leftButtons + "/div[1]");

        int count = selenium.getXpathCount(leftTable + "/tr").intValue();
        assertEquals(count, 0, "All items from left table should be removed.");

        count = selenium.getXpathCount(rightTable + "/tr").intValue();
        assertEquals(count, rightCount + leftCount, "All items should be added to the right table.");

        // check that they were added also to the toolbar
        count = selenium.getXpathCount(toolbar + "/td[contains(@class,'rich-toolbar-item')]").intValue();
        assertEquals(count, rightCount + leftCount, "Number of items in toolbar.");
    }

    @Test
    public void testRemoveButton() {
        // get the count of items in left table
        int leftCount = selenium.getXpathCount(leftTable + "/tr").intValue();
        // get the count of items in right table
        int rightCount = selenium.getXpathCount(rightTable + "/tr").intValue();

        // click the first item in the right table
        selenium.click(rightTable + "/tr[1]");

        // check that the 'Remove' button is enabled
        try {
            String text = selenium.getAttribute(leftButtons + "/div[5]@style");
            assertFalse(text.contains("display: none;"), "Remove button should not have style 'display: none;'");
        } catch (Exception e) {
            // OK
        }

        // click 'Remove' button
        selenium.click(leftButtons + "/div[5]");

        int tmpInt = selenium.getXpathCount(leftTable + "/tr").intValue();
        assertEquals(tmpInt, leftCount + 1, "One item should be added to the left table.");

        tmpInt = selenium.getXpathCount(rightTable + "/tr").intValue();
        assertEquals(tmpInt, rightCount - 1, "One item should be removed from the right table.");

        // check that one item was removed from toolbar
        tmpInt = selenium.getXpathCount(toolbar + "/td[contains(@class,'rich-toolbar-item')]").intValue();
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

    @Test
    public void testRemoveAllButton() {
        // get the count of items in left table
        int leftCount = selenium.getXpathCount(leftTable + "/tr").intValue();
        // get the count of items in right table
        int rightCount = selenium.getXpathCount(rightTable + "/tr").intValue();

        // check that the 'Remove All' button is enabled
        try {
            String text = selenium.getAttribute(leftButtons + "/div[7]@style");
            assertFalse(text.contains("display: none;"), "Remove all button should not have style 'display: none;'");
        } catch (Exception e) {
            // OK
        }

        // click 'Remove All' button
        selenium.click(leftButtons + "/div[7]");

        int tmpInt = selenium.getXpathCount(leftTable + "/tr").intValue();
        assertEquals(tmpInt, leftCount + rightCount, "All items should be added to the left table.");

        tmpInt = selenium.getXpathCount(rightTable + "/tr").intValue();
        assertEquals(tmpInt, 0, "All items from right table should be removed.");

        // check that everything was removed from toolbar
        tmpInt = selenium.getXpathCount(toolbar + "/td[contains(@class,'rich-toolbar-item')]").intValue();
        assertEquals(tmpInt, 0, "Number of items in toolbar.");
    }

    @Test
    public void testUpButton() {
        // click 'Copy All' button
        selenium.click(leftButtons + "/div[1]");

        String firstItemFromTable = selenium.getText(rightTable + "/tr[1]/td[2]");
        String firstItemFromToolbar = selenium.getText(toolbar + "/td[1]");
        String secondItemFromTable = selenium.getText(rightTable + "/tr[2]/td[2]");
        String secondItemFromToolbar = selenium.getText(toolbar + "/td[3]");

        assertEquals(secondItemFromToolbar, secondItemFromTable,
                "The second item in right table and toolbar should be the same.");

        // click the second item in the right table
        selenium.click(rightTable + "/tr[2]");
        // click the 'Up' button
        selenium.click(rightButtons + "/div[3]");

        // check items in the table
        String tmpString = selenium.getText(rightTable + "/tr[1]/td[2]");
        assertEquals(tmpString, secondItemFromTable, "The first and the second item in the table should be swapped.");
        tmpString = selenium.getText(rightTable + "/tr[2]/td[2]");
        assertEquals(tmpString, firstItemFromTable, "The first and the second item in the table should be swapped.");

        // check items in the toolbar
        tmpString = selenium.getText(toolbar + "/td[1]");
        assertEquals(tmpString, secondItemFromToolbar,
                "The first and the second item in the toolbar should be swapped.");
        tmpString = selenium.getText(toolbar + "/td[3]");
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

    @Test
    public void testFirstButton() {
        // click 'Copy All' button
        selenium.click(leftButtons + "/div[1]");

        String firstItemFromTable = selenium.getText(rightTable + "/tr[1]/td[2]");
        String firstItemFromToolbar = selenium.getText(toolbar + "/td[1]");
        String secondItemFromTable = selenium.getText(rightTable + "/tr[2]/td[2]");
        String secondItemFromToolbar = selenium.getText(toolbar + "/td[3]");
        String thirdItemFromTable = selenium.getText(rightTable + "/tr[3]/td[2]");
        String thirdItemFromToolbar = selenium.getText(toolbar + "/td[5]");

        assertEquals(secondItemFromToolbar, secondItemFromTable,
                "The second item in right table and toolbar should be the same.");

        // click the third item in the right table
        selenium.click(rightTable + "/tr[3]");
        // click the 'First' button
        selenium.click(rightButtons + "/div[1]");

        // check items in the table
        String tmpString = selenium.getText(rightTable + "/tr[2]/td[2]");
        assertEquals(tmpString, firstItemFromTable, "The first item from the table should be now the second.");
        tmpString = selenium.getText(rightTable + "/tr[3]/td[2]");
        assertEquals(tmpString, secondItemFromTable, "The second item from the table should be now the third.");
        tmpString = selenium.getText(rightTable + "/tr[1]/td[2]");
        assertEquals(tmpString, thirdItemFromTable, "The third item from the table should be now the first.");

        // check items in the toolbar
        tmpString = selenium.getText(toolbar + "/td[3]");
        assertEquals(tmpString, firstItemFromToolbar, "The first item from the toolbar should be now the second.");
        tmpString = selenium.getText(toolbar + "/td[5]");
        assertEquals(tmpString, secondItemFromToolbar, "The second item from the toolbar should be now the third.");
        tmpString = selenium.getText(toolbar + "/td[1]");
        assertEquals(tmpString, thirdItemFromToolbar, "The third item from the toolbar should be now the first.");
    }

    @Test
    public void testDownButton() {
        // click 'Copy All' button
        selenium.click(leftButtons + "/div[1]");

        String firstItemFromTable = selenium.getText(rightTable + "/tr[1]/td[2]");
        String firstItemFromToolbar = selenium.getText(toolbar + "/td[1]");
        String secondItemFromTable = selenium.getText(rightTable + "/tr[2]/td[2]");
        String secondItemFromToolbar = selenium.getText(toolbar + "/td[3]");

        assertEquals(secondItemFromToolbar, secondItemFromTable,
                "The second item in right table and toolbar should be the same.");

        // click the first item in the right table
        selenium.click(rightTable + "/tr[1]");
        // click the 'Down' button
        selenium.click(rightButtons + "/div[5]");

        // check items in the table
        String tmpString = selenium.getText(rightTable + "/tr[1]/td[2]");
        assertEquals(tmpString, secondItemFromTable, "The first and the second item in the table should be swapped.");
        tmpString = selenium.getText(rightTable + "/tr[2]/td[2]");
        assertEquals(tmpString, firstItemFromTable, "The first and the second item in the table should be swapped.");

        // check items in the toolbar
        tmpString = selenium.getText(toolbar + "/td[1]");
        assertEquals(tmpString, secondItemFromToolbar,
                "The first and the second item in the toolbar should be swapped.");
        tmpString = selenium.getText(toolbar + "/td[3]");
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

    @Test
    public void testLastButton() {
        // click 'Copy All' button
        selenium.click(leftButtons + "/div[1]");

        String firstItemFromTable = selenium.getText(rightTable + "/tr[1]/td[2]");
        String firstItemFromToolbar = selenium.getText(toolbar + "/td[1]");
        String secondItemFromTable = selenium.getText(rightTable + "/tr[2]/td[2]");
        String secondItemFromToolbar = selenium.getText(toolbar + "/td[3]");
        String thirdItemFromTable = selenium.getText(rightTable + "/tr[3]/td[2]");
        String thirdItemFromToolbar = selenium.getText(toolbar + "/td[5]");

        assertEquals(secondItemFromToolbar, secondItemFromTable,
                "The second item in right table and toolbar should be the same.");

        // click the first item in the right table
        selenium.click(rightTable + "/tr[1]");
        // click the 'Last' button
        selenium.click(rightButtons + "/div[7]");

        // check items in the table
        String tmpString = selenium.getText(rightTable + "/tr[7]/td[2]");
        assertEquals(tmpString, firstItemFromTable, "The first item from the table should be now last.");
        tmpString = selenium.getText(rightTable + "/tr[1]/td[2]");
        assertEquals(tmpString, secondItemFromTable, "The second item from the table should be now the first.");
        tmpString = selenium.getText(rightTable + "/tr[2]/td[2]");
        assertEquals(tmpString, thirdItemFromTable, "The third item from the table should be now the second.");

        // check items in the toolbar
        tmpString = selenium.getText(toolbar + "/td[13]");
        assertEquals(tmpString, firstItemFromToolbar, "The first item from the toolbar should be now last.");
        tmpString = selenium.getText(toolbar + "/td[1]");
        assertEquals(tmpString, secondItemFromToolbar, "The second item from the toolbar should be now the first.");
        tmpString = selenium.getText(toolbar + "/td[3]");
        assertEquals(tmpString, thirdItemFromToolbar, "The third item from the toolbar should be now the second.");
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
        super.loadPage("richSelect", 1, "listShuttle component allows to select items using two lists");
        openComponent("List Shuttle");
        scrollIntoView("//fieldset[1]/legend", true);
    }
}
