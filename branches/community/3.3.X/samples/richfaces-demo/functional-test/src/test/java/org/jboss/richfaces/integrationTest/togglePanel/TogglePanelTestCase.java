package org.jboss.richfaces.integrationTest.togglePanel;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests toggle panel.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class TogglePanelTestCase extends AbstractSeleniumRichfacesTestCase {

	// messages
	private final String MSG_COMPONENT_DESCRIPTION = getMess("COMPONENT_DESCRIPTION");
	private final String MSG_CLICK_ME_BUTTON_VISIBLE = getMess("CLICK_ME_BUTTON_VISIBLE");
	private final String MSG_CLICK_ME_BUTTON_NOT_VISIBLE = getMess("CLICK_ME_BUTTON_NOT_VISIBLE");
	private final String MSG_PANEL_N_VISIBLE = getMess("PANEL_N_VISIBLE");
	private final String MSG_PANEL_N_NOT_VISIBLE = getMess("PANEL_N_NOT_VISIBLE");

	// locators
	private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");
	private final String LOC_CLICK_ME_BUTTON = getLoc("CLICK_ME_BUTTON");
	private final String LOC_CLICK_ME_BUTTON_A = formatLoc("CLICK_ME_BUTTON_A_RELATIVE", LOC_CLICK_ME_BUTTON);
	private final String LOC_CLOSE_BUTTON = getLoc("CLOSE_BUTTON");
	private final String LOC_PANEL_N = getLoc("PANEL_N");
	private final String LOC_PREVIOUS_BUTTON_N = getLoc("PREVIOUS_BUTTON_N");
	private final String LOC_NEXT_BUTTON_N = getLoc("NEXT_BUTTON_N");

	/**
	 * Tests opening and closing the panel. It verifies that the "Click Me"
	 * button and all panels are in proper state.
	 */
	@Test
	public void testOpenClosePanel() {
		assertTrue(isDisplayed(LOC_CLICK_ME_BUTTON),
				MSG_CLICK_ME_BUTTON_VISIBLE);
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 1)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 1));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 2)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 2));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 3)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 3));

		// open the panel
		selenium.click(LOC_CLICK_ME_BUTTON_A);
		assertFalse(isDisplayed(LOC_CLICK_ME_BUTTON),
				MSG_CLICK_ME_BUTTON_NOT_VISIBLE);
		assertTrue(isDisplayed(String.format(LOC_PANEL_N, 1)), String.format(
				MSG_PANEL_N_VISIBLE, 1));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 2)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 2));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 3)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 3));

		// close the panel
		selenium.click(LOC_CLOSE_BUTTON);
		assertTrue(isDisplayed(LOC_CLICK_ME_BUTTON),
				MSG_CLICK_ME_BUTTON_VISIBLE);
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 1)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 1));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 2)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 2));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 3)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 3));
	}

	/**
	 * Tests navigating between panels. First it opens the toggle panel and then
	 * click next twice and previous twice. After each click it verifies that
	 * only necessary elements are visible.
	 */
	@Test
	public void testNavigation() {
		// open the panel
		selenium.click(LOC_CLICK_ME_BUTTON_A);

		// 1st panel is open
		assertTrue(isDisplayed(String.format(LOC_PANEL_N, 1)), String.format(
				MSG_PANEL_N_VISIBLE, 1));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 2)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 2));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 3)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 3));

		// click next -- move to the 2nd panel
		selenium.click(String.format(LOC_NEXT_BUTTON_N, 1));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 1)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 1));
		assertTrue(isDisplayed(String.format(LOC_PANEL_N, 2)), String.format(
				MSG_PANEL_N_VISIBLE, 2));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 3)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 3));

		// click next -- move to the 3rd panel
		selenium.click(String.format(LOC_NEXT_BUTTON_N, 2));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 1)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 1));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 2)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 2));
		assertTrue(isDisplayed(String.format(LOC_PANEL_N, 3)), String.format(
				MSG_PANEL_N_VISIBLE, 3));

		// click previous -- move to the 2nd panel
		selenium.click(String.format(LOC_PREVIOUS_BUTTON_N, 3));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 1)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 1));
		assertTrue(isDisplayed(String.format(LOC_PANEL_N, 2)), String.format(
				MSG_PANEL_N_VISIBLE, 2));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 3)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 3));

		// click previous -- move to the 1st panel
		selenium.click(String.format(LOC_PREVIOUS_BUTTON_N, 2));
		assertTrue(isDisplayed(String.format(LOC_PANEL_N, 1)), String.format(
				MSG_PANEL_N_VISIBLE, 1));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 2)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 2));
		assertFalse(isDisplayed(String.format(LOC_PANEL_N, 3)), String.format(
				MSG_PANEL_N_NOT_VISIBLE, 3));
	}

	/**
	 * Tests the "View Source". It checks that the source code is not visible,
	 * clicks on the link, and checks 8 lines of source code.
	 */
	@Test
	public void testComponentSource() {
		String[] strings = new String[] {
				"<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
				".infopanel  { border:2px solid #{richSkin.panelBorderColor}; }",
				"<rich:togglePanel value=\"#{toggleBean.skinChooserState1}\" switchType=\"client\"",
				"stateOrder=\"closed,tip1, tip2,tip3\">",
				"<h:graphicImage id=\"pic\" style=\"border-width:0\" value=\"/richfaces/togglePanel/picture/clickme.gif\" />",
				"<f:facet name=\"tip1\">",
				"<ui:include src=\"/richfaces/togglePanel/examples/tipBlock.xhtml\">",
				"<ui:param name=\"tip\" value=\"rich:toggleControl might bre located inside of outside", };

		abstractTestSource(1, "View Source", strings);
	}

	/**
	 * Tests the "View TipBlock.xhtml Source". It checks that the source code is
	 * not visible, clicks on the link, and checks 7 lines of source code.
	 */
	@Test
	public void testXhtmlSource() {
		String[] strings = new String[] {
				"<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
				"<f:subview>",
				"<h:panelGrid  styleClass=\"infopanel\"  cellpadding=\"0\" cellspacing=\"0\" columnClasses=\"col\"",
				"<h:outputText value=\"#{tip}\" />",
				"<rich:separator height=\"1\" />",
				"<h:panelGrid columns=\"3\">",
				"<rich:toggleControl switchToState=\"#{previous}\" value=\"#{empty previous? '': '&#171; Previous'}\" />", };

		abstractTestSource(1, "View TipBlock.xhtml Source", strings);
	}

	/**
	 * Loads the page containing the component.
	 */
	@BeforeMethod
	private void loadPage() {
		super.loadPage("richOutputs", 11, 1, MSG_COMPONENT_DESCRIPTION);
		scrollIntoView(LOC_EXAMPLE_HEADER, true);
	}
}
