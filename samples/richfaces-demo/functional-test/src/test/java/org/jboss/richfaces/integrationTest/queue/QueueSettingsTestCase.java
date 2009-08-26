package org.jboss.richfaces.integrationTest.queue;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.utils.array.ArrayTransform;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class QueueSettingsTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.allowNativeXpath("true");

		selenium.open(format("{0}/{1}", contextPath, PAGE));

		scrollIntoView(header, true);
	}

	private final String PAGE = "richfaces/queue.jsf?c=queue&tab=queueSettings";
	private final String header = getLoc("queue-settings--header");
	private final String inputText = getLoc("queue-settings--input--text");
	private final String outputText = getLoc("queue-settings--output--text");
	private final String outputEventsCount = getLoc("queue-settings--output--events-count");
	private final String outputRequestsCount = getLoc("queue-settings--output--requests-count");
	private final String outputDOMUpdatesCount = getLoc("queue-settings--output--dom-updates-count");

//	@Test
	public void disabledQueueTest() {
		openPage();

		setQueue(10, false, true);
		fastTyping();

		int eventsCount = Integer.valueOf(selenium.getText(outputEventsCount));
		int requestsCount = Integer.valueOf(selenium
				.getText(outputRequestsCount));
		int domUpdatesCount = Integer.valueOf(selenium
				.getText(outputDOMUpdatesCount));

		Assert.assertEquals(eventsCount, domUpdatesCount);
		Assert.assertEquals(0, requestsCount);
	}

	@Test
	public void dontIgnoreDuplicatedResponsesTest() {
		openPage();

		setQueue(10, false, false);
		fastTyping();

		int eventsCount = Integer.valueOf(selenium.getText(outputEventsCount));
		int requestsCount = Integer.valueOf(selenium
				.getText(outputRequestsCount));
		int domUpdatesCount = Integer.valueOf(selenium
				.getText(outputDOMUpdatesCount));

		Assert.assertTrue(requestsCount <= eventsCount);
		Assert.assertEquals(requestsCount, domUpdatesCount);
	}

//	@Test
	public void ignoreDuplicatedResponsesTest() {
		openPage();

		setQueue(10, true, false);

		fastTyping();

		int eventsCount = Integer.valueOf(selenium.getText(outputEventsCount));
		int requestsCount = Integer.valueOf(selenium
				.getText(outputRequestsCount));
		int domUpdatesCount = Integer.valueOf(selenium
				.getText(outputDOMUpdatesCount));

		Assert.assertTrue(domUpdatesCount <= requestsCount);
		Assert.assertTrue(requestsCount <= eventsCount);
	}

//	@Test
	public void requestDelayTest() {
		openPage();

		Integer[] delays = new ArrayTransform<String, Integer>(Integer.class) {
			public Integer transformation(String source) {
				return Integer.valueOf(source);
			}
		}.transform(StringUtils.splitPreserveAllTokens(
				getMess("queue-settings--delays"), ','));

		for (final int delay : delays) {
			setQueue(delay, false, false);

			int count = 1;
			final long start = currentTime();
			long end = start + 10000;
			final StringBuffer text = new StringBuffer();

			while (count <= 3 || (count <= 100 && currentTime() < end)) {
				final String lastText = text.toString();
				text.append(Integer.toString(count % 10));
				selenium.type(inputText, text.toString());
				final long eventTime = currentTime();
				selenium.fireEvent(inputText, Event.KEYUP);
				Wait.dontFail().interval(delay / 4).timeout(delay).until(
						new Condition() {
							public boolean isTrue() {
								String actualText = selenium
										.getText(outputText);
								long currentTime = currentTime();
								Assert.assertTrue(currentTime >= eventTime
										+ delay
										|| lastText.equals(actualText));
								return currentTime >= eventTime + delay;
							}
						});
				waitForTextEquals(outputText, text.toString());

				String actualCount = Integer.toString(count);
				Assert.assertEquals(actualCount, selenium
						.getText(outputEventsCount));
				Assert.assertEquals(actualCount, selenium
						.getText(outputRequestsCount));
				Assert.assertEquals(actualCount, selenium
						.getText(outputDOMUpdatesCount));

				count++;
			}
		}
	}

	private final long currentTime() {
		return System.currentTimeMillis();
	}

	private void setQueue(int delay, boolean ignoreDuplicatedResponses,
			boolean disableQueue) {
		final String inputRequestDelay = getLoc("queue-settings--input--request-delay");
		final String checkboxIgnoreDupResponces = getLoc("queue-settings--checkbox--ignore-dup-responses");
		final String checkboxDisableQueue = getLoc("queue-settings--checkbox--disable-queue");
		final String buttonApply = getLoc("queue-settings--button--apply");

		selenium.type(inputRequestDelay, Integer.toString(delay));
		check(checkboxIgnoreDupResponces, ignoreDuplicatedResponses);
		check(checkboxDisableQueue, disableQueue);
		selenium.click(buttonApply);
		selenium.waitForPageToLoad(Long.toString(Wait.DEFAULT_TIMEOUT));
		scrollIntoView(header, true);
	}

	private void check(String locator, boolean check) {
		if (check) {
			selenium.check(locator);
		} else {
			selenium.uncheck(locator);
		}
	}

	private void fastTyping() {
		StringBuffer text = new StringBuffer();
		final String charToType = getMess("queue-settings--char-to-type");

		for (int i = 1; i <= 75; i++) {
			text.append(charToType);
			selenium.type(inputText, text.toString());
			selenium.fireEvent(inputText, Event.KEYUP);
		}

		waitModelUpdate.waitForTimeout();
	}
}
