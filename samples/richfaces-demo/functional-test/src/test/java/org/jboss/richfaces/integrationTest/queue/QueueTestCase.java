/**
 * License Agreement.
 *
 *  JBoss RichFaces
 *
 * Copyright (C) 2009  Red Hat, Inc.
 *
 * This code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this test suite; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */
package org.jboss.richfaces.integrationTest.queue;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class QueueTestCase extends AbstractSeleniumRichfacesTestCase {
	private String LOC_BUTTON_IMAGE_PREFORMATTED = getLoc("BUTTON_IMAGE_PREFORMATTED");
	private String LOC_OUTPUT_QUEUE_ITEM = getLoc("OUTPUT_QUEUE_ITEM");

	/**
	 * Enqueues several numbers (by clicking to numbered images) and waits for
	 * it it appears in the queue bottom.
	 */
	@Test
	public void testSimpleQueueImages() {
		int[] order = new int[] { 3, 7, 15, 3, 15 };

		for (int i = 0; i < order.length; i++) {
			final String locButtonImage = format(LOC_BUTTON_IMAGE_PREFORMATTED, order[i]);
			final String locQueueItem = format(LOC_OUTPUT_QUEUE_ITEM, order[i]);

			selenium.click(locButtonImage);

			scrollIntoView(locButtonImage, true);

			waitFor(1000);

			scrollIntoView(locQueueItem, false);

			waitModelUpdate.failWith(format("The enqueued item isn't present '{0}'", locQueueItem)).until(
					new Condition() {

						public boolean isTrue() {
							return selenium.isElementPresent(locQueueItem);
						}
					});
		}
	}

	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("Queue");
		openTab("Usage");
	}
}
