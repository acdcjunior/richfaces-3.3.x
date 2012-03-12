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
