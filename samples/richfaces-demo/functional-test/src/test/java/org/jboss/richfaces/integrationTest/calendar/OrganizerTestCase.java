/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and others contributors as indicated
 * by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */

package org.jboss.richfaces.integrationTest.calendar;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Calendar;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests the calendar used as an organizer.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class OrganizerTestCase extends AbstractSeleniumRichfacesTestCase {

    // messages
    private final String MSG_COMPONENT_DESCRIPTION = getMsg("COMPONENT_DESCRIPTION");
    private final String MSG_CLASS_ATTRIBUTE_TODAY = getMsg("CLASS_ATTRIBUTE_TODAY");
    private final String MSG_CLASS_ATTRIBUTE_GREY = getMsg("CLASS_ATTRIBUTE_GREY");
    private final String MSG_DIALOG_VISIBLE = getMsg("DIALOG_VISIBLE");
    private final String MSG_DIALOG_NOT_VISIBLE = getMsg("DIALOG_NOT_VISIBLE");
    private final String MSG_CELL_X_Y_NOTE = getMsg("CELL_X_Y_NOTE");
    private final String MSG_CELL_X_Y_DESC = getMsg("CELL_X_Y_DESC");

    // locators
    private final String LOC_FIELDSET = getLoc("FIELDSET");

    private final String LOC_CELL_X_Y = getLoc("CELL_X_Y");
    private final String LOC_CELL_X_Y_DATE = getLoc("CELL_X_Y_DATE");
    private final String LOC_CELL_X_Y_DESC = getLoc("CELL_X_Y_DESC");
    private final String LOC_CELL_X_Y_NOTE = getLoc("CELL_X_Y_NOTE");

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
        
        int fromLine = 3;
        if (Integer.parseInt(today) > 15) {
            fromLine = 5;
        }
        
        for (int i = fromLine; i < 9; i++) {
            for (int j = 1; j < 8; j++) {
                text = selenium.getText(String.format(LOC_CELL_X_Y_DATE, i, j));
                if (today.equals(text)) {
                    text = selenium.getAttribute(String.format(LOC_CELL_X_Y, i, j) + "@class");
                    assertTrue(text.contains("rich-calendar-today"), MSG_CLASS_ATTRIBUTE_TODAY);
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
        String attr = selenium.getAttribute(String.format(LOC_CELL_X_Y, 8, 7) + "@class");
        assertTrue(attr.contains("rich-calendar-boundary-dates"), MSG_CLASS_ATTRIBUTE_GREY);
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
        String text = selenium.getText(String.format(LOC_CELL_X_Y_DESC, 4, 3));
        assertEquals(text, "Nothing planned", String.format(MSG_CELL_X_Y_DESC, 4, 3));

        assertFalse(isDisplayed(LOC_DIALOG), MSG_DIALOG_NOT_VISIBLE);

        selenium.click(String.format(LOC_CELL_X_Y, 4, 3));

        // wait for JavaScript to show the dialog
        Wait.until(new Condition() {
            public boolean isTrue() {
                return isDisplayed(LOC_DIALOG);
            }
        });

        assertTrue(isDisplayed(LOC_DIALOG), MSG_DIALOG_VISIBLE);

        selenium.type(LOC_DIALOG_DESCRIPTION, "some description");
        selenium.type(LOC_DIALOG_NOTE, "note note note note note");
        selenium.click(LOC_DIALOG_STORE_BUTTON);

        // wait for JavaScript to change the organizer
        Wait.until(new Condition() {

            public boolean isTrue() {
                return !"Nothing planned".equals(selenium.getText(String.format(LOC_CELL_X_Y_DESC, 4, 3)));
            }

        });

        text = selenium.getText(String.format(LOC_CELL_X_Y_DESC, 4, 3));
        assertEquals(text, "some description", String.format(MSG_CELL_X_Y_DESC, 2, 3));

        text = selenium.getText(String.format(LOC_CELL_X_Y_NOTE, 4, 3));
        assertEquals(text, "note note note note note", String.format(MSG_CELL_X_Y_NOTE, 2, 3));
    }

    /**
     * Tests the cancel button. First it checks the content of the cell in the
     * 3rd week, 3rd day. Then it clicks into the cell, enters the description
     * and note, and clicks the cancel button. In the end it verifies that the
     * organizer was not changed.
     */
    @Test
    public void testCancelNoteCancelButton() {
        String text = selenium.getText(String.format(LOC_CELL_X_Y_DESC, 5, 3));
        assertEquals(text, "Nothing planned", String.format(MSG_CELL_X_Y_DESC, 3, 3));

        assertFalse(isDisplayed(LOC_DIALOG), MSG_DIALOG_NOT_VISIBLE);

        selenium.click(String.format(LOC_CELL_X_Y, 5, 3));

        // wait for JavaScript to show the dialog
        Wait.until(new Condition() {
            public boolean isTrue() {
                return isDisplayed(LOC_DIALOG);
            }
        });

        assertTrue(isDisplayed(LOC_DIALOG), MSG_DIALOG_VISIBLE);

        selenium.type(LOC_DIALOG_DESCRIPTION, "some description");
        selenium.type(LOC_DIALOG_NOTE, "note note note note note");
        selenium.click(LOC_DIALOG_CANCEL_BUTTON);

        // wait for JavaScript to finish - nothing should change
        waitFor(3000);

        text = selenium.getText(String.format(LOC_CELL_X_Y_DESC, 5, 3));
        assertEquals(text, "Nothing planned", String.format(MSG_CELL_X_Y_DESC, 3, 3));

        text = selenium.getText(String.format(LOC_CELL_X_Y_NOTE, 5, 3));
        assertEquals(text, "", String.format(MSG_CELL_X_Y_NOTE, 3, 3));
    }

    /**
     * Tests the cross button in the corner of the dialog. First it checks the
     * content of the cell in the 3rd week, 3rd day. Then it clicks into the
     * cell, enters the description and note, and clicks the cancel button. In
     * the end it verifies that the organizer was not changed.
     */
    @Test
    public void testCancelNoteCrossButton() {
        String text = selenium.getText(String.format(LOC_CELL_X_Y_DESC, 5, 5));
        assertEquals(text, "Nothing planned", String.format(MSG_CELL_X_Y_DESC, 3, 5));

        assertFalse(isDisplayed(LOC_DIALOG), MSG_DIALOG_NOT_VISIBLE);

        selenium.click(String.format(LOC_CELL_X_Y, 5, 5));

        // wait for JavaScript to show the dialog
        Wait.until(new Condition() {
            public boolean isTrue() {
                return isDisplayed(LOC_DIALOG);
            }
        });

        assertTrue(isDisplayed(LOC_DIALOG), MSG_DIALOG_VISIBLE);

        selenium.type(LOC_DIALOG_DESCRIPTION, "some description");
        selenium.type(LOC_DIALOG_NOTE, "note note note note note");
        selenium.click(LOC_DIALOG_CROSS_BUTTON);

        // wait for JavaScript to finish - nothing should change
        waitFor(3000);

        text = selenium.getText(String.format(LOC_CELL_X_Y_DESC, 5, 5));
        assertEquals(text, "Nothing planned", String.format(MSG_CELL_X_Y_DESC, 3, 5));

        text = selenium.getText(String.format(LOC_CELL_X_Y_NOTE, 5, 5));
        assertEquals(text, "", String.format(MSG_CELL_X_Y_NOTE, 3, 5));
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
                "<a4j:outputPanel layout=\"block\" id=\"cell\" onclick=\"#{rich:component('organizer')}.resetSelectedDate()\" style=\"height: 100%;\">",
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
    @BeforeMethod
    private void loadPage() {
        openComponent("Calendar");
        openTab("Organizer Creation");
        scrollIntoView(LOC_FIELDSET, true);
    }
}
