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

package org.jboss.test.selenium.utils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Provides URL manipulations and functionality.
 * 
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public class URLUtils {
	
    /**
	 * Use URL context and one or more relocations to build end URL.
	 * 
	 * @param context
	 *            first URL used like a context root for all relocation changes
	 * @param relocations
	 *            array of relocation URLs
	 * @return end url after all changes made on context with relocations
	 * @throws MalformedURLException
	 *             when context or some of relocations are malformed
	 */
	public static String buildUrl(String context, String... relocations) throws MalformedURLException {
		URL url = new URL(context);

		for (String move : relocations) {
			url = new URL(url, move);
		}

		return url.toString();
	}

	/**
	 * Gets a MD5 digest of some resource obtains as input stream from
	 * connection to URL given by URL string.
	 * 
	 * @param url of the resource
	 * @return MD5 message digest of resource
	 * @throws IOException when connection to URL fails
	 */
	public static String resourceMd5Digest(String url) throws IOException {
		URLConnection connection = new URL(url).openConnection();

		InputStream in = connection.getInputStream();
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			throw new IllegalStateException("MD5 hashing is unsupported", ex);
		}
		byte[] buffer = new byte[8192];
		int read = 0;

		while ((read = in.read(buffer)) > 0) {
			digest.update(buffer, 0, read);
		}

		byte[] md5sum = digest.digest();
		BigInteger bigInt = new BigInteger(1, md5sum);
		return bigInt.toString(16);
	}
}
