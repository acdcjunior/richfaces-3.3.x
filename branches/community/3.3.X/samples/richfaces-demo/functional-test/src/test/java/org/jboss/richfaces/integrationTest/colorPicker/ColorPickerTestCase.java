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

package org.jboss.richfaces.integrationTest.colorPicker;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jboss.richfaces.integrationTest.AbstractSeleniumRichfacesTestCase;
import static org.jboss.test.selenium.utils.ColorUtils.convertToAWTColor;
import org.jboss.test.selenium.utils.URLUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * A test case for color picker component.
 * <ul>
 * <li><b>TODO</b>try decimals, negative integers, integers greater than 100,
 * 255 or 360</li>
 * <li><b>TODO</b>check the type of cursor</li>
 * </ul>
 * 
 * @author <a href="mailto:ppitonak@redhat.com">Pavol Pitonak</a>
 * @version $Revision$
 */
public class ColorPickerTestCase extends AbstractSeleniumRichfacesTestCase {

    // components on the page
    private final String LOC_COMPONENT_HEADER = getLoc("COMPONENT_HEADER");
    private final String LOC_IMAGE = getLoc("IMAGE");
    private final String LOC_COLOR_INPUT = getLoc("COLOR_INPUT");
    private final String LOC_COLOR_BUTTON = getLoc("COLOR_BUTTON");

    private final String LOC_COLOR_AREA = getLoc("COLOR_AREA");
    private final String LOC_CURSOR = getLoc("CURSOR");
    private final String LOC_RED_VALUE = getLoc("RED_VALUE");
    private final String LOC_GREEN_VALUE = getLoc("GREEN_VALUE");
    private final String LOC_BLUE_VALUE = getLoc("BLUE_VALUE");
    private final String LOC_HUE_VALUE = getLoc("HUE_VALUE");
    private final String LOC_SATURATION_VALUE = getLoc("SATURATION_VALUE");
    private final String LOC_BRIGHTNESS_VALUE = getLoc("BRIGHTNESS_VALUE");
    private final String LOC_CURRENT_COLOR_BOX = getLoc("CURRENT_COLOR_BOX");
    private final String LOC_ORIGINAL_COLOR_BOX = getLoc("ORIGINAL_COLOR_BOX");
    private final String LOC_RAINBOW = getLoc("RAINBOW");
    private final String LOC_HEX_COLOR = getLoc("HEX_COLOR");
    private final String LOC_APPLY_BUTTON = getLoc("APPLY_BUTTON");
    private final String LOC_CANCEL_BUTTON = getLoc("CANCEL_BUTTON");

    private final int MSG_VALUES_CHANGE_RED_1 = Integer.parseInt(getMsg("VALUES_CHANGE_RED_1"));
    private final int MSG_VALUES_CHANGE_GREEN_1 = Integer.parseInt(getMsg("VALUES_CHANGE_GREEN_1"));
    private final int MSG_VALUES_CHANGE_BLUE_1 = Integer.parseInt(getMsg("VALUES_CHANGE_BLUE_1"));
    private final int MSG_VALUES_CHANGE_HUE_1 = Integer.parseInt(getMsg("VALUES_CHANGE_HUE_1"));
    private final int MSG_VALUES_CHANGE_SATURATION_1 = Integer.parseInt(getMsg("VALUES_CHANGE_SATURATION_1"));
    private final int MSG_VALUES_CHANGE_BRIGHTNESS_1 = Integer.parseInt(getMsg("VALUES_CHANGE_BRIGHTNESS_1"));
    private final String MSG_VALUES_CHANGE_HEXCOLOR_1 = getMsg("VALUES_CHANGE_HEXCOLOR_1");
    private final int MSG_VALUES_CHANGE_RED_2 = Integer.parseInt(getMsg("VALUES_CHANGE_RED_2"));
    private final int MSG_VALUES_CHANGE_GREEN_2 = Integer.parseInt(getMsg("VALUES_CHANGE_GREEN_2"));
    private final int MSG_VALUES_CHANGE_BLUE_2 = Integer.parseInt(getMsg("VALUES_CHANGE_BLUE_2"));
    private final int MSG_VALUES_CHANGE_HUE_2 = Integer.parseInt(getMsg("VALUES_CHANGE_HUE_2"));
    private final int MSG_VALUES_CHANGE_SATURATION_2 = Integer.parseInt(getMsg("VALUES_CHANGE_SATURATION_2"));
    private final int MSG_VALUES_CHANGE_BRIGHTNESS_2 = Integer.parseInt(getMsg("VALUES_CHANGE_BRIGHTNESS_2"));
    private final String MSG_VALUES_CHANGE_HEXCOLOR_2 = getMsg("VALUES_CHANGE_HEXCOLOR_2");

