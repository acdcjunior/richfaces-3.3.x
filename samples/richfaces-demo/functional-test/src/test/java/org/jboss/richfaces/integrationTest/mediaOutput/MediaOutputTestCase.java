/*******************************************************************************
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *******************************************************************************/
package org.jboss.richfaces.integrationTest.mediaOutput;

import java.io.IOException;

import static org.testng.Assert.*;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.utils.URLUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class MediaOutputTestCase extends AbstractSeleniumRichfacesTestCase {

	private String LOC_FIELDSET_HEADER = getLoc("FIELDSET_HEADER");
	private String LOC_ATTRIBUTE_IMAGE_SRC = getLoc("ATTRIBUTE_IMAGE_SRC");
	private String LOC_ATTRIBUTE_FLASH_HREF = getLoc("ATTRIBUTE_FLASH_HREF");

	private String MSG_MD5DIGEST_IMAGE = getMsg("MD5DIGEST_IMAGE");
	private String MSG_MD5DIGEST_FLASH = getMsg("MD5DIGEST_FLASH");

	/**
	 * Gets a image's source URL and obtains it's MD5 digest - checks that the
	 * digest is same as expected.
	 */
	@Test
	public void testImageMd5Digest() {
		String imageSrc = selenium.getAttribute(LOC_ATTRIBUTE_IMAGE_SRC);

		try {
			String url = URLUtils.buildUrl(selenium.getLocation(), imageSrc);

			try {
				assertEquals(URLUtils.resourceMd5Digest(url), MSG_MD5DIGEST_IMAGE);
			} catch (IOException e) {
				fail("Getting resources from URL failed");
			}
		} catch (IOException e) {
			fail(format("Building of URL failed: '{0}', '{1}'", selenium.getLocation(), imageSrc));
		}
	}

	/**
	 * Gets a flash object's data URL and obtains it's MD5 digest - checks that
	 * the digest is same as expected.
	 */
	@Test
	public void testFlashMd5Digest() {
		String flashHref = selenium.getAttribute(LOC_ATTRIBUTE_FLASH_HREF);

		try {
			String url = URLUtils.buildUrl(selenium.getLocation(), flashHref);

			try {
				assertEquals(URLUtils.resourceMd5Digest(url), MSG_MD5DIGEST_FLASH);
			} catch (IOException e) {
				fail("Getting resources from URL failed");
			}
		} catch (IOException e) {
			fail(format("Building of URL failed: '{0}', '{1}'", selenium.getLocation(), flashHref));
		}
	}

	protected void loadPage() {
		openComponent("Media Output");

		scrollIntoView(LOC_FIELDSET_HEADER, true);
	}
}
