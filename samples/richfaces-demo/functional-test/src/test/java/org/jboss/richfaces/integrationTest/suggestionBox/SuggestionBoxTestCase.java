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

package org.jboss.richfaces.integrationTest.suggestionBox;

import static org.testng.Assert.assertEquals;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests the suggestion box.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class SuggestionBoxTestCase extends AbstractSeleniumRichfacesTestCase {

    // messages
    private final String MSG_TABLE_PREFORMATTED = getMsg("TABLE_PREFORMATTED");
    private final String MSG_PREFORMATTEDO_CAPITALS_FOUND = getMsg("NO_CAPITALS_FOUND");
    private final String MSG_SUGGESTION_BOX_BORDER_PREFORMATTED = getMsg("SUGGESTION_BOX_BORDER_PREFORMATTED");
    private final String MSG_SUGGESTION_BOX_WIDTH_PREFORMATTED = getMsg("SUGGESTION_BOX_WIDTH_PREFORMATTED");
    private final String MSG_SUGGESTION_BOX_HEIGHT_PREFORMATTED = getMsg("SUGGESTION_BOX_HEIGHT_PREFORMATTED");
    private final String MSG_SUGGESTION_BOX_SHADOW_DEPTH_PREFORMATTED = getMsg("SUGGESTION_BOX_SHADOW_DEPTH_PREFORMATTED");
    private final String MSG_SUGGESTION_BOX_SHADOW_OPACITY_PREFORMATTED = getMsg("SUGGESTION_BOX_SHADOW_OPACITY_PREFORMATTED");
    private final String MSG_SUGGESTION_BOX_CELLPADDING_PREFORMATTED = getMsg("SUGGESTION_BOX_CELLPADDING_PREFORMATTED");
    private final String MSG_COUNT_OF_ALL_SUGGESTIONS = getMsg("COUNT_OF_ALL_SUGGESTIONS");
    private final String MSG_AUGUSTA_MAINE = getMsg("AUGUSTA_MAINE");
    private final String MSG_AUGUSTA_MADISON_MAINE_WISCONSIN = getMsg("AUGUSTA_MADISON_MAINE_WISCONSIN");

    // locators
    private final String LOC_FIRST_SUGGESTION_BOX_PREFORMATTED = getLoc("FIRST_SUGGESTION_BOX_PREFORMATTED");
    private final String LOC_FIRST_TOWN_TABLE_PREFORMATTED = getLoc("FIRST_TOWN_TABLE_PREFORMATTED");

    private final String LOC_FIRST_INPUT = getLoc("FIRST_INPUT");
    private final String LOC_FIRST_BORDER_INPUT = getLoc("FIRST_BORDER_INPUT");
    private final String LOC_FIRST_WIDTH_INPUT = getLoc("FIRST_WIDTH_INPUT");
    private final String LOC_FIRST_HEIGHT_INPUT = getLoc("FIRST_HEIGHT_INPUT");
    private final String LOC_FIRST_SHADOW_DEPTH_INPUT = getLoc("FIRST_SHADOW_DEPTH_INPUT");
    private final String LOC_FIRST_SHADOW_OPACITY_INPUT = getLoc("FIRST_SHADOW_OPACITY_INPUT");
    private final String LOC_FIRST_CELLPADDING_INPUT = getLoc("FIRST_CELLPADDING_INPUT");

    private final String LOC_FIRST_BORDER_STYLE = getLoc("FIRST_BORDER_STYLE");
    private final String LOC_FIRST_WIDTH_STYLE = getLoc("FIRST_WIDTH_STYLE");
    private final String LOC_FIRST_HEIGHT_STYLE = getLoc("FIRST_HEIGHT_STYLE");
    private final String LOC_FIRST_SHADOW_DEPTH_STYLE = getLoc("FIRST_SHADOW_DEPTH_STYLE");
    private final String LOC_FIRST_SHADOW_OPACITY_STYLE = getLoc("FIRST_SHADOW_OPACITY_STYLE");
    private final String LOC_FIRST_CELLPADDING = getLoc("FIRST_CELLPADDING");

    private final String LOC_SECOND_INPUT = getLoc("SECOND_INPUT");
    private final String LOC_SECOND_BUTTON = getLoc("SECOND_BUTTON");
    private final String LOC_SECOND_SUGGESTION_LINES = getLoc("SECOND_SUGGESTION_LINES");
    private final String LOC_SECOND_SUGGESTION_BOX_PREFORMATTED = getLoc("SECOND_SUGGESTION_BOX_PREFORMATTED");
    private final String LOC_SECOND_STATE = getLoc("SECOND_STATE");

    /**
     * Tests inputting two cities separated by a comma. First it chooses Atlanta
     * and Madison. Then it checks all items in the table on the right.
     */
    @Test
    public void testCommaSeparatedTowns() {
        scrollIntoView(LOC_FIRST_INPUT, true);

        // select Atlanta
        selenium.typeKeys(LOC_FIRST_INPUT, "at");
        waitForElement(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 1));

        selenium.click(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 1));

        // select Madison
        selenium.typeKeys(LOC_FIRST_INPUT, ",ma");

        waitForTextEquals(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 1), "MadisonWisconsin");
        selenium.click(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 1));

        String text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 1));
        assertEquals(text, "Georgia", format(MSG_TABLE_PREFORMATTED, 1, 1));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 2));
        assertEquals(text, "Atlanta", format(MSG_TABLE_PREFORMATTED, 1, 2));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 3));
        assertEquals(text, "Augusta", format(MSG_TABLE_PREFORMATTED, 1, 3));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 4));
        assertEquals(text, "Columbus", format(MSG_TABLE_PREFORMATTED, 1, 4));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 1));
        assertEquals(text, "Wisconsin", format(MSG_TABLE_PREFORMATTED, 2, 1));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 2));
        assertEquals(text, "Milwaukee", format(MSG_TABLE_PREFORMATTED, 2, 2));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 3));
        assertEquals(text, "Madison", format(MSG_TABLE_PREFORMATTED, 2, 3));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 4));
        assertEquals(text, "Green Bay", format(MSG_TABLE_PREFORMATTED, 2, 4));
    }

    /**
     * Tests inputting two cities separated by square brackets. First it chooses
     * Atlanta and Madison. Then it checks all items in the table on the right.
     */
    @Test
    public void testSquareBracketsSeparatedTowns() {
        scrollIntoView(LOC_FIRST_INPUT, true);

        // select Atlanta
        selenium.typeKeys(LOC_FIRST_INPUT, "[at");
        waitForElement(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 1));

        selenium.click(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 1));
        selenium.typeKeys(LOC_FIRST_INPUT, "]");

        // select Madison
        selenium.typeKeys(LOC_FIRST_INPUT, "[ma");
        waitForTextEquals(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 1), "MadisonWisconsin");
        selenium.click(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 1));
        selenium.typeKeys(LOC_FIRST_INPUT, "]");

        String text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 1));
        assertEquals(text, "Georgia", format(MSG_TABLE_PREFORMATTED, 1, 1));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 2));
        assertEquals(text, "Atlanta", format(MSG_TABLE_PREFORMATTED, 1, 2));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 3));
        assertEquals(text, "Augusta", format(MSG_TABLE_PREFORMATTED, 1, 3));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 4));
        assertEquals(text, "Columbus", format(MSG_TABLE_PREFORMATTED, 1, 4));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 1));
        assertEquals(text, "Wisconsin", format(MSG_TABLE_PREFORMATTED, 2, 1));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 2));
        assertEquals(text, "Milwaukee", format(MSG_TABLE_PREFORMATTED, 2, 2));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 3));
        assertEquals(text, "Madison", format(MSG_TABLE_PREFORMATTED, 2, 3));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 4));
        assertEquals(text, "Green Bay", format(MSG_TABLE_PREFORMATTED, 2, 4));
    }

    /**
     * Tests typing in a non-existing city ('aaa'). It checks that the string
     * "No capitals found" is shown.
     */
    @Test
    public void testNonExisting() {
        scrollIntoView(LOC_FIRST_INPUT, true);

        // select aaa
        selenium.typeKeys(LOC_FIRST_INPUT, "aaa");

        waitForElement(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 1));
        String text = selenium.getText(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 1));
        assertEquals(text, "No capitals found", MSG_PREFORMATTEDO_CAPITALS_FOUND);
    }

    /**
     * Tests the "Border" slider. It tests values 3, 5, and 7.
     */
    @Test
    public void testBorder() {
        scrollIntoView(LOC_FIRST_INPUT, true);

        selenium.type(LOC_FIRST_BORDER_INPUT, "3");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_BORDER_STYLE, "border-width") == 3;
            }
        });

        int width = getValue(LOC_FIRST_BORDER_STYLE, "border-width");
        assertEquals(width, 3, format(MSG_SUGGESTION_BOX_BORDER_PREFORMATTED, 3));

        selenium.type(LOC_FIRST_BORDER_INPUT, "5");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_BORDER_STYLE, "border-width") == 5;
            }
        });

        width = getValue(LOC_FIRST_BORDER_STYLE, "border-width");
        assertEquals(width, 5, format(MSG_SUGGESTION_BOX_BORDER_PREFORMATTED, 5));

        selenium.type(LOC_FIRST_BORDER_INPUT, "7"); // 7 -> 5
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_BORDER_STYLE, "border-width") == 5;
            }
        });

        width = getValue(LOC_FIRST_BORDER_STYLE, "border-width");
        assertEquals(width, 5, format(MSG_SUGGESTION_BOX_BORDER_PREFORMATTED, 7));
    }

    /**
     * Tests the "Width" slider. It tests values 150, 350, 400, and 176.
     */
    @Test
    public void testWidth() {
        scrollIntoView(LOC_FIRST_INPUT, true);

        selenium.type(LOC_FIRST_WIDTH_INPUT, "150");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_WIDTH_STYLE, "width") == 150;
            }
        });

        int width = getValue(LOC_FIRST_WIDTH_STYLE, "width");
        assertEquals(width, 150, format(MSG_SUGGESTION_BOX_WIDTH_PREFORMATTED, 150));

        selenium.type(LOC_FIRST_WIDTH_INPUT, "350");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_WIDTH_STYLE, "width") == 350;
            }
        });

        width = getValue(LOC_FIRST_WIDTH_STYLE, "width");
        assertEquals(width, 350, format(MSG_SUGGESTION_BOX_WIDTH_PREFORMATTED, 350));

        selenium.type(LOC_FIRST_WIDTH_INPUT, "400"); // 400 -> 350
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_WIDTH_STYLE, "width") == 350;
            }
        });

        width = getValue(LOC_FIRST_WIDTH_STYLE, "width");
        assertEquals(width, 350, format(MSG_SUGGESTION_BOX_WIDTH_PREFORMATTED, 400));

        selenium.type(LOC_FIRST_WIDTH_INPUT, "176"); // 176 -> 200
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_WIDTH_STYLE, "width") == 200;
            }
        });

        width = getValue(LOC_FIRST_WIDTH_STYLE, "width");
        assertEquals(width, 200, format(MSG_SUGGESTION_BOX_WIDTH_PREFORMATTED, 176));
    }

    /**
     * Tests the "Height" slider. It tests values 100, 300, 400, and 176.
     */
    @Test
    public void testHeight() {
        scrollIntoView(LOC_FIRST_INPUT, true);

        selenium.type(LOC_FIRST_HEIGHT_INPUT, "100");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_HEIGHT_STYLE, "height") == 100;
            }
        });

        int height = getValue(LOC_FIRST_HEIGHT_STYLE, "height");
        assertEquals(height, 100, format(MSG_SUGGESTION_BOX_HEIGHT_PREFORMATTED, 100));

        selenium.type(LOC_FIRST_HEIGHT_INPUT, "300");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_HEIGHT_STYLE, "height") == 300;
            }
        });

        height = getValue(LOC_FIRST_HEIGHT_STYLE, "height");
        assertEquals(height, 300, format(MSG_SUGGESTION_BOX_HEIGHT_PREFORMATTED, 300));

        selenium.type(LOC_FIRST_HEIGHT_INPUT, "400"); // 400 -> 300
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_HEIGHT_STYLE, "height") == 300;
            }
        });

        height = getValue(LOC_FIRST_HEIGHT_STYLE, "height");
        assertEquals(height, 300, format(MSG_SUGGESTION_BOX_HEIGHT_PREFORMATTED, 400));

        selenium.type(LOC_FIRST_HEIGHT_INPUT, "176"); // 176 -> 200
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_HEIGHT_STYLE, "height") == 200;
            }
        });

        height = getValue(LOC_FIRST_HEIGHT_STYLE, "height");
        assertEquals(height, 200, format(MSG_SUGGESTION_BOX_HEIGHT_PREFORMATTED, 176));
    }

    /**
     * Tests the "Shadow Depth" slider. It tests values 3, 5, 6, and 7. It test
     * both top and left offset.
     */
    @Test
    public void testShadowDepth() {
        scrollIntoView(LOC_FIRST_INPUT, true);

        selenium.type(LOC_FIRST_SHADOW_DEPTH_INPUT, "3");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "top") == 3;
            }
        });

        int top = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "top");
        assertEquals(top, 3, format(MSG_SUGGESTION_BOX_SHADOW_DEPTH_PREFORMATTED, 3));
        int left = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "left");
        assertEquals(left, 3, format(MSG_SUGGESTION_BOX_SHADOW_DEPTH_PREFORMATTED, 3));

        selenium.type(LOC_FIRST_SHADOW_DEPTH_INPUT, "5");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "top") == 5;
            }
        });

        top = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "top");
        assertEquals(top, 5, format(MSG_SUGGESTION_BOX_SHADOW_DEPTH_PREFORMATTED, 5));
        left = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "left");
        assertEquals(left, 5, format(MSG_SUGGESTION_BOX_SHADOW_DEPTH_PREFORMATTED, 5));

        selenium.type(LOC_FIRST_SHADOW_DEPTH_INPUT, "6");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "top") == 6;
            }
        });

        top = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "top");
        assertEquals(top, 6, format(MSG_SUGGESTION_BOX_SHADOW_DEPTH_PREFORMATTED, 6));
        left = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "left");
        assertEquals(left, 6, format(MSG_SUGGESTION_BOX_SHADOW_DEPTH_PREFORMATTED, 6));

        selenium.type(LOC_FIRST_SHADOW_DEPTH_INPUT, "7"); // 7 -> 6
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "top") == 6;
            }
        });

        top = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "top");
        assertEquals(top, 6, format(MSG_SUGGESTION_BOX_SHADOW_DEPTH_PREFORMATTED, 7));
        left = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "left");
        assertEquals(left, 6, format(MSG_SUGGESTION_BOX_SHADOW_DEPTH_PREFORMATTED, 7));
    }

    /**
     * Tests the "Shadow Opacity" slider. It tests values 1, 5, 9, and 11.
     */
    @Test
    public void testShadowOpacity() {
        scrollIntoView(LOC_FIRST_INPUT, true);

        selenium.type(LOC_FIRST_SHADOW_OPACITY_INPUT, "1");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getOpacity(LOC_FIRST_SHADOW_OPACITY_STYLE) == 0.1;
            }
        });

        double opacity = getOpacity(LOC_FIRST_SHADOW_OPACITY_STYLE);
        assertEquals(opacity, 0.1, format(MSG_SUGGESTION_BOX_SHADOW_OPACITY_PREFORMATTED, 1));

        selenium.type(LOC_FIRST_SHADOW_OPACITY_INPUT, "5");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getOpacity(LOC_FIRST_SHADOW_OPACITY_STYLE) == 0.5;
            }
        });

        opacity = getOpacity(LOC_FIRST_SHADOW_OPACITY_STYLE);
        assertEquals(opacity, 0.5, format(MSG_SUGGESTION_BOX_SHADOW_OPACITY_PREFORMATTED, 5));

        selenium.type(LOC_FIRST_SHADOW_OPACITY_INPUT, "9");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getOpacity(LOC_FIRST_SHADOW_OPACITY_STYLE) == 0.9;
            }
        });

        opacity = getOpacity(LOC_FIRST_SHADOW_OPACITY_STYLE);
        assertEquals(opacity, 0.9, format(MSG_SUGGESTION_BOX_SHADOW_OPACITY_PREFORMATTED, 9));

        selenium.type(LOC_FIRST_SHADOW_OPACITY_INPUT, "11"); // 11 -> 9
        Wait.until(new Condition() {
            public boolean isTrue() {
                return getOpacity(LOC_FIRST_SHADOW_OPACITY_STYLE) == 0.9;
            }
        });

        opacity = getOpacity(LOC_FIRST_SHADOW_OPACITY_STYLE);
        assertEquals(opacity, 0.9, format(MSG_SUGGESTION_BOX_SHADOW_OPACITY_PREFORMATTED, 11));
    }

    /**
     * Tests the "Cellpadding" slider. It tests values 1, 10, 20, and 25.
     */
    @Test
    public void testCellpadding() {
        scrollIntoView(LOC_FIRST_INPUT, true);

        selenium.type(LOC_FIRST_CELLPADDING_INPUT, "1");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return Integer.parseInt(selenium.getAttribute(LOC_FIRST_CELLPADDING)) == 1;
            }
        });

        int cellpadding = Integer.parseInt(selenium.getAttribute(LOC_FIRST_CELLPADDING));
        assertEquals(cellpadding, 1, format(MSG_SUGGESTION_BOX_CELLPADDING_PREFORMATTED, 1));

        selenium.type(LOC_FIRST_CELLPADDING_INPUT, "10");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return Integer.parseInt(selenium.getAttribute(LOC_FIRST_CELLPADDING)) == 10;
            }
        });

        cellpadding = Integer.parseInt(selenium.getAttribute(LOC_FIRST_CELLPADDING));
        assertEquals(cellpadding, 10, format(MSG_SUGGESTION_BOX_CELLPADDING_PREFORMATTED, 10));

        selenium.type(LOC_FIRST_CELLPADDING_INPUT, "20");
        Wait.until(new Condition() {
            public boolean isTrue() {
                return Integer.parseInt(selenium.getAttribute(LOC_FIRST_CELLPADDING)) == 20;
            }
        });

        cellpadding = Integer.parseInt(selenium.getAttribute(LOC_FIRST_CELLPADDING));
        assertEquals(cellpadding, 20, format(MSG_SUGGESTION_BOX_CELLPADDING_PREFORMATTED, 20));

        selenium.type(LOC_FIRST_CELLPADDING_INPUT, "25"); // 25 -> 20
        Wait.until(new Condition() {
            public boolean isTrue() {
                return Integer.parseInt(selenium.getAttribute(LOC_FIRST_CELLPADDING)) == 20;
            }
        });

        cellpadding = Integer.parseInt(selenium.getAttribute(LOC_FIRST_CELLPADDING));
        assertEquals(cellpadding, 20, format(MSG_SUGGESTION_BOX_CELLPADDING_PREFORMATTED, 25));
    }

    /**
     * Tests the button with arrow in the second example. It checks that there
     * are 50 suggestions.
     */
    @Test
    void testSecondExampleButton() {
        scrollIntoView(LOC_SECOND_INPUT, true);

        selenium.click(LOC_SECOND_BUTTON);
        waitForElement(LOC_SECOND_SUGGESTION_LINES);

        int count = selenium.getXpathCount(LOC_SECOND_SUGGESTION_LINES).intValue();
        assertEquals(count, 50, MSG_COUNT_OF_ALL_SUGGESTIONS);
    }

    /**
     * Tests inputting one city in the second example. It presses 'a' and clicks
     * on the second suggestion.
     */
    @Test
    public void testSecondExampleOneCity() {
        scrollIntoView(LOC_SECOND_INPUT, true);

        selenium.typeKeys(LOC_SECOND_INPUT, "a");
        waitForElement(format(LOC_SECOND_SUGGESTION_BOX_PREFORMATTED, 2));
        selenium.click(format(LOC_SECOND_SUGGESTION_BOX_PREFORMATTED, 2)); // Augusta

        String state = selenium.getText(LOC_SECOND_STATE);
        assertEquals(state, "Maine", MSG_AUGUSTA_MAINE);
    }

    /**
     * Tests inputting two cities separated by a comma in the second example. It
     * presses 'a', clicks on the second suggestion, presses ',' and 'm' and
     * clicks on the third suggestion (Augusta and Madison). Then it checks that
     * states belonging to these cities are shown below the combo box.
     */
    @Test
    public void testSecondExampleMoreCities() {
        scrollIntoView(LOC_SECOND_INPUT, true);

        selenium.typeKeys(LOC_SECOND_INPUT, "a");
        waitForElement(format(LOC_SECOND_SUGGESTION_BOX_PREFORMATTED, 2));
        selenium.click(format(LOC_SECOND_SUGGESTION_BOX_PREFORMATTED, 2)); // Augusta

        selenium.typeKeys(LOC_SECOND_INPUT, ",m");
        waitForTextEquals(format(LOC_SECOND_SUGGESTION_BOX_PREFORMATTED, 3), "Madison");
        selenium.click(format(LOC_SECOND_SUGGESTION_BOX_PREFORMATTED, 3)); // Madison

        Wait.failWith(MSG_AUGUSTA_MADISON_MAINE_WISCONSIN).until(new Condition() {
            public boolean isTrue() {
                return selenium.getText(LOC_SECOND_STATE).equals("Maine,Wisconsin");
            }
        });
    }

    /**
     * Tests the "View Page Source" in the first example. It checks that the
     * source code is not visible, clicks on the link, and checks 10 lines of
     * source code.
     */
    @Test
    public void testFirstExamplePageSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<script type=\"text/javascript\">//<![CDATA[",
                "\"<tr><td>State</td><td>1st City</td><td>2nd City</td><td>3rd City</td></tr>\" +",
                "vertical-align: top;",
                "<h:panelGrid columns=\"2\" columnClasses=\"sb_test_column1,sb_test_column2\" width=\"100%\">",
                "<rich:suggestionbox id=\"suggestionBoxId\" for=\"text\" tokens=\",[]\"",
                " rules=\"#{suggestionBox.rules}\"",
                "suggestionAction=\"#{capitalsBean.autocomplete}\" var=\"result\"",
                "height=\"#{suggestionBox.height}\"", "nothingLabel=\"No capitals found\" columnClasses=\"center\"", };

        abstractTestSource(1, "View Page Source", strings);
    }

    /**
     * Tests the "View CapitalsBean.java Source" in the first example. It checks
     * that the source code is not visible, clicks on the link, and checks 8
     * lines of source code.
     */
    @Test
    public void testFirstExampleBeanSource() {
        String[] strings = new String[] {
                "package org.richfaces.demo.capitals;",
                "import javax.faces.FacesException;",
                "public class CapitalsBean {",
                "public List<Capital> autocomplete(Object suggest) {",
                "if ((elem.getName() != null && elem.getName().toLowerCase().indexOf(pref.toLowerCase()) == 0) || \"\".equals(pref))",
                "URL rulesUrl = getClass().getResource(\"capitals-rules.xml\");",
                "digester.parse(getClass().getResourceAsStream(\"capitals.xml\"));",
                "public ArrayList<Capital> getCapitals() {", };

        abstractTestSource(1, "View CapitalsBean.java Source", strings);
    }

    /**
     * Tests the "View Source" in the second example. It checks that the source
     * code is not visible, clicks on the link, and checks 8 lines of source
     * code.
     */
    @Test
    public void testSecondExamplePageSource() {
        String[] strings = new String[] {
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">",
                "<h:form id=\"form\">",
                "<h:panelGrid columns=\"2\">",
                "<rich:suggestionbox height=\"200\" width=\"200\"",
                "usingSuggestObjects=\"true\"",
                "onobjectchange=\"printObjectsSelected(#{rich:element('objects')}, #{rich:component('suggestion')});\"",
                "suggestionAction=\"#{capitalsBean.autocomplete}\" var=\"cap\"",
                "for=\"statesinput\" fetchValue=\"#{cap.name}\" id=\"suggestion\" tokens=\",\">", };

        abstractTestSource(2, "View Source", strings);
    }

    /**
     * Pulls out the value of the specified attribute from the specified
     * location.
     * 
     * @param loc
     *            an attribute locator
     * @param attr
     *            an 'subattribute' whose value we want (e.g. <div
     *            style="left: 4px;"/>, then loc=//div@style and attr=left
     * @return the value of the subattribute
     */
    private int getValue(String loc, String attr) {
        String tmp = selenium.getAttribute(loc);
        int firstIdx = tmp.indexOf(attr) + 2 + attr.length();
        int secondIdx = tmp.indexOf("px;", firstIdx);
        return Integer.parseInt(tmp.substring(firstIdx, secondIdx));
    }

    /**
     * Pulls out opacity from the specified attribute.
     * 
     * @param loc
     *            an attribute locator
     * @return the value of opacity
     */
    private double getOpacity(String loc) {
        String tmp = selenium.getAttribute(loc);
        int firstIdx = tmp.indexOf("opacity") + 9;
        int secondIdx = tmp.indexOf(";", firstIdx);
        return Double.parseDouble(tmp.substring(firstIdx, secondIdx));
    }

    /**
     * Loads the page containing the calendar component.
     */
    @SuppressWarnings("unused")
    @BeforeMethod
    private void loadPage() {
        openComponent("Suggestion Box");
    }
}
