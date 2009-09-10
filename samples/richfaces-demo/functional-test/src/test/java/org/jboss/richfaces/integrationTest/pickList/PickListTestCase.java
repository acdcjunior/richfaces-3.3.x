/*
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
 */

package org.jboss.richfaces.integrationTest.pickList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case for pick list.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
// TODO check that buttons are enabled and disabled properly
// TODO implement tests for multiple files with shift and control key
// TODO check not only one table, but both
// TODO check the header of panel with capitals
public class PickListTestCase extends AbstractSeleniumRichfacesTestCase {

    // locators
    private final String LOC_FIRST_EXAMPLE_HEADER = getLoc("FIRST_EXAMPLE_HEADER");
    private final String LOC_FIRST_LEFT_ITEMS = getLoc("FIRST_LEFT_ITEMS");
    private final String LOC_FIRST_LEFT_FIRST_ITEM = getLoc("FIRST_LEFT_FIRST_ITEM");
    private final String LOC_FIRST_RIGHT_ITEMS = getLoc("FIRST_RIGHT_ITEMS");
    private final String LOC_FIRST_RIGHT_FIRST_ITEM = getLoc("FIRST_RIGHT_FIRST_ITEM");
    private final String LOC_FIRST_BUTTON_COPY_ALL = getLoc("FIRST_BUTTON_COPY_ALL");
    private final String LOC_FIRST_BUTTON_COPY = getLoc("FIRST_BUTTON_COPY");
    private final String LOC_FIRST_BUTTON_REMOVE = getLoc("FIRST_BUTTON_REMOVE");
    private final String LOC_FIRST_BUTTON_REMOVE_ALL = getLoc("FIRST_BUTTON_REMOVE_ALL");
    
    private final String LOC_SECOND_EXAMPLE_HEADER = getLoc("SECOND_EXAMPLE_HEADER");
    private final String LOC_SECOND_LEFT_ITEMS = getLoc("SECOND_LEFT_ITEMS");
    private final String LOC_SECOND_LEFT_FIRST_ITEM = getLoc("SECOND_LEFT_FIRST_ITEM");
    private final String LOC_SECOND_RIGHT_ITEMS = getLoc("SECOND_RIGHT_ITEMS");
    private final String LOC_SECOND_RIGHT_FIRST_ITEM = getLoc("SECOND_RIGHT_FIRST_ITEM");
    private final String LOC_SECOND_BUTTON_COPY_ALL = getLoc("SECOND_BUTTON_COPY_ALL");
    private final String LOC_SECOND_BUTTON_COPY = getLoc("SECOND_BUTTON_COPY");
    private final String LOC_SECOND_BUTTON_REMOVE = getLoc("SECOND_BUTTON_REMOVE");
    private final String LOC_SECOND_BUTTON_REMOVE_ALL = getLoc("SECOND_BUTTON_REMOVE_ALL");
    
    private final String LOC_SECOND_CHOSEN_OPTIONS_UL = getLoc("SECOND_CHOSEN_OPTIONS_UL");
    private final String LOC_SECOND_CHOSEN_OPTIONS_LI = getLoc("SECOND_CHOSEN_OPTIONS_LI");
    private final String LOC_SECOND_CHOSEN_OPTIONS_LI1 = getLoc("SECOND_CHOSEN_OPTIONS_LI1");
    
    /**
     * Test first pick list. It checks that there are some items on the left and no items on the right.
     */
    @Test
    public void testFirstPickList() {
        scrollIntoView(LOC_FIRST_EXAMPLE_HEADER, true);
        
        int count = selenium.getXpathCount(LOC_FIRST_LEFT_ITEMS).intValue();
        assertTrue(count > 0, "There are no lines in the upper left table.");

        count = selenium.getXpathCount(LOC_FIRST_RIGHT_ITEMS).intValue();
        assertEquals(count, 0, "There should be no lines in the upper right table.");
    }

    /**
     * Tests the button copy in the first example.
     */
    @Test
    public void testFirstCopySingleItem() {
        scrollIntoView(LOC_FIRST_EXAMPLE_HEADER, true);
        
        int count = selenium.getXpathCount(LOC_FIRST_LEFT_ITEMS).intValue();

        selenium.click(LOC_FIRST_LEFT_FIRST_ITEM);
        selenium.click(LOC_FIRST_BUTTON_COPY);

        int newCount = selenium.getXpathCount(LOC_FIRST_LEFT_ITEMS).intValue();
        assertEquals(newCount, count - 1, "There should be less lines in the left table.");
    }

    // @Test
    // public void testFirstCopyMultipleItemsShift() {
    // fail("TODO");
    // }
    //	
    // @Test
    // public void testFirstCopyMultipleItemsCtrl() {
    // fail("TODO");
    // }

