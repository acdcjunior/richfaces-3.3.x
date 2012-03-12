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
package org.jboss.richfaces.integrationTest.dataFilterSlider;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.*;

import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.jboss.test.selenium.waiting.Wait.Waiting;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class DataFilterSliderTestCase extends AbstractDataIterationTestCase {

	private final String LOC_FIELDSET_HEADER = getLoc("FIELDSET_HEADER");
	private final String LOC_TABLE_ROWS = getLoc("TABLE_ROWS");
	private final String LOC_TD_MILEAGE_PREFORMATTED = getLoc("TD_MILEAGE_PREFORMATTED");
	private final String LOC_TD_BRAND_PREFORMATTED = getLoc("TD_BRAND_PREFORMATTED");
	private final String LOC_LINK_BRAND_PREFORMATTED = getLoc("LINK_BRAND_PREFORMATTED");
	private final String LOC_DIV_SLIDER_HANDLE = getLoc("DIV_SLIDER_HANDLE");
	private final String LOC_DIV_SLIDER_TRACK = getLoc("DIV_SLIDER_TRACK");
	private final String LOC_INPUT_MAX_PRICE = getLoc("INPUT_MAX_PRICE");

	private final int MSG_COUNT_MAX_ROWS = Integer.valueOf(getMsg("COUNT_MAX_ROWS"));
	private final String MSG_CHOICES_OF_BRANDS = getMsg("CHOICES_OF_BRANDS");
	private final String MSG_CHOICES_OF_SLIDER_PERCENTAGES = getMsg("CHOICES_OF_SLIDER_PERCENTAGES");
	private final String MSG_CHOICES_OF_SLIDER_PERCENTAGES_FOR_INTERLEAVING = getMsg("CHOICES_OF_SLIDER_PERCENTAGES_FOR_INTERLEAVING");
	private final String MSG_INPUT_SIMPLE_SLIDER_PERCENTAGE = getMsg("INPUT_SIMPLE_SLIDER_PERCENTAGE");

	/**
	 * Click on the specified brand and checks that all rows contains only
	 * specified brand (column Make)
	 */
	@Test
	public void testSimpleBrand() {
		testBrand(format(MSG_CHOICES_OF_BRANDS, 0));
	}

	/**
	 * Use slider to change input price and checks that all prices in table is
	 * lower than price selected by slider and given in text-input next to the
	 * slider
	 */
	@Test
	public void testSimpleSlider() {
		testSlider(MSG_INPUT_SIMPLE_SLIDER_PERCENTAGE);
	}

	/**
	 * Use several values of prices to use with slider and checks that in each
	 * step is contained all values defined in all steps with lesser selected
	 * value of price
	 */
	@Test(dependsOnMethods = "testSimpleSlider")
	public void testComplexSlider() {
		List<Integer> lowMileages = testSlider(format(MSG_CHOICES_OF_SLIDER_PERCENTAGES, 0));
		List<Integer> highMileages = testSlider(format(MSG_CHOICES_OF_SLIDER_PERCENTAGES, 1));
		List<Integer> lowestMileages = testSlider(format(MSG_CHOICES_OF_SLIDER_PERCENTAGES, 2));
		List<Integer> allMileages = testSlider(format(MSG_CHOICES_OF_SLIDER_PERCENTAGES, 3));

		// check that each state of table contains all mileages in states with
		// lesser specified max mileage (don't try to check tables that have
		// maxRowCount and more rows, because tables are constrained by
		// maxRowCount per table and so don't have to include all lesser tables)
		assertTrue(allMileages.size() >= MSG_COUNT_MAX_ROWS || allMileages.containsAll(highMileages));
		assertTrue(highMileages.size() >= MSG_COUNT_MAX_ROWS || highMileages.containsAll(lowMileages));
		assertTrue(lowMileages.size() >= MSG_COUNT_MAX_ROWS || lowMileages.containsAll(lowestMileages));
		// check that there is at least one mileage greater than slider minimum
		assertFalse(allMileages.isEmpty());
	}

	/**
	 * Test several predefined value of selected brand and selected price to
	 * test influencing and interleaving of 'by slider' and 'by brand' selection
	 * models. First use slider to select value and test all prices in table are
	 * lesser than selected price, next click on the brand (column Make) and
	 * checks that only given brand is in table. Then repeat this process once
	 * with another input data.
	 */
	@Test(dependsOnMethods = { "testSimpleSlider", "testSimpleBrand" })
	public void testMethodInterleaving() {
		testSlider(format(MSG_CHOICES_OF_SLIDER_PERCENTAGES_FOR_INTERLEAVING, 0));
		testBrand(format(MSG_CHOICES_OF_BRANDS, 0));
		testSlider(format(MSG_CHOICES_OF_SLIDER_PERCENTAGES_FOR_INTERLEAVING, 1));
		testBrand(format(MSG_CHOICES_OF_BRANDS, 1));
		testSlider(format(MSG_CHOICES_OF_SLIDER_PERCENTAGES_FOR_INTERLEAVING, 2));
	}

	private void testBrand(String brand) {
		final String locLinkBrand = format(LOC_LINK_BRAND_PREFORMATTED, brand);

		final String tableText = selenium.getText(LOC_TABLE_COMMON);
		selenium.click(locLinkBrand);

		Wait.dontFail().timeout(5000).until(new Condition() {
			public boolean isTrue() {
				return !tableText.equals(selenium.getText(LOC_TABLE_COMMON));
			}
		});

		for (int row = 1; row <= getJQueryCount(LOC_TABLE_ROWS); row++) {
			String rowBrand = selenium.getText(format(LOC_TD_BRAND_PREFORMATTED, row));

			assertEquals(rowBrand, brand, "In all rows must be the defined brand");
		}
	}

	private List<Integer> testSlider(String percentage) {
		clickSliderAtPercent(Integer.valueOf(percentage));
		int maxMileage = getCurrentMileageFromInput();
		List<Integer> result = checkAllMileagesMaxAndReturnItsList(maxMileage);
		return result;
	}

	private List<Integer> checkAllMileagesMaxAndReturnItsList(int maxMileage) {
		List<Integer> list = new LinkedList<Integer>();
		
		int rowCount = getJQueryCount(LOC_TABLE_ROWS);
		
		for (int row = 1; row <= rowCount; row++) {
			int rowMileage = Double.valueOf(selenium.getText(format(LOC_TD_MILEAGE_PREFORMATTED, row))).intValue();

			assertTrue(rowMileage <= maxMileage, format(
					"All mileages in table must be lesser than selected value ({0}), but '{1}' isn't", maxMileage,
					rowMileage));

			list.add(rowMileage);
		}
		return list;
	}

	private int getCurrentMileageFromInput() {
		return Integer.valueOf(selenium.getValue(LOC_INPUT_MAX_PRICE));
	}

	private void clickSliderAtPercent(double percent) {
	    final Number handlePosition = selenium.getElementPositionLeft(LOC_DIV_SLIDER_HANDLE);

		int width = selenium.getElementWidth(LOC_DIV_SLIDER_TRACK).intValue();
		int height = selenium.getElementHeight(LOC_DIV_SLIDER_TRACK).intValue();
		String coords = format("{0,number,integer},{1}", width * percent / 100, height / 2);

		selenium.mouseDownAt(LOC_DIV_SLIDER_TRACK, coords);
		selenium.mouseUpAt(LOC_DIV_SLIDER_TRACK, coords);

		Wait.failWith("Slider never changes left position").until(new Condition() {
			public boolean isTrue() {
				return !handlePosition.equals(selenium.getElementPositionLeft(LOC_DIV_SLIDER_HANDLE));
			}
		});
		
		waitTableTextStabilizes.until(conditionTableTextStabilizes);
	}
	
	private Waiting waitTableTextStabilizes = Wait.interval(1000).timeout(15000).failWith("Table text never got stabilized");
	
	private Condition conditionTableTextStabilizes = new Condition() {
        String memory;
        int count = 0;

        public boolean isTrue() {
                String actual = selenium.getText(LOC_TABLE_COMMON);

                if (memory == null) {
                        memory = actual;
                        return false;
                }

                if (memory.equals(actual)) {
                        count++;
                } else {
                        count = 0;
                }

                if (count >= 3) {
                        return true;
                }

                memory = actual;

                return false;
        }
};

	protected void loadPage() {
		openComponent("Data Filter Slider");
		scrollIntoView(LOC_FIELDSET_HEADER, true);
		selenium.allowNativeXpath("true");
	}
}
