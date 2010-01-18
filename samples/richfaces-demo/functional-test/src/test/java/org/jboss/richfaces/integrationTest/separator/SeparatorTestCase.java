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

package org.jboss.richfaces.integrationTest.separator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private final String MSG_FIRST_HEIGHT = getMsg("FIRST_HEIGHT");
    private final String MSG_FIRST_HASH = getMsg("FIRST_HASH");
    private final String MSG_SECOND_HEIGHT = getMsg("SECOND_HEIGHT");
    private final String MSG_SECOND_HASH = getMsg("SECOND_HASH");
    private final String MSG_THIRD_HEIGHT = getMsg("THIRD_HEIGHT");
    private final String MSG_THIRD_HASH = getMsg("THIRD_HASH");
    private final String MSG_FOURTH_HEIGHT = getMsg("FOURTH_HEIGHT");
    private final String MSG_FOURTH_HASH = getMsg("FOURTH_HASH");
    private final String MSG_FIFTH_HEIGHT = getMsg("FIFTH_HEIGHT");
    private final String MSG_FIFTH_HASH = getMsg("FIFTH_HASH");
    private final String MSG_SIXTH_HEIGHT = getMsg("SIXTH_HEIGHT");
    private final String MSG_SIXTH_HASH = getMsg("SIXTH_HASH");

    // locators
    private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");
    private final String LOC_SEPARATOR_N = getLoc("SEPARATOR_N");

    /**
     * Tests the first separator. It checks both height of the separator and
     * hash code of the image used in separator.
     */
    @Test
    public void testFirstSeparator() {
        String text = getStyle(format(LOC_SEPARATOR_N, 0), "height");
        assertEquals(text, MSG_FIRST_HEIGHT, "Height of the separator.");

        text = getSeparatorHash(format(LOC_SEPARATOR_N, 0));
        assertEquals(text, MSG_FIRST_HASH, "Hash code of the image that makes the separator.");
    }

    /**
     * Tests the second separator. It checks height of the separator, width of
     * the separator, and hash code of the image used in separator.
     */
    @Test
    public void testSecondSeparator() {
        String text = getStyle(format(LOC_SEPARATOR_N, 1), "height");
        assertEquals(text, MSG_SECOND_HEIGHT, "Height of the separator.");

        long widthFull = selenium.getElementWidth(format(LOC_SEPARATOR_N, 0)).longValue();
        long width75 = selenium.getElementWidth(format(LOC_SEPARATOR_N, 1)).longValue();

        assertTrue(Math.abs(width75 - widthFull * 0.75) < 1, "Width of the second separator isn't 75% of first");

        text = getSeparatorHash(format(LOC_SEPARATOR_N, 1));
        assertEquals(text, MSG_SECOND_HASH, "Hash code of the image that makes the separator.");
    }

    /**
     * Tests the third separator. It checks both height of the separator and
     * hash code of the image used in separator.
     */
    @Test
    public void testThirdSeparator() {
        String text = getStyle(format(LOC_SEPARATOR_N, 2), "height");
        assertEquals(text, MSG_THIRD_HEIGHT, "Height of the separator.");

        text = getSeparatorHash(format(LOC_SEPARATOR_N, 2));
        assertEquals(text, MSG_THIRD_HASH, "Hash code of the image that makes the separator.");
    }

    /**
     * Tests the fourth separator. It checks both height of the separator and
     * hash code of the image used in separator.
     */
    @Test
    public void testFourthSeparator() {
        String text = getStyle(format(LOC_SEPARATOR_N, 3), "height");
        assertEquals(text, MSG_FOURTH_HEIGHT, "Height of the separator.");

        text = getSeparatorHash(format(LOC_SEPARATOR_N, 3));
        assertEquals(text, MSG_FOURTH_HASH, "Hash code of the image that makes the separator.");
    }

    /**
     * Tests the fifth separator. It checks both height of the separator and
     * hash code of the image used in separator.
     */
    @Test
    public void testFifthSeparator() {
        String text = getStyle(format(LOC_SEPARATOR_N, 4), "height");
        assertEquals(text, MSG_FIFTH_HEIGHT, "Height of the separator.");

        text = getSeparatorHash(format(LOC_SEPARATOR_N, 4));
        assertEquals(text, MSG_FIFTH_HASH, "Hash code of the image that makes the separator.");
    }

    /**
     * Tests the sixth separator. It checks both height of the separator and
     * hash code of the image used in separator.
     */
    @Test
    public void testSixthSeparator() {
        String text = getStyle(format(LOC_SEPARATOR_N, 5), "height");
        assertEquals(text, MSG_SIXTH_HEIGHT, "Height of the separator.");

        text = getSeparatorHash(format(LOC_SEPARATOR_N, 5));
        assertEquals(text, MSG_SIXTH_HASH, "Hash code of the image that makes the separator.");
    }

    /**
     * Tests the "View Source" in the example. It checks that the source code is
     * not visible, clicks on the link, and checks 7 lines of source code.
     */
    @Test
    public void testFirstExampleSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
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
        String url = getStyle(locator, "background-image");
        Matcher matcher = Pattern.compile("url\\(\"?([^\"\\)]+)\"?\\)").matcher(url);
        if (matcher.matches()) {
        	url = matcher.group(1);
        } else {
        	fail("Url '" + url + "' doesn't match url pattern");
        }

        String hash = null;
        try {
            hash = URLUtils.resourceMd5Digest(url);
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        return hash;
    }

    /**
     * Loads the page containing the component.
     */
    protected void loadPage() {
        openComponent("Separator");
        scrollIntoView(LOC_EXAMPLE_HEADER, true);
    }
}
