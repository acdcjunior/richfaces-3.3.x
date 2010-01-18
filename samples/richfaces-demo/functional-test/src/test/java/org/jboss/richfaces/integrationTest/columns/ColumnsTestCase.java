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
package org.jboss.richfaces.integrationTest.columns;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.*;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class ColumnsTestCase extends AbstractSeleniumRichfacesTestCase {

	private final String LOC_TABLE = getLoc("TABLE");
	private final String LOC_TH_PREFORMATTED = getLoc("TH_PREFORMATTED");
	private final String LOC_TD_PREFORMATTED = getLoc("TD_PREFORMATTED");

	private final Pattern MSG_PATTERN_PRICE = Pattern.compile(getMsg("PATTERN_PRICE"));

	/**
	 * First get map of all rows, then sort by second column and checks that
	 * association of all rows is equal as on the start of test.
	 */
	@Test
	public void testSimpleSorting() {
		final int sortColumn = 2;

		String[][] map = getMapOfCells();

		final String tableBeforeChange = selenium.getText(LOC_TABLE);

		selenium.click(format(LOC_TH_PREFORMATTED, sortColumn));

		sortByColumn(map, sortColumn, false);

		Wait.dontFail().until(new Condition() {
			public boolean isTrue() {
				return !tableBeforeChange.equals(selenium.getText(LOC_TABLE));
			}
		});

		String[][] newMap = getMapOfCells();

		assertTrue(Arrays.deepEquals(map, newMap),
				"Association of rows isn't equal to association of rows on the start of the test");
	}

	/**
	 * First get map of all rows, then sort by forth column in reverse order and
	 * checks that association of all rows is equal as on the start of the test.
	 */
	@Test
	public void testReverseOrderSorting() {
		final int sortColumn = 4;

		String[][] map = getMapOfCells();

		final String contextBeforeChange = selenium.getText(LOC_TABLE);

		selenium.click(format(LOC_TH_PREFORMATTED, sortColumn));

		sortByColumn(map, sortColumn, true);

		Wait.dontFail().until(new Condition() {
			public boolean isTrue() {
				return !contextBeforeChange.equals(selenium.getText(LOC_TABLE));
			}
		});

		selenium.click(format(LOC_TH_PREFORMATTED, sortColumn));

		Wait.dontFail().until(new Condition() {
			public boolean isTrue() {
				return !contextBeforeChange.equals(selenium.getText(LOC_TABLE));
			}
		});

		String[][] newMap = getMapOfCells();

		assertTrue(Arrays.deepEquals(map, newMap),
				"Association of rows isn't equal to association of rows on the start of the test");
	}

	private String[][] getMapOfCells() {
		final int columns = getJQueryCount(format(LOC_TD_PREFORMATTED, 1, 0));
		final int rows = getJQueryCount(format(LOC_TD_PREFORMATTED, 0, 1));

		String[][] map = new String[rows][columns];

		for (int row = 1; row <= rows; row++) {
			map[row - 1] = new String[columns];
			for (int column = 1; column <= columns; column++) {
				String cell = format(LOC_TD_PREFORMATTED, row, column);
				map[row - 1][column - 1] = selenium.getText(cell);
			}
		}
		return map;
	}

	private void sortByColumn(final String[][] map, final int column, final boolean reverseOrder) {
		Arrays.sort(map, new Comparator<String[]>() {
			public int compare(String[] o1, String[] o2) {
				String s1 = o1[column - 1];
				String s2 = o2[column - 1];

				Matcher m1 = MSG_PATTERN_PRICE.matcher(s1);
				m1.find();
				int i1 = Integer.valueOf(m1.group(1));

				Matcher m2 = MSG_PATTERN_PRICE.matcher(s2);
				m2.find();
				int i2 = Integer.valueOf(m2.group(1));

				if (reverseOrder) {
					return i2 - i1;
				} else {
					return i1 - i2;
				}
			}
		});
	}

	protected void loadPage() {
		openComponent("Columns");
		scrollIntoView(LOC_TABLE, true);
		selenium.allowNativeXpath("true");
	}
}