    /**
     * Tests the button copy all in the first example.
     */
    @Test
    public void testFirstCopyAll() {
        scrollIntoView(LOC_FIRST_EXAMPLE_HEADER, true);
        
        int count = selenium.getXpathCount(LOC_FIRST_LEFT_ITEMS).intValue();

        selenium.click(LOC_FIRST_BUTTON_COPY_ALL);

        int newCount = selenium.getXpathCount(LOC_FIRST_LEFT_ITEMS).intValue();
        assertEquals(newCount, 0, "There should be no lines in the left table.");

        newCount = selenium.getXpathCount(LOC_FIRST_RIGHT_ITEMS).intValue();
        assertEquals(newCount, count, "All items from left table should be now in the right table.");
    }

    /**
     * Tests the button remove in the first example.
     */
    @Test
    public void testFirstRemoveSingleItem() {
        scrollIntoView(LOC_FIRST_EXAMPLE_HEADER, true);
        
        selenium.click(LOC_FIRST_BUTTON_COPY_ALL);

        int count = selenium.getXpathCount(LOC_FIRST_RIGHT_ITEMS).intValue();

        selenium.click(LOC_FIRST_RIGHT_FIRST_ITEM);
        selenium.click(LOC_FIRST_BUTTON_REMOVE);

        int newCount = selenium.getXpathCount(LOC_FIRST_RIGHT_ITEMS).intValue();
        assertEquals(newCount, count - 1, "There should be less lines in the right table.");
    }

    // @Test
    // public void testFirstRemoveMultipleItemsShift() {
    // fail("TODO");
    // }
    //	
    // @Test
    // public void testFirstRemoveMultipleItemsCtrl() {
    // fail("TODO");
    // }

    /**
     * Tests the button remove all in the first example.
     */
    @Test
    public void testFirstRemoveAll() {
        scrollIntoView(LOC_FIRST_EXAMPLE_HEADER, true);
        
        int count = selenium.getXpathCount(LOC_FIRST_LEFT_ITEMS).intValue();

        selenium.click(LOC_FIRST_BUTTON_COPY_ALL);
        selenium.click(LOC_FIRST_BUTTON_REMOVE_ALL);

        int newCount = selenium.getXpathCount(LOC_FIRST_RIGHT_ITEMS).intValue();
        assertEquals(newCount, 0, "There should be no lines in the right table.");

        newCount = selenium.getXpathCount(LOC_FIRST_LEFT_ITEMS).intValue();
        assertEquals(newCount, count, "All items from right table should be now in the left table.");
    }

    /**
     * Tests the second pick list. It checks that there is something on the left, nothing on the right, and "chosen options" is empty.
     */
    @Test
    public void testSecondPickList() {
        scrollIntoView(LOC_SECOND_EXAMPLE_HEADER, true);
        
        int count = selenium.getXpathCount(LOC_SECOND_LEFT_ITEMS).intValue();
        assertTrue(count > 0, "There are no lines in the lower left table.");

        count = selenium.getXpathCount(LOC_SECOND_RIGHT_ITEMS).intValue();
        assertEquals(count, 0, "There should be no lines in the lower right table.");

        boolean empty = !selenium.isElementPresent(LOC_SECOND_CHOSEN_OPTIONS_UL);
        assertTrue(empty, "Chosen options should not contain any items.");
    }

    /**
     * Tests the button copy in the second example.
     */
    @Test
    public void testSecondCopySingleItem() {
        scrollIntoView(LOC_SECOND_EXAMPLE_HEADER, true);
        
        int count = selenium.getXpathCount(LOC_SECOND_LEFT_ITEMS).intValue();

        selenium.click(LOC_SECOND_LEFT_FIRST_ITEM);
        selenium.click(LOC_SECOND_BUTTON_COPY);

        int newCount = selenium.getXpathCount(LOC_SECOND_LEFT_ITEMS).intValue();
        assertEquals(newCount, count - 1, "There should be less lines in the left table.");

        count = selenium.getXpathCount(LOC_SECOND_CHOSEN_OPTIONS_LI).intValue();
        assertEquals(count, 1, "There should be only one capital city.");

        String capital = selenium.getText(LOC_SECOND_CHOSEN_OPTIONS_LI1);
        assertEquals(capital, "Montgomery", "Capital of Alaska.");
    }

    // @Test
    // public void testSecondCopyMultipleItemsShift() {
    // fail("TODO");
    // }
    //	
    // @Test
    // public void testSecondCopyMultipleItemsCtrl() {
    // fail("TODO");
    // }

