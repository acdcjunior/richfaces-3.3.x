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
package org.jboss.richfaces.integrationTest.tableSorting;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class TableSortingTestCase extends AbstractDataIterationTestCase {

	private final String LOC_TH_STATE = getLoc("TH_STATE");
	private final String LOC_TH_CAPITAL = getLoc("TH_CAPITAL");
	private final String LOC_IMG_SRC_SORT_ICON_RELATIVE = getLoc("IMG_SRC_SORT_ICON_RELATIVE");
	private final String LOC_IMG_SRC_SORT_ICON_STATE = format(LOC_IMG_SRC_SORT_ICON_RELATIVE, LOC_TH_STATE);
	private final String LOC_IMG_SRC_SORT_ICON_CAPITAL = format(LOC_IMG_SRC_SORT_ICON_RELATIVE, LOC_TH_CAPITAL);
	private final String LOC_TD_STATE_PREFORMATTED = getLoc("TD_STATE_PREFORMATTED");
	private final String LOC_TD_CAPITAL_PREFORMATTED = getLoc("TD_CAPITAL_PREFORMATTED");
	private final String LOC_BUTTON_SORT = getLoc("BUTTON_SORT");
	private final String LOC_SELECT_COLUMN_PREFORMATTED = getLoc("SELECT_COLUMN_PREFORMATTED");
	private final String LOC_SELECT_ORDER_PREFORMATTED = getLoc("SELECT_ORDER_PREFORMATTED");
	private final String[] LOC_LIST_OF_TD_PREFORMATTED = new String[] { getLoc("TD_PREFORMATTED_1"),
			getLoc("TD_PREFORMATTED_2"), getLoc("TD_PREFORMATTED_3") };

	private final String[] MSG_LIST_OF_SORTED_COLUMNS = StringUtils.splitPreserveAllTokens(
			getMsg("LIST_OF_SORTED_COLUMNS"), '#');

	/**
	 * First sort by state column in normal (ascending) order by clicking arrows
	 * in column header, then sort by this arrow in reverse (descending) order.
	 * Then do that for the second column. After each sorting action checks that
	 * are the data sorted well.
	 */
	@Test
	public void testBuiltInSorting() {
		openTab("Built-in Sorting Feature");

		scrollIntoView(LOC_TH_STATE, true);

		// sort by state column
		String locIconSrc = selenium.getAttribute(LOC_IMG_SRC_SORT_ICON_STATE);

		selenium.click(LOC_TH_STATE);

		locIconSrc = waitForAttributeChangesAndReturn(LOC_IMG_SRC_SORT_ICON_STATE, locIconSrc);

		checkSorting(LOC_TD_STATE_PREFORMATTED);

		// sort by state column reverse order
		selenium.click(LOC_TH_STATE);

		waitForAttributeChanges(LOC_IMG_SRC_SORT_ICON_STATE, locIconSrc);

		checkSorting(LOC_TD_STATE_PREFORMATTED);

		// sort by capital column
		locIconSrc = selenium.getAttribute(LOC_IMG_SRC_SORT_ICON_CAPITAL);

		selenium.click(LOC_TH_CAPITAL);

		locIconSrc = waitForAttributeChangesAndReturn(LOC_IMG_SRC_SORT_ICON_CAPITAL, locIconSrc);

		checkSorting(LOC_TD_CAPITAL_PREFORMATTED);

		// sort by capital column reverse order
		selenium.click(LOC_TH_CAPITAL);

		waitForAttributeChanges(LOC_IMG_SRC_SORT_ICON_CAPITAL, locIconSrc);

		checkSorting(LOC_TD_CAPITAL_PREFORMATTED);
	}

	/**
	 * Select three columns to sort table in predefined orders and checks that
	 * after application of all settings are data sorted well.
	 */
	@Test
	public void testExternalSorting() {
		openTab("External Sorting");

		scrollIntoView(LOC_TABLE_COMMON, true);

		final int columns = MSG_LIST_OF_SORTED_COLUMNS.length;

		// define 3 columns sorting in table
		for (int column = 0; column < columns; column++) {
			String[] association = StringUtils.splitPreserveAllTokens(MSG_LIST_OF_SORTED_COLUMNS[column], '|');
			String msgLabel = association[0];
			String msgColumnName = association[1];
			String msgColumnOrder = association[2];

			selenium.select(format(LOC_SELECT_COLUMN_PREFORMATTED, msgLabel), msgColumnName);

			selenium.select(format(LOC_SELECT_ORDER_PREFORMATTED, msgLabel), msgColumnOrder);

			Wait.failWith("Sort table button never got enabled").until(new Condition() {
				public boolean isTrue() {
					return !selenium.isElementPresent(format("{0}/@disabled", LOC_BUTTON_SORT));
				}
			});
		}

		// check sorting
		String tableText = getTableText();

		selenium.click(LOC_BUTTON_SORT);
		waitForTextChanges(LOC_TABLE_COMMON, tableText);

		checkSortingForColumnOrder(LOC_LIST_OF_TD_PREFORMATTED);
	}

	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("Table Sorting");

		selenium.allowNativeXpath("true");
	}
}
