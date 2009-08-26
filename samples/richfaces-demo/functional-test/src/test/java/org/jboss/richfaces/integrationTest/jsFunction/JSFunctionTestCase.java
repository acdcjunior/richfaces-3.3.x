package org.jboss.richfaces.integrationTest.jsFunction;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.waiting.Condition;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class JSFunctionTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	private void openPage() {
		selenium.open(contextPath
				+ "/richfaces/jsFunction.jsf?c=jsFunction&tab=usage");
	}

	@Test
	public void hoveringNames() {
		openPage();
		
		final String showName = getLoc("js-function--show-name");
		String[] names = new String[] {getMess("js-function--name1"), getMess("js-function--name2"), getMess("js-function--name3")};

		for (final String name : names) {
			String span = formatLoc("js-function--active-span", name);

			Assert.assertTrue(StringUtils.isBlank(selenium.getText(showName)));
			
			selenium.fireEvent(span, Event.MOUSEOVER);

			waitModelUpdate.until(new Condition() {
				public boolean isTrue() {
					return name.equals(selenium.getText(showName));
				}
			});
			
			selenium.fireEvent(span, Event.MOUSEOUT);
			
			waitModelUpdate.until(new Condition() {
				public boolean isTrue() {
					return StringUtils.isBlank(selenium.getText(showName));
				}
			});
		}
	}
}