    private final String MSG_SET_RGB_NUMBERS_HEXCOLOR = getMsg("SET_RGB_NUMBERS_HEXCOLOR");
    private final String MSG_SET_HSB_NUMBERS_HEXCOLOR = getMsg("SET_HSB_NUMBERS_HEXCOLOR");

    private final int MSG_SET_MAX_VALUE_RED = Integer.parseInt(getMsg("SET_MAX_VALUE_RED"));
    private final int MSG_SET_MAX_VALUE_GREEN = Integer.parseInt(getMsg("SET_MAX_VALUE_GREEN"));
    private final int MSG_SET_MAX_VALUE_BLUE = Integer.parseInt(getMsg("SET_MAX_VALUE_BLUE"));
    private final int MSG_SET_MAX_VALUE_HUE = Integer.parseInt(getMsg("SET_MAX_VALUE_HUE"));
    private final int MSG_SET_MAX_VALUE_SATURATION = Integer.parseInt(getMsg("SET_MAX_VALUE_SATURATION"));
    private final int MSG_SET_MAX_VALUE_BRIGHTNESS = Integer.parseInt(getMsg("SET_MAX_VALUE_BRIGHTNESS"));

    private final int MSG_SET_NEGATIVE_VALUE_RED = Integer.parseInt(getMsg("SET_NEGATIVE_VALUE_RED"));
    private final int MSG_SET_NEGATIVE_VALUE_GREEN = Integer.parseInt(getMsg("SET_NEGATIVE_VALUE_GREEN"));
    private final int MSG_SET_NEGATIVE_VALUE_BLUE = Integer.parseInt(getMsg("SET_NEGATIVE_VALUE_BLUE"));
    private final int MSG_SET_NEGATIVE_VALUE_HUE = Integer.parseInt(getMsg("SET_NEGATIVE_VALUE_HUE"));
    private final int MSG_SET_NEGATIVE_VALUE_SATURATION = Integer.parseInt(getMsg("SET_NEGATIVE_VALUE_SATURATION"));
    private final int MSG_SET_NEGATIVE_VALUE_BRIGHTNESS = Integer.parseInt(getMsg("SET_NEGATIVE_VALUE_BRIGHTNESS"));

    private final String MSG_SLIDER_HEXCOLOR_1 = getMsg("SLIDER_HEXCOLOR_1");
    private final String MSG_SLIDER_HEXCOLOR_2 = getMsg("SLIDER_HEXCOLOR_2");
    private final int MSG_SLIDER_RED_VALUE_1 = Integer.parseInt(getMsg("SLIDER_RED_VALUE_1"));
    private final int MSG_SLIDER_GREEN_VALUE_1 = Integer.parseInt(getMsg("SLIDER_GREEN_VALUE_1"));
    private final int MSG_SLIDER_BLUE_VALUE_1 = Integer.parseInt(getMsg("SLIDER_BLUE_VALUE_1"));
    private final int MSG_SLIDER_RED_VALUE_2 = Integer.parseInt(getMsg("SLIDER_RED_VALUE_2"));
    private final int MSG_SLIDER_GREEN_VALUE_2 = Integer.parseInt(getMsg("SLIDER_GREEN_VALUE_2"));
    private final int MSG_SLIDER_BLUE_VALUE_2 = Integer.parseInt(getMsg("SLIDER_BLUE_VALUE_2"));
    private final int MSG_SLIDER_RED_VALUE_3 = Integer.parseInt(getMsg("SLIDER_RED_VALUE_3"));
    private final int MSG_SLIDER_GREEN_VALUE_3 = Integer.parseInt(getMsg("SLIDER_GREEN_VALUE_3"));
    private final int MSG_SLIDER_BLUE_VALUE_3 = Integer.parseInt(getMsg("SLIDER_BLUE_VALUE_3"));

