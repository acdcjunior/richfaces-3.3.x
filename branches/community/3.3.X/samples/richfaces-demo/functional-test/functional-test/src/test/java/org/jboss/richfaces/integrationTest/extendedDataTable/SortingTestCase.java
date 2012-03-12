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
package org.jboss.richfaces.integrationTest.extendedDataTable;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class SortingTestCase extends AbstractExtendedDataTableTestCase {

	private final String[] LOC_TH_SORTING_TESTS = new String[] { LOC_TH_STATE, LOC_TH_CAPITAL };
	private final String[] LOC_TH_LABELS = new String[] { LOC_SPAN_STATE, LOC_SPAN_CAPITAL };
	private final String LOC_SELECT_SORT_MODE = getLoc("SELECT_SORT_MODE");
	
	private final String MSG_OPTION_SORT_SINGLE = getMsg("OPTION_SORT_SINGLE");
	private final String MSG_OPTION_SORT_MULTI = getMsg("OPTION_SORT_MULTI");

	/**
	 * Switch to single sorting mode and try sort by each column. Checks that
	 * sorted column is sorted right.
	 */
	@Test
	public void testSortingModeSingle() {
		selectMode(LOC_SELECT_SORT_MODE, MSG_OPTION_SORT_SINGLE);

		for (String columnHeader : LOC_TH_SORTING_TESTS) {
			// two iterations -- one for ascending and one for descending order
		    String columnText = columnHeader.equals(LOC_TH_STATE) ? LOC_SPAN_STATE : LOC_SPAN_CAPITAL;
		    
			for (int i = 0; i < 2; i++) {
				selenium.click(columnText);
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
			selenium.click(LOC_TH_LABELS[0]);
			waitForSplash();
			
			// two iterations -- one for ascending and one for descending order
			for (int j = 0; j < 2; j++) {
				selenium.click(LOC_TH_LABELS[1]);
				waitForSplash();

				checkSortingForColumnOrder(columnsPreformatted);
			}
		}
	}
}
