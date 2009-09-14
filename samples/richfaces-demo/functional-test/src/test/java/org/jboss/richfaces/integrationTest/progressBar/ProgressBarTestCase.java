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

package org.jboss.richfaces.integrationTest.progressBar;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests progress bar.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class ProgressBarTestCase extends AbstractSeleniumRichfacesTestCase {

    // locators
    private final String LOC_EXAMPLE_1_HEADER = getLoc("EXAMPLE_1_HEADER");
    private final String LOC_EXAMPLE_2_HEADER = getLoc("EXAMPLE_2_HEADER");
    private final String LOC_FIRST_BUTTON = getLoc("FIRST_BUTTON");
    private final String LOC_FIRST_PROGRESS_BAR_STYLE = getLoc("FIRST_PROGRESS_BAR_STYLE");
    private final String LOC_SECOND_BUTTON = getLoc("SECOND_BUTTON");
    private final String LOC_SECOND_LABEL_FINISHED = getLoc("SECOND_LABEL_FINISHED");
    private final String LOC_SECOND_LABEL_INITIAL = getLoc("SECOND_LABEL_INITIAL");
    private final String LOC_SECOND_PROGRESS_BAR_STYLE = getLoc("SECOND_PROGRESS_BAR_STYLE");

    /**
     * Tests the first example. It checks that the button is visible and then it
     * click on the button. The process starts and the test checks 3 times that
     * the label of the progress bar and its "width" attribute equal.
     */
    @Test
    public void testFirstExample() {
        scrollIntoView(LOC_EXAMPLE_1_HEADER, true);

        boolean present = selenium.isElementPresent(LOC_FIRST_BUTTON);
        assertTrue(present, "Button should be present on the page.");

        selenium.click(LOC_FIRST_BUTTON);

        waitFor(500);
        present = selenium.isElementPresent(LOC_FIRST_BUTTON);
        assertFalse(present, "Button \"Restart Process\" should not be present on the page.");

        int value1 = 0;
        int value2 = 0;

        for (int i = 0; i < 6; i++) {
            waitFor(2000);
            value2 = value1;
            value1 = (int) Double.parseDouble(getStyle(LOC_FIRST_PROGRESS_BAR_STYLE, "width").replace("px", ""));
            assertTrue(value1 > value2, "Progress bar should move to the right.");
        }
    }

    /**
     * Tests the second example. First it checks the displayed label. Then it
     * clicks on the button and verifies several times that the progress bar is
     * moving right. Then it waits for process to finish and checks that a label
     * was displayed.
     */
    @Test
    public void testSecondExample() {
        scrollIntoView(LOC_EXAMPLE_2_HEADER, true);

        assertTrue(isDisplayed(LOC_SECOND_LABEL_INITIAL), "Initial label should be visible.");
        assertFalse(isDisplayed(LOC_SECOND_LABEL_FINISHED), "Finished label should not be visible.");

        selenium.click(LOC_SECOND_BUTTON);

        int current = 0;
        int previous = 0;
        waitFor(2200);

        for (int i = 0; i < 10; i++) {
            waitFor(2000);
            current = (int) Double.parseDouble(getStyle(LOC_SECOND_PROGRESS_BAR_STYLE, "width").replace("px", ""));
            assertTrue(current >= previous, "Progress of the progress bar -- the current value should be greater then the one 2 seconds ago.");
            previous = current;
        }

        waitFor(82000);

        assertFalse(isDisplayed(LOC_SECOND_LABEL_INITIAL), "Initial label should not be visible.");
        assertTrue(isDisplayed(LOC_SECOND_LABEL_FINISHED), "Finished label should be visible.");
    }

    /**
     * Tests the "View Source" in the first example. It checks that the source
     * code is not visible, clicks on the link, and checks 7 lines of source
     * code.
     */
    @Test
    public void testFirstExampleSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<a4j:outputPanel id=\"progressPanel\">",
                "<rich:progressBar value=\"#{progressBarBean.currentValue}\"",
                "interval=\"2000\" label=\"#{progressBarBean.currentValue} %\"",
                "enabled=\"#{progressBarBean.enabled}\" minValue=\"-1\" maxValue=\"100\"",
                "reRenderAfterComplete=\"progressPanel\">",
                "<a4j:commandButton action=\"#{progressBarBean.startProcess}\"", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Tests the "View Java Bean Source" in the first example. It checks that
     * the source code is not visible, clicks on the link, and checks 7 lines of
     * source code.
     */
    @Test
    public void testFirstExampleBeanSource() {
        String[] strings = new String[] { "package org.richfaces.demo.progressBar;", "public class ProgressBarBean {",
                "private boolean buttonRendered = true;", "setStartTime(new Date().getTime());",
                "Long current = (new Date().getTime() - startTime)/1000;", "public void setEnabled(boolean enabled) {",
                "this.buttonRendered = buttonRendered;", };

        abstractTestSource(1, "View Java Bean Source", strings);
    }

    /**
     * Tests the "View Source" in the second example. It checks that the source
     * code is not visible, clicks on the link, and checks 7 lines of source
     * code.
     */
    @Test
    public void testSecondExampleSource() {
        String[] strings = new String[] {
                "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "$('form:progressBar').component.setValue(counter*5);",
                "document.getElementById('button').disabled=false;",
                "<h:form id=\"form\">",
                "</f:facet>",
                "</rich:progressBar>",
                "<rich:progressBar mode=\"client\" id=\"progressBar\">",
                "<button type=\"button\" onclick=\"startProgress();\" style=\"margin: 9px 0px 5px;\" id=\"button\">Start Progress</button>", };

        abstractTestSource(2, "View Source", strings);
    }

    /**
     * Loads the page containing the component.
     */
    @SuppressWarnings("unused")
    @BeforeMethod
    private void loadPage() {
        openComponent("Progress Bar");
    }
}
