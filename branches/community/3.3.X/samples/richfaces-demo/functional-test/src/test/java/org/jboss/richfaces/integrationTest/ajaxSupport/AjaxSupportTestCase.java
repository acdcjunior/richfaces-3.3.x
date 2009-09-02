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
 * License along with this test suite; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */
package org.jboss.richfaces.integrationTest.ajaxSupport;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class AjaxSupportTestCase extends AbstractSeleniumRichfacesTestCase {
	private String LOC_INPUT_TEXT = getLoc("INPUT_TEXT");
	private String LOC_OUTPUT_TEXT_RELATIVE = formatLoc("OUTPUT_TEXT_RELATIVE", LOC_INPUT_TEXT);

	private String MSG_INPUT_NON_EMPTY = getMsg("INPUT_NON_EMPTY");

	/**
	 * Try non-empty input. Input should appear in output.
	 */
	@Test
	public void testNonEmpty() {
		nonEmpty();
	}

	/**
	 * Try empty input. No output should appear.
	 */
	@Test
	public void testEmpty() {
		empty();
	}

	/**
	 * Test interleaving of typing empty and non-empty input and watch output to
	 * be changed in right way.
	 */
	@Test
	public void testInterleaving() {
		nonEmpty();
		empty();
		nonEmpty();
		empty();
	}

	public void nonEmpty() {
		selenium.type(LOC_INPUT_TEXT, MSG_INPUT_NON_EMPTY);
		selenium.fireEvent(LOC_INPUT_TEXT, Event.KEYUP);

		waitFor(Wait.DEFAULT_INTERVAL);
		waitForTextEquals(LOC_OUTPUT_TEXT_RELATIVE, MSG_INPUT_NON_EMPTY);
	}

	public void empty() {
		selenium.type(LOC_INPUT_TEXT, "");
		selenium.fireEvent(LOC_INPUT_TEXT, Event.KEYUP);

		waitFor(Wait.DEFAULT_INTERVAL);
		waitForTextEquals(LOC_INPUT_TEXT, "");
	}

	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("Ajax Support");

		scrollIntoView(LOC_INPUT_TEXT, true);
	}
}
