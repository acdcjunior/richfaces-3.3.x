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
package org.jboss.richfaces.integrationTest.outputPanel;

import static org.testng.Assert.*;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.waiting.Condition;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class OutputPanelTestCase extends AbstractSeleniumRichfacesTestCase {

	private String LOC_FIELDSET_HEADER = getLoc("FIELDSET_HEADER");
	private String LOC_INPUT_WRONG = getLoc("INPUT_WRONG");
	private String LOC_INPUT_CORRECT = getLoc("INPUT_CORRECT");
	private String LOC_OUTPUT_MESSAGE = getLoc("OUTPUT_MESSAGE");

	private String MSG_INPUT_CORRECT = getMsg("INPUT_CORRECT");
	private String MSG_INPUT_WRONG = getMsg("INPUT_WRONG");
	private String MSG_OUTPUT_VALIDATOR_ERROR = getMsg("OUTPUT_VALIDATOR_ERROR");

	/**
	 * Add correct text into wrong input, checks that no text and no error
	 * message appear.
	 */
	@Test
	public void testCorrectTextIntoWrongInput() {
		enterTextAndCheckOutputAndErrorMessage(LOC_INPUT_WRONG, MSG_INPUT_CORRECT, false, false);
	}

	/**
	 * Enter wrong text into wrong input, checks that no text and no error
	 * message appear.
	 */
	@Test
	public void testWrongTextIntoWrongInput() {
		enterTextAndCheckOutputAndErrorMessage(LOC_INPUT_WRONG, MSG_INPUT_WRONG, false, false);
	}

	/**
	 * Enter correct text into correct input, checks that text but no error
	 * message appear.
	 */
	@Test
	public void testCorrectTextIntoSuccessInput() {
		enterTextAndCheckOutputAndErrorMessage(LOC_INPUT_CORRECT, MSG_INPUT_CORRECT, true, false);
	}

	/**
	 * Enter wrong text into correct input, checks that no text but only error
	 * message appear.
	 */
	@Test
	public void testWrongTextIntoSuccessInput() {
		enterTextAndCheckOutputAndErrorMessage(LOC_INPUT_CORRECT, MSG_INPUT_WRONG, false, true);
	}

	private void enterTextAndCheckOutputAndErrorMessage(String locInput, final String msgText,
			final boolean textShouldAppear, final boolean errorMessageShouldAppear) {
		selenium.type(locInput, msgText);
		selenium.fireEvent(locInput, Event.KEYUP);

		waitModelUpdate.dontFail().until(new Condition() {
			public boolean isTrue() {
				if (errorMessageShouldAppear || textShouldAppear) {
					return selenium.getXpathCount(format(LOC_OUTPUT_MESSAGE, msgText)).intValue() > 0
							|| selenium.getXpathCount(format(LOC_OUTPUT_MESSAGE, MSG_OUTPUT_VALIDATOR_ERROR))
									.intValue() > 0;
				}
				return false;
			}
		});

		assertEquals(selenium.getXpathCount(format(LOC_OUTPUT_MESSAGE, msgText)), textShouldAppear ? 1 : 0,
				textShouldAppear ? "Text output should appear" : "No text output should appear");
		assertEquals(selenium.getXpathCount(format(LOC_OUTPUT_MESSAGE, MSG_OUTPUT_VALIDATOR_ERROR)),
				errorMessageShouldAppear ? 1 : 0, errorMessageShouldAppear ? "Error message should appear"
						: "No error message should appear");
	}

	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("Output Panel");

		scrollIntoView(LOC_FIELDSET_HEADER, true);
	}
}
