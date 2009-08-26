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

package org.jboss.richfaces.integrationTest.comboBox;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsNoOrder;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests the combo box component.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class ComboBoxTestCase extends AbstractSeleniumRichfacesTestCase {

    private final String firstComboInput = "//fieldset[1]/div/div[1]/div/div[2]/input[1]";
    private final String firstComboButton = "//fieldset[1]/div/div[1]/div/div[2]/input[3]";
    private final String firstComboConcreteSuggestion = "//body//div[@class='rich-combobox-list-scroll']/span[{0}]";
    private final String firstComboSuggestions = "//body//div[@class='rich-combobox-list-scroll']/span";

    private final String secondComboInput = "//fieldset[2]/div/div[1]/div/div[2]/input[1]";
    private final String secondComboButton = "//fieldset[2]/div/div[1]/div/div[2]/input[3]";
    private final String secondComboConcreteSuggestion = "//body//div[@class='rich-combobox-list-scroll']/span[{0}]";
    private final String secondComboSuggestions = "//body//div[@class='rich-combobox-list-scroll']/span";

    private final String thirdComboInput = "//fieldset[3]/div/div[1]/div/div[2]/input[1]";
    private final String thirdComboButton = "//fieldset[3]/div/div[1]/div/div[2]/input[3]";
    private final String thirdComboConcreteSuggestion = "//body//div[@class='rich-combobox-list-scroll']/span[{0}]";
    private final String thirdComboSuggestions = "//body//div[@class='rich-combobox-list-scroll']/span";

    /**
     * Tests the initial state of the page. It checks that there are all
     * necessary component and that all combo boxes contain the string
     * "Enter some value".
     */
    @Test
    public void testInitialState() {
        assertTrue(selenium.isElementPresent(firstComboInput), "First combo's input is not present.");
        assertTrue(selenium.isElementPresent(firstComboButton), "First combo's button is not present.");
        assertTrue(selenium.isElementPresent(secondComboInput), "Second combo's input is not present.");
        assertTrue(selenium.isElementPresent(secondComboInput), "Second combo's button is not present.");
        assertTrue(selenium.isElementPresent(thirdComboInput), "Third combo's input is not present.");
        assertTrue(selenium.isElementPresent(thirdComboInput), "Third combo's button is not present.");

        String text = selenium.getValue(firstComboInput);
        assertEquals(text, "Enter some value", "Text in the first combo box.");

        text = selenium.getValue(secondComboInput);
        assertEquals(text, "Enter some value", "Text in the second combo box.");

        text = selenium.getValue(thirdComboInput);
        assertEquals(text, "Enter some value", "Text in the third combo box.");
    }

    /**
     * Tests the first combo box. It clicks on combo's button and checks the
     * count of all suggestions. Then it types "su", checks the number of
     * suggestions and the content of all suggestions. In the end, it clicks on
     * the third suggestion and verifies the combo box's input field.
     * 
     * <ul>
     * <li><b>FIXME</b>the div containing suggestions moves out of fieldset</li>
     * <li><b>FIXME</b>"su " has to be clicked instead of "su"</li>
     * <li><b>FIXME</b>it is not possible to click on the third suggestion</li>
     * </ul>
     */
    @Test
    public void testSuggestionsFirstComboBox() {
        scrollIntoView(firstComboButton, true);

        selenium.click(firstComboButton);
        waitForElement(format(firstComboConcreteSuggestion, 1));
        int count = selenium.getXpathCount(firstComboSuggestions).intValue();
        assertEquals(count, 5, "Number of suggestions after after clicking on button.");

        selenium.click(firstComboInput);
        selenium.typeKeys(firstComboInput, "su");
        // FIXME why is this necessary?
        selenium.typeKeys(firstComboInput, " ");

        count = selenium.getXpathCount(firstComboSuggestions).intValue();
        assertEquals(count, 5, "Number of suggestions after typing 'su'.");

        String[] suggestions = new String[5];
        for (int i = 0; i < 5; i++) {
            suggestions[i] = selenium.getText(format(firstComboConcreteSuggestion, i + 1));
        }

        String[] expected = new String[] { "suggestion 1", "suggestion 2", "suggestion 3", "suggestion 4",
                "suggestion 5", };

        assertEqualsNoOrder(suggestions, expected, "Suggestions after typing 'sa'.");

        try {
            selenium.clickAt(format(firstComboConcreteSuggestion, 3), "");
        } catch (Exception ex) {
            // TODO why the exception is thrown?
        }
        waitFor(1000);
        String text = selenium.getValue(firstComboInput);
        assertEquals(text, "suggestion 3", "Third suggestion was chosen.");
    }

    /**
     * Tests the second combo box. It clicks on combo's button and checks the
     * count of all suggestions. Then it types "sa", checks the number of
     * suggestions and the content of all suggestions. In the end, it clicks on
     * the third suggestion and verifies the combo box's input field.
     * 
     * <ul>
     * <li><b>FIXME</b>the div containing suggestions moves out of fieldset</li>
     * <li><b>FIXME</b>"sa " has to be clicked instead of "sa"</li>
     * </ul>
     */
    @Test
    public void testSuggestionsSecondComboBox() {
        scrollIntoView(secondComboButton, true);

        selenium.click(secondComboButton);
        waitForElement(format(secondComboConcreteSuggestion, 1));
        int count = selenium.getXpathCount(secondComboSuggestions).intValue();
        assertEquals(count, 50, "Number of suggestions after after clicking on button.");

        selenium.click(secondComboInput);
        selenium.typeKeys(secondComboInput, "sa");
        // FIXME why is this necessary?
        selenium.typeKeys(secondComboInput, " ");

        count = selenium.getXpathCount(secondComboSuggestions).intValue();
        assertEquals(count, 4, "Number of suggestions after typing 'sa'.");

        String[] suggestions = new String[4];
        for (int i = 0; i < 4; i++) {
            suggestions[i] = selenium.getText(format(secondComboConcreteSuggestion, i + 1));
        }

        String[] expected = new String[] { "Sacramento", "Santa Fe", "Salem", "Salt Lake City" };

        assertEqualsNoOrder(suggestions, expected, "Suggestions after typing 'sa'.");

        try {
            selenium.clickAt(format(secondComboConcreteSuggestion, 3), "");
        } catch (Exception ex) {
            // TODO why the exception is thrown?
        }

        waitFor(1000);
        String text = selenium.getValue(secondComboInput);
        assertEquals(text, "Salem", "Third suggestion was chosen.");
    }

    /**
     * Tests the third combo box. It clicks on combo's button and checks the
     * count of all suggestions. Then it types "sa", checks the number of
     * suggestions and the content of all suggestions. In the end, it clicks on
     * the third suggestion and verifies the combo box's input field.
     * 
     * <ul>
     * <li><b>FIXME</b>the div containing suggestions moves out of fieldset</li>
     * <li><b>FIXME</b>"sa " has to be clicked instead of "sa"</li>
     * </ul>
     */
    @Test
    public void testSuggestionsThirdComboBox() {
        selenium.click(thirdComboButton);

        waitForElement(format(thirdComboConcreteSuggestion, 1));
        int count = selenium.getXpathCount(thirdComboSuggestions).intValue();
        assertEquals(count, 50, "Number of suggestions after after clicking on button.");

        selenium.click(thirdComboInput);
        selenium.typeKeys(thirdComboInput, "sa");
        // FIXME why is this necessary?
        selenium.typeKeys(thirdComboInput, " ");

        count = selenium.getXpathCount(thirdComboSuggestions).intValue();
        assertEquals(count, 4, "Number of suggestions after typing 'sa'.");

        String[] suggestions = new String[4];
        for (int i = 0; i < 4; i++) {
            suggestions[i] = selenium.getText(format(thirdComboConcreteSuggestion, i + 1));
        }

        String[] expected = new String[] { "Sacramento", "Santa Fe", "Salem", "Salt Lake City" };

        assertEqualsNoOrder(suggestions, expected, "Suggestions after typing 'sa'.");

        try {
            selenium.clickAt(format(thirdComboConcreteSuggestion, 4), "");
        } catch (Exception ex) {
            // TODO why the exception is thrown?
        }

        waitFor(1000);
        String text = selenium.getValue(thirdComboInput);
        assertEquals(text, "Salt Lake City", "Third suggestion was chosen.");
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks the first 2 components of source code,
     * i.e. that the source code begins with "&lt;ui:composition".
     */
    @Test
    public void testFirstComboBoxSource() {
        // index has to be 2 because the combo box is in a div element
        abstractTestSource(1, 2, "<", "ui:composition");
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks the first 2 components of source code,
     * i.e. that the source code begins with "&lt;ui:composition".
     */
    @Test
    public void testSecondComboBoxSource() {
        // index has to be 2 because the combo box is in a div element
        abstractTestSource(2, 2, "<", "ui:composition");
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks the first 2 components of source code,
     * i.e. that the source code begins with "&lt;ui:composition".
     */
    @Test
    public void testThirdComboBoxSource() {
        // index has to be 2 because the combo box is in a div element
        abstractTestSource(3, 2, "<", "ui:composition");
    }

    /**
     * Loads the page containing the calendar component.
     */
    @BeforeMethod
    private void loadPage() {
        super.loadPage("richInputs", 3, "The component provides editable combobox element on the page.");
    }

}
