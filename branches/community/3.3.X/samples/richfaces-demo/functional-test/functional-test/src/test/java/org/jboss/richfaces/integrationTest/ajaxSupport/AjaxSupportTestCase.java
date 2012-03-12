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
	private String LOC_OUTPUT_TEXT = getLoc("OUTPUT_TEXT");

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
		waitForTextEquals(LOC_OUTPUT_TEXT, MSG_INPUT_NON_EMPTY);
	}

	public void empty() {
		selenium.type(LOC_INPUT_TEXT, "");
		selenium.fireEvent(LOC_INPUT_TEXT, Event.KEYUP);

		waitFor(Wait.DEFAULT_INTERVAL);
		waitForTextEquals(LOC_INPUT_TEXT, "");
	}

	protected void loadPage() {
		openComponent("Ajax Support");

		scrollIntoView(LOC_INPUT_TEXT, true);
	}
}
