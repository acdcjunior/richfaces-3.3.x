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

package org.jboss.richfaces.integrationTest.inplaceSelect;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests the inplace select component.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
// TODO test cancel buttons
public class InplaceSelectTestCase extends AbstractSeleniumRichfacesTestCase {

    // messages
    private final String MSG_CLICK_HERE_TO_EDIT = getMsg("CLICK_HERE_TO_EDIT");
    private final String MSG_DOUBLE_CLICK_TO_EDIT = getMsg("DOUBLE_CLICK_TO_EDIT");
    private final String MSG_READ_ONLY = getMsg("READ_ONLY");
    private final String MSG_DISPLAY_NONE = getMsg("DISPLAY_NONE");
    private final String MSG_OPTION4_SELECTED = getMsg("OPTION4_SELECTED");
    private final String MSG_ARKANSAS_SELECTED = getMsg("ARKANSAS_SELECTED");
    private final String MSG_LITTLE_ROCK = getMsg("LITTLE_ROCK");
    private final String MSG_COUNT_OF_ITEMS = getMsg("COUNT_OF_ITEMS");

    // locators
    private final String LOC_FIRST = getLoc("FIRST");
    private final String LOC_FIRST_INPUT_1 = getLoc("FIRST_INPUT_1");
    private final String LOC_FIRST_INPUT_2 = getLoc("FIRST_INPUT_2");
    private final String LOC_FIRST_SELECT_VIEW = getLoc("FIRST_SELECT_VIEW");
    private final String LOC_FIRST_LIST_SPAN = getLoc("FIRST_LIST_SPAN");
    private final String LOC_FIRST_LIST_SPAN_N = getLoc("FIRST_LIST_SPAN_N");
    private final String LOC_SECOND_OK_BUTTON = getLoc("SECOND_OK_BUTTON");

    private final String LOC_SECOND = getLoc("SECOND");
    private final String LOC_SECOND_INPUT_1 = getLoc("SECOND_INPUT_1");
    private final String LOC_SECOND_INPUT_2 = getLoc("SECOND_INPUT_2");
    private final String LOC_SECOND_LIST_SPAN = getLoc("SECOND_LIST_SPAN");
    private final String LOC_SECOND_LIST_SPAN_N = getLoc("SECOND_LIST_SPAN_N");

    private final String LOC_THIRD = getLoc("THIRD");
    private final String LOC_THIRD_INPUT_1 = getLoc("THIRD_INPUT_1");
    private final String LOC_THIRD_INPUT_2 = getLoc("THIRD_INPUT_2");
    private final String LOC_THIRD_LIST_SPAN = getLoc("THIRD_LIST_SPAN");
    private final String LOC_THIRD_LIST_SPAN_N = getLoc("THIRD_LIST_SPAN_N");
    private final String LOC_THIRD_CAPITAL = getLoc("THIRD_CAPITAL");
    private final String LOC_THIRD_SAVE_BUTTON = getLoc("THIRD_SAVE_BUTTON");

    /**
     * Tests the first example. It checks the "readonly" and "style" attribute
     * of the inplace select. Then it clicks into and out of the component and
     * verifies that nothing changed. Consequently it checks the number of items
     * in the component. In the end it selects the fourth item from the inplace
     * select and checks the text.
     */
    @Test
    public void testFirstInput() {
        scrollIntoView(LOC_FIRST, true);

        String text = selenium.getText(LOC_FIRST);
        assertTrue(text.equals("Click here to edit"), MSG_CLICK_HERE_TO_EDIT);

        String attr = selenium.getAttribute(LOC_FIRST_INPUT_1 + "@readonly");
        assertTrue(attr.equals("readonly"), MSG_READ_ONLY);

        assertFalse(isDisplayed(LOC_FIRST_INPUT_2), MSG_DISPLAY_NONE);

        // expand and collapse the inplace select
        selenium.click(LOC_FIRST_INPUT_1);
        selenium.click(LOC_FIRST_INPUT_2);

        text = selenium.getText(LOC_FIRST);
        assertTrue(text.equals("Click here to edit"), MSG_CLICK_HERE_TO_EDIT);

        // expand the component and select "Option 4"
        selenium.click(LOC_FIRST);

        int count = getJQueryCount(LOC_FIRST_LIST_SPAN);
        assertEquals(count, 5, MSG_COUNT_OF_ITEMS);

        selenium.mouseMove(format(LOC_FIRST_LIST_SPAN_N, 3));

        // TODO explore whether this isn't too low-level
        selenium.getEval(format("selenium.browserbot.findElement(\"{0}\").component.save()", LOC_FIRST_SELECT_VIEW));

        // another way to select it
        // selenium.selectWindow(null); // select the main window
        // selenium.windowFocus();
        // selenium.keyPressNative(Integer.toString(KeyEvent.VK_ENTER));

        text = selenium.getText(LOC_FIRST);
        assertTrue(text.equals("Option 4"), MSG_OPTION4_SELECTED);
    }

