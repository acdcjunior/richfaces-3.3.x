/*******************************************************************************
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *******************************************************************************/
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

			scrollIntoView(locQueueItem, false);

			waitModelUpdate.failWith(format("The enqueued item isn't present '{0}'", locQueueItem)).until(
					new Condition() {

						public boolean isTrue() {
							return selenium.isElementPresent(locQueueItem);
						}
					});
		}
	}

	protected void loadPage() {
		openComponent("Queue");
		openTab("Usage");
	}
}
