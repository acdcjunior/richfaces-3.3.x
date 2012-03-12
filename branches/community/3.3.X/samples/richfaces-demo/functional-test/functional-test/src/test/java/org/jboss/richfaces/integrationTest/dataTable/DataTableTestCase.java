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
package org.jboss.richfaces.integrationTest.dataTable;

import static org.testng.Assert.*;

import org.apache.commons.lang.StringUtils;
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

		String tableText = selenium.getText(LOC_TABLE);
		tableText = StringUtils.deleteWhitespace(tableText);
		assertEquals(tableText, MSG_TABLE_TEXT_CONTENT, "Table doesn't contain expected text");
	}

	protected void loadPage() {
		openComponent("Data Table");
		openTab("Usage");
	}
}




