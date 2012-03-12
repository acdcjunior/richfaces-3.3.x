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
	private final String MSG_CLICK_ME_BUTTON_VISIBLE = getMsg("CLICK_ME_BUTTON_VISIBLE");
	private final String MSG_CLICK_ME_BUTTON_NOT_VISIBLE = getMsg("CLICK_ME_BUTTON_NOT_VISIBLE");
	private final String MSG_PANEL_VISIBLE_PREFORMATTED = getMsg("PANEL_VISIBLE_PREFORMATTED");
	private final String MSG_PANEL_NOT_VISIBLE_PREFORMATTED = getMsg("PANEL_NOT_VISIBLE_PREFORMATTED");

	// locators
	private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");
	private final String LOC_CLICK_ME_BUTTON = getLoc("CLICK_ME_BUTTON");
	private final String LOC_CLICK_ME_BUTTON_A = getLoc("CLICK_ME_BUTTON_A");
	private final String LOC_CLOSE_BUTTON = getLoc("CLOSE_BUTTON");
	private final String LOC_PANEL_PREFORMATTED = getLoc("PANEL_PREFORMATTED");
	private final String LOC_PREVIOUS_BUTTON_PREFORMATTED = getLoc("PREVIOUS_BUTTON_PREFORMATTED");
	private final String LOC_NEXT_BUTTON_PREFORMATTED = getLoc("NEXT_BUTTON_PREFORMATTED");

	/**
	 * Tests opening and closing the panel. It verifies that the "Click Me"
	 * button and all panels are in proper state.
	 */
	@Test
	public void testOpenClosePanel() {
	    assertTrue(isDisplayed(LOC_CLICK_ME_BUTTON), MSG_CLICK_ME_BUTTON_VISIBLE);
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 1)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 1));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 2)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 2));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 3)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 3));

		// open the panel
		selenium.click(LOC_CLICK_ME_BUTTON_A);
		assertFalse(isDisplayed(LOC_CLICK_ME_BUTTON), MSG_CLICK_ME_BUTTON_NOT_VISIBLE);
		assertTrue(isDisplayed(format(LOC_PANEL_PREFORMATTED, 1)), format(MSG_PANEL_VISIBLE_PREFORMATTED, 1));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 2)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 2));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 3)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 3));

		// close the panel
		selenium.click(LOC_CLOSE_BUTTON);
		assertTrue(isDisplayed(LOC_CLICK_ME_BUTTON), MSG_CLICK_ME_BUTTON_VISIBLE);
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 1)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 1));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 2)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 2));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 3)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 3));
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
		assertTrue(isDisplayed(format(LOC_PANEL_PREFORMATTED, 1)), format(MSG_PANEL_VISIBLE_PREFORMATTED, 1));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 2)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 2));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 3)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 3));

		// click next -- move to the 2nd panel
		selenium.click(format(LOC_NEXT_BUTTON_PREFORMATTED, 1));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 1)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 1));
		assertTrue(isDisplayed(format(LOC_PANEL_PREFORMATTED, 2)), format(MSG_PANEL_VISIBLE_PREFORMATTED, 2));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 3)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 3));

		// click next -- move to the 3rd panel
		selenium.click(format(LOC_NEXT_BUTTON_PREFORMATTED, 2));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 1)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 1));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 2)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 2));
		assertTrue(isDisplayed(format(LOC_PANEL_PREFORMATTED, 3)), format(MSG_PANEL_VISIBLE_PREFORMATTED, 3));

		// click previous -- move to the 2nd panel
		selenium.click(format(LOC_PREVIOUS_BUTTON_PREFORMATTED, 3));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 1)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 1));
		assertTrue(isDisplayed(format(LOC_PANEL_PREFORMATTED, 2)), format(MSG_PANEL_VISIBLE_PREFORMATTED, 2));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 3)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 3));

		// click previous -- move to the 1st panel
		selenium.click(format(LOC_PREVIOUS_BUTTON_PREFORMATTED, 2));
		assertTrue(isDisplayed(format(LOC_PANEL_PREFORMATTED, 1)), format(MSG_PANEL_VISIBLE_PREFORMATTED, 1));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 2)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 2));
		assertFalse(isDisplayed(format(LOC_PANEL_PREFORMATTED, 3)), format(MSG_PANEL_NOT_VISIBLE_PREFORMATTED, 3));
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
	protected void loadPage() {
	    openComponent("Toggle Panel");
		scrollIntoView(LOC_EXAMPLE_HEADER, true);
	}
}
