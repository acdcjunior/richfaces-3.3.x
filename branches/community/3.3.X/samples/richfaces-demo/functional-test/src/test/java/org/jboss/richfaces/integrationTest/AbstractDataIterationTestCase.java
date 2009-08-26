package org.jboss.richfaces.integrationTest;

import java.util.Properties;

import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.Assert;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class AbstractDataIterationTestCase extends
		AbstractSeleniumRichfacesTestCase {

	protected Properties getMessagesProperties() {
		Properties childProperties = super.getMessagesProperties();
		Properties dataIterationCommonProperties = getNamedPropertiesForClass(
				AbstractSeleniumRichfacesTestCase.class,
				"data-iteration--messages");
		dataIterationCommonProperties.putAll(childProperties);
		return dataIterationCommonProperties;
	}

	protected Properties getLocatorsProperties() {
		Properties childProperties = super.getLocatorsProperties();
		Properties dataIterationCommonProperties = getNamedPropertiesForClass(
				AbstractSeleniumRichfacesTestCase.class,
				"data-iteration--locators");
		dataIterationCommonProperties.putAll(childProperties);
		return dataIterationCommonProperties;
	}
	
	protected final String table = getLoc("data-table-common--table");
	protected final String buttonFirstPage = getLoc("data-scroller--button--first-page");
	protected final String buttonLastPage = getLoc("data-scroller--button--last-page");
	protected final String buttonNextPage = getLoc("data-scroller--button--next-page");
	protected final String buttonPreviousPage = getLoc("data-scroller--button--previous-page");
	protected final String buttonPagePreformatted = getLoc("data-scroller--button--numbered-page-preformatted");
	protected final String outputActivePage = getLoc("data-scroller--output--active-page");

	protected void gotoPage(String button) {
		final String previousPage = getActivePage().toString();
		if (previousPage.equals(selenium.getText(button))) {
			return;
		}
		if (previousPage.equals("1")
				&& (buttonFirstPage.equals(button) || buttonPreviousPage
						.equals(button))) {
			return;
		}
		if (previousPage.equals(getLastVisiblePage().toString())
				&& (buttonLastPage.equals(button) || buttonNextPage
						.equals(button))) {
			return;
		}
		selenium.click(button);
		Wait.until(new Condition() {
			public boolean isTrue() {
				return !previousPage.equals(getActivePage().toString());
			}
		});
	}

	protected Integer getActivePage() {
		return Integer.valueOf(selenium.getText(outputActivePage));
	}

	protected Integer getLastVisiblePage() {
		Number pages = selenium
				.getXpathCount(format(buttonPagePreformatted, 0));
		String lastVisiblePage = selenium.getText(format(
				buttonPagePreformatted, pages));
		return Integer.valueOf(lastVisiblePage);
	}

	protected String getTableText() {
		return selenium.getText(table);
	}

	protected void checkSorting(String columnPreformatted) {
		checkSortingForColumnOrder(columnPreformatted);
	}

	protected void checkSortingForColumnOrder(String... columnsPreformatted) {
		final boolean navigationEnabled = selenium
				.isElementPresent(buttonFirstPage);

		if (navigationEnabled)
			gotoPage(buttonFirstPage);

		final int columns = columnsPreformatted.length;

		String[] lastText = new String[columns];
		Boolean[] sortedAscending = new Boolean[columns];

		while ((!navigationEnabled && lastText[0] == null)
				|| (navigationEnabled && getActivePage() < getLastVisiblePage())) {
			if (navigationEnabled && lastText[0] != null) {
				gotoPage(buttonNextPage);
			}
			
			final int rows = selenium.getXpathCount(
					format(columnsPreformatted[0], 0)).intValue();

			for (int row = 1; row <= rows; row++) {
				for (int column = 0; column < columns; column++) {
					String text = selenium.getText(format(
							columnsPreformatted[column], row));
					if (lastText[column] != null) {
						int comparison = text.compareTo(lastText[column]);
						try {
							Double number = Double.parseDouble(text);
							Double lastNumber = Double
									.parseDouble(lastText[column]);
							comparison = number.compareTo(lastNumber);
						} catch (NumberFormatException e) {
						}
						if (sortedAscending[column] == null) {
							if (comparison > 0) {
								sortedAscending[column] = true;
								break;
							} else if (comparison < 0) {
								sortedAscending[column] = false;
								break;
							}
						} else {
							if (sortedAscending[column]) {
								if (comparison < 0) {
									Assert.fail();
								}
								break;
							} else {
								if (comparison > 0) {
									Assert.fail();
								}
								break;
							}
						}
					}
					lastText[column] = text;
				}
			}
		}
	}
}
