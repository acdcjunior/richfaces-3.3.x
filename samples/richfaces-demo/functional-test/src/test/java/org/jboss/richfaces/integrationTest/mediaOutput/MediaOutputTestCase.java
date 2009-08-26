package org.jboss.richfaces.integrationTest.mediaOutput;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import junit.framework.Assert;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.utils.URLUtils;
import org.jboss.test.selenium.waiting.Wait;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class MediaOutputTestCase extends AbstractSeleniumRichfacesTestCase {
	/**
	 * Opens specified page
	 */
	public void openPage() {
		selenium.open(format("{0}/{1}", contextPath, PAGE));
		scrollIntoView(header, true);
	}

	private final String PAGE = "richfaces/mediaOutput.jsf?c=mediaOutput&tab=usage";

	private String header = getLoc("media-output--header");

	@Test
	public void imageMd5DigestTest() throws IOException,
			NoSuchAlgorithmException {
		openPage();

		waitForElement(getLoc("media-output--attribute--image-src"), Wait.DEFAULT_TIMEOUT);
		
		String imageSrc = selenium
				.getAttribute(getLoc("media-output--attribute--image-src"));

		String url = URLUtils
				.buildUrl(contextRoot, contextPath, PAGE, imageSrc);

		Assert.assertEquals(getMess("media-output--md5-digest--image"),
				URLUtils.resourceMd5Digest(url));
	}

	@Test
	public void flashMd5DigestTest() throws IOException,
			NoSuchAlgorithmException {
		openPage();

		waitForElement(getLoc("media-output--attribute--flash-object-data"), Wait.DEFAULT_TIMEOUT);
		
		String flashData = selenium
				.getAttribute(getLoc("media-output--attribute--flash-object-data"));

		String url = URLUtils.buildUrl(contextRoot, contextPath, PAGE,
				flashData);

		Assert.assertEquals(getMess("media-output--md5-digest--flash"),
				URLUtils.resourceMd5Digest(url));
	}
}
