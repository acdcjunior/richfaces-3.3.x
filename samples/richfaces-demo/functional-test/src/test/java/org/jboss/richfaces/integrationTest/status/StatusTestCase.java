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
package org.jboss.richfaces.integrationTest.status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.waiting.Condition;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class StatusTestCase extends AbstractSeleniumRichfacesTestCase {

	private final String LOC_FIELDSET_PAGE_PART_PREFORMATTED = getLoc("FIELDSET_PAGE_PART_PREFORMATTED");
	private final String LOC_OUTPUT_STATUS_MESSAGE_PREFORMATTED = getLoc("OUTPUT_STATUS_MESSAGE_PREFORMATTED");
	private final String LOC_BUTTON_REQUEST_PREFORMATTED = getLoc("BUTTON_REQUEST_PREFORMATTED");
	private final String LOC_INPUT_NAME = getLoc("INPUT_NAME");
	private final String LOC_INPUT_JOB = getLoc("INPUT_JOB");
	private final String LOC_OUTPUT_TEXT = getLoc("OUTPUT_TEXT");

	private final String MSG_STYLE_DISPLAY = getMsg("STYLE_DISPLAY");
	private final String MSG_PATTERN_NAME_JOB = getMsg("PATTERN_NAME_JOB");

	/**
	 * <p>
	 * This test requests update by pressing button, waiting for processing
	 * (indicates by status message's change) and finally waiting to be
	 * completed (status changes to initial state).
	 * </p>
	 * 
	 * <p>
	 * This version using text like a status message for user.
	 * </p>
	 */
	@Test
	public void testTextStatus() {
		doStatusTesting(1);
	}

	/**
	 * <p>
	 * For description see {@link #testTextStatus()}
	 * </p>
	 * 
	 * <p>
	 * This version using image like a status message for user.
	 * </p>
	 */
	@Test
	public void testImageStatus() {
		doStatusTesting(2);
	}

	/**
	 * <p>
	 * Implementation for two tests distinguished by testNumber.
	 * </p>
	 * 
	 * @param testNumber
	 *            version of test (1 or 2)
	 */
	private void doStatusTesting(final int testNumber) {
		scrollIntoView(format(LOC_FIELDSET_PAGE_PART_PREFORMATTED, testNumber), true);

		// PAGE COMPONENTS
		final String locOutputStatusMessage = format(LOC_OUTPUT_STATUS_MESSAGE_PREFORMATTED, testNumber);
		final String locButtonRequest = format(LOC_BUTTON_REQUEST_PREFORMATTED, testNumber);

		// get a initial style of status field
		final String initialStyle = getStyle(locOutputStatusMessage, MSG_STYLE_DISPLAY);

		selenium.click(locButtonRequest);

		// wait for style is changed to "processing" state indicates that
		// request is in progress
		waitModelUpdate.interval(10).failWith("Test timeouted when waiting for request moves to processing state.")
				.until(new Condition() {
					public boolean isTrue() {
						String actualStyle = getStyle(locOutputStatusMessage, MSG_STYLE_DISPLAY);
						return !initialStyle.equals(actualStyle);
					}
				});

		// wait for style is changed back to initial state after request is
		// complete
		waitModelUpdate.interval(10).failWith("Timeout when waiting for request moves to complete state.").until(
				new Condition() {
					public boolean isTrue() {
						String actual = getStyle(locOutputStatusMessage, MSG_STYLE_DISPLAY);
						return initialStyle.equals(actual);
					}
				});
	}

	/**
	 * <p>
	 * Alternates with typing 30 characters into two inputs and waiting for
	 * status to change properly.
	 * </p>
	 * 
	 * <p>
	 * Watches the correct output value.
	 * </p>
	 */
	// TODO investigate JavaScript injecting to fix testInputsStatus, see
	// https://jira.jboss.org/jira/browse/JBQA-2606
	// @Test
	public void testInputsStatus() {
		scrollIntoView(format(LOC_FIELDSET_PAGE_PART_PREFORMATTED, 3), true);

		// PAGE CONTROLS
		final String locOutputStatusMessage = format(LOC_OUTPUT_STATUS_MESSAGE_PREFORMATTED, 3);

		// init buffers for typed text for each input
		StringBuilder textName = new StringBuilder();
		StringBuilder textJob = new StringBuilder();

		// init pattern for getting name and job from output
		Pattern patternNameJob = Pattern.compile(MSG_PATTERN_NAME_JOB);

		// get a initial value of status message's style
		final String initialStyle = getStyle(locOutputStatusMessage, MSG_STYLE_DISPLAY);

		// cycle over 30 chars and alternating between two inputs
		for (int counter = 1; counter <= 30; counter++) {
			// select input and it's buffered text by state of counter
			final String selectedInput = (counter % 3 == 0) ? LOC_INPUT_NAME : LOC_INPUT_JOB;
			final StringBuilder selectedText = (counter % 3 == 0) ? textName : textJob;

			// add one char according to chars [0-9] given by cycle counter
			selectedText.append(counter % 10);

			selenium.type(selectedInput, selectedText.toString());
			selenium.fireEvent(selectedInput, Event.KEYUP);

			// wait for display-style changes to value different than initial
			waitForDisplayChanges(locOutputStatusMessage, initialStyle, false);

			// wait for display-style changes to initial value
			waitForDisplayChanges(locOutputStatusMessage, initialStyle, true);

			String outputText = selenium.getText(LOC_OUTPUT_TEXT);

			// matches output to pattern declared above?
			Matcher matcher = patternNameJob.matcher(outputText);
			if (matcher.matches()) {
				// output matches, name is in group(1) and job in group(2)
				assertEquals(matcher.group(1), textName.toString(), format(
						"Name output '{0}' doesn't equal to typed value '{1}'", matcher.group(1), textName));
				assertEquals(matcher.group(2), textJob.toString(), format(
						"Job output '{0}' doesn't equal to typed value '{1}'", matcher.group(2), textJob));
			} else {
				fail(format("Output '{0}' doesn't matches to Name/Job pattern '{1}'", outputText, patternNameJob
						.toString()));
			}
		}
	}

	/**
	 * Wait for display-style changes to specified initial (resp. other than
	 * initial) value depending on what we want.
	 * 
	 * @param locator
	 *            of element we want watch
	 * @param initialStyle
	 *            initial display-style value
	 * @param shouldEqualOldValue
	 *            if true, wait for element's display-style became initialStyle;
	 *            if false, wait for element's display-style became other than
	 *            initialStyle
	 */
	private void waitForDisplayChanges(final String locator, final String initialStyle,
			final boolean shouldEqualOldValue) {
		waitModelUpdate.interval(10).until(new Condition() {
			public boolean isTrue() {
				String actualStyle = getStyle(locator, MSG_STYLE_DISPLAY);
				return !shouldEqualOldValue ^ initialStyle.equals(actualStyle);
			}
		});
	}
	
	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("Status");
		openTab("Usage");
		
		selenium.allowNativeXpath("true");
	}
}
