package org.jboss.richfaces.integrationTest.spacer;

import static org.testng.Assert.assertEquals;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests spacer.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class SpacerTestCase extends AbstractSeleniumRichfacesTestCase {

	// messages
	private final String MSG_COMPONENT_DESCRIPTION = getMess("COMPONENT_DESCRIPTION");
	private final String MSG_SPACER_HEIGHT = getMess("SPACER_HEIGHT");
	private final String MSG_SPACER_WIDTH = getMess("SPACER_WIDTH");

	// locators
	private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");
	private final String LOC_FIRST_SPACER_HEIGHT = getLoc("FIRST_SPACER_HEIGHT");
	private final String LOC_FIRST_SPACER_WIDTH = getLoc("FIRST_SPACER_WIDTH");
	private final String LOC_SECOND_SPACER_HEIGHT = getLoc("SECOND_SPACER_HEIGHT");
	private final String LOC_SECOND_SPACER_WIDTH = getLoc("SECOND_SPACER_WIDTH");

	/**
	 * Tests the first spacer. Verifies its width and height.
	 */
	@Test
	public void testFirstSpacer() {
		int num = Integer.parseInt(selenium
				.getAttribute(LOC_FIRST_SPACER_HEIGHT));
		assertEquals(num, 10, MSG_SPACER_HEIGHT);

		num = Integer.parseInt(selenium.getAttribute(LOC_FIRST_SPACER_WIDTH));
		assertEquals(num, 100, MSG_SPACER_WIDTH);
	}

	/**
	 * Tests the second spacer. Verifies its width and height.
	 */
	@Test
	public void testSecondSpacer() {
		int num = Integer.parseInt(selenium
				.getAttribute(LOC_SECOND_SPACER_HEIGHT));
		assertEquals(num, 5, MSG_SPACER_HEIGHT);

		num = Integer.parseInt(selenium.getAttribute(LOC_SECOND_SPACER_WIDTH));
		assertEquals(num, 1, MSG_SPACER_WIDTH);
	}

	/**
	 * Tests the "View Source". It checks that the source code is not visible,
	 * clicks on the link, and checks 5 lines of source code.
	 */
	@Test
	public void testFirstExampleSource() {
		String[] strings = new String[] {
				"<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
				"background-color: #{a4jSkin.panelBorderColor};",
				"There is a spacer 100x10<rich:spacer width=\"100\" height=\"10\" title=\"Here is a spacer...\"/>before this.",
				"<div class=\"div_near_spacer\" />",
				"<rich:spacer width=\"1\" height=\"5\" title=\"Here is a spacer...\"/>", };

		abstractTestSource(1, "View Source", strings);
	}

	/**
	 * Loads the page containing the component.
	 */
	@BeforeMethod
	private void loadPage() {
		super.loadPage("richOutputs", 9, 1, MSG_COMPONENT_DESCRIPTION);
		scrollIntoView(LOC_EXAMPLE_HEADER, true);
	}
}
