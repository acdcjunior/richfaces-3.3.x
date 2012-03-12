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
package org.jboss.richfaces.integrationTest.log;

import static org.testng.Assert.*;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class LogTestCase extends AbstractSeleniumRichfacesTestCase {

	private final String LOC_FIELDSET_HEADER = getLoc("FIELDSET_HEADER");
	private final String LOC_INPUT_TEXT = getLoc("INPUT_TEXT");
	private final String LOC_BUTTON_CLEAR = getLoc("BUTTON_CLEAR");
	private final String LOC_OUTPUT_LOG_CONSOLE = getLoc("OUTPUT_LOG_CONSOLE");
	private final String LOC_DIV_LOG_CONSOLE_ENTRY = getLoc("DIV_LOG_CONSOLE_ENTRY");

	private final String MSG_INPUT_SAMPLE = getMsg("INPUT_SAMPLE");
	private final String MSG_OUTPUT_DEBUG = getMsg("OUTPUT_DEBUG");
	private final String MSG_OUTPUT_CLEAR = getMsg("OUTPUT_CLEAR");

	/**
	 * Force event and wait for log contain specified text
	 */
	@Test
	public void testFillLog() {
		fillLog();
	}

	/**
	 * Force event, wait for log contain specified text and log entries count
	 * stabilizes and then tries to clear the log
	 */
	@Test
	public void testClearLog() {
		fillLog();
		waitForLogStabilize();
		clearLog();
	}

	/**
	 * Try fill the log and next clear and checks right behaviour
	 */
	@Test
	public void testRepeatingFillAndClear() {
		fillLog();
		waitForLogStabilize();
		clearLog();
		waitForLogStabilize();
		fillLog();
		waitForLogStabilize();
		clearLog();
	}

	private void fillLog() {
		final int startCount = getJQueryCount(LOC_DIV_LOG_CONSOLE_ENTRY);

		selenium.type(LOC_INPUT_TEXT, MSG_INPUT_SAMPLE);
		selenium.fireEvent(LOC_INPUT_TEXT, Event.KEYUP);

		Wait.failWith("Count of log entries never increase").until(new Condition() {
			public boolean isTrue() {
				return getJQueryCount(LOC_DIV_LOG_CONSOLE_ENTRY) > startCount;
			}
		});

		String consoleText = selenium.getText(LOC_OUTPUT_LOG_CONSOLE);

		assertTrue(consoleText.contains(MSG_OUTPUT_DEBUG), format("Console text should contain word '{0}'",
				MSG_OUTPUT_DEBUG));
	}

	private void clearLog() {
		selenium.click(LOC_BUTTON_CLEAR);

		Wait.failWith("Text of the console never get without entries (clear button only)").until(new Condition() {
			public boolean isTrue() {
				String consoleText = selenium.getText(LOC_OUTPUT_LOG_CONSOLE);
				return MSG_OUTPUT_CLEAR.equals(StringUtils.trim(consoleText));
			}
		});
	}

	private void waitForLogStabilize() {
		Wait.failWith("Log stabilized in entry count in given time").interval(3000).timeout(9000).until(
				new Condition() {
					int count = -1;

					public boolean isTrue() {
						int actual = getJQueryCount(LOC_DIV_LOG_CONSOLE_ENTRY);
						if (actual == count) {
							return true;
						}
						count = actual;
						return false;
					}
				});
	}

	protected void loadPage() {
		openComponent("Log");

		scrollIntoView(LOC_FIELDSET_HEADER, true);
	}
}
