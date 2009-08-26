package org.jboss.richfaces.integrationTest.graphValidator;

import junit.framework.Assert;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class GraphValidatorAfterModelUpdateTestCase extends
		AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.open(contextPath
				+ "/richfaces/graphValidator.jsf?c=graphValidator&tab=usage");
		scrollIntoView(header, true);
	}

	private String header = getLoc("graph-validator--header2");
	private String buttonSubmit = getLoc("graph-validator--button--submit2");
	private String validationMessage = getLoc("graph-validator--output--validation-message");
	private String validationMessageClass = getLoc("graph-validator--attribute--validation-message-class");
	private String activityTimes = getLoc("graph-validator--input--activity-times");
	private String validMessageClass = getMess("graph-validator--attribute--class-valid");
	private String invalidMessageClass = getMess("graph-validator--attribute--class-invalid");

	@Test
	public void noChangeIntoFormTest() {
		openPage();

		submitAndWaitForMessageAppears();

		validateMessages(
				invalidMessageClass,
				getMess("graph-validator--message--please-fill-at-least-one-entry"));
	}

	@Test
	public void changeStoredSuccessfullyTest() {
		openPage();

		typeAndSubmit(format(activityTimes, 1),
				getMess("graph-validator--input--ok"));

		validateMessages(
				validMessageClass,
				getMess("graph-validator--message--changes-stored-successfully"));
	}

	@Test
	public void oneValueTooGreatTest() {
		openPage();

		typeAndSubmit(format(activityTimes, 1),
				getMess("graph-validator--input--too-great"));

		validateMessages(invalidMessageClass,
				getMess("graph-validator--message--invalid-values"));
	}

	@Test
	public void sumOfValuesTooGreatTest() {
		openPage();

		for (int i = 1; i <= 3; i++) {
			selenium.type(format(activityTimes, i),
					getMess("graph-validator--input--sum-too-great"));
		}
		submitAndWaitForMessageAppears();

		validateMessages(invalidMessageClass,
				getMess("graph-validator--message--sum-too-great"));
	}

	/* HELP METHODS */

	private void typeAndSubmit(String locator, String text) {
		selenium.type(locator, text);
		submitAndWaitForMessageAppears();
	}

	private void submitAndWaitForMessageAppears() {
		selenium.click(buttonSubmit);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return selenium.isElementPresent(validationMessage);
			}
		});
	}

	private void validateMessages(String className, String text) {
		Assert.assertEquals(className, selenium
				.getAttribute(validationMessageClass));
		Assert.assertEquals(text, selenium.getText(validationMessage));
	}
}
