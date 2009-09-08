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

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class SelectingTestCase extends AbstractExtendedDataTableTestCase {

	private final String LOC_TR_SELECTED = getLoc("TR_SELECTED");
	private final String LOC_SELECT_SELECTION_MODE = getLoc("SELECT_SELECTION_MODE");

	private final String MSG_OPTION_SELECTION_NONE = getMsg("OPTION_SELECTION_NONE");
	private final String MSG_OPTION_SELECTION_SINGLE = getMsg("OPTION_SELECTION_SINGLE");
	private final String MSG_OPTION_SELECTION_MULTI = getMsg("OPTION_SELECTION_MULTI");
	private final String MSG_INPUT_SINGLE_ROW = getMsg("INPUT_SINGLE_ROW");
	private final String MSG_INPUT_MULTIPLE_ROWS = getMsg("INPUT_MULTIPLE_ROWS");
	private final String MSG_OUTPUT_MODE_NONE = getMsg("OUTPUT_MODE_NONE");
	private final String MSG_OUTPUT_SINGLE_ROW_IN_SINGLE_MODE = getMsg("OUTPUT_SINGLE_ROW_IN_SINGLE_MODE");
	private final String MSG_OUTPUT_MULTIPLE_ROWS_IN_SINGLE_MODE = getMsg("OUTPUT_MULTIPLE_ROWS_IN_SINGLE_MODE");
	private final String MSG_OUTPUT_SINGLE_ROW_IN_MULTI_MODE = getMsg("OUTPUT_SINGLE_ROW_IN_MULTI_MODE");
	private final String MSG_OUTPUT_MULTIPLE_ROWS_IN_MULTI_MODE = getMsg("OUTPUT_MULTIPLE_ROWS_IN_MULTI_MODE");

	/**
	 * Checks single and multiple selection in selection-mode "none". Checks
	 * that no rows will select.
	 */
	@Test
	public void testSelectionModeNone() {
		selectMode(LOC_SELECT_SELECTION_MODE, MSG_OPTION_SELECTION_NONE);

		int[] rows, selectedRows;

		rows = getRowSelection(MSG_INPUT_SINGLE_ROW);
		multiSelection(rows);
		selectedRows = getRowSelection(MSG_OUTPUT_MODE_NONE);
		checkSelection(selectedRows);

		loadPage();

		selectMode(LOC_SELECT_SELECTION_MODE, MSG_OPTION_SELECTION_NONE);

		rows = getRowSelection(MSG_INPUT_MULTIPLE_ROWS);
		multiSelection(rows);
		selectedRows = getRowSelection(MSG_OUTPUT_MODE_NONE);
		checkSelection(selectedRows);
	}

	/**
	 * Checks single and multiple row selection in selection-mode "single".
	 * Checks that only first selected row is selected right.
	 */
	@Test
	public void testSelectionModeSingle() {
		selectMode(LOC_SELECT_SELECTION_MODE, MSG_OPTION_SELECTION_SINGLE);

		int[] rows, selectedRows;

		rows = getRowSelection(MSG_INPUT_SINGLE_ROW);
		multiSelection(rows);
		selectedRows = getRowSelection(MSG_OUTPUT_SINGLE_ROW_IN_SINGLE_MODE);
		checkSelection(selectedRows);

		loadPage();

		selectMode(LOC_SELECT_SELECTION_MODE, MSG_OPTION_SELECTION_SINGLE);

		rows = getRowSelection(MSG_INPUT_MULTIPLE_ROWS);
		multiSelection(rows);
		selectedRows = getRowSelection(MSG_OUTPUT_MULTIPLE_ROWS_IN_SINGLE_MODE);
		checkSelection(selectedRows);
	}

	/**
	 * Checks single and multiple row selection in selection-mode "multi".
	 * Checks that all selected rows will be selected right.
	 */
	@Test
	public void testSelectionModeMulti() {
		selectMode(LOC_SELECT_SELECTION_MODE, MSG_OPTION_SELECTION_MULTI);

		int[] rows, selectedRows;

		rows = getRowSelection(MSG_INPUT_SINGLE_ROW);
		multiSelection(rows);
		selectedRows = getRowSelection(MSG_OUTPUT_SINGLE_ROW_IN_MULTI_MODE);
		checkSelection(selectedRows);

		loadPage();

		selectMode(LOC_SELECT_SELECTION_MODE, MSG_OPTION_SELECTION_MULTI);

		rows = getRowSelection(MSG_INPUT_MULTIPLE_ROWS);
		multiSelection(rows);
		selectedRows = getRowSelection(MSG_OUTPUT_MULTIPLE_ROWS_IN_MULTI_MODE);
		checkSelection(selectedRows);
	}

	private void checkSelection(int[] rows) {
		checkSelection(rows, true);
		checkSelection(rows, false);
	}

	private void multiSelection(int[] rows) {
		int column = 2;

		for (int i = 0; i < rows.length; i++) {
			final int row = rows[i];
			String cell = format(LOC_TD_PREFORMATTED, row, column);

			if (i > 0) {
				selenium.controlKeyDown();
			}

			selenium.click(cell);

			if (i > 0) {
				selenium.controlKeyUp();
			}
		}
	}

	private void checkSelection(int[] rows, boolean positiveComparison) {
		String comparison = (positiveComparison) ? "position()=" : "position()!=";
		String conjuction = (positiveComparison) ? " or " : " and ";

		String condition = " and (" + comparison + StringUtils.join(ArrayUtils.toObject(rows), conjuction + comparison)
				+ ")";

		if (rows.length == 0) {
			condition = "";
		}

		int count = selenium.getXpathCount(format(LOC_TR_SELECTED, condition)).intValue();

		assertTrue(positiveComparison ? count == rows.length : count == 0);
	}

	private int[] getRowSelection(String message) {
		String[] tokens = StringUtils.splitPreserveAllTokens(message, ',');
		int[] rows = new int[tokens.length];

		for (int i = 0; i < tokens.length; i++) {
			rows[i] = Integer.valueOf(tokens[i]);
		}

		return rows;
	}
}
