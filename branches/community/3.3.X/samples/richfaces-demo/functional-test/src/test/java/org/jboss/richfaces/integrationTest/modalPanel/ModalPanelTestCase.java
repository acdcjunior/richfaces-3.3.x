/**
 * License Agreement.
 *
 *  JBoss RichFaces
 *
 * Copyright (C) 2009  Red Hat, Inc.
 *
 * This code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this code; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package org.jboss.richfaces.integrationTest.modalPanel;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests modal panels.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class ModalPanelTestCase extends AbstractSeleniumRichfacesTestCase {

	// messages
	private final String MSG_COMPONENT_DESCRIPTION = getMsg("COMPONENT_DESCRIPTION");

	private final String MSG_FIRST_PANEL_NOT_VISIBLE = getMsg("FIRST_PANEL_NOT_VISIBLE");
	private final String MSG_FIRST_PANEL_VISIBLE = getMsg("FIRST_PANEL_VISIBLE");
	private final String MSG_FIRST_PANEL_WRONG_CONTENT = getMsg("FIRST_PANEL_WRONG_CONTENT");
	private final String MSG_FIRST_PANEL_WRONG_HEADER = getMsg("FIRST_PANEL_WRONG_HEADER");

	private final String MSG_SECOND_PANEL_NOT_VISIBLE = getMsg("SECOND_PANEL_NOT_VISIBLE");
	private final String MSG_SECOND_PANEL_VISIBLE = getMsg("SECOND_PANEL_VISIBLE");
	private final String MSG_SECOND_PANEL_WRONG_CONTENT = getMsg("SECOND_PANEL_WRONG_CONTENT");
	private final String MSG_SECOND_PANEL_WRONG_HEADER = getMsg("SECOND_PANEL_WRONG_HEADER");

	// locators
	private final String LOC_FIRST_EXAMPLE_HEADER = getLoc("FIRST_EXAMPLE_HEADER");
	private final String LOC_FIRST_PANEL = getLoc("FIRST_PANEL");
	private final String LOC_FIRST_PANEL_POSITION = getLoc("FIRST_PANEL_POSITION");
	private final String LOC_FIRST_PANEL_OPEN = getLoc("FIRST_PANEL_OPEN");
	private final String LOC_FIRST_PANEL_CLOSE = getLoc("FIRST_PANEL_CLOSE");
	private final String LOC_FIRST_PANEL_HEADER = getLoc("FIRST_PANEL_HEADER");
	private final String LOC_FIRST_PANEL_CONTENT = getLoc("FIRST_PANEL_CONTENT");

	private final String LOC_SECOND_EXAMPLE_HEADER = getLoc("SECOND_EXAMPLE_HEADER");
	private final String LOC_SECOND_PANEL = getLoc("SECOND_PANEL");
	private final String LOC_SECOND_PANEL_POSITION = getLoc("SECOND_PANEL_POSITION");
	private final String LOC_SECOND_PANEL_OPEN = getLoc("SECOND_PANEL_OPEN");
	private final String LOC_SECOND_PANEL_CLOSE = getLoc("SECOND_PANEL_CLOSE");
	private final String LOC_SECOND_PANEL_HEADER = getLoc("SECOND_PANEL_HEADER");
	private final String LOC_SECOND_PANEL_CONTENT = getLoc("SECOND_PANEL_CONTENT");

	/**
	 * Tests the first panel. First it checks that the panel is invisible
	 * initially, then opens the panel and verifies it is visible. Then it
	 * checks the content of the panel's header and its content. In the end it
	 * closes the panel.
	 */
	@Test
	public void testFirstPanelOpenClose() {
		String tmp = selenium.getAttribute(LOC_FIRST_PANEL + "@style");
		assertFalse(!tmp.contains("display: none;"),
				MSG_FIRST_PANEL_NOT_VISIBLE);
		
		selenium.click(LOC_FIRST_PANEL_OPEN);

		tmp = selenium.getAttribute(LOC_FIRST_PANEL + "@style");
		assertTrue(!tmp.contains("display: none;"), MSG_FIRST_PANEL_VISIBLE);

		tmp = selenium.getText(LOC_FIRST_PANEL_HEADER);
		assertEquals(tmp, "Modal Panel", MSG_FIRST_PANEL_WRONG_HEADER);

		tmp = selenium.getText(LOC_FIRST_PANEL_CONTENT);
		assertEquals(
				tmp,
				"This panel is called using Component Control Component \n"
						+ "Closure link (X) works also through Component Control",
				MSG_FIRST_PANEL_WRONG_CONTENT);

		selenium.click(LOC_FIRST_PANEL_CLOSE);
		
		tmp = selenium.getAttribute(LOC_FIRST_PANEL + "@style");
		assertFalse(!tmp.contains("display: none;"),
				MSG_FIRST_PANEL_NOT_VISIBLE);
	}

	/**
	 * Tests moving the panel. It opens the panel, gets panel's position on the
	 * page, and moves it 20px right, 20px down. Then it verifies the position,
	 * closes the panel, and opens it again. Then it verifies that the panel is
	 * locatet on the new position.
	 */
	@Test
	public void testFirstPanelMove() {
		selenium.click(LOC_FIRST_PANEL_OPEN);

		int firstX = getPosition("left", LOC_FIRST_PANEL_POSITION);
		int firstY = getPosition("top", LOC_FIRST_PANEL_POSITION);

		for (int i = 0; i < 40; i++) {
			if (i % 2 == 0) {
				selenium.dragAndDrop(LOC_FIRST_PANEL_HEADER, "+1,0");
			} else {
				selenium.dragAndDrop(LOC_FIRST_PANEL_HEADER, "0,+1");
			}
		}

		int secondX = getPosition("left", LOC_FIRST_PANEL_POSITION);
		int secondY = getPosition("top", LOC_FIRST_PANEL_POSITION);

		assertEquals(secondX, firstX + 20, "sdlkfj");
		assertEquals(secondY, firstY + 20, "skldfjsk");

		selenium.click(LOC_FIRST_PANEL_CLOSE);
		selenium.click(LOC_FIRST_PANEL_OPEN);

		int thirdX = getPosition("left", LOC_FIRST_PANEL_POSITION);
		int thirdY = getPosition("top", LOC_FIRST_PANEL_POSITION);

		assertEquals(thirdX, secondX, "sdsdflkfj");
		assertEquals(thirdY, secondY, "skldfsdfjsk");
	}

	/**
	 * Tests the second panel. First it checks that the panel is invisible
	 * initially, then opens the panel and verifies it is visible. Then it
	 * checks the content of the panel's header and its content. In the end it
	 * closes the panel.
	 */
	@Test
	public void testSecondPanelOpenClose() {
		String tmp = selenium.getAttribute(LOC_SECOND_PANEL + "@style");
		assertFalse(!tmp.contains("display: none;"),
				MSG_SECOND_PANEL_NOT_VISIBLE);

		selenium.click(LOC_SECOND_PANEL_OPEN);

		tmp = selenium.getAttribute(LOC_SECOND_PANEL + "@style");
		assertTrue(!tmp.contains("display: none;"), MSG_SECOND_PANEL_VISIBLE);

		tmp = selenium.getText(LOC_SECOND_PANEL_HEADER);
		assertEquals(tmp, "Modal Panel Title", MSG_SECOND_PANEL_WRONG_HEADER);

		tmp = selenium.getText(LOC_SECOND_PANEL_CONTENT);
				assertTrue(tmp.contains("Any JSF content might be inside the panel."),
				MSG_SECOND_PANEL_WRONG_CONTENT);

		selenium.click(LOC_SECOND_PANEL_CLOSE);

		tmp = selenium.getAttribute(LOC_SECOND_PANEL + "@style");
		assertFalse(!tmp.contains("display: none;"),
				MSG_SECOND_PANEL_NOT_VISIBLE);
	}

	/**
	 * Tests moving the panel. It opens the panel, gets panel's position on the
	 * page, and moves it 20px right, 20px down. Then it verifies the position,
	 * closes the panel, and opens it again. Then it verifies that the panel is
	 * locatet on the new position.
	 */
	@Test
	public void testSecondPanelMove() {
		selenium.click(LOC_SECOND_PANEL_OPEN);

		int firstX = getPosition("left", LOC_SECOND_PANEL_POSITION);
		int firstY = getPosition("top", LOC_SECOND_PANEL_POSITION);

		for (int i = 0; i < 40; i++) {
			if (i % 2 == 0) {
				selenium.dragAndDrop(LOC_SECOND_PANEL_HEADER, "+1,0");
			} else {
				selenium.dragAndDrop(LOC_SECOND_PANEL_HEADER, "0,+1");
			}
		}

		int secondX = getPosition("left", LOC_SECOND_PANEL_POSITION);
		int secondY = getPosition("top", LOC_SECOND_PANEL_POSITION);

		assertEquals(secondX, firstX + 20, "sdlkfj");
		assertEquals(secondY, firstY + 20, "skldfjsk");

		selenium.click(LOC_SECOND_PANEL_CLOSE);
		selenium.click(LOC_SECOND_PANEL_OPEN);

		int thirdX = getPosition("left", LOC_SECOND_PANEL_POSITION);
		int thirdY = getPosition("top", LOC_SECOND_PANEL_POSITION);

		assertEquals(thirdX, secondX, "sdsdflkfj");
		assertEquals(thirdY, secondY, "skldfsdfjsk");
	}

	/**
	 * Tests the "View Source". It checks that the source code is not visible,
	 * clicks on the link, and checks 5 lines of source code.
	 */
	@Test
	public void testFirstSource() {
		String [] strings = new String [] {
				 "<rich:modalPanel id=\"panel\" width=\"350\" height=\"100\">",
				 "<f:facet name=\"header\">",
                 "<h:graphicImage value=\"/images/modal/close.png\" styleClass=\"hidelink\" id=\"hidelink\"/>",
                 "<h:outputText value=\"This panel is called using Component Control Component\"></h:outputText>",
                 "<h:outputLink value=\"#\" id=\"link\">",
		};
		
		abstractTestSource(1, "View Source", strings);
	}

	/**
	 * Tests the "View Source". It checks that the source code is not visible,
	 * clicks on the link, and checks 5 lines of source code.
	 */
	@Test
	public void testSecondSource() {
		String [] strings = new String [] {
				"function getRightTop(ref) {",
				"<h:outputText value=\"Modal Panel Title\" />",
				"<p>Any JSF content might be inside the panel. In case of using ",
				"<a href=\"#\" onclick=\"#{rich:component('mp')}.hide()\">hide this panel</a>:",
				"<f:verbatim>&#35;</f:verbatim>{rich:component('mp')}.hide()</p>",
		};
		
		abstractTestSource(2, "View Source", strings);
	}

	/**
	 * Returns one component of the position from the attribute "style".
	 * 
	 * @param which
	 *            one of left, right, top, bottom
	 * @param locator
	 *            locator of the style attribute
	 * @return the value of the attribute in pixels
	 */
	private int getPosition(String which, String locator) {
		String attr = selenium.getAttribute(locator);
		attr = attr.substring(attr.indexOf(which));
		attr = attr.substring(which.length() + 2, attr.indexOf("px;"));
		return Integer.parseInt(attr);
	}

	/**
	 * Loads the page containing the calendar component.
	 */
	@BeforeMethod
	private void loadPage() {
		openComponent("Modal Panel");
	}
}
