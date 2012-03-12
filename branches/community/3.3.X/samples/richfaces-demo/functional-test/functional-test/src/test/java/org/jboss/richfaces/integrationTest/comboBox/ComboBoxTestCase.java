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

package org.jboss.richfaces.integrationTest.comboBox;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEqualsNoOrder;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests the combo box component.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class ComboBoxTestCase extends AbstractSeleniumRichfacesTestCase {

    // locators
    private final String LOC_FIRST_COMBO_INPUT = getLoc("FIRST_COMBO_INPUT");
    private final String LOC_FIRST_COMBO_BUTTON = getLoc("FIRST_COMBO_BUTTON");
    private final String LOC_FIRST_COMBO_SUGGESTIONS = getLoc("FIRST_COMBO_SUGGESTIONS");

    private final String LOC_SECOND_COMBO_INPUT = getLoc("SECOND_COMBO_INPUT");
    private final String LOC_SECOND_COMBO_BUTTON = getLoc("SECOND_COMBO_BUTTON");
    private final String LOC_SECOND_COMBO_SUGGESTIONS = getLoc("SECOND_COMBO_SUGGESTIONS");

    private final String LOC_THIRD_COMBO_INPUT = getLoc("THIRD_COMBO_INPUT");
    private final String LOC_THIRD_COMBO_BUTTON = getLoc("THIRD_COMBO_BUTTON");
    private final String LOC_THIRD_COMBO_SUGGESTIONS = getLoc("THIRD_COMBO_SUGGESTIONS");

    // messages
    private final String MSG_INITIAL_STATE_INITIAL_VALUE_1 = getMsg("INITIAL_STATE_INITIAL_VALUE_1");
    private final String MSG_INITIAL_STATE_INITIAL_VALUE_2 = getMsg("INITIAL_STATE_INITIAL_VALUE_2");
    private final String MSG_INITIAL_STATE_INITIAL_VALUE_3 = getMsg("INITIAL_STATE_INITIAL_VALUE_3");
    
    private final int MSG_SUGGESTIONS_FIRST_COMBO_COUNT_1 = Integer.parseInt(getMsg("SUGGESTIONS_FIRST_COMBO_COUNT_1"));
    private final int MSG_SUGGESTIONS_FIRST_COMBO_COUNT_2 = Integer.parseInt(getMsg("SUGGESTIONS_FIRST_COMBO_COUNT_2"));
    private final String MSG_SUGGESTIONS_FIRST_COMBO_INPUT = getMsg("SUGGESTIONS_FIRST_COMBO_INPUT");
    
    private final int MSG_SUGGESTIONS_SECOND_COMBO_COUNT_1 = Integer.parseInt(getMsg("SUGGESTIONS_SECOND_COMBO_COUNT_1"));
    private final int MSG_SUGGESTIONS_SECOND_COMBO_COUNT_2 = Integer.parseInt(getMsg("SUGGESTIONS_SECOND_COMBO_COUNT_2"));
    private final String MSG_SUGGESTIONS_SECOND_COMBO_INPUT = getMsg("SUGGESTIONS_SECOND_COMBO_INPUT");
    
    private final int MSG_SUGGESTIONS_THIRD_COMBO_COUNT_1 = Integer.parseInt(getMsg("SUGGESTIONS_THIRD_COMBO_COUNT_1"));
    private final int MSG_SUGGESTIONS_THIRD_COMBO_COUNT_2 = Integer.parseInt(getMsg("SUGGESTIONS_THIRD_COMBO_COUNT_2"));
    private final String MSG_SUGGESTIONS_THIRD_COMBO_INPUT = getMsg("SUGGESTIONS_THIRD_COMBO_INPUT");
    
    /**
     * Tests the initial state of the page. It checks that there are all
     * necessary component and that all combo boxes contain the string
     * "Enter some value".
     */
    @Test
    public void testInitialState() {
        assertTrue(selenium.isElementPresent(LOC_FIRST_COMBO_INPUT), "First combo's input is not present.");
        assertTrue(selenium.isElementPresent(LOC_FIRST_COMBO_BUTTON), "First combo's button is not present.");
        assertTrue(selenium.isElementPresent(LOC_SECOND_COMBO_INPUT), "Second combo's input is not present.");
        assertTrue(selenium.isElementPresent(LOC_SECOND_COMBO_INPUT), "Second combo's button is not present.");
        assertTrue(selenium.isElementPresent(LOC_THIRD_COMBO_INPUT), "Third combo's input is not present.");
        assertTrue(selenium.isElementPresent(LOC_THIRD_COMBO_INPUT), "Third combo's button is not present.");

        String text = selenium.getValue(LOC_FIRST_COMBO_INPUT);
        assertEquals(text, MSG_INITIAL_STATE_INITIAL_VALUE_1, "Text in the first combo box.");

        text = selenium.getValue(LOC_SECOND_COMBO_INPUT);
        assertEquals(text, MSG_INITIAL_STATE_INITIAL_VALUE_2, "Text in the second combo box.");

        text = selenium.getValue(LOC_THIRD_COMBO_INPUT);
        assertEquals(text, MSG_INITIAL_STATE_INITIAL_VALUE_3, "Text in the third combo box.");
    }

    /**
     * Tests the first combo box. It clicks on combo's button and checks the
     * count of all suggestions. Then it types "su", checks the number of
     * suggestions and the content of all suggestions. In the end, it clicks on
     * the third suggestion and verifies the combo box's input field.
     */
    @Test
    public void testSuggestionsFirstComboBox() {
        scrollIntoView(LOC_FIRST_COMBO_BUTTON, true);

        selenium.click(LOC_FIRST_COMBO_BUTTON);
        waitForElement(format(LOC_FIRST_COMBO_SUGGESTIONS, 0));
        int count = getJQueryCount(format(LOC_FIRST_COMBO_SUGGESTIONS,-1));
        assertEquals(count, MSG_SUGGESTIONS_FIRST_COMBO_COUNT_1, "Number of suggestions after clicking on button.");

        // verify selecting item by mouse
        try {
            selenium.clickAt(format(LOC_FIRST_COMBO_SUGGESTIONS, 2), "");
        } catch (Exception ex) {
            // TODO: why the exception is thrown?
        }
        waitFor(1000);
        String text = selenium.getValue(LOC_FIRST_COMBO_INPUT);
        assertEquals(text, MSG_SUGGESTIONS_FIRST_COMBO_INPUT, "Third suggestion was chosen.");
        
        // verify filtering
        selenium.type(LOC_FIRST_COMBO_INPUT, "suggestion ");
        selenium.fireEvent(LOC_FIRST_COMBO_INPUT, Event.KEYUP);

        count = getJQueryCount(format(LOC_FIRST_COMBO_SUGGESTIONS, -1));
        assertEquals(count, MSG_SUGGESTIONS_FIRST_COMBO_COUNT_1, "Number of suggestions after typing 'sugestion '.");
        
        selenium.type(LOC_FIRST_COMBO_INPUT, "suggestion 1");
        selenium.fireEvent(LOC_FIRST_COMBO_INPUT, Event.KEYUP);

        count = getJQueryCount(format(LOC_FIRST_COMBO_SUGGESTIONS, -1));
        assertEquals(count, MSG_SUGGESTIONS_FIRST_COMBO_COUNT_2, "Number of suggestions after typing 'suggestion 1'.");

        String[] suggestions = new String[count];
        for (int i = 0; i < count; i++) {
            suggestions[i] = selenium.getText(format(LOC_FIRST_COMBO_SUGGESTIONS, i));
        }

        String[] expected = new String[] { "suggestion 1" };

        assertEqualsNoOrder(suggestions, expected, "Suggestions after typing 'suggestion 1'.");
    }

    /**
     * Tests the second combo box. It clicks on combo's button and checks the
     * count of all suggestions. Then it types "sa", checks the number of
     * suggestions and the content of all suggestions. In the end, it clicks on
     * the third suggestion and verifies the combo box's input field.
     */
    @Test
    public void testSuggestionsSecondComboBox() {
        scrollIntoView(LOC_SECOND_COMBO_BUTTON, true);

        selenium.click(LOC_SECOND_COMBO_BUTTON);
        waitForElement(format(LOC_SECOND_COMBO_SUGGESTIONS, 0));
        int count = getJQueryCount(format(LOC_SECOND_COMBO_SUGGESTIONS, -1));
        assertEquals(count, MSG_SUGGESTIONS_SECOND_COMBO_COUNT_1, "Number of suggestions after after clicking on button.");

        selenium.click(LOC_SECOND_COMBO_INPUT);
        selenium.type(LOC_SECOND_COMBO_INPUT, "sa");
        selenium.fireEvent(LOC_SECOND_COMBO_INPUT, Event.KEYUP);

        count = getJQueryCount(format(LOC_SECOND_COMBO_SUGGESTIONS, -1));
        assertEquals(count, MSG_SUGGESTIONS_SECOND_COMBO_COUNT_2, "Number of suggestions after typing 'sa'.");

        String[] suggestions = new String[4];
        for (int i = 0; i < 4; i++) {
            suggestions[i] = selenium.getText(format(LOC_SECOND_COMBO_SUGGESTIONS, i));
        }

        String[] expected = new String[] { "Sacramento", "Santa Fe", "Salem", "Salt Lake City" };

        assertEqualsNoOrder(suggestions, expected, "Suggestions after typing 'sa'.");

        try {
            selenium.clickAt(format(LOC_SECOND_COMBO_SUGGESTIONS, 2), "");
        } catch (Exception ex) {
            // TODO: why the exception is thrown?
        }

        waitFor(1000);
        String text = selenium.getValue(LOC_SECOND_COMBO_INPUT);
        assertEquals(text, MSG_SUGGESTIONS_SECOND_COMBO_INPUT, "Third suggestion was chosen.");
    }

    /**
     * Tests the third combo box. It clicks on combo's button and checks the
     * count of all suggestions. Then it types "sa", checks the number of
     * suggestions and the content of all suggestions. In the end, it clicks on
     * the third suggestion and verifies the combo box's input field.
     */
    @Test
    public void testSuggestionsThirdComboBox() {
        selenium.click(LOC_THIRD_COMBO_BUTTON);

        waitForElement(format(LOC_THIRD_COMBO_SUGGESTIONS, 0));
        int count = getJQueryCount(format(LOC_THIRD_COMBO_SUGGESTIONS, -1));
        assertEquals(count, MSG_SUGGESTIONS_THIRD_COMBO_COUNT_1, "Number of suggestions after after clicking on button.");

        selenium.click(LOC_THIRD_COMBO_INPUT);
        selenium.type(LOC_THIRD_COMBO_INPUT, "sa");
        selenium.fireEvent(LOC_THIRD_COMBO_INPUT, Event.KEYUP);

        count = getJQueryCount(format(LOC_THIRD_COMBO_SUGGESTIONS, -1));
        assertEquals(count, MSG_SUGGESTIONS_THIRD_COMBO_COUNT_2, "Number of suggestions after typing 'sa'.");

        String[] suggestions = new String[4];
        for (int i = 0; i < 4; i++) {
            suggestions[i] = selenium.getText(format(LOC_THIRD_COMBO_SUGGESTIONS, i));
        }

        String[] expected = new String[] { "Sacramento", "Santa Fe", "Salem", "Salt Lake City" };

        assertEqualsNoOrder(suggestions, expected, "Suggestions after typing 'sa'.");

        try {
            selenium.clickAt(format(LOC_THIRD_COMBO_SUGGESTIONS, 3), "");
        } catch (Exception ex) {
            // TODO: why the exception is thrown?
        }

        waitFor(1000);
        String text = selenium.getValue(LOC_THIRD_COMBO_INPUT);
        assertEquals(text, MSG_SUGGESTIONS_THIRD_COMBO_INPUT, "Third suggestion was chosen.");
    }

    /**
     * Tests the "View Source" in the first example. It checks that the source code is not visible,
     * clicks on the link, and checks 8 lines of source code.
     */
    @Test
    public void testFirstComboBoxSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<rich:comboBox defaultLabel=\"Enter some value\">",
                "<f:selectItem itemValue=\"suggestion 1\"/>",
                "<f:selectItem itemValue=\"suggestion 2\"/>",
                "<f:selectItem itemValue=\"suggestion 3\"/>",
                "<f:selectItem itemValue=\"suggestion 4\"/>",
                "<f:selectItem itemValue=\"suggestion 5\"/>",
                "</rich:comboBox>",
        };

        abstractTestSource(1, "View Source", strings);
    }
    
    /**
     * Tests the "View Source" in the second example. It checks that the source code is not visible,
     * clicks on the link, and checks 5 lines of source code.
     */
    @Test
    public void testSecondComboBoxSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<rich:comboBox selectFirstOnUpdate=\"false\"",
                "defaultLabel=\"Enter some value\">",
                "<f:selectItems value=\"#{capitalsBean.capitalsOptions}\" />",
                "</rich:comboBox>",
       };

        abstractTestSource(2, "View Source", strings);
    }
    
    /**
     * Tests the "View Source" in the third example. It checks that the source code is not visible,
     * clicks on the link, and checks 3 lines of source code.
     */
    @Test
    public void testThirdComboBoxSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<rich:comboBox suggestionValues=\"#{capitalsBean.capitalsNames}\"",
                "directInputSuggestions=\"true\" defaultLabel=\"Enter some value\" />",
        };

        abstractTestSource(3, "View Source", strings);
    }
    
    /**
     * Loads the page containing the calendar component.
     */
    protected void loadPage() {
        openComponent("Combo Box");
    }

}
