package org.jboss.richfaces.integrationTest.dataGrid;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class DataGridTestCase extends AbstractDataIterationTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.open(format("{0}/{1}", contextPath, PAGE));

		scrollIntoView(table, true);
	}

	private final String PAGE = "richfaces/dataGrid.jsf?c=dataGrid&tab=usage";
	private final String grid = getLoc("data-grid--cells--preformatted");
	private final int cellsPerColumn = Integer
			.valueOf(getMess("data-grid--cells-per-column"));
	private final int cellsPerPage = Integer
			.valueOf(getMess("data-grid--cells-per-page"));

	@Test
	public void functionalityTest() {
		openPage();

		final Set<String> cellTexts = new HashSet<String>();
		
		while (getActivePage() < getLastVisiblePage()) {
			if (!cellTexts.isEmpty()) {
				gotoPage(buttonNextPage);
			}
			
			int cells = selenium.getXpathCount(format(grid, 0, 0)).intValue();
			Assert.assertTrue(cells > 0 && cells <= cellsPerPage);

			int rows = selenium.getXpathCount(format(grid, 0, 1)).intValue();


			for (int row = 1; row <= rows; row++) {
				int columns = selenium.getXpathCount(format(grid, row, 0))
						.intValue();
				Assert.assertTrue(columns > 0 && columns <= cellsPerColumn);

				for (int column = 1; column <= columns; column++) {
					String cellText = selenium
							.getText(format(grid, row, column));
					if (cellTexts.contains(cellText)) {
						Assert.fail();
					}
					cellTexts.add(cellText);
				}
			}
		}
	}
}
