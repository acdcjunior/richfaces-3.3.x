package org.jboss.richfaces.integrationTest.inplaceInput;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests the inplace input component.
 * <ul>
 * <li><b>TODO</b> create tests for the fourth example (table)
 * </ul>
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class InplaceInputTestCase extends AbstractSeleniumRichfacesTestCase {

	// messages
	private final String MSG_COMPONENT_DESCRIPTION = getMess("COMPONENT_DESCRIPTION");
	private final String MSG_INITIAL_VALUE_NAME = getMess("INITIAL_VALUE_NAME");
	private final String MSG_INITIAL_VALUE_EMAIL = getMess("INITIAL_VALUE_EMAIL");
	private final String MSG_NAME_JOHN_SMITH = getMess("NAME_JOHN_SMITH");
	private final String MSG_EMAIL_JOHN_SMITH = getMess("EMAIL_JOHN_SMITH");
	private final String MSG_RICH_INPLACE_VIEW = getMess("RICH_INPLACE_VIEW");
	private final String MSG_RICH_INPLACE_CHANGED = getMess("RICH_INPLACE_CHANGED");
	private final String MSG_NOT_RICH_INPLACE_CHANGED = getMess("NOT_RICH_INPLACE_CHANGED");
	private final String MSG_RICH_INPLACE_EDIT = getMess("RICH_INPLACE_EDIT");
	private final String MSG_NOT_RICH_INPLACE_EDIT = getMess("NOT_RICH_INPLACE_EDIT");

	// locators
	private final String LOC_FIRST_NAME = getLoc("FIRST_NAME");
	private final String LOC_FIRST_NAME_INPUT = getLoc("FIRST_NAME_INPUT");
	private final String LOC_FIRST_EMAIL = getLoc("FIRST_EMAIL");
	private final String LOC_FIRST_EMAIL_INPUT = getLoc("FIRST_EMAIL_INPUT");
	private final String LOC_SECOND_NAME = getLoc("SECOND_NAME");
	private final String LOC_SECOND_NAME_INPUT = getLoc("SECOND_NAME_INPUT");
	private final String LOC_SECOND_NAME_OK = getLoc("SECOND_NAME_OK");
	private final String LOC_SECOND_NAME_CANCEL = getLoc("SECOND_NAME_CANCEL");
	private final String LOC_SECOND_EMAIL = getLoc("SECOND_EMAIL");
	private final String LOC_SECOND_EMAIL_INPUT = getLoc("SECOND_EMAIL_INPUT");
	private final String LOC_SECOND_EMAIL_OK = getLoc("SECOND_EMAIL_OK");
	private final String LOC_SECOND_EMAIL_CANCEL = getLoc("SECOND_EMAIL_CANCEL");
	private final String LOC_THIRD = getLoc("THIRD");
	private final String LOC_THIRD_INPUT = getLoc("THIRD_INPUT");
	private final String LOC_THIRD_SAVE = getLoc("THIRD_SAVE");
	private final String LOC_THIRD_CANCEL = getLoc("THIRD_CANCEL");

	/**
	 * Tests the input for name in the first example. First it checks the attribute  
	 * "class" of the input. Then it clicks on text, edits the text and fires the
	 * event "blur" so that the text is changed permanently. After that, it 
	 * checks the attribute "class" and verifies that the text was changed.
	 */
	@Test
	public void testFirstName() {
		scrollIntoView(LOC_FIRST_NAME, true);

		String attr = selenium.getAttribute(LOC_FIRST_NAME + "@class");
		assertTrue(attr.contains("rich-inplace-view"), MSG_RICH_INPLACE_VIEW);
		assertFalse(attr.contains("rich-inplace-changed"),
				MSG_NOT_RICH_INPLACE_CHANGED);
		assertFalse(attr.contains("rich-inplace-edit"),
				MSG_NOT_RICH_INPLACE_EDIT);

		String text = selenium.getText(LOC_FIRST_NAME);
		assertTrue(text.endsWith("click to enter your name"),
				MSG_INITIAL_VALUE_NAME);

		selenium.click(LOC_FIRST_NAME);

		attr = selenium.getAttribute(LOC_FIRST_NAME + "@class");
		assertTrue(attr.contains("rich-inplace-edit"), MSG_RICH_INPLACE_EDIT);

		selenium.type(LOC_FIRST_NAME_INPUT, "John Smith");
		selenium.fireEvent(LOC_FIRST_NAME_INPUT, "blur");

		attr = selenium.getAttribute(LOC_FIRST_NAME + "@class");
		assertTrue(attr.contains("rich-inplace-changed"),
				MSG_RICH_INPLACE_CHANGED);
		assertFalse(attr.contains("rich-inplace-edit"),
				MSG_NOT_RICH_INPLACE_EDIT);

		text = selenium.getText(LOC_FIRST_NAME);
		assertTrue(text.endsWith("John Smith"), MSG_NAME_JOHN_SMITH);
	}

	/**
	 * Tests the input for email in the first example. First it checks the attribute  
	 * "class" of the input. Then it clicks on text, edits the text and fires the
	 * event "blur" so that the text is changed permanently. After that, it 
	 * checks the attribute "class" and verifies that the text was changed.
	 */
	@Test
	public void testFirstEmail() {
		scrollIntoView(LOC_FIRST_EMAIL, true);

		String attr = selenium.getAttribute(LOC_FIRST_EMAIL + "@class");
		assertTrue(attr.contains("rich-inplace-view"), MSG_RICH_INPLACE_VIEW);
		assertFalse(attr.contains("rich-inplace-changed"),
				MSG_NOT_RICH_INPLACE_CHANGED);
		assertFalse(attr.contains("rich-inplace-edit"),
				MSG_NOT_RICH_INPLACE_EDIT);

		String text = selenium.getText(LOC_FIRST_EMAIL);
		assertTrue(text.endsWith("click to enter your email"),
				MSG_INITIAL_VALUE_EMAIL);

		selenium.click(LOC_FIRST_EMAIL);

		attr = selenium.getAttribute(LOC_FIRST_EMAIL + "@class");
		assertTrue(attr.contains("rich-inplace-edit"), MSG_RICH_INPLACE_EDIT);

		selenium.type(LOC_FIRST_EMAIL_INPUT, "john@smith.name");
		selenium.fireEvent(LOC_FIRST_EMAIL_INPUT, "blur");

		attr = selenium.getAttribute(LOC_FIRST_EMAIL + "@class");
		assertTrue(attr.contains("rich-inplace-changed"),
				MSG_RICH_INPLACE_CHANGED);
		assertFalse(attr.contains("rich-inplace-edit"),
				MSG_NOT_RICH_INPLACE_EDIT);

		text = selenium.getText(LOC_FIRST_EMAIL);
		assertTrue(text.endsWith("john@smith.name"), MSG_EMAIL_JOHN_SMITH);
	}

	/**
	 * Tests the input for name in the second example. First it checks the attribute  
	 * "class" of the input. Then it clicks on text, edits the text and 
	 * clicks on the button so that the text is changed permanently. After that, it 
	 * checks the attribute "class" and verifies that the text was changed.
	 */
	@Test
	public void testSecondName() {
		scrollIntoView(LOC_SECOND_NAME, true);

		String attr = selenium.getAttribute(LOC_SECOND_NAME + "@class");
		assertTrue(attr.contains("rich-inplace-view"), MSG_RICH_INPLACE_VIEW);
		assertFalse(attr.contains("rich-inplace-changed"),
				MSG_NOT_RICH_INPLACE_CHANGED);
		assertFalse(attr.contains("rich-inplace-edit"),
				MSG_NOT_RICH_INPLACE_EDIT);

		String text = selenium.getText(LOC_SECOND_NAME);
		assertTrue(text.endsWith("click to enter your name"),
				MSG_INITIAL_VALUE_NAME);

		selenium.click(LOC_SECOND_NAME);

		attr = selenium.getAttribute(LOC_SECOND_NAME + "@class");
		assertTrue(attr.contains("rich-inplace-edit"), MSG_RICH_INPLACE_EDIT);

		selenium.type(LOC_SECOND_NAME_INPUT, "John Smith");
		// TODO check that the button is visible
		selenium.fireEvent(LOC_SECOND_NAME_OK, Event.MOUSEDOWN);

		attr = selenium.getAttribute(LOC_SECOND_NAME + "@class");
		assertTrue(attr.contains("rich-inplace-changed"),
				MSG_RICH_INPLACE_CHANGED);
		assertFalse(attr.contains("rich-inplace-edit"),
				MSG_NOT_RICH_INPLACE_EDIT);

		text = selenium.getText(LOC_SECOND_NAME);
		assertTrue(text.endsWith("John Smith"), MSG_NAME_JOHN_SMITH);
	}

	/**
	 * Tests the input for email in the second example. First it checks the attribute  
	 * "class" of the input. Then it clicks on text, edits the text and 
	 * clicks on the button so that the text is changed permanently. After that, it 
	 * checks the attribute "class" and verifies that the text was changed.
	 */
	@Test
	public void testSecondEmail() {
		scrollIntoView(LOC_SECOND_EMAIL, true);

		String attr = selenium.getAttribute(LOC_SECOND_EMAIL + "@class");
		assertTrue(attr.contains("rich-inplace-view"), MSG_RICH_INPLACE_VIEW);
		assertFalse(attr.contains("rich-inplace-changed"),
				MSG_NOT_RICH_INPLACE_CHANGED);
		assertFalse(attr.contains("rich-inplace-edit"),
				MSG_NOT_RICH_INPLACE_EDIT);

		String text = selenium.getText(LOC_SECOND_EMAIL);
		assertTrue(text.endsWith("click to enter your email"),
				MSG_INITIAL_VALUE_EMAIL);

		selenium.click(LOC_SECOND_EMAIL);

		attr = selenium.getAttribute(LOC_SECOND_EMAIL + "@class");
		assertTrue(attr.contains("rich-inplace-edit"), MSG_RICH_INPLACE_EDIT);

		selenium.type(LOC_SECOND_EMAIL_INPUT, "john@smith.name");
		// TODO check that the button is visible
		selenium.fireEvent(LOC_SECOND_EMAIL_OK, Event.MOUSEDOWN);

		attr = selenium.getAttribute(LOC_SECOND_EMAIL + "@class");
		assertTrue(attr.contains("rich-inplace-changed"),
				MSG_RICH_INPLACE_CHANGED);
		assertFalse(attr.contains("rich-inplace-edit"),
				MSG_NOT_RICH_INPLACE_EDIT);

		text = selenium.getText(LOC_SECOND_EMAIL);
		assertTrue(text.endsWith("john@smith.name"), MSG_EMAIL_JOHN_SMITH);
	}

	/**
	 * Tests the input in the third example. First it checks the attribute  
	 * "class" of the input. Then it clicks on text, edits the text and 
	 * clicks on the "Save" button so that the text is changed permanently. 
	 * After that, it checks the attribute "class" and verifies that the text was changed.
	 */
	@Test
	public void testThird() {
		scrollIntoView(LOC_THIRD, true);

		String attr = selenium.getAttribute(LOC_THIRD + "@class");
		assertTrue(attr.contains("rich-inplace-view"), MSG_RICH_INPLACE_VIEW);
		assertFalse(attr.contains("rich-inplace-changed"),
				MSG_NOT_RICH_INPLACE_CHANGED);
		assertFalse(attr.contains("rich-inplace-edit"),
				MSG_NOT_RICH_INPLACE_EDIT);

		String text = selenium.getText(LOC_THIRD);
		assertTrue(text.endsWith("Click here to edit"), MSG_INITIAL_VALUE_EMAIL);

		selenium.click(LOC_THIRD);

		attr = selenium.getAttribute(LOC_THIRD + "@class");
		assertTrue(attr.contains("rich-inplace-edit"), MSG_RICH_INPLACE_EDIT);

		selenium.type(LOC_THIRD_INPUT, "John Smith");
		// TODO check that the button is visible
		selenium.fireEvent(LOC_THIRD_SAVE, Event.MOUSEDOWN);

		attr = selenium.getAttribute(LOC_THIRD + "@class");
		assertTrue(attr.contains("rich-inplace-changed"),
				MSG_RICH_INPLACE_CHANGED);
		assertFalse(attr.contains("rich-inplace-edit"),
				MSG_NOT_RICH_INPLACE_EDIT);

		text = selenium.getText(LOC_THIRD);
		assertTrue(text.endsWith("John Smith"), MSG_NAME_JOHN_SMITH);
	}

	/**
	 * Tests the "View Source". It checks that the source code is not visible,
	 * clicks on the link, and checks the first 2 components of source code,
	 * i.e. that the source code begins with "&lt;ui:composition".
	 */
	@Test
	public void testFirstInputSource() {
		abstractTestSource(1, 2, "<", "ui:composition");
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
		super.loadPage("richInputs", 6, MSG_COMPONENT_DESCRIPTION);
	}
}
