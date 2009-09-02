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
package org.jboss.richfaces.integrationTest.poll;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.*;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class PollTestCase extends AbstractSeleniumRichfacesTestCase {
	private final String LOC_BUTTON_POLL_CONTROL = getLoc("BUTTON_POLL_CONTROL");
	private final String LOC_OUTPUT_POLL_STATUS = getLoc("OUTPUT_POLL_STATUS");
	private final String LOC_OUTPUT_SERVER_DATE = getLoc("OUTPUT_SERVER_DATE");

	private final String MSG_POLLING_ACTIVE = getMsg("POLLING_ACTIVE");
	private final String MSG_POLLING_INACTIVE = getMsg("POLLING_INACTIVE");

	/**
	 * Set polling state to active and checks that status is showing as active
	 * and server date is really polled.
	 */
	@Test
	public void testPollingProgress() {
		setPollingStatus(true);

		checkPollingProgress();
	}

	/**
	 * Set polling state to inactive and checks that status is showing as
	 * inactive and server date isn't polled.
	 */
	@Test
	public void testPollingStop() {
		setPollingStatus(false);

		checkPollingStopped();
	}

	/**
	 * Switch between polling state active/inactive and checks that status is
	 * right and server date is/isn't polled.
	 */
	@Test
	public void testPollingStopAndStart() {
		setPollingStatus(false);

		checkPollingStopped();

		setPollingStatus(true);

		checkPollingProgress();

		setPollingStatus(false);

		checkPollingStopped();

		setPollingStatus(true);

		checkPollingProgress();
	}

	private void checkPollingProgress() {
		assertEquals(selenium.getText(LOC_OUTPUT_POLL_STATUS), MSG_POLLING_ACTIVE, "Polling status should be active");

		final String oldServerDate = selenium.getText(LOC_OUTPUT_SERVER_DATE);

		Wait.failWith("Server date didn't changed before timeout").timeout(3000).until(new Condition() {
			public boolean isTrue() {
				String currentServerDate = selenium.getText(LOC_OUTPUT_SERVER_DATE);

				return !oldServerDate.equals(currentServerDate);
			}
		});
	}

	private void checkPollingStopped() {
		assertEquals(selenium.getText(LOC_OUTPUT_POLL_STATUS), MSG_POLLING_INACTIVE,
				"Polling status should be inactive");

		String expected = selenium.getText(LOC_OUTPUT_SERVER_DATE);
		waitFor(2000);
		String actual = selenium.getText(LOC_OUTPUT_SERVER_DATE);

		assertEquals(actual, expected, format("Actual server date '{0}' should equal '{1}' when polling is inactive",
				actual, expected));
	}

	private void setPollingStatus(final boolean requiredPollingStatus) {
		// if polling status don't match required polling status, change it
		if (requiredPollingStatus != MSG_POLLING_ACTIVE.equals(selenium.getText(LOC_OUTPUT_POLL_STATUS))) {

			selenium.click(LOC_BUTTON_POLL_CONTROL);

			Wait.failWith("Polling status didn't change").until(new Condition() {
				public boolean isTrue() {
					return requiredPollingStatus == MSG_POLLING_ACTIVE.equals(selenium.getText(LOC_OUTPUT_POLL_STATUS));
				}
			});
		}
	}

	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("Poll");

		scrollIntoView(LOC_OUTPUT_POLL_STATUS, true);
	}
}
