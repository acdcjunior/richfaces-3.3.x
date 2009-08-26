package org.jboss.richfaces.integrationTest.extendedDataTable;

import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.jboss.test.selenium.actions.Drag;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.utils.array.ArrayTransform;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class ExtendedDataTableTestCase extends AbstractDataIterationTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage() {
		openComponent("Extended Data Table");
		openTab("Usage");

		scrollIntoView(table, true);
	}

	@BeforeClass
	public void allowNativeXPath() {
		selenium.allowNativeXpath("true");
	}

	@AfterClass
	public void allowInitialXPath() {
		selenium.allowNativeXpath("false");
	}

	private final String table = getLoc("extended-data-table--table");
	private final String cells = getLoc("extended-data-table--cells");
	private final String selectSortMode = getLoc("extended-data-table--select--sort");
	private final String selectSelectionMode = getLoc("extended-data-table--select--selection");
	private final String columnHeaderState = getLoc("extended-data-table--column-header--state");
	private final String columnHeaderCapital = getLoc("extended-data-table--column-header--capital");
	private final String columnHeaderTimeZone = getLoc("extended-data-table--column-header--time-zone");
	private final String columnHeaderFlag = getLoc("extended-data-table--column-header--flag");
	private final String[] columnHeadersSorting = new String[] {
			columnHeaderState, columnHeaderCapital };
	private final String[] columnHeadersDragging = new String[] {
			columnHeaderState, columnHeaderCapital, columnHeaderFlag,
			columnHeaderTimeZone };
	private final String relativeColumnHeaderToItsLabel = getLoc("extended-data-table--relative-column-header--from-label");

	@Test
	public void groupingTest() {
		openPage();

		final String columnHeader = format(relativeColumnHeaderToItsLabel,
				columnHeaderState);
		final String buttonContextMenu = formatLoc(
				"extended-data-table--relative-div--context-menu-button",
				columnHeader);
		final String menuItemGroupByColumn = getLoc("extended-data-table--menu-item--group-by-column");
		final String groupTextsPreformatted = getLoc("extended-data-table--table-row--group-text-preformatted");
		final String rowsPreformatted = getLoc("extended-data-table--table-row--regular-row-preformatted");
		final String rowClass = getMess("extended-data-table--class--group-row");

		selenium.fireEvent(columnHeader, Event.MOUSEOVER);
		selenium.clickAt(buttonContextMenu, "1,1");
		waitForElement(menuItemGroupByColumn);
		selenium.click(menuItemGroupByColumn);
		waitForSplash();

		final int rows = selenium.getXpathCount(format(rowsPreformatted, 0))
				.intValue();
		String expected = null;
		for (int row = 1, group = 0, tabular = 0; row <= rows; row++) {
			if (belongsClass(rowClass, format(rowsPreformatted, row))) {
				group++;
				expected = selenium.getText(format(groupTextsPreformatted,
						group));
			} else {
				tabular++;
				Assert
						.assertNotNull(
								expected,
								format(
										"First row in grouped table has to belong to class '{0}'",
										rowClass));
				String actual = selenium.getText(format(
						preformatColumn(columnHeader), tabular));
				Assert.assertEquals(actual, expected, format(
						"Cell ('{0}', row {1}) doesn't belong to group '{2}'",
						actual, row, expected));
			}
		}
	}

	@Test
	public void draggingTest() {
		openPage();

		Assert.assertEquals(1, getColumnIndex(columnHeaderFlag));
		Assert.assertEquals(2, getColumnIndex(columnHeaderState));
		Assert.assertEquals(3, getColumnIndex(columnHeaderCapital));
		Assert.assertEquals(4, getColumnIndex(columnHeaderTimeZone));

		String inputFilterState = getInputFilterState();
		selenium
				.type(
						inputFilterState,
						getMess("extended-data-table--input--sorting-letter-in-dragging"));
		selenium.fireEvent(inputFilterState, Event.KEYUP);
		waitForSplash();

		int associationHash = getAssociationMap().hashCode();

		selenium.click(columnHeaderCapital);
		waitForSplash();

		Assert.assertEquals(associationHash, getAssociationMap().hashCode());

		new Drag(selenium, columnHeaderTimeZone, formatLoc(
				"extended-data-table--relative-div--drop-zone-left",
				columnHeaderState)).drop();
		waitForSplash();

		Assert.assertEquals(associationHash, getAssociationMap().hashCode());

		Assert.assertEquals(1, getColumnIndex(columnHeaderFlag));
		Assert.assertEquals(2, getColumnIndex(columnHeaderTimeZone));
		Assert.assertEquals(3, getColumnIndex(columnHeaderState));
		Assert.assertEquals(4, getColumnIndex(columnHeaderCapital));

		new Drag(selenium, columnHeaderState, formatLoc(
				"extended-data-table--relative-div--drop-zone-right",
				columnHeaderCapital)).drop();
		waitForSplash();

		Assert.assertEquals(associationHash, getAssociationMap().hashCode());

		Assert.assertEquals(1, getColumnIndex(columnHeaderFlag));
		Assert.assertEquals(2, getColumnIndex(columnHeaderTimeZone));
		Assert.assertEquals(3, getColumnIndex(columnHeaderCapital));
		Assert.assertEquals(4, getColumnIndex(columnHeaderState));
	}

	@Test
	public void filteringTest() {
		openPage();

		final String[] statePrefixes = StringUtils.splitPreserveAllTokens(
				getMess("extended-data-table--input--state-prefixes"), ',');
		final String[] capitalPrefixes = StringUtils.splitPreserveAllTokens(
				getMess("extended-data-table--input--capital-prefixes"), ',');
		final String inputFilterState = preformatFilterInput(columnHeaderState);
		final String inputFilterCapital = preformatFilterInput(columnHeaderCapital);

		Assert.assertTrue(statePrefixes.length == capitalPrefixes.length);

		for (int i = 0; i < statePrefixes.length; i++) {
			final String statePrefix = statePrefixes[i];
			final String capitalPrefix = capitalPrefixes[i];

			selenium.type(inputFilterState, statePrefix);
			selenium.fireEvent(inputFilterState, Event.KEYUP);
			waitForSplash();
			checkFiltering();

			selenium.type(inputFilterCapital, capitalPrefix);
			selenium.fireEvent(inputFilterCapital, Event.KEYUP);
			waitForSplash();
			checkFiltering();
		}
	}

	@Test
	public void sortingModeSingleTest() {
		openPage();

		final String singleSortMode = getMess("extended-data-table--option--sort-single");
		selectMode(selectSortMode, singleSortMode);

		for (String columnHeader : columnHeadersSorting) {
			// two iterations -- one for ascending and one for descending order
			for (int i = 0; i < 2; i++) {
				selenium.click(columnHeader);
				waitForSplash();

				String columnPreformatted = preformatColumn(columnHeader);
				checkSorting(columnPreformatted);
			}
		}
	}

	@Test
	public void sortingModeMultiTest() {
		openPage();

		final String multiSortMode = getMess("extended-data-table--option--sort-multi");
		selectMode(selectSortMode, multiSortMode);

		Assert.assertTrue(columnHeadersSorting.length == 2);

		String[] columnsPreformatted = new String[] {
				preformatColumn(columnHeadersSorting[0]),
				preformatColumn(columnHeadersSorting[1]) };

		// two iterations -- one for ascending and one for descending order
		for (int i = 0; i < 2; i++) {
			selenium.click(columnHeadersSorting[0]);
			waitForSplash();

			// two iterations -- one for ascending and one for descending order
			for (int j = 0; j < 2; j++) {
				selenium.click(columnHeadersSorting[1]);
				waitForSplash();

				checkSortingForColumnOrder(columnsPreformatted);
			}
		}
	}

	@Test
	public void selectionModeNoneTest() {
		final String noneSelectionMode = getMess("extended-data-table--option--selection-none");
		
		openPage();
		selectMode(selectSelectionMode, noneSelectionMode);

		int[] rows, selectedRows;

		rows = getRowSelection("extended-data-table--input--single-row");
		multiSelection(rows);
		selectedRows = getRowSelection("extended-data-table--output--mode-none");
		checkSelection(selectedRows);

		openPage();
		selectMode(selectSelectionMode, noneSelectionMode);

		rows = getRowSelection("extended-data-table--input--multiple-rows");
		multiSelection(rows);
		selectedRows = getRowSelection("extended-data-table--output--mode-none");
		checkSelection(selectedRows);
	}

	@Test
	public void selectionModeSingleTest() {
		final String singleSelectionMode = getMess("extended-data-table--option--selection-single");
		
		openPage();
		selectMode(selectSelectionMode, singleSelectionMode);

		int[] rows, selectedRows;

		rows = getRowSelection("extended-data-table--input--single-row");
		multiSelection(rows);
		selectedRows = getRowSelection("extended-data-table--output--mode-single--single-row");
		checkSelection(selectedRows);

		openPage();
		selectMode(selectSelectionMode, singleSelectionMode);

		rows = getRowSelection("extended-data-table--input--multiple-rows");
		multiSelection(rows);
		selectedRows = getRowSelection("extended-data-table--output--mode-single--multiple-rows");
		checkSelection(selectedRows);
	}

	@Test
	public void selectionModeMultiTest() {
		final String multiSelectionMode = getMess("extended-data-table--option--selection-multi");
		
		openPage();
		selectMode(selectSelectionMode, multiSelectionMode);

		int[] rows, selectedRows;

		rows = getRowSelection("extended-data-table--input--single-row");
		multiSelection(rows);
		selectedRows = getRowSelection("extended-data-table--output--mode-multi--single-row");
		checkSelection(selectedRows);

		openPage();
		selectMode(selectSelectionMode, multiSelectionMode);

		rows = getRowSelection("extended-data-table--input--multiple-rows");
		multiSelection(rows);
		selectedRows = getRowSelection("extended-data-table--output--mode-multi--multiple-rows");
		checkSelection(selectedRows);
	}

	private void selectMode(String selectLocator, String selectionMode) {
		if (!selectionMode.equals(selenium.getValue(selectLocator))) {
			selenium
					.select(
							selectLocator,
							formatMess(
									"extended-data-table--option-value--selection-mode-preformatted",
									selectionMode));
			waitForSplash();
		}
	}

	private int[] getRowSelection(String messageKey) {
		String message = getMess(messageKey);
		String[] tokens = StringUtils.splitPreserveAllTokens(message, ',');
		int[] rows = new int[tokens.length];

		for (int i = 0; i < tokens.length; i++) {
			rows[i] = Integer.valueOf(tokens[i]);
		}

		return rows;
	}

	private void multiSelection(int[] rows) {
		int column = 2;

		for (int i = 0; i < rows.length; i++) {
			final int row = rows[i];
			String cell = format(cells, row, column);

			if (i > 0) {
				selenium.controlKeyDown();
			}

			selenium.click(cell);

			if (i > 0) {
				selenium.controlKeyUp();
			}
		}
	}

	private void checkSelection(int[] rows) {
		checkSelection(rows, true);
		checkSelection(rows, false);
	}

	private void checkSelection(int[] rows, boolean positiveComparison) {
		final String selectedRow = getLoc("extended-data-table--selected-row");

		String comparison = (positiveComparison) ? "position()="
				: "position()!=";
		String conjuction = (positiveComparison) ? " or " : " and ";

		String condition = " and ("
				+ comparison
				+ StringUtils.join(ArrayUtils.toObject(rows), conjuction
						+ comparison) + ")";

		if (rows.length == 0) {
			condition = "";
		}

		int count = selenium.getXpathCount(format(selectedRow, condition))
				.intValue();

		Assert.assertTrue(positiveComparison ? count == rows.length
				: count == 0);
	}

	private void waitForSplash() {
		final String splashScreen = getLoc("extended-data-table--splash-screen");
		Wait.dontFail().interval(1).timeout(2000).until(new Condition() {
			public boolean isTrue() {
				return selenium.isElementPresent(splashScreen);
			}
		});
		Wait.interval(1).timeout(2000).until(new Condition() {
			public boolean isTrue() {
				return !selenium.isElementPresent(splashScreen);
			}
		});
	}

	private String preformatColumn(String columnHeader) {
		int columnIndex = getColumnIndex(columnHeader);
		String columnPreformatted = format(cells, Integer.MAX_VALUE,
				columnIndex);
		return columnPreformatted.replaceFirst(format("\\[{0,number}\\]",
				Integer.MAX_VALUE), "{0,choice,0#|1#[{0}]}");
	}

	private String preformatFilterInput(String columnHeader) {
		final String inputFilterPreformatted = getLoc("extended-data-table--input--filter-column");
		int columnIndex = getColumnIndex(columnHeader);
		return format(inputFilterPreformatted, columnIndex);
	}

	private int getColumnIndex(String columnHeader) {
		return 1 + selenium.getElementIndex(
				format(relativeColumnHeaderToItsLabel, columnHeader))
				.intValue();
	}

	private void checkFiltering() {
		final String inputFilterState = preformatFilterInput(columnHeaderState);
		final String inputFilterCapital = preformatFilterInput(columnHeaderCapital);
		final String cellsState = preformatColumn(columnHeaderState);
		final String cellsCapital = preformatColumn(columnHeaderCapital);

		String statePrefix = selenium.getValue(inputFilterState);
		String capitalPrefix = selenium.getValue(inputFilterCapital);

		int rows = selenium.getXpathCount(format(cellsState, 0)).intValue();

		for (int row = 1; row <= rows; row++) {
			if (statePrefix.length() > 0) {
				String state = selenium.getText(format(cellsState, row));
				Assert.assertTrue(state.startsWith(statePrefix));
			}
			if (capitalPrefix.length() > 0) {
				String capital = selenium.getText(format(cellsCapital, row));
				Assert.assertTrue(capital.startsWith(capitalPrefix));
			}
		}
	}

	private String getImgSrcOrText(String locator) {
		String imgSrc = formatLoc(
				"extended-data-table--relative-image-src--flag", locator);
		if (selenium.isElementPresent(imgSrc)) {
			return selenium.getAttribute(imgSrc);
		} else {
			return selenium.getText(locator);
		}
	}

	private HashMap<String, Vector<String>> getAssociationMap() {
		HashMap<String, Vector<String>> association = new HashMap<String, Vector<String>>();

		String[] columnsPreformatted = new ArrayTransform<String, String>(String.class) {
			public String transformation(String columnHeaderDragging) {
				return preformatColumn(columnHeaderDragging);
			}
		}.transform(columnHeadersDragging);

		int rows = selenium.getXpathCount(format(columnsPreformatted[0], 0))
				.intValue();

		for (int row = 1; row <= rows; row++) {
			String key = selenium.getText(format(columnsPreformatted[0], row));
			Vector<String> values = new Vector<String>(
					columnsPreformatted.length - 1);
			for (int column = 1; column < columnsPreformatted.length; column++) {
				String columnsContentDiv = formatLoc(
						"extended-data-table--relative-div--column-to-its-content",
						columnsPreformatted[column]);
				String value = getImgSrcOrText(format(columnsContentDiv, row));
				values.add(column - 1, value);
			}
			association.put(key, values);
		}

		return association;
	}

	private String getInputFilterState() {
		return preformatFilterInput(columnHeaderState);
	}
}
