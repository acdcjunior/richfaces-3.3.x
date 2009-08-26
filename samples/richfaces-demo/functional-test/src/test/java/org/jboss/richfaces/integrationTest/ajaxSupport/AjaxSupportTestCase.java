package org.jboss.richfaces.integrationTest.ajaxSupport;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class AjaxSupportTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	private void openPage() {
		selenium.open(contextPath
				+ "/richfaces/support.jsf?c=support&tab=usage");
	}

	private String textInput = getLoc("ajax-support--input");
	private String relativeText = formatLoc("ajax-support--text-relative",
			textInput);
	private String nonEmpty = getMess("ajax-support--non-empty");
	private String inputEvent = getMess("ajax-support--input-event");

	@Test
	public void testNonEmpty() {
		openPage();
		nonEmpty();
	}

	@Test
	public void testEmpty() {
		openPage();
		empty();
	}

	@Test
	public void testInterleaving() {
		openPage();
		nonEmpty();
		empty();
		nonEmpty();
		empty();
	}

	public void nonEmpty() {
		selenium.type(textInput, nonEmpty);
		selenium.fireEvent(textInput, inputEvent);
		waitFor(Wait.DEFAULT_INTERVAL);
		waitForTextEquals(relativeText, nonEmpty);
	}

	public void empty() {
		selenium.type(textInput, "");
		selenium.fireEvent(textInput, inputEvent);
		waitFor(Wait.DEFAULT_INTERVAL);
		waitForTextEquals(textInput, "");
	}
}
