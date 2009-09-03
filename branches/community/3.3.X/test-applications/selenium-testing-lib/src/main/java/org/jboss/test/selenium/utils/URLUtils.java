/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and others contributors as indicated
 * by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */

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
