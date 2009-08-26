package org.jboss.richfaces.integrationTest.poll;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class PollTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	private void openPage() {
		selenium.open(contextPath + "/richfaces/poll.jsf?c=poll&tab=usage");
	}

	private String status = getLoc("poll--status");
	private String control = getLoc("poll--control");
	private String serverDate = getLoc("poll--server-date");

	@Test
	public void testPollingProgress() {
		openPage();
		
		setPollingStatus(true);

		pollingProgress();
	}

	@Test
	public void testPollingStop() {
		openPage();

		setPollingStatus(false);

		pollingStopped();
	}

	@Test
	public void testPollingStopAndStart() {
		openPage();

		setPollingStatus(false);

		pollingStopped();

		setPollingStatus(true);

		pollingProgress();
	}

	public void pollingProgress() {
		final String old = selenium.getText(serverDate);

		Wait.timeout(3000).until(new Condition() {
			public boolean isTrue() {
				String actual = selenium.getText(serverDate);

				return !old.equals(actual);
			}
		});
	}

	public void pollingStopped() {
		Assert.assertEquals(selenium.getText(status),
				getMess("poll--polling-inactive"));

		String expected = selenium.getText(serverDate);
		waitFor(2000);
		String actual = selenium.getText(serverDate);

		Assert.assertEquals(actual, expected);
	}

	public void setPollingStatus(final boolean pollingActive) {
		if (pollingActive != getMess("poll--polling-active").equals(
				selenium.getText(status))) {

			selenium.click(control);

			Wait.until(new Condition() {
				public boolean isTrue() {
					return pollingActive == getMess("poll--polling-active")
							.equals(selenium.getText(status));
				}
			});
		}
	}
}
