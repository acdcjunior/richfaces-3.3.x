package org.jboss.richfaces.integrationTest.ajaxValidator;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.Assert;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class AbstractAjaxValidatorTestCase extends
		AbstractSeleniumRichfacesTestCase {

	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.open(contextPath
				+ "/richfaces/ajaxValidator.jsf?c=ajaxValidator&tab=usage");
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
	public void typeAndBlur(String locator, String value) {
		selenium.type(locator, value);
		selenium.fireEvent(locator, "blur");
	}

	/**
	 * Get locator of message box for element given by locator
	 * 
	 * @param locator
	 *            for which element should be find message box
	 * @return locator of message box for element given by locator
	 */
	public String getMessageFor(String locator) {
		return String.format(getLoc("relativeMessageLink"), locator);
	}
}
