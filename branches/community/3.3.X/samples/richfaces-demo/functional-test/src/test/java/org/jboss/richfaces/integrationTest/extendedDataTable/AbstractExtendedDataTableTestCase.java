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

import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.BeforeMethod;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class AbstractExtendedDataTableTestCase extends AbstractDataIterationTestCase {

	protected final String LOC_TH_STATE = getLoc("extended-data-table--column-header--state");
	protected final String LOC_TH_CAPITAL = getLoc("extended-data-table--column-header--capital");
	protected final String LOC_TH_TIME_ZONE = getLoc("extended-data-table--column-header--time-zone");
	protected final String LOC_TH_FLAG = getLoc("extended-data-table--column-header--flag");
	protected final String LOC_TH_RELATIVE = getLoc("extended-data-table--relative-column-header--from-label");
	protected final String LOC_TD_PREFORMATTED = getLoc("extended-data-table--cells");
	private final String LOC_TABLE_EXTENDED = getLoc("extended-data-table--table");
	private final String LOC_DIV_SPLASH_SCREEN = getLoc("extended-data-table--splash-screen");
	private final String LOC_INPUT_COLUMN_FILTER = getLoc("extended-data-table--input--filter-column");

	private final String MSG_OPTION_SELECTION_MODE_PREFORMATTED = getMsg("extended-data-table--option-value--selection-mode-preformatted");

	@BeforeMethod
	protected void loadPage() {
		openComponent("Extended Data Table");
		openTab("Usage");
		scrollIntoView(LOC_TABLE_EXTENDED, true);
		selenium.allowNativeXpath("true");
	}

	/**
	 * Wait for splash screen indicating request of table rerendering disappears
	 */
	protected void waitForSplash() {
		Wait.dontFail().interval(1).timeout(2000).until(new Condition() {
			public boolean isTrue() {
				return selenium.isElementPresent(LOC_DIV_SPLASH_SCREEN);
			}
		});
		Wait.interval(1).timeout(2000).until(new Condition() {
			public boolean isTrue() {
				return !selenium.isElementPresent(LOC_DIV_SPLASH_SCREEN);
			}
		});
	}

	/**
	 * Selects given mode of selection (resp. sorting) and wait for table
	 * rerenders
	 * 
	 * @param selectLocator
	 *            locator of select input
	 * @param selectionMode
	 *            value of selection mode, which should be selected
	 */
	protected void selectMode(String selectLocator, String selectionMode) {
		if (!selectionMode.equals(selenium.getValue(selectLocator))) {
			selenium.select(selectLocator, format(MSG_OPTION_SELECTION_MODE_PREFORMATTED, selectionMode));
			waitForSplash();
		}
	}

	/**
	 * Get a actual position of column given by columnHeader (TH)
	 * 
	 * @param columnHeader
	 *            the locator of given TH
	 * @return the actual position (sequence) of column in table
	 */
	protected int getColumnIndex(String columnHeader) {
		return 1 + selenium.getElementIndex(format(LOC_TH_RELATIVE, columnHeader)).intValue();
	}

	/**
	 * Preformat column's rows for column given by columnHeader (TH)
	 * 
	 * Uses {@link getColumnIndex(String)}
	 * 
	 * @param columnHeader
	 *            the locator of given TH
	 * @return preformatted column rows' locators ready for use with format()
	 *         method
	 */
	protected String preformatColumn(String columnHeader) {
		int columnIndex = getColumnIndex(columnHeader);
		String columnPreformatted = format(LOC_TD_PREFORMATTED, Integer.MAX_VALUE, columnIndex);
		return columnPreformatted.replaceFirst(format("\\[{0,number}\\]", Integer.MAX_VALUE), "{0,choice,0#|1#[{0}]}");
	}

	/**
	 * Preformat filtering input's locator for column given by its columnHeader
	 * (TH)
	 * 
	 * @param columnHeader
	 *            the locator of given TH
	 * @return locator of filter input for given columnHeader
	 */
	protected String preformatFilterInput(String columnHeader) {
		int columnIndex = getColumnIndex(columnHeader);
		return format(LOC_INPUT_COLUMN_FILTER, columnIndex);
	}
}
