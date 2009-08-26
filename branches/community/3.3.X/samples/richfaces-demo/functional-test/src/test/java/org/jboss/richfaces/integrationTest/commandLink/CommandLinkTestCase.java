package org.jboss.richfaces.integrationTest.commandLink;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class CommandLinkTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	private void openPage() {
		selenium.open(contextPath
				+ "/richfaces/commandLink.jsf?c=commandLink&tab=usage");
	}

	private String textInput = getLoc("ajax-support--input");
	private String textOutput = formatLoc("command-button--text", 2);
	private String helloOutput = formatLoc("command-button--text", 1);
	private String exclamationOutput = formatLoc("command-button--text", 3);
	private String hello = formatMess("command-button--hello");
	private String exclamation = formatMess("command-button--exclamation");
	private String commandLink = getLoc("command-link--link");
	private String nonEmpty = getMess("ajax-support--non-empty");

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
		selenium.click(commandLink);
		waitFor(Wait.DEFAULT_INTERVAL);
		waitForTextEquals(textOutput, nonEmpty);
		Assert.assertEquals(selenium.getText(helloOutput), hello);
		Assert.assertEquals(selenium.getText(exclamationOutput), exclamation);
	}

	public void empty() {
		selenium.type(textInput, "");
		selenium.click(commandLink);
		waitFor(Wait.DEFAULT_INTERVAL);
		Assert.assertFalse(selenium.isElementPresent(textOutput));
	}
}
