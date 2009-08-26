package org.jboss.richfaces.integrationTest.ajaxValidator;

import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class AjaxValidatorHibernatePartTestCase extends
		AbstractAjaxValidatorTestCase {

	/* COMPONENTS */

	private final String name = getLoc("hibernateName");
	private final String age = getLoc("hibernateAge");
	private final String email = getLoc("hibernateEmail");

	/* TESTS */

	@Test
	public void nameValueRequired() {
		openPage();
		typeAndBlur(name, "");
		waitForTextEquals(getMessageFor(name), formatMess("value-required",
				name));
	}

	@Test
	public void nameMinimumLength() {
		openPage();
		typeAndBlur(name, getMess("less-than-minimum--enter"));
		waitForTextEquals(getMessageFor(name), getMess("bean-name--length"));
	}

	@Test
	public void nameMaximumLength() {
		openPage();
		typeAndBlur(name, getMess("greater-than-maximum--enter"));
		waitForTextEquals(getMessageFor(name), getMess("bean-name--length"));
	}

	@Test
	public void nameOnlySpacesPattern() {
		openPage();
		typeAndBlur(name, " ");
		waitForTextEquals(getMessageFor(name), getMess("bean-name--not-empty"));
	}

	@Test
	public void nameMessageDisappers() {
		nameValueRequired();
		typeAndBlur(name, getMess("bean-name--valid-input"));
		waitForTextEquals(getMessageFor(name), "");
	}

	@Test
	public void ageValueRequired() {
		openPage();
		typeAndBlur(age, "");
		waitForTextEquals(getMessageFor(age), getMess("may-not-be-null"));
	}

	@Test
	public void ageMinimumValue() {
		openPage();
		typeAndBlur(age, getMess("long-range--bellow"));
		waitForTextEquals(getMessageFor(age), getMess("bean-age--min"));
	}

	@Test
	public void ageMaximumValue() {
		openPage();
		typeAndBlur(age, getMess("bean-age--max--enter"));
		waitForTextEquals(getMessageFor(age), getMess("bean-age--max"));
	}

	@Test
	public void ageIntegerOnly() {
		openPage();
		typeAndBlur(age, formatMess("age-integer-only--enter", age));
		waitForTextEquals(getMessageFor(age), formatMess(
				"bean-age--integer-only", age));
	}

	@Test
	public void ageMessageDisappers() {
		ageValueRequired();
		typeAndBlur(age, getMess("valid-age"));
		waitForTextEquals(getMessageFor(age), "");
	}

	@Test
	public void emailMayNotBeNullOrEmpty() {
		openPage();
		typeAndBlur(email, "");
		waitForTextEquals(getMessageFor(email),
				getMess("may-not-be-null-or-empty"));
	}

	@Test
	public void emailOnlySpaces() {
		openPage();
		typeAndBlur(email, " ");
		waitForTextEquals(getMessageFor(email),
				getMess("not-well-formed-email"));
	}

	@Test
	public void emailBad1() {
		openPage();
		typeAndBlur(email, getMess("bad-email-1"));
		waitForTextEquals(getMessageFor(email),
				getMess("not-well-formed-email"));
	}

	@Test
	public void emailBad2() {
		openPage();
		typeAndBlur(email, getMess("bad-email-2"));
		waitForTextEquals(getMessageFor(email),
				getMess("not-well-formed-email"));
	}

	@Test
	public void emailMessageDisappers() {
		emailMayNotBeNullOrEmpty();
		typeAndBlur(email, getMess("well-formed-email"));
		waitForTextEquals(getMessageFor(email), "");
	}
}