    /**
     * Tests the initial state of color picker. It checks that the color of
     * letters in the image, the color defined in the input field, and the color
     * of the button are the same.
     */
    @Test
    public void testInitialState() {
        // get color from the input field
        Color inputColor = convertToAWTColor(selenium.getValue(LOC_COLOR_INPUT));

        Map<Color, Integer> histogram = getHistogram();
        assertTrue(histogram.containsKey(inputColor),
                "Image should have the same color as the one defined in input field.");

        // get color of the button
        Color colorButton = convertToAWTColor(getStyle(LOC_COLOR_BUTTON, "background-color"));

        assertTrue(histogram.containsKey(colorButton), "Image should have the same color as the button.");
    }

    /**
     * It clicks to the color area of color picker, clicks on the apply button,
     * and gets the color of pixel [18,97] from the image.
     */
    @Test
    public void testClickToColorArea() {
        selenium.click(LOC_COLOR_BUTTON);
        selenium.mouseDownAt(LOC_COLOR_AREA, "50,50");
        selenium.click(LOC_APPLY_BUTTON);
        waitFor(1500);

        Map<Color, Integer> histogram = getHistogram();
        Color inputColor = convertToAWTColor(selenium.getValue(LOC_COLOR_INPUT));

        assertTrue(histogram.containsKey(inputColor),
                "Image should have the same color as the one defined in input field.");
    }

    /**
     * Tests input fields with RGB and HSB values and color number. It clicks on
     * [0,0] into color area and checks that all values changed.
     */
    @Test
    public void testValuesChange() {
        selenium.click(LOC_COLOR_BUTTON);

        int red = Integer.parseInt(selenium.getValue(LOC_RED_VALUE));
        int green = Integer.parseInt(selenium.getValue(LOC_GREEN_VALUE));
        int blue = Integer.parseInt(selenium.getValue(LOC_BLUE_VALUE));
        int hue = Integer.parseInt(selenium.getValue(LOC_HUE_VALUE));
        int saturation = Integer.parseInt(selenium.getValue(LOC_SATURATION_VALUE));
        int brightness = Integer.parseInt(selenium.getValue(LOC_BRIGHTNESS_VALUE));
        String hexColor = selenium.getValue(LOC_HEX_COLOR);

        assertEquals(red, MSG_VALUES_CHANGE_RED_1, "Red before click.");
        assertEquals(green, MSG_VALUES_CHANGE_GREEN_1, "Green before click.");
        assertEquals(blue, MSG_VALUES_CHANGE_BLUE_1, "Blue before click.");
        assertEquals(hue, MSG_VALUES_CHANGE_HUE_1, "Hue before click.");
        assertEquals(saturation, MSG_VALUES_CHANGE_SATURATION_1, "Saturation before click.");
        assertEquals(brightness, MSG_VALUES_CHANGE_BRIGHTNESS_1, "Brightness before click.");
        assertEquals(hexColor, MSG_VALUES_CHANGE_HEXCOLOR_1, "Hex value of color before click.");

        selenium.clickAt(LOC_COLOR_AREA, "0,0");

        red = Integer.parseInt(selenium.getValue(LOC_RED_VALUE));
        green = Integer.parseInt(selenium.getValue(LOC_GREEN_VALUE));
        blue = Integer.parseInt(selenium.getValue(LOC_BLUE_VALUE));
        hue = Integer.parseInt(selenium.getValue(LOC_HUE_VALUE));
        saturation = Integer.parseInt(selenium.getValue(LOC_SATURATION_VALUE));
        brightness = Integer.parseInt(selenium.getValue(LOC_BRIGHTNESS_VALUE));
        hexColor = selenium.getValue(LOC_HEX_COLOR);

        assertEquals(red, MSG_VALUES_CHANGE_RED_2, "Red component after white was selected (top left corner).");
        assertEquals(green, MSG_VALUES_CHANGE_GREEN_2, "Green component after white was selected (top left corner).");
        assertEquals(blue, MSG_VALUES_CHANGE_BLUE_2, "Blue component after white was selected (top left corner).");
        assertTrue(Math.abs(hue - MSG_VALUES_CHANGE_HUE_2) < 2, format(
                "Hue should be {0} (+-6) after white was selected (top left corner).", MSG_VALUES_CHANGE_HUE_2));
        assertTrue(Math.abs(saturation - MSG_VALUES_CHANGE_SATURATION_2) < 2, format(
                "Saturation should be {0} (+-6) after white was selected (top left corner).",
                MSG_VALUES_CHANGE_SATURATION_2));
        assertTrue(Math.abs(brightness - MSG_VALUES_CHANGE_BRIGHTNESS_2) < 2, format(
                "Brightness should be 100 (+-6) after white was selected (top left corner).",
                MSG_VALUES_CHANGE_BRIGHTNESS_2));
        assertEquals(hexColor, MSG_VALUES_CHANGE_HEXCOLOR_2,
                "Hex value of color after white was selected (top left corner).");
    }

