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

package org.jboss.richfaces.integrationTest.toolBar;

import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests tool bar.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class ToolBarTestCase extends AbstractSeleniumRichfacesTestCase {

    // messages
    private final String MSG_ITEM_CLASS_ATTRIBUTE = getMsg("ITEM_CLASS_ATTRIBUTE");
    private final String MSG_GROUP_SEPARATOR = getMsg("GROUP_SEPARATOR");
    private final String MSG_ITEM_SEPARATOR = getMsg("GROUP_SEPARATOR");
    private final String MSG_ITEM_IMAGE_S = getMsg("ITEM_IMAGE_S");
    private final String MSG_THERE_IS_NO_GROUP_SEPARATOR = getMsg("THERE_IS_NO_GROUP_SEPARATOR");
    private final String MSG_THERE_IS_GROUP_SEPARATOR = getMsg("THERE_IS_GROUP_SEPARATOR");
    private final String MSG_THERE_IS_NO_ITEM_SEPARATOR = getMsg("THERE_IS_NO_ITEM_SEPARATOR");
    private final String MSG_THERE_IS_ITEM_SEPARATOR = getMsg("THERE_IS_ITEM_SEPARATOR");
    private final String MSG_GROUP_SEPARATOR_S = getMsg("GROUP_SEPARATOR_S");
    private final String MSG_ITEM_SEPARATOR_S = getMsg("ITEM_SEPARATOR_S");

    // locators
    private final String LOC_EXAMPLE_N_HEADER = getLoc("EXAMPLE_N_HEADER");
    private final String LOC_FIRST_TOOLBAR_ITEM_N = getLoc("FIRST_TOOLBAR_ITEM_N");
    private final String LOC_SECOND_TOOLBAR_ITEM_N = getLoc("SECOND_TOOLBAR_ITEM_N");
    private final String LOC_THIRD_TOOLBAR_GROUP_SEPARATOR_N = getLoc("THIRD_TOOLBAR_GROUP_SEPARATOR_N");
    private final String LOC_THIRD_TOOLBAR_ITEM_SEPARATOR_N = getLoc("THIRD_TOOLBAR_ITEM_SEPARATOR_N");
    private final String LOC_GROUP_SEPARATOR_IMG = getLoc("GROUP_SEPARATOR_IMG");
    private final String LOC_ITEM_SEPARATOR_IMG = getLoc("ITEM_SEPARATOR_IMG");

    /**
     * Tests the first tool bar. It checks the class attribute of three items
     * from toolbar, align attribute of the group separator, and style attribute
     * of the big separator. Then it checks that three chosen items display
     * necessary image.
     */
    @Test
    public void testFirstToolBar() {
        scrollIntoView(format(LOC_EXAMPLE_N_HEADER, 0), true);

        assertTrue(belongsClass("rich-toolbar-item", format(LOC_FIRST_TOOLBAR_ITEM_N, 0)), MSG_ITEM_CLASS_ATTRIBUTE);
        assertTrue(belongsClass("rich-toolbar-item", format(LOC_FIRST_TOOLBAR_ITEM_N, 4)), MSG_ITEM_CLASS_ATTRIBUTE);
        assertTrue(belongsClass("rich-toolbar-item", format(LOC_FIRST_TOOLBAR_ITEM_N, 8)), MSG_ITEM_CLASS_ATTRIBUTE);

        String text = selenium.getAttribute(format(LOC_FIRST_TOOLBAR_ITEM_N, 3) + "@align");
        assertTrue(text.contains("center"), MSG_GROUP_SEPARATOR);

        text = selenium.getAttribute(format(LOC_FIRST_TOOLBAR_ITEM_N, 0) + " > img@src");
        assertTrue(text.contains("create_doc.gif"), format(MSG_ITEM_IMAGE_S, "create_doc.gif"));

        text = selenium.getAttribute(format(LOC_FIRST_TOOLBAR_ITEM_N, 1) + " > img@src");
        assertTrue(text.contains("create_folder.gif"), format(MSG_ITEM_IMAGE_S, "create_folder.gif"));

        text = selenium.getAttribute(format(LOC_FIRST_TOOLBAR_ITEM_N, 5) + " > img@src");
        assertTrue(text.contains("save_as.gif"), format(MSG_ITEM_IMAGE_S, "save_as.gif"));
    }

    /**
     * Tests the second tool bar. It checks the class attribute of three items
     * from toolbar, align attribute of the item separator, and style attribute
     * of the big separator. Then it checks that two chosen items display
     * necessary image.
     */
    @Test
    public void testSecondToolBar() {
        scrollIntoView(format(LOC_EXAMPLE_N_HEADER, 1), true);

        assertTrue(belongsClass("rich-toolbar-item", format(LOC_SECOND_TOOLBAR_ITEM_N, 0)), MSG_ITEM_CLASS_ATTRIBUTE);
        assertTrue(belongsClass("rich-toolbar-item", format(LOC_SECOND_TOOLBAR_ITEM_N, 3)), MSG_ITEM_CLASS_ATTRIBUTE);
        assertTrue(belongsClass("rich-toolbar-item", format(LOC_SECOND_TOOLBAR_ITEM_N, 6)), MSG_ITEM_CLASS_ATTRIBUTE);

        String text = selenium.getAttribute(format(LOC_SECOND_TOOLBAR_ITEM_N, 2) + "@align");
        assertTrue(text.contains("center"), MSG_ITEM_SEPARATOR);

        text = selenium.getAttribute(format(LOC_SECOND_TOOLBAR_ITEM_N, 0) + " > img@src");
        assertTrue(text.contains("edit.gif"), format(MSG_ITEM_IMAGE_S, "edit.gif"));

        text = selenium.getAttribute(format(LOC_SECOND_TOOLBAR_ITEM_N, 3) + " > img@src");
        assertTrue(text.contains("find.gif"), format(MSG_ITEM_IMAGE_S, "find.gif"));
    }

    /**
     * Tests all types of group separators in the third tool bar.
     */
    @Test
    public void testThirdToolBarGroupSeparators() {
        scrollIntoView(format(LOC_EXAMPLE_N_HEADER, 2), true);
        
        // select line
        selenium.click(format(LOC_THIRD_TOOLBAR_GROUP_SEPARATOR_N, 1));
        
        Wait.failWith(format(MSG_GROUP_SEPARATOR_S, "LineSeparatorImage")).until(new Condition() {
            public boolean isTrue() {
                return selenium.getAttribute(LOC_GROUP_SEPARATOR_IMG + "@src").contains("LineSeparatorImage");
            }
        });
        
        assertTrue(selenium.isElementPresent(LOC_GROUP_SEPARATOR_IMG), MSG_THERE_IS_NO_GROUP_SEPARATOR);
        
        // select grid
        selenium.click(format(LOC_THIRD_TOOLBAR_GROUP_SEPARATOR_N, 2));
        
        Wait.failWith(format(MSG_GROUP_SEPARATOR_S, "GridSeparatorImage")).until(new Condition() {
            public boolean isTrue() {
                return selenium.getAttribute(LOC_GROUP_SEPARATOR_IMG + "@src").contains("GridSeparatorImage");
            }
        });
        
        assertTrue(selenium.isElementPresent(LOC_GROUP_SEPARATOR_IMG), MSG_THERE_IS_NO_GROUP_SEPARATOR);
        
        // select disc
        selenium.click(format(LOC_THIRD_TOOLBAR_GROUP_SEPARATOR_N, 3));
        
        Wait.failWith(format(MSG_GROUP_SEPARATOR_S, "DotSeparatorImage")).until(new Condition() {
            public boolean isTrue() {
                return selenium.getAttribute(LOC_GROUP_SEPARATOR_IMG + "@src").contains("DotSeparatorImage");
            }
        });
        
        assertTrue(selenium.isElementPresent(LOC_GROUP_SEPARATOR_IMG), MSG_THERE_IS_NO_GROUP_SEPARATOR);
        
        // select square
        selenium.click(format(LOC_THIRD_TOOLBAR_GROUP_SEPARATOR_N, 4));
        
        Wait.failWith(format(MSG_GROUP_SEPARATOR_S, "SquareSeparatorImage")).until(new Condition() {
            public boolean isTrue() {
                return selenium.getAttribute(LOC_GROUP_SEPARATOR_IMG + "@src").contains("SquareSeparatorImage");
            }
        });
        
        assertTrue(selenium.isElementPresent(LOC_GROUP_SEPARATOR_IMG), MSG_THERE_IS_NO_GROUP_SEPARATOR);
        
        // select none
        selenium.click(format(LOC_THIRD_TOOLBAR_GROUP_SEPARATOR_N, 5));
        Wait.failWith(MSG_THERE_IS_GROUP_SEPARATOR).until(new Condition() {
            public boolean isTrue() {
                return !selenium.isElementPresent(LOC_GROUP_SEPARATOR_IMG);
            }
        });
    }

    /**
     * Tests all types of item separators in the third tool bar.
     */
    @Test
    public void testToolBarItemSeparators() {
        scrollIntoView(format(LOC_EXAMPLE_N_HEADER, 2), true);
     
        // select line
        selenium.click(format(LOC_THIRD_TOOLBAR_ITEM_SEPARATOR_N, 1));
        
        Wait.failWith(format(MSG_ITEM_SEPARATOR_S, "LineSeparatorImage")).until(new Condition() {
            public boolean isTrue() {
                return selenium.getAttribute(LOC_ITEM_SEPARATOR_IMG + "@src").contains("LineSeparatorImage");
            }
        });
        
        assertTrue(selenium.isElementPresent(LOC_ITEM_SEPARATOR_IMG), MSG_THERE_IS_NO_ITEM_SEPARATOR);
        
        // select grid
        selenium.click(format(LOC_THIRD_TOOLBAR_ITEM_SEPARATOR_N, 2));

        Wait.failWith(format(MSG_ITEM_SEPARATOR_S, "GridSeparatorImage")).until(new Condition() {
            public boolean isTrue() {
                return selenium.getAttribute(LOC_ITEM_SEPARATOR_IMG + "@src").contains("GridSeparatorImage");
            }
        });
        
        assertTrue(selenium.isElementPresent(LOC_ITEM_SEPARATOR_IMG), MSG_THERE_IS_NO_ITEM_SEPARATOR);
        
        // select disc
        selenium.click(format(LOC_THIRD_TOOLBAR_ITEM_SEPARATOR_N, 3));

        Wait.failWith(format(MSG_ITEM_SEPARATOR_S, "DotSeparatorImage")).until(new Condition() {
            public boolean isTrue() {
                return selenium.getAttribute(LOC_ITEM_SEPARATOR_IMG + "@src").contains("DotSeparatorImage");
            }
        });

        assertTrue(selenium.isElementPresent(LOC_ITEM_SEPARATOR_IMG), MSG_THERE_IS_NO_ITEM_SEPARATOR);

        // select square
        selenium.click(format(LOC_THIRD_TOOLBAR_ITEM_SEPARATOR_N, 4));

        Wait.failWith(format(MSG_ITEM_SEPARATOR_S, "SquareSeparatorImage")).until(new Condition() {
            public boolean isTrue() {
                return selenium.getAttribute(LOC_ITEM_SEPARATOR_IMG + "@src").contains("SquareSeparatorImage");
            }
        });

        assertTrue(selenium.isElementPresent(LOC_ITEM_SEPARATOR_IMG), MSG_THERE_IS_NO_ITEM_SEPARATOR);

        // select none
        selenium.click(format(LOC_THIRD_TOOLBAR_ITEM_SEPARATOR_N, 5));
        Wait.failWith(MSG_THERE_IS_ITEM_SEPARATOR).until(new Condition() {
            public boolean isTrue() {
                return !selenium.isElementPresent(LOC_ITEM_SEPARATOR_IMG);
            }
        });
    }

    /**
     * Tests the "View Source" in the first example. It checks that the source
     * code is not visible, clicks on the link, and checks 8 lines of source
     * code.
     */
    @Test
    public void testFirstExampleSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "margin-bottom: -4px;", "<rich:toolBar height=\"26\" itemSeparator=\"grid\">", "<rich:toolBarGroup>",
                "<h:graphicImage value=\"/images/icons/create_doc.gif\" styleClass=\"pic\"/>",
                "<h:graphicImage value=\"/images/icons/create_folder.gif\" styleClass=\"pic\"/>",
                "<h:graphicImage value=\"/images/icons/save.gif\" styleClass=\"pic\"/>",
                "<h:graphicImage value=\"/images/icons/filter.gif\" styleClass=\"pic\"/>", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Tests the "View Source" in the second example. It checks that the source
     * code is not visible, clicks on the link, and checks 8 lines of source
     * code.
     */
    @Test
    public void testSecondExampleSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<rich:toolBar height=\"34\" itemSeparator=\"line\">", "<rich:toolBarGroup>",
                "<h:graphicImage id=\"edit\" value=\"/images/icons/edit.gif\" />",
                "<h:outputLabel value=\"Edit\" for=\"edit\" />", "<rich:toolBarGroup location=\"right\">",
                "<h:inputText styleClass=\"barsearch\" />",
                "<h:commandButton styleClass=\"barsearchbutton\" onclick=\"return false;\"  value=\"Search\" />", };

        abstractTestSource(2, "View Source", strings);
    }

    /**
     * Tests the "View Source" in the third example. It checks that the source
     * code is not visible, clicks on the link, and checks 10 lines of source
     * code.
     */
    @Test
    public void testThirdExampleSource() {
        String[] strings = new String[] {
                "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<h:panelGrid columns=\"3\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin-bottom : 4px\">",
                "<h:panelGrid columns=\"8\">", "<a4j:commandLink value=\"Line\" reRender=\"bar\">",
                "<a4j:actionparam name=\"gs\" value=\"line\" assignTo=\"#{tbBean.groupSeparator}\" />",
                "<h:panelGroup style=\"padding-left : 4px\">",
                "<rich:panel bodyClass=\"rich-laguna-panel-no-header\">",
                "<h:outputText value=\"Group Item Separator:\" />",
                "<rich:toolBar id=\"bar\" height=\"30\" itemSeparator=\"#{tbBean.groupSeparator}\">",
                "<rich:toolBarGroup itemSeparator=\"#{tbBean.groupItemSeparator}\">", };

        abstractTestSource(3, "View Source", strings);
    }

    /**
     * Loads the page containing the component.
     */
    protected void loadPage() {
        openComponent("Tool Bar");
    }
}
