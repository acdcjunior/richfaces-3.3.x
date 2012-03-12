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

package org.jboss.richfaces.integrationTest.panel;

import static org.testng.Assert.assertEquals;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests panels.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class PanelTestCase extends AbstractSeleniumRichfacesTestCase {

    // messages
    private final String MSG_FIRST_PANEL_TWO_PARTS = getMsg("FIRST_PANEL_TWO_PARTS");
    private final String MSG_FIRST_HEADER = getMsg("FIRST_HEADER");
    private final String MSG_SECOND_PANEL_NO_HEADER = getMsg("SECOND_PANEL_NO_HEADER");
    private final String MSG_THIRD_PANEL_TWO_PARTS = getMsg("THIRD_PANEL_TWO_PARTS");
    private final String MSG_SHOULD_BE_TWO_NESTED_PANELS = getMsg("SHOULD_BE_TWO_NESTED_PANELS");
    private final String MSG_SIX_ITEMS_IN_LIST_LEFT = getMsg("SIX_ITEMS_IN_LIST_LEFT");
    private final String MSG_SIX_ITEMS_IN_LIST_RIGHT = getMsg("SIX_ITEMS_IN_LIST_RIGHT");
    private final String MSG_THIRD_HEADER = getMsg("THIRD_HEADER");

    // locators
    private final String LOC_FIRST_DIVS = getLoc("FIRST_DIVS");
    private final String LOC_FIRST_HEADER = getLoc("FIRST_HEADER");
    private final String LOC_SECOND_DIVS = getLoc("SECOND_DIVS");
    private final String LOC_THIRD_DIVS = getLoc("THIRD_DIVS");
    private final String LOC_THIRD_HEADER = getLoc("THIRD_HEADER");
    private final String LOC_THIRD_NESTED_PANELS = getLoc("THIRD_NESTED_PANELS");
    private final String LOC_LEFT_NESTED_PANEL_LIST_LI = getLoc("LEFT_NESTED_PANEL_LIST_LI");
    private final String LOC_RIGHT_NESTED_PANEL_LIST_LI = getLoc("RIGHT_NESTED_PANEL_LIST_LI");

    /**
     * Tests the first example. It verifies that there are both header and body
     * of the panel. Then it checks the content of the header.
     */
    @Test
    public void testFirstExample() {
        int count = getJQueryCount(LOC_FIRST_DIVS);
        assertEquals(count, 2, MSG_FIRST_PANEL_TWO_PARTS);

        String text = selenium.getText(LOC_FIRST_HEADER);
        assertEquals(text, "Write your own custom rich components with built-in AJAX support", MSG_FIRST_HEADER);
    }

    /**
     * Tests the second example. It verifies that there is no header.
     */
    @Test
    public void testSecondExample() {
        int count = getJQueryCount(LOC_SECOND_DIVS);
        assertEquals(count, 1, MSG_SECOND_PANEL_NO_HEADER);
    }

    /**
     * Tests the third example. Verifies that the main panel has both header and
     * body and checks its header. Then it checks the count of nested panels.
     * For both left and right nested panel it checks that there are 6 items in
     * lists.
     */
    @Test
    public void testThirdExample() {
        int count = getJQueryCount(LOC_THIRD_DIVS);
        assertEquals(count, 2, MSG_THIRD_PANEL_TWO_PARTS);

        String text = selenium.getText(LOC_THIRD_HEADER);
        assertEquals(text, "", MSG_THIRD_HEADER);

        count = getJQueryCount(LOC_THIRD_NESTED_PANELS);
        assertEquals(count, 2, MSG_SHOULD_BE_TWO_NESTED_PANELS);

        count = getJQueryCount(LOC_LEFT_NESTED_PANEL_LIST_LI);
        assertEquals(count, 6, MSG_SIX_ITEMS_IN_LIST_LEFT);

        count = getJQueryCount(LOC_RIGHT_NESTED_PANEL_LIST_LI);
        assertEquals(count, 6, MSG_SIX_ITEMS_IN_LIST_RIGHT);
    }

    /**
     * Tests the "View Source" of the first example. It checks that the source
     * code is not visible, clicks on the link, and checks 7 lines of source
     * code.
     */
    @Test
    public void testFirstExampleSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"", "<rich:panel>",
                "<f:facet name=\"header\">", "Write your own custom rich components with built-in AJAX support",
                "The CDK includes a code-generation facility and a", "</rich:panel>", "</ui:composition>", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Tests the "View Source" of the second example. It checks that the source
     * code is not visible, clicks on the link, and checks 6 lines of source
     * code.
     */
    @Test
    public void testSecondExampleSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "xmlns:a4j=\"http://richfaces.org/a4j\"", "<rich:panel bodyClass=\"rich-laguna-panel-no-header\">",
                "RichFaces is a library for adding rich user interface features to JSF", "</rich:panel>",
                "</ui:composition>", };

        abstractTestSource(2, "View Source", strings);
    }

    /**
     * Tests the "View Source" of the third example. It checks that the source
     * code is not visible, clicks on the link, and checks 7 lines of source
     * code.
     */
    @Test
    public void testThirdExampleSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<rich:panel style=\"padding:0\" headerClass=\"outpanelHeader\">", "<f:facet name=\"header\">",
                "<h2 align=\"center\"><h:outputText value=\"Benefits of Using Ajax4jsf\" /></h2>",
                "<rich:panel bodyClass=\"inpanelBody\">", "<li>Production quality Open Source</li>",
                "</ui:composition>", };

        abstractTestSource(3, "View Source", strings);
    }

    /**
     * Loads the page containing the component.
     */
    protected void loadPage() {
        openComponent("Panel");
    }
}
