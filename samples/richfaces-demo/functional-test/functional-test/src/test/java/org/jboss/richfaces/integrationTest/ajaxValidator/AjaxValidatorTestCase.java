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
package org.jboss.richfaces.integrationTest.ajaxValidator;

import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version test$Revision$
 */
public class AjaxValidatorTestCase extends AbstractAjaxValidatorTestCase {

	private final String LOC_INPUT_NAME = getLoc("INPUT_NAME");
	private final String LOC_INPUT_AGE = getLoc("INPUT_AGE");

	private final String MSG_INPUT_VALUE_IS_GREATER_THAN_MAXIMUM = getMsg("INPUT_VALUE_IS_GREATER_THAN_MAXIMUM");
	private final String MSG_INPUT_VALUE_IS_LESS_THAN_MINIMUM = getMsg("INPUT_VALUE_IS_LESS_THAN_MINIMUM");
	private final String MSG_INPUT_VALID_NAME = getMsg("INPUT_VALID_NAME");
	private final String MSG_INPUT_NOT_BETWEEN_EXPECTED_VALUES_BELLOW = getMsg("INPUT_NOT_BETWEEN_EXPECTED_VALUES_BELLOW");
	private final String MSG_INPUT_NOT_BETWEEN_EXPECTED_VALUES_ABOVE = getMsg("INPUT_NOT_BETWEEN_EXPECTED_VALUES_ABOVE");
	private final String MSG_INPUT_IS_NOT_NUMBER = getMsg("INPUT_IS_NOT_NUMBER");
	private final String MSG_INPUT_VALID_AGE = getMsg("INPUT_VALID_AGE");
	private final String MSG_OUTPUT_VALUE_REQUIRED_PREFORMATTED = getMsg("OUTPUT_VALUE_REQUIRED");
	private final String MSG_OUTPUT_VALUE_IS_LESS_THAN_MINIMUM = getMsg("OUTPUT_VALUE_IS_LESS_THAN_MINIMUM");
	private final String MSG_OUTPUT_VALUE_IS_GREATER_THAN_MAXIMUM_PREFORMATTED = getMsg("OUTPUT_VALUE_IS_GREATER_THAN_MAXIMUM_PREFORMATTED");
	private final String MSG_OUTPUT_IS_NOT_NUMBER_PREFORMATTED = getMsg("OUTPUT_IS_NOT_NUMBER_PREFORMATTED");
	private final String MSG_OUTPUT_NOT_BETWEEN_EXPECTED_VALUES_PREFORMATTED = getMsg("OUTPUT_NOT_BETWEEN_EXPECTED_VALUES_PREFORMATTED");

	/**
	 * Try type no chars in input name and checks that value required message
	 * will appear.
	 */
	@Test
	public void testNameValueRequired() {
		final String validationMessage = format(MSG_OUTPUT_VALUE_REQUIRED_PREFORMATTED, LOC_INPUT_NAME);
		typeAndBlur(LOC_INPUT_NAME, "");
		waitForTextEquals(getMessageFor(LOC_INPUT_NAME), validationMessage);
	}

	/**
	 * Try type string of length less than minimum length in name input and
	 * checks that validation message will appear.
	 */
	@Test
	public void testNameMinimumLength() {
		final String validationMessage = format(MSG_OUTPUT_VALUE_IS_LESS_THAN_MINIMUM, LOC_INPUT_NAME);
		typeAndBlur(LOC_INPUT_NAME, MSG_INPUT_VALUE_IS_LESS_THAN_MINIMUM);
		waitForTextEquals(getMessageFor(LOC_INPUT_NAME), validationMessage);
	}

	/**
	 * Try type string of length greater than maximum length in name input and
	 * checks that validation message will appear.
	 */
	@Test
	public void testNameMaximumLength() {
		final String validationMessage = format(MSG_OUTPUT_VALUE_IS_GREATER_THAN_MAXIMUM_PREFORMATTED, LOC_INPUT_NAME);
		typeAndBlur(LOC_INPUT_NAME, MSG_INPUT_VALUE_IS_GREATER_THAN_MAXIMUM);
		waitForTextEquals(getMessageFor(LOC_INPUT_NAME), validationMessage);
	}

	/**
	 * Try type the invalid name input and checks that after typing valid input
	 * will validation message disappear.
	 */
	@Test
	public void testNameMessageDisappers() {
		// first violate validation error
		testNameValueRequired();
		// then try valid input
		typeAndBlur(LOC_INPUT_NAME, MSG_INPUT_VALID_NAME);
		waitForTextEquals(getMessageFor(LOC_INPUT_NAME), "");
	}

	/**
	 * Try type no chars in age input and checks that the validation message
	 * value required will appear.
	 */
	@Test
	public void testAgeValueRequired() {
		final String validationMessage = format(MSG_OUTPUT_VALUE_REQUIRED_PREFORMATTED, LOC_INPUT_AGE);
		typeAndBlur(LOC_INPUT_AGE, "");
		waitForTextEquals(getMessageFor(LOC_INPUT_AGE), validationMessage);
	}

	/**
	 * Try input age less than minimum and checks that the validation message
	 * will appear.
	 */
	@Test
	public void testAgeMinimumValue() {
		final String validationMessage = format(MSG_OUTPUT_NOT_BETWEEN_EXPECTED_VALUES_PREFORMATTED, LOC_INPUT_AGE);
		typeAndBlur(LOC_INPUT_AGE, MSG_INPUT_NOT_BETWEEN_EXPECTED_VALUES_BELLOW);
		waitForTextEquals(getMessageFor(LOC_INPUT_AGE), validationMessage);
	}

	/**
	 * Try input age greater than maximum and checks that the validation message
	 * will appear.
	 */
	@Test
	public void testAgeMaximumValue() {
		final String validationMessage = format(MSG_OUTPUT_NOT_BETWEEN_EXPECTED_VALUES_PREFORMATTED, LOC_INPUT_AGE);
		typeAndBlur(LOC_INPUT_AGE, MSG_INPUT_NOT_BETWEEN_EXPECTED_VALUES_ABOVE);
		waitForTextEquals(getMessageFor(LOC_INPUT_AGE), validationMessage);
	}

	/**
	 * Try input age as non-Integer (alphabetical chars) and checks that
	 * validation message will appear.
	 */
	@Test
	public void testAgeIntegerOnly() {
		final String validationMessage = format(MSG_OUTPUT_IS_NOT_NUMBER_PREFORMATTED, LOC_INPUT_AGE);
		typeAndBlur(LOC_INPUT_AGE, MSG_INPUT_IS_NOT_NUMBER);
		waitForTextEquals(getMessageFor(LOC_INPUT_AGE), validationMessage);
	}

	/**
	 * Try input no input first and wait for validation message appears and then
	 * type a valid input and checks that validation message will disappear.
	 */
	@Test
	public void testAgeMessageDisappers() {
		// first violate validation error
		testAgeValueRequired();
		// then try valid input
		typeAndBlur(LOC_INPUT_AGE, MSG_INPUT_VALID_AGE);
		waitForTextEquals(getMessageFor(LOC_INPUT_AGE), "");
	}
}
