/**
 * License Agreement.
 *
 *  JBoss RichFaces
 *
 * Copyright (C) 2009  Red Hat, Inc.
 *
 * This code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this code; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package org.jboss.richfaces.integrationTest.paint2d;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import org.jboss.test.selenium.utils.URLUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test case that tests Paint2D.
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class Paint2DTestCase extends AbstractSeleniumRichfacesTestCase {

    // messages
    private final String MSG_TEXT_IN_IMAGE = getMsg("TEXT_IN_IMAGE");
    private final String MSG_COLOR_OF_IMAGE = getMsg("COLOR_OF_IMAGE");
    private final String MSG_SHADOW_SIZE = getMsg("SHADOW_SIZE");
    private final String MSG_POSITION_OF_SLIDERS_HANDLE = getMsg("POSITION_OF_SLIDERS_HANDLE");
    private final String MSG_IMAGE_HASH = getMsg("IMAGE_HASH");

    // locators
    private final String LOC_EXAMPLE_HEADER = getLoc("EXAMPLE_HEADER");
    private final String LOC_INPUT_TEXT = getLoc("INPUT_TEXT");
    private final String LOC_INPUT_COLOR = getLoc("INPUT_COLOR");
    private final String LOC_BUTTON_APPLY_COLOR = getLoc("BUTTON_APPLY_COLOR");
    private final String LOC_SHADOW_SLIDER = getLoc("SHADOW_SLIDER");
    private final String LOC_SHADOW_SLIDER_HANDLE = getLoc("SHADOW_SLIDER_HANDLE");
    private final String LOC_IMAGE = getLoc("IMAGE");

    /**
     * Tests all component on the page as they appear after the page is loaded.
     * It checks the value in the text input, color, position of the slider, and
     * hash code of the displayed image.
     */
    @Test
    public void testInitialState() {
        String text = selenium.getValue(LOC_INPUT_TEXT);
        assertEquals(text, "Paint 2D", MSG_TEXT_IN_IMAGE);

        text = selenium.getValue(LOC_INPUT_COLOR);
        assertEquals(text, "0003e8", MSG_COLOR_OF_IMAGE);

        int position = Integer.parseInt(getStyle(LOC_SHADOW_SLIDER_HANDLE, "left").replace("px", ""));
        assertEquals(position, 127, MSG_SHADOW_SIZE);

        assertImageHash("da54ff0e62ee33804e270a863d7001a4");
    }

    /**
     * Tests changing text that should be in the image.
     */
    @Test
    public void testChangeText() {
        selenium.type(LOC_INPUT_TEXT, "XoXoXoXoX");
        selenium.typeKeys(LOC_INPUT_TEXT, " "); // why is this necessary?
        waitFor(1500);
        assertImageHash("490d3b23e3f8c19290d3fbe351409be");

        selenium.type(LOC_INPUT_TEXT, "Red Hat");
        selenium.typeKeys(LOC_INPUT_TEXT, " "); // why is this necessary?
        waitFor(1500);
        assertImageHash("f56c23afa5207cfdea008d298480c750");
    }

    /**
     * Tests changing color of the image.
     */
    @Test
    public void testChangeColor() {
        selenium.type(LOC_INPUT_COLOR, "fbff00");
        selenium.click(LOC_BUTTON_APPLY_COLOR);

        waitFor(1500);
        assertImageHash("8f71bfc38cf5a26cdd548d3bb0f67291");

        selenium.type(LOC_INPUT_COLOR, "00ffbb");
        selenium.click(LOC_BUTTON_APPLY_COLOR);

        waitFor(1500);
        assertImageHash("6623c69f0397e00a955ed6bbb41e68e1");
    }

    /**
     * Tests 3 positions of the slider. Verifies that the image changed.
     */
    @Test
    public void testChangeShadow() {
        selenium.mouseDownAt(LOC_SHADOW_SLIDER, "0,0");
        selenium.mouseUp(LOC_SHADOW_SLIDER);
        int position = Integer.parseInt(getStyle(LOC_SHADOW_SLIDER_HANDLE, "left").replace("px", ""));
        assertEquals(position, 0, MSG_POSITION_OF_SLIDERS_HANDLE);

        waitFor(1500);
        assertImageHash("6f15cab627ffd32528a66ef9731990a2");

        selenium.mouseDownAt(LOC_SHADOW_SLIDER, "96,0");
        selenium.mouseUp(LOC_SHADOW_SLIDER);
        position = Integer.parseInt(getStyle(LOC_SHADOW_SLIDER_HANDLE, "left").replace("px", ""));
        assertEquals(position, 96, MSG_POSITION_OF_SLIDERS_HANDLE);

        waitFor(1500);
        assertImageHash("19063b8ea31e2939699abd7dc92cf23e");

        selenium.mouseDownAt(LOC_SHADOW_SLIDER, "191,0");
        selenium.mouseUp(LOC_SHADOW_SLIDER);
        position = Integer.parseInt(getStyle(LOC_SHADOW_SLIDER_HANDLE, "left").replace("px", ""));
        assertEquals(position, 191, MSG_POSITION_OF_SLIDERS_HANDLE);

        waitFor(1500);
        assertImageHash("d9e5b2f628d230b7dc41b8ece16a83ff");
    }

    /**
     * Tests changing text and color at the same time.
     */
    @Test
    public void testChangeTextAndColor() {
        selenium.type(LOC_INPUT_TEXT, "RichFaces");

        selenium.type(LOC_INPUT_COLOR, "894bd6");
        selenium.click(LOC_BUTTON_APPLY_COLOR);

        waitFor(1500);
        assertImageHash("a18ff3c5c86154fc1c3ca2a59e60bbbf");
    }

    /**
     * Tests changing text and shadow at the same time.
     */
    @Test
    public void testChangeTextAndShadow() {
        selenium.type(LOC_INPUT_TEXT, "RichFaces");

        selenium.mouseDownAt(LOC_SHADOW_SLIDER, "159,0");
        selenium.mouseUp(LOC_SHADOW_SLIDER);
        int position = Integer.parseInt(getStyle(LOC_SHADOW_SLIDER_HANDLE, "left").replace("px", ""));
        assertEquals(position, 159, MSG_POSITION_OF_SLIDERS_HANDLE);

        waitFor(1500);
        assertImageHash("74e992e595303a2f16c72a178a116189");
    }

    /**
     * Tests changing color and shadow at the same time.
     */
    @Test
    public void testChangeColorAndShadow() {
        selenium.type(LOC_INPUT_COLOR, "6bab57");
        selenium.click(LOC_BUTTON_APPLY_COLOR);

        selenium.mouseDownAt(LOC_SHADOW_SLIDER, "96,0");
        selenium.mouseUp(LOC_SHADOW_SLIDER);
        int position = Integer.parseInt(getStyle(LOC_SHADOW_SLIDER_HANDLE, "left").replace("px", ""));
        assertEquals(position, 96, MSG_POSITION_OF_SLIDERS_HANDLE);

        waitFor(1500);
        assertImageHash("fd6a4503b768b0a197851b78948b497c");
    }

    /**
     * Tests changing text, color and size of shadow.
     */
    @Test
    public void testChangeAll() {
        selenium.type(LOC_INPUT_TEXT, "RichFaces");

        selenium.type(LOC_INPUT_COLOR, "e8a54c");
        selenium.click(LOC_BUTTON_APPLY_COLOR);

        selenium.mouseDownAt(LOC_SHADOW_SLIDER, "127,0");
        selenium.mouseUp(LOC_SHADOW_SLIDER);
        int position = Integer.parseInt(getStyle(LOC_SHADOW_SLIDER_HANDLE, "left").replace("px", ""));
        assertEquals(position, 127, MSG_POSITION_OF_SLIDERS_HANDLE);

        waitFor(1500);
        assertImageHash("f2016d2528b7d288f752bdaaefc7a666");
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks 5 lines of source code.
     */
    @Test
    public void testPageSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"",
                "<h:panelGrid columns=\"3\" width=\"100%\">", "<h:inputText value=\"#{paintData.text}\">",
                "<a4j:support event=\"onchange\" reRender=\":painter\" />",
                "<rich:inputNumberSlider showInput=\"false\"", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Tests the "View PaintBean.java Source". It checks that the source code is
     * not visible, clicks on the link, and checks 5 lines of source code.
     */
    @Test
    public void testPaintBeanSource() {
        String[] strings = new String[] { "package org.richfaces.demo.paint2d;", "import java.awt.Graphics2D;",
                "public void paint(Graphics2D g2d, Object obj) {", "int testLenght = data.text.length();",
                "g2d.setPaint(new Color(color.getRed(),color.getGreen(), color.getBlue(), 30));", };

        abstractTestSource(1, "View PaintBean.java Source", strings);
    }

    /**
     * Tests the "View PaintData.java Source". It checks that the source code is
     * not visible, clicks on the link, and checks 5 lines of source code.
     */
    @Test
    public void testPaintDataSource() {
        String[] strings = new String[] { "package org.richfaces.demo.paint2d;",
                "public class PaintData implements Serializable{", "public int getColor() {", "return color;",
                "public void setText(String text) {", };

        abstractTestSource(1, "View PaintData.java Source", strings);
    }

    /**
     * Verifies the hash code of the displayed image.
     * 
     * @param hashCode
     *            expected hash code of the image
     */
    private void assertImageHash(String hashCode) {
        // create URL of the image
        int index = selenium.getLocation().indexOf('/', 7);
        String tmp = selenium.getLocation().substring(0, index);
        tmp += selenium.getAttribute(LOC_IMAGE + "@src");

        try {
            tmp = URLUtils.resourceMd5Digest(tmp);
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        assertEquals(tmp, hashCode, MSG_IMAGE_HASH);
    }

    /**
     * Loads the page containing the component.
     */
    @BeforeMethod
    private void loadPage() {
        openComponent("Paint2D");
        scrollIntoView(LOC_EXAMPLE_HEADER, true);
    }
}
