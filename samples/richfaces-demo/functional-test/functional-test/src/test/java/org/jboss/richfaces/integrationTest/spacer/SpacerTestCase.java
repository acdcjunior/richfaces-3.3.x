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

package org.jboss.richfaces.integrationTest.spacer;

import static org.testng.Assert.assertEquals;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests spacer.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class SpacerTestCase extends AbstractSeleniumRichfacesTestCase {

    // messages
    private final int MSG_FIRST_SPACER_HEIGHT = Integer.parseInt(getMsg("FIRST_SPACER_HEIGHT"));
    private final int MSG_FIRST_SPACER_WIDTH = Integer.parseInt(getMsg("FIRST_SPACER_WIDTH"));
    private final int MSG_SECOND_SPACER_HEIGHT = Integer.parseInt(getMsg("SECOND_SPACER_HEIGHT"));
    private final int MSG_SECOND_SPACER_WIDTH = Integer.parseInt(getMsg("SECOND_SPACER_WIDTH"));

    // locators
    private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");
    private final String LOC_FIRST_SPACER_HEIGHT = getLoc("FIRST_SPACER_HEIGHT");
    private final String LOC_FIRST_SPACER_WIDTH = getLoc("FIRST_SPACER_WIDTH");
    private final String LOC_SECOND_SPACER_HEIGHT = getLoc("SECOND_SPACER_HEIGHT");
    private final String LOC_SECOND_SPACER_WIDTH = getLoc("SECOND_SPACER_WIDTH");

    /**
     * Tests the first spacer. Verifies its width and height.
     */
    @Test
    public void testFirstSpacer() {
        int num = Integer.parseInt(selenium.getAttribute(LOC_FIRST_SPACER_HEIGHT));
        assertEquals(num, MSG_FIRST_SPACER_HEIGHT, "Height of the spacer (in pixels).");

        num = Integer.parseInt(selenium.getAttribute(LOC_FIRST_SPACER_WIDTH));
        assertEquals(num, MSG_FIRST_SPACER_WIDTH, "Width of the spacer (in pixels).");
    }

    /**
     * Tests the second spacer. Verifies its width and height.
     */
    @Test
    public void testSecondSpacer() {
        int num = Integer.parseInt(selenium.getAttribute(LOC_SECOND_SPACER_HEIGHT));
        assertEquals(num, MSG_SECOND_SPACER_HEIGHT, "Height of the spacer (in pixels).");

        num = Integer.parseInt(selenium.getAttribute(LOC_SECOND_SPACER_WIDTH));
        assertEquals(num, MSG_SECOND_SPACER_WIDTH, "Width of the spacer (in pixels).");
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks 5 lines of source code.
     */
    @Test
    public void testFirstExampleSource() {
        String[] strings = new String[] {
                "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "background-color: #{a4jSkin.panelBorderColor};",
                "There is a spacer 100x10<rich:spacer width=\"100\" height=\"10\" title=\"Here is a spacer...\"/>before this.",
                "<div class=\"div_near_spacer\" />",
                "<rich:spacer width=\"1\" height=\"5\" title=\"Here is a spacer...\"/>", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Loads the page containing the component.
     */
    protected void loadPage() {
        openComponent("Spacer");
        scrollIntoView(LOC_EXAMPLE_HEADER, true);
    }
}