    /**
     * Tests the position of the cursor after clicking into color area.
     */
    @Test
    public void testCursorPosition() {
        selenium.click(LOC_COLOR_BUTTON);
        selenium.mouseDownAt(LOC_COLOR_AREA, "50,20");
        selenium.mouseUp(LOC_COLOR_AREA);

        int x = Integer.parseInt(getStyle(LOC_CURSOR, "left").replace("px", ""));
        int y = Integer.parseInt(getStyle(LOC_CURSOR, "top").replace("px", ""));
        assertTrue(Math.abs(x - 50) < 7, format("Cursor should move 50+-6px right (was {0}).", x));
        assertTrue(Math.abs(y - 20) < 7, format("Cursor should move 20+-6px down (was {0}).", y));
    }

    /**
     * Tests typing valid values into input fields for RGB.
     */
    @Test
    public void testSetRGBNumbers() {
        String hex1 = selenium.getValue(LOC_HEX_COLOR);
        String hue1 = selenium.getValue(LOC_HUE_VALUE);
        String saturation1 = selenium.getValue(LOC_SATURATION_VALUE);
        String brightness1 = selenium.getValue(LOC_BRIGHTNESS_VALUE);

        // change color to dark blue
        selenium.type(LOC_RED_VALUE, "36");
        selenium.type(LOC_GREEN_VALUE, "36");
        selenium.type(LOC_BLUE_VALUE, "99");

        String hex2 = selenium.getValue(LOC_HEX_COLOR);
        String hue2 = selenium.getValue(LOC_HUE_VALUE);
        String saturation2 = selenium.getValue(LOC_SATURATION_VALUE);
        String brightness2 = selenium.getValue(LOC_BRIGHTNESS_VALUE);

        assertFalse(hex1.equals(hex2), "Hex value of the selected color should have changed.");
        assertEquals(hex2, MSG_SET_RGB_NUMBERS_HEXCOLOR, "Hex value of the selected color should have changed.");

        assertFalse(hue1.equals(hue2), "Hue should change.");
        assertFalse(saturation1.equals(saturation2), "Saturation should have changed.");
        assertFalse(brightness1.equals(brightness2), "Brightness should have changed.");
    }