    /**
     * Tests the second example. It checks the "readonly" and "style" attribute
     * of the inplace select. Then it clicks into and out of the component and
     * verifies that nothing changed. Consequently it checks the number of items
     * in the component. In the end it selects the fourth item from the inplace
     * select and checks the text.
     */
    @Test
    public void testSecondInput() {
        scrollIntoView(LOC_SECOND, true);

        String text = selenium.getText(LOC_SECOND);
        assertTrue(text.equals("Double Click to edit"), MSG_DOUBLE_CLICK_TO_EDIT);

        String attr = selenium.getAttribute(LOC_SECOND_INPUT_1 + "@readonly");
        assertTrue(attr.equals("readonly"), MSG_READ_ONLY);

        assertFalse(isDisplayed(LOC_SECOND_INPUT_2), MSG_DISPLAY_NONE);

        // expand and collapse the inplace select
        selenium.click(LOC_SECOND_INPUT_1);
        selenium.click(LOC_SECOND_INPUT_2);

        int count = getJQueryCount(LOC_SECOND_LIST_SPAN);
        assertEquals(count, 50, MSG_COUNT_OF_ITEMS);

        text = selenium.getText(LOC_SECOND);
        assertTrue(text.equals("Double Click to edit"), MSG_DOUBLE_CLICK_TO_EDIT);

        // expand the inplace select and click "Arkansas"
        selenium.mouseMove(format(LOC_SECOND_LIST_SPAN_N, 3));
        selenium.mouseDown(LOC_SECOND_OK_BUTTON);

        text = selenium.getText(LOC_SECOND);
        assertTrue(text.equals("Arkansas"), MSG_ARKANSAS_SELECTED);
    }

    /**
     * Tests the third example. It checks the "readonly" and "style" attribute
     * of the inplace select. Then it clicks into and out of the component and
     * verifies that nothing changed. Consequently it checks the number of items
     * in the component. In the end it selects the fourth item from the inplace
     * select and checks the text.
     */
    @Test
    public void testThirdInput() {
        scrollIntoView(LOC_THIRD, true);

        String text = selenium.getText(LOC_THIRD);
        assertTrue(text.equals("Click here to edit"), MSG_CLICK_HERE_TO_EDIT);

        String attr = selenium.getAttribute(LOC_THIRD_INPUT_1 + "@readonly");
        assertTrue(attr.equals("readonly"), MSG_READ_ONLY);

        assertFalse(isDisplayed(LOC_THIRD_INPUT_2), MSG_DISPLAY_NONE);

        // expand and collapse the inplace select
        selenium.click(LOC_THIRD_INPUT_1);
        selenium.click(LOC_THIRD_INPUT_2);

        int count = getJQueryCount(LOC_THIRD_LIST_SPAN);
        assertEquals(count, 50, MSG_COUNT_OF_ITEMS);

        text = selenium.getText(LOC_THIRD);
        assertTrue(text.endsWith("Click here to edit"), MSG_CLICK_HERE_TO_EDIT);

        // remember for waiting
        text = selenium.getText(LOC_THIRD_CAPITAL);

        // select "Arkansas"
        selenium.mouseMove(format(LOC_THIRD_LIST_SPAN_N, 3));
        selenium.mouseDown(LOC_THIRD_SAVE_BUTTON);

        // wait for remembered value change
        text = waitForTextChangesAndReturn(LOC_THIRD_CAPITAL, text);
        assertTrue(text.endsWith("Little Rock"), MSG_LITTLE_ROCK);

        text = selenium.getText(LOC_THIRD);
        assertTrue(text.equals("Arkansas"), MSG_ARKANSAS_SELECTED);
    }

    /**
     * Tests the "View Source" in the first example. It checks that the source
     * code is not visible, clicks on the link, and checks 7 lines of source
     * code.
     */
    @Test
    public void testFirstExampleSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<rich:inplaceSelect value=\"#{inplaceComponentsBean.inputValue}\"",
                "defaultLabel=\"Click here to edit\">", "<f:selectItem itemValue=\"0\" itemLabel=\"Option 1\" />",
                "</rich:inplaceSelect>", "<rich:spacer width=\"100%\" height=\"20px\"/>", "</ui:composition>", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Tests the "View Source" in the second example. It checks that the source
     * code is not visible, clicks on the link, and checks 10 lines of source
     * code.
     */
    @Test
    public void testSecondExampleSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"", "<style>",
                "<h:panelGroup style=\"width:200px;\" layout=\"block\">",
                "<rich:inplaceSelect value=\"#{inplaceComponentsBean.inputValue}\"",
                "defaultLabel=\"Double Click to edit\" openOnEdit=\"true\"",
                "showControls=\"true\" editEvent=\"ondblclick\" layout=\"block\"",
                "viewClass=\"inplace\" changedClass=\"inplace\"",
                "changedHoverClass=\"hover\" viewHoverClass=\"hover\">",
                " <f:selectItems value=\"#{capitalsBean.capitalsOptions}\" />",
                "<rich:spacer width=\"100%\" height=\"20px\" />", };

        abstractTestSource(2, "View Source", strings);
    }

    /**
     * Tests the "View Source" in the third example. It checks that the source
     * code is not visible, clicks on the link, and checks 17 lines of source
     * code.
     */
    @Test
    public void testThirdExampleSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"", "<style>",
                "<h:panelGrid columns=\"3\" width=\"500px;\">",
                "<rich:inplaceSelect value=\"#{inplaceComponentsBean.inputValue}\"",
                "defaultLabel=\"Click here to edit\" showControls=\"true\"",
                "controlsHorizontalPosition=\"left\" controlsVerticalPosition=\"bottom\"", "id=\"inplaceSelect\">",
                "<f:facet name=\"controls\">", "<button onmousedown=\"#{rich:component('inplaceSelect')}.save();\"",
                "type=\"button\">Save</button>",
                "<button onmousedown=\"#{rich:component('inplaceSelect')}.cancel();\"",
                "type=\"button\">Cancel</button>", "<f:selectItems value=\"#{capitalsBean.capitalsOptions}\" />",
                "<a4j:support event=\"onviewactivated\" reRender=\"output\" />", "<h:outputText id=\"output\"",
                "value=\"Current State Capital: #{inplaceComponentsBean.inputValue}\"",
                "style=\"font-weight:bold;\" />", };

        abstractTestSource(3, "View Source", strings);
    }

    /**
     * Loads the page containing the calendar component.
     */
    protected void loadPage() {
        openComponent("Inplace Select");
    }
}
