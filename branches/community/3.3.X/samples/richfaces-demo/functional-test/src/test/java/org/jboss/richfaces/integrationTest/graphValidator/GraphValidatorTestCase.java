package org.jboss.richfaces.integrationTest.graphValidator;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class GraphValidatorTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.open(contextPath
				+ "/richfaces/graphValidator.jsf?c=graphValidator&tab=usage");
		scrollIntoView(header, true);
	}

	private String header = getLoc("graph-validator--header1");
	private String buttonSubmit = getLoc("graph-validator--button--submit1");
	private String inputName = getLoc("graph-validator--input--name");
	private String inputEmail = getLoc("graph-validator--input--email");
	private String inputAge = getLoc("graph-validator--input--age");
	private String relativeValidationMessage = getLoc("graph-validator--relative--validation-message");

	private void typeAndSubmit(String locator, String value) {
		selenium.type(locator, value);
		selenium.click(buttonSubmit);
	}

	public String getMessageFor(String locator) {
		return format(relativeValidationMessage, locator);
	}

	@Test
	public void nameValueRequired() {
		openPage();
		typeAndSubmit(inputName, "");
		waitForTextEquals(getMessageFor(inputName),
				getMess("may-not-be-null-or-empty"));
	}

	@Test
	public void nameMinimumLength() {
		openPage();
		typeAndSubmit(inputName, getMess("less-than-minimum--enter"));
		waitForTextEquals(getMessageFor(inputName),
				getMess("bean-name--length"));
	}

	@Test
	public void nameMaximumLength() {
		openPage();
		typeAndSubmit(inputName, getMess("greater-than-maximum--enter"));
		waitForTextEquals(getMessageFor(inputName),
				getMess("bean-name--length"));
	}

	@Test
	public void nameOnlySpacesPattern() {
		openPage();
		typeAndSubmit(inputName, " ");
		waitForTextEquals(getMessageFor(inputName), formatMess(
				"less-than-minimum-of-2", selenium.getAttribute(format(
						"{0}/@id", inputName))));
	}

	@Test
	public void nameMessageDisappers() {
		nameValueRequired();
		typeAndSubmit(inputName, getMess("bean-name--valid-input"));
		waitForTextEquals(getMessageFor(inputName), "");
	}

	@Test
	public void ageValueRequired() {
		openPage();
		typeAndSubmit(inputAge, "");
		waitForTextEquals(getMessageFor(inputAge), getMess("may-not-be-null"));
	}

	@Test
	public void ageMinimumValue() {
		openPage();
		typeAndSubmit(inputAge, getMess("long-range--bellow"));
		waitForTextEquals(getMessageFor(inputAge), getMess("bean-age--min"));
	}

	@Test
	public void ageMaximumValue() {
		openPage();
		typeAndSubmit(inputAge, getMess("bean-age--max--enter"));
		waitForTextEquals(getMessageFor(inputAge), getMess("bean-age--max"));
	}

	@Test
	public void ageIntegerOnly() {
		openPage();
		typeAndSubmit(inputAge, formatMess("age-integer-only--enter", inputAge));
		waitForTextEquals(getMessageFor(inputAge), formatMess(
				"bean-age--integer-only", selenium.getAttribute(format(
						"{0}/@id", inputAge))));
	}

	@Test
	public void ageMessageDisappers() {
		ageValueRequired();
		typeAndSubmit(inputAge, getMess("valid-age"));
		waitForTextEquals(getMessageFor(inputAge), "");
	}

	@Test
	public void emailMayNotBeNullOrEmpty() {
		openPage();
		typeAndSubmit(inputEmail, "");
		waitForTextEquals(getMessageFor(inputEmail),
				getMess("may-not-be-null-or-empty"));
	}

	@Test
	public void emailOnlySpaces() {
		openPage();
		typeAndSubmit(inputEmail, " ");
		waitForTextEquals(getMessageFor(inputEmail),
				getMess("not-well-formed-email"));
	}

	@Test
	public void emailBad1() {
		openPage();
		typeAndSubmit(inputEmail, getMess("bad-email-1"));
		waitForTextEquals(getMessageFor(inputEmail),
				getMess("not-well-formed-email"));
	}

	@Test
	public void emailBad2() {
		openPage();
		typeAndSubmit(inputEmail, getMess("bad-email-2"));
		waitForTextEquals(getMessageFor(inputEmail),
				getMess("not-well-formed-email"));
	}

	@Test
	public void emailMessageDisappers() {
		emailMayNotBeNullOrEmpty();
		typeAndSubmit(inputEmail, getMess("well-formed-email"));
		waitForTextEquals(getMessageFor(inputEmail), "");
	}
}