    /**
     * Tests typing valid values into input fields for HSB.
     */
    @Test
    public void testSetHSBNumbers() {
        String hex1 = selenium.getValue(LOC_HEX_COLOR);
        String red1 = selenium.getValue(LOC_RED_VALUE);
        String green1 = selenium.getValue(LOC_GREEN_VALUE);
        String blue1 = selenium.getValue(LOC_BLUE_VALUE);

        // change color to light orange
        selenium.type(LOC_HUE_VALUE, "36");
        selenium.type(LOC_SATURATION_VALUE, "36");
        selenium.type(LOC_BRIGHTNESS_VALUE, "99");

        String hex2 = selenium.getValue(LOC_HEX_COLOR);
        String red2 = selenium.getValue(LOC_RED_VALUE);
        String green2 = selenium.getValue(LOC_GREEN_VALUE);
        String blue2 = selenium.getValue(LOC_BLUE_VALUE);

        assertFalse(hex1.equals(hex2), "Hex value of the selected color should have changed.");
        assertEquals(hex2, MSG_SET_HSB_NUMBERS_HEXCOLOR, "Hex value of the selected color should have changed.");

        assertFalse(red1.equals(red2), "Red should have changed.");
        assertFalse(green1.equals(green2), "Green should have changed.");
        assertFalse(blue1.equals(blue2), "Blue should have changed.");
    }

    /**
     * Test typing invalid values into input fields for RGB and HSB. It tries to
     * type 500 for all components. R, G and B should change to 255, H should
     * change to 360, S and B to 100.
     */
    @Test
    public void testSetMaxValueRGBHSB() {
        selenium.type(LOC_RED_VALUE, "500");
        int number = Integer.parseInt(selenium.getValue(LOC_RED_VALUE));
        assertEquals(number, MSG_SET_MAX_VALUE_RED, "Red should be set to 255 if bigger number was typed.");

        selenium.type(LOC_GREEN_VALUE, "500");
        number = Integer.parseInt(selenium.getValue(LOC_GREEN_VALUE));
        assertEquals(number, MSG_SET_MAX_VALUE_GREEN, "Green should be set to 255 if bigger number was typed.");

        selenium.type(LOC_BLUE_VALUE, "500");
        number = Integer.parseInt(selenium.getValue(LOC_BLUE_VALUE));
        assertEquals(number, MSG_SET_MAX_VALUE_BLUE, "Blue should be set to 255 if bigger number was typed.");

        selenium.type(LOC_HUE_VALUE, "500");
        number = Integer.parseInt(selenium.getValue(LOC_HUE_VALUE));
        assertEquals(number, MSG_SET_MAX_VALUE_HUE, "Hue should be set to 360 if bigger number was typed.");

        selenium.type(LOC_SATURATION_VALUE, "500");
        number = Integer.parseInt(selenium.getValue(LOC_SATURATION_VALUE));
        assertEquals(number, MSG_SET_MAX_VALUE_SATURATION,
                "Saturation should be set to 100 if bigger number was typed.");

        selenium.type(LOC_BRIGHTNESS_VALUE, "500");
        number = Integer.parseInt(selenium.getValue(LOC_BRIGHTNESS_VALUE));
        assertEquals(number, MSG_SET_MAX_VALUE_BRIGHTNESS,
                "Brightness should be set to 100 if bigger number was typed.");
    }

