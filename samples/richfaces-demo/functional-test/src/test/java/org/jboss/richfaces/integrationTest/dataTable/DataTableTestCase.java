package org.jboss.richfaces.integrationTest.dataTable;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class DataTableTestCase extends AbstractDataIterationTestCase {
	{
		// each test will be ran in separated browser session
		setCleanSessionForEachMethod(true);
	}
	
	/**
	 * Opens specified page
	 */
	private void openPage(final String tabTitle,
			String scrollToLocator) {
		openComponent("Column");
		openTab(tabTitle);
		scrollIntoView(scrollToLocator, true);
	}

	private final String modifiableTableCells = getLoc("data-table-modifiable--table-cell--preformatted-locators");

	@Test
	public void wholeDataTableContentTest() {
		openPage(getMsg("data-table--open-tab"), getLoc("data-table--header"));

		String expected = getMess("data-table--text--whole-table");
		String text = selenium
				.getText(getLoc("data-table--output--whole-table"));

		Assert.assertEquals(expected, text);
	}

	@Test
	public void extendedDataTableTest() throws ParseException {

		String actual;
		final String tableCellHighestBid = getLoc("data-table-extended--table-cell--highest-bid");
		final String tableCellAmount = getLoc("data-table-extended--table-cell--amount");
		final String inputYoutBid = getLoc("data-table-extended--input--your-bid");
		final String linkPlaceBid = getLoc("data-table-extended--link--place-bid");

		final String header = buttonNextPage;

		// opens tab
		openPage(getMsg("data-table-extended--open-tab"), header);

		// get a actual value of highest bid in specified row
		actual = selenium.getText(tableCellHighestBid);
		NumberFormat numberFormat = DecimalFormat.getInstance();
		final double highestBid = numberFormat.parse(actual.replace("$", ""))
				.doubleValue();

		// asserts blank value of "amount" cell
		Assert.assertTrue(StringUtils
				.isBlank(selenium.getText(tableCellAmount)));

		// fill new highest bid like a number smaller than actual highest bid
		selenium.type(inputYoutBid, Double.toString(highestBid - 1));
		selenium.click(linkPlaceBid);

		// waits for validation fails message
		waitForText(getMess("data-table-extended--message--lower-bid"));

		// fix your bid to number greater than actual bid
		selenium.type(inputYoutBid, Double.toString(highestBid + 1));
		selenium.click(linkPlaceBid);

		// test that amount was saved as a highest bid
		final String yourBid = formatMess(
				"data-table-extended--format--amount", highestBid + 1);
		Wait.until(new Condition() {
			public boolean isTrue() {
				String actual = selenium.getText(tableCellAmount);
				if (StringUtils.isNotBlank(actual)) {
					Assert.assertEquals(yourBid, actual);
					return true;
				}
				return false;
			}
		});
		Assert.assertEquals(yourBid, selenium.getText(tableCellHighestBid));

		// relocate on second page
		selenium.click(buttonNextPage);
		Wait.until(new Condition() {
			public boolean isTrue() {
				return "2".equals(selenium.getText(outputActivePage));
			}
		});

		// relocate on first page
		selenium.click(buttonPreviousPage);
		Wait.until(new Condition() {
			public boolean isTrue() {
				return "1".equals(selenium.getText(outputActivePage));
			}
		});

		// check if all things stay changed
		actual = selenium.getText(tableCellAmount);
		Assert.assertEquals(yourBid, actual);
		actual = selenium.getText(tableCellHighestBid);
		Assert.assertEquals(yourBid, actual);
	}

	@Test
	public void modifiableDataModelSortingTest() {
		// opens tab
		openPage(getMsg("data-table-modifiable--open-tab"), table);

		final String tableHeaderCellKey = getLoc("data-table-modifiable--table-header-cell--key");

		// clicks on "key" column-header to sort table by keys
		final String tableContentBeforeSorting = selenium.getText(table);
		selenium.click(tableHeaderCellKey);
		Wait.dontFail().until(new Condition() {
			public boolean isTrue() {
				return !tableContentBeforeSorting.equals(selenium
						.getText(table));
			}
		});

		// check if keys on all pages are sorted in right way
		int pageCount = selenium.getXpathCount(
				format(buttonPagePreformatted, 0)).intValue();
		String lastText = null; // remembers last cell text
		for (int i = 1; i <= pageCount; i++) {
			// switch on the wanted page
			final String buttonPage = format(buttonPagePreformatted, i);
			selenium.click(buttonPage);
			Wait.until(new Condition() {
				public boolean isTrue() {
					return belongsClass("rich-datascr-act", buttonPage);
				}
			});

			// count how many rows (cells in one column) table have
			int cellCount = selenium.getXpathCount(
					format(modifiableTableCells, 0)).intValue();

			// checks that columns are correctly sorted
			for (int j = 1; j <= cellCount; j++) {
				String cell = format(modifiableTableCells, j);
				String cellText = selenium.getText(cell);
				if (lastText != null && cellText.compareToIgnoreCase(lastText) <= 0) {
					Assert.fail();
				}
				lastText = cellText;
			}
		}
	}

	@Test
	public void modifiableDataModelSearchingTest() {
		// opens tab
		openPage(getMsg("data-table-modifiable--open-tab"), table);

		String expected, actual;
		final String inputAssignee = getLoc("data-table-modifiable--input--assignee");
		final String assigneeName = getMess("data-model-modifiable--input--assignee-name");

		// types a name to the assignee column input
		final String tableContentBeforeSorting = selenium.getText(table);
		selenium.type(inputAssignee, assigneeName);
		selenium.fireEvent(inputAssignee, Event.KEYUP);
		Wait.dontFail().until(new Condition() {
			public boolean isTrue() {
				return !tableContentBeforeSorting.equals(selenium
						.getText(table));
			}
		});

		// check how many pages should be displayed
		expected = getMess("data-model-modifiable--count--assignee-table-pages");
		String pageCount = selenium.getXpathCount(
				format(buttonPagePreformatted, 0)).toString();
		Assert.assertEquals(expected, pageCount);

		// checks how many columns should be displayed
		expected = getMess("data-model-modifiable--count--assignee-table-rows");
		String cellCount = selenium.getXpathCount(
				format(modifiableTableCells, 0)).toString();
		Assert.assertEquals(expected, cellCount);

		// checks that assignee column contains assignee name
		actual = selenium
				.getText(getLoc("data-table-modifiable--table-cell--assignee"));
		Assert.assertTrue(actual.contains(assigneeName));
	}

	@Test
	public void editableTableWithModalPanelEditTest() {
		final int row = 2;
		final String linkEdit = formatLoc("data-table-editable--link--edit",
				row);
		final String panelEdit = getLoc("data-table-editable--panel--edit");
		final String outputPrice = formatLoc(
				"data-table-editable--output--price", row);
		final String inputPrice = getLoc("data-table-editable--input--price");
		final String buttonStore = getLoc("data-table-editable--button--store");

		// opens tab
		openPage(getMsg("data-table-editable--open-tab"), table);

		// get a initial value of selected row
		int value = Integer.valueOf(selenium.getText(outputPrice));

		// open and wait for editation modal panel
		selenium.click(linkEdit);
		Wait.until(new Condition() {
			public boolean isTrue() {
				return !"none".equals(getStyle(panelEdit, "display"));
			}
		});

		// check that value in input is same like initial
		Assert.assertEquals(selenium.getValue(inputPrice), Integer
				.toString(value));

		// increase price in input
		value += 2;
		selenium.type(inputPrice, Integer.toString(value));

		// store new price and wait for modal panel disappears
		selenium.click(buttonStore);
		Wait.until(new Condition() {
			public boolean isTrue() {
				return "none".equals(getStyle(panelEdit, "display"));
			}
		});

		// check that new price was stored successfully
		Assert.assertEquals(selenium.getText(outputPrice), Integer
				.toString(value));

		// goto next page and then back to first
		gotoNextAndThenBack();

		// check that new price is shown right after page movement
		Assert.assertEquals(selenium.getText(outputPrice), Integer
				.toString(value));
	}

	@Test
	public void editableTableWithModalPanelDeleteTest() {
		final int row = 2;
		final String linkDelete = formatLoc(
				"data-table-editable--link--delete", row);
		final String panelDelete = getLoc("data-table-editable--panel--delete");
		final String outputPrice = formatLoc(
				"data-table-editable--output--price", row);
		final String outputPriceAbove = formatLoc(
				"data-table-editable--output--price", row - 1);
		final String outputPriceBelow = formatLoc(
				"data-table-editable--output--price", row + 1);
		final String buttonYes = getLoc("data-table-editable--button--yes");
		final String buttonCancel = getLoc("data-table-editable--button--cancel");

		// opens tab
		openPage(getMsg("data-table-editable--open-tab"), table);

		// get a initial value of selected row and its neighbour
		String value = selenium.getText(outputPrice);
		String valueAbove = selenium.getText(outputPriceAbove);
		String valueBelow = selenium.getText(outputPriceBelow);

		// open delete modal panel
		selenium.click(linkDelete);
		Wait.until(new Condition() {
			public boolean isTrue() {
				return !"none".equals(getStyle(panelDelete, "display"));
			}
		});

		// try to cancel delete action
		selenium.click(buttonCancel);
		Wait.until(new Condition() {
			public boolean isTrue() {
				return "none".equals(getStyle(panelDelete, "display"));
			}
		});

		// check that nothing happened with data model
		Assert.assertEquals(value, selenium.getText(outputPrice));
		Assert.assertEquals(valueAbove, selenium.getText(outputPriceAbove));
		Assert.assertEquals(valueBelow, selenium.getText(outputPriceBelow));

		// try to open delete modal panel again
		selenium.click(linkDelete);
		Wait.until(new Condition() {
			public boolean isTrue() {
				return !"none".equals(getStyle(panelDelete, "display"));
			}
		});

		// try to cancel delete action
		selenium.click(buttonYes);
		Wait.until(new Condition() {
			public boolean isTrue() {
				return "none".equals(getStyle(panelDelete, "display"));
			}
		});

		// check that value below moved to place where was value
		// and that nothing happened with value above
		Assert.assertEquals(valueBelow, selenium.getText(outputPrice));
		Assert.assertEquals(valueAbove, selenium.getText(outputPriceAbove));

		// goto next page and then back to first
		gotoNextAndThenBack();

		// check that value below moved to place where was value
		// and that nothing happened with value above
		Assert.assertEquals(valueBelow, selenium.getText(outputPrice));
		Assert.assertEquals(valueAbove, selenium.getText(outputPriceAbove));
	}

	private void gotoNextAndThenBack() {
		Assert.assertEquals("1", selenium.getText(outputActivePage));
		
		// go to next page
		selenium.click(buttonNextPage);
		Wait.until(new Condition() {
			public boolean isTrue() {
				return "2".equals(selenium.getText(outputActivePage));
			}
		});

		// go back to previous page
		selenium.click(buttonPreviousPage);
		Wait.until(new Condition() {
			public boolean isTrue() {
				return "1".equals(selenium.getText(outputActivePage));
			}
		});
	}
}
