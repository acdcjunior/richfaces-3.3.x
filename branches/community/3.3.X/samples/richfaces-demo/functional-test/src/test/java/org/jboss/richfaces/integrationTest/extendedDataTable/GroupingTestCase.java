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
import org.jboss.test.selenium.dom.Event;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class GroupingTestCase extends AbstractExtendedDataTableTestCase {

	private final String LOC_DIV_CONTEXT_MENU_FOR_COLUMN_RELATIVE = getLoc("extended-data-table--relative-div--context-menu-button");
	private final String LOC_MENU_ITEM_GROUP_BY_COLUMN = getLoc("extended-data-table--menu-item--group-by-column");
	private final String LOC_TD_GROUP_PREFORMATTED = getLoc("extended-data-table--table-row--group-text-preformatted");
	private final String LOC_TR_PREFORMATTED = getLoc("extended-data-table--table-row--regular-row-preformatted");
	private final String LOC_COLUMN_HEADER = format(LOC_TH_RELATIVE, LOC_TH_STATE);
	private final String LOC_BUTTON_MENU = format(LOC_DIV_CONTEXT_MENU_FOR_COLUMN_RELATIVE, LOC_COLUMN_HEADER);

	private final String MSG_TR_CLASS = getMsg("extended-data-table--class--group-row");

	/**
	 * Group by state column and checks that each regular row is under group
	 * specified by nearest group row above.
	 */
	@Test
	public void testGrouping() {
		// use grouping by state
		selenium.fireEvent(LOC_COLUMN_HEADER, Event.MOUSEOVER);
		selenium.clickAt(LOC_BUTTON_MENU, "1,1");

		waitForElement(LOC_MENU_ITEM_GROUP_BY_COLUMN);
		selenium.click(LOC_MENU_ITEM_GROUP_BY_COLUMN);
		waitForSplash();

		final int rows = selenium.getXpathCount(format(LOC_TR_PREFORMATTED, 0)).intValue();
		String expectedGroup = null;

		for (int row = 1, group = 0, tabular = 0; row <= rows; row++) {
			if (belongsClass(MSG_TR_CLASS, format(LOC_TR_PREFORMATTED, row))) {
				// table row is type group
				group++;

				expectedGroup = selenium.getText(format(LOC_TD_GROUP_PREFORMATTED, group));
			} else {
				// table row is regular data row
				tabular++;

				assertNotNull(expectedGroup, format("First row in grouped table has to belong to class '{0}'",
						MSG_TR_CLASS));

				String actualGroup = selenium.getText(format(preformatColumn(LOC_COLUMN_HEADER), tabular));

				assertEquals(actualGroup, expectedGroup, format("Cell ('{0}', row {1}) doesn't belong to group '{2}'",
						actualGroup, row, expectedGroup));
			}
		}
	}
}
