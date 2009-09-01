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
package org.jboss.richfaces.integrationTest.ajaxForm;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class AjaxFormTestCase extends AbstractSeleniumRichfacesTestCase {

	private String LOC_LEGEND_HEADER = getLoc("LEGEND_HEADER");
	private String LOC_BUTTON_AJAX = getLoc("BUTTON_AJAX");
	private String LOC_BUTTON_NON_AJAX = getLoc("BUTTON_NON_AJAX");
	private String LOC_OUTPUT_TEXT_RELATIVE = getLoc("OUTPUT_TEXT_RELATIVE");
	private String LOC_OUTPUT_AJAX = format(LOC_OUTPUT_TEXT_RELATIVE, LOC_BUTTON_AJAX);
	private String LOC_OUTPUT_NON_AJAX = format(LOC_OUTPUT_TEXT_RELATIVE, LOC_BUTTON_NON_AJAX);

	private final String MSG_OUTPUT_NON_AJAX = getMsg("OUTPUT_NON_AJAX");
	private final String MSG_OUTPUT_AJAX = getMsg("OUTPUT_AJAX");

	/**
	 * Sends non-Ajax request and check that it changes both of output fields.
	 */
	@Test
	public void testNonAjaxSubmit() {
		nonAjaxSubmit();
	}

	/**
	 * Sends Ajax request and check that it changes only ajax-rendered field.
	 */
	@Test
	public void testAjaxSubmit() {
		ajaxSubmit();
	}

	/**
	 * Test interleaving of non-Ajax and Ajax requested changes and checks that
	 * it is rendered in right way.
	 */
	@Test
	public void testInterleaving() {
		ajaxSubmit();
		nonAjaxSubmit();
		ajaxSubmit();
		nonAjaxSubmit();
	}

	public void nonAjaxSubmit() {
		selenium.click(LOC_BUTTON_NON_AJAX);
		selenium.waitForPageToLoad(String.valueOf(Wait.DEFAULT_TIMEOUT));

		scrollIntoView(LOC_LEGEND_HEADER, true);

		// both of outputs should be same
		String actual = selenium.getText(LOC_OUTPUT_AJAX);
		assertEquals(actual, MSG_OUTPUT_NON_AJAX);

		actual = selenium.getText(LOC_OUTPUT_NON_AJAX);
		assertEquals(actual, MSG_OUTPUT_NON_AJAX);
	}

	public void ajaxSubmit() {
		String startingNonAjaxText = selenium.getText(LOC_OUTPUT_NON_AJAX);

		// ajax-rendered output should be set right
		selenium.click(LOC_BUTTON_AJAX);
		waitForTextEquals(LOC_OUTPUT_AJAX, MSG_OUTPUT_AJAX);

		// non-ajax output should stay same like before the request
		String actual = selenium.getText(LOC_OUTPUT_NON_AJAX);
		assertEquals(actual, startingNonAjaxText);
	}

	/**
	 * Opens specific component's page
	 */
	@SuppressWarnings("unused")
	@BeforeMethod
	private void loadPage() {
		openComponent("Ajax Form");

		scrollIntoView(LOC_LEGEND_HEADER, true);
	}
}
