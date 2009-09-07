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
package org.jboss.richfaces.integrationTest.dataGrid;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class DataGridTestCase extends AbstractDataIterationTestCase {

	private final String LOC_TD_GRID_PREFORMATTED = getLoc("TD_GRID_PREFORMATTED");
	
	private final int MSG_COUNT_CELLS_PER_PAGE = Integer.valueOf(getMsg("COUNT_CELLS_PER_PAGE"));
	private final int MSG_COUNT_CELLS_PER_COLUMN = Integer.valueOf(getMsg("COUNT_CELLS_PER_COLUMN"));

	/**
	 * Go through all of pages and add all cells to set. Each new item is tested
	 * on absence in this set (each item must be unique in grid).
	 */
	@Test
	public void testFunctionality() {
		final Set<String> cellTexts = new HashSet<String>();

		int page;

		while ((page = getActivePage()) < getLastVisiblePage()) {
			if (!cellTexts.isEmpty()) {
				gotoPage(LOC_BUTTON_NEXT_PAGE);
			}

			final int cellCount = selenium.getXpathCount(format(LOC_TD_GRID_PREFORMATTED, 0, 0)).intValue();

			assertTrue(cellCount > 0 && cellCount <= MSG_COUNT_CELLS_PER_PAGE, format(
					"There should be at least one cell per page (page {0}), but no more than {1}", page,
					MSG_COUNT_CELLS_PER_PAGE));

			int rowCount = selenium.getXpathCount(format(LOC_TD_GRID_PREFORMATTED, 0, 1)).intValue();

			for (int row = 1; row <= rowCount; row++) {
				final int columnCount = selenium.getXpathCount(format(LOC_TD_GRID_PREFORMATTED, row, 0)).intValue();

				assertTrue(columnCount > 0 && columnCount <= MSG_COUNT_CELLS_PER_COLUMN, format(
						"There should be at least one cell per column (page {0}), but no more than {1}", page,
						MSG_COUNT_CELLS_PER_COLUMN));

				for (int column = 1; column <= columnCount; column++) {
					final String cellText = selenium.getText(format(LOC_TD_GRID_PREFORMATTED, row, column));

					if (cellTexts.contains(cellText)) {
						fail(format("The text of cell ('{0}') isn't unique in grid (page {1}, row {2}, column {3}",
								cellText, page, row, column));
					}

					cellTexts.add(cellText);
				}
			}
		}
	}
	
	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("Data Grid");
		scrollIntoView(LOC_TABLE_COMMON, true);
		selenium.allowNativeXpath("true");
	}
}
