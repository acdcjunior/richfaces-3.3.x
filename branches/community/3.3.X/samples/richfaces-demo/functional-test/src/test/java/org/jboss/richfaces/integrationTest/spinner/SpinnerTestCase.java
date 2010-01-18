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

package org.jboss.richfaces.integrationTest.spinner;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests the spinner component.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class SpinnerTestCase extends AbstractSeleniumRichfacesTestCase {

    // locators
    private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");
    private final String LOC_UPPER_SPINNER_INPUT = getLoc("UPPER_SPINNER_INPUT");
    private final String LOC_UPPER_SPINNER_UP = getLoc("UPPER_SPINNER_UP");
    private final String LOC_UPPER_SPINNER_DOWN = getLoc("UPPER_SPINNER_DOWN");
    private final String LOC_LOWER_SPINNER_INPUT = getLoc("LOWER_SPINNER_INPUT");
    private final String LOC_LOWER_SPINNER_UP = getLoc("LOWER_SPINNER_UP");
    private final String LOC_LOWER_SPINNER_DOWN = getLoc("LOWER_SPINNER_DOWN");

    // messages
    private final int MSG_INITIAL_STATE_UPPER = Integer.parseInt(getMsg("INITIAL_STATE_UPPER"));
    private final int MSG_INITIAL_STATE_LOWER = Integer.parseInt(getMsg("INITIAL_STATE_LOWER"));

    private final String MSG_TYPE_TO_UPPER_FIRST_TYPED = getMsg("TYPE_TO_UPPER_FIRST_TYPED");
    private final int MSG_TYPE_TO_UPPER_FIRST_EXPECTED = Integer.parseInt(getMsg("TYPE_TO_UPPER_FIRST_EXPECTED"));
    private final String MSG_TYPE_TO_UPPER_SECOND_TYPED = getMsg("TYPE_TO_UPPER_SECOND_TYPED");
    private final int MSG_TYPE_TO_UPPER_SECOND_EXPECTED = Integer.parseInt(getMsg("TYPE_TO_UPPER_SECOND_EXPECTED"));
    private final String MSG_TYPE_TO_UPPER_THIRD_TYPED = getMsg("TYPE_TO_UPPER_THIRD_TYPED");
    private final int MSG_TYPE_TO_UPPER_THIRD_EXPECTED = Integer.parseInt(getMsg("TYPE_TO_UPPER_THIRD_EXPECTED"));
    private final String MSG_TYPE_TO_UPPER_FOURTH_TYPED = getMsg("TYPE_TO_UPPER_FOURTH_TYPED");
    private final double MSG_TYPE_TO_UPPER_FOURTH_EXPECTED = Double
            .parseDouble(getMsg("TYPE_TO_UPPER_FOURTH_EXPECTED"));
    private final String MSG_TYPE_TO_UPPER_FIFTH_TYPED = getMsg("TYPE_TO_UPPER_FIFTH_TYPED");
    private final double MSG_TYPE_TO_UPPER_FIFTH_EXPECTED = Double.parseDouble(getMsg("TYPE_TO_UPPER_FIFTH_EXPECTED"));

    private final String MSG_TYPE_TO_LOWER_FIRST_TYPED = getMsg("TYPE_TO_LOWER_FIRST_TYPED");
    private final int MSG_TYPE_TO_LOWER_FIRST_EXPECTED = Integer.parseInt(getMsg("TYPE_TO_LOWER_FIRST_EXPECTED"));
    private final String MSG_TYPE_TO_LOWER_SECOND_TYPED = getMsg("TYPE_TO_LOWER_SECOND_TYPED");
    private final int MSG_TYPE_TO_LOWER_SECOND_EXPECTED = Integer.parseInt(getMsg("TYPE_TO_LOWER_SECOND_EXPECTED"));
    private final String MSG_TYPE_TO_LOWER_THIRD_TYPED = getMsg("TYPE_TO_LOWER_THIRD_TYPED");
    private final int MSG_TYPE_TO_LOWER_THIRD_EXPECTED = Integer.parseInt(getMsg("TYPE_TO_LOWER_THIRD_EXPECTED"));
    private final String MSG_TYPE_TO_LOWER_FOURTH_TYPED = getMsg("TYPE_TO_LOWER_FOURTH_TYPED");
    private final double MSG_TYPE_TO_LOWER_FOURTH_EXPECTED = Double
            .parseDouble(getMsg("TYPE_TO_LOWER_FOURTH_EXPECTED"));
    private final String MSG_TYPE_TO_LOWER_FIFTH_TYPED = getMsg("TYPE_TO_LOWER_FIFTH_TYPED");
    private final double MSG_TYPE_TO_LOWER_FIFTH_EXPECTED = Double.parseDouble(getMsg("TYPE_TO_LOWER_FIFTH_EXPECTED"));

    private final int MSG_OVERFLOW_UPPER = Integer.parseInt(getMsg("OVERFLOW_UPPER"));
    private final int MSG_OVERFLOW_LOWER = Integer.parseInt(getMsg("OVERFLOW_LOWER"));
    private final int MSG_UNDERFLOW_UPPER = Integer.parseInt(getMsg("UNDERFLOW_UPPER"));
    private final int MSG_UNDERFLOW_LOWER = Integer.parseInt(getMsg("UNDERFLOW_LOWER"));

    /**
     * Tests the initial state of both spinners. Both should be set to 50.
     */
    @Test
    public void testInitialState() {
        assertTrue(selenium.isElementPresent(LOC_UPPER_SPINNER_INPUT), "Upper input field is not present.");
        assertTrue(selenium.isElementPresent(LOC_UPPER_SPINNER_UP), "Upper arrow up is not present.");
        assertTrue(selenium.isElementPresent(LOC_UPPER_SPINNER_DOWN), "Upper arrow down is not present.");
        assertTrue(selenium.isElementPresent(LOC_LOWER_SPINNER_INPUT), "Lower input field is not present.");
        assertTrue(selenium.isElementPresent(LOC_LOWER_SPINNER_UP), "Lower arrow up is not present.");
        assertTrue(selenium.isElementPresent(LOC_LOWER_SPINNER_DOWN), "Lower arrow down is not present.");

        int number = Integer.parseInt(selenium.getValue(LOC_UPPER_SPINNER_INPUT));
        assertEquals(number, MSG_INITIAL_STATE_UPPER, "Content of upper spinner.");

        number = Integer.parseInt(selenium.getValue(LOC_LOWER_SPINNER_INPUT));
        assertEquals(number, MSG_INITIAL_STATE_LOWER, "Content of lower spinner.");
    }

    /**
     * Tests clicking on the up arrow of the upper spinner.
     */
    @Test
    public void testUpperClickUp() {
        int oldValue = Integer.parseInt(selenium.getValue(LOC_UPPER_SPINNER_INPUT));

        selenium.clickAt(LOC_UPPER_SPINNER_UP, "0,0");

        int newValue = Integer.parseInt(selenium.getValue(LOC_UPPER_SPINNER_INPUT));
        assertEquals(newValue, oldValue + 1, "The value should increase by 1 after clicking on up arrow.");
    }

    /**
     * Tests clicking on the down arrow of the upper spinner.
     */
    @Test
    public void testUpperClickDown() {
        int oldValue = Integer.parseInt(selenium.getValue(LOC_UPPER_SPINNER_INPUT));

        selenium.clickAt(LOC_UPPER_SPINNER_DOWN, "0,0");

        int newValue = Integer.parseInt(selenium.getValue(LOC_UPPER_SPINNER_INPUT));
        assertEquals(newValue, oldValue - 1, "The value should decrease by 1 after clicking on down arrow.");

    }

    /**
     * Tests clicking on the up arrow of the lower spinner.
     */
    @Test
    public void testLowerClickUp() {
        int oldValue = Integer.parseInt(selenium.getValue(LOC_LOWER_SPINNER_INPUT));

        selenium.clickAt(LOC_LOWER_SPINNER_UP, "0,0");

        int newValue = Integer.parseInt(selenium.getValue(LOC_LOWER_SPINNER_INPUT));
        assertEquals(newValue, oldValue + 10, "The value should increase by 10 after clicking on up arrow.");
    }

    /**
     * Tests clicking on the down arrow of the lower spinner.
     */
    @Test
    public void testLowerClickDown() {
        int oldValue = Integer.parseInt(selenium.getValue(LOC_LOWER_SPINNER_INPUT));

        selenium.clickAt(LOC_LOWER_SPINNER_DOWN, "0,0");

        int newValue = Integer.parseInt(selenium.getValue(LOC_LOWER_SPINNER_INPUT));
        assertEquals(newValue, oldValue - 10, "The value should decrease by 10 after clicking on down arrow.");
    }

    /**
     * Tests typing into the upper spinner. In the beginning it tries valid
     * value (20), then a value bigger than maximum (2000), a value lower than
     * minimum (-23), a decimal value (34.5), and a string ("aaa").
     */
    @Test
    public void testTypeToUpper() {
        selenium.type(LOC_UPPER_SPINNER_INPUT, MSG_TYPE_TO_UPPER_FIRST_TYPED);
        Number newValue = Integer.parseInt(selenium.getValue(LOC_UPPER_SPINNER_INPUT));
        assertEquals(newValue, MSG_TYPE_TO_LOWER_FIRST_EXPECTED, format(
                "Input field should contain {0} after {1} was typed.", MSG_TYPE_TO_UPPER_FIRST_EXPECTED,
                MSG_TYPE_TO_UPPER_FIRST_TYPED));

        selenium.type(LOC_UPPER_SPINNER_INPUT, MSG_TYPE_TO_UPPER_SECOND_TYPED);
        newValue = Integer.parseInt(selenium.getValue(LOC_UPPER_SPINNER_INPUT));
        assertEquals(newValue, MSG_TYPE_TO_UPPER_SECOND_EXPECTED, format(
                "Input field should contain {0} after {1} was typed.", MSG_TYPE_TO_UPPER_SECOND_EXPECTED,
                MSG_TYPE_TO_UPPER_SECOND_TYPED));

        selenium.type(LOC_UPPER_SPINNER_INPUT, MSG_TYPE_TO_UPPER_THIRD_TYPED);
        newValue = Integer.parseInt(selenium.getValue(LOC_UPPER_SPINNER_INPUT));
        assertEquals(newValue, MSG_TYPE_TO_UPPER_THIRD_EXPECTED, format(
                "Input field should contain {0} after {1} was typed.", MSG_TYPE_TO_UPPER_THIRD_EXPECTED,
                MSG_TYPE_TO_UPPER_THIRD_TYPED));

        selenium.type(LOC_UPPER_SPINNER_INPUT, MSG_TYPE_TO_UPPER_FOURTH_TYPED);
        newValue = Double.parseDouble(selenium.getValue(LOC_UPPER_SPINNER_INPUT));
        assertEquals(newValue, MSG_TYPE_TO_UPPER_FOURTH_EXPECTED, format(
                "Input field should contain {0} after {1} was typed.", MSG_TYPE_TO_UPPER_FOURTH_EXPECTED,
                MSG_TYPE_TO_UPPER_FOURTH_TYPED));

        selenium.type(LOC_UPPER_SPINNER_INPUT, MSG_TYPE_TO_UPPER_FIFTH_TYPED);
        newValue = Double.parseDouble(selenium.getValue(LOC_UPPER_SPINNER_INPUT));
        assertEquals(newValue, MSG_TYPE_TO_UPPER_FIFTH_EXPECTED, format(
                "Input field should contain {0} after {1} was typed.", MSG_TYPE_TO_UPPER_FIFTH_EXPECTED,
                MSG_TYPE_TO_UPPER_FIFTH_TYPED));
    }

    /**
     * Tests typing into the lower spinner. In the beginning it tries valid
     * value (20), then a value bigger than maximum (2000), a value lower than
     * minimum (-23), a decimal value (34.5), and a string ("aaa").
     */
    @Test
    public void testTypeToLower() {
        selenium.type(LOC_LOWER_SPINNER_INPUT, MSG_TYPE_TO_LOWER_FIRST_TYPED);
        Number newValue = Integer.parseInt(selenium.getValue(LOC_LOWER_SPINNER_INPUT));
        assertEquals(newValue, MSG_TYPE_TO_LOWER_FIRST_EXPECTED, format(
                "Input field should contain {0} after {1} was typed.", MSG_TYPE_TO_LOWER_FIRST_EXPECTED,
                MSG_TYPE_TO_LOWER_FIRST_TYPED));

        selenium.type(LOC_LOWER_SPINNER_INPUT, MSG_TYPE_TO_LOWER_SECOND_TYPED);
        newValue = Integer.parseInt(selenium.getValue(LOC_LOWER_SPINNER_INPUT));
        assertEquals(newValue, MSG_TYPE_TO_LOWER_SECOND_EXPECTED, format(
                "Input field should contain {0} after {1} was typed.", MSG_TYPE_TO_LOWER_SECOND_EXPECTED,
                MSG_TYPE_TO_LOWER_SECOND_TYPED));

        selenium.type(LOC_LOWER_SPINNER_INPUT, MSG_TYPE_TO_LOWER_THIRD_TYPED);
        newValue = Integer.parseInt(selenium.getValue(LOC_LOWER_SPINNER_INPUT));
        assertEquals(newValue, MSG_TYPE_TO_LOWER_THIRD_EXPECTED, format(
                "Input field should contain {0} after {1} was typed.", MSG_TYPE_TO_LOWER_THIRD_EXPECTED,
                MSG_TYPE_TO_LOWER_THIRD_TYPED));

        selenium.type(LOC_LOWER_SPINNER_INPUT, MSG_TYPE_TO_LOWER_FOURTH_TYPED);
        newValue = Double.parseDouble(selenium.getValue(LOC_LOWER_SPINNER_INPUT));
        assertEquals(newValue, MSG_TYPE_TO_LOWER_FOURTH_EXPECTED, format(
                "Input field should contain {0} after {1} was typed.", MSG_TYPE_TO_LOWER_FOURTH_EXPECTED,
                MSG_TYPE_TO_LOWER_FOURTH_TYPED));

        selenium.type(LOC_LOWER_SPINNER_INPUT, MSG_TYPE_TO_LOWER_FIFTH_TYPED);
        newValue = Double.parseDouble(selenium.getValue(LOC_LOWER_SPINNER_INPUT));
        assertEquals(newValue, MSG_TYPE_TO_LOWER_FIFTH_EXPECTED, format(
                "Input field should contain {0} after {1} was typed.", MSG_TYPE_TO_LOWER_FIFTH_EXPECTED,
                MSG_TYPE_TO_LOWER_FIFTH_TYPED));
    }

    /**
     * Tests value overflow. It clicks 51 times on the up button of the upper
     * spinner and verifies that the value changed to 0. Then it clicks 6 times
     * on the up button of the lower spinner and checks that the value changed
     * to 0.
     */
    @Test
    public void testOverflow() {
        // it starts at 50, maximum is 100, increment 1, it will try to click 51
        // times
        for (int i = 0; i < 51; i++) {
            selenium.clickAt(LOC_UPPER_SPINNER_UP, "0,0");
        }

        int newValue = Integer.parseInt(selenium.getValue(LOC_UPPER_SPINNER_INPUT));
        assertEquals(newValue, MSG_OVERFLOW_UPPER, format("The value should change to {0} at overflow.",
                MSG_OVERFLOW_UPPER));

        // it starts at 50, maximum is 100, increment 10, it will try to click 6
        // times
        for (int i = 0; i < 6; i++) {
            selenium.clickAt(LOC_LOWER_SPINNER_UP, "0,0");
        }

        newValue = Integer.parseInt(selenium.getValue(LOC_LOWER_SPINNER_INPUT));
        assertEquals(newValue, MSG_OVERFLOW_LOWER, format("The value should change to {0} at overflow.",
                MSG_OVERFLOW_LOWER));
    }

    /**
     * Tests value underflow. It clicks 51 times on the down button of the lower
     * spinner and verifies that the value changed to 100. Then it clicks 6
     * times on the down button of the lower spinner and checks that the value
     * changed to 100.
     */
    @Test
    public void testUnderflow() {
        // it starts at 50, minimum is 0, increment 1, it will try to click 51
        // times
        for (int i = 0; i < 51; i++) {
            selenium.clickAt(LOC_UPPER_SPINNER_DOWN, "0,0");
        }

        int newValue = Integer.parseInt(selenium.getValue(LOC_UPPER_SPINNER_INPUT));
        assertEquals(newValue, MSG_UNDERFLOW_UPPER, format("The value should change to {0} at underflow.",
                MSG_UNDERFLOW_UPPER));

        // it starts at 50, minimum is 0, increment 10, it will try to click 6
        // times
        for (int i = 0; i < 6; i++) {
            selenium.clickAt(LOC_LOWER_SPINNER_DOWN, "0,0");
        }

        newValue = Integer.parseInt(selenium.getValue(LOC_LOWER_SPINNER_INPUT));
        assertEquals(newValue, MSG_UNDERFLOW_LOWER, format("The value should change to {0} at underflow.",
                MSG_UNDERFLOW_LOWER));
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks 5 lines of source code.
     */
    @Test
    public void testSpinnerSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<a4j:form ajaxSubmit=\"true\">", "Here is an example of default inputNumberSpinner:",
                "<rich:inputNumberSpinner value=\"50\"/>", "<rich:inputNumberSpinner value=\"50\" step=\"10\"/>", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Loads the page containing needed component.
     */
    protected void loadPage() {
        openComponent("Input Number Spinner");
        scrollIntoView(LOC_EXAMPLE_HEADER, true);
    }

}
