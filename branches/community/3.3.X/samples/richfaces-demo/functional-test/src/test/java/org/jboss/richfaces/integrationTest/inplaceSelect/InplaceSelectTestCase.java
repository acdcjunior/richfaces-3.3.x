package org.jboss.richfaces.integrationTest.inplaceSelect;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.event.KeyEvent;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests the inplace select component.
 * <ul>
 * <li><b>TODO</b> test cancel buttons
 * </ul>
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class InplaceSelectTestCase extends AbstractSeleniumRichfacesTestCase {

	// messages
	private final String MSG_COMPONENT_DESCRIPTION = getMess("COMPONENT_DESCRIPTION");
	private final String MSG_CLICK_HERE_TO_EDIT = getMess("CLICK_HERE_TO_EDIT");
	private final String MSG_DOUBLE_CLICK_TO_EDIT = getMess("DOUBLE_CLICK_TO_EDIT");
	private final String MSG_READ_ONLY = getMess("READ_ONLY");
	private final String MSG_DISPLAY_NONE = getMess("DISPLAY_NONE");
	private final String MSG_OPTION4_SELECTED = getMess("OPTION4_SELECTED");
	private final String MSG_ARKANSAS_SELECTED = getMess("ARKANSAS_SELECTED");
	private final String MSG_LITTLE_ROCK = getMess("LITTLE_ROCK");
	private final String MSG_COUNT_OF_ITEMS = getMess("COUNT_OF_ITEMS");

	// locators
	private final String LOC_FIRST = getLoc("FIRST");
	private final String LOC_FIRST_INPUT_1 = getLoc("FIRST_INPUT_1");
	private final String LOC_FIRST_INPUT_2 = getLoc("FIRST_INPUT_2");
	private final String LOC_FIRST_SELECT_VIEW = getLoc("FIRST_SELECT_VIEW");
	private final String LOC_FIRST_LIST_SPAN = getLoc("FIRST_LIST_SPAN");
	private final String LOC_FIRST_LIST_SPAN_N = getLoc("FIRST_LIST_SPAN_N");
	private final String LOC_SECOND_OK_BUTTON = getLoc("SECOND_OK_BUTTON");

	private final String LOC_SECOND = getLoc("SECOND");
	private final String LOC_SECOND_INPUT_1 = getLoc("SECOND_INPUT_1");
	private final String LOC_SECOND_INPUT_2 = getLoc("SECOND_INPUT_2");
	private final String LOC_SECOND_LIST_SPAN = getLoc("SECOND_LIST_SPAN");
	private final String LOC_SECOND_LIST_SPAN_N = getLoc("SECOND_LIST_SPAN_N");

	private final String LOC_THIRD = getLoc("THIRD");
	private final String LOC_THIRD_INPUT_1 = getLoc("THIRD_INPUT_1");
	private final String LOC_THIRD_INPUT_2 = getLoc("THIRD_INPUT_2");
	private final String LOC_THIRD_LIST_SPAN = getLoc("THIRD_LIST_SPAN");
	private final String LOC_THIRD_LIST_SPAN_N = getLoc("THIRD_LIST_SPAN_N");
	private final String LOC_THIRD_CAPITAL = getLoc("THIRD_CAPITAL");
	private final String LOC_THIRD_SAVE_BUTTON = getLoc("THIRD_SAVE_BUTTON");

	/**
	 * Tests the first example. It checks the "readonly" and "style" attribute
	 * of the inplace select. Then it clicks into and out of the component and
	 * verifies that nothing changed. Consequently it checks the number of items
	 * in the component. In the end it selects the fourth item from the inplace
	 * select and checks the text.
	 */
	@Test
	public void testFirstInput() {
		scrollIntoView(LOC_FIRST, true);

		String text = selenium.getText(LOC_FIRST);
		assertTrue(text.equals("Click here to edit"), MSG_CLICK_HERE_TO_EDIT);

		String attr = selenium.getAttribute(LOC_FIRST_INPUT_1 + "@readonly");
		assertTrue(attr.equals("readonly"), MSG_READ_ONLY);

		attr = selenium.getAttribute(LOC_FIRST_INPUT_2 + "@style");
		assertTrue(attr.contains("display: none;"), MSG_DISPLAY_NONE);

		// expand and collapse the inplace select
		selenium.click(LOC_FIRST_INPUT_1);
		selenium.click(LOC_FIRST_INPUT_2);

		text = selenium.getText(LOC_FIRST);
		assertTrue(text.equals("Click here to edit"), MSG_CLICK_HERE_TO_EDIT);

		// expand the component and select "Option 4"
		selenium.click(LOC_FIRST);
		
		int count = selenium.getXpathCount(LOC_FIRST_LIST_SPAN).intValue();
		assertEquals(count, 5, MSG_COUNT_OF_ITEMS);
		
		selenium.mouseMove(String.format(LOC_FIRST_LIST_SPAN_N, 4));

		// TODO explore whether this isn't too low-level
		selenium.getEval(format("selenium.browserbot.findElement(\"{0}\").component.save()", LOC_FIRST_SELECT_VIEW));

		// another way to select it
		// selenium.selectWindow(null); // select the main window
		// selenium.windowFocus();
		// selenium.keyPressNative(Integer.toString(KeyEvent.VK_ENTER));

		text = selenium.getText(LOC_FIRST);
		assertTrue(text.equals("Option 4"), MSG_OPTION4_SELECTED);
	}

	/**
	 * Tests the second example. It checks the "readonly" and "style" attribute
	 * of the inplace select. Then it clicks into and out of the component and
	 * verifies that nothing changed. Consequently it checks the number of items
	 * in the component. In the end it selects the fourth item from the inplace
	 * select and checks the text.
	 */
	@Test
	public void testSecondInput() {
		scrollIntoView(LOC_SECOND, true);

		String text = selenium.getText(LOC_SECOND);
		assertTrue(text.equals("Double Click to edit"),
				MSG_DOUBLE_CLICK_TO_EDIT);

		String attr = selenium.getAttribute(LOC_SECOND_INPUT_1 + "@readonly");
		assertTrue(attr.equals("readonly"), MSG_READ_ONLY);

		attr = selenium.getAttribute(LOC_SECOND_INPUT_2 + "@style");
		assertTrue(attr.contains("display: none;"), MSG_DISPLAY_NONE);

		// expand and collapse the inplace select
		selenium.click(LOC_SECOND_INPUT_1);
		selenium.click(LOC_SECOND_INPUT_2);

		int count = selenium.getXpathCount(LOC_SECOND_LIST_SPAN).intValue();
		assertEquals(count, 50, MSG_COUNT_OF_ITEMS);

		text = selenium.getText(LOC_SECOND);
		assertTrue(text.equals("Double Click to edit"),
				MSG_DOUBLE_CLICK_TO_EDIT);

		// expand the inplace select and click "Arkansas"
		selenium.mouseMove(String.format(LOC_SECOND_LIST_SPAN_N, 4));
		selenium.mouseDown(LOC_SECOND_OK_BUTTON);

		text = selenium.getText(LOC_SECOND);
		assertTrue(text.equals("Arkansas"), MSG_ARKANSAS_SELECTED);
	}

	/**
	 * Tests the third example. It checks the "readonly" and "style" attribute
	 * of the inplace select. Then it clicks into and out of the component and
	 * verifies that nothing changed. Consequently it checks the number of items
	 * in the component. In the end it selects the fourth item from the inplace
	 * select and checks the text.
	 */
	@Test
	public void testThirdInput() {
		scrollIntoView(LOC_THIRD, true);

		String text = selenium.getText(LOC_THIRD);
		assertTrue(text.equals("Click here to edit"), MSG_CLICK_HERE_TO_EDIT);

		String attr = selenium.getAttribute(LOC_THIRD_INPUT_1 + "@readonly");
		assertTrue(attr.equals("readonly"), MSG_READ_ONLY);

		attr = selenium.getAttribute(LOC_THIRD_INPUT_2 + "@style");
		assertTrue(attr.contains("display: none;"), MSG_DISPLAY_NONE);

		// expand and collapse the inplace select
		selenium.click(LOC_THIRD_INPUT_1);
		selenium.click(LOC_THIRD_INPUT_2);

		int count = selenium.getXpathCount(LOC_THIRD_LIST_SPAN).intValue();
		assertEquals(count, 50, MSG_COUNT_OF_ITEMS);

		text = selenium.getText(LOC_THIRD);
		assertTrue(text.endsWith("Click here to edit"), MSG_CLICK_HERE_TO_EDIT);

		// remember for waiting
		text = selenium.getText(LOC_THIRD_CAPITAL);
		
		// select "Arkansas"
		selenium.mouseMove(String.format(LOC_THIRD_LIST_SPAN_N, 4));
		selenium.mouseDown(LOC_THIRD_SAVE_BUTTON);

		// wait for remembered value change
		text = waitForTextChangesAndReturn(LOC_THIRD_CAPITAL, text);
		assertTrue(text.endsWith("Little Rock"), MSG_LITTLE_ROCK);
		
		text = selenium.getText(LOC_THIRD);
		assertTrue(text.equals("Arkansas"), MSG_ARKANSAS_SELECTED);
	}

	/**
	 * Tests the "View Source". It checks that the source code is not visible,
	 * clicks on the link, and checks the first 2 components of source code,
	 * i.e. that the source code begins with "&lt;ui:composition".
	 */
	@Test
	public void testFirstInputSource() {
		abstractTestSource(1, 1, "<", "ui:composition");
	}

	/**
	 * Tests the "View Source". It checks that the source code is not visible,
	 * clicks on the link, and checks the first 2 components of source code,
	 * i.e. that the source code begins with "&lt;ui:composition".
	 */
	@Test
	public void testSecondInputSource() {
		abstractTestSource(2, 2, "<", "ui:composition");
	}

	/**
	 * Tests the "View Source". It checks that the source code is not visible,
	 * clicks on the link, and checks the first 2 components of source code,
	 * i.e. that the source code begins with "&lt;ui:composition".
	 */
	@Test
	public void testThirdInputSource() {
		abstractTestSource(3, 1, "<", "ui:composition");
	}

	/**
	 * Loads the page containing the calendar component.
	 */
	@BeforeMethod
	private void loadPage() {
		super.loadPage("richInputs", 7, 1, MSG_COMPONENT_DESCRIPTION);
	}
}
