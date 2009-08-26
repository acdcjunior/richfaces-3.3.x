package org.jboss.richfaces.integrationTest.suggestionBox;

import static org.testng.Assert.assertEquals;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
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
	private final String MSG_COMPONENT_DESCRIPTION = getMsg("COMPONENT_DESCRIPTION");
	private final String MSG_TABLE_M_N = getMsg("TABLE_M_N");
	private final String MSG_NO_CAPITALS_FOUND = getMsg("NO_CAPITALS_FOUND");
	private final String MSG_SUGGESTION_BOX_BORDER = getMsg("SUGGESTION_BOX_BORDER");
	private final String MSG_SUGGESTION_BOX_WIDTH = getMsg("SUGGESTION_BOX_WIDTH");
	private final String MSG_SUGGESTION_BOX_HEIGHT = getMsg("SUGGESTION_BOX_HEIGHT");
	private final String MSG_SUGGESTION_BOX_SHADOW_DEPTH = getMsg("SUGGESTION_BOX_SHADOW_DEPTH");
	private final String MSG_SUGGESTION_BOX_SHADOW_OPACITY = getMsg("SUGGESTION_BOX_SHADOW_OPACITY");
	private final String MSG_SUGGESTION_BOX_CELLPADDING = getMsg("SUGGESTION_BOX_CELLPADDING");
	private final String MSG_COUNT_OF_ALL_SUGGESTIONS = getMsg("COUNT_OF_ALL_SUGGESTIONS");
	private final String MSG_AUGUSTA_MAINE = getMsg("AUGUSTA_MAINE");
	private final String MSG_AUGUSTA_MADISON_MAINE_WISCONSIN = getMsg("AUGUSTA_MADISON_MAINE_WISCONSIN");

	// locators
	private final String LOC_FIRST_SUGGESTION_BOX_N = getLoc("FIRST_SUGGESTION_BOX_N");
	private final String LOC_FIRST_TOWN_TABLE_M_N = getLoc("FIRST_TOWN_TABLE_M_N");

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
	private final String LOC_SECOND_SUGGESTION_BOX_N = getLoc("SECOND_SUGGESTION_BOX_N");
	private final String LOC_SECOND_STATE = getLoc("SECOND_STATE");

	/**
	 * Tests inputting two cities separated by a comma. First it chooses Atlanta and Madison. Then 
	 * it checks all items in the table on the right.
	 */
	@Test
	public void testCommaSeparatedTowns() {
		scrollIntoView(LOC_FIRST_INPUT, true);

		// select Atlanta
		selenium.typeKeys(LOC_FIRST_INPUT, "at");
		waitForElement(String.format(LOC_FIRST_SUGGESTION_BOX_N, 1));

		selenium.click(String.format(LOC_FIRST_SUGGESTION_BOX_N, 1));

		// select Madison
		selenium.typeKeys(LOC_FIRST_INPUT, ",ma");
		
		waitForTextEquals(String.format(LOC_FIRST_SUGGESTION_BOX_N, 1),
				"MadisonWisconsin");
		selenium.click(String.format(LOC_FIRST_SUGGESTION_BOX_N, 1));

		String text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N,
				1, 1));
		assertEquals(text, "Georgia", String.format(MSG_TABLE_M_N, 1, 1));

		text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N, 1, 2));
		assertEquals(text, "Atlanta", String.format(MSG_TABLE_M_N, 1, 2));

		text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N, 1, 3));
		assertEquals(text, "Augusta", String.format(MSG_TABLE_M_N, 1, 3));

		text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N, 1, 4));
		assertEquals(text, "Columbus", String.format(MSG_TABLE_M_N, 1, 4));

		text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N, 2, 1));
		assertEquals(text, "Wisconsin", String.format(MSG_TABLE_M_N, 2, 1));

		text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N, 2, 2));
		assertEquals(text, "Milwaukee", String.format(MSG_TABLE_M_N, 2, 2));

		text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N, 2, 3));
		assertEquals(text, "Madison", String.format(MSG_TABLE_M_N, 2, 3));

		text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N, 2, 4));
		assertEquals(text, "Green Bay", String.format(MSG_TABLE_M_N, 2, 4));
	}

	/**
	 * Tests inputting two cities separated by square brackets. First it chooses Atlanta and Madison. Then 
	 * it checks all items in the table on the right.
	 */
	@Test
	public void testSquareBracketsSeparatedTowns() {
		scrollIntoView(LOC_FIRST_INPUT, true);

		// select Atlanta
		selenium.typeKeys(LOC_FIRST_INPUT, "[at");
		waitForElement(String.format(LOC_FIRST_SUGGESTION_BOX_N, 1));

		selenium.click(String.format(LOC_FIRST_SUGGESTION_BOX_N, 1));
		selenium.typeKeys(LOC_FIRST_INPUT, "]");

		// select Madison
		selenium.typeKeys(LOC_FIRST_INPUT, "[ma");
		waitForTextEquals(String.format(LOC_FIRST_SUGGESTION_BOX_N, 1),
				"MadisonWisconsin");
		selenium.click(String.format(LOC_FIRST_SUGGESTION_BOX_N, 1));
		selenium.typeKeys(LOC_FIRST_INPUT, "]");
		
		String text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N,
				1, 1));
		assertEquals(text, "Georgia", String.format(MSG_TABLE_M_N, 1, 1));

		text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N, 1, 2));
		assertEquals(text, "Atlanta", String.format(MSG_TABLE_M_N, 1, 2));

		text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N, 1, 3));
		assertEquals(text, "Augusta", String.format(MSG_TABLE_M_N, 1, 3));

		text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N, 1, 4));
		assertEquals(text, "Columbus", String.format(MSG_TABLE_M_N, 1, 4));

		text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N, 2, 1));
		assertEquals(text, "Wisconsin", String.format(MSG_TABLE_M_N, 2, 1));

		text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N, 2, 2));
		assertEquals(text, "Milwaukee", String.format(MSG_TABLE_M_N, 2, 2));

		text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N, 2, 3));
		assertEquals(text, "Madison", String.format(MSG_TABLE_M_N, 2, 3));

		text = selenium.getText(String.format(LOC_FIRST_TOWN_TABLE_M_N, 2, 4));
		assertEquals(text, "Green Bay", String.format(MSG_TABLE_M_N, 2, 4));
	}

	/**
	 * Tests typing in a non-existing city ('aaa'). It checks that the string "No capitals found" is shown.
	 */
	@Test
	public void testNonExisting() {
		scrollIntoView(LOC_FIRST_INPUT, true);

		// select aaa
		selenium.typeKeys(LOC_FIRST_INPUT, "aaa");
		
		waitForElement(String.format(LOC_FIRST_SUGGESTION_BOX_N, 1));

		String text = selenium.getText(String.format(
				LOC_FIRST_SUGGESTION_BOX_N, 1));

		assertEquals(text, "No capitals found", MSG_NO_CAPITALS_FOUND);
	}

	/**
	 * Tests the "Border" slider. It tests values 3, 5, and 7.
	 */
	@Test
	public void testBorder() {
		scrollIntoView(LOC_FIRST_INPUT, true);

		selenium.type(LOC_FIRST_BORDER_INPUT, "3");
		waitFor(1500);
		int width = getValue(LOC_FIRST_BORDER_STYLE, "border-width");
		assertEquals(width, 3, String.format(MSG_SUGGESTION_BOX_BORDER, 3));

		selenium.type(LOC_FIRST_BORDER_INPUT, "5");
		waitFor(1500);
		width = getValue(LOC_FIRST_BORDER_STYLE, "border-width");
		assertEquals(width, 5, String.format(MSG_SUGGESTION_BOX_BORDER, 5));

		selenium.type(LOC_FIRST_BORDER_INPUT, "7"); // 7 -> 5
		waitFor(1500);
		width = getValue(LOC_FIRST_BORDER_STYLE, "border-width");
		assertEquals(width, 5, String.format(MSG_SUGGESTION_BOX_BORDER, 7));
	}

	/**
	 * Tests the "Width" slider. It tests values 150, 350, 400, and 176.
	 */
	@Test
	public void testWidth() {
		scrollIntoView(LOC_FIRST_INPUT, true);

		selenium.type(LOC_FIRST_WIDTH_INPUT, "150");
		waitFor(1500);
		int width = getValue(LOC_FIRST_WIDTH_STYLE, "width");
		assertEquals(width, 150, String.format(MSG_SUGGESTION_BOX_WIDTH, 150));

		selenium.type(LOC_FIRST_WIDTH_INPUT, "350");
		waitFor(1500);
		width = getValue(LOC_FIRST_WIDTH_STYLE, "width");
		assertEquals(width, 350, String.format(MSG_SUGGESTION_BOX_WIDTH, 350));

		selenium.type(LOC_FIRST_WIDTH_INPUT, "400"); // 400 -> 350
		waitFor(1500);
		width = getValue(LOC_FIRST_WIDTH_STYLE, "width");
		assertEquals(width, 350, String.format(MSG_SUGGESTION_BOX_WIDTH, 400));

		selenium.type(LOC_FIRST_WIDTH_INPUT, "176"); // 176 -> 200
		waitFor(1500);
		width = getValue(LOC_FIRST_WIDTH_STYLE, "width");
		assertEquals(width, 200, String.format(MSG_SUGGESTION_BOX_WIDTH, 176));
	}

	/**
	 * Tests the "Height" slider. It tests values 100, 300, 400, and 176.
	 */
	@Test
	public void testHeight() {
		scrollIntoView(LOC_FIRST_INPUT, true);

		selenium.type(LOC_FIRST_HEIGHT_INPUT, "100");
		waitFor(1500);
		int height = getValue(LOC_FIRST_HEIGHT_STYLE, "height");
		assertEquals(height, 100, String.format(MSG_SUGGESTION_BOX_HEIGHT, 100));

		selenium.type(LOC_FIRST_HEIGHT_INPUT, "300");
		waitFor(1500);
		height = getValue(LOC_FIRST_HEIGHT_STYLE, "height");
		assertEquals(height, 300, String.format(MSG_SUGGESTION_BOX_HEIGHT, 300));

		selenium.type(LOC_FIRST_HEIGHT_INPUT, "400"); // 400 -> 300
		waitFor(1500);
		height = getValue(LOC_FIRST_HEIGHT_STYLE, "height");
		assertEquals(height, 300, String.format(MSG_SUGGESTION_BOX_HEIGHT, 400));

		selenium.type(LOC_FIRST_HEIGHT_INPUT, "176"); // 176 -> 200
		waitFor(1500);
		height = getValue(LOC_FIRST_HEIGHT_STYLE, "height");
		assertEquals(height, 200, String.format(MSG_SUGGESTION_BOX_HEIGHT, 176));
	}

	/**
	 * Tests the "Shadow Depth" slider. It tests values 3, 5, 6, and 7. It test both top and left offset.
	 */
	@Test
	public void testShadowDepth() {
		scrollIntoView(LOC_FIRST_INPUT, true);

		selenium.type(LOC_FIRST_SHADOW_DEPTH_INPUT, "3");
		waitFor(1500);
		int top = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "top");
		assertEquals(top, 3, String.format(MSG_SUGGESTION_BOX_SHADOW_DEPTH, 3));
		int left = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "left");
		assertEquals(left, 3, String.format(MSG_SUGGESTION_BOX_SHADOW_DEPTH, 3));

		selenium.type(LOC_FIRST_SHADOW_DEPTH_INPUT, "5");
		waitFor(1500);
		top = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "top");
		assertEquals(top, 5, String.format(MSG_SUGGESTION_BOX_SHADOW_DEPTH, 5));
		left = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "left");
		assertEquals(left, 5, String.format(MSG_SUGGESTION_BOX_SHADOW_DEPTH, 5));

		selenium.type(LOC_FIRST_SHADOW_DEPTH_INPUT, "6");
		waitFor(1500);
		top = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "top");
		assertEquals(top, 6, String.format(MSG_SUGGESTION_BOX_SHADOW_DEPTH, 6));
		left = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "left");
		assertEquals(left, 6, String.format(MSG_SUGGESTION_BOX_SHADOW_DEPTH, 6));

		selenium.type(LOC_FIRST_SHADOW_DEPTH_INPUT, "7"); // 7 -> 6
		waitFor(1500);
		top = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "top");
		assertEquals(top, 6, String.format(MSG_SUGGESTION_BOX_SHADOW_DEPTH, 7));
		left = getValue(LOC_FIRST_SHADOW_DEPTH_STYLE, "left");
		assertEquals(left, 6, String.format(MSG_SUGGESTION_BOX_SHADOW_DEPTH, 7));
	}

	/**
	 * Tests the "Shadow Opacity" slider. It tests values 1, 5, 9, and 11.
	 */
	@Test
	public void testShadowOpacity() {
		scrollIntoView(LOC_FIRST_INPUT, true);

		selenium.type(LOC_FIRST_SHADOW_OPACITY_INPUT, "1");
		waitFor(1500);
		double opacity = getOpacity(LOC_FIRST_SHADOW_OPACITY_STYLE);
		assertEquals(opacity, 0.1, String.format(
				MSG_SUGGESTION_BOX_SHADOW_OPACITY, 1));

		selenium.type(LOC_FIRST_SHADOW_OPACITY_INPUT, "5");
		waitFor(1500);
		opacity = getOpacity(LOC_FIRST_SHADOW_OPACITY_STYLE);
		assertEquals(opacity, 0.5, String.format(
				MSG_SUGGESTION_BOX_SHADOW_OPACITY, 5));

		selenium.type(LOC_FIRST_SHADOW_OPACITY_INPUT, "9");
		waitFor(1500);
		opacity = getOpacity(LOC_FIRST_SHADOW_OPACITY_STYLE);
		assertEquals(opacity, 0.9, String.format(
				MSG_SUGGESTION_BOX_SHADOW_OPACITY, 9));

		selenium.type(LOC_FIRST_SHADOW_OPACITY_INPUT, "11"); // 11 -> 9
		waitFor(1500);
		opacity = getOpacity(LOC_FIRST_SHADOW_OPACITY_STYLE);
		assertEquals(opacity, 0.9, String.format(
				MSG_SUGGESTION_BOX_SHADOW_OPACITY, 11));
	}

	/**
	 * Tests the "Cellpadding" slider. It tests values 1, 10, 20, and 25.
	 */
	@Test
	public void testCellpadding() {
		scrollIntoView(LOC_FIRST_INPUT, true);

		selenium.type(LOC_FIRST_CELLPADDING_INPUT, "1");
		waitFor(1500);
		int cellpadding = Integer.parseInt(selenium
				.getAttribute(LOC_FIRST_CELLPADDING));
		assertEquals(cellpadding, 1, String.format(
				MSG_SUGGESTION_BOX_CELLPADDING, 1));

		selenium.type(LOC_FIRST_CELLPADDING_INPUT, "10");
		waitFor(1500);
		cellpadding = Integer.parseInt(selenium
				.getAttribute(LOC_FIRST_CELLPADDING));
		assertEquals(cellpadding, 10, String.format(
				MSG_SUGGESTION_BOX_CELLPADDING, 10));

		selenium.type(LOC_FIRST_CELLPADDING_INPUT, "20");
		waitFor(1500);
		cellpadding = Integer.parseInt(selenium
				.getAttribute(LOC_FIRST_CELLPADDING));
		assertEquals(cellpadding, 20, String.format(
				MSG_SUGGESTION_BOX_CELLPADDING, 20));

		selenium.type(LOC_FIRST_CELLPADDING_INPUT, "25"); // 25 -> 20
		waitFor(1500);
		cellpadding = Integer.parseInt(selenium
				.getAttribute(LOC_FIRST_CELLPADDING));
		assertEquals(cellpadding, 20, String.format(
				MSG_SUGGESTION_BOX_CELLPADDING, 25));
	}

	/**
	 * Tests the button with arrow in the second example. It checks that there are 50 suggestions.
	 */
	@Test
	void testSecondExampleButton() {
		scrollIntoView(LOC_SECOND_INPUT, true);

		selenium.click(LOC_SECOND_BUTTON);
		waitForElement(LOC_SECOND_SUGGESTION_LINES);

		int count = selenium.getXpathCount(LOC_SECOND_SUGGESTION_LINES)
				.intValue();
		assertEquals(count, 50, MSG_COUNT_OF_ALL_SUGGESTIONS);
	}

	/**
	 * Tests inputting one city in the second example. It presses 'a' and clicks on the second suggestion.
	 */
	@Test
	public void testSecondExampleOneCity() {
		scrollIntoView(LOC_SECOND_INPUT, true);

		selenium.typeKeys(LOC_SECOND_INPUT, "a");
		waitForElement(String.format(LOC_SECOND_SUGGESTION_BOX_N, 2));
		selenium.click(String.format(LOC_SECOND_SUGGESTION_BOX_N, 2)); // Augusta

		String state = selenium.getText(LOC_SECOND_STATE);
		assertEquals(state, "Maine", MSG_AUGUSTA_MAINE);
	}

	/**
	 * Tests inputting two cities separated by a comma in the second example. It presses 'a', clicks 
	 * on the second suggestion, presses ',' and 'm' and clicks on the third suggestion (Augusta and Madison).
	 * Then it checks that states belonging to these cities are shown below the combo box.
	 */
	@Test
	public void testSecondExampleMoreCities() {
		scrollIntoView(LOC_SECOND_INPUT, true);
		
		selenium.typeKeys(LOC_SECOND_INPUT, "a"); 
		waitForElement(String.format(LOC_SECOND_SUGGESTION_BOX_N, 2));
		selenium.click(String.format(LOC_SECOND_SUGGESTION_BOX_N, 2)); // Augusta

		selenium.typeKeys(LOC_SECOND_INPUT, ",m"); 
		waitForTextEquals(String.format(LOC_SECOND_SUGGESTION_BOX_N, 3),
				"Madison");
		selenium.click(String.format(LOC_SECOND_SUGGESTION_BOX_N, 3)); // Madison

		waitFor(700);
		String state = selenium.getText(LOC_SECOND_STATE);
		assertEquals(state, "Maine,Wisconsin",
				MSG_AUGUSTA_MADISON_MAINE_WISCONSIN);
	}

	/**
     * Tests the "View Page Source" in the first example. It checks that the source
     * code is not visible, clicks on the link, and checks 10 lines of source
     * code.
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
                "height=\"#{suggestionBox.height}\"",
                "nothingLabel=\"No capitals found\" columnClasses=\"center\"",
        };

        abstractTestSource(1, "View Page Source", strings);
    }

    /**
     * Tests the "View CapitalsBean.java Source" in the first example. It checks that the source
     * code is not visible, clicks on the link, and checks 8 lines of source
     * code.
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
                "public ArrayList<Capital> getCapitals() {", 
        };

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
                "for=\"statesinput\" fetchValue=\"#{cap.name}\" id=\"suggestion\" tokens=\",\">",
        };

        abstractTestSource(2, "View Source", strings);
    }
    
	/**
	 * Pulls out the value of the specified attribute from the specified location.
	 * @param loc an attribute locator
	 * @param attr an 'subattribute' whose value we want (e.g. <div style="left: 4px;"/>, then loc=//div@style and attr=left
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
	 * @param loc an attribute locator
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
	@BeforeMethod
	private void loadPage() {
		super.loadPage("richInputs", 10, 1, MSG_COMPONENT_DESCRIPTION);
	}
}
