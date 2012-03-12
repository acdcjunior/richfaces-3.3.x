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
	    Wait.failWith("Pushing was inactive but should be active").interval(200).timeout(10000).until(new Condition() {
            public boolean isTrue() {
                return isPushingActive();
            }
        });
	    
		final String oldOutput = selenium.getText(LOC_OUTPUT_TEXT);

		Wait.failWith("When waiting for text change, it never happen").interval(1000).timeout(20000).until(
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
	    Wait.failWith("Pushing was active but should be inactive").interval(200).timeout(10000).until(new Condition() {
            public boolean isTrue() {
                return !isPushingActive();
            }
        });
	    
		String expected = MSG_OUTPUT_PUSH_INACTIVE;

		for (int i = 0; i < 7; i++) {
			if (i > 0)
				waitFor(1000);
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

	protected void loadPage() {
		openComponent("Push");

		scrollIntoView(LOC_BUTTON_POLL_CONTROL, true);
	}
}
