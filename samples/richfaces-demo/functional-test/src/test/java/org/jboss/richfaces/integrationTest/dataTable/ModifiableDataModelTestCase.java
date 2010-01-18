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
package org.jboss.richfaces.integrationTest.dataTable;

import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.waiting.*;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ModifiableDataModelTestCase extends AbstractDataIterationTestCase {

	private final String LOC_TH_KEY = getLoc("TH_KEY");
	private final String LOC_TD_KEY_PREFORMATTED = getLoc("TD_KEY_PREFORMATTED");
	private final String LOC_INPUT_ASSIGNEE = getLoc("INPUT_ASSIGNEE");
	private final String LOC_TD_ASSIGNEE_PREFORMATTED = getLoc("TD_ASSIGNEE_PREFORMATTED");

	private final String MSG_TAB_TO_OPEN = getMsg("TAB_TO_OPEN");
	private final String MSG_INPUT_ASSIGNEE = getMsg("INPUT_ASSIGNEE");
	private final String MSG_COUNT_ASSIGNEE_PAGES = getMsg("COUNT_ASSIGNEE_PAGES");
	private final String MSG_COUNT_ASSIGNEE_ROWS = getMsg("COUNT_ASSIGNEE_ROWS");
	private final String MSG_CLASS_ACTIVE_PAGE = getMsg("CLASS_ACTIVE_PAGE");

	/**
	 * Sort table by keys and checks that column key is sorted well on all pages
	 */
	@Test
	public void testSorting() {

		// clicks on "key" column-header to sort table by keys
		final String tableContentBeforeSorting = selenium.getText(LOC_TABLE_COMMON);

		selenium.click(LOC_TH_KEY);

		Wait.failWith("Table content never changed").dontFail().until(new Condition() {
			public boolean isTrue() {
				return !tableContentBeforeSorting.equals(selenium.getText(LOC_TABLE_COMMON));
			}
		});

		// check if keys on all pages are sorted in right way
		final int pageCount = getJQueryCount(format(LOC_BUTTON_NUMBERED_PAGE_PREFORMATTED, 0));

		String lastText = null; // remembers last cell text

		for (int page = 1; page <= pageCount; page++) {
			// switch to the wanted page
			final String locButtonPage = format(LOC_BUTTON_NUMBERED_PAGE_PREFORMATTED, page);

			selenium.click(locButtonPage);

			Wait.until(new Condition() {
				public boolean isTrue() {
					return belongsClass(MSG_CLASS_ACTIVE_PAGE, locButtonPage);
				}
			});

			// count how many rows (cells in one column) table have
			final int cellCount = getJQueryCount(format(LOC_TD_KEY_PREFORMATTED, 0));

			// checks that columns are correctly sorted
			for (int row = 1; row <= cellCount; row++) {
				final String locCell = format(LOC_TD_KEY_PREFORMATTED, row);

				String cellText = selenium.getText(locCell);

				if (lastText != null && cellText.compareToIgnoreCase(lastText) <= 0) {
					fail();
				}

				lastText = cellText;
			}
		}
	}

	/**
	 * Enter the search input to filter table and checks that only values
	 * containing this input is contained on all displayed pages
	 */
	@Test
	public void testFiltering() {

		// types a name to the assignee column input
		final String tableContentBeforeSorting = selenium.getText(LOC_TABLE_COMMON);

		selenium.type(LOC_INPUT_ASSIGNEE, MSG_INPUT_ASSIGNEE);
		selenium.fireEvent(LOC_INPUT_ASSIGNEE, Event.KEYUP);

		Wait.failWith("Table content never changed").dontFail().until(new Condition() {
			public boolean isTrue() {
				return !tableContentBeforeSorting.equals(selenium.getText(LOC_TABLE_COMMON));
			}
		});

		// check how many pages should be displayed
		int pageCount = getJQueryCount(format(LOC_BUTTON_NUMBERED_PAGE_PREFORMATTED, 0));
		assertEquals(pageCount, Integer.parseInt(MSG_COUNT_ASSIGNEE_PAGES));

		// checks how many columns should be displayed
		int cellCount = getJQueryCount(format(LOC_TD_KEY_PREFORMATTED, 0));
		assertEquals(cellCount, Integer.parseInt(MSG_COUNT_ASSIGNEE_ROWS));

		// checks that assignee column contains assignee name
		String assigneeName = selenium.getText(LOC_TD_ASSIGNEE_PREFORMATTED);
		assertTrue(assigneeName.contains(MSG_INPUT_ASSIGNEE));
	}

	protected void loadPage() {
		openComponent("Data Table");
		openTab(MSG_TAB_TO_OPEN);
		scrollIntoView(LOC_TABLE_COMMON, true);
	}
}
