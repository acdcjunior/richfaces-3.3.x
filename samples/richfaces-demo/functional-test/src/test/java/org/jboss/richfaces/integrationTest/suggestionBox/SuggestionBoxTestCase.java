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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.Range;
import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.SeleniumException;

/**
 * Test case that tests the suggestion box.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>, <a
 *         href="mailto:lfryc@redhat.com">Lukas Fryc</a>
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
    private final String LOC_FIRST_SHADOW = getLoc("FIRST_SHADOW");
    private final String LOC_FIRST_NOTHING_FOUND = getLoc("FIRST_NOTHING_FOUND");
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
        selenium.type(LOC_FIRST_INPUT, "at");
        selenium.fireEvent(LOC_FIRST_INPUT, Event.KEYDOWN);
        
        waitForElement(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 0));

        selenium.click(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 0));

        // select Madison
        selenium.type(LOC_FIRST_INPUT, selenium.getValue(LOC_FIRST_INPUT) + ",ma");
        selenium.fireEvent(LOC_FIRST_INPUT, Event.KEYDOWN);

        waitForTextEquals(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 0), "Wisconsin");
        selenium.click(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 0));

        String text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 0));
        assertEquals(text, "Georgia", format(MSG_TABLE_PREFORMATTED, 1, 0));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 1));
        assertEquals(text, "Atlanta", format(MSG_TABLE_PREFORMATTED, 1, 1));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 2));
        assertEquals(text, "Augusta", format(MSG_TABLE_PREFORMATTED, 1, 2));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 3));
        assertEquals(text, "Columbus", format(MSG_TABLE_PREFORMATTED, 1, 3));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 0));
        assertEquals(text, "Wisconsin", format(MSG_TABLE_PREFORMATTED, 2, 0));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 1));
        assertEquals(text, "Milwaukee", format(MSG_TABLE_PREFORMATTED, 2, 1));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 2));
        assertEquals(text, "Madison", format(MSG_TABLE_PREFORMATTED, 2, 2));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 3));
        assertEquals(text, "Green Bay", format(MSG_TABLE_PREFORMATTED, 2, 3));
    }

    /**
     * Tests inputting two cities separated by square brackets. First it chooses
     * Atlanta and Madison. Then it checks all items in the table on the right.
     */
    @Test
    public void testSquareBracketsSeparatedTowns() {
        scrollIntoView(LOC_FIRST_INPUT, true);

        // select Atlanta
        selenium.type(LOC_FIRST_INPUT, "[at");
        selenium.fireEvent(LOC_FIRST_INPUT, Event.KEYDOWN);
        
        waitForElement(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 0));

        selenium.click(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 0));
        selenium.type(LOC_FIRST_INPUT, selenium.getValue(LOC_FIRST_INPUT) + "]");
        selenium.fireEvent(LOC_FIRST_INPUT, Event.KEYDOWN);

        // select Madison
        selenium.type(LOC_FIRST_INPUT, selenium.getValue(LOC_FIRST_INPUT) + "[ma");
        selenium.fireEvent(LOC_FIRST_INPUT, Event.KEYDOWN);
        
        waitForTextEquals(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 0), "Wisconsin");
        selenium.click(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 0));
        selenium.type(LOC_FIRST_INPUT, selenium.getValue(LOC_FIRST_INPUT) + "]");
        selenium.fireEvent(LOC_FIRST_INPUT, Event.KEYDOWN);

        String text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 0));
        assertEquals(text, "Georgia", format(MSG_TABLE_PREFORMATTED, 1, 0));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 1));
        assertEquals(text, "Atlanta", format(MSG_TABLE_PREFORMATTED, 1, 1));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 2));
        assertEquals(text, "Augusta", format(MSG_TABLE_PREFORMATTED, 1, 2));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 1, 3));
        assertEquals(text, "Columbus", format(MSG_TABLE_PREFORMATTED, 1, 3));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 0));
        assertEquals(text, "Wisconsin", format(MSG_TABLE_PREFORMATTED, 2, 0));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 1));
        assertEquals(text, "Milwaukee", format(MSG_TABLE_PREFORMATTED, 2, 1));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 2));
        assertEquals(text, "Madison", format(MSG_TABLE_PREFORMATTED, 2, 2));

        text = selenium.getText(format(LOC_FIRST_TOWN_TABLE_PREFORMATTED, 2, 3));
        assertEquals(text, "Green Bay", format(MSG_TABLE_PREFORMATTED, 2, 3));
    }

    /**
     * Tests typing in a non-existing city ('aaa'). It checks that the string
     * "No capitals found" is shown.
     */
    @Test
    public void testNonExisting() {
        scrollIntoView(LOC_FIRST_INPUT, true);

        // select aaa
        selenium.type(LOC_FIRST_INPUT, "aaa");
        selenium.fireEvent(LOC_FIRST_INPUT, Event.KEYDOWN);

        waitForElement(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 0));
        String text = selenium.getText(format(LOC_FIRST_SUGGESTION_BOX_PREFORMATTED, 0));
        assertEquals(text, "No capitals found", MSG_PREFORMATTEDO_CAPITALS_FOUND);
    }

    /**
     * Tests the "Border" slider. It tests values 5, 0, 3, 7, 1
     */
	@Test
	public void testBorder() {
		scrollIntoView(LOC_FIRST_INPUT, true);

		StepRange range = new StepRange(1, 5, 1);
		for (int i : new int[] { 5, 0, 3, 7, 1 }) {
			selenium.type(LOC_FIRST_BORDER_INPUT, String.valueOf(i));
			selenium.fireEvent(LOC_FIRST_BORDER_INPUT, Event.BLUR);
			String result = range.getRoundedValue(i).toString();
			Wait.failWith(format(MSG_SUGGESTION_BOX_BORDER_PREFORMATTED, String.valueOf(i))).until(
					new StyleCondition(LOC_FIRST_BORDER_STYLE, "border-top-width", result));
			assertEquals(getStyleValue(LOC_FIRST_BORDER_STYLE, "border-right-width"), result);
			assertEquals(getStyleValue(LOC_FIRST_BORDER_STYLE, "border-bottom-width"), result);
			assertEquals(getStyleValue(LOC_FIRST_BORDER_STYLE, "border-left-width"), result);
			assertEquals(selenium.getValue(LOC_FIRST_BORDER_INPUT), result);
		}
	}

    /**
     * Tests the "Width" slider. It tests values 350, 149, 176, 351, 200, 150, 500
     */
    @Test
    public void testWidth() {
        scrollIntoView(LOC_FIRST_INPUT, true);

        StepRange range = new StepRange(150, 350, 50);
        final String locInput = LOC_FIRST_WIDTH_INPUT;
		for (int i : new int[] { 350, 149, 176, 351, 200, 150, 500 }) {
			selenium.type(locInput, String.valueOf(i));
			selenium.fireEvent(locInput, Event.BLUR);
			String result = range.getRoundedValue(i).toString();
			Wait.failWith(format(MSG_SUGGESTION_BOX_WIDTH_PREFORMATTED, String.valueOf(i))).until(
					new StyleCondition(LOC_FIRST_WIDTH_STYLE, "width", result));
			assertEquals(selenium.getValue(locInput), result);
		}
    }

    /**
     * Tests the "Height" slider. It tests values 300, 99, 176, 301, 200, 100, 400
     */
    @Test
    public void testHeight() {
        scrollIntoView(LOC_FIRST_INPUT, true);

        StepRange range = new StepRange(100, 300, 50);
        final String locInput = LOC_FIRST_HEIGHT_INPUT;
		for (int i : new int[] { 300, 99, 176, 301, 200, 100, 400 }) {
			selenium.type(locInput, String.valueOf(i));
			selenium.fireEvent(locInput, Event.BLUR);
			String result = range.getRoundedValue(i).toString();
			Wait.failWith(format(MSG_SUGGESTION_BOX_HEIGHT_PREFORMATTED, String.valueOf(i))).until(
					new StyleCondition(LOC_FIRST_HEIGHT_STYLE, "height", result));
			assertEquals(selenium.getValue(locInput), result);
		}
    }

    /**
     * Tests the "Shadow Depth" slider. It tests values 7, 4, 2, 6, 3, 0, 8. It test
     * both top and left offset.
     */
	@Test
	public void testShadowDepth() {
		scrollIntoView(LOC_FIRST_INPUT, true);

		StepRange range = new StepRange(3, 6, 1);
		final String locInput = LOC_FIRST_SHADOW_DEPTH_INPUT;
		for (int i : new int[] { 7, 4, 2, 6, 3, 0, 8 }) {
			selenium.type(locInput, String.valueOf(i));
			selenium.fireEvent(locInput, Event.BLUR);
			for (int j = 1; j <= 5; j++) {
				try {
					selenium.type(LOC_FIRST_INPUT, StringUtils.repeat(String.valueOf(i), j));
					selenium.fireEvent(LOC_FIRST_INPUT, Event.KEYDOWN);
					selenium.waitForCondition(format("jqFind('{0}').is(':visible')", removeJQueryPrefix(LOC_FIRST_SHADOW)), "5000");
					break;
				} catch (SeleniumException e) {
					if (j < 5 && e.getMessage().startsWith("Timed out")) {
						continue;
					}
					throw e;
				}
			}
			String result = range.getRoundedValue(i).toString();
			assertEquals(getStyleValue(LOC_FIRST_SHADOW, "top"), result, format(MSG_SUGGESTION_BOX_SHADOW_DEPTH_PREFORMATTED, i));
			assertEquals(getStyleValue(LOC_FIRST_SHADOW, "left"), result, format(MSG_SUGGESTION_BOX_SHADOW_DEPTH_PREFORMATTED, i));
			assertEquals(selenium.getValue(locInput), result);
			selenium.click(LOC_FIRST_NOTHING_FOUND);
			selenium.waitForCondition(format("jqFind('{0}').is(':hidden')", removeJQueryPrefix(LOC_FIRST_SHADOW)), "5000");
			Wait.timeout(500).waitForTimeout();
		}
	}

    /**
     * Tests the "Shadow Opacity" slider. It tests values 10, 5, 0, 9, 1, -1, 12.
     */
    @Test
    public void testShadowOpacity() {
        scrollIntoView(LOC_FIRST_INPUT, true);
        
        StepRange range = new StepRange(1, 9, 1);
		final String locInput = LOC_FIRST_SHADOW_OPACITY_INPUT;
		for (int i : new int[] { 10, 5, 0, 9, 1, -1, 12 }) {
			selenium.type(locInput, String.valueOf(i));
			selenium.fireEvent(locInput, Event.BLUR);
			selenium.type(LOC_FIRST_INPUT, String.valueOf(i));
			selenium.fireEvent(LOC_FIRST_INPUT, Event.KEYDOWN);
			String result = range.getRoundedValue(i).toString();
			Wait.failWith(format(MSG_SUGGESTION_BOX_SHADOW_OPACITY_PREFORMATTED, String.valueOf(i))).until(
					new StyleCondition(LOC_FIRST_SHADOW, "opacity", format("0.{0}", result)));
			assertEquals(selenium.getValue(locInput), result);
		}
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

//        int count = selenium.getXpathCount(LOC_SECOND_SUGGESTION_LINES).intValue();
        int count = getJQueryCount(LOC_SECOND_SUGGESTION_LINES);
        assertEquals(count, 50, MSG_COUNT_OF_ALL_SUGGESTIONS);
    }

    /**
     * Tests inputting one city in the second example. It presses 'a' and clicks
     * on the second suggestion.
     */
    @Test
    public void testSecondExampleOneCity() {
        scrollIntoView(LOC_SECOND_INPUT, true);

        selenium.type(LOC_SECOND_INPUT, "a");
        selenium.fireEvent(LOC_SECOND_INPUT, Event.KEYDOWN);
        
        waitForElement(format(LOC_SECOND_SUGGESTION_BOX_PREFORMATTED, 1));
        selenium.click(format(LOC_SECOND_SUGGESTION_BOX_PREFORMATTED, 1)); // Augusta

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

        selenium.type(LOC_SECOND_INPUT, "a");
        selenium.fireEvent(LOC_SECOND_INPUT, Event.KEYDOWN);
        
        waitForElement(format(LOC_SECOND_SUGGESTION_BOX_PREFORMATTED, 1));
        selenium.click(format(LOC_SECOND_SUGGESTION_BOX_PREFORMATTED, 1)); // Augusta

        selenium.type(LOC_SECOND_INPUT, selenium.getValue(LOC_SECOND_INPUT) + ",m");
        selenium.fireEvent(LOC_SECOND_INPUT, Event.KEYDOWN);
        
        waitForTextEquals(format(LOC_SECOND_SUGGESTION_BOX_PREFORMATTED, 2), "Madison");
        selenium.click(format(LOC_SECOND_SUGGESTION_BOX_PREFORMATTED, 2)); // Madison

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
	 * Condition for Wait.until(Condition) which waits for the item specified by
	 * locator becomes in given style given value
	 */
	private class StyleCondition implements Condition {
		private String locator;
		private String style;
		private String value;
		private String actualValue;

		/**
		 * @param locator
		 *            locator of item which we will be testing for becoming
		 *            given style in given value
		 * @param style
		 *            tested on item given by locator to equality with given
		 *            value
		 * @param value
		 *            of given style which we are testing on item specified by
		 *            locator
		 */
		public StyleCondition(String locator, String style, String value) {
			this.locator = locator;
			this.style = style;
			this.value = value;
		}

		public boolean isTrue() {
			actualValue = getStyleValue(locator, style);
			return actualValue.equals(value);
		}
		
		@Override
		public String toString() {
			return actualValue;
		}
	}

	/**
	 * Pulls out the value of the specified attribute from the specified
	 * location.
	 * 
	 * @param loc
	 *            an attribute locator
	 * @param attr
	 *            an style value what we want to pull out
	 * @return the value of the subattribute
	 */
	private String getStyleValue(String loc, String attr) {
		return getStyle(loc, attr).replaceFirst("px", "");
	}
	
	/**
	 * Class representing Range of numbers with defined offset between them (step)
	 * 
	 * Can test the number for presence in range and round the number into number, which is in the range.
	 */
	private class StepRange extends Range {

		private int minimum;
		private int maximum;
		private int step;
		
		public StepRange(int minimum, int maximum, int step) {
			this.minimum = minimum;
			this.maximum = maximum;
			this.step = step;
		}
		
		@Override
		public boolean containsNumber(Number number) {
			return number.intValue() == getRoundedValue(number).intValue();
		}

		@Override
		public Number getMaximumNumber() {
			return maximum;
		}

		@Override
		public Number getMinimumNumber() {
			return minimum;
		}
		
		public Number getRoundedValue(Number number) {
			if (number.intValue() < minimum)
				return minimum;
			if (number.intValue() > maximum)
				return maximum;
			int delta = number.intValue() % step;
			if (delta != 0) {
				if (delta > (step / 2)) {
					return number.intValue() - delta + step;
				} else {
					return number.intValue() - delta;
				}
			}
			return number;
		}
	}

	/**
	 * Loads the page containing the calendar component.
	 */
    protected void loadPage() {
        openComponent("Suggestion Box");
    }
    
    
}
