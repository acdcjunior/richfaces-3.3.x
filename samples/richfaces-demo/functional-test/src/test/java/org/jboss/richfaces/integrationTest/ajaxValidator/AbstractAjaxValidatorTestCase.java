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
package org.jboss.richfaces.integrationTest.ajaxValidator;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.testng.annotations.BeforeMethod;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class AbstractAjaxValidatorTestCase extends AbstractSeleniumRichfacesTestCase {

	private final String LOC_VALIDATION_MESSAGE_RELATIVE = getLoc("VALIDATION_MESSAGE_RELATIVE");

	/**
	 * Opens specified component's page before each test method
	 */
	@BeforeMethod
	protected void loadPage() {
		openComponent("Ajax Validator");
	}

	/**
	 * Fires selenium actions "type input" with given value and
	 * "blur from element" on specified locator.
	 * 
	 * @param locator
	 *            the locator where should be fired actions
	 * @param value
	 *            the value which should be typed in "type input" action
	 */
	protected void typeAndBlur(String locator, String value) {
		scrollIntoView(locator, true);
		
		selenium.type(locator, value);
		selenium.fireEvent(locator, Event.BLUR);
	}

	/**
	 * Get locator of message box for element given by locator
	 * 
	 * @param locator
	 *            for which element should be find message box
	 * @return locator of message box for element given by locator
	 */
	protected String getMessageFor(String locator) {
		scrollIntoView(locator, true);
		
		return format(LOC_VALIDATION_MESSAGE_RELATIVE, locator);
	}
}
