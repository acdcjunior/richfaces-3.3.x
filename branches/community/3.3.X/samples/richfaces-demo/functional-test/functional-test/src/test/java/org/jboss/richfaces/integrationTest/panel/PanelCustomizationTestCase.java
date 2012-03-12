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
    private final String MSG_CUST_FIRST_HEADER = getMsg("CUST_FIRST_HEADER");
    private final String MSG_CUST_FIRST_CLASS = getMsg("CUST_FIRST_CLASS");
    private final String MSG_CUST_SECOND_HEADER = getMsg("CUST_SECOND_HEADER");
    private final String MSG_CUST_SECOND_CLASS = getMsg("CUST_SECOND_CLASS");
    private final String MSG_CUST_THIRD_CLASS_1 = getMsg("CUST_THIRD_CLASS_1");
    private final String MSG_CUST_THIRD_CLASS_2 = getMsg("CUST_THIRD_CLASS_2");
    private final String MSG_CUST_FOURTH_HEADER = getMsg("CUST_FOURTH_HEADER");
    private final String MSG_CUST_FOURTH_CLASS = getMsg("CUST_FOURTH_CLASS");
    
    // locators
    private final String LOC_CUST_FIRST_PANEL_HEADER = getLoc("CUST_FIRST_PANEL_HEADER");
    private final String LOC_CUST_SECOND_PANEL_HEADER = getLoc("CUST_SECOND_PANEL_HEADER");
    private final String LOC_CUST_THIRD_PANEL_HEADER = getLoc("CUST_THIRD_PANEL_HEADER");
    private final String LOC_CUST_THIRD_PANEL_BODY = getLoc("CUST_THIRD_PANEL_BODY");
    private final String LOC_CUST_FOURTH_PANEL_BODY = getLoc("CUST_FOURTH_PANEL_BODY");
    private final String LOC_CUST_FOURTH_PANEL_HEADER = getLoc("CUST_FOURTH_PANEL_HEADER");
    private final String LOC_CUST_FIFTH_PANEL_BODY = getLoc("CUST_FIFTH_PANEL_BODY");
    private final String LOC_CUST_FIFTH_PANEL_HEADER = getLoc("CUST_FIFTH_PANEL_HEADER");

    /**
     * Tests first panel. It checks panel header's text and class attribute.
     */
    @Test
    public void testFirstPanel() {
        String text = selenium.getText(LOC_CUST_FIRST_PANEL_HEADER);
        assertEquals(text, MSG_CUST_FIRST_HEADER, "Header of the panel.");
        assertTrue(belongsClass(MSG_CUST_FIRST_CLASS, LOC_CUST_FIRST_PANEL_HEADER),
                "The \"class\" attribute of the header should contain \"rich-panel-header\".");
    }

    /**
     * Tests second panel. It checks panel header's text and class attribute.
     */
    @Test
    public void testSecondPanel() {
        String text = selenium.getText(LOC_CUST_SECOND_PANEL_HEADER);
        assertEquals(text, MSG_CUST_SECOND_HEADER, "Header of the panel.");
        assertTrue(belongsClass(MSG_CUST_SECOND_CLASS, LOC_CUST_SECOND_PANEL_HEADER),
                "The \"class\" attribute of the header should contain \"rich-panel-header\".");
    }

    /**
     * Tests third panel. It checks that both header and body are green.
     */
    @Test
    public void testThirdPanel() {
        assertTrue(belongsClass(MSG_CUST_THIRD_CLASS_1, LOC_CUST_THIRD_PANEL_HEADER),
                "Header of the third panel should be green -- its class attribute has to contain \"head2\".");
        assertTrue(belongsClass(MSG_CUST_THIRD_CLASS_2, LOC_CUST_THIRD_PANEL_BODY),
                "Body of the third panel should be green -- its class attribute has to contain \"body3\".");
    }

    /**
     * Tests fourth panel. It checks panel's header and that it is scrollable.
     */
    @Test
    public void testFourthPanel() {
        String text = selenium.getText(LOC_CUST_FOURTH_PANEL_HEADER);
        assertEquals(text, MSG_CUST_FOURTH_HEADER, "Header of the panel.");
        assertTrue(belongsClass(MSG_CUST_FOURTH_CLASS, LOC_CUST_FOURTH_PANEL_BODY),
                "Body of the fourth panel should be scrollable -- its class attribute has to contain \"body3\".");
    }

    /**
     * Tests fifth panel. It checks that the panel has no header.
     */
    @Test
    public void testFifthPanel() {
        boolean isPresent = selenium.isElementPresent(LOC_CUST_FIFTH_PANEL_HEADER);
        assertEquals(isPresent, false, "The fifth panel should have no header.");
        
        isPresent = selenium.isElementPresent(LOC_CUST_FIFTH_PANEL_BODY);
        assertEquals(isPresent, true, "The fifth panel should have body.");
    }

    /**
     * Loads the page containing the component.
     */
    protected void loadPage() {
        openComponent("Panel");
        openTab("Look Customization");
    }
}
