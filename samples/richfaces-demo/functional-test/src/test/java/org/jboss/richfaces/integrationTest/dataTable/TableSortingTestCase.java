package org.jboss.richfaces.integrationTest.dataTable;


import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class TableSortingTestCase extends AbstractDataIterationTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage(String page) {
		selenium.open(format("{0}/{1}", contextPath, page));

		scrollIntoView(table, true);
	}

	private final String table = getLoc("data-table-common--table");
	private final String headerCellState = getLoc("table-sorting--header-cell--state");
	private final String headerCellCapital = getLoc("table-sorting--header-cell--capital");
	private final String srcSortIconRelative = getLoc("table-sorting--image-src--sort-icon-relative");
	private final String srcSortIconState = format(srcSortIconRelative,
			headerCellState);
	private final String srcSortIconCapital = format(srcSortIconRelative,
			headerCellCapital);
	private final String cellsState = getLoc("table-sorting--cells--state-preformatted");
	private final String cellsCapital = getLoc("table-sorting--cells--capital-preformatted");

	@Test
	public void builtInSortingTest() {
		openPage("richfaces/sortingFeature.jsf?c=sorting&tab=usage");

		String iconSrc = selenium.getAttribute(srcSortIconState);
		selenium.click(headerCellState);
		iconSrc = waitForAttributeChangesAndReturn(srcSortIconState, iconSrc);
		checkSorting(cellsState);

		selenium.click(headerCellState);
		waitForAttributeChanges(srcSortIconState, iconSrc);
		checkSorting(cellsState);

		iconSrc = selenium.getAttribute(srcSortIconCapital);
		selenium.click(headerCellCapital);
		iconSrc = waitForAttributeChangesAndReturn(srcSortIconCapital, iconSrc);
		checkSorting(cellsCapital);

		selenium.click(headerCellCapital);
		waitForAttributeChanges(srcSortIconCapital, iconSrc);
		checkSorting(cellsCapital);
	}

	private final String buttonSort = getLoc("table-sorting-external--button--sort");
	private final String selectColumn = getLoc("table-sorting-external--select--column");
	private final String selectOrder = getLoc("table-sorting-external--select--order");
	private final String[] externalSortingColumns = StringUtils
			.splitPreserveAllTokens(getMess("table-sorting-external--columns"),
					'#');
	private final String[] externalColumns = new String[] {
			getLoc("table-sorting-external--cells--column1-preformatted"),
			getLoc("table-sorting-external--cells--column2-preformatted"),
			getLoc("table-sorting-external--cells--column3-preformatted") };

	@Test
	public void externalSortingTest() {
		openPage("richfaces/sortingFeature.jsf?c=sorting&tab=ex-usage");

		final int columns = externalSortingColumns.length;

		for (int column = 0; column < columns; column++) {
			String[] trio = StringUtils.splitPreserveAllTokens(
					externalSortingColumns[column], '|');
			selenium.select(format(selectColumn, trio[0]), trio[1]);
			selenium.select(format(selectOrder, trio[0]), trio[2]);
			Wait.until(new Condition() {
				public boolean isTrue() {
					return !selenium.isElementPresent(format("{0}/@disabled",
							buttonSort));
				}
			});
		}

		String tableText = getTableText();
		selenium.click(buttonSort);
		waitForTextChanges(table, tableText);

		checkSortingForColumnOrder(externalColumns);
	}

	
}
