package org.jboss.richfaces.integrationTest.push;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class PushTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	private void openPage() {
		selenium.open(contextPath + "/richfaces/push.jsf?c=push&tab=usage");
		
		scrollIntoView(control, false);
	}

	private String control = getLoc("push--control");
	private String outputText = getLoc("push--output-text");

	@Test
	public void testPushingProgress() {
		openPage();
		
		setPushingStatus(true);

		pushingProgress();
	}

	@Test
	public void testPushingStop() {
		openPage();

		setPushingStatus(false);

		pushingStopped();
	}

	@Test
	public void testPushingStopAndStart() {
		openPage();

		setPushingStatus(false);

		pushingStopped();

		setPushingStatus(true);

		pushingProgress();
	}

	public void pushingProgress() {
		Assert.assertTrue(isPushingActive());

		final String old = selenium.getText(outputText);

		Wait.interval(2500).timeout(20000).until(new Condition() {
			public boolean isTrue() {
				String actual = selenium.getText(outputText);

				return !old.equals(actual);
			}
		});

		String uuid = StringUtils.removeStart(selenium.getText(outputText),
				getMess("push--active"));

		Assert.assertTrue(StringUtils.isNotBlank(uuid));
	}

	public void pushingStopped() {
		Assert.assertFalse(isPushingActive());

		String expected = getMess("push--inactive");
		
		for (int i = 0; i < 7; i++) {
			if (i > 0) waitFor(2500);
			String actual = selenium.getText(outputText);

			Assert.assertEquals(actual, expected);
		}
	}

	public void setPushingStatus(final boolean pushingActive) {
		if (pushingActive != isPushingActive()) {

			selenium.click(control);

			Wait.until(new Condition() {
				public boolean isTrue() {
					return pushingActive == isPushingActive();
				}
			});
		}
	}

	public boolean isPushingActive() {
		return !getMess("push--inactive").equals(selenium.getText(outputText));
	}
}
