package org.jboss.richfaces.integrationTest.outputPanel;

import junit.framework.Assert;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.waiting.Condition;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class OutputPanelTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.open(contextPath
				+ "/richfaces/outputPanel.jsf?c=outputPanel&tab=usage");
		scrollIntoView(header, true);
	}

	private String header = getLoc("output-panel--header");
	private String inputFail = getLoc("output-panel--input--fail");
	private String inputSuccess = getLoc("output-panel--input--success");
	private String textCorrect = getMess("output-panel--input--correct");
	private String textWrong = getMess("output-panel--input--wrong");
	private String textValidationError = getMess("output-panel--output--validation-error");
	private String formatMessage = getLoc("output-panel--output--message");

	Condition isApprovedTextPresent = new Condition() {
		public boolean isTrue() {
			return true;
		}
	};

	@Test
	public void correctTextIntoFailInputTest() {
		textIntoInput(inputFail, textCorrect, false, false);
	}

	@Test
	public void wrongTextIntoFailInputTest() {
		textIntoInput(inputFail, textWrong, false, false);
	}

	@Test
	public void correctTextIntoSuccessInputTest() {
		textIntoInput(inputSuccess, textCorrect, true, false);
	}

	@Test
	public void wrongTextIntoSuccessInputTest() {
		textIntoInput(inputSuccess, textWrong, false, true);
	}

	public void textIntoInput(String input, final String text,
			final boolean textAppears, final boolean errorAppears) {
		openPage();

		selenium.type(input, text);
		selenium.fireEvent(input, Event.KEYUP);

		waitModelUpdate.dontFail().until(new Condition() {
			public boolean isTrue() {
				if (errorAppears || textAppears) {
					return selenium.getXpathCount(format(formatMessage, text))
							.intValue() > 0
							|| selenium.getXpathCount(
									format(formatMessage, textValidationError))
									.intValue() > 0;
				}
				return false;
			}
		});

		Assert.assertEquals(textAppears ? 1 : 0, selenium.getXpathCount(format(
				formatMessage, text)));
		Assert.assertEquals(errorAppears ? 1 : 0, selenium
				.getXpathCount(format(formatMessage, textValidationError)));
	}
}
