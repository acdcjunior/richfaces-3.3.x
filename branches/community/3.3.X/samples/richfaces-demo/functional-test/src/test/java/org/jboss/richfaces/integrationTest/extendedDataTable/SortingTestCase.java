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
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class SortingTestCase extends AbstractExtendedDataTableTestCase {

	private final String[] LOC_TH_SORTING_TESTS = new String[] { LOC_TH_STATE, LOC_TH_CAPITAL };
	private final String LOC_SELECT_SORT_MODE = getLoc("extended-data-table--select--sort");
	
	private final String MSG_OPTION_SORT_SINGLE = getMsg("extended-data-table--option--sort-single");
	private final String MSG_OPTION_SORT_MULTI = getMsg("extended-data-table--option--sort-multi");

	/**
	 * Switch to single sorting mode and try sort by each column. Checks that
	 * sorted column is sorted right.
	 */
	@Test
	public void testSortingModeSingle() {
		selectMode(LOC_SELECT_SORT_MODE, MSG_OPTION_SORT_SINGLE);

		for (String columnHeader : LOC_TH_SORTING_TESTS) {
			// two iterations -- one for ascending and one for descending order
			for (int i = 0; i < 2; i++) {
				selenium.click(columnHeader);
				waitForSplash();

				String columnPreformatted = preformatColumn(columnHeader);
				checkSorting(columnPreformatted);
			}
		}
	}

	/**
	 * Switch to multi sorting mode and try sort by columns in sequence. Checks
	 * that sorted columns are sorted right.
	 */
	@Test
	public void testSortingModeMulti() {
		selectMode(LOC_SELECT_SORT_MODE, MSG_OPTION_SORT_MULTI);

		assertTrue(LOC_TH_SORTING_TESTS.length == 2, "There should be 2 columns to sort by");

		String[] columnsPreformatted = new String[] { preformatColumn(LOC_TH_SORTING_TESTS[0]),
				preformatColumn(LOC_TH_SORTING_TESTS[1]) };

		// two iterations -- one for ascending and one for descending order
		for (int i = 0; i < 2; i++) {
			selenium.click(LOC_TH_SORTING_TESTS[0]);
			waitForSplash();

			// two iterations -- one for ascending and one for descending order
			for (int j = 0; j < 2; j++) {
				selenium.click(LOC_TH_SORTING_TESTS[1]);
				waitForSplash();

				checkSortingForColumnOrder(columnsPreformatted);
			}
		}
	}
}
