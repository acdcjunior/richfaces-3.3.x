/**
 * License Agreement.
 *
 * Rich Faces - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */
package org.richfaces.testng.rf7292;

import org.richfaces.SeleniumTestBase;

/**
 * @author Mikhail Vitenkov
 * @since 3.3.2
 */
public class Test extends SeleniumTestBase {
	private static final String FORM = "form";
	private static final String SECOND_LABEL = FORM + ":second_lbl";
	private static final String OUT = FORM + ":out";
	
	private String getIdSelector(String id) {
		return "//*[@id='" + id + "']";
	}

	@org.testng.annotations.Test
	public void testExecute() throws Exception {
		renderPage();
		
		clickAjaxCommandAndWait(getIdSelector(SECOND_LABEL));		
		AssertVisible(OUT);		
	}
		
	public String getTestUrl() {
		return "pages/rf7292.xhtml";
	}

}