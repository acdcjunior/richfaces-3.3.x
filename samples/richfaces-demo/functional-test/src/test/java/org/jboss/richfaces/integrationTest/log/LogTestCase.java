package org.jboss.richfaces.integrationTest.log;

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
public class LogTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	private void openPage() {
		selenium.open(contextPath + "/richfaces/log.jsf?c=log&tab=usage");
		scrollIntoView(header, true);
	}

	private String header = getLoc("log--header");
	private String inputText = getLoc("log--input--text");
	private String buttonClear = getLoc("log--button--clear");
	private String outputText = getLoc("log--output--text");
	private String outputDivs = getLoc("log--output--divs");
	private String messageSample = getMess("log--input--sample");
	private String messageDebug = getMess("log--output--debug");
	private String messageClear = getMess("log--output--clear");

	@Test
	public void testFillLog() {
		openPage();

		fillLog();
	}

	@Test
	public void testClearLog() {
		openPage();

		fillLog();
		waitForLogStabilize();
		clearLog();
	}

	@Test
	public void testRepeatingFillAndClear() {
		openPage();

		fillLog();
		waitForLogStabilize();
		clearLog();
		waitForLogStabilize();
		fillLog();
		waitForLogStabilize();
		clearLog();
	}

	public void waitForLogStabilize() {
		Wait.interval(3000).timeout(9000).until(new Condition() {
			long count = -1;

			public boolean isTrue() {
				long actual = selenium.getXpathCount(outputDivs).longValue();
				if (actual == count) {
					return true;
				}
				count = actual;
				return false;
			}
		});
	}

	public void fillLog() {
		final long startCount = selenium.getXpathCount(outputDivs).longValue();

		selenium.type(inputText, messageSample);
		selenium.fireEvent(inputText, Event.KEYUP);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return selenium.getXpathCount(outputDivs).longValue() > startCount;
			}
		});

		String output = selenium.getText(outputText);
		Assert.assertTrue(output.contains(messageDebug));
	}

	public void clearLog() {
		selenium.click(buttonClear);

		Wait.until(new Condition() {
			public boolean isTrue() {
				String output = selenium.getText(outputText);
				return messageClear.equals(StringUtils.trim(output));
			}
		});
	}
}
