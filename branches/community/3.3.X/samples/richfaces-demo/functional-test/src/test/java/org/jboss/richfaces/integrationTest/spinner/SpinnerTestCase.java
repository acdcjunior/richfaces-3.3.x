package org.jboss.richfaces.integrationTest.spinner;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests the spinner component.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class SpinnerTestCase extends
		AbstractSeleniumRichfacesTestCase {

	// upper spinner
	private final String upperInput = "//fieldset[1]/div/form/table[1]/tbody/tr/td[1]/input";
	private final String upperUpArrow = "//fieldset[1]/div/form/table[1]/tbody/tr/td[2]/table/tbody/tr[1]/td/input";
	private final String upperDownArrow = "//fieldset[1]/div/form/table[1]/tbody/tr/td[2]/table/tbody/tr[2]/td/input";

	// lower spinner
	private final String lowerInput = "//fieldset[1]/div/form/table[2]/tbody/tr/td[1]/input";
	private final String lowerUpArrow = "//fieldset[1]/div/form/table[2]/tbody/tr/td[2]/table/tbody/tr[1]/td/input";
	private final String lowerDownArrow = "//fieldset[1]/div/form/table[2]/tbody/tr/td[2]/table/tbody/tr[2]/td/input";

	/**
	 * Tests the initial state of both spinners. Both should be set to 50.
	 */
	@Test
	public void testInitialState() {
		assertTrue(selenium.isElementPresent(upperInput),
				"Upper input field is not present.");
		assertTrue(selenium.isElementPresent(upperUpArrow),
				"Upper arrow up is not present.");
		assertTrue(selenium.isElementPresent(upperDownArrow),
				"Upper arrow down is not present.");
		assertTrue(selenium.isElementPresent(lowerInput),
				"Lower input field is not present.");
		assertTrue(selenium.isElementPresent(lowerUpArrow),
				"Lower arrow up is not present.");
		assertTrue(selenium.isElementPresent(lowerDownArrow),
				"Lower arrow down is not present.");

		int number = Integer.parseInt(selenium.getValue(upperInput));
		assertEquals(number, 50, "Content of upper spinner.");

		number = Integer.parseInt(selenium.getValue(lowerInput));
		assertEquals(number, 50, "Content of lower spinner.");
	}

	/**
	 * Tests clicking on the up arrow of the upper spinner.
	 */
	@Test
	public void testUpperClickUp() {
		int oldValue = Integer.parseInt(selenium.getValue(upperInput));

		selenium.clickAt(upperUpArrow, "0,0");

		int newValue = Integer.parseInt(selenium.getValue(upperInput));

		assertEquals(newValue, oldValue + 1,
				"The value should increase by 1 after clicking on up arrow.");
	}

	/**
	 * Tests clicking on the down arrow of the upper spinner.
	 */
	@Test
	public void testUpperClickDown() {
		int oldValue = Integer.parseInt(selenium.getValue(upperInput));

		selenium.clickAt(upperDownArrow, "0,0");

		int newValue = Integer.parseInt(selenium.getValue(upperInput));

		assertEquals(newValue, oldValue - 1,
				"The value should decrease by 1 after clicking on down arrow.");

	}

	/**
	 * Tests clicking on the up arrow of the lower spinner.
	 */
	@Test
	public void testLowerClickUp() {
		int oldValue = Integer.parseInt(selenium.getValue(lowerInput));

		selenium.clickAt(lowerUpArrow, "0,0");

		int newValue = Integer.parseInt(selenium.getValue(lowerInput));

		assertEquals(newValue, oldValue + 10,
				"The value should increase by 10 after clicking on up arrow.");
	}

	/**
	 * Tests clicking on the down arrow of the lower spinner.
	 */
	@Test
	public void testLowerClickDown() {
		int oldValue = Integer.parseInt(selenium.getValue(lowerInput));

		selenium.clickAt(lowerDownArrow, "0,0");

		int newValue = Integer.parseInt(selenium.getValue(lowerInput));

		assertEquals(newValue, oldValue - 10,
				"The value should decrease by 10 after clicking on down arrow.");
	}

	/**
	 * Tests typing into the upper spinner. In the beginning it tries valid
	 * value (20), then a value bigger than maximum (2000), a value lower than
	 * minimum (-23), a decimal value (34.5), and a string ("aaa").
	 */
	@Test
	public void testTypeToUpper() {
		selenium.type(upperInput, "20");
		Number newValue = Integer.parseInt(selenium.getValue(upperInput));
		assertEquals(newValue, 20, "Value in the input field did not change.");

		selenium.type(upperInput, "2000");
		newValue = Integer.parseInt(selenium.getValue(upperInput));
		assertEquals(newValue, 100,
				"Input field should contain 100 after 2000 was typed.");

		selenium.type(upperInput, "-23");
		newValue = Integer.parseInt(selenium.getValue(upperInput));
		assertEquals(newValue, 0,
				"Input field should contain 0 after -23 was typed.");

		selenium.type(upperInput, "34.5");
		newValue = Double.parseDouble(selenium.getValue(upperInput));
		assertEquals(newValue, 34.5,
				"Input field should contain 34.5 after 34.5 was typed.");

		Number oldValue = Double.parseDouble(selenium.getValue(upperInput));
		selenium.type(upperInput, "aaa");
		newValue = Double.parseDouble(selenium.getValue(upperInput));
		assertEquals(newValue, oldValue, "Input field should contain "
				+ oldValue + " after 'aaa' was typed.");
	}

	/**
	 * Tests typing into the lower spinner. In the beginning it tries valid
	 * value (20), then a value bigger than maximum (2000), a value lower than
	 * minimum (-23), a decimal value (34.5), and a string ("aaa").
	 */
	@Test
	public void testTypeToLower() {
		selenium.type(upperInput, "20");
		Number newValue = Integer.parseInt(selenium.getValue(upperInput));
		assertEquals(newValue, 20, "Value in the input field did not change.");

		selenium.type(upperInput, "2000");
		newValue = Integer.parseInt(selenium.getValue(upperInput));
		assertEquals(newValue, 100,
				"Input field should contain 100 after 2000 was typed.");

		selenium.type(upperInput, "-23");
		newValue = Integer.parseInt(selenium.getValue(upperInput));
		assertEquals(newValue, 0,
				"Input field should contain 0 after -23 was typed.");

		selenium.type(upperInput, "34.5");
		newValue = Double.parseDouble(selenium.getValue(upperInput));
		assertEquals(newValue, 34.5,
				"Input field should contain 34.5 after 34.5 was typed.");

		Number oldValue = Double.parseDouble(selenium.getValue(upperInput));
		selenium.type(upperInput, "aaa");
		newValue = Double.parseDouble(selenium.getValue(upperInput));
		assertEquals(newValue, oldValue, "Input field should contain "
				+ oldValue + " after 'aaa' was typed.");
	}

	/**
	 * Tests value overflow. It clicks 51 times on the up button of the upper
	 * spinner and verifies that the value changed to 0. Then it clicks 6 times
	 * on the up button of the lower spinner and checks that the value changed
	 * to 0.
	 */
	@Test
	public void testOverflow() {
		// it starts at 50, maximum is 100, increment 1, it will try to click 51
		// times
		for (int i = 0; i < 51; i++) {
			selenium.clickAt(upperUpArrow, "0,0");
		}

		int newValue = Integer.parseInt(selenium.getValue(upperInput));
		assertEquals(newValue, 0, "The value should change to 0 at overflow.");

		// it starts at 50, maximum is 100, increment 10, it will try to click 6
		// times
		for (int i = 0; i < 6; i++) {
			selenium.clickAt(lowerUpArrow, "0,0");
		}

		newValue = Integer.parseInt(selenium.getValue(lowerInput));
		assertEquals(newValue, 0, "The value should change to 0 at overflow.");
	}

	/**
	 * Tests value underflow. It clicks 51 times on the down button of the lower
	 * spinner and verifies that the value changed to 100. Then it clicks 6
	 * times on the down button of the lower spinner and checks that the value
	 * changed to 100.
	 */
	@Test
	public void testUnderflow() {
		// it starts at 50, minimum is 0, increment 1, it will try to click 51
		// times
		for (int i = 0; i < 51; i++) {
			selenium.clickAt(upperDownArrow, "0,0");
		}

		int newValue = Integer.parseInt(selenium.getValue(upperInput));
		assertEquals(newValue, 100,
				"The value should change to 100 at underflow.");

		// it starts at 50, minimum is 0, increment 10, it will try to click 6
		// times
		for (int i = 0; i < 6; i++) {
			selenium.clickAt(lowerDownArrow, "0,0");
		}

		newValue = Integer.parseInt(selenium.getValue(lowerInput));
		assertEquals(newValue, 100,
				"The value should change to 100 at underflow.");
	}

	/**
	 * Tests the "View Source". It checks that the source code is not visible,
	 * clicks on the link, and checks the first 2 components of source code,
	 * i.e. that the source code begins with "&lt;ui:composition".
	 */
	@Test
	public void testSpinnerSource() {
		abstractTestSource(1, 1, "<", "ui:composition");
	}

	/**
	 * Loads the page containing the calendar component.
	 */
	@BeforeMethod
	private void loadPage() {
		super.loadPage("richInputs", 9,
				"InputNumberSpinner is a highly customizable component");
	}

}
