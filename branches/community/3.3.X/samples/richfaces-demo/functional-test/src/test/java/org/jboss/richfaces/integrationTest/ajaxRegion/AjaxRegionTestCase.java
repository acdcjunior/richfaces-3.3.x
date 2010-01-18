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
package org.jboss.richfaces.integrationTest.ajaxRegion;

import static org.testng.Assert.*;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.waiting.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class AjaxRegionTestCase extends AbstractSeleniumRichfacesTestCase {
	private final String LOC_INPUT_TEST1_NAME1 = getLoc("INPUT_TEST1_NAME1");
	private final String LOC_INPUT_TEST1_JOB1 = getLoc("INPUT_TEST1_JOB1");
	private final String LOC_INPUT_TEST1_NAME2 = getLoc("INPUT_TEST1_NAME2");
	private final String LOC_INPUT_TEST2_NAME1 = getLoc("INPUT_TEST2_NAME1");
	private final String LOC_INPUT_TEST2_NAME2 = getLoc("INPUT_TEST2_NAME2");
	private final String LOC_OUTPUT_VALIDATION_MESSAGE = getLoc("OUTPUT_VALIDATION_MESSAGE");
	private final String LOC_OUTPUT_TEST1_TYPED_NAME = getLoc("OUTPUT_TEST1_TYPED_NAME");
	private final String LOC_OUTPUT_TEST2_TYPED_NAME = getLoc("OUTPUT_TEST2_TYPED_NAME");
	private final String LOC_OUTPUT_TEST2_INFLUENCED_TEXT_PREFORMATTED = getLoc("OUTPUT_TEST2_INFLUENCED_TEXT_PREFORMATTED");
	private final String LOC_OUTPUT_TEXT_DISAPPEAR = format(LOC_OUTPUT_TEST2_INFLUENCED_TEXT_PREFORMATTED, 3);
	private final String LOC_OUTPUT_TEXT_NOT_DISAPPER = format(LOC_OUTPUT_TEST2_INFLUENCED_TEXT_PREFORMATTED, 4);

	private final String MSG_INPUT_SAMPLE = getMsg("INPUT_SAMPLE");
	private final String MSG_OUTPUT_TYPED_NAME_PREFORMATTED = getMsg("OUTPUT_TYPED_NAME_PREFORMATTED");
	private final String MSG_MESSAGE_VALUE_REQUIRED = getMsg("MESSAGE_VALUE_REQUIRED");

	/**
	 * Test1 (group of 4 inputs) - When only first Name input will be changed,
	 * check that validation message appears and Typed name contains no output.
	 */
	@Test
	public void testName1AloneDontPassValidation() {
		name1AloneDontPassValidation();
	}

	/**
	 * Test1 (group of 4 inputs) - When name was changed, validation message
	 * appears and typed name wasn't changed, try to input first Job input -
	 * validation message should disappear and typed name should be changed
	 * right.
	 */
	@Test
	public void testJob1ChangeDontInfluenceName1ValidationError() {
		name1AloneDontPassValidation();
		job1ChangeInfluenceName1Validation();
	}

	/**
	 * Test1 (group of 4 inputs) - When job input is not blank, we can change
	 * name input as needed and no validation message should appear and typed
	 * name will change right.
	 */
	@Test
	public void testName1CanBeChangedAnywayIfJob1IsNotBlank() {
		name1AloneDontPassValidation();
		job1ChangeInfluenceName1Validation();
		name1CanBeChangedAnywayIfJob1IsNotBlank();
	}

	/**
	 * Test1 (group of 4 inputs) - Second column can be changed as needed and no
	 * validation message will appear and typed name will change right.
	 */
	@Test
	public void testSecondInputsCanBeChangedAnyway() {
		tryTypingToInput(LOC_INPUT_TEST1_NAME2);
	}

	/**
	 * Test types sample into input and checks that text under input will
	 * disappear when region will rerender.
	 */
	@Test
	public void testTextWillDisappear() {
		scrollIntoView(LOC_INPUT_TEST2_NAME1, true);
		
		assertTrue(StringUtils.isNotBlank(selenium.getText(LOC_OUTPUT_TEXT_DISAPPEAR)),
				"Text under first input should not be blank");

		selenium.type(LOC_INPUT_TEST2_NAME1, MSG_INPUT_SAMPLE);
		selenium.fireEvent(LOC_INPUT_TEST2_NAME1, Event.KEYUP);

		Wait.failWith("Typed name never changes after firing event on input").until(new Condition() {
			public boolean isTrue() {
				String expectedText = format(MSG_OUTPUT_TYPED_NAME_PREFORMATTED, input(MSG_INPUT_SAMPLE));
				return expectedText.equals(selenium.getText(LOC_OUTPUT_TEST2_TYPED_NAME));
			}
		});

		assertTrue(StringUtils.isBlank(selenium.getText(LOC_OUTPUT_TEXT_DISAPPEAR)),
				"Text didn't disappear as expected when input changed");
	}

	/**
	 * Test types sample into input and checks that text under input will not
	 * disappear when region rendered.
	 */
	@Test
	public void testTextWillNotDisappear() {
		scrollIntoView(LOC_INPUT_TEST2_NAME2, true);

		assertTrue(StringUtils.isNotBlank(selenium.getText(LOC_OUTPUT_TEXT_NOT_DISAPPER)),
				"Text under second input should not be blank");
		
		selenium.type(LOC_INPUT_TEST2_NAME2, MSG_INPUT_SAMPLE);
		selenium.fireEvent(LOC_INPUT_TEST2_NAME2, Event.KEYUP);

		Wait.failWith("Typed name never changes after firing event on input").until(new Condition() {
			public boolean isTrue() {
				String expectedText = format(MSG_OUTPUT_TYPED_NAME_PREFORMATTED, input(MSG_INPUT_SAMPLE));
				return expectedText.equals(selenium.getText(LOC_OUTPUT_TEST2_TYPED_NAME));
			}
		});

		assertTrue(StringUtils.isNotBlank(selenium.getText(LOC_OUTPUT_TEXT_NOT_DISAPPER)),
				"Text disappeared even it wasn't expected when input changed");
	}

	private void name1AloneDontPassValidation() {
		final String expectedTypedName = format(MSG_OUTPUT_TYPED_NAME_PREFORMATTED, "");

		scrollIntoView(LOC_INPUT_TEST1_NAME1, true);
		
		selenium.type(LOC_INPUT_TEST1_NAME1, MSG_INPUT_SAMPLE);
		selenium.fireEvent(LOC_INPUT_TEST1_NAME1, Event.KEYUP);

		Wait.failWith(format("Validation message never appears or doesn't contain '{0}'", MSG_MESSAGE_VALUE_REQUIRED))
				.until(new Condition() {
					public boolean isTrue() {
						if (!selenium.isElementPresent(LOC_OUTPUT_VALIDATION_MESSAGE))
							return false;
						return selenium.getText(LOC_OUTPUT_VALIDATION_MESSAGE).contains(MSG_MESSAGE_VALUE_REQUIRED);
					}
				});

		assertEquals(selenium.getText(LOC_OUTPUT_TEST1_TYPED_NAME), expectedTypedName,
				"Typed name should isn't blank as expected");
	}

	private void job1ChangeInfluenceName1Validation() {
		final String expectedTypedName = format(MSG_OUTPUT_TYPED_NAME_PREFORMATTED, input(MSG_INPUT_SAMPLE));

		assertTrue(selenium.getText(LOC_OUTPUT_VALIDATION_MESSAGE).contains(MSG_MESSAGE_VALUE_REQUIRED), format(
				"Validation message doesn't contain '{0}'", MSG_MESSAGE_VALUE_REQUIRED));

		selenium.type(LOC_INPUT_TEST1_JOB1, MSG_INPUT_SAMPLE);
		selenium.fireEvent(LOC_INPUT_TEST1_NAME1, Event.KEYUP);

		Wait.failWith("Validation message did not disappear").until(new Condition() {
			public boolean isTrue() {
				return !selenium.isElementPresent(LOC_OUTPUT_VALIDATION_MESSAGE);
			}
		});

		assertEquals(selenium.getText(LOC_OUTPUT_TEST1_TYPED_NAME), expectedTypedName, format(
				"Typed name does not contain value '{0}'", expectedTypedName));
	}

	private void name1CanBeChangedAnywayIfJob1IsNotBlank() {
		assertFalse(selenium.isElementPresent(LOC_OUTPUT_VALIDATION_MESSAGE),
				"Validation message should not be present");
		assertTrue(StringUtils.isNotBlank(selenium.getValue(LOC_INPUT_TEST1_JOB1)),
				"First Job input should not be blank");

		tryTypingToInput(LOC_INPUT_TEST1_NAME1);
	}

	private void tryTypingToInput(String locator) {
		scrollIntoView(locator, true);
		
		for (final String enterValue : new String[] { "", MSG_INPUT_SAMPLE, "" }) {
			final String expectedTypedName = format(MSG_OUTPUT_TYPED_NAME_PREFORMATTED, input(enterValue));

			selenium.type(locator, enterValue);
			selenium.fireEvent(locator, Event.KEYUP);

			Wait.failWith(format("Typed name did not changed to '{0}'", expectedTypedName)).until(new Condition() {
				public boolean isTrue() {
					return expectedTypedName.equals(selenium.getText(LOC_OUTPUT_TEST1_TYPED_NAME));
				}
			});

			assertFalse(selenium.isElementPresent(LOC_OUTPUT_VALIDATION_MESSAGE),
					"Validation message should not appear");
		}
	}

	private String input(String input) {
		if (StringUtils.isBlank(input)) {
			return "";
		} else {
			return " " + input;
		}
	}

	/**
	 * Opens specific component's page
	 */
	protected void loadPage() {
		openComponent("Ajax Region");
	}
}
