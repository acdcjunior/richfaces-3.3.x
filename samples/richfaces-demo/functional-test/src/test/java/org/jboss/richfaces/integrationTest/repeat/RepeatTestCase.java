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
package org.jboss.richfaces.integrationTest.repeat;

import static org.testng.Assert.*;

import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.jboss.test.selenium.waiting.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class RepeatTestCase extends AbstractDataIterationTestCase {

	private final String LOC_FIELDSET_HEADER = getLoc("FIELDSET_HEADER");
	private final String LOC_INPUT_PROPOSED_PRICE_PREFORMATTED = getLoc("INPUT_PROPOSED_PRICE_PREFORMATTED");
	private final String LOC_SELECT_REASON_PREFORMATTED = getLoc("SELECT_REASON_PREFORMATTED");
	private final String LOC_OUTPUT_SALES_COST_PREFORMATTED = getLoc("OUTPUT_SALES_COST_PREFORMATTED");
	private final String LOC_OUTPUT_GROSS_MARGIN_PREFORMATTED = getLoc("OUTPUT_GROSS_MARGIN_PREFORMATTED");

	/**
	 * Go through all rows two times. In each row enter proposed price less
	 * than, equal or greater than sales cost and checks if proposed gross
	 * margin is less than, equal or greater than 0 accordingly. Check that
	 * selecting reason will not influence output.
	 */
	@Test
	public void functionalTest() {
		int rows = selenium.getXpathCount(format(LOC_INPUT_PROPOSED_PRICE_PREFORMATTED, 0)).intValue();

		String[] grossMargins = new String[rows];

		// two run through all rows
		for (int instance = 0; instance < rows * 2; instance++) {
			final boolean isFirstIteration = (instance < rows);
			final int row = 1 + (instance % rows);
			final String locInputProposedPrice = format(LOC_INPUT_PROPOSED_PRICE_PREFORMATTED, row);
			final String locSelectReason = format(LOC_SELECT_REASON_PREFORMATTED, row);
			final String locOutputGrossMargin = format(LOC_OUTPUT_GROSS_MARGIN_PREFORMATTED, row);
			final String locOutputSalesCost = format(LOC_OUTPUT_SALES_COST_PREFORMATTED, row);

			int difference = (row % 2 == 0 ? 1 : -1) * (instance % 3);

			double salesCost = Double.parseDouble(selenium.getText(locOutputSalesCost));
			double proposedPrice = salesCost + difference;

			String grossMarginString = selenium.getText(locOutputGrossMargin);

			if (!isFirstIteration) {
				assertEquals(grossMarginString, grossMargins[row - 1],
						"Gross margin changes between first and second iteration");
			}

			selenium.type(locInputProposedPrice, Double.toString(proposedPrice));

			if (isFirstIteration) {
				Wait.failWith("Reason selection never change from blank").waitForChange("", new Retrieve<String>() {
					public String retrieve() {
						return selenium.getValue(locSelectReason);
					}
				});
			} else {
				waitForTextChanges(locOutputGrossMargin, grossMarginString);
			}

			// select some option in reason
			int options = selenium.getXpathCount(locSelectReason + "/*[@value]").intValue();
			assertTrue(options > 0);
			selenium.select(locSelectReason, format("index={0}", row % options));

			grossMarginString = selenium.getText(locOutputGrossMargin);

			double grossMargin = parseGrossMargin(grossMarginString);

			if (difference > 0) {
				assertTrue(grossMargin > 0);
			} else if (difference < 0) {
				assertTrue(grossMargin < 0);
			} else {
				assertTrue(grossMargin == 0);
			}

			grossMargins[row - 1] = grossMarginString;
		}

		for (int row = 1; row <= rows; row++) {
			final String outputGrossMargin = format(LOC_OUTPUT_GROSS_MARGIN_PREFORMATTED, row);

			String grossMarginString = selenium.getText(outputGrossMargin);
			assertEquals(grossMarginString, grossMargins[row - 1]);
		}
	}

	private double parseGrossMargin(String string) {
		string = string.replaceFirst("\\$", "");
		return Double.parseDouble(string);
	}

	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("Repeat");
		scrollIntoView(LOC_FIELDSET_HEADER, true);
		selenium.allowNativeXpath("true");
	}
}
