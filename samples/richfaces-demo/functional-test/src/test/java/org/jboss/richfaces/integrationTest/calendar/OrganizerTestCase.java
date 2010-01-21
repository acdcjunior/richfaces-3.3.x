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

package org.jboss.richfaces.integrationTest.calendar;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Calendar;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * Test case that tests the calendar used as an organizer.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class OrganizerTestCase extends AbstractSeleniumRichfacesTestCase {

    // locators
    private final String LOC_FIELDSET = getLoc("FIELDSET");

    private final String LOC_CELL_PREFORMATTED = getLoc("CELL_PREFORMATTED");
    private final String LOC_CELL_DATE_PREFORMATTED = getLoc("CELL_DATE_PREFORMATTED");
    private final String LOC_CELL_DESC_PREFORMATTED = getLoc("CELL_DESC_PREFORMATTED");
    private final String LOC_CELL_NOTE_PREFORMATTED = getLoc("CELL_NOTE_PREFORMATTED");

    private final String LOC_DIALOG = getLoc("DIALOG");
    private final String LOC_DIALOG_DESCRIPTION = getLoc("DIALOG_DESCRIPTION");
    private final String LOC_DIALOG_NOTE = getLoc("DIALOG_NOTE");
    private final String LOC_DIALOG_STORE_BUTTON = getLoc("DIALOG_STORE_BUTTON");
    private final String LOC_DIALOG_CANCEL_BUTTON = getLoc("DIALOG_CANCEL_BUTTON");
    private final String LOC_DIALOG_CROSS_BUTTON = getLoc("DIALOG_CROSS_BUTTON");

    /**
     * Tests that today's cell is highlighted. It goes through the table until
     * it finds today's date.
     */
    @Test
    public void testTodayIsHighlighted() {
        String today = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        String text = null;

        int fromLine = 1;
        if (Integer.parseInt(today) > 15) {
            fromLine = 3;
        }

        for (int i = fromLine; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                text = selenium.getText(format(LOC_CELL_DATE_PREFORMATTED, i, j));
                if (today.equals(text)) {
                    assertTrue(belongsClass("rich-calendar-today", format(LOC_CELL_PREFORMATTED, i, j)),
                            "Class attribute of the cell with today's date should contain \"rich-calendar-today\".");
                    return;
                }
            }
        }
    }

    /**
     * Tests the last cell of the table. The last line is usually empty and the
     * last cell of the last row has to be disabled.
     */
    @Test
    public void testLastDayIsGrey() {
        assertTrue(belongsClass("rich-calendar-boundary-dates", format(LOC_CELL_PREFORMATTED, 6, 6)),
                "Class attribute of the last cell should contain \"rich-calendar-boundary-dates\".");
    }

    /**
     * Tests adding a new note to the organizer. First it checks the text of the
     * selected cell (2nd week, 3rd day), checks that the dialog is hidden,
     * clicks into the cell, and checks that the dialog is shown. Then it enters
     * values into description and note fields and clicks the store button. In
     * the end it checks that the organizer was changed.
     */
    @Test
    public void testSaveNote() {
        String text = selenium.getText(format(LOC_CELL_DESC_PREFORMATTED, 4, 3));
        assertEquals(text, "Nothing planned", "The description in the cell (week 2, day 3).");

        assertFalse(isDisplayed(LOC_DIALOG), "Dialog should not be visible.");

        selenium.click(format(LOC_CELL_PREFORMATTED, 4, 3));

        // wait for JavaScript to show the dialog
        Wait.until(new Condition() {
            public boolean isTrue() {
                return isDisplayed(LOC_DIALOG);
            }
        });

        assertTrue(isDisplayed(LOC_DIALOG), "Dialog should be visible.");

        selenium.type(LOC_DIALOG_DESCRIPTION, "some description");
        selenium.type(LOC_DIALOG_NOTE, "note note note note note");
        selenium.click(LOC_DIALOG_STORE_BUTTON);

        // wait for JavaScript to change the organizer
        Wait.until(new Condition() {
            public boolean isTrue() {
                return !"Nothing planned".equals(selenium.getText(format(LOC_CELL_DESC_PREFORMATTED, 4, 3)));
            }
        });

        text = selenium.getText(format(LOC_CELL_DESC_PREFORMATTED, 4, 3));
        assertEquals(text, "some description", "The description in the cell (week 2, day 3).");

        text = selenium.getText(format(LOC_CELL_NOTE_PREFORMATTED, 4, 3));
        assertEquals(text, "note note note note note", "The note in the cell (week 2, day 3).");
    }

    /**
     * Tests the cancel button. First it checks the content of the cell in the
     * 3rd week, 3rd day. Then it clicks into the cell, enters the description
     * and note, and clicks the cancel button. In the end it verifies that the
     * organizer was not changed.
     */
    @Test
    public void testCancelNoteCancelButton() {
        String text = selenium.getText(format(LOC_CELL_DESC_PREFORMATTED, 3, 2));
        assertEquals(text, "Nothing planned", "The description in the cell (week 3, day 3).");

        assertFalse(isDisplayed(LOC_DIALOG), "Dialog should not be visible.");

        selenium.click(format(LOC_CELL_PREFORMATTED, 3, 2));

        // wait for JavaScript to show the dialog
        Wait.until(new Condition() {
            public boolean isTrue() {
                return isDisplayed(LOC_DIALOG);
            }
        });

        assertTrue(isDisplayed(LOC_DIALOG), "Dialog should be visible.");

        selenium.type(LOC_DIALOG_DESCRIPTION, "some description");
        selenium.type(LOC_DIALOG_NOTE, "note note note note note");
        selenium.click(LOC_DIALOG_CANCEL_BUTTON);

        // wait for JavaScript to finish - nothing should change
        waitFor(3000);

        text = selenium.getText(format(LOC_CELL_DESC_PREFORMATTED, 3, 2));
        assertEquals(text, "Nothing planned", "The description in the cell (week 3, day 3).");

        text = selenium.getText(format(LOC_CELL_NOTE_PREFORMATTED, 3, 2));
        assertEquals(text, "", "The note in the cell (week 3, day 3).");
    }

    /**
     * Tests the cross button in the corner of the dialog. First it checks the
     * content of the cell in the 3rd week, 3rd day. Then it clicks into the
     * cell, enters the description and note, and clicks the cancel button. In
     * the end it verifies that the organizer was not changed.
     */
    @Test
    public void testCancelNoteCrossButton() {
        String text = selenium.getText(format(LOC_CELL_DESC_PREFORMATTED, 3, 4));
        assertEquals(text, "Nothing planned", "The description in the cell (week 3, day 5).");

        assertFalse(isDisplayed(LOC_DIALOG), "Dialog should be visible.");

        selenium.click(format(LOC_CELL_PREFORMATTED, 3, 4));

        // wait for JavaScript to show the dialog
        Wait.until(new Condition() {
            public boolean isTrue() {
                return isDisplayed(LOC_DIALOG);
            }
        });

        assertTrue(isDisplayed(LOC_DIALOG), "Dialog should be visible.");

        selenium.type(LOC_DIALOG_DESCRIPTION, "some description");
        selenium.type(LOC_DIALOG_NOTE, "note note note note note");
        selenium.click(LOC_DIALOG_CROSS_BUTTON);

        // wait for JavaScript to finish - nothing should change
        waitFor(3000);

        text = selenium.getText(format(LOC_CELL_DESC_PREFORMATTED, 3, 4));
        assertEquals(text, "Nothing planned", "The description in the cell (week 3, day 5).");

        text = selenium.getText(format(LOC_CELL_NOTE_PREFORMATTED, 3, 4));
        assertEquals(text, "", "The note in the cell (week 3, day 5).");
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks 8 lines of source code.
     */
    @Test
    public void testPageSource() {
        String[] strings = new String[] {
                "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<rich:messages/>",
                "<a4j:jsFunction name=\"ajaxSubmit\" oncomplete=\"#{rich:component('panel')}.show()\" reRender=\"editContent\" />",
                "<rich:calendar value=\"#{calendarBean.selectedDate}\"",
                "cellWidth=\"100px\" cellHeight=\"100px\"",
                "dataModel=\"#{calendarDataModel}\" onchanged=\"if (event.rich.date) {ajaxSubmit();}\" oncurrentdateselect=\"return false\"",
                "<a4j:outputPanel layout=\"block\" id=\"cell\" onclick=\"#{rich:component('organizer')}.resetSelectedDate()\"",
                "<rich:modalPanel id=\"panel\" resizeable=\"false\">", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Tests the source of CalendarDataModelImpl.java. It checks that the source
     * code is not visible, clicks on the link, and checks 8 lines of source
     * code.
     */
    @Test
    public void testDataModelSource() {
        String[] strings = new String[] {
                "package org.richfaces.demo.calendar.modelImpl;",
                "import org.richfaces.model.CalendarDataModelItem;",
                "private CalendarDataModelItem[] items;",
                "public CalendarDataModelItem[] getData(Date[] dateArray) {",
                "protected CalendarDataModelItem createDataModelItem(Date date) {",
                "item.setDay(c.get(Calendar.DAY_OF_MONTH));",
                "setCurrentDescription((String)((HashMap)items[calendar.get(Calendar.DAY_OF_MONTH)-1].getData()).get(\"description\"));",
                "public String getCurrentShortDescription() {", };

        abstractTestSource(1, "CalendarDataModelImpl.java", strings);
    }

    /**
     * Tests the source of CalendarDataModelItemImpl.java. It checks that the
     * source code is not visible, clicks on the link, and checks 8 lines of
     * source code.
     */
    @Test
    public void testDataModelItemSource() {
        String[] strings = new String[] { "package org.richfaces.demo.calendar.modelImpl;",
                "public class CalendarDataModelItemImpl implements CalendarDataModelItem {", "private Object data;",
                "private boolean enabled = true;", "public Object getData() {",
                "public void setStyleClass(String styleClass) {", "this.styleClass = styleClass;",
                "public void setToolTip(Object toolTip) {", };

        abstractTestSource(1, "CalendarDataModelItemImpl.java", strings);
    }

    /**
     * Loads the page containing the calendar component.
     */
    protected void loadPage() {
        openComponent("Calendar");
        openTab("Organizer Creation");
        scrollIntoView(LOC_FIELDSET, true);
    }
}
