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

package org.jboss.richfaces.integrationTest.modalPanel;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests modal panels.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class ModalPanelTestCase extends AbstractSeleniumRichfacesTestCase {

    // messages
    private final String MSG_FIRST_PANEL_HEADER = getMsg("FIRST_PANEL_HEADER");
    private final String MSG_FIRST_PANEL_CONTENT = getMsg("FIRST_PANEL_CONTENT");

    private final String MSG_SECOND_PANEL_HEADER = getMsg("SECOND_PANEL_HEADER");
    private final String MSG_SECOND_PANEL_CONTENT = getMsg("SECOND_PANEL_CONTENT");

    // locators
    private final String LOC_FIRST_EXAMPLE_HEADER = getLoc("FIRST_EXAMPLE_HEADER");
    private final String LOC_FIRST_PANEL = getLoc("FIRST_PANEL");
    private final String LOC_FIRST_PANEL_POSITION = getLoc("FIRST_PANEL_POSITION");
    private final String LOC_FIRST_PANEL_OPEN = getLoc("FIRST_PANEL_OPEN");
    private final String LOC_FIRST_PANEL_CLOSE = getLoc("FIRST_PANEL_CLOSE");
    private final String LOC_FIRST_PANEL_HEADER = getLoc("FIRST_PANEL_HEADER");
    private final String LOC_FIRST_PANEL_CONTENT = getLoc("FIRST_PANEL_CONTENT");

    private final String LOC_SECOND_EXAMPLE_HEADER = getLoc("SECOND_EXAMPLE_HEADER");
    private final String LOC_SECOND_PANEL = getLoc("SECOND_PANEL");
    private final String LOC_SECOND_PANEL_POSITION = getLoc("SECOND_PANEL_POSITION");
    private final String LOC_SECOND_PANEL_OPEN = getLoc("SECOND_PANEL_OPEN");
    private final String LOC_SECOND_PANEL_CLOSE = getLoc("SECOND_PANEL_CLOSE");
    private final String LOC_SECOND_PANEL_HEADER = getLoc("SECOND_PANEL_HEADER");
    private final String LOC_SECOND_PANEL_CONTENT = getLoc("SECOND_PANEL_CONTENT");

    /**
     * Tests the first panel. First it checks that the panel is invisible
     * initially, then opens the panel and verifies it is visible. Then it
     * checks the content of the panel's header and its content. In the end it
     * closes the panel.
     */
    @Test
    public void testFirstPanelOpenClose() {
        scrollIntoView(LOC_FIRST_EXAMPLE_HEADER, true);

        assertFalse(isDisplayed(LOC_FIRST_PANEL), "The first panel should not be visible.");

        selenium.click(LOC_FIRST_PANEL_OPEN);
        assertTrue(isDisplayed(LOC_FIRST_PANEL), "The first panel should be visible.");

        String tmp = selenium.getText(LOC_FIRST_PANEL_HEADER);
        assertEquals(tmp, MSG_FIRST_PANEL_HEADER, "The header of the first panel is wrong.");
        tmp = selenium.getText(LOC_FIRST_PANEL_CONTENT);
        assertEquals(tmp, MSG_FIRST_PANEL_CONTENT, "The content of the first panel is wrong.");

        selenium.click(LOC_FIRST_PANEL_CLOSE);
        assertFalse(isDisplayed(LOC_FIRST_PANEL), "The first panel should not be visible.");
    }

    /**
     * Tests moving the panel. It opens the panel, gets panel's position on the
     * page, and moves it 20px right, 20px down. Then it verifies the position,
     * closes the panel, and opens it again. Then it verifies that the panel is
     * locatet on the new position.
     */
    @Test
    public void testFirstPanelMove() {
        scrollIntoView(LOC_FIRST_EXAMPLE_HEADER, true);
        selenium.click(LOC_FIRST_PANEL_OPEN);

        int firstX = Integer.parseInt(getStyle(LOC_FIRST_PANEL_POSITION, "left").replace("px", ""));
        int firstY = Integer.parseInt(getStyle(LOC_FIRST_PANEL_POSITION, "top").replace("px", ""));

        for (int i = 0; i < 40; i++) {
            if (i % 2 == 0) {
                selenium.dragAndDrop(LOC_FIRST_PANEL_HEADER, "+1,0");
            } else {
                selenium.dragAndDrop(LOC_FIRST_PANEL_HEADER, "0,+1");
            }
        }

        int secondX = Integer.parseInt(getStyle(LOC_FIRST_PANEL_POSITION, "left").replace("px", ""));
        int secondY = Integer.parseInt(getStyle(LOC_FIRST_PANEL_POSITION, "top").replace("px", ""));

        assertEquals(secondX, firstX + 20, "Panel should move 20px right.");
        assertEquals(secondY, firstY + 20, "Panel should move 20px down.");

        selenium.click(LOC_FIRST_PANEL_CLOSE);
        selenium.click(LOC_FIRST_PANEL_OPEN);

        int thirdX = Integer.parseInt(getStyle(LOC_FIRST_PANEL_POSITION, "left").replace("px", ""));
        int thirdY = Integer.parseInt(getStyle(LOC_FIRST_PANEL_POSITION, "top").replace("px", ""));

        assertEquals(thirdX, secondX, "Panel shouldn't move after closing and opening.");
        assertEquals(thirdY, secondY, "Panel shouldn't move after closing and opening.");
    }

    /**
     * Tests the second panel. First it checks that the panel is invisible
     * initially, then opens the panel and verifies it is visible. Then it
     * checks the content of the panel's header and its content. In the end it
     * closes the panel.
     */
    @Test
    public void testSecondPanelOpenClose() {
        scrollIntoView(LOC_SECOND_EXAMPLE_HEADER, true);
        assertFalse(isDisplayed(LOC_SECOND_PANEL), "The second panel should not be visible.");

        selenium.click(LOC_SECOND_PANEL_OPEN);
        assertTrue(isDisplayed(LOC_SECOND_PANEL), "The second panel should be visible.");

        String tmp = selenium.getText(LOC_SECOND_PANEL_HEADER);
        assertEquals(tmp, MSG_SECOND_PANEL_HEADER, "The header of the second panel is wrong.");

        tmp = selenium.getText(LOC_SECOND_PANEL_CONTENT);
        assertTrue(tmp.contains(MSG_SECOND_PANEL_CONTENT), "The content of the second panel is wrong.");

        selenium.click(LOC_SECOND_PANEL_CLOSE);
        assertFalse(isDisplayed(LOC_SECOND_PANEL), "The second panel should not be visible.");
    }

    /**
     * Tests moving the panel. It opens the panel, gets panel's position on the
     * page, and moves it 20px right, 20px down. Then it verifies the position,
     * closes the panel, and opens it again. Then it verifies that the panel is
     * locatet on the new position.
     */
    @Test
    public void testSecondPanelMove() {
        scrollIntoView(LOC_SECOND_EXAMPLE_HEADER, true);
        selenium.click(LOC_SECOND_PANEL_OPEN);

        int firstX = Integer.parseInt(getStyle(LOC_SECOND_PANEL_POSITION, "left").replace("px", ""));
        int firstY = Integer.parseInt(getStyle(LOC_SECOND_PANEL_POSITION, "top").replace("px", ""));

        for (int i = 0; i < 40; i++) {
            if (i % 2 == 0) {
                selenium.dragAndDrop(LOC_SECOND_PANEL_HEADER, "+1,0");
            } else {
                selenium.dragAndDrop(LOC_SECOND_PANEL_HEADER, "0,+1");
            }
        }

        int secondX = Integer.parseInt(getStyle(LOC_SECOND_PANEL_POSITION, "left").replace("px", ""));
        int secondY = Integer.parseInt(getStyle(LOC_SECOND_PANEL_POSITION, "top").replace("px", ""));

        assertEquals(secondX, firstX + 20, "Panel should move 20px right.");
        assertEquals(secondY, firstY + 20, "Panel should move 20px down.");

        selenium.click(LOC_SECOND_PANEL_CLOSE);
        selenium.click(LOC_SECOND_PANEL_OPEN);

        int thirdX = Integer.parseInt(getStyle(LOC_SECOND_PANEL_POSITION, "left").replace("px", ""));
        int thirdY = Integer.parseInt(getStyle(LOC_SECOND_PANEL_POSITION, "top").replace("px", ""));

        assertEquals(thirdX, secondX, "Panel shouldn't move after closing and opening.");
        assertEquals(thirdY, secondY, "Panel shouldn't move after closing and opening.");
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks 5 lines of source code.
     */
    @Test
    public void testFirstSource() {
        String[] strings = new String[] { "<rich:modalPanel id=\"panel\" width=\"350\" height=\"100\">",
                "<f:facet name=\"header\">",
                "<h:graphicImage value=\"/images/modal/close.png\" styleClass=\"hidelink\" id=\"hidelink\"/>",
                "<h:outputText value=\"This panel is called using Component Control Component\"></h:outputText>",
                "<h:outputLink value=\"#\" id=\"link\">", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks 5 lines of source code.
     */
    @Test
    public void testSecondSource() {
        String[] strings = new String[] { "function getRightTop(ref) {",
                "<h:outputText value=\"Modal Panel Title\" />",
                "<p>Any JSF content might be inside the panel. In case of using ",
                "<a href=\"#\" onclick=\"#{rich:component('mp')}.hide()\">hide this panel</a>:",
                "<f:verbatim>&#35;</f:verbatim>{rich:component('mp')}.hide()</p>", };

        abstractTestSource(2, "View Source", strings);
    }

    /**
     * Loads the page containing the calendar component.
     */
    protected void loadPage() {
        openComponent("Modal Panel");
    }
}
