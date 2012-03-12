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
package org.jboss.richfaces.integrationTest.tableFiltering;

import static org.testng.Assert.*;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.waiting.Retrieve;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class TableFilteringTestCase extends AbstractDataIterationTestCase {

	private final String LOC_INPUT_STATE = getLoc("INPUT_STATE");
	private final String LOC_INPUT_CAPITAL = getLoc("INPUT_CAPITAL");
	private final String LOC_TD_STATE_PREFORMATTED = getLoc("TD_STATE_PREFORMATTED");
	private final String LOC_TD_CAPITAL_PREFORMATTED = getLoc("TD_CAPITAL_PREFORMATTED");
	private final String LOC_SELECT_TIMEZONE = getLoc("SELECT_TIMEZONE");
	private final String LOC_OPTION_SELECTED_TIMEZONE = getLoc("OPTION_SELECTED_TIMEZONE");
	private final String LOC_TD_TIMEZONE_PREFORMATTED = LOC_TD_CAPITAL_PREFORMATTED;

	private final String[] MSG_LIST_OF_STATE_PREFIXES = StringUtils.splitPreserveAllTokens(
			getMsg("LIST_OF_STATE_PREFIXES"), ',');
	private final String[] MSG_LIST_OF_CAPITAL_PREFIXES = StringUtils.splitPreserveAllTokens(
			getMsg("LIST_OF_CAPITAL_PREFIXES"), ',');
	private final String MSG_INPUT_TIMEZONE_PREFORMATTED = getMsg("INPUT_TIMEZONE_PREFORMATTED");
	private final String[] MSG_LIST_OF_PAIRS_STATE_TIMEZONE = StringUtils.split(getMsg("LIST_OF_PAIRS_STATE_TIMEZONE"),
			'|');

	/**
	 * Alternate between state and capital filter inputs and enter to each one
	 * predefined prefixes. Checks that only right prefixed data are contained
	 * in table.
	 */
	@Test
	public void builtInFilteringTest() {

		openTab("Built-In Filtering Usage");

		scrollIntoView(LOC_TABLE_COMMON, true);

		assertTrue(MSG_LIST_OF_STATE_PREFIXES.length == MSG_LIST_OF_CAPITAL_PREFIXES.length,
				"Length of state and capital prefixes array must equal");

		for (int i = 0; i < MSG_LIST_OF_STATE_PREFIXES.length; i++) {
			final String statePrefix = MSG_LIST_OF_STATE_PREFIXES[i];
			final String capitalPrefix = MSG_LIST_OF_CAPITAL_PREFIXES[i];

			// change state prefix
			String tableText = selenium.getText(LOC_TABLE_COMMON);

			selenium.type(LOC_INPUT_STATE, statePrefix);
			selenium.fireEvent(LOC_INPUT_STATE, Event.KEYUP);

			waitForTextChanges(LOC_TABLE_COMMON, tableText);

			checkFiltering();

			// change capital prefix
			tableText = selenium.getText(LOC_TABLE_COMMON);

			selenium.type(LOC_INPUT_CAPITAL, capitalPrefix);
			selenium.fireEvent(LOC_INPUT_CAPITAL, Event.KEYUP);

			waitForTextChanges(LOC_TABLE_COMMON, tableText);

			checkFiltering();
		}
	}

	/**
	 * Alternate between state input and timezone select filtering - enter to
	 * each one predefined prefix (resp. value). Checks that only column
	 * corresponding to set filter are displayed in whole table on all pages.
	 */
	@Test
	public void externalFilteringTest() {

		openTab("External Filtering Usage");

		scrollIntoView(LOC_TABLE_COMMON, true);

		for (int i = 0; i < MSG_LIST_OF_PAIRS_STATE_TIMEZONE.length; i++) {
			String[] pair = StringUtils.splitPreserveAllTokens(MSG_LIST_OF_PAIRS_STATE_TIMEZONE[i], ':');

			// change state prefix
			String tableText = selenium.getText(LOC_TABLE_COMMON);

			selenium.type(LOC_INPUT_STATE, pair[0]);
			selenium.fireEvent(LOC_INPUT_STATE, Event.KEYUP);

			Wait.dontFail().timeout(5000).waitForChange(tableText, retrieveTableText);

			checkExternalFiltering();

			// change time zone
			tableText = selenium.getText(LOC_TABLE_COMMON);

			selenium.select(LOC_SELECT_TIMEZONE, pair[1]);
			selenium.fireEvent(LOC_SELECT_TIMEZONE, Event.CHANGE);

			Wait.dontFail().timeout(5000).waitForChange(tableText, retrieveTableText);

			checkExternalFiltering();
		}
	}

	private Retrieve<String> retrieveTableText = new Retrieve<String>() {
		public String retrieve() {
			return selenium.getText(LOC_TABLE_COMMON);
		}
	};

	private void checkFiltering() {
		String statePrefix = selenium.getValue(LOC_INPUT_STATE);
		String capitalPrefix = selenium.getValue(LOC_INPUT_CAPITAL);

		int rows = getJQueryCount(format(LOC_TD_STATE_PREFORMATTED, 0));

		for (int row = 1; row <= rows; row++) {
			if (statePrefix.length() > 0) {
				String state = selenium.getText(format(LOC_TD_STATE_PREFORMATTED, row));
				assertTrue(state.startsWith(statePrefix));
			}
			if (capitalPrefix.length() > 0) {
				String capital = selenium.getText(format(LOC_TD_CAPITAL_PREFORMATTED, row));
				assertTrue(capital.startsWith(capitalPrefix));
			}
		}
	}

	private void checkExternalFiltering() {
		String statePrefix = selenium.getValue(LOC_INPUT_STATE);
		String selectedTimezone = selenium.getText(LOC_OPTION_SELECTED_TIMEZONE);

		int rows = getJQueryCount(format(LOC_TD_STATE_PREFORMATTED, 0));

		for (int row = 1; row <= rows; row++) {
			if (statePrefix.length() > 0) {
				String state = selenium.getText(format(LOC_TD_STATE_PREFORMATTED, row));
				assertTrue(state.startsWith(statePrefix));
			}
			String actualTimezone = selenium.getText(format(LOC_TD_TIMEZONE_PREFORMATTED, row));
			if (StringUtils.isNotBlank(selectedTimezone)) {
				String expected = format(MSG_INPUT_TIMEZONE_PREFORMATTED, selectedTimezone);
				assertEquals(expected, actualTimezone);
			} else {
				String prefix = format(MSG_INPUT_TIMEZONE_PREFORMATTED, "");
				actualTimezone.startsWith(prefix);
			}
		}
	}

	protected void loadPage() {
		openComponent("Table Filtering");
		
		selenium.allowNativeXpath("true");
	}
}
