package org.jboss.richfaces.integrationTest.actionParameter;

import java.util.HashMap;
import java.util.Map;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Condition;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class ActionParameterTestCase extends AbstractSeleniumRichfacesTestCase {

	/**
	 * Opens specified page
	 */
	private void openPage() {
		selenium.open(contextPath
				+ "/richfaces/actionparam.jsf?c=actionparam&tab=usage");
	}

	@Test
	public void selectingNames() {
		openPage();

		String prefix = getMess("selected-name--prefix");
		final String selectedName = formatLoc("selected-name--text", prefix);

		Assert.assertEquals(prefix, selenium.getText(selectedName));

		String[] guys = new String[] { getMess("first-guy"),
				getMess("second-guy") };

		for (String guy : guys) {
			String button = formatLoc("selected-name--button", guy);
			final String expect = formatMess("selected-name--pattern", prefix,
					guy);

			selenium.click(button);

			Wait.until(new Condition() {
				public boolean isTrue() {
					return expect.equals(selenium.getText(selectedName));
				}
			});
		}
	}

	@Test
	@SuppressWarnings("serial")
	public void selectingSkin() {
		openPage();

		final String changeSkinLabel = getLoc("change-skin--label");

		Map<String, String> skins = new HashMap<String, String>() {
			{
				put(getMess("skin-1-name"), getMess("skin-1-color"));
				put(getMess("skin-2-name"), getMess("skin-2-color"));
			}
		};

		for (String skin : skins.keySet()) {
			final String expected = skins.get(skin);

			String skinAnchor = formatLoc("change-skin--link-relative",
					changeSkinLabel, skin);

			selenium.click(skinAnchor);

			Wait.until(new Condition() {
				public boolean isTrue() {
					String actual = getStyle(changeSkinLabel,
							"background-color");
					return expected.equals(actual);
				}
			});
		}
	}

	@Test
	public void showScreenSize() {
		openPage();

		String screenSizeButton = getLoc("screen-size--button");

		final String widthText = formatLoc("screen-size--width--text-relative",
				screenSizeButton);
		final String heightText = formatLoc(
				"screen-size--height--text-relative", screenSizeButton);

		final String expectedWidth = selenium.getEval("screen.width");
		final String expectedHeight = selenium.getEval("screen.height");

		selenium.click(screenSizeButton);

		Wait.until(new Condition() {
			public boolean isTrue() {
				return expectedWidth.equals(selenium.getText(widthText))
						&& expectedHeight.equals(selenium.getText(heightText));
			}
		});
	}
}