    /**
     * Tests the button copy all in the second example.
     */
    @Test
    public void testSecondCopyAll() {
        scrollIntoView(LOC_SECOND_EXAMPLE_HEADER, true);
      
        int count = selenium.getXpathCount(LOC_SECOND_LEFT_ITEMS).intValue();

        selenium.click(LOC_SECOND_BUTTON_COPY_ALL);

        int newCount = selenium.getXpathCount(LOC_SECOND_LEFT_ITEMS).intValue();
        assertEquals(newCount, 0, "There should be no lines in the left table.");

        newCount = selenium.getXpathCount(LOC_SECOND_RIGHT_ITEMS).intValue();
        assertEquals(newCount, count, "All items from left table should be now in the right table.");

        newCount = selenium.getXpathCount(LOC_SECOND_CHOSEN_OPTIONS_LI).intValue();
        assertEquals(newCount, count, "Not all capital cities were displayed in the panel.");
    }

    /**
     * Tests the button remove in the second example.
     */
    @Test
    public void testSecondRemoveSingleItem() {
        scrollIntoView(LOC_SECOND_EXAMPLE_HEADER, true);

        selenium.click(LOC_SECOND_BUTTON_COPY_ALL);

        int count = selenium.getXpathCount(LOC_SECOND_RIGHT_ITEMS).intValue();

        selenium.click(LOC_SECOND_RIGHT_FIRST_ITEM);
        selenium.click(LOC_SECOND_BUTTON_REMOVE);

        int newCount = selenium.getXpathCount(LOC_SECOND_RIGHT_ITEMS).intValue();
        assertEquals(newCount, count - 1, "There should be less lines in the right table.");

        newCount = selenium.getXpathCount(LOC_SECOND_CHOSEN_OPTIONS_LI).intValue();
        assertEquals(newCount, count - 1, "The capital city was not removed from panel.");
    }

    // @Test
    // public void testSecondRemoveMultipleItemsShift() {
    // fail("TODO");
    // }
    //	
    // @Test
    // public void testSecondRemoveMultipleItemsCtrl() {
    // fail("TODO");
    // }

    /**
     * Tests the button remove all in the second example.
     */
    @Test
    public void testSecondRemoveAll() {
        scrollIntoView(LOC_SECOND_EXAMPLE_HEADER, true);

        int count = selenium.getXpathCount(LOC_SECOND_LEFT_ITEMS).intValue();

        selenium.click(LOC_SECOND_BUTTON_COPY_ALL);
        selenium.click(LOC_SECOND_BUTTON_REMOVE_ALL);

        int newCount = selenium.getXpathCount(LOC_SECOND_RIGHT_ITEMS).intValue();
        assertEquals(newCount, 0, "There should be no lines in the right table.");

        newCount = selenium.getXpathCount(LOC_SECOND_LEFT_ITEMS).intValue();
        assertEquals(newCount, count, "All items from right table should be now in the left table.");

        count = selenium.getXpathCount(LOC_SECOND_CHOSEN_OPTIONS_LI).intValue();
        assertEquals(count, 0, "All capitals from the panel should be removed.");
    }

    /**
     * Tests the "View Source" in the first example. It checks that the source
     * code is not visible, clicks on the link, and checks 5 lines of source
     * code.
     */
    @Test
    public void testFirstPickListSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"", "<rich:pickList>",
                "<f:selectItem itemLabel=\"Option 1\" itemValue=\"1\"/>",
                "<f:selectItem itemLabel=\"Option 2\" itemValue=\"2\"/>",
                "<f:selectItem itemLabel=\"Option 3\" itemValue=\"3\"/>",
                "<f:selectItem itemLabel=\"Option 4\" itemValue=\"4\"/>",
                "<f:selectItem itemLabel=\"Option 5\" itemValue=\"5\"/>", "</rich:pickList>", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Tests the "View Source" in the second example. It checks that the source
     * code is not visible, clicks on the link, and checks 5 lines of source
     * code.
     */
    @Test
    public void testSecondPickListSource() {
        String[] strings = new String[] {
                "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<h:panelGrid columns=\"2\" columnClasses=\"top, top\">",
                "<rich:pickList value=\"#{pickListBean.result}\"> ",
                "<f:selectItems value=\"#{capitalsBean.capitalsOptions}\"/>",
                "<a4j:support event=\"onlistchanged\" reRender=\"result\"/>",
                "<rich:panel id=\"result\" bodyClass=\"pbody\">",
                "<f:facet name=\"header\">",
                "<h:outputText value=\"#{pickListBean.items} Options Choosen\"></h:outputText>",
                "<rich:dataList value=\"#{pickListBean.result}\" var=\"pickList\" rendered=\"#{pickListBean.items>0}\"> ",
                "<h:outputText value=\"#{pickList}\"/>", };

        abstractTestSource(2, "View Source", strings);
    }

    /**
     * Loads the needed page.
     */
    @SuppressWarnings("unused")
    @BeforeMethod
    private void loadPage() {
        openComponent("Pick List");
    }
}
