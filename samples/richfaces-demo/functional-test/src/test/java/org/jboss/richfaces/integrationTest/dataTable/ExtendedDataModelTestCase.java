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

import static org.testng.Assert.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.jboss.test.selenium.waiting.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExtendedDataModelTestCase extends AbstractDataIterationTestCase {

	private final String LOC_TD_HIGHEST_BID = getLoc("TD_HIGHEST_BID");
	private final String LOC_TD_CELL_AMOUNT = getLoc("TD_CELL_AMOUNT");
	private final String LOC_INPUT_YOUR_BID = getLoc("INPUT_YOUR_BID");
	private final String LOC_LINK_PLACE_BID = getLoc("LINK_PLACE_BID");

	private final String MSG_TAB_TO_OPEN = getMsg("TAB_TO_OPEN");
	private final String MSG_MESSAGE_LOWER_BID = getMsg("MESSAGE_LOWER_BID");
	private final String MSG_INPUT_AMOUNT_PREFORMATTED = getMsg("INPUT_AMOUNT_PREFORMATTED");

	/**
	 * Get a actual highest bid and try to enter own bid less than highest. The
	 * validation message should appear. So try to enter higher bid than highest
	 * and checks that bid is saved like a new highest bid.
	 */
	@Test
	public void testExtendedDataTable() {
		final NumberFormat numberFormat = DecimalFormat.getInstance();

		// get a actual value of highest bid in specified row
		double highestBid;
		try {
			highestBid = numberFormat.parse(selenium.getText(LOC_TD_HIGHEST_BID).replace("$", "")).doubleValue();
		} catch (ParseException e) {
			fail("Cannot parse a highest bid", e);
			return;
		}

		// asserts blank value of "amount" cell
		assertTrue(StringUtils.isBlank(selenium.getText(LOC_TD_CELL_AMOUNT)), "Amount should be blank");

		// fill new highest bid like a number smaller than actual highest bid
		selenium.type(LOC_INPUT_YOUR_BID, Double.toString(highestBid - 1));
		selenium.click(LOC_LINK_PLACE_BID);

		// waits for validation fails message
		waitForText(MSG_MESSAGE_LOWER_BID);

		// fix your bid to number greater than actual bid
		selenium.type(LOC_INPUT_YOUR_BID, Double.toString(highestBid + 1));
		selenium.click(LOC_LINK_PLACE_BID);

		// test that amount was saved as a highest bid
		final String yourBid = format(MSG_INPUT_AMOUNT_PREFORMATTED, highestBid + 1);

		Wait.until(new Condition() {
			public boolean isTrue() {
				String actual = selenium.getText(LOC_TD_CELL_AMOUNT);
				if (StringUtils.isNotBlank(actual)) {
					assertEquals(yourBid, actual);
					return true;
				}
				return false;
			}
		});
		assertEquals(yourBid, selenium.getText(LOC_TD_HIGHEST_BID));

		// relocate on second page
		selenium.click(LOC_BUTTON_NEXT_PAGE);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return "2".equals(selenium.getText(LOC_OUTPUT_ACTIVE_PAGE));
			}
		});

		// relocate on first page
		selenium.click(LOC_BUTTON_PREVIOUS_PAGE);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return "1".equals(selenium.getText(LOC_OUTPUT_ACTIVE_PAGE));
			}
		});

		// check if all things stay changed
		String highestBidString = selenium.getText(LOC_TD_CELL_AMOUNT);
		assertEquals(yourBid, highestBidString);
		highestBidString = selenium.getText(LOC_TD_HIGHEST_BID);
		assertEquals(yourBid, highestBidString);
	}

	protected void loadPage() {
		openComponent("Data Table");
		openTab(MSG_TAB_TO_OPEN);

		scrollIntoView(LOC_BUTTON_NEXT_PAGE, true);
	}
}
