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
import org.jboss.test.selenium.waiting.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EditTableWithModalPanelTestCase extends AbstractDataIterationTestCase {

	private final int MSG_ROW = 2;

	private final String LOC_LINK_EDIT = formatLoc("LINK_EDIT", MSG_ROW);
	private final String LOC_LINK_DELETE = formatLoc("LINK_DELETE", MSG_ROW);
	private final String LOC_INPUT_PRICE = getLoc("INPUT_PRICE");
	private final String LOC_OUTPUT_PRICE_PREFORMATTED = getLoc("OUTPUT_PRICE_PREFORMATTED");
	private final String LOC_OUTPUT_PRICE = format(LOC_OUTPUT_PRICE_PREFORMATTED, MSG_ROW);
	private final String LOC_OUTPUT_PRICE_ABOVE = format(LOC_OUTPUT_PRICE_PREFORMATTED, MSG_ROW - 1);
	private final String LOC_OUTPUT_PRICE_BELLOW = format(LOC_OUTPUT_PRICE_PREFORMATTED, MSG_ROW + 1);
	private final String LOC_PANEL_EDIT = getLoc("PANEL_EDIT");
	private final String LOC_PANEL_DELETE = getLoc("PANEL_DELETE");
	private final String LOC_BUTTON_EDIT_STORE = getLoc("BUTTON_EDIT_STORE");
	private final String LOC_BUTTON_DELETE_YES = getLoc("BUTTON_DELETE_YES");
	private final String LOC_BUTTON_DELETE_CANCEL = getLoc("BUTTON_DELETE_CANCEL");

	private final String MSG_TAB_TO_OPEN = getMsg("TAB_TO_OPEN");

	/**
	 * Edit value of row using modal panel and checks that changes is durable
	 * when moving through pages.
	 */
	@Test
	public void testEditableTableWithModalPanelEdit() {

		// get a initial value of selected row
		int rowValue = Integer.valueOf(selenium.getText(LOC_OUTPUT_PRICE));

		// open and wait for editable modal panel
		selenium.click(LOC_LINK_EDIT);

		Wait.failWith("Editable modal panel was never displayed").until(new Condition() {
			public boolean isTrue() {
				return !"none".equals(getStyle(LOC_PANEL_EDIT, "display"));
			}
		});

		// check that value in input is same like initial
		assertEquals(selenium.getValue(LOC_INPUT_PRICE), Integer.toString(rowValue),
				"Value in input changed unexpectedly");

		// increase price in input
		rowValue += 2;
		selenium.type(LOC_INPUT_PRICE, Integer.toString(rowValue));

		// store new price and wait for modal panel disappears
		selenium.click(LOC_BUTTON_EDIT_STORE);

		Wait.failWith("Modal panel never disappeared").until(new Condition() {
			public boolean isTrue() {
				return "none".equals(getStyle(LOC_PANEL_EDIT, "display"));
			}
		});

		// check that new price was stored successfully
		assertEquals(selenium.getText(LOC_OUTPUT_PRICE), Integer.toString(rowValue),
				"New value of price was not stored successfully");

		// goto next page and then back to first
		gotoNextAndThenBack();

		// check that new price is shown right after page movement
		assertEquals(selenium.getText(LOC_OUTPUT_PRICE), Integer.toString(rowValue),
				"The changed value wasn't stored durable so changed when going throught pages");
	}

	/**
	 * Delete row using modal panel and checks new row and checks that changes
	 * endures when moving through pages.
	 */
	@Test
	public void testEditableTableWithModalPanelDelete() {

		// get a initial value of selected row and its neighbours
		String rowValue = selenium.getText(LOC_OUTPUT_PRICE);
		String rowAboveValue = selenium.getText(LOC_OUTPUT_PRICE_ABOVE);
		String rowBellowValue = selenium.getText(LOC_OUTPUT_PRICE_BELLOW);

		// open delete modal panel
		selenium.click(LOC_LINK_DELETE);

		Wait.failWith("Delete modal panel never displayed").until(new Condition() {
			public boolean isTrue() {
				return !"none".equals(getStyle(LOC_PANEL_DELETE, "display"));
			}
		});

		// try to cancel delete action
		selenium.click(LOC_BUTTON_DELETE_CANCEL);

		Wait.failWith("Modal panel didn't disappeared when cancelling delete action").until(new Condition() {
			public boolean isTrue() {
				return "none".equals(getStyle(LOC_PANEL_DELETE, "display"));
			}
		});

		// check that nothing happened with data model
		assertEquals(selenium.getText(LOC_OUTPUT_PRICE), rowValue, "Selected row changed unexpectedly value");
		assertEquals(selenium.getText(LOC_OUTPUT_PRICE_ABOVE), rowAboveValue,
				"Row bellow selected row changed unexpectedly value");
		assertEquals(selenium.getText(LOC_OUTPUT_PRICE_BELLOW), rowBellowValue,
				"Row above selected row changed unexpectedly value");

		// try to open delete modal panel again
		selenium.click(LOC_LINK_DELETE);

		Wait.failWith("Delete modal panel never get displayed").until(new Condition() {
			public boolean isTrue() {
				return !"none".equals(getStyle(LOC_PANEL_DELETE, "display"));
			}
		});

		// try to confirm delete action
		selenium.click(LOC_BUTTON_DELETE_YES);

		Wait.failWith("Delete modal panel never disappeared when confirmed delete action").until(new Condition() {
			public boolean isTrue() {
				return "none".equals(getStyle(LOC_PANEL_DELETE, "display"));
			}
		});

		// check that value below moved to place where was value
		// and that nothing happened with value above
		assertEquals(selenium.getText(LOC_OUTPUT_PRICE), rowBellowValue,
				"Row bellow previously selected row didn't moved to place where was selected row");
		assertEquals(selenium.getText(LOC_OUTPUT_PRICE_ABOVE), rowAboveValue,
				"Row above previously selected row was unexpectedly changed");

		// goto next page and then back to first
		gotoNextAndThenBack();

		// check that value below moved to place where was value
		// and that nothing happened with value above
		assertEquals(selenium.getText(LOC_OUTPUT_PRICE), rowBellowValue,
				"The row bellow previously selected row was moved first but this change didn't endure a movement of pages");
		assertEquals(selenium.getText(LOC_OUTPUT_PRICE_ABOVE), rowAboveValue,
				"The row above previously selected row was not changed by deleting selected row but was changed by movement of pages");
	}

	private void gotoNextAndThenBack() {
		assertEquals((int) getActivePage(), 1, "Current page is not first page");

		// go to next page
		selenium.click(LOC_BUTTON_NEXT_PAGE);
		Wait.failWith("The table never moved to second page").until(new Condition() {
			public boolean isTrue() {
				return getActivePage() == 2;
			}
		});

		// go back to previous page
		selenium.click(LOC_BUTTON_PREVIOUS_PAGE);
		Wait.failWith("The table never moved to first page again").until(new Condition() {
			public boolean isTrue() {
				return getActivePage() == 1;
			}
		});
	}

	protected void loadPage() {
		openComponent("Data Table");
		openTab(MSG_TAB_TO_OPEN);

		scrollIntoView(LOC_TABLE_COMMON, true);
	}
}
