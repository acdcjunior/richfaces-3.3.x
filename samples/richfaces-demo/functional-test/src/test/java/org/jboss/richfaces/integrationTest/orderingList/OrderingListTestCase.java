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

package org.jboss.richfaces.integrationTest.orderingList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsNoOrder;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case for ordering list.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
// TODO check that the number of lines does not change on Up, Down, First, Last
// (+Ctrl/Shift)
// TODO check that all buttons are disabled if no lines selected
// TODO refactor tests so that the common functionality is in one method
public class OrderingListTestCase extends AbstractSeleniumRichfacesTestCase {

    // locators
    private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");
    private final String LOC_TABLE_LINES = getLoc("TABLE_LINES");
    private final String LOC_TABLE_LINE_PREFORMATTED = getLoc("TABLE_LINE_PREFORMATTED");
    private final String LOC_SELECTION = getLoc("SELECTION");
    private final String LOC_SELECTION_PREFORMATTED = getLoc("SELECTION_PREFORMATTED");

    private final String LOC_BUTTON_FIRST = getLoc("BUTTON_FIRST");
    private final String LOC_BUTTON_UP = getLoc("BUTTON_UP");
    private final String LOC_BUTTON_DOWN = getLoc("BUTTON_DOWN");
    private final String LOC_BUTTON_LAST = getLoc("BUTTON_LAST");

    // messages
    private final String MSG_ORDERING_LIST_SELECTION = getMsg("ORDERING_LIST_SELECTION");

    /**
     * Tests initial state of the ordering list.
     */
    @Test
    public void testOrderingList() {
        int count = getJQueryCount(LOC_TABLE_LINES);
        assertTrue(count > 0, "There are no lines in the table.");

        waitFor(1000);
        String text = selenium.getText(LOC_SELECTION);
        assertEquals(text, MSG_ORDERING_LIST_SELECTION, "Selection should not contain any name of song.");
    }

