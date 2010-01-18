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
package org.jboss.richfaces.integrationTest.graphValidator;

import static org.testng.Assert.*;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class GraphValidatorAfterModelUpdateTestCase extends AbstractSeleniumRichfacesTestCase {
	private final String LOC_FIELDSET_HEADER_ACTIVITIES = getLoc("LOC_FIELDSET_HEADER_ACTIVITIES");
	private final String LOC_BUTTON_SUBMIT_ACTIVITIES = getLoc("BUTTON_SUBMIT_ACTIVITIES");
	private final String LOC_OUTPUT_VALIDATION_MESSAGE = getLoc("OUTPUT_VALIDATION_MESSAGE");
	private final String LOC_CLASS_VALIDATION_MESSAGE = getLoc("CLASS_VALIDATION_MESSAGE");
	private final String LOC_INPUT_ACTIVITY_HOURS_PREFORMATTED = getLoc("INPUT_ACTIVITY_HOURS_PREFORMATTED");

	private final String MSG_CLASS_VALID = getMsg("CLASS_VALID");
	private final String MSG_CLASS_INVALID = getMsg("CLASS_INVALID");
	private final String MSG_INPUT_VALID = getMsg("INPUT_VALID");
	private final String MSG_INPUT_INVALID_TOO_GREAT = getMsg("INPUT_INVALID_TOO_GREAT");
	private final String MSG_INPUT_INVALID_SUM_TOO_GREAT = getMsg("INPUT_INVALID_SUM_TOO_GREAT");
	private final String MSG_OUTPUT_PLEASE_FILL_AT_LEAST_ONE_ENTRY = getMsg("OUTPUT_PLEASE_FILL_AT_LEAST_ONE_ENTRY");
	private final String MSG_OUTPUT_CHANGES_STORED_SUCCESSFULLY = getMsg("OUTPUT_CHANGES_STORED_SUCCESSFULLY");
	private final String MSG_OUTPUT_INVALID_VALUES = getMsg("OUTPUT_INVALID_VALUES");
	private final String MSG_OUTPUT_INVALID_SUM_TOO_GREAT = getMsg("OUTPUT_INVALID_SUM_TOO_GREAT");

	/**
	 * Do no changes to form and checks that validate message 'Please fill at
	 * least one entry' appear
	 */
	@Test
	public void testNoChangeIntoForm() {
		submitAndWaitForMessageAppears();

		validateMessages(MSG_CLASS_INVALID, MSG_OUTPUT_PLEASE_FILL_AT_LEAST_ONE_ENTRY);
	}

	/**
	 * Enter first input and checks that changes will store successfully
	 */
	@Test
	public void testChangeStoredSuccessfully() {
		typeAndSubmit(format(LOC_INPUT_ACTIVITY_HOURS_PREFORMATTED, 1), MSG_INPUT_VALID);

		validateMessages(MSG_CLASS_VALID, MSG_OUTPUT_CHANGES_STORED_SUCCESSFULLY);
	}

	/**
	 * Enter only one value that is too great and checks that validation message
	 * appear
	 */
	@Test
	public void testOneValueTooGreat() {
		typeAndSubmit(format(LOC_INPUT_ACTIVITY_HOURS_PREFORMATTED, 1), MSG_INPUT_INVALID_TOO_GREAT);

		validateMessages(MSG_CLASS_INVALID, MSG_OUTPUT_INVALID_VALUES);
	}

	/**
	 * Enter several values, which is in sum greater than allow maximum and
	 * checks that validation message appear.
	 */
	@Test
	public void testSumOfValuesTooGreat() {
		for (int i = 1; i <= 3; i++) {
			selenium.type(format(LOC_INPUT_ACTIVITY_HOURS_PREFORMATTED, i), MSG_INPUT_INVALID_SUM_TOO_GREAT);
		}
		submitAndWaitForMessageAppears();

		validateMessages(MSG_CLASS_INVALID, MSG_OUTPUT_INVALID_SUM_TOO_GREAT);
	}

	private void typeAndSubmit(String locator, String text) {
		selenium.type(locator, text);
		submitAndWaitForMessageAppears();
	}

	private void submitAndWaitForMessageAppears() {
		selenium.click(LOC_BUTTON_SUBMIT_ACTIVITIES);

		Wait.failWith("Validation message never appeared").until(new Condition() {
			public boolean isTrue() {
				return selenium.isElementPresent(LOC_OUTPUT_VALIDATION_MESSAGE);
			}
		});
	}

	private void validateMessages(String className, String text) {
		assertEquals(selenium.getAttribute(LOC_CLASS_VALIDATION_MESSAGE), className,
				"Validation message's class is invalid");
		assertEquals(selenium.getText(LOC_OUTPUT_VALIDATION_MESSAGE), text, "Given validation message isn't expected");
	}

	protected void loadPage() {
		openComponent("Graph Validator");

		scrollIntoView(LOC_FIELDSET_HEADER_ACTIVITIES, true);
	}
}
