package org.jboss.richfaces.integrationTest.keepAlive;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.dom.Event;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class KeepAliveTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	private void openPage() {
		selenium.open(contextPath
				+ "/richfaces/keepAlive.jsf?c=keepAlive&tab=usage");
	}

	private String header = getLoc("keep-alive--header");
	private String inputIncorrect1 = getLoc("keep-alive--input--incorrect1");
	private String inputIncorrect2 = getLoc("keep-alive--input--incorrect2");
	private String buttonIncorrect = getLoc("keep-alive--button--incorrect");
	private String outputIncorrect = getLoc("keep-alive--output--incorrect");
	private String inputCorrect1 = getLoc("keep-alive--input--correct1");
	private String inputCorrect2 = getLoc("keep-alive--input--correct2");
	private String buttonCorrect = getLoc("keep-alive--button--correct");
	private String outputCorrect = getLoc("keep-alive--output--correct");
	private String firstNumber = getMess("keep-alive--input--first-number");
	private String secondNumber = getMess("keep-alive--input--second-number");
	private String expectedResultNumber = getMess("keep-alive--output--expected-result-number");

	@Test
	public void usingIncorrectWay() {
		openPage();
		scrollIntoView(header, true);

		// fill first two summands
		selenium.type(inputIncorrect1, firstNumber);
		selenium.fireEvent(inputIncorrect1, Event.KEYUP);
		selenium.type(inputIncorrect2, secondNumber);
		selenium.fireEvent(inputIncorrect2, Event.KEYUP);

		// wait for "equal sign" button became enabled (lost disabled status)
		Wait.until(new Condition() {
			public boolean isTrue() {
				boolean result = selenium.isElementPresent(format(
						"{0}/@disabled", buttonIncorrect));
				return !result;

			}
		});

		// try to count result
		selenium.click(buttonIncorrect);

		// TODO: try to find exact way to catch "progress done" event
		// waiting for summarization is processed
		waitFor(5000);
		
		// get a result and validate it
		String result = selenium.getText(outputIncorrect);
		Assert.assertTrue(StringUtils.isBlank(result));
	}

	@Test
	public void usingCorrectWay() {
		openPage();
		scrollIntoView(header, true);

		// fill first two summands
		selenium.type(inputCorrect1, firstNumber);
		selenium.fireEvent(inputCorrect1, Event.KEYUP);
		selenium.type(inputCorrect2, secondNumber);
		selenium.fireEvent(inputCorrect2, Event.KEYUP);

		// wait for "equal sign" button became enabled (lost disabled status)
		Wait.until(new Condition() {
			public boolean isTrue() {
				boolean result = selenium.isElementPresent(format(
						"{0}/@disabled", buttonCorrect));
				return !result;

			}
		});

		// try to count result
		selenium.click(buttonCorrect);

		// waiting for result became right - If this not happen, waiting timeouts and test fail
		Wait.until(new Condition() {
			public boolean isTrue() {
				String result = selenium.getText(outputCorrect);
				return expectedResultNumber.equals(result);
			}
		});
	}
}
