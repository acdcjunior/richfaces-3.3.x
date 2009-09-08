/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and others contributors as indicated
 * by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */
package org.jboss.richfaces.integrationTest.extendedDataTable;

import static org.testng.Assert.*;

import org.apache.commons.lang.StringUtils;
import org.jboss.test.selenium.dom.Event;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class FilteringTestCase extends AbstractExtendedDataTableTestCase {

	private final String[] MSG_LIST_OF_STATE_PREFIXES = StringUtils.splitPreserveAllTokens(
			getMsg("extended-data-table--input--state-prefixes"), ',');
	private final String[] MSG_LIST_OF_CAPITAL_PREFIXES = StringUtils.splitPreserveAllTokens(
			getMsg("extended-data-table--input--capital-prefixes"), ',');

	/**
	 * Alternate between state and capital input by entering predefined prefixes
	 * which should be use as a filters for table. Checks that only right
	 * prefixed records are in table.
	 */
	@Test
	public void testFiltering() {
		final String locInputStateFilter = preformatFilterInput(LOC_TH_STATE);
		final String locInputCapitalFilter = preformatFilterInput(LOC_TH_CAPITAL);

		assertTrue(MSG_LIST_OF_STATE_PREFIXES.length == MSG_LIST_OF_CAPITAL_PREFIXES.length,
				"Length of state and capital prefixes must be same");

		for (int i = 0; i < MSG_LIST_OF_STATE_PREFIXES.length; i++) {
			final String msgStatePrefix = MSG_LIST_OF_STATE_PREFIXES[i];
			final String msgCapitalPrefix = MSG_LIST_OF_CAPITAL_PREFIXES[i];

			selenium.type(locInputStateFilter, msgStatePrefix);
			selenium.fireEvent(locInputStateFilter, Event.KEYUP);
			waitForSplash();
			checkFiltering();

			selenium.type(locInputCapitalFilter, msgCapitalPrefix);
			selenium.fireEvent(locInputCapitalFilter, Event.KEYUP);
			waitForSplash();
			checkFiltering();
		}
	}

	private void checkFiltering() {
		final String inputFilterState = preformatFilterInput(LOC_TH_STATE);
		final String inputFilterCapital = preformatFilterInput(LOC_TH_CAPITAL);
		final String cellsState = preformatColumn(LOC_TH_STATE);
		final String cellsCapital = preformatColumn(LOC_TH_CAPITAL);

		String statePrefix = selenium.getValue(inputFilterState);
		String capitalPrefix = selenium.getValue(inputFilterCapital);

		int rows = selenium.getXpathCount(format(cellsState, 0)).intValue();

		for (int row = 1; row <= rows; row++) {
			if (statePrefix.length() > 0) {
				String state = selenium.getText(format(cellsState, row));
				assertTrue(state.startsWith(statePrefix), format("'{0}' doesn't start with prefix '{1}'", state,
						statePrefix));
			}
			if (capitalPrefix.length() > 0) {
				String capital = selenium.getText(format(cellsCapital, row));
				assertTrue(capital.startsWith(capitalPrefix), format("'{0}' doesn't start with prefix '{1}'", capital,
						capitalPrefix));
			}
		}
	}
}
