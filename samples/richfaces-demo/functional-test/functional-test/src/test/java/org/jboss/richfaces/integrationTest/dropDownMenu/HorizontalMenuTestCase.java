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

package org.jboss.richfaces.integrationTest.dropDownMenu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test horizontal version of drop down menu.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class HorizontalMenuTestCase extends AbstractSeleniumRichfacesTestCase {

    private final String LOC_FIRST_EXAMPLE_HEADER = getLoc("FIRST_EXAMPLE_HEADER");
    private final String LOC_FIRST_CURRENT_SELECTION = getLoc("FIRST_CURRENT_SELECTION");
    private final String LOC_FIRST_MENU = getLoc("FIRST_MENU");
    private final String LOC_FIRST_MENU_NEW = getLoc("FIRST_MENU_NEW");
    private final String LOC_FIRST_MENU_SAVE_ALL = getLoc("FIRST_MENU_SAVE_ALL");
    private final String LOC_FIRST_MENU_SEPARATOR = getLoc("FIRST_MENU_SEPARATOR");
    private final String LOC_FIRST_MENU_IMAGE = getLoc("FIRST_MENU_IMAGE");
    private final String LOC_FIRST_MENU_LINK_HOMEPAGE = getLoc("FIRST_MENU_LINK_HOMEPAGE");
    private final String LOC_FIRST_MENU_LINK_FORUM = getLoc("FIRST_MENU_LINK_FORUM");

    private final String MSG_FIRST_INITIAL_CURRENT_SELECTION = getMsg("FIRST_INITIAL_CURRENT_SELECTION");
    private final String MSG_FIRST_CURRENT_SELECTION_NEW = getMsg("FIRST_CURRENT_SELECTION_NEW");
    private final String MSG_FIRST_CURRENT_SELECTION_SAVE_ALL = getMsg("FIRST_CURRENT_SELECTION_SAVE_ALL");

    /**
     * Checks that menu is not shown at start and verifies that text in current
     * selection area is empty.
     */
    @Test
    public void testHorizontalMenu() {
        assertFalse(isDisplayed(LOC_FIRST_MENU), "Menu should not be expanded at start.");

        String text = selenium.getText(LOC_FIRST_CURRENT_SELECTION);
        assertEquals(text, MSG_FIRST_INITIAL_CURRENT_SELECTION, "Last menu action should be empty.");
    }

    /**
     * Tests the "New" menu item. It clicks the item and verifies that text is
     * shown in current selection.
     */
    @Test
    public void testMenuItem() {
        selenium.click(LOC_FIRST_MENU_NEW);

        Wait.failWith("Text shown in current selection.").until(new Condition() {
            public boolean isTrue() {
                return MSG_FIRST_CURRENT_SELECTION_NEW.equals(selenium.getText(LOC_FIRST_CURRENT_SELECTION));
            }
        });
    }

    /**
     * Tests menu group. It chooses "Save All" from the group "Save as...".
     */
    @Test
    public void testMenuGroup() {
        selenium.click(LOC_FIRST_MENU_SAVE_ALL);

        Wait.failWith("Text shown in current Selection.").until(new Condition() {
            public boolean isTrue() {
                return MSG_FIRST_CURRENT_SELECTION_SAVE_ALL.equals(selenium.getText(LOC_FIRST_CURRENT_SELECTION));
            }
        });
    }

    /**
     * Tests separator in menu.
     */
    @Test
    public void testSeparator() {
        assertTrue(belongsClass("rich-menu-separator", LOC_FIRST_MENU_SEPARATOR),
                "There should be a separator in the menu.");
    }

    /**
     * Tests icon next to menu item.
     */
    @Test
    public void testIcon() {
        int width = selenium.getElementWidth(LOC_FIRST_MENU_IMAGE).intValue();
        int height = selenium.getElementHeight(LOC_FIRST_MENU_IMAGE).intValue();

        assertTrue(width > 0, "Menu item \"File\" should have a visible icon (width > 0).");
        assertTrue(height > 0, "Menu item \"File\" should have a visible icon (height > 0).");
    }

    /**
     * Tests link to the RichFaces homepage.
     */
    @Test
    public void testLinkRFHomePage() {
        String location = selenium.getAttribute(LOC_FIRST_MENU_LINK_HOMEPAGE + "@href");
        assertTrue(location.contains("http://"), "Href has to contain http://");
    }

    /**
     * Tests link to the RichFaces forum.
     */
    @Test
    public void testLinkRFForum() {
        String location = selenium.getAttribute(LOC_FIRST_MENU_LINK_FORUM + "@href");
        assertTrue(location.contains("http://"), "Href has to contain http://");
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks 17 lines of source code.
     */
    @Test
    public void testHorizontalMenuSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"", "<h:form>",
                "<rich:toolBar>", "<rich:dropDownMenu>", "<f:facet name=\"label\"> ",
                "<h:graphicImage value=\"/images/icons/copy.gif\" styleClass=\"pic\"/>",
                "<rich:menuItem submitMode=\"ajax\" value=\"New\"",
                "action=\"#{ddmenu.doNew}\" icon=\"/images/icons/create_doc.gif\">",
                "<rich:menuGroup value=\"Save As...\">", "<rich:menuItem submitMode=\"ajax\" value=\"Save\" ",
                "action=\"#{ddmenu.doSave}\" icon=\"/images/icons/save.gif\" />",
                "<rich:menuItem submitMode=\"ajax\" value=\"Save All\"", "action=\"#{ddmenu.doSaveAll}\">",
                "<f:facet name=\"icon\">", "<h:graphicImage value=\"/images/icons/save_all.gif\" />",
                "<rich:menuSeparator id=\"menuSeparator11\" />",
                "<h:outputLink value=\"http://labs.jboss.com/jbossrichfaces/\">", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Loads the page containing needed component.
     */
    protected void loadPage() {
        openComponent("Drop Down Menu");
        scrollIntoView(LOC_FIRST_EXAMPLE_HEADER, true);
    }
}
