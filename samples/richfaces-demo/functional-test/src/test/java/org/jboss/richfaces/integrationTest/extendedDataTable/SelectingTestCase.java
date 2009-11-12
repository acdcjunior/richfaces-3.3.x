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

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
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

    private static int rows = -1;

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

    private void checkSelection(int[] selectedRows) {
        if (rows == -1) {
            rows = getJQueryCount(LOC_TR_SELECTED);
        }

        int[] notSelectedRows = new int[rows];

        // create an array with all rows
        for (int i = 0; i < rows; i++) {
            notSelectedRows[i] = i + 1;
        }

        // create an array with not selected rows
        for (int row : selectedRows) {
            notSelectedRows = ArrayUtils.removeElement(notSelectedRows, row);
        }

        for (final int row : selectedRows) {
            Wait.timeout(3000).interval(100).failWith(format("Row nr. {0} should be selected.", row)).until(
                    new Condition() {
                        public boolean isTrue() {
                            return selenium.isElementPresent(format(LOC_TR_SELECTED, row));
                        }
                    });
        }
        for (final int row : notSelectedRows) {
            Wait.timeout(3000).interval(100).failWith(format("Row nr. {0} should not be selected.", row)).until(
                    new Condition() {
                        public boolean isTrue() {
                            return !selenium.isElementPresent(format(LOC_TR_SELECTED, row));
                        }
                    });
        }
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

    private int[] getRowSelection(String message) {
        String[] tokens = StringUtils.splitPreserveAllTokens(message, ',');
        int[] rows = new int[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            rows[i] = Integer.valueOf(tokens[i]);
        }

        return rows;
    }
}
