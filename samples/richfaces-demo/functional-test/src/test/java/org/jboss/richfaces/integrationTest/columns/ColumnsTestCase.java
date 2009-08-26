package org.jboss.richfaces.integrationTest.columns;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class ColumnsTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.open(format("{0}/{1}", contextPath, PAGE));

		scrollIntoView(header, true);
	}

	private final String PAGE = "richfaces/columns.jsf?c=columns&tab=usage";
	private final String table = getLoc("columns--table");
	private final String header = table;
	private final String cellHeaders = getLoc("columns--table-cell-header--preformatted");
	private final String cells = getLoc("columns--table-cell--preformatted");
	private final Pattern patternPrice = Pattern.compile(".*\\s(\\d+)\\$$");

	@Test
	public void simpleSortingTest() {
		openPage();

		final int sortColumn = 2;

		String[][] map = getMapOfCells();

		final String contextBeforeChange = selenium.getText(table);

		selenium.click(format(cellHeaders, sortColumn));

		sortByColumn(map, sortColumn, false);

		Wait.dontFail().until(new Condition() {
			public boolean isTrue() {
				return !contextBeforeChange.equals(selenium.getText(table));
			}
		});

		String[][] newMap = getMapOfCells();

		for (int i = 0; i < newMap.length; i++) {
			Assert.assertTrue(Arrays.equals(map[i], newMap[i]));
		}
	}

	@Test
	public void reverseOrderSortingTest() {
		openPage();

		final int sortColumn = 4;

		String[][] map = getMapOfCells();

		final String contextBeforeChange = selenium.getText(table);

		selenium.click(format(cellHeaders, sortColumn));

		sortByColumn(map, sortColumn, true);

		Wait.dontFail().until(new Condition() {
			public boolean isTrue() {
				return !contextBeforeChange.equals(selenium.getText(table));
			}
		});

		selenium.click(format(cellHeaders, sortColumn));

		Wait.dontFail().until(new Condition() {
			public boolean isTrue() {
				return !contextBeforeChange.equals(selenium.getText(table));
			}
		});

		String[][] newMap = getMapOfCells();

		for (int i = 0; i < newMap.length; i++) {
			Assert.assertTrue(Arrays.equals(map[i], newMap[i]));
		}
	}

	private String[][] getMapOfCells() {
		final int columns = selenium.getXpathCount(format(cells, 1, 0))
				.intValue();
		final int rows = selenium.getXpathCount(format(cells, 0, 1)).intValue();

		String[][] map = new String[rows][columns];

		for (int row = 1; row <= rows; row++) {
			map[row - 1] = new String[columns];
			for (int column = 1; column <= columns; column++) {
				String cell = format(cells, row, column);
				map[row - 1][column - 1] = selenium.getText(cell);
			}
		}
		return map;
	}

	private void sortByColumn(final String[][] map, final int column,
			final boolean reverseOrder) {
		Arrays.sort(map, new Comparator<String[]>() {
			public int compare(String[] o1, String[] o2) {
				String s1 = o1[column - 1];
				String s2 = o2[column - 1];

				Matcher m1 = patternPrice.matcher(s1);
				m1.find();
				int i1 = Integer.valueOf(m1.group(1));

				Matcher m2 = patternPrice.matcher(s2);
				m2.find();
				int i2 = Integer.valueOf(m2.group(1));

				if (reverseOrder) {
					return i2 - i1;
				} else {
					return i1 - i2;
				}
			}
		});
	}
}
