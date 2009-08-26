package org.jboss.richfaces.integrationTest.include;

import junit.framework.Assert;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class IncludeTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.open(contextPath
				+ "/richfaces/include.jsf?c=include&tab=usage");
		scrollIntoView(header, true);
	}

	private String header = getLoc("include--header");
	private String buttonNext = getLoc("include--button--next");
	private String buttonPrevious = getLoc("include--button--previous");
	private String inputFirstName = getLoc("include--input--first-name");
	private String inputLastName = getLoc("include--input--last-name");
	private String inputCompany = getLoc("include--input--company");
	private String inputNotes = getLoc("include--input--notes");
	private String outputFirstName = getLoc("include--output--first-name");
	private String outputLastName = getLoc("include--output--last-name");
	private String outputCompany = getLoc("include--output--company");
	private String outputNotes = getLoc("include--output--notes");
	private String sample = getMess("include--input--sample");

	@Test
	public void goThroughStepsTest() {
		openPage();

		goThroughSteps();
	}

	@Test(dependsOnMethods = { "goThroughStepsTest" })
	public void goThroughStepsBackTest() {
		openPage();

		goThroughSteps();
		goThroughStepsBack();
	}

	@Test
	public void tryFailedValidationTest() {
		openPage();

		Assert.assertTrue(isFirstPage());

		selenium.type(inputFirstName, format(sample, 1));
		selenium.type(inputLastName, format(sample, 2));
		selenium.click(buttonNext);

		waitForText(getMess("include--message--company-required"));

		selenium.type(inputCompany, format(sample, 3));
		selenium.click(buttonNext);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return isSecondPage();
			}
		});

		selenium.click(buttonPrevious);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return isFirstPage();
			}
		});

		selenium.type(inputLastName, "");
		selenium.click(buttonNext);

		waitForText(getMess("include--message--last-name-required"));

		selenium.type(inputLastName, format(sample, 2));
		selenium.click(buttonNext);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return isSecondPage();
			}
		});

		selenium.click(buttonNext);

		waitForText(getMess("include--message--notes-required"));

		selenium.type(inputNotes, format(sample, 4));
		selenium.click(buttonNext);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return isLastPage();
			}
		});

		Assert.assertEquals(format(sample, 1), selenium
				.getText(outputFirstName));
		Assert
				.assertEquals(format(sample, 2), selenium
						.getText(outputLastName));
		Assert.assertEquals(format(sample, 3), selenium.getText(outputCompany));
		Assert.assertEquals(format(sample, 4), selenium.getText(outputNotes));
	}

	public void goThroughSteps() {
		Assert.assertTrue(isFirstPage());

		selenium.type(inputFirstName, format(sample, 1));
		selenium.type(inputLastName, format(sample, 2));
		selenium.type(inputCompany, format(sample, 3));
		selenium.click(buttonNext);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return isSecondPage();
			}
		});

		selenium.type(inputNotes, format(sample, 4));
		selenium.click(buttonNext);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return isLastPage();
			}
		});

		Assert.assertEquals(format(sample, 1), selenium
				.getText(outputFirstName));
		Assert
				.assertEquals(format(sample, 2), selenium
						.getText(outputLastName));
		Assert.assertEquals(format(sample, 3), selenium.getText(outputCompany));
		Assert.assertEquals(format(sample, 4), selenium.getText(outputNotes));
	}

	private void goThroughStepsBack() {
		selenium.click(buttonPrevious);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return isSecondPage();
			}
		});

		Assert.assertEquals(format(sample, 4), selenium.getValue(inputNotes));

		selenium.click(buttonPrevious);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return isFirstPage();
			}
		});

		Assert.assertEquals(format(sample, 1), selenium
				.getValue(inputFirstName));
		Assert
				.assertEquals(format(sample, 2), selenium
						.getValue(inputLastName));
		Assert.assertEquals(format(sample, 3), selenium.getValue(inputCompany));
	}

	private boolean isFirstPage() {
		return isButtonsPresent(false, true);
	}

	private boolean isSecondPage() {
		return isButtonsPresent(true, true);
	}

	private boolean isLastPage() {
		return isButtonsPresent(true, false);
	}

	private boolean isButtonsPresent(boolean previousPresent,
			boolean nextPresent) {
		return (previousPresent == selenium.isElementPresent(buttonPrevious))
				&& (nextPresent == selenium.isElementPresent(buttonNext));
	}
}
