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

import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests styling in calendar.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class CalendarStylingTestCase extends AbstractSeleniumRichfacesTestCase {

    // locators
    private final String LOC_STYLING_FIELDSET = getLoc("STYLING_FIELDSET");
    private final String LOC_STYLING_CELL_PREFORMATTED = getLoc("STYLING_CELL_PREFORMATTED");
    private final String LOC_STYLING_CALENDAR_BUTTON = getLoc("STYLING_CALENDAR_BUTTON");
    private final String LOC_STYLING_RIGHT_ARROW = getLoc("STYLING_RIGHT_ARROW");

    /**
     * Tests the styling functionality of the calendar. It goes through all days
     * of a month and checks that each day that is a multiple of 3 is either
     * disabled (boundary dates) or is styled (days from selected month).
     */
    @Test
    public void testStyling() {
        selenium.click(LOC_STYLING_CALENDAR_BUTTON);
        selenium.click(LOC_STYLING_RIGHT_ARROW);
        selenium.click(LOC_STYLING_RIGHT_ARROW);

        String text = null;
        int date = 0;

        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 8; j++) {
                text = selenium.getText(format(LOC_STYLING_CELL_PREFORMATTED, i, j));
                date = Integer.parseInt(text);
                if (date % 3 == 0) {
                    text = selenium.getAttribute(format(LOC_STYLING_CELL_PREFORMATTED, i, j) + "@class");
                    assertTrue(text.contains("everyThirdDay") || text.contains("rich-calendar-boundary-dates"),
                            "Every third cell should be either disabled (boundary dates) or styled (dates from current month).");
                }
            }
        }
    }

    /**
     * Tests the "View Source" in the first example. It checks that the source
     * code is not visible, clicks on the link, and checks 8 lines of source
     * code.
     */
    @Test
    public void testPageSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"", ".everyThirdDay{",
                "background-color:gray;", "<script type=\"text/javascript\">", "function disablementFunction(day){",
                "if (day.isWeekend) return false;", "function disabledClassesProv(day){",
                "if (day.day%3==0) res+='everyThirdDay';", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Loads the page containing the calendar component.
     */
    protected void loadPage() {
        openComponent("Calendar");
        openTab("Client Side Disable/Styling");
        scrollIntoView(LOC_STYLING_FIELDSET, true);
    }
}
