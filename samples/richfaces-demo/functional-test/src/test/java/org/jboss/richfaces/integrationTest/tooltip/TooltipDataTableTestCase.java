package org.jboss.richfaces.integrationTest.tooltip;

import junit.framework.Assert;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class TooltipDataTableTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.allowNativeXpath("true");

		selenium.open(format("{0}/{1}", contextPath, PAGE));

		scrollIntoView(table, true);
	}

	private static class Column {
		public static final int MAKE = 0;
		public static final int MODEL = 1;
		public static final int YEAR = 2;
	}

	private final String PAGE = "richfaces/toolTip.jsf?c=toolTip&tab=table";
	private final String table = getLoc("data-table--table");
	private final String cells = getLoc("data-table--cells-preformatted");
	private final String coords = getMess("data-table--coords--event-at-cell");
	private final String itemsOnTooltip = getLoc("data-table--items-on-tooltip");
	private final String outputTooltipMake = format(itemsOnTooltip, Column.MAKE);
	private final String outputTooltipModel = format(itemsOnTooltip,
			Column.MODEL);
	private final String outputTooltipYear = format(itemsOnTooltip, Column.YEAR);

	@Test
	public void iterateThroughTableTest() {
		openPage();

		int rows = selenium.getXpathCount(format(cells, 0, Column.MAKE))
				.intValue();

		for (int row = 1; row <= rows; row++) {
			final String cellMake = format(cells, row, Column.MAKE);
			final String cellModel = format(cells, row, Column.MODEL);
			final String cellYear = format(cells, row, Column.YEAR);
			final String activeTooltipArea = formatLoc(
					"data-table--relative--cell-make-to-its-active-tooltip-area",
					cellMake);

			if (row == 1)
				selenium.mouseMoveAt(activeTooltipArea, coords);
			mouseOverAt(activeTooltipArea, coords);
			waitForElementAppears(outputTooltipMake);

			Assert.assertEquals(selenium.getText(outputTooltipMake), selenium
					.getText(formatLoc(
							"data-table--relative--cell-make-to-its-label",
							cellMake)));
			Assert.assertEquals(selenium.getText(outputTooltipModel), selenium
					.getText(cellModel));
			Assert.assertEquals(selenium.getText(outputTooltipYear), selenium
					.getText(cellYear));

			selenium.mouseOut(activeTooltipArea);
			waitForElementDisappears(outputTooltipMake);
		}
	}

	private void waitForElementAppears(final String locator) {
		waitModelUpdate.until(new Condition() {
			public boolean isTrue() {
				return selenium.isElementPresent(locator);
			}
		});
	}

	private void waitForElementDisappears(final String locator) {
		waitModelUpdate.until(new Condition() {
			public boolean isTrue() {
				return !selenium.isElementPresent(locator);
			}
		});
	}
}
