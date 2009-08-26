package org.jboss.richfaces.integrationTest.style;

import java.util.LinkedHashMap;
import java.util.Map;

import junit.framework.Assert;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class StyleTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	private void openPage() {
		selenium.open(contextPath
				+ "/richfaces/style.jsf?c=loadStyle&tab=usage");
		scrollIntoView(header, true);
	}

	private String header = getLoc("style--header");
	private String linkDeepMarine = getLoc("style--link--deep-marine");
	private String linkBlueSky = getLoc("style--link--blue-sky");
	private String linkJapanCherry = getLoc("style--link--japan-cherry");
	private String tableOutput = getLoc("style--table--output");
	private String colorDeepMarine = getMess("style--color--deep-marine");
	private String colorBlueSky = getMess("style--color--blue-sky");
	private String colorJapanCherry = getMess("style--color--japan-cherry");

	Map<String, String> relation = new LinkedHashMap<String, String>() {
		{
			put(linkDeepMarine, colorDeepMarine);
			put(linkBlueSky, colorBlueSky);
			put(linkJapanCherry, colorJapanCherry);
		}
	};

	@Test
	public void testBackgroundColor() {
		openPage();

		for (final String link : relation.keySet()) {
			final String color = relation.get(link);

			selenium.click(link);
			selenium.waitForPageToLoad(Long.toString(Wait.DEFAULT_TIMEOUT));
			scrollIntoView(header, true);

			Assert.assertEquals(color, getStyle(tableOutput, "background-color"));
		}
	}
}
