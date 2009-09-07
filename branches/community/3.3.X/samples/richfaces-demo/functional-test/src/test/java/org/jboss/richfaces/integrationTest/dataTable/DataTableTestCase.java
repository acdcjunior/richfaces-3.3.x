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
package org.jboss.richfaces.integrationTest.dataTable;

import static org.testng.Assert.*;

import org.jboss.richfaces.integrationTest.AbstractDataIterationTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version test$Revision$
 */
public class DataTableTestCase extends AbstractDataIterationTestCase {

	String LOC_FIELDSET_HEADER = getLoc("FIELDSET_HEADER");
	String LOC_TABLE = getLoc("TABLE");

	String MSG_TAB_TO_OPEN = getMsg("TAB_TO_OPEN");
	String MSG_TABLE_TEXT_CONTENT = getMsg("TABLE_TEXT_CONTENT");

	/**
	 * Get a whole table text content and compare it to expected value
	 */
	@Test
	public void testWholeDataTableContentTest() {
		openTab(MSG_TAB_TO_OPEN);
		scrollIntoView(LOC_FIELDSET_HEADER, true);

		assertEquals(selenium.getText(LOC_TABLE), MSG_TABLE_TEXT_CONTENT, "Table doesn't contain expected text");
	}

	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("Data Table");
		openTab("Usage");
	}
}




