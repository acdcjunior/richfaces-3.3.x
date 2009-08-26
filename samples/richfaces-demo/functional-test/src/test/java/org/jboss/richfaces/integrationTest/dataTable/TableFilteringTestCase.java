package org.jboss.richfaces.integrationTest.dataTable;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.waiting.Retrieve;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class TableFilteringTestCase extends AbstractDataIterationTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage(String page) {
		selenium.open(format("{0}/{1}", contextPath, page));

		scrollIntoView(table, true);
	}

	private final String inputState = getLoc("table-filtering--input--state-name");
	private final String inputCapital = getLoc("table-filtering--input--state-capital");
	private final String cellsState = getLoc("table-filtering--table-cell--state-name-preformatted");
	private final String cellsCapital = getLoc("table-filtering--table-cell--state-capital-preformatted");
	private final String[] statePrefixes = StringUtils.splitPreserveAllTokens(
			getMess("table-filtering--input--state-prefixes"), ',');
	private final String[] capitalPrefixes = StringUtils
			.splitPreserveAllTokens(
					getMess("table-filtering--input--capital-prefixes"), ',');
	private final String selectTimezone = getLoc("table-filtering-external--select--timezone");
	private final String optionTimezoneSelected = getLoc("table-filtering-external--option--selected");
	private final String cellsTimezone = cellsCapital;
	private final String outputTimezonePreformatted = getMess("data-filtering-external--output--timezone-preformatted");
	private final String[] pairs = StringUtils.splitPreserveAllTokens(
			getMess("table-filtering-external--input--pairs"), '|');

	@Test
	public void builtInFilteringTest() {
		openPage("richfaces/filteringFeature.jsf?c=filtering&tab=usage");

		Assert.assertTrue(statePrefixes.length == capitalPrefixes.length);

		for (int i = 0; i < statePrefixes.length; i++) {
			final String statePrefix = statePrefixes[i];
			final String capitalPrefix = capitalPrefixes[i];

			String tableText = selenium.getText(table);
			selenium.type(inputState, statePrefix);
			selenium.fireEvent(inputState, Event.KEYUP);
			waitForTextChanges(table, tableText);
			checkFiltering();

			tableText = selenium.getText(table);
			selenium.type(inputCapital, capitalPrefix);
			selenium.fireEvent(inputCapital, Event.KEYUP);
			waitForTextChanges(table, tableText);
			checkFiltering();
		}
	}

	@Test
	public void externalFilteringTest() {
		openPage("richfaces/filteringFeature.jsf?c=filtering&tab=ex-usage");

		for (int i = 0; i < pairs.length; i++) {
			String[] pair = StringUtils.splitPreserveAllTokens(pairs[i], ':');

			String tableText = selenium.getText(table);
			selenium.type(inputState, pair[0]);
			selenium.fireEvent(inputState, Event.KEYUP);
			Wait.dontFail().timeout(5000).waitForChange(tableText, retrieveTableText);
			checkExternalFiltering();

			tableText = selenium.getText(table);
			selenium.select(selectTimezone, pair[1]);
			selenium.fireEvent(selectTimezone, Event.CHANGE);
			Wait.dontFail().timeout(5000).waitForChange(tableText, retrieveTableText);
			checkExternalFiltering();
		}
	}
	
	Retrieve<String> retrieveTableText = new Retrieve<String>() {
		public String retrieve() {
			return selenium.getText(table);
		}
	};

	private void checkFiltering() {
		String statePrefix = selenium.getValue(inputState);
		String capitalPrefix = selenium.getValue(inputCapital);

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

	private void checkExternalFiltering() {
		String statePrefix = selenium.getValue(inputState);
		String selectedTimezone = selenium.getText(optionTimezoneSelected);

		int rows = selenium.getXpathCount(format(cellsState, 0)).intValue();

		for (int row = 1; row <= rows; row++) {
			if (statePrefix.length() > 0) {
				String state = selenium.getText(format(cellsState, row));
				Assert.assertTrue(state.startsWith(statePrefix));
			}
			String actualTimezone = selenium
					.getText(format(cellsTimezone, row));
			if (StringUtils.isNotBlank(selectedTimezone)) {
				String expected = format(outputTimezonePreformatted,
						selectedTimezone);
				Assert.assertEquals(expected, actualTimezone);
			} else {
				String prefix = format(outputTimezonePreformatted, "");
				actualTimezone.startsWith(prefix);
			}
		}
	}
}
