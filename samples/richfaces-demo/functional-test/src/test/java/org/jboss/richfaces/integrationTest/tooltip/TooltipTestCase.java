package org.jboss.richfaces.integrationTest.tooltip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class TooltipTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.allowNativeXpath("true");

		selenium.open(format("{0}/{1}", contextPath, PAGE));

		scrollIntoView(header, true);
	}

	private final String PAGE = "richfaces/toolTip.jsf?c=toolTip&tab=usage";
	private final String header = getLoc("header");

	private final String coords = getMess("coords--event-at-panel");
	private final String tooltip = getLoc("tooltip--text-part--tooltips-requested");
	private final String toolTipWait = getMess("output--tooltip3-4-wait");
	private final String toolTipText = getMess("output--tooltip3-4-text-part");
	private final Pattern pattern = Pattern.compile(getMess("regexp--tooltips-requested"));

	@Test
	public void defaultToolTipTest() {
		openPage();

		final String panel = getLoc("panel--sample1");
		final String toolTipText = getMess("output--tooltip1-text-part");

		for (int i = 0; i < 3; i++) {
			Assert.assertFalse(selenium.isTextPresent(toolTipText));

			if (i == 0) /*
						 * satisfies that mouseOverAt will work as expected -
						 * without this mouseOverAt do nothing
						 */
				selenium.mouseMoveAt(panel, coords);
			mouseOverAt(panel, coords);

			waitForText(toolTipText);

			selenium.mouseOut(panel);
			waitForTextDisappears(toolTipText);
		}
	}

	@Test
	public void followMouseDelayedTest() {
		openPage();

		final String panel = getLoc("panel--sample2");
		final String toolTipText = getMess("output--tooltip2-text-part");

		for (int i = 0; i < 3; i++) {
			Assert.assertFalse(selenium.isTextPresent(toolTipText));

			if (i == 0)
				selenium.mouseMoveAt(panel, coords);
			mouseOverAt(panel, coords);

			waitForText(toolTipText);

			selenium.mouseOut(panel);
			waitForTextDisappears(toolTipText);
		}
	}

	@Test
	public void separateServerRequestsTest() {
		openPage();

		final String panel = getLoc("panel--sample3");
		Integer tooltipsRequested = null;

		for (int i = 0; i < 3; i++) {
			Assert.assertFalse(selenium.isTextPresent(toolTipWait));
			Assert.assertFalse(selenium.isTextPresent(toolTipText));

			if (i == 0)
				selenium.mouseMoveAt(panel, coords);
			mouseOverAt(panel, coords);

			tooltipsRequested = waitForTooltipChanges(tooltipsRequested, i == 0);
			 
			selenium.mouseOut(panel);
			waitForTextDisappears(toolTipText);
		}
	}
	
	@Test
	public void mouseClickActivationTest() {
		openPage();

		final String panel = getLoc("panel--sample4");
		Integer tooltipsRequested = null;

		for (int i = 0; i < 3; i++) {
			Assert.assertFalse(selenium.isTextPresent(toolTipWait));
			Assert.assertFalse(selenium.isTextPresent(toolTipText));

			selenium.clickAt(panel, coords);

			tooltipsRequested = waitForTooltipChanges(tooltipsRequested, i == 0);

			selenium.mouseOut(panel);
			waitForTextDisappears(toolTipText);
		}
	}
	
	private Integer waitForTooltipChanges(Integer tooltipsRequestedOld, boolean firstLoop) {
		Integer tooltipsRequested = null;
		
		if (firstLoop) {
			waitForText(toolTipWait);
			waitForText(toolTipText);
			tooltipsRequested = retrieveRequestedTooltips.retrieve();
		} else {
			waitForText(toolTipText);
			tooltipsRequested = Wait.waitForChangeAndReturn(tooltipsRequestedOld, retrieveRequestedTooltips);
			
			if (tooltipsRequestedOld != null) {
				Assert.assertEquals(tooltipsRequested, Integer.valueOf(tooltipsRequestedOld + 1));
			}
		}
		
		return tooltipsRequested;
	}
	
	private Retrieve<Integer> retrieveRequestedTooltips = new Retrieve<Integer>() {
		public Integer retrieve() {
			String text = Wait.interval(20).timeout(2000).waitForChangeAndReturn(null, new Retrieve<String>() {
				public String retrieve() {
					return getTextOrNull(tooltip);
				}
			});
			Matcher matcher = pattern.matcher(text);
			if (!matcher.matches()) {
				Assert.fail();
			}
			return Integer.valueOf(matcher.group(1));
		}
	};
	
	private void waitForTextDisappears(final String text) {
		waitModelUpdate.until(new Condition() {
			public boolean isTrue() {
				return !selenium.isElementPresent(text);
			}
		});
	}
}
