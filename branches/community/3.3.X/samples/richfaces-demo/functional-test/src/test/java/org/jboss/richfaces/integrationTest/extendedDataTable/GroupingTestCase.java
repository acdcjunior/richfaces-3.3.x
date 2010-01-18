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
import org.jboss.test.selenium.dom.Event;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class GroupingTestCase extends AbstractExtendedDataTableTestCase {

	private final String LOC_DIV_CONTEXT_MENU_FOR_COLUMN_RELATIVE = getLoc("DIV_CONTEXT_MENU_FOR_COLUMN_RELATIVE");
	private final String LOC_MENU_ITEM_GROUP_BY_COLUMN = getLoc("MENU_ITEM_GROUP_BY_COLUMN");
	private final String LOC_TD_GROUP_PREFORMATTED = getLoc("TD_GROUP_PREFORMATTED");
	private final String LOC_TR_PREFORMATTED = getLoc("TR_PREFORMATTED");
	private final String LOC_BUTTON_MENU = format(LOC_DIV_CONTEXT_MENU_FOR_COLUMN_RELATIVE, LOC_TH_STATE);

	private final String MSG_TR_CLASS = getMsg("TR_CLASS");

	/**
	 * Group by state column and checks that each regular row is under group
	 * specified by nearest group row above.
	 */
	@Test
	public void testGrouping() {
		// use grouping by state
		selenium.fireEvent(LOC_TH_STATE, Event.MOUSEOVER);
		selenium.clickAt(LOC_BUTTON_MENU, "1,1");

		waitForElement(LOC_MENU_ITEM_GROUP_BY_COLUMN);
		selenium.click(LOC_MENU_ITEM_GROUP_BY_COLUMN);
		waitForSplash();
		
		final int rows = getJQueryCount(format(LOC_TR_PREFORMATTED, 0)) -1;
		
		String expectedGroup = null;

		for (int row = 1; row <= rows; row++) {
			if (row == 1 && browserIsInternetExplorer()) {
				continue;
			}
			
		    if (belongsClass(MSG_TR_CLASS, format(LOC_TR_PREFORMATTED, row))) {
				// table row is type group
				expectedGroup = selenium.getText(format(LOC_TD_GROUP_PREFORMATTED, row)).replace("State Name: ", "").replace("(1)", "");
			} else {
				// table row is regular data row
				assertNotNull(expectedGroup, format("First row in grouped table has to belong to class '{0}'",
						MSG_TR_CLASS));

				String actualGroup = selenium.getText(format(preformatColumn(LOC_TH_STATE), row));

				assertEquals(actualGroup, expectedGroup, format("Cell ('{0}', row {1}) doesn't belong to group '{2}'",
						actualGroup, row, expectedGroup));
			}
		}
	}
}
