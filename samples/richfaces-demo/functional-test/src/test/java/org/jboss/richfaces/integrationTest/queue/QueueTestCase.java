package org.jboss.richfaces.integrationTest.queue;

import junit.framework.Assert;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class QueueTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	private void openPage() {
		selenium.open(contextPath + "/richfaces/queue.jsf?c=queue&tab=usage");
	}

	private String tableHeader = getLoc("queue--table-header");

	@Test
	public void simpleQueueImagesTest() {
		openPage();

		scrollIntoView(tableHeader, true);

		int[] order = new int[] { 3, 7, 15, 3, 15 };

		for (int i = 0; i < order.length; i++) {
			selenium.click(formatLoc("queue--image-button", order[i]));

			waitFor(1000);

			Assert.assertTrue(selenium.isElementPresent(formatLoc(
					"queue--item", order[i])));
		}
	}
}
