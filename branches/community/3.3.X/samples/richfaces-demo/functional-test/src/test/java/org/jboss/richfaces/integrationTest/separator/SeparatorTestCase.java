package org.jboss.richfaces.integrationTest.separator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.utils.URLUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests progress bar.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class SeparatorTestCase extends AbstractSeleniumRichfacesTestCase {

	// messages
	private final String MSG_COMPONENT_DESCRIPTION = getMess("COMPONENT_DESCRIPTION");
	private final String MSG_HEIGHT_OF_SEPARATOR = getMess("HEIGHT_OF_SEPARATOR");
	private final String MSG_WIDTH_OF_SEPARATOR = getMess("WIDTH_OF_SEPARATOR");
	private final String MSG_HASH_CODE = getMess("HASH_CODE");

	// locators
	private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");
	private final String LOC_SEPARATOR_N = getLoc("SEPARATOR_N");

	/**
	 * Tests the first separator. It checks both height of the separator and
	 * hash code of the image used in separator.
	 */
	@Test
	public void testFirstSeparator() {
		String text = getStyle(String.format(LOC_SEPARATOR_N, 1), "height");
		assertEquals(text, "6px", MSG_HEIGHT_OF_SEPARATOR);

		text = getSeparatorHash(String.format(LOC_SEPARATOR_N, 1) + "@style");
		assertEquals(text, "c8568515d1a22227648977798c87cfb0", MSG_HASH_CODE);
	}

	/**
	 * Tests the second separator. It checks height of the separator, width of
	 * the separator, and hash code of the image used in separator.
	 */
	@Test
	public void testSecondSeparator() {
		String text = getStyle(String.format(LOC_SEPARATOR_N, 2), "height");
		assertEquals(text, "8px", MSG_HEIGHT_OF_SEPARATOR);

		double widthFull = Double.parseDouble(getStyle(
				String.format(LOC_SEPARATOR_N, 1), "width").replace("px", ""));
		double width75 = Double.parseDouble(getStyle(
				String.format(LOC_SEPARATOR_N, 2), "width").replace("px", ""));

		assertEquals(width75, widthFull * 0.75, MSG_WIDTH_OF_SEPARATOR);

		text = getSeparatorHash(String.format(LOC_SEPARATOR_N, 2) + "@style");
		assertEquals(text, "350f395b0570f1c1cb528ef18299dd5d", MSG_HASH_CODE);
	}

	/**
	 * Tests the third separator. It checks both height of the separator and
	 * hash code of the image used in separator.
	 */
	@Test
	public void testThirdSeparator() {
		String text = getStyle(String.format(LOC_SEPARATOR_N, 3), "height");
		assertEquals(text, "2px", MSG_HEIGHT_OF_SEPARATOR);

		// FIXME the hash code is not valid
		// text = getSeparatorHash(String.format(LOC_SEPARATOR_N, 3) +
		// "@style");
		// assertEquals(text, "0bf81058de26083a10657f2db675e3ea",
		// MSG_HASH_CODE);
	}

	/**
	 * Tests the fourth separator. It checks both height of the separator and
	 * hash code of the image used in separator.
	 */
	@Test
	public void testFourthSeparator() {
		String text = getStyle(String.format(LOC_SEPARATOR_N, 4), "height");
		assertEquals(text, "2px", MSG_HEIGHT_OF_SEPARATOR);

		text = getSeparatorHash(String.format(LOC_SEPARATOR_N, 4) + "@style");
		assertEquals(text, "94779ca2ce53f57796d3704454cabb2f", MSG_HASH_CODE);
	}

	/**
	 * Tests the fifth separator. It checks both height of the separator and
	 * hash code of the image used in separator.
	 */
	@Test
	public void testFifthSeparator() {
		String text = getStyle(String.format(LOC_SEPARATOR_N, 5), "height");
		assertEquals(text, "4px", MSG_HEIGHT_OF_SEPARATOR);

		text = getSeparatorHash(String.format(LOC_SEPARATOR_N, 5) + "@style");
		assertEquals(text, "14c1dfe353747b738a586a51070f631a", MSG_HASH_CODE);
	}

	/**
	 * Tests the sixth separator. It checks both height of the separator and
	 * hash code of the image used in separator.
	 */
	@Test
	public void testSixthSeparator() {
		String text = getStyle(String.format(LOC_SEPARATOR_N, 6), "height");
		assertEquals(text, "2px", MSG_HEIGHT_OF_SEPARATOR);

		text = getSeparatorHash(String.format(LOC_SEPARATOR_N, 6) + "@style");
		assertEquals(text, "6ab8ba31211c2a0ecbbeff9cc61bc950", MSG_HASH_CODE);
	}

	/**
	 * Tests the "View Source" in the example. It checks that the source code is
	 * not visible, clicks on the link, and checks 7 lines of source code.
	 */
	@Test
	public void testFirstExampleSource() {
		String[] strings = new String[] {
				"<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
				"<rich:separator/>",
				"<rich:separator lineType=\"beveled\" height=\"8\" width=\"75%\" align=\"center\"/>",
				"<rich:separator height=\"2\" lineType=\"dotted\"/><br/>",
				"<rich:separator height=\"2\" lineType=\"dashed\"/><br/>",
				"<rich:separator height=\"4\" lineType=\"double\"/><br/>",
				"<rich:separator height=\"2\" lineType=\"solid\"/><br/>", };

		abstractTestSource(1, "View Source", strings);
	}

	/**
	 * Returns hash code of the separator's image.
	 * 
	 * @param locator
	 *            style attribute of the separator
	 */
	private String getSeparatorHash(String locator) {
		// create URL of the image
		String tmp = selenium.getAttribute(locator);

		int index = tmp.indexOf("background-image:");
		tmp = tmp.substring(index + 22);
		tmp = tmp.substring(0, tmp.indexOf(");"));

		index = selenium.getLocation().indexOf('/', 7);
		String url = selenium.getLocation().substring(0, index);
		url += tmp;

		try {
			tmp = URLUtils.resourceMd5Digest(url);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		return tmp;
	}

	/**
	 * Loads the page containing the component.
	 */
	@BeforeMethod
	private void loadPage() {
		super.loadPage("richOutputs", 7, 1, MSG_COMPONENT_DESCRIPTION);
		scrollIntoView(LOC_EXAMPLE_HEADER, true);
	}
}