    /**
     * Test typing invalid values into input fields for RGB and HSB. It tries to
     * type -2 for all components. All should change to 0.
     */
    @Test
    public void testSetNegativeValueRGBHSB() {
        selenium.type(LOC_RED_VALUE, "-2");
        int number = Integer.parseInt(selenium.getValue(LOC_RED_VALUE));
        assertEquals(number, MSG_SET_NEGATIVE_VALUE_RED, "Red should be set to 0 if lower number was typed.");

        selenium.type(LOC_GREEN_VALUE, "-2");
        number = Integer.parseInt(selenium.getValue(LOC_GREEN_VALUE));
        assertEquals(number, MSG_SET_NEGATIVE_VALUE_GREEN, "Green should be set to 0 if lower number was typed.");

        selenium.type(LOC_BLUE_VALUE, "-2");
        number = Integer.parseInt(selenium.getValue(LOC_BLUE_VALUE));
        assertEquals(number, MSG_SET_NEGATIVE_VALUE_BLUE, "Blue should be set to 0 if lower number was typed.");

        selenium.type(LOC_HUE_VALUE, "-2");
        number = Integer.parseInt(selenium.getValue(LOC_HUE_VALUE));
        assertEquals(number, MSG_SET_NEGATIVE_VALUE_HUE, "Hue should be set to 0 if lower number was typed.");

        selenium.type(LOC_SATURATION_VALUE, "-2");
        number = Integer.parseInt(selenium.getValue(LOC_SATURATION_VALUE));
        assertEquals(number, MSG_SET_NEGATIVE_VALUE_SATURATION,
                "Saturation should be set to 0 if lower number was typed.");

        selenium.type(LOC_BRIGHTNESS_VALUE, "-2");
        number = Integer.parseInt(selenium.getValue(LOC_BRIGHTNESS_VALUE));
        assertEquals(number, MSG_SET_NEGATIVE_VALUE_BRIGHTNESS,
                "Brightness should be set to 0 if lower number was typed.");
    }

    /**
     * Tests the 'Cancel' button. It types 100, 255, 100 for RGB, clicks on
     * cancel, and checks that the color defined in the input field and color of
     * the image did not change.
     */
    @Test
    public void testCancelButton() {
        Color original = convertToAWTColor(selenium.getValue(LOC_COLOR_INPUT));
        selenium.click(LOC_COLOR_BUTTON);

        selenium.type(LOC_RED_VALUE, "100");
        selenium.type(LOC_GREEN_VALUE, "255");
        selenium.type(LOC_BLUE_VALUE, "100");

        selenium.click(LOC_CANCEL_BUTTON);
        Color newColor = convertToAWTColor(selenium.getValue(LOC_COLOR_INPUT));
        assertEquals(newColor, original, "Color in input should not change after clicking on \"Cancel\"");

        Map<Color, Integer> histogram = getHistogram();
        assertTrue(histogram.containsKey(original), "Color of image should not change after clicking on \"Cancel\"");
    }

