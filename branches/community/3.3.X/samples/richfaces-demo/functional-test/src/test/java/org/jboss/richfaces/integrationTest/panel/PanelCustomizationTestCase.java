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

package org.jboss.richfaces.integrationTest.panel;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests customization of panels.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class PanelCustomizationTestCase extends AbstractSeleniumRichfacesTestCase {

    // messages
    private final String MSG_CUST_PANEL_HEADER = getMsg("CUST_PANEL_HEADER");
    private final String MSG_CUST_CLASS_HEADER = getMsg("CUST_CLASS_HEADER");
    private final String MSG_CUST_THIRD_HEADER = getMsg("CUST_THIRD_HEADER");
    private final String MSG_CUST_THIRD_BODY = getMsg("CUST_THIRD_BODY");
    private final String MSG_CUST_FOURTH_PANEL_BODY_SCROLLABLE = getMsg("CUST_FOURTH_PANEL_BODY_SCROLLABLE");
    private final String MSG_CUST_FIFTH_PANEL_NO_HEADER = getMsg("CUST_FIFTH_PANEL_NO_HEADER");

    // locators
    private final String LOC_CUST_FIRST_PANEL_HEADER = getLoc("CUST_FIRST_PANEL_HEADER");
    private final String LOC_CUST_SECOND_PANEL_HEADER = getLoc("CUST_SECOND_PANEL_HEADER");
    private final String LOC_CUST_THIRD_PANEL_HEADER = getLoc("CUST_THIRD_PANEL_HEADER");
    private final String LOC_CUST_THIRD_PANEL_BODY = getLoc("CUST_THIRD_PANEL_BODY");
    private final String LOC_CUST_FOURTH_PANEL_BODY = getLoc("CUST_FOURTH_PANEL_BODY");
    private final String LOC_CUST_FOURTH_PANEL_HEADER = getLoc("CUST_FOURTH_PANEL_HEADER");
    private final String LOC_CUST_FIFTH_PANEL_PARTS = getLoc("CUST_FIFTH_PANEL_PARTS");

    @Test
    public void testFirstPanel() {
        String text = selenium.getText(LOC_CUST_FIRST_PANEL_HEADER);
        assertEquals(text, "Panel #1. Changing Style Synchronously", MSG_CUST_PANEL_HEADER);

        text = selenium.getAttribute(LOC_CUST_FIRST_PANEL_HEADER + "@class");
        assertTrue(text.contains("rich-panel-header"), MSG_CUST_CLASS_HEADER);
    }

    @Test
    public void testSecondPanel() {
        String text = selenium.getText(LOC_CUST_SECOND_PANEL_HEADER);
        assertEquals(text, "Panel #2. Changing Style Synchronously", MSG_CUST_PANEL_HEADER);

        text = selenium.getAttribute(LOC_CUST_SECOND_PANEL_HEADER + "@class");
        assertTrue(text.contains("rich-panel-header"), MSG_CUST_CLASS_HEADER);
    }

    @Test
    public void testThirdPanel() {
        String text = selenium.getAttribute(LOC_CUST_THIRD_PANEL_HEADER + "@class");
        assertTrue(text.contains("head2"), MSG_CUST_THIRD_HEADER);

        text = selenium.getAttribute(LOC_CUST_THIRD_PANEL_BODY + "@class");
        assertTrue(text.contains("body3"), MSG_CUST_THIRD_BODY);
    }

    @Test
    public void testFourthPanel() {
        String text = selenium.getText(LOC_CUST_FOURTH_PANEL_HEADER);
        assertEquals(text, "Scrolling Text Panel", MSG_CUST_PANEL_HEADER);

        text = selenium.getAttribute(LOC_CUST_FOURTH_PANEL_BODY + "@class");
        assertTrue(text.contains("body3"), MSG_CUST_FOURTH_PANEL_BODY_SCROLLABLE);
    }

    @Test
    public void testFifthPanel() {
        int count = selenium.getXpathCount(LOC_CUST_FIFTH_PANEL_PARTS).intValue();
        assertEquals(count, 1, MSG_CUST_FIFTH_PANEL_NO_HEADER);
    }

    /**
     * Loads the page containing the component.
     */
    @BeforeMethod
    private void loadPage() {
        openComponent("Panel");
        openTab("Look Customization");
    }
}
