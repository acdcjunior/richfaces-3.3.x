package org.jboss.richfaces.integrationTest.simpleTogglePanel;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests simple toggle bar.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class SimpleTogglePanelTestCase extends
		AbstractSeleniumRichfacesTestCase {

	// messages
	private final String MSG_COMPONENT_DESCRIPTION = getMess("COMPONENT_DESCRIPTION");
	private final String MSG_PANEL_HEADER = getMess("PANEL_HEADER");
	private final String MSG_LEFT_ARROWS_SHOULD_BE_VISIBLE = getMess("LEFT_ARROWS_SHOULD_BE_VISIBLE");
	private final String MSG_LEFT_ARROWS_SHOULD_NOT_BE_VISIBLE = getMess("LEFT_ARROWS_SHOULD_NOT_BE_VISIBLE");
	private final String MSG_RIGHT_ARROWS_SHOULD_BE_VISIBLE = getMess("RIGHT_ARROWS_SHOULD_BE_VISIBLE");
	private final String MSG_RIGHT_ARROWS_SHOULD_NOT_BE_VISIBLE = getMess("RIGHT_ARROWS_SHOULD_NOT_BE_VISIBLE");
	private final String MSG_BODY_SHOULD_BE_VISIBLE = getMess("BODY_SHOULD_BE_VISIBLE");
	private final String MSG_BODY_SHOULD_NOT_BE_VISIBLE = getMess("BODY_SHOULD_NOT_BE_VISIBLE");

	// locators
	private final String LOC_EXAMPLE_1_HEADER = getLoc("EXAMPLE_1_HEADER");
	private final String LOC_EXAMPLE_2_HEADER = getLoc("EXAMPLE_2_HEADER");
	private final String LOC_FIRST_EXAMPLE_HEADER = getLoc("FIRST_EXAMPLE_HEADER");
	private final String LOC_FIRST_EXAMPLE_ARROWS_LEFT = formatLoc("FIRST_EXAMPLE_ARROWS_LEFT_RELATIVE_TO_HEADER", LOC_FIRST_EXAMPLE_HEADER);
	private final String LOC_FIRST_EXAMPLE_ARROWS_RIGHT = formatLoc("FIRST_EXAMPLE_ARROWS_RIGHT_RELATIVE_TO_HEADER", LOC_FIRST_EXAMPLE_HEADER);
	private final String LOC_FIRST_EXAMPLE_BODY = getLoc("FIRST_EXAMPLE_BODY");
	private final String LOC_SECOND_EXAMPLE_N_HEADER = getLoc("SECOND_EXAMPLE_N_HEADER");
	private final String LOC_SECOND_EXAMPLE_N_ARROWS_LEFT = formatLoc("SECOND_EXAMPLE_N_ARROWS_LEFT_RELATIVE_TO_HEADER", LOC_SECOND_EXAMPLE_N_HEADER);
	private final String LOC_SECOND_EXAMPLE_N_ARROWS_RIGHT = formatLoc("SECOND_EXAMPLE_N_ARROWS_RIGHT_RELATIVE_TO_HEADER", LOC_SECOND_EXAMPLE_N_HEADER);
	private final String LOC_SECOND_EXAMPLE_N_BODY = getLoc("SECOND_EXAMPLE_N_BODY");

	/**
	 * Tests the first example. It verifies header's text and checks that there
	 * are right arrows displayed in the right side. Then it checks that the
	 * panel's content is displayed and clicks header to toggle panel. Then it
	 * checks again arrows and verifies that the content is hidden.
	 */
	@Test
	public void testFirstExample() {
		scrollIntoView(LOC_EXAMPLE_1_HEADER, true);
		String header = "Add AJAX capability to existing JSF applications";

		String text = selenium.getText(LOC_FIRST_EXAMPLE_HEADER);
		assertTrue(text.contains(header), String.format(MSG_PANEL_HEADER,
				header));

		assertTrue(isDisplayed(LOC_FIRST_EXAMPLE_ARROWS_LEFT),
				MSG_LEFT_ARROWS_SHOULD_BE_VISIBLE);
		assertFalse(isDisplayed(LOC_FIRST_EXAMPLE_ARROWS_RIGHT),
				MSG_RIGHT_ARROWS_SHOULD_NOT_BE_VISIBLE);
		assertTrue(isDisplayed(LOC_FIRST_EXAMPLE_BODY),
				MSG_BODY_SHOULD_BE_VISIBLE);

		selenium.click(LOC_FIRST_EXAMPLE_HEADER);

		assertFalse(isDisplayed(LOC_FIRST_EXAMPLE_ARROWS_LEFT),
				MSG_LEFT_ARROWS_SHOULD_NOT_BE_VISIBLE);
		assertTrue(isDisplayed(LOC_FIRST_EXAMPLE_ARROWS_RIGHT),
				MSG_RIGHT_ARROWS_SHOULD_BE_VISIBLE);
		assertFalse(isDisplayed(LOC_FIRST_EXAMPLE_BODY),
				MSG_BODY_SHOULD_NOT_BE_VISIBLE);
	}

	/**
	 * Tests the second example -- server switch type. It verifies header's text
	 * and checks that there are right arrows displayed in the right side. Then
	 * it checks that the panel's content is displayed and clicks header to
	 * toggle panel. Then it checks again arrows and verifies that the content
	 * is hidden.
	 */
	@Test
	public void testSecondExampleServerSwitch() {
		abstractTestPanelInSecondExample(1, "Server Switch Type");
	}

	/**
	 * Tests the second example -- ajax switch type. It verifies header's text
	 * and checks that there are right arrows displayed in the right side. Then
	 * it checks that the panel's content is displayed and clicks header to
	 * toggle panel. Then it checks again arrows and verifies that the content
	 * is hidden.
	 */
	@Test
	public void testSecondExampleAjaxSwitch() {
		abstractTestPanelInSecondExample(2, "Ajax Switch Type");
	}

	/**
	 * Tests the second example -- client switch type. It verifies header's text
	 * and checks that there are right arrows displayed in the right side. Then
	 * it checks that the panel's content is displayed and clicks header to
	 * toggle panel. Then it checks again arrows and verifies that the content
	 * is hidden.
	 */
	@Test
	public void testSecondExampleClientSwitch() {
		abstractTestPanelInSecondExample(3, "Client Switch Type");
	}

	/**
	 * Tests the "View Source" in the first example. It checks that the source
	 * code is not visible, clicks on the link, and checks 5 lines of source
	 * code.
	 */
	@Test
	public void testFirstExampleSource() {
		String[] strings = new String[] {
				"<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
				"<rich:simpleTogglePanel switchType=\"client\" label=\"Add AJAX capability to existing JSF applications\">",
				"The framework is implemented by using a component library. The library",
				"</rich:simpleTogglePanel>", "</ui:composition>", };

		abstractTestSource(1, "View Source", strings);
	}

	/**
	 * Tests the "View Source" in the second example. It checks that the source
	 * code is not visible, clicks on the link, and checks 7 lines of source
	 * code.
	 */
	@Test
	public void testSecondExampleSource() {
		String[] strings = new String[] {
				"<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
				"<h:form>",
				"<h:panelGrid columns=\"3\" width=\"100%\" columnClasses=\"tpanels,tpanels,tpanels\">",
				"<rich:simpleTogglePanel switchType=\"server\" label=\"Server Switch Type\" height=\"90px\">",
				"<rich:simpleTogglePanel switchType=\"ajax\" label=\"Ajax Switch Type\" height=\"90px\">",
				"<rich:simpleTogglePanel switchType=\"client\" label=\"Client Switch Type\" height=\"90px\">",
				"</rich:simpleTogglePanel>", };

		abstractTestSource(2, "View Source", strings);
	}

	/**
	 * Abstract method that is parametrized in tests.
	 * 
	 * @param index
	 *            which panel should be tested
	 * @param header
	 *            a string appearing in panel's header
	 */
	private void abstractTestPanelInSecondExample(int index, String header) {
		scrollIntoView(LOC_EXAMPLE_2_HEADER, true);

		String text = selenium.getText(String.format(
				LOC_SECOND_EXAMPLE_N_HEADER, index));
		assertTrue(text.contains(header), String.format(MSG_PANEL_HEADER,
				header));

		assertTrue(isDisplayed(String.format(LOC_SECOND_EXAMPLE_N_ARROWS_LEFT,
				index)), MSG_LEFT_ARROWS_SHOULD_BE_VISIBLE);
		assertFalse(isDisplayed(String.format(
				LOC_SECOND_EXAMPLE_N_ARROWS_RIGHT, index)),
				MSG_RIGHT_ARROWS_SHOULD_NOT_BE_VISIBLE);
		// client type is a bit different
		assertTrue(isDisplayed(String.format(LOC_SECOND_EXAMPLE_N_BODY, index,
				index == 3 ? 3 : 2)), MSG_BODY_SHOULD_BE_VISIBLE);

		selenium.click(String.format(LOC_SECOND_EXAMPLE_N_HEADER, index));
		waitFor(2000);

		assertFalse(isDisplayed(String.format(LOC_SECOND_EXAMPLE_N_ARROWS_LEFT,
				index)), MSG_LEFT_ARROWS_SHOULD_NOT_BE_VISIBLE);
		assertTrue(isDisplayed(String.format(LOC_SECOND_EXAMPLE_N_ARROWS_RIGHT,
				index)), MSG_RIGHT_ARROWS_SHOULD_BE_VISIBLE);
		// client type is a bit different
		assertFalse(isDisplayed(String.format(LOC_SECOND_EXAMPLE_N_BODY, index,
				index == 3 ? 3 : 2)), MSG_BODY_SHOULD_NOT_BE_VISIBLE);
	}

	/**
	 * Loads the page containing the component.
	 */
	@BeforeMethod
	private void loadPage() {
		super.loadPage("richOutputs", 8, 1, MSG_COMPONENT_DESCRIPTION);
	}
}
