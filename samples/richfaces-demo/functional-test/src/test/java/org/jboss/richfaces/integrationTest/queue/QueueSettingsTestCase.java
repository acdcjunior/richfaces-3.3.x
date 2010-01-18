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
package org.jboss.richfaces.integrationTest.queue;

import static org.testng.Assert.*;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.utils.array.ArrayTransform;
import org.jboss.test.selenium.waiting.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class QueueSettingsTestCase extends AbstractSeleniumRichfacesTestCase {
	private final String LOC_FIELDSET_HEADER = getLoc("FIELDSET_HEADER");
	private final String LOC_INPUT_TYPED_TEXT = getLoc("INPUT_TYPED_TEXT");
	private final String LOC_OUTPUT_TYPED_TEXT = getLoc("OUTPUT_TYPED_TEXT");
	private final String LOC_OUTPUT_EVENTS_COUNT = getLoc("OUTPUT_EVENTS_COUNT");
	private final String LOC_OUTPUT_REQUESTS_COUNT = getLoc("OUTPUT_REQUESTS_COUNT");
	private final String LOC_OUTPUT_DOM_UPDATES_COUNT = getLoc("OUTPUT_DOM_UPDATES_COUNT");
	private final String LOC_INPUT_REQUEST_DELAY = getLoc("INPUT_REQUEST_DELAY");
	private final String LOC_CHECKBOX_IGNORE_DUP_RESPONSES = getLoc("CHECKBOX_IGNORE_DUP_RESPONSES");
	private final String LOC_CHECKBOX_DISABLE_QUEUE = getLoc("CHECKBOX_DISABLE_QUEUE");
	private final String LOC_BUTTON_APPLY_SETTINGS = getLoc("BUTTON_APPLY_SETTINGS");

	private final String[] MSG_DELAY_LIST = StringUtils.split(getMsg("DELAY_LIST"), ',');
	private final String MSG_CHAR_FAST_TYPING = getMsg("MSG_CHAR_FAST_TYPING");

	/**
	 * Set queue queue disabled and test it with typing as fast is possible and
	 * check that after typing is counters set right: DOM updates count ==
	 * Events count, Requests count is zero (queue disabled)
	 */
	@Test
	public void testDisabledQueue() {
		setQueue(10, false, true);

		fastTyping();

		int eventsCount = Integer.valueOf(selenium.getText(LOC_OUTPUT_EVENTS_COUNT));
		int requestsCount = Integer.valueOf(selenium.getText(LOC_OUTPUT_REQUESTS_COUNT));
		int domUpdatesCount = Integer.valueOf(selenium.getText(LOC_OUTPUT_DOM_UPDATES_COUNT));

		assertEquals(0, requestsCount, "Requests count should be 0 (queue disabled)");
		assertTrue(eventsCount == domUpdatesCount, "Count of Events and Dom Updates should equal");
	}

	/**
	 * Set queue to NOT Ignore Duplicated Responses and test it with typing as
	 * fast is possible and check that after typing is counters set right: DOM
	 * updates count == Requests count, Requests count <= Events count
	 */
	@Test
	public void testDontIgnoreDuplicatedResponses() {
		setQueue(10, false, false);

		fastTyping();

		int eventsCount = Integer.valueOf(selenium.getText(LOC_OUTPUT_EVENTS_COUNT));
		int requestsCount = Integer.valueOf(selenium.getText(LOC_OUTPUT_REQUESTS_COUNT));
		int domUpdatesCount = Integer.valueOf(selenium.getText(LOC_OUTPUT_DOM_UPDATES_COUNT));

		assertTrue(requestsCount == domUpdatesCount, "Count of Requests and Dom Updates should equal");
		assertTrue(requestsCount <= eventsCount, "Count of Requests should be less than or equal Events count");
	}

	/**
	 * Set queue to Ignore Duplicated Responses and test it with typing as fast
	 * is possible and check that after typing is counters set right: DOM
	 * updates count <= Requests count, Requests count <= Events count
	 */
	@Test
	public void testIgnoreDuplicatedResponses() {
		setQueue(10, true, false);

		fastTyping();

		int eventsCount = Integer.valueOf(selenium.getText(LOC_OUTPUT_EVENTS_COUNT));
		int requestsCount = Integer.valueOf(selenium.getText(LOC_OUTPUT_REQUESTS_COUNT));
		int domUpdatesCount = Integer.valueOf(selenium.getText(LOC_OUTPUT_DOM_UPDATES_COUNT));

		assertTrue(domUpdatesCount <= requestsCount, "Count of Dom Updates should be less than or equal Requests count");
		assertTrue(requestsCount <= eventsCount, "Count of Requests should be less than or equal Events count");
	}

	/**
	 * Types characters and check that output will not change for defined time
	 * (queue delay) and when it actually change, check that it have value of
	 * input and that counters are printed right.
	 */
	@Test
	public void testRequestDelay() {
		Integer[] delays = stringsToInteger.transform(MSG_DELAY_LIST);

		for (final int delay : delays) {
			setQueue(delay, false, false);

			// initiate time borders for the test
			final long start = currentTime();
			final long end = start + 10000;
			// create a buffer for whole text in input
			final StringBuffer typedText = new StringBuffer();

			// type at least 3 and at most 100 characters and don't exhause
			// border of 10 sec. for this tests
			while (typedText.length() <= 3 || (typedText.length() <= 100 && currentTime() < end)) {
				final String lastText = typedText.toString();

				// type char '1-9' like last decimal char of buffer's length
				typedText.append(Integer.toString(typedText.length() % 10));
				selenium.type(LOC_INPUT_TYPED_TEXT, typedText.toString());

				final long eventTime = currentTime();
				selenium.fireEvent(LOC_INPUT_TYPED_TEXT, Event.KEYUP);

				// check that text will not change for defined time (delay)
				Wait.dontFail().interval(delay / 4).timeout(delay).until(new Condition() {
					public boolean isTrue() {
						final String actualText = selenium.getText(LOC_OUTPUT_TYPED_TEXT);
						long currentTime = currentTime();
						assertTrue(currentTime >= eventTime + delay || lastText.equals(actualText));
						return currentTime >= eventTime + delay;
					}
				});

				// wait for output actually changes to value of input
				waitForTextEquals(LOC_OUTPUT_TYPED_TEXT, typedText.toString());

				// check that all output counts are right
				String actualCount = Integer.toString(typedText.length());
				assertEquals(actualCount, selenium.getText(LOC_OUTPUT_EVENTS_COUNT));
				assertEquals(actualCount, selenium.getText(LOC_OUTPUT_REQUESTS_COUNT));
				assertEquals(actualCount, selenium.getText(LOC_OUTPUT_DOM_UPDATES_COUNT));
			}
		}
	}

	private final long currentTime() {
		return System.currentTimeMillis();
	}

	private void setQueue(int delay, boolean ignoreDuplicatedResponses, boolean disableQueue) {
		selenium.type(LOC_INPUT_REQUEST_DELAY, Integer.toString(delay));
		check(LOC_CHECKBOX_IGNORE_DUP_RESPONSES, ignoreDuplicatedResponses);
		check(LOC_CHECKBOX_DISABLE_QUEUE, disableQueue);
		
		selenium.click(LOC_BUTTON_APPLY_SETTINGS);
		selenium.waitForPageToLoad(Long.toString(Wait.DEFAULT_TIMEOUT));
		
		scrollIntoView(LOC_FIELDSET_HEADER, true);
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

		for (int i = 1; i <= 75; i++) {
			text.append(MSG_CHAR_FAST_TYPING);
			
			selenium.type(LOC_INPUT_TYPED_TEXT, text.toString());
			selenium.fireEvent(LOC_INPUT_TYPED_TEXT, Event.KEYUP);
		}

		waitModelUpdate.waitForTimeout();
	}

	private final ArrayTransform<String, Integer> stringsToInteger = new ArrayTransform<String, Integer>(Integer.class) {
		public Integer transformation(String source) {
			return Integer.valueOf(source);
		}
	};

	protected void loadPage() {
		openComponent("Queue");
		openTab("Queue Settings");

		scrollIntoView(LOC_FIELDSET_HEADER, true);
	}

}