    /**
     * Tests clicking on a single line.
     */
    @Test
    public void testClickSingleSong() {
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        String text = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));

        waitFor(1000);
        String text2 = selenium.getText(LOC_SELECTION);

        assertEquals(text2, text, "Selected song is not shown in the selection panel.");
    }

    /**
     * Tests clicking on multiple lines using Ctrl key.
     */
    @Test
    public void testClickMultipleSongsCtrl() {
        String[] fromTable = new String[3];
        String[] fromPanel = new String[3];

        // click lines nr. 1, 3, and 5 in the table
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        selenium.controlKeyDown();
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 2));
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 4));
        selenium.controlKeyUp();

        fromTable[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        fromTable[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 2));
        fromTable[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 4));

        waitFor(1000);

        // get all selections from the panel on the right
        for (int i = 0; i < 3; i++) {
            fromPanel[i] = selenium.getText(format(LOC_SELECTION_PREFORMATTED, i+1));
        }

        assertEqualsNoOrder(fromPanel, fromTable, "Items chosen in table should be also in the right panel.");
    }

    /**
     * Tests clicking on multiple lines using Shift key.
     */
    @Test
    public void testClickMultipleSongsShift() {
        String[] fromTable = new String[3];
        String[] fromPanel = new String[3];

        // click lines nr. 1 and 3 in the table
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        selenium.shiftKeyDown();
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 2));
        selenium.shiftKeyUp();

        fromTable[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        fromTable[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 1));
        fromTable[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 2));

        waitFor(1000);

        // get all selections from the panel on the right
        for (int i = 0; i < 3; i++) {
            fromPanel[i] = selenium.getText(format(LOC_SELECTION_PREFORMATTED, i+1));
        }

        assertEqualsNoOrder(fromPanel, fromTable, "Items chosen in table should be also in the right panel.");
    }

    /**
     * Tests the button up for a single song.
     */
    @Test
    public void testUpSingleSong() {
        String[] before = new String[2];
        String[] after = new String[2];

        before[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 4));
        before[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 5));

        // click line nr. 6 in the table
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        // click the 'Up' button
        selenium.click(LOC_BUTTON_UP);

        waitFor(1000);

        after[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 4));
        after[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 5));

        assertEquals(after[0], before[1], "The sixth line should be now fifth.");
        assertEquals(after[1], before[0], "The fifth line should be now sixth.");
    }

    /**
     * Tests the button up for multiple songs selected using Shift key.
     */
    @Test
    public void testUpMultipleSongsShift() {
        String[] before = new String[3];
        String[] after = new String[3];

        before[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 4));
        before[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        before[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 6));

        // click line nr. 6 and 7 in the table
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        selenium.shiftKeyDown();
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 6));
        selenium.shiftKeyUp();

        // click the 'Up' button (5,6,7 -> 6,7,5)
        selenium.click(LOC_BUTTON_UP);

        waitFor(1000);

        after[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 4));
        after[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        after[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 6));

        assertEquals(after[0], before[1], "The sixth line should be now fifth.");
        assertEquals(after[1], before[2], "The seventh line should be now sixth.");
        assertEquals(after[2], before[0], "The fifth line should be now seventh.");
    }

    /**
     * Tests the button up for multiple songs selected using Ctrl key.
     */
    @Test
    public void testUpMultipleSongsCtrl() {
        String[] before = new String[3];
        String[] after = new String[3];

        before[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 4));
        before[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        before[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 6));

        // click line nr. 6 and 7 in the table
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        selenium.controlKeyDown();
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 6));
        selenium.controlKeyUp();

        // click the 'Up' button (5,6,7 -> 6,7,5)
        selenium.click(LOC_BUTTON_UP);

        waitFor(1000);

        after[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 4));
        after[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        after[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 6));

        assertEquals(after[0], before[1], "The sixth line should be now fifth.");
        assertEquals(after[1], before[2], "The seventh line should be now sixth.");
        assertEquals(after[2], before[0], "The fifth line should be now seventh.");
    }

    /**
     * Tests the button down for a single song.
     */
    @Test
    public void testDownSingleSong() {
        String[] before = new String[2];
        String[] after = new String[2];

        before[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        before[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 6));

        // click line nr. 6 in the table
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        // click the 'Down' button
        selenium.click(LOC_BUTTON_DOWN);

        waitFor(1000);

        after[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        after[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 6));

        assertEquals(after[0], before[1], "The sixth line should be now seventh.");
        assertEquals(after[1], before[0], "The seventh line should be now sixth.");
    }

    /**
     * Tests the button down for multiple songs selected using Shift key.
     */
    @Test
    public void testDownMultipleSongsShift() {
        String[] before = new String[3];
        String[] after = new String[3];

        before[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 4));
        before[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        before[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 6));

        // click line nr. 5 and 6 in the table
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 4));
        selenium.shiftKeyDown();
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        selenium.shiftKeyUp();

        // click the 'Down' button (5,6,7 -> 7,5,6)
        selenium.click(LOC_BUTTON_DOWN);

        waitFor(1000);

        after[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 4));
        after[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        after[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 6));

        assertEquals(after[0], before[2], "The seventh line should be now fifth.");
        assertEquals(after[1], before[0], "The fifth line should be now sixth.");
        assertEquals(after[2], before[1], "The sixth line should be now seventh.");
    }

    /**
     * Tests the button down for multiple songs selected using Ctrl key.
     */
    @Test
    public void testDownMultipleSongsCtrl() {
        String[] before = new String[3];
        String[] after = new String[3];

        before[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 4));
        before[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        before[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 6));

        // click line nr. 5 and 6 in the table
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 4));
        selenium.controlKeyDown();
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        selenium.controlKeyUp();

        // click the 'Down' button (5,6,7 -> 7,5,6)
        selenium.click(LOC_BUTTON_DOWN);

        waitFor(1000);

        after[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 4));
        after[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 5));
        after[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 6));

        assertEquals(after[0], before[2], "The seventh line should be now sixth.");
        assertEquals(after[1], before[0], "The fifth line should be now seventh.");
        assertEquals(after[2], before[1], "The sixth line should be now fifth.");
    }

    /**
     * Tests the button last for a single song.
     */
    @Test
    public void testLastSingleSong() {
        String[] before = new String[3];
        String[] after = new String[3];
        int countOfLines = getJQueryCount(LOC_TABLE_LINES);

        before[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        before[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 1));
        before[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));

        // click line nr. 1 in the table
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        // click the 'Last' button (1,2,..,last-1,last --> 2,..,last,1)
        selenium.click(LOC_BUTTON_LAST);

        waitFor(1000);

        after[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        after[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-2));
        after[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));

        assertEquals(after[0], before[1], "The second line should be now first.");
        assertEquals(after[1], before[2], "The last line should be now last but one.");
        assertEquals(after[2], before[0], "The first line should be now last.");
    }

    /**
     * Tests the button last for multiple songs selected using Shift key.
     */
    @Test
    public void testLastMultipleSongsShift() {
		String[] before = new String[4];
        String[] after = new String[4];
        int countOfLines = getJQueryCount(LOC_TABLE_LINES);

        before[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        before[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 1));
        before[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 2));
        before[3] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));

        // click line nr. 1 and 2 in the table
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        selenium.shiftKeyDown();
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 1));
        selenium.shiftKeyUp();

        // click the 'Last' button (1,2,3,..,last --> 3,..,last,1,2)
        selenium.click(LOC_BUTTON_LAST);

        waitFor(1000);

        after[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        after[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-3));
        after[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-2));
        after[3] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));

        assertEquals(after[0], before[2], "The third line should be now first.");
        assertEquals(after[1], before[3], "The last line should be now last but two.");
        assertEquals(after[2], before[0], "The first line should be now last but one.");
        assertEquals(after[3], before[1], "The second line should be now last.");
    }

    /**
     * Tests the button last for multiple songs selected using Ctrl key.
     */
    @Test
    public void testLastMultipleSongsCtrl() {
        String[] before = new String[4];
        String[] after = new String[4];
        int countOfLines = getJQueryCount(LOC_TABLE_LINES);

        before[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        before[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 1));
        before[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 2));
        before[3] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));

        // click line nr. 1 and 2 in the table
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        selenium.controlKeyDown();
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, 1));
        selenium.controlKeyUp();

        // click the 'Last' button (1,2,3,..,last --> 3,..,last,1,2)
        selenium.click(LOC_BUTTON_LAST);

        waitFor(1000);

        after[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        after[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-3));
        after[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-2));
        after[3] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));

        assertEquals(after[0], before[2], "The third line should be now first.");
        assertEquals(after[1], before[3], "The last line should be now last but two.");
        assertEquals(after[2], before[0], "The first line should be now last but one.");
        assertEquals(after[3], before[1], "The second line should be now last.");
    }

    /**
     * Tests the button first for a single song.
     */
    @Test
    public void testFirstSingleSong() {
        String[] before = new String[3];
        String[] after = new String[3];
        int countOfLines = getJQueryCount(LOC_TABLE_LINES);

        before[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        before[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-2));
        before[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));

        // click the last line in the table
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));
        // click the 'First' button (1...last-1,last --> last,1...last-1)
        selenium.click(LOC_BUTTON_FIRST);

        waitFor(1000);

        after[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        after[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 1));
        after[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));

        assertEquals(after[0], before[2], "The second line should be now first.");
        assertEquals(after[1], before[0], "The first line should be now second.");
        assertEquals(after[2], before[1], "The last but one line should be now last.");
    }

    /**
     * Tests the button first for multiple songs selected using Shift key.
     */
    @Test
    public void testFirstMultipleSongsShift() {
        String[] before = new String[4];
        String[] after = new String[4];
        int countOfLines = getJQueryCount(LOC_TABLE_LINES);

        before[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        before[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-3));
        before[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-2));
        before[3] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));

        // click last 2 lines in the table
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-2));
        selenium.shiftKeyDown();
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));
        selenium.shiftKeyUp();

        // click the 'First' button (1...last-2,last-1,last -->
        // last-1,last,1...last-2)
        selenium.click(LOC_BUTTON_FIRST);

        waitFor(1000);

        after[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        after[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 1));
        after[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 2));
        after[3] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));

        assertEquals(after[0], before[2], "The last but one line should be now first.");
        assertEquals(after[1], before[3], "The last line should be now second.");
        assertEquals(after[2], before[0], "The first line should be now third.");
        assertEquals(after[3], before[1], "The last but two line should be now last.");
    }

    /**
     * Tests the button first for multiple songs selected using Ctrl key.
     */
    @Test
    public void testFirstMultipleSongsCtrl() {
        String[] before = new String[4];
        String[] after = new String[4];
        int countOfLines = getJQueryCount(LOC_TABLE_LINES);

        before[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        before[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-3));
        before[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-2));
        before[3] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));

        // click last 2 lines in the table
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-2));
        selenium.controlKeyDown();
        selenium.click(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));
        selenium.controlKeyUp();

        // click the 'First' button (1...last-2,last-1,last -->
        // last-1,last,1...last-2)
        selenium.click(LOC_BUTTON_FIRST);

        waitFor(1000);

        after[0] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 0));
        after[1] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 1));
        after[2] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, 2));
        after[3] = selenium.getText(format(LOC_TABLE_LINE_PREFORMATTED, countOfLines-1));

        assertEquals(after[0], before[2], "The last but one line should be now first.");
        assertEquals(after[1], before[3], "The last line should be now second.");
        assertEquals(after[2], before[0], "The first line should be now third.");
        assertEquals(after[3], before[1], "The last but two line should be now last.");
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks 5 lines of source code.
     */
    @Test
    public void testExampleSource() {
        String[] strings = new String[] {
                "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<h:panelGrid columns=\"2\" columnClasses=\"top 70per, top 30per\" width=\"100%\">",
                "<rich:orderingList value=\"#{library.songsList}\" var=\"lib\" listHeight=\"300\" listWidth=\"350\" converter=\"orderingListConverter\" selection=\"#{library.selectedSongsSet}\">",
                " <rich:column  width=\"180\">",
                "<f:facet name=\"header\">",
                "<h:outputText value=\"#{lib.title}\"></h:outputText>",
                " <a4j:support event=\"onclick\" ignoreDupResponses=\"true\" requestDelay=\"500\" action=\"#{library.takeSelection}\" reRender=\"output\"/>",
                "<a4j:support event=\"onkeyup\" ignoreDupResponses=\"true\" requestDelay=\"500\" action=\"#{library.takeSelection}\" reRender=\"output\"/>",
                "<rich:panel id=\"output\" header=\"Current Selection\" style=\"width:200px\">",
                "<rich:dataList value=\"#{library.selectedSongsList}\" var=\"song\" rendered=\"#{not empty library.selectedSongsList}\">",
                "<h:outputText value=\"#{song.title}\"></h:outputText>",
                "<h:outputText value=\"No Songs Selected\" rendered=\"#{empty library.selectedSongsList}\"/>", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Loads the page containing needed component.
     */
    protected void loadPage() {
        openComponent("Ordering List");
        scrollIntoView(LOC_EXAMPLE_HEADER, true);
    }
}