    /**
     * Tests slider. It clicks into the rainbow on [0,0] and checks that values
     * of RGB changed. Then it clicks at the bottom of rainbow and checks values
     * of RGB. In the end, it clicks in the middle of the rainbow and checks RGB
     * values. There is tolerance of 2.
     */
    @Test
    public void testSlider() {
        selenium.click(LOC_COLOR_BUTTON);
        selenium.mouseDownAt(LOC_RAINBOW, "0,0");
        selenium.mouseUp(LOC_RAINBOW);

        String newColor = selenium.getValue(LOC_HEX_COLOR);
        assertEquals(newColor, MSG_SLIDER_HEXCOLOR_1, "Color should change to dark red after clicking on [0,0].");

        int red = Integer.parseInt(selenium.getValue(LOC_RED_VALUE));
        int green = Integer.parseInt(selenium.getValue(LOC_GREEN_VALUE));
        int blue = Integer.parseInt(selenium.getValue(LOC_BLUE_VALUE));

        assertTrue(Math.abs(red - MSG_SLIDER_RED_VALUE_1) < 7, format(
                "Red should be {0}+-6 (was {1}) when the slider is at the top.", MSG_SLIDER_RED_VALUE_1, red));
        assertTrue(Math.abs(green - MSG_SLIDER_GREEN_VALUE_1) < 7, format(
                "Green should be {0}+-6 (was {1}) when the slider is at the top.", MSG_SLIDER_GREEN_VALUE_1, green));
        assertTrue(Math.abs(blue - MSG_SLIDER_BLUE_VALUE_1) < 7, format(
                "Blue should be {0}+-6 (was {1}) when the slider is at the top.", MSG_SLIDER_BLUE_VALUE_1, blue));

        selenium.mouseDownAt(LOC_RAINBOW, "0,151");
        selenium.mouseUp(LOC_RAINBOW);

        newColor = selenium.getValue(LOC_HEX_COLOR);
        assertEquals(newColor, MSG_SLIDER_HEXCOLOR_2, "Color should change to dark red after clicking on [0,150].");

        red = Integer.parseInt(selenium.getValue(LOC_RED_VALUE));
        green = Integer.parseInt(selenium.getValue(LOC_GREEN_VALUE));
        blue = Integer.parseInt(selenium.getValue(LOC_BLUE_VALUE));

        assertTrue(Math.abs(red - MSG_SLIDER_RED_VALUE_2) < 7, format(
                "Red should be {0}+-6 (was {1}) when the slider is at the bottom.", MSG_SLIDER_RED_VALUE_2, red));
        assertTrue(Math.abs(green - MSG_SLIDER_GREEN_VALUE_2) < 7, format(
                "Green should be {0}+-6 (was {1}) when the slider is at the bottom.", MSG_SLIDER_GREEN_VALUE_2, green));
        assertTrue(Math.abs(blue - MSG_SLIDER_BLUE_VALUE_2) < 7, format(
                "Blue should be {0}+-6 (was {1}) when the slider is at the bottom.", MSG_SLIDER_BLUE_VALUE_2, blue));

        selenium.mouseDownAt(LOC_RAINBOW, "0,75");
        selenium.mouseUp(LOC_RAINBOW);

        red = Integer.parseInt(selenium.getValue(LOC_RED_VALUE));
        green = Integer.parseInt(selenium.getValue(LOC_GREEN_VALUE));
        blue = Integer.parseInt(selenium.getValue(LOC_BLUE_VALUE));

        assertTrue(Math.abs(red - MSG_SLIDER_RED_VALUE_3) < 7, format(
                "Red should be {0}+-6 (was {1}) when the slider is in the middle.", MSG_SLIDER_RED_VALUE_3, red));
        assertTrue(Math.abs(green - MSG_SLIDER_GREEN_VALUE_3) < 7, format(
                "Green should be {0}+-6 (was {1}) when the slider is in the middle.", MSG_SLIDER_GREEN_VALUE_3, green));
        assertTrue(Math.abs(blue - MSG_SLIDER_BLUE_VALUE_3) < 7, format(
                "Green should be {0}+-6 (was {1}) when the slider is in the middle.", MSG_SLIDER_BLUE_VALUE_3, blue));

        assertTrue(Math.abs(red - MSG_SLIDER_RED_VALUE_3) < 7, format(
                "Red should be 0 (+-6) when the slider is in the middle (was {0}).", red));
        assertTrue(Math.abs(green - MSG_SLIDER_GREEN_VALUE_3) < 7, format(
                "Green should be 230 (+-6) when the slider is in the middle (was {0}).", green));
        assertTrue(Math.abs(blue - MSG_SLIDER_BLUE_VALUE_3) < 7, format(
                "Blue should be 230 (+-6) when the slider is at the bottom (was {0}).", blue));
    }

    /**
     * Tests the color box on the left. It changes RGB values to 100, 100, 100
     * and checks that the color of the box changed.
     */
    @Test
    public void testLeftColorBox() {
        Color leftBoxColor = convertToAWTColor(getStyle(LOC_CURRENT_COLOR_BOX, "background-color"));
        Color expectedColor = convertToAWTColor("#" + selenium.getValue(LOC_HEX_COLOR));

        assertEquals(leftBoxColor, expectedColor, "At the beginning box should be of color from input field.");

        selenium.type(LOC_RED_VALUE, "100");
        selenium.type(LOC_GREEN_VALUE, "100");
        selenium.type(LOC_BLUE_VALUE, "100");

        leftBoxColor = convertToAWTColor(getStyle(LOC_CURRENT_COLOR_BOX, "background-color"));
        expectedColor = convertToAWTColor("#" + selenium.getValue(LOC_HEX_COLOR));

        assertEquals(leftBoxColor, expectedColor, "Color of the box should change afted a color was chosen.");
    }

