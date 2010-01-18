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
package org.jboss.richfaces.integrationTest.commandLink;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class CommandLinkTestCase extends AbstractSeleniumRichfacesTestCase {
	private final String LOC_INPUT_TEXT = getLoc("INPUT_TEXT");
	private final String LOC_OUTPUT_TEXT = getLoc("OUTPUT_TEXT");
	private final String LOC_LINK_SAY_HELLO = getLoc("LINK_SAY_HELLO");

	private final String MSG_INPUT_NON_EMPTY = getMsg("INPUT_NON_EMPTY");
	private final String MSG_OUTPUT_TEXT_PREFORMATTED = getMsg("OUTPUT_TEXT_PREFORMATTED");

	/**
	 * Test that after non-empty input will be shown hello message.
	 */
	@Test
	public void testNonEmpty() {
		nonEmpty();
	}

	/**
	 * Test that after empty input will not shown any message.
	 */
	@Test
	public void testEmpty() {
		empty();
	}

	/**
	 * Test that after switching between empty and non-empty input is hello
	 * message shown only if input is non-empty.
	 */
	@Test
	public void testInterleaving() {
		nonEmpty();
		empty();
		nonEmpty();
		empty();
	}

	private void nonEmpty() {
		final String expectedOutputText = format(MSG_OUTPUT_TEXT_PREFORMATTED, MSG_INPUT_NON_EMPTY);

		selenium.type(LOC_INPUT_TEXT, MSG_INPUT_NON_EMPTY);
		selenium.click(LOC_LINK_SAY_HELLO);

		waitFor(Wait.DEFAULT_INTERVAL);
		waitForTextEquals(LOC_OUTPUT_TEXT, expectedOutputText);
	}

	private void empty() {
		final String expectedOutputText = "";

		selenium.type(LOC_INPUT_TEXT, "");
		selenium.click(LOC_LINK_SAY_HELLO);

		waitFor(Wait.DEFAULT_INTERVAL);
		waitForTextEquals(LOC_OUTPUT_TEXT, expectedOutputText);
	}

	protected void loadPage() {
		openComponent("Command Link");

		scrollIntoView(LOC_LINK_SAY_HELLO, true);
	}
}
