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
package org.jboss.richfaces.integrationTest.push;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.*;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class PushTestCase extends AbstractSeleniumRichfacesTestCase {
	private final String LOC_BUTTON_POLL_CONTROL = getLoc("BUTTON_POLL_CONTROL");
	private final String LOC_OUTPUT_TEXT = getLoc("OUTPUT_TEXT");

	private final String MSG_OUTPUT_PUSH_ACTIVE = getMsg("OUTPUT_PUSH_ACTIVE");
	private final String MSG_OUTPUT_PUSH_INACTIVE = getMsg("OUTPUT_PUSH_INACTIVE");

	/**
	 * Start pushing and checks that new values are really pushed.
	 */
	@Test
	public void testPushingProgress() {
		setPushingStatus(true);

		checkPushingProgress();
	}

	/**
	 * Stop pushing and checks that pushing isn't in progress.
	 */
	@Test
	public void testPushingStop() {
		setPushingStatus(false);

		checkPushingStopped();
	}

	/**
	 * Switches between active and inactive pushing states and checks that
	 * output behave appropriately.
	 */
	@Test
	public void testPushingStopAndStart() {
		setPushingStatus(false);

		checkPushingStopped();

		setPushingStatus(true);

		checkPushingProgress();

		setPushingStatus(false);

		checkPushingStopped();

		setPushingStatus(true);

		checkPushingProgress();
	}

	private void checkPushingProgress() {
		assertTrue(isPushingActive(), "Pushing was inactive but should be active");

		final String oldOutput = selenium.getText(LOC_OUTPUT_TEXT);

		Wait.failWith("When waiting for text change, it never happen").interval(2500).timeout(20000).until(
				new Condition() {
					public boolean isTrue() {
						String actualOutput = selenium.getText(LOC_OUTPUT_TEXT);

						return !oldOutput.equals(actualOutput);
					}
				});

		String uuid = selenium.getText(LOC_OUTPUT_TEXT);

		assertTrue(uuid.startsWith(MSG_OUTPUT_PUSH_ACTIVE));

		uuid = StringUtils.removeStart(uuid, MSG_OUTPUT_PUSH_ACTIVE);

		assertTrue(StringUtils.isNotBlank(uuid), "Generated UUID should not be blank");
	}

	private void checkPushingStopped() {
		assertFalse(isPushingActive(), "Pushing was active but expected to be inactive");

		String expected = MSG_OUTPUT_PUSH_INACTIVE;

		for (int i = 0; i < 7; i++) {
			if (i > 0)
				waitFor(2500);
			String actual = selenium.getText(LOC_OUTPUT_TEXT);

			assertEquals(actual, expected);
		}
	}

	private void setPushingStatus(final boolean requiredPushingStatus) {
		if (requiredPushingStatus != isPushingActive()) {

			selenium.click(LOC_BUTTON_POLL_CONTROL);

			Wait.until(new Condition() {
				public boolean isTrue() {
					return requiredPushingStatus == isPushingActive();
				}
			});
		}
	}

	private boolean isPushingActive() {
		return !MSG_OUTPUT_PUSH_INACTIVE.equals(selenium.getText(LOC_OUTPUT_TEXT));
	}

	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("Push");

		scrollIntoView(LOC_BUTTON_POLL_CONTROL, true);
	}
}
