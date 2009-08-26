package org.jboss.richfaces.integrationTest.progressBar;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests progress bar.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class ProgressBarTestCase extends AbstractSeleniumRichfacesTestCase {

	// messages
	private final String MSG_COMPONENT_DESCRIPTION = getMess("COMPONENT_DESCRIPTION");
	private final String MSG_BUTTON_SHOULD_BE_PRESENT = getMess("BUTTON_SHOULD_BE_PRESENT");
	private final String MSG_BUTTON_SHOULD_NOT_BE_PRESENT = getMess("BUTTON_SHOULD_NOT_BE_PRESENT");
	private final String MSG_FIRST_PROGRESS_BAR_LABEL = getMess("FIRST_PROGRESS_BAR_LABEL");
	private final String MSG_SECOND_PROGRESS_BAR_UPLOAD = getMess("SECOND_PROGRESS_BAR_UPLOAD");
	private final String MSG_SECOND_PB_INITIAL_LABEL_SHOULD_BE_VISIBLE = getMess("SECOND_PB_INITIAL_LABEL_SHOULD_BE_VISIBLE");
	private final String MSG_SECOND_PB_INITIAL_LABEL_SHOULD_NOT_BE_VISIBLE = getMess("SECOND_PB_INITIAL_LABEL_SHOULD_NOT_BE_VISIBLE");
	private final String MSG_SECOND_PB_FINISHED_LABEL_SHOULD_BE_VISIBLE = getMess("SECOND_PB_FINISHED_LABEL_SHOULD_BE_VISIBLE");
	private final String MSG_SECOND_PB_FINISHED_LABEL_SHOULD_NOT_BE_VISIBLE = getMess("SECOND_PB_FINISHED_LABEL_SHOULD_NOT_BE_VISIBLE");

	// locators
	private final String LOC_EXAMPLE_1_HEADER = getLoc("EXAMPLE_1_HEADER");
	private final String LOC_EXAMPLE_2_HEADER = getLoc("EXAMPLE_2_HEADER");
	private final String LOC_FIRST_BUTTON = getLoc("FIRST_BUTTON");
	private final String LOC_FIRST_PROGRESS_BAR_LABEL = getLoc("FIRST_PROGRESS_BAR_LABEL");
	private final String LOC_FIRST_PROGRESS_BAR_STYLE = getLoc("FIRST_PROGRESS_BAR_STYLE");
	private final String LOC_SECOND_BUTTON = getLoc("SECOND_BUTTON");
	private final String LOC_SECOND_LABEL_FINISHED = getLoc("SECOND_LABEL_FINISHED");
	private final String LOC_SECOND_LABEL_INITIAL = getLoc("SECOND_LABEL_INITIAL");
	private final String LOC_SECOND_PROGRESS_BAR_STYLE = getLoc("SECOND_PROGRESS_BAR_STYLE");

	/**
	 * Tests the first example. It checks that the button is visible and then it
	 * click on the button. The process starts and the test checks 3 times that
	 * the label of the progress bar and its "width" attribute equal.
	 */
	@Test
	public void testFirstExample() {
		scrollIntoView(LOC_EXAMPLE_1_HEADER, true);

		boolean present = selenium.isElementPresent(LOC_FIRST_BUTTON);
		assertTrue(present, MSG_BUTTON_SHOULD_BE_PRESENT);

		selenium.click(LOC_FIRST_BUTTON);

		waitFor(500);
		present = selenium.isElementPresent(LOC_FIRST_BUTTON);
		assertFalse(present, MSG_BUTTON_SHOULD_NOT_BE_PRESENT);

		int value1 = 0;
		int value2 = 0;

		for (int i = 0; i < 4; i++) {
			value1 = getValue(LOC_FIRST_PROGRESS_BAR_STYLE);
			value2 = Integer.parseInt(selenium.getText(
					LOC_FIRST_PROGRESS_BAR_LABEL).replace(" %", ""));
			assertEquals(value1, value2, MSG_FIRST_PROGRESS_BAR_LABEL);
			waitFor(6000);
		}
	}

	/**
	 * Tests the second example. First it checks the displayed label. Then it
	 * clicks on the button and verifies several times that the progress bar is
	 * moving right. Then it waits for process to finish and checks that a label
	 * was displayed.
	 */
	@Test
	public void testSecondExample() {
		scrollIntoView(LOC_EXAMPLE_2_HEADER, true);

		String text = null;
		try {
			text = selenium.getAttribute(LOC_SECOND_LABEL_INITIAL + "@style");
			assertTrue(!text.contains("display: none;"),
					MSG_SECOND_PB_INITIAL_LABEL_SHOULD_BE_VISIBLE);
		} catch (Exception e) {
			// OK -- there is no style attribute
		}
		text = selenium.getAttribute(LOC_SECOND_LABEL_FINISHED + "@style");
		assertFalse(!text.contains("display: none;"),
				MSG_SECOND_PB_FINISHED_LABEL_SHOULD_NOT_BE_VISIBLE);

		selenium.click(LOC_SECOND_BUTTON);

		int current = 0;
		int previous = 0;
		waitFor(2200);

		for (int i = 0; i < 10; i++) {
			waitFor(2000);
			current = getValue(LOC_SECOND_PROGRESS_BAR_STYLE);
			assertTrue(current >= previous, MSG_SECOND_PROGRESS_BAR_UPLOAD);
			previous = current;
		}

		waitFor(82000);

		text = selenium.getAttribute(LOC_SECOND_LABEL_INITIAL + "@style");
		assertFalse(!text.contains("display: none;"),
				MSG_SECOND_PB_INITIAL_LABEL_SHOULD_NOT_BE_VISIBLE);
		try {
			text = selenium.getAttribute(LOC_SECOND_LABEL_FINISHED + "@style");
			assertTrue(!text.contains("display: none;"),
					MSG_SECOND_PB_FINISHED_LABEL_SHOULD_BE_VISIBLE);
		} catch (Exception e) {
			// OK -- there is no style attribute
		}

	}

	/**
	 * Tests the "View Source" in the first example. It checks that the source
	 * code is not visible, clicks on the link, and checks 7 lines of source
	 * code.
	 */
	@Test
	public void testFirstExampleSource() {
		String[] strings = new String[] {
				"<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
				"<a4j:outputPanel id=\"progressPanel\">",
				"<rich:progressBar value=\"#{progressBarBean.currentValue}\"",
				"interval=\"2000\" label=\"#{progressBarBean.currentValue} %\"",
				"enabled=\"#{progressBarBean.enabled}\" minValue=\"-1\" maxValue=\"100\"",
				"reRenderAfterComplete=\"progressPanel\">",
				"<a4j:commandButton action=\"#{progressBarBean.startProcess}\"", };

		abstractTestSource(1, "View Source", strings);
	}

	/**
	 * Tests the "View Java Bean Source" in the first example. It checks that
	 * the source code is not visible, clicks on the link, and checks 7 lines of
	 * source code.
	 */
	@Test
	public void testFirstExampleBeanSource() {
		String[] strings = new String[] {
				"package org.richfaces.demo.progressBar;",
				"public class ProgressBarBean {",
				"private boolean buttonRendered = true;",
				"setStartTime(new Date().getTime());",
				"Long current = (new Date().getTime() - startTime)/1000;",
				"public void setEnabled(boolean enabled) {",
				"this.buttonRendered = buttonRendered;", };

		abstractTestSource(1, "View Java Bean Source", strings);
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
				"$('form:progressBar').component.setValue(counter*5);",
				"document.getElementById('button').disabled=false;",
				"<h:form id=\"form\">",
				"</f:facet>",
				"</rich:progressBar>",
				"<rich:progressBar mode=\"client\" id=\"progressBar\">",
				"<button type=\"button\" onclick=\"startProgress();\" style=\"margin: 9px 0px 5px;\" id=\"button\">Start Progress</button>", };

		abstractTestSource(2, "View Source", strings);
	}

	/**
	 * Returns the value of progress bar from the style attribute.
	 * 
	 * @param locator
	 *            locator of the style attribute
	 * @return the value of the attribute style (%)
	 */
	private int getValue(String locator) {
		String attr = selenium.getAttribute(locator);
		attr = attr.substring(attr.indexOf("width"));
		attr = attr.substring(7, attr.indexOf("%;"));
		return (int) Double.parseDouble(attr);
	}

	/**
	 * Loads the page containing the component.
	 */
	@BeforeMethod
	private void loadPage() {
		super.loadPage("richOutputs", 6, 1, MSG_COMPONENT_DESCRIPTION);
	}
}
