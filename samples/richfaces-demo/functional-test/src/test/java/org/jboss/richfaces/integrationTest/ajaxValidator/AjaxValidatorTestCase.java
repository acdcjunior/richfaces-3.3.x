package org.jboss.richfaces.integrationTest.ajaxValidator;

import java.text.MessageFormat;

import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class AjaxValidatorTestCase extends AbstractAjaxValidatorTestCase {

	/* COMPONENTS */

	private final String name = getLoc("name");
	private final String age = getLoc("age");

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
		waitForTextEquals(getMessageFor(name), formatMess("less-than-minimum",
				name));
	}

	@Test
	public void nameMaximumLength() {
		openPage();
		typeAndBlur(name, getMess("greater-than-maximum--enter"));
		waitForTextEquals(getMessageFor(name), formatMess(
				"greater-than-maximum", name));
	}

	@Test
	public void nameMessageDisappers() {
		nameValueRequired(); // violates validation message
		typeAndBlur(name, getMess("valid-name"));
		waitForTextEquals(getMessageFor(name), "");
	}

	@Test
	public void ageValueRequired() {
		openPage();
		typeAndBlur(age, "");
		waitForTextEquals(getMessageFor(age), formatMess("value-required", age));
	}

	@Test
	public void ageMinimumValue() {
		openPage();
		typeAndBlur(age, getMess("long-range--bellow"));
		waitForTextEquals(getMessageFor(age), formatMess("long-range", age));
	}

	@Test
	public void ageMaximumValue() {
		openPage();
		typeAndBlur(age, getMess("long-range--above"));
		waitForTextEquals(getMessageFor(age), formatMess("long-range", age));
	}

	@Test
	public void ageIntegerOnly() {
		openPage();
		typeAndBlur(age, getMess("age-integer-only--enter"));
		waitForTextEquals(getMessageFor(age), formatMess("age-integer-only",
				age));
	}

	@Test
	public void ageMessageDisappers() {
		ageValueRequired();
		typeAndBlur(age, getMess("valid-age"));
		waitForTextEquals(getMessageFor(age), "");
	}
}
