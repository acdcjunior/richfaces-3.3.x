package org.jboss.richfaces.integrationTest.inputNumberSlider;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests the input number slider.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class InputNumberSliderTestCase extends
		AbstractSeleniumRichfacesTestCase {

	// messages
	private final String MSG_COMPONENT_DESCRIPTION = getMess("COMPONENT_DESCRIPTION");
	private final String MSG_HANDLE_N_PX = getMess("HANDLE_N_PX");
	private final String MSG_TIP_N_PX = getMess("TIP_N_PX");
	private final String MSG_TIP_SHOULD_BE_VISIBLE = getMess("TIP_SHOULD_BE_VISIBLE");
	private final String MSG_TIP_SHOULD_NOT_BE_VISIBLE = getMess("TIP_SHOULD_NOT_BE_VISIBLE");
	private final String MSG_OFFSETS_SHOULD_BE_THE_SAME = getMess("OFFSETS_SHOULD_BE_THE_SAME");
	private final String MSG_TIP_FIRST_HALF = getMess("TIP_FIRST_HALF");
	private final String MSG_TIP_SECOND_HALF = getMess("TIP_SECOND_HALF");
	private final String MSG_HANDLE_FIRST_HALF = getMess("HANDLE_FIRST_HALF");
	private final String MSG_HANDLE_SECOND_HALF = getMess("HANDLE_SECOND_HALF");
	private final String MSG_VALUE_IN_INPUT = getMess("VALUE_IN_INPUT");

	// locators
	private final String LOC_FIRST = String.format(getLoc("SLIDER_N"), 1);
	private final String LOC_FIRST_HANDLE = String.format(
			getLoc("SLIDER_N_HANDLE"), 1);
	private final String LOC_FIRST_TIP = String.format(getLoc("SLIDER_N_TIP"),
			1);
	private final String LOC_FIRST_INPUT = String.format(
			getLoc("SLIDER_N_INPUT"), 1);

	private final String LOC_SECOND = String.format(getLoc("SLIDER_N"), 2);
	private final String LOC_SECOND_HANDLE = String.format(
			getLoc("SLIDER_N_HANDLE"), 2);
	private final String LOC_SECOND_TIP = String.format(getLoc("SLIDER_N_TIP"),
			2);
	private final String LOC_SECOND_INPUT = String.format(
			getLoc("SLIDER_N_INPUT"), 2);

	private final String LOC_THIRD = String.format(getLoc("SLIDER_N"), 3);
	private final String LOC_THIRD_HANDLE = String.format(
			getLoc("SLIDER_N_HANDLE"), 3);
	private final String LOC_THIRD_TIP = String.format(getLoc("SLIDER_N_TIP"),
			3);
	private final String LOC_THIRD_INPUT = String.format(
			getLoc("SLIDER_N_INPUT"), 3);

	/**
	 * Tests clicking on the first slider. First, it checks the offset of the handler and
	 * tip box and checks that tip is hidden. Then it clics to the first half of the slider
	 * and verifies that the tip is visible while the mouse button is pressed.
	 * In the end it verifies that the handler and tip box moved.
	 */
	@Test
	public void testFirstSliderMouse() {
		scrollIntoView(LOC_FIRST, true);

		// TODO find out why it isn't offset by 75px but 74px
		assertTrue(Math.abs(getOffset(LOC_FIRST_HANDLE + "@style") - 75) < 2,
				String.format(MSG_HANDLE_N_PX, 75));

		assertTrue(Math.abs(getOffset(LOC_FIRST_TIP + "@style") - 75) < 2,
				String.format(MSG_TIP_N_PX, 75));

		String attr = selenium.getAttribute(LOC_FIRST_TIP + "@style");
		assertFalse(!attr.contains("display: none;"),
				MSG_TIP_SHOULD_NOT_BE_VISIBLE);

		selenium.mouseDownAt(LOC_FIRST, "20,3");

		attr = selenium.getAttribute(LOC_FIRST_TIP + "@style");
		assertTrue(!attr.contains("display: none;"), MSG_TIP_SHOULD_BE_VISIBLE);

		selenium.mouseUp(LOC_FIRST);

		int tipOffset = getOffset(LOC_FIRST_TIP + "@style");
		int handleOffset = getOffset(LOC_FIRST_HANDLE + "@style");
		int value = Integer.parseInt(selenium.getValue(LOC_FIRST_INPUT));

		assertEquals(tipOffset, handleOffset, MSG_OFFSETS_SHOULD_BE_THE_SAME);
		assertTrue(Math.abs(tipOffset - value * 1.5) < 5, MSG_TIP_FIRST_HALF);
		assertTrue(handleOffset < 75, MSG_HANDLE_FIRST_HALF);
	}

	/**
	 * Tests typing into first slider's input box. First it checks the position of the tip box and 
	 * handle. Then it types a number from the first and second half of slider and checks 
	 * the position of tip and
	 * handle. Then it clicks min value, max value, negative integer, an integer bigger than 
	 * maximum, and decimal number and checks that the value of the input box changes to the
	 * right value.
	 */
	@Test
	public void testFirstSliderKeyboard() {
		scrollIntoView(LOC_FIRST, true);

		selenium.type(LOC_FIRST_INPUT, "10");
		int tipOffset = getOffset(LOC_FIRST_TIP + "@style");
		int handleOffset = getOffset(LOC_FIRST_HANDLE + "@style");

		assertEquals(tipOffset, handleOffset, MSG_OFFSETS_SHOULD_BE_THE_SAME);
		assertTrue(Math.abs(tipOffset - 10 * 1.5) < 5, MSG_TIP_FIRST_HALF);
		assertTrue(handleOffset < 75, MSG_HANDLE_FIRST_HALF);

		selenium.type(LOC_FIRST_INPUT, "90");
		tipOffset = getOffset(LOC_FIRST_TIP + "@style");
		handleOffset = getOffset(LOC_FIRST_HANDLE + "@style");

		assertEquals(tipOffset, handleOffset, MSG_OFFSETS_SHOULD_BE_THE_SAME);
		assertTrue(Math.abs(tipOffset - 90 * 1.5) < 5, MSG_TIP_SECOND_HALF);
		assertTrue(handleOffset > 75, MSG_HANDLE_SECOND_HALF);

		selenium.type(LOC_FIRST_INPUT, "0");
		int value = Integer.parseInt(selenium.getValue(LOC_FIRST_INPUT));
		assertEquals(value, 0, MSG_VALUE_IN_INPUT);

		selenium.type(LOC_FIRST_INPUT, "100");
		value = Integer.parseInt(selenium.getValue(LOC_FIRST_INPUT));
		assertEquals(value, 100, MSG_VALUE_IN_INPUT);

		selenium.type(LOC_FIRST_INPUT, "-10");
		value = Integer.parseInt(selenium.getValue(LOC_FIRST_INPUT));
		assertEquals(value, 0, MSG_VALUE_IN_INPUT);

		selenium.type(LOC_FIRST_INPUT, "130");
		value = Integer.parseInt(selenium.getValue(LOC_FIRST_INPUT));
		assertEquals(value, 100, MSG_VALUE_IN_INPUT);

		selenium.type(LOC_FIRST_INPUT, "1.30");
		value = Integer.parseInt(selenium.getValue(LOC_FIRST_INPUT));
		assertEquals(value, 1, MSG_VALUE_IN_INPUT);
	}

	/**
	 * Tests clicking on the second slider. First, it checks the offset of the handler and
	 * tip box and checks that tip is hidden. Then it clics to the first half of the slider
	 * and verifies that the tip is invisible while the mouse button is pressed.
	 * In the end it verifies that the handler and tip box moved.
	 */
	@Test
	public void testSecondSlider() {
		scrollIntoView(LOC_SECOND, true);

		// TODO find out why it isn't offset by 96px
		assertTrue(Math.abs(getOffset(LOC_SECOND_HANDLE + "@style") - 96) < 2,
				String.format(MSG_HANDLE_N_PX, 96));

		assertTrue(Math.abs(getOffset(LOC_SECOND_TIP + "@style") - 96) < 2,
				String.format(MSG_TIP_N_PX, 96));

		String attr = selenium.getAttribute(LOC_SECOND_TIP + "@style");
		assertFalse(!attr.contains("display: none;"),
				MSG_TIP_SHOULD_NOT_BE_VISIBLE);

		selenium.mouseDownAt(LOC_SECOND, "20,3");

		attr = selenium.getAttribute(LOC_SECOND_TIP + "@style");
		// it is a slider without tip so it cannot be visible
		assertFalse(!attr.contains("display: none;"),
				MSG_TIP_SHOULD_NOT_BE_VISIBLE);

		selenium.mouseUp(LOC_SECOND);

		int tipOffset = getOffset(LOC_SECOND_TIP + "@style");
		int handleOffset = getOffset(LOC_SECOND_HANDLE + "@style");
		int value = Integer.parseInt(selenium.getValue(LOC_SECOND_INPUT));

		assertEquals(tipOffset, handleOffset, MSG_OFFSETS_SHOULD_BE_THE_SAME);
		assertTrue(Math.abs(tipOffset - value * 1.5) < 5, MSG_TIP_FIRST_HALF);
		assertTrue(handleOffset < 75, MSG_HANDLE_FIRST_HALF);
	}

	/**
	 * Tests clicking on the third slider. First, it checks the offset of the handler and
	 * tip box and checks that tip is hidden. Then it clics to the first half of the slider
	 * and verifies that the tip is invisible while the mouse button is pressed.
	 * In the end it verifies that the handler and tip box moved.
	 */
	@Test
	public void testThirdSliderMouse() {
		scrollIntoView(LOC_THIRD, true);

		// TODO find out why it isn't offset by 75px but 74px
		assertTrue(Math.abs(getOffset(LOC_THIRD_HANDLE + "@style") - 225) < 2,
				String.format(MSG_HANDLE_N_PX, 225));

		assertTrue(Math.abs(getOffset(LOC_THIRD_TIP + "@style") - 225) < 2,
				String.format(MSG_TIP_N_PX, 225));

		String attr = selenium.getAttribute(LOC_THIRD_TIP + "@style");
		assertFalse(!attr.contains("display: none;"),
				MSG_TIP_SHOULD_NOT_BE_VISIBLE);

		selenium.mouseDownAt(LOC_THIRD, "20,3");

		attr = selenium.getAttribute(LOC_THIRD_TIP + "@style");
		// slider does not use the tip so it has to be invisible
		assertFalse(!attr.contains("display: none;"),
				MSG_TIP_SHOULD_NOT_BE_VISIBLE);

		selenium.mouseUp(LOC_THIRD);

		int tipOffset = getOffset(LOC_THIRD_TIP + "@style");
		int handleOffset = getOffset(LOC_THIRD_HANDLE + "@style");
		int value = Integer.parseInt(selenium.getValue(LOC_THIRD_INPUT));

		assertEquals(tipOffset, handleOffset, MSG_OFFSETS_SHOULD_BE_THE_SAME);
		assertTrue(Math.abs(tipOffset - value * 0.46) < 12, MSG_TIP_FIRST_HALF);
		assertTrue(handleOffset < 75, MSG_HANDLE_FIRST_HALF);
	}

	/**
	 * Tests typing into third slider's input box. First it checks the position of the tip box and 
	 * handle. Then it types a number from the first and second half of the slider
	 * and checks the position of tip and
	 * handle. Then it clicks min value, max value, negative integer, an integer bigger than 
	 * maximum, and decimal number and checks that the value of the input box changes to the
	 * right value. In the end it tries a number that should be rounded up and a number
	 * that should be rounded down.
	 */
	@Test
	public void testThirdSliderKeyboard() {
		scrollIntoView(LOC_THIRD, true);

		selenium.type(LOC_THIRD_INPUT, "10"); // 10 -> 0
		int tipOffset = getOffset(LOC_THIRD_TIP + "@style");
		int handleOffset = getOffset(LOC_THIRD_HANDLE + "@style");

		assertEquals(tipOffset, handleOffset, MSG_OFFSETS_SHOULD_BE_THE_SAME);
		assertTrue(tipOffset < 5, MSG_TIP_FIRST_HALF);
		assertTrue(handleOffset < 75, MSG_HANDLE_FIRST_HALF);

		selenium.type(LOC_THIRD_INPUT, "690"); // 690 -> 700
		tipOffset = getOffset(LOC_THIRD_TIP + "@style");
		handleOffset = getOffset(LOC_THIRD_HANDLE + "@style");

		assertEquals(tipOffset, handleOffset, MSG_OFFSETS_SHOULD_BE_THE_SAME);
		assertTrue(Math.abs(tipOffset - 0.69 * 450) < 5, MSG_TIP_SECOND_HALF);
		assertTrue(handleOffset > 75, MSG_HANDLE_SECOND_HALF);

		selenium.type(LOC_THIRD_INPUT, "0");
		int value = Integer.parseInt(selenium.getValue(LOC_THIRD_INPUT));
		assertEquals(value, 0, MSG_VALUE_IN_INPUT);

		selenium.type(LOC_THIRD_INPUT, "1000");
		value = Integer.parseInt(selenium.getValue(LOC_THIRD_INPUT));
		assertEquals(value, 1000, MSG_VALUE_IN_INPUT);

		selenium.type(LOC_THIRD_INPUT, "-10"); // -10 -> 0
		value = Integer.parseInt(selenium.getValue(LOC_THIRD_INPUT));
		assertEquals(value, 0, MSG_VALUE_IN_INPUT);
		
		selenium.type(LOC_THIRD_INPUT, "1200"); // 1200 -> 1000
		value = Integer.parseInt(selenium.getValue(LOC_THIRD_INPUT));
		assertEquals(value, 1000, MSG_VALUE_IN_INPUT);

		selenium.type(LOC_THIRD_INPUT, "1.30"); // 1.30 -> 0
		value = Integer.parseInt(selenium.getValue(LOC_THIRD_INPUT));
		assertEquals(value, 0, MSG_VALUE_IN_INPUT);
		
		selenium.type(LOC_THIRD_INPUT, "524"); // 524 -> 500
		value = Integer.parseInt(selenium.getValue(LOC_THIRD_INPUT));
		assertEquals(value, 500, MSG_VALUE_IN_INPUT);

		selenium.type(LOC_THIRD_INPUT, "525"); // 525 -> 500
		value = Integer.parseInt(selenium.getValue(LOC_THIRD_INPUT));
		assertEquals(value, 550, MSG_VALUE_IN_INPUT);
	}

	/**
	 * Tests the "View Source". It checks that the source code is not visible,
	 * clicks on the link, and checks the first 2 components of source code,
	 * i.e. that the source code begins with "&lt;ui:composition".
	 */
	@Test
	public void testSliderSource() {
		abstractTestSource(1, 1, "<", "ui:composition");
	}

	/**
	 * Returns the offset of the element. It requires a locator for an
	 * attribute, e.g. //div@style. It returns the 'left' attribute, e.g. for
	 * style="visibility: visible; left: 51px;" would return 51.
	 */
	private int getOffset(String locator) {
		StringBuilder attr = new StringBuilder(selenium.getAttribute(locator));
		attr = attr.delete(0, attr.indexOf("left: "));
		attr = attr.delete(0, 6);
		attr = attr.delete(attr.indexOf("px;"), attr.length());
		return Integer.parseInt(attr.toString());
	}

	/**
	 * Loads the page containing the calendar component.
	 */
	@BeforeMethod
	private void loadPage() {
		super.loadPage("richInputs", 8, MSG_COMPONENT_DESCRIPTION);
	}
}