    /**
     * Tests the color box on the right. It changes RGB values to 100, 100, 100
     * and checks that the color of the box did not change.
     */
    @Test
    public void testRightColorBox() {
        Color rightBoxColor = convertToAWTColor(getStyle(LOC_ORIGINAL_COLOR_BOX, "background-color"));
        Color expectedColor = convertToAWTColor("#" + selenium.getValue(LOC_HEX_COLOR));

        assertEquals(rightBoxColor, expectedColor, "At the beginning box should be of color from input field.");

        selenium.type(LOC_RED_VALUE, "100");
        selenium.type(LOC_GREEN_VALUE, "100");
        selenium.type(LOC_BLUE_VALUE, "100");

        rightBoxColor = convertToAWTColor(getStyle(LOC_ORIGINAL_COLOR_BOX, "background-color"));
        expectedColor = convertToAWTColor("#" + selenium.getValue(LOC_HEX_COLOR));

        assertNotSame(rightBoxColor, expectedColor, "Color of the box should change afted a color was chosen.");
    }

    /**
     * Tests the "View Source". It checks that the source code is not visible,
     * clicks on the link, and checks 8 lines of source code.
     */
    @Test
    public void testPageSource() {
        String[] strings = new String[] { "<ui:composition xmlns=\"http://www.w3.org/1999/xhtml\"", "<h:form>",
                "<h:panelGrid columns=\"1\">", "<rich:paint2D id=\"painter\" width=\"300\" height=\"120\"",
                "data=\"#{paintData}\" format=\"png\" paint=\"#{paintBean.paint}\" >", "</rich:paint2D>",
                "<h:outputText value=\"Change text color \" />",
                "<rich:colorPicker colorMode=\"hex\" value=\"#{paintData.color}\">", };

        abstractTestSource(1, "View Source", strings);
    }

    /**
     * Creates a histogram of colors contained in the image. It reads 6 lines of
     * the image where letters, shadow and background should be present.
     * 
     * @return map containg each color of the image and how many pixels are of
     *         that color
     */
    private Map<Color, Integer> getHistogram() {
        final String src = selenium.getAttribute(LOC_IMAGE + "@src");
        final String location = selenium.getLocation();
        String url = null;
        try {
        	url = URLUtils.buildUrl(location, src);
        } catch (MalformedURLException e) {
        	e.printStackTrace();
        	fail(format("Could not build an URL from location '{0}' and src '{1}'", location, src));
        }

        BufferedImage image = null;
        try {
            image = ImageIO.read(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            fail("Could not create an URL object.");
        } catch (IOException e) {
            e.printStackTrace();
            fail("Could not read image.");
        }

        Map<Integer, Integer> result = new HashMap<Integer, Integer>();

        int width = image.getWidth();
        int color = 0;

        for (int y = 95; y < 101; y++) {
            for (int x = 0; x < width; x++) {
                color = image.getRGB(x, y);
                if (result.containsKey(color)) {
                    result.put(color, result.get(color) + 1);
                } else {
                    result.put(color, 1);
                }
            }
        }

        Map<Color, Integer> colors = new HashMap<Color, Integer>();
        for (int key : result.keySet()) {
            int red = (key & 0x00ff0000) >> 16;
            int green = (key & 0x0000ff00) >> 8;
            int blue = key & 0x000000ff;
            colors.put(new Color(red, green, blue), result.get(key));
        }

        return colors;
    }

    /**
     * Loads the page containing the color picker component.
     */
    protected void loadPage() {
        openComponent("Color Picker");
        scrollIntoView(LOC_COMPONENT_HEADER, true);
    }

}
