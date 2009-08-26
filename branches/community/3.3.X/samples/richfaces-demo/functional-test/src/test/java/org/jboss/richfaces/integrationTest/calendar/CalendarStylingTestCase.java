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

    // messages
    private final String MSG_COMPONENT_DESCRIPTION_3 = getMsg("COMPONENT_DESCRIPTION_3");
    private final String MSG_EVERY_THIRD_CELL = getMsg("EVERY_THIRD_CELL");

    // locators
    private final String LOC_STYLING_FIELDSET = getLoc("STYLING_FIELDSET");
    private final String LOC_STYLING_CELL_X_Y = getLoc("STYLING_CELL_X_Y");
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

        for (int i = 3; i < 9; i++) {
            for (int j = 2; j < 9; j++) {
                text = selenium.getText(String.format(LOC_STYLING_CELL_X_Y, i, j));
                date = Integer.parseInt(text);
                if (date % 3 == 0) {
                    text = selenium.getAttribute(String.format(LOC_STYLING_CELL_X_Y, i, j) + "@class");
                    assertTrue(text.contains("everyThirdDay") || text.contains("rich-calendar-boundary-dates"),
                            MSG_EVERY_THIRD_CELL);
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
    @BeforeMethod
    private void loadPage() {
        super.loadPage("richInputs", 1, 3, MSG_COMPONENT_DESCRIPTION_3);
        scrollIntoView(LOC_STYLING_FIELDSET, true);
    }
}
