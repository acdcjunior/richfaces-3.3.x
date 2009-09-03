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
		final long startCount = selenium.getXpathCount(LOC_DIV_LOG_CONSOLE_ENTRY).longValue();

		selenium.type(LOC_INPUT_TEXT, MSG_INPUT_SAMPLE);
		selenium.fireEvent(LOC_INPUT_TEXT, Event.KEYUP);

		Wait.failWith("Count of log entries never increase").until(new Condition() {
			public boolean isTrue() {
				return selenium.getXpathCount(LOC_DIV_LOG_CONSOLE_ENTRY).longValue() > startCount;
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
					long count = -1;

					public boolean isTrue() {
						long actual = selenium.getXpathCount(LOC_DIV_LOG_CONSOLE_ENTRY).longValue();
						if (actual == count) {
							return true;
						}
						count = actual;
						return false;
					}
				});
	}

	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("Log");

		scrollIntoView(LOC_FIELDSET_HEADER, true);
	}
}
