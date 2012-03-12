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
package org.jboss.richfaces.integrationTest.dataList;

import static org.testng.Assert.*;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class DataListTestCase extends AbstractSeleniumRichfacesTestCase {

	private final String LOC_FIELDSET_HEADER = getLoc("FIELDSET_HEADER");
	private final String MSG_OUTPUT_PRESENT_TEXT_1 = getMsg("OUTPUT_PRESENT_TEXT_1");
	private final String MSG_OUTPUT_PRESENT_TEXT_2 = getMsg("OUTPUT_PRESENT_TEXT_2");

	/**
	 * Test that the predefined strings are present on the page as expected.
	 */
	@Test
	public void testStaticContent() {
		assertTrue(selenium.isTextPresent(MSG_OUTPUT_PRESENT_TEXT_1), format("Text '{0}' isn't present on the page",
				MSG_OUTPUT_PRESENT_TEXT_1));
		assertTrue(selenium.isTextPresent(MSG_OUTPUT_PRESENT_TEXT_2), format("Text '{0}' isn't present on the page",
				MSG_OUTPUT_PRESENT_TEXT_2));
	}

	protected void loadPage() {
		openComponent("Data List");
		scrollIntoView(LOC_FIELDSET_HEADER, true);
	}
}
