package org.jboss.richfaces.integrationTest.dataFilterSlider;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class DataFilterSliderTestCase extends AbstractDataIterationTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.open(format("{0}/{1}", contextPath, PAGE));

		scrollIntoView(header, true);
	}

	private final String PAGE = "richfaces/dataFilterSlider.jsf?c=dataFilterSlider&tab=usage";
	private final String header = getLoc("data-filter-slider--header");
	private final String cellsMileage = getLoc("data-filter-slider--table-cells--mileage-preformatted");
	private final String cellsBrand = getLoc("data-filter-slider--table-cells--brand-preformatted");
	private final int maxRowCount = Integer
			.valueOf(getMess("data-filter-slider--count--max-rows-in-table"));
	private final String brands = getMess("data-filter-slider--choices--brands");
	private final String percentages = getMess("data-filter-slider--choices--slider-percentages");
	private final String percentagesInterleaving = getMess("data-filter-slider--choices--slider-percentages-interleaving");
	private final String percentageSimple = getMess("data-filter-slider--simple-slider-percentage");

	@Test
	public void simpleBrandTest() {
		openPage();
		testBrand(format(brands, 0));
	}

	@Test
	public void simpleSliderTest() {
		openPage();
		testSlider(percentageSimple);
	}

	@Test
	public void complexSliderTest() {
		openPage();

		List<Integer> lowMileages = testSlider(format(percentages, 0));
		List<Integer> highMileages = testSlider(format(percentages, 1));
		List<Integer> lowestMileages = testSlider(format(percentages, 2));
		List<Integer> allMileages = testSlider(format(percentages, 3));

		// check that each state of table contains all mileages in states with
		// lesser specified max mileage (don't try to check tables that have
		// maxRowCount and more rows, because tables are constrained by
		// maxRowCount per table and so don't have to include all lesser tables)
		Assert.assertTrue(allMileages.size() >= maxRowCount
				|| allMileages.containsAll(highMileages));
		Assert.assertTrue(highMileages.size() >= maxRowCount
				|| highMileages.containsAll(lowMileages));
		Assert.assertTrue(lowMileages.size() >= maxRowCount
				|| lowMileages.containsAll(lowestMileages));
		// check that there is at least one mileage greater than slider minimum
		Assert.assertFalse(lowestMileages.isEmpty());
	}

	@Test
	public void interleavingMethodsTest() {
		openPage();
		testSlider(format(percentagesInterleaving, 0));
		testBrand(format(brands, 0));
		testSlider(format(percentagesInterleaving, 1));
		testBrand(format(brands, 1));
		testSlider(format(percentagesInterleaving, 2));
	}

	private void testBrand(String brand) {
		final String table = getLoc("data-table-common--table");
		final String linkBrandPreformatted = getLoc("data-filter-slider--link--brand-preformatted");

		String linkBrand = format(linkBrandPreformatted, brand);

		final String tableText = selenium.getText(table);
		selenium.click(linkBrand);

		Wait.dontFail().timeout(5000).until(new Condition() {
			public boolean isTrue() {
				return !tableText.equals(selenium.getText(table));
			}
		});

		for (int row = 1; row <= selenium.getXpathCount(format(cellsBrand, 0))
				.intValue(); row++) {
			String rowBrand = selenium.getText(format(cellsBrand, row));
			Assert.assertEquals(brand, rowBrand);
		}
	}

	private List<Integer> testSlider(String percentage) {
		clickSliderAtPercent(Integer.valueOf(percentage));
		int maxMileage = getCurrentMileageFromInput();
		return checkAllMileagesMaxAndReturnItsList(maxMileage);
	}

	private List<Integer> checkAllMileagesMaxAndReturnItsList(int maxMileage) {
		List<Integer> list = new LinkedList<Integer>();
		int rowCount = selenium.getXpathCount(format(cellsMileage, 0)).intValue();
		for (int row = 1; row <= rowCount; row++) {
			int rowMileage = Double.valueOf(
					selenium.getText(format(cellsMileage, row))).intValue();
			Assert.assertTrue(rowMileage <= maxMileage);
			list.add(rowMileage);
		}
		return list;
	}

	private int getCurrentMileageFromInput() {
		final String inputPrice = getLoc("data-filter-slider--input--price");
		return Integer.valueOf(selenium.getValue(inputPrice));
	}

	private void clickSliderAtPercent(double percent) {
		final String handle = getLoc("data-filter-slider--handle");
		final String track = getLoc("data-filter-slider--track");

		final Number handlePosition = selenium.getElementPositionLeft(handle);

		int width = selenium.getElementWidth(track).intValue();
		int height = selenium.getElementHeight(track).intValue();
		String coords = format("{0,number,integer},{1}", width * percent / 100,
				height / 2);

		selenium.mouseDownAt(track, coords);
		selenium.mouseUpAt(track, coords);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return !handlePosition.equals(selenium
						.getElementPositionLeft(handle));
			}
		});
	}
}
