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
package org.jboss.richfaces.integrationTest.dataScroller;

import static org.testng.Assert.*;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class DataScrollerTestCase extends AbstractDataIterationTestCase {
	
	private final String LOC_FIELDSET_HEADER = getLoc("FIELDSET_HEADER");

	private final String[] MSG_LIST_OF_PAGES = StringUtils.split(getMsg("LIST_OF_PAGES"), ',');
	private final String MSG_CLASS_SCROLLER_BUTTON_DISABLED = getMsg("CLASS_SCROLLER_BUTTON_DISABLED");
	private final String MSG_CLASS_SCROLLER_BUTTON_ENABLED = getMsg("CLASS_SCROLLER_BUTTON_ENABLED");

	/**
	 * Go through predefined set of numbered pages. Checks that after each
	 * movement are table text changed.
	 */
	@Test
	public void testNumberedPage() {
		String tableText = getTableText();

		for (String page : MSG_LIST_OF_PAGES) {
			gotoPage(format(LOC_BUTTON_NUMBERED_PAGE_PREFORMATTED, Integer.valueOf(page)));

			tableText = checkThatTextDiffersAndReturn(tableText);
		}

		gotoFirstPage();

		checkThatTextDiffersAndReturn(tableText);

		checkFirstPage();
	}

	/**
	 * Go to second page and try refresh page. Checks that active page is still
	 * the second page.
	 */
	@Test(dependsOnMethods = "testNumberedPage")
	public void testRemembersActivePage() {
		gotoFirstPage();

		Integer page = 2;

		gotoPage(format(LOC_BUTTON_NUMBERED_PAGE_PREFORMATTED, page));

		selenium.refresh();

		assertTrue(page.equals(getActivePage()));

		// TODO cannot just do openPage
		// openPage();
		// assertTrue(page.equals(getActivePage()));
	}

	/**
	 * Click on the last page button and checks that page is really last page
	 * (last page and next page buttons are disabled, ...)
	 */
	@Test(dependsOnMethods = "testNumberedPage")
	public void testLastPage() {
		gotoFirstPage();

		String tableText = getTableText();

		gotoPage(LOC_BUTTON_LAST_PAGE);

		checkThatTextDiffersAndReturn(tableText);

		checkLastPage();
	}

	/**
	 * Go to last page and then use first page button and checks that the first
	 * page is really active and the text changed when moving to first page.
	 */
	@Test(dependsOnMethods = "testLastPage")
	public void testFirstPage() {
		gotoFirstPage();

		gotoPage(LOC_BUTTON_LAST_PAGE);

		String tableText = getTableText();

		gotoPage(LOC_BUTTON_FIRST_PAGE);

		checkThatTextDiffersAndReturn(tableText);

		checkFirstPage();
	}

	/**
	 * Go through all pages using next button and checks that all buttons are in
	 * right state (enabled/disabled) and that text differs between all moving
	 * actions.
	 */
	@Test(dependsOnMethods = "testNumberedPage")
	public void testNextPage() {
		gotoFirstPage();

		String tableText = getTableText();

		for (int page = 2; page < getLastVisiblePage(); page++) {
			gotoPage(LOC_BUTTON_NEXT_PAGE);

			checkNonExtremePage();

			tableText = checkThatTextDiffersAndReturn(tableText);
		}

		gotoPage(LOC_BUTTON_NEXT_PAGE);

		checkThatTextDiffersAndReturn(tableText);

		checkLastPage();
	}

	/**
	 * Go through all pages in reverse order using previous button and checks
	 * that all buttons are in right state (enabled/disabled) and that text
	 * differs between all moving actions.
	 */
	@Test(dependsOnMethods = "testLastPage")
	public void testPreviousPage() {
		gotoFirstPage();

		gotoPage(LOC_BUTTON_LAST_PAGE);

		String tableText = getTableText();

		for (int page = getActivePage() - 1; page > 1; page--) {
			gotoPage(LOC_BUTTON_PREVIOUS_PAGE);

			checkNonExtremePage();

			tableText = checkThatTextDiffersAndReturn(tableText);
		}

		gotoPage(LOC_BUTTON_PREVIOUS_PAGE);

		checkThatTextDiffersAndReturn(tableText);

		checkFirstPage();
	}

	private void checkFirstPage() {
		assertTrue(belongsClass(MSG_CLASS_SCROLLER_BUTTON_DISABLED, LOC_BUTTON_FIRST_PAGE));
		assertTrue(belongsClass(MSG_CLASS_SCROLLER_BUTTON_DISABLED, LOC_BUTTON_PREVIOUS_PAGE));
		assertTrue(belongsClass(MSG_CLASS_SCROLLER_BUTTON_ENABLED, LOC_BUTTON_NEXT_PAGE));
		assertTrue(belongsClass(MSG_CLASS_SCROLLER_BUTTON_ENABLED, LOC_BUTTON_LAST_PAGE));
		assertTrue("1".equals(selenium.getText(LOC_OUTPUT_ACTIVE_PAGE)));
	}

	private void checkNonExtremePage() {
		assertTrue(belongsClass(MSG_CLASS_SCROLLER_BUTTON_ENABLED, LOC_BUTTON_FIRST_PAGE));
		assertTrue(belongsClass(MSG_CLASS_SCROLLER_BUTTON_ENABLED, LOC_BUTTON_PREVIOUS_PAGE));
		assertTrue(belongsClass(MSG_CLASS_SCROLLER_BUTTON_ENABLED, LOC_BUTTON_NEXT_PAGE));
		assertTrue(belongsClass(MSG_CLASS_SCROLLER_BUTTON_ENABLED, LOC_BUTTON_LAST_PAGE));
		String activePage = selenium.getText(LOC_OUTPUT_ACTIVE_PAGE);
		assertFalse("1".equals(activePage));
		assertFalse(getLastVisiblePage().toString().equals(activePage));
	}

	private void checkLastPage() {
		assertTrue(belongsClass(MSG_CLASS_SCROLLER_BUTTON_ENABLED, LOC_BUTTON_FIRST_PAGE));
		assertTrue(belongsClass(MSG_CLASS_SCROLLER_BUTTON_ENABLED, LOC_BUTTON_PREVIOUS_PAGE));
		assertTrue(belongsClass(MSG_CLASS_SCROLLER_BUTTON_DISABLED, LOC_BUTTON_NEXT_PAGE));
		assertTrue(belongsClass(MSG_CLASS_SCROLLER_BUTTON_DISABLED, LOC_BUTTON_LAST_PAGE));
		assertTrue(getLastVisiblePage().toString().equals(selenium.getText(LOC_OUTPUT_ACTIVE_PAGE)));
	}

	private void gotoFirstPage() {
		gotoPage(LOC_BUTTON_FIRST_PAGE);
	}

	private String checkThatTextDiffersAndReturn(String lastTableText) {
		String tableText = getTableText();
		assertFalse(lastTableText.equals(tableText));
		return tableText;
	}

	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("Data Scroller");
		scrollIntoView(LOC_FIELDSET_HEADER, true);
		selenium.allowNativeXpath("true");
	}
}
